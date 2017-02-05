<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../common/common_js.jsp' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<link rel="stylesheet" type="text/css" href="${localWebServer}/homePage/css/fanhuahoutai.css">
<title>泛华首页</title>
</head>
<body>
	<!--ll-part1开始-->
	<div class="ll-part1">
        <dl class="myTask">
            <dt><i>我的任务<span>${pendingTasks.totalRows == null ? 0 : pendingTasks.totalRows}</span></i><a href="##" onclick="pendingMore()">更多</a></dt>
            <c:forEach items="${pendingTasks.records}" var="pendingTask">
            	<dd><a href="#" onclick="viewTasks('${pendingTask.taskName}','${pendingTask.framePageUrl}','${pendingTask.taskInstanceId}','${pendingTaskl.taskCandidateId}')">${pendingTask.subject}一${pendingTask.processNm}一${pendingTask.taskName}</a>
            	<span>${pendingTask.startTimeAsString}<%-- <fmt:formatDate value="${pendingTask.startTime}" pattern="yyyy/MM/dd"/> --%></span></dd>
            </c:forEach>
            <c:if test="${pendingTasks.totalRows < 6}">
            	<c:forEach begin="0" end="${6 - pendingTasks.totalRows}" step="1">
            		<dd><a href="#"></a><span></span></dd>
            	</c:forEach>
            </c:if>
        </dl>
        <div class="caseReminder ">
            <p>案件提醒<a href="##" onclick="remindMore();">更多</a></p>
            <dl>
            	<c:if test="${fn:length(borrowVos) > 0}">
	                <dd>
	                    <p><i>借款借据<span>${fn:length(borrowVos)}</span></i></p>
	                    <ul>
	                    	<c:forEach items="${borrowVos}" var="borrow">
	                    		<li><a href="##" onclick="remindClick('${borrow.remindUrl}');">${borrow.reminders}</a></li>
	                    	</c:forEach>
	                    </ul>
	                </dd>
                </c:if>
                <c:if test="${fn:length(contractVos) > 0}">
	                <dd>
	                    <p><i>补充合同<span>${fn:length(contractVos)}</span></i></p>
	                    <ul>
	                    	<c:forEach items="${contractVos}" var="contract">
	                    		<li><a href="##" onclick="remindClick('${contract.remindUrl}')">${contract.reminders}</a></li>
	                    	</c:forEach>
	                    </ul>
	                </dd>
                </c:if>
                <c:if test="${fn:length(encumbranceVos) > 0}">
	                <dd>
	                    <p><i>他项权证<span>${fn:length(encumbranceVos)}</span></i></p>
	                    <ul>
	                    	<c:forEach items="${encumbranceVos}" var="encumbrance">
	                    		<li><a href="##" onclick="remindClick('${encumbrance.remindUrl}')">${encumbrance.reminders}</a></li>
	                    	</c:forEach>
	                    </ul>
	                </dd>
                </c:if>
                <c:if test="${fn:length(entrustVos) > 0}">
	                <dd>
	                    <p><i>委托公证<span>${fn:length(entrustVos)}</span></i></p>
	                    <ul>
	                    	<c:forEach items="${entrustVos}" var="entrust">
	                    		<li><a href="##" onclick="remindClick('${entrust.remindUrl}')">${entrust.reminders}</a></li>
	                    	</c:forEach>
	                    </ul>
	                </dd>
                </c:if>
                <c:if test="${fn:length(warrantVos) > 0}">
	                <dd>
	                    <p><i>权证公证<span>${fn:length(entrustVos)}</span></i></p>
	                    <ul>
	                    	<c:forEach items="${entrustVos}" var="entrust">
	                    		<li><a href="##" onclick="remindClick('${entrust.remindUrl}')">${entrust.reminders}</a></li>
	                    	</c:forEach>
	                    </ul>
	                </dd>
	            </c:if>
	            <c:if test="${fn:length(otherVos) > 0}">
	                <dd>
	                    <p><i>其他<span>${fn:length(otherVos)}</span></i></p>
	                    <ul>
	                    	<c:forEach items="${otherVos}" var="other">
	                    		<li><a href="##" onclick="remindClick('${other.remindUrl}')">${entrust.other}</a></li>
	                    	</c:forEach>
	                    </ul>
	                </dd>
                </c:if>
            </dl>
        </div>
    </div>
    <!--ll-part2开始-->
    <div class="ll-part2">
        <div class="topNav">
            <p>预期案件</p>
            <ul>
                <li data-type="first"><span class="current">60天及以上</span><em>0</em></li>
                <li data-type="second"><span>30及59天</span><em>0</em></li>
                <li data-type="third"><span>20及29天</span><em>0</em></li>
                <li data-type="fourth"><span>1-19天</span><em>0</em></li>
            </ul>
        </div>
        <div class="tableBox">
            <p>总计：<span>0</span><em>单</em>已垫资：<span>0</span><em>万元</em>未收回：<span>0</span><em>万元</em></p>
            <table data-type="first" class="tabActive">
                <thead>
                    <tr>
                        <th>序号</th>
                        <th>机构</th>
                        <th>主借人</th>
                        <th>案件号</th>
                        <th>产品分类</th>
                        <th>子产品</th>
                        <th>申请天数</th>
                        <th>放款金额（万）</th>
                        <th>本金余额（万）</th>
                        <th>逾期天数</th>
                        <th>放款日期</th>
                        <th>贷后状态</th>
                        <th>贷后方案</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                	<!-- 数据 -->
                    <%-- <tr>
                        <td>1</td>
                        <td>重庆正大华日软件</td>
                        <td>张三</td>
                        <td class="caseNum">20160701</td>
                        <td>兴业贷</td>
                        <td>经营贷-抵押</td>
                        <td>20</td>
                        <td class="money">50</td>
                        <td class="money">50</td>
                        <td>60</td>
                        <td class="loanDate">2016/12/12</td>
                        <td>供货管理</td>
                        <td>—</td>
                        <td class="operate"><a href="#"><img src="${localWebServer}/homePage/images/operate.jpg" alt="operate"></a></td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>重庆正大华日软件</td>
                        <td>张三</td>
                        <td class="caseNum">20160701</td>
                        <td>兴业贷</td>
                        <td>经营贷-抵押</td>
                        <td>20</td>
                        <td class="money">50</td>
                        <td class="money">50</td>
                        <td>60</td>
                        <td class="loanDate">2016/12/12</td>
                        <td>供货管理</td>
                        <td>—</td>
                        <td class="operate"><a href="#"><img src="${localWebServer}/homePage/images/operate.jpg" alt="operate"></a></td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>重庆正大华日软件</td>
                        <td>张三</td>
                        <td class="caseNum">20160701</td>
                        <td>兴业贷</td>
                        <td>经营贷-抵押</td>
                        <td>20</td>
                        <td class="money">50</td>
                        <td class="money">50</td>
                        <td>60</td>
                        <td class="loanDate">2016/12/12</td>
                        <td>供货管理</td>
                        <td>—</td>
                        <td class="operate"><a href="#"><img src="${localWebServer}/homePage/images/operate.jpg" alt="operate"></a></td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>重庆正大华日软件</td>
                        <td>张三</td>
                        <td class="caseNum">20160701</td>
                        <td>兴业贷</td>
                        <td>经营贷-抵押</td>
                        <td>20</td>
                        <td class="money">50</td>
                        <td class="money">50</td>
                        <td>60</td>
                        <td class="loanDate">2016/12/12</td>
                        <td>供货管理</td>
                        <td>—</td>
                        <td class="operate"><a href="#"><img src="${localWebServer}/homePage/images/operate.jpg" alt="operate"></a></td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>重庆正大华日软件</td>
                        <td>张三</td>
                        <td class="caseNum">20160701</td>
                        <td>兴业贷</td>
                        <td>经营贷-抵押</td>
                        <td>20</td>
                        <td class="money">50</td>
                        <td class="money">50</td>
                        <td>60</td>
                        <td class="loanDate">2016/12/12</td>
                        <td>供货管理</td>
                        <td>—</td>
                        <td class="operate"><a href="#"><img src="${localWebServer}/homePage/images/operate.jpg" alt="operate"></a></td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>重庆正大华日软件</td>
                        <td>张三</td>
                        <td class="caseNum">20160701</td>
                        <td>兴业贷</td>
                        <td>经营贷-抵押</td>
                        <td>20</td>
                        <td class="money">50</td>
                        <td class="money">50</td>
                        <td>60</td>
                        <td class="loanDate">2016/12/12</td>
                        <td>供货管理</td>
                        <td>—</td>
                        <td class="operate"><a href="#"><img src="${localWebServer}/homePage/images/operate.jpg" alt="operate"></a></td>
                    </tr> --%>
                </tbody>
            </table>
            <table data-type="second">
                <thead>
	                <tr>
	                    <th>序号</th>
	                    <th>机构</th>
	                    <th>主借人</th>
	                    <th>案件号</th>
	                    <th>产品分类</th>
	                    <th>子产品</th>
	                    <th>申请天数</th>
	                    <th>放款金额（万）</th>
	                    <th>本金余额（万）</th>
	                    <th>逾期天数</th>
	                    <th>放款日期</th>
	                    <th>贷后状态</th>
	                    <th>贷后方案</th>
	                    <th>操作</th>
	                </tr>
                </thead>
                <tbody>
                	<!-- 数据 -->
                </tbody>
            </table>
            <table data-type="third">
                <thead>
	                <tr>
	                    <th>序号</th>
	                    <th>机构</th>
	                    <th>主借人</th>
	                    <th>案件号</th>
	                    <th>产品分类</th>
	                    <th>子产品</th>
	                    <th>申请天数</th>
	                    <th>放款金额（万）</th>
	                    <th>本金余额（万）</th>
	                    <th>逾期天数</th>
	                    <th>放款日期</th>
	                    <th>贷后状态</th>
	                    <th>贷后方案</th>
	                    <th>操作</th>
	                </tr>
                </thead>
                <tbody>
                	<!-- 数据 -->
                </tbody>
            </table>
            <table data-type="fourth">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>机构</th>
                    <th>主借人</th>
                    <th>案件号</th>
                    <th>产品分类</th>
                    <th>子产品</th>
                    <th>申请天数</th>
                    <th>放款金额（万）</th>
                    <th>本金余额（万）</th>
                    <th>逾期天数</th>
                    <th>放款日期</th>
                    <th>贷后状态</th>
                    <th>贷后方案</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                	<!-- 数据 -->
                </tbody>
            </table>
        </div>
    </div>
