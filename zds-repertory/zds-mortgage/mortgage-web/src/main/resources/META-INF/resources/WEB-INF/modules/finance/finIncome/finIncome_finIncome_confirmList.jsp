<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file='../../common/common_js.jsp' %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>前端费用收款确认</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<!-- 查询搜索区域 -->
	<div class="page-box">
        <div class="page-title">查询信息</div>
        <div id="searchForm" class="p5">
            <form id="form_search" class="zui-form" action="javascript:void(0);" zdata-options="{}">
                 <dl class="form-item">
					<dt class="title">案件号：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input" id="" name="mca|caseApplyCode|LK|S">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">单据号：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input" id="" name="fin|billCode|LK|S" >
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">业务部门：</dt>
					<dd class="detail">
						<label> <input class="zui-input" id=""	name="mca|developmentDepartmentName|LK|S">
						</label>
					</dd>
				</dl>
				
				<dl class="form-item">
					<dt class="title">机构：</dt>
					<dd class="detail">
						<label> <input class="zui-input" id=""	name="mca|mechanismName|LK|S">
						</label>
					</dd>
				</dl>
				
				<dl class="form-item">
					<dt class="title">主借人：</dt>
					<dd class="detail">
						<label> <input class="zui-input" id=""	name="cus|customerName|LK|S">
						</label>
					</dd>
				</dl>
				
				<dl class="form-item">
					<dt class="title">收款日期：</dt>
					 <dd class="detail">
	                    <label>
	                        <input class="zui-date zui-validatebox" type="text" id="startTimeLimit"  placeholder="选择开始日期"  onclick="WdatePicker({readOnly:true,realDateFmt:'yyyyMMdd',dateFmt:'yyyy-MM-dd',vel:'applyDate_Re',maxDate:'#F{$dp.$D(\'endTimeLimit\')}'})"  style="width: 95px;"/>
	                   		<input type="hidden" id="applyDate_Re" name="fin|receiptsDate|RE|S">
	                    </label>
	                </dd>
	                <dd class="detail">
	                    <label>
	                        <input class="zui-date zui-validatebox" type="text" id="endTimeLimit"   placeholder="选择结束日期"   onclick="WdatePicker({readOnly:true,realDateFmt:'yyyyMMdd',dateFmt:'yyyy-MM-dd',vel:'applyDate_Le',minDate:'#F{$dp.$D(\'startTimeLimit\')}'})"  style="width: 95px;"/>
	                   		<input type="hidden" id="applyDate_Le" name="fin|receiptsDate|LE|S">
	                    </label>
	                </dd>
				</dl>
				
				 <dl class="form-item">
					<dt class="title">状态：</dt>
					<dd class="detail">
						<label> 
						 <input class="zui-combobox" type="hidden" 
							  data-data="[{'id':'0','text':'草稿'},{'id':'1','text':'待确认'},{'id':'2','text':'已确认'},{'id':'3','text':'已退回'},{'id':'1','text':'已废弃'}]"
					          data-valuefield="id" data-textfield="text" name="fin|status|E|S" >
						</label>
					</dd>
				</dl>
			</form>
                <div class="form-btn">
		            <button class="btn-blue" type="button" id="search">查询</button>
		            <button class="btn-gray" id="reset">重置</button>
       		    </div>
        </div>
    </div>
	
	<!-- datagrid列表区域 -->
    <div class="page-box">
		<div class="page-title">案件信息列表</div>
		<div class="p10">
			<div id="zd-table"></div>
		</div>
	</div>

