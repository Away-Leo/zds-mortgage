<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>    
<!-------------客户信息》提交选择关系弹窗-------------->
<!-- 提交选择关系弹窗 -->
<div id="relationshipDialog" style="display: none">
     <div id="relationshipDiv" class="p10">
	     <form id="relationship_form" class="zui-form " method="post" enctype="multipart/form-data" action="javascript:void(0);">
	        <input class="zui-input" type="hidden" id="mainborrowVoId" name="mainborrowVo.id" value="${mainborrowVo.id }">
	        <table class="table-detail">
	           <tr>
	               <td class="td-title pct10">主借人</td>
	               <td class="pct20">
	                   <dl class="form-item form-auto">
	                          <dd class="detail">
	                              <label>
	                                  <input class="zui-input zui-disabled" type="text" id="zjr" name="zjr" value="${mainborrowVo.customerName }" disabled>
	                              </label>
	                          </dd>
	                   </dl>
	               </td>
	               <td class="td-title pct10">年龄</td>
	               <td class="pct20">
	                   <dl class="form-item form-auto">
	                       <dd class="detail">
	                           <label>
	                               <input type="text" class="zui-input zui-disabled" name="zjrAge" id="zjrAge" value="${mainborrowVo.age }" disabled/>
	                           </label>
	                       </dd>
	                   </dl>
	               </td>
	           </tr>
           </table>
	   </form>
	   <div id="relationshipList" class="mt10">
	   </div>
     </div>
</div>
	
<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.dialog','zd/jquery.zds.table'], 
		function($, CALLBACK) {
		// 添加联系方式的对话框
		$("#relationshipDialog").Zdialog({
		   width: 700, height: 350, closed: true, title: "选择关系",isDestroy:true,buttons: 
	       [
	           {
	               id: 'message-btn',
	               text: '确定',
	               buttonCls: 'btn-blue',
	               handler: function () {
	            	   if(!$("#relationshipList").ZTable("getRows")){
	    					$.ZMessage.info("提示", "请选择与主借人关系！");
	    					return false;
	    				}
	   					//获取选择关系对象
	   					if(saveRelationship()){
	   						submitProcess(submitData);
	   					};
	   					$("#relationshipDialog").Zdialog("close");
	   				 	
	               }
	           },
	           {
	               id: 'message-btn',
	               text: '取消',
	                  buttonCls: 'btn-gray',
	                  handler: function () {
	                    $("#relationshipDialog").Zdialog("close");
	                  }
	            }
	        ]
	   	});
		
		
		// 关系方式列表
		$('#relationshipList').ZTable({
	       url : "<z:ukey key='com.zdsoft.finance.casemanage.datasurvey.applicantinfomation.findRelationship' context='admin'/>"+"&caseApplyId="+caseApplyId,
	       singleSelect : true,
	       isRowNum : true,
	       pagination : false,
	       currentPage : 1,
	       idField: "id",
	       //dbclickEditRow: true,//是否双击可编辑行
	       tableCls : 'table-index',//table的样式
	       onEdit:true,
	       columns:[[
				{field : 'id',title : 'id',align : 'center', hidden:true},
	    	  	{field : 'customerName',title : '姓名',align : 'center'},
				{field : 'joinTypeName',title : '参与类型',align : 'center'},
				{field : 'maritalStatusName',title : '婚况', align : 'center'},
				{field : 'relationship',title : '与主借人关系', align : 'center',required:true},
			] ],
			columnsType: [
                {
                    "relationship": {
                        "inputType": "combobox",
                        "data": {
                            "valueField": "fullcode",
                            "textField": "name",
                            "data": "[{'fullcode': 'YWDM0014801', 'name': '朋友'}, {'fullcode': 'YWDM0014802', 'name': '亲戚'}, {'fullcode': 'YWDM0014803','name': '同事'}, {'fullcode': 'YWDM0014804', 'name': '配偶'}, {'fullcode': 'YWDM0014804', 'name': '父子'}]"
                        },
                        "validateType": "Require"
                    }
                },
                {
                    "inputWidth": 100,
                    "areaWidth": 200,
                    "areaHeight": 80
                }
            ],
			onLoadSuccess:function() {
				$("td").each(function(){
					if($(this).text().trim()=='null'){
						$(this).text("");  
					}
				}); 
			}
		});
		$("#relationshipDialog").Zdialog("open");
		$.ZUI.initForms("#relationshipDiv");
		
		function saveRelationship(){
			var relationshipList=$('#relationshipList').ZTable("getRows");
			var mainborrowVoId = '${mainborrowVo.id }';
			var params = '&mainborrowId='+mainborrowVoId+'&relationshipList='+JSON.stringify(relationshipList);
			var saveRelationshipStatus = false;
			$.ajax({
                type: 'post',
                url: '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.applicantinfomation.saveRelationship" context="admin"/>',
                data: params,
                dataType: 'json',
                async: false,
                success: function (data) {
                	if (data.resultStatus == 0) {
                		saveRelationshipStatus =true;
                	}
                	else{
                       	$.ZMessage.error("错误", data.msg, function () {
                       	})
                       	return false;
                	};
		                   
               	},
                error: function () {
                  	$.ZMessage.error("错误", "保存主借人关系异常，请联系管理员", function () {
                    });
                }
            });
			
			return saveRelationshipStatus;
		}
	});
</script>