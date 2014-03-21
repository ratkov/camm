jQuery(document).ready(function(){
    addEvents();
});

function addEvents() {
    jQuery(document).on("change", "#partner", function () {
        $(document.body).append("<div id='modal-window' class='ui-widget-overlay ui-front' style='display: block;'></div>");
        var url = $(this).data("url");
        var data = {
            partnerId: $(this).val()
        };

        viewMode(url, data, false);
    });

    jQuery(document).on("click", "#add-product-type", function () {
        $(document.body).append("<div id='modal-window' class='ui-widget-overlay ui-front' style='display: block;'></div>");
        var url = $("#editProduct").val();
        var data = {
            partnerId: $("#partner").val(),
            productTypeId: null
        };
        viewMode(url, data, true);

    });

    jQuery(document).on("click", ".editProduct", function (e) {
        $(document.body).append("<div id='modal-window' class='ui-widget-overlay ui-front' style='display: block;'></div>");
        var url = $("#editProduct").val();
        var data = {
            partnerId: $(this).data("partner"),
            productTypeId: $(this).data("id")
        };
        viewMode(url, data, true);
    });

    jQuery(document).on("click", ".changeStatus", function (e) {
        $(document.body).append("<div id='modal-window' class='ui-widget-overlay ui-front' style='display: block;'></div>");
        var url = $("#changeStatus").val();
        var data = {
            partnerId: $("#partner").val(),
            productTypeId: $(this).data("id")
        };
        viewMode(url, data, false);
    });

    jQuery(document).on("click", ".previewProduct", function (e) {
        $(document.body).append("<div id='modal-window' class='ui-widget-overlay ui-front' style='display: block;'></div>");
        var url = $("#previewProduct").val();
        var data = {
            partnerId: $("#partner").val(),
            productTypeId: $(this).data("id")
        };
        viewMode(url, data, true);
    });
}

function viewMode(url, data, validate) {
    $.ajax({
        type: "POST",
        url: url,
        data: data,
        success: function (data) {
            $("#partnerWrapper").html(data);
            $("#modal-window").remove();
            setTabs();
            if(validate)
                setValidation();
        }, error: function () {
            $("#modal-window").remove();
        }
    });
}

function styling(){

}

function setTabs(){
    if($(".quest-template").length){
        if($(".quest-template").find(".t_content").length > 0){
            $(".quest-template").liTabs({
                block: true,
                equal: true,
                onClick:function(){
                    disablePrev(".quest-template");
                    loadDictionary();
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

function dateFromISO8601(isostr) {
    var parts = isostr.match(/\d+/g);
    return new Date(parts[0], parts[1] - 1, parts[2], parts[3], parts[4], parts[5]);
}