<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../common/common_js.jsp' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>合规复核</title>
</head>
<body>
<div class="frm-content frm-bottom" id="complianceReview">
    <div class="page-box">
        <h1 class="page-title">案件信息</h1>
        <div class="p5">
            <table class="table-detail">
                <tr>
                    <td class="td-title pct15">案件号</td>
                    <td class="pct15">${caseApplyVo.caseApplyCode}</td>
                    <td class="td-title pct15">主借人</td>
                    <td class="pct15" ></td>
                    <td class="td-title pct15">放款金额</td>
                    <td class="pct15"></td>
                </tr>
                <tr>
                    <td class="td-title pct15">放款期限</td>
                    <td class="pct15"></td>
                    <td class="td-title pct15">已放款资金</td>
                    <td class="pct15"></td>
                    <td class="td-title pct15">待垫出</td>
                    <td class="pct15"></td>
                </tr>
                <tr>
                    <td class="td-title pct15">已收回资金</td>
                    <td class="pct15"></td>
                    <td class="td-title pct15">待回收资金</td>
                    <td class="pct15"></td>
                    <td class="td-title pct15"></td>
                    <td class="pct15"></td>
                </tr>
            </table>
        </div>
        <h1 class="page-title">审批记录</h1>
        <div class="p5">
            <table class="table-flow">
                <tr>
                    <th rowspan="2">集团专员审查</td>
                    <th class="tr">受理人</td>
                    <td class="tl">xxx</td>
                    <th class="tr">审批时间</td>
                    <td class="tl">xxx</td>
                    <th class="tr">意见结论</td>
                    <td class="tl">xxx</td>
                </tr>
                <tr>
                    <th class="tr">审批意见</td>
                    <td class="tl" colspan="5">xxx</td>
                </tr>
               <tr>
                    <th rowspan="2">集团经理审批</td>
                    <th class="tr">受理人</td>
                    <td class="tl">xxx</td>
                    <th class="tr">审批时间</td>
                    <td class="tl">xxx</td>
                    <th class="tr">意见结论</td>
                    <td class="tl">xxx</td>
                </tr>
                <tr>
                    <th class="tr">审批意见</td>
                    <td class="tl" colspan="5">xxx</td>
                </tr>
            </table>
        </div>
        <div class="page-title">复核结果</div>
		<div class="p10">
			<div class="zd-form">
				<form id="reviewForm" class="zui-form" action="javascript:void(0);" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.casemanage.getRelatedLoanInfo" context="admin"/>&jsoncallback=?", "callBack": "saveCallBack"}'>
					<table class="table-detail">
							<tr>
								<td class="td-title pct20"><b class="c-red mr5">*</b>复核结果</td>
								<td class="pct30">
									<dd class="detail" id="reviewResults">
				                        <input class="zui-checkbox zui-validatebox" id="radioResult" type="hidden" data-multiple="false"
				                               data-data="[{'id':'0','text':'复核通过'},{'id':'1','text':'复核未通过'},{'id':'2','text':'否决'}]"
				                               data-valuefield="id" data-textfield="text" validate-type="Require">
				                    </dd>
								</td>
								<td class="td-title pct20"></td>
								<td class="pct30">
								</td>
							</tr>
							
					</table>
				</form>
			</div>
		</div>
		<div id="zd-table-warp">
			<div class="page-title">复核信息</div>
			<div class="p10">
				<div class="zd-form">
					<form id="reviewInfo" class="zui-form" action="javascript:void(0);" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.casemanage.getRelatedLoanInfo" context="admin"/>&jsoncallback=?", "callBack": "saveCallBack"}'>
						<table class="table-detail">
								<tr>
									<td class="td-title pct10"><b class="c-red mr5">*</b>一级标示</td>
									<td class="pct20">
										<dl class="form-item form-auto">
											<dd class="detail">
												<input class="zui-combobox zui-validatebox" type="hidden" data-data="[{'id':'0','text':'业务风险审批合规性'},{'id':'1','text':'业务操作合规性'},{'id':'2','text':'违规操作'}]" data-callback="firstMarking" data-valuefield="id" data-textfield="text" validate-type="Require">
											</dd>
										</dl>
									</td>
									<td class="td-title pct10"><b class="c-red mr5">*</b>二级标示</td>
									<td class="pct20">
										<dl class="form-item form-auto">
											<dd class="detail">
												<input class="zui-combobox zui-validatebox" type="hidden" id="secondMarking"  validate-type="Require">
											</dd>
										</dl>
									</td>
									<td class="td-title pct10"><b class="c-red mr5">*</b>三级标示</td>
									<td class="pct30">
										<dl class="form-item form-auto">
											<dd class="detail">
												<input class="zui-combobox zui-validatebox" type="hidden"  id="thirdMarking" validate-type="Require">
											</dd>
										</dl>
									</td>
								</tr>
						</table>
					</form>
				</div>
			</div>
			<div class="p10">
				<div id="zd-table-test"></div>
			</div>
			<!-- 责任人选择器 -->
			<div id="personnelSelector"></div>
			<div class="zd-form" id="remark">
