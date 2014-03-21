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
            selectEvents();
            if(validate){
                var locale = $(".validateForm").data("locale");
                setValidator(locale);
            }
        }, error: function () {
            $("#modal-window").remove();
        }
    });
}