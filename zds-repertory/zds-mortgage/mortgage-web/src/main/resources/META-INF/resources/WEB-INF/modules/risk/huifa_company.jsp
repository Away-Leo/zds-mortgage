<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<div id="huifaCompany">
	<!-- 列表区域 -->
	<div class="page-box">
		<div class="page-title">企业法院信息</div>
		<div class="p10">
			<table data-type="first" class="scroll table-index">
                <thead class="datagrid-header">
                    <tr>
                    	<th>企业名称</th>
						<th>申请人</th>
						<th>有无法院涉诉信息</th>
						<th>涉诉信息条数</th>
						<th>接口调用状态</th>
						<th>操作</th>
                    </tr>
                </thead>
                <c:if test="${not empty companyVoList}">
                <tbody class="datagrid-body">
                	<c:forEach items="${companyVoList}" var="companyInfoVo">
                		<tr>
                			<td>${companyInfoVo.name }</td>
                        	<td>${companyInfoVo.applicant }</td>
                        	<td>${companyInfoVo.hasLawsuit }</td>
                        	<td>${companyInfoVo.totalNumber }</td>
                        	<td>${companyInfoVo.statusStr }</td>
                        	<td>
                        		<%-- <c:if test="${companyInfoVo.huifaResultVo != null}"> --%>
			                    	<a href="javaScript:void(0)" class="btn-blue showHuifaCompany" name="${companyInfoVo.resultId }">查看详情</a>
			                    <%-- </c:if> --%>
                        	</td>
                        </tr>
            		</c:forEach>
                </tbody>
           		</c:if>
            </table>
		</div>
	</div>
</div>

<script type="text/javascript">
seajs.use(['jquery'], function($) {
	
	$(".showHuifaCompany").each(function(index, ele){
		$(ele).click(function(){
			var	url = '<z:ukey key="com.zdsoft.finance.risk.findByResultId" context="admin"/>&resultId='+$(this).attr("name");
			ZDS_MESSAGE_CLIENT.openMenuLink('huifa_detail', '汇法网信息详情', url);
		});
	});
	
	//初始化页面
	$.ZUI.initDiv("#huifaCompany");
});
</script>
