<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 押品信息 -->		
<form id="houseProperty_form" class="zui-form " method="post" enctype="multipart/form-data">
	<input type="hidden" id="housePropertyId" name="housePropertyVo.id" value="${housePropertyVo.id }"> 	
	<div class="page-box">
		<div class="page-title">押品信息</div>
		<div class="p5">
			<table class="table-detail">
				<tr>
					<td class="td-title pct10"><b class="c-red mr5">*</b>小区名称</td>
					<td class="pct20">
						<dl class="form-item form-auto">
							<dd class="detail">
								<label> <input type="text" class="zui-input zui-validatebox" value="${housePropertyVo.communityName }" id="communityName" name="housePropertyVo.communityName" validate-type="Require,Length[0-64]"/>
								</label>
							</dd>
						</dl>
					</td>
					<td class="td-title pct10"><b class="c-red mr5">*</b>所在楼层</td>
					<td class="pct20">
						<dl class="form-item form-auto">
							<dd class="detail">
								<label> <input id="placeFloor" type="text" class="zui-input zui-validatebox toInt" value="${housePropertyVo.placeFloor }" name="housePropertyVo.placeFloor" validate-type="Require,Integer,CompareAmount[<=-sumFloor]"
                                   validate-false="||所在楼层不能大于总楼层,请重新输入!"/><front style="font-size: 14px;">层</front>
								</label>
							</dd>
						</dl>
					</td>
					<td class="td-title pct10"><b class="c-red mr5">*</b>总楼层</td>
					<td class="pct30">
						<dl class="form-item form-auto">
							<dd class="detail">
								<label> <input id="sumFloor" type="text" class="zui-input zui-validatebox toInt" value="${housePropertyVo.sumFloor }" name="housePropertyVo.sumFloor" validate-type="Require,Integer,Length[0-4],CompareAmount[>=-placeFloor]"
                                   validate-false="|||总楼层不能小于所在楼层,请重新输入!"/><front style="font-size: 14px;">层</front>
								</label>
							</dd>
						</dl>
					</td>
				</tr>
				<tr>
					<td class="td-title pct10"><b class="c-red mr5">*</b>面积</td>
					<td>
						<dl class="form-item form-auto">
							<dd class="detail">
								<label> <input type="text" class="zui-input zui-validatebox toFloat" id="houseArea" value="${housePropertyVo.area }" name="housePropertyVo.area" validate-type="Require,Digital[6-4]"/><front style="font-size: 14px;">㎡</front>
								</label>
							</dd>
						</dl>
					</td>
					<td class="td-title pct10"><b class="c-red mr5">*</b>房产性质</td>
					<td>
						<dl class="form-item form-auto">
							<dd class="detail">
								<input class="zui-combobox zui-validatebox" type="hidden" name="housePropertyVo.estateProperties"
									data-width="94"
									 data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0057"
									data-callback="change" data-id="isAgriculture"
									data-valuefield="fullcode" data-textfield="name"
									data-defaultvalue="${housePropertyVo.estateProperties }"
									validate-type="Require">
							</dd>
							<dd class="detail">
								<label> <input type="text" class="zui-input nwidth2" value="${housePropertyVo.estatePropertiessOther }" name="housePropertyVo.estatePropertiessOther"/>
								</label>
							</dd>
						</dl>
					</td>
					<td class="td-title pct10"><b class="c-red mr5">*</b>房产权属</td>
					<td>
						<dl class="form-item form-auto">
							<dd class="detail">
								<input class="zui-combobox zui-validatebox" type="hidden" name="housePropertyVo.estateOwnership"
									data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0058"
									data-defaultvalue="${housePropertyVo.estateOwnership}"
									data-valuefield="fullcode" data-textfield="name" validate-type="Require">
							</dd>
						</dl>
					</td>
				</tr>
				<tr>
					<td class="td-title pct10">楼龄</td>
					<td>
						<dl class="form-item form-auto">
							<dd class="detail">
								<label> <input type="text" class="zui-input toInt" value="${housePropertyVo.floorAge }" name="housePropertyVo.floorAge" validate-type="Integer"/><front style="font-size: 14px;">年</front>
								</label>
							</dd>
						</dl>
					</td>
					<td class="td-title pct10"></td>
					<td></td>
					<td class="td-title pct10"></td>
					<td></td>
				</tr>
				<tr>
	               <td class="td-title pct10"><b class="c-red mr5">*</b>押品地址</td>
	               <td class="pct20" colspan="5">
	                   <dl class="form-item form-auto">
	                       <dd class="detail">
	                           <input type="hidden" id="collateralProvince" name="housePropertyVo.province" value="${housePropertyVo.province }"/>
	                           <input type="hidden" id="collateralCity" name="housePropertyVo.city" value="${housePropertyVo.city }"/>
	                           <input type="hidden" id="collateralDistrict" name="housePropertyVo.district" value="${housePropertyVo.district }"/>
	                           <div id="selectAddress_collateral" data-code="${housePropertyVo.district }">
	                               <input id="address_collateral_text" class="zui-input zui-validatebox" type="text" readonly="true" style="width: 260px;" validate-type="Require"/>
	                               <input id="address_collateral_code" type="hidden" value=""/>
	                           </div>
	                       </dd>
	                       <dd class="detail">
	                           <input class="zui-input zui-validatebox" validate-type="Require,Length[0-128]" value="${housePropertyVo.mailingAddress }" id="mailingAddress" name="housePropertyVo.mailingAddress" style="width: 455px;">
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
					<td>
						<dl class="form-item form-auto">
							<dd class="detail">
		                        <input class="zui-checkbox zui-validatebox" id="radio1" type="hidden" name="housePropertyVo.mortgageSituation" value=""
		                        		data-multiple="false"
		                        		data-width="90"
		                              data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0052"
		                               data-valuefield="fullcode" data-defaultvalue="${empty housePropertyVo.mortgageSituation?'YWDM0051012':housePropertyVo.mortgageSituation }" data-textfield="name" validate-type="Require">
		                    </dd>
						</dl>
					</td>
					
					<td class="td-title pct10">综合评估价</td>
					<td class="pct20" colspan="3">
						<dl class="form-item form-auto">
							<dd class="detail">
							<label>
								<!-- 综合评估价: 当房产评估价为空的时候，也就是没调用综合评估价时，显示房产估价按钮，反之显示“查看估价”和“集团复议”按钮 -->
								<input id="synthesizePriceInfo"  class="zui-input zui-disabled"  name="housePropertyVo.synthesizePrice" 
								value="${housePropertyVo.synthesizePrice == 0 ?'':housePropertyVo.synthesizePrice}" readonly="readonly"/>
								<c:choose>  
								   <c:when test="${empty housePropertyVo.synthesizePrice || housePropertyVo.synthesizePrice <= 0}"> 
										<front style="font-size: 14px;">元&nbsp;&nbsp;</front>
										<button class="btn-blue mr10" id="propertyEvaluation">房产评估</button>
								   </c:when>  
								   <c:otherwise> 
										<front style="font-size: 14px;">元&nbsp;&nbsp;</front>
										<button class="btn-orange mr10" id="checkValuation">查看估价</button>
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

<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.address'], 
		function($, CALLBACK) {
		//综合评估价：房产评估按钮 
		$("#propertyEvaluation").click(function(event){
			event.preventDefault();
			//“房产评估”进入房产试算界面，自动带入押品地址、面前等到房产评评估界面
			var	url = '<z:ukey key="com.zdsoft.finance.houseassessment.houseEvaluate.houseAssessmentQuery" context="admin"/>'
				+'bizId='+$("#housePropertyId").val()+'&houseKey='+$("#communityName").val()+'&houseArea='+$("#houseArea").val()+'&houseAddress=' + $("#mailingAddress").val();
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
            	if(v=="liveCounty"){
            		$("#mailingAddress").val($('#liveAddr').val());
            	}else if(v=="homeDistrict"){
            		$("#mailingAddress").val($('#homePlace').val());
            	}
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
                    }
                });
            }
            $('#address_collateral_text').ZDSValidatebox('validate', $('#address_collateral_text'));//校验 
            $('#mailingAddress').ZDSValidatebox('validate', $('#mailingAddress'));//校验 
        }
	});
</script>