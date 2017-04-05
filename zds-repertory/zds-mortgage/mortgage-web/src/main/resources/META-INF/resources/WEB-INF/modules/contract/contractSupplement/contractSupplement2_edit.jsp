<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<!-- 营销申请基本信息 -->
<form id="contractSupplement2_edit_form" class="zui-form " method="post" enctype="multipart/form-data">
	<input type="hidden" id="conCaseContractSupplement2Id" name="ConCaseContractSupplement2Vo.id" value="${conCaseContractSupplement2Id }"> 
	<input type="hidden" id="caseContractId" name="ConCaseContractSupplement2Vo.caseContractId" value="${caseApplyId }">
	
	<div class="page-box">
	
	<!------------------还款计划表  --------------------- -->
		<div class="page-title">还款计划表 </div>
		<div class="p5">
			<table class="table-detail"> 
				<tr>
					<td class="td-title pct10">还款日方案</td>
					<td colspan="5">
					<span style="line-height: 28px;">①当月  &nbsp&nbsp <dl class="form-item form-auto">
		                          <dd class="detail">
		                              <label>
			                                  <input class="zui-input width3" 
											    type="text" validate-type="Number"  name="ConCaseContractSupplement2Vo.bc30" id="bc30" value="${info2.bc30}">
		                              </label>
		                          </dd>
		                   </dl> &nbsp&nbsp   号；
		                   <br>
		                   ②实际放款日后每  &nbsp&nbsp <dl class="form-item form-auto">
		                          <dd class="detail">
		                              <label>
			                                  <input class="zui-input width3" 
											    type="text" validate-type="Number"  name="ConCaseContractSupplement2Vo.bc31" id="bc31" value="${info2.bc31}">
		                              </label>
		                          </dd>
		                   </dl> &nbsp&nbsp   个月的同一天；
		                   </br>
		                   	本案件选择第  &nbsp&nbsp <dl class="form-item form-auto">
		                          <dd class="detail">
		                              <label>
			                              <input class="zui-combobox zui-validatebox" data-width="94" name="ConCaseContractSupplement2Vo.bc32" 
												id='bc32' value="${info2.bc32}" data-data="[{'id':'01','text':'①'},{'id':'02','text':'②'}]" data-valuefield="id" data-textfield="text" validate-place="true" style="display: none;" type="hidden">
		                              </label>
		                          </dd>
		                   </dl> &nbsp&nbsp   方案
		                   
					</span>		            
					</td>
				</tr>
			</table>
		</div>
			
		<!---------------------- 贷款补充合同   ------------------ -->
		<div class="page-title">贷款补充合同  </div>
			<div class="p5">
				<table class="table-detail"> 
					<tr>
						<td class="td-title pct10">还款日方案</td>
						<td colspan="5">
							<span style="line-height: 28px;">本贷款补充合同为主合同的组成部分。本合同一式     &nbsp&nbsp <dl class="form-item form-auto">
				                          <dd class="detail">
				                              <label>
					                                  <input class="zui-input width3" 
													    type="text" validate-type="Number"  name="ConCaseContractSupplement2Vo.bc33" id="bc33" value="${info2.bc33}">
				                              </label>
				                          </dd>
				                   </dl> &nbsp&nbsp   份，自双方签字或盖章之日起生效。
							</span>		            
						</td>
					</tr>
				</table>
			</div>					
		
		
		<!---------------------- 代办手续服务协议   ------------------ -->
		<div class="page-title">代办手续服务协议  </div>
			<div class="p5">
				<table class="table-detail"> 
					<tr>
						<td class="td-title pct10">委托代办事项</td>
						<td colspan="5">
							<div  style="line-height: 24px;">
								<label>
									<input type="checkbox" class="mr5" id="bc34" name="ConCaseContractSupplement2Vo.bc34" value="${info2.bc34 }">乙方在能力范围内为甲方提供相关金融政策分析及融资信息咨询服务。
								</label>
							</div>
							<div  style="line-height: 24px;">
								<label>
									<input type="checkbox" class="mr5" id="bc35" name="ConCaseContractSupplement2Vo.bc35" value="${info2.bc35 }">乙方向甲方提供有关咨询、劳务方面的服务，指导甲方填写、整理登记有关表格、资料，协助办理相关手续。
								</label>
							</div>
							<div style="line-height: 24px;">
								<label>
									<input type="checkbox" class="mr5" id="bc36" name="ConCaseContractSupplement2Vo.bc36" value="${info2.bc36 }"> 乙方代甲方向融资合作方申请借款，提交相关申请资料等相关事宜。
								</label>
							</div>
							<div style="line-height: 24px;">
								<label>
									<input type="checkbox" class="mr5" id="bc37" name="ConCaseContractSupplement2Vo.bc37" value="${info2.bc37 }"> 乙方代为甲方办理与借款相关的抵押物的抵押登记申请、缴费等手续，代为领取入押回执、抵押权登记证明文件等相关文件。
								</label>
							</div>
							<div style="line-height: 24px;">
								<label>
									<input type="checkbox" class="mr5" id="bc38" name="ConCaseContractSupplement2Vo.bc38" value="${info2.bc38 }"> 代为甲方办理与融资相关的房产、车辆交易及借款服务的全部相关手续，包括但不限于查册、评估、公证、赎契、涂销抵押、过户、缴税、再次抵押及领取《房地产权证》、《房地产他项权证》、抵押登记回执、《车辆登记证》、代收贷款或售（购）房/车款项等。
								</label>
							</div>
							<div style="line-height: 24px;">
								<label>
									<input type="checkbox" class="mr5" id="bc39" name="ConCaseContractSupplement2Vo.bc39" value="${info2.bc39 }"> 代为甲方办理与借款相关的承兑汇票的领取及贴现等手续。
								</label>
							</div>
							<div style="line-height: 24px;">
								<label>
									<input type="checkbox" class="mr5" id="bc40" name="ConCaseContractSupplement2Vo.bc40" value="${info2.bc40 }"> 代为甲方办理开具银行存款证明等相关手续。
								</label>
							</div>
							<div style="line-height: 24px;">
								<label>
									<input type="checkbox" class="mr5" id="bc41" name="ConCaseContractSupplement2Vo.bc41" value="${info2.bc41 }"> 代甲方进行贷后管理，在甲方借款发生逾期时，乙方代甲方以甲方交纳的保证金垫付逾期款项。
								</label>
							</div>
							<div  class="mb5" style="line-height: 24px;">
								<label>
									<input type="checkbox" class="mr5" id="bc42" name="ConCaseContractSupplement2Vo.bc42" value="${info2.bc42 }">其他手续。
								</label>
								<input style="width: 739px;" class="zui-input zui-validatebox" id="bc43" type="text"  name="ConCaseContractSupplement2Vo.bc43" value="${info2.bc43}"/>
								<!-- <input type="text" class="zui-input"> -->
							</div>	            
						</td>
					</tr>
					
					<tr>
						<td class="td-title pct10">其他约定</td>
						<td colspan="5">
							<textarea class="zui-area row-width" validate-type="Length[0-512]" id="bc44" value="" name="ConCaseContractSupplement2Vo.bc44" placeholder="最多可以输入512个字符"   >${info2.bc44}</textarea>
						</td>
					</tr>		
					<tr>
						<td class="td-title pct10">金额填写</td>
						<td colspan="5">
							<span style="line-height: 28px;">第四条 代办服务费及付款方式
							<br>3、因甲方原因中途停止委托的，甲方应按代办服务费总额的      &nbsp&nbsp <dl class="form-item form-auto">
				                          <dd class="detail">
				                              <label>
					                                  <input class="zui-input width3" 
													    type="text" validate-type="Number"  name="ConCaseContractSupplement2Vo.bc45" id="bc45" value="${info2.bc45}">
				                              </label>
				                          </dd>
				                   </dl> &nbsp&nbsp   %支付乙方劳务费。
				                   <br>
				                 		  第五条 违约责任
									<br>1、若甲方违反本合同约定，甲方需向乙方支付违约金为代办服务费总额的      &nbsp&nbsp <dl class="form-item form-auto">
				                          <dd class="detail">
				                              <label>
					                                  <input class="zui-input width3" 
													    type="text" validate-type="Number"  name="ConCaseContractSupplement2Vo.bc46" id="bc46" value="${info2.bc46}">
				                              </label>
				                          </dd>
				                   </dl> &nbsp&nbsp  %。
							</span>		            
						</td>
					</tr>
					
				</table>
			</div>						
		

		<!---------------------- 保证金协议    ------------------ -->
		<div class="page-title">保证金协议 </div>
			<div class="p5">
				<table class="table-detail"> 
					<tr>
						<td class="td-title pct10">金额填写</td>
						<td colspan="5">
							<span style="line-height: 28px;">保证金数额为乙方贷款金额的     &nbsp&nbsp <dl class="form-item form-auto">
				                          <dd class="detail">
				                              <label>
					                                  <input class="zui-input width3" 
													    type="text" validate-type="Number"  id="bc47"  name="ConCaseContractSupplement2Vo.bc47"  value="${info2.bc47}">
				                              </label>
				                          </dd>
				                   </dl> &nbsp&nbsp   %     &nbsp&nbsp 即人民币 &nbsp&nbsp<dl class="form-item form-auto">
				                          <dd class="detail">
				                              <label>
					                                  <input class="zui-input width3" 
													    type="text" validate-type="Number" id="bc48"  name="ConCaseContractSupplement2Vo.bc48"  value="${info2.bc48}">
				                              </label>
				                          </dd>
				                   </dl>          。
							</span>		            
						</td>
					</tr>
					
					
					<tr>
						<td class="td-title pct10">保证金无息退还条件</td>
						<td colspan="5" class="tl">
							<div  style="line-height: 24px;">
								<label>
									<input type="checkbox" class="mr5" id="bc49" name="ConCaseContractSupplement2Vo.bc49" value="${info2.bc49 }">贷款期限届满，乙方按约还清贷款。
								</label>
							</div>
							<div  style="line-height: 24px;">
								<label>
									<input type="checkbox" class="mr5" id="bc50" name="ConCaseContractSupplement2Vo.bc50" value="${info2.bc50 }">出具全权委托公证书。
								</label>
							</div>
							<div style="line-height: 24px;">
								<label>
									<input type="checkbox" class="mr5"  id="bc51" name="ConCaseContractSupplement2Vo.bc51" value="${info2.bc51 }"> 出具借款合同强制执行公证。
								</label>
							</div>
							<div  class="mb5" style="line-height: 24px;">
								<label>
									<input type="checkbox" class="mr5" id="bc52" name="ConCaseContractSupplement2Vo.bc52"  value="${info2.bc52 }" >其他。
								</label>
								<input style="width: 762px;" class="zui-input zui-validatebox" id="bc53" type="text"  name="ConCaseContractSupplement2Vo.bc53"  value="${info2.bc53}"/>
								<!-- <input type="text" class="zui-input"> -->
							</div>
						</td>
					</tr>
					
					
					<tr>
						<td class="td-title pct10">其他费用约定</td>
						<td colspan="5">
							<textarea class="zui-area row-width" validate-type="Length[0-512]" id="bc54" value="" name="ConCaseContractSupplement2Vo.bc54" placeholder="最多可以输入512个字符"   >${info2.bc54}</textarea>
						</td>
					</tr>
					<tr>
						<td class="td-title pct10">其他事项约定</td>
						<td colspan="5">
							<textarea class="zui-area row-width" validate-type="Length[0-512]" id="bc55" value="" name="ConCaseContractSupplement2Vo.bc55" placeholder="最多可以输入512个字符"   >${info2.bc55}</textarea>
						</td>
					</tr>
					<tr>
						<td class="td-title pct10">合同打印份数</td>
						<td colspan="5">
							<span style="line-height: 28px;">本保证金协议正本一式     &nbsp&nbsp <dl class="form-item form-auto">
				                          <dd class="detail">
				                              <label>
					                                  <input class="zui-input width3" 
													    type="text" validate-type="Number"  name="ConCaseContractSupplement2Vo.bc56" id="bc56" value="${info2.bc56}">
				                              </label>
				                          </dd>
				                   </dl> &nbsp&nbsp   份，甲方、乙方各执壹份，具有同等法律效力。
							</span>		            
						</td>
					</tr>
				</table>
			</div>						
		
		
		<!---------------------- 董事会决议     ------------------ -->
		<div class="page-title">董事会决议 </div>
			<div class="p5">
				<table class="table-detail"> 
					<tr>
						<td class="td-title pct10">公司名称</td>
						<td colspan="3">
							<dl class="form-item form-auto">
								<label> <input style="width: 455px;" class="zui-input zui-validatebox" id="bc57" type="text"  name="ConCaseContractSupplement2Vo.bc57" value="${info2.bc57}"/>
								</label>
							</dd>
						</dl>	            
						</td>
						<td class="td-title pct10">时间</td>
						<td colspan="3">
							<dl class="form-item form-auto">
							<dd class="detail">
								<label>
		                            <input class="zui-input zui-validatebox"   id="IsBc58" value="${info2.bc58 }" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'bc58',dateFmt:'yyyy-MM-dd',minDate:new Date() })">
		                            <input type="hidden" id="bc58" name="ConCaseContractSupplement2Vo.bc58" value="${info2.bc58 }"/>
		                    	</label>
							</dd>
						</dl>	            
						</td>
					</tr>
					<tr>
						<td class="td-title pct10">地点</td>
						<td colspan="3">
							<dl class="form-item form-auto">
								<label> <input style="width: 455px;" class="zui-input zui-validatebox" id="bc59" type="text"  name="ConCaseContractSupplement2Vo.bc59" value="${info2.bc59}"/>
								</label>
							</dd>
						</dl>	            
						</td>
						<td class="td-title pct10"></td>
						<td colspan="3">
							<dl class="form-item form-auto">
							<dd class="detail">
							</dd>
						</dl>	            
						</td>
					</tr>
					<tr>
						<td class="td-title pct10">出席人员</td>
						<td colspan="5">
							<textarea class="zui-area row-width" validate-type="Length[0-128]" id="bc60" value="" name="ConCaseContractSupplement2Vo.bc60" placeholder="最多可以输入128个字符"   >${info2.bc60}</textarea>
						</td>
					</tr>
				</table>
			</div>
			
			
		<!---------------------- 股东会决议      ------------------ -->
		<div class="page-title">股东会决议  </div>
			<div class="p5">
				<table class="table-detail"> 
					<tr>
						<td class="td-title pct10">公司名称</td>
						<td colspan="3">
							<dl class="form-item form-auto">
								<label> <input style="width: 455px;" class="zui-input zui-validatebox" id="bc61" type="text"  name="ConCaseContractSupplement2Vo.bc61" value="${info2.bc61}"/>
								</label>
							</dd>
						</dl>	            
						</td>
						<td class="td-title pct10">时间</td>
						<td colspan="3">
							<dl class="form-item form-auto">
							<dd class="detail">
								<label>
		                            <input class="zui-input zui-validatebox"   id="IsBc62" value="${info2.bc62 }" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'bc62',dateFmt:'yyyy-MM-dd',minDate:new Date() })">
		                            <input type="hidden" id="bc62" name="ConCaseContractSupplement2Vo.bc62" value="${info2.bc62 }"/>
		                    	</label>
							</dd>
						</dl>	            
						</td>
					</tr>
					<tr>
						<td class="td-title pct10">地点</td>
						<td colspan="3">
							<dl class="form-item form-auto">
								<label> <input style="width: 455px;" class="zui-input zui-validatebox" id="bc63" type="text"  name="ConCaseContractSupplement2Vo.bc63" value="${info2.bc63}"/>
								</label>
							</dd>
						</dl>	            
						</td>
						<td class="td-title pct10"></td>
						<td colspan="3">
							<dl class="form-item form-auto">
							<dd class="detail">
							</dd>
						</dl>	            
						</td>
					</tr>
					<tr>
						<td class="td-title pct10">人数</td>
						<td >
							<dl class="form-item form-auto">
								<label> <input class="zui-input zui-validatebox" id="bc64" type="text"  name="ConCaseContractSupplement2Vo.bc64" value="${info2.bc64}"/>
								</label>
							</dd>
						</dl>	            
						</td>
						<td class="td-title pct10">股权数</td>
						<td >
							<dl class="form-item form-auto">
								<label> <input class="zui-input zui-validatebox" id="bc65" type="text"  name="ConCaseContractSupplement2Vo.bc65" value="${info2.bc65}"/>
								</label>
							</dd>            
						</td>
						<td class="td-title pct10"></td>
						<td >
							<dl class="form-item form-auto">
							<dd class="detail">
							</dd>
						</dl>	            
						</td>
					</tr>
					<tr>
						<td class="td-title pct10">出席人员</td>
						<td colspan="5">
							<textarea class="zui-area row-width" validate-type="Length[0-128]" id="bc66" value="" name="ConCaseContractSupplement2Vo.bc66" placeholder="最多可以输入128个字符"   >${info2.bc66}</textarea>
						</td>
					</tr>
				</table>
			</div>
			
			
		<!---------------------- 股东决定书      ------------------ -->
		<div class="page-title">股东决定书  </div>
			<div class="p5">
				<table class="table-detail"> 
					<tr>
						<td class="td-title pct10">公司名称</td>
						<td colspan="3">
							<dl class="form-item form-auto">
								<label> <input style="width: 455px;" class="zui-input zui-validatebox" id="bc67" type="text"  name="ConCaseContractSupplement2Vo.bc67" value="${info2.bc67}"/>
								</label>
							</dd>
						</dl>	            
						</td>
						<td class="td-title pct10">时间</td>
						<td colspan="3">
							<dl class="form-item form-auto">
							<dd class="detail">
								<label>
		                            <input class="zui-input zui-validatebox"   id="IsBc68" value="${info2.bc68 }" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'bc68',dateFmt:'yyyy-MM-dd',minDate:new Date() })">
		                            <input type="hidden" id="bc68" name="ConCaseContractSupplement2Vo.bc68" value="${info2.bc68 }"/>
		                    	</label>
							</dd>
						</dl>	            
						</td>
					</tr>
					<tr>
						<td class="td-title pct10">唯一股东</td>
						<td class="pct30">
							<dl class="form-item form-auto">
								<label> <input class="zui-input zui-validatebox" id="bc69" type="text"  name="ConCaseContractSupplement2Vo.bc69" value="${info2.bc69}"/>
								</label>
							</dd>
						</dl>	            
						</td>
						<td class="td-title pct10"></td>
						<td class="pct30">
							<dl class="form-item form-auto">
								
							</dd>            
						</td>
						<td class="td-title pct10"></td>
						<td class="pct30">
							<dl class="form-item form-auto">
							<dd class="detail">
							</dd>
						</dl>	            
						</td>
					</tr>
					<tr>
						<td class="td-title pct10">出席人员</td>
						<td colspan="5">
							<textarea class="zui-area row-width" validate-type="Length[0-128]" id="bc70" value="" name="ConCaseContractSupplement2Vo.bc70" placeholder="最多可以输入128个字符"   >${info2.bc70}</textarea>
						</td>
					</tr>
				</table>
			</div>
			
			
		<!---------------------- 委托扣款授权书       ------------------ -->
		<div class="page-title">委托扣款授权书   </div>
			<div class="p5">
				<table class="table-detail"> 
					<tr>
						<td class="td-title pct10">姓名</td>
						<td class="pct30">
							<dl class="form-item form-auto">
								<label> <input class="zui-input zui-validatebox" id="bc71" type="text"  name="ConCaseContractSupplement2Vo.bc71" value="${info2.bc71}"/>
								</label>
							</dd>
							</dl>	            
						</td>
						<td class="td-title pct10">身份证号</td>
						<td class="pct30">
							<dl class="form-item form-auto">
								<label> <input class="zui-input zui-validatebox" id="bc72" type="text"  name="ConCaseContractSupplement2Vo.bc72" value="${info2.bc72}"/>
								</label>
							</dd>
						</dl>	            
						</td>
						<td class="td-title pct10">时间</td>
						<td cclass="pct30">
							<dl class="form-item form-auto">
							<dd class="detail">
								<label>
		                            <input class="zui-input zui-validatebox"   id="IsBc73" value="${info2.bc73 }" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'bc73',dateFmt:'yyyy-MM-dd',minDate:new Date() })">
		                            <input type="hidden" id="bc73" name="ConCaseContractSupplement2Vo.bc73" value="${info2.bc73 }"/>
		                    	</label>
							</dd>
							</dl>	            
						</td>
					</tr>
					<tr>
						<td class="td-title pct10">联系电话</td>
						<td class="pct30">
							<dl class="form-item form-auto">
								<label>  <input class="zui-input zui-validatebox" id="bc74" validate-type="Mobile"  name="ConCaseContractSupplement2Vo.bc74" value="${info2.bc74}"/>
								</label>
							</dd>
						</dl>	            
						</td>
						<td class="td-title pct10"></td>
						<td class="pct30">
							<dl class="form-item form-auto">
								
							</dd>            
						</td>
						<td class="td-title pct10"></td>
						<td class="pct30">
							<dl class="form-item form-auto">
							<dd class="detail">
							</dd>
						</dl>	            
						</td>
					</tr>
					<tr>
						<td class="td-title pct10">地址</td>
						<td colspan="5">
							<textarea class="zui-area  row-width" validate-type="Length[0-128]" id="bc75" value="" name="ConCaseContractSupplement2Vo.bc75" placeholder="最多可以输入128个字符"   >${info2.bc75}</textarea>
						</td>
					</tr>
				</table>
			</div>
			
			
		<!---------------------- 还款受托人       ------------------ -->
		<div class="page-title">还款受托人   </div>
			<div class="p5">
				<table class="table-detail"> 
					<tr>
						<td class="td-title pct10">还款受托人</td>
						<td class="pct30">
							<dl class="form-item form-auto">
								<label> <input class="zui-input zui-validatebox" id="bc76"  name="ConCaseContractSupplement2Vo.bc76" type="text"  value="${info2.bc76}"/>
								</label>
							</dd>
							</dl>	            
						</td>
						<td class="td-title pct10"></td>
						<td class="pct30">
							<dl class="form-item form-auto">
								
							</dd>
						</dl>	            
						</td>
						<td class="td-title pct10"></td>
						<td cclass="pct30">
							<dl class="form-item form-auto">
							<dd class="detail">
								
							</dd>
							</dl>	            
						</td>
					</tr>
				</table>
			</div>	
			
							
		</div>	
		
		
		
	</form>
	
