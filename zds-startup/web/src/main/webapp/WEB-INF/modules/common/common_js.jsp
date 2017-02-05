<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="com.zdsoft.framework.core.common.configure.AppParameter"%>
<%
	String portalServer = AppParameter.getPortalServer();
	String resServer =portalServer+"/static";
	String portal_loadHtml_url = portalServer + "/ui/workbench/resource/loadHtmlContentByUrl?method=post&url=";
	String localServer = request.getContextPath();
%>

<link rel="stylesheet" type="text/css" href="<%=localServer %>/assets/css/style.css">
<script>
	var resServer = "<%=resServer %>";	
</script>
<script type="text/javascript" src="<%=resServer %>/assets/js/vendor-modules/sea.js"></script>
<script type="text/javascript" src="<%=resServer %>/assets/js/vendor-modules/sea.config.js"></script>

<!-- Iframe 跨域组件 -->
<script type="text/javascript" src="<%=resServer %>/assets/js/vendor-modules/xd/util.js"></script>
<script type="text/javascript" src="<%=resServer %>/assets/js/vendor-modules/xd/xd.js"></script>
<script type="text/javascript" src="<%=resServer %>/assets/js/vendor-modules/xd/zds-portal-message.js"></script>
<script type="text/javascript" src="<%=resServer %>/assets/js/vendor-modules/xd/zds-portal-message-client.js"></script>
<script type="text/javascript" src="<%=resServer %>/assets/js/common/common_init.js"></script>
<script type="text/javascript">
	//处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外
	function banBackSpace(e) {
		var ev = e || window.event;//获取event对象
		var obj = ev.target || ev.srcElement;//获取事件源
		var t = obj.type || obj.getAttribute('type');//获取事件源类型
		//获取作为判断条件的事件类型
		var vReadOnly = obj.getAttribute('readonly');
		//处理null值情况
		vReadOnly = (vReadOnly == "") ? false : vReadOnly;
		//当敲Backspace键时，事件源类型为密码或单行、多行文本的，
		//并且readonly属性为true或enabled属性为false的，则退格键失效
		var flag1 = (ev.keyCode == 8 && (t == "password" || t == "text" || t == "textarea") && vReadOnly == "readonly") ? true : false;
		//当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
		var flag2 = (ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea") ? true : false;

		//判断
		if (flag2) {
			return false;
		}
		if (flag1) {
			return false;
		}
	}
    
	window.onload = function() {
		//禁止后退键 作用于Firefox、Opera
		document.onkeypress = banBackSpace;
		//禁止后退键  作用于IE、Chrome
		document.onkeydown = banBackSpace;
	}
	
	var getNewTokenUrl = '<z:ukey key="com.zdsoft.common.token.getNewToken" context="admin"/>';
	//根据code取下级节点数据
	var cityUrl = '<z:res resource="ess.simplecode.ByParentId" isDefault="true"/>&jsonCallBack=?';
	//根据子节点取同级及上级的数据
	var getDataCityUrl = '<z:res resource="ess.simplecode.region" isDefault="true"/>&jsoncallback=?';
</script>
