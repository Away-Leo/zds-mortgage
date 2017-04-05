<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 产权状态 -->
<div class="page-box">
	<div class="page-title">产权状态 </div>
	<div class="p5">
		<table class="table-detail" id="propertyStatusTable">
			<c:choose>  
				<c:when test="${searchVo.searchStatus == 1}">
					<tr> 
						<td class="td-title pct10">查册状态</td>
						<td class="pct20">
							已查册
						</td>
						<td class="td-title pct10">查册单位</td>
						<td class="pct20">
							${searchVo.searchUnit }
						</td>
						<td class="td-title pct10"></td>
						<td class="pct30"></td>
					</tr>
					<tr>
						<td class="td-title">是否查封</td>
						<td>
							<c:choose>  
								<c:when test="${searchVo.isSearched == true}">
									已查封
								</c:when>
								<c:otherwise>
									未查封
								</c:otherwise>
							</c:choose>
						</td>
						<td class="td-title"></td>
						<td></td>
						<td class="td-title"></td>
						<td></td>
					</tr>
					<c:choose>  
						<c:when test="${searchVo.isSearchHistory == true}">
							<tr>
								<td class="td-title">是否有查封历史</td>
								<td>
									有查封历史
								</td>
								<td class="td-title">查封时间</td>
								<td>
									${searchVo.searchDate }
								</td>
								<td class="td-title">查封期限</td>
								<td>
									<c:if test="${not empty searchVo.searchTerm }">
										${searchVo.searchTerm}<front style="font-size: 14px;">年</front> 
									</c:if>
								</td>
							</tr>
							<tr>
								<td class="td-title">查封金额</td>
								<td>
									<c:if test="${not empty searchVo.searchAmount }">
										${searchVo.searchAmount}<front style="font-size: 14px;">元</front> 
									</c:if>
								</td>
								<td class="td-title">查封者</td>
								<td>
									${searchVo.searchName }
								</td>
								<td class="td-title">解封时间</td>
								<td>
									${searchVo.unsealDate }
								</td>
							</tr>
							<tr style="display:none">
								<td class="td-title">查封事由</td>
								<td colspan="5">
									<label>
										${searchVo.searchReason }
									</label>
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td class="td-title">是否有查封历史</td>
								<td>
									无查封历史
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
					<c:choose>  
						<c:when test="${searchVo.isMortgageHistory == true}">
							<tr>
								<td class="td-title">是否有抵押历史</td>
								<td>
									有抵押历史
								</td>
								<td class="td-title">抵押时间</td>
								<td>
									${searchVo.mortgageDate }
								</td>
								<td class="td-title">查封金额</td>
								<td>
									<c:if test="${not empty searchVo.mortgageAmount }">
										${searchVo.mortgageAmount}<front style="font-size: 14px;">元</front> 
									</c:if>
								</td>
							</tr>
							<tr>
								<td class="td-title"></td>
								<td></td>
								<td class="td-title">抵押状态</td>
								<td>
									<c:choose>  
										<c:when test="${searchVo.mortgageeStatus == 0}">
											已抵押
										</c:when>
										<c:when test="${searchVo.mortgageeStatus == 1}">
											未抵押
										</c:when>
										<c:otherwise>
										</c:otherwise>
									</c:choose>
								</td>
								<td class="td-title">解押时间</td>
								<td>
									${searchVo.dischargeDate }
								</td>
							</tr>
							<tr style="display:none">
								<td class="td-title"></td>
								<td></td>
								<td class="td-title">抵押权人</td>
								<td>
									${searchVo.mortgagee }
								</td>
								<td class="td-title">预计日期</td>
								<td>
									${searchVo.expectDate }
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td class="td-title">是否有查封历史</td>
								<td>
									无抵押历史
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</c:when>  
				<c:otherwise>
			   		<tr> 
				   		<td class="td-title pct10">查册状态</td>
						<td class="pct20">
							未查册
						</td>
						<td class="td-title pct10"></td>
						<td class="pct30"></td>
					</tr>
				</c:otherwise>  
			</c:choose>
		</table>
	</div>
</div>