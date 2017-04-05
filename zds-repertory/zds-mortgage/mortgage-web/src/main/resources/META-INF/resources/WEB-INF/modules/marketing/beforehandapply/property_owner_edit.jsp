<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>    
<!-------------押品信息》产权人信息---------------------------------------->
<div id="propertyDiv">
	<div class="page-box">
	    <div class="page-title">产权信息
	    <button type="button"  class="btn-blue" id="andPropertyInfo" style="float:right;margin-right: 40px">新增</button>
	    </div>
	    <div class="p5">
	        <div id="propertyList">
			</div>
		</div>
	</div>
		
	<!-- 新增产权人信息弹窗 -->
	<div id="propertyInfoEdit" style="display:none">
		<div id="propertyEditDiv" class="p10">
	     <form id="property_form" class="zui-form" method="post" enctype="multipart/form-data">
	       	 <input id="propertyId" name="propertyId" type="hidden"/>
	       	 <input id="propertyOwnerId" name="propertyOwnerId" type="hidden"/>
			 <dl class="form-item">
				<dt class="title"><b class="c-red mr5">*</b>产权人姓名</dt>
		         	<dd class="detail">
	                    <label> 
	                    	<input class="zui-input nwidth2 zui-validatebox" id="ownerName" name="ownerName" validate-type="Require">
						</label>
	               	</dd>
	                <dd class="detail">
	                    <input  type="hidden" data-width="94" id="copyOwnerName" name="copyOwnerName">
	                </dd>
	           </dl>
	           <dl class="form-item">
	       	   <dt class="title"><b class="c-red mr5">*</b>产权人身份证号</dt>
	              <dd class="detail">
				 	<label>
						<input type="text" class="zui-input zui-validatebox" id="credentialNo" name="credentialNo" validate-type="Require,IDCard" />
					</label>
			   </dd>
	       	</dl>
	       	
	       	<dl class="form-item block">
	       		<dt class="title"><b class="c-red mr5">*</b>证件有效期</dt>
	             <dd class="detail">
					<label> 
						<input class="zui-date strToDate zui-validatebox" type="text" id="isIdentityStartDate"
						 onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'identityStartDate',dateFmt:'yyyy-MM-dd', maxDate:'#F{$dp.$D(\'isIdentityEndDate\',{y:-5})}',onpicked:function(){WdateValidate(this);setCodeEndDate();}})" validate-type="Require" readonly />
						<input type="hidden" id="identityStartDate" name="identityStartDate" value=""/>
						 <span class="word" style="width: 22px;">至</span>
					</label>
                 </dd>
                 <dd class="detail">
				   <label> 
						<input class="zui-date strToDate zui-validatebox" type="text" id="isIdentityEndDate" 
						onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'identityEndDate',dateFmt:'yyyy-MM-dd', minDate:'#F{$dp.$D(\'isIdentityStartDate\',{y:5})}',onpicked:function(){WdateValidate(this);setCodeEndDate();}})" validate-type="Require" readonly />
						<input type="hidden" id="identityEndDate" name="identityEndDate" value=""/>
					</label>
				</dd>
				<dd class="detail">
					<label>
						<input type="hidden"  class="zui-validatebox" data-width="94" id="identityTerm"  name="identityTerm">
					</label>
				</dd>
	       	</dl>
	       	<dl class="form-item">
	       		 <dt class="title">产权人邮箱地址</dt>
	             <dd class="detail">
	               		<label> 
	               			<input type="text" class="zui-input zui-validatebox" id="emailAddress" name="emailAddress" validate-type="Email" />
						</label>
	             </dd>
	       	</dl>
	       	<dl class="form-item">
	       		 <dt class="title">产权人电话号码</dt>
	             <dd class="detail">
	               		<label> 
	               			<input type="text" class="zui-input zui-validatebox" id="property_phoneNumber" name="property_phoneNumber" validate-type="PhoneOrMobile"/> 
						</label>
	             </dd>
	       	</dl>
	       	<dl class="form-item block">
	       	   <dt class="title">产权人居住地址</dt>
	                 <dd class="detail">
	                     <input type="hidden" id="propertyProvince" name="propertyProvince" value=""/>
	                     <input type="hidden" id="propertyCity" name="propertyCity" value=""/>
	                     <input type="hidden" id="propertyCounty" name="propertyCounty" value=""/>
	                     <div id="selectAddress_property">
	                         <input id="address_property_text" class="zui-input zui-validatebox" type="text" readonly="true"/>
	                     </div>
	                 </dd>
	                 <dd class="detail">
	                     <input class="zui-input zui-validatebox" validate-type="Length[0-128]" value="" id="propertyAddress" name="propertyAddress" style="width: 215px;">
	                 </dd>
	                 <dd class="detail">
	                 		<input type="hidden" class="zd-combobox" data-width="94" id="propertyCopyAddressValue">
	                 </dd>
	             </dl>
	     </form>
	    </div> 
	</div>
