<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 风险信息查看 -->
<div class="page-box">
	<div class="page-title">基本信息</div>
	<div class="p5">
		<table class="table-detail">
			<tr>
				<td class="td-title pct10">产品</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail  f12">
							${vo.productTypeName}--${vo.productSubtypeName}
						</dd>
					</dl>
				</td>
				<td class="td-title pct10">接单日期</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail  f12">
							<span  id="applyDate"></span> 
						</dd>
					</dl>
				</td>
				<td class="td-title pct10">贷款用途</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail  f12">
							${vo.capitalUseForName }
						</dd>
					</dl>
				</td>
			</tr>
			<tr>
				<td class="td-title pct10">终端</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail  f12">
							${vo.terminalIdName }
						</dd>
					</dl>
				</td>
				<td class="td-title pct10">拓展经理</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail  f12">
							${vo.developmentManagerName}
						</dd>
					</dl>
				</td>
				<td class="td-title pct10">拓展部门</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail  f12">
							${vo.developmentDepartmentName}
						</dd>
					</dl>
				</td>
			</tr>
			<tr>
				<td class="td-title pct10">资信员</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail  f12">
							${vo.creditMember}
						</dd>
					</dl>
				</td>
				<td class="td-title pct10">评估价抵押成数</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail  f12">
							${vo.assessedPriceMortgage}<c:if test="${not empty vo.loanNumber }">%</c:if>
						</dd>
					</dl>
				</td>
				<td class="td-title pct10">贷款成数</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail  f12">
							${vo.loanNumber}<c:if test="${not empty vo.loanNumber }">%</c:if>
						</dd>
					</dl>
				</td>
			</tr>
		</table>
	</div>
</div>
<div class="page-box">
	<div class="page-title">风控措施</div>
	<div class="p5">
		<table class="table-detail">
			<tr>
				<td class="td-title pct10">预计查册时间</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail  f12">
							<span  id="expectSearchDate"></span> 
						</dd>
					</dl>
				</td>
				<td class="td-title pct10">提放类型</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail  f12">
							${vo.riskInfoVo.putTypeName}
						</dd>
					</dl>
				</td>
				<td class="td-title pct10">是否办理仲裁</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail  f12">
							${vo.riskInfoVo.isDealArbitrationName}
						</dd>
					</dl>
				</td>
			</tr>
			<tr>
				<td class="td-title pct10">是否办理委托</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail  f12">
							${vo.riskInfoVo.isDealEntrustName}
						</dd>
					</dl>
				</td>
				<td class="td-title pct10">预计办理时间</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail  f12">
							<span  id="expectEntrustDate"></span> 
						</dd>
					</dl>
				</td>
				<td class="td-title pct10"></td>
				<td></td>
			</tr>
			<tr>
				<td class="td-title pct10">是否办理债权公证</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail  f12">
							${vo.riskInfoVo.isDealNotarialName}
						</dd>
					</dl>
				</td>
				<td class="td-title pct10">预计办理时间</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail  f12">
							<span  id="expectNotarialDate"></span>
						</dd>
					</dl>
				</td>
				<td class="td-title pct10"></td>
				<td></td>
			</tr>
			<tr>
				<td class="td-title pct10">是否办理可交易全委</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail  f12">
							${vo.riskInfoVo.isDealTradableName}
						</dd>
					</dl>
				</td>
				<td class="td-title pct10"></td>
				<td></td>
				<td class="td-title pct10"></td>
				<td></td>
			</tr>
		</table>
	</div>
</div>
<div class="page-box">
	<div class="page-title">风控评价</div>
	<div class="p5">
		<table class="table-detail">
			<tr>
				<td class="td-title pct10">抵押物综述</td>
				<td colspan="5">
                      <label>
                     		 <textarea class="zui-area row-width zui-disabled" readonly>${vo.riskInfoVo.pledgeReview}</textarea>
                      </label>
				</td>
			</tr>
			<tr>
				<td class="td-title pct10">业务综析</td>
				<td colspan="5">
					<label>
                  		<textarea class="zui-area row-width zui-disabled" readonly>${vo.riskInfoVo.businessAnalysis}</textarea>
                    </label>
				</td>
			</tr>
			<tr>
				<td class="td-title pct10">特殊情况</td>
				<td colspan="5">
					<label>
                  		<textarea class="zui-area row-width zui-disabled" readonly>${vo.riskInfoVo.specialSituation}</textarea>
                    </label>
				</td>
			</tr>
			<tr>
				<td class="td-title pct10">经营情况/工作情况</td>
				<td colspan="5">
					<label>
                  		<textarea class="zui-area row-width zui-disabled" readonly>${vo.riskInfoVo.workSituation}</textarea>
                    </label>
				</td>
			</tr>
			<tr>
				<td class="td-title pct10">其他</td>
				<td colspan="5">
					<label>
                  		<textarea class="zui-area row-width" readonly>${vo.riskInfoVo.remark}</textarea>
                    </label>
				</td>
			</tr>
		</table>
	</div>
</div>
<script>
	seajs.use([ 'jquery', 'zd/tools'], function($, ZTOOlS) {
		//格式化时间
		 $("#applyDate").html(ZTOOlS.strToDate("${vo.applyDate}"));
		 $("#expectSearchDate").html(ZTOOlS.strToDate("${vo.riskInfoVo.expectSearchDate}"));
		 $("#expectEntrustDate").html(ZTOOlS.strToDate("${vo.riskInfoVo.expectEntrustDate}"));
		 $("#expectNotarialDate").html(ZTOOlS.strToDate("${vo.riskInfoVo.expectNotarialDate}"));
	});
</script>