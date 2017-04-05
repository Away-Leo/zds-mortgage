<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>

<div id="gartDialogDiv">
	<div id="InfoDialog" >
	        <div class="p10">
				<div class="info-tab">
	                <ul class="tabs">
	                    <li id="terminal_tab_btn" class="tabs-on" formatter="terminalTab"><a href="javascript:void(0);">终端</a></li>
	                    <li formatter="evaluationTab"><a href="javascript:void(0);">评估公司</a></li>
	                    <li formatter="otherCooperTab"><a href="javascript:void(0);">其他合作单位</a></li>
	                </ul>
	                
	                <div class="tabcontents">
	                
	                    <div id="terminalInfo" name="terminalFullName" cooperType="TERMINAL">
	                        <div id="terminalSearchForm" class="p5">
						        <form id="terminal_search" class="zui-form" action="javascript:void(0);" zdata-options="{}">
						            <dl class="form-item">
						                <dt class="title">终端名称</dt>
						                <dd class="detail">
						                    <label>
						                        <input class="zui-input" id="terminalFullName" name="terminalFullName|LK|S">
						                    </label>
						                </dd>
						            </dl>
									<dl class="form-item">
						                <dt class="title">终端类别</dt>
						                <dd class="detail">
						                    <label>
						                        <input class="zui-combobox" id="terminalType" name="terminalType|E|S" type="hidden"
						                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM0081"
						                              data-valuefield="fullcode" data-textfield="name" >
						                    </label>
						                </dd>
						            </dl>
						        </form>
					            <div class="form-btn">
						         	<a href="javaScript:void(0)" class="btn-blue queryBtn" id="terminal-query-btn">查询</a>
						         	 <button class="btn-gray" id="reset-btn">重置</button>
					   		    </div>
						    </div>
						    
						     <div class="page-box">
								<div class="p10">
									<div id="terminal-table"></div>
								</div>
							</div>
	                    </div>
	                    
	                    <div id="evaluationInfo" class="hide"  name="companyName" cooperType="EVALUATION">
	                       <div id="evaluationSearchForm" class="p5">
						        <form id="evaluation_search" class="zui-form" action="javascript:void(0);" zdata-options="{}">
						            <dl class="form-item">
						                <dt class="title">评估公司名称</dt>
						                <dd class="detail">
						                    <label>
						                    	<input class="zui-input" id="companyName" name="zeov|companyName|LK|S">
						                    </label>
						                </dd>
						            </dl>
						        </form>
					            <div class="form-btn">
						         	<a href="javaScript:void(0)" class="btn-blue queryBtn"  id="evaluation-query-btn">查询</a>
						         	 <button class="btn-gray" id="reset-btn"  >重置</button>
					   		    </div>
						    </div>
						    <div class="p10">
						    	<div id="evaluation-table"></div>
							</div>
	                    </div>
	                    
	                    <div id="otherCooperaterInfo" class="hide" name="contactCompanyName" cooperType="OTHER">
	                        <div id="otherCooperaterSearchForm" class="p5">
						        <form id="otherCooperater_search" class="zui-form" action="javascript:void(0);" zdata-options="{}">
						            <dl class="form-item">
						                <dt class="title">合作单位名称</dt>
						                <dd class="detail">
						                    <label>
						                        <input class="zui-input" type="text"  name="companyName|LK|S">
						                    </label>
						                </dd>
						            </dl>
									<dl class="form-item">
						                <dt class="title">地址</dt>
						                <dd class="detail">
						                   <input  class="zui-input"  type="hidden" id="detailedProvince" name="addProvince|E|S" />
				                           <input class="zui-input"  type="hidden" id="detailedCity" name="addCity|E|S"/>
				                           <input class="zui-input"  type="hidden" id="detailedDistrict" name="addCountry|E|S"/>
				                           <div id="selectAddress_otherCoop">
				                               <input id="address_otherCoop_text" class="zui-input zui-validatebox" type="text" readonly="true" style="width: 260px;"/>
				                           </div>
						                </dd>
						            </dl>
						        </form>
					            <div class="form-btn">
					            	<a href="javaScript:void(0)" class="btn-blue queryBtn"  id="otherCooperater-query-btn">查询</a>
						         	 <button class="btn-gray" id="reset-btn">重置</button>
					   		    </div>
						    </div>
						    <div class="p10">
						     	<div id="otherCooperater-table"></div>
							</div>
	                    </div>
	                
	                </div>
	            </div>
		</div>
	</div>
</div>


<script type="text/javascript">

var  callbackParam={};

