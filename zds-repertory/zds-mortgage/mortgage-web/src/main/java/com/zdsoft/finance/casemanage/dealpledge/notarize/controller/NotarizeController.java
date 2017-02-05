package com.zdsoft.finance.casemanage.dealpledge.notarize.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.casemanage.dealpledge.notarize.entity.Notarize;
import com.zdsoft.finance.casemanage.dealpledge.notarize.service.NotarizeService;
import com.zdsoft.finance.casemanage.dealpledge.notarize.vo.NotarizeVo;
import com.zdsoft.finance.cooperator.entity.CooperatorTerminal;
import com.zdsoft.finance.cooperator.service.CapitalistService;
import com.zdsoft.finance.cooperator.service.CooperatorTerminalService;
import com.zdsoft.finance.cooperator.vo.CooperatorTerminalVo;
import com.zdsoft.finance.customer.service.BeforeCustomerService;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.service.HousePropertyService;
import com.zdsoft.finance.marketing.vo.CaseApplyVo;
import com.zdsoft.finance.marketing.vo.HousePropertyVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;



/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:NotarizeController.java
 * @Package:com.zdsoft.finance.casemanage.dealpledge.notarize.controller
 * @Description:公证页面Controller
 * @author: caixiekang
 * @date:2017年1月10日 下午9:44:09
 * @version:v1.0
 */
@Controller
@RequestMapping("/casemanage/dealpledge/notarize")
public class NotarizeController extends BaseController {
    
    @Autowired
    private NotarizeService notarizeService;
    
    @Autowired
    private CED CED;
    
    @Autowired
    private HousePropertyService housePropertyService;
    
    @Autowired
    private CaseApplyService caseApplyService;
    
    @Autowired
    private CapitalistService capitalistService;
    
    @Autowired
    private CooperatorTerminalService cooperatorTerminalService;
    
    @Autowired
    private BeforeCustomerService beforeCustomerService;
    