<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter','zd/make-first-py'], 
		function($, CALLBACK) {
		
		//回显时间
		var bc58 = $("#IsBc58").val();
		if(bc58){
			$("#IsBc58").val(bc58.substring(0,4)+"-"+bc58.substring(4,6)+"-"+bc58.substring(6,8));
		}
		
		var bc62 = $("#IsBc62").val();
		if(bc62){
			$("#IsBc62").val(bc62.substring(0,4)+"-"+bc62.substring(4,6)+"-"+bc62.substring(6,8));
		}
		
		var bc68 = $("#IsBc68").val();
		if(bc68){
			$("#IsBc68").val(bc68.substring(0,4)+"-"+bc68.substring(4,6)+"-"+bc68.substring(6,8));
		}
		
		var bc73 = $("#IsBc73").val();
		if(bc73){
			$("#IsBc73").val(bc73.substring(0,4)+"-"+bc73.substring(4,6)+"-"+bc73.substring(6,8));
		}
		//判断多选框是否被选中
		if("${info2.bc52 }"=="1")
		{
			 $("#bc52").attr("checked","checked");
		}
		else
		{
			document.getElementById('bc53').disabled = true;
			$("#bc53").val("");
		}
		if("${info2.bc51 }"=="1")
		{
			 $("#bc51").attr("checked","checked");
		}
		if("${info2.bc50 }"=="1")
		{
			 $("#bc50").attr("checked","checked");
		}
		if("${info2.bc49 }"=="1")
		{
			 $("#bc49").attr("checked","checked");
		}
		
		if("${info2.bc42 }"=="1")
		{
			 $("#bc42").attr("checked","checked");
		}
		else
		{
			document.getElementById('bc43').disabled = true;
			$("#bc43").val("");
		}
		if("${info2.bc41 }"=="1")
		{
			 $("#bc41").attr("checked","checked");
		}
		if("${info2.bc40 }"=="1")
		{
			 $("#bc40").attr("checked","checked");
		}
		if("${info2.bc39 }"=="1")
		{
			 $("#bc39").attr("checked","checked");
		}
		if("${info2.bc38 }"=="1")
		{
			 $("#bc38").attr("checked","checked");
		}
		if("${info2.bc37 }"=="1")
		{
			 $("#bc37").attr("checked","checked");
		}
		if("${info2.bc36 }"=="1")
		{
			 $("#bc36").attr("checked","checked");
		}
		if("${info2.bc35 }"=="1")
		{
			 $("#bc35").attr("checked","checked");
		}
		if("${info2.bc34 }"=="1")
		{
			 $("#bc34").attr("checked","checked");
		}
		
		//多选框点击事件
		$("#bc52").click(function(){
			if($(this).attr("checked")=="checked")
			{
				$("#bc52").val("1");
				document.getElementById('bc53').disabled = false;
			}
			else
			{
				$("#bc52").val("0");
				$("#bc53").val("");
				document.getElementById('bc53').disabled = true;
			}
		})
		
		$("#bc51").click(function(){
			if($(this).attr("checked")=="checked")
			{
				$("#bc51").val("1");
			}
			else
			{
				$("#bc51").val("0");
			}
		})
		$("#bc50").click(function(){
			if($(this).attr("checked")=="checked")
			{
				$("#bc50").val("1");
			}
			else
			{
				$("#bc50").val("0");
			}
		})
		$("#bc49").click(function(){
			if($(this).attr("checked")=="checked")
			{
				$("#bc49").val("1");
			}
			else
			{
				$("#bc49").val("0");
			}
		})
		$("#bc42").click(function(){
			if($(this).attr("checked")=="checked")
			{
				$("#bc42").val("1");
				document.getElementById('bc43').disabled = false;
			}
			else
			{
				$("#bc42").val("0");
				$("#bc43").val("");
				document.getElementById('bc43').disabled = true;
			}
		})
		$("#bc41").click(function(){
			if($(this).attr("checked")=="checked")
			{
				$("#bc41").val("1");
			}
			else
			{
				$("#bc41").val("0");
			}
		})
		$("#bc40").click(function(){
			if($(this).attr("checked")=="checked")
			{
				$("#bc40").val("1");
			}
			else
			{
				$("#bc40").val("0");
			}
		})
		$("#bc39").click(function(){
			if($(this).attr("checked")=="checked")
			{
				$("#bc39").val("1");
			}
			else
			{
				$("#bc39").val("0");
			}
		})
		$("#bc38").click(function(){
			if($(this).attr("checked")=="checked")
			{
				$("#bc38").val("1");
			}
			else
			{
				$("#bc38").val("0");
			}
		})
		$("#bc37").click(function(){
			if($(this).attr("checked")=="checked")
			{
				$("#bc37").val("1");
			}
			else
			{
				$("#bc37").val("0");
			}
		})
		$("#bc36").click(function(){
			if($(this).attr("checked")=="checked")
			{
				$("#bc36").val("1");
			}
			else
			{
				$("#bc36").val("0");
			}
		})
		$("#bc35").click(function(){
			if($(this).attr("checked")=="checked")
			{
				$("#bc35").val("1");
			}
			else
			{
				$("#bc35").val("0");
			}
		})
		$("#bc34").click(function(){
			if($(this).attr("checked")=="checked")
			{
				$("#bc34").val("1");
			}
			else
			{
				$("#bc34").val("0");
			}
		})
		
		
		$.ZUI.init();
	});
	
		
</script>
</body>
</html>