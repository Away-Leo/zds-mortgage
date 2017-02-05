<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form id="houseProperty_form" class="zui-form " method="post" enctype="multipart/form-data">
	<div class="page-box">
	<div class="page-title">基本信息</div>
	<div class="p5">
		<table class="table-detail">
			<tr>
				<td class="td-title pct10">案件号</td>
				<td class="pct20">
					<label>${caseApply.caseApplyCode }</label>
				</td>
				<td class="td-title pct10">主借人</td>
				<td class="pct20">
					<label>${beforePersonal.customerName }</label>
				</td>
				<td class="td-title pct10">审批金额</td>
				<td class="pct30">
					<!-- 暂时取申请金额 -->
					<label>${caseApply.applyAmount }</label>
					<c:if test="${caseApply.applyAmount !=null}">元</c:if>
				</td>
			</tr>
			
			<tr>
				<td class="td-title pct10">期限</td>
				<td>
					<label>${caseApply.applyDeadline }</label>
				</td>
				<td class="td-title pct10">利率</td>
				<td>
					<label>${caseApply.applyRate }</label>
				</td>
				<td class="td-title pct10">还款计划利率</td>
				<td>
					<label>${receivableInfo.loanMonthRate }</label>
					<c:if test="${receivableInfo.loanMonthRate !=null}">%</c:if>
				</td>
			</tr>
			
			<tr>
				<td class="td-title pct10">放款金额</td>
				<td>
					<label>${caseApply.applyAmount }</label>
					<c:if test="${caseApply.applyAmount !=null}">元</c:if>
				</td>
				<td class="td-title pct10">已收本金</td>
				<td>
					<label>0</label>
				</td>
				<td class="td-title pct10">未收本金</td>
				<td>
					<!-- 暂时取申请金额 -->
					<label>${caseApply.applyAmount }</label>
					<c:if test="${caseApply.applyAmount !=null}">元</c:if>
				</td>
			</tr>
			
			<tr>
				<td class="td-title pct10">还款方式</td>
				<td>
					<label>${receivableInfo.repaymentType }</label>
				</td>
				<td class="td-title pct10">已收利息</td>
				<td>
					<label>0</label>
				</td>
				<td class="td-title pct10">已收服务费</td>
				<td>
					<label>0</label>
				</td>
			</tr>
			
			<tr>
				<td class="td-title pct10">罚息记息方式</td>
				<td>
					<label></label>
				</td>
				<td class="td-title pct10">逾期利率</td>
				<td>
					<label>${caseApply.overdueRate }</label>
				</td>
				<td class="td-title pct10">罚息总应收</td>
				<td>
					<label>0</label>
				</td>
			</tr>
			
			<tr>
				<td class="td-title pct10">罚息已收</td>
				<td>
					<label>0</label>
				</td>
				<td class="td-title pct10">罚息待收</td>
				<td>
					<label>0</label>
				</td>
				<td class="td-title pct10">当期罚息总应收</td>
				<td>
					<label>0</label>
				</td>
			</tr>
			
			<tr>
				<td class="td-title pct10">当期罚息已收</td>
				<td>
					<label>0</label>
				</td>
				<td class="td-title pct10">当期罚息代收</td>
				<td>
					<label>0</label>
				</td>
				<td class="td-title pct10"></td>
				<td>
					<label></label>
				</td>
			</tr>
		</table>
	</div>
	</div>
	
	<div class="page-box">
		<div class="page-title">还款计划跟进</div>
		<div class="p5">
            <table class="table-flow">
                <tr>
                    <th colspan="6">还款计划</td>
                    <th colspan="5">已还与待还</td>
                    <th colspan="4">罚息</td>
                    <th colspan="3">违约金</td>
                </tr>
                <tr>
                    <th >期次</td>
                    <th >应还日期</th>
                    <th >本金</th>
                    <th >服务费</th>
                    <th >利息</th>
                    <th >剩余本金</th>
                    
                    <th >本金</td>
                    <th >利息</th>
                    <th >服务费</th>
                    <th >待还</th>
                    <th >结清本息日期</th>
                    
                    <th >逾期罚息</td>
                    <th >减免</th>
                    <th >实收填充</th>
                    <th >罚息未收</th>
                    
                    <th >应收</th>
                    <th >减免</th>
                    <th >已收</th>
                </tr>
                <c:forEach var="plan" items="${planList }">
                	<tr>
	                	<td>${plan.periodsNo }</td>
	                	<td>${plan.repaymentDate }</td>
	                	<td>${plan.repaymentAmount }</td>
	                	<td>${plan.serviceChange }</td>
	                	<td>${plan.interestAmount }</td>
	                	<td>${plan.affirmPenalty }</td>
	                	
	                	<td>0</td>
	                	<td>0</td>
	                	<td>0</td>
	                	<td>0</td>
	                	<td>0</td>
	                	
	                	<td>0</td>
	                	<td>0</td>
	                	<td>0</td>
	                	<td>0</td>
	                	
	                	<td>0</td>
	                	<td>0</td>
	                	<td>0</td>
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
