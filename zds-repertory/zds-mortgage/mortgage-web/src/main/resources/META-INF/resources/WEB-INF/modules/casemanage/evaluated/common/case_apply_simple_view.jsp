<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="page-box">
	<div class="p10">
	     <h1 class="page-title">基本信息</h1>
	     <div class="p5">
	         <table class="table-detail">
	             <tr>
	                 <td class="td-title pct10">案件号</td>
	                 <td class="pct20">
	                	 <dl class="form-item form-auto">
							<dd class="detail f12">
								${caseApplyVo.caseApplyCode}
							</dd>
						</dl>
					 </td>
	                 <td class="td-title pct10">机构</td>
	                 <td class="pct20">
	                 	<dl class="form-item form-auto">
							<dd class="detail f12">
								${caseApplyVo.mechanismName}
							</dd>
						</dl>
	                 </td>
	                 <td class="td-title pct10">主借人</td>
	                 <td class="pct20">
		                 <dl class="form-item form-auto">
							<dd class="detail f12">
								${caseApplyVo.customerName}
							</dd>
						</dl>
	                 </td>
	             </tr>
	             <tr>
	                 <td class="td-title pct10">产品</td>
	                 <td class="pct20">
	                 	<dl class="form-item form-auto">
							<dd class="detail f12">
								${caseApplyVo.productTypeName}/${caseApplyVo.productSubtypeName}
							</dd>
						</dl>
	                 </td>
	                 <td class="td-title pct10">申请金额</td>
	                 <td class="pct20">
	                 	<dl class="form-item form-auto">
							<dd class="detail f12">
								${caseApplyVo.applyAmount}元
							</dd>
						</dl>
	                 </td>
	                 <td class="td-title pct10"></td>
	                 <td class="pct20">
	                 	<dl class="form-item form-auto">
							<dd class="detail f12">
							</dd>
						</dl>
	                 </td>
	             </tr>
	         </table>
	     </div>
	</div>
</div>
