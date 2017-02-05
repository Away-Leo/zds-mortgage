<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<!-- 押品信息 -->		
<form id="houseProperty_form" class="zui-form " method="post" enctype="multipart/form-data">
	<input type="hidden" value="${housePropertyVo.id }" name="id">
	<div class="page-box">
	<div class="page-title">押品信息</div>
	<div class="p5">
		<table class="table-detail">
			<tr>
				<td class="td-title pct10"><b class="c-red mr5">*</b>小区名称</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail">
							<label> <input type="text" class="zui-input zui-validatebox" name="communityName" value="${housePropertyVo.communityName }" validate-type="Require"/>
							</label>
						</dd>
					</dl>
				</td>
				<td class="td-title pct10"><b class="c-red mr5">*</b>所在楼层</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail">
							<label> <input type="text" class="zui-input zui-validatebox" name="placeFloor" value="${housePropertyVo.placeFloor }" validate-type="Require"/><front style="font-size: 14px;">层</front>
							</label>
						</dd>
					</dl>
				</td>
				<td class="td-title pct10"><b class="c-red mr5">*</b>总楼层</td>
				<td class="pct30">
					<dl class="form-item form-auto">
						<dd class="detail">
							<label> <input type="text" class="zui-input zui-validatebox" name="sumFloor" value="${housePropertyVo.sumFloor }" validate-type="Require"/><front style="font-size: 14px;">层</front>
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
							<label> <input type="text" class="zui-input zui-validatebox" name="area" value="${housePropertyVo.area }" validate-type="Require"/><front style="font-size: 14px;">㎡</front>
							</label>
						</dd>
					</dl>
				</td>
				<td class="td-title pct10"><b class="c-red mr5">*</b>房产性质</td>
				<td>
					<dl class="form-item form-auto">
						<dd class="detail">
							<input class="zui-combobox zui-validatebox" type="hidden" name="estateProperties" value="${housePropertyVo.estateProperties }"
								data-width="94"
								data-data="[{'id':'0','text':'商品房','isDefault':'true'},{'id':'1','text':'商铺'},{'id':'2','text':'别墅'}]"
								data-callback="change" data-id="isAgriculture"
								data-valuefield="id" data-textfield="text"
								validate-type="Require">
						</dd>
						<dd class="detail">
							<label> <input type="text" class="zui-input nwidth2" name="estatePropertiessOther" value="${housePropertyVo.estatePropertiessOther }"/>
							</label>
						</dd>
					</dl>
				</td>
				<td class="td-title pct10"><b class="c-red mr5">*</b>房产权属</td>
				<td>
					<dl class="form-item form-auto">
						<dd class="detail">
							<input class="zui-combobox zui-validatebox" type="hidden" name="estateOwnership" value="${housePropertyVo.estateOwnership }"
								data-data="[{'id':'0','text':'自有'},{'id':'1','text':'共有'}]"
								data-valuefield="id" data-textfield="text" validate-type="Require">
						</dd>
					</dl>
				</td>
			</tr>
			<tr>
				<td class="td-title pct10">楼龄</td>
				<td>
					<dl class="form-item form-auto">
						<dd class="detail">
							<label> <input type="text" class="zui-input" name="floorAge" value="${housePropertyVo.floorAge }"/><front style="font-size: 14px;">年</front>
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
                           <input type="hidden" id="collateralProvince" name="province" value="${housePropertyVo.province }"/>
                           <input type="hidden" id="collateralCity" name="city" value="${housePropertyVo.city }"/>
                           <input type="hidden" id="collateralDistrict" name="district" value="${housePropertyVo.district }"/>
                           <div id="selectAddress_collateral" data-code="${housePropertyVo.district }">
                               <input id="address_collateral_text" class="zui-input zui-validatebox" type="text" readonly="true" style="width: 260px;"  value=""/>
                           </div>
                       </dd>
                       <dd class="detail">
                           <input class="zui-input zui-validatebox" validate-type="Length[0-128]" value="${housePropertyVo.mailingAddress }" name="mailingAddress" style="width: 455px;">
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
	                        <input class="zui-checkbox zui-validatebox" id="radio1" type="hidden" name="mortgageSituation" value="${housePropertyVo.mortgageSituation }"
	                        		data-multiple="false"
	                        		data-width="90"
	                               data-data="[{'id':'0','text':'1抵'},{'id':'1','text':'2抵'},{'id':'2','text':'3抵'},{'id':'3','text':'其他'}]"
	                               data-valuefield="id" data-textfield="text" validate-type="Require">
	                    </dd>
					</dl>
				</td>
				
				<td class="td-title pct10"></td>
				<td></td>
				<td class="td-title pct10"></td>
				<td></td>
				
			</tr>
		</table>
	</div>
	
</div>			
</form>
<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter','zd/jquery.zds.address'], 
		function($, CALLBACK) {
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
        }
	});
</script>
