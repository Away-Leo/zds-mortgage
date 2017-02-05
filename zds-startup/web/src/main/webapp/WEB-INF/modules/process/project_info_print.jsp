<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<%@ include file="../common/common_js.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>项目文件夹打印</title>
 <script type="text/javascript">
seajs.use(['jquery'], function ($) {

	var businessKey = "${businessKey}";
	var businessUrl = "${businessUrl}";//真实环境
	//var businessUrl =resServerHTMLURL+'/business_demo4.0/dailycheckListFolderView.html';
	var actInstId ='${actInstId}';
	var currentUser='${currentUser}';
	var projectId='${projectId}';
	//var previousOpinionUrl='<z:ukey key="workflow.process.findOtherTaskOptions"  context="admin"/>&jsoncallback=?';
	//var previousOpinionUrl=resServerURL+'/FindOtherTaskOptions?jsoncallback=?';

	var orderSx=true;//是否为升序
	var isLoadTaskFrom=false;
	$(function() {
		if(businessUrl && businessUrl!=undefined && businessUrl!=''){
			//var frameSrc = businessUrl + '?id=' + businessKey+'&businessKey='+businessKey+'&processInstanceId='+actInstId+'&bpmMode=false&projectId='+projectId;
			var frameSrc = businessUrl;
			//$("#target_frame").attr("src",frameSrc);
			$("#divBusinessFrame").hide();
			$("#divBusinessFrame").load(frameSrc,function(){
				setTimeout("changeLiToTable()", 500 ); 
			});
		}
		//loadTask();
	});
	
	window.windowPrint=function(){
		$("#footButton_").hide();
		window.print();
		window.close();
	}
	var i=0,j=0;
	function expressionHTML(obj){
		obj.find(".page-box").each(function(index){
			var tableId = "dtablePrint"+i;
			if($(this).find(".page-title").html()!=null)
				$("#divBusinessFrameTemp").append('<h2 class="print-subtitle">'+$(this).find(".page-title").html()+'</h2>');
			if($(this).children().last().length>0){
				$(this).each(function(jndex){
					tableId=tableId+"_"+j;
					var count = $(this).find('.form-item').length;
					if(count==0){//table
						var tableTemp = $(this).find("table");
						tableTemp.removeClass();
						tableTemp.addClass("table-print");
						tableTemp.find("tr").removeClass();
						tableTemp.find("th").removeClass();
						tableTemp.find("td").removeClass();
						$("#divBusinessFrameTemp").append(tableTemp);
					}else{//item
						$("#divBusinessFrameTemp").append('<div class="print-box"><table class="table-print" id="'+tableId+'"></table></div>');
						var tr="<tr>";
						var td=0;
						$(this).find('.form-item').each(function(k){
							var falg = false;
							if($(this).attr("class")&&($(this).attr("class").indexOf("li600")>=0||$(this).attr("class").indexOf("autotext")>=0)){
								falg = true;
							}
							if((count-1)==k){
								if(td==2){
									tr+="</tr></tr>";
								}else{
									tr+="<td>&nbsp;</td><td>&nbsp;</td></tr></tr>";
								}
								if(falg){
									tr+=Iterator(this);
								}else{
									tr+=Iterator(this);
									tr+='<td>&nbsp;</td><td>&nbsp;</td></tr>';
								}
								$("#"+tableId).append(tr);
							}else{
								if(td%2==0&&k!=0){
									tr+="</tr>";
									$("#"+tableId).append(tr);
									tr="<tr>";
									if(falg){
										tr+=Iterator(this);
										td = 2;
									}else{
										tr+=Iterator(this);
										td = 1;
									}
								}else{
									if(falg){
										tr+='<td>&nbsp;</td><td>&nbsp;</td></tr><tr>';
										tr+=Iterator(this);
										td = 2;
									}else{
										tr+=Iterator(this);
										td++;
									}
								}
							}
						});
						j++;
					}
						
			});
			}else{
					$("#divBusinessFrameTemp").append($(this).find(".main_key_center").children());
			}
			i++;
		});
	}

	window.changeLiToTable=function(){
		var obj = $("#divBusinessFrame");
		$(".container").append("<div id='divBusinessFrameTemp' ></div>");
		expressionHTML(obj);
		obj.empty();
		var taskFrom = $("#taskFrom");
		$(".container").append(taskFrom.html());
		taskFrom.empty();
	}
	/**
	*迭代页面数据
	**/
	function Iterator(obj){
		var childObj = $(obj).children();
		var td = "";
		if($(obj).attr("class")&&$(obj).attr("class").indexOf("autotext")>=0&&childObj.length==1&&$(obj).children("table")){
			childObj.find("td").each(function(i){
				//获取内容
				var textValue=$(this).find(':last').val();
				if(textValue==undefined || textValue==''){
					textValue=$(this).html();
				}
				if(i==0){
					td+='<td class="right">'+textValue+'&nbsp;</td>';
				}else{
					td+='<td colspan="3">'+textValue+'&nbsp;</td>';
				}
			});
		}else{
			//获取内容
			var textValue=$(childObj[1]).find(':last').val();
			if(textValue==undefined || textValue==''){
				textValue=$(childObj[1]).html();
			}
			td+='<td class="right">'+$(childObj[0]).html()+'&nbsp;</td>';
			td+='<td>'+textValue+'&nbsp;</td>';
		}
		return td;
	}
	/**
	 * 加载历史审批意见
	 */
	function loadTask() {
		var callback=function(data){
			//默认初始化当前页面的审批时间
			if( data.resultStatus==0 && data.rows) {
				var dataObj = data.rows;
				var len = dataObj.length;
				if (len == 0) {
					return;
				}
				var isShowHistory=false;;
				var results=new Array();
				for ( var i = 0; i < len; i++) {
					var obj = dataObj[i];
					isShowHistory=true;				
					results.push({id:obj.id,opinionLabel:obj.taskLabel,employeeName:obj.employeeName,opinion:obj.opinionLabel,issueTime:obj.issueTime,confirm:obj.opinionContent});
				}
				if(isShowHistory){
					 $("#taskFrom_histroy").show();
					 //initTaskFromHis();
					 //$("#opinion_history").datagrid("loadData",{rows:results,total:results.length});
					 for(var key in results){
						var row = results[key];
						var opinion="同意";
						var confirm="";
						if(row.opinion) opinion=row.opinion;
						if(row.confirm) confirm=row.confirm;
						if(row.opinion){
							var html="	<tr>";
							html+='<td rowspan="2" class="right">'+row.opinionLabel+'：</td>';
							html+='<td colspan="4">';
							html+='<p>意见结论：'+opinion+'</p>';
							html+='<p>审批意见：'+confirm+'</p>';
							html+='</td></tr>';
							html+="	<tr>";
							html+='<td class="right">审批人：</td>';
							html+='<td>'+row.employeeName+'</td>';
							html+='<td class="right">审批时间：</td>';
							html+='<td>'+row.issueTime+'</td></tr>';
							$("#opinion_history").append(html);
						}
					 }
				}
			}
		};
		var datas={businessKey : businessKey, isAsc:true};
		var errorMsg="读取前置审批信息失败!";
		var type="get";
		doAjax(previousOpinionUrl,type,datas,callback,errorMsg);
	}
	
	/**
	 *执行Ajax请求
	 */
	function doAjax(url,type,datas,callback,errorMsg){
		$.ajax({
			type : type,
			dataType : "json",
			url : url,
			data : datas,
			success : function(result) {
				if(typeof callback=="function"){
				callback(result);
				}
			},
			error : function() {
				$.messager.alert("提醒","请求错误", 'warning');
			}
		});
	}
});
</script>
 
</head>
<body style="width:790px;margin-left:20px;font-size: 15px;">
<div class="container" >
	<div class="tr mt20" id="footButton_" style="display: block;">
		<button id="printBtn" class="mr10 btn-blue" onclick="windowPrint()" >打印</button>
	</div>
	<div id="divCenter" style="margin-top:40px;">
		<div style="text-align:center;font-size:20px; font-weight:bold;">${nodeName}</div>
		<div class="contents" id="divBusinessFrame" style="font-weight: bold;font-size: 15px;">
		</div>
		<div class="contents_" id="taskFrom" style="font-weight: bold;">
			<!-- 历史审批意见 -->
			<h2 class="print-subtitle">审批记录</h2>
			<div class="print-box">
				<table id="opinion_history" class="table-print" ></table>
			</div>
		</div>
	</div>
</div>

</body>
</html>