</body>
<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK) {
	var thisIframeId=window.name;
	//案件提醒
    $(".caseReminder dl").find("p").on("click", function () {
        if($(this).hasClass("active")){
            $(this).removeClass("active").siblings("ul").stop(true,true).slideUp(500);
        }else{
            $(this).addClass("active").siblings("ul").slideDown(500).parent("dd").siblings().find("p").removeClass("active").siblings().slideUp(500);
        }
    });
    //表格
    $(".tableBox").find("table[data-type=first]").show();
    $(".topNav ul").find("li").on("click",function(){
        //var type=$(this).data("type");ie8不兼容
        var type=$(this).attr("data-type");
		$(this).find("span").addClass("current").parent().siblings("li").find("span").removeClass("current");
        $(".tableBox").find("table[data-type="+type+"]").show().siblings("table").hide();
    });
    //代办任务 更多
    window.pendingMore = function(){
    	var viewMyPendingTasksUrl = '<z:res application="${application}" resource="workflow.process.initTaskInstancePage" isDefault="false"/>';
    	ZDS_MESSAGE_CLIENT.openMenuLink('pendingTasks', '我的待办任务', viewMyPendingTasksUrl+"&openMethod=tabs&parentIframeId="+thisIframeId);
    };
    //提醒
    window.remindClick = function(_url){
    	ZDS_MESSAGE_CLIENT.openMenuLink('remindTasks', '提醒', _url+"&openMethod=tabs&parentIframeId="+thisIframeId);
    };
    //更多提醒
    window.remindMore = function(){
    	alert("需要提醒给出列表页URL");
    };
    //处理任务
    window.viewTasks = function(taskName,url,taskCandidateId,taskCandidateId) {
        ZDS_MESSAGE_CLIENT.openMenuLink('myPendingTask'+taskCandidateId, taskName, url+"?taskCandidateId=" + taskCandidateId + "&taskCandidateId=" + taskCandidateId + "&openMethod=tabs&parentIframeId="+thisIframeId);
    }
});
</script>
</html>