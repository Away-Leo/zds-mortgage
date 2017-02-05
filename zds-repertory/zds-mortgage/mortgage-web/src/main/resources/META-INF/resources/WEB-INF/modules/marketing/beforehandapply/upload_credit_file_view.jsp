<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../../common/common_upload.jsp" %>
<!-- 上传征信 -->		
<form id="credit_form" class="zui-form " method="post" enctype="multipart/form-data">
	<div class="page-box">
	<div class="page-title">上传征信</div>
	<div class="p5">
		<table class="table-detail">
			<tr>
				<td class="td-title pct10">上传征信</td>
				<td class="pct20">
					<dl class="form-item">
						<dd class="detail">
							<input type="hidden" id="creditAttachment" name="creditAttachment" value="${attachmentIds }"/>
							<div>
								<span id="creditUploadFile" class="f12">
								<c:if test="${empty caseMaterialListAttaVos }">注：未上传附件</c:if>
								<c:if test="${not empty caseMaterialListAttaVos }">
								<c:forEach var="c" items="${caseMaterialListAttaVos }">
								<div id="fileDiv_ff${c.attachmentId }">${c.attachmentName }  
								<a class="ml5 c-blue" name="downLink" data-id="ff${c.attachmentId }" onclick="downFile('${c.attachmentId }')">下载</a></div>
								</c:forEach>
								
								</c:if>
								
								</span>
							</div>
						</dd>
					</dl>
				</td>
				
				<td class="pct20">
				</td>
				<td class="td-title pct10"></td>
				<td class="pct30">
				</td>
			</tr>
		</table>
	</div>
</div>	
</form>




<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.table'], function($, CALLBACK) {
});
    
</script>