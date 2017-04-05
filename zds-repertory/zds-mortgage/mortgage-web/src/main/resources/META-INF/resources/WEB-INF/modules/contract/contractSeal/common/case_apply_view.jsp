<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="page-box">
    <div class="p10">
        <h1 class="page-title">基本信息</h1>
        <div class="p5">
            <table class="table-detail">
                <tr>
                    <td class="td-title pct10">合同号</td>
                    <td class="pct20">
                        <dl class="form-item form-auto">
                            <dd class="detail f12">
                                ${contract.contractNo}
                            </dd>
                        </dl>
                    </td>
                    <td class="td-title pct10">接单日期</td>
                    <td class="pct20">
                        <dl class="form-item form-auto">
                            <dd class="detail f12">
                                ${caseApplyVo.mechanismName}
                            </dd>
                        </dl>
                    </td>
                    <td class="td-title pct10">子产品</td>
                    <td class="pct20">
                        <dl class="form-item form-auto">
                            <dd class="detail f12">
                                ${caseApply.productSubtypeName}
                            </dd>
                        </dl>
                    </td>
                </tr>
                <tr>
                    <td class="td-title pct10">扩展经理</td>
                    <td class="pct20">
                        <dl class="form-item form-auto">
                            <dd class="detail f12">
                                ${caseApply.developmentManagerName}
                            </dd>
                        </dl>
                    </td>
                    <td class="td-title pct10">拓展部门</td>
                    <td class="pct20">
                        <dl class="form-item form-auto">
                            <dd class="detail f12">
                                ${caseApply.developmentDepartmentName}
                            </dd>
                        </dl>
                    </td>
                    <td class="td-title pct10">机构</td>
                    <td class="pct20">
                        <dl class="form-item form-auto">
                            <dd class="detail f12">
                                ${caseApply.mechanismName}
                            </dd>
                        </dl>
                    </td>
                </tr>
                <tr>
                    <td class="td-title pct10">合同金额(元)</td>
                    <td class="pct20">
                        <dl class="form-item form-auto">
                            <dd class="detail f12">
                                ${contract.contractAmount}
                            </dd>
                        </dl>
                    </td>
                    <td class="td-title pct10">贷款期限</td>
                    <td class="pct20">
                        <dl class="form-item form-auto">
                            <dd class="detail f12">
                                ${caseApply.applyTerm}
                            </dd>
                        </dl>
                    </td>
                    <td class="td-title pct10">还款方式</td>
                    <td class="pct20">
                        <dl class="form-item form-auto">
                            <dd class="detail f12">
                                ${caseApply.repayMethod}
                            </dd>
                        </dl>
                    </td>
                </tr>
                <tr>
                    <td class="td-title pct10">贷款利率</td>
                    <td class="pct20">
                        <dl class="form-item form-auto">
                            <dd class="detail f12">
                                ${caseApply.applyRate}
                            </dd>
                        </dl>
                    </td>
                    <td class="td-title pct10">逾期利率</td>
                    <td class="pct20">
                        <dl class="form-item form-auto">
                            <dd class="detail f12">
                                ${caseApply.overdueRate}
                            </dd>
                        </dl>
                    </td>
                    <td class="td-title pct10">终端</td>
                    <td class="pct20">
                        <dl class="form-item form-auto">
                            <dd class="detail f12">
                                ${caseApply.terminalIdName}
                            </dd>
                        </dl>
                    </td>
                </tr>
                <tr>
                    <td class="td-title pct10">资金来源</td>
                    <td class="pct20">
                        <dl class="form-item form-auto">
                            <dd class="detail f12">
                                ${caseApply.capitalSourceName}
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
