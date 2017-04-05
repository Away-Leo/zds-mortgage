<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<div id="businessInfoDiv">
	<!-- 列表区域 -->
	<div class="page-box">
		<div class="page-title">工商信息</div>
		<div class="p10">
			<table data-type="first" class="scroll table-index">
                <thead class="datagrid-header">
                    <tr>
                        <th>企业名称</th>
                        <th>统一社会信用代码</th>
                        <th>企业(机构)类型</th>
                        <th>法定代表人</th>
                        <th>注册资本(万元)</th>
                        <th>成立日期</th>
                        <th>经营状态</th>
                        <th>住址</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <c:if test="${not empty basicVoList}">
                <tbody class="datagrid-body">
                	<c:forEach items="${basicVoList}" var="basicVo">
                		<tr>
                			<td>${basicVo.entname }</td>
                        	<td>${basicVo.creditcode }</td>
                        	<td>${basicVo.enttype }</td>
                        	<td>${basicVo.frname }</td>
                        	<td>${basicVo.regcap }</td>
                        	<td>${basicVo.esdate }</td>
                        	<td>${basicVo.entstatus }</td>
                        	<td>${basicVo.dom }</td>
                        	<td>
                        		<a href="javaScript:void(0)" class="btn-blue viewDetail" name="${basicVo.orderId }"  >查看详情</a>
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
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK) {
	
	$(".viewDetail").each(function(index, ele){
		$(ele).click(function(){
			var	url = '<z:ukey key="com.zdsoft.finance.risk.findByOrderId" context="admin"/>&orderId='+$(this).attr("name");
			ZDS_MESSAGE_CLIENT.openMenuLink('business_detail', '工商信息详情', url);
		});
	});
	//初始化页面
	$.ZUI.initDiv("#businessInfoDiv");
});
</script>
