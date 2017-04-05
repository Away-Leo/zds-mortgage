<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>    
<!-------------客户信息》工作单位信息---------------------------------------->
<div class="page-box">
  <div class="page-title">工作单位信息
  	<button type="button"  class="btn-blue" id="andWorkUnitInfo" style="float:right;margin-right: 40px">新增</button>
  </div>
  <div class="p5">
      <div id="workUnitList">
      </div>
  </div>
</div>

<!-- 新增工作单位信息弹窗 -->
<div id="workUnitInfoEdit" style="display:none">
	<div id="workUnitEditDiv" class="p10">
     <form id="workUnit_form" class="zui-form" method="post" enctype="multipart/form-data">
       	 <input id="workUnitId" name="workUnitId" type="hidden"/>
		 <dl class="form-item">
			<dt class="title"><b class="c-red mr5">*</b>姓名</dt>
	         	<dd class="detail">
                    <label> 
                    	<input class="zui-input nwidth2 zui-validatebox" id="workUnitName" name="workUnitName" validate-type="Require">
					</label>
               	</dd>
                <dd class="detail">
                    <input  type="hidden" data-width="94" id="copyWorkUnitName" name="copyWorkUnitName">
                </dd>
              </dl>
              <dl class="form-item">
       		<dt class="title"><b class="c-red mr5">*</b>工作单位名称</dt>
            <dd class="detail">
              <label> 
              	<input type="text" class="zui-input zui-validatebox" name="companyName" id="companyName" validate-type="Require"/>
              </label> 
            </dd>
       	</dl>
       	
       	<dl class="form-item">
       		<dt class="title"><b class="c-red mr5">*</b>行业类型</dt>
            <dd class="detail">
              		<input class="zui-combobox zui-validatebox" type="hidden"
                        data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0021" validate-type="Require"
                      data-valuefield="fullcode" data-textfield="name"  name="industryType" id="industryType"  data-callback="industryChange">
            </dd>
       	</dl>  
       	<dl class="form-item">
       		<dt class="title"><b class="c-red mr5">*</b>行业</dt>
            <dd class="detail">
              		<input class="zui-combobox zui-validatebox" type="hidden" validate-type="Require" 
                       name="industry" id="industry" >
            </dd>
       	</dl>
       	<dl class="form-item">
       		<dt class="title"><b class="c-red mr5">*</b>单位性质</dt>
            <dd class="detail">
              	<input class="zui-combobox zui-validatebox" type="hidden" validate-type="Require"
                      data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00118"
                      data-valuefield="fullcode" data-textfield="name" validate-type="Require"  name="workUnitNature" id="workUnitNature">
            </dd>
       	</dl>
       	<dl class="form-item">
       		<dt class="title"><b class="c-red mr5">*</b>工作年限</dt>
            <dd class="detail">
              <label> 
              	<input type="text" class="zui-input zui-validatebox toInt" name="workYears" id="workYears" validate-type="Require,Integer,MaxSize[100]"/><front style="font-size: 14px;">年</front>
              </label> 
            </dd>
       	</dl>
       	<dl class="form-item">
       		<dt class="title"><b class="c-red mr5">*</b>职务</dt>
            <dd class="detail">
              		<input class="zui-combobox zui-validatebox" type="hidden" validate-type="Require"
                      data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0088"
                      data-valuefield="fullcode" data-textfield="name" validate-type="Require"  name="position" id="position">
            </dd>
       	</dl>
       	<dl class="form-item ">
       		<dt class="title">工作单位电话</dt>
            <dd class="detail">
              <label> 
              	<input type="text" class="zui-input zui-validatebox" name="phoneNumber" id="workPhoneNumber" validate-type="Number,Length[6-13]"/>
              </label>
            </dd>
       	</dl>
       	<dl class="form-item">
       	   <dt class="title">单位地址</dt>
                 <dd class="detail">
                     <input type="hidden" id="workUnitProvince" name="workUnitProvince" value=""/>
                     <input type="hidden" id="workUnitCity" name="workUnitCity" value=""/>
                     <input type="hidden" id="workUnitCounty" name="workUnitCounty" value=""/>
                     <div id="selectAddress_workUnit" data-code="">
                         <input id="address_workUnit_text" class="zui-input zui-validatebox" type="text" readonly="true"/>
                     </div>
                 </dd>
                 <dd class="detail">
                     <input class="zui-input zui-validatebox" validate-type="Length[0-128]" value="" id="workUnitDetailed" name="workUnitDetailed" style="width: 215px;">
                 </dd>
                 <dd class="detail">
                 		<input class="zui-combobox" type="hidden"
                             data-data="[{'id':'liveCounty','text':'复制户籍地址'},{'id':'homeDistrict','text':'复制家庭地址'}]"
                             data-valuefield="id" data-callback="workUnitCopyAddressValue"
                             data-textfield="text"   data-width="94"
                       >
                 </dd>
             </dl>
     </form>
    </div> 
