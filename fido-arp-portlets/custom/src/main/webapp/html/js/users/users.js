UsersProcessor = Class.extend({

    init: function (namespace) {
        this.namespace = namespace;

        var $this = this;

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

            $this.submitFormAjax($userForm, $link, {partnerId: $partner});

        }, this));

    },

    submitFormAjax: function (form, link, data) {
        var $this = this;

        if (form.valid() && link.attr("disabled") != "disabled") {

            link.attr("disabled", "disabled");
            form.ajaxSubmit({
                type: "POST",
                data: data,
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