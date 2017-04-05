<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<!-- 公证 -->
<!-- 查看案件基本信息 -->
  <%@ include file="common/case_apply_view.jsp" %>
<!-- 押品列表信息 -->
  <%@ include file="common/house_property_list.jsp" %>
<!-- 公证 -->	
<div class="page-box">
       <h1 class="page-title">公证</h1>
	<div class="p5">
		<div id="notarizeList">
		</div>
	</div>
</div>

<!-- 新增公证弹窗 -->
<div id="editNotarize" style="display: none">
    <div class="p10" id="editNotarizeDiv">
        <form id="notarizeForm" class="zui-form" method="post" enctype="multipart/form-data">
        	<input type="hidden" id="indexId" name="indexId"> <!-- indexId -->
        	<input type="hidden" id="notarizeId" name="id"> <!-- 公证id -->
        	<input type="hidden" id="caseApplyId" name="caseApplyId"> <!-- 案件号id -->
            <dl class="form-item">
                <dt class="title"><b class="c-red mr5">*</b>公证类型：</dt>
                <dd class="detail">
                    <label>
				        <input class="zd-combobox" type="hidden" id="notarizeType" name="notarizeType" data-choose="disable">
                    </label>
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title"><b class="c-red mr5">*</b>公证机关：</dt>
                <dd class="detail">
                   	<label> 
                   		<input class="zui-input zui-validatebox" type="text" id="notarizeOffice" name="notarizeOffice" validate-type="Require,Length[0-32]" />
					</label>
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title"><b class="c-red mr5">*</b>办理公证时间：</dt>
                <dd class="detail">
                    <label> 
						<input class="zui-date zui-validatebox strToDate" type="text" id="notarizeDate" onclick="WdatePicker({realDateFmt:'yyyyMMdd',dateFmt:'yyyy-MM-dd',vel:'changeNotarizeDate', maxDate:'#F{$dp.$D(\'notarizeProvideDate\')}'})" validate-type="Require" readonly />
						<input type="hidden" id="changeNotarizeDate" name="notarizeDate" />
					</label>
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title sptitle"><b class="c-red mr5">*</b>预计公证书出具时间：</dt>
                <dd class="detail">
					<label>   
						<input class="zui-date zui-validatebox strToDate" type="text" id="notarizeProvideDate" onclick="WdatePicker({realDateFmt:'yyyyMMdd',dateFmt:'yyyy-MM-dd',vel:'changeNotarizeProvideDate', minDate:'#F{$dp.$D(\'notarizeDate\')}'})" validate-type="Require" readonly />
						<input type="hidden" id="changeNotarizeProvideDate" name="notarizeProvideDate" />
					</label>
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title">备注：</dt>
                <dd class="detail">
					<label> 
						<textarea class="zui-area zui-validatebox" placeholder="最多可以输入200个字符" id="remark" name="remark" validate-type="Length[0-200]"></textarea>
					</label>
					<div class="zd-area">
	                    <span class="zd-curval">0</span>/<span class="zd-maxval">200</span>
	                 </div>
                </dd>
            </dl>
        </form>
    </div>
