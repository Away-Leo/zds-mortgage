<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>    
<!-------------押品信息》产权人信息---------------------------------------->
   <div class="page-box">
	    <div class="page-title">产权信息
	    <button type="button"  class="btn-blue" id="andPropertyInfo" style="float:right;margin-right: 40px">新增</button>
	    </div>
	    <div class="p10">
	        <div id="propertyList">
			</div>
		</div>
	</div>
	
	<!-- 新增产权人信息弹窗 -->
	<div id="propertyInfoEdit" style="display:none">
		<div id="propertyEditDiv" class="p10">
	     <form id="property_form" class="zui-form" method="post" enctype="multipart/form-data">
	       	 <input id="propertyId" name="propertyId" type="hidden"/>
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
						 onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'identityStartDate',dateFmt:'yyyy-MM-dd',onpicked:function(){WdateValidate(this);}})" validate-type="Require" readonly />
						<input type="hidden" id="identityStartDate" name="identityStartDate" value=""/>
					</label>
                         <span class="word" style="width: 22px;">至</span>
				   <label> 
						<input class="zui-date strToDate zui-validatebox" type="text" id="isIdentityEndDate" 
						onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'identityEndDate',dateFmt:'yyyy-MM-dd',onpicked:function(){WdateValidate(this);}})" validate-type="Require" readonly />
						<input type="hidden" id="identityEndDate" name="identityEndDate" value=""/>
					</label>
				</dd>
				<dd class="detail">
					<label>
						<input type="hidden"  class="zui-validatebox" data-width="94" id="identityTerm"  name="identityTerm" validate-type="Require">
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
                  		<input type="hidden" class="zui-combobox" data-width="94" id="propertyCopyAddressValue"
                  		
                  		 data-data="[{'id':'liveCounty','text':'复制户籍地址'},{'id':'homeDistrict','text':'复制家庭地址'}]"
                                   data-valuefield="id"
                                   data-textfield="text" 
                  		 data-callback="propertyCopyAddressValue">
                  </dd>
              </dl>
	     </form>
	    </div> 
	</div>
	
	<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.dialog','zd/jquery.zds.table','zd/jquery.zds.address'], 
		function($, CALLBACK) {
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
	                                   "mailingAddress": $("#mailingAddress").val(),
	                                   "comprehensiveAddress":$("#address_property_text").val(),
	                                   "id": ""
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
                                   "mailingAddress": $("#mailingAddress").val(),
                                   "comprehensiveAddress":$("#address_property_text").val(),
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
	       url : '<z:ukey key="com.zdsoft.finance.marketing.getPropertyOwnerList" context="admin"/>&jsoncallback=?&housePropertyId=${housePropertyVo.id}',
	       singleSelect : true,
	       isRowNum : false,
	       pagination : false,
	       currentPage : 1,
	       idField: "id",
	       tableCls : 'table-index',//table的样式
	       columns:[[
				{field : 'ownerName',title : '产权人姓名', align : 'center'},
				{field : 'credentialNo',title : '产权人身份证号码', align : 'center'},
				{field : 'identityStartDate',title : '证件有效期', align : 'center'},
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
					var propertyOwner = '<a href="javaScript:void(0)" onclick="editPropertyHandle"><button class="btn-blue" type="button">编辑</button></a>';
					    propertyOwner += '&nbsp;&nbsp;<a href="javaScript:void(0)" onclick="deletePropertyHandle"><button class="btn-blue" type="button" >删除</button></a>';
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
			// 如何更新一行的数据
			$('#ownerName').val(rowData.ownerName);
			$('#credentialNo').val(rowData.credentialNo);
			$('#property_phoneNumber').val(rowData.phoneNumber);
			$('#emailAddress').val(rowData.emailAddress);
			$('#isIdentityStartDate').val(rowData.identityStartDate);
			$('#isIdentityEndDate').val(rowData.identityEndDate);
			$('#identityTerm').val(rowData.identityTerm);
			$('#propertyProvince').val(rowData.province);
			$('#propertyCity').val(rowData.city);
			$('#propertyCounty').val(rowData.district);
			$('#mailingAddress').val(rowData.mailingAddress);
			//组装复制name数据
        	installPropertyName();
			$("#propertyInfoEdit").Zdialog('open');
		}
		
		//删除行
		CALLBACK.deletePropertyHandle=function(index, rowData){
			//模拟点击选中事件
			$($('#propertyList .datagrid-body').find("tr")[0]).trigger("click");
			$('#propertyList').ZTable("deleteRow");
		}
		
		//新增产权人弹窗
        $('#andPropertyInfo').click(function () {
        	$("#propertyId").val("");
        	//组装复制name数据
        	installPropertyName();
            $('#propertyInfoEdit').Zdialog("open");
        });
		
        //组装复制name数据
		function installPropertyName(){
			//组装数据
        	var client_customerName= $("#client_customerName").val();
        	var spouse_customerName= $("#spouse_customerName").val();
        	var jsonstr=[{"id": spouse_customerName, "text": spouse_customerName}, {"id": client_customerName, "text": client_customerName}];
        	$("#copyOwnerName").ZCombobox({
        		 valueField: "id",
                 textField: "text",
                 data: jsonstr,
                 onSelect:function(selfVal){
                	$("#ownerName").val(selfVal);
                 }

        	});
		}
        
		/* $("#propertyCopyAddressValue").ZCombobox({
   		 	valueField: "id",
            textField: "text",
            data: [{'id':'liveCounty','text':'复制户籍地址'},{'id':'homeDistrict','text':'复制家庭地址'}]
   		}); */
		
		$("#identityTerm").ZCombobox({
   		 	valueField: "id",
            textField: "text",
            url: "<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0054"
		});
		/* $("#propertyCopyAddressValue").ZCombobox({
   		 	valueField: "id",
            textField: "text",
            data: [{'id':'liveCounty','text':'复制户籍地址'},{'id':'homeDistrict','text':'复制家庭地址'}]
   		}); */
		$("#identityTerm").ZCombobox({
   		 	valueField: "id",
            textField: "text",
            data: [{'id':'0','text':'十年','isDefault':'true'},{'id':'1','text':'二十年'}]
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
        CALLBACK.propertyCopyAddressValue=function(v,t){
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
	}); 
	</script>