seajs.use(['jquery','zd/jquery.zds.page.callback','zd/switch', 'zd/jquery.zds.dialog','zd/jquery.zds.table', 'zd/jquery.zds.form','zd/jquery.zds.seleter','zd/jquery.zds.address'],
		function ($,CALLBACK,Switch,Zdialog) {
		var table_obj={"flag":"terminal","name":"terminalFullName"};
		
		// 对话框初始化
	    $("#gartDialogDiv").Zdialog({
	    	width : 800,
			height : 600,
			closed : true,
			isDestroy: true,
			title:'合作方选择',
			buttons: [{
				id: 'message-btn',
				text: '确认', 
				buttonCls: 'btn-blue',
				handler: function () {
					var row=$("#"+table_obj.flag+"-table").ZTable("getSelections");
					var bool=false;
					var hiddenId="";
					var hiddenName="";
					for(var e in row){
						if(bool){
							hiddenId+=",";
							hiddenName+=",";
						}
						
						hiddenId+=row[e].id;
						hiddenName+=row[e][table_obj.name];
						bool=true;
					}
					$("#"+callbackParam.hiddenId).val(hiddenId);
					$("#"+callbackParam.hiddenName).val(hiddenName);
					$("#gartDialogDiv").Zdialog("close");
				}
			},{
				id: 'cancel-btn',
				text: '取消',
				buttonCls: 'btn-gray',
				handler: function () {
					 $("#gartDialogDiv").Zdialog("close");
				}
			}]
		});
		
	    
	    $("#gartDialogDiv").Zdialog("open");
		
	    
	    CALLBACK.terminalTab = function () {
	    	table_obj={"flag":"terminal","name":"terminalFullName"};
	    	 $('#terminal-table').ZTable({
		   		 url : '<z:ukey key="com.zdsoft.finance.cooperator.getCooperator" context="admin"/>&jsoncallback=?',
		   		 singleSelect: false,//表格行是单选还是多选
	         	 isRowNum: true,//是否显示行号
	        	 pagination: true,//表格是否分页
	        	 tableCls: 'table-index',//table的样式
		         columns:[[
						{field : 'terminalTypeName',title : '终端类别',align : 'center'},
		        	 	{field : 'terminalFullName',title : '终端名称',align : 'center'}
		              ]]
			});
	    	 
	  	    $('#terminal-query-btn').on('click',function(){
	     		var formArray=$("#terminal_search").serialize();
	     			formArray=decodeURIComponent(formArray, true);
	     		$('#terminal-table').ZTable("reload",formArray);
	     	});
	    };
	    
	    $('.tabcontents button[class=btn-gray]').each(function(){
 	    	$(this).click(function(){
 	    		$('.tabcontents .zui-input').val("");
 				$('.tabcontents .zui-combobox').val("");
 				$('.tabcontents .zui-combobox').ZCombobox('setValue','');
 	    	});
      	});
	    
	    CALLBACK.evaluationTab = function () {//评估公司
	    	table_obj={"flag":"evaluation","name":"companyName"};
		    	$('#evaluation-table').ZTable({
		    	     url : '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.feeinfomation.findEvaluationInfos" context="admin"/>',
	                singleSelect : false,
	                isRowNum : true,
	                pagination : true,
	                tableCls : 'table-index',//table的样式
	                columns:[[
						{field : 'companyTypeName',title : '类别',align : 'center'},
						{field : 'companyName',title : '评估公司名称',align : 'center'}
					] ]
	      	});
	    
		    	 $('#evaluation-query-btn').on('click',function(){
			     		var formArray=$("#evaluation_search").serialize();
			     			formArray=decodeURIComponent(formArray, true);
			     		$('#evaluation-table').ZTable("reload",formArray);
			     	});
        };

        CALLBACK.otherCooperTab = function () {//其他合作单位
        	table_obj={"flag":"otherCooperater","name":"companyName"};
        	$('#otherCooperater-table').ZTable({
	    	     url : '<z:ukey key="com.zdsoft.finance.otherCooperater.getOtherCooperater" context="admin"/>&jsoncallback=?',
               singleSelect : false,
               isRowNum : true,
               pagination : true,
               tableCls : 'table-index',//table的样式
               columns:[[
					{field : 'companyBelongRelevanceName',title : '类别',align : 'center'},
					{field : 'companyName',title : '合作单位名称',align : 'center'},
					{field : 'clientNm',title : '地址',align : 'center'}
				] ]
     		});
        
        	 $('#otherCooperater-query-btn').on('click',function(){
 	     		var formArray=$("#otherCooperater_search").serialize();
 	     			formArray=decodeURIComponent(formArray, true);
 	     		$('#otherCooperater-table').ZTable("reload",formArray);
 	     	});
        	 
        	//初始地址选择器
     	    $("#selectAddress_otherCoop").Address({
     	    	showStreet:false,//不显示街道
     	        cityUrl:cityUrl,//真实数据源
     	        getDataCityUrl:getDataCityUrl,//根据子节点取同级及上级的数据
     	        callback:function(infos,selected_ids) {
     	            var str = '';
     	            for(var i=0;i<infos.length;i++) {
     	                if(str==""){
     	                    str = str+infos[i];
     	                }else{
     	                    str = str+" / "+infos[i];
     	                }
     	            }
     	            $('#address_otherCoop_text').val(str);
     	            $('#detailedProvince').val(selected_ids[0]);
     	            $('#detailedCity').val(selected_ids[1]);
     	            $('#detailedDistrict').val(selected_ids[2]);
     	        }
     	    });
        };
        
        $.ZUI.init("#InfoDialog");	//初始化
        CALLBACK.terminalTab();
});
</script>
