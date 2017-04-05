package com.zdsoft.finance.houseassessment.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.houseassessment.entity.HouseEvaluateDetail;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HouseEvaluateDetailRepository.java 
 * @ClassName: HouseEvaluateDetailRepository 
 * @Description: 房产评估实现类
 * @author yangjia 
 * @date 2017年2月18日 下午5:16:25 
 * @version V1.0
 */
public interface HouseEvaluateDetailRepository extends CustomRepository<HouseEvaluateDetail, String>{
	
	/**
	 * 
	 * @Title: queryHouseEvaluateInfo 
	 * @Description: 根据传入参数查询房价评估信息
	 * @author jingyh 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public default List<Map<String,Object>> queryHouseEvaluateInfo(Map<String, Object> param) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT a.house_address  as houseAddress,"); // 地址
		sql.append("       a.sum_price      as sumPrice,"); // 总价
		sql.append("        b.evaluate_price as evaluatePrice,"); // 评估价
		sql.append("        b.create_time    as createTime,"); // 请求时间      
		sql.append("        thc.company_name as evaluateCompany,"); // 评估公司
		sql.append("        b.result_time    as resultTime,"); // 返回时间
		sql.append("        b.on_line        as onlineFlg,"); // 处理标示:0:人工，1：自动
		sql.append("        a.id as evaluateId");
		sql.append("   FROM t_house_evaluate a");
		sql.append("   LEFT JOIN t_house_evaluate_detail b");
		sql.append("     ON a.id = b.evaluate_id");
		sql.append("   LEFT JOIN t_house_company thc");
		sql.append("     on b.evaluate_company = thc.company_code");
		sql.append("  WHERE 1=1 ");
		Map<String, Object> condition = new HashMap<String, Object>();
		if (ObjectHelper.isNotEmpty(param)) {
			if (ObjectHelper.isNotEmpty(param.get("houseKey"))) {
				// 关键字
				sql.append("    and (a.house_address like '%'||:houseKey||'%' "); // 地址
				sql.append("    or a.house_key like '%'||:houseKey||'%' ) ");// 楼盘关键字
				condition.put("houseKey", param.get("houseKey"));
			}
			// 详细地址
			if (ObjectHelper.isNotEmpty(param.get("houseAddress"))) {
				sql.append("    and a.house_address like '%'||:houseAddress||'%' ");// 详细地址
				condition.put("houseAddress", param.get("houseAddress"));
			}
			// 面积
			if (ObjectHelper.isNotEmpty(param.get("houseArea"))) {
				sql.append("    and a.house_area = :houseArea ");// 面积
				condition.put("houseArea", param.get("houseArea"));
			}
			// 省市区
			if (ObjectHelper.isNotEmpty(param.get("provinceName"))) {
				sql.append("    and a.province_name = :provinceName ");// 省
				condition.put("provinceName", param.get("provinceName"));
			}
			if (ObjectHelper.isNotEmpty(param.get("cityName"))) {
				sql.append("    and a.city_name = :cityName ");// 市
				condition.put("cityName", param.get("cityName"));
			}
			if (ObjectHelper.isNotEmpty(param.get("districtName"))) {
				sql.append("    and a.district_name = :districtName ");// 区
				condition.put("districtName", param.get("districtName"));
			}
			// 业务Id
			if (ObjectHelper.isNotEmpty(param.get("businessId"))) {
				sql.append("    and a.biz_id = :businessId ");// 业务Id
				condition.put("businessId", param.get("businessId"));
			}
		}
		// 创建时间排倒序
		sql.append("    ORDER BY a.create_time desc");
		return this.findListMapByCondition(sql.toString(), condition);
	}
	
	 /**
	  * 
	  * @Title: getPriceForCompany 
	  * @Description: 根据房产信息查询评估信息
	  * @author yangjia 
	  * @param map
	  * @return
	  * @throws Exception
	  */
	 public default List<Map<String,Object>> getPriceForCompany(Map<String,Object> map) throws Exception{
		 StringBuffer sql = new StringBuffer(" select a.house_address as houseAddress, a.sum_price as sumPrice, b.evaluate_price as evaluatePrice, ");
		    sql.append(  "  to_char(b.create_time,'yyyy-mm-dd hh24:mi:ss') as createTime,b.evaluate_company as evaluateCompany, ");
		    sql.append(  "  decode(b.on_line,0,to_char(b.result_time,'yyyy-mm-dd hh24:mi:ss'),1,to_char(b.create_time,'yyyy-mm-dd hh24:mi:ss')) as resultTime, ");
		    sql.append(  "  decode(b.on_line,0,'人工查询',1,'自动查询') as isOnLine ");
	        sql.append(  "  from t_house_evaluate a " );
	        sql.append(  "  left join t_house_evaluate_detail b " );
	        sql.append(  "  on a.id = b.evaluate_id ");
	        sql.append(  "  where a.house_address like '%'||'"+map.get("mailingAddress")+"'||'%' ");
	        sql.append(  "  and b.b.house_name like '%'||'"+map.get("communityName")+"'||'%' ");
	        sql.append(  "  and a.province_name = '"+map.get("province")+"' ");
	        sql.append(  "  and a.city_name = '"+map.get("city")+"' ");
	        sql.append(  "  and a.district_name = '"+map.get("district")+"' ");
	       
	        //查询数据	
			return this.findListMapByCondition(sql.toString(), null);
	    }
	 
	 /**
	  * 
	  * @Title: getPriceByBizId 
	  * @Description: 根据押品ID查询房产信息
	  * @author yangjia 
	  * @param map
	  * @return
	  * @throws Exception
	  */
	 public default List<Map<String,Object>> getPriceByBizId(String bizid) throws Exception{
		 
		 StringBuffer sql = new StringBuffer(" select a.house_address as houseAddress, a.sum_price as sumPrice, b.evaluate_price as evaluatePrice, ");
		    sql.append(  "  to_char(b.create_time,'yyyy-mm-dd hh24:mi:ss') as createTime,b.evaluate_company as evaluateCompany, ");
		    sql.append(  "  decode(b.on_line,0,to_char(b.result_time,'yyyy-mm-dd hh24:mi:ss'),1,to_char(b.create_time,'yyyy-mm-dd hh24:mi:ss')) as resultTime, ");
		    sql.append(  "  decode(b.on_line,0,'人工查询',1,'自动查询') as isOnLine ");
	        sql.append(  "  from t_house_evaluate a " );
	        sql.append(  "  left join t_house_evaluate_detail b " );
	        sql.append(  "  on a.id = b.evaluate_id ");
	        sql.append(  "  where a.biz_id = '"+bizid+"' ");
	       
	        //查询数据	
			return this.findListMapByCondition(sql.toString(), null);
	 }
	 
	 /**
	  * 
	  * @Title: findByHouseEvaluateId 
	  * @Description: 根据评估ID查找HouseEvaluateDetail
	  * @author caixiekang 
	  * @param houseEvaluateId 评估ID
	  * @return
	  */
	@Query("from HouseEvaluateDetail hed where hed.evaluateId =:houseEvaluateId and hed.isDeleted=false")
	public List<HouseEvaluateDetail> findByHouseEvaluateId(@Param("houseEvaluateId")String houseEvaluateId);

}
