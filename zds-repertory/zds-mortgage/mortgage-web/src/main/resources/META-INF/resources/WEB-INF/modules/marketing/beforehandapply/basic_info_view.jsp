<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<!-- 营销申请基本信息 -->
<div class="page-box">
	<div class="page-title">基本信息</div>
	<div class="p5">
		<table class="table-detail">
			<tr>
				<td class="td-title pct10">产品</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail f12"> 
							${beforehandApplyVo.productTypeName}   
					</dd>
					<dd class="detail f12">
						(${beforehandApplyVo.productSubtypeName})
					</dd>
				</dl>
				</td>
				<td class="td-title pct10">接单日期</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail f12">
							<label id="isApplyDate"> 
						</label>
						</dd>
					</dl>
				</td>
				<td class="td-title pct10">贷款用途</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail  f12">
							${beforehandApplyVo.capitalUseForName }
						</dd>
					</dl>
				</td>
			</tr>
			<tr>
				<td class="td-title pct10">终端</td>
				<td>
					<dl class="form-item form-auto">
						<dd class="detail f12">
							${beforehandApplyVo.terminalIdName}
						</dd>
					</dl>
				</td>
				<td class="td-title pct10">拓展经理</td>
				<td>
					<dl class="form-item form-auto">
						<dd class="detail f12">
							<label>
								 ${beforehandApplyVo.developmentManagerName } 
							</label>
						</dd>
					</dl>
				</td>
				<td class="td-title pct10">拓展部门</td>
				<td>
					<dl class="form-item form-auto">
						<dd class="detail  f12">
							<label>
							${beforehandApplyVo.developmentDepartmentName }
							</label>
						</dd>
					</dl>
				</td>
			</tr>
			<tr>
				<td class="td-title pct10">资信员</td> 
				<td>
					<dl class="form-item form-auto">
						<dd class="detail  f12">
							<label> 
							${beforehandApplyVo.creditMember }
							</label>
						</dd>
					</dl>
				</td>
				<td class="td-title pct10">案件号</td>
				<td>
					<dl class="form-item form-auto">
						<dd class="detail  f12">
							<label> 
							${beforehandApplyVo.caseApplyCode }
							</label>
						</dd>
					</dl>
				</td>
				<td class="td-title pct10"></td>
				<td>
					<dl class="form-item form-auto">
						<dd class="detail  f12">
							<label> 
							</label>
						</dd>
					</dl>
				</td>
			</tr>
		</table>
	</div>
</div>
<div class="page-box">
	<div class="page-title">贷款信息</div>
	<div class="p5">
		<table class="table-detail">
			<tr>
				<td class="td-title pct10">申请金额</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail f12">
							<label> 
							${beforehandApplyVo.applyAmount } 
								<front style="font-size: 14px;">元</front>
							</label>
						</dd>
					</dl>
				</td>
				<td class="td-title pct10">贷款期限</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail f12">
								<label>
								${beforehandApplyVo.applyTerm }/${beforehandApplyVo.applyTermUnitName }
								</label>
							</dd>
						<dd class="detail  f12">
						
						</dd>
					</dl>
				</td>
				<td class="td-title pct10">资金来源</td>
				<td class="pct20">  
					<dl class="form-item form-auto">
						<dd class="detail  f12">
							${beforehandApplyVo.capitalSourceName }
						</dd>
					</dl>
				</td>
			</tr>
		</table>
	</div>				
</div>	
<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.dialog','zd/jquery.zds.seleter','zd/make-first-py'], 
		function($, CALLBACK) {
		//回显接单时间
		var isApplyDate = '${beforehandApplyVo.applyDate }';
		if(isApplyDate){ 
			$("#isApplyDate").html(isApplyDate.substring(0,4)+"-"+isApplyDate.substring(4,6)+"-"+isApplyDate.substring(6,8));
		}
	});
</script>
</body>
</html>