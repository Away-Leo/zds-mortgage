 // 请勿修改，否则可能出错
	var userAgent = navigator.userAgent, 
				rMsie = /(msie\s|trident.*rv:)([\w.]+)/, 
				rFirefox = /(firefox)\/([\w.]+)/, 
				rOpera = /(opera).+version\/([\w.]+)/, 
				rChrome = /(chrome)\/([\w.]+)/, 
				rSafari = /version\/([\w.]+).*(safari)/;
				var browser;
				var version;
				var ua = userAgent.toLowerCase();
				function uaMatch(ua) {
					var match = rMsie.exec(ua);
					if (match != null) {
						return { browser : "IE", version : match[2] || "0" };
					}
					var match = rFirefox.exec(ua);
					if (match != null) {
						return { browser : match[1] || "", version : match[2] || "0" };
					}
					var match = rOpera.exec(ua);
					if (match != null) {
						return { browser : match[1] || "", version : match[2] || "0" };
					}
					var match = rChrome.exec(ua);
					if (match != null) {
						return { browser : match[1] || "", version : match[2] || "0" };
					}
					var match = rSafari.exec(ua);
					if (match != null) {
						return { browser : match[2] || "", version : match[1] || "0" };
					}
					if (match != null) {
						return { browser : "", version : "0" };
					}
				}
				var browserMatch = uaMatch(userAgent.toLowerCase());
				if (browserMatch.browser) {
					browser = browserMatch.browser;
					version = browserMatch.version;
				}
				//document.write(browser);
