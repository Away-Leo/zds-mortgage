<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 家庭收入 查看-->
<div class="page-box">
   <div class="page-title">家庭收入</div>
   <div class="p5">
       <table class="table-detail">
           <tr>
           		<td class="td-title pct10">户主名</td>
           		<td class="pct20">
           			${familyIncomeVo.houseHolder}
           		</td>
           		<td class="td-title pct10">是否为实际用款人流水</td>
           		<td class="pct20">
					${familyIncomeVo.isMoneyName }
                </td>
           		<td class="td-title pct10"></td>
           		<td class="pct20"></td>
           </tr>
           </table>
           
           <table class="table-detail">
           <tr>
               	<td class="td-title pct45" style="text-align: center;">月份</td>
               	<td class="td-title pct45" style="text-align: center;">贷方发生额(元)</td>
           </tr>
           <c:forEach items="${detailListVo}" var="detailVo" varStatus="status">
           		<tr>
			   		<td class="pct45"  style="text-align: center;">
						 <label>
						     ${detailVo.realMonthStr }
						 </label>
			   		</td>
			   		<td class="pct45" style="text-align: center;">
						<label>
						    ${detailVo.realAmount }
						</label>
			   		</td>
			   </tr>
           </c:forEach>
		   <tr>
		   		<td class="pct45"  style="text-align: center;">月均收入</td>
		   		<td class="pct45" style="text-align: center;">
					<label>
					    ${familyIncomeVo.monthAmount }
					</label>
		   		</td>
		   </tr>
		   <tr>
		   		<td class="pct45" style="text-align: center;">半年合计收入</td>
		   		<td class="pct45" style="text-align: center;">
					<label>
					    ${familyIncomeVo.totalAmount }
					</label>
		   		</td>
		   </tr>
       </table>
   </div>
</div>