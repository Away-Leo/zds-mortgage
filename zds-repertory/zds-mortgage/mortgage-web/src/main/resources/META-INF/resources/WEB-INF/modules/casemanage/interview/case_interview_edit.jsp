<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<div class ="frm-content">
	<form id="interview_form" class="zui-form " method="post" enctype="multipart/form-data">
	<input type="hidden" id="caseApplyId" name="caseApplyId" value="${caseApplyId }">
	<input type="hidden" id="id" name="id" value="${interviewVo.id}">
	<div class="page-box">
        <h1 class="page-title">面签</h1>
		<div class="p5">
               <div id="tb_housePropertyInfo"></div>
		</div>
   			 <div class="p5">
				<table class="table-detail">
					<tr>
						<td class="td-title pct10" >预计强制执行公证办理时间</td>
						<td class="pct20">
							 <dl class="form-item form-auto">
                                <dd class="detail">
                                <label>
									<input type="text" class="zui-date strToDate  zui-validatebox" id="IsPredictCompelNotarizationDate" value="${interviewVo.compulsoryNotaryDate }" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'predictCompelNotarizationDate',dateFmt:'yyyy-MM-dd',minDate:new Date() })">
				                	<input type="hidden" id="predictCompelNotarizationDate" name="compulsoryNotaryDate" value="${interviewVo.compulsoryNotaryDate }" />
				        		</label>
				        	</dd></dl>
				        </td>
						<td class="td-title pct10"><b class="c-red mr5">*</b>是否仲裁</td>
						<td class="pct20">
							 <dl class="form-item form-auto">
                                <dd class="detail">
								<input class="zui-checkbox zui-validatebox" validate-type="Require,Length[1-32]" id="isArbitrate" name="isArbitrate" type="hidden" data-multiple="false"
			                           data-data="[{'id':'0','text':'否'},{'id':'1','text':'是'}]"
			                           data-valuefield="id" data-textfield="text"  value="${interviewVo.isArbitrate==true?'1':'0'}"/>
			                  	</dd></dl>         
			                  </td>
						<td class="td-title pct10" ></td>
				        <td class="pct30"></td>
					</tr>
				<!-- </table>
				<table class="table-detail"> -->
					<tr>
						<td  colspan="6" class="pct10" ><h4 >收款账户</h4></td>
					</tr>
					<tr>
						<td class="td-title pct10" ><b class="c-red mr5">*</b>开户行</td>
						<td class="pct20">
							<dl class="form-item form-auto">
                                <dd class="detail">
                                	<label>
								<input class="zui-input zui-validatebox"    type="text" validate-type="Require" 
								id="bankAccount1" name="loanAccountVo.bankAccount"  value="${bankAccountVo.loanAccountVo.bankAccount}">
								</label>
								</dd>
							</dl>
						</td>
						<td class="td-title pct10" >银行代码</td>
						<td class="pct20">
							<dl class="form-item form-auto">
                                <dd class="detail" id="ddbankCode1">
									<input class="zui-input" value="${bankAccountVo.loanAccountVo.bankCode}"
										id="bankCode1" name="loanAccountVo.bankCode"">
							</dd></dl>	
						</td>
						<td class="td-title pct10" ><b class="c-red mr5">*</b>账户名</td>
						<td class="pct30">
							<dl class="form-item form-auto">
                                <dd class="detail">
                                	<label>
							<input class="zui-input zui-validatebox" validate-type="Require,Length[1-32]" 
								id="cardholderName1" name="loanAccountVo.cardholderName"  value="${bankAccountVo.loanAccountVo.cardholderName}">
							</label>
							</dd></dl>			
						</td>
					</tr>
					<tr>
						<td class="td-title pct10" ><b class="c-red mr5">*</b>账号</td>
						<td class="pct20">
							<dl class="form-item form-auto">
                                <dd class="detail">
                                	<label>
							<input class="zui-input zui-validatebox" validate-type="Require,Length[1-32]" 
								id="bankNo1" name="loanAccountVo.bankNo"  value="${bankAccountVo.loanAccountVo.bankNo}">
								</label>
							</dd></dl>		
						</td>
						<td class="td-title pct10" ></td>
						<td class="pct20"></td>
						<td class="td-title pct10" ></td>
						<td class="pct30"></td>
					</tr>
					<tr>
						<td colspan="6" class="title"><h4 >还款(代扣)账户</h4></td>
					</tr>
					<tr>
						<td class="td-title pct10" ><b class="c-red mr5">*</b>开户行</td>
						<td class="pct20">
							<dl class="form-item form-auto">
                                <dd class="detail">
                                	<label>
										<input class="zui-input nwidth2  zui-validatebox" validate-type="Require,Length[1-32]" 
											id="bankAccount2" name="recAccountVo.bankAccount"  value="${bankAccountVo.recAccountVo.bankAccount}">
									</label>
								</dd>
								<dd class="detail">
                            		<input class="zui-combobox zui-validatebox" id="changeBox" type="hidden" data-width="94"
                                   data-data="[{'id':'1','text':'复制收款账户'}]" data-callback="change"  data-valuefield="id" data-textfield="text">
                        		</dd>
								
							</dl>
						</td>
						<td class="td-title pct10" >银行代码</td>
						<td class="pct20">
							<dl class="form-item form-auto">
                                <dd class="detail" id ="ddbankCode2">
										<input class="zui-input" value="${bankAccountVo.recAccountVo.bankCode}"
										id="bankCode2" name="recAccountVo.bankCode" >
							</dd></dl>
						</td>
						<td class="td-title pct10" ><b class="c-red mr5">*</b>账户名</td>
						<td class="pct30">
							<dl class="form-item form-auto">
                                <dd class="detail">
                                	<label>
										<input class="zui-input zui-validatebox" validate-type="Require,Length[1-32]" 
										id="cardholderName2" name="recAccountVo.cardholderName"  value="${bankAccountVo.recAccountVo.cardholderName}">
									</label>
							</dd></dl>
						</td>
					</tr>
					<tr>
						<td class="td-title pct10" ><b class="c-red mr5">*</b>账号</td>
						<td class="pct20">
							<dl class="form-item form-auto">
                                <dd class="detail">
                                	<label>
									<input class="zui-input zui-validatebox" validate-type="Require,Length[1-32]" 
										id="bankNo2" name="recAccountVo.bankNo"  value="${bankAccountVo.recAccountVo.bankNo}">
									</label>
								</dd>
							</dl>		
						</td>
						<td class="td-title pct10" ></td>
						<td class="pct20"></td>
						<td class="td-title pct10" ></td>
						<td class="pct30"></td>
					</tr>
				</table>
			</div>
		</div>
		<!-- 第三方还款授权 信息-->
		<div class="page-box">
			<div class="page-title">第三方还款授权</div>
    		<div class="p5">
				<table class="table-detail">
					<tr>
						<td class="td-title pct10"><b class="c-red mr5">*</b>第三方还款授权</td>
						<td class="pct20">
							<dl class="form-item form-auto">
                                <dd class="detail">
							<input class="zui-checkbox zui-validatebox" validate-type="Require,Length[1-32]"  id="isThirdPartyRepayAccredit" name="isThirdPartyRepayAccredit" type="hidden" data-multiple="false"
		                          data-data="[{'id':'0','text':'否'},{'id':'1','text':'是'}]"
		                           data-valuefield="id" data-textfield="text" value="${interviewVo.isThirdPartyRepayAccredit==true?'1':'0' }" data-callback="partyRepayAccreditChange" >
		                	  </dd>
                            </dl>           
		                </td>
						<td class="td-title pct10 aa" >第三方还款授权人</td>
						<!-- 选人下拉框 -->  
						<td class="pct20 aa">
							<dl class="form-item form-auto">
                                <dd class="detail">
                                	 <input type="hidden" class="zui-input" id="thirdPartyRepayAccreditName"  name="thirdPartyRepayAccreditName" value="${interviewVo.thirdPartyRepayAccreditName}">
                                    <input class="zui-combobox zui-validatebox" type="hidden" id="thirdPartyRepayAccreditId" name="thirdPartyRepayAccreditId"
									data-url='<z:ukey key="com.cnfh.rms.casemanage.interview.findThirdPartyRepayAccreditName" context="admin"/>&caseApplyId=${caseApplyId }'  data-callback="comboChange" data-valuefield="fullCode" data-textfield="name" value="${interviewVo.thirdPartyRepayAccreditId}" />
                                </dd>
                            </dl>
						</td>
						<td class="td-title pct10 cc" ></td>
				        <td class="pct20 cc"></td>
						<td class="td-title pct10 bb" ></td>
				        <td class="pct30 bb"></td>
						
					</tr>    
				</table>
			</div>
		</div>
	</form>
		<div class="form-btn">
			<button id="btn-save" class="btn-blue mr10" type="button">保存</button>
			<button id="btn-sub" class="btn-blue mr10" type="button">提交</button>
		</div>
