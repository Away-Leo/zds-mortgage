<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../common/common_js.jsp" %>
<title>评估公司权重查看</title>
</head>
<body>
	<div>
		<div class="page-box">
			<div class="p10">
				<form id="client_form" class="zui-form " method="post" enctype="multipart/form-data">
					<input  id="id" name="id" type="hidden" value="${evaluateCompanyRuleVo.id }">
					<div class="page-box">
						<div class="page-title">基本信息</div>
						<dl class="form-item">
							<dt class="title">机构：</dt>
							<dd class="detail">
			                    <input class="zui-input " validate-type="Length[0-64]" 
									id="orgName" name="orgName" value="${evaluateCompanyRuleVo.orgName }">
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">机构ID：</dt>
							<dd class="detail">
									<input class="zui-input " validate-type="Length[0-64]" 
									id="orgId" name="orgId" value="${evaluateCompanyRuleVo.orgId }">
							</dd>
						</dl>
						<dl class="form-item">
			                <dt class="title">城市:</dt>
                             <dd class="detail">
                             	<div id="selectAddress" data-code="${evaluateCompanyRuleVo.city }">
		                            <input id="region" class="zui-input zui-validatebox"  type="text" readonly="true" style="width: 200px;" validate-type="Require"/>
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
									    	<input class="zui-input " validate-type="Length[0-64]" 
											id="evaluateCompanyOne" name="evaluateCompanyOne" value="${evaluateCompanyRuleVo.evaluateCompanyOne }">
										</td>
									    <td>
									    	<input class="zui-input " validate-type="Length[0-64]" 
											id="outProportionOne" name="outProportionOne" value="${evaluateCompanyRuleVo.outProportionOne }">
										</td>
									    <td>
									    	<input class="zui-input " validate-type="Length[0-64]" 
											id="calculateRuleOne" name="calculateRuleOne" value="${evaluateCompanyRuleVo.calculateRuleOne }">
										</td>
									  </tr>
									  <tr>
									    <td>
									    	<input class="zui-input " validate-type="Length[0-64]" 
											id="evaluateCompanyTwo" name="evaluateCompanyTwo" value="${evaluateCompanyRuleVo.evaluateCompanyTwo }">
										</td>
									    <td>
									    	<input class="zui-input " validate-type="Length[0-64]" 
											id="outProportionTwo" name="outProportionTwo" value="${evaluateCompanyRuleVo.outProportionTwo }">
										</td>
									    <td>
									    	<input class="zui-input " validate-type="Length[0-64]" 
											id="calculateRuleTwo" name="calculateRuleTwo" value="${evaluateCompanyRuleVo.calculateRuleTwo }">
										</td>
									  </tr>
									  <tr>
									    <td>
									    	<input class="zui-input " validate-type="Length[0-64]" 
											id="evaluateCompanyThree" name="evaluateCompanyThree" value="${evaluateCompanyRuleVo.evaluateCompanyThree }">
										</td>
									    <td>
									    	<input class="zui-input " validate-type="Length[0-64]" 
											id="outProportionThree" name="outProportionThree" value="${evaluateCompanyRuleVo.outProportionThree }">
										</td>
									    <td>
									    	<input class="zui-input " validate-type="Length[0-64]" 
											id="calculateRuleThree" name="calculateRuleThree" value="${evaluateCompanyRuleVo.calculateRuleThree }">
										</td>
									  </tr>
									  <tr>
									    <td>
									    	<input class="zui-input " validate-type="Length[0-64]" 
											id="evaluateCompanyFour" name="evaluateCompanyFour" value="${evaluateCompanyRuleVo.evaluateCompanyFour }">
										</td>
									    <td>
									    	<input class="zui-input " validate-type="Length[0-64]" 
											id="outProportionFour" name="outProportionFour" value="${evaluateCompanyRuleVo.outProportionFour }">
										</td>
									    <td>
									    	<input class="zui-input " validate-type="Length[0-64]" 
											id="calculateRuleFour" name="calculateRuleFour" value="${evaluateCompanyRuleVo.calculateRuleFour }">
										</td>
									  </tr>
									  <tr>
									    <td>
									    	<input class="zui-input " validate-type="Length[0-64]" 
											id="evaluateCompanyFive" name="evaluateCompanyFive" value="${evaluateCompanyRuleVo.evaluateCompanyFive }">
										</td>
									    <td>
									    	<input class="zui-input " validate-type="Length[0-64]" 
											id="outProportionFive" name="outProportionFive" value="${evaluateCompanyRuleVo.outProportionFive }">
										</td>
									    <td>
									    	<input class="zui-input " validate-type="Length[0-64]" 
											id="calculateRuleFive" name="calculateRuleFive" value="${evaluateCompanyRuleVo.calculateRuleFive }">
										</td>
									  </tr>
							  	</tbody>
							</table>
						</div>
					</div>					
		        </form>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.address','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter','zd/make-first-py'], 
			function($, CALLBACK) {
			//城市
			$("#selectAddress").Address({
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
			
		});
	</script>
</body>
</html>