    /**
     * 
     * 初始化页面
     *
     * @author caixiekang
     * @param caseApplyId
     * @param processInstanceId
     * @param businessKey
     * @return
     */
    @RequestMapping("/viewNotarize")
    @UriKey(key = "com.zdsoft.finance.casemanage.dealpledge.notarize.viewNotarize")
    @Menu(resource = "com.zdsoft.finance.casemanage.dealpledge.notarize.viewNotarize", label = "公证", group = "archive", order = 2)
    //@ManualJob(resource = "com.zdsoft.finance.project.notarize.viewNotarize", label = "费用支拥查看")
    //@FinishJob(resource = "com.zdsoft.finance.project.notarize.viewNotarize", businessId = "businessKey", projectId = "projectId")
    public ModelAndView viewNotarize(String caseApplyId, String processInstanceId, String businessKey){
        ModelMap model = new ModelMap();
        caseApplyId = "liu";//模拟数据
        model.put("caseApplyId", caseApplyId);
        model.put("processInstanceId", processInstanceId);
        model.put("businessKey", businessKey);
        //基本信息
        CaseApply basicInfo = null;
        CooperatorTerminal cooperatorTerminal = null;
        
        try {
            basicInfo = caseApplyService.findOne(caseApplyId);
            cooperatorTerminal = cooperatorTerminalService.findOne(basicInfo.getTerminalId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询数据失败",e.getMessage());
        }
        
        if(ObjectHelper.isNotEmpty(basicInfo) && ObjectHelper.isNotEmpty(cooperatorTerminal)){
            CaseApplyVo basicInfoVo = new CaseApplyVo(basicInfo);
            model.put("basicInfoVo", basicInfoVo);
            
            CooperatorTerminalVo cooperatorTerminalVo = new CooperatorTerminalVo(cooperatorTerminal);
            model.put("cooperatorTerminalVo", cooperatorTerminalVo);
        }
        return new ModelAndView("/casemanage/dealpledge/notarize/notarize_view", model);
    }
    
    
    /**
     * 
     * 查找押品信息
     *
     * @author caixiekang
     * @param pageable
     * @param caseApplyId
     * @return
     */
    @RequestMapping("/listHouse")
    @UriKey(key = "com.zdsoft.finance.casemanage.dealpledge.notarize.listHouse")
    @ResponseBody
    public ResponseMsg listHouse(String caseApplyId){
        ResponseMsg msg = new ResponseMsg();
        List<HouseProperty> list = housePropertyService.findByCaseApplyId(caseApplyId);
        List<HousePropertyVo> listVo = new ArrayList<>();
        for (HouseProperty houseProperty : list) {
            HousePropertyVo housePropertyVo = new HousePropertyVo(houseProperty);
            listVo.add(housePropertyVo);
        }
        msg.setMsg("房产（押品）列表查询成功");
        msg.setResultStatus(ResponseMsg.SUCCESS);
        //msg.setTotal(list.getTotalRows());
        msg.setRows(listVo);
        
        return msg;
    }
    
    /**
     * 
     * @param pageable
     * @param flag
     * @return
     */
    @RequestMapping("/listNotarize")
    @UriKey(key = "com.zdsoft.finance.casemanage.dealpledge.notarize.listNotarize")
    @ResponseBody
    public ResponseMsg listNotarize(PageRequest pageable, String caseApplyId){
        //查找该案件Id的所有公证
        ResponseMsg msg = new ResponseMsg();
        try {
            Page<Notarize> page = notarizeService.queryNotarizeBycaseApplyId(pageable, null);
            List<NotarizeVo> listVo = new ArrayList<NotarizeVo>();
            for (Notarize notarize : page.getRecords()) {
                NotarizeVo notarizeVo = new NotarizeVo(notarize);
                listVo.add(notarizeVo);
            }
            msg.setMsg("公证列表查询成功");
            msg.setResultStatus(ResponseMsg.SUCCESS);
            msg.setTotal(page.getTotalRows());
            msg.setRows(listVo);
        } catch (com.zdsoft.finance.common.exception.BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            msg.setMsg("公证列表查询失败");
            msg.setResultStatus(ResponseMsg.APP_ERROR);
        }
        
        return msg;
        
                
    }
   

    /**
     * 新增或编辑公证
     * @param notarizeVo 公证VO对象
     * @return
     */
    @RequestMapping("/addNotarize")
    @UriKey(key = "com.zdsoft.finance.casemanage.dealpledge.notarize.saveOrUpdateNotarize")
    @ResponseBody
    public ResponseMsg saveOrUpdateNotarize(NotarizeVo notarizeVo) {
        ResponseMsg msg = new ResponseMsg();
        
        try {
            //EmpDto empDto = CED.getLoginUser();
            Notarize notarize = notarizeVo.toPo();
            Notarize notarize2 = null ;
            //新建公证
            if(ObjectHelper.isEmpty(notarizeVo.getId())){
//                notarize.setCreateBy(empDto.getEmpCd());
//                notarize.setCreateOrgCd(empDto.getEmpCd());
                notarize2 = new Notarize();
                BeanUtils.copyProperties(notarize, notarize2);
            }else{  //编辑公证
                notarize2 = notarizeService.findOne(notarizeVo.getId());
//                notarize.setUpdateBy(empDto.getEmpCd());
//                notarize.setUpdateOrgCd(empDto.getEmpCd());
                BeanUtils.copyProperties(notarize, notarize2,new String[]{"id","createBy","createOrgCd","createTime"});
            }
            
            
            notarizeService.saveOrUpdateEntity(notarize2);
            msg.setResultStatus(ResponseMsg.SUCCESS);
            msg.setMsg("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("保存失败", e);
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            msg.setMsg("系统内部错误，请查看日志");
        }
        return msg;
    }
    
    
    
    /**
     * 删除公证
     * @param notarizeVo 公证VO对象
     * @return
     */
    @RequestMapping("/deleteNotarize")
    @UriKey(key = "com.zdsoft.finance.casemanage.dealpledge.notarize.deleteNotarize")
    @ResponseBody
    public ResponseMsg deleteNotarize(String id) {
        ResponseMsg msg = new ResponseMsg();
        try {
//            EmpDto empDto = CED.getLoginUser();
            Notarize notarize = notarizeService.findOne(id);
//            notarize.setUpdateBy(empDto.getEmpCd());
//            notarize.setUpdateOrgCd(empDto.getEmpCd());
            notarizeService.logicDelete(notarize);
            msg.setResultStatus(ResponseMsg.SUCCESS);
            msg.setMsg("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除失败", e);
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            msg.setMsg("系统内部错误，请查看日志");
        }
        return msg;
    }
    
//    /**
//     * 
//     * 公证流程逻辑保存
//     *
//     * @author caixiekang
//     * @param vo
//     * @param businessKey
//     * @param projectId
//     * @return
//     */
//    @RequestMapping(value = "/editJobSave")
//    @UriKey(key = "com.zdsoft.finance.casemanage.dealpledge.notarize.editJobSave")
//    @ResponseBody
//    @FinishJob(resource = "com.zdsoft.finance.prCostitemApply.editJob", businessId = "businessKey", projectId = "caseApplyId")
//    public ResponseMsg notarizeEditSave(String businessKey,String caseApplyId) {
//        ResponseMsg msg = new ResponseMsg();
//        
//        try {
//            //有公证数据
//            if(ObjectHelper.isNotEmpty(notarizeService.findOne(caseApplyId))){
//                msg.setResultStatus(ResponseMsg.SUCCESS);
//                msg.setMsg("保存成功");
//            }else{
//            //无公证数据
//                throw new BusinessException();
//            }
//        }catch (Exception e) {
//            msg.setResultStatus(ResponseMsg.APP_ERROR);
//            msg.setMsg("保存失败");
//            logger.error("保存失败",e);
//            e.printStackTrace();
//        }
//        return msg;
//    }
    

    
}


