$(document).ready(function(){
setTimeout(function(){

   $(".temptable").each( 
       function(){
          if($(this).find("tr").length < 8 )
          { 
              //$(this).addClass("hktemptable");
              $(this).find("tbody").each(function(){$(this).attr("style","font-size:12pt");});
          }
       } );

},1000);
//alert($(".temptable").length);

    
    //);
});

