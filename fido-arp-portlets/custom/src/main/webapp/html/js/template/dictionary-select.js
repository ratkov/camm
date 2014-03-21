function loadDictionary(){
    var url = $("#urlResource").val();
    var currentContent = $(".dict-load").find("fieldset").not(":hidden");
    $(currentContent).find("select.autoload").not(":hidden").each(function(){
        if($(this).data("dictionary")){
            if($(this).val() == null){
                var dict = $(this).data("dictionary");
                var data = { dictionary : dict };
                loadJsonSelect(url, data, this);
            }
        }
    });

    if($(currentContent).find("select[name*='region']").not(".autoload").length > 0){
        $(currentContent).find("select[name*='region']").not(":hidden").trigger("change");
    }
}


var chooserMessage = {
    "uk_UA" : { message:"Немає збігів!" },
    "ru_RU" : { message:"Нет совпадений!"  },
    "en_US" : { message:"Nothing found!" }
};

function selectEvents(){
    var $dict = $(".dict-load");
    $dict.on("change", "select[name*='region']", function(){
        getDistrict(this);
    });
    $dict.on("change", "select[data-dictionary='fidoDistrict']", function(){
        getLocalityType(this)
    });
    $dict.on("change", "select[data-dictionary='fidoLocalityType']", function(){
        getLocalityName(this)
    });
    $dict.on("change", "select[data-dictionary='fidoLocalityName']", function(){
        getStreetType(this);
    });
    $dict.on("change", "select[data-dictionary='fidoStreetType']", function(){
        getStreet(this)
    });
    $dict.on("change","select[data-dictionary='fidoStreet']", function(e){
        getZipCode(e.target);
    });
    $dict.on("change","select[data-dictionary='fidoDepartmentsCities']", function(e){
        getDepartmentName(e.target);
    });
}

function offSelectEvents(){
    var $dict = $(".dict-load");
    $dict.off("change", "select[name*='region']");
    $dict.off("change", "select[data-dictionary='fidoDistrict']");
    $dict.off("change", "select[data-dictionary='fidoLocalityType']");
    $dict.off("change", "select[data-dictionary='fidoLocalityName']");
    $dict.off("change", "select[data-dictionary='fidoStreetType']");
    $dict.off("change", "select[data-dictionary='fidoStreet']");
    $dict.off("change", "select[data-dictionary='fidoDepartmentsCities']");
    $dict.off("change", "select#locationName");
    $dict.off("change", "select#locationDelivery");
}

function findNextElement(current, find){
    var element;
    var parents = $(current).parents(".regRow").nextAll();
    for(var i=0; i < parents.length; i++){
        if($(parents[i]).find(find).length > 0){
            return $(parents[i]).find(find);
        }
    }
    return element;
}

function findPrevElement(current, find){
    var element;
    var parents = $(current).parents(".regRow").prevAll();
    for(var i=0; i < parents.length; i++){
        if($(parents[i]).find(find).length > 0){
            element = $(parents[i]).find(find);
            break;
        }
    }
    return element;
}

function getDistrict(region){
    if($(region).val() != null){
        var select = findNextElement(region, "select[data-dictionary='fidoDistrict']");
        var data = {
            region : $(region).val()
        };
        initSelect(select, data);
    }
}

function getLocalityType(district){
    if($(district).val() != null){
        var select = findNextElement(district, "select[data-dictionary='fidoLocalityType']");
        var data = {
            district : $(district).val()
        };
        initSelect(select, data);
    }
}

function getLocalityName(localityType){
    if($(localityType).val() != null){
        var select = findNextElement(localityType, "select[data-dictionary='fidoLocalityName']");
        var fidoDistrict = findPrevElement(localityType, "select[data-dictionary='fidoDistrict']");
        var data = {
            localityType : $(localityType).val(),
            district : $(fidoDistrict).val()
        };
        initSelect(select,data);
    }
}

function getStreetType(localityName){
    if($(localityName).val() != null){
        var select = findNextElement(localityName, "select[data-dictionary='fidoStreetType']");
        var data = {
            localityName : $(localityName).val()
        };
        initSelect(select,data);
    }
}

