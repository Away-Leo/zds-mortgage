<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="z" uri="http://www.zdsoft.cn/tags" %>  
		<div class="frm-content">     

			<div class="page-box">
				<div class="page-title">案件信息</div>
				<div class="p5">
					<table class="table-detail">
						<tr>
							<td class="td-title pct10">案件号</td>
							<td class="pct20">
								${caseApplyVo.caseApplyCode }
							</td>
							<td class="td-title pct10">机构</td>
							<td class="pct20">
								${caseApplyVo.mechanismName }
							</td>
							<td class="td-title pct10">主借人</td>
							<td class="pct30">
								${customerVo.customerName }
							</td>
						</tr>
						<tr>
							<td class="td-title pct10">贷款金额</td>
							<td class="pct20">
								${caseApplyVo.applyAmount }
							</td>
							<td class="td-title pct10">贷款期限</td>  
							<td class="pct20">
							<c:if test="${not empty caseApplyVo.applyTerm }">
								${caseApplyVo.applyTerm }/${caseApplyVo.applyTermUnitName }
							</c:if>
							</td>    
							<td class="td-title pct10">子产品</td>
							<td class="pct30">
								${caseApplyVo.productSubtypeName }
							</td>
						</tr>
						<tr>
							<td class="td-title pct10">综合利率</td>  
							<td class="pct20">  
							<c:if test="${not empty caseApplyVo.synthesizeRateUnitName}"> 
								${caseApplyVo.synthesizeRate }${caseApplyVo.synthesizeRateUnitName }
							</c:if>
							</td>
							<td class="td-title pct10">还款方式</td>
							<td class="pct20">
							${caseApplyVo.repayMethodName }
							</td>
							<td class="td-title pct10">资金来源</td>
							<td class="pct30">
							${cooperatorName }
							</td>
						</tr>
						<tr>
							<td class="td-title pct10">评估价抵押成数</td>
							<td class="pct20">
							${caseApplyVo.assessedPriceMortgage }
							<c:if test="${not empty caseApplyVo.assessedPriceMortgage }">%</c:if>    
							</td>
							<td class="td-title pct10">贷款成数</td>  
							<td class="pct20">
							${caseApplyVo.loanNumber } 
							<c:if test="${not empty caseApplyVo.loanNumber }">%</c:if>    
							</td>
							<td class="td-title pct10"></td>
							<td class="pct30"></td>
						</tr>
					</table>
				</div>
				<div class="page-title">复核结果</div>
				<div class="p10">
					<div class="zd-form">
						<form id="dialogFor4" class="zui-form" action="javascript:void(0);">
							<input type="hidden" id="complianceReviewId" value="${complianceReviewVo.id }"></input>   
							<table class="table-detail"> 
								<tr>
									<td class="td-title pct20"><b class="c-red mr5">*</b>复核结果</td>
									<td class="pct30">   
										<dd class="detail" id="detail12">
											<input class="zui-checkbox zui-validatebox" id="reviewResult" type="hidden" data-multiple="false" data-data="[{'id':'1','text':'复核通过'},{'id':'2','text':'复核未通过'},{'id':'3','text':'否决'}]"
											 data-valuefield="id" data-textfield="text" data-defaultvalue="${empty complianceReviewVo.reviewResult?'2':complianceReviewVo.reviewResult }" validate-type="Require">
										</dd>
									</td>
									<td class="td-title pct20"></td>
									<td class="pct30">
									</td>
								</tr>  

							</table>
						</form>
					</div>
				</div>
				<div id="zd-table-warp">
					<div class="page-title">复核信息</div>
					<div class="p10">
						<div class="zd-form">
							<form id="dialogFor3" class="zui-form" action="javascript:void(0);" >
								<table class="table-detail">
									<tr>
										<td class="td-title pct10">一级标示</td>   
										<td class="pct20">
											<dl class="form-item form-auto">
												<dd class="detail">
													<input class="zui-combobox zui-validatebox" type="hidden"    id="firstMarking"   
													data-url="<z:ukey key='com.cnfh.rms.casemanage.complianceReview.getFirstMark' context='admin'/>&jsoncallback=?"
													data-callback="firstMarking" data-valuefield="fullCode" data-textfield="name">
												</dd>
												  
											</dl>
										</td>    
										<td class="td-title pct10">二级标示</td>
										<td class="pct20">
											<dl class="form-item form-auto">
												<dd class="detail">
													<input class="zui-combobox zui-validatebox" type="hidden" id="secondMarking">
												</dd>
											</dl>
										</td>
									</tr>
								</table>

							</form>
						</div>
					</div>
					<div class="p10">
						<div id="zd-table-reviewInfo"></div>
					</div>
				</div>
				
				  
				<div class="p10">
						<div class="zd-form">
							<form id="dialogFor3" class="zui-form" action="javascript:void(0);">
								<table class="table-detail">
									<tr>
										<td class="td-title">描述</td>
										<td>
											<label>
                            						<textarea class="zui-area zui-validatebox row-width" id="remark" placeholder="最多可以输入200个字符" validate-type="Length[0-200]">${complianceReviewVo.remark }</textarea>
                   							 </label>
										</td>
									</tr>
								</table>
							</form>
						</div>
					</div>
					<div class="page-title">后补资料</div>
					<div class="p10">
						<div id="zd-table-reviewInfo2"></div>
					</div>
					
					
					
			</div>
		</div>

		</div>   
		<input type="hidden" id="personnelSelector"  class="zui-input" />
		<script>
			seajs.use(['jquery', 'zd/tools', 'zd/jquery.zds.page.callback','zd/jquery.zds.loading', 'zd/jquery.zds.table', 'zd/jquery.zds.form'], function($, ZTOOL, CALLBACK,Loading) {
				var paramMark = "";
				//记录点击触发人员选择器的input框
				var _this_personLiable=null;
				var firstMarkingUrl =  '<z:ukey key="com.cnfh.rms.casemanage.complianceReview.getMarkByParent" context="admin"/>&jsoncallback=?';
				//选择一级标示 
				CALLBACK.firstMarking = function(value, text) {   
					$("#zd-table-reviewInfo").ZTable("reload",{firstMark:value});   
					$("#secondMarking").ZCombobox({    
						valueField: "fullCode",
						textField: "name",         
						url: firstMarkingUrl+"&id="+value,
						onSelect: function(value, text, index, data) {
							var firstMarkFullcode = $("#firstMarking").val();   
							$("#zd-table-reviewInfo").ZTable("reload",{firstMark:firstMarkFullcode,secondMark:value});
						}  
					});     
					
				};
				

				$.ZUI.initForms('.zd-form');
				$('#zd-table-reviewInfo').ZTable({
					columns: [
						[{
							field: 'personLiableCode',         
							title: '责任人code',   
							align: 'center',
							width: '20%',
							hidden:true
						},{
							field: 'firstMarkFullCode',         
							title: '一级code',
							align: 'center',
							width: '20%',
							hidden:true
						},{
							field: 'secondMarkFullCode',  
							title: '二级code',
							align: 'center',
							width: '20%',
							hidden:true          
						},{
							field: 'threeMarkFullCode',
							title: '三级code',
							align: 'center',
							width: '20%',  
							hidden:true
						},{
							field: 'threeMarkFullCodeNameText',  
							title: '三级name',    
							align: 'center',
							width: '20%',  
							hidden:true
						},{
							field: 'firstMarkFullCodeName',
							title: '一级标示',
							align: 'left',
							width: '20%'
						}, {
							field: 'secondMarkFullCodeName',
							title: '二级标示',
							align: 'left',
							width: '200'
						}, {
							field: 'threeMarkFullCodeName',
							title: '三级标示',
							align: 'left',
							width: '200'
						}, {
							field: 'personLiableName',
							title: '负责人',
							align: 'center',
							width: '100', 
			         	   formatter: function (r, v) {
			             	   }
						}]
					],  
					columnsType: [{
						"personLiableName": {
							"inputType": "input"
						},
						"threeMarkFullCodeName": {
							"inputType": "checkbox",
							"data": {
								"valueField": "checked",
								"textField": "name",
								"multiple": true,
								"singleCheck": true,
							}
						}

					}, {
						"inputWidth": 140,
						"inputHeight": 24,
						"areaWidth": 200,     
						"areaHeight": 24
					}],
					idField: 'threeMarkFullCodeName', //每行数据的，唯一标识符    
					url: '<z:ukey key="com.cnfh.rms.casemanage.complianceReview.getAllMarkByParent" context="admin"/>&jsoncallback=?&complianceReviewId='+$("#complianceReviewId").val(),   
				/* 	data:﻿fuzayemian,   */    
					//url: 'fuzayemian.json',     
					singleSelect: true, //表格行是单选还是多选
					isRowNum: false, //是否显示行号
					pagination: false, //表格是否分页,合并单元格暂时不支持分页
					tableCls: 'table-index', //table的样式
					batchSave: false, //默认为true，是否批量保存
					isMergeCell: true, //默认为false,合并单元格列表
					mergeColField: 'firstMarkFullCodeName', //需要合并指定字段  
					mergeCol:'firstMarkFullCodeName,secondMarkFullCodeName',  
					onEdit: true, //启用所有行编辑
					onSelect: function(rowIndex, rowData) {
						//alert("选中" + rowIndex+'='+JSON.stringify(rowData));
					},
					onUnselect: function(rowIndex, rowData) {
						//alert("选中" + rowIndex+'='+"取消" + rowIndex+'='+JSON.stringify(rowData));
					},
					onMergeSave: function(rowIndex, rowData) {
						//保存数据到数据库
						return true;
					},
					onLoadSuccess:function() {
					}
				});
				$('#zd-table-reviewInfo2').ZTable({
					columns: [
						[{
							field: 'id',
							title: 'id',
							align: 'center',
							width: '20%',
							hidden:true
						},{
							field: 'materialTypeCode',
							title: '后补资料类型code',
							align: 'center',
							width: '20%',
							hidden:true
						},{
							field: 'pMaterialTypeCode',
							title: '父后补资料类型code',
							align: 'center',
							width: '20%',
							hidden:true   
						},{
							field: 'pMaterialTypeName',
							title: '父后补资料类型name',
							align: 'center',
							width: '20%',
							hidden:true
						},{
							field: 'materialTypeName',
							title: '后补资料类型',
							align: 'center',
							width: '20%'
						}, {
							field: 'predictDate',
							title: '预计后补时间',
							align: 'center',
							width: '200',     
							formatter: function (r, v) {      
								if(v && v>0){               
									return window.formatDate(r,v);//字符串转换为日期字符串20150101==>2015-01-01
 								}else{
									return '';
								}
					                    
							}   
						}, {
							field: 'operatorCode',
							title: '经办人CODE',
							align: 'center',
							width: '200',
							hidden:true
						}, {
							field: 'operatorName',
							title: '经办人',
							align: 'center',
							width: '200'
						}, {
							field: 'promiseRemark',
							title: '备注',
							align: 'center',
							width: '100'
						}]
					],
					columnsType: [{  
						"promiseRemark": {  
							"inputType": "input" ,
							 "validateType": "Length[0-200]"   
						},
						"predictDate": {    
                        "inputType": "date",
                       /*  "validateType": "Require", */
                        "dateStyle":"{realDateFmt:'yyyyMMdd',vel:'changeNotarizeDate',dateFmt:'yyyy-MM-dd'}"
                    },

					}, {
						"inputWidth": 140,
						"inputHeight": 24,
						"areaWidth": 200,
						"areaHeight": 24
					}],  
					idField: 'id', //每行数据的，唯一标识符
					url: '<z:ukey key="com.cnfh.rms.casemanage.complianceReview.findMaterialPromiseByCaseApplyId" context="admin"/>&jsoncallback=?&caseApplyId=${caseApplyVo.id}',   
					singleSelect: true, //表格行是单选还是多选
					isRowNum:false, //是否显示行号  
					pagination: false, //表格是否分页,合并单元格暂时不支持分页
					tableCls: 'table-index', //table的样式
					batchSave: false, //默认为true，是否批量保存
					isMergeCell:false, //默认为false,合并单元格列表
					onEdit: true, //启用所有行编辑
					onSelect: function(rowIndex, rowData) {
						//alert("选中" + rowIndex+'='+JSON.stringify(rowData));
					},
					onUnselect: function(rowIndex, rowData) {
						//alert("选中" + rowIndex+'='+"取消" + rowIndex+'='+JSON.stringify(rowData));
					},
					onMergeSave: function(rowIndex, rowData) {
						//保存数据到数据库
						/* alert('保存的数据:' + JSON.stringify(rowData)); */
						return true;
					}
				});
				//初始数据，判断复核信息列表是否现行
				if('${complianceReviewVo.reviewResult}'=='1'){
					$("#zd-table-warp").css("display", "none");  
				}
				//复核结果点击事件判断是否显示
				$("#detail12").click(function() {            
					if($("#reviewResult").val() == '2' || $("#reviewResult").val() == '3') {
						$("#zd-table-warp").css("display", "block");
					} else {
						$("#zd-table-warp").css("display", "none");

					}
				});
				//人员选择器初始化  
				$("#personnelSelector").commonSelect({rowHeight: "30px", height: 440,width: 750, type: "emp", singleSelect: true,
			        onOk: function (data) { 
			        	if(data && data.length>0){
							var personLiableCode = data[0].empCd;         
							var personLiableName = data[0].empNm; 
							_this_personLiable.parents("tr").find("td[field='personLiableCode']").text(personLiableCode);
							_this_personLiable.val(personLiableName);
						}else{
							_this_personLiable.parents("tr").find("td[field='personLiableCode']").text("");
							_this_personLiable.val("");
							
						}
			     },onBeforeOpen:function(){          
			    	 if(_this_personLiable) {
			         //重新获取列表中的值
			         var arr =[_this_personLiable.parents("tr").find("td[field='personLiableCode']").text(),_this_personLiable.val()];
			         $("#personnelSelector").Zseleter("setValue",arr);
			         $("#zd-seleter-search")[0].reset();    
			    	 }  
			         return true;
			     }});
				       
				//人员选择器绑定
			    $("#zd-table-reviewInfo td[field='personLiableName']").find("input").live("click",function(){
			    	_this_personLiable = $(this);        
			    	$("#personnelSelector").click();
			    	  
			    })
			    
				//保存
			 	ZDS_WORKFLOW_CLIENT.saveFunction = function (datas,isSubmit) {  
					if(!isSubmit){
						isSubmit = false;
					}
					var isValidation = false;
					var complianceReviewList = [];
					if($("#reviewResult").val() != '1'){    
					 $("#zd-table-reviewInfo tr td[field='threeMarkFullCodeName']  .form-checkbox .selected").each(function(){
			    		 var _tr = $(this).parents("tr");         
			    		 //一级code
			    		 var firstMarkCode = _tr.find("td[field='firstMarkFullCode']").text();    
			    		 //一级name
			    		 var firstMarkName = _tr.find("td[field='firstMarkFullCodeName']").text();
			    		 var secondMarkCode = _tr.find("td[field='secondMarkFullCode']").text();
			    		 var secondMarkName = _tr.find("td[field='secondMarkFullCodeName']").text();    
			    		 var thirdMarkCode = _tr.find("td[field='threeMarkFullCode']").text();
			    		 var thirdMarkName = _tr.find("td[field='threeMarkFullCodeNameText']").text();    
			    	 	 var personLiableName = _tr.find("td[field='personLiableName']").find("input").val();   
			    		 var personLiableCode = _tr.find("td[field='personLiableCode']").text();
			    		 if(!personLiableCode){   
			    			 isValidation = true;
			    			 return false;
			    		 }
			    		 complianceReviewList.push({firstMarkCode:firstMarkCode,firstMarkName:firstMarkName,secondMarkCode:secondMarkCode,secondMarkName:secondMarkName,thirdMarkCode:thirdMarkCode,thirdMarkName:thirdMarkName,personLiableName:personLiableName,personLiableCode:personLiableCode});
			    		       
			    	 }); 
					}
			    	if(isValidation){
			    		ZDS_WORKFLOW_CLIENT.callBackFuntion(datas,ZDS_WORKFLOW_PARAM._STATUS_ERROR,"请选择负责人！");
			    		return false;   
			    	}
			    	 
			    	           //---------start------流程中有修改页面，需要提交业务数据操作------------------
			   		//后补资料
					var frows = $('#zd-table-reviewInfo2').ZTable("getRows");
            //校验
            var params = "";  
            var args = JSON.parse(datas.args);
            params += '&processInstanceId=' + args.processInstanceId;
            params += '&taskInstanceId=' + args.taskInstanceId;
            params += '&taskId=' + args.taskId;
            params += '&taskName=' + args.taskName;
            params += '&jobAppCd=' + args.jobAppCd;
            params += '&caseApplyId=${caseApplyVo.id}';
            params += '&businessKey=${businessKey}';
            params += '&reviewInformationStr='+JSON.stringify(complianceReviewList);
            params += '&id=' + $("#complianceReviewId").val();
            params += '&reviewResult=' + $("#reviewResult").val();   
            params += '&remark=' + $("#remark").val();  
            params += '&materialPromiseStr=' +JSON.stringify(frows); 
            params += '&isSubmit=' +isSubmit; 
            Loading.show();
            $.ajax({
                url:'<z:ukey key="com.cnfh.rms.casemanage.complianceReview.saveComplianceReview" context="admin"/>&jsoncallback=?',
                data:params,
                type:"post",
                dataType:"json",
                success:function(rdata){
                	Loading.hide();
                    var msg="";
                    if(rdata.resultStatus == 0){
                    	var id = rdata.id;
                    	$("#complianceReviewId").val(id); 
                    	$("#zd-table-reviewInfo2").ZTable("reload");   
                        //执行回调函数
                        ZDS_WORKFLOW_CLIENT.callBackFuntion(datas,ZDS_WORKFLOW_PARAM._STATUS_SUCCESS,rdata.msg);
                    }else{
                        //执行回调函数
                        ZDS_WORKFLOW_CLIENT.callBackFuntion(datas,ZDS_WORKFLOW_PARAM._STATUS_ERROR,rdata.msg);
                    }
                },
                error: function () {
	            	Loading.hide();
	            	ZDS_WORKFLOW_CLIENT.callBackFuntion(datas,ZDS_WORKFLOW_PARAM._STATUS_ERROR,"操作失败，请联系系统管理员！");
	            }
            });
            //---------end------流程中有修改页面，需要提交业务数据操作------------------
			    };  
			    //提交方法
			    ZDS_WORKFLOW_CLIENT.submitFunction = function(datas){
			    	ZDS_WORKFLOW_CLIENT.saveFunction(datas,true);
			    };
			});
		</script>


