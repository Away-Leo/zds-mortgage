<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>    
<!-------------其他资产》设备信息---------------->
<div class="page-box">
	<div class="p5">
		<div class="page-title">设备情况
		   <button type="button"  class="btn-blue" id="addAssetsDeviceInfo" style="float:right;margin-right: 40px">新增</button>
		</div>
		<div class="p10">
		   <div id="assetsDeviceInfoList">
		   </div>
		</div>
   </div>
</div>

<!-- 新增设备信息弹窗 -->
<div id="assetsDeviceEditDialog" style="display:none">
	<div id="assetsDeviceEditDiv" class="p20">
     <form id="assetsDeviceEdit_form" class="zui-form" method="post" enctype="multipart/form-data">
       	        <input id="caseApplyId" name="caseApplyId" value="${caseApplyId }" type="hidden"/>
        		<input id="assetsDeviceId" name="id" value="" type="hidden"/>
		 <dl class="form-item">
			<dt class="title"><b class="c-red mr5">*</b>设备名称</dt>
	         	<dd class="detail">
                    <label> 
                    	<input class="zui-input zui-validatebox" type="text" name="deviceName" id="device_deviceName" value="" validate-type="Require">
					</label>
               	</dd>
        </dl>
        <dl class="form-item">
       		<dt class="title"><b class="c-red mr5">*</b>设备内部估价</dt>
            <dd class="detail">
              <label> 
              	<input class="zui-input zui-validatebox" type="text" id="device_evaluationAmount" name="evaluationAmount" value="" validate-type="Require,Digital[10-2],MinSize[1]"><front style="font-size: 14px;">元</front>
              </label> 
            </dd>
       	</dl>
       	
        <dl class="form-item">
       		<dt class="title">设备发票出具日期</dt>
            <dd class="detail">
              <label> 
              		<input type="text" class="zui-date strToDate  zui-validatebox" id="isInvoiceDate" value="" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'device_invoiceDate',dateFmt:'yyyy-MM-dd',maxDate:new Date() })" readonly>
				    	<input type="hidden" id="device_invoiceDate" name="invoiceDate" value="" />
              </label> 
            </dd>
       	</dl>
		 <dl class="form-item">
			<dt class="title"><b class="c-red mr5">*</b>设备权属人</dt>
	         	<dd class="detail">
                    <label> 
                    	<input class="zui-input zui-validatebox" type="text"  name="ownerName" id="device_ownerName" value="" validate-type="Require">
					</label>
               	</dd>
        </dl>
        
       	<dl class="form-item">
       		<dt class="title"><b class="c-red mr5">*</b>是否在押</dt>
            <dd class="detail">
              	<input class="zui-combobox zui-validatebox" type="hidden" value="" validate-type="Require"
                     data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0049"
                   data-valuefield="fullcode" data-textfield="name"  name="isPledge" id="device_isPledge" data-callback="device_isPledgeChange">
            </dd>
       	</dl>
        <dl class="form-item cc" style="display: none;">
       		<dt class="title">抵押金额</dt>
            <dd class="detail">
              <label> 
              	  <input class="zui-input zui-validatebox" type="text"  id="device_pledgeAmount" name="pledgeAmount" value=""  validate-type="Digital[10-2],MinSize[1]" ><front style="font-size: 14px;">元</front>
              </label> 
            </dd>
       	</dl>
        <dl class="form-item cco" style="display: none;">
       	</dl>
       	
       	<dl class="form-item">
       	   <dt class="title">设备所在地</dt>
                 <dd class="detail">
                     <input type="hidden" id="device_province" name="deviceProvince" value=""/>
                     <input type="hidden" id="device_city" name="deviceCity" value=""/>
                     <input type="hidden" id="device_district" name="deviceDistrict" value=""/>
                     <div id="selectAddress_device" data-code="">
                         <input id="address_device_text" class="zui-input zui-validatebox" type="text" readonly="true"/>
                     </div>
                 </dd>
                 <dd class="detail">
                     <input class="zui-input zui-validatebox" validate-type="Length[0-128]" value="" id="device_detailAddress" name="detailAddress" style="width: 215px;">
                 </dd>
        </dl>
        
     </form>
    </div> 
</div>
	
