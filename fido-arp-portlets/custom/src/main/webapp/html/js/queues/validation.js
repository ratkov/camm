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
        $(form).on("submit", function(e) {
            e.preventDefault();
            return false;
        }).on("keydown keypress keyup", function(e) {
            var KEY_ENTER = 13;
            if (e.keyCode == KEY_ENTER) {
                e.preventDefault();
                return false;
            }
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

                    var listCheckbox = $(form).find('input[type=checkbox]:not(:checked)');

                    var jsonCheckbox = {};
                    var formJson = $(form).serializeObject();

                    // Find disabled inputs, and remove the "disabled" attribute
                    var disabled = $(form).find(':input:disabled, select:disabled').removeAttr('disabled');

                    if(listCheckbox.length > 0){
                        listCheckbox.each(function(){
                            jsonCheckbox[$(this).attr("name")] = $(this).val();
                        });

                        // re-disabled the set of inputs that you previously enabled
                        formJson = $.extend(formJson, jsonCheckbox);
                    }

                    disabled.attr('disabled','disabled');

                    var data = {
                        json :  JSON.stringify(formJson)
                    };
                    var url = $(form).data("url");
                    $.ajax({
                        type:"POST",
                        url:url,
                        data:data,
                        success:function(data){
                            $("#queues-view").html(data);
                            styling();
                            selectEvents();
                            //for selects
                            loadDictionary();
                            checkboxEvent();
                            showTip();
                            applyDate();
                            offChangeFieldEvent();
                            setEvents();
                            $("#modal-window").remove();
                        }
                        ,error: function(){
                            $("#modal-window").remove();
                        }
                    })
                }
            } else {
                if($(event.target).parents(".t_content").length > 0)
                    $(event.target).parents(".t_content").removeClass('success');
//                v.focusInvalid();
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