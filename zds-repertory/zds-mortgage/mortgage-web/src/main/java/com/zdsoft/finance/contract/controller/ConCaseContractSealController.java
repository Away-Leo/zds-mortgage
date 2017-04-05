package com.zdsoft.finance.contract.controller;

import com.zdsoft.bpm.annotation.client.FinishJob;
import com.zdsoft.bpm.annotation.client.ManualJob;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.basedata.SimpleCodeDto;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.busiform.service.BusiFormService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.utils.DateUtil;
import com.zdsoft.finance.contract.entity.ConCaseContract;
import com.zdsoft.finance.contract.entity.ConCaseContractDetail;
import com.zdsoft.finance.contract.entity.ConCaseContractSeal;
import com.zdsoft.finance.contract.entity.ConCaseContractSealDetail;
import com.zdsoft.finance.contract.service.ConCaseContractSealDetailService;
import com.zdsoft.finance.contract.service.ConCaseContractSealService;
import com.zdsoft.finance.contract.service.ConCaseContractService;
import com.zdsoft.finance.contract.vo.ConCaseContractVo;
import com.zdsoft.finance.contract.vo.ConContractSealDetailVo;
import com.zdsoft.finance.contract.vo.ConContractSealVo;
import com.zdsoft.finance.cooperator.entity.Capitalist;
import com.zdsoft.finance.cooperator.entity.CooperatorTerminal;
import com.zdsoft.finance.cooperator.service.CapitalistService;
import com.zdsoft.finance.cooperator.service.CooperatorTerminalService;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.vo.CaseApplyVo;
import com.zdsoft.finance.workbench.vo.BusiFormVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 *
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConCaseContractSealController.java
 * @ClassName: ConCaseContractSealController
 * @Description: 合同申请盖章
 * @author denglw
 * @date 2017年3月25日 下午5:38:42
 * @version V1.0
 */
@Controller
@RequestMapping("/conCaseContractSeal")
public class ConCaseContractSealController extends BaseController {
    @Autowired
    private ConCaseContractSealService conCaseContractSealService;
    @Autowired
    private ConCaseContractSealDetailService conCaseContractSealDetailService;
    @Autowired
    private ConCaseContractService conCaseContractService;
    @Autowired
    private CaseApplyService caseApplyService;
    @Autowired
    private BusiFormService busiFormService;
    @Autowired
    private CapitalistService capitalistService;
    @Autowired
    private CooperatorTerminalService cooperatorTerminalService;
    @Autowired
    private CED CED;

    /**
     * @Title: contractCancelList
     * @Description: 案件合同列表
     * @author denglw
     * @return
     */
    @RequestMapping("/contractCancelList")
    @UriKey(key = "com.zdsoft.finance.contract.conCaseContractSeal.contractSealList")
    @Menu(resource = "com.zdsoft.finance.contract.conCaseContractSeal.contractSealList", label = "案件合同盖章", group = "contract", order = 6)
    public ModelAndView contractSealList() {
        return new ModelAndView("/contract/contractSeal/contractSeal_list");
    }