<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.table','zd/jquery.zds.address', 'datepicker'], 
		function($, CALLBACK, ZTOOLS) {
			$(".cco").hide();
			var industryTypeArray={};
			$.ajax({
                async: false,
                url: "<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0021",
                dataType: "json",
                success: function (data) {  
                 	if(data){
                		 for(var i=0;i<data.length;i++){
                			var children = data[i].children;
                			industryTypeArray[data[i].fullcode] = children; 
                		 }
                	} 
                },
                error: function () {
                    alert("请求错误,获取城市下拉数据失败!");
                }
            });
			
			
			// 添加设备的对话框
			$("#assetsDeviceEditDialog").Zdialog({
			   width: 800, height: 450, closed: true, title: "设备",buttons: 
		       [
		           {
		               id: 'message-btn',
		               text: '确定',
		               buttonCls: 'btn-blue',
		               handler: function () {
		            	   var isValid = $.ZUI.validateForm($('#assetsDeviceEditDiv'));
		            	   if (isValid) {
			           		var assetsDevice = $("#assetsDeviceEdit_form").serialize();
			        		var params = assetsDevice;
			        		//保存设备
			        		$.ajax({
			                    type: 'post',
			                    url: '<z:ukey key="com.zdsoft.finance.casemanage.promotion.otherproperty.saveAssetsDevice" context="admin"/>',
			                    data: params,
			                    dataType: 'json',
			                    success: function (data) {
			                        if (data.resultStatus == 0) {
			                        	$.ZMessage.success("提示", "保存设备成功", function () {
			                         		setTimeout(function(){
			                         			$("#assetsDeviceEditDialog").Zdialog("close");
			                         			$("#assetsDeviceInfoList").ZTable("reload",{});
			                                 },200);
		
			                          	 });
			                        }else{
			                        	$.ZMessage.error("错误", data.msg, function () {
			                            });
			                        }
			                    },
			        	            error: function () {
			        	            	$.ZMessage.error("错误", "兴业贷资调其他资产之设备保存异常，请联系管理员", function () {
			        	             });
			        	            }
			                }); 
		            	   
		               }
		               }
		           },
		           {
		               id: 'message-btn',
		               text: '取消',
		                  buttonCls: 'btn-gray',
		                  handler: function () {
		                    $("#assetsDeviceEditDialog").Zdialog("close");
		                  }
		            }
		        ]
		   	});
			//金额千分位
			var formatterAmount = function(r,value){
				if(value){
					return ZTOOLS.formatCurrency(value+"");
				}  
				return '';
			}
			
			// 设备情况列表
			$('#assetsDeviceInfoList').ZTable({
		       url : "<z:ukey key='com.zdsoft.finance.casemanage.promotion.getAssetsDeviceList' context='admin'/>&jsoncallback=?&caseApplyId=${caseApplyId}",
		       singleSelect : true,
		       isRowNum : true,
		       rows : 5,
		       pagination : true,
		       currentPage : 1,
		       idField: "ID",
		       tableCls : 'table-index',//table的样式
		       columns:[[
		    	  	{field : 'DEVICENAME',title : '设备名称',align : 'center'},
					{field : 'EVALUATIONAMOUNT',title : '设备内部估价(元)',align : 'center',formatter:formatterAmount},
					{field : 'INVOICEDATE',title : '设备发票出具日期',align : 'center',hidden:true},
					{field : 'INVOICEDATEFMT',title : '设备发票出具日期',align : 'center'},
					{field : 'OWNERNAME',title : '设备权属人',align : 'center'},
					{field : 'ISPLEDGE',title : '是否在押',align : 'center',hidden:true},
					{field : 'ISPLEDGENM',title : '是否在押',align : 'center'},
					{field : 'PLEDGEAMOUNT',title : '抵押金额(元)', align : 'center',formatter:formatterAmount},
	 				{field : 'DEVICEPROVINCE',title : '省code', align : 'center',hidden:true},
					{field : 'DEVICECITY',title : '市code', align : 'center',hidden:true},
					{field : 'DEVICEDISTRICT',title : '区code', align : 'center',hidden:true},
					{field : 'DETAILADDRESS',title : '详细地址', align : 'center',hidden:true},
					{field : 'DEVICEADDRESS',title : '设备所在地', align : 'center'},
					{field : 'ID',title : '操作', align : 'center',width:'20%',formatter:function(r,v){
						return '<a href="javaScript:void(0)" onclick="editAssetsDeviceHandle" class="btn-blue" >编辑</a>&nbsp;&nbsp;<a href="javaScript:void(0)" onclick="deleteAssetsDeviceHandle" class="btn-blue">删除</a>';
					}}
			] ],
			onDelete:function(index, rowData) {
				//  添加判断
				return true;
			},
			onLoadSuccess:function() {
			}
			});
			
			//编辑
			CALLBACK.editAssetsDeviceHandle = function(index,rowData) {
	  	        $("#assetsDeviceId").val(rowData.ID);
		        $("#device_deviceName").val(rowData.DEVICENAME);
		        $("#device_evaluationAmount").val(rowData.EVALUATIONAMOUNT);
		        $("#device_ownerName").val(rowData.OWNERNAME);
		        $("#device_isPledge").ZCombobox('setValue',rowData.ISPLEDGE);
		        $("#device_pledgeAmount").val(rowData.PLEDGEAMOUNT);
		        $("#device_province").val(rowData.DEVICEPROVINCE);
		        $("#device_city").val(rowData.DEVICECITY);
		        $("#device_district").val(rowData.DEVICEDISTRICT);
		        $("#selectAddress_device").data("code",rowData.DEVICEDISTRICT);
		        $("#device_detailAddress").val(rowData.DETAILADDRESS);

	       		var invoiceDate = $("#device_invoiceDate").val(rowData.INVOICEDATE);
		        if(invoiceDate){
			        $("#isInvoiceDate").val(rowData.INVOICEDATEFMT);
		        } 
		        
				//重新加载城市下拉
                $("#selectAddress_device").setAddress({
                    showStreet:false,//不显示街道
                    cityUrl:cityUrl,//真实数据源
                    getDataCityUrl:getDataCityUrl,//根据子节点取同级及上级的数据
                    data:rowData.DEVICEDISTRICT,
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
                        $('#address_device_text').val(str);
                        $("#device_province").val(selected_ids[0]);
                        $("#device_city").val(selected_ids[1]);
                        $("#device_district").val(selected_ids[2]);
                    }
                });
		        
	            $("#assetsDeviceEditDialog").Zdialog('open');
				
			};
			
			//删除
			CALLBACK.deleteAssetsDeviceHandle=function(index, rowData){
	    		var params = "&assetsDeviceId="+rowData.ID;
				
	    		$.ajax({
	                type: 'post',
	                url: '<z:ukey key="com.zdsoft.finance.casemanage.promotion.otherproperty.deleteAssetsDevice" context="admin"/>',
	                data: params,
	                dataType: 'json',
	                success: function (data) {
	                    if (data.resultStatus == 0) {
	                    	$.ZMessage.success("提示", "删除设备成功", function () {
	                     		setTimeout(function(){
	                     			$("#assetsDeviceInfoList").ZTable("reload",{});
	                             },200);

	                      	 });
	                    }else{
	                    	$.ZMessage.error("错误", data.msg, function () {
	                        });
	                    }
	                },
	    	            error: function () {
	    	            	$.ZMessage.error("错误", "兴业贷资调其他资产之设备删除异常，请联系管理员", function () {
	    	             });
	    	            }
	            });
				
			};
			
			//新增设备弹窗
	        $('#addAssetsDeviceInfo').click(function () {
	        	$("#assetsDeviceId").val("");
	        	$("#assetsDeviceEditDialog").Zdialog('open');
	        });
			
			
	
		//初始选择器
        $("#selectAddress_device").Address({
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
                $('#address_device_text').val(str);
                $("#device_province").val(selected_ids[0]);
                $("#device_city").val(selected_ids[1]);
                $("#device_district").val(selected_ids[2]);
            }
        });
		
		// 下拉回调
 		CALLBACK.device_isPledgeChange = function(v,t){
 			deviceChangeValue(v);
		};
		// 初始化值
		$(function(){
			var v = $('#device_isPledge').val();
			deviceChangeValue(v);
		});
		//隐藏或显示设备抵押金额
		function deviceChangeValue(v){
			if("YWDM0049002"==v){
				$(".cc").show();
				$(".cco").hide();
			}else{
				$(".cc").hide();
				$(".cco").show();
			}
		}
	
        $.ZUI.initForms("#assetsDeviceEditDiv");
	}); 
</script>