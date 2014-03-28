jQuery(document).ready(function(){
    setEvents();

    if($("#query-form").length){
        setTabs();
        var locale = $("#query-form").find(".validateForm").data("locale");
        setValidator(locale);
    }
});

function setEvents() {
    $("#createNewQuery").on("click", function () {
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

    $(document).on("click",".edit-query",function(){
        var url = $("#queryFormEdit").val();
        var data = {
            appId: $(this).text()
        };
        $.ajax({
            type:"POST",
            url:url,
            data:data,
            success:function(data){
                $("#queues-view").html(data);
                setTabs();
                var locale = $("#query-form").find(".validateForm").data("locale");
                setValidator(locale);
                $("#modal-window").remove();
            }
            ,error: function(){
                $("#modal-window").remove();
            }
        })
    });
}