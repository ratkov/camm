/*You must to use /js/jquery.validate.js from portlet because it uses patch*/
var messages = {
    "en_US": {
        required: "This field is required.",
        requiredSelect: "Select value from dictionary",
        requiredDate: "Select value from dropdown list",
        remote: "Please fix this field.",
        email: "Please enter a valid email address.",
        url: "Please enter a valid URL.",
        date: "Please enter a valid date.",
        dateISO: "Please enter a valid date (ISO).",
        number: "Please enter a valid number.",
        digits: "Please enter only digits.",
        creditcard: "Please enter a valid credit card number.",
        equalTo: "Please enter the same value again.",
        notEquals: "Please enter the different values.",
        maxlength: $.validator.format("Please enter no more than {0} characters."),
        minlength: $.validator.format("Please enter at least {0} characters."),
        rangelength: $.validator.format("Please enter a value between {0} and {1} characters long."),
        range: $.validator.format("Please enter a value between {0} and {1}."),
        max: $.validator.format("Please enter a value less than or equal to {0}."),
        min: $.validator.format("Please enter a value greater than or equal to {0}."),
        cyrillic: "Only letters of the Ukrainian alphabet.",
        letterswithbasicpunc: "Only letters of the Latin alphabet.",
        htmlcode: "Not allowed html code",
        nourl: "Not allowed links",
        phonenumber: "Enter the correct number!",
        requiredTrue: "Must be selected",
        twoPhone: "Must be entered at least two phones",
        oneChose: "Must be chosen one of the items" ,
        dateMoreCurrent: "Please enter a date not earlier than today",
        passportDate: "Passport Date should be 16 years before your birth year and no later than today"
    },
    "ru_RU":{
        required: "Заполни поле",
        requiredSelect: "Выбери необходимое значение из словаря",
        requiredDate: "Выбирай с випадающего списка",
        remote: "Please fix this field.",
        email: "Введи правильный электронный адрес",
        url: "Введи правильную ссылку",
        date: "Введи правильную дату",
        dateISO: "Please enter a valid date (ISO).",
        number: "Используй только числа",
        digits: "Используй только числа",
        creditcard: "Введи правильный номер кредитной карты",
        equalTo: "Please enter the same value again.",
        notEquals: "Пожалуйста, введи различные значения.",
        maxlength: $.validator.format("Максимум {0} символов!"),
        minlength: $.validator.format("Минимум {0} символов!."),
        rangelength: $.validator.format("Please enter a value between {0} and {1} characters long."),
        range: $.validator.format("Please enter a value between {0} and {1}."),
        max: $.validator.format("Please enter a value less than or equal to {0}."),
        min: $.validator.format("Please enter a value greater than or equal to {0}."),
        cyrillic: "Используй буквы украинского алфавита",
        letterswithbasicpunc: "Используй буквы латинского алфавита",
        htmlcode: "Запрещен ввод html кода!",
        nourl: "Запрещен ввод ссылок!",
        phonenumber: "Введи корректный номер!",
        requiredTrue: "Обязательная установка \"Чек\". Без установки \"Чек\" заполнение анкеты не возможно",
        twoPhone: "Должно быть заполненно не меньше двух телефонов",
        oneChose: "Обязательная выбор одного из значений",
        dateMoreCurrent: "Пожалуйста введи дату не ранее сегодня",
        passportDate:"Дата выдачи паспорта должна быть не ранее 16 лет от года вашего рождения и не позднее сегодня"
    },
    "uk_UA":{
        required: "Заповни поле",
        requiredSelect: "Обери потрібне значення з довідника",
        requiredDate: "Обирай з випадаючого списку",
        remote: "Please fix this field.",
        email: "Введи правельну електронну адресу",
        url: "Введи правильне посилання",
        date: "Введи правильну дату",
        dateISO: "Please enter a valid date (ISO).",
        number: "Використовуй тільки цифри",
        digits: "Використовуй тільки цифри",
        creditcard: "Введи правильний номер кредитної картки",
        equalTo: "Please enter the same value again.",
        notEquals: "Будь ласка, введи різні значення.",
        maxlength: $.validator.format("Максимум {0} символів"),
        minlength: $.validator.format("Мінімум {0} символів"),
        rangelength: $.validator.format("Please enter a value between {0} and {1} characters long."),
        range: $.validator.format("Please enter a value between {0} and {1}."),
        max: $.validator.format("Please enter a value less than or equal to {0}."),
        min: $.validator.format("Please enter a value greater than or equal to {0}."),
        cyrillic: "Використовуй літери українського алфавіту",
        letterswithbasicpunc: "Використовуй літери латинського алфавіту",
        htmlcode: "Не дозволяється вводити html код!",
        nourl: "Не дозволяється вводити посилання!",
        phonenumber: "Введи коректний номер!",
        requiredTrue: "Обов\'язкове встановлення \"Чек\". Без встановлення \"Чек\" заповнення анкети не можливе",
        twoPhone: "Повинно бути заповненно не меньше двох телефонів",
        oneChose: "Обов\'язковий вибір одного зі значень",
        dateMoreCurrent: "Будь-ласка введи дату не раніше сьогодні",
        passportDate: "Дата видачі паспорту повинна бути не раніше 16 років від року вашого народження і не пізніше сьогодні"
    }
};

