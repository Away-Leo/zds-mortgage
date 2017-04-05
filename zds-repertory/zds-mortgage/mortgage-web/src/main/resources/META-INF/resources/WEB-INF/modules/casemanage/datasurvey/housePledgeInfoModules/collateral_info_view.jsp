<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- 押品信息 -->		
<div class="page-box">
	<div class="page-title">押品信息</div>
	<div class="p5">
		<table class="table-detail">
			<tr>
				<td class="td-title pct10">小区名称</td>
				<td class="pct20">
					${housePropertyVo.communityName }
				</td>
				<td class="td-title pct10">所在楼层</td>
				<td class="pct20">
					${housePropertyVo.placeFloor }
					<c:if test="${not empty housePropertyVo.placeFloor }">
						<front style="font-size: 14px;">层</front>
					</c:if>
				</td>
				<td class="td-title pct10">总楼层</td>
				<td class="pct20">
					${housePropertyVo.sumFloor }
					<c:if test="${not empty housePropertyVo.sumFloor }">
						<front style="font-size: 14px;">层</front>
					</c:if>
				</td>
			</tr>
			<tr>
				<td class="td-title pct10">楼龄</td>
				<td class="pct20">
					${housePropertyVo.floorAge }
					<c:if test="${not empty housePropertyVo.floorAge }">
						<front style="font-size: 14px;">年</front>
					</c:if>
				</td>
				<td class="td-title pct10">面积</td>
				<td class="pct20">
					${housePropertyVo.area }
					<c:if test="${not empty housePropertyVo.area }">
						<front style="font-size: 14px;">㎡</front>
					</c:if>
				</td>
				<td class="td-title pct10">房产性质</td>
				<td class="pct20">
					${housePropertyVo.estatePropertiesName }
					<c:if test="${not empty housePropertyVo.estatePropertiessOther }">
					/${housePropertyVo.estatePropertiessOther }
					</c:if>
				</td>
			</tr>
			<tr>
				<td class="td-title pct10">房产权属</td>
				<td class="pct20">
					${housePropertyVo.estateOwnershipName}
				</td>
				<td class="td-title pct10">中介询价</td>
				<td class="pct20">
					${housePropertyVo.intermediaryInquiry}
					<c:if test="${not empty housePropertyVo.intermediaryInquiry }">
						<front style="font-size: 14px;">元</front>
					</c:if>
				</td>
				<td class="td-title pct10">网络询价</td>
				<td class="pct20">
					${housePropertyVo.networkInquiry }
					<c:if test="${not empty housePropertyVo.networkInquiry }">
						<front style="font-size: 14px;">元</front>
					</c:if>
				</td>
			</tr>
			<tr>
				<td class="td-title pct10">风控核定价</td>
				<td class="pct20">
					${housePropertyVo.controlPrice }
					<c:if test="${not empty housePropertyVo.controlPrice }">
						<front style="font-size: 14px;">元</front>
					</c:if>
				</td>
				<td class="td-title pct10">评估价</td>
				<td class="pct20">
					${housePropertyVo.evaluatingPrice }
					<c:if test="${not empty housePropertyVo.evaluatingPrice }">
						<front style="font-size: 14px;">元</front>
					</c:if>
				</td>
				<td class="td-title pct10">风控复议价</td>
				<td class="pct20">
					${housePropertyVo.controlReviewPrice }
					<c:if test="${not empty housePropertyVo.controlReviewPrice }">
						<front style="font-size: 14px;">元</front>
					</c:if>
				</td>
			</tr>
			<tr>
				<td class="td-title pct10">是否有电梯</td>
				<td class="pct20">
					${housePropertyVo.isElevatorName }
				</td>
				<td class="td-title pct10">居住状态</td>
				<td class="pct20">
					${housePropertyVo.livingStateName}
				</td>
				<td class="td-title pct10">是否装修</td>
				<td class="pct20">
					${housePropertyVo.isRenovationName }
				</td>
			</tr>
			<tr>
				<td class="td-title pct10">不动产证号</td>
				<td class="pct20">
					${housePropertyVo.houseNo }
				</td>
				<td class="td-title pct10"></td>
				<td class="pct20">
				</td>
				<td class="td-title pct10"></td>
				<td class="pct20">
				</td>
			</tr>
			<tr>
				<td class="td-title pct10">押品地址</td>
               	<td class="pct20" colspan="5">
                   ${housePropertyVo.totalMailingAddress }
               </td>
			</tr>
			<tr>
				<td class="td-title pct10">抵押情况(第N抵)</td>
				<td class="pct20">
					${housePropertyVo.mortgageSituationName }
				</td>
				<td class="td-title pct10">综合评估价</td>
				<td class="pct20" colspan="3">
<!-- 综合评估价: 当房产评估价为空的时候，也就是没调用综合评估价时，显示房产估价按钮，反之显示“查看估价”和“集团复议”按钮 -->
					<c:choose>  
					   <c:when test="${empty housePropertyVo.synthesizePrice || housePropertyVo.synthesizePrice <= 0}"> 
							<front class="word">未评估&nbsp;&nbsp;</front>
					   </c:when>  
					   <c:otherwise>
					   		<fmt:formatNumber value="${housePropertyVo.synthesizePrice}" pattern="#,##0.00#"/> 
							<front style="font-size: 14px;">元&nbsp;&nbsp;</front>
							<button class="btn-orange mr10" id="checkValuation">查看估价</button>
					   </c:otherwise>  
					</c:choose> 
				</td>
			</tr>
		</table>
	</div>
</div>			
<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter','zd/jquery.zds.address'], 
		function($, CALLBACK) {
		
		//综合评估价：查看估价按钮 
		$("#checkValuation").click(function(event){
			event.preventDefault();
 			//进入兴业贷->押品信息下“查看自动评估界面。 	同时返回这个综合评估价
			ZDS_MESSAGE_CLIENT.openMenuLink('automaticEvaluation', "查看自动评估",'<z:ukey key="com.zdsoft.finance.houseassessment.houseEvaluate.getPriceForCompany" context="admin"/>&bizid=${housePropertyVo.id}');
		});
	});
</script>