<<<<<<< HEAD
				<table class="table-detail">
						<tr>
							<td class="td-title" style="width:200px;">描述</td>
							<td colspan="5">
								<label>
                  						<textarea class="zui-area" name="markRemark" placeholder="最多可以输入200个字符" style="width:100%;"></textarea>
          							</label>
							</td>
						</tr>
				</table>
=======
					<form id="remarkInfo" class="zui-form" action="javascript:void(0);" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.casemanage.getRelatedLoanInfo" context="admin"/>&jsoncallback=?", "callBack": "saveCallBack"}'>
						<table class="table-detail">
								<tr>
									<td class="td-title" style="width:200px;">描述</td>
									<td colspan="5">
										<label>
                    						<textarea class="zui-area" placeholder="最多可以输入200个字符" style="width:100%;"></textarea>
               							 </label>
									</td>
								</tr>
						</table>
					</form>
				</div>
>>>>>>> branch 'master' of http://222.177.14.56:8081/liuwei/zds-finance-mortgage.git
			</div>
		</div>
			
		<div class="page-title">后补资料</div>
		<div class="p10">
			<form id="afterData" class="zui-form" action="javascript:void(0);" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.casemanage.getRelatedLoanInfo" context="admin"/>&jsoncallback=?"}'>
			<div class="table-index">
				<table>
					<thead>
	        			<tr>
	            			<th>后补资料类型</th>
	            			<th>预计后补时间</th>
	            			<th>经办人</th>
	            			<th>备注</th>
				        </tr>
					</thead>
					<input id="afterDataNumber" name="afterDataNumber" type="hidden" value="${afterDataNumber }"/>
					<tbody>
						<c:forEach items="${afterData }" var="item" varStatus="s">
							<tr>
								<input id="afterDataId" name="afterData[${s.index }].id" type="hidden" value="${item.id }"/>
								<td>${item.materialType }</td>
								<td style="width:50px;">
									<input class="zui-date zui-validatebox strToDate" value="${item.predictDate }" type="text" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'predictDate',dateFmt:'yyyy-MM-dd'})" validate-type="Require" readonly "/>
									<input type="hidden" id="predictDate" name="afterData[${s.index }].predictDate" value="${item.predictDate  }"/>
								</td>
								<td>${item.operatorId }</td>
								<td>
									<input class="zui-input " style="width:100%;" type="text" name="afterData[${s.index }].mo" value="${item.mo }" >
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			</form>
		</div>
		
        <div class="save">
		    <button id="btn-save" class="btn-blue mr10" type="button">保存</button>
		    <button id="btn-cancel" class="btn-blue mr10" type="button" onclick="javascript:history.back(-1);">返回</button>
		</div>
    </div>
