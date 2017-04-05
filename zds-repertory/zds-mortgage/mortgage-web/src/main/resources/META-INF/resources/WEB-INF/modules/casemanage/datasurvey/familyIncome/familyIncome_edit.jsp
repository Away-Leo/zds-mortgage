<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 家庭收入编辑 -->
<div class="page-box" id="familyIncome_Div">
<form id="familyIncome_form" class="zui-form" method="post" enctype="multipart/form-data">
	<input type="hidden" id="id" name="id" value="${familyIncomeVo.id }"> 
   <div class="page-title">家庭收入</div>
   <div class="p5">
       <table class="table-detail">
           <tr>
           		<td class="td-title pct10"><b class="c-red mr5">*</b>户主名</td>
           		<td class="pct20">
           			<dl class="form-item form-auto">
                       <dd class="detail" id="houseHolderId">
		                            <input class="zui-input zui-validatebox"  type="text" validate-type="Require,Length[0-64]" 
											id="houseHolder" name="houseHolder"  value="${familyIncomeVo.houseHolder}">
									<input type="hidden" id="customerId"  name="customerId" value="${familyIncomeVo.customerId}">
                       </dd>
                   </dl>
           		</td>
           		<td class="td-title pct10">是否为实际用款人流水</td>
           		<td class="pct20">
   					<dl class="form-item form-auto">
                       <dd class="detail"> 
                       		 <input class="zui-combobox" type="hidden" name="isMoney"
                              	data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0049"
                                data-valuefield="fullcode" data-defaultvalue="${familyIncomeVo.isMoney}"
                                data-textfield="name">
                        </dd>
                   </dl>
                </td>
           		<td class="td-title pct10"></td>
           		<td class="pct20"></td>
           </tr>
           </table>
           
           <table class="table-detail">
           <tr>
               	<td class="td-title pct45" style="text-align: center;">月份</td>
               	<td class="td-title pct45" style="text-align: center;">贷方发生额(元)</td>
           </tr>
           <!-- 根据录入时间倒推6个月（不含当月时间） -->
           <c:forEach items="${detailListVo}" var="detailVo" varStatus="status">
           		<tr>
			   		<td class="pct45"  style="text-align: center;">
			   			<dl class="form-item form-auto">
                          <dd class="detail">
                              <label>
                                     <input class="zui-input zui-disabled zui-validatebox" style="text-align:center;"  type="text" validate-type="Require" value="${detailVo.realMonthStr }" readonly="readonly"  id="month_${status.index}">
                                     <input class="zui-input"  type="hidden"  value="${detailVo.realMonth }"  name="month[${status.index}]" >
                              </label>
                          </dd>
                  		</dl>
			   		</td>
			   		<td class="pct45" style="text-align: center;">
			   			<dl class="form-item form-auto">
                          <dd class="detail">
                              <label>
                                  <input class="zui-input zui-validatebox toFloat" type="text" value="${detailVo.realAmount }" validate-type="Require,Digital[7-4]" name="amount[${status.index}]" validate-false="请输入正确的金额!" >
                              </label>
                          </dd>
                  		</dl>
			   		</td>
			   </tr>
           </c:forEach>
		   <tr>
		   		<td class="pct45"  style="text-align: center;">月均收入</td>
		   		<td class="pct45" style="text-align: center;">
		   			<dl class="form-item form-auto">
                        <dd class="detail">
                            <label>
                                <input class="zui-input zui-disabled zui-validatebox" validate-type="Require" type="text" value="${familyIncomeVo.monthAmount }"  name="monthAmount" id="monthAmount" readonly="readonly" placeholder="自动计算">
                            </label>
                        </dd>
               		</dl>
		   		</td>
		   </tr>
		   <tr>
		   		<td class="pct45" style="text-align: center;">半年合计收入</td>
		   		<td class="pct45" style="text-align: center;">
		   			<dl class="form-item form-auto">
                        <dd class="detail">
                            <label>
                                <input class="zui-input zui-disabled zui-validatebox" validate-type="Require" type="text" value="${familyIncomeVo.totalAmount }"  name="totalAmount" id="totalAmount" readonly="readonly" placeholder="自动计算">
                            </label>
                        </dd>
               		</dl>
		   		</td>
		   </tr>
       </table>
   </div>
</form>
</div>
<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.seleter','zd/completer'], 
		function($, CALLBACK) {
			//自动计算金额
			CALLBACK.validateCallBack=function (){
				var monthAmount=0;
			    var totalAmount=0;
				for(var i=0;i<=5;i++){
					var amount=$("input[name='amount["+i+"]']").val();
					if(isNaN(amount)||amount==""){
						amount=0;
					}
					totalAmount=amount*1+totalAmount*1;
				}
				monthAmount=(totalAmount/6).toFixed(4);
				//赋值
				$("#monthAmount").val(monthAmount);
				$("#totalAmount").val(totalAmount.toFixed(4));
			}
			
			$.ZUI.init("#familyIncome_Div");

			//联想查询户主名数据
        	$("#houseHolder").completer({
	            suggest: true,//默认false
	            idField: 'customerId',//默认id,唯一标识字段
	            nameField: 'customerName',//默认name,下拉列表展示数据的字段
	            valueField: 'customerName',//默认value,根据值查询数据的字段
	            placeObj:$("#houseHolderId"),//悬浮框需要定位到的对象
	            url:'<z:ukey key="com.zdsoft.finance.customer.findLikeCustomerName" context="admin"/>&caseApplyId='+caseApplyId ,//请求数据地址
	            writable: true,//默认false，是否可自定义输入
	            complete: function (data) {
	            	$("#customerId").val(data.customerId);
	            },
	            filter: function (val) {
	                return val;//过滤输入的value值
	            }
			});
	});
</script>