</div>
<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.dialog','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.combobox','zd/jquery.zds.table'], 
			function($, CALLBACK,ZTOOL) {
		
			
			// 公证列表
			$('#notarizeList').ZTable({
		       url : '<z:ukey key="com.cnfh.rms.casemanage.handleMortgage.getNotarizeList" context="admin"/>&jsoncallback=?&caseApplyId=${caseApplyVo.id}',
		       singleSelect : true,
		       isRowNum : false,
		       pagination : false,
		       currentPage : 1,
		       idField: "id",
		       tableCls : 'table-index',//table的样式
		       columns:[[
					{field : 'notarizeType',title : '公证类型code',align : 'center',hidden:true},//隐藏字段
					{field : 'caseApplyId',title : '案件ID',align : 'center',hidden:true},//隐藏字段
					{field : 'notarizeTypeName',title : '公证类型',align : 'center',width:'10%',formatter:function(r,v){
						return "<b class='c-red mr5'>*</b>"+v;    
						}
					},
					{field : 'notarizeOffice',title : '公证机关', align : 'center',width:'15%'},      
					{field : 'notarizeDate',title : '办理公证时间', align : 'center',width:'20%',formatter:function(r,v){
							if(v==0){return "";} return ZTOOL.strToDate(v);
						} 
					},
					{field : 'notarizeProvideDate',title : '预计公证书出具时间', align : 'center',width:'20%',formatter:function(r,v){
							if(v==0){return "";} return ZTOOL.strToDate(v);
						}
					},
					{field : 'remark',title : '备注', align : 'center',width:'25%'},
					{field : 'id',title : '操作', align : 'center',width:'10%',formatter:function(r,v){
						 return '<a href="javaScript:void(0)" onclick="editNotarize" class="btn-blue">编辑</a>';
						}
					}
				] ],
				onLoadSuccess:function() {
				}
			});
			
			//公证编辑行
			var notarizeTypes=[];
			CALLBACK.editNotarize = function(index,rowData) {
				$("#indexId").val(index);
				$("#notarizeId").val(rowData.id);
				$('#caseApplyId').val(rowData.caseApplyId);
				
				notarizeTypes.push({'fullcode':rowData.notarizeType,'name':rowData.notarizeTypeName});
				$("#notarizeType").ZCombobox({
     	            valueField: "fullcode",
     	            textField: "name",
     	            data: notarizeTypes,
     	            value:rowData.notarizeType
     	        });
				$('#notarizeOffice').val(rowData.notarizeOffice);
				if(rowData.notarizeDate!=0){
					$("#notarizeDate").val(rowData.notarizeDate);
					$("#changeNotarizeDate").val(rowData.notarizeDate);
					$("#notarizeProvideDate").val(rowData.notarizeProvideDate);
					$("#changeNotarizeProvideDate").val(rowData.notarizeProvideDate);
					$.ZUI.strToDate();
				}
				$("#remark").val(rowData.remark);
				$("#editNotarize").Zdialog('open');
			}
			
			// 添加公证对话框
			$("#editNotarize").Zdialog({
			   width: 700, height: 300, closed: true, title: "新增",buttons: 
		       [
		           {
		               id: 'message-btn',
		               text: '确定',
		               buttonCls: 'btn-blue',
		               handler: function () {
		            	   var isValid = $.ZUI.validateForm($('#notarizeForm'));
		            	   if (!isValid) {
		            		   $.ZMessage.info("提示", "请完善必填项信息！", function () {
		                       });	 
		   					   return false;
		            	   }
	            		   var arr=[];
	                          arr[0] =$("#indexId").val();//indexId
	                          arr[1]={
	                                  "notarizeType": $("#notarizeType").ZCombobox("getValue"),
	                                  "notarizeTypeName": $("#notarizeType").ZCombobox("getText"),
	                                  "notarizeOffice": $("#notarizeOffice").val(),
	                                  "notarizeDate": $("#changeNotarizeDate").val(),
	                                  "notarizeProvideDate": $("#changeNotarizeProvideDate").val(),
	                                  "remark": $("#remark").val(),
	                                  "caseApplyId": $("#caseApplyId").val(),
	                                  "id": $("#notarizeId").val()
	                                  };//行数据
	                          $("#notarizeList").ZTable("editOneRow",arr);
	                          $("#editNotarize").Zdialog("close");
	            	   }
		               
		           },
		           {
		               id: 'message-btn',
		               text: '取消',
		                  buttonCls: 'btn-gray',
		                  handler: function () {
		                    $("#editNotarize").Zdialog("close");
		                  }
		            }
		        ]
		   	});
			
			$.ZUI.initGrid("#notarizeList");
			$.ZUI.initForms("#editNotarizeDiv");
	 }); 
</script>