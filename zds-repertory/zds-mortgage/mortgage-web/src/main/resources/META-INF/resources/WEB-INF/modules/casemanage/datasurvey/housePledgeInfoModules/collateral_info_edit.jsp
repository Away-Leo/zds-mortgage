<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 押品信息 -->		
<div id="housePropertyDiv">
<form id="houseProperty_form" class="zui-form " method="post" enctype="multipart/form-data">
	<input type="hidden" value="${housePropertyVo.id }" id="houseId" name="id">
	<div class="page-box">
	<div class="page-title">押品信息</div>
	<div class="p5">
		<table class="table-detail">
			<tr>
				<td class="td-title pct10"><b class="c-red mr5">*</b>小区名称</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail">
							<label> 
								<input type="text" class="zui-input zui-validatebox" id="communityName" name="communityName" value="${housePropertyVo.communityName }" validate-type="Require"/>
							</label>
						</dd>
					</dl>
				</td>
				<td class="td-title pct10"><b class="c-red mr5">*</b>所在楼层</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail">
							<label> <input type="text" id="placeFloor" class="zui-input zui-validatebox toInt" name="placeFloor" value="${housePropertyVo.placeFloor }"  validate-type="Require,Integer,CompareAmount[<=-sumFloor]"
                                   validate-false="||所在楼层不能大于总楼层,请重新输入!" /><front style="font-size: 14px;">层</front>
							</label>
						</dd>
					</dl>
				</td>
				<td class="td-title pct10"><b class="c-red mr5">*</b>总楼层</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail">
							<label> <input type="text" id="sumFloor" class="zui-input zui-validatebox toInt" name="sumFloor" value="${housePropertyVo.sumFloor }" validate-type="Require,Integer,CompareAmount[>=-placeFloor]"
                                   validate-false="||总楼层不能小于所在楼层,请重新输入!"/><front style="font-size: 14px;">层</front>
							</label>
						</dd>
					</dl>
				</td>
			</tr>
			<tr>
				<td class="td-title pct10"><b class="c-red mr5">*</b>楼龄</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail">
							<label> <input type="text" class="zui-input zui-validatebox toInt" name="floorAge" value="${housePropertyVo.floorAge }" validate-type="Require"/><front style="font-size: 14px;">年</front>
							</label>
						</dd>
					</dl>
				</td>
				<td class="td-title pct10"><b class="c-red mr5">*</b>面积</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail">
							<label> <input type="text" class="zui-input zui-validatebox toFloat" id="houseArea" name="area" value="${housePropertyVo.area }" validate-type="Require,Digital[6-4]"/><front style="font-size: 14px;">㎡</front>
							</label>
						</dd>
					</dl>
				</td>
				<td class="td-title pct10"><b class="c-red mr5">*</b>房产性质</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail">
							<input class="zui-combobox zui-validatebox" type="hidden" name="estateProperties"
								data-width="94"
								 data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0057"
								data-callback="change" data-id="isAgriculture"
								data-valuefield="fullcode" data-textfield="name"
								data-defaultvalue="${housePropertyVo.estateProperties }"
								validate-type="Require">
						</dd>
						<dd class="detail">
							<label> <input type="text" class="zui-input nwidth2" value="${housePropertyVo.estatePropertiessOther }" name="estatePropertiessOther"/>
							</label>
						</dd>
					</dl>
				</td>
			</tr>
			<tr>
				<td class="td-title pct10"><b class="c-red mr5">*</b>房产权属</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail">
							<input class="zui-combobox zui-validatebox" type="hidden" name="estateOwnership"
								data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0058"
								data-defaultvalue="${housePropertyVo.estateOwnership}"
								data-valuefield="fullcode" data-textfield="name" validate-type="Require">
						</dd>
					</dl>
				</td>
				<td class="td-title pct10"><b class="c-red mr5">*</b>中介询价</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail">
							<label>
		                       <input type="text" class="zui-input width1 zui-validatebox toFloat" name="intermediaryInquiry" validate-type="Require,Digital[15-2]" value="${housePropertyVo.intermediaryInquiry}">
							   <front style="font-size: 14px;">元</front>
		                    </label>
						</dd>
					</dl>
				</td>
				<td class="td-title pct10"><b class="c-red mr5">*</b>网络询价</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail">
							<label>
		                       <input type="text" class="zui-input width1 zui-validatebox toFloat" name="networkInquiry" value="${housePropertyVo.networkInquiry }" validate-type="Require,Digital[15-2]">
								<front style="font-size: 14px;">元</front>
		                    </label>
						</dd>
					</dl>
				</td>
			</tr>
			<tr>
				<td class="td-title pct10"><b class="c-red mr5">*</b>风控核定价</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail">
							<label>
		                       <input type="text" class="zui-input width1 zui-validatebox toFloat" name="controlPrice" value="${housePropertyVo.controlPrice }" validate-type="Require,Digital[15-2]">
								<front style="font-size: 14px;">元</front>
		                    </label>
						</dd>
					</dl>
				</td>
				<td class="td-title pct10"><b class="c-red mr5">*</b>评估价</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail">
							<label>
		                       <input type="text" class="zui-input width1 zui-validatebox toFloat" name="evaluatingPrice" value="${housePropertyVo.evaluatingPrice }" validate-type="Require,Digital[15-2]">
								<front style="font-size: 14px;">元</front>
		                    </label>
						</dd>
					</dl>
				</td>
				<td class="td-title pct10">风控复议价</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail">
							<label>
		                       <input type="text" class="zui-input width1 zui-disabled" name="controlReviewPrice" value="${housePropertyVo.controlReviewPrice }" readonly="readonly,Digital[15-2]" />
								<front style="font-size: 14px;">元</front>
		                    </label>
						</dd>
					</dl>
				</td>
			</tr>
			<tr>
				<td class="td-title pct10"><b class="c-red mr5">*</b>是否有电梯</td>
				<td class="pct20">
					<dl class="form-item form-auto">
                       <dd class="detail">
                               <input class="zui-combobox zui-validatebox" type="hidden"
                                      data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0049"
                                      id="isElevator" name="isElevator" data-valuefield="fullcode"
                                      data-textfield="name" data-defaultvalue="${housePropertyVo.isElevator }"
                                      validate-type="Require">
                       </dd>
                   </dl>
				</td>
				<td class="td-title pct10"><b class="c-red mr5">*</b>居住状态</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail">
							<input class="zui-combobox zui-validatebox" type="hidden" name="livingState"
								data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0076"
								data-defaultvalue="${housePropertyVo.livingState}"
								data-valuefield="fullcode" data-textfield="name" validate-type="Require">
						</dd>
					</dl>
				</td>
				<td class="td-title pct10"><b class="c-red mr5">*</b>是否装修</td>
				<td class="pct20">
					<dl class="form-item form-auto">
                       <dd class="detail">
                               <input class="zui-combobox zui-validatebox" type="hidden"
                                      data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0049"
                                      id="isRenovation" name="isRenovation" data-valuefield="fullcode"
                                      data-textfield="name" data-defaultvalue="${housePropertyVo.isRenovation }"
                                      validate-type="Require">
                       </dd>
                   </dl>
				</td>
			</tr>
			<tr>
				<td class="td-title pct10"><b class="c-red mr5">*</b>不动产证号</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail">
							<label>
               					<input type="text" class="zui-input zui-validatebox" name="houseNo" value="${housePropertyVo.houseNo }" validate-type="Require,Length[1-32]"/>
           					</label>
						</dd>
					</dl>
				</td>
				<td class="td-title pct10"></td>
				<td class="pct20">
				</td>
				<td class="td-title pct10"></td>
				<td class="pct20">
				</td>
			</tr>
			<tr>
				<td class="td-title pct10"><b class="c-red mr5">*</b>押品地址</td>
               	<td class="pct20" colspan="5">
                   <dl class="form-item form-auto">
                       <dd class="detail">
                           <input type="hidden" id="collateralProvince" name="province" value="${housePropertyVo.province }"/>
                           <input type="hidden" id="collateralCity" name="city" value="${housePropertyVo.city }"/>
                           <input type="hidden" id="collateralDistrict" name="district" value="${housePropertyVo.district }"/>
                           <div id="selectAddress_collateral" data-code="${housePropertyVo.district }">
                               <input id="address_collateral_text" class="zui-input zui-validatebox" type="text" readonly="true" style="width: 260px;"  value=""  validate-type="Require"/>
                           </div>
                       </dd>
                       <dd class="detail">
                           <input class="zui-input zui-validatebox" type="text"  validate-type="Length[1-128],Require" id="mailingAddress" value="${housePropertyVo.mailingAddress }" name="mailingAddress" style="width: 455px;">
                       </dd>
                       <dd class="detail">
                       		<input class="zui-combobox" type="hidden"
                                   data-data="[{'id':'liveCounty','text':'复制户籍地址'},{'id':'homeDistrict','text':'复制家庭地址'}]"
                                   id="copyAddress"  data-valuefield="id" data-callback="collateralCopyAddressValue"
                                   data-textfield="text" 
                             >
                       </dd>
                   </dl>
               </td>
			</tr>
			<tr>
				<td class="td-title pct10"><b class="c-red mr5">*</b>抵押情况(第N抵)</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail">
							<input class="zui-checkbox zui-validatebox" type="hidden"
	                                      data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0052"
	                                      id="isRenovation" name="mortgageSituation" data-valuefield="fullcode"
	                                      data-textfield="name" data-defaultvalue="${empty housePropertyVo.mortgageSituation?'YWDM0051012':housePropertyVo.mortgageSituation }"
	                                      validate-type="Require">
	                    </dd>
					</dl>
				</td>
				<td class="td-title pct10">综合评估价</td>
				<td class="pct20" colspan="3">
					<dl class="form-item form-auto">
						<dd class="detail">
						<label>