function getStreet(streetType){
    if($(streetType).val() != null){
        var select = findNextElement(streetType, "select[data-dictionary='fidoStreet']");
        var fidoLocalityName = findPrevElement(streetType, "select[data-dictionary='fidoLocalityName']");
        var data = {
            streetType : $(streetType).val(),
            localityName : $(fidoLocalityName).val()
        };
        initSelect(select,data);
    }
}

function getZipCode(street){
    if($(street).val() != null){
        var select = findNextElement(street, "select[data-dictionary='fidoZipCode']");
        var data = {
            street : $(street).val()
        };
        initSelect(select,data);
    }
}

//for event or simple call
function initSelect(select, newData){
    var url = $("#urlResource").val();
    if(select.length > 0){
        if($(select).data("dictionary")){
            var dict = $(select).data("dictionary");
            var data =  $.extend({ dictionary : dict }, newData);
            loadJsonSelect(url, data, select);
        }
    }
}

function canLoad($select){
    var influence, influence2;
    if($select.data("dictionary").indexOf('fidoDistrict') > -1){
       influence = findPrevElement($select, "select[name*='region']");
       return !!influence.val();
    }else if($select.data("dictionary").indexOf('fidoLocalityType') > -1){
        influence = findPrevElement($select, "select[data-dictionary='fidoDistrict']");
        return !!influence.val();
    }else if($select.data("dictionary").indexOf('fidoLocalityName') > -1){
        influence = findPrevElement($select, "select[data-dictionary='fidoLocalityType']");
        influence2 = findPrevElement($select, "select[data-dictionary='fidoDistrict']");
        return !!influence.val() || !!influence2.val();
    }else if($select.data("dictionary").indexOf('fidoStreetType') > -1){
        influence = findPrevElement($select, "select[data-dictionary='fidoLocalityName']");
        return !!influence.val();
    }else if($select.data("dictionary").indexOf('fidoStreet') > -1 && $select.data("dictionary").indexOf('fidoStreetType') == -1){
        influence = findPrevElement($select, "select[data-dictionary='fidoStreetType']");
        influence2 = findPrevElement($select, "select[data-dictionary='fidoLocalityName']");
        return !!influence.val() || !!influence2.val();
    }else if($select.data("dictionary").indexOf('fidoZipCode') > -1){
        influence = findPrevElement($select, "select[data-dictionary='fidoStreet']");
        return !!influence.val();
    }
    return true;
}

function loadJsonSelect(url, data, select){
    var $select = $(select);
    if($select.parents("fieldset")){
        $select.parents("fieldset").addClass("loader");
    }
    var locale = $(".dict-load").parents("form").data("locale");
    if(canLoad($select)){
        $.ajaxQueue({
            type:"POST"
            ,url: url
            ,data:data
        }).done(function(dataJson){
                $select.find("option").remove();
                for(var i = 0; i < dataJson.length; i++){
                    var label = dataJson[i].value;
                    if(dataJson[i].value == ""){
                        label = "---";
                    }
                    $select.append($("<option></option>").attr("value", dataJson[i].id).text(label));
                }
                if($select.data("selected") != ""){
                   $select.val($select.data("selected"));
                }
                if($select.hasClass("sel")){
                    $select.trigger("refresh");
                }else if($select.hasClass("chose") != ""){
                    var chosen = $select.data('chosen');
                    if(chosen){
                        chosen.destroy();
                    }
                    $select.chosen({
                        width:"100%",
                        no_results_text: chooserMessage[locale].message
                    });
                }
                if($select.attr("name").indexOf('region') > -1){
                    $select.trigger("change");
                }else if($select.data("dictionary").indexOf('fidoDistrict') > -1){
                    $select.trigger("change");
                }else if($select.data("dictionary").indexOf('fidoLocalityType') > -1){
                    $select.trigger("change");
                }else if($select.data("dictionary").indexOf('fidoLocalityName') > -1){
                    $select.trigger("change");
                }else if($select.data("dictionary").indexOf('fidoStreetType') > -1){
                    $select.trigger("change");
                }else if($select.data("dictionary").indexOf('fidoStreet') > -1 && $select.data("dictionary").indexOf('fidoStreetType') == -1){
                    $select.trigger("change");
                }
                $select.parents("fieldset").removeClass("loader");
            }
        );
    }
}
