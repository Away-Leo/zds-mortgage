<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file='../common/common_js.jsp' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${localWebServer}/homePage/css/fanhuahoutai.css">
<title>提醒</title>
<script type="text/javascript">

// var taskUrl= '<z:res application="${application}" resource="workflow.process.initTaskInstancePage" isDefault="false"/>';

seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK) {
  
	//案件提醒
    $("dl").find("p").on("click", function () {
        if($(this).hasClass("active")){
            $(this).removeClass("active").siblings("ul").stop(true,true).slideUp(500);
   
        }else{
            $(this).addClass("active").siblings("ul").slideDown(500).parent("dd").siblings().find("p").removeClass("active").siblings().slideUp(500);
        }
    });
    
    $("#case").on("click", function () {
    	refreshPage1();
    });
    
});
</script>
        <p id="case">案件提醒<a href="##" onclick="remindMore();">更多</a></p>
            <dl>
               
            	<c:if test="${fn:length(borrowVos) > 0}">
            	<div>
	                <dd>
	                    <p><i>借款借据<span>${fn:length(borrowVos)}</span></i></p>
	                    <ul>
	                    	<c:forEach items="${borrowVos}" var="borrow">
	                    		<li><a href="##" onclick="remindClick('<z:ukey key="${borrow.remindUrl}" context="admin"/>');">${borrow.reminders}</a></li>
	                    	</c:forEach>
	                    </ul>
	                </dd>
	             </div>
                </c:if>
                
                <c:if test="${fn:length(contractVos) > 0}">
	                <dd>
	                    <p><i>补充合同<span>${fn:length(contractVos)}</span></i></p>
	                    <ul>
	                    	<c:forEach items="${contractVos}" var="contract">
	                    		<li><a href="##" onclick="remindClick('<z:ukey key="${contract.remindUrl}" context="admin"/>')">${contract.reminders}</a></li>
	                    	</c:forEach>
	                    </ul>
	                </dd>
                </c:if>
                <c:if test="${fn:length(encumbranceVos) > 0}">
	                <dd>
	                    <p><i>他项权证<span>${fn:length(encumbranceVos)}</span></i></p>
	                    <ul>
	                    	<c:forEach items="${encumbranceVos}" var="encumbrance">
	                    		<li><a href="##" onclick="remindClick('<z:ukey key="${encumbrance.remindUrl}" context="admin"/>')">${encumbrance.reminders}</a></li>
	                    	</c:forEach>
	                    </ul>
	                </dd>
                </c:if>
                <c:if test="${fn:length(entrustVos) > 0}">
	                <dd>
	                    <p><i>委托公证<span>${fn:length(entrustVos)}</span></i></p>
	                    <ul>
	                    	<c:forEach items="${entrustVos}" var="entrust">
	                    		<li><a href="##" onclick="remindClick('<z:ukey key="${entrust.remindUrl}" context="admin"/>')">${entrust.reminders}</a></li>
	                    	</c:forEach>
	                    </ul>
	                </dd>
                </c:if>
                <c:if test="${fn:length(warrantVos) > 0}">
	                <dd>
	                    <p><i>权证公证<span>${fn:length(entrustVos)}</span></i></p>
	                    <ul>
	                    	<c:forEach items="${entrustVos}" var="entrust">
	                    		<li><a href="##" onclick="remindClick('<z:ukey key="${entrust.remindUrl}" context="admin"/>')">${entrust.reminders}</a></li>
	                    	</c:forEach>
	                    </ul>
	                </dd>
	            </c:if>
	            <c:if test="${fn:length(otherVos) > 0}">
	                <dd>
	                    <p><i>其他<span>${fn:length(otherVos)}</span></i></p>
	                    <ul>
	                    	<c:forEach items="${otherVos}" var="other">
	                    		<li><a href="##" onclick="remindClick('<z:ukey key="${other.remindUrl}" context="admin"/>')">${entrust.other}</a></li>
	                    	</c:forEach>
	                    </ul>
	                </dd>
                </c:if>
            </dl>
</html>