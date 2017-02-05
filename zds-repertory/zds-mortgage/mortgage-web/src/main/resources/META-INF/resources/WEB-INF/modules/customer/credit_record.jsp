<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../common/common_js.jsp" %>
<!-- 引入图片组件 -->
<link rel="stylesheet" type="text/css" href="<%=resServer %>/assets/css/piccontext.css">
<script type="text/javascript" src="<%=resServer %>/assets/js/piccontext/piccontent.min.js"></script>


<title>征信录入</title>
</head>
<body>
	<div>
		<div class="page-box">
			<div class="p10">
		    <div class="z-main">
            
            <div class="z-role">
                <div class="z-role-title">
                    <span id="role-name" class="z-role-left">所有角色</span>
					<span class="z-role-right">
						<i id="role-select" class="icon-btn30 z-role-four"></i>
						<i id="role-add" class="icon-sbtn02 z-role-one"></i>
						<i id="role-delete" class="icon-btn12 z-role-two"></i>
						<i id="role-edit" class="icon-sbtn03 z-role-three"></i>
					</span>
                </div>

                <div class="z-wpeizhi">
                    <div id="role-delete-bottom" class="z-fd2" style="display: none">
                        <div id="role-delete-save" class="z-fd-bc2">保存</div>
                        <div id="role-delete-cancel" class="z-fd-qx2">取消</div>
                    </div>
                    <div id="role-edit-bottom" class="z-fd2" style="display: none">
                        <div id="role-edit-save" class="z-fd-bc2">保存</div>
                        <div id="role-edit-cancel" class="z-fd-qx2">取消</div>
                    </div>


                    <div class="z-search">
                        <div class="z-role-search"><input id="role-input" type="search"></div>
                        <div id="role-search" class="z-role-tu"><i class="icon-search"></i></div>
                    </div>

                    <ul id="role-list"><li data-id="111" data-name="角色1">角色1</li><li data-id="222" data-name="角色2">角色2<span class="wpeizhi">未配置</span></li><li data-id="333" data-name="角色3">角色3<span class="wpeizhi">未配置</span></li></ul>
                    <ul id="role-delete-list"></ul>
                    <ul id="role-edit-list"></ul>
                </div>
            </div>
            
            <div class="z-power">
                <div class="z-power-mian">
                    <div class="z-power-left">
                        <p class="z-power-title">可用权限</p>

                        <div class="z-power-kuang">
                            <div class="z-power-search">
                                <div class="z-power-search"><input id="privilege-input" type="search"></div>
                                <div id="privilege-search" class="z-power-tu"><i class="iconfont"></i></div>
                            </div>

                            <ul id="disable-privilege"></ul>

                        </div>
                    </div>
                    <div class="z-power-tuleft">
                        <div id="privilege-add" class="z-power-add">新增<i class="icon-power-delete ml5"></i></div>
                        <div id="privilege-delete" class="z-power-delete"><i class="icon-power-add mr5"></i>删除</div>
                    </div>
                    <div class="z-power-right">
                        <p class="z-power-right-title">已拥有权限</p>

                        <div class="z-power-right-kuang">

                            <div><p class="z-power-qz"><i id="enable-privilege-check" class="icon-checkbox z-power-gray2 mr5"></i>全选</p></div>

                            <ul id="enable-privilege"></ul>

                        </div>
                    </div>
                </div>
                <div class="z-bc">
                    <div id="privilege-save" class="span">保存</div>
                </div>
            </div>
        </div>
		           
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter','zd/make-first-py'], 
			function($, CALLBACK) {
			
			
			$.ZUI.init();
			
		});
	</script>
</body>
</html>