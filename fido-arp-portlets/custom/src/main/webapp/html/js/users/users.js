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
            height: 250,
            width: 450,
            buttons: [
                {
                    id: "ok",
                    click: function (e) {
                        $form.ajaxSubmit({
                            success: function (data) {
                                var parsedMessage = $.parseJSON($.parseJSON(data).resultMap),
                                    $statusForm = $('#' + namespace + 'statusForm'),
                                    $content = $('#' + namespace + 'successContent');

                                if (parsedMessage.length == 0) {
                                    $statusForm.hide();
                                    $content.show();
                                } else {
                                    $('#' + $this.namespace + 'changeStatusError')
                                        .empty()
                                        .append(parsedMessage[0].value).show().addClass('error');
                                }
                            }
                        });
                    }
                },
                {
                    id: "cancel",
                    click: function () {
                        window.location.reload();
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

        $(document).on('click','#' + namespace + 'showUserForm', function(e) {
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
                $link = $('#' + namespace + 'addUser');

            $this.submitFormAjax($userForm, $link);

        }, this));


        $(document).on('click', '#' + namespace + "usersSearchContainer table td[id*='col-user.status']", function (e) {
            e.preventDefault();

            $statusDialog.dialog("open");

            var $loginName = $(e.target).parents("tr").find("td[id*='col-user.login'] ").text().trim(),
                $userId = $(e.target).parents("tr").find("td[id*='col-id'] ").text().trim(),
                $dialogTitle = $('.ui-dialog-title'),
                $inputIdField = $('#' + namespace + 'userId');

            $dialogTitle.append($loginName);
            $inputIdField.val($userId);

        });

//        $('#' + namespace + 'usersSearchContainer table').on('click', "td[id*='col-user.password.recovery']", function (e) {
//            e.preventDefault();
//
//            var $url = $('#' + namespace + 'passwordUrl').val();
//
//            $.ajax({
//                url: $url,
//                type: "POST",
//                success: function (data) {
//
//                }
//
//            });
//
//        });

    },

    submitFormAjax: function (form, link) {
        var $this = this;

        if (form.valid() && link.attr("disabled") != "disabled") {

            link.attr("disabled", "disabled");
            form.ajaxSubmit({
                type: "POST",
                success: function (data) {
                    var parsedMessage = $.parseJSON($.parseJSON(data).resultMap);

                    if (parsedMessage.length == 0) {
                        window.location.reload();

                    } else {
                        $('#' + $this.namespace + 'addUserError')
                            .empty()
                            .append(parsedMessage[0].value).show().addClass('error');

                        link.removeAttr("disabled");
                    }

                }
            });
        }
    }
});