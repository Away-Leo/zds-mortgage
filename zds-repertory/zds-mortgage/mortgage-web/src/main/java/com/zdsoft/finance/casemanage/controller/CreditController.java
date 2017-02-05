package com.zdsoft.finance.casemanage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.entity.Credit;
import com.zdsoft.finance.customer.service.BeforePersonalCustomerService;
import com.zdsoft.finance.customer.service.CreditService;
import com.zdsoft.finance.customer.vo.CreditVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * @ClassName CreditController
 * @Description 征信信息
 * @author Liyb
 * @Date 2017年1月12日 下午7:36:58
 * @version 1.0.0
 */
@Controller
@RequestMapping("casemanage/credit")
public class CreditController extends BaseController {

    @Autowired
    private BeforePersonalCustomerService beforePersonalCustomerService;
    @Autowired
    private CreditService creditService;

    @Autowired
    private CED ced;

    /**
     * 征信录入列表页面
     * 
     * @return
     */
    @RequestMapping("/list")
    @UriKey(key = "com.zdsoft.finance.casemanage.credit.list")
    public ModelAndView initCreditList(String caseApplyId) {
        ModelAndView mv = new ModelAndView("/customer/credit_list");
        mv.addObject("caseApplyId", caseApplyId);
        return mv;
    }

    /**
     * 征信录入列表表格分页
     * 
     * @param pageable
     * @param jsoncallback
     * @return
     */
    @RequestMapping("/getCreditList")
    @UriKey(key = "com.zdsoft.finance.casemanage.credit.getCreditList")
    @ResponseBody
    public String getCreditList(String jsoncallback, String caseApplyId) {
        ResponseMsg msg = new ResponseMsg();
        try {
            // 根据案件ID获取案件参与人列表
            List<BeforePersonalCustomer> beforePersonalCustomers = beforePersonalCustomerService
                    .queryByCaseApplyId(caseApplyId);
            // Po转换Vo
            List<CreditVo> creditVOs = new ArrayList<CreditVo>();
            for (BeforePersonalCustomer beforePersonalCustomer : beforePersonalCustomers) {
                CreditVo creditVO = new CreditVo();
                creditVO.setCustomerName(beforePersonalCustomer.getCustomerName());
                creditVO.setCustomerId(beforePersonalCustomer.getId());
                creditVO.setCredentialNo(beforePersonalCustomer.getCredentialNo());
                String joinTypeName = ced.loadSimpleCodeNameByFullCode(beforePersonalCustomer.getJoinType());
                creditVO.setJoinTypeName(joinTypeName);
                // 获取客户征信记录,一个客户只有一份征信记录
                Credit credit = creditService.findByCustomerId(beforePersonalCustomer.getId());
                if (null != credit) {
                    creditVO.setCreditStatus(credit.getCreditStatus());
                }
                creditVOs.add(creditVO);
            }
            msg.setResultStatus(ResponseMsg.SUCCESS);
            msg.setMsg("案件相关客户征信列表查询成功");
            msg.setRows(creditVOs);
        } catch (Exception e) {
            logger.error("error:", e);
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            msg.setMsg("案件相关客户征信列表查询失败");
        }
        return ObjectHelper.objectToJson(msg, jsoncallback);
    }

    /**
     * 征信录入页面 TODO:（本期暂不实现）
     * 
     * @param id
     * @param jsoncallback
     * @return
     */
    @RequestMapping("/recordCredit")
    @UriKey(key = "com.zdsoft.finance.casemanage.credit.recordCredit")
    @ResponseBody
    public ModelAndView recordCredit(String id, String jsoncallback) {
        Map<String, Object> optional = new HashMap<String, Object>();
        return new ModelAndView("/customer/credit_record", optional);
    }

}