</div>
</body>
<script type="text/javascript">
seajs.use(['jquery', 'zd/tools', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter','zd/jquery.zds.checkbox'], function($, ZTOOL, CALLBACK) {
	var paramMark = "";
	//选择一级标示 
    CALLBACK.firstMarking=function(value,text) {
    	paramMark = "";
        paramMark += "&firstMark="+value;
        if(value == "0"){//业务风险审批合规性
        	$("#secondMarking").ZCombobox({
                valueField: "id",
                textField: "name",
                data: [{'id': '0', 'name': '全部'}, {'id': '1', 'name': '业务审批合规性'}, {'id': '2', 'name': '审批资料缺失/错漏'}, {'id': '3','name': '系统填写准确性'}, {'id': '4', 'name': '风控审批要求执行与风险判断能力'}],
                onSelect: function(value,text,index,data) {
                	paramMark += "&secondMark="+value;
	            	$("#thirdMarking").ZCombobox({
	               		valueField: "id",
	                    textField: "name",
	                    data: [{'id': '0', 'name': '全部'}, {'id': '1', 'name': '已选择项'}, {'id': '2', 'name': '为选择项'}],
	                    onSelect:function(value,text,index,data){
	                    	paramMark += "&thirdMark="+value;
	                    	$('#zd-table-reviewInfo').ZTable("reload");
	                    	$('#remark').show();
	                   	}
	            	});
	            }
            });
        }
        if(value == "1"){//业务操作合规性
        	$("#secondMarking").ZCombobox({
	       		valueField: "id",
	            textField: "name",
	            data: [{'id': '0', 'name': '全部'}, {'id': '1', 'name': '卷宗复核'}, {'id': '2', 'name': '节点管控'}, {'id': '3', 'name': '后补资料'}],
	            onSelect: function(value,text,index,data) {
	            	paramMark += "&secondMark="+value;
	            	$("#thirdMarking").ZCombobox({
	               		valueField: "id",
	                    textField: "name",
	                    data: [{'id': '0', 'name': '全部'}, {'id': '1', 'name': '已选择项'}, {'id': '2', 'name': '为选择项'}],
	                    onSelect:function(value,text,index,data){
	                    	paramMark += "&thirdMark="+value;
	                    	$('#zd-table-reviewInfo').ZTable("reload");
	                    	$('#remark').show();
	                   	}
	            	});
	            }
        	});
        }
        if(value == "2"){//违规操作 
        	
        }
    };
    //_________________________________________________________________
    //人员选择成功后的方法
    var chooseEmpOk = null;
    //表格1-未分页-批量保存
    $('#zd-table-test').ZTable({
        columns: [[
            {field: 'firstMark', title: '一级标示', align: 'center', width: '20%'},
            {field: 'secondMark', title: '二级标示', align: 'center', width: '300'},
            {field: 'thirdMark', title: '三级标示', align: 'left', width: '100' },
            {field: 'personLiable', title: '责任人', align: 'center', width: '100', 
         	   formatter: function (r, v) {
         		  return '<dl class="form-item form-auto">'
                   		+ '<dd class="detail">'
                       		+ '<label>'
                       		+   '<input id="empCode" name="empCode" type="hidden" value=""/>'
                       		+ 	'<input id="empName" name="empName" type="text" value="" class="zui-input" readonly/>'
                       		+	'<span name="chooseEmp"><a title="选择"   class="btn-blue" onclick="taskPersonnel">选择</a></span>';
                       		+ '</label>'
                   		+ '</dd>'
               		+ '</dl>'
            	   }
   }
        ]],
        idField: 'productid',//每行数据的，唯一标识符
        url: '<z:ukey key="com.zdsoft.finance.casemanage.getReviewInformation" context="admin"/>&jsoncallback=?'+paramMark,
        singleSelect: false,//表格行是单选还是多选
        isRowNum: true,//是否显示行号
        rows: 8,//分页情况下，显示的条数
        currentPage: 1,//分页情况下的，当前页
        pagination: false,//表格是否分页
        tableCls: 'table-index',//table的样式
        dbclickEditRow: true,//是否双击可编辑行
        batchSave: true,//是否批量保存
        onSelect: function (rowIndex, rowData) {
            //alert("选中" + rowIndex+'='+JSON.stringify(rowData));
        },
        onUnselect: function (rowIndex, rowData) {
            //alert("选中" + rowIndex+'='+"取消" + rowIndex+'='+JSON.stringify(rowData));
        },
        onSave: function (rowIndex, rowData) {
            //保存数据到数据库
            console.log('保存的数据:' + JSON.stringify(rowData));

            return true;
            //返回值：编辑，返回true或false；
            //返回值：新增，返回行ID值，即当前行idField的值。
        },
        onSaveAll: function (rowIndex, rowData) {
            //保存数据到数据库
            console.log('保存的数据:' + JSON.stringify(rowData));
            var saveDatas = [
                {
                    "itemid": 0,
                    "productid": "999",
                    "unitcost": "金融项目新增1",
                    "attr1": "批量新增1",
                    "listprice": "12323"
                },
                {
                    "itemid": 0,
                    "productid": "1111",
                    "unitcost": "金融项目新增2",
                    "attr1": "批量新增2",
                    "listprice": "12323"
                }
            ];
            return true;
            //返回值:true或false；
        },
        onDelete: function (rowsIndex, rowsData) {
            alert("删除IDs" + JSON.stringify(rowsIndex) + ',删除成功:' + JSON.stringify(rowsData));
            return true;
        }
    });

    //_________________________________________________________________
   	// 人员选择器
    $("#personnelSelector").Zseleter({
		title: '选择器',
		btnId: "select",
		width: 900,
		height: 400,
		key: "empCd",
		value: "empNm",
		singleSelect: true,
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
			if(chooseEmpOk != null && typeof(chooseEmpOk) == 'function'){
				chooseEmpOk(data);
			}
		},
		onClose:function(){
			chooseEmpOk = null;
		}
	});
	// 人员选择器
	$("span[name='chooseEmp']").each(function(index,data){
		console.log(data);
		$(data).click(function(){
			console.log(data);
		});
	});
	CALLBACK.taskPersonnel = function(index,rowData) {
		$("#personnelSelector").Zseleter("open");
	}


	
	//加载标示选择的table
    $('#zd-table-reviewInfo').ZTable({
           columns: [[
               {field: 'firstMark', title: '一级标示', align: 'center', width: '20%'},
               {field: 'secondMark', title: '二级标示', align: 'center', width: '300'},
               {field: 'thirdMark', title: '三级标示', align: 'left', width: '100', 	
            	   formatter: function (r, v) {
                		return "<span style='text-align:left;'><input type='checkbox' value="+v+">"+v+"</span>";
              	   }   
               },
               {field: 'personLiable', title: '责任人', align: 'center', width: '100', 
            	   formatter: function (r, v) {
                 		return "<input class='zui-input' type='text' value="+v+">";
               	   }
			   }
           ]],
           columnsType: [
               {
                   "personLiable": {
                       "inputType": "input"
                   }
               },
               {
                   "inputWidth": 140,
                   "inputHeight": 24
               }
           ],
           idField: 'thirdMark',//每行数据的，唯一标识符
           //queryParams: {param: 'xxooxxooxxoo000000000000000000'},//分页业务参数
           //url: 'http://192.168.33.13:8085/test/simplecode?jsoncallback=?',
           url: '<z:ukey key="com.zdsoft.finance.casemanage.getReviewInformation" context="admin"/>&jsoncallback=?'+paramMark,
//            data: paramMark,
           singleSelect: true,//表格行是单选还是多选
           isRowNum: true,//是否显示行号
           pagination: false,//表格是否分页,合并单元格暂时不支持分页
           tableCls: 'table-index',//table的样式
           batchSave: true,//默认为true，是否批量保存
           isMergeCell:true,//默认为false,合并单元格列表
           mergeColField: 'thirdMark',//需要合并之前的字段
           onSelect: function (rowIndex, rowData) {
              //alert("选中" + rowIndex+'='+JSON.stringify(rowData));
           },
           onUnselect: function (rowIndex, rowData) {
              //alert("选中" + rowIndex+'='+"取消" + rowIndex+'='+JSON.stringify(rowData));
           },
           onMergeSave: function (rowIndex, rowData) {
               //保存数据到数据库
               alert('保存的数据:' + JSON.stringify(rowData));
               return true;
           }
       });

       $('.superlink').click(function () {
           alert('超链接的点击事件,当前jQuery的版本号：' + $.fn.jquery);
           return false;
       });
    
    //---------------------------
	$("#reviewResults").click(function(){
		if($("#radioResult").val()==1 || $("#radioResult").val()==2){
			$("#zd-table-warp").css("display","none");
		}else{
			$("#zd-table-warp").css("display","block");
		}
	});
	//保存
	$("#btn-save").click(function(){
		var formData = $("#afterData").serializeArray();
		$.ajax({
			url:'<z:ukey key="com.zdsoft.finance.casemanage.saveComplianceReview" context="admin"/>&jsoncallBack=?',
			data:formData,
			type:"post",
			dataType:"jsonp",
			traditional:true,
			success:function(data){
				if(data.status == 1){
					$.ZMessage.success("提示", data.msg, function () {
	                })
				}else{
					$.ZMessage.error("错误", data.msg, function () {
	                })
				}
			}
		});
	});
	$("input[type='checkbox']").is(':checked')
	$.ZUI.init();
});
</script> 
</html>