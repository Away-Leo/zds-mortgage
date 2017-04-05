package com.zdsoft.finance.contract.controller;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.contract.entity.ConFormatContractDetail;
import com.zdsoft.finance.contract.entity.ConFormatContractMaterial;
import com.zdsoft.finance.contract.service.ConCaseContractService;
import com.zdsoft.finance.contract.service.ConFormatContractDetailService;
import com.zdsoft.finance.contract.service.ConFormatContractMaterialService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 版权所有：重庆正大华日软件有限公司
 *
 * @author denglw
 * @version V1.0
 * @Title: ContractCancelController.java
 * @ClassName: ContractCancelController
 * @Description: 合同作废
 * @date 2017年3月14日 上午11:45:41
 */
@Controller
@RequestMapping("/contractCancel")
public class ContractCancelController extends BaseController {
    @Autowired
    private ConCaseContractService caseContractService;
    @Autowired
    private ConFormatContractMaterialService contractMaterialService;
    @Autowired
    private ConFormatContractDetailService contractDetailService;
    @Autowired
    private CED CED;

    /**
     *
     * @Title: contractCancelList
     * @Description: 合同作废页面
     * @author denglw
     * @return
     */
    @RequestMapping("/contractCancelList")
    @UriKey(key = "com.zdsoft.finance.contract.contractCancel.contractCancelList")
    @Menu(resource = "com.zdsoft.finance.contract.contractCancel.contractCancelList", label = "合同作废", group = "contract", order = 5)
    public ModelAndView contractCancelList() {
        ModelAndView modelView = new ModelAndView("/contract/contractCancel_list");
        try {
            modelView.addObject("curOrgId", CED.getLoginUser().getOrgCd());
        } catch (Exception e) {
            logger.error("获取当前登录人员机构失败", e);
        }
        return modelView;
    }

    /**
     *
     * @Title: getContractCancelList
     * @Description: 合同作废数据列表
     * @author denglw
     * @param request
     * @param pageable
     * @param jsoncallback
     * @return
     */
    @RequestMapping("/getContractCancelList")
    @UriKey(key = "com.zdsoft.finance.contract.contractCancel.getContractCancelList")
    @ResponseBody
    public String getContractCancelList(HttpServletRequest request, PageRequest pageable, String jsoncallback) {
        ResponseMsg msg = new ResponseMsg();
        @SuppressWarnings("unchecked")
        List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
        Page<Map<String, Object>> pms = null;
        try {
            /**
             * 第一次进入页面默认查询当前机构的合同申请
             */
            if (queryObjs.size() == 0) {
                QueryObj queryObj = new QueryObj();
                queryObj.setObj("contractTemp");
                queryObj.setElement("orgcd");
                queryObj.setOperator("=");
                queryObj.setValue(CED.getLoginUser().getOrgCd());
                queryObjs.add(queryObj);
            }
            pms = caseContractService.getContractCancelReport(pageable, queryObjs);
            List<Map<String, Object>> records = pms.getRecords();
            for (Map<String, Object> map : records) {
                map.put("CONTRACTTYPENAME", CED.loadSimpleCodeByFullCode(map.get("CONTRACTTYPE").toString()).getName());
            }
            msg.setResultStatus(ResponseMsg.SUCCESS);
            msg.setMsg("列表查询成功");
            msg.setRows(pms.getRecords());
            msg.setTotal(pms.getTotalRows());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("列表查询失败", e);
        }
        return ObjectHelper.objectToJson(msg, jsoncallback);
    }

    /**
     *
     * @Title: procContractCancel
     * @Description: 合同作废
     * @author denglw
     * @param capitaIdC
     * @param contractTypeC
     * @param contractNameC
     * @param fileNo
     * @return
     */
    @RequestMapping("/procContractCancel")
    @UriKey(key = "com.zdsoft.finance.contract.contractCancel.procContractCancel")
    @ResponseBody
    public String procContractCancel(String capitaIdC, String contractTypeC, String contractNameC, String fileNo) {
        fileNo = fileNo.trim();
        ResponseMsg msg = new ResponseMsg();
        if (isNotEmpty(capitaIdC) && isNotEmpty(contractTypeC) && isNotEmpty(contractNameC) && isNotEmpty(fileNo)) {
            List<Map<String, Object>> maps = caseContractService.getMaterialCountByCondition(capitaIdC, contractTypeC, contractNameC, fileNo);
            if (maps.size() > 0) {
                try {
                    caseContractService.procContractCancel(maps);
                    msg.setResultStatus(ResponseMsg.SUCCESS);
                    msg.setMsg("合同作废成功!");
                } catch (Exception e) {
                    logger.error("合同作废失败！", e);
                    msg.setResultStatus(ResponseMsg.APP_ERROR);
                    msg.setMsg("合同作废失败，请稍后重试!");
                }

            } else {
                logger.error("没有找到相应的文件编码，作废失败!");
                msg.setResultStatus(ResponseMsg.APP_ERROR);
                msg.setMsg("没有找到相应的文件编码，作废失败!");
            }
        } else {
            logger.error("合同作废参数值为空，查询失败!");
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            msg.setMsg("参数值为空，查询失败!");
        }
        return ObjectHelper.objectToJson(msg);
    }

    /**
     *
     * @Title: isNotEmpty
     * @Description: 判断字符串是否为空
     * @author denglw
     * @param value
     * @return
     */
    private boolean isNotEmpty(String value) {
        if (value != null && !"".equals(value) && "undefined" != value) {
            return true;
        } else {
            return false;
        }
    }
}
