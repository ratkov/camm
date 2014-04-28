function setValidator(locale){
    setValidationConfig(locale);

    $(".validateForm").each(function() {
        var form = $(this);

        var v = form.validate({
            rules:{
                passportDateDay:{passportDate:true},
                passportDateMonth:{passportDate:true},
                passportDateYear:{passportDate:true}
            },
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
                $(".validateForm").each(function() {
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


        $(form).on("click", ".nextStep", function(event) {
            event.preventDefault();
            $(form).valid();
            if($(form).valid()) {
                var tab = $(event.target).parents("fieldset");
                tab.addClass('success');
                var nextIndex = tab.index() + 1;
                if($(form).find(".t_item").length && nextIndex < $(form).find(".t_item").length){
                    var nextTab = $(form).find(".t_item").get(nextIndex);
                    $(nextTab).find("a").removeClass("disable").trigger("click");
                }else{
                    offSelectEvents();
                    offCheckboxEvent();
                    offChangeFieldEvent();

                    $(document.body).append("<div id='modal-window' style='display: block;'></div>");
                    var url = $("#backToMain").val();
                    var data = {
                        partnerId: $("#partnerId").val()
                    };
                    viewMode(url, data, false);
                }
            } else {
                if($(event.target).parents(".t_content").length > 0)
                    $(event.target).parents(".t_content").removeClass('success');

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
    setMasks(".validateForm");
}

function isJsonString(str) {
    try {
        JSON.parse(str);
    } catch (e) {
        return false;
    }
    return true;
}