function setValidator(locale){
    /* validator */
    jQuery.extend(jQuery.validator.messages, {
        required: messages[locale].required,
        requiredSelect: messages[locale].requiredSelect,
        requiredDate: messages[locale].requiredDate,
        requiredIf: messages[locale].required,
        remote: messages[locale].remote,
        email: messages[locale].email,
        url: messages[locale].url,
        date: messages[locale].date,
        dateISO: messages[locale].dateISO,
        number: messages[locale].number,
        digits: messages[locale].digits,
        creditcard: messages[locale].creditcard,
        equalTo: messages[locale].equalTo,
        notEquals: messages[locale].notEquals,
        maxlength: messages[locale].maxlength,
        minlength: messages[locale].minlength,
        rangelength: messages[locale].rangelength,
        range: messages[locale].range,
        max: messages[locale].max,
        min: messages[locale].min,
        cyrillic:  messages[locale].cyrillic,
        letterswithbasicpunc:  messages[locale].letterswithbasicpunc,
        htmlcode:  messages[locale].htmlcode,
        nourl: messages[locale].nourl,
        phonenumber: messages[locale].phonenumber,
        requiredTrue: messages[locale].requiredTrue,
        twoPhone: messages[locale].twoPhone,
        oneChose: messages[locale].oneChose,
        dateMoreCurrent: messages[locale].dateMoreCurrent,
        passportDate: messages[locale].passportDate
    });

    jQuery.extend(jQuery.validator.classRuleSettings, {
        phonenumber: { phonenumber:true },
        requiredDate: { requiredDate:true },
        requiredSelect: { requiredSelect:true },
        cyrillic: { cyrillic:true },
        number: { number:true },
        letterswithbasicpunc: { letterswithbasicpunc:true },
        twoPhone: { twoPhone:true },
        oneChose: { oneChose:true },
        notEquals: { notEquals:true },
        requiredTrue: { requiredTrue:true },
        minlength: { minlength:0 },
        maxlength: { maxlength:0 },
        requiredIf: { requiredIf:true },
        dateMoreCurrent: {dateMoreCurrent:true} // check in dateChooser contract
    });

    jQuery.extend(jQuery.validator.methods, {
        twoPhone: function(value, element, param) {
            var fieldset = $("fieldset").not(":hidden");
            if(fieldset.find("input[type='text'].twoPhone").not(":hidden").length >= 4){
                var counter = 0;
                fieldset.find("input[type='text'].twoPhone").not(":hidden").each(function(){
                    if($(this).val() != ""){
                        counter++;
                    }
                });
                return counter >= 2;
            }
            return true;
        },
        requiredTrue: function( value, element, param ) {
            // check if dependency is met
            if ( !this.depend(param, element) ) {
                return "dependency-mismatch";
            }
            if ( element.nodeName.toLowerCase() === "select" ) {
                // could be an array for select-multiple or a string, both are fine this way
                var val = $(element).val();
                return val && val.length > 0;
            }
            if ( this.checkable(element) ) {
                return this.getLength(value, element) > 0;
            }

            return $.trim(value).length > 0;
        },
        requiredSelect: function( value, element, param ) {
            // check if dependency is met
            if ( !this.depend(param, element) ) {
                return "dependency-mismatch";
            }
            if ( element.nodeName.toLowerCase() === "select" ) {
                // could be an array for select-multiple or a string, both are fine this way
                var val = $(element).val();
                return val && val.length > 0;
            }
            if ( this.checkable(element) ) {
                return this.getLength(value, element) > 0;
            }

            return $.trim(value).length > 0;
        },
        requiredDate: function( value, element, param ) {
            // check if dependency is met
            if ( !this.depend(param, element) ) {
                return "dependency-mismatch";
            }
            if ( element.nodeName.toLowerCase() === "select" ) {
                // could be an array for select-multiple or a string, both are fine this way
                var val = $(element).val();
                return val && val.length > 0;
            }
            if ( this.checkable(element) ) {
                return this.getLength(value, element) > 0;
            }

            return $.trim(value).length > 0;
        },
        letterswithbasicpunc: function(value, element, param ) {
            return this.optional(element) || /^[a-z\-.,()'"\s]+$/i.test(value)
        },
        requiredIf: function(value, element, param ) {
            if($(element).parents(".regRow").length > 0){
                var data = $(element).parents(".regRow").data("show");
                if(!!data && !checkRequiredField($(element).parents(".regRow"))){
                    return true;
                }else{
                    // check if dependency is met
                    if ( !this.depend(param, element) ) {
                        return "dependency-mismatch";
                    }
                    if ( element.nodeName.toLowerCase() === "select" ) {
                        // could be an array for select-multiple or a string, both are fine this way
                        var val = $(element).val();
                        return val && val.length > 0;
                    }
                    if ( this.checkable(element) ) {
                        return this.getLength(value, element) > 0;
                    }

                    return $.trim(value).length > 0;
                }
            }
            return true;
        },
        cyrillic: function(value, element){
            return this.optional(element) || /^[а,б,в,г,ґ,д,е,є,ж,з,и,і,ї,й,к,л,м,н,о,п,р,с,т,у,ф,х,ц,ч,ш,щ,ь,ю,я,А,Б,В,Г,Ґ,Д,Е,Є,Ж,З,И,І,Ї,Й,К,Л,М,Н,О,П,Р,С,Т,У,Ф,Х,Ц,Ч,Ш,Щ,Ь,Ю,Я,\u2019,\u2013,\u2014,\u002D,-,.,\u2018,`,\\ ,',"]*$/.test(value);
        },
        oneChose: function(value, elem, param) {
            return $(".oneChose:checkbox:checked").length > 0;
        },
        dateMoreCurrent: function(value, elem){
            var parent = $(elem).parents(".dateChooser");
            var day = parent.find(".day select").val();
            var month = parent.find(".month select").val();
            var year = parent.find(".year select").val();
            if(!year || year.length < 0){
                year = new Date().getFullYear();
            }
            return new Date(year, month, day, 0, 0, 0, 0) >= new Date();
        },
        passportDate: function(value, elem){
            //passport
            var parent = $(elem).parents(".dateChooser");
            var dayPass = parent.find(".day select").val();
            var monthPass = parent.find(".month select").val();
            var yearPass = parent.find(".year select").val();

            var yearBirth = $("div[data-name='dateBirth'] .year select").val();

            return ((yearPass - 16) >= yearBirth) && (new Date(yearPass, monthPass - 1, dayPass, 0, 0, 0, 0) <= new Date());
        },
        notEquals: function(value, elem){
            var isDiff = 0;
            $(".notEquals").not(":hidden").each(function (){
                if($(this).val() != '' && $(this).val() == value){
                    isDiff++;
                }
            });
            return isDiff < 2;
        }
    });

    $.validator.setDefaults({
        focusInvalid: false
    });

    $(".validateForm").each(function() {
        var form = $(this);

        var v = form.validate({
            rules:{
                passportDateDay:{passportDate:true},
                passportDateMonth:{passportDate:true},
                passportDateYear:{passportDate:true}
            },
            errorElement: "span",
            showErrors: function(errorMap, errorList) {
                if (errorList.length) {
                    var s = errorList.shift();
                    var n = [];
                    n.push(s);
                    this.errorList = n;
                }
                this.defaultShowErrors();
            },
            highlight: function(element) {
                $(element).closest('.regRow').addClass('errorP');
                if ($(element).hasClass('sel')) {
                    $(element).parent().children('.sbHolder').addClass('error');
                } else {
                    $(element).addClass('error');
                }
            },
            unhighlight: function(element) {
                $(element).closest('.regRow').removeClass('errorP');
                $(element).removeClass('error');
                $(element).parent().children('.sbHolder').removeClass('error');
            },
            invalidHandler: function(form, validator) {
                $(".validateForm").each(function() {
                    $(this).addClass('invalidForm');
                });
            }
        });
        $(form).on("submit", function(e) {
            e.preventDefault();
            return false;
        }).on("keydown keypress keyup", function(e) {
            var KEY_ENTER = 13;
            if (e.keyCode == KEY_ENTER) {
                e.preventDefault();
                return false;
            }
        });

        $(form).on("click", ".submit", function(event) {
            event.preventDefault();
            $(form).valid();
            if($(form).valid()) {
                $(document.body).append("<div id='modal-window' class='ui-widget-overlay ui-front' style='display: block;'></div>");

                var listCheckbox = $(form).find('input[type=checkbox]:not(:checked)');
                var jsonCheckbox = {};
                var formJson = $(form).serializeObject();
                if(listCheckbox.length > 0){
                    listCheckbox.each(function(){
                        jsonCheckbox[$(this).attr("name")] = $(this).val();
                    });

                    // re-disabled the set of inputs that you previously enabled
                    formJson = $.extend( $(form).serializeObject(), jsonCheckbox);
                }

                var data = formJson;
                var url = $(form).attr("action");
                $.ajax({
                    type:"POST",
                    url:url,
                    data:data,
                    success:function(data){
                        $("#partnerWrapper").html(data);
                        $("#modal-window").remove();
                    }
                    ,error: function(){
                        $("#modal-window").remove();
                    }
                });
            } else {
                if (!v.numberOfInvalids())
                    return;

                v.errorList[0].element.focus();
                if($(v.errorList[0].element).offset().top < 0){
                    $(window).scrollTop( $(v.errorList[0].element).next().offset().top )
                } else {
                    $(window).scrollTop( $(v.errorList[0].element).offset().top )
                }
            }
            return false;
        });


        $(form).on("click", ".nextStep", function(event) {
            event.preventDefault();
            $(form).valid();
            if($(form).valid()) {
                var tab = $(event.target).parents("fieldset");
                tab.addClass('success');
                var nextIndex = tab.index() + 1;
                if($(form).find(".t_item").length && nextIndex < $(form).find(".t_item").length){
                    var nextTab = $(form).find(".t_item").get(nextIndex);
                    $(nextTab).find("a").removeClass("disable").trigger("click");
                }else{
                    offSelectEvents();
                    offCheckboxEvent();
                    offChangeFieldEvent();

                    $(document.body).append("<div id='modal-window' style='display: block;'></div>");
                    var url = $("#backToMain").val();
                    var data = {
                        partnerId: $("#partnerId").val()
                    };
                    viewMode(url, data, false);
                }
            } else {
                if($(event.target).parents(".t_content").length > 0)
                    $(event.target).parents(".t_content").removeClass('success');
//                v.focusInvalid();
                if (!v.numberOfInvalids())
                    return;

                v.errorList[0].element.focus();
                if($(v.errorList[0].element).offset().top < 0){
                    $(window).scrollTop( $(v.errorList[0].element).next().offset().top )
                } else {
                    $(window).scrollTop( $(v.errorList[0].element).offset().top )
                }
            }
            return false;
        });

    });
    setMasks(".validateForm");
}

function setMasks(form){
    $(form).find('input[id*="mobile"]').inputmask("+38(099)9999999");
    $(form).find('input[id*="phone"]').inputmask("+3809999999*99");
    $(form).find('input[name="sms"]').inputmask({'mask':'9', 'repeat':8});
    $(form).find('input[id*="series"]').inputmask({'mask':'a', 'repeat':2});
}

function isJsonString(str) {
    try {
        JSON.parse(str);
    } catch (e) {
        return false;
    }
    return true;
}