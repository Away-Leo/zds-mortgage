<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../common/common_js.jsp' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>工商信息详情</title>
</head>
<body>
<div id="businessForm">
	<!-- 列表区域 -->
	<div class="page-box">
		<div class="page-title">企业照面信息</div>
		<div class="p10 table-scroll">
			<table data-type="first" class="scroll table-index">
                <thead class="datagrid-header">
                    <tr>
                        <th>统一社会信用代码</th>
                        <th>注册号</th>
                        <th>注册资本币种</th>
                        <th>经营期限至</th>
                        <th>经营（业务）范围</th>
                        <th>法定代表人/负责人/执行事务合伙人</th>
                        <th>注册资本(万元)</th>
                        <th>住址</th>
                        <th>经营（业务）范围及方式</th>
                        <th>原注册号</th>
                        <th>许可经营项目</th>
                        <th>登记机关</th>
                        <th>成立日期</th>
                        <th>吊销日期</th>
                        <th>经营期限自</th>
                        <th>企业(机构)类型</th>
                        <th>注销日期</th>
                        <th>经营状态</th>
                        <th>企业名称</th>
                        <th>一般经营项目</th>
                    </tr>
                </thead>
                <tbody class="datagrid-body">
                	<c:forEach items="${detailVo.basicList}" var="basicVo">
                		<tr>
                			<td>${basicVo.creditcode }</td>
                        	<td>${basicVo.regno }</td>
                        	<td>${basicVo.regcapcur }</td>
                        	<td>${basicVo.opto }</td>
                        	<td>${basicVo.opscope }</td>
                        	<td>${basicVo.frname }</td>
                        	<td>${basicVo.regcap }</td>
                        	<td>${basicVo.dom }</td>
                			<td>${basicVo.opscoandform }</td>
                        	<td>${basicVo.oriregno }</td>
                        	<td>${basicVo.abuitem }</td>
                        	<td>${basicVo.regorg }</td>
                        	<td>${basicVo.esdate }</td>
                        	<td>${basicVo.revdate }</td>
                        	<td>${basicVo.opfrom }</td>
                        	<td>${basicVo.enttype }</td>
                        	<td>${basicVo.candate }</td>
                        	<td>${basicVo.entstatus }</td>
                        	<td>${basicVo.entname }</td>
                        	<td>${basicVo.cbuitem }</td>
                        </tr>
            		</c:forEach>
                </tbody>
            </table>
		</div>
		<br>
		
		<div class="page-title">企业股东及出资信息</div>
		<div class="p10">
			<table data-type="first" class="scroll table-index">
                <thead class="datagrid-header">
                    <tr>
                        <th>出资日期</th>
                        <th>出资方式</th>
                        <th>国别</th>
                        <th>股东名称</th>
                        <th>认缴出资币种</th>
                        <th>出资比例</th>
                        <th>认缴出资额（万元）</th>
                    </tr>
                </thead>
                <tbody class="datagrid-body">
                	<c:forEach items="${detailVo.shareHolderList}" var="shareHolderVo">
                		<tr>
                			<td>${shareHolderVo.condate }</td>
                        	<td>${shareHolderVo.conform }</td>
                        	<td>${shareHolderVo.country }</td>
                        	<td>${shareHolderVo.shaname }</td>
                        	<td>${shareHolderVo.regcapcur }</td>
                        	<td>${shareHolderVo.fundedratio }</td>
                        	<td>${shareHolderVo.subconam }</td>
                        </tr>
            		</c:forEach>
                </tbody>
            </table>
		</div>
		<br>
		
		<div class="page-title">企业主要管理人员信息</div>
		<div class="p10">
			<table data-type="first" class="scroll table-index">
                <thead class="datagrid-header">
                    <tr>
                        <th>人员姓名</th>
                        <th>职务</th>
                        <th>性别</th>
                    </tr>
                </thead>
                <tbody class="datagrid-body">
                	<c:forEach items="${detailVo.personList}" var="personVo">
                		<tr>
                			<td>${personVo.pername }</td>
                        	<td>${personVo.position }</td>
                        	<td>${personVo.sex }</td>
                        </tr>
            		</c:forEach>
                </tbody>
            </table>
		</div>
		<br>
		
		<div class="page-title">企业法定代表人对外投资信息</div>
		<div class="p10">
			<table data-type="first" class="scroll table-index">
                <thead class="datagrid-header">
                    <tr>
                        <th>企业（机构）类型</th>
                        <th>注册资本（万元）</th>
                        <th>注册资本币种</th>
                        <th>出资比例</th>
                        <th>开业日期</th>
                        <th>企业状态</th>
                        <th>出资方式</th>
                        <th>注册号</th>
                        <th>注销日期</th>
                        <th>企业（机构）名称</th>
                        <th>法定代表人姓名</th>
                        <th>认缴出资币种</th>
                        <th>吊销日期</th>
                        <th>登记机关</th>
                        <th>认缴出资额（万元）</th>
                    </tr>
                </thead>
                <tbody class="datagrid-body">
                	<c:forEach items="${detailVo.frinvList}" var="frinvVo">
                		<tr>
                			<td>${frinvVo.enttype }</td>
                        	<td>${frinvVo.regcap }</td>
                        	<td>${frinvVo.regcapcur }</td>
                        	<td>${frinvVo.fundedratio }</td>
                        	<td>${frinvVo.esdate }</td>
                        	<td>${frinvVo.entstatus }</td>
                        	<td>${frinvVo.conform }</td>
                        	<td>${frinvVo.regno }</td>
                			<td>${frinvVo.candate }</td>
                        	<td>${frinvVo.entname }</td>
                        	<td>${frinvVo.name }</td>
                        	<td>${frinvVo.currency }</td>
                        	<td>${frinvVo.revdate }</td>
                        	<td>${frinvVo.regorg }</td>
                        	<td>${frinvVo.subconam }</td>
                        </tr>
            		</c:forEach>
                </tbody>
            </table>
		</div>
		<br>
		
		<div class="page-title">企业法定代表人其他公司任职信息</div>
		<div class="p10">
			<table data-type="first" class="scroll table-index">
                <thead class="datagrid-header">
                    <tr>
                        <th>企业（机构）名称</th>
                        <th>注册号</th>
                        <th>职务</th>
                        <th>开业日期</th>
                        <th>法定代表人姓名</th>
                        <th>注销日期</th>
                        <th>企业（机构）类型</th>
                        <th>吊销日期</th>
                        <th>注册资本（万元）</th>
                        <th>是否法定代表人</th>
                        <th>注册资本币种</th>
                        <th>企业状态</th>
                        <th>登记机关</th>
                    </tr>
                </thead>
                <tbody class="datagrid-body">
                	<c:forEach items="${detailVo.frpositionList}" var="frpositionVo">
                		<tr>
                			<td>${frpositionVo.entname }</td>
                        	<td>${frpositionVo.regno }</td>
                        	<td>${frpositionVo.position }</td>
                        	<td>${frpositionVo.esdate }</td>
                        	<td>${frpositionVo.name }</td>
                        	<td>${frpositionVo.candate }</td>
                        	<td>${frpositionVo.enttype }</td>
                        	<td>${frpositionVo.revdate }</td>
                			<td>${frpositionVo.regcap }</td>
                        	<td>${frpositionVo.lerepsign }</td>
                        	<td>${frpositionVo.regcapcur }</td>
                        	<td>${frpositionVo.entstatus }</td>
                        	<td>${frpositionVo.regorg }</td>
                        </tr>
            		</c:forEach>
                </tbody>
            </table>
		</div>
		<br>
		
		<div class="page-title">企业对外投资信息</div>
		<div class="p10">
			<table data-type="first" class="scroll table-index">
                <thead class="datagrid-header">
                    <tr>
                        <th>注销日期</th>
                        <th>企业（机构）名称</th>
                        <th>注册号</th>
                        <th>出资方式</th>
                        <th>开业日期</th>
                        <th>出资比例</th>
                        <th>注册资本（万元）</th>
                        <th>登记机关</th>
                        <th>企业（机构）类型</th>
                        <th>注册资本币种</th>
                        <th>认缴出资额（万元）</th>
                        <th>认缴出资币种</th>
                        <th>企业状态</th>
                        <th>吊销日期</th>
                    </tr>
                </thead>
                <tbody class="datagrid-body">
                	<c:forEach items="${detailVo.entinvList}" var="entinvVo">
                		<tr>
                			<td>${entinvVo.candate }</td>
                        	<td>${entinvVo.entname }</td>
                        	<td>${entinvVo.regno }</td>
                        	<td>${entinvVo.conform }</td>
                        	<td>${entinvVo.esdate }</td>
                        	<td>${entinvVo.fundedratio }</td>
                        	<td>${entinvVo.regcap }</td>
                        	<td>${entinvVo.regorg }</td>
                			<td>${entinvVo.enttype }</td>
                        	<td>${entinvVo.regcapcur }</td>
                        	<td>${entinvVo.subconam }</td>
                        	<td>${entinvVo.congrocur }</td>
                        	<td>${entinvVo.entstatus }</td>
                        	<td>${entinvVo.revdate }</td>
                        </tr>
            		</c:forEach>
                </tbody>
            </table>
		</div>
		<br>
		
		<div class="page-title">企业历史变更信息</div>
		<div class="p10">
			<table data-type="first" class="scroll table-index">
                <thead class="datagrid-header">
                    <tr>
                        <th>变更前内容</th>
                        <th>变更日期</th>
                        <th>变更事项</th>
                        <th>变更后内容</th>
                    </tr>
                </thead>
                <tbody class="datagrid-body">
                	<c:forEach items="${detailVo.alterList}" var="alterVo">
                		<tr>
                			<td>${alterVo.altbe }</td>
                        	<td>${alterVo.altdate }</td>
                        	<td>${alterVo.altitem }</td>
                        	<td>${alterVo.altaf }</td>
                        </tr>
            		</c:forEach>
                </tbody>
            </table>
		</div>
		<br>
		
		<div class="page-title">企业分支机构信息</div>
		<div class="p10">
			<table data-type="first" class="scroll table-index">
                <thead class="datagrid-header">
                    <tr>
                        <th>分支机构企业注册号</th>
                        <th>分支机构名称</th>
                        <th>一般经营项目</th>
                        <th>分支机构地址</th>
                        <th>分支机构负责人</th>
                    </tr>
                </thead>
                <tbody class="datagrid-body">
                	<c:forEach items="${detailVo.filiationList}" var="filiationVo">
                		<tr>
                			<td>${filiationVo.brregno }</td>
                        	<td>${filiationVo.brname }</td>
                        	<td>${filiationVo.cbuitem }</td>
                        	<td>${filiationVo.braddr }</td>
                        	<td>${filiationVo.brprincipal }</td>
                        </tr>
            		</c:forEach>
                </tbody>
            </table>
		</div>
		<br>
		
		<div class="page-title">股权出质历史信息</div>
		<div class="p10">
			<table data-type="first" class="scroll table-index">
                <thead class="datagrid-header">
                    <tr>
                        <th>出质批准日期</th>
                        <th>出质截至日期</th>
                        <th>出质金额（万元）</th>
                        <th>质权人姓名</th>
                        <th>质权人类别</th>
                        <th>出质备案日期</th>
                        <th>出质审批日期</th>
                    </tr>
                </thead>
                <tbody class="datagrid-body">
                	<c:forEach items="${detailVo.sharesImpawnList}" var="sharesImpawnVo">
                		<tr>
                			<td>${sharesImpawnVo.impsandate }</td>
                        	<td>${sharesImpawnVo.impto }</td>
                        	<td>${sharesImpawnVo.impam }</td>
                        	<td>${sharesImpawnVo.imporg }</td>
                        	<td>${sharesImpawnVo.imporgtype }</td>
                        	<td>${sharesImpawnVo.imponrecdate }</td>
                        	<td>${sharesImpawnVo.impexaeep }</td>
                        </tr>
            		</c:forEach>
                </tbody>
            </table>
		</div>
		<br>
		
		<div class="page-title">动产抵押信息</div>
		<div class="p10">
			<table data-type="first" class="scroll table-index">
                <thead class="datagrid-header">
                    <tr>
                        <th>抵押人</th>
                        <th>状态标识</th>
                        <th>抵押ID</th>
                        <th>履约起始日期</th>
                        <th>履约截止日期</th>
                        <th>登记日期</th>
                        <th>登记证号</th>
                        <th>被担保主债权数额（万元）</th>
                        <th>申请抵押原因</th>
                        <th>被担保主债权种类</th>
                        <th>注销日期</th>
                        <th>抵押权人</th>
                        <th>登记机关</th>
                    </tr>
                </thead>
                <tbody class="datagrid-body">
                	<c:forEach items="${detailVo.mordetailList}" var="mordetailVo">
                		<tr>
                			<td>${mordetailVo.mortgagor }</td>
                        	<td>${mordetailVo.mortype }</td>
                        	<td>${mordetailVo.morregId }</td>
                        	<td>${mordetailVo.pefperform }</td>
                        	<td>${mordetailVo.pefperto }</td>
                        	<td>${mordetailVo.regidate }</td>
                        	<td>${mordetailVo.morrengcno }</td>
                        	<td>${mordetailVo.priclasecam }</td>
                			<td>${mordetailVo.appregrea }</td>
                        	<td>${mordetailVo.priclaseckind }</td>
                        	<td>${mordetailVo.candate }</td>
                        	<td>${mordetailVo.more }</td>
                        	<td>${mordetailVo.regorg }</td>
                        </tr>
            		</c:forEach>
                </tbody>
            </table>
		</div>
		<br>
		
		<div class="page-title">动产抵押物信息</div>
		<div class="p10">
			<table data-type="first" class="scroll table-index">
                <thead class="datagrid-header">
                    <tr>
                        <th>抵押物名称</th>
                        <th>数量</th>
                        <th>价值（万元）</th>
                        <th>抵押ID</th>
                    </tr>
                </thead>
                <tbody class="datagrid-body">
                	<c:forEach items="${detailVo.morguaInfoList}" var="morguaInfoVo">
                		<tr>
                			<td>${morguaInfoVo.guaname }</td>
                        	<td>${morguaInfoVo.quan }</td>
                        	<td>${morguaInfoVo.value }</td>
                        	<td>${morguaInfoVo.morregId }</td>
                        </tr>
            		</c:forEach>
                </tbody>
            </table>
		</div>
		<br>
		
		<div class="page-title">失信被执行人信息</div>
		<div class="p10 table-scroll">
			<table data-type="first" class="scroll table-index">
                <thead class="datagrid-header">
                    <tr>
                        <th>执行法院</th>
                        <th>省份</th>
                        <th>失信被执行人行为具体情形</th>
                        <th>未履行（元）</th>
                        <th>年龄</th>
                        <th>被执行人姓名/名称</th>
                        <th>法定代表人/负责人姓名</th>
                        <th>已履行（元）</th>
                        <th>立案时间</th>
                        <th>发布时间</th>
                        <th>被执行人的履行情况</th>
                        <th>做出执行依据单位</th>
                        <th>性别</th>
                        <th>失信人类型</th>
                        <th>执行依据文号</th>
                        <th>案号</th>
                        <th>身份证号码/工商注册号</th>
                        <th>身份证原始发证地</th>
                        <th>生效法律文书确定的义务</th>
                    </tr>
                </thead>
                <tbody class="datagrid-body">
                	<c:forEach items="${detailVo.punishBreakList}" var="punishBreakVo">
                		<tr>
                			<td>${punishBreakVo.courtname }</td>
                        	<td>${punishBreakVo.areanameclean }</td>
                        	<td>${punishBreakVo.disrupttypename }</td>
                        	<td>${punishBreakVo.unperformpart }</td>
                        	<td>${punishBreakVo.ageclean }</td>
                        	<td>${punishBreakVo.inameclean }</td>
                        	<td>${punishBreakVo.businessentity }</td>
                        	<td>${punishBreakVo.performedpart }</td>
                			<td>${punishBreakVo.regdateclean }</td>
                        	<td>${punishBreakVo.publishdateclean }</td>
                        	<td>${punishBreakVo.performance }</td>
                        	<td>${punishBreakVo.gistunit }</td>
                        	<td>${punishBreakVo.sexyclean }</td>
                        	<td>${punishBreakVo.type }</td>
                        	<td>${punishBreakVo.gistid }</td>
                        	<td>${punishBreakVo.casecode }</td>
                        	<td>${punishBreakVo.cardnum }</td>
                        	<td>${punishBreakVo.ysfzd }</td>
                        	<td>${punishBreakVo.duty }</td>
                        </tr>
            		</c:forEach>
                </tbody>
            </table>
		</div>
		<br>
		
		<div class="page-title">被执行人信息</div>
		<div class="p10">
			<table data-type="first" class="scroll table-index">
                <thead class="datagrid-header">
                    <tr>
                        <th>被执行人姓名/名称</th>
                        <th>性别</th>
                        <th>年龄</th>
                        <th>省份</th>
                        <th>执行法院</th>
                        <th>案号</th>
                        <th>立案时间</th>
                        <th>案件状态</th>
                        <th>身份证号码/企业注册号</th>
                        <th>执行标的（元）</th>
                        <th>身份证原始发证地</th>
                    </tr>
                </thead>
                <tbody class="datagrid-body">
                	<c:forEach items="${detailVo.punishedList}" var="punishedVo">
                		<tr>
                			<td>${punishedVo.inameclean }</td>
                        	<td>${punishedVo.sexyclean }</td>
                        	<td>${punishedVo.ageclean }</td>
                        	<td>${punishedVo.areanameclean }</td>
                        	<td>${punishedVo.courtname }</td>
                        	<td>${punishedVo.casecode }</td>
                        	<td>${punishedVo.regdateclean }</td>
                        	<td>${punishedVo.casestate }</td>
                			<td>${punishedVo.cardnumclean }</td>
                        	<td>${punishedVo.execmoney }</td>
                        	<td>${punishedVo.ysfzd }</td>
                        </tr>
            		</c:forEach>
                </tbody>
            </table>
		</div>
		<br>
		
		<div class="page-title">股权冻结历史信息</div>
		<div class="p10">
			<table data-type="first" class="scroll table-index">
                <thead class="datagrid-header">
                    <tr>
                        <th>冻结起始日期</th>
                        <th>解冻日期</th>
                        <th>解冻说明</th>
                        <th>冻结机关</th>
                        <th>冻结截至日期</th>
                        <th>解冻文号</th>
                        <th>冻结文号</th>
                        <th>冻结金额（万元）</th>
                        <th>解冻机关</th>
                    </tr>
                </thead>
                <tbody class="datagrid-body">
                	<c:forEach items="${detailVo.sharesFrostList}" var="sharesFrostVo">
                		<tr>
                			<td>${sharesFrostVo.frofrom }</td>
                        	<td>${sharesFrostVo.thawdate }</td>
                        	<td>${sharesFrostVo.thawcomment }</td>
                        	<td>${sharesFrostVo.froauth }</td>
                        	<td>${sharesFrostVo.froto }</td>
                        	<td>${sharesFrostVo.thawdocno }</td>
                        	<td>${sharesFrostVo.frodocno }</td>
                        	<td>${sharesFrostVo.froam }</td>
                			<td>${sharesFrostVo.thawauth }</td>
                        </tr>
            		</c:forEach>
                </tbody>
            </table>
		</div>
		<br>
		
		
		
		<div class="page-title">清算信息</div>
		<div class="p10">
			<table data-type="first" class="scroll table-index">
                <thead class="datagrid-header">
                    <tr>
                        <th>清算完结情况</th>
                        <th>清算组成员</th>
                        <th>债券承接人</th>
                        <th>清算责任人</th>
                        <th>清算负责人</th>
                        <th>债务承接人</th>
                        <th>清算完结日期</th>
                    </tr>
                </thead>
                <tbody class="datagrid-body">
                	<c:forEach items="${detailVo.liquidationList}" var="liquidationVo">
                		<tr>
                			<td>${liquidationVo.ligst }</td>
                        	<td>${liquidationVo.liqmen }</td>
                        	<td>${liquidationVo.claimtranee }</td>
                        	<td>${liquidationVo.ligentity }</td>
                        	<td>${liquidationVo.ligprincipal }</td>
                        	<td>${liquidationVo.debttranee }</td>
                        	<td>${liquidationVo.ligenddate }</td>
                        </tr>
            		</c:forEach>
                </tbody>
            </table>
		</div>
		<br>
		
		
		
		
		<div class="page-title">行政处罚历史信息</div>
		<div class="p10">
			<table data-type="first" class="scroll table-index">
                <thead class="datagrid-header">
                    <tr>
                        <th>处罚机关</th>
                        <th>案由</th>
                        <th>处罚决定书签发日期</th>
                        <th>处罚依据</th>
                        <th>处罚结果</th>
                        <th>处罚金额（万元）</th>
                        <th>处罚执行情况</th>
                        <th>案件类型</th>
                        <th>主要违法事实</th>
                        <th>处罚种类</th>
                        <th>案发时间</th>
                        <th>案件结果</th>
                        <th>执行类别</th>
                    </tr>
                </thead>
                <tbody class="datagrid-body">
                	<c:forEach items="${detailVo.caseInfoList}" var="caseInfoVo">
                		<tr>
                			<td>${caseInfoVo.penauth }</td>
                        	<td>${caseInfoVo.casereason }</td>
                        	<td>${caseInfoVo.pendeclssdate }</td>
                        	<td>${caseInfoVo.penbasis }</td>
                        	<td>${caseInfoVo.penresult }</td>
                        	<td>${caseInfoVo.penam }</td>
                        	<td>${caseInfoVo.penexest }</td>
                        	<td>${caseInfoVo.casetype }</td>
                			<td>${caseInfoVo.illegfact }</td>
                        	<td>${caseInfoVo.pentype }</td>
                        	<td>${caseInfoVo.casetime }</td>
                        	<td>${caseInfoVo.caseresult }</td>
                        	<td>${caseInfoVo.exesort }</td>
                        </tr>
            		</c:forEach>
                </tbody>
            </table>
		</div>
		
		
		
	</div>
</div>

<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK) {
	
	//初始化页面
	$.ZUI.init();
});
</script>
</html>