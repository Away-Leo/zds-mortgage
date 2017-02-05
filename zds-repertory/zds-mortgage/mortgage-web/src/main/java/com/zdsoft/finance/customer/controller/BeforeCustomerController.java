package com.zdsoft.finance.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.service.CaseApplyBeforeCustomerService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 贷前客户
 * 
 * @author zhangchao 2016/12/22
 */
@Controller
@RequestMapping("beforeCustomer")
public class BeforeCustomerController extends BaseController {

    @Autowired
    private CaseApplyBeforeCustomerService caseApplyBeforeCustomerService;

    /**
     * @Description 参与人列表（贷审审批单-主借人、共借人、担保人基本情况）
     * @param pageable
     * @param jsoncallback
     * @return
     */
    @RequestMapping("/getParticipantList")
    @UriKey(key = "com.zdsoft.finance.beforeCustomer.getParticipantList")
    @ResponseBody
    public String getParticipantList(String jsoncallback, String caseApplyId) {
        ResponseMsg msg = new ResponseMsg();
        List<CaseApplyBeforeCustomer> beforeCustomerList = caseApplyBeforeCustomerService
                .queryByCaseApplyId(caseApplyId);

        msg.setResultStatus(ResponseMsg.SUCCESS);
        msg.setMsg("列表查询成功");
        msg.setRows(beforeCustomerList);
        msg.setTotal(16L);
        return ObjectHelper.objectToJson(msg, jsoncallback);
    }

}
