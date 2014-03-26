jQuery(document).ready(function(){

    addStatusEvents();
});

function addStatusEvents() {
    jQuery(document).on("click", "#add-app-status", function (e) {
        $(document.body).append("<div id='modal-window' class='ui-widget-overlay ui-front' style='display: block;'></div>");
        var url = $("#editAppStatus").val();
        var data = {
            appStatusId: null
        };
        viewStatusMode(url, data, true);

    });

    jQuery(document).on("click", ".edit-app-status", function (e) {
        $(document.body).append("<div id='modal-window' class='ui-widget-overlay ui-front' style='display: block;'></div>");
        var url = $("#editAppStatus").val();
        var data = {
            appStatusId: $(this).data("id")
        };
        viewStatusMode(url, data, true);
    });
}

function viewStatusMode(url, data, validate) {
    $.ajax({
        type: "POST",
        url: url,
        data: data,
        success: function (data) {
            $("#appStatusWrapper").html(data);
            $("#modal-window").remove();
            if(validate){
                var locale = $(".validateEditForm").data("locale");
                setValidatorStatus(locale);
            }
        }, error: function () {
            $("#modal-window").remove();
        }
    });
}

