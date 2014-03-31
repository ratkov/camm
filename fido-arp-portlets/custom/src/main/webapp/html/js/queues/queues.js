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

    setPaginationEvents();
}


function setPaginationEvents() {
    if ($(".pagination").length) {
        $(".pagination").on("click", "a", function () {
            if ($(this).parent().hasClass("disabled"))
                return;

            var url = $("#paginator").val();
            var data = {
                cpage: $(this).data("page")
            };
            $.ajax({
                type: "POST",
                url: url,
                data: data,
                success: function (data) {
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