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
                     console.log(data);                }
           }
           , "json"
           );
	})
})

function getApplyForm(id){
	actionUrl = '/wegether/activity/apply/'+id;
	
    $.getJSON(
        "/wegether/activity/applyform/"+id
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
    var title = $('<p/>').text(question.title);
    div.html(title);
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
    var input = $('<textarea/>')
        .attr('name', question.name);
    div.append(input);
}

function text(question, div) {
    var input = $('<input/>')
        .attr('type', question.type)
        .attr('name', question.name);
    div.append(input);
}

function radio(question, div) {
	console.log(JSON.stringify(question));
    $.each(question.options, function (i, option) {
    	var key = Object.keys(option)[0]
        var input = $('<input/>')
            .attr('type', question.type)
            .attr('name', question.name)
            .val(key);
        div.append(input, option[key]+"<br>");
    })
}

function checkbox(question, div) {
    $.each(question.options, function (i, option) {
    	var key = Object.keys(option)[0];
        var input = $('<input/>')
            .attr('type', question.type)
            .attr('name', key)
            .val(1);
        div.append(input, option[key] + "<br>");
    })
}