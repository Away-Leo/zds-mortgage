<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<!-------------其他资产》房产信息--------------------->
<div class="page-box">
	<div class="p5">
		<div class="page-title">房产情况
		   <button type="button"  class="btn-blue" id="addCustomerHouseInfo" style="float:right;margin-right: 40px">新增</button>
		</div>
		<div class="p10">
		   <div id="customerHouseInfoList">
		   </div>
		</div>	
   </div>
</div>

<!-- 新增房产信息弹窗 -->
<div id="customerHouseEditDialog" style="display:none">
	<div id="customerHouseEditDiv" class="p20">
     <form id="customerHouseEdit_form" class="zui-form" method="post" enctype="multipart/form-data">
       	        <input id="caseApplyId" name="caseApplyId" value="${caseApplyId }" type="hidden"/>
        		<input id="customerHouseId" name="id" value="" type="hidden"/>
		 <dl class="form-item">
			<dt class="title"><b class="c-red mr5">*</b>面积</dt>
	         	<dd class="detail">
                    <label> 
                    	<input class="zui-input zui-validatebox" type="text" name="measureArea" id="measureArea" value="" validate-type="Require,Digital[10-2],MinSize[1]" ><front style="font-size: 14px;">㎡</front>
					</label>
               	</dd>
        </dl>
        <dl class="form-item">
       		<dt class="title"><b class="c-red mr5">*</b>价值</dt>
            <dd class="detail">
              <label>
              	<input class="zui-input zui-validatebox" type="text" id="worth" name="worth" value="" validate-type="Require,Digital[10-2],MinSize[1]" ><front style="font-size: 14px;">元</front>
              </label> 
            </dd>
       	</dl>
       	
        <dl class="form-item">
       		<dt class="title"><b class="c-red mr5">*</b>权属人</dt>
            <dd class="detail">
              <label> 
              	<input class="zui-input zui-validatebox" type="text" id="owner" name="owner" value="" validate-type="Require" >
              </label> 
            </dd>
       	</dl>
       	<dl class="form-item">
       		<dt class="title"><b class="c-red mr5">*</b>是否在押</dt>
            <dd class="detail">
              	<input class="zui-combobox zui-validatebox" type="hidden" value=""
                     data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0049"
                   data-valuefield="fullcode" data-textfield="name"  name="isPledge" validate-type="Require"  id="isPledge" data-callback="house_isPledgeChange">
            </dd>
       	</dl>
       	
		 <dl class="form-item dd" style="display: none">
			<dt class="title">抵押权人</dt>
	         	<dd class="detail">
                    <label> 
                    	<input class="zui-input zui-validatebox" type="text"  name="pledgePerson" id="pledgePerson" value="">
					</label>
               	</dd>
        </dl>
        <dl class="form-item dd" style="display: none">
       		<dt class="title">抵押金额</dt>
            <dd class="detail">
              <label> 
              	  <input class="zui-input zui-validatebox" type="text"  id="pledgeAmount" name="pledgeAmount"   value="" validate-type="Digital[10-2],MinSize[1]" ><front style="font-size: 14px;">元</front>
              </label> 
            </dd>
       	</dl>
       	
        <dl class="form-item dd" style="display: none">
       		<dt class="title">抵押期限</dt>
            <dd class="detail">
              	<input class="zui-input nwidth2 zui-validatebox" type="text" id="pledgeDeadLine" name="pledgeDeadLine" value="" validate-type="IsInteger,MinSize[1]" >
            </dd>
            <dd class="detail">
              	<input class="zui-combobox zui-validatebox" type="hidden" value="" data-width="94"
                     data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=0931" data-defaultvalue="0931001"
                   data-valuefield="fullcode" data-textfield="name"  name="pledgeDeadLineUnit" id="house_pledgeDeadLineUnit" data-toggle="combobox" >
            </dd>
       	</dl>
       	<dl class="form-item">
       		<dt class="title">房产性质</dt>
            <dd class="detail">
          		<input class="zui-combobox zui-validatebox" type="hidden" value=""
                   data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0057"
                   data-valuefield="fullcode" data-textfield="name"  name="houseProperty" id="houseProperty" data-toggle="combobox" data-callback="housePropertyChange">
            </dd>
       	</dl>
       	<dl class="form-item ddo">
       	</dl>
       	
       	<dl class="form-item">
       	   <dt class="title">地址</dt>
                 <dd class="detail">
                     <input type="hidden" id="province" name="province" value=""/>
                     <input type="hidden" id="city" name="city" value=""/>
                    <!--  <input type="hidden" id="county" name="county" value=""/> -->
                     <input type="hidden" id="district" name="district" value=""/>
                     <div id="selectAddress_house" data-code="">
                         <input id="address_house_text" class="zui-input zui-validatebox" type="text" readonly="true"/>
                     </div>
                 </dd>
                 <dd class="detail">
                     <input class="zui-input zui-validatebox" validate-type="Length[0-128]" value="" id="address" name="address" style="width: 215px;">
                 </dd>
        </dl>
        
     </form>
    </div> 
