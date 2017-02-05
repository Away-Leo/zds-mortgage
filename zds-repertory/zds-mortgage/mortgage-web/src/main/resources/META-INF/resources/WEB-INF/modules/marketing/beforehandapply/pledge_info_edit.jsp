<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>    
<!-------------押品信息》抵押情况---------------------------------------->
   <div class="page-box">
	    <div class="page-title">抵押情况
	    <button type="button"  class="btn-blue" id="andPledgeInfo" style="float:right;margin-right: 40px">新增</button>
	    </div>
	    <div class="p10">
	        <div id="pledgeList">
			</div>
		</div>
	</div>
	
	<!-- 新增抵押情况信息弹窗 -->
	<div id="pledgeInfoEdit" style="display:none">
		<div id="pledgeEditDiv" class="p10">
	     <form id="pledge_form" class="zui-form" method="post" enctype="multipart/form-data">
	       	 <input id="pledgeId" name="pledgeId" type="hidden"/>
			 <dl class="form-item">
				<dt class="title"><b class="c-red mr5">*</b>抵押顺序</dt>
	         	<dd class="detail">
                           <input class="zui-combobox zui-validatebox" type="hidden" id="pledgeType" name="pledgeType" value=""
						  data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0052"
						data-valuefield="fullcode" data-textfield="name" data-defaultvalue="YWDM0051012" validate-type="Require">
				</dd>
            </dl>
            <dl class="form-item">
        	   <dt class="title"><b class="c-red mr5">*</b>贷款银行</dt>
               <dd class="detail">
				 	<label>
						<input type="text" class="zui-input zui-validatebox" id="loanBank" name="loanBank" validate-type="Require" />
					</label>
			   </dd>
        	</dl>
        	
        	<dl class="form-item">
        		<dt class="title"><b class="c-red mr5">*</b>期限</dt>
	             <dd class="detail">
					<label> 
						<input type="text" class="zui-input zui-validatebox" id="deadline" name="deadline" validate-type="Require,Integer" />
					</label>
				 </dd>
        	</dl>
        	<dl class="form-item">
        		<dt class="title"><b class="c-red mr5">*</b>类型</dt>
             <dd class="detail">
               		<input class="zui-combobox zui-validatebox" type="hidden" id="type" name="type" value=""
					data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0053"
					data-valuefield="fullcode" data-textfield="name" validate-type="Require">
             </dd>
        	</dl>
        	<dl class="form-item">
        		<dt class="title"><b class="c-red mr5">*</b>现贷款余额</dt>
             	<dd class="detail">
					<label> 
						<input type="text" class="zui-input zui-validatebox" id="loanBalance" name="loanBalance" validate-type="Require,Digital[7-4]"/>
					</label>
				</dd>
        	</dl>
        	<dl class="form-item">
        		 <dt class="title"><b class="c-red mr5">*</b>抵押金额</dt>
	             <dd class="detail">
		              <label> 
		              	<input type="text" class="zui-input zui-validatebox" id="pledgeAmout" name="pledgeAmout" validate-type="Require,Digital[7-4]"/>
					  </label> 
	             </dd>
        	</dl>
        	<dl class="form-item">
        		<dt class="title"><b class="c-red mr5">*</b>成数</dt>
             	<dd class="detail">
					<label> 
						<input type="text" class="zui-input zui-validatebox" id="percentage" name="percentage" validate-type="Require,Integer"/>
					</label>
				</dd>
        	</dl>
	     </form>
	    </div> 
	</div>
	
	<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.dialog','zd/jquery.zds.table'], 
		function($, CALLBACK) {
		// 添加抵押情况对话框
		$("#pledgeInfoEdit").Zdialog({
		   width: 700, height: 300, closed: true, title: "抵押信息",buttons: 
	       [
	           {
	               id: 'message-btn',
	               text: '确定',
	               buttonCls: 'btn-blue',
	               handler: function () {
	            	   var isValid = $.ZUI.validateForm($('#pledgeEditDiv'));
	            	   if (isValid) {
	            		   var isAdd = false;
	            		   var id = $("#pledgeId").val();
	            		   if (id == null || id == "") {
	            			   isAdd = true;
	            		   }
	            		   if (!isAdd) {//编辑
	            			   var arr=[];
	                           arr[0] =id;//index
	                           arr[1]={
	                                   "pledgeType": $("#pledgeType").ZCombobox("getValue"),
	                                   "pledgeTypeNm": $("#pledgeType").ZCombobox("getText"),
	                                   "loanBank": $("#loanBank").val(),
	                                   "deadline": $("#deadline").val(),
	                                   "type": $("#type").ZCombobox("getValue"),
	                                   "typeNm": $("#type").ZCombobox("getText"),
	                                   "loanBalance": $("#loanBalance").val(),
	                                   "pledgeAmout": $("#pledgeAmout").val(),
	                                   "percentage": $("#percentage").val(),
	                                   "id": ""
	                                   };//行数据
	                           $('#pledgeList').ZTable("editOneRow",arr);
	                           $("#pledgeInfoEdit").Zdialog("close");
	                           return;
	            		   }
	            		   var row =  {
	            				   "pledgeType": $("#pledgeType").ZCombobox("getValue"),
                                   "pledgeTypeNm": $("#pledgeType").ZCombobox("getText"),
                                   "loanBank": $("#loanBank").val(),
                                   "deadline": $("#deadline").val(),
                                   "type": $("#type").ZCombobox("getValue"),
                                   "typeNm": $("#type").ZCombobox("getText"),
                                   "loanBalance": $("#loanBalance").val(),
                                   "pledgeAmout": $("#pledgeAmout").val(),
                                   "percentage": $("#percentage").val(),
                                   "id": ""
                                   };//行数据
	                   		$('#pledgeList').ZTable("addOneRow",row);
	                   		$("#pledgeInfoEdit").Zdialog("close");
	            	   }
	            	   
	               }
	           },
	           {
	               id: 'message-btn',
	               text: '取消',
	                  buttonCls: 'btn-gray',
	                  handler: function () {
	                    $("#pledgeInfoEdit").Zdialog("close");
	                  }
	            }
	        ]
	   	});
		
		
		// 抵押情况列表
		$('#pledgeList').ZTable({
	       url : '<z:ukey key="com.zdsoft.finance.marketing.getPledgeInfoList" context="admin"/>&jsoncallback=?&housePropertyId=${housePropertyVo.id}',
	       singleSelect : true,
	       isRowNum : false,
	       pagination : false,
	       currentPage : 1,
	       idField: "id",
	       tableCls : 'table-index',//table的样式
	       columns:[[
				{field : 'pledgeType',title : '行业类型code',align : 'center',hidden:true},//隐藏字段
				{field : 'pledgeTypeNm',title : '抵押类型',align : 'center',hidden:true},//隐藏字段
				{field : 'loanBank',title : '贷款银行', align : 'center'},
				{field : 'deadline',title : '贷款期限', align : 'center'},
				{field : 'type',title : '类型code', align : 'center',hidden:true},
				{field : 'typeNm',title : '类型', align : 'center'},
				{field : 'loanBalance',title : '现贷款余额', align : 'center'},
				{field : 'pledgeAmout',title : '抵押金额', align : 'center'},
				{field : 'percentage',title : '成数', align : 'center'},
				{field : 'id',title : '操作', align : 'center',formatter:function(r,v){
					var pledgeInfo ='<a href="javaScript:void(0)" onclick="editPledgeHandle"><button class="btn-blue" type="button">编辑</button></a>';
						pledgeInfo +='&nbsp;&nbsp;<a href="javaScript:void(0)" onclick="deletePledgeHandle"><button class="btn-blue" type="button" >删除</button></a>';
					 return pledgeInfo;
				}}
		] ],
		onDelete:function(index, rowData) {
			//  添加判断
			return true;
		},
		onLoadSuccess:function() {
		}
		});
		
		
		
		//编辑行
		CALLBACK.editPledgeHandle = function(index,rowData) {
			$("#pledgeId").val(index);
			// 如何更新一行的数据
			$('#pledgeType').ZCombobox('setValue',rowData.pledgeType);
			$('#type').ZCombobox('setValue',rowData.type);
			$('#loanBank').val(rowData.loanBank);
			$('#deadline').val(rowData.deadline);
			$('#loanBalance').val(rowData.loanBalance);
			$('#pledgeAmout').val(rowData.pledgeAmout);
			$('#percentage').val(rowData.percentage);
			$("#pledgeInfoEdit").Zdialog('open');
		}
		
		//删除行
		CALLBACK.deletePledgeHandle=function(index, rowData){
			//模拟点击选中事件
			$($('#pledgeList .datagrid-body').find("tr")[0]).trigger("click");
			$('#pledgeList').ZTable("deleteRow");
		}
		
		//新增抵押情况弹窗
        $('#andPledgeInfo').click(function () {
        	$("#pledgeId").val("");
            $('#pledgeInfoEdit').Zdialog("open");
        });
	}); 
	</script>
