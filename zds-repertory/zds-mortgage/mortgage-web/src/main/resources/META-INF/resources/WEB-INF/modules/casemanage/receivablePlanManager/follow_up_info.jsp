<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form id="houseProperty_form" class="zui-form " method="post" enctype="multipart/form-data">
	<div class="page-box">
		<div class="page-title">跟进操作信息</div>
		<div class="p5">
            <table class="table-flow">
                <tr>
                    <th class="tl">序号</td>
                    <th class="tl">操作类型</th>
                    <th class="tl">处理金额（元）</th>
                    <th class="tl">实际发生日期</th>
                    <th class="tl">提交人</th>
                    <th class="tl">提交时间</th>
                    
                    <th class="tl">状态</td>
                    <th class="tl">备注</th>
                </tr>
                <c:forEach var="s" items="receivableFollowUp">
                	<tr>
	                	<td></td>
	                	<td></td>
	                	<td></td>
	                	<td></td>
	                	<td></td>
	                	<td></td>
	                	
	                	<td></td>
	                	<td></td>
	                </tr>
                </c:forEach>
                
            </table>
        </div>
	</div>
	
</form>
<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter','zd/make-first-py'], 
	function($, CALLBACK, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
		$.ZUI.init();
		// 获取贷款基本申请数据
		function getBeforehandApply() {
			return $('#beforehandApply_form').serialize();
		}
		// 获取押品信息数据
		function getHouseProperty() {
			return $('#houseProperty_form').serialize();
		}
		
		//保存营销申请信息
		$('#saveBeforehandApply').click(function(){
			//saveData(false);
		});
		//提交营销申请信息
		$('#submitBeforehandApply').click(function(){
			//saveData(true);
		});
	}
	
);
</script>