/*
谷歌浏览器事件接管
*/
function OnComplete2(type,code,result)
{
	var res = null;
	if(result != "" && result!=null){
		res = JSON.parse(result);  
		if (!res.resultStatus) {
			// 保存失败
			alert("文档保存失败，请截图并联系管理员！");
		}else{
			alert("已成功保存文档！");
			window.close();
		}
	}
}
function OnComplete(type,code,html)
{
	//alert(type);
	//alert(code);
	//alert(html);
	//alert("BeginOpenFromURL成功回调");
}
function OnComplete3(str,doc)
{
	try{
		OnDocumentOpenedForAll();
	}catch(e){
		
	}
}
function publishashtml(type,code,html){
	//alert(html);
	//alert("Onpublishashtmltourl成功回调");
}
function publishaspdf(type,code,html){
	//alert(html);
	//alert("Onpublishaspdftourl成功回调");
}
function saveasotherurl(type,code,html){
	//alert(html);
	//alert("SaveAsOtherformattourl成功回调");
}
function dowebget(type,code,html){
	//alert(html);
	//alert("OnDoWebGet成功回调");
}
function webExecute(type,code,html){
	//alert(html);
	//alert("OnDoWebExecute成功回调");
}
function webExecute2(type,code,html){
	//alert(html);
	//alert("OnDoWebExecute2成功回调");
}
function FileCommand(TANGER_OCX_str,OFFICE_CONTROL_OBJ){
	if (TANGER_OCX_str == 3) 
	{
		//alert("不能保存！");
		OFFICE_CONTROL_OBJ.CancelLastCommand = true;
	}
}
function CustomMenuCmd(menuPos,submenuPos,subsubmenuPos,menuCaption,menuID){
	//alert("第" + menuPos +","+ submenuPos +","+ subsubmenuPos +"个菜单项,menuID="+menuID+",菜单标题为\""+menuCaption+"\"的命令被执行.");
}
var classid = '6AA93B0B-D450-4a80-876E-3909055B0640';
if (browser=="IE"){
	document.write('<!-- 用来产生编辑状态的ActiveX控件的JS脚本-->   ');
	document.write('<!-- 因为微软的ActiveX新机制，需要一个外部引入的js-->   ');
	document.write('<object id="TANGER_OCX" classid="clsid:'+classid+'"');
	document.write('codebase="'+localServer_offControll+'/online/officecontrol/ofctnewclsid.cab#version=5,0,2,8" width="100%" height="100%">   ');
	document.write('<param name="IsUseUTF8URL" value="-1">   ');
	document.write('<param name="IsUseUTF8Data" value="-1">   ');
	document.write('<param name="BorderStyle" value="1">   ');
	document.write('<param name="BorderColor" value="14402205">   ');
	document.write('<param name="TitlebarColor" value="15658734">   ');
	document.write('<param name="isoptforopenspeed" value="0">   ');
	


document.write('<param name="MakerCaption" value="重庆正大华日软件有限公司">');
document.write('<param name="MakerKey" value="3B6C00B53400A1B3717FB3209D19CA0ECDFE89A3">');
document.write('<param name="ProductCaption" value="泛华金融"> ');
document.write('<param name="ProductKey" value="3CEAE7D1B8A88835E05A93BA6EE26E118FAD9563">');


	document.write('<param name="TitlebarTextColor" value="0">   ');
	document.write('<param name="MenubarColor" value="14402205">   ');
	document.write('<param name="MenuButtonColor" VALUE="16180947">   ');
	document.write('<param name="MenuBarStyle" value="3">   ');
	document.write('<param name="MenuButtonStyle" value="7">   ');
	document.write('<param name="WebUserName" value="NTKO">   ');
	document.write('<param name="Caption" value="泛华金融">   ');
	document.write('<SPAN STYLE="color:red">不能装载文档控件。请在检查浏览器的选项中检查浏览器的安全设置。</SPAN>   ');
	document.write('</object>');
}
else if (browser=="firefox"){ 	
		document.write('<object id="TANGER_OCX" type="application/ntko-plug"  codebase="'+localServer_offControll+'/online/officecontrol/ofctnewclsid.cab#version=5,0,2,8" width="100%" height="100%" ForOnSaveToURL="OnComplete2" ForOnBeginOpenFromURL="OnComplete" ForOndocumentopened="OnComplete3"');
		document.write('ForOnpublishAshtmltourl="publishashtml"');
		document.write('ForOnpublishAspdftourl="publishaspdf"');
		document.write('ForOnSaveAsOtherFormatToUrl="saveasotherurl"');
		document.write('ForOnDoWebGet="dowebget"');
		document.write('ForOnDoWebExecute="webExecute"');
		document.write('ForOnDoWebExecute2="webExecute2"');
		document.write('ForOnFileCommand="FileCommand"');
		document.write('ForOnCustomMenuCmd2="CustomMenuCmd"');
		document.write('_IsUseUTF8URL="-1"   ');
		

document.write('_MakerCaption="重庆正大华日软件有限公司"');
document.write('_MakerKey="3B6C00B53400A1B3717FB3209D19CA0ECDFE89A3"');
document.write('_ProductCaption="泛华金融" ');
document.write('_ProductKey="3CEAE7D1B8A88835E05A93BA6EE26E118FAD9563"');

	
		
		document.write('_IsUseUTF8Data="-1"   ');
		document.write('_BorderStyle="1"   ');
		document.write('_BorderColor="14402205"   ');
		document.write('_MenubarColor="14402205"   ');
		document.write('_MenuButtonColor="16180947"   ');
		document.write('_MenuBarStyle="3"  ');
		document.write('_MenuButtonStyle="7"   ');
		document.write('_WebUserName="NTKO"   ');
		document.write('_Caption="泛华金融"    ');
		document.write('clsid="{'+classid+'}" >');
		document.write('<SPAN STYLE="color:red">尚未安装NTKO Web FireFox跨浏览器插件。请点击<a href="'+localServer_offControll+'/online/NtkoCrossBrowserSetup.msi">安装组件</a></SPAN>   ');
		document.write('</object>   ');
}else if(browser=="chrome"){
		document.write('<object id="TANGER_OCX" clsid="{'+classid+'}"  ForOnSaveToURL="OnComplete2" ForOnBeginOpenFromURL="OnComplete" ForOndocumentopened="OnComplete3"');
		document.write('ForOnpublishAshtmltourl="publishashtml"');
		document.write('ForOnpublishAspdftourl="publishaspdf"');
		document.write('ForOnSaveAsOtherFormatToUrl="saveasotherurl"');
		document.write('ForOnDoWebGet="dowebget"');
		document.write('ForOnDoWebExecute="webExecute"');
		document.write('ForOnDoWebExecute2="webExecute2"');
		document.write('ForOnFileCommand="FileCommand"');
		document.write('ForOnCustomMenuCmd2="CustomMenuCmd"');
		
	
	
	
	
document.write('_MakerCaption="重庆正大华日软件有限公司"');
document.write('_MakerKey="3B6C00B53400A1B3717FB3209D19CA0ECDFE89A3"');
document.write('_ProductCaption="泛华金融" ');
document.write('_ProductKey="3CEAE7D1B8A88835E05A93BA6EE26E118FAD9563"');

		document.write('codebase="'+localServer_offControll+'/online/officecontrol/ofctnewclsid.cab#version=5,0,2,8" width="100%" height="100%" type="application/ntko-plug" ');
		document.write('_IsUseUTF8URL="-1"   ');
		document.write('_IsUseUTF8Data="-1"   ');
		document.write('_BorderStyle="1"   ');
		document.write('_BorderColor="14402205"   ');
		document.write('_MenubarColor="14402205"   ');
		document.write('_MenuButtonColor="16180947"   ');
		document.write('_MenuBarStyle="3"  ');
		document.write('_MenuButtonStyle="7"   ');
		document.write('_WebUserName="NTKO"   ');
		document.write('_Caption="泛华金融">    ');
		document.write('<SPAN STYLE="color:red">尚未安装NTKO Web Chrome跨浏览器插件。请点击<a href="'+localServer_offControll+'/online/NtkoCrossBrowserSetup.msi">安装组件</a></SPAN>   ');
		document.write('</object>');
	}else if (Sys.opera){
		alert("sorry,ntko web印章暂时不支持opera!");
	}else if (Sys.safari){
		 alert("sorry,ntko web印章暂时不支持safari!");
	}