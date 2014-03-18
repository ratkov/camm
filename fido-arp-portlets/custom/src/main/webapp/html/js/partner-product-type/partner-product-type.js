jQuery(document).ready(function(){
   jQuery(document).on("click","#add-product-type",function(){
       $(document.body).append("<div id='modal-window' class='ui-widget-overlay ui-front' style='display: block;'></div>");
       var url = $(this).data("url");
       var data = {
           partnerId: $("#partner").val(),
           productTypeId: null
       };
       $.ajax({
           type:"POST",
           url:url,
           data:data,
           success:function(data){
               $("#partnerWrapper").html(data);
               $("#modal-window").remove();

               setValidation();
           }
           ,error: function(){
               $("#modal-window").remove();
           }
       });

   });

});

function setValidation(){
    $.validator.setDefaults({
        focusInvalid: false
    });

    $(".validateForm").each(function() {
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
                $(element).closest('.form-group').addClass('has-error');
                $(element).addClass('error');
            },
            unhighlight: function(element) {
                $(element).closest('.form-group').removeClass('has-error');
                $(element).removeClass('error');
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
        $(form).on("click", ".submit", function(event) {
            event.preventDefault();
            $(form).valid();
            if($(form).valid()) {
                $(document.body).append("<div id='modal-window' class='ui-widget-overlay ui-front' style='display: block;'></div>");

                var listCheckbox = $(form).find('input[type=checkbox]:not(:checked)');
                var jsonCheckbox = {};
                var formJson = $(form).serializeObject();
                if(listCheckbox.length > 0){
                    listCheckbox.each(function(){
                        jsonCheckbox[$(this).attr("name")] = $(this).val();
                    });

                    // re-disabled the set of inputs that you previously enabled
                    formJson = $.extend( $(form).serializeObject(), jsonCheckbox);
                }

                var data = formJson;
                var url = $(form).attr("action");
                $.ajax({
                    type:"POST",
                    url:url,
                    data:data,
                    success:function(data){
                       $("#partnerWrapper").html(data);
                       $("#modal-window").remove();
                    }
                    ,error: function(){
                       $("#modal-window").remove();
                    }
                });
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