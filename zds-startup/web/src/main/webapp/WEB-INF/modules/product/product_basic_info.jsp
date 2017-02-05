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
	        	<input type="hidden" name="productId" value="${product.id }">
                <dl class="form-item">
                    <dt class="title">产品分类:</dt>
                    <dd class="detail">
                        <label>
                            <input class="zui-input zui-disabled zui-validatebox" type="text" value="${product.categoryVo.name }"
                                   validate-type="Require" disabled/>
                        </label>
                    </dd>
                </dl>

                <dl class="form-item">
                    <dt class="title">产品:</dt>
                    <dd class="detail">
                        <label>
                            <input class="zui-input zui-disabled zui-validatebox" type="text" value="${product.productName }"
                                   validate-type="Require" disabled/>
                        </label>
                    </dd>
                </dl>

                <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>是否有效:</dt>
                    <dd class="detail">
                        <dd class="detail">
	                        <input class="zui-combobox zui-validatebox" type="hidden"
	                               data-data="[{'id':'true','text':'是'},{'id':'false','text':'否'}]"
	                               data-valuefield="id" data-textfield="text" name="isValid" value="${product.isValid }"
	                               validate-type="Require">
	                    </dd>
                    </dd>
                </dl>

				<dl class="form-item">
	                <dt class="title"><b class="c-red mr5">*</b>开始日期:</dt>
	                <dd class="detail">
	                    <label>
	                        <input class="zui-date zui-validatebox" type="text" id="startTimeLimit" name="startTime" value="${product.startTime }" validate-type="Require" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endTimeLimit\')}'})" readonly/>
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
	                        <input type="text" class="zui-input zui-validatebox" validate-type="LegalChar,Length[0-30]" name="customCode" value="${product.customCode }"/>
	                    </label>
	                </dd>
	            </dl>
	            <dl class="form-item">
	                <dt class="title">资方:</dt>
	                <dd class="detail">
	                    <input class="zui-combobox zui-validatebox" type="hidden" validate-type="" data-url="<z:ukey key='com.zdsoft.finance.cooperator.capitalist.capitalistSimpleCode' context='admin'/>&jsoncallback=?"
				                          data-valuefield="id" data-textfield="cooperatorName" name="capitalistId" value="${product.capitalistId }">
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
	                    <input class="zui-combotree zui-validatebox" type="hidden" data-parent-value ="false"
	                     name="orgCd" id="orgCd" data-multiple="true" data-defaultvalue=""
	                    	data-url="<z:res resource='enssential.org.findOrgToTree' isDefault='true'/>&jsoncallback=?&type=dept"
	                       data-valuefield="id" data-textfield="text" value="${company }">
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
	    <div class="page-title">产品利率</div>
	    <div class="p10">
	        <div id="tb-productRate" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.product.getRateList" context="admin"/>&productId=${product.id }","singleSelect":true,"pagination":false,"idField":"id","tableCls":"table-index","toolbar":"#btn-rate"}'>
			    <table>
			        <thead>
			        <tr>
			            <th data-options="field:rate,width:40%" formatter="formatRate">利率</th>
			            <th data-options="field:startDate,width:40%" formatter="formatDate">贷款期限范围</th>
			            <th data-options="field:id,width:20%" formatter="formatId">操作</th>
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

    seajs.use(['jquery', 'zd/jquery.zds.page.callback',
        'datepicker', 'zd/jquery.zds.button', 'zd/jquery.zds.combotree',
        'zd/jquery.zds.combobox', 'zd/jquery.zds.checkbox','zd/jquery.zds.form','zd/jquery.zds.table'], function ($, CALLBACK) {
    	$('#back').on('click',function(){
    		/* var editPrCostitem = '<z:ukey key="com.zdsoft.finance.product.list" context="admin"/>';
            ZDS_MESSAGE_CLIENT.openMenuLink('产品管理','产品管理',editPrCostitem + "&openMethod=tabs"); */
         	ZDS_MESSAGE_CLIENT.closeSelf();
    	});

    	$('#save').on('click',function(){
    		
    		var flag=$.ZUI.validateForm($('#basicInfoForm'));
    		if(flag){
    			var basicInfoForm = $('#basicInfoForm').serialize();
    			$.ajax({
                    type: 'post',
                    url: '<z:ukey key="com.zdsoft.finance.product.update" context="admin"/>',
                    data: basicInfoForm,
                    dataType: 'json',
                    success: function (data) {
                        if (data.resultStatus == 0) {
                        	$('#productRateId').val(data.id);
                        	$.ZMessage.success("提示", "保存成功", function () {
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
    		}
    	});
    	
    	CALLBACK.formatRate=function(rowData,index){
    		
    		if(rowData.rateUnit=='31001'){
    			return rowData.rate+'(%年)';
    		}else if(rowData.rateUnit=='31002'){
    			return rowData.rate+'(%月)';
    		}else{
    			return rowData.rate+'(%日)';
    		}
    	}
    	CALLBACK.formatDate=function(rowData,index){
    		var str='';
    		if(rowData.startDateUnit=='31001'){
    			str =str + rowData.startDate+'年';
    		}else if(rowData.startDateUnit=='31002'){
    			str =str + rowData.startDate+'月';
    		}else{
    			str =str + rowData.startDate+'日';
    		}
    		str=str+"至"
    		if(rowData.endDateUnit=='31001'){
    			str =str + rowData.endDate+'年';
    		}else if(rowData.endDateUnit=='31002'){
    			str =str + rowData.endDate+'月';
    		}else{
    			str =str + rowData.endDate+'日';
    		}
    		return str;
    	}
    	CALLBACK.formatId=function(rowData,index){
    		return '<a href="javaScript:void(0)" onclick="editRate"><button class="btn-blue">编辑</button></a>'+'&nbsp;&nbsp;'+
        	'<a href="javaScript:void(0)" onclick="deleteRate"><button class="btn-blue">删除</button></a>'
    	}
    	
    	CALLBACK.addRate=function(){
    		var	url = '<z:ukey key="com.zdsoft.finance.product.productRateDialog" context="admin"/>&productId=${product.id}';
    		$('#editProductRateDialog').load(url,function(){
				
			});
    	}
    	CALLBACK.editRate=function(index,rowData){
    		var	url = '<z:ukey key="com.zdsoft.finance.product.productRateDialog" context="admin"/>&productId=${product.id}&productRateId='+rowData.id;
    		$('#editProductRateDialog').load(url,function(){
				
			});
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