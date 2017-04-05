package com.zdsoft.finance.app.creditinvestigation.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.app.creditinvestigation.controller.vo.MyCreditAppVo;
import com.zdsoft.finance.app.creditinvestigation.controller.vo.PhotoTreeVo;
import com.zdsoft.finance.casemanage.receivablePlan.IrrUtil;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivableInfo;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlanForm;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlanTemp;
import com.zdsoft.finance.casemanage.receivablePlan.entity.RepayPlan;
import com.zdsoft.finance.casemanage.receivablePlan.service.ReceivableInfoService;
import com.zdsoft.finance.casemanage.receivablePlan.service.ReceivablePlanService;
import com.zdsoft.finance.casemanage.receivablePlan.service.ReceivablePlanTempService;
import com.zdsoft.finance.casemanage.receivablePlan.vo.ReceivableInfoVo;
import com.zdsoft.finance.common.utils.AppStatus;
import com.zdsoft.finance.common.utils.BigDecimalCalculateTwo;
import com.zdsoft.finance.common.utils.app.AppServerUtil;
import com.zdsoft.finance.credit.entity.CreditSituation;
import com.zdsoft.finance.credit.entity.CustomerCreditStatistics;
import com.zdsoft.finance.credit.entity.HmCreditCardInfo;
import com.zdsoft.finance.credit.entity.MyCredit;
import com.zdsoft.finance.credit.entity.MyCreditCard;
import com.zdsoft.finance.credit.service.CreditManageService;
import com.zdsoft.finance.credit.service.CreditSituationService;
import com.zdsoft.finance.credit.service.CustomerCreditStatisticsService;
import com.zdsoft.finance.credit.service.MyCreditCardService;
import com.zdsoft.finance.credit.service.PhotoTreeService;
import com.zdsoft.finance.credit.vo.CustomerCreditStatisticsVo;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.vo.CaseApplyVo;
import com.zdsoft.finance.shoot.entity.PhotoTree;
import com.zdsoft.framework.core.common.util.JsonHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 版权所有：重庆正大华日软件有限公司
 *
 * @author yangjia
 * @version V1.0
 * @Title: CreditInvestigationreController.java
 * @ClassName: CreditInvestigationreController
 * @Description: 征信相关信息Controller
 * @date 2017年2月27日 下午8:00:22
 */
@Controller
@RequestMapping("/server/creditinvestigation")
public class CreditInvestigationreController extends BaseController {

    @Autowired
    private CaseApplyService caseApplyService;

    @Autowired
    private CreditSituationService creditSituationService;

    @Autowired
    private CreditManageService creditManageService;

    @Autowired
    private ReceivablePlanService receivablePlanService;

    @Autowired
    private ReceivablePlanTempService receivablePlanTempService;

    @Autowired
    private ReceivableInfoService receivableInfoService;

    @Autowired
    private MyCreditCardService myCreditCardService;

    @Autowired
    private CustomerCreditStatisticsService customerCreditStatisticsService;

    @Autowired
    private PhotoTreeService photoTreeService;

    @Autowired
    private CED CED;

