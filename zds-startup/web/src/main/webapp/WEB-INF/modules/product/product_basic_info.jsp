<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../common/common_js.jsp" %>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>基本信息</title>
</head>
<body>
<div class="save">
    <button id="back" type="button" class="btn-blue">返回</button>
    <button id="save" type="button" class="btn-blue mr20">保存</button>
</div>
<div id="basicInfo" class="frm-content frm-bottom">
    <form id="basicInfoForm" class="zui-form" action="javascript:void(0);"
          zdata-options="{}">
    	<div class="page-box">
	        <h1 class="page-title">基本信息</h1>
	        <div class="p10">
	        	<input type="hidden" id="id" name="id" value="${product.id}">
	        	<input type="hidden" name="categoryId" value="${category.id}">
                <dl class="form-item">
                    <dt class="title">产品分类:</dt>
                    <dd class="detail">
                        <label>
                            <input class="zui-input zui-disabled zui-validatebox" type="text" value="${category.name}"
                                   validate-type="Require" disabled/>
                        </label>
                    </dd>
                </dl>

                <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>产品:</dt>
                    <dd class="detail">
                        <label>
                            <input class="zui-input zui-validatebox" name="productName" type="text" value="${product.productName }"
                                   validate-type="Require,Length[1-32]"/>
                        </label>
                    </dd>
                </dl>

                <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>是否有效:</dt>
                    <dd class="detail">
                        <input class="zui-combobox zui-validatebox" type="hidden"
                               data-data="[{'id':'true','text':'是'},{'id':'false','text':'否'}]"
                               data-valuefield="id" data-textfield="text" name="isValid" value="${product.isValid }"
                               validate-type="Require">
                    </dd>
                </dl>

				<dl class="form-item">
	                <dt class="title"><b class="c-red mr5">*</b>开始日期:</dt>
	                <dd class="detail">
	                    <label>
	                        <input class="zui-date zui-validatebox" type="text" id="startTimeLimit" name="startTime" value="${product.startTime }" validate-type="Require" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d',maxDate:'#F{$dp.$D(\'endTimeLimit\')}'})" readonly/>
	                    </label>
	                </dd>
	            </dl>
	            <dl class="form-item">
	                <dt class="title"><b class="c-red mr5">*</b>结束日期:</dt>
	                <dd class="detail">
	                    <label>
	                        <input class="zui-date zui-validatebox" type="text" id="endTimeLimit" name="endTime" value="${product.endTime }" validate-type="Require" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTimeLimit\')}'})" readonly/>
	                    </label>
	                </dd>
	            </dl>
	            <dl class="form-item">
	                <dt class="title">产品编号:</dt>
	                <dd class="detail">
	                    <label>
	                        <input type="text" class="zui-input zui-validatebox" validate-type="LegalChar,Length[0-32]" name="customCode" value="${product.customCode }"/>
	                    </label>
	                </dd>
	            </dl>
	            <dl class="form-item">
	                <dt class="title"><b class="c-red mr5">*</b>资方:</dt>
	                <dd class="detail">
	                    <input class="zui-combobox zui-validatebox" type="hidden" validate-type="Require" data-url="<z:ukey key='com.zdsoft.finance.cooperator.capitalist.capitalistSimpleCode' context='admin'/>&jsoncallback=?"
				                          data-valuefield="id" data-textfield="capitalName" name="capitalistId" data-callback="capitalist" value="${product.capitalistId }">
				        <input type="hidden" name="capitalistName" id="capitalistName" value="${product.capitalistName}"/>
	                </dd>
	            </dl>
	        </div>
	    </div>
    
	    <div class="page-box">
	        <h1 class="page-title">适用机构</h1>
	        <div class="p10">
                  <dl class="form-item">
	                <dt class="title"><b class="c-red mr5">*</b>适用机构:</dt>
	                <dd class="detail">
	                    <%-- <input class="zui-combotree zui-validatebox" type="hidden" data-parent-value ="true"
	                     name="orgCd" id="orgCd" data-multiple="true" data-defaultvalue=""
	                    	data-url="<z:res resource='enssential.org.findOrgToTree' isDefault='true'/>&jsoncallback=?&type=dept"
	                       data-valuefield="id" data-textfield="text" value="${company }"> --%>
                       <input class="zui-combobox zui-validatebox" type="hidden" validate-type="Require" data-url="<z:ukey key='com.zdsoft.finance.product.allCompany' context='admin'/>&jsoncallback=?"
	                          name="orgCd" id="orgCd" data-multiple="true" data-valuefield="orgCd" data-textfield="orgNm" value="${company}">
	                </dd>
	            </dl>
	        </div>
	    </div>
    
	    <div class="page-box">
	        <h1 class="page-title">面签资料</h1>
	        <div id="productInfo" class="p10">
                  <dl class="form-item">
	                <dt class="title"><b class="c-red mr5">*</b>面签资料:</dt>
	                <dd class="detail">
	                    <label>
                            <textarea class="zui-area zui-validatebox" validate-type="Require,Length[1-1000]" name="faceData" placeholder="最多可以输入1000个字符">${product.faceData }</textarea>
                        </label>
	                </dd>
	            </dl>
	        </div>
	    </div>
    </form>
	<div class="page-box">
	    <div class="page-title">产品信息</div>
	    <div class="p10">
	        <div id="tb-productRate" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.product.getRateList" context="admin"/>&productId=${product.id}","singleSelect":true,"pagination":false,"idField":"id","tableCls":"table-index","toolbar":"#btn-rate"}'>
			    <table>
			        <thead>
			        <tr>
			            <th data-options="field:rate,width:40%" formatter="formatRate">利率</th>
			            <th data-options="field:startDate,width:40%" formatter="formatDate">贷款期限范围</th>
			            <th data-options="field:id,width:20%" formatter="rateFunction">操作</th>
			        </tr>
			        </thead>
			    </table>
			</div>
			<div id="btn-rate">
			    <a class="zui-toolbar" id="btn-add" text="新增" buttonCls="btn-blue" handler="addRate"></a>
		    </div>
		</div>
	</div>
