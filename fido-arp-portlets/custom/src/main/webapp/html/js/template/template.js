function removeErrorInWrapper(){
    var $selectWrapp = $(".jq-selectbox-wrapper");
    if($selectWrapp.length > 0){
        var newVar = $selectWrapp.find("span").is(":hidden");
        if(newVar.length > 0) newVar.remove();
    }
}

function styling(){
    if($('.pretCheck').length) {
        $('.pretCheck').prettyCheckable();
    }

    if($('.pretRad').length) {
        $('.pretRad').prettyCheckable();
    }

    removeErrorInWrapper();

    var stepCur = $("#step-section .rightContent > div").data("current");
    var index = 0;
    var $step = $("#step-menu");
    $step.find(".regStep").each(function(){
        if($(this).find("a").data("step") == stepCur){
            index = $(this).index();
        }
    });
    $step.find(".regStep").each(function(){
        if($(this).index() > index){
            $(this).find("a").addClass("disable");
            $(this).find("a").removeClass("cur");
        }else if($(this).index() == index){
            $(this).find("a").addClass("cur");
            $(this).off("click");
        }else{
            $(this).find("a").removeClass("disable");
            $(this).find("a").removeClass("cur");
        }
    });

    if ($('.sel').length) {
        $('.sel').styler({
            selectSearch: false
        });
    }
    if ($(".chose").length) {
        var $chose = $(".chose");
        var locale = $chose.parents("form").data("locale");
        $chose.chosen({
            width:"100%",
            no_results_text: chooserMessage[locale].message
        });
    }

    if( $("input[type='checkbox'][data-children='true']").length > 0 ){
        subchildShow($("input[type='checkbox'][data-children='true']"));
    }

    //for date
    applyDate();

    checkboxEvent();
}
function setTabs(){

    styling();

    if($(".quest-template").length){
        if($(".quest-template").find(".t_content").length > 0){
            $(".quest-template").liTabs({
                block: true,
                equal: false,
                onClick:function(){
                    disablePrev(".quest-template");
                    loadDictionary();
                    selectEvents();
                    removeErrorInWrapper();
                    applyDate();
                    var container = $("fieldset.t_content").not(":hidden");
                    checkShowFields(container);
                    offChangeFieldEvent();
                    onChangeFieldEvent();
                }
            });
        }
    }
}

function showTip(){
    $(".quest-template").on("mouseover", ".enterField", function(){
        $(this).find(".tip").show();
    }).on("mouseleave", ".enterField", function(){
        $(this).find(".tip").hide();
    });
}


function dateFromISO8601(isostr) {
    var parts = isostr.match(/\d+/g);
    return new Date(parts[0], parts[1] - 1, parts[2], parts[3], parts[4], parts[5]);
}


function checkShowFields($container){
    $container.find(".regRow").each(function(){
        if($(this).data("show")){
            var showIf = $(this).data("show");
            if($(this).find(".requiredIf").length == 0){
                var fields = [showIf];
                var isShow = false;

                if(showIf.indexOf(",") > -1){
                    fields = showIf.split(",");
                    isShow = true;
                    for(var i = 0; i < fields.length; i++){
                        isShow = detectFieldValue(fields[i], isShow, false);
                    }
                }else{
                    if(showIf.indexOf("|") > -1){
                        fields = showIf.split("|");
                    }
                    for(var j = 0; j < fields.length; j++){
                        isShow = detectFieldValue(fields[j], !isShow, true);
                    }
                }
                if($(this).hasClass("change-label")){
                    if(isShow) {
                        $(this).find(".label1").hide();
                        $(this).find(".label2").show();
                    } else {
                        $(this).find(".label1").show();
                        $(this).find(".label2").hide();
                    }
                } else {
                    if(isShow) {
                        $(this).show();
                        $(this).removeClass("hide");
                    } else {
                        $(this).hide();
                        $(this).addClass("hide");
                    }
                }
            }
        }
    });

    $container.find(".regTitle").each(function(i,v){
        var nextTitle = $(this).siblings(".regTitle").eq(i);
        var list = $(this).nextUntil(nextTitle, ".regRow");
        if(list.not(".hide").length == 0){
            $(this).hide();
        }else{
            $(this).show();
        }
    });
}


function checkRequiredField(regRow){

    var showIf = $(regRow).data("show");
    var fields = [showIf];
    var isShow = false;

    if(showIf.indexOf(",") > -1){
        fields = showIf.split(",");
        isShow = true;
        for(var i = 0; i < fields.length; i++){
            isShow = detectFieldValue(fields[i], isShow, false);
        }
    }else{
        if(showIf.indexOf("|") > -1){
            fields = showIf.split("|");
        }
        for(var j = 0; j < fields.length; j++){
            isShow = detectFieldValue(fields[j], !isShow, true);
        }
    }

    return isShow;
}