    /**
     * @Title: getinfomation
     * @Description: 获取资信调查主页信息
     * @author yangjia
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping("/getinfomation")
    @ResponseBody
    public String getinfomation(HttpServletRequest req, HttpServletResponse resp) {

        String caseApplyId = (String) req.getParameter("caseApplyId");

        if (ObjectHelper.isEmpty(caseApplyId)) {
            caseApplyId = (String) req.getAttribute("caseApplyId");
        }

        if (ObjectHelper.isEmpty(caseApplyId)) {
            logger.error("案件id不能为空");
            return AppServerUtil.buildError(AppStatus.ArgsError);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Map<String, String> condition;

            condition = caseApplyService.findCaseApplyByCondition(caseApplyId);

            map.put("caseApplyId", caseApplyId);
            map.put("procInstanceId", req.getParameter("procInstanceId"));
            map.put("taskInstanceId", req.getParameter("taskInstanceId"));
            map.put("businessKey", req.getParameter("businessKey"));
            map.put("caseApplyCode", condition.get("caseApplyCode"));
            map.put("productSubtypeName", condition.get("productSubtypeName"));
            map.put("capitalSource", condition.get("capitalSource"));
            map.put("cooperatorName", condition.get("cooperatorName"));
            map.put("applyAmount", condition.get("applyAmount"));
            map.put("applyTerm", condition.get("applyTerm"));
            map.put("terminalFullName", condition.get("terminalFullName"));
            map.put("capitalUseFor", condition.get("capitalUseFor"));
            map.put("customerName", condition.get("customerName"));
            map.put("mailingAddress", condition.get("mailingAddress"));
        } catch (Exception e) {
            logger.error("获取资信调查主页信息失败");
            e.printStackTrace();
            return AppServerUtil.buildJsonObject(map);
        }
        return AppServerUtil.buildJsonObject(map);
    }

    /**
     * @Title: getcreditcards
     * @Description: 获取信用卡记录
     * @author yangjia
     * @param req
     * @param resp
     * @return
     * @throws Exception
     */
    @RequestMapping("/getcreditcards")
    @ResponseBody
    public String getcreditcards(HttpServletRequest req,
                                 HttpServletResponse resp) {

        String idCard = (String) req.getParameter("idCard");
        String customerId = (String) req.getParameter("customerId");
        if (ObjectHelper.isEmpty(customerId) || ObjectHelper.isEmpty(idCard)) {
            return AppServerUtil.buildError(AppStatus.ArgsError);
        }
        List<MyCreditCard> creditCardList = new ArrayList<MyCreditCard>();
        List<HmCreditCardInfo> hmCreditCardList = new ArrayList<HmCreditCardInfo>();
        Map<String, Object> map = new HashMap<String, Object>();
        try {

            CreditSituation CreditSituation = creditSituationService
                    .findByCustomerId(customerId);

            if (ObjectHelper.isNotEmpty(CreditSituation)) {
                creditCardList = myCreditCardService.findByCreditSituationId(CreditSituation.getId());
            }
            List<HmCreditCardInfo> list = new ArrayList<HmCreditCardInfo>();
            try {
                list = creditManageService.findByCardNum(idCard);
            } catch (Exception e) {
                logger.error("根据身份证查询贷记卡概况失败");
                e.printStackTrace();
            }

            for (int i = 0; i < list.size(); i++) {
                boolean isOverdue = true;
                String repayStatus = list.get(i).getMonth24RepayStatus();
                if (!ObjectHelper.isEmpty(repayStatus)) {
                    for (int j = 0; j < repayStatus.length(); j++) {
                        if (Character.isDigit(repayStatus.charAt(j))) {
                            isOverdue = false;
                            break;
                        }
                    }
                }
                if (isOverdue) {
                    hmCreditCardList.add(list.get(i));
                }
            }

            map.put("creditCardList", creditCardList);
            map.put("hmCreditCardList", hmCreditCardList);
        } catch (Exception e) {
            logger.error("获取信用卡记录失败");
            e.printStackTrace();
            map.put("creditCardList", creditCardList);
            map.put("hmCreditCardList", hmCreditCardList);
            return AppServerUtil.buildJsonObject(map);
        }
        return AppServerUtil.buildJsonObject(map);
    }

