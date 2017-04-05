<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ include file='../WEB-INF/modules/common/common_js.jsp'%>
<%@ include file="../WEB-INF/modules/common/common_upload.jsp"%>
 <link href="js/css/ViewPrint.css" rel="stylesheet" type="text/css" />
    <link href="js/css/OnlyPrintCss.css" rel="stylesheet" media="print" type="text/css" />

<title>Insert title here</title>
	
</head>
<body>
    <div class="screen-only">
        <input type="button" value="全部打印" onclick="doPrint()">
        <span id="ContractTemplateNameTip"></span>

    </div>
      
      


 
 <script src="js/updateTableCss.js" type="text/javascript"></script>
 <script src="js/jquery.jqprint-0.3.js" type="text/javascript"></script>


    <script type="text/javascript">
	seajs.use(
			[ 'jquery', 'zd/jquery.zds.page.callback',
					 'zd/jquery.zds.message',
					'zd/jquery.zds.dialog',
					
					'zd/jquery.zds.table' ],
			function($, CALLBACK, Zdialog) {
				
				
       // var pageNum = getParam("pageNum");
        var autoid = getParam("caseApplyId");//案件号
        var hidContractTemplateId = getParam("ContractTemplateId");
        var TradeObjectID = getParam("TradeObjectID");
        var flagName = getParam("flagName");
        var ContractTemplates = {};

        if ($(".jp-page").length > 0)
            return;

    
        	
        if (hidContractTemplateId != null && hidContractTemplateId.length > 0) {//全部预览

        	var urlgetPagePath= '<z:ukey key="com.zdsoft.finance.contract.getTplAndPageByPrintTplId" context="admin"/>';
        	
            $.get(urlgetPagePath, { printTemplateId: hidContractTemplateId }, function(result) {
               var resultList=$.parseJSON(result);
            	var templateName=resultList.templateName;
            	ContractTemplates = resultList.ContractTemplates;
             
                $("#ContractTemplateNameTip").text('模版名：《' + templateName + '》  案件号：' + autoid);
                $(document).attr("title", templateName);
                for (k = 0; k < ContractTemplates.length; k++) {
                	var filePath=essdownUrl + '&attachmentId=' + ContractTemplates[k].attachmentid;
                    ShowLabelLoadcation(ContractTemplates[k].id, ContractTemplates[k].pagename, filePath, ContractTemplates[k].topmargin, ContractTemplates[k].leftmargin);
                }

                $(".jp-content:last").css("page-break-after", "auto");
            });

        }
			});
    


    //获取URL参数
    function getParam(paramName) {
        paramValue = "", isFound = !1;
        if (this.location.search.indexOf("?") == 0 && this.location.search.indexOf("=") > 1) {
            arrSource = unescape(this.location.search).substring(1, this.location.search.length).split("&"), i = 0;
            while (i < arrSource.length && !isFound) arrSource[i].indexOf("=") > 0 && arrSource[i].split("=")[0].toLowerCase() == paramName.toLowerCase() && (paramValue = arrSource[i].split("=")[1], isFound = !0), i++;
        }
        return paramValue == "" && (paramValue = null), paramValue;
    }

    //显示标签位置
    function ShowLabelLoadcation(pageID, pageName, pagePath, topPad, leftPad) {

        var vLen = $(".jp-page").length + 1;
        var pageIDName = "page" + vLen;
        var contantID = 'contant' + pageIDName;
        
        var backgroundDiv = '<div class="jp-content" id="' + contantID + '"><div id="' + pageIDName + '" class="jp-page" style="top:' + topPad + 'px;left:' + leftPad + 'px"><img class="jp-paper-background screen-only" src="' + pagePath + '"/></div></div>';

        $("body").append('<div class="screen-only" style="width:200mm;text-align:right;"><span>页名：' + pageName + '</span><input  type="button" value="打印当前页" onclick="doPrintSingle(\'' + contantID + '\')"></div>');
        $("body").append(backgroundDiv)



        $.ajaxSetup({
            async: false //取消异步
        });

        var getLabelUrl = '<z:ukey key="com.zdsoft.finance.contract.printTplPageFieldList" context="admin"/>';
		var autoid =0;
		var TradeObjectID=0;
		var flagName="";
        $.get(getLabelUrl, { PageID: pageID, actionname: "view", Autoid: autoid,TradeObjectID: TradeObjectID,flagName:flagName }, function(result) {

            if (result != null && result.length > 0) {

                var listPosition = jQuery.parseJSON(result);
                for (i = 0; i < listPosition.length; i++) {
                    var item = listPosition[i];

                    var element = '<div class="jp-text" ' +
    'style="top: ' + item.top + 'px; left: ' + item.left + 'px; width: ' + item.width + 'px; height: ' + item.height + 'px; z-index: 10' + i.toString() + ';">'

     +
    '<span class="jp-text-content" id="' + item.labelid + '">' + item.labelname + '</span>'


    + '</div>';

                    $("#" + pageIDName).append(element);


                }

            }

        });

    }

    //全部打印
    function doPrint() {

        //$("body").jqprint();
        window.print();
    }

    //单个打印
    function doPrintSingle(pageName) {
        $("#" + pageName).css("page-break-after", "auto");
        $("#" + pageName).jqprint();

    }
    </script>
 
</body>
</html>