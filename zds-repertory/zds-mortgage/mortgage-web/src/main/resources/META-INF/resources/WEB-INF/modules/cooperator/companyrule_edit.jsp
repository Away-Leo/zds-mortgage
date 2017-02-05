<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../common/common_js.jsp" %>
<title>评估公司权重编辑</title>
</head>
<body>
	<div>
		<div class="page-box">
			<div class="p10">
				<form id="client_form" class="zui-form " method="post" enctype="multipart/form-data">
					<input type="hidden" id="id" name="id" value="${evaluateCompanyRuleVo.id }">
					<div class="page-box">
						<div class="page-title">基本信息</div>
						<dl class="form-item">
							<dt class="title">机构：</dt>
							<dd class="detail">
			                    <input class="zui-input zui-validatebox" validate-type="Length[0-64]" 
									id="orgName" name="orgName" value="${evaluateCompanyRuleVo.orgName }">
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">机构ID：</dt>
							<dd class="detail">
									<input class="zui-input zui-validatebox" validate-type="Length[0-64]" 
									id="orgId" name="orgId" value="${evaluateCompanyRuleVo.orgId }">
							</dd>
						</dl>
						<dl class="form-item">
			                <dt class="title">城市:</dt>
                           <dd class="detail">
                             	<div id="selectAddress" data-code="${evaluateCompanyRuleVo.city }">
		                            <input id="region" class="zui-input zui-validatebox"  type="text" readonly="true" style="width: 200px;" validate-type=""/>
		                            <input id="city" type="hidden" name="city" value="${evaluateCompanyRuleVo.city }"/>
		                        </div>
                             </dd>
			            </dl>
					</div>
					<div class="page-box">
						<div class="page-title">计算规则</div>
						<div class="zd-table">
							<table class="scroll table-index">
								<thead class="datagrid-header">
									<tr>
										<th style="text-align:center;display:table-cell;">评估公司</th>
										<th style="text-align:center;display:table-cell;">对外展示比例（%）</th>
										<th style="text-align:center;display:table-cell;">计算规则（所和为100%）</th>
									</tr>
								</thead>
							  	<tbody class="datagrid-body">
							  		  <tr>
									    <td>
									    	<dl class="form-item">
										    <dd class="detail">
									    	<input class="zui-combobox" 
											id="evaluateCompanyOne" name="evaluateCompanyOne" type="hidden" value="${evaluateCompanyRuleVo.evaluateCompanyOne }">
											</dd>
											</dl>
										</td>
									    <td>
									    	<input class="zui-input zui-validatebox" validate-type="Length[0-64]" 
											id="outProportionOne" name="outProportionOne" value="${evaluateCompanyRuleVo.outProportionOne }">
										</td>
									    <td>
									    	<input class="zui-input zui-validatebox" validate-type="Length[0-64]" 
											id="calculateRuleOne" name="calculateRuleOne" value="${evaluateCompanyRuleVo.calculateRuleOne }">
										</td>
									  </tr>
									  <tr>
									    <td>
									    	<dl class="form-item">
										    <dd class="detail">
									    	<input class="zui-combobox" 
											id="evaluateCompanyTwo" name="evaluateCompanyTwo" type="hidden" data-defaultvalue="${evaluateCompanyRuleVo.evaluateCompanyTwo }">
											</dd>
											</dl>
										</td>
									    <td>
									    	<input class="zui-input zui-validatebox" validate-type="Length[0-64]" 
											id="outProportionTwo" name="outProportionTwo" value="${evaluateCompanyRuleVo.outProportionTwo }">
										</td>
									    <td>
									    	<input class="zui-input zui-validatebox" validate-type="Length[0-64]" 
											id="calculateRuleTwo" name="calculateRuleTwo" value="${evaluateCompanyRuleVo.calculateRuleTwo }">
										</td>
									  </tr>
									  <tr>
									    <td>
									    	<dl class="form-item">
										    <dd class="detail">
									    	<input class="zui-combobox" 
											id="evaluateCompanyThree" name="evaluateCompanyThree" type="hidden" data-defaultvalue="${evaluateCompanyRuleVo.evaluateCompanyThree }">
											</dd>
											</dl>
										</td>
									    <td>
									    	<input class="zui-input zui-validatebox" validate-type="Length[0-64]" 
											id="outProportionThree" name="outProportionThree" value="${evaluateCompanyRuleVo.outProportionThree }">
										</td>
									    <td>
									    	<input class="zui-input zui-validatebox" validate-type="Length[0-64]" 
											id="calculateRuleThree" name="calculateRuleThree" value="${evaluateCompanyRuleVo.calculateRuleThree }">
										</td>
									  </tr>
									  <tr>
									    <td>
									    	<dl class="form-item">
										    <dd class="detail">
									    	<input class="zui-combobox"
											id="evaluateCompanyFour" name="evaluateCompanyFour" type="hidden" data-defaultvalue="${evaluateCompanyRuleVo.evaluateCompanyFour }">
											</dd>
											</dl>
										</td>
									    <td>
									    	<input class="zui-input zui-validatebox" validate-type="Length[0-64]" 
											id="outProportionFour" name="outProportionFour" value="${evaluateCompanyRuleVo.outProportionFour }">
										</td>
									    <td>
									    	<input class="zui-input zui-validatebox" validate-type="Length[0-64]" 
											id="calculateRuleFour" name="calculateRuleFour" value="${evaluateCompanyRuleVo.calculateRuleFour }">
										</td>
									  </tr>
									  <tr>
									    <td>
									    	<dl class="form-item">
										    <dd class="detail">
									    	<input class="zui-combobox"
											id="evaluateCompanyFive" name="evaluateCompanyFive" type="hidden" data-defaultvalue="${evaluateCompanyRuleVo.evaluateCompanyFive }">
											</dd>
											</dl>
										</td>
									    <td>
									    	<input class="zui-input zui-validatebox" validate-type="Length[0-64]" 
											id="outProportionFive" name="outProportionFive" value="${evaluateCompanyRuleVo.outProportionFive }">
										</td>
									    <td>
									    	<input class="zui-input zui-validatebox" validate-type="Length[0-64]" 
											id="calculateRuleFive" name="calculateRuleFive" value="${evaluateCompanyRuleVo.calculateRuleFive }">
										</td>
									  </tr>
							  	</tbody>
							</table>
						</div>
					</div>					
		        </form>
		            <div class="form-btn">
	                	<button id="submitClient" type="button" class="btn-blue" >保存</button>
	                </div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.address','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter','zd/make-first-py'], 
			function($, CALLBACK) {
			
			//保存评估公司权重
			$('#submitClient').click(function(){
				var calculateRuleOne = $("#calculateRuleOne").val()*1;
				var calculateRuleTwo = $("#calculateRuleTwo").val()*1;
				var calculateRuleThree = $("#calculateRuleThree").val()*1;
				var calculateRuleFour = $("#calculateRuleFour").val()*1;
				var calculateRuleFive = $("#calculateRuleFive").val()*1;
				var calculateRule = 0*1;
				//校验
				var isValidate = $.ZUI.validateForm($('#client_form'));
				if(isValidate){
					if(calculateRuleOne!=''&&calculateRuleOne!=null){
						calculateRule += calculateRuleOne;
					}
					if(calculateRuleTwo!=''&&calculateRuleTwo!=null){
						calculateRule += calculateRuleTwo;
					}
					if(calculateRuleThree!=''&&calculateRuleThree!=null){
						calculateRule += calculateRuleThree;
					}
					if(calculateRuleFour!=''&&calculateRuleFour!=null){
						calculateRule += calculateRuleFour;
					}
					if(calculateRuleFive!=''&&calculateRuleFive!=null){
						calculateRule += calculateRuleFive;
					}
					if(100*1 - calculateRule != 0){
						$.ZMessage.error("错误", "计算规则和应为100%", function () {
                        });
					}else{
						var param = $('#client_form').serializeArray();
						//保存
						$.ajax({
	                        type: 'post',
	                        url: '<z:ukey key="com.zdsoft.finance.evaluateCompanyRule.saveEvaluateCompanyRule" context="admin"/>',
	                        data: param,
	                        dataType: 'json',
	                        success: function (data) {
	                            if (data.resultStatus == 0) {
	                            	$.ZMessage.warning("提示", "保存成功", function () {
	                            		$('#id').val(data.optional.newotherCooperaterVo.id);
	                              	 });
	                            }else{
	                            	$.ZMessage.error("错误", data.msg, function () {
			                        });
	                            }
	                        },
	                        error: function () {
	                        	$.ZMessage.error("错误", "保存信息系统异常，请联系管理员", function () {
		                        });
	                        }
	                    });
					}
				}
			});
			
			//地址格式化
			$("#selectAddress").Address({
				showStreet:false,//不显示街道
	            cityUrl:"cityData.json",//获取城市数据源
                callback:function(infos,selected_ids) {
                    var str = '';
                    var strCode = '';
                    for(var i=0;i<infos.length;i++) {
                        if(str==""){
                            str = str+infos[i];
                            strCode = strCode+selected_ids[i];
                        }else{
                            str = str+" / "+infos[i];
                            strCode = strCode+","+selected_ids[i];
                        }
                    }
                    $('#region').val(str);
                    $('#city').val(strCode);
                }
            });
			
			$.ZUI.init();
			
			//评估公司下拉框
			var url = "<z:ukey key='com.zdsoft.finance.cooperator.evaluation.company.findAllCompanyName' context='admin'/>&jsoncallback=?";
  			$("#evaluateCompanyOne").ZCombobox({
  		         valueField: "fullcode",
  		         textField: "name",
  		         emptyValue:true,
  		         multiple:false,
  		         url:url
  		  	});
  			
  			$("#evaluateCompanyTwo").ZCombobox({
 		         valueField: "fullcode",
 		         textField: "name",
 		         emptyValue:true,
 		         multiple:false,
 		         url:url
 		  	});
  			
  			$("#evaluateCompanyThree").ZCombobox({
 		         valueField: "fullcode",
 		         textField: "name",
 		         emptyValue:true,
 		         multiple:false,
 		         url:url
 		  	});
  			
  			$("#evaluateCompanyFour").ZCombobox({
 		         valueField: "fullcode",
 		         textField: "name",
 		         emptyValue:true,
 		         multiple:false,
 		         url:url
 		  	});
  			
  			$("#evaluateCompanyFive").ZCombobox({
 		         valueField: "fullcode",
 		         textField: "name",
 		         emptyValue:true,
 		         multiple:false,
 		         url:url
 		  	});
		});
	</script>
</body>
</html>