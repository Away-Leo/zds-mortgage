<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 押品信息 -->		
<form id="houseProperty_form" class="zui-form " method="post" enctype="multipart/form-data">
	<input type="hidden" id="housePropertyId" name="housePropertyVo.id"> 	
	<div class="page-box">
	<div class="page-title">押品信息</div>
	<div class="p5">
		<table class="table-detail">
			<tr>
				<td class="td-title pct10"><b class="c-red mr5">*</b>小区名称</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail f12">
							<label>
							${housePropertyVo.communityName }
							</label>
						</dd>
					</dl>
				</td>
				<td class="td-title pct10"><b class="c-red mr5">*</b>所在楼层</td>
				<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail f12">
							<label> 
							${housePropertyVo.placeFloor }
							</label>
						</dd>
					</dl>
				</td>
				<td class="td-title pct10"><b class="c-red mr5">*</b>总楼层</td>
				<td class="pct30">
					<dl class="form-item form-auto">
						<dd class="detail f12">
							<label> 
							${housePropertyVo.sumFloor }
							</label>
						</dd>
					</dl>
				</td>
			</tr>
			<tr>
				<td class="td-title pct10"><b class="c-red mr5">*</b>面积</td>
				<td>
					<dl class="form-item form-auto">
						<dd class="detail f12">
							<label>${housePropertyVo.area }
								<front style="font-size: 14px;">㎡</front>
							</label>
						</dd>
					</dl>
				</td>
				<td class="td-title pct10"><b class="c-red mr5">*</b>房产性质</td>
				<td>
					<dl class="form-item form-auto">
						<dd class="detail f12">
							${housePropertyVo.estatePropertiesName }
						</dd>
						<dd class="detail f12">
						&nbsp;
						&nbsp;
						&nbsp;
							${housePropertyVo.estatePropertiessOther }
						</dd>
					</dl>
				</td>
				<td class="td-title pct10"><b class="c-red mr5">*</b>房产权属</td>
				<td>
					<dl class="form-item form-auto">
						<dd class="detail f12">
							${housePropertyVo.estateOwnershipName}
						</dd>
					</dl>
				</td>
			</tr>
			<tr>
				<td class="td-title pct10">楼龄</td>
				<td>
					<dl class="form-item form-auto">
						<dd class="detail f12">
							<label>
								<c:if test="${not empty housePropertyVo.floorAge }">
									${housePropertyVo.floorAge }<front style="font-size: 14px;">年</front> 
								</c:if>
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
                       <dd class="detail f12">
                       ${housePropertyVo.provinceName }/${housePropertyVo.cityName }/${housePropertyVo.district }
                       </dd>
                       <dd class="detail f12">
                       &nbsp;
                       &nbsp;
                       &nbsp;
                       &nbsp;
                       ${housePropertyVo.mailingAddress }
                       </dd>
                   </dl>
               </td>
           </tr>
			<tr>
				<td class="td-title pct10"><b class="c-red mr5">*</b>抵押情况(第N抵)</td>
				<td>
					<dl class="form-item form-auto">
						<dd class="detail f12">
						${housePropertyVo.mortgageSituationName }
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
