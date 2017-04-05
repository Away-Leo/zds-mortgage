<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file='../common/common_js.jsp' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>查看房产评估价</title>
</head>
<body>
<div id="infoDiv" class="frm-content frm-bottom">
	<div class="page-box">
		<div class="p10">
		<c:if test="${empty showTitle}">
			<div class="page-box">
				<div class="page-title">地址与价格</div>
				<div class="p5">
					<table class="table-detail">
						<tr>
							<td class="td-title pct10">地址</td>
							<td class="pct20">
								<dl class="form-item form-auto">
									<dd class="detail  f12">
										<label> ${houseAddress} </label>
									</dd>
								</dl>
							</td>
							<td class="td-title pct10">评估价(元)</td>
							<td class="pct20">
								<dl class="form-item form-auto">
									<dd class="detail f12">
										<label id="sumPrice"> ${not empty sumPrice ? sumPrice:"未查询到对应的估价信息"} </label>
									</dd>
								</dl>
							</td>
							<td class="td-title pct10"></td>
							<td class="pct20">
								<dl class="form-item form-auto">
									<dd class="detail  f12">
										<label> </label>
									</dd>
								</dl>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</c:if>
		<c:if test="${empty resutMap}">
			<td class="td-title pct10">未返回房产评估数据</td>
		</c:if>
			<c:forEach items="${resutMap}" var="evaluateInfo" varStatus="status" >
				<div class="page-box">
					<div class="page-title">${evaluateInfo.key }查询</div>
					<div class="p5">
						<table class="table-detail">
							<tr>
								<td class="td-title pct10">总价(元)</td>
								<td class="pct20">
									<dl class="form-item form-auto">
										<dd class="detail  f12">
											<label> ${evaluateInfo.value.evaluatePriceStr} </label>
										</dd>
									</dl>
								</td>
								<td class="td-title pct10">请求时间</td>
								<td class="pct20">
									<dl class="form-item form-auto">
										<dd class="detail f12">
											<label> ${evaluateInfo.value.createTimeStr} </label>
										</dd>
									</dl>
								</td>
								<td class="td-title pct10">返回时间</td>
								<td class="pct20">
									<dl class="form-item form-auto">
										<dd class="detail  f12">
											<label>
												<c:if test="${evaluateInfo.value.onlineFlg eq 0 }">
													${evaluateInfo.value.resultTimeStr}
												</c:if>
												<c:if test="${evaluateInfo.value.onlineFlg eq 1 }">
													${evaluateInfo.value.createTimeStr}
												</c:if>
											</label>
										</dd>
									</dl>
								</td>
							</tr>
							<tr>
								<td class="td-title pct10">MSG</td>
								<td class="pct20">
									<dl class="form-item form-auto">
										<dd class="detail  f12">
											<label>
												<c:if test="${evaluateInfo.value.onlineFlg eq 0 }">
													人工查询
												</c:if>
												<c:if test="${evaluateInfo.value.onlineFlg eq 1 }">
													自动查询
												</c:if>
											</label>
										</dd>
									</dl>
								</td>
								<td class="td-title pct10"></td>
								<td>
									<dl class="form-item form-auto">
										<dd class="detail f12">
											<label> </label>
										</dd>
									</dl>
								</td>
								<td class="td-title pct10"></td>
								<td class="pct20">
									<dl class="form-item form-auto">
										<dd class="detail  f12">
											<label> </label>
										</dd>
									</dl>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>
<script type="text/javascript">
seajs.use(['jquery'], function($) {
	
	$.ZUI.initDiv("#infoDiv");
	
	
});

 
</script> 
</body>
</html>