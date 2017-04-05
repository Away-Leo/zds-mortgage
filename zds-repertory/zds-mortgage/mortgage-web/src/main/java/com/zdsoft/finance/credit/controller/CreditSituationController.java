package com.zdsoft.finance.credit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.basedata.AttachmentDto;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.credit.entity.CreditSituation;
import com.zdsoft.finance.credit.service.CreditSituationService;
import com.zdsoft.finance.credit.vo.CreditSituationVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MyCreditController.java
 * @ClassName: MyCreditController
 * @Description: 征信综合情况
 * @author zhongyong
 * @date 2017年2月23日 下午4:00:15
 * @version V1.0
 */
@Controller
@RequestMapping("creditSituation")
public class CreditSituationController extends BaseController {

	@Autowired
	private CreditSituationService creditSituationService;
	
	@Autowired
	private CED CED ;

	/**
	 * @Title: creditEntering
	 * @Description: 进入征信录入页面
	 * @author zhongyong
	 * @param req
	 * @param caseApplyId
	 *            案件id
	 * @param customerId
	 *            客户id
	 * @param customerName
	 *            客户姓名
	 * @param creditId
	 *            案件客户征信id
	 * @return
	 */
	@RequestMapping("/edit")
	@UriKey(key = "com.zdsoft.finance.credit.creditSituation.edit")
	public ModelAndView edit(HttpServletRequest req, String caseApplyId, String customerId,
			String customerName, String creditId) {
		ModelMap model = new ModelMap();
		try {
			// 获取图片附件
			List<AttachmentDto> atts = creditSituationService.findAttachments(creditId);
			model.put("atts", atts);
			model.put("size", atts.size());
			// 获取征信综合情况,若无征信综合情况，初始化添加一个
			CreditSituation cs = creditSituationService.findByCreditId(creditId);
			if(ObjectHelper.isNotEmpty(cs)){
			    CreditSituationVo vo = new CreditSituationVo(cs);
			    model.put("cs", vo);
			}else{
			    //如果为空 默认新增一条征信综合情况
			    EmpDto empDto = CED.getLoginUser();
			    cs = new CreditSituation();
			    cs.setCaseApplyId(caseApplyId);
			    cs.setCreateBy(empDto.getEmpCd());
			    cs.setCreateOrgCd(empDto.getOrgCd());
			    cs.setCreditId(creditId);
			    cs.setCustomerName(customerName);
			    cs.setCustomerId(customerId);
			    cs = creditSituationService.saveOrUpdateEntity(cs);
			    CreditSituationVo vo = new CreditSituationVo(cs);
			    model.put("cs", vo);
			}
		    model.put("caseApplyId", caseApplyId);
		    model.put("customerId", customerId);
		    model.put("customerName", customerName);
		    model.put("creditId", creditId);
		} catch (Exception e) {
			logger.error("进入征信录入页面异常：", e);
			e.printStackTrace();
		}
		return new ModelAndView("creditmanage/credit_situation_edit", model);

	}
	
	/**
     * @Title: creditEntering
     * @Description: 进入征信详细页面
     * @author dengyy
     * @param req
     * @param caseApplyId
     *            案件id
     * @param customerId
     *            客户id
     * @param customerName
     *            客户姓名
     * @param creditId
     *            案件客户征信id
     * @return
     */
    @RequestMapping("/view")
    @UriKey(key = "com.zdsoft.finance.credit.creditSituation.view")
    public ModelAndView view(HttpServletRequest req, String caseApplyId, String customerId,
            String customerName, String creditId) {
        ModelMap model = new ModelMap();
        try {
            // 获取图片附件
            List<AttachmentDto> atts = creditSituationService.findAttachments(creditId);
            model.put("atts", atts);
            model.put("size", atts.size());
            // 获取征信综合情况,若无征信综合情况，初始化添加一个
            CreditSituation cs = creditSituationService.findByCreditId(creditId);
            if(ObjectHelper.isNotEmpty(cs)){
                CreditSituationVo vo = new CreditSituationVo(cs);
                model.put("cs", vo);
            }else{
                //如果为空 默认新增一条征信综合情况
                EmpDto empDto = CED.getLoginUser();
                cs = new CreditSituation();
                cs.setCaseApplyId(caseApplyId);
                cs.setCreateBy(empDto.getEmpCd());
                cs.setCreateOrgCd(empDto.getOrgCd());
                cs.setCreditId(creditId);
                cs.setCustomerName(customerName);
                cs.setCustomerId(customerId);
                cs = creditSituationService.saveOrUpdateEntity(cs);
                CreditSituationVo vo = new CreditSituationVo(cs);
                model.put("cs", vo);
            }
            model.put("caseApplyId", caseApplyId);
            model.put("customerId", customerId);
            model.put("customerName", customerName);
            model.put("creditId", creditId);
        } catch (Exception e) {
            logger.error("进入征信录入页面异常：", e);
            e.printStackTrace();
        }
        return new ModelAndView("creditmanage/credit_situation_view", model);

    }

	/**
	 * @Title: save 
	 * @Description: 保存征信综合信息
	 * @author zhongyong 
	 * @param cs
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/save")
	@UriKey(key = "com.zdsoft.finance.credit.creditSituation.save")
	@ResponseBody
	public String save(CreditSituationVo cs){
		ResponseMsg msg = new ResponseMsg();
		try {
		    CreditSituation entity = cs.toPo();
			creditSituationService.saveCreditSituation(entity);
			msg.setMsg("录入成功！");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			msg.setMsg("保存征信综合信息出错");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			logger.error("保存征信综合信息出错！", e);
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(msg);
	}

}
