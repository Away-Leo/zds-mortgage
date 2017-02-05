<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file='/modules/common/head.jsp' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>在线编辑管理</title>
<meta http-equiv="cache-control" content="no-cache,must-revalidate">
<meta http-equiv="pragram" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript" src="<%=localServer%>/assets/js/uploadify/OfficeContorlFunctions.js"></script>
<script type="text/javascript">
var url_open = '<z:ukey key="zg.business.online.openFile"  context="admin"/>';//文档打开
var url_save = '<z:ukey key="zg.business.online.saveFile"  context="admin"/>&jsoncallback=?';//文档保存
var url_back = '<z:ukey key="zg.business.project.initProjectAtta" context="admin"/>&jsoncallback=?';//返回项目附件列表
$(document).ready(
	function() {
		intializePage(url_open + "&path=" + escape(escape($("#openFilePath").val())));//打开文档
		
		$('#save_').click(function (){ 
			saveFileToUrl(url_save);
		});
		
		$('#back_').click(function (){ 
			window.location.href = url_back;
		});
	}
);
</script>
</head>
<body>
	<form id="form1" enctype="multipart/form-data" style="padding: 0px; margin: 0px;" method="post">
	<input type="hidden" id="openFilePath" name="openFilePath" value="${openFilePath}"/>
	<input type="hidden" id="saveFilePath" name="saveFilePath" value="${saveFilePath}"/>
	<div id="center">
		<div class="combg wsubnav">
        	<span>在线编辑</span>
        	<a href="#" class="comicon"></a>
       </div>
       			<div class="s_content">
       				<table cellpadding="0" cellspacing="0">
                          <tr>
                              <td class="conright">
                              	<div id="officecontrol" style="height:600px;width:100%;">
                					<script type="text/javascript" src="<%=localServer%>/assets/js/uploadify/officecontrol/ntkoofficecontrol.js"></script>
                					<div id=statusBar style="height:20px;width:100%;background-color:#c0c0c0;font-size:12px;"></div>
									<script language="JScript" for=TANGER_OCX event="OnDocumentClosed()">
										setFileOpenedOrClosed(false);
									</script>
									<script language="JScript" for="TANGER_OCX" event="OnDocumentOpened(TANGER_OCX_str,TANGER_OCX_obj)">
										
										OFFICE_CONTROL_OBJ.activeDocument.saved=true;//saved属性用来判断文档是否被修改过,文档打开的时候设置成ture,当文档被修改,自动被设置为false,该属性由office提供.
										//获取文档控件中打开的文档的文档类型
										switch (OFFICE_CONTROL_OBJ.doctype)
										{
											case 1:
												fileType = "Word.Document";
												fileTypeSimple = "wrod";
												break;
											case 2:
												fileType = "Excel.Sheet";
												fileTypeSimple="excel";
												break;
											case 3:
												fileType = "PowerPoint.Show";
												fileTypeSimple = "ppt";
												break;
											case 4:
												fileType = "Visio.Drawing";
												break;
											case 5:
												fileType = "MSProject.Project";
												break;
											case 6:
												fileType = "WPS Doc";
												fileTypeSimple="wps";
												break;
											case 7:
												fileType = "Kingsoft Sheet";
												fileTypeSimple="et";
												break;
											default :
												fileType = "unkownfiletype";
												fileTypeSimple="unkownfiletype";
										}
										setFileOpenedOrClosed(true);
									</script>
									<script language="JScript" for=TANGER_OCX event="BeforeOriginalMenuCommand(TANGER_OCX_str,TANGER_OCX_obj)">
										alert("BeforeOriginalMenuCommand事件被触发");
									</script>
									<script language="JScript" for=TANGER_OCX event="OnFileCommand(TANGER_OCX_str,TANGER_OCX_obj)">
										if (TANGER_OCX_str == 3) 
										{
											alert("不能保存！");
											CancelLastCommand = true;
										}
									</script>
									<script language="JScript" for=TANGER_OCX event="AfterPublishAsPDFToURL(result,code)">
										result=trim(result);
										alert(result);
										document.all("statusBar").innerHTML="服务器返回信息:"+result;
										if(result=="文档保存成功。")
										{window.close();}
									</script>
									<script language="JScript" for=TANGER_OCX event="OnCustomMenuCmd2(menuPos,submenuPos,subsubmenuPos,menuCaption,menuID)">
									alert("第" + menuPos +","+ submenuPos +","+ subsubmenuPos +"个菜单项,menuID="+menuID+",菜单标题为\""+menuCaption+"\"的命令被执行.");
									</script>
                				</div>
                              </td>
						  </tr>                          
                    </table>
       		</div>
       </div>
       <div class="tabs_choosebtn">
           <button id="save_" href="#">保 存</button>
           <button id="back_" href="#">返 回</button>
       </div>
    </div>
    </form>
</body>
</html>