</div>
	
<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.dialog','zd/jquery.zds.table','zd/jquery.zds.address'], 
		function($, CALLBACK) {
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
			//行业级联
			CALLBACK.industryChange = function(v,t){
				 $("#industry").ZCombobox({
           		 	valueField: "fullcode",
                    textField: "name",
                    data: industryTypeArray[v]
           		}); 
			}
			// 添加工作单位对话框
			$("#workUnitInfoEdit").Zdialog({
			   width: 700, height: 400, closed: true, title: "工作单位信息",buttons: 
		       [
		           {
		               id: 'message-btn',
		               text: '确定',
		               buttonCls: 'btn-blue',
		               handler: function () {
		            	   var isValid = $.ZUI.validateForm($('#workUnit_form'));
		            	   if (isValid) {
		            		   var isAdd = false;
		            		   var id = $("#workUnitId").val();
		            		   if (id == null || id == "") {
		            			   isAdd = true;
		            		   }
		            		   if (!isAdd) {//编辑
		            			   var arr=[];
		                           arr[0] =id;//index
		                           arr[1]={
		                        		   "workUnitName": $("#workUnitName").val(),
		                        		   "companyName": $("#companyName").val(),
		                                   "industryType": $("#industryType").ZCombobox("getValue"),
		                                   "industryTypeName": $("#industryType").ZCombobox("getText"),
		                                   "industry": $("#industry").ZCombobox("getValue"),
		                                   "industryName": $("#industry").ZCombobox("getText"),
		                                   "workUnitNature": $("#workUnitNature").ZCombobox("getValue"),
		                                   "workUnitNatureName": $("#workUnitNature").ZCombobox("getText"),
		                                   "position": $("#position").ZCombobox("getValue"),
		                                   "positionName": $("#position").ZCombobox("getText"),
		                                   "workYears": $("#workYears").val(),
		                                   "phoneNumber": $("#workPhoneNumber").val(),
		                                   "workUnitAddressName": $("#address_workUnit_text").val()+"/"+$("#workUnitDetailed").val(),
		                                   "province": $("#workUnitProvince").val(),
		                                   "city": $("#workUnitCity").val(),
		                                   "district": $("#workUnitCounty").val(),
		                                   "workUnitAddress": $("#workUnitDetailed").val(),
		                                   "id": ""
		                                   };//行数据
		                           $('#workUnitList').ZTable("editOneRow",arr);
		                           $("#workUnitInfoEdit").Zdialog("close");
		                           return;
		            		   }
		            		   var row =  {
		            				   "workUnitName": $("#workUnitName").val(),
	                        		   "companyName": $("#companyName").val(),
	                                   "industryType": $("#industryType").ZCombobox("getValue"),
	                                   "industryTypeName": $("#industryType").ZCombobox("getText"),
	                                   "industry": $("#industry").ZCombobox("getValue"),
	                                   "industryName": $("#industry").ZCombobox("getText"),
	                                   "workUnitNature": $("#workUnitNature").ZCombobox("getValue"),
	                                   "workUnitNatureName": $("#workUnitNature").ZCombobox("getText"),
	                                   "position": $("#position").ZCombobox("getValue"),
	                                   "positionName": $("#position").ZCombobox("getText"),
	                                   "workYears": $("#workYears").val(),
	                                   "phoneNumber": $("#workPhoneNumber").val(),
	                                   "workUnitAddressName": $("#address_workUnit_text").val()+"/"+$("#workUnitDetailed").val(),
	                                   "province": $("#workUnitProvince").val(),
	                                   "city": $("#workUnitCity").val(),
	                                   "district": $("#workUnitCounty").val(),
	                                   "workUnitAddress": $("#workUnitDetailed").val(),
	                                   "id": ""
	                                   };//行数据
		                   		$('#workUnitList').ZTable("addOneRow",row);
		                   		$("#workUnitInfoEdit").Zdialog("close");
		            	   }
		            	   
		               }
		           },
		           {
		               id: 'message-btn',
		               text: '取消',
		                  buttonCls: 'btn-gray',
		                  handler: function () {
		                    $("#workUnitInfoEdit").Zdialog("close");
		                  }
		            }
		        ]
		   	});
			
			// 工作单位列表
			$('#workUnitList').ZTable({
		       url : "<z:ukey key='com.zdsoft.finance.beforeWorkUnit.listWorkUnitByCustomerId' context='admin'/>&jsoncallback=?&customerId="+$("#mainCustomerId").val(),
		       singleSelect : true,
		       isRowNum : false,
		       pagination : false,
		       currentPage : 1,
		       idField: "id",
		       tableCls : 'table-index',//table的样式
		       columns:[[
		    	  	{field : 'workUnitName',title : '姓名',align : 'center'},
					{field : 'companyName',title : '工作单位名称',align : 'center'},
					{field : 'industryType',title : '行业类型code',align : 'center',hidden:true},//隐藏字段
					{field : 'industryTypeName',title : '行业类型',align : 'center'},
					{field : 'industry',title : '行业类型code',align : 'center',hidden:true},//隐藏字段
					{field : 'industryName',title : '行业',align : 'center'},
					{field : 'workUnitNature',title : '单位性质code', align : 'center',hidden:true},
					{field : 'workUnitNatureName',title : '单位性质', align : 'center'},
					{field : 'position',title : '职务code', align : 'center',hidden:true},
					{field : 'positionName',title : '职务', align : 'center'},
					{field : 'workYears',title : '工作年限（年）', align : 'center'},
					{field : 'phoneNumber',title : '工作单位电话', align : 'center'},
					{field : 'workUnitAddressName',title : '单位地址', align : 'center'},
					{field : 'province',title : '省code', align : 'center',hidden:true},
					{field : 'city',title : '市code', align : 'center',hidden:true},
					{field : 'district',title : '县code', align : 'center',hidden:true},
					{field : 'workUnitAddress',title : '详细地址', align : 'center',hidden:true},
					{field : 'id',title : '操作', align : 'center',width:'15%',formatter:function(r,v){
						return '<a href="javaScript:void(0)" onclick="editWorkUnitHandle" class="btn-blue">编辑</a>&nbsp;&nbsp;<a href="javaScript:void(0)" onclick="deleteWorkUnitHandle" class="btn-blue">删除</a>';
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
			CALLBACK.editWorkUnitHandle = function(index,rowData) {
				$("#workUnitId").val(index);
				// 如何更新一行的数据
				$('#workUnitName').val(rowData.workUnitName);
				$('#companyName').val(rowData.companyName);
				$('#industryType').ZCombobox('setValue',rowData.industryType);
				$('#industry').ZCombobox('setValue',rowData.industry);
				$('#workUnitNature').ZCombobox('setValue',rowData.workUnitNature);
				$('#position').ZCombobox('setValue',rowData.position);
				$('#workYears').val(rowData.workYears);
				$('#workPhoneNumber').val(rowData.phoneNumber);
				$('#workUnitProvince').val(rowData.province);
				$('#workUnitCity').val(rowData.city);
				$('#workUnitDistrict').val(rowData.district);
				$('#workUnitDetailed').val(rowData.workUnitAddress);
				$("#selectAddress_workUnit").data("code",rowData.district);
				
				//重新加载城市下拉
                $("#selectAddress_workUnit").setAddress({
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
                        $('#address_workUnit_text').val(str);
                        $("#workUnitProvince").val(selected_ids[0]);
                        $("#workUnitCity").val(selected_ids[1]);
                        $("#workUnitCounty").val(selected_ids[2]);
                    }
                });
				//组装复制name数据
				installWorkUnitName();
				$("#workUnitInfoEdit").Zdialog('open');
			}
			//删除行
			CALLBACK.deleteWorkUnitHandle=function(index, rowData){
				$.ZMessage.question("确认", "确认要删除这条数据吗？", function () {
					//模拟点击选中事件
					$($('#workUnitList .datagrid-body').find("tr")[index]).trigger("click");
					$('#workUnitList').ZTable("deleteRow");
				});
			}
			
			//新增工作单位弹窗
	        $('#andWorkUnitInfo').click(function () {
	        	$("#workUnitId").val("");
	        	//组装复制name数据
	        	installWorkUnitName();
	            $('#workUnitInfoEdit').Zdialog("open");
	        });
			
			//组装复制name数据
			function installWorkUnitName(){
				//组装数据
				var jsonstr=[];
				var client_customerName= $("#client_customerName").val();
	        	var spouse_customerName= $("#spouse_customerName").val();
	        	if(client_customerName != ""){
	        		jsonstr.unshift({"id": client_customerName, "text": client_customerName});
	        	}
	        	if(spouse_customerName != ""){
	        		jsonstr.unshift({"id": spouse_customerName, "text": spouse_customerName});
	        	}
	        	$("#copyWorkUnitName").val("");
	        	$("#copyWorkUnitName").ZCombobox({
	        		 valueField: "id",
	                 textField: "text",
	                 data: jsonstr,
	                 onSelect:function(selfVal){
	                	$("#workUnitName").val(selfVal);
	                 }
	        	});
			}
			
		//初始选择器
        $("#selectAddress_workUnit").Address({
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
                $('#address_workUnit_text').val(str);
                $("#workUnitProvince").val(selected_ids[0]);
                $("#workUnitCity").val(selected_ids[1]);
                $("#workUnitCounty").val(selected_ids[2]);
            }
        });
		
		//复制地址
        CALLBACK.workUnitCopyAddressValue=function(v,t){
        	//获取对象的值
        	var objValue=$('#'+v).val();
            if(null!=objValue&&objValue!=""){
            	//赋值街道详细地址
            	if(v="liveCounty"){
            		$("#workUnitDetailed").val($('#liveAddr').val());
            	}else if(v="homeDistrict"){
            		$("#workUnitDetailed").val($('#homePlace').val());
            	}
                //用这种方式复制选择项
                $("#selectAddress_workUnit").setAddress({
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
                        //显示中文
                        $('#address_workUnit_text').val(str);
                        $("#workUnitProvince").val(selected_ids[0]);
                        $("#workUnitCity").val(selected_ids[1]);
                        $("#workUnitCounty").val(selected_ids[2]);
                    }
                });
                //复制地址后  自动关闭选择框(JS生成规则=====city-select_+id)
                $("#city-select_selectAddress_workUnit").css("display", "none");
            }
        }
        $.ZUI.initForms("#workUnitEditDiv");
	}); 
</script>