<script type="text/javascript">
	    seajs.use(
	    	['jquery', 'zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.loading','zd/jquery.zds.form', 'zd/jquery.zds.table'],
	    	 function ($, CALLBACK,ZTOOL){
	    	
	    	var url_data_list = '<z:ukey key="com.zdsoft.finance.finance.finIncomeDataList" context="admin"/>&fin|status|IN|S=1,2';
	    	var url_view_case_finIncome_page = '<z:ukey key="com.zdsoft.finance.finance.finIncome.view" context="admin"/>';//案件收款明细查看页面
	    	var url_review_repaymentReceipt = '<z:ukey key="com.zdsoft.finance.finance.finIncome.finIncomeFirm" context="admin"/>';
	         
	         
	         CALLBACK.reviewFinIncome = function (index,value) {
	        	 ZDS_MESSAGE_CLIENT.openMenuLink('review_finIncome_tab', "前端费用收款复核",url_review_repaymentReceipt+"&businessKey="+value.ID);
	         };
	         
	         CALLBACK.scanFinIncome = function (index,value) {
	             ZDS_MESSAGE_CLIENT.openMenuLink('scan_finIncome_tab', "查看收款单明细",url_view_case_finIncome_page+"&businessKey="+value.ID);
	         };
	    	
	    	$.ZUI.init();
	    	
	    	$('#zd-table').ZTable({
	    	      url : url_data_list,
                  singleSelect : false,
                  isRowNum : false,
                  pagination : true,
                  currentPage : 1,
                  tableCls : 'table-index',//table的样式
                  toolbar : [
                	  {
                          id: 'btncut3', text: '批量复核', iconCls: 'icon-btn08', buttonCls: 'btn-blue',
                          handler: function () {
                         	 var rows=$('#zd-table').ZTable("getSelections");
                         	 if(rows.length==0){
                         		 $.ZMessage.warning("警告", "请选择要复核的案件收费！");
                         		 return;
                         	 }
                         	 
                         	$.ZMessage.confirm("确认", "是否要批量复核！", function (r) {
                                if (r == 'recovery') {
                                	 var ids=[];
                                 	 for(var e in rows){
                                 		if(rows[e].STATUS==2){
                                 			 $.ZMessage.warning("警告", "选择的案件收费有已复核完成的！");
                                 			return;
                                 		}
                                 		ids.push(rows[e].ID);
                                 	 }
                                 	var url_update_status = '<z:ukey key="com.zdsoft.finance.finance.finIncome.finIncomeFirmSave" context="admin"/>';
                                 	$.ajax({
             			                type: 'post',
             			                url: url_update_status,
             			                data:"&status=2&businessKey="+ids,
             							dataType: "json",
             			                success: function (data) {
             			                	if(data.resultStatus == 0){
           	 			                		$.ZMessage.success("成功", "复核成功",function(){
           	 			                			$('#zd-table').ZTable("reload");
           	 			                		});
             			                	}else{
             			                		$.ZMessage.error("错误", "操作失败");
             			                	}
             			                }
             			            });
                                }
                            }, {
                                'recovery': {id: 'recovery', text: '确认', buttonCls: 'btn-blue'},
                                'cancel': {id: 'cancel', text: '取消', buttonCls: 'btn-gray'}
                            });
                          }
                      },
                      {
                          id: 'btn3', text: '导出明细', iconCls: 'icon-btn08', buttonCls: 'btn-white',
                          handler: function () {
	                        	  $.ZMessage.confirm("提示", "是否导出前端费用收款?<br/>如果导出缓慢,请耐心等待....", function (r) {
		      				            if (r == 'recovery') {
		      				            	 window.location.href ='<z:ukey key="com.zdsoft.finance.finIncome.exportFinIncomeExcel" context="admin"/>';
		      				            }
	    				        	}, {
	    				            'recovery': {id: 'recovery', text: '确认', buttonCls: 'btn-blue'},
	    				            'cancel': {id: 'cancel', text: '取消', buttonCls: 'btn-gray'}
	    				        	});
                          }
                      }
                  ],
                  columns:[[
                	{field : 'MECHANISMNAME',title : '机构',align : 'center'},
					{field : 'CASEAPPLYCODE',title : '案件号',align : 'center'},
					{field : 'CUSTOMERNAME',title : '主借人',align : 'center'},
					{field : 'BILLCODE',title : '单据号',align : 'center'},
					{field : 'PAYERAMOUNT',title : '收款金额(元)',align : 'center',
                        formatter: function (row, value) {
                       	 return ZTOOL.formatCurrency(value+""); 
                       }},
                       {field : 'RECEIPTSDATE',title : '收款日期',align : 'center',
                           formatter: function (row, value) {
                             	 return ZTOOL.strToDate(value+""); 
                             }},
					{field : 'DEVELOPMENTDEPARTMENTNAME',title : '业务部门',align : 'center'},
					{field : 'STATUS',title : '状态',align : 'center',
                        formatter: function (row, value) {
                        	var xx={"0":"草稿","1":"待确认","2":"已确认","3":"已废弃","4":"已退回"};
                         	 return xx[value]; 
                          }},
					{field : 'ID',title : '操作',align : 'center',
						formatter : function(rowData,value) {
							var aHtml='';
							if(rowData.STATUS==1){
								 aHtml+='<a title="复核"  onclick="reviewFinIncome"><button class="btn-blue">复核</button></a>&nbsp;&nbsp;';	
							}
							 aHtml+='<a title="查看案件收款明细"  onclick="scanFinIncome"><button class="btn-blue">详情</button></a>';
						return aHtml;
				}}
				] ]
        	});
	         
	         ZDS_MESSAGE_CLIENT.refreshThis=function(){
		    		$('#zd-table').ZTable("reload");
		    	};//回调刷新当前页面
		    	
	    	$('#search').on('click',function(){
	    		var formArray=$("#form_search").serialize();
	    			formArray=decodeURIComponent(formArray, true);
	    		$('#zd-table').ZTable("reload",formArray);
	    	});
	    	
	    	$('#reset').on('click',function(){
	    		$('input[class="zui-input"]').val("");
	    		$('.zui-date').val("");
	    		$('input[type="hidden"]').val("");
	    		$('.zui-combobox').val("");
	    		$('.zui-combobox').ZCombobox('setValue','');
	    	});
	    });
	</script>
</body>
</html>