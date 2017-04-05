<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">

<%@ include file='../../common/common_js.jsp'%>
<title>案件详情</title>
</head>
<body>
	<div class="save">
	    <button id="getBack" class="btn-blue mr10">返回</button>
	</div>    
	<div class="page-box">
	<div class="p10">
	<div class="page-box">  
		<!-- 查询区域 -->
		<div class="page-title">案件信息</div>
		<div class="p5">
		     <table class="table-detail">
		           <tr>
		               <td class="td-title pct10">案件号</td>
		               <td class="pct20">
		                   <dl class="form-item form-auto">
		                          <dd class="detail f12">
		                              <label>
		                              ${param.caseApplyCode}
		                              </label>
		                          </dd>
		                   </dl>
		               </td>
		               <td class="td-title pct10">机构</td>
		               <td class="pct20">
		                   <dl class="form-item form-auto">
		                       <dd class="detail f12">
		                           <label>
		                               ${param.mechanismName}
		                           </label>
		                       </dd>
		                   </dl>
		               </td>
		               <td class="td-title pct10">子产品</td>
		               <td class="pct20">
		                   <dl class="form-item form-auto">
		                       <dd class="detail f12">
		                           <label id="number">
		                               ${param.productSubtypeName}
		                           </label>
		                       </dd>
		                   </dl>
		               </td>
		           </tr>
		           <tr>
		               <td class="td-title pct10">主借人</td>
		               <td class="pct20">
		                   <dl class="form-item form-auto">
		                          <dd class="detail f12">
		                              <label id="overdueAmount">
		                              ${param.mainBorrower}
		                              </label>
		                          </dd>
		                   </dl>
		               </td>
		               <td class="td-title pct10">联系方式</td>
		               <td class="pct20">
		                   <dl class="form-item form-auto">
		                          <dd class="detail f12">
		                              <label>
		                            	  ${param.phoneNumber}
		                              </label>
		                          </dd>
		                   </dl>
		               </td>
		               <td class="td-title pct10">申请金额</td>
		               <td class="pct20">
		                   <dl class="form-item form-auto">
		                          <dd class="detail f12">
		                              <label>
			                              ${param.applyAmount}
		                              </label>元
		                          </dd>
		                   </dl>
		               </td>
		           </tr>
		           <tr>
		               <td class="td-title pct10">逾期金额</td>
		               <td class="pct20">
		                   <dl class="form-item form-auto">
		                          <dd class="detail f12">
		                              <label>
		                              	${param.overdueAmount}
		                              </label>元   
		                          </dd>
		                   </dl>
		               </td>
		               <td class="td-title pct10">逾期日期</td>
		               <td class="pct20">
		                   <dl class="form-item form-auto">
		                          <dd class="detail f12">
		                              <label>
		                               	${param.overdueDate}
		                              </label>
		                          </dd>
		                   </dl>
		               </td>
		               <td class="td-title pct10">逾期天数</td>
		               <td class="pct20">
		                   <dl class="form-item form-auto">
		                          <dd class="detail f12">
		                              <label>
		                               	${param.overdueDay}
		                              </label>天
		                          </dd>
		                   </dl>
		               </td>
		           </tr>
		     </table>
		</div>
	</div>
		<!-- 案件列表 -->
		<!-- begin -->   
			<%@ include file="bondsman_list.jsp"%>
		<!-- end -->
		<!-- 押品列表 -->
		<!-- begin -->
			<%@ include file="collateral_list.jsp"%>
		<!-- end -->
		<!-- 最近跟催列表列表 --> 
		<!-- begin -->
			<%@ include file="lately_followInfo_list.jsp"%>  
		<!-- end -->  
		</div>
		</div>
		<script type="text/javascript">
		var productUrl =  '<z:ukey key="com.cnfh.rms.businessProduct.findByCategoryId" context="admin"/>&jsoncallback=?';
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK,ZTOOL) {
				//返回
				$("#getBack").click(function(){
					ZDS_MESSAGE_CLIENT.refreshOpenner();
		         	ZDS_MESSAGE_CLIENT.closeSelf();
				});
				
			
				$.ZUI.init();
				});
		</script>
</body>
</html>