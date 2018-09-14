var actionUrl;

var readed = 0;

function clickApplyForm(id){
	if(readed == 0){
		getApplyForm(id);
	}
	$('#applyForm').modal();
}

$(function(){
	$('body').on('click', '#sendApply',function(){
        message = $("#applyForm form").serialize();
		 $.post(
             actionUrl ,
             message, 
             function( data ) {
                 if(data.state){
                	 $('#applyForm').modal('hide');
                	 $('#applyState').modal().find('.modal-body').html($('<h4/>').text('報名成功'));
                 }else{
                	
                	 if(Object.keys(data.errors).length == 0){
                		 $('#applyForm').modal('hide');
                		 $('#applyState').modal().find('.modal-body').html($('<h4/>').text('報名失敗'));
                	 }else{
		            	 $.each(data.errors,function(error){
		            		 $('#'+error+'_error').addClass('error');
		            	 })
                	 }
                 }
           }
           , "json"
           ).fail(function(){ 
        	   $('#applyForm').modal('hide');
      		 	$('#applyState').modal().find('.modal-body').html($('<h4/>').text('請登入'));
           });
	})
})

function getApplyForm(id){
	actionUrl = '/wegether/Rest/activity/apply/'+id;
	
    $.getJSON(
        "/wegether/Rest/activity/applyform/"+id
        , function (data) {
            questions = data.questions;
            var form = $('<form/>');
            var txt = "";
            $.each(questions, function (i, v) {
                form.append(creatQ(v));
            })
            $('#applyForm .modal-body').html(form);
            readed = 1;
        }
    )
}

function creatQ(question) {
    var div = $('<div/>');
    var title = $('<label/>').text(question.title);
    div.append(title);
    var span = $('<span id="'+question.name+'_error"/>')
    if(question.required){
    	span.text("*必填");
    }
    div.append(span);
    switch (question.type) {
        case 'textarea':
            textarea(question, div);
            break;
        case "date": case "time": case "text":
            text(question, div);
            break;
        case "radio":
            radio(question, div);
            break;
        case "checkbox":
            checkbox(question, div);
            break;
        case "select":
            select(question, div);
            break;
    }
    return div;
}
function select(question, div) {
    var select = $('<select/>')
        .attr("name", question.name);
    var def = $('<option/>').text("請選擇").prop('disabled', 'disabled').prop("selected", true);
    select.append(def);
    $.each(question.options, function (i, option) {
    	var key = Object.keys(option)[0];
        var option = $('<option/>')
            .val(key)
            .text(option[key]);
        select.append(option);
    })
    div.append(select);
}


function textarea(question, div) {
    var input = $('<textarea placeholder="請輸入..."/>')
        .attr('name', question.name);
    div.append(input);
}

function text(question, div) {
    var input = $('<input placeholder="請輸入..."/>')
        .attr('type', question.type)
        .attr('name', question.name);
    div.append(input);
}

function radio(question, div) {
    $.each(question.options, function (i, option) {
        var key = Object.keys(option)[0]
        var p = $('<p/>');
        var input = $('<input/>')
            .attr('type', question.type)
            .attr('name', question.name)
            .val(key);
        var label = $('<label/>').text(option[key]);;
        p.append(input, label);
        div.append(p);
    })
}

function checkbox(question, div) {
    $.each(question.options, function (i, option) {
        var key = Object.keys(option)[0];
        var p = $('<p/>');
        var input = $('<input/>')
            .attr('type', question.type)
            .attr('name', key)
            .val(1);
        var label = $('<label/>').text(option[key]);
        p.append(input, label);
        div.append(p);
    })
}