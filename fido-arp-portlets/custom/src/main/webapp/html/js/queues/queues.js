function showProductsType() {
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
}
jQuery(document).ready(function(){
   showProductsType();

    if($("#query-form").length > 0){
        setTabs();
        setValidator($("#query-form").find(".validateForm").data("locale"));
    }
});
