<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn"   uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../common/common_js.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=localServer%>/online/OfficeContorlFunctions.js"></script>
<title>文档</title>
<script>
// 本地路径
var localServer_offControll = '<%=localServer%>';
// 下载路径
var downloadUrl = '<z:ukey key="public.upload.download"  context="admin"/>&attachmentId=';
<c:if test="${isPdfReport}">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools'], function($, CALLBACK, ZTOOL) {
	intializePage(downloadUrl + '${pdfInfo.id}');
	$("#officecontrol").css("height",window.screen.height - 270);
});
function OnDocumentOpenedForAll() {
	OFFICE_CONTROL_OBJ.activeDocument.saved=true;//saved属性用来判断文档是否被修改过,文档打开的时候设置成ture,当文档被修改,自动被设置为false,该属性由office提供.
	//获取文档控件中打开的文档的文档类型
	switch (OFFICE_CONTROL_OBJ.doctype)
	{
		case 1:
			fileType = "Word.Document";
			fileTypeSimple = "wrod";
			break;
		case 2:
			fileType = "Excel.Sheet";
			fileTypeSimple="excel";
			break;
		case 3:
			fileType = "PowerPoint.Show";
			fileTypeSimple = "ppt";
			break;
		case 4:
			fileType = "Visio.Drawing";
			break;
		case 5:
			fileType = "MSProject.Project";
			break;
		case 6:
			fileType = "WPS Doc";
			fileTypeSimple="wps";
			break;
		case 7:
			fileType = "Kingsoft Sheet";
			fileTypeSimple="et";
			break;
		case 8:
			fileType = "Kingsoft Sheet";
			fileTypeSimple="et";
			break;
		default :
			fileType = "unkownfiletype";
			fileTypeSimple="unkownfiletype";
	}
	setFileOpenedOrClosed(true);
	
	var offDocStatus = "${offDocVo.status}";
	if(OFFICE_CONTROL_OBJ){
		if(offDocStatus!=null && offDocStatus!='' && offDocStatus!='WAITINGREG' && (offDocStatus!='NEW' && offDocStatus!='BESEND')){
		
			//设置留痕
			SetReviewMode(true);
			setShowRevisions(true);
			//接受修订
			//OFFICE_CONTROL_OBJ.ActiveDocument.AcceptAllRevisions();
		}
		else{
			//取消留痕
			SetReviewMode(false);
			setShowRevisions(false);
		}
	}
}
</c:if>
</script>
</head>
<body style="overflow: auto;">
<c:if test="${empty isPdfReport }">
	暂无该客户的征信报告信息！
</c:if>
<c:if test="${not empty errorMsg }">
	查询客户征信报告发生异常！异常信息为：${errorMsg}
</c:if>
<c:if test="${isPdfReport}">
<form id="form1" action="" enctype="multipart/form-data" style="padding:0px;margin:0px;" method="post">
	<div id="officecontrol" style="width:100%;height:750px;">
           <script type="text/javascript" src="<%=localServer%>/online/officecontrol/ntkoofficecontrol.js"></script>
           <div id=statusBar style="height:20px;width:100%;font-size:12px;"></div>
		<script language="JScript" for=TANGER_OCX event="OnDocumentClosed()">
			setFileOpenedOrClosed(true);
		</script>
		<script language="JScript" for="TANGER_OCX" event="OnDocumentOpened(TANGER_OCX_str,TANGER_OCX_obj)">
			OnDocumentOpenedForAll();
		</script>
      </div>
</form>
</c:if>
<c:if test="${ not isPdfReport and not empty imgFiles }">
<div class="frm-content">
    <div class="page-box">
        <h1 class="page-title">征信报告查看</h1>
        <div class="p10 picScroll-screen">
            <div  class="picScroll-current">
                <div class="crt-img" id="picShow"></div>
                <div class="crt-icon"><span class="crt-page">共${fn:length(imgFiles)}张</span></div>
            </div>
            <div class="picScroll-left">
                <div class="bd">
                    <a class="prev"><i class="icon-power-add"></i></a>
                    <a class="next"><i class="icon-power-delete"></i></a>
                    <ul class="picList" id="picList">
                    	<c:forEach items="${imgFiles }" var="imgFile" varStatus="status" >
                    		<c:choose>
                    			<c:when test="${status.index eq 0 }">
                    				<li class="active"><img src="../upload/download?attachmentId=${imgFile.id }" /></li>
                    			</c:when>
                    			<c:otherwise>
                    				 <li><img src="../upload/download?attachmentId=${imgFile.id }" /></li>
                    			</c:otherwise>
                    		</c:choose>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
seajs.use(['jquery', 'zd/jquery.zds.page.callback','zd/tools', 'zd/jquery.zds.table',
        'zd/jquery.zds.form','viewer/iviewer','viewer/superslide'], function ($, CALLBACK,ZTOOL) {
   $(function(){
	   //初始化轮播图片
	    $(".picScroll-left").slide({
	        mainCell:".bd ul",
	        autoPage:true,
	        effect:"left",
	        autoPlay:true,
	        vis:5,
	        scroll:5
	    });
	    //初始化当前图片
	    ZTOOL.initView('#picList','#picShow');
   });
   
});
</script>
</c:if>
</body>
</html>
