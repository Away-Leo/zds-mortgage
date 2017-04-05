<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%> 
<!-------------客户信息------------------------------------>
<div class="page-box">
			 <div class="page-title">押品信息
			  </div>
			<div class="p10">
			<div id="collateralList"></div>
</div>
</div>
    
<script type="text/javascript">
	  
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter','zd/jquery.zds.address'], 
		function($, CALLBACK) {
		$('#collateralList').ZTable({   
		       url : "<z:ukey key='com.cnfh.rms.marketing.houseProperty.getHousePropertyList' context='admin'/>&jsoncallback=?&caseApplyId=${param.id}",
		       isRowNum : false,
		       currentPage : 1, 
		       rows:20,
		       singleSelect: true,  
		       pagination:false,
		       idField: "id", 
		       tableCls : 'table-index',//table的样式  
		       columns:[[
		    	  	{field : 'id',title : 'id',align : 'center',hidden:true},
		    	  	{field : 'communityName',title : '小区名称',align : 'center'},
					{field : 'placeFloor',title : '所在楼层（层）',align : 'center'},
					{field : 'sumFloor',title : '总层楼（层）', align : 'center'},
					{field : 'floorAge',title : '楼龄（年）', align : 'center'},
					{field : 'area',title : '面积（㎡）', align : 'center'},
					{field : 'estatePropertiesName',title : '房产性质', align : 'center'},
					{field : 'estateOwnershipName',title : '房产权属', align : 'center'},
					{field : 'controlPrice',title : '风控核定价（元）', align : 'center'},
					{field : 'isElevator',title : '是否有电梯', align : 'center',formatter:function(r,v){
						if(v){return '是';}return '否';     
					}},  
					{field : 'isRenovation',title : '是否有装修', align : 'center',formatter:function(r,v){
  						if(v){return '是';}return '否';     
					}},  
					{field : 'houseNo',title : '不动产证号', align : 'center'},
					{field : 'mailingAddress',title : '押品地址', align : 'center',formatter:function(r,v){
  						return r.provinceName+" "+r.cityName+" "+r.districtName;    
					}},    
			] ],
			onDelete:function(index, rowData) {
				//  添加判断
				return true;
			},  
			onLoadSuccess:function() {
			}
			});
		
		$.ZUI.init();
	});
</script>
