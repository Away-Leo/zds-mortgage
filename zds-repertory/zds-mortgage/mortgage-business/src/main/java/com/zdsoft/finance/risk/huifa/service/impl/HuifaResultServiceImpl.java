package com.zdsoft.finance.risk.huifa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.risk.entity.HuifaRequest;
import com.zdsoft.finance.risk.entity.HuifaResult;
import com.zdsoft.finance.risk.huifa.repository.HuifaResultRepository;
import com.zdsoft.finance.risk.huifa.service.HuifaResultService;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HuifaDetailServiceImpl.java 
 * @ClassName: HuifaDetailServiceImpl 
 * @Description: 汇法网详情服务类
 * @author panshm 
 * @date 2017年2月18日 下午2:01:13 
 * @version V1.0
 */
@Service("HuifaResultService")
public class HuifaResultServiceImpl extends BaseServiceImpl<HuifaResult, HuifaResultRepository>
		implements HuifaResultService {

	@Override
	public HuifaResult findByRequestId(String id) throws Exception {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("requestId", id);
		String hql = "";
		hql = "from HuifaResult where requestId=:requestId";
		List<HuifaResult> list = customReposity.findByHql(hql, condition);

		// Map<String, Object> condition = new HashMap<String, Object>();
		// condition.put("requestId", id);
		// StringBuffer sb = new StringBuffer(
		// "select id, success, totalnumber from t_huifa_result t where 1=1 and
		// request_id=:requestId");
		// customReposity.
		// List<HuifaResult> list = customReposity.findBySql(sb.toString(),
		// condition, HuifaResult.class);
		if (null != list && list.size() == 1) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public boolean hasHuifaMessage(Integer type, String name, String code, String businessId) {
		String sql = "SELECT req.stype, req.name, req.code, req.deleted, req.biz_id, res.success";
		sql += " FROM T_HUIFA_REQUEST req"; // 汇法请求
		sql += " inner join T_HUIFA_RESULT res"; // 请求结果
		sql += "   on req.id = res.request_id";
		sql += " WHERE 1 = 1";
		sql += "  AND req.deleted = 'N'"; // 未删除
		sql += "  AND res.success = 's' "; // 成功
		Map<String, Object> params = new HashMap<String, Object>();
		Long result = 0L;
		if (ObjectHelper.isNotEmpty(businessId)) {
			// 业务Id不为空，优先使用业务Id进行计算
			String newSql = sql + "  AND req.biz_id = :businessId ";
			params.put("businessId", businessId);
			result = this.customReposity.countAllSql(newSql, params);
		}
		if (result > 0) {
			return true;
		}
		if (ObjectHelper.isNotEmpty(type)) {
			params.clear(); // 清空
			if (HuifaRequest.STYPE_COMPANY.equals(type) && ObjectHelper.isNotEmpty(name)) {
				// 企业
				String newSql = sql + " and req.stype = :type";
				newSql += " and req.name = :companyName ";
				params.put("type", HuifaRequest.STYPE_COMPANY);
				params.put("companyName", name);
				result = this.customReposity.countAllSql(newSql, params);
			} else if (HuifaRequest.STYPE_PERSONAL.equals(type) && ObjectHelper.isNotEmpty(code)) {
				// 个人
				String newSql = sql + " and req.stype = :type";
				newSql += " and req.code = :personalCode ";
				params.put("type", HuifaRequest.STYPE_PERSONAL);
				params.put("personalCode", code);
				result = this.customReposity.countAllSql(newSql, params);
			}
			if (result > 0) {
				return true;
			}
		}
		return false;
	}
}
