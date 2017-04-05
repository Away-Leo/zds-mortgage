<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<%@ include file='../common/common_js.jsp'%>
<title>流程Demo列表</title>
</head>
<body>
	<div class="frm-content">
		<!-- 查询区域 -->
		<div class="page-box">
			<div class="page-title">查询参数</div>
			<div class="p10">
				<form id="search_from" class="zui-form form-search" method="post"
					enctype="multipart/form-data">
					<dl class="form-item">
						<dt class="title">操作人：</dt>
						<dd class="detail">
							<label> <input class="zui-input"
								style="height: 30px; line-height: 30px;" id="operatorNm"
								name="operatorNm|LK|S">
							</label>
						</dd>
					</dl>
					<dl class="form-item">
						<dt class="title">操作时间：</dt>
						<dd class="detail">

								<input class="zui-date zui-validatebox" type="text" id="startTimeLimit"  placeholder="选择开始日期"  onclick="WdatePicker({readOnly:true,realDateFmt:'yyyyMMdd',dateFmt:'yyyy-MM-dd',vel:'operatorTime_Re',maxDate:'#F{$dp.$D(\'endTimeLimit\')}'})"  style="width: 95px;"/>
								<input type="hidden" id="operatorTime_Re" name="operatorTime|RE|S">
						</dd>
						<dd class="detail">
							<label>
								<input class="zui-date zui-validatebox" type="text" id="endTimeLimit"   placeholder="选择结束日期"   onclick="WdatePicker({readOnly:true,realDateFmt:'yyyyMMdd',dateFmt:'yyyy-MM-dd',vel:'operatorTime_Le',minDate:'#F{$dp.$D(\'startTimeLimit\')}'})"  style="width: 95px;"/>
								<input type="hidden" id="operatorTime_Le" name="operatorTime|LE|S">
							</label>
						</dd>
					</dl>
					<dl class="form-item">
						<dt class="title">模块名：</dt>
						<dd class="detail">
							<label> <input class="zui-input"
								style="height: 30px; line-height: 30px;" id="moduleNm"
								name="moduleNm|LK|S">
							</label>
						</dd>
					</dl>
					<dl class="form-item">
						<dt class="title">日志记录类型：</dt>
						<dd class="detail">
							<label>
								<input class="zui-combobox zui-validatebox" id="logType" type="hidden"
									   name="logType|E|S" validate-type="Require"
									   data-data="[{'id':'1','text':'自动'},{'id':'2','text':'手动'}]"
									   data-valuefield="id" data-textfield="text"/>
							</label>
						</dd>
					</dl>
					<dl class="form-btn">
						<button type="button" class="btn-search-blue" id="btn-submit">查询</button>
						<button type="button" class="btn-search-gray" id="btn-reset">重置</button>
					</dl>
				</form>
			</div>
			<div class="p10">
				<div id="log-datagrid"></div>
			</div>
		</div>
	</div>
	<div id="dialogForm" style="display: none">
		<div id="businessLogDiv">
			<form id="businessLogForm" class="zui-form" action="javascript:void(0);"
			      zdata-options={"url":"","callBack":"saveCallBack"}>
				<dl class="form-item">
					<dt class="title">操作人：</dt>
					<dd class="detail">
						<label>
							<input type="text" name="operatorNm" id="operatorNmDialog" class="zui-input"/>
							<input type="text" name="operatorId" id="operatorIdDialog" />
							<div id="selecter" class="btn-blue">选择</div>
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">模块名：</dt>
					<dd class="detail">
						<label>
							<input type="text" name="moduleNm" class="zui-input"/>
							<i class="require icon-mrequire"></i>
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">动作：</dt>
					<dd class="detail">
						<label>
							<input type="text" name="actionNm" class="zui-input"/>
							<i class="require icon-mrequire"></i>
						</label>
					</dd>
				</dl>
				<dl class="form-item span2">
                    <dt class="title">描述</dt>
                    <dd class="detail">
                        <label>
                            <textarea name="description" class="zui-area zui-validatebox" validate-type="Require"
                                      placeholder="最多可以输入200个字符"></textarea>
                        </label>
                    </dd>
                </dl>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK) {
            $.ZUI.init();
			$("#btn-reset").click(function () {
                $.ZUI.resetForms('#search_from');
            });
			$("#dialogForm").Zdialog({
				width: 700, height: 340, closed: true, title: "新增业务日志",
				buttons: [
					{
						id: 'message-btn',
						text: '确定',
						buttonCls: 'btn-blue',
						handler: function () {
							
							var data = $('#businessLogForm').serialize();
							
							$.ajax({
				        		url :'<z:ukey key="zf.businesslog.saveBusinessLog" context="admin"/>',
				                type : 'post',
				                data : data,
				                success : function(data) {
				                    if(data.resultStatus == 0){
				                    	$.ZMessage.info("成功", data.msg, function(){
				                    		var formArray=$("#search_from").serialize();
				                    		formArray = decodeURIComponent(formArray, true);
				            				$('#log-datagrid').ZTable("reload",formArray);
				            				$("#dialogForm").Zdialog("close");
				                    	});
				                    }else{
				                    	$.ZMessage.error("错误", data.msg);
				                    }
				                }
				        	});
						}
					},
					{
						id: 'message-btn',
						text: '取消',
						buttonCls: 'btn-gray',
						handler: function () {
							$("#dialogForm").Zdialog("close");
						}
					}
				]
			});

			

			var url = '<z:ukey key='zf.businesslog.getLogList' context='admin'/>&jsoncallback=?';
			$('#log-datagrid').ZTable({
				columns:[[{field:'operatorNm', title:'操作人', align:'center'},
				          {field:'operatorTime', title:'操作时间', align:'left',formatter:formatDate},
				          {field:'moduleNm', title:'模块名', align:'left'},
				          {field:'actionNm', title:'动作', align:'left'},
				          {field:'description', title:'描述', align:'left'},
				          {field:'logType', title:'日志类型', align:'left'}]],
				url:url,
				singleSelect:true,
				isRowNum:true,
				rows:10,
				currentPage:1,
				pagination:true,
				tableCls:'table-index',//table的样式
				toolbar:[{
					id:'add',
					text:'新增',
					iconCls: 'icon-blue06',
					buttonCls: 'btn-blue',
					handler: function (jq) {
						$('#dialogForm').Zdialog('open');
					}
				}],
			});

            //格式化列表数据
            CALLBACK.formatDate=function(rowData,index){
                return ZTools.strToDate(rowData.operatorTime);
            };

            //点击查询
			$("#btn-submit").click(function() {
				doSearch();
			});
			//点击重置
			$("#btn-reset").click(function() {
				$('#amount').val("");
				$('#busiCd').val("");
                $("#operatorTime_Re").val("");
                $("#operatorTime_Le").val("");
			
			});

			function doSearch() {
                var s = $("#operatorTime_Re").val();
                var e = $("#operatorTime_Le").val();
                var formArray=$("#search_from").serialize();
                formArray = decodeURIComponent(formArray, true);
                if(s&&e){
                    formArray += "&operatorTime|BT|BT=" + s+"000000" + "," + e+"235959";
				}
                $('#log-datagrid').ZTable("reload",formArray);

			};
			// 人员选择器
			$("#selecter").Zseleter({
				title: '选择器',
				btnId: "select",
				width: 900,
				height: 400,
				key: "empCd",
				value: "empNm",
				singleSelect: false,
				columns: {
					test: [[
						{field: 'empNm', title: '姓名', width: 80},
						{
							field: 'empTypeNm', title: '岗位', width: 80, align: 'right', formatter: function (r, v) {
							return "<span class='c-blue'>" + v + "</span>";
						}
						},
						{field: 'gendar', title: '员工编号', width: 80},
						{
							field: 'orgNm', title: '部门名称', width: 60, align: 'center', formatter: function (r, v) {
							return v;
						}
						}
					]]
				},
				url:"<z:res resource='essential.comm.employees.select' isDefault='true'/>&jsoncallback=?",
				type: 'test',
				defSearchForm: {
					test: [
						{
							label: "姓名",
							type: "input",
							name: "empNm"
						}
					]
				},
				onOk:function(data){
					$('#operatorIdDialog').val(data[0]._data.empCd);
					$('#operatorNmDialog').val(data[0]._data.empNm);
				}
			});
		});
	</script>
</body>
</html>