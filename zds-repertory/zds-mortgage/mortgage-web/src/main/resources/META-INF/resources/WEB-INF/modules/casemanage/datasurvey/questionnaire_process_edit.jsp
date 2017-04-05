<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>   
<div class="frm-content frm-bottom">
		<div id="questionnaire_div"></div>
</div>
	
	<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/switch','zd/jquery.zds.table', 'zd/jquery.zds.form','zd/jquery.zds.seleter'],
	    	 function ($, CALLBACK,ZTOOL){
	    	 $("#questionnaire_div").load('<z:ukey key="com.zdsoft.finance.question.questionnairePage" context="admin"/>&caseApplyId=${caseApplyId}&sceneTypeCode=YWDM0010202');
	});
</script>