    /**
     * @Title: getloans
     * @Description: 根据征信综合情况id查询贷款记录
     * @author panshm
     * @param request
     * @param sourceMarkId 征信综合情况数据中的数据来源标识
     * @param sourceFrom
     * @return
     */
    @RequestMapping("/getloans")
    @UriKey(key = "com.zdsoft.finance.credit.loanRecord.getloans")
    @ResponseBody
    public String getloans(HttpServletRequest request, String sourceMarkId, String sourceFrom) {
        // 查询贷款记录
        List<MyCredit> list = new ArrayList<MyCredit>();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<MyCreditAppVo> hasOverdueList = new ArrayList<MyCreditAppVo>();
            List<MyCreditAppVo> noOverdueList = new ArrayList<MyCreditAppVo>();
            MyCreditAppVo tempVo = null;
            list = customerCreditStatisticsService.showLoanRecordBySourceMarkId(sourceMarkId, sourceFrom);
            for (MyCredit myCredit : list) {
                tempVo = new MyCreditAppVo(myCredit);
                if (myCredit.getCumulativeOverdue() != null && myCredit.getCumulativeOverdue() > 0) {
                    hasOverdueList.add(tempVo);
                } else {
                    noOverdueList.add(tempVo);
                }
            }
            map.put("hasOverdueList", hasOverdueList);
            map.put("noOverdueList", noOverdueList);
        } catch (Exception e) {
            logger.error("取得贷款记录失败");
            e.printStackTrace();
            return AppServerUtil.buildError(AppStatus.SystemError, e);
        }
        return AppServerUtil.buildJsonObject(map);
    }

    /**
     * @Title: getcreditgeneralinfo
     * @Description: 供APP端取得并显示征信概要统计数据
     * @author panshm
     * @param request
     * @param customerId
     * @param caseApplyId    案件id
     * @param caseApplyStage 案件来源标识
     * @return
     */
    @RequestMapping("/getcreditgeneralinfo")
    @UriKey(key = "com.zdsoft.finance.credit.loanRecord.getcreditgeneralinfo")
    @ResponseBody
    public String getcreditgeneralinfo(HttpServletRequest request, String customerId,
                                       String caseApplyId, String caseApplyStage) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 将客户id封装为数组，便于传参
            List<CustomerCreditStatisticsVo> listVo = new ArrayList<CustomerCreditStatisticsVo>();
            List<String> customerIds = new ArrayList<String>();
            customerIds.add(customerId);
            // 根据案件id和客户id取得征信概要统计数据
            List<CustomerCreditStatistics> list = customerCreditStatisticsService
                    .findByCustomerIdsAndCaseApplyIdAndCaseApplyStage(caseApplyId, customerIds, caseApplyStage);
            for (CustomerCreditStatistics info : list) {
                CustomerCreditStatisticsVo vo = new CustomerCreditStatisticsVo(info);
                listVo.add(vo);
            }
            map.put("creditGeneralInfoList", listVo);
        } catch (Exception e) {
            logger.error("取得征信概要统计数据失败");
            e.printStackTrace();
            return AppServerUtil.buildError(AppStatus.SystemError, e);
        }
        return AppServerUtil.buildJsonObject(map);
    }

    /**
     * @Title: getShootInfo
     * @Description: 获取资调-摄像数据（各种分类照片的数量统计）
     * @author panshm
     * @param request       HttpServletRequest
     * @param caseApplyCode 案件号
     * @param linkCode      环节号，资调的环节编号=06
     * @return
     */
    @RequestMapping("/getshootinfo")
    @UriKey(key = "com.zdsoft.finance.credit.shoot.getshootinfo")
    @ResponseBody
    public String getShootInfo(HttpServletRequest request, String caseApplyCode, String linkCode) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<PhotoTreeVo> voList = new ArrayList<PhotoTreeVo>();
        try {
            List<PhotoTree> list = photoTreeService.findShootPhotoInfo(caseApplyCode, linkCode);
            Map<String, Integer> countMap = new HashMap<String, Integer>();
            for (PhotoTree photoTree : list) {
                String type1nd = photoTree.getType1Name();
                if (countMap.get(type1nd) != null) {
                    countMap.put(type1nd, countMap.get(type1nd) + 1);
                } else {
                    countMap.put(type1nd, 1);
                }
                if (!ObjectUtils.isEmpty(photoTree.getType2Name())) {
                    String type2nd = photoTree.getType1Name() + "-" + photoTree.getType2Name();
                    if (countMap.get(type2nd) != null) {
                        countMap.put(type2nd, countMap.get(type2nd) + 1);
                    } else {
                        countMap.put(type2nd, 1);
                    }
                }
                if (!ObjectUtils.isEmpty(photoTree.getType3Name())) {
                    String type3nd = photoTree.getType1Name() + "-" + photoTree.getType2Name() + "-"
                            + photoTree.getType3Name();
                    if (countMap.get(type3nd) != null) {
                        countMap.put(type3nd, countMap.get(type3nd) + 1);
                    } else {
                        countMap.put(type3nd, 1);
                    }
                }
                if (!ObjectUtils.isEmpty(photoTree.getType4Name())) {
                    String type4nd = photoTree.getType1Name() + "-" + photoTree.getType2Name() + "-"
                            + photoTree.getType3Name() + "-" + photoTree.getType4Name();
                    if (countMap.get(type4nd) != null) {
                        countMap.put(type4nd, countMap.get(type4nd) + 1);
                    } else {
                        countMap.put(type4nd, 1);
                    }
                }
                if (!ObjectUtils.isEmpty(photoTree.getType5Name())) {
                    String type5nd = photoTree.getType1Name() + "-" + photoTree.getType2Name() + "-"
                            + photoTree.getType3Name() + "-" + photoTree.getType4Name() + "-"
                            + photoTree.getType5Name();
                    if (countMap.get(type5nd) != null) {
                        countMap.put(type5nd, countMap.get(type5nd) + 1);
                    } else {
                        countMap.put(type5nd, 1);
                    }
                }
                voList.add(new PhotoTreeVo(photoTree));
            }
//			map.put("shootInfoList", voList);
            map.put("caseApplyCode", caseApplyCode);
            map.put("linkCode", linkCode);
            map.put("typeCountMap", countMap);
        } catch (Exception e) {
            logger.error("获取资调-摄像数据失败", e);
            e.printStackTrace();
            return AppServerUtil.buildError(AppStatus.SystemError, e);
        }
        return AppServerUtil.buildJsonObject(map);
    }

    /**
     * @Title: getShootDetailInfo
     * @Description: 获取资调-摄像详细数据（具体分类内的照片信息）
     * @author panshm
     * @param request       HttpServletRequest
     * @param caseApplyCode 案件号
     * @param linkCode      环节编号，资调的环节编号=06
     * @param allNodeStr    完整分类节点
     * @return
     */
    @RequestMapping("/getshootdetailinfo")
    @UriKey(key = "com.zdsoft.finance.credit.shoot.getshootdetailinfo")
    @ResponseBody
    public String getShootDetailInfo(HttpServletRequest request, String caseApplyCode,
                                     String linkCode, String allNodeStr) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<PhotoTreeVo> voList = new ArrayList<PhotoTreeVo>();
        try {
            List<PhotoTree> list = photoTreeService.findShootPhotoDetail(caseApplyCode, linkCode, allNodeStr);
            for (PhotoTree photoTree : list) {
                voList.add(new PhotoTreeVo(photoTree));
            }
            map.put("shootDetailList", voList);
        } catch (Exception e) {
            logger.error("获取资调-摄像详细数据失败");
            e.printStackTrace();
            return AppServerUtil.buildError(AppStatus.SystemError, e);
        }
        return AppServerUtil.buildJsonObject(map);
    }

    /**
     * @Title: uploadShootFile
     * @Description: 向服务器上传摄像文件
     * @author panshm
     * @param request        HttpServletRequest
     * @param multipartfiles 文件流数组
     * @return
     */
    @RequestMapping("/uploadshootfile")
    @UriKey(key = "com.zdsoft.finance.credit.shoot.uploadShootFile")
    @ResponseBody
    public String uploadShootFile(HttpServletRequest request, @RequestParam("fileDatas") CommonsMultipartFile[] files) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            photoTreeService.saveShootPhotoFiles(files);
            map.put("status", AppStatus.Succeed.value());
            map.put("message", "上传成功！");
        } catch (Exception e) {
            logger.error("获取资调-摄像详细数据失败");
            e.printStackTrace();
            return AppServerUtil.buildError(AppStatus.SystemError, e);
        }
        return AppServerUtil.buildJsonObject(AppStatus.Succeed, map);
    }

    /**
     * @Title: submitShootData
     * @Description: 将上传的照片文件对应的数据保存到服务器
     * @author panshm
     * @param request       HttpServletRequest
     * @param addListJson   待添加的文件列表 一种分类的完整数据（抵押物-抵押物1，不存添删改的记录信息）
     * @param delListJson   待删除的文件列表,暂时无效，传值为NULL
     * @param isOver        是否是最终上传,如果为true生成 pdf
     * @param caseApplyCode 案件号
     * @param linkCode      环节编号，资调的环节编号=06
     * @return
     */
    @RequestMapping("/submitshootdata")
    @UriKey(key = "com.zdsoft.finance.credit.shoot.submitShootData")
    @SuppressWarnings("unchecked")
    @ResponseBody
    public String submitShootData(HttpServletRequest request, String addListJson, String delListJson,
                                  String isOver, String caseApplyCode, String linkCode) {
        logger.debug("###submitShootData() isOver={}, caseApplyCode={}, linkCode={},addListJson={" + addListJson + "},isOver={" + isOver + "}," +
                "caseApplyCode={" + caseApplyCode + "},linkCode={" + linkCode + "}");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (StringUtils.isNotEmpty(addListJson)) {
                List<PhotoTree> addList = (List<PhotoTree>) JsonHelper.jsonToJavas(addListJson, new PhotoTree());
                if (addList.size() != 0) {
                    photoTreeService.submitData(addList,caseApplyCode,linkCode,isOver);
                }
            }
            map.put("status", AppStatus.Succeed.value());
            map.put("message", "提交成功！");
        } catch (Exception e) {
            logger.error("获取资调-摄像详细数据失败");
            e.printStackTrace();
            return AppServerUtil.buildError(AppStatus.SystemError, e);
        }
        return AppServerUtil.buildJsonObject(map);
    }

    /**
     *
     * @Title: repaymentplantrial
     * @Description: 生成还款计划参数对象
     * @author denglw
     * @param planForm
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/repaymentplantrial", produces = "text/plain;charset=UTF-8")
    public String repaymentplantrial(ReceivablePlanForm planForm) {
        try {
            logger.info("进入还款计划试算！！！！！！！！！！！！！！！！");
            BigDecimal planPrincipalAmount = BigDecimal.ZERO;
            BigDecimal planInterestAmount = BigDecimal.ZERO;
            Integer totalCount = 0;
            Integer term = planForm.getTerm();
        	if(CaseApply.DATEUNIT_DAY.equals(planForm.getTermUnit())){
        		return AppServerUtil.buildJsonObject(AppStatus.ArgsError,"期限暂不支持日算法！", "0");
        	}
            if (CaseApply.DATEUNIT_YEAR.equals(planForm.getTermUnit())) {
                planForm.setTerm(Integer.valueOf(term) * 12);
            } 
            
            if (ReceivableInfo.RECEIVABLEINFO_MONTH.equals(planForm.getRateUnit())) {
                planForm.setRate(BigDecimalCalculateTwo.mul(planForm.getRate(), BigDecimal.valueOf(12d)));
            } else if (ReceivableInfo.RECEIVABLEINFO_DAY.equals(planForm.getRateUnit())) {
                planForm.setRate(BigDecimalCalculateTwo.mul(planForm.getRate(), BigDecimal.valueOf(36.5d)));
            }

            List<RepayPlan> planList = receivablePlanService
                    .receivablePlanGuarate(planForm);
            for (RepayPlan plan : planList) {
                planPrincipalAmount = BigDecimalCalculateTwo.add(
                        planPrincipalAmount, plan.getPlanPrincipalAmount());
                planInterestAmount = BigDecimalCalculateTwo.add(
                        planInterestAmount, plan.getPlanInterestAmount());
                totalCount = plan.getPeriods();
            }

            Map<String, Object> result = new HashMap<String, Object>();
            Map<String, Object> resultdata = new HashMap<String, Object>();
            result.put("status", AppStatus.Succeed.value());
            result.put("message", "试算成功");
            resultdata.put("loanAmount", planPrincipalAmount + "");
            resultdata.put("periodNum", totalCount + "");
            resultdata.put("totalCount", planList.size() + "");
            resultdata.put("periodsNum", term + "");
            resultdata.put("periodsNumUnit", CED.loadSimpleCodeNameByFullCode(planForm.getTermUnit()));
            resultdata.put("grossInterest", planInterestAmount + "");
            resultdata.put(
                    "repaymentTotal",
                    BigDecimalCalculateTwo.add(planPrincipalAmount,
                            planPrincipalAmount) + "");
            resultdata.put("list", planList);
            result.put("data", resultdata);
            logger.info("试算成功！！！！！！！！！！！！！！！！");
            if (ObjectHelper.isEmpty(planList)) {
                logger.error("暂不支持该还款计划");
                return AppServerUtil.buildJsonMessage(AppStatus.ArgsError, "暂不支持该还款计划");
            }
            return AppServerUtil.buildJsonObjectTwo(result);
        } catch (Exception e) {
            logger.error(e.getMessage() + "");
            return AppServerUtil
                    .buildJsonMessage(AppStatus.SystemError, "试算失败");
        }

    }

    /**
     *
     * @Title: saverepaymentplan
     * @Description: 保存还款计划
     * @author jingjiyan
     * @param caseApplyId 案件ID
     * @param receivableInfoId 还款基本信息
     * @param planList 还款计划JSON
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saverepaymentplan", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
    public String saverepaymentplan(String caseApplyId,
                                    String receivableInfoId, String planList) {
        try {
            receivablePlanTempService.saveReceivablePlanTemp(caseApplyId,
                    receivableInfoId, planList);
            logger.info("保存成功！！！！！！！！！！！！！！！！");
            return AppServerUtil.buildJsonMessage(AppStatus.Succeed, "保存成功");
        } catch (Exception e) {
            logger.error(e.getMessage() + "");
            return AppServerUtil.buildJsonMessage(AppStatus.LogicError, "保存失败");
        }
    }

    /**
     *
     * @Title: getrepaymentplan
     * @Description: 还款计划信息回显
     * @author jingjiyan
     * @param caseApplyId 案件ID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getrepaymentplan", produces = "text/plain;charset=UTF-8")
    public String getrepaymentplan(String caseApplyId) {
        try {
            logger.info("进入还款计划信息回显！！！！！！！！！！！！！！！！");
            BigDecimal planPrincipalAmount = BigDecimal.ZERO;
            BigDecimal planInterestAmount = BigDecimal.ZERO;
            Integer totalCount = 0;
            List<ReceivablePlanTemp> planList = receivablePlanTempService
                    .findReceivablePlanTempByCaseApplyId(caseApplyId);
            for (ReceivablePlanTemp temp : planList) {
                planPrincipalAmount = BigDecimalCalculateTwo.add(
                        planPrincipalAmount, temp.getPlanPrincipalAmount());// 总本金
                planInterestAmount = BigDecimalCalculateTwo.add(
                        planInterestAmount, temp.getPlanInterestAmount());// 总利息
                totalCount = temp.getPeriods();// 期限
            }
            CaseApply caseApply = caseApplyService.findOne(caseApplyId);
            Map<String, Object> result = new HashMap<String, Object>();
            Map<String, Object> resultdata = new HashMap<String, Object>();
            result.put("status", AppStatus.Succeed.value());
            result.put("message", "获取信息成功");
            resultdata.put("loanAmount", planPrincipalAmount + "");
            resultdata.put("periodNum", totalCount + "");
            resultdata.put("totalCount", planList.size() + "");
            resultdata.put("periodsNum", caseApply.getApplyTerm() + "");
            resultdata.put("periodsNumUnit", CED.loadSimpleCodeNameByFullCode(caseApply.getApplyTermUnit()));
            resultdata.put("grossInterest", planInterestAmount + "");
            resultdata.put(
                    "repaymentTotal",
                    BigDecimalCalculateTwo.add(planPrincipalAmount,
                            planPrincipalAmount) + "");
            resultdata.put("list", planList);
            result.put("data", resultdata);
            logger.info("获取信息成功！！！！！！！！！！！！！！！！");
            return AppServerUtil.buildJsonObjectTwo(result);
        } catch (Exception e) {
            logger.error(e.getMessage() + "");
            return AppServerUtil.buildJsonMessage(AppStatus.LogicError,
                    "获取信息失败");
        }
    }

    /**
     *
     * @Title: getrepaymentplaninfo
     * @Description: 还款计划基本信息回显
     * @author jingjiyan
     * @param caseApplyId 案件ID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getrepaymentplaninfo", produces = "text/plain;charset=UTF-8")
    public String getrepaymentplaninfo(String caseApplyId) {
        try {
            ReceivableInfoVo vo = new ReceivableInfoVo();
            BigDecimal planInterestAmount = BigDecimal.ZERO;
            ReceivableInfo info = receivableInfoService
                    .findByCaseApplyId(caseApplyId);
            CaseApply caseApply = caseApplyService.findOne(caseApplyId);
            CaseApplyVo caseVo = new CaseApplyVo(caseApply);
            if (ObjectHelper.isNotEmpty(info)) {
                vo = new ReceivableInfoVo(info);
            }else{
                vo.setLoanMonthRate(caseVo.getApplyRate());
                vo.setLoanMonthRateUnit(caseVo.getApplyRateUnit());
            }
            Map<String, Object> result = new HashMap<String, Object>();
            Map<String, Object> resultdata = new HashMap<String, Object>();
            List<ReceivablePlanTemp> planList = receivablePlanTempService
                    .findReceivablePlanTempByCaseApplyId(caseApplyId);
            for (ReceivablePlanTemp temp : planList) {
                planInterestAmount = BigDecimalCalculateTwo.add(
                        planInterestAmount, temp.getPlanInterestAmount());// 获取总利息
            }
            vo.setApplyAmount(caseApply.getApplyAmount());
            vo.setApplyTerm(caseApply.getApplyTerm());
            vo.setApplyTermUnit(caseApply.getApplyTermUnit());
            vo.setApplyTermUnitName(CED.loadSimpleCodeNameByFullCode(caseApply.getApplyTermUnit()));
            result.put("status", AppStatus.Succeed.value());
            result.put("message", "获取信息成功");
            resultdata.put("planInterestAmount", planInterestAmount);
            resultdata.put("info", vo);
            result.put("data", resultdata);
            return AppServerUtil.buildJsonObjectTwo(result);
        } catch (Exception e) {
            logger.error(e.getMessage() + "");
            return AppServerUtil.buildJsonMessage(AppStatus.SystemError,
                    "获取信息失败");
        }
    }

    /**
     *
     * @Title: saverepaymentplaninfo
     * @Description: 还款计划基本信息保存
     * @author jingjiyan
     * @param receivableInfo 还款计划信息Vo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saverepaymentplaninfo", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
    public String getrepaymentplaninfo(ReceivableInfoVo receivableInfo) {
        try {
            
            if ("YWDM0011903".equals(receivableInfo.getLoanMonthRateUnit())) {//贷款利率
            	receivableInfo.setLoanMonthRate(BigDecimalCalculateTwo.div(receivableInfo.getLoanMonthRate(), BigDecimal.valueOf(1000d)));
            }else{
            	receivableInfo.setLoanMonthRate(BigDecimalCalculateTwo.div(receivableInfo.getLoanMonthRate(), BigDecimal.valueOf(100d)));
            }
            
            if ("YWDM0011903".equals(receivableInfo.getOverdueDailyRateUnit())) {//逾期利率
            	receivableInfo.setOverdueDailyRate(BigDecimalCalculateTwo.div(receivableInfo.getOverdueDailyRate(), BigDecimal.valueOf(1000d)));
            } else {
            	receivableInfo.setOverdueDailyRate(BigDecimalCalculateTwo.div(receivableInfo.getOverdueDailyRate(), BigDecimal.valueOf(100d)));
            }
            
            
            if ("YWDM0011903".equals(receivableInfo.getSyntheticalRateUnit())) {// 综合利率
            	receivableInfo.setSyntheticalRate(BigDecimalCalculateTwo.div(receivableInfo.getSyntheticalRate(), BigDecimal.valueOf(1000d)));
            } else{
            	 receivableInfo.setSyntheticalRate(BigDecimalCalculateTwo.div(receivableInfo.getSyntheticalRate(), BigDecimal.valueOf(100d)));
            }
            
            ReceivableInfo po = new ReceivableInfo();
            if (ObjectHelper.isNotEmpty(receivableInfo.getId())) {
                po = receivableInfoService.findOne(receivableInfo.getId());
                BeanUtils.copyProperties(receivableInfo, po, new String[]{
                        "id", "version", "createTime", "createBy", "createOrgCd", "busiForm", "isDeleted",
                        "receivableFlag"});
            } else {
                po = new ReceivableInfo();
                BeanUtils.copyProperties(receivableInfo, po, new String[]{
                        "id", "version", "createTime", "createBy", "createOrgCd", "isDeleted"});
            }
            po = receivableInfoService.saveOrUpdateReceivableInfo(po, receivableInfo.getApplyTerm(), receivableInfo.getApplyTermUnit());
            return AppServerUtil.buildJsonObject(AppStatus.Succeed, "保存成功", po.getId());
        } catch (Exception e) {
            logger.error(e.getMessage() + "");
            return AppServerUtil
                    .buildJsonMessage(AppStatus.SystemError, "保存失败");
        }
    }

    /**
     *
     * @Title: getinternalRateReturn
     * @Description: 动态利率获取
     * @author jingjiyan
     * @param principalAmount 本金
     * @param term            期限
     * @param repayMethod     还款方式
     * @param rate            利率
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getinternalRateReturn", produces = "text/plain;charset=UTF-8")
    public String getinternalRateReturn(BigDecimal principalAmount,
                                        Integer term, String repayMethod, BigDecimal rate,String termUnit,String rateUnit) {
        if ("YWDM0051007".equals(repayMethod)
                || "YWDM0051007".equals(repayMethod)
                || "YWDM0051008".equals(repayMethod)
                || "YWDM0051010".equals(repayMethod)
                || "YWDM0051005".equals(repayMethod)) {
            try {
            	if(CaseApply.DATEUNIT_DAY.equals(termUnit)){
            		return AppServerUtil.buildJsonObject(AppStatus.ArgsError,"期限暂不支持日算法！", "0");
            	}
                if (CaseApply.DATEUNIT_YEAR.equals(termUnit)) {
                	term = Integer.valueOf(term) * 12;
                }
                if (ReceivableInfo.RECEIVABLEINFO_MONTH.equals(rateUnit)) {
                	rate= BigDecimalCalculateTwo.mul(rate, BigDecimal.valueOf(12d));
                } else if (ReceivableInfo.RECEIVABLEINFO_DAY.equals(rateUnit)) {
                	rate = BigDecimalCalculateTwo.mul(rate, BigDecimal.valueOf(36.5d));
                }
            	
                Map<String, Object> result = new HashMap<String, Object>();
                
        		BigDecimal totalInterestAmount=BigDecimalCalculateTwo.div(BigDecimalCalculateTwo.mul(BigDecimalCalculateTwo.mul(principalAmount,rate),BigDecimalCalculateTwo.div(BigDecimal.valueOf(term),BigDecimal.valueOf(12))),BigDecimal.valueOf(100));
        		BigDecimal eachTotalAmount=BigDecimalCalculateTwo.div(BigDecimalCalculateTwo.add(principalAmount, totalInterestAmount), BigDecimal.valueOf(term)).setScale(2, BigDecimal.ROUND_HALF_UP);//每期还款合计   
                List<Double> flowInArr = new ArrayList<Double>();
                flowInArr.add(BigDecimal.ZERO.subtract(principalAmount)
                        .doubleValue());
                for (int i = 0; i < term; i++) {
                    flowInArr.add(eachTotalAmount.doubleValue());
                }
                BigDecimal baseRate = IrrUtil.getIrr(flowInArr);// 实际月利率
                if (ReceivableInfo.RECEIVABLEINFO_DAY.equals(rateUnit)) {
                	baseRate = BigDecimalCalculateTwo.div(baseRate, BigDecimal.valueOf(10d));
                }
                result.put("status", AppStatus.Succeed.value());
                result.put("message", "获取动态利率成功");
                result.put("data", BigDecimalCalculateTwo.mul(baseRate, BigDecimal.valueOf(12)).setScale(6, BigDecimal.ROUND_HALF_UP));
                return AppServerUtil.buildJsonObjectTwo(result);
            } catch (Exception e) {
                logger.error(e.getMessage() + "");
                return AppServerUtil.buildJsonMessage(AppStatus.SystemError,
                        "获取动态利率失败");
            }
        } else {
            return AppServerUtil.buildJsonObject(AppStatus.ArgsError,
                    "该还款方式不支持获取动态利率", "0");
        }
    }

}
