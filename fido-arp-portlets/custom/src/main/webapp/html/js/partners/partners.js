PartnersProcessor = Class.extend({

    init: function (namespace) {
        this.namespace = namespace;

        var $this = this;

        $this.setValidator();

        $(document).on("click", ".editStatus", function (e) {
            $(document.body).append("<div id='modal-window' class='ui-widget-overlay ui-front' style='display: block;'></div>");
            var url = $("#addUser").val();
            var data = {
                userId: $(this).data("id"),
                partnerId: $(this).data("partner")
            };
            $this.viewMode(url, data);
        });

        $(document).on('click', ".change-status", function (e) {
            e.preventDefault();

            $('#' + namespace + 'dialog-status').html("");
            var $statusDialogHtml = $('#' + namespace + 'dialog-status-script').html();
            var container = $($statusDialogHtml);
            var title = $("#" + $this.namespace + "title-dialog").val() + $(this).data("name");

            $(container).dialog({
                closeOnEscape: false,
                title: title,
                appendTo: '#' + namespace + 'dialog-status'
            });

            $(container).find("#"+$this.namespace+"partnerId").val($(this).data("partner"));
            $(container).find("input[type='radio'][value='"+$(this).data("status")+"']").attr("checked","checked");
        });

        if ($(".pagination").length) {
            $(document).on("click", ".pagination a", function (e) {
                if ($(this).parent().hasClass("disabled"))
                    return;

                e.preventDefault();
                $(document.body).append("<div id='modal-window' class='ui-widget-overlay ui-front' style='display: block;'></div>");

                var url = $("#"+ $this.namespace +"paginator").val(),
                data = {
                    cpage: $(this).data("page")
                };

                $this.viewMode(url, data);
            });
        }

    },

    setValidator: function(){
        var $this = this;

        var locale = $("#"+ $this.namespace +"partner-view").find(".validationForm").data("locale");

        if(!!locale && locale.length){
            setValidationConfig(locale);

            $(".validationForm").each(function() {
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

                $(form).on("click", "#"+ $this.namespace +"add-partner", function(event) {
                    event.preventDefault();
                    $(form).valid();
                    if($(form).valid()) {
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
    },

    submitFormAjax: function (form, link) {
        var $this = this;

        if (form.valid() && link.attr("disabled") != "disabled") {

            link.attr("disabled", "disabled");
            form.ajaxSubmit({
                type: "POST",
                success: function (data) {
                    var parsedMessage = $.parseJSON($.parseJSON(data).resultMap);

                    if (parsedMessage.length == 0) {
                        window.location.reload();

                    } else {
                        $('#' + $this.namespace + 'addUserError')
                            .empty()
                            .append(parsedMessage[0].value).show().addClass('alert alert-danger');

                        link.removeAttr("disabled");
                    }

                }
            });
        }
    },

    viewMode: function (url, data) {
        var $this = this;

        $.ajax({
            type: "POST",
            url: url,
            data: data,
            success: function (data) {
                $("#"+ $this.namespace +"partner-view").html(data);
                $("#modal-window").remove();
            },
            error: function () {
                $("#modal-window").remove();
            }
        });
    }

});