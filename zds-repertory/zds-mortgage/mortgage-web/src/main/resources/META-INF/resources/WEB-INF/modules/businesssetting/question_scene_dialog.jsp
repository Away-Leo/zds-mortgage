<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>

<div id="questionDialog">
    <div id="dialog">
        <form id="addQuestionForm" class="zui-form mt15" action="javascript:void(0);"
              zdata-options="{}">
            <dl class="form-item">
	             <dt class="title"><b class="c-red mr5">*</b>类型:</dt>
	             <dd class="detail">
	                 <input class="zui-combobox zui-validatebox" type="hidden" validate-type="Require" id="sceneCd"
	             	          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00102"
	                        data-valuefield="fullcode" data-textfield="name" name="sceneCd">
	             </dd>
	         </dl>
        </form>
    </div>
</div>

<script type="text/javascript">
    seajs.use(['jquery','zd/jquery.zds.form','zd/jquery.zds.table','zd/jquery.zds.seleter'], function ($) {

    	
    	//初始化
        $.ZUI.initForms("#dialog");
    });
</script>
