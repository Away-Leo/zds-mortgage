<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ include file="../common/common_js.jsp" %>

<div id="categoryDialog">
    <div id="dialog">
        <form id="addCategoryForm" class="zui-form mt15" action="javascript:void(0);"
              zdata-options="{}">
            <input type="hidden" name="id" value="">
            <input type="hidden" name="parentId" value="${category.id }">
            <dl class="form-item">
                <dt class="title"><b class="c-red mr5">*</b>产品类别:</dt>
                <dd class="detail">
                    <label>
                        <input type="text" class="zui-input zui-validatebox" validate-type="Require,Length[0-64]" id="categoryName" name="name" value=""/>
                    </label>
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title"><b class="c-red mr5">*</b>顺序号:</dt>
                <dd class="detail">
                    <label>
                        <input type="text" class="zui-input zui-validatebox" validate-type="Require,Integer,Length[0-10]" name="orderNumber" value=""/>
                    </label>
                </dd>
            </dl>
        </form>
    </div>
</div>

<script type="text/javascript">
    seajs.use(['jquery','zd/jquery.zds.form'], function ($) {
    	//初始化
        $.ZUI.initForms("#categoryDialog");
    });
</script>