</div>
<div id="editProductRateDialog">
</div>
<script>

    seajs.use(['jquery', 'zd/jquery.zds.page.callback','zd/switch',
        'datepicker', 'zd/jquery.zds.button', 'zd/jquery.zds.combotree',
        'zd/jquery.zds.combobox', 'zd/jquery.zds.checkbox','zd/jquery.zds.form','zd/jquery.zds.table'], function ($, CALLBACK,Switch) {
    	$('#back').on('click',function(){
    		/* var editPrCostitem = '<z:ukey key="com.zdsoft.finance.product.list" context="admin"/>';
            ZDS_MESSAGE_CLIENT.openMenuLink('产品管理','产品管理',editPrCostitem + "&openMethod=tabs"); */
         	ZDS_MESSAGE_CLIENT.closeSelf();
    	});
		CALLBACK.capitalist = function(value,text){
			$("#capitalistName").val(text);
		};
    	$('#save').on('click',function(){
    		//保存
       		var flag=$.ZUI.validateForm($('#basicInfoForm'));
    		if(flag){
	    		//验证产品名称
	       	 	$.ajax({
	                type: 'post',
	                url: '<z:ukey key="com.zdsoft.finance.product.findProductByName" context="admin"/>',
	                data: {name:$("#basicInfoForm input[name='productName']").val(),
	                	id:$("#basicInfoForm input[name='id']").val(),
	                	categoryId:$("#basicInfoForm input[name='categoryId']").val()
	                	},
	                dataType: 'json',
	                success: function (data) {
                    	if (data.resultStatus == 0 && data.optional.isExist) {
                   			//保存
                			var basicInfoForm = $('#basicInfoForm').serializeArray();
                			$.ajax({
                                type: 'post',
                                url: '<z:ukey key="com.zdsoft.finance.product.update" context="admin"/>',
                                data: basicInfoForm,
                                dataType: 'json',
                                success: function (data) {
                                    if (data.resultStatus == 0) {
                                    	productId = data.id;
                                    	$('#id').val(data.id);
                                    	$.ZMessage.success("提示", "保存成功", function () {
                                    		Switch.enableTab("li[lang='editTab']");
                    	                    $(".zd-message").ZWindow("close");
                    	                });
                                    }else{
                                    	$.ZMessage.error("错误", data.msg, function () {
                    	                    $(".zd-message").ZWindow("close");
                    	                });
                                    }
                                },
                                error: function () {
                                	$.ZMessage.error("错误", "系统异常,请联系管理员", function () {
                                        $(".zd-message").ZWindow("close");
                                    });
                                }
                            });
	                   	}else{
	                   		$.ZMessage.error("错误", "产品名称已存在", function () {
		                            $(".zd-message").ZWindow("close");
	                        });
	                   	}
                	},
	                error: function () {
	                	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {
	                        $(".zd-message").ZWindow("close");
	                    });
	                }
            	});
    		 }else{
             	$.ZMessage.error("错误", data.msg, function () {
                     $(".zd-message").ZWindow("close");
                 });
             }
    	});
    	
    	CALLBACK.formatRate=function(rowData,index){
    		if(rowData.rateUnit=='YWDM0011901'){
    			return rowData.rate+'(%年)';
    		}else if(rowData.rateUnit=='YWDM0011902'){
    			return rowData.rate+'(%月)';
    		}else{
    			return rowData.rate+'(‰日)';
    		}
    	}
    	CALLBACK.formatDate=function(rowData,index){
    		var str='';
    		if(rowData.startDateUnit=='0931001'){
    			str =str + rowData.startDate+'年';
    		}else if(rowData.startDateUnit=='0931002'){
    			str =str + rowData.startDate+'月';
    		}else{
    			str =str + rowData.startDate+'日';
    		}
    		str=str+"至"
    		if(rowData.endDateUnit=='0931001'){
    			str =str + rowData.endDate+'年';
    		}else if(rowData.endDateUnit=='0931002'){
    			str =str + rowData.endDate+'月';
    		}else{
    			str =str + rowData.endDate+'日';
    		}
    		return str;
    	}
    	CALLBACK.rateFunction=function(rowData,index){
    		var str = "<a title='修改' class='btn-blue' onclick='editRate'>修改</a>" +
        	"&nbsp;&nbsp;<a title='删除' class='btn-blue' onclick='deleteRate'>删除</a>";
    		return str;
    	};
    	
    	CALLBACK.addRate=function(){
    		var id = $("#id").val();
    		if(id == ""){
    			$.ZMessage.info("提示", "请先保存产品！", function () {
                    $(".zd-message").ZWindow("close");
                });
    		}else{
	    		var	url = '<z:ukey key="com.zdsoft.finance.product.productRateDialog" context="admin"/>&productId=' + id;
    			$('#editProductRateDialog').load(url);
    		}
    	}
    	CALLBACK.editRate=function(index,rowData){
    		var	url = '<z:ukey key="com.zdsoft.finance.product.productRateDialog" context="admin"/>&productId=${product.id}&productRateId='+rowData.id;
    		$('#editProductRateDialog').load(url);
    	}
    	CALLBACK.deleteRate=function(index,rowData){
    		$.ZMessage.question("警告", "确认删除？", function () {
    			$(".zd-message").ZWindow("close");
    			$.ajax({
                    type: 'post',
                    url: '<z:ukey key="com.zdsoft.finance.product.deleteRate" context="admin"/>',
                    data: {productRateId:rowData.id},
                    dataType: 'json',
                    success: function (data) {
                        if (data.resultStatus == 0) {
                        	$('#tb-productRate').ZTable("reload", {});
                        	$.ZMessage.success("提示", "删除成功", function () {
        	                    $(".zd-message").ZWindow("close");
        	                });
                        }else{
                        	$.ZMessage.error("错误", data.msg, function () {
        	                    $(".zd-message").ZWindow("close");
        	                });
                        }
                    },
                    error: function () {
                    	$.ZMessage.error("错误", "系统异常,请联系管理员", function () {
                            $(".zd-message").ZWindow("close");
                        });
                    }
                });
            });
    	}
    	
    	//初始化表单
        $.ZUI.initForms('#basicInfo');
        $.ZUI.initGrid("#basicInfo");
    	$("#orgCd").ZComboTree('setValue','${company}');
    });
</script>
</body>
</html>