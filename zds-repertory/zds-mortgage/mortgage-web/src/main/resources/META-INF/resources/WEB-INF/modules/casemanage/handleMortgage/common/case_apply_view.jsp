<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 案件基本信息 -->
<div class="page-box">
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
                 <td class="td-title pct10">接单日期</td>
                 <td class="pct20">
                 	<dl class="form-item form-auto">
						<dd class="detail f12">
							${caseApplyVo.applyDateStr}
						</dd>
					</dl>
                 </td>
                 <td class="td-title pct10">子产品</td>
                 <td class="pct20">
	                 <dl class="form-item form-auto">
						<dd class="detail f12">
							${caseApplyVo.productSubtypeName}
						</dd>
					</dl>
                 </td>
             </tr>
             <tr>
                 <td class="td-title pct10">拓展经理</td>
                 <td class="pct20">
                 	<dl class="form-item form-auto">
						<dd class="detail f12">
							${caseApplyVo.developmentManagerName}
						</dd>
					</dl>
                 </td>
                 <td class="td-title pct10">拓展部门</td>
                 <td class="pct20">
                 	<dl class="form-item form-auto">
						<dd class="detail f12">
							${caseApplyVo.developmentDepartmentName}
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
             </tr>
             <tr>
                 <td class="td-title pct10">申请金额(元)</td>
                 <td class="pct20">
                 	<dl class="form-item form-auto">
						<dd class="detail f12">
							${caseApplyVo.applyAmountString}
						</dd>
					</dl>
                 </td>
                 <td class="td-title pct10">贷款期限</td>
                 <td class="pct20">
                	 <dl class="form-item form-auto">
						<dd class="detail f12">
							${caseApplyVo.applyTerm}/
							${caseApplyVo.applyTermUnitName}
						</dd>
					</dl>
                 </td>
                 <td class="td-title pct10">还款方式</td>
                 <td class="pct20">
                 	<dl class="form-item form-auto">
						<dd class="detail f12">
							${caseApplyVo.repayMethodName}
						</dd>
					</dl>
                 </td>
             </tr>
             <tr>
                 <td class="td-title pct10">贷款利率</td>
                 <td class="pct20">
                 	<dl class="form-item form-auto">
						<dd class="detail f12">
							${caseApplyVo.applyRate == 0? '':caseApplyVo.applyRate} ${empty caseApplyVo.applyRateUnitName ? '':caseApplyVo.applyRateUnitName }
						</dd>
					</dl>
                 </td>
                 <td class="td-title pct10">逾期利率</td>
                 <td class="pct20">
                 	<dl class="form-item form-auto">
						<dd class="detail f12">
							${caseApplyVo.overdueRate == 0?'':caseApplyVo.overdueRate} ${empty caseApplyVo.overdueRateUnitName ? '':caseApplyVo.overdueRateUnitName }
						</dd>
					</dl>
                 </td>
                 <td class="td-title pct10">终端</td>
                 <td class="pct20">
                 	<dl class="form-item form-auto">
						<dd class="detail f12">
							${caseApplyVo.terminalIdName }
						</dd>
					</dl>
                 </td>
             </tr>
             <tr>
                 <td class="td-title pct10">资金来源</td>
                 <td class="pct20">
                	 <dl class="form-item form-auto">
						<dd class="detail f12">
							${caseApplyVo.capitalSourceName}
						</dd>
					</dl>
                 </td>
                 <td class="td-title pct10"></td>
                 <td class="pct20"></td>
                 <td class="td-title pct10"></td>
                 <td class="pct20"></td>
             </tr>
         </table>
     </div>
</div>