<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">

<%@ include file='../../common/common_js.jsp'%>
<title>待发送清单列表</title>
</head>
<body>  
<div class="save">
	    <button id="getBack" class="btn-blue mr10">返回</button>
	</div>
	<div class="page-box">
	<div class="p10">
	<div class="page-box">     
		<!-- 跟进催收单 -->
		<div class="page-title">跟进催收单</div> 
		<div class="p5">
		     <table class="table-detail">
		           <tr>
		               <td class="td-title pct10">跟催方式</td>
		               <td class="pct20">
		                   <dl class="form-item form-auto">
		                          <dd class="detail f12">
		                              <label>
		                              </label>
		                          </dd>
		                   </dl>
		               </td>
		               <td class="td-title pct10">跟催状态</td>
		               <td class="pct20">
		                   <dl class="form-item form-auto">
		                       <dd class="detail f12">
		                           <label>
		                           </label>
		                       </dd>
		                   </dl>
		               </td>
		               <td class="td-title pct10">外呼对象</td>
		               <td class="pct20">
		                   <dl class="form-item form-auto">
		                       <dd class="detail f12">
		                           <label id="number">
		                           </label>
		                       </dd>
		                   </dl>
		               </td>
		           </tr>
		           <tr>
		               <td class="td-title pct10">处置预案</td>
		               <td class="pct20">
		                   <dl class="form-item form-auto">
		                          <dd class="detail f12">
		                              <label id="overdueAmount">
		                              </label>
		                          </dd>
		                   </dl>
		               </td>
		               <td class="td-title pct10">子目录</td>
		               <td class="pct20">
		                   <dl class="form-item form-auto">
		                          <dd class="detail f12">
		                              <label>
		                              </label>
		                          </dd>
		                   </dl>
		               </td>
		               <td class="td-title pct10">跟踪部门</td>
		               <td class="pct20">
		                   <dl class="form-item form-auto">
		                          <dd class="detail f12">
		                              <label>
		                              </label>
		                          </dd>
		                   </dl>
		               </td>
		           </tr>
		           <tr>
		               <td class="td-title pct10">预计还款日期</td>
		               <td class="pct20">
		                   <dl class="form-item form-auto">
		                          <dd class="detail f12">
		                              <label id="overdueAmount">
		                              </label>
		                          </dd>
		                   </dl>
		               </td>
		               <td class="td-title pct10">预计下次跟进日期</td>
		               <td class="pct20">
		                   <dl class="form-item form-auto">
		                          <dd class="detail f12">
		                              <label>
		                              </label> 
		                          </dd>
		                   </dl>
		               </td>
		               <td class="td-title pct10">是否发起督办</td>
		               <td class="pct20">
		                   <dl class="form-item form-auto">
		                          <dd class="detail f12">
		                              <label>
		                              </label>
		                          </dd>
		                   </dl>
		               </td>
		           </tr>
		           <tr>
		               <td class="td-title pct10">跟催情况</td> 
		               <td class="pct20" colspan="5">   
		                   <dl class="form-item form-auto">
		                          <dd class="detail f12">  
		                              <label id="overdueAmount">      
		                              <textarea style="width:600%;" class="zui-area zui-validatebox" disabled="">这里是文本域</textarea>
		                              </label>
		                          </dd>
		                   </dl>
		               </td> 
		           </tr>
		           <tr>
		               <td class="td-title pct10">跟催人</td>
		               <td class="pct20">
		                   <dl class="form-item form-auto">
		                          <dd class="detail f12">
		                              <label id="overdueAmount">
		                              </label>
		                          </dd>
		                   </dl>
		               </td>
		               <td class="td-title pct10">跟催日期</td>
		               <td class="pct20">
		                   <dl class="form-item form-auto">
		                          <dd class="detail f12">
		                              <label>
		                              </label> 
		                          </dd>
		                   </dl>
		               </td>   
		               <td class="td-title pct10"></td>
		               <td class="pct20">
		                   <dl class="form-item form-auto">
		                          <dd class="detail f12">
		                              <label>
		                              </label>
		                          </dd>
		                   </dl>
		               </td>
		           </tr>
		     </table>
		</div>  
		<!--  跟进催收单end -->
		<!-- 督办事项 -->
		<div class="page-title">督办事项</div> 
		<div class="p5">
		     <table class="table-detail">
		           <tr>
		               <td class="td-title pct10">督办类型</td>
		               <td class="pct20">
		                   <dl class="form-item form-auto">
		                          <dd class="detail f12">
		                              <label>
		                              </label>
		                          </dd>
		                   </dl>
		               </td>
		               <td class="td-title pct10">督办子类型</td>
		               <td class="pct20">
		                   <dl class="form-item form-auto">
		                       <dd class="detail f12">
		                           <label>
		                           </label>
		                       </dd>
		                   </dl>
		               </td>
		               <td class="td-title pct10"></td>
		               <td class="pct20">
		                   <dl class="form-item form-auto">
		                       <dd class="detail f12">
		                           <label id="number">
		                           </label>
		                       </dd>
		                   </dl>
		               </td>
		           </tr>
		           <tr>
		               <td class="td-title pct10">督办接收人</td>
		               <td class="pct20">
		                   <dl class="form-item form-auto">
		                          <dd class="detail f12">
		                              <label id="overdueAmount">
		                              </label>
		                          </dd>
		                   </dl>
		               </td>
		               <td class="td-title pct10">督办抄送人</td>
		               <td class="pct20">
		                   <dl class="form-item form-auto">
		                          <dd class="detail f12">
		                              <label>
		                              </label>
		                          </dd>
		                   </dl>
		               </td>
		               <td class="td-title pct10">处理时限</td>
		               <td class="pct20">
		                   <dl class="form-item form-auto">
		                          <dd class="detail f12">
		                              <label>
		                              </label>
		                          </dd>
		                   </dl>
		               </td>
		           </tr>
		           <tr>
		               <td class="td-title pct10">督办人</td>
		               <td class="pct20">
		                   <dl class="form-item form-auto">
		                          <dd class="detail f12">
		                              <label id="overdueAmount">
		                              </label>
		                          </dd>
		                   </dl>
		               </td>
		               <td class="td-title pct10">跟踪部门</td>
		               <td class="pct20">
		                   <dl class="form-item form-auto">
		                          <dd class="detail f12">
		                              <label>
		                              </label> 
		                          </dd>
		                   </dl>
		               </td>
		               <td class="td-title pct10"></td>
		               <td class="pct20">
		                   <dl class="form-item form-auto">
		                          <dd class="detail f12">
		                              <label>
		                              </label>
		                          </dd>
		                   </dl>
		               </td>
		           </tr>  
		           <tr>
		               <td class="td-title pct10">备注</td> 
		               <td class="pct20" colspan="5">   
		                   <dl class="form-item form-auto">
		                          <dd class="detail f12">  
		                              <label id="overdueAmount">      
		                              <textarea style="width:600%;" class="zui-area zui-validatebox" disabled="">这里是文本域</textarea>
		                              </label>
		                          </dd>
		                   </dl>
		               </td> 
		           </tr>
		           
		     </table>
		</div>    
		<!--  督办事项end -->
	</div>  
	</div>
	</div>
		<div id="contactsDialog"  style="display: none">
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