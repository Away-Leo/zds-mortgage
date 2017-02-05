<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>

<div id="questionDialog">
    <div id="dialog">
        <form id="addQuestionForm" class="zui-form mt15" action="javascript:void(0);"
              zdata-options="{}">
            <input type="hidden" name="id" value="${question.id }">
            <dl class="form-item">
	             <dt class="title"><b class="c-red mr5">*</b>问题:</dt>
	             <dd class="detail">
	                <label> 
						<input class="zui-input zui-validatebox" style="width: 504px" validate-type="Require,Length[0-60]" name="title" value="${question.title }">
					</label>
	             </dd>
	        </dl>
	        <br/>
            <dl class="form-item">
	             <dt class="title"><b class="c-red mr5">*</b>字段类型:</dt>
	             <dd class="detail">
	                 <input class="zui-combobox zui-validatebox" type="hidden" validate-type="Require" value="${question.typeCd }" data-callback="showParam" id="typeCdValue"
	             	          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=questionTypeCd"
	                        data-valuefield="fullcode" data-textfield="name" name="typeCd">
	             </dd>
	         </dl>
	         <br/>
            <dl class="form-item" id="showParam">
	             <dt class="title"><b class="c-red mr5">*</b>参数值:</dt>
	             <dd class="detail">
	                 <%-- <label> 
						<input class="zui-input zui-disabled zui-validatebox" style="width: 456px" type="text" disabled validate-type="Require,Length[0-500]" id="paramNm" name="paramNm" value="${question.paramNm }">
						<input class="zui-input zui-disabled zui-validatebox" style="width: 456px" type="hidden" disabled validate-type="Require,Length[0-255]" id="paramCd" name="paramCd" value="${question.paramCd }">
                        <a id="paramSelecter" class="btn-blue" href="javascript:void(0);">选择</a>
					</label> --%>
					<input class="zui-combobox zui-validatebox" data-multiple="true" type="hidden" validate-type="Require" value="${question.paramCd }" id="paramCdValue"
	             	          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=questionParamCd"
	                        data-valuefield="fullcode" data-textfield="name" name="paramCd">
	             </dd>
	         </dl>
	         <br>
	         <dl class="form-item">
	             <dt class="title"><b class="c-red mr5">*</b>命中规则:</dt>
	             <dd class="detail">
	                 <label>
	                 	<textarea class="zui-area zui-validatebox" name="hitRule" validate-type="Require,Length[0-500]" placeholder="最多可以输入500个字符">${question.hitRule }</textarea>
		             </label>
		             <div class="zd-area">
	                    <span class="zd-curval">0</span>/<span class="zd-maxval">500</span>
	                 </div>
	             </dd>
	         </dl>
        </form>
    </div>
</div>

<script type="text/javascript">
    seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.table','zd/jquery.zds.seleter'], function ($,CALLBACK) {
    	
    	$("#questionDialog").Zdialog({
            width: 700, height: 340, closed: false, title: "问题库",isDestroy:true,
            buttons: 
            [
                {
                    id: 'message-btn',
                    text: '确定',
                    buttonCls: 'btn-blue',
                    handler: function () {
                   	var flag=$.ZUI.validateForm($('#addQuestionForm'));
                    	if(flag){
                    		var addQuestionForm = $('#addQuestionForm').serialize();
                            $.ajax({
                                type: 'post',
                                url: '<z:ukey key="com.zdsoft.finance.question.saveOrUpdate" context="admin"/>',
                                data: addQuestionForm,
                                dataType: 'json',
                                success: function (data) {
                                    if (data.resultStatus == 0) {
                                    	$.ZMessage.success("提示", "保存成功", function () {
                    	                    $(".zd-message").ZWindow("close");
                    	                });
                                    	$('#tb-question').ZTable("reload", {});
                                    	$("#questionDialog").Zdialog("close");
                                    }
                                },
                                error: function () {
                                	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {
                                        $(".zd-message").ZWindow("close");
                                    });
                                	$("#questionDialog").Zdialog("close");
                                }
                            });
                    	}else{
                    		$.ZMessage.error("错误", "请填入合法参数", function () {
                                $(".zd-message").ZWindow("close");
                            });
                    	}
                    }
                },
                {
                    id: 'message-btn',
                    text: '取消',
                    buttonCls: 'btn-gray',
                    handler: function () {
                    	$("#questionDialog").Zdialog("close");
                    }
                }
            ]
        });
    	
    	$("#paramSelecter").Zseleter({
            title: '参数值',
            btnId: "param",
            width: 900,
            height: 400,
            key: "fullCode",
            value: "name",
            singleSelect: false,
            columns: {
                test: [[
                    {field: 'fullCode', title: '参数名', width: 80},
                    {field: 'name', title: '参数值', width: 80}
                ]]
            },
            url: '<z:ukey key="com.zdsoft.finance.question.paramSimpleCode" context="admin"/>',
            type: 'test',
            defSearchForm: {
                test: [
                    {
                        label: "参数名",
                        type: "input",
                        name: "fullCode"
                    }
                ]
            },
            onOk: function (data) {
                var fullCode = '';
                var name = '';
                $.each(data, function (i, item) {
                    if (fullCode) {
                    	fullCode += ",";
                    } else {
                    	fullCode = "";
                    }
                    if (name) {
                    	name += ",";
                    } else {
                    	name = "";
                    }
                    fullCode += item.fullCode;
                    name += item.name;
                });
                $("#paramCd").val(fullCode);
                $("#paramNm").val(name);
            }
        });
    	
    	CALLBACK.showParam=function(index,data){
    		if('questionTypeCd01'==index){
    			$('#paramCdValue').attr('validate-type','');
    			$('#paramCdValue').ZCombobox('setValue','');
	    		$('#showParam').hide();
    		}else{
    			$('#paramCdValue').attr('validate-type','Require');
    			$('#paramCdValue').ZCombobox('setValue','')
	    		$('#showParam').show();
    		}
    	}
    	
    	//初始化
        $.ZUI.initForms("#dialog");
    });
</script>
