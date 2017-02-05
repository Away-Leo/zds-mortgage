<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<div class="page-box">
	<h1 class="page-title">审批意见</h1>
	<div id="opinionForm" class="p5">
	<form id="taskProcessOptionApproval" class="zui-form">
	<table class="table-flow" id="history2" ></table>
	<table class="table-flow" id="qianpi2">
			<tr>
				<th rowspan="3" style="width: 120px;"><strong id="taskNameId"></strong></th>
				<th class="tr" id="opinionTaskName1">操作</th>
				<td colspan="5" class="tl">
					<dl class="form-item">
						<dd class="detail" >
							<input class="zui-combobox zui-validatebox" id="opinionApproval"   type="hidden"
                               data-data="[{'id':'1','text':'同意'},{'id':'2','text':'不同意'},{'id':'3','text':'退回'}]"
                               data-valuefield="id" data-textfield="text" validate-type="Require">
						</dd>
					</dl>
				</td>
			</tr>
			<tr >
				<th class="tr">风险措施</th>
				<td colspan="5" class="tl">
					<dl class="form-item" style="width: 100%;display: block;">
	                  	<dd class="detail">
	                        <input class="zui-checkbox zui-validatebox" id="approvalOpinion" type="hidden"
									   data-multiple="true"
									   data-data='${approvalOpinion}'
									   data-valuefield="id" data-textfield="remark" data-newline ="true">
	                	</dd>
					</dl> 
				</td>
			</tr>
			<tr>
				<th class="tr">审批</th>
				<td colspan="5" class="tl">
					<dl class="form-item block">
						<dd class="detail">
							<label>
	                            <textarea id="opinioncontentApproval"  class="zui-area zui-validatebox" validate-type="Require"
							  placeholder="最多可以输入3000个字符" style="height: 100px;"></textarea>
							</label>
						</dd>
					</dl>
				</td>
			</tr>
		</table>
	</form>
</div>
<script>
	seajs.use(['jquery', 'zd/iframe', 'zd/tools','zd/jquery.zds.page.callback','zd/jquery.zds.loading','zd/jquery.zds.message','zd/jquery.zds.combobox','zd/jquery.zds.dialog','zd/jquery.zds.seleter','common/zds-common-selecter','zd/jquery.zds.form'],function($,IFRAME, ZTOOlS,CALLBACK,Loading){
		//设置自定义审批意见
		isCustomOpinion_=true;
		
		//$("#opinionApproval").val(opinionId);
		
		//加载保存签批意见
		//$("#opinioncontentApproval").val($("#opinion_content").val());
		
		//是否加载过历史意见
		var historyFlag = false;
		
		$("#taskNameId").html(taskName);
		
		/**
		 * 加载历史意见
		 */
		CALLBACK.loadHistory2=function(){
			//判断是否加载过历史意见
			if(!historyFlag){
				var errorMsg="";
				var type="POST";
				$.ajax({
					type : type,
					dataType : "jsonp",
					url : findOtherTaskOptionsUrl,
					data : {
						processInstanceId:processInstanceId,
						businessKey:businessKey,
						taskInstId:taskInstanceId,
						taskCandidateId:taskCandidateId
					},
					success : function(data) {
						if( data.resultStatus =="0") {
							if(!$.isEmptyObject(data)){
								var rows = data.rows;
								if(!$.isEmptyObject(rows)){
									var _in=1;
									var _length=rows.length;
									for(var key in rows){
										var row = rows[key];
										if(row.length==0){
											break;
										}
										var html ='<tr> ';
										html+='<th rowspan="2" style="width: 120px;" id="taskLabel"><strong>'+undefinedToStr(row.taskLabel)+'</strong></th>';
										html+='<th class="tr" style="width: 120px;">审批人</th>';
										html+='<td class="tl" id="employeeName">'+undefinedToStr(row.employeeName)+'</td>';
										html+='<th class="tr">审批时间</th>';
										html+='<td class="tl" id="issueTime">'+undefinedToStr(row.issueTime)+'</td>';
										html+='<th class="tr">意见结论</th>';
										var opinionLabel = "" ;
										if(row.opinion==1){
											opinionLabel = "同意";
										}else if(row.opinion == 2){
											opinionLabel = "不同意";
										}else if(row.opinion == 3){
											opinionLabel = "退回";
										}
										html+='<td class="tl" id="opinionLabel">'+undefinedToStr(opinionLabel)+'</td> ';
										html+='</tr>';
										html+='<tr>';
										html+='<th class="tr">审批意见</th>';
										html+='<td colspan="5" class="tl"> <input type="hidden" id="opinionContent" value="'+undefinedToStr(row.opinionContent)+'">';
										if(undefinedToStr(row.opinionContent).length>=98){
											html+=undefinedToStr(row.opinionContent).substr(0,98)+' <a href="javascript:void(0);"  class="ca-blue more" >显示更多</a>';
										}else{
											html+=undefinedToStr(row.opinionContent);
										}
										html+='</td> ';
										html+='</tr>';
										$("#history2").append(html);
										_in++;
									}
									//显示更多
									//============================================================
									$('.more').on('click', function () {
										var opinionContent_temp =$(this).parent().parent().find("#opinionContent").val();
										var opinionContent =opinionContent_temp.substr(0,opinionContent_temp.length-5);
										var employeeName = $(this).parent().parent().prev().find("#employeeName").text();
										var issueTime = $(this).parent().parent().prev().find("#issueTime").text();
										var opinionLabel = $(this).parent().parent().prev().find("#opinionLabel").text();
										var taskLabel = $(this).parent().parent().prev().find("#taskLabel").text();
										var html = '<table class="table-flow"><tr> <th class="tr">审批人</th> <td>'+employeeName+'</td> <th class="tr">审批时间</th> <td>'+issueTime+'</td> <th class="tr">意见结论</th> <td colspan="2">'+opinionLabel+'</td></tr><tr><th class="tr vt">审批意见</th> <td colspan="6" class="tl">'+opinionContent+'</td></tr></table>';
										$.ZMessage.html(taskLabel, html);
									});
								}else{
									isShowHisTory=false;
									$("history2").hide();
								}
							}
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						if(errorMsg){
							$.ZMessage.warning("警告",errorMsg);
						}
						if(typeof errorcallback=="function"){
							errorcallback();
						}
					}
				});
				
				historyFlag = true;
			}
		};
		//历史意见
		CALLBACK.loadHistory2();
		$.ZUI.initForms("#opinionForm");
	});
</script>