<!-- 综合评估价: 当房产评估价为空的时候，也就是没调用综合评估价时，显示房产估价按钮，反之显示“查看估价”和“集团复议”按钮 -->
							<c:choose>  
							   <c:when test="${empty housePropertyVo.synthesizePrice || housePropertyVo.synthesizePrice <= 0}"> 
									<input type="text" id="synthesizePriceInfo"  class="zui-input zui-disabled" disable name="synthesizePrice" value="" readonly="readonly"/>
									<front style="font-size: 14px;">元&nbsp;&nbsp;</front>
									<button class="btn-blue mr10" id="propertyEvaluation">房产评估</button>
							   </c:when>  
							   <c:otherwise> 
							   		<input type="text" id="synthesizePriceInfo" class="zui-input zui-disabled" name="synthesizePrice" value="${housePropertyVo.synthesizePrice}" readonly="readonly"/>
									<front style="font-size: 14px;">元&nbsp;&nbsp;</front>
									<button class="btn-orange mr10" id="checkValuation">查看估价</button>
									<button class="btn-blue" id="groupReview">集团复议</button>
							   </c:otherwise>  
							</c:choose> 
						</label>
						</dd>
					</dl>
				</td>
			</tr>
		</table>
	</div>
</div>			
</form>
</div>
<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter','zd/jquery.zds.address'], 
		function($, CALLBACK) {
		
		//综合评估价：房产评估按钮 
		$("#propertyEvaluation").click(function(event){
			event.preventDefault();
			//“房产评估”进入房产试算界面，自动带入押品地址、面前等到房产评评估界面
			var	url = '<z:ukey key="com.zdsoft.finance.houseassessment.houseEvaluate.houseAssessmentQuery" context="admin"/>'
			+'bizId='+$("#houseId").val()+'&houseKey='+$("#communityName").val()+'&houseArea='+$("#houseArea").val()+'&houseAddress=' + $("#mailingAddress").val();
			url += "&province=" + $("#collateralProvince").val() + "&city=" + $("#collateralCity").val() + "&district=" + $("#collateralDistrict").val();
			ZDS_MESSAGE_CLIENT.openMenuLink('house_assessment_query', '房产评估', url,{"callBack":"true"});
		});
		// 房产评估回调刷新
		ZDS_MESSAGE_CLIENT.refreshThis = function(data) {
			var datas = JSON.parse(data.args);
			var sumPrice = datas.sumPrice; 
			if (sumPrice != null && sumPrice != "undefined" && sumPrice > 0) {
				$("#synthesizePriceInfo").val(sumPrice);
			}
		}
		//综合评估价：查看估价按钮 
		$("#checkValuation").click(function(event){
			event.preventDefault();
 			//进入兴业贷->押品信息下“查看自动评估界面。 	同时返回这个综合评估价
			ZDS_MESSAGE_CLIENT.openMenuLink('automaticEvaluation', "查看自动评估",'<z:ukey key="com.zdsoft.finance.houseassessment.houseEvaluate.getPriceForCompany" context="admin"/>&bizid=${housePropertyVo.id}');
		});
		//集团复议 按钮 
		$("#groupReview").click(function(event){
			event.preventDefault();
			$.ajax({
	              type: 'post',
	              url: '<z:ukey key="com.zdsoft.finance.casemanage.evaluated.group.checkIsAppealInProcess" context="admin"/>',
	              data: {"housePropertyId":"${housePropertyVo.id}"},
	              dataType: 'json',
	               success: function (data) {
	                  if (data.resultStatus == 0) {
							if(data.id == "T"){
								var doAppealUrl = '<z:ukey key="com.zdsoft.finance.casemanage.evaluated.eidtAppeal" context="admin"/>&caseApplyId='+caseApplyId+'&housePropertyId=${housePropertyVo.id}';
								ZDS_MESSAGE_CLIENT.openMenuLink('groupReview', "申诉",doAppealUrl);
							}else{
								$.ZMessage.error("错误", data.msg, function () {
		                        });
							}
                        }else{
                        	$.ZMessage.error("错误", data.msg, function () {
	                        });
                        }
	                },
				    error: function () {
		            	$.ZMessage.error("错误", "保存信息系统异常，请联系管理员", function () {
		             	});
		            }
              });
 			//进入评估价申述->申述申请 
		});
		
		
		//初始选择器
        $("#selectAddress_collateral").Address({
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
                $('#address_collateral_text').val(str);
                $('#collateralProvince').val(selected_ids[0]);
                $('#collateralCity').val(selected_ids[1]);
                $('#collateralDistrict').val(selected_ids[2]);
            }
        });
		
        CALLBACK.collateralCopyAddressValue=function(v,t){
        	//获取对象的值
        	var objValue=$('#'+v).val();
            if(null!=objValue&&objValue!=""){
            	//赋值街道详细地址
            	if(v="liveCounty"){
            		$("#mailingAddress").val($('#liveAddr').val());
            	}else if(v="homeDistrict"){
            		$("#mailingAddress").val($('#homePlace').val());
            	}
            	 $('#mailingAddress').ZDSValidatebox('validate', $('#mailingAddress'));//校验 
                //用这种方式复制选择项
                $("#selectAddress_collateral").setAddress({
                    showStreet:false,//不显示街道
                    cityUrl:cityUrl,//真实数据源
                    getDataCityUrl:getDataCityUrl,//根据子节点取同级及上级的数据
                    data:objValue,
                    callback:function(infos,selected_ids) {
                        var str = '';
                        for(var i=0;i<infos.length;i++) {
                            if(str==""){
                                str = str+infos[i];
                            }else{
                                str = str+" / "+infos[i];
                            }
                        }
                        
                        //赋值
                        $('#address_collateral_text').val(str);
                        $('#collateralProvince').val(selected_ids[0]);
                        $('#collateralCity').val(selected_ids[1]);
                        $('#collateralDistrict').val(selected_ids[2]);
                        $('#address_collateral_text').ZDSValidatebox('validate', $('#address_collateral_text'));//校验 
                    }
                });
            }
        }
        $.ZUI.init("#housePropertyDiv");
	});
</script>
