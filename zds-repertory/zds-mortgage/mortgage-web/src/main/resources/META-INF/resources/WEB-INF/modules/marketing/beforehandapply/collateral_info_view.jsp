<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- 押品信息 -->		
<div class="page-box">
	<div class="page-title">押品信息</div>
	<div class="p5">
		<table class="table-detail">
			<tr>
				<td class="td-title pct10">小区名称</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail f12">
							<label>
							${housePropertyVo.communityName }
							</label>
						</dd>
					</dl>
				</td>
				<td class="td-title pct10">所在楼层</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail f12">
							<label> 
							${housePropertyVo.placeFloor }
							</label>
						</dd>
					</dl>
				</td>
				<td class="td-title pct10">总楼层</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail f12">
							<label> 
							${housePropertyVo.sumFloor }
							</label>
						</dd>
					</dl>
				</td>
			</tr>
			<tr>
				<td class="td-title pct10">面积</td>
				<td>
					<dl class="form-item form-auto">
						<dd class="detail f12">
							<label>${housePropertyVo.area }
								<front style="font-size: 14px;">㎡</front>
							</label>
						</dd>
					</dl>
				</td>
				<td class="td-title pct10">房产性质</td>
				<td>
					<dl class="form-item form-auto">
						<dd class="detail f12">
							${housePropertyVo.estatePropertiesName }
						</dd>
						<dd class="detail f12">
						&nbsp;
						&nbsp;
						&nbsp;
							${housePropertyVo.estatePropertiessOther }
						</dd>
					</dl>
				</td>
				<td class="td-title pct10">房产权属</td>
				<td>
					<dl class="form-item form-auto">
						<dd class="detail f12">
							${housePropertyVo.estateOwnershipName}
						</dd>
					</dl>
				</td>
			</tr>
			<tr>
				<td class="td-title pct10">楼龄</td>
				<td>
					<dl class="form-item form-auto">
						<dd class="detail f12">
							<label>
								<c:if test="${not empty housePropertyVo.floorAge }">
									${housePropertyVo.floorAge }<front style="font-size: 14px;">年</front> 
								</c:if>
							</label>
						</dd>
					</dl>
				</td>
				<td class="td-title pct10"></td>
				<td></td>
				<td class="td-title pct10"></td>
				<td></td>
			</tr>
			<tr>
               <td class="td-title pct10">押品地址</td>
               <td class="pct20" colspan="5">
                   <dl class="form-item form-auto">
                   		<dd class="detail f12">
                           ${housePropertyVo.totalMailingAddress }
                       </dd>
                   </dl>
               </td>
           </tr>
			<tr>
				<td class="td-title pct10">抵押情况(第N抵)</td>
				<td>
					<dl class="form-item form-auto">
						<dd class="detail f12">
						${housePropertyVo.mortgageSituationName }
	                    </dd>
					</dl>
				</td>
				<td class="td-title pct10">综合评估价</td>
				<td>
					<c:choose>  
					   <c:when test="${empty housePropertyVo.synthesizePrice || housePropertyVo.synthesizePrice <= 0}"> 
							<front class="word">未评估&nbsp;&nbsp;</front>
					   </c:when>  
					   <c:otherwise>
					   		<fmt:formatNumber value="${housePropertyVo.synthesizePrice}" pattern="#,##0.00#"/> 
							<front class="word">元&nbsp;&nbsp;</front>
							<button class="btn-orange mr10" id="checkValuation">查看估价</button>
					   </c:otherwise>  
					</c:choose> 
				</td>
				<td class="td-title pct10"></td>
				<td></td>
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
