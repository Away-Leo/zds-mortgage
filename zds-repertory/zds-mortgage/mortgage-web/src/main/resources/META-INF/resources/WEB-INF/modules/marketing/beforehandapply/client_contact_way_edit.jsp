<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>    
<!-------------客户信息》联系方式---------------------------------------->
   <div class="page-title">联系方式
   <button type="button"  class="btn-blue" id="andContactWayInfo" style="float:right;margin-right: 40px">新增</button>
   </div>
   <div class="p10">
      	<div id="contactWayInfoList">
		</div>
   </div>
	
	<!-- 新增联系方式弹窗 -->
	<div id="contactEditDialog" style="display: none">
	     <div id="contactEditDiv" class="p10">
		     <form id="contactEdit_form" class="zui-form " method="post" enctype="multipart/form-data">
	        		<input id="contactId" name="contactId" type="hidden"/>
					<dl class="form-item">
						<dt class="title"><b class="c-red mr5">*</b>姓名</dt>
		            	<dd class="detail">
	                        <label> 
	                        	<input class="zui-input nwidth2 zui-validatebox" id="customerName" name="customerName" validate-type="Require">
							</label>
	                    </dd>
	                    <dd class="detail">
	                        <input class="zui-combobox" type="hidden" data-width="94" id="copyName" name="copyName">
	                    </dd>
                    </dl>
	            	<dl class="form-item">
	            		<dt class="title"><b class="c-red mr5">*</b>联系类型</dt>
		                <dd class="detail">
		                  		<input class="zui-combobox zui-validatebox" type="hidden" validate-type="Require"
		                         data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=c0115"

		                          data-callback="contactTypeChange"
		                          data-valuefield="fullcode" data-textfield="name" validate-type="Require"  name="contactType" id="contactType">
		                </dd>
	            	</dl>
	            	<dl class="form-item block">
	            		<dt class="title"><b class="c-red mr5">*</b>电话号码</dt>
		                <dd class="detail">
		                  <label> 
		                  	<input type="text" class="zui-input zui-validatebox" name="phoneNumber" id="phoneNumber" validate-type="Require,PhoneOrMobile"/>
		                  </label> 
		                </dd>
	            	</dl>
		   </form>
	     </div>
	</div>
	
	<script type="text/javascript">
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], 
			function($, CALLBACK) {
			
			// 添加联系方式的对话框
			$("#contactEditDialog").Zdialog({
			   width: 700, height: 200, closed: true, title: "联系方式",buttons: 
		       [
		           {
		               id: 'message-btn',
		               text: '确定',
		               buttonCls: 'btn-blue',
		               handler: function () {
		            	   var isValid = $.ZUI.validateForm($('#contactEditDiv'));
		            	   if (isValid) {
		            		   var isAdd = false;
		            		   var id = $("#contactId").val();
		            		   if (id == null || id == "") {
		            			   isAdd = true;
		            		   }
		            		   if (!isAdd) {//编辑
		            			   var arr=[];
		                           arr[0] =id;//index
		                           arr[1]={
		                        		   "customerName": $("#customerName").val(),
		                                   "contactType": $("#contactType").ZCombobox("getValue"),
		                                   "contactTypeNm": $("#contactType").ZCombobox("getText"),
		                                   "phoneNumber": $("#phoneNumber").val(),
		                                   "id": ""
		                                   };//行数据
		                           $('#contactWayInfoList').ZTable("editOneRow",arr);
		                           $("#contactEditDialog").Zdialog("close");
		                                   return;
		            		   }
		            		   var row =  {
	                        		   "customerName": $("#customerName").val(),
	                                   "contactType": $("#contactType").ZCombobox("getValue"),
	                                   "contactTypeNm": $("#contactType").ZCombobox("getText"),
	                                   "phoneNumber": $("#phoneNumber").val(),
	                                   "id": ""
	                                   };//行数据
		                   		$('#contactWayInfoList').ZTable("addOneRow",row);
		                   		$("#contactEditDialog").Zdialog("close");
		            	   }
		            	   
		               }
		           },
		           {
		               id: 'message-btn',
		               text: '取消',
		                  buttonCls: 'btn-gray',
		                  handler: function () {
		                    $("#contactEditDialog").Zdialog("close");
		                  }
		            }
		        ]
		   	});
			
			// 联系方式列表
			$('#contactWayInfoList').ZTable({
		       url : "<z:ukey key='com.zdsoft.finance.beforeContact.listContactByCustomerId' context='admin'/>&jsoncallback=?&customerId="+$("#mainCustomerId").val(),
		       singleSelect : true,
		       isRowNum : false,
		       pagination : false,
		       currentPage : 1,
		       idField: "id",
		       tableCls : 'table-index',//table的样式
		       columns:[[
		    	  	{field : 'customerName',title : '姓名',align : 'center'},
					{field : 'contactType',title : '联系类型code',align : 'center',hidden:true},//隐藏字段
					{field : 'contactTypeNm',title : '联系类型',align : 'center'},
					{field : 'phoneNumber',title : '电话号码', align : 'center'},
					{field : 'id',title : '操作', align : 'center',formatter:function(r,v){
						return '<a href="javaScript:void(0)" onclick="editContactHandle"><button class="btn-blue" type="button">编辑</button></a>&nbsp;&nbsp;<a href="javaScript:void(0)" onclick="deleteContactHandle"><button class="btn-blue" type="button" >删除</button></a>';
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
			CALLBACK.editContactHandle = function(index,rowData) {
				$("#contactId").val(index);
				// 如何更新一行的数据
				$('#contactType').ZCombobox('setValue',rowData.contactType);
				$('#phoneNumber').val(rowData.phoneNumber);
				$('#customerName').val(rowData.customerName);
				//组装复制name数据
				installName();
				$("#contactEditDialog").Zdialog('open');
			}
			//删除行
			CALLBACK.deleteContactHandle=function(index, rowData){
				//模拟点击选中事件
				$($('#contactWayInfoList .datagrid-body').find("tr")[0]).trigger("click");
				$('#contactWayInfoList').ZTable("deleteRow");
			}
			//新增联系方式弹窗
	        $('#andContactWayInfo').click(function () {
	        	$("#contactId").val("");
	        	//组装复制name数据
	        	installName();
	            $('#contactEditDialog').Zdialog("open");
	        });
			//组装复制name数据
			function installName(){
				//组装数据
	        	var client_customerName= $("#client_customerName").val();
	        	var spouse_customerName= $("#spouse_customerName").val();
	        	var jsonstr=[{"id": spouse_customerName, "text": spouse_customerName}, {"id": client_customerName, "text": client_customerName}];
	        	$("#copyName").ZCombobox({
	        		 valueField: "id",
	                 textField: "text",
	                 data: jsonstr,
	                 onSelect:function(selfVal){
	                	$("#customerName").val(selfVal);
	                 }

	        	});
			}
		  //联系类型
		  CALLBACK.contactTypeChange=function(value,text){
			  if(value==1){
			  	$("#phoneNumber").attr("validate-type","Require,Phone");
			  }else{
			  	$("#phoneNumber").attr("validate-type","Require,Mobile");
			  }
		  }
		});
	</script>
