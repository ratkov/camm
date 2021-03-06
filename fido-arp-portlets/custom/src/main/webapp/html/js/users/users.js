UsersProcessor = Class.extend({

    init: function (namespace) {
        this.namespace = namespace;

        var $this = this,
            $statusDialog = $('#' + namespace + 'dialog-status'),
            $passwordDialog = $('#' + namespace + 'dialog-password'),
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

                                    $('#ok').hide();
                                    $('#cancel').text("OK");

                                    $content.show();
                                } else {
                                    $('#' + $this.namespace + 'changeStatusError')
                                        .empty()
                                        .append(parsedMessage[0].value).show().addClass('alert alert-danger');
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

        $passwordDialog.dialog({
            closeOnEscape: false,
            modal: true,
            autoOpen: false,
            resizable: false,
            height: 250,
            width: 450,
            buttons: [
                {
                    id: "change-password-ok",
                    click: function (e) {

                        var $url = $('#' + namespace + 'passwordUrl').val(),
                            $userId = $('#' + namespace + 'dialog-password-userId').val();

                        $.ajax({
                            type: "POST",
                            url: $url,
                            data: {
                                "userId": $userId
                            },
                            success: function (data) {
                                var parsedMessage = $.parseJSON($.parseJSON(data).resultMap),
                                    $dialogContent = $('#' + namespace + 'change-password-info'),
                                    $success = $('#' + namespace + 'change-password-successContent');

                                if (parsedMessage.length == 0) {
                                    $dialogContent.hide();

                                    $('#change-password-ok').hide();
                                    $('#change-password-cancel').text("OK");

                                    $success.show();
                                } else {
                                    $('#' + $this.namespace + 'changePasswordError')
                                        .empty()
                                        .append(parsedMessage[0].value).show().addClass('alert alert-danger');
                                }
                            }

                        });

                    }
                },
                {
                    id: "change-password-cancel",
                    click: function () {
                        window.location.reload();
                    }
                }
            ]

        });

        // Filter users by partner
        $("#" + namespace + "partners").change(function (e) {

            var $partner = $("#" + namespace + "partners option:selected").val(),
                $url = $("#" + namespace + "partners").data("url");

            if (history.pushState) {
                var $param = $partner == 0 ? '' : $partner;
                history.pushState(null, document.title, window.location.pathname + "?partner=" + $param);
            }

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

        // Filter users by status
        $(document).on("change", "#" + namespace + "filterStatus", function (e) {

            var $filter = $("#" + namespace + "filterStatus option:selected").val(),
                $partner = $("#" + namespace + "partners option:selected").val(),
                $url = $("#" + namespace + "filterByStatusURL").val();

            $.ajax({
                data: {
                    status: $filter,
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
            $(document.body).append("<div id='modal-window' class='ui-widget-overlay ui-front' style='display: block;'></div>");

            var url = $("#addUser").val(),
                data = {userId: null};

            $this.viewMode(url, data);

        });

        $(document).on("click", ".editUser", function (e) {
            $(document.body).append("<div id='modal-window' class='ui-widget-overlay ui-front' style='display: block;'></div>");
            var url = $("#addUser").val();
            var data = {
                userId: $(this).data("id"),
                partnerId: $(this).data("partner")
            };
            $this.viewMode(url, data);
        });

        // Add new user
        $(document).on('click', '#' + namespace + 'addUser', $.proxy(function (e) {
            e.preventDefault();

            var $userForm = $('#' + namespace + 'usersForm'),
                $link = $('#' + namespace + 'addUser');

            $this.submitFormAjax($userForm, $link);

        }, this));

        $(document).on('click', '#' + namespace + "changeStatus", function (e) {
            e.preventDefault();

            $statusDialog.dialog("open");

            var $loginName = $(e.target).parents("tr").find("td[id*='login'] ").text().trim(),
                $userId = $(e.target).parents("tr").find("td[id*='id'] ").text().trim(),
                $dialogTitle = $('#ui-id-1'),
                $inputIdField = $('#' + namespace + 'userId');

            $dialogTitle.append($loginName);
            $inputIdField.val($userId);

        });

        $(document).on('click', '#' + namespace + "generatePassword", function (e) {
            e.preventDefault();

            $passwordDialog.dialog("open");

            var $loginName = $(e.target).parents("tr").find("td[id*='login'] ").text().trim(),
                $userId = $(e.target).parents("tr").find("td[id*='id'] ").text().trim(),
                $dialogTitle = $('#ui-id-2'),
                $inputIdField = $('#' + namespace + 'dialog-password-userId');

            $dialogTitle.append($loginName);
            $inputIdField.val($userId);

        });

        if ($(".pagination").length) {
            $(document).on("click", ".pagination a", function (e) {
                if ($(this).parent().hasClass("disabled"))
                    return;

                e.preventDefault();
                $(document.body).append("<div id='modal-window' class='ui-widget-overlay ui-front' style='display: block;'></div>");

                var url = $("#paginator").val(),
                    data = {
                        cpage: $(this).data("page"),
                        partnerId: $("#" + namespace + "partners option:selected").val()
                    };

                $.ajax({
                    type: "POST",
                    url: url,
                    data: data,
                    success: function (data) {
                        $('#' + namespace + 'userTable').empty().html(data);
                        $("#modal-window").remove();
                    }, error: function () {
                        $("#modal-window").remove();
                    }
                })
            });
        }

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
                            .append(parsedMessage[0].value).show().addClass('alert alert-danger');

                        link.removeAttr("disabled");
                    }

                }
            });
        }
    },

    viewMode: function (url, data) {
        $.ajax({
            type: "POST",
            url: url,
            data: data,
            success: function (data) {
                $("#userWrapper").html(data);
                $("#modal-window").remove();
            },
            error: function () {
                $("#modal-window").remove();
            }
        });
    }

});