    /**
     * @Title: getContractSealList
     * @Description: 合同盖章列表数据
     * @author denglw
     * @param request
     * @param pageable
     * @param jsoncallback
     * @return
     */
    @RequestMapping("/getContractSealList")
    @UriKey(key = "com.zdsoft.finance.contract.conCaseContractSeal.getContractSealList")
    @ResponseBody
    public String getContractSealList(HttpServletRequest request, PageRequest pageable, String jsoncallback) {
        ResponseMsg msg = new ResponseMsg();
        List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
        Page<Map<String, Object>> pms = null;
        try {
            pms = conCaseContractSealService.getContractList(pageable, queryObjs);
            List<Map<String, Object>> records = pms.getRecords();
            convertStrEmptyMapList(pms.getRecords());
            for (Map<String, Object> map : records) {
                if (map.get("STAGE") != null) {
                    map.put("STAGENAME", CED.loadSimpleCodeNameByFullCode(map.get("STAGE").toString()));
                }
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
     * @Title: preAddContractSeal
     * @Description: 合同盖章申请页面
     * @author denglw
     * @param contractId
     * @return
     */
    @RequestMapping("/preAddContractSeal")
    @UriKey(key = "com.zdsoft.finance.contract.conCaseContractSeal.preAddContractSeal")
    public ModelAndView preAddContractSeal(String contractId) {
        ModelAndView modelView = new ModelAndView("/contract/contractSeal/contractSeal_add");
        if (contractId != null && !"".equals(contractId)) {
            try {
                //合同
                ConCaseContract contract = conCaseContractService.findOne(contractId);
                ConCaseContractVo contractVo = new ConCaseContractVo();
                BeanUtils.copyProperties(contract, contractVo);
                modelView.addObject("contract", contractVo);
                //案件
                CaseApply caseApply = caseApplyService.findOne(contract.getCaseApplyId());
                String stage = caseApply.getStage();
                if (stage != null && !"".equals(stage)) {
                    modelView.addObject("subStage", stage.substring(stage.length() - 2, stage.length()));
                }
                CaseApplyVo caseApplyVo = new CaseApplyVo();
                BeanUtils.copyProperties(caseApply, caseApplyVo);
                String repMethodName = CED.loadSimpleCodeNameByFullCode(caseApply.getRepayMethod());
                caseApplyVo.setRepayMethod(repMethodName);
                Capitalist capitalist = capitalistService.findOne(caseApply.getCapitalSource());
                if (capitalist != null) {
                    caseApplyVo.setCapitalSourceName(capitalist.getCapitalName());
                }
                CooperatorTerminal cooperatorTerminal = cooperatorTerminalService.findOne(caseApply.getTerminalId());
                if (cooperatorTerminal != null) {
                    caseApplyVo.setTerminalIdName(cooperatorTerminal.getTerminalFullName());
                }
                modelView.addObject("caseApply", caseApplyVo);
                EmpDto empDto = CED.getLoginUser();
                modelView.addObject("emp", empDto);
                modelView.addObject("applyDate", DateUtil.getCurrentDate());
                modelView.addObject("uuid", UUID.randomUUID().toString().replace("-", ""));
                return modelView;
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("合同盖章申请页面加载失败", e);
            }
        }
        return modelView;
    }


    /**
     * @Title: contractSealDetailList
     * @Description: 根据合同盖章主ID获取合同明细
     * @author denglw
     * @param sealId
     * @param jsoncallBack
     * @return
     */
    @RequestMapping("/contractSealDetailList")
    @UriKey(key = "com.zdsoft.finance.contract.conCaseContractSeal.contractSealDetailList")
    @ResponseBody
    public String contractSealDetailList(String sealId, String jsoncallBack) {
        ResponseMsg msg = new ResponseMsg();
        try {
            List<ConCaseContractSealDetail> ls = conCaseContractSealDetailService.findByCaseContractSealId(sealId);
            msg.setResultStatus(ResponseMsg.SUCCESS);
            msg.setMsg("列表查询成功");
            msg.setRows(ls);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("列表查询失败", e);
        }
        return ObjectHelper.objectToJson(msg, jsoncallBack);
    }

    /**
     * @Title: sealDataFileName
     * @Description: 合同盖章资料名称列表
     * @author denglw
     * @return
     */
    @RequestMapping("/sealDataFileName")
    @UriKey(key = "com.zdsoft.finance.contract.conCaseContractSeal.sealDataFileName")
    @ResponseBody
    public String sealDataFileName() {
        ResponseMsg msg = new ResponseMsg();
        try {
            List<SimpleCodeDto> ls = new ArrayList<SimpleCodeDto>();
            SimpleCodeDto mortgage = CED.loadSimpleCodeByFullCode("YWDM0016601");//抵押合同
            SimpleCodeDto Cross = CED.loadSimpleCodeByFullCode("YWDM0016602");//划款授权书（划出）
            SimpleCodeDto Loan = CED.loadSimpleCodeByFullCode("YWDM0016603");//借款合同
            ls.add(mortgage);
            ls.add(Cross);
            ls.add(Loan);
            msg.setResultStatus(ResponseMsg.SUCCESS);
            msg.setMsg("列表查询成功");
            msg.setRows(ls);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("列表查询失败", e);
        }
        return ObjectHelper.objectToJson(msg);
    }

    /**
     * @Title: saveOrUpdateContractSeal
     * @Description: 保存或提交盖章信息
     * @author denglw
     * @param sealVo
     * @return
     */
    @RequestMapping("/saveOrUpdateContractSeal")
    @UriKey(key = "com.zdsoft.finance.contract.conCaseContractSeal.saveOrUpdateContractSeal")
    @ResponseBody
    public String saveOrUpdateContractSeal(ConContractSealVo sealVo) {
        ResponseMsg msg = new ResponseMsg();
        try {
            ConCaseContractSeal seal = new ConCaseContractSeal();
            List<ConCaseContractSealDetail> sealDetails = new ArrayList<ConCaseContractSealDetail>();
            sealVo.toPo(sealVo, seal);
            List<ConContractSealDetailVo> sealDetailVos = sealVo.getSealDetails();
            for (ConContractSealDetailVo detailVo : sealDetailVos) {
                ConCaseContractSealDetail sealDetail = new ConCaseContractSealDetail();
                detailVo.toPo(detailVo, sealDetail);
                sealDetails.add(sealDetail);
            }
            conCaseContractSealService.saveOrUpdateBill(seal, sealDetails, sealVo.isSubmitStatus(), sealVo.getFileUuid());
            msg.setResultStatus(ResponseMsg.SUCCESS);
            if (sealVo.isSubmitStatus()) {
                msg.setMsg("提交成功");
            } else {
                msg.setMsg("保存成功");
            }
        } catch (Exception e) {
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            msg.setMsg("系统内部错误，请查看日志");
            logger.error("合同盖章申请保存失败", e);
            e.printStackTrace();
        }
        return ObjectHelper.objectToJson(msg);
    }


    /**
     * @Title: saveSealEntity
     * @Description: 流程中盖章实体保存或修改
     * @author denglw
     * @param sealVo
     * @param businessKey
     * @param projectId
     * @return
     */
    @RequestMapping("/saveSealEntity")
    @UriKey(key = "com.zdsoft.finance.contract.conCaseContractSeal.saveSealEntity")
    @FinishJob(resource = "com.zdsoft.finance.contract.conCaseContractSeal.contractSealProcessEdit", businessId = "businessKey", projectId = "projectId")
    @ResponseBody
    public String saveSealEntity(ConContractSealVo sealVo, String businessKey, String projectId) {
        ResponseMsg msg = new ResponseMsg();
        try {
            ConCaseContractSeal seal = new ConCaseContractSeal();
            List<ConCaseContractSealDetail> sealDetails = new ArrayList<ConCaseContractSealDetail>();
            sealVo.toPo(sealVo, seal);
            List<ConContractSealDetailVo> sealDetailVos = sealVo.getSealDetails();
            for (ConContractSealDetailVo detailVo : sealDetailVos) {
                ConCaseContractSealDetail sealDetail = new ConCaseContractSealDetail();
                detailVo.toPo(detailVo, sealDetail);
                sealDetails.add(sealDetail);
            }
            conCaseContractSealService.saveOrUpdateEntity(seal, sealDetails, sealVo.getFileUuid());
            msg.setResultStatus(ResponseMsg.SUCCESS);
            if (sealVo.isSubmitStatus()) {
                msg.setMsg("提交成功");
            } else {
                msg.setMsg("保存成功");
            }
        } catch (Exception e) {
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            msg.setMsg("系统内部错误，请查看日志");
            logger.error("合同盖章申请保存失败", e);
            e.printStackTrace();
        }
        return ObjectHelper.objectToJson(msg);
    }

    /**
     * @Title: orgInsideOffice
     * @Description: 机构内勤申请盖章
     * @author denglw
     * @param projectId
     * @param processInstanceId
     * @param businessKey
     * @param model
     * @return
     */
    @RequestMapping("/contractSealProcessEdit")
    @UriKey(key = "com.zdsoft.finance.contract.conCaseContractSeal.contractSealProcessEdit")
    @ManualJob(resource = "com.zdsoft.finance.contract.conCaseContractSeal.contractSealProcessEdit", label = "机构内勤申请盖章")
    public ModelAndView contractSealProcessEdit(String projectId, String processInstanceId, String businessKey, ModelMap model) {
        if (ObjectHelper.isNotEmpty(businessKey)) {
            try {
                this.convertToVo(projectId, businessKey, model);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("机构内勤申请盖章页面加载失败", e);
            }
        }
        return new ModelAndView("/contract/contractSeal/contractSeal_process_edit", model);
    }

    /**
     *
     * @Title: contractSealProcessEdit
     * @Description:我的申请查看
     * @author denglw
     * @param projectId
     * @param processInstanceId
     * @param businessKey
     * @param model
     * @return
     */
    @RequestMapping("/contractSealEdit")
    @UriKey(key = "com.zdsoft.finance.contract.conCaseContractSeal.contractSealEdit")
    public ModelAndView contractSealEdit(String projectId, String processInstanceId, String businessKey, ModelMap model) {
        if (ObjectHelper.isNotEmpty(businessKey)) {
            try {
                this.convertToVo(projectId, businessKey, model);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("我的申请查看加载失败", e);
            }
        }
        return new ModelAndView("/contract/contractSeal/contractSeal_edit", model);
    }
    /**
     * @Title: sealSend
     * @Description: 驻点寄出
     * @author denglw
     * @param projectId
     * @param processInstanceId
     * @param businessKey
     * @param model
     * @return
     */
    @RequestMapping("/sealSend")
    @UriKey(key = "com.zdsoft.finance.contract.conCaseContractSeal.sealSend")
    @ManualJob(resource = "com.zdsoft.finance.contract.conCaseContractSeal.sealSend", label = "驻点寄出")
    public ModelAndView sealSend(String projectId, String processInstanceId, String businessKey, ModelMap model) {
        if (ObjectHelper.isNotEmpty(businessKey)) {
            try {
                this.convertToVo(projectId, businessKey, model);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("驻点寄出页面加载失败", e);
            }
        }
        return new ModelAndView("/contract/contractSeal/contractSeal_send_process_edit", model);
    }

    /**
     * @Title: saveSealBack
     * @Description: 驻点寄出保存
     * @author denglw
     * @param sealVo
     * @param businessKey
     * @param projectId
     * @return
     */
    @RequestMapping("/saveSealBack")
    @UriKey(key = "com.zdsoft.finance.contract.conCaseContractSeal.saveSealBack")
    @FinishJob(resource = "com.zdsoft.finance.contract.conCaseContractSeal.sealSend", businessId = "businessKey", projectId = "projectId")
    @ResponseBody
    public String saveSealBack(ConContractSealVo sealVo, String businessKey, String projectId) {
        ResponseMsg msg = new ResponseMsg();
        try {
            if (ObjectHelper.isNotEmpty(sealVo.getId()) && ObjectHelper.isNotEmpty(sealVo.getTrackingNoSend())) {
                conCaseContractSealService.saveTrackingNoSend(sealVo.getId(), sealVo.getTrackingNoSend());
                msg.setResultStatus(ResponseMsg.SUCCESS);
                msg.setMsg("保存成功");
            } else {
                msg.setResultStatus(ResponseMsg.APP_ERROR);
                msg.setMsg("系统内部错误，请查看日志");
                logger.error("驻点寄出保存失败,id或快递单信息为空,id:" + sealVo.getId() + ";noSend:" + sealVo.getTrackingNoSend());
            }
        } catch (Exception e) {
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            msg.setMsg("系统内部错误，请查看日志");
            logger.error("驻点寄出保存失败", e);
            e.printStackTrace();
        }
        return ObjectHelper.objectToJson(msg);
    }

    /**
     * @Title: sealBackSend
     * @Description: 合同退回信托寄出
     * @author denglw
     * @param projectId
     * @param processInstanceId
     * @param businessKey
     * @param model
     * @return
     */
    @RequestMapping("/sealBackSend")
    @UriKey(key = "com.zdsoft.finance.contract.conCaseContractSeal.sealBackSend")
    @ManualJob(resource = "com.zdsoft.finance.contract.conCaseContractSeal.sealBackSend", label = "合同退回信托寄出")
    public ModelAndView sealBackSend(String projectId, String processInstanceId, String businessKey, ModelMap model) {
        if (ObjectHelper.isNotEmpty(businessKey)) {
            try {
                this.convertToVo(projectId, businessKey, model);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("合同退回信托寄出页面加载失败", e);
            }
        }
        return new ModelAndView("/contract/contractSeal/contractSeal_backSend_process_edit", model);
    }

    /**
     * @Title: saveSealBackSend
     * @Description: 合同退回信托寄出保存
     * @author denglw
     * @param sealVo
     * @param businessKey
     * @param projectId
     * @return
     */
    @RequestMapping("/saveSealBackSend")
    @UriKey(key = "com.zdsoft.finance.contract.conCaseContractSeal.saveSealBackSend")
    @FinishJob(resource = "com.zdsoft.finance.contract.conCaseContractSeal.sealBackSend", businessId = "businessKey", projectId = "projectId")
    @ResponseBody
    public String saveSealBackSend(ConContractSealVo sealVo, String businessKey, String projectId) {
        ResponseMsg msg = new ResponseMsg();
        try {
            if (ObjectHelper.isNotEmpty(sealVo.getId()) && ObjectHelper.isNotEmpty(sealVo.getTrackingNoReceive())) {
                conCaseContractSealService.saveTrackingNoReceive(sealVo.getId(), sealVo.getTrackingNoReceive());
                msg.setResultStatus(ResponseMsg.SUCCESS);
                msg.setMsg("保存成功");
            } else {
                msg.setResultStatus(ResponseMsg.APP_ERROR);
                msg.setMsg("系统内部错误，请查看日志");
                logger.error("合同退回信托寄出保存失败,id或快递单信息为空,id:" + sealVo.getId() + ";noSend:" + sealVo.getTrackingNoReceive());
            }
        } catch (Exception e) {
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            msg.setMsg("系统内部错误，请查看日志");
            logger.error("合同退回信托寄出保存失败", e);
            e.printStackTrace();
        }
        return ObjectHelper.objectToJson(msg);
    }

    /**
     * @Title: orgSealAuditing
     * @Description: 机构合同盖章审核
     * @author denglw
     * @param projectId
     * @param businessKey
     * @param model
     * @return
     */
    @RequestMapping("/contractSealProcessView")
    @UriKey(key = "com.zdsoft.finance.contract.conCaseContractSeal.contractSealProcessView")
    @ManualJob(resource = "com.zdsoft.finance.contract.conCaseContractSeal.contractSealProcessView", label = "机构合同盖章审核")
    @FinishJob(resource = "com.zdsoft.finance.contract.conCaseContractSeal.contractSealProcessView", businessId = "businessKey", projectId = "projectId")
    public ModelAndView contractSealProcessView(String projectId, String businessKey, ModelMap model) {
        if (ObjectHelper.isNotEmpty(businessKey)) {
            try {
                this.convertToVo(projectId, businessKey, model);
            } catch (Exception e) {
                logger.error("机构合同盖章审核页面加载失败", e);
            }
        }
        return new ModelAndView("/contract/contractSeal/contractSeal_process_view", model);
    }
    /**
     * @Title: orgSealAuditing
     * @Description: 机构合同盖章审核
     * @author denglw
     * @param projectId
     * @param businessKey
     * @param model
     * @return
     */
    @RequestMapping("/contractSealView")
    @UriKey(key = "com.zdsoft.finance.contract.conCaseContractSeal.contractSealView")
    public ModelAndView contractSealView(String projectId, String businessKey, ModelMap model) {
        if (ObjectHelper.isNotEmpty(businessKey)) {
            try {
                this.convertToVo(projectId, businessKey, model);
            } catch (Exception e) {
                logger.error("机构合同盖章审核页面加载失败", e);
            }
        }
        return new ModelAndView("/contract/contractSeal/contractSeal_view", model);
    }
    /**
     * @Title: convertToVo
     * @Description: 实体转换vo
     * @author denglw
     * @param projectId
     * @param businessKey
     * @param model
     * @throws Exception
     */
    private void convertToVo(String projectId, String businessKey, ModelMap model) throws Exception {
        ConCaseContractSeal contractSeal = conCaseContractSealService.findOne(businessKey);
        //合同
        ConCaseContract contract = conCaseContractService.findOne(contractSeal.getCaseContractId());
        CaseApply caseApply = caseApplyService.findOne(contract.getCaseApplyId());
        BusiForm busiForm = busiFormService.findByBusinessEntityId(contractSeal.getId());
        ConCaseContractVo contractVo = new ConCaseContractVo();
        ConContractSealVo contractSealVo = new ConContractSealVo();
        CaseApplyVo caseApplyVo = new CaseApplyVo();
        BusiFormVo busiFormVo = new BusiFormVo();
        BeanUtils.copyProperties(contract, contractVo);
        BeanUtils.copyProperties(caseApply, caseApplyVo);
        BeanUtils.copyProperties(contractSeal, contractSealVo);
        BeanUtils.copyProperties(busiForm, busiFormVo);

        String repMethodName = CED.loadSimpleCodeNameByFullCode(caseApply.getRepayMethod());
        caseApplyVo.setRepayMethod(repMethodName);
        Capitalist capitalist = capitalistService.findOne(caseApply.getCapitalSource());
        if (capitalist != null) {
            caseApplyVo.setCapitalSourceName(capitalist.getCapitalName());
        }
        CooperatorTerminal cooperatorTerminal = cooperatorTerminalService.findOne(caseApply.getTerminalId());
        if (cooperatorTerminal != null) {
            caseApplyVo.setTerminalIdName(cooperatorTerminal.getTerminalFullName());
        }

        String stage = caseApply.getStage();
        if (stage != null && !"".equals(stage)) {
            model.put("subStage", stage.substring(stage.length() - 2, stage.length()));
        }
        model.put("projectId", projectId);
        model.put("businessKey", businessKey);
        model.put("contractSeal", contractSealVo);
        model.put("contract", contractVo);
        model.put("caseApply", caseApplyVo);
        model.put("busiForm", busiFormVo);
    }

    /**
     * @Title: importContractFileList
     * @Description: 导入合同文件显示
     * @author denglw
     * @param contractId
     * @return
     */
    @RequestMapping("/importContractFileList")
    @UriKey(key = "com.zdsoft.finance.contract.conCaseContractSeal.importContractFileList")
    @ResponseBody
    public String importContractFileList(String contractId) {
        ResponseMsg msg = new ResponseMsg();
        try {
            List<Map<String, Object>> contractDetail = conCaseContractService.getContractDetailByContractId(contractId);
            msg.setResultStatus(ResponseMsg.SUCCESS);
            msg.setMsg("导入合同文件显示列表查询成功");
            msg.setRows(contractDetail);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("导入合同文件显示列表查询失败", e);
        }
        return ObjectHelper.objectToJson(msg);
    }

    /**
     *
     * @Title: saveImportContractFile
     * @Description: 导入合同
     * @author denglw
     * @param contractDetailIds
     * @return
     */
    @RequestMapping("/saveImportContractFile")
    @UriKey(key = "com.zdsoft.finance.contract.conCaseContractSeal.saveImportContractFile")
    @ResponseBody
    public String saveImportContractFile(String contractDetailIds,String businessId, String caseApplyId, String linkCode, String productId){
        ResponseMsg msg = new ResponseMsg();
        try {
            conCaseContractSealService.importContractFile(contractDetailIds,businessId,caseApplyId,linkCode,productId);
            msg.setResultStatus(ResponseMsg.SUCCESS);
            msg.setMsg("导入合同成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("导入合同失败", e);
        }
        return ObjectHelper.objectToJson(msg);
    }


    /**
     * @Title: convertStrEmptyMapList
     * @Description: 获取案件的预约信息
     * @author denglw
     * @param maps
     */
    private void convertStrEmptyMapList(List<Map<String, Object>> maps) {
        for (Map<String, Object> map : maps) {
            Set<String> keys = map.keySet();
            for (String key : keys) {
                Object obj = map.get(key);
                if (obj == null) {
                    map.put(key, "");
                }
            }
        }
    }
}
