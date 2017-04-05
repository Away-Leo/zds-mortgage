<%@ taglib prefix="fn"   uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../common/common_upload.jsp" %>
<!-- 图片查看 -->
<div class="frm-content">
    <div class="page-box" style="height: 750px;">
        <h1 class="page-title">
            附件图片预览
        </h1>
        <div class="p10 picScroll-screen" style="height: 750px" >
            <div  class="picScroll-current">
                <div class="crt-img" id="picShow"></div>
                <div class="crt-icon"><span class="crt-page">共${fn:length(attas)}张</span></div>
            </div>
            <div class="picScroll-left">
                <div class="bd">
                    <a class="prev"><i class="icon-power-add"></i></a>
                    <a class="next"><i class="icon-power-delete"></i></a>
                    <ul class="picList" id="picList">
                        <c:forEach items="${attas}" var="a" varStatus="i">
                            <li <c:if test="${i.index==0}">class="active"</c:if> ><img src='<z:ukey key="public.upload.download" context="admin"/>&jsoncallback=?&attachmentId=${a.id}' /></li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    seajs.use(['jquery', 'zd/jquery.zds.page.callback','zd/tools', 'zd/jquery.zds.combobox', 'zd/jquery.zds.checkbox', 'zd/jquery.zds.loading', 'zd/switch',
                'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form','viewer/superslide','viewer/iviewer',
                'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter', 'zd/bothselecter', 'zd/jquery.zds.button'],
            function ($, CALLBACK,ZTOOL, Loading, Switch, DropDown, Filter, Check, Zdialog, ZUI_MESSAGE_CLIENT) {
                $.ZUI.init();
                //初始化轮播图片
                $(".picScroll-left").slide({
                    mainCell:".bd ul",
                    autoPage:true,
                    effect:"left",
                    autoPlay:true,
                    vis:5,
                    scroll:5
                });

                //初始化当前图片
                ZTOOL.initView('#picList','#picShow');

            });

</script>
