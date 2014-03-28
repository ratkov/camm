ValidationProcessor = Class.extend({

    init: function (errorCode) {

        $.validator.setDefaults({
            focusInvalid: false
        });

        $.validator.addMethod(
            'regexp',
            function (value, element, regexp) {
                var re = new RegExp(regexp);
                return this.optional(element) || re.test(value);
            }
        );

        $.validator.addMethod(
            "alphanumeric", function (value, element) {
                return this.optional(element) || /^[a-zA-Z0-9-_.-]+$/.test(value);
            }
        );

        $(".userContent").each(function () {
            var form = $(this);

            var v = form.validate({
                errorElement: "span",
                ignore: ':hidden',
                rules: {
                    firstName: {
                        required: true,
                        minlength: 2,
                        maxlength: 20,
                        regexp: /^[a-zA-Zа-яА-ЯґєіїҐЄІЇ'\- ]*$/
                    },
                    middleName: {
                        required: true,
                        minlength: 2,
                        maxlength: 20,
                        regexp: /^[a-zA-Zа-яА-ЯґєіїҐЄІЇ'\- ]*$/
                    },
                    lastName: {
                        required: true,
                        minlength: 2,
                        maxlength: 20,
                        regexp: /^[a-zA-Zа-яА-ЯґєіїҐЄІЇ'\- ]*$/
                    },
                    login: {
                        required: true,
                        minlength: 4,
                        maxlength: 30,
                        alphanumeric: true
                    },
                    email: {
                        required: true,
                        email: true
                    }
                },

                messages: {
                    firstName: {
                        required: errorCode.firstNameRequired,
                        minlength: errorCode.nameMinLength,
                        maxlength: errorCode.nameMaxLength,
                        regexp: errorCode.firstNameRegexp
                    },
                    middleName: {
                        required: errorCode.middleNameRequired,
                        minlength: errorCode.nameMinLength,
                        maxlength: errorCode.nameMaxLength,
                        regexp: errorCode.middleNameRegexp
                    },
                    lastName: {
                        required: errorCode.lastNameRequired,
                        minlength: errorCode.nameMinLength,
                        maxlength: errorCode.nameMaxLength,
                        regexp: errorCode.lastNameRegexp
                    },
                    login: {
                        required: errorCode.loginRequired,
                        minlength: errorCode.loginMinLength,
                        maxlength: errorCode.loginMaxLength,
                        alphanumeric: errorCode.loginRegexp
                    },
                    email: {
                        required: errorCode.emailRequired,
                        email: errorCode.emailFormatError
                    }
                },
                showErrors: function (errorMap, errorList) {
                    if (errorList.length) {
                        var s = errorList.shift();
                        var n = [];
                        n.push(s);
                        this.errorList = n;
                    }
                    this.defaultShowErrors();
                },
                highlight: function (element) {
                    $(element).closest('.regRow').addClass('errorP');
                    if ($(element).hasClass('sel')) {
                        $(element).parent().children('.sbHolder').addClass('error');
                    } else {
                        $(element).addClass('error');
                    }
                },
                unhighlight: function (element) {
                    $(element).closest('.regRow').removeClass('errorP');
                    $(element).removeClass('error');
                    $(element).parent().children('.sbHolder').removeClass('error');
                },
                invalidHandler: function (form, validator) {
                    $(".userContent").each(function () {
                        $(this).addClass('invalidForm');
                    });
                }
            });
        });

    }
});