</div>
	
<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.table','zd/jquery.zds.address'], 
		function($, CALLBACK, ZTOOLS) {
			var industryTypeArray={};
			
			// 添加房产的对话框
			$("#customerHouseEditDialog").Zdialog({
			   width: 800, height: 450, closed: true, title: "房产",buttons: 
		       [
		           {
		               id: 'message-btn',
		               text: '确定',
		               buttonCls: 'btn-blue',
		               handler: function () {
		            	   var isValid = $.ZUI.validateForm($('#customerHouseEditDiv'));
		            	   if (isValid) {
				           		var customerHouseVo = $("#customerHouseEdit_form").serialize();
				        		var params = customerHouseVo;
				        		//保存案件预约
				        		$.ajax({
				                    type: 'post',
				                    url: '<z:ukey key="com.zdsoft.finance.casemanage.promotion.otherproperty.saveCustomerHouse" context="admin"/>',
				                    data: params,
				                    dataType: 'json',
				                    success: function (data) {
				                        if (data.resultStatus == 0) {
		                         			$("#customerHouseEditDialog").Zdialog("close");
				                        	$.ZMessage.success("提示", "房产保存成功", function () {
				                         		setTimeout(function(){
				                         			$("#customerHouseInfoList").ZTable("reload",{});
				                                 },200);
				                          	 });
				                        }else{
				                        	$.ZMessage.error("错误", data.msg, function () {
				                            });
				                        }
				                    },
				        	            error: function () {
				        	            	$.ZMessage.error("错误", "兴业贷资调其他资产之房产保存异常，请联系管理员", function () {
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
		                    $("#customerHouseEditDialog").Zdialog("close");
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
			
			// 房产情况列表
			$('#customerHouseInfoList').ZTable({
		       url : "<z:ukey key='com.zdsoft.finance.casemanage.promotion.getCustomerHouseList' context='admin'/>&jsoncallback=?&caseApplyId=${caseApplyId}",
		       singleSelect : true,
		       isRowNum : true,
		       rows : 5,
		       pagination : true,
		       currentPage : 1,
		       idField: "ID",
		       tableCls : 'table-index',//table的样式
		       columns:[[
		    	  	{field : 'OWNER',title : '权属人',align : 'center'},
					{field : 'HOUSEPROPERTY',title : '房产性质',align : 'center',hidden:true},
					{field : 'HOUSEPROPERTYNM',title : '房产性质',align : 'center',formatter:function(rowData,v){if(!v){return ""} return v}},
					{field : 'ISPLEDGE',title : '是否在押',align : 'center',hidden:true},
					{field : 'ISPLEDGENM',title : '是否在押',align : 'center'},
					{field : 'PLEDGEPERSON',title : '抵押权人', align : 'center',formatter:function(rowData,v){if(!v){return ""} return v}},
					{field : 'PLEDGEAMOUNT',title : '抵押金额(元)', align : 'center',formatter:formatterAmount},
					{field : 'PLEDGEDEADLINES',title : '抵押期限', align : 'center',hidden:true},
					{field : 'PLEDGEDEADLINEUNIT',title : '抵押期限单位', align : 'center',hidden:true},
					{field : 'PLEDGEDEADLINEALL',title : '抵押期限', align : 'center'},
					{field : 'MEASUREAREA',title : '面积', align : 'center'},
					{field : 'WORTH',title : '价值(元)', align : 'center',formatter:formatterAmount},
	 				{field : 'PROVINCE',title : '省code', align : 'center',hidden:true},
					{field : 'CITY',title : '市code', align : 'center',hidden:true},
					{field : 'DISTRICT',title : '区code', align : 'center',hidden:true},
					{field : 'ADDRESS',title : '地址', align : 'center',hidden:true},
					{field : 'HOUSEADDRESS',title : '地址', align : 'center'},
					{field : 'ID',title : '操作', align : 'center',width:'20%',formatter:function(r,v){
						return '<a href="javaScript:void(0)" onclick="editCustomerHouseHandle" class="btn-blue" >编辑</a>&nbsp;&nbsp;<a href="javaScript:void(0)" onclick="deleteCustomerHouseHandle" class="btn-blue">删除</a>';
					}}
				] ],
				onDelete:function(index, rowData) {
					//  添加判断
					return true;
				},
				onLoadSuccess:function(index, rowData) {
				}
			
			});
			
			//编辑
			CALLBACK.editCustomerHouseHandle = function(index,rowData) {
	  	        $("#customerHouseId").val(rowData.ID);
		        $("#measureArea").val(rowData.MEASUREAREA);
		        $("#worth").val(rowData.WORTH);
		        $("#owner").val(rowData.OWNER);
		        $("#isPledge").ZCombobox('setValue',rowData.ISPLEDGE);
		        $("#pledgePerson").val(rowData.PLEDGEPERSON);
		        $("#pledgeAmount").val(rowData.PLEDGEAMOUNT);
		        $("#pledgeDeadLine").val(rowData.PLEDGEDEADLINE);
			    $("#house_pledgeDeadLineUnit").ZCombobox('setValue',rowData.PLEDGEDEADLINEUNIT);
			    $("#houseProperty").ZCombobox('setValue',rowData.HOUSEPROPERTY);
		        $("#province").val(rowData.PROVINCE);
		        $("#city").val(rowData.CITY);
		        $("#district").val(rowData.DISTRICT);
		        $("#selectAddress_house").data("code",rowData.DISTRICT);
		        $("#address").val(rowData.ADDRESS);

				//重新加载城市下拉
                $("#selectAddress_house").setAddress({
                    showStreet:false,//不显示街道
                    cityUrl:cityUrl,//真实数据源
                    getDataCityUrl:getDataCityUrl,//根据子节点取同级及上级的数据
                    data:rowData.DISTRICT,
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
                        $('#address_house_text').val(str);
                        $("#province").val(selected_ids[0]);
                        $("#city").val(selected_ids[1]);
                        $("#district").val(selected_ids[2]);
                    }
                });
		        
	            $("#customerHouseEditDialog").Zdialog('open');
				
				
			};
			
			//删除
			CALLBACK.deleteCustomerHouseHandle=function(index, rowData){
	    		var params = "&customerHouseId="+rowData.ID;
				
	    		$.ajax({
	                type: 'post',
	                url: '<z:ukey key="com.zdsoft.finance.casemanage.promotion.otherproperty.deleteCustomerHouse" context="admin"/>',
	                data: params,
	                dataType: 'json',
	                success: function (data) {
	                    if (data.resultStatus == 0) {
	                    	$.ZMessage.success("提示", "删除房产成功", function () {
	                     		setTimeout(function(){
	                     			$("#customerHouseInfoList").ZTable("reload",{});
	                             },200);

	                      	 });
	                    }else{
	                    	$.ZMessage.error("错误", data.msg, function () {
	                        });
	                    }
	                },
	    	            error: function () {
	    	            	$.ZMessage.error("错误", "兴业贷资调其他资产之房产删除异常，请联系管理员", function () {
	    	             });
	    	            }
	            });
				
			};
			
			//新增房产弹窗
	        $('#addCustomerHouseInfo').click(function () {
	        	$("#customerHouseId").val("");
	        	$("#customerHouseEditDialog").Zdialog('open');
	        });
			
			
	
		//初始选择器
        $("#selectAddress_house").Address({
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
                $('#address_house_text').val(str);
                $("#province").val(selected_ids[0]);
                $("#city").val(selected_ids[1]);
                $("#district").val(selected_ids[2]);
            }
        });
		
		// 下拉回调
 		CALLBACK.house_isPledgeChange = function(v,t){
			houseChangeValue(v);
		};
		// 初始化值
		$(function(){
			var v = $('#isPledge').val();
			houseChangeValue(v);
		});
		//隐藏或显示房产是否抵押及情况
		function houseChangeValue(v){
			if("YWDM0049002"==v){
				$(".dd").show();
				$(".ddo").hide();
			}else{
				$(".dd").hide();
				$(".ddo").show();
			}
			
		}

		$.ZUI.initForms("#customerHouseEditDiv");
	}); 
</script>