function detectFieldValue(field, isShow, retVal){
    var params = field.split(":");
    var nameField = "*[name='" + $.trim(params[0]) + "']";
    var valField = $.trim(params[1]);
    var detectField = $(nameField);

    if($(nameField).hasClass("hide")){
        return false;
    }

    if($(nameField).length > 1){
        $(nameField).each(function(){
            if($(this).is(":checked")){
                detectField = $(this);
            }
        });
    }

    if(isShow){
        if(valField.indexOf("not-empty") > -1){
            return !!detectField && detectField.val() != "";
        }else if(valField.indexOf("!") == 0){
            var valArr = valField.split("!");
            return !!detectField && !!detectField.val() && detectField.val() != valArr[1];
        }else{
            return !!detectField && detectField.val() == valField;
        }
    }

    return retVal;
}


function onChangeFieldEvent(){
    var container = $(".quest-template");
    container.on("change", "input, select, textarea", function(){
        checkShowFields(container);
    })
}

function offChangeFieldEvent(){
    var container = $(".quest-template");
    container.off("change", "input, select, textarea");
}


function checkboxEvent(){
    $(".quest-template").on("change", "input[type='checkbox']", function(){
        if($(this).is(":checked")){
            $(this).val("true");
            $(this).attr("value","true");
        }else{
            $(this).val("false");
            $(this).attr("value","false");
        }
    });

    $(".quest-template").on("change", "input[type='checkbox'][data-children='true']", function(){
        subchildShow(this);
    });
    $(".checkout-portlet").on("click", "#go-shopping", function(){
        var category = $("#categoryId").val();
        window.location.href = Liferay.ThemeDisplay.getURLHome() + "/category/-/category/" + category;
    });
    $(".checkout-portlet").on("change", "input[type='checkbox'].lockedForm", function(){
        lockedForm($(this));
    });
    $(".checkout-portlet").on("change", "input[type='checkbox'].disableFields", function(){
        disableFields($(this));
    });
    if($("input[type='checkbox'].lockedSet").length){
        lockedSet($("input[type='checkbox'].lockedSet"));
    }
    if($("input[type='checkbox'].lockedOne").length){
        lockedOne($("input[type='checkbox'].lockedOne"));
    }
    $(".checkout-portlet").on("change", "input[type='checkbox'].lockedSet", function(){
        lockedSet($(this));
    });
    $(".checkout-portlet").on("change", "input[type='checkbox'].lockedOne", function(){
        lockedOne($(this));
    });
}

function offCheckboxEvent(){
    $(".quest-template").off("change", "input[type='checkbox']");
    $(".quest-template").off("change", "input[type='checkbox'][data-children='true']");
    $(".checkout-portlet").off("click", "#go-shopping");
    $(".checkout-portlet").off("change", "input[type='checkbox'].lockedForm");
    $(".checkout-portlet").off("change", "input[type='checkbox'].lockedSet");
    $(".checkout-portlet").off("change", "input[type='checkbox'].lockedOne");
}



function lockedSet($check){
    if(!$check.is(":checked")){
        $check.parents(".enterField").find("input[type='checkbox'].lockedOne").each(function(){
            $(this).removeAttr("disabled");
            $(this).parent(".prettycheckbox").removeClass("disabled");
        });
    } else {
        $check.parents(".enterField").find("input[type='checkbox'].lockedOne").each(function(){
            $(this).attr("disabled","disabled");
            $(this).parent(".prettycheckbox").addClass("disabled");
        });
    }
}
function lockedOne($check){
    var isLocked = false;
    if(!$check.is(":checked")){
        $check.parents(".enterField").find("input[type='checkbox'].lockedOne").each(function(){
            if($(this).is(":checked")){
                isLocked = true;
            }
        });
    }else{
        isLocked = true;
    }

    if(isLocked){
        $check.parents(".enterField").find("input[type='checkbox'].lockedSet").each(function(){
            $(this).attr("disabled","disabled");
            $(this).parent(".prettycheckbox").addClass("disabled");
        });
    } else {
        $check.parents(".enterField").find("input[type='checkbox'].lockedSet").each(function(){
            $(this).removeAttr("disabled");
            $(this).parent(".prettycheckbox").removeClass("disabled");
        });
    }
}

function selectUpdate(select){
    var locale = $(".dict-load").parents("form").data("locale");
    if($(select).hasClass("chose")){
        var chosen = $(select).data('chosen');
        if(chosen){
            chosen.destroy();
        }
        $(select).chosen({
            width:"100%",
            no_results_text: chooserMessage[locale].message
        });
    }else{
        $(select).trigger("refresh")
    }
}

function subchildShow(checkbox) {
    var parents = $(checkbox).parents(".regRow");
    if ($(checkbox).is(":checked")) {
        parents.next(".children-list").show();
        loadDictionary();
    } else {
        parents.next(".children-list").hide();
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