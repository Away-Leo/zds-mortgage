<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ include file="../common/common_js.jsp" %>

<div id="productCopyDialog">
    <div id="">
        <form id="copyProductForm" class="zui-form mt15" action="javascript:void(0);"
              zdata-options="{}">
            <input type="hidden" name="id" value="${product.id }">
            <dl class="form-item">
                <dt class="title"><b class="c-red mr5">*</b>产品:</dt>
                <dd class="detail">
                    <label>
                        <input type="text" class="zui-input zui-validatebox" validate-type="Require,LegalChar,Length[0-60]" id="productName" name="productName" value=""/>
                    </label>
                </dd>
            </dl>
        </form>
    </div>
</div>

<script type="text/javascript">
    seajs.use(['jquery','zd/jquery.zds.form'], function ($) {
    	//初始化
        $.ZUI.initForms("#productCopyDialog");
    });
</script>
