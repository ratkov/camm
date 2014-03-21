jQuery(document).ready(function(){
   $("#createNewQuery").on("click", function(){
       $("#dialog").html( $("#form-select-templates").html() ).dialog();
   });

    formChanging();
});