</div>
	
<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.dialog','zd/jquery.zds.table','zd/jquery.zds.address','zd/jquery.zds.form'], 
		function($, CALLBACK,ZTOOL) {
		// 添加产权人对话框
		$("#propertyInfoEdit").Zdialog({
		   width: 700, height: 300, closed: true, title: "产权信息",buttons: 
	       [
	           {
	               id: 'message-btn',
	               text: '确定',
	               buttonCls: 'btn-blue',
	               handler: function () {
	            	   var isValid = $.ZUI.validateForm($('#propertyEditDiv'));
	            	   
	            	   if (isValid) {
	            		   var isAdd = false;
	            		   var id = $("#propertyId").val();
	            		   if (id == null || id == "") {
	            			   isAdd = true;
	            		   }
	            		   //拼接地址
	            		   var comprehensiveAddress = $("#address_property_text").val();
	            		   var propertyAddress = $("#propertyAddress").val();
	            		   if(comprehensiveAddress!=''&&propertyAddress!=''){
	            			   comprehensiveAddress+="/";
	            		   }
	            		   comprehensiveAddress+=propertyAddress;
	            		   if (!isAdd) {//编辑
	            			   var arr=[];
	                           arr[0] =id;//index
	                           arr[1]={
	                                   "ownerName": $("#ownerName").val(),
	                                   "credentialNo": $("#credentialNo").val(),
	                                   "identityStartDate": $("#identityStartDate").val(),
	                                   "phoneNumber": $("#property_phoneNumber").val(),
	                                   "identityStartDate": $("#identityStartDate").val(),
	                                   "identityEndDate": $("#identityEndDate").val(),
	                                   "identityTerm": $("#identityTerm").ZCombobox("getValue"),
	                                   "emailAddress": $("#emailAddress").val(),
	                                   "province": $("#propertyProvince").val(),
	                                   "city": $("#propertyCity").val(),
	                                   "district": $("#propertyCounty").val(),
	                                   "mailingAddress": $("#propertyAddress").val(),
	                                   "comprehensiveAddress":comprehensiveAddress,
	                                   "id": $("#propertyOwnerId").val()
	                                   };//行数据
	                           $('#propertyList').ZTable("editOneRow",arr);
	                           $("#propertyInfoEdit").Zdialog("close");
	                           return;
	            		   }
	            		   var row =  {
	            				   "ownerName": $("#ownerName").val(),
                                   "credentialNo": $("#credentialNo").val(),
                                   "identityStartDate": $("#identityStartDate").val(),
                                   "phoneNumber": $("#property_phoneNumber").val(),
                                   "identityStartDate": $("#identityStartDate").val(),
                                   "identityEndDate": $("#identityEndDate").val(),
                                   "identityTerm": $("#identityTerm").ZCombobox("getValue"),
                                   "emailAddress": $("#emailAddress").val(),
                                   "province": $("#propertyProvince").val(),
                                   "city": $("#propertyCity").val(),
                                   "district": $("#propertyCounty").val(),
                                   "mailingAddress": $("#propertyAddress").val(),
                                   "comprehensiveAddress":comprehensiveAddress,
                                   "id": ""
                                   };//行数据
	                   		$('#propertyList').ZTable("addOneRow",row);
	                   		$("#propertyInfoEdit").Zdialog("close");
	            	   }
	            	   
	               }
	           },
	           {
	               id: 'message-btn',
	               text: '取消',
	                  buttonCls: 'btn-gray',
	                  handler: function () {
	                    $("#propertyInfoEdit").Zdialog("close");
	                  }
	            }
	        ]
	   	});
		
		// 产权人列表
		$('#propertyList').ZTable({
	       url : '<z:ukey key="com.zdsoft.finance.marketing.propertyOwner.getPropertyOwnerList" context="admin"/>&jsoncallback=?&housePropertyId=${housePropertyVo.id}',
	       singleSelect : true,
	       isRowNum : false,
	       pagination : false,
	       currentPage : 1,
	       idField: "id",
	       tableCls : 'table-index',//table的样式
	       columns:[[
				{field : 'ownerName',title : '产权人姓名', align : 'center'},
				{field : 'credentialNo',title : '产权人身份证号码', align : 'center'},
				{field : 'identityStartDate1',title : '证件有效期', align : 'center',formatter:function(r,v){
						return ZTOOL.strToDate(r.identityStartDate+"") +"至" +ZTOOL.strToDate(r.identityEndDate+"");
					}
				},
				{field : 'phoneNumber',title : '产权人手机号码', align : 'center'},
				{field : 'emailAddress',title : '产权人邮箱地址', align : 'center'},
				{field : 'comprehensiveAddress',title : '产权人居住地址', align : 'center'},
				{field : 'identityStartDate',title : '期限开始',align : 'center',hidden:true},
				{field : 'identityEndDate',title : '期限结束',align : 'center',hidden:true},
				{field : 'identityTerm',title : '期限年限',align : 'center',hidden:true},
				{field : 'province',title : '产权人居住地址省',align : 'center',hidden:true},
				{field : 'city',title : '产权人居住地址市',align : 'center',hidden:true},
				{field : 'district',title : '产权人居住地址县',align : 'center',hidden:true},
				{field : 'mailingAddress',title : '产权人居住地址详细地址',align : 'center',hidden:true},
				{field : 'id',title : '操作', align : 'center',formatter:function(r,v){
					var propertyOwner = '<a href="javaScript:void(0)" onclick="editPropertyHandle" class="btn-blue">编辑</a>';
					    propertyOwner += '&nbsp;&nbsp;<a href="javaScript:void(0)" onclick="deletePropertyHandle" class="btn-blue">删除</a>';
					return propertyOwner;
				}}
		] ],
		onDelete:function(index, rowData) {
			//  添加判断
			return true;
		},
		onLoadSuccess:function() {
		}
		});
		
		//编辑行
		CALLBACK.editPropertyHandle = function(index,rowData) {
			$("#propertyId").val(index);
			$("#propertyOwnerId").val(rowData.id);
			var identityStartDate = rowData.identityStartDate+"";
			var identityEndDate = rowData.identityEndDate+"";
			var identityStartDate_sub=identityStartDate.substring(0,4)+"-"+identityStartDate.substring(4,6)+"-"+identityStartDate.substring(6,8);
			var identityEndDate_sub=identityEndDate.substring(0,4)+"-"+identityEndDate.substring(4,6)+"-"+identityEndDate.substring(6,8);
			// 如何更新一行的数据
			$('#ownerName').val(rowData.ownerName);
			$('#credentialNo').val(rowData.credentialNo);
			$('#property_phoneNumber').val(rowData.phoneNumber);
			$('#emailAddress').val(rowData.emailAddress);
			$('#isIdentityStartDate').val(identityStartDate_sub);
			$('#identityStartDate').val(identityStartDate);
			$('#isIdentityEndDate').val(identityEndDate_sub);
			$('#identityEndDate').val(identityStartDate);
			$('#identityTerm').ZCombobox('setValue',rowData.identityTerm);
			$('#propertyProvince').val(rowData.province);
			$('#propertyCity').val(rowData.city);
			$('#propertyCounty').val(rowData.district);
			var mailingAddress = rowData.mailingAddress;
			if(mailingAddress){
				//户籍地址
				$('#mailingAddress').val(rowData.mailingAddress);
				$('#propertyAddress').val(rowData.mailingAddress);
				$('#propertyProvince').val(rowData.province);
				$('#propertyCity').val(rowData.city);
				$('#propertyCounty').val(rowData.district);
    			$("#selectAddress_property").setAddress({
                    showStreet:false,//不显示街道   
                    cityUrl:cityUrl,//真实数据源
                    getDataCityUrl:getDataCityUrl,//根据子节点取同级及上级的数据
                    data:rowData.district,
                    callback:function(infos,selected_ids) {   
                        var str = '';
                        for(var i=0;i<infos.length;i++) {
                            if(str==""){
                                str = str+infos[i];
                            }else{
                                str = str+" / "+infos[i];
                            }
                        }
                        //显示中文
                        $('#address_property_text').val(str);
                        $('#propertyProvince').val(selected_ids[0]);
                        $('#propertyCity').val(selected_ids[1]);
                        $('#propertyCounty').val(selected_ids[2]);
                    }
                });
			}
			
			
			//组装复制name数据
        	installPropertyName();
			$("#propertyInfoEdit").Zdialog('open');
		}
		
		//删除行
		CALLBACK.deletePropertyHandle=function(index, rowData){
			$.ZMessage.question("确认", "确认要删除这条数据吗？", function () {
				if(rowData.id){
					var param ="id="+rowData.id;
					//删除数据
					$.ajax({
						type:'post',
						url:'<z:ukey key="com.zdsoft.finance.marketing.propertyOwner.deletePropertyOwner" context="admin"/>',
						data:param,
						dataType:'json',
						success:function(data){
							if(data.resultStatus == 0){
								$.ZMessage.success("成功", data.msg, function(){
		                       		$('#propertyList').ZTable("reload");
								});
							}else{
								$.ZMessage.error("失败", data.msg, function(){
								})
							}
						},
						error:function(){
							$.ZMessage.error("错误", "删除信息系统异常，请联系管理员", function(){
							});
						}
					
					});
				}else{
					//模拟点击选中事件
					$($('#propertyList .datagrid-body').find("tr")[index]).trigger("click");
					$('#propertyList').ZTable("deleteRow");
					$.ZMessage.success("成功", "删除产权人信息成功！", function(){
					});
				}
            });
		}
		
		//新增产权人弹窗
        $('#andPropertyInfo').click(function () {
        	$("#propertyId").val("");
        	$("#identityTerm").ZCombobox("setValue","");
        	//组装复制name数据
        	installPropertyName();
            $('#propertyInfoEdit').Zdialog("open");
        });
        var copyOwnerNameUrl =  '<z:ukey key="com.zdsoft.finance.beforePersonalCustomer.getAllCustomerNames" context="admin"/>&jsoncallback=?&caseApplyId=';
        //组装复制name数据
		function installPropertyName(){
			$("#copyOwnerName").val("");
			//组装数据
        	var client_customerName= $("#client_customerName").val();
        	var client_credentialType= $("#client_credentialType").val();
        	var client_customerCode="";
        	if('YWDM002501'== client_credentialType){
        		client_customerCode= $("#clientCredentialNo").val();
        	}
        	var spouse_customerName= $("#spouse_customerName").val();
        	var spouse_credentialType= $("#spouse_credentialType").val();
        	var spouse_customerCode="";
        	if('YWDM002501'== spouse_credentialType){
        		spouse_customerCode= $("#spouseVoCredentialNo").val();
        	}
        	//添加当前页面的申请人邮箱数据
        	var client_email = '';
        	if($('#clientEmail') .val()!== null || $('#clientEmail') .val()!== undefined || $('#clientEmail') .val()!== ''){
        		client_email = $('#clientEmail') .val();
        	}
        	var spouse_email = '';
        	if($('#spouseEmail ') .val()!== null || $('#spouseEmail ') .val()!== undefined || $('#spouseEmail ') .val()!== ''){
        		spouse_email =  $('#spouseEmail') .val();
        	}
        	var jsonstr=[{"id": client_customerName, "text": client_customerName,"code":client_customerCode, "email":client_email},
        	             {"id": spouse_customerName, "text": spouse_customerName,"code":spouse_customerCode, "email":spouse_email}];
        	if('${isUrlName}'){   
	        	$("#copyOwnerName").ZCombobox({
	        		 valueField: "id",
	                 textField: "text",
	                 url: copyOwnerNameUrl+caseApplyId,
	                 onSelect:function(v,t){
	                	$("#ownerName").val(t);
	                	
	                	$.ajax({
	                		type:"post",
	                		url:'<z:ukey key="com.zdsoft.finance.beforePersonalCustomer.findByCustomerId" context="admin"/>',
	                		data:"customerId="+v,
	                		dataType:"json",
	                		success:function(data){
	                			$("#credentialNo").val(data.credentialNo);
	                			$("#emailAddress").val(data.email);
	                		},
	                		error:function(){
	                			
	                		}
	                	});
	                 }
	
	        	});
        	}else{
        		$("#copyOwnerName").ZCombobox({
           		 	valueField: "id",
                    textField: "text",  
                    data: jsonstr,
                    onSelect:function(selfVal,t,i,d){
                   		$("#ownerName").val(selfVal);
                    	$("#credentialNo").val(d.code);
                    	$('#emailAddress').val(d.email);
                    }

           		});
        	}
		}
        
		// 对Date的扩展，将 Date 转化为指定格式的String 
		// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
		// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
		// 例子： 
		// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
		// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
		Date.prototype.Format = function(fmt) 
		{ //author: meizz 
		  var o = { 
		    "M+" : this.getMonth()+1,                 //月份 
		    "d+" : this.getDate(),                    //日 
		    "h+" : this.getHours(),                   //小时 
		    "m+" : this.getMinutes(),                 //分 
		    "s+" : this.getSeconds(),                 //秒 
		    "q+" : Math.floor((this.getMonth()+3)/3), //季度 
		    "S"  : this.getMilliseconds()             //毫秒 
		  }; 
		  if(/(y+)/.test(fmt)) 
		    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
		  for(var k in o) 
		    if(new RegExp("("+ k +")").test(fmt)) 
		  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length))); 
		  return fmt; 
		}
        
		$("#identityTerm").ZCombobox({
   		 	valueField: "id",
            textField: "text",
            url: "<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0054",
            onSelect:function(v,t){
            	setCodeEndDate(v);
            	if("YWDM0051044"==v){
            		$("#isIdentityEndDate").removeClass("zui-validatebox error").removeAttr("validate-type");
            	}else{
            		$("#isIdentityEndDate").addClass("zui-validatebox").attr({"validate-type": "Require"});
            	}
			}
		});
		//根据开始时间和年限算出结束时间
		window.setCodeEndDate = function(v){
			var beginDateStr = $("#isIdentityStartDate").val();
			var yearCode;
			if(v==""||v==null){
				yearCode = $("#identityTerm").ZCombobox("getValue");
			}else{
				yearCode = v;
			}
			if(yearCode!=""&&beginDateStr!=""){
				var year=0,autoEndDt=true;
	        	if("YWDM0051042"==yearCode){
	        		year=10;
	        	}else if("YWDM0051043"==yearCode){
	        		year=20;
	        	}else{
	        		autoEndDt=false
	        	}
	        	if(autoEndDt){
					beginDate = new Date(beginDateStr);
					beginDate.setFullYear(beginDate.getFullYear() + year);
					var endDateStr = beginDate.Format("yyyy-MM-dd");
		        	$("#isIdentityEndDate").val(endDateStr);
		        	$("#identityEndDate").val(endDateStr.replace(/-/g,""));
		        	$("#isIdentityEndDate").removeClass("error");
	        	}
			}
		}
		
		$("#propertyCopyAddressValue").ZCombobox({
   		 	valueField: "id",
            textField: "text",
            data: [{'id':'liveCounty','text':'复制户籍地址'},{'id':'homeDistrict','text':'复制家庭地址'}],
            onSelect:function(v,t){
            	propertyCopyAddressValue(v,t);
           }
   		});
		
        //初始选择器
        $("#selectAddress_property").Address({
            showStreet:false,//不显示街道
            cityUrl:cityUrl,//真实数据源
            getDataCityUrl:getDataCityUrl,//根据子节点取同级及上级的数据
            callback:function(infos,selected_ids) {
                var str = '';
                for(var i=0;i<infos.length;i++) {
                    if(str==""){
                        str = str+infos[i];
                    }else{
                        str = str+" / "+infos[i];
                    }
                }
                //赋值
                $('#address_property_text').val(str);
                $("#propertyProvince").val(selected_ids[0]);
                $("#propertyCity").val(selected_ids[1]);
                $("#propertyCounty").val(selected_ids[2]);
            }
        });
        
      	//复制地址
       function propertyCopyAddressValue(v,t){
        	//获取对象的值
        	var objValue=$('#'+v).val();
            if(null!=objValue&&objValue!=""){
            	//赋值街道详细地址
            	if(v=="liveCounty"){
            		$("#propertyAddress").val($('#liveAddr').val());
            	}else if(v=="homeDistrict"){
            		$("#propertyAddress").val($('#homePlace').val());
            	}
                //用这种方式复制选择项
                $("#selectAddress_property").setAddress({
                    showStreet:false,//不显示街道
                    cityUrl:cityUrl,//真实数据源
                    getDataCityUrl:getDataCityUrl,//根据子节点取同级及上级的数据
                    data:objValue,
                    callback:function(infos,selected_ids) {
                        var str = '';
                        for(var i=0;i<infos.length;i++) {
                            if(str==""){
                                str = str+infos[i];
                            }else{
                                str = str+" / "+infos[i];
                            }
                        }
                        
                        //赋值
                        $('#address_property_text').val(str);
                        $("#propertyProvince").val(selected_ids[0]);
                        $("#propertyCity").val(selected_ids[1]);
                        $("#propertyCounty").val(selected_ids[2]);
                    }
                });
            }
        }
      	
       $.ZUI.init("#propertyDiv");
	});
</script>