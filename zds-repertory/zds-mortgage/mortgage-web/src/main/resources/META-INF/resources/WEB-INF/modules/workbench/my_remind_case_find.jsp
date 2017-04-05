<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>

<div id="remindPoolFindDialog">
    <div id="poolFindDialog">
        <form class="zui-form mt15" action="javascript:void(0);"
              zdata-options="{}">
            <dl class="form-item">
	             <dt class="title">案件号:</dt>
	             <dd class="detail">
	                 <label> 
						<input class="zui-input zui-disabled zui-validatebox" validate-type="Length[0-32]" id="projectCode" name="projectCode" value="${remindPool.projectCode }" disabled="disabled">
					</label>
	             </dd>
	        </dl>
            <dl class="form-item">
	             <dt class="title">主借人:</dt>
	             <dd class="detail">
	                 <label> 
						<input class="zui-input zui-disabled zui-validatebox" validate-type="Length[0-100]" id="customerName" name="customerName" value="${remindPool.customerName }" disabled="disabled">
					</label>
	             </dd>
	        </dl>
            <dl class="form-item">
	             <dt class="title">提醒类型:</dt>
	             <dd class="detail">
	                 <label> 
						<input class="zui-input zui-disabled zui-validatebox" validate-type="Length[0-20]" id="category" name="category" value="${remindPool.category }" disabled="disabled">
					</label>
	             </dd>
	        </dl>
	        <br>
            <dl class="form-item">
	             <dt class="title">提醒内容:</dt>
	             <dd class="detail">
	                 <label>
	                 	<textarea class="zui-area zui-validatebox" name="content" validate-type="Length[0-500]" disabled="disabled">${remindPool.content }</textarea>
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
    seajs.use(['jquery','zd/jquery.zds.form','zd/jquery.zds.dialog'], function ($) {
    	
    	$("#remindPoolFindDialog").Zdialog({
            width: 700, height: 340, closed: false, title: "提醒",isDestroy:true,
            buttons: 
            [
                {
                    id: 'message-btn',
                    text: '确定',
                    buttonCls: 'btn-blue',
                    handler: function () {
                    	$("#remindPoolFindDialog").Zdialog("close");
                    }
                }
            ]
        });
    	
    	//初始化
        $.ZUI.initForms("#poolFindDialog");
    });
</script>
