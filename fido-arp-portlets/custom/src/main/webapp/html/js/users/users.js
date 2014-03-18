UsersProcessor = Class.extend({

    init: function (namespace) {
        this.namespace = namespace;

        var $this = this,
            $statusDialog = $('#' + namespace + 'dialog-status'),
            $form = $('#' + namespace + 'statusForm');

        $statusDialog.dialog({
            closeOnEscape: false,
            modal: true,
            autoOpen: false,
            resizable: false,
            height: 150,
            width: 350,
            buttons: [
                {
                    id: "ok",
                    click: function (e) {
                        $form.ajaxSubmit({
                            success: function (data) {
                                $statusDialog.dialog("close");
                            }
                        });
                    }
                },
                {
                    id: "cancel",
                    click: function () {
                        $statusDialog.dialog("close");
                    }
                }
            ]

        });

        // Filter users
        $("#" + namespace + "partners").change(function (e) {

            var $partner = $("#" + namespace + "partners option:selected").val(),
                $url = $("#" + namespace + "partners").data("url");

            $.ajax({
                data: {
                    partnerId: $partner
                },
                type: "POST",
                url: $url,
                success: function (data) {
                    $('#' + namespace + 'userTable').empty().html(data);
                }

            });

        });

        $('#' + namespace + 'showUserForm').on('click', function(e) {
            e.preventDefault();

            var $userBlock = $('#' + namespace + 'addUserForm'),
                $form = $('#' + namespace + 'usersForm'),
                $addText = $('.add'),
                $cancelText = $('.cancel');

            if ($userBlock.is(":hidden")) {
                $userBlock.show();
                $addText.hide();
                $cancelText.show();

            } else {
                $('.error').empty();

                $form[0].reset();
                $userBlock.hide();
                $cancelText.hide();
                $addText.show();
            }
        });

        // Add new user
        $('#' + namespace + 'addUser').on('click', $.proxy(function (e) {
            e.preventDefault();

            var $userForm = $('#' + namespace + 'usersForm'),
                $partner = $("#" + namespace + "partners option:selected").val(),
                $link = $('#' + namespace + 'addUser');

            $this.submitFormAjax($userForm, $link);

        }, this));


        $('#' + namespace + 'usersSearchContainer').on('click', function (e) {
//            $form.dialog("open");
            e.preventDefault();
            alert($(e.target).parents("tr").text());
        });

    },

    submitFormAjax: function (form, link) {
        var $this = this;

        if (form.valid() && link.attr("disabled") != "disabled") {

            link.attr("disabled", "disabled");
            form.ajaxSubmit({
                type: "POST",
                success: function (data) {
                    var parsedErrors = $.parseJSON($.parseJSON(data).errorMap);

                    if (parsedErrors.length == 0) {
                        window.location.reload();

                    } else {
                        $('#' + $this.namespace + 'addUserError')
                            .empty()
                            .append(parsedErrors[0].value).show().addClass('error');

                        link.removeAttr("disabled");
                    }

                }
            });
        }
    }
});