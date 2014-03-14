jQuery(document).ready(function(){
   $("#createNewQuery").on("click", function(){
       $("#dialog").html( $("#form-select-templates").html() ).dialog();
   });

    formChanging();
});

function formChanging(){
    if($(".quest-template").length){
        if($(".quest-template").find(".t_content").length > 0){
            $(".quest-template").liTabs({
                block: true,
                equal: true,
                onClick:function(){
                    disablePrev(".quest-template");
                    removeErrorInWrapper();
                    var container = $("fieldset.t_content").not(":hidden");
                }
            });
        }
    }

    if($(".validateForm").length){
        var locale = $(".validateForm").data("locale");
        if(locale == null || locale == ""){
            locale = "uk_UA"
        }
//        setValidator(locale);
    }
}


function disablePrev($container){
    var currentIndex = $($container).find(".t_link.cur").parent().index();
    $($container).find(".t_item").each(function(){
        var index = $(this).index();
        if(currentIndex < index){
            $(this).find(".t_link").addClass("disable");
        }else{
            $(this).find(".t_link").removeClass("disable");
        }
    })
}

function removeErrorInWrapper(){
    var $selectWrapp = $(".jq-selectbox-wrapper");
    if($selectWrapp.length > 0){
        var newVar = $selectWrapp.find("span").is(":hidden");
        if(newVar.length > 0) newVar.remove();
    }
}