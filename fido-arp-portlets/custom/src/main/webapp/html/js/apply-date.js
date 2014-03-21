

function applyDate(){
    var currentContent = $(".quest-template").find("fieldset").not(":hidden");
    var $dateChooser = currentContent.find(".dateChooser");
    if($dateChooser.length){
        $dateChooser.each(function(){
            var $this = $(this),
                dayElem = $this.find(".day select"),
                monthElem = $this.find(".month select"),
                yearElem = $this.find(".year select");

            var locale = $this.data("locale");

            if($(".quest-template").hasClass("liferay-form-quest")){
                locale = "uk_UA";
            }

            selectsDateChooser($this, locale);

            var name = $this.data("name");
            if($("#" + name + "Month-styler").length > 0){
                $(monthElem).trigger("refresh");
            }else{
                $(monthElem).styler({
                    selectSearch: false
                });
            }
            if($("#" + name + "Day-styler").length > 0){
                $(dayElem).trigger("refresh");
            }else{
                $(dayElem).styler({
                    selectSearch: false
                });
            }
            if($("#" + name + "Year-styler").length > 0){
                $(yearElem).trigger("refresh");
            }else{
                $(yearElem).styler({
                    selectSearch: false
                });
            }

            $().dateSelectBoxes(monthElem, dayElem, yearElem, true);
        });
    }
}


function selectsDateChooser($dateChooser, locale){
    var dayVal = "", monthVal = "", yearVal = "", valueDate = "";

    if(!!$dateChooser.data("value") && $dateChooser.data("value") != ""){
        var dateStringInRange = $.trim($dateChooser.data("value"));
        valueDate = new Date(dateFromISO8601(dateStringInRange));
        if(isNaN(valueDate)){
            valueDate = new Date(dateStringInRange);
        }
        dayVal = $.datepicker.formatDate("d", valueDate);
        yearVal = $.datepicker.formatDate("yy", valueDate);
        monthVal = $.datepicker.formatDate("m", valueDate);
    }else if(!!$dateChooser.data("today") && $dateChooser.data("today")){
        valueDate = new Date();
        dayVal = $.datepicker.formatDate("d", valueDate);
        yearVal = $.datepicker.formatDate("yy", valueDate);
        monthVal = $.datepicker.formatDate("m", valueDate);
    }

    //day values
    var day = $dateChooser.find("select[id*='Day']");
    if(day.length && day.find("option").length == 0){
        day.append($("<option></option>"));
        for(var i = 1; i<= 31; i++){
            var option = $("<option></option>").attr("value", i).text(i);
            if(dayVal != "" && dayVal == i){
                option.attr("selected","selected");
            }
            day.append(option);
        }
    }

    //month values
    var month = $dateChooser.find("select[id*='Month']");
    if(month.length && month.find("option").length == 0){
        var array = months[locale].monthNames;
        if(array.length == 0){
            array = months['en_US'].monthNames;
        }
        month.append($("<option></option>"));
        for(var j = 1; j <= 12; j++){
            var option = $("<option></option>").attr("value", j).attr("label", array[j-1]).text(array[j-1]);
            if(monthVal != "" && monthVal == j){
                option.attr("selected","selected");
            }
            month.append(option);
        }
    }

    //year values
    var year = $dateChooser.find("select[id*='Year']");
    var yearTail = 0;
    if(year.length && year.find("option").length == 0){
        year.append($("<option></option>"));
        var startYear = 1920;
        if(!!$dateChooser.data("start") && $dateChooser.data("start") != ""){
            startYear = $dateChooser.data("start");
            if(startYear.toString().indexOf("-") > -1){
                yearTail = startYear.toString().replace("-", "");
                startYear = new Date().getFullYear() - parseInt(yearTail);
            }else if(startYear.toString().indexOf("+") > -1){
                yearTail = startYear.toString().replace("+", "");
                startYear = new Date().getFullYear() + parseInt(yearTail);
            }
        }
        var lastYear = new Date().getFullYear();
        if(!!$dateChooser.data("end") && $dateChooser.data("end") != ""){
            lastYear = $dateChooser.data("end");
            if(lastYear.toString().indexOf("-") > -1){
                yearTail = lastYear.toString().replace("-", "");
                lastYear = new Date().getFullYear() - parseInt(yearTail);
            }else if(lastYear.toString().indexOf("+") > -1){
                yearTail = lastYear.toString().replace("+", "");
                lastYear = new Date().getFullYear() + parseInt(yearTail);
            }
        }
        for(var y = startYear; y <= lastYear; y++){
            var option = $("<option></option>");
            option.attr("value", y).text(y);
            option.attr("label", y);
            if(yearVal != "" && yearVal == y){
                option.attr("selected","selected");
            }
            year.append(option);
        }
    }
}
