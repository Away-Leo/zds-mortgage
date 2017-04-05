<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<style type="text/css">
.tsInput{
	width: 50px;
	border-left: 0px;
	border-right: 0px;
	border-top: 0px;
	border-bottom: 1px solid #CCCCCC;
	box-sizing: border-box;
	height: 24px;
	line-height: 24px;
	margin-right: 2px;
	font-size: 12px;
	vertical-align: top;
	text-overflow: ellipsis;
}
</style>
<!-- 基本信息 -->
<form id="contractSupplement1_edit_form" class="zui-form " method="post" enctype="multipart/form-data">
	<input type="hidden" id="conCaseContractSupplementId" name="ConCaseContractSupplementVo.id" value="${conCaseContractSupplementId }"> 
	<input type="hidden" id="caseContractId" name="ConCaseContractSupplementVo.caseContractId" value="${caseApplyId }">
	<div class="page-box">
	
	<!---------------------- 贷款合同—房屋抵押  ----------------------->
		<div class="page-title">贷款合同—房屋抵押<b class="c-red mr5"> &nbsp&nbsp&nbsp&nbsp&nbsp 请各机构根据业务实际情况填写下列补充内容，不填写则默认为划斜杠“/”</b></div>
		<div class="p5">
			<table class="table-detail"> 
				<tr>
					<td class="td-title pct10"><b class="c-red mr5"></b>贷款用途</td>
					<td class="pct30">
						<dl class="form-item form-auto">
							<dd class="detail">
								<input class="zui-combobox zui-validatebox" type="hidden" name="ConCaseContractSupplementVo.bc1" id="bc1" value="${info.bc1}"
									 data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0055"
									data-valuefield="fullcode" data-textfield="name" validate-type="Require">
							</dd>
						</dl>
					</td>
					<td class="td-title pct10">合同编号</td>
					<td>
						<dl class="form-item form-auto">
							<dd class="detail">
								<label> <input type="text" name="ConCaseContractSupplementVo.bc2" id="bc2" value="${info.bc2}" class="zui-input" />
								</label>
							</dd>
						</dl>
					</td>
					<td class="td-title pct10"></b></td>
					<td class="pct30">
						<dl class="form-item form-auto">
							<dd class="detail">
								
							</dd>
						</dl>
					</td>
				</tr>
				
				
				<tr>
					<td class="td-title pct10">逾期违约金</td>
					
					
					<td colspan="5" class="pct20">
						<dl class="form-item form-auto">
	                          <dd class="detail">
	                              <label>
		                              <span class="word">本案件逾期违约金的计算公式为：贷款金额×</span>
		                                  <input class="zui-input width3" 
										    type="text" validate-type="Number"  name="ConCaseContractSupplementVo.bc3" id="bc3" value="${info.bc3}">
		                              <span class="word">%/日</span>
	                              </label>
	                          </dd>
	                   </dl>
					</td>
				</tr>
				
				<tr>
					<td class="td-title pct10">提还方案</td>
					<td colspan="5" class="pct20">
					 <span style="line-height: 28px;">第一方案 甲方在贷款1-3个月（含）内提前还款的，应补交利息损失差额=提前还款金额×</span>
						<dl class="form-item form-auto">
		                          <dd class="detail">
		                              <label>
			                                  <input class="zui-input width3" 
											    type="text" validate-type="Number"  name="ConCaseContractSupplementVo.bc4" id="bc4" value="${info.bc4}">
		                              </label>
		                          </dd>
		                   </dl>
		                   <span style="line-height: 28px;">%×实际借款月数；甲方在贷款4-6个月（含）内提前还款的，应补交利息损失差额=提前还款金额×
								 <dl class="form-item form-auto">
		                          <dd class="detail">
		                              <label>
			                                  <input class="zui-input width3" 
											    type="text" validate-type="Number"  name="ConCaseContractSupplementVo.bc5" id="bc5" value="${info.bc5}">
		                              </label>
		                          </dd>
		                   </dl>%×实际借款月数； 甲方在贷款7-12个月（含）内提前还款的，应补交利息损失差额=提前还款金额×<dl class="form-item form-auto">
		                          <dd class="detail">
		                              <label>
			                                  <input class="zui-input width3" 
											    type="text" validate-type="Number"  name="ConCaseContractSupplementVo.bc6" id="bc6" value="${info.bc6}">
		                              </label>
		                          </dd>
		                   </dl>%×实际借款月数。 
					甲方在贷款6个月（含）内提前还款的，除按照实际贷款期限计算利息外，同时需支付提前还款金额的<dl class="form-item form-auto">
		                          <dd class="detail">
		                              <label>
			                                  <input class="zui-input width3" 
											    type="text" validate-type="Number"  name="ConCaseContractSupplementVo.bc7" id="bc7" value="${info.bc7}">
		                              </label>
		                          </dd>
		                   </dl>%作为提前还款违约金； 甲方在贷款7个月以后提前还款的，除按照实际贷款期限计算利息外，
					同时需支付提前还款金额的<dl class="form-item form-auto">
		                          <dd class="detail">
		                              <label>
			                                  <input class="zui-input width3" 
											    type="text" validate-type="Number"  name="ConCaseContractSupplementVo.bc8" id="bc8" value="${info.bc8}">
		                              </label>
		                          </dd>
		                   </dl>  %作为提前还款违约金；
		                   
		                   
		                   <br>
					<br>第二方案 甲方在贷款6个月（含）内提前还款的，除按照实际贷款期限计算利息外，同时需支付提前还款金额的<dl class="form-item form-auto">
		                          <dd class="detail">
		                              <label>
			                                  <input class="zui-input width3" 
											    type="text" validate-type="Number"  name="ConCaseContractSupplementVo.bc9" id="bc9" value="${info.bc9}">
		                              </label>
		                          </dd>
		                   </dl>%作为提前还款违约金； 甲方在贷款7-12个月（含）内提前还款的，
					除按照实际贷款期限计算利息外，同时需支付提前还款金额的<dl class="form-item form-auto">
		                          <dd class="detail">
		                              <label>
			                                  <input class="zui-input width3" 
											    type="text" validate-type="Number"  name="ConCaseContractSupplementVo.bc10" id="bc10" value="${info.bc10}">
		                              </label>
		                          </dd>
		                   </dl>  %作为提前还款违约金； 甲方在贷款12个月后提前还款，除按照实际贷款期限计算利息外，同时需支付提前还款金额的<dl class="form-item form-auto">
		                          <dd class="detail">
		                              <label>
			                                  <input class="zui-input width3" 
											    type="text" validate-type="Number"  name="ConCaseContractSupplementVo.bc11" id="bc11" value="${info.bc11}">
		                              </label>
		                          </dd>
		                   </dl>   %作为提前还款违约金。 
					<br>
					<br>第三方案 甲方在贷款<dl class="form-item form-auto">
		                          <dd class="detail">
		                              <label>
			                                  <input class="zui-input width3" 
											    type="text" validate-type="Number"  name="ConCaseContractSupplementVo.bc12" id="bc12" value="${info.bc12}">
		                              </label>
		                          </dd>
		                   </dl>个月（含）内提前还款的，不收取提前还款违约金； 甲方在贷款 <dl class="form-item form-auto">
		                          <dd class="detail">
		                              <label>
			                                  <input class="zui-input width3" 
											    type="text" validate-type="Number"  name="ConCaseContractSupplementVo.bc13" id="bc13" value="${info.bc13}">
		                              </label>
		                          </dd>
		                   </dl>  - <dl class="form-item form-auto">
		                          <dd class="detail">
		                              <label>
			                                  <input class="zui-input width3" 
											    type="text" validate-type="Number"  name="ConCaseContractSupplementVo.bc14" id="bc14" value="${info.bc14}">
		                              </label>
		                          </dd>
		                   </dl>个月（含）内提前还款的， 除按照实际贷款期限计算利息外，
					同时需支付提前还款金额的<dl class="form-item form-auto">
		                          <dd class="detail">
		                              <label>
			                                  <input class="zui-input width3" 
											    type="text" validate-type="Number"  name="ConCaseContractSupplementVo.bc15" id="bc15" value="${info.bc15}">
		                              </label>
		                          </dd>
		                   </dl>作为提前还款违约金； 甲方在贷款<dl class="form-item form-auto">
		                          <dd class="detail">
		                              <label>
			                                  <input class="zui-input width3" 
											    type="text" validate-type="Number"  name="ConCaseContractSupplementVo.bc16" id="bc16" value="${info.bc16}">
		                              </label>
		                          </dd>
		                   </dl> - <dl class="form-item form-auto">
		                          <dd class="detail">
		                              <label>
			                                  <input class="zui-input width3" 
											    type="text" validate-type="Number"  name="ConCaseContractSupplementVo.bc17" id="bc17" value="${info.bc17}">
		                              </label>
		                          </dd>
		                   </dl>个月（含）内提前还款的， 除按照实际贷款期限计算利息外，同时需支付提前还款金额的 <dl class="form-item form-auto">
		                          <dd class="detail">
		                              <label>
			                                  <input class="zui-input width3" 
											    type="text" validate-type="Number"  name="ConCaseContractSupplementVo.bc18" id="bc18" value="${info.bc18}">
		                              </label>
		                          </dd>
		                   </dl> 作为提前还款违约金；
					 甲方在贷款<dl class="form-item form-auto">
		                          <dd class="detail">
		                              <label>
			                                  <input class="zui-input width3" 
											    type="text" validate-type="Number"  name="ConCaseContractSupplementVo.bc19" id="bc19" value="${info.bc19}">
		                              </label>
		                          </dd>
		                   </dl>个月后提前还款，除按照实际贷款期限计算利息外， 同时需支付提前还款金额的<dl class="form-item form-auto">
		                          <dd class="detail">
		                              <label>
			                                  <input class="zui-input width3" 
											    type="text" validate-type="Number"  name="ConCaseContractSupplementVo.bc20" id="bc20" value="${info.bc20}">
		                              </label>
		                          </dd>
		                   </dl> 作为提前还款违约金。
		                   
		                   <br>
						   <br>本案件选择第 <dl class="form-item form-auto">
		                          <dd class="detail">
		                              <label>
										<input class="zui-combobox zui-validatebox" data-width="94" name="ConCaseContractSupplementVo.bc21" 
										id='bc21' value="${info.bc21}" data-data="[{'id':'01','text':'一'},{'id':'02','text':'二'},{'id':'02','text':'三'}]" data-valuefield="id" data-textfield="text" validate-place="true" style="display: none;" type="hidden">
		                              </label>
		                          </dd>
		                   </dl>案
		                   
		                   </span>
				
					
					</td>
				</tr>
				
				
				<tr>
					<td class="td-title pct10">合同打印份数</td>
					<td colspan="5"><span style="line-height: 28px;">本贷款合同一式<dl class="form-item form-auto">
		                          <dd class="detail">
		                              <label>
			                                  <input class="zui-input width3" 
											    type="text" validate-type="Number"  name="ConCaseContractSupplementVo.bc22" id="bc22" value="${info.bc22}">
		                              </label>
		                          </dd>
		                   </dl>份，每份均具同等法律效力。 如有担保人的，则甲方应负责向担保人提供一份本合同的复印件，但是甲方未能提供并不对乙方的债权和担保权益产生不利影响。
					本合同如需办理登记、公证的，则应再多签署相应份数正本
					</span>
					</td>
				</tr>
				
				</table>
			</div>
			
		<!--------------------------- 抵押合同—房屋抵押 -------------------------------->
		<div class="page-title">抵押合同—房屋抵押 </div>
		<div class="p5">
			<table class="table-detail"> 
				
			
				<tr>
					<td class="td-title pct10">抵押率及抵押顺位</td>
					<td colspan="5">
						<span style="line-height: 28px;">甲方应保持抵押率不高于[ &nbsp&nbsp <dl class="form-item form-auto">
			                          <dd class="detail">
			                              <label>
				                                  <input class="zui-input width3" 
												    type="text" validate-type="Number"  name="ConCaseContractSupplementVo.bc23" id="bc23" value="${info.bc23}">
			                              </label>
			                          </dd>
			                   </dl>&nbsp&nbsp%]；抵押权登记的顺位  &nbsp&nbsp <dl class="form-item form-auto">
			                          <dd class="detail">
			                              <label>
				                             <input class="zui-combobox zui-validatebox" data-width="94" name="ConCaseContractSupplementVo.bc24" 
												id='bc24' value="${info.bc24}" data-data="[{'id':'01','text':'第1顺位'},{'id':'02','text':'第2顺位'},{'id':'02','text':'第3顺位'}]" data-valuefield="id" data-textfield="text" validate-place="true" style="display: none;" type="hidden">
			                              </label>
			                          </dd>
			                   </dl> &nbsp&nbsp:
						</span>           
					</td>
				</tr>
				
				
				<tr>
					<td class="td-title pct10">抵押登记期限</td>
					<td colspan="5">
					<span style="line-height: 28px;">甲方应在本合同签署后 &nbsp&nbsp <dl class="form-item form-auto">
		                          <dd class="detail">
		                              <label>
			                                  <input class="zui-input width3" 
											    type="text" validate-type="Number"  name="ConCaseContractSupplementVo.bc25" id="bc25" value="${info.bc25}">
		                              </label>
		                          </dd>
		                   </dl> &nbsp&nbsp  日内，办妥抵押登记等法定手续及本合同要求的手续
					</span>		            
					</td>
				</tr>
				
				<tr>
					<td class="td-title pct10">多抵补充描述</td>
					<td colspan="5">
						<textarea class="zui-area row-width" validate-type="Length[0-512]" id="bc26" value="" name="ConCaseContractSupplementVo.bc26" placeholder="最多可以输入512个字符"   >${info.bc26}</textarea>
					</td>
				</tr>
				
				</table>
			</div>		
			
		<!------------------------  抵押物列表  ---------------------------->	
		<div class="page-title">抵押物列表 </div>
			<div class="p5">
				<table class="table-detail"> 
					<tr>
					
					 <div class="p10">
	        			<div id="receivablePlanEdit" class="table-scroll"></div>
						</div>
					</div>
					
					</tr>
				
					<tr>
						<td class="td-title pct10">备注</td>
						<td colspan="5">
							<textarea class="zui-area row-width" validate-type="Length[0-512]" id="bc27" value="" name="ConCaseContractSupplementVo.bc27" placeholder="最多可以输入512个字符"   >${info.bc27}</textarea>
						</td>
					</tr>
					
					<tr>
						<td class="td-title pct10">特别条款（苏州）</td>
						<td colspan="5">
							<textarea class="zui-area row-width" validate-type="Length[0-512]" id="bc28" value="" name="ConCaseContractSupplementVo.bc28" placeholder="最多可以输入512个字符"   >${info.bc28}</textarea>
						</td>
					</tr>
					<tr>
						<td class="td-title pct10">合同打印份数</td>
						<td colspan="5">
						<span style="line-height: 28px;">本抵押合同一式  &nbsp&nbsp <dl class="form-item form-auto">
			                          <dd class="detail">
			                              <label>
				                                  <input class="zui-input width3" 
												    type="text" validate-type="Number"  name="ConCaseContractSupplementVo.bc29" id="bc29" value="${info.bc29}">
			                              </label>
			                          </dd>
			                   </dl> &nbsp&nbsp  份，均具有同等法律效力，自双方签字或盖章之日起生效。本合同如需办理登记、公证的，则应再多签署相应份数正本。
						</span>		            
						</td>
					</tr>
				</table>
			</div>							
		</div>	
	</form>
	
