/*You must to use /js/jquery.validate.js from portlet because it uses patch*/
var error = {
    "en_US": {
        required: "This field is required."
    },
    "ru_RU":{
        required: "Заполни поле"
    },
    "uk_UA":{
        required: "Заповни поле"
    }
};

function setValidatorStatus(locale){
    /* validator */
    jQuery.extend(jQuery.validator.messages, {
        required: error[locale].required
    });

    $.validator.setDefaults({
        focusInvalid: false
    });

    $(".validateEditForm").each(function() {
        var form = $(this);

        var v = form.validate({
            errorElement: "span",
            showErrors: function(errorMap, errorList) {
                if (errorList.length) {
                    var s = errorList.shift();
                    var n = [];
                    n.push(s);
                    this.errorList = n;
                }
                this.defaultShowErrors();
            },
            highlight: function(element) {
                $(element).closest('.regRow').addClass('errorP');
                if ($(element).hasClass('sel')) {
                    $(element).parent().children('.sbHolder').addClass('error');
                } else {
                    $(element).addClass('error');
                }
            },
            unhighlight: function(element) {
                $(element).closest('.regRow').removeClass('errorP');
                $(element).removeClass('error');
                $(element).parent().children('.sbHolder').removeClass('error');
            },
            invalidHandler: function(form, validator) {
                $(".validateEditForm").each(function() {
                    $(this).addClass('invalidForm');
                });
            }
        });
        $(form).on("keydown keypress keyup", function(e) {
            var KEY_ENTER = 13;
            if (e.keyCode == KEY_ENTER) {
                e.preventDefault();
                return false;
            }
        });

        $(form).on("click", ".submit", function(event) {
            event.preventDefault();
            $(form).valid();
            if($(form).valid()) {
                $(".flag-selector").click();
                $(form).submit();
            } else {
                if (!v.numberOfInvalids())
                    return;

                v.errorList[0].element.focus();
                if($(v.errorList[0].element).offset().top < 0){
                    $(window).scrollTop( $(v.errorList[0].element).next().offset().top )
                } else {
                    $(window).scrollTop( $(v.errorList[0].element).offset().top )
                }
            }
            return false;
        });

    });
}