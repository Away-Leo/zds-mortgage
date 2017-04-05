<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<!-- 列表区域 -->
<div id="relationCaseDiv" class="page-box">
	<div class="page-title">关联贷款信息</div>
	<div class="p10">
		<div id="zd-table-reviewInfo"></div>
	</div>
</div>
<script type="text/javascript">
seajs.use(['jquery','zd/tools','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.table'], 
	 	function ($,ZTOOL,CALLBACK) {
	$('#zd-table-reviewInfo').ZTable({
		url: '<z:ukey key="com.zdsoft.finance.marketing.findCustomerRelationCaseInfos" context="admin"/>&customerId=${customerId}&exceptCaseIds=${exceptCaseIds}',   
		singleSelect: true, //表格行是单选还是多选
		isRowNum: false, //是否显示行号
		pagination: false, //表格是否分页,合并单元格暂时不支持分页
		tableCls: 'table-index', //table的样式
		batchSave: false, //默认为true，是否批量保存
		isMergeCell: true, //默认为false,合并单元格列表
		idField: 'threeMarkFullCodeName', //每行数据的，唯一标识符    
		mergeColField: 'caseApplyId', //需要合并指定字段  
		mergeCol:'caseApplyCode,customerName,caseApplyAmount,overdueAmount,overdueDate,overdueDays,caseApplyId',  
		onEdit: false, //启用所有行编辑
		columns: [
			[{
				field: 'caseApplyCode',
				title: '案件号',
				align: 'center'
			}, {
				field: 'customerName',
				title: '主借人',
				align: 'center'
			}, {
				field: 'caseApplyAmount',
				title: '贷款金额(元)',
				align: 'center',
				formatter : function(r, v){
					return ZTOOL.formatCurrency(v+"");
				}
			}, {
				field: 'overdueAmount',
				title: '逾期金额(元)',
				align: 'center',
				formatter : function(r,v) {
					if (v == null) {
						return "";
					}
					return v;
				}
			}, {
				field: 'overdueDate',
				title: '逾期日期',
				align: 'center',
				formatter :  function(r,v) {
					if (v == null || v == 0) {
						return "";
					}
					return ZTOOL.strToDate(v);
				}
			}, {
				field: 'overdueDays',
				title: '逾期天数',
				align: 'center',
				formatter : function(r,v) {
					if (v == null || v == 0) {
						return "";
					}
					return v;
				}
			}, {
				field: 'houseAddress',
				title: '小区名称',
				align: 'center',
				formatter : function(r,v) {
					if (v == null) {
						return "";
					}
					return v;
				}
			}, {
				field: 'housePropertyName',
				title: '房产性质',
				align: 'center',
				formatter : function(r,v) {
					if (v == null) {
						return "";
					}
					return v;
				}
			}, {
				field: 'synthesizePrice',
				title: '综合评估价(元)',
				align: 'center',
				formatter : function(r,v) {
					if (v == null) {
						return "";
					}
					return v;
				}
			}, {
				field: 'houseAge',
				title: '楼龄(年)',
				align: 'center',
				formatter : function(r,v) {
					if (v == null) {
						return "";
					}
					return v;
				}
			}, {
				field: 'houseArea',
				title: '面积(㎡)',
				align: 'center',
				formatter : function(r,v) {
					if (v == null) {
						return "";
					}
					return v;
				}
			}, {
				field: 'pledgeTypeName',
				title: '抵押模式',
				align: 'center',
				formatter : function(r,v) {
					if (v == null) {
						return "";
					}
					return v;
				}
			}, {
				field: 'caseApplyId',
				title: '操作',
				align: 'center',
				width: '100', 
         	   formatter: function (r, v) {
         		   if (r.hasViewPower != null && r.hasViewPower > 0) {
	         		   return '<a href="javaScript:void(0)" onclick="viewApply"><button class="btn-blue">查看详情</button></a>';
         		   }
         		   return "";
             	}
			}]
		],  
		onSelect: function(rowIndex, rowData) {
		},
		onUnselect: function(rowIndex, rowData) {
		},
		onMergeSave: function(rowIndex, rowData) {
			//保存数据到数据库
			return true;
		},
		onLoadSuccess:function() {
		}
	});
	
	// 查看详情
	CALLBACK.viewApply=function(index,rowData){
		// 案件详情页面
		var detailView = '<z:ukey key="com.zdsoft.finance.casemanage.riskAuditView" context="admin"/>&projectId=' + rowData.caseApplyId;
		ZDS_MESSAGE_CLIENT.openMenuLink('case_detail_infos_view', '案件详情', detailView); 
	}
	//初始化页面
	$.ZUI.initDiv("#relationCaseDiv");
});
</script>