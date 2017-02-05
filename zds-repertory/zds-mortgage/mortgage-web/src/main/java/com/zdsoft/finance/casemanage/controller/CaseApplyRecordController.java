package com.zdsoft.finance.casemanage.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.casemanage.record.entity.CaseApplyRecord;
import com.zdsoft.finance.casemanage.record.service.CaseApplyRecordService;
import com.zdsoft.finance.casemanage.vo.CaseApplyRecordVo;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.service.BeforePersonalCustomerService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CaseRelatedInfoRecordController.java
 * @Package com.zdsoft.finance.casemanage.controller
 * @Description: 案件信息录入controller
 * @author Liyb
 * @date 2017年1月16日 上午10:57:40
 * @version V1.0
 */
@Controller
@RequestMapping("casemanage/relatedinforecord")
public class CaseApplyRecordController extends BaseController {

    private static final String MAIN_BORROWER_FULL_CODE = "YWDM0051036";

    @Autowired
    private CaseApplyRecordService caseApplyRecordService;
    @Autowired
    private BeforePersonalCustomerService beforePersonalCustomerService;

    /**
     * 案件信息录入列表
     * 
     * @param caseApplyId
     * @return
     */
    @RequestMapping("/list")
    @UriKey(key = "com.zdsoft.finance.casemanage.relatedinforecord.initList")
    @Menu(resource = "com.zdsoft.finance.casemanage.relatedinforecord.initList", label = "案件信息录入", group = "project", order = 1)
    public ModelAndView initList() {
        return new ModelAndView("/casemanage/relatedInfoRecord/case_related_info_record_list");
    }

    /**
     * 案件信息录入分页列表
     * 
     * @param request
     * @param jsoncallback
     * @param pageable
     * @return
     */
    @RequestMapping("/getCaseRecordList")
    @UriKey(key = "com.zdsoft.finance.casemanage.relatedinforecord.getCaseRecordList")
    @ResponseBody
    public String getCaseRecordList(HttpServletRequest request, String jsoncallback, PageRequest pageable) {
        ResponseMsg msg = new ResponseMsg();
        try {
            // 获取页面封装的查询参数
            @SuppressWarnings("unchecked")
            List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
            for (QueryObj queryObj : queryObjs) {
                logger.info(queryObj.getElement());
                logger.info(queryObj.getObj());
                logger.info(queryObj.getOperator());
                logger.info(queryObj.getValue().toString());
            }
            // 分页查询
            Page<CaseApplyRecord> caseApplyRecordPage = caseApplyRecordService.findByHqlConditions(pageable, queryObjs);
            // Po转换Vo
            List<CaseApplyRecordVo> recordVos = new ArrayList<CaseApplyRecordVo>();
            for (CaseApplyRecord record : caseApplyRecordPage.getRecords()) {
                CaseApplyRecordVo recordVo = new CaseApplyRecordVo(record, new String[] {},
                        new String[] {"recordType", "recordStatus" });
                // 获取案件主借人名称
                List<BeforePersonalCustomer> beforePersonalCustomers = beforePersonalCustomerService
                        .queryCustomerByCaseApplyIdAndJoinType(record.getCaseApply().getId(), MAIN_BORROWER_FULL_CODE);
                if (CollectionUtils.isNotEmpty(beforePersonalCustomers)) {
                    recordVo.setMainBorrower(beforePersonalCustomers.get(0).getCustomerName());
                }
                recordVos.add(recordVo);
            }
            msg.setResultStatus(ResponseMsg.SUCCESS);
            msg.setMsg("案件信息录入列表查询成功");
            msg.setRows(recordVos);
            msg.setTotal(caseApplyRecordPage.getTotalRows());
        } catch (Exception e) {
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            msg.setMsg("案件信息录入列表查询失败");
            logger.error("error:", e);
        }

        return ObjectHelper.objectToJson(msg, jsoncallback);
    }

}