<script type="text/javascript">
	var productUrl =  '<z:ukey key="com.cnfh.rms.businessProduct.findByCategoryIdAndOrgCd" context="admin"/>&jsoncallback=?';
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter','zd/make-first-py'], 
		function($, CALLBACK) {
		
		
		$('#receivablePlanEdit').ZTable({
	         toolbar: [
	             {
	                 id: 'btncut5',
	                 text: '编辑',
	                 iconCls: 'icon-btn22',
	                 buttonCls: 'btn-white',
	                 handler: function () {
	                     $('#receivablePlanEdit').ZTable("editRow");
	                 }
	             }
	         ],
	         columns: [[
	        	 {field: 'id', title: 'id', hidden: true },
	        	 {field: 'mailingAddress', title: '抵押物地址', align: 'center', width: '15%'},
	        	 {field: 'creditorsAmount', title: '债权数额', align: 'center', width: '20%'},
                 {field: 'landUseArea', title: '土地使用权面积', align: 'center', width: '100'},
                 {field: 'landCertificateNo', title: '土地证编号', align: 'center', width: '100'},
                 {field: 'mortgageAmount1', title: '抵押物价值(苏州)/估价(中山)/剩余价值(江阴二抵)', align: 'center', width: '20%'},
                 {field: 'mortgageAmount2', title: '抵押金额(无锡)', align: 'center', width: '20%'}
	         ]],
	         columnsType: [
	             {
	            	 "landUseArea": {
	                     "inputType": "input"
	                 },
	                 "landCertificateNo": {
	                     "inputType": "input"
	                 },
	                 "creditorsAmount": {
	                     "inputType": "input",
	                     "validateType": "Amount"
	                 },
	                 "mortgageAmount1": {
	                     "inputType": "input",
	                     "validateType": "Amount"
	                 },
	                 "mortgageAmount2": {
	                	 "inputType": "input",
	                     "validateType": "Amount"
	                 },
	             },
	             {
	                 "inputWidth": 100,
	                 "inputHeight": 24,
	                 "areaWidth": 100,
	                 "areaHeight": 24
	             }
	         ],
	         idField: 'id',//每行数据的，唯一标识符
	         //queryParams: {param: 'xxooxxooxxoo000000000000000000'},//分页业务参数
	         url: '<z:ukey key="com.zdsoft.finance.contract.getMktGouseProperty" context="admin"/>&jsoncallback=?&caseApplyId=${caseApplyId }',
	         // url: 'table.json',
	         singleSelect: true,//表格行是单选还是多选
            isRowNum: true,//是否显示行号
            rows: 8,//分页情况下，显示的条数
            currentPage: 1,//分页情况下的，当前页
            pagination: false,//表格是否分页
            tableCls: 'table-index scroll',//table的样式
            dbclickEditRow: true,//是否双击可编辑行
            batchSave: true,//是否批量保存
            //onEdit:true,//启用所有行编辑
	         onSelect: function (rowIndex, rowData) {
	             //alert("选中" + rowIndex+'='+JSON.stringify(rowData));
	         },
	         onUnselect: function (rowIndex, rowData) {
	             //alert("选中" + rowIndex+'='+"取消" + rowIndex+'='+JSON.stringify(rowData));
	         },
	         onSave: function (rowIndex, rowData) {
	             //保存数据到数据库
	             
	             var idVal = rowData.id;
	             var creditorsAmountVal=rowData.creditorsAmount;
	             var landUseAreaVal = rowData.landUseArea;
	             var landCertificateNoVal = rowData.landCertificateNo;
	             var mortgageAmount1Val = rowData.mortgageAmount1;
	             var mortgageAmount2Val = rowData.mortgageAmount2;
	             var paramPost = { id: idVal, creditorsAmount: creditorsAmountVal, landUseArea: landUseAreaVal , landCertificateNo: landCertificateNoVal, mortgageAmount1: mortgageAmount1Val, mortgageAmount2: mortgageAmount2Val};
	             $.ajax({
                     type: 'post',
                     url: '<z:ukey key="com.zdsoft.finance.contract.saveMktHouseProperty" context="admin"/>',
                     data: paramPost,
                     dataType: 'json',
                     success: function (data) {
                    	 
                    	 $.ZMessage.success("提示", "保存成功", function () {
	                        });
                    	 $('#receivablePlanEdit').ZTable("reload", {});
                     },
		            error: function () {
		            	$.ZMessage.error("错误", "保存信息系统异常，请联系管理员", function () {
		             });
		            }
                 });  
	             /* console.log('保存的数据:' + JSON.stringify(rowData));
	             return true; */
	         }
	    });
		
	});
</script>
</body>
</html>