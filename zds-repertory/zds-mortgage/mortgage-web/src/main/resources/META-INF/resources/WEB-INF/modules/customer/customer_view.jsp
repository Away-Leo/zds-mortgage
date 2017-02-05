<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../common/common_js.jsp" %>
<%@ include file="../common/common_upload.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>客户信息查看</title>
</head>
<body>
	<div>
		<div class="page-box">
			<div class="p10">
				<form id="client_form" class="zui-form " method="post" enctype="multipart/form-data">
					<input type="hidden" id="customerId" name="id" value="${latestCustomerVo.id }">
					<div class="page-box">
						<div class="page-title">主借人信息</div>
						<dl class="form-item">
							<dt class="title">主借人头像：</dt>
							<dd class="detail">
								<input type="hidden" id="imgId" name="imgId" value="${latestCustomerVo.imgId }">
								<div id="showImg">
									<c:if test="${!(latestCustomerVo.imgId eq null || latestCustomerVo.imgId == '')}">
										<img src="<z:ukey key="public.upload.download"  context="admin"/>"&attachmentId="${latestCustomerVo.imgId}" width="200px" height="87px"/>
									</c:if>
								</div>
								
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">姓名：</dt>
							<dd class="detail">
								
									<input class="zui-input" value="${latestCustomerVo.clientNm }"
									id="clientNm" name="clientNm" >
								
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">曾用名：</dt>
							<dd class="detail">
								
									<input class="zui-input" value="${latestCustomerVo.formerNm }"
									id="formerNm" name="formerNm" >
								
							</dd>
						</dl>
						<dl class="form-item">
			                <dt class="title">证件类型:</dt>
			                <dd class="detail">
								<input class="zui-input" value="${latestCustomerVo.credentiaType }"
									id="credentiaType" name="credentiaType" >			                           
			                </dd>
			            </dl>
						<dl class="form-item">
							<dt class="title">证件号码：</dt>
							<dd class="detail">
								
									<input class="zui-input"  value="${latestCustomerVo.credentialNo }"
									id="credentialNo" name="credentialNo">
								
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">出生日期：</dt>
							<dd class="detail">
								
									<input class="zui-input" id="birthday" value="${latestCustomerVo.birthday }">
								
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">性别：</dt>
							<dd class="detail">
								
			                      <input class="zui-input" value="${latestCustomerVo.gender }"
									id="gender" name="gender" >
								
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">婚况：</dt>
							<dd class="detail">
								
			                           <input class="zui-input" value="${latestCustomerVo.marital }"
									id="marital" name="marital" >
								
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">职业类型：</dt>
							<dd class="detail">
								
			                           <input class="zui-input" value="${latestCustomerVo.careerType }"
									id="careerType" name="careerType" >
								
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">个人年收入(元)：</dt>
							<dd class="detail">
								
									<input class="zui-input" value="${latestCustomerVo.annualIncome }"
									id="annualIncome" name="annualIncome" >
								
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">教育程度：</dt>
							<dd class="detail">
								
			                           <input class="zui-input" value="${latestCustomerVo.degree }"
									id="degree" name="degree" >
								
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">邮箱地址：</dt>
							<dd class="detail">
								
									<input class="zui-input" value="${latestCustomerVo.email }"
									id="email" name="email" >
								
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">居住年限(年)：</dt>
							<dd class="detail">
								
									<input class="zui-input"  value="${latestCustomerVo.liveAge }"
									id="liveAge" name="liveAge" >
								
							</dd>
						</dl>
						<br>
						<dl class="form-item">
							<dt class="title">居住地址：</dt>
							<%-- <dd class="detail">
								
			                           <input class="zui-input" value="${latestCustomerVo.liveProvince }"
									id="liveProvince" name="liveProvince" >
                                
                            </dd>
                            <dd class="detail">
                               	
			                           <input class="zui-input" value="${latestCustomerVo.liveCity }"
									id="liveCity" name="liveCity" >
                               	
                             </dd>
                            <dd class="detail">
                               	
			                           <input class="zui-input" value="${latestCustomerVo.liveDistrict }"
									id="liveDistrict" name="liveDistrict" >
                             	
                             </dd> --%>
                             <dd class="detail">
                             	<div id="selectAddress" data-code="${latestCustomerVo.liveCode }">
		                            <input id="region" class="zui-input zui-validatebox"  type="text" readonly="true" style="width: 200px;"/>
		                            <input id="liveCode" type="hidden" name="liveCode" value="${latestCustomerVo.liveCode }"/>
		                        </div>
                             </dd>
                            <dd class="detail">
                            	
                               		<input class="zui-input" value="${latestCustomerVo.liveAddress }"
									id="liveAddress" name="liveAddress" >
								
							</dd>
						</dl>
						<br>
						<dl class="form-item">
							<dt class="title">户籍地址：</dt>
							<%-- <dd class="detail">
								
			                           <input class="zui-input" value="${latestCustomerVo.domicileProvince }"
									id="domicileProvince" name="domicileProvince" >
                               	
                            </dd>
                            <dd class="detail">
                               	
			                           <input class="zui-input" value="${latestCustomerVo.domicileCity }"
									id="domicileCity" name="domicileCity" >
                               	
                           </dd>
                           <dd class="detail">
                               	
			                           <input class="zui-input" value="${latestCustomerVo.domicileDistrict }"
									id="domicileDistrict" name="domicileDistrict" >
                               	
                          </dd> --%>
                          <dd class="detail">
                             	<div id="selectAddresss" data-code="${latestCustomerVo.domicileCode }">
		                            <input id="regions" class="zui-input zui-validatebox"  type="text" readonly="true" style="width: 200px;"/>
		                            <input id="domicileCode" type="hidden" name="domicileCode" value="${latestCustomerVo.domicileCode }"/>
		                        </div>
                             </dd>
                          <dd class="detail">
                               	
                               		<input class="zui-input"  value="${latestCustomerVo.domicileAddress }"
									id="domicileAddress" name="domicileAddress" >
								
							</dd>
						</dl>
					</div>
				</form>
				<form id="marital_form" class="zui-form " method="post" enctype="multipart/form-data" <c:if test="${latestCustomerVo.marital == '已婚' || latestCustomerVo.marital == '初婚' || latestCustomerVo.marital == '再婚' || latestCustomerVo.marital == '复婚'}">style="display:inline"</c:if><c:if test="${latestCustomerVo.marital != '已婚' && latestCustomerVo.marital != '初婚' && latestCustomerVo.marital != '再婚' && latestCustomerVo.marital != '复婚'}">style="display:none"</c:if>>
					<input type="hidden" id="maritalId" name="id" value="${maritalCustomerVo.id }">
					<div class="page-box">
						<div class="page-title">主借人配偶信息</div>
						<dl class="form-item">
							<dt class="title">配偶头像：</dt>
							<dd class="detail">
								<input type="hidden" id="maritalImgId" name="imgId" value="${maritalCustomerVo.imgId}">
								<div id="maritalShowImg">
									<c:if test="${!(maritalCustomerVo.imgId eq null || maritalCustomerVo.imgId == '')}">
										<img src="<z:ukey key="public.upload.download"  context="admin"/>"&attachmentId="${maritalCustomerVo.imgId}" width="200px" height="87px"/>
									</c:if>
								</div>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">姓名：</dt>
							<dd class="detail">
								
									<input class="zui-input" 
									value="${maritalCustomerVo.clientNm}" id="maritalclientNm" name="clientNm" >
								
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">曾用名：</dt>
							<dd class="detail">
								
									<input class="zui-input" value="${maritalCustomerVo.formerNm }"
									id="maritalformerNm" name="formerNm" >
								
							</dd>
						</dl>
						<dl class="form-item">
			                <dt class="title">证件类型:</dt>
			                <dd class="detail">
			                           <input class="zui-input" value="${maritalCustomerVo.credentiaType }"
									id="maritalcredentiaType" name="credentiaType" >
			                </dd>
			            </dl>
						<dl class="form-item">
							<dt class="title">证件号码：</dt>
							<dd class="detail">
								
									<input class="zui-input" value="${maritalCustomerVo.credentialNo }"
									id="maritalcredentialNo" name="credentialNo">
								
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">职业类型：</dt>
							<dd class="detail">
								
			                           <input class="zui-input" value="${maritalCustomerVo.careerType }"
									id="maritalcareerType" name="careerType" >
								
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">邮箱地址：</dt>
							<dd class="detail">
								
									<input class="zui-input"  value="${maritalCustomerVo.email }"
									id="maritalemail" name="email" >
								
							</dd>
						</dl>
					</div>					
		        </form>
		            <div class="page-box">
						<div class="page-title">联系方式</div>
						<div class="p10">
					        <div id="tb-contact" class="zui-datagrid" zdata-options='{"queryParams":{"clientId|E|S":"${latestCustomerVo.id}"},"url":"<z:ukey key="com.zdsoft.finance.contact.findByClientId" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index"}'>
							    <table>
							        <thead>
							        <tr>
							            <th data-options="field:contactName,width:20%">姓名</th>
							            <th data-options="field:relationshipType,width:25%">关系</th>
							            <th data-options="field:contactType,width:25%">联系类型</th>
							            <th data-options="field:contact,width:25%">电话号码</th>
							            <th data-options="field:id,width:15%" formatter="contactView">操作</th>
							        </tr>
							        </thead>
							    </table>
							</div>
						</div>
					</div>
					<div class="page-box">
						<div class="page-title">工作单位信息</div>
						<div class="p10">
					        <div id="tb-unit" class="zui-datagrid" zdata-options='{"queryParams":{"clientId|E|S":"${latestCustomerVo.id}"},"url":"<z:ukey key="com.zdsoft.finance.workUnits.findByClientId" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index"}'>
							    <table>
							        <thead>
							        <tr>
							            <th data-options="field:unitsName,width:6%">姓名</th>
							            <th data-options="field:relationshipType,width:5%">关系</th>
							            <th data-options="field:companyName,width:10%">工作单位名称</th>
							            <th data-options="field:industryType,width:10%">行业类型</th>
							            <th data-options="field:industry,width:10%">行业</th>
							            <th data-options="field:unitNature,width:6%">单位性质</th>
							            <th data-options="field:position,width:6%">职务</th>
							            <th data-options="field:workeYears,width:2%">工作年限</th>
							            <th data-options="field:unitPhone,width:8%">工作单位电话</th>
							            <th data-options="field:unitAddress,width:25%">单位住址</th>
							            <th data-options="field:id,width:20%" formatter="unitView">操作</th>
							        </tr>
							        </thead>
							    </table>
							</div>
							<div id="unit-function">
							    <a class="zui-toolbar" id="unit-add" text="新增" buttonCls="btn-blue" handler="unitAdd"></a>
						    </div>
						</div>
					</div>
			</div>
		</div>
	</div>
	
					<!-- 查看联系方式弹出框 -->
	                <div class="page-box" id="contactViewDialog" style = "display: none">
						<!-- <div class="page-title">查看联系方式</div> -->
						<div class="p10">
					        <form id="client_form" class="zui-form " method="post" enctype="multipart/form-data">
					        	<dl class="form-item">
									<dt class="title">姓名：</dt>
									<dd class="detail">
										
											<input class="zui-input" readonly="readonly"
											value="" id="contactnameview">
										
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">关系：</dt>
									<dd class="detail">
										
											<input class="zui-input" readonly="readonly" 
											value="" id="relationshipTypeview">
										
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">联系类型：</dt>
									<dd class="detail">
										
											<input class="zui-input" readonly="readonly"
											value="" id="contactTypeview">
										
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">电话号码：</dt>
									<dd class="detail">
										
											<input class="zui-input" readonly="readonly"
											value="" id="contactsview">
										
									</dd>
								</dl>
					        </form>
						</div>
					</div>
					
					<!-- 查看工作单位弹出框 -->
					<div class="page-box" id="unitViewDialog" style = "display: none">
						<!-- <div class="page-title">查看工作单位信息</div> -->
						<div class="p10">
					        <form id="client_form" class="zui-form " method="post" enctype="multipart/form-data">
					        	<dl class="form-item">
									<dt class="title">姓名：</dt>
									<dd class="detail">
										
											<input class="zui-input" readonly="readonly"
											value="" id="unitsNameView">
										
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">关系：</dt>
									<dd class="detail">
										
											<input class="zui-input" readonly="readonly"
											value="" id="relationshipTypesView">
										
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">工作单位名称：</dt>
									<dd class="detail">
										
											<input class="zui-input" readonly="readonly"
											value="" id="companyNameView">
										
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">行业类型：</dt>
									<dd class="detail">
										
											<input class="zui-input" readonly="readonly"
											value="" id="industryTypeView">
										
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">单位性质：</dt>
									<dd class="detail">
										
											<input class="zui-input" readonly="readonly"
											value="" id="unitNatureView">
										
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">职务：</dt>
									<dd class="detail">
										
											<input class="zui-input" readonly="readonly"
											value="" id="positionView" >
										
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">工作年限(年)：</dt>
									<dd class="detail">
										
											<input class="zui-input" readonly="readonly"
											value="" id="workeYearsView" >
										
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">工作单位电话：</dt>
									<dd class="detail">
										
											<input class="zui-input" readonly="readonly"
											value="" id="unitPhoneView" >
										
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">单位地址：</dt>
									<dd class="detail">
										
											<input class="zui-input" readonly="readonly" 
											value="" id="unitAddressView" >
										
									</dd>
								</dl>
					        </form>
						</div>
					</div>
	
	<script type="text/javascript">
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.address','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter','zd/make-first-py'], 
			function($, CALLBACK) {
			
			//根据主借人客户id查询相关配偶信息
			function getMarital(){
				var clientId = '${latestCustomerVo.id }';
				$.ajax({
                    type: 'post',
                    url: '<z:ukey key="com.zdsoft.finance.customer.findMaritalCustomerByClientId" context="admin"/>',
                    data: {clientId:clientId},
                    dataType: 'json',
                    success: function (result) {
                        if (result.resultStatus == 0) {
                        	var data = result.optional.maritalCustomerVo;
                    		if(data){
                    			//如果id不为空，表示对应证件类型+证件号的附属人已存在，带出来允许修改
            					if(data.id ){
            						//如果附属人已存在，给附属人赋值
            						$("#maritalformerNm").val(data.formerNm);
            						$("#maritalcareerType").val(data.careerType);
            						$("#maritalemail").val(data.email);
            						$("#maritalclientNm").val(data.clientNm);
            						$("#maritalcredentiaType").val(data.credentiaType);
            						$("#maritalcredentialNo").val(data.credentialNo);
            						$("#maritalImgId").val(data.imgId);
            						$('#maritalShowImg').children().remove();
            		    			var url = '<z:ukey key="public.upload.download"  context="admin"/>"&attachmentId=' + data.imgId;
            		    			$('#maritalShowImg').append("<img  width='200px' height='87px' src ='"+url+"'/>")
            					}
            				}
                        }
                    },
                    error: function () {
                    	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {});
                    }
                });
			};
			
			//联系方式操作格式化
	        CALLBACK.contactView=function(rowData,index){
	        	var data='<a href="javaScript:void(0)" onclick="contactViewDialog"><button class="btn-blue">详情</button></a>'
	        	return data;
	        }
			
	      //联系方式新增弹出框打开
			CALLBACK.contactViewDialog = function(index, row){
				$("#contactnameview").val(row.contactName);
				$("#relationshipTypeview").val(row.relationshipType);
				$("#contactTypeview").val(row.contactType);
				$("#contactsview").val(row.contact);
				$("#contactViewDialog").Zdialog("open");
			};
			
			$("#contactViewDialog").Zdialog({
				width : 430,
				height : 350,
				closed : true,
				title:"查看联系方式",
				onOpen:function(){
					 CALLBACK.openLayerBefore(this,420);//更改弹出与iframe的间距   参数一表示 obj 弹出层的div 参数二表示 需要调整的高度
				},onClose:function(){
					CALLBACK.closeLayerBefore(this,420);//更改弹出与iframe的间距   参数一表示 obj 弹出层的div 参数二表示 需要调整的高度
				},
				
				buttons : [{
					id : 'message-btn',
					text : '关闭',
					handler : function() {
						$("#contactViewDialog").Zdialog("close");
					}
				}]
			});
			
	      	//工作单位操作格式化
	        CALLBACK.unitView=function(rowData,index){
	        	var data='<a href="javaScript:void(0)" onclick="unitViewDialog"><button class="btn-blue">详情</button></a>'
	        	return data;
	        }
	      	
	      //工作单位新增弹出框打开
			CALLBACK.unitViewDialog = function(index, row){
				$("#unitsNameView").val(row.unitsName);
				$("#relationshipTypesView").val(row.relationshipType);
				$("#companyNameView").val(row.companyName);
				$("#industryTypeView").val(row.industryType);
				$("#unitNatureView").val(row.unitNature);
				$("#positionView").val(row.position);
				$("#workeYearsView").val(row.workeYears);
				$("#unitPhoneView").val(row.unitPhone);
				$("#unitAddressView").val(row.unitAddress);
		  		$("#unitViewDialog").Zdialog("open");
			};
			$("#unitViewDialog").Zdialog({
				width : 430,
				height : 350,
				closed : true,
				title:"查看工作单位",
				onOpen:function(){
					 CALLBACK.openLayerBefore(this,420);//更改弹出与iframe的间距   参数一表示 obj 弹出层的div 参数二表示 需要调整的高度
				},onClose:function(){
					CALLBACK.closeLayerBefore(this,420);//更改弹出与iframe的间距   参数一表示 obj 弹出层的div 参数二表示 需要调整的高度
				},
				
				buttons : [{
					id : 'message-btn',
					text : '关闭',
					handler : function() {
						$("#unitViewDialog").Zdialog("close");
					}
				}]
			});
			
			//家庭住址
			$("#selectAddress").Address({
                callback:function(infos,selected_ids) {
                    var str = '';
                    var strCode = '';
                    for(var i=0;i<infos.length;i++) {
                        if(str==""){
                            str = str+infos[i];
                            strCode = strCode+selected_ids[i];
                        }else{
                            str = str+" / "+infos[i];
                            strCode = strCode+","+selected_ids[i];
                        }
                    }
                    $('#region').val(str);
                    $('#liveCode').val(strCode);
                }
            });
			
			//户籍地址
			$("#selectAddresss").Address({
                callback:function(infos,selected_ids) {
                    var str = '';
                    var strCode = '';
                    for(var i=0;i<infos.length;i++) {
                        if(str==""){
                            str = str+infos[i];
                            strCode = strCode+selected_ids[i];
                        }else{
                            str = str+" / "+infos[i];
                            strCode = strCode+","+selected_ids[i];
                        }
                    }
                    $('#regions').val(str);
                    $('#domicileCode').val(strCode);
                }
            });
			$.ZUI.init();
			
			getMarital();
		});
	</script>
</body>
</html>