jQuery(document).ready(function(){
    setEvents();

    if($("#query-form").length){
        setTabs();
        var locale = $("#query-form").find(".validateForm").data("locale");
        setValidator(locale);
    }
});
function setEvents() {
    $(document).on("click","#createNewQuery", function () {
        var $dialog = $("#dialog");
        $dialog.html($("#form-select-templates").html())
            .dialog({
                modal: true
            });

        if ($('.pretCheck').length) {
            $('.pretCheck').prettyCheckable();
        }
        if ($('.pretRad').length) {
            $('.pretRad').prettyCheckable();
        }
    });

    setEditEvent();

    setSearchEvents();

    setPaginationEvents();

    setExportExcelEvent();
}

function setSearchEvents(){
    $("#showSearch").on("click", function(){
        var id = $(this).data("show");
        var $element = $("#"+id);
        if($element.hasClass("show")){
            $("#isSearch").val(false);
            $element.removeClass("show");
        }else{
            $("#isSearch").val(true);
            $element.addClass("show");
        }
    });

    $( "#from" ).datepicker({
        defaultDate: "+1w",
        changeMonth: true,
        numberOfMonths: 3,
        regional:["uk"],
        dateFormat:"dd.mm.yy",
        onClose: function( selectedDate ) {
            $( "#to" ).datepicker( "option", "minDate", selectedDate );
        }

    });
    $( "#to" ).datepicker({
        defaultDate: "+1w",
        changeMonth: true,
        numberOfMonths: 3,
        regional:["uk"],
        dateFormat:"dd.mm.yy",
        onClose: function( selectedDate ) {
            $( "#from" ).datepicker( "option", "maxDate", selectedDate );
        }
    });
}

function setExportExcelEvent() {
    if ($("#exportExcel").length) {
        $("#exportExcel").on("click", function () {
            var formParams = $("#search-box form").serialize();
            var url = $(this).data("url");
            window.location.href = url + "&" + formParams;
        });
    }
}


function setPaginationEvents() {
    if ($(".pagination").length) {
        $(".pagination").on("click", "a", function () {
            if ($(this).parent().hasClass("disabled"))
                return;

            $(document.body).append("<div id='modal-window' style='display: block;'></div>");

            var url = $("#paginator").val();
            var json = {
                cpage: $(this).data("page")
            };
            var formJson = $("#search-box form").serializeObject();
            formJson = $.extend(formJson, json);
            $.ajax({
                type: "POST",
                url: url,
                data: formJson,
                success: function(data) {
                    $("#queues-view").html(data);
                    $("#modal-window").remove();
                    setEvents();
                }, error: function () {
                    $("#modal-window").remove();
                }
            })
        });
    }
}

function setEditEvent() {
    $(document).on("click", ".edit-query", function () {
        var url = $("#queryFormEdit").val();
        $(document.body).append("<div id='modal-window' style='display: block;'></div>");
        var data = {
            appId: $(this).text(),
            cpage: $("#cpage").val()
        };
        $.ajax({
            type: "POST",
            url: url,
            data: data,
            success: function (data) {
                $("#queues-view").html(data);
                setTabs();
                var locale = $("#query-form").find(".validateForm").data("locale");
                setValidator(locale);
                $("#modal-window").remove();
            }, error: function () {
                $("#modal-window").remove();
            }
        })
    });
}