<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../../common/common_upload.jsp" %>
<!-- 上传征信 -->		
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
									<c:if test="${empty fileStoreVos }">注：未上传附件</c:if>
									<c:if test="${not empty fileStoreVos }">
										<c:forEach var="c" items="${fileStoreVos }">
											<div id="fileDiv_${c.attachmentId }" >${c.fileName }
												<a class='ml5 c-blue'  name='downLink'  data-id="${c.attachmentId }" >下载</a>
											</div>
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