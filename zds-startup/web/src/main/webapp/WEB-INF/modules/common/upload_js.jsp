<script type="text/javascript" src="<%=path%>/assets/js/uploadify/jquery.min.js"></script>
 <script type="text/javascript" src="<%=path%>/assets/js/uploadify/jquery.uploadify.js"></script>
<link href="<%=path%>/assets/js/uploadify/uploadify.css" rel="stylesheet" >
<script type="text/javascript">
 $(document).ready(function(){
//  var isIE = !-[1,];
//  if (isIE) {
//   try {
//    var swf1 = new ActiveXObject('ShockwaveFlash.ShockwaveFlash');
//    // alert('安装了Flash');
//
//   }
//   catch (e) {
//    //alert('');
//    $("body").html("<h1>You Are Not Install Flash, Click <a href=\"http://get.adobe.com/cn/flashplayer/\" style=\"color: blue;\">Here</a> Install</h1>");
//   }
//  } else {
//   try {
//    var swf2 = navigator.plugins['Shockwave Flash'];
//    if (swf2 == undefined) {
//     //alert('没有安装Flash');
//    	 $("body").html("<h1>You Are Not Install Flash, Click <a href=\"http://get.adobe.com/cn/flashplayer/\" style=\"color: blue;\">Here</a> Install</h1>");
//    }
//    else {
//
//     // alert('安装了Flash');
//    }
//   }
//   catch (e) {
//    //alert('没有安装Flash');
//	   $("body").html("<h1>You Are Not Install Flash, Click <a href=\"http://get.adobe.com/cn/flashplayer/\" style=\"color: blue;\">Here</a> Install</h1>");
// }
//
//  }
  var fls=flashChecker();
  if(!fls.f){
   $("body").html("<h1>You Are Not Install Flash, Click <a href=\"http://get.adobe.com/cn/flashplayer/\" style=\"color: blue;\">Here</a> Install</h1>");
  }
 });
 function flashChecker()
 {
  var hasFlash=0;
  var flashVersion=0;

  if(document.all)
  {
   var swf = new ActiveXObject('ShockwaveFlash.ShockwaveFlash');
   if(swf) {
    hasFlash=1;
    VSwf=swf.GetVariable("$version");
    flashVersion=parseInt(VSwf.split(" ")[1].split(",")[0]);
   }
  }else{
   if (navigator.plugins && navigator.plugins.length > 0)
   {
    var swf=navigator.plugins["Shockwave Flash"];
    if (swf)
    {
     hasFlash=1;
     var words = swf.description.split(" ");
     for (var i = 0; i < words.length; ++i)
     {
      if (isNaN(parseInt(words[i]))) continue;
      flashVersion = parseInt(words[i]);
     }
    }
   }
  }
  return {f:hasFlash,v:flashVersion};
 }
</script>
