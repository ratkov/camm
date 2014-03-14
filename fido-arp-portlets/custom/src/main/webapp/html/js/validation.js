ValidationProcessor = Class.extend({

    init: function (errorCode) {

        $.validator.setDefaults({
            focusInvalid: false
        });

        $(".userContent").each(function () {
            var form = $(this);

            var v = form.validate({
                errorElement: "span",
                ignore: ':hidden',
                rules: {
                    fullName: {
                        required: true,
                        minlength: 2,
                        maxlength: 20
                    },
                    login: {
                        required: true,
                        minlength: 4,
                        maxlength: 20
                    },
                    email: {
                        required: true,
                        email: true
                    }
                },

                messages: {
                    fullName: {
                        required: errorCode.fullNameRequired,
                        minlength: errorCode.nameMinLength,
                        maxlength: errorCode.nameMaxLength
                    },
                    login: {
                        required: errorCode.loginRequired,
                        minlength: errorCode.loginMinLength,
                        maxlength: errorCode.loginMaxLength
                    },
                    email: {
                        required: errorCode.emailRequired,
                        email: errorCode.emailFormatError
                    }
                }
            });
        });

    }
});