</div>
<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.loading','zd/jquery.zds.dialog','zd/jquery.zds.message',
		'zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter',
		'zd/completer','zd/jquery.zds.form'], 
			function($, CALLBACK, ZTOOL,Loading, ZUI_MESSAGE_CLIENT) {
			//选择回调
        	CALLBACK.change = function (value,text) {
				if(value=='1'){
					$("#bankAccount2").val($("#bankAccount1").val());
					$("#bankCode2").val($("#bankCode1").val());
					$("#cardholderName2").val($("#cardholderName1").val());
					$("#bankNo2").val($("#bankNo1").val());
					$('#bankAccount2').ZDSValidatebox('validate', $('#bankAccount2'));//校验
					$('#cardholderName2').ZDSValidatebox('validate', $('#cardholderName2'));//校验
					$('#bankNo2').ZDSValidatebox('validate', $('#bankNo2'));//校验
				}
        	};
        	
        	//第三方还款授权人 值设置
        	CALLBACK.comboChange = function (value,text) {
        		$("#thirdPartyRepayAccreditName").val(text);
        	}

			CALLBACK.partyRepayAccreditChange = function(v,t){
				if('1'==v){  
					$(".aa").show();
					$("#thirdPartyRepayAccreditName").attr('validate-type','Require');
					//$(".bb").hide();
					$(".cc").hide();
				}else{
					//$(".bb").show();
					$(".aa").hide();
					$(".cc").show();
					$("#thirdPartyRepayAccreditName").ZDSValidatebox("clearErrorMessage","#thirdPartyRepayAccreditName");//手动清除校验
					$("#thirdPartyRepayAccreditName").removeAttr('validate-type');
				}
			};
			
			//隐藏 第三方还款授权人
			if(${interviewVo.isThirdPartyRepayAccredit==true}){
				$(".aa").show();
				$(".cc").hide();
			}else{
				$(".aa").hide();
				$(".cc").show();
			}
			
			//保存
			$("#btn-save").click(function(){
				save(0);
			});
			
			//提交
			$("#btn-sub").click(function(){
				save(1);
			});
			
			function save(isSubmit){
				var flog = $('#interview_form').ZDSValidatebox('validateAll', $('#interview_form'));
				if(flog){
					Loading.show();
					var totalVo = $("#interview_form").serialize();
					//$('#tb_housePropertyInfo').ZTable("saveAll");
					var houseData = $('#tb_housePropertyInfo').ZTable("getRows");
					var params = totalVo;
					params +="&houseData="+JSON.stringify(houseData) ;
					//alert(JSON.stringify(params));
					//保存案件预约
					$.ajax({
	                    type: 'post',
	                    url: '<z:ukey key="com.cnfh.rms.casemanage.interview.saveInterview" context="admin"/>&isSubmit='+isSubmit,
	                    data: params,
	                    dataType: 'json',
	                    success: function (data) {
	                    	Loading.hide();
	                        if (data.resultStatus == 0) {
	                        	$.ZMessage.success("提示", data.msg, function () {
	                        		$("#id").val(data.id);
	                        		if(isSubmit=='1'){
	                        			setTimeout(function(){
		                                 	ZDS_MESSAGE_CLIENT.refreshOpenner();
		                                 	ZDS_MESSAGE_CLIENT.closeSelf();
		                                 },200);
	                        		}
	                          	 });
	                        }else{
	                        	$.ZMessage.error("错误", data.msg, function () {
		                        });
	                        }
	                    },
				            error: function () {
				            	Loading.hide();
				            	$.ZMessage.error("错误", "案件面签信息保存系统异常，请联系管理员", function () {
				             });
				            }
	                });
				}
			}
			
			$.ZUI.init();
			var jsonData='${mortgageHolder}';
			var jsonData1 = jsonData.replace(/\"/g,"'");
			//案件的房产信息 
			$('#tb_housePropertyInfo').ZTable({
			       url : '<z:ukey key="com.cnfh.rms.casemanage.interview.findHousePropertyInfo" context="admin"/>&caseApplyId=${caseApplyId }',
			       singleSelect : true,
			       isRowNum : true,
			       pagination : false,
			       currentPage : 1,
			       idField: "HOUSEID",
			       //dbclickEditRow: true,//是否双击可编辑行
			       tableCls : 'table-index',//table的样式
			       onEdit:true,
			       batchSave: true,//是否批量保存
			       columns:[[
			    	  	{field : 'COMMUNITYNAME',title : '小区名称',align : 'center',width:'15%'},
						{field : 'MAILINGADDRESS',title : '押品地址',align : 'center',width:'25%'},
						{field : 'SYNTHESIZEPRICE',title : '综合评估价（元）',align : 'center',width:'15%'},
						{field : 'HOUSENO',title : '房产证编号', align : 'center',width:'15%',required:true,},
						{field : 'MORTGAGEEID',title : '抵押权人名称',align : 'center',required:true,width:'15%'},
						{field : 'HOUSEID',title : '抵押权人名称',align : 'center',hidden:true},
						{field : 'MORTGAGEDATE',title : '预计入押时间', align : 'center',width:'15%',required:true,
							formatter:function(index,value){
								return window.formatDate(index,value);
							}	
						}
					] ],
					columnsType: [
		                {
		                    "HOUSENO": {
		                    	 "inputType": "input",
		                    	 "validateType": "Require,Length[1-32]"
		                    },
		                	"MORTGAGEEID": {
		                        "inputType": "combobox",
		                        "data": {
		                            "valueField": "fullcode",
		                            "textField": "name",
		                             //"url":'<z:ukey key="com.cnfh.rms.casemanage.interview.findMortgageHolder" context="admin"/>&jsoncallback=?&caseApplyId=${caseApplyId }'
		                            "data": jsonData1
		                             
		                        },
		                        "validateType": "Require"
		                    },
		                    "MORTGAGEDATE": {
								"inputType": "date",
		                        "validateType": "Require"
		                    }
		                },
		                {
		                    "inputWidth": 150
		                }
		            ],
		            onLoadSuccess:function(){
		            	//联想银行数据
		            	$("#bankCode1").completer({
		    	            suggest: true,//默认false
		    	            idField: 'bankCode',//默认id,唯一标识字段
		    	            nameField: 'bankName',//默认name,下拉列表展示数据的字段
		    	            valueField: 'bankName',//默认value,根据值查询数据的字段
		    	            url:'<z:ukey key="com.cnfh.rms.casemanage.interview.findBankByLikeName" context="admin"/>' ,//请求数据地址
		    	            writable: false,//默认false，是否可自定义输入
		    	            placeObj:$("#ddbankCode1"),//悬浮框需要定位到的对象
		    	            complete: function (data) {
		    	            	$("#bankCode1").val(data.bankCode);
		    	            },
		    	            filter: function (val) {
		    	                return val;//过滤输入的value值
		    	            }
		    			});
		            	
		            	//联想银行数据
		            	$("#bankCode2").completer({
		    	            suggest: true,//默认false
		    	            idField: 'bankCode',//默认id,唯一标识字段
		    	            nameField: 'bankName',//默认name,下拉列表展示数据的字段
		    	            valueField: 'bankName',//默认value,根据值查询数据的字段
		    	            url:'<z:ukey key="com.cnfh.rms.casemanage.interview.findBankByLikeName" context="admin"/>' ,//请求数据地址
		    	            writable: false,//默认false，是否可自定义输入
		    	            placeObj:$("#ddbankCode2"),//悬浮框需要定位到的对象
		    	            complete: function (data) {
		    	            	$("#bankCode2").val(data.bankCode);
		    	            },
		    	            filter: function (val) {
		    	                return val;//过滤输入的value值
		    	            }
		    			});
		            }
				});
		});	
</script>
