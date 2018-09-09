var script = document.createElement('script');
script.src = 'https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js';
script.type = 'text/javascript';
document.getElementsByTagName('head')[0].appendChild(script);

var template;
var formDiv_applyForm;
var formJson;
var typeText = {
    "text": "簡答",
    "textarea": "詳答",
    "date": "年/月/日",
    "time": "時間",
}
$(document).ready(function () {
    template = $("#template").hide();
    formDiv_applyForm = $("#formDiv_applyForm");
    formDiv_applyForm.on("change", ".selectDiv_applyForm > select", function () {
        var value = this.value;
        var inputDD = $(this.parentNode).siblings('.inputDiv_applyForm').children('div');
        switch (value) {
            case "radio":
            case "select":
            case "checkbox":
                inputDD.html('<ol>' + inputOption() +
                    '</ol><button type="button" class="addOption">新增選項</button>');
                checkOnly(inputDD.children('ol'));
                break;
            default:
                inputDD.html(inputText(typeText[value]));
                break;
        }

    });
    formDiv_applyForm.on("click", ".inputDiv_applyForm div .addOption", function () {
        $(this).prev().append(inputOption());
        checkOnly($(this).prev());
    })
    addInput();

    $(".add").click(addInput);

    formDiv_applyForm.on('click', '.delete_applyForm', function () {
        var p = this.parentNode;
        var pp = p.parentNode;
        $(p).remove();
        checkOnly(pp);
    });
    $('#save').click(function () {
        var actForm = {};
        actForm.questions = [];
        $.each(formDiv_applyForm.children('div'), function (i, e) {
            var q = {};
            var ele = $(e);
            q.title = ele.find('.inputDiv_applyForm :text').val();
            q.type = ele.find('.selectDiv_applyForm select').val();
            q.required = ele.find('.selectDiv_applyForm :checkbox').prop('checked');
            q.name = q.type + i.toString();
            switch (q.type) {
                case "radio": case "select": case "checkbox":
                    q.options = {};
                    $.each(ele.find('.inputDiv_applyForm ol input'), function (i, e) {
                        q.options[q.name + "_" + i] = e.value;
                    })
            }
            actForm.questions.push(q);
        })
        formJson = JSON.stringify(actForm);
        console.log(formJson);
    });
})

function addInput() {
    var insert = template.clone().removeAttr("id").show();
    formDiv_applyForm.append(insert);
    checkOnly(formDiv_applyForm);
}
function checkOnly(div) {
    var del = $(div).children().children('.delete_applyForm');
    if (del.length == 1) {
        del.css('visibility', 'hidden');
    } else {
        del.css('visibility', 'visible');
    }
}
function inputText(str) {
    return "<p>" + str + "</p>";
}
function inputOption() {
    return '<li><input type="text" placeholder="選項">' +
        '<span class="delete_applyForm">&times;</span>' +
        '</li>';
}