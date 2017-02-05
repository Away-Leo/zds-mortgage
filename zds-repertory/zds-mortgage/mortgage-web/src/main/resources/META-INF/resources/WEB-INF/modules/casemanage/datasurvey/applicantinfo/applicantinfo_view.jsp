<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<div class="frm-content frm-bottom">
	<div class="page-box">
		<div class="p10">
			<!-- 客户信息查看 -->
			<%@ include file="client_info_view.jsp"%>
			<!--联系方式 -->
			<%@ include file="client_contact_way_view.jsp"%>
			<!-- 工作单位 -->
			<%@ include file="client_work_unit_view.jsp"%>
		</div>
	</div>
	</div>
</div>
<script type="text/javascript">
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form'], 
		function($, CALLBACK) {
			$.ZUI.init();
		});
	</script>