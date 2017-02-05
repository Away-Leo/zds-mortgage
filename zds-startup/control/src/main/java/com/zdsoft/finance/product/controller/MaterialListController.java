package com.zdsoft.finance.product.controller;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.basedata.SimpleCodeDto;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.MateriaListAuth;
import com.zdsoft.finance.product.entity.MaterialList;
import com.zdsoft.finance.product.service.MateriaListAuthService;
import com.zdsoft.finance.product.service.MateriaListService;
import com.zdsoft.finance.product.util.SimpleCodeCache;
import com.zdsoft.finance.product.vo.MaterialListVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 资料清单controller
 *
 * @author LiaoGuoWei
 * @create 2016-12-23 15:31
 **/
@Controller
@RequestMapping("/materialList")
public class MaterialListController extends BaseController {

    @Autowired
    private MateriaListService materiaListService;

    @Autowired
    private CED CED;

    @Autowired
    private MateriaListAuthService materiaListAuthService;

    /**
     * 进入资料清单页面
     *
     * @param modelAndView
     * @param productCode
     * @return
     */
    @RequestMapping("/materialListPage")
    @UriKey(key = "com.zdsoft.finance.MaterialListPage")
    public ModelAndView materialListPage(ModelAndView modelAndView, String productCode,String productName) {
        modelAndView.setViewName("product/product_material_list");
        modelAndView.addObject("productCode", productCode);
        modelAndView.addObject("productName", productName);


        //查找当前应用所配置的流程 TODO
        List<Map<String, Object>> processData = new ArrayList<Map<String, Object>>();
        Map<String, Object> process1 = new HashMap<String, Object>();
        process1.put("id", "1111");
        process1.put("name", "房贷流程1");
        processData.add(process1);
        Map<String, Object> process2 = new HashMap<String, Object>();
        process2.put("id", "2222");
        process2.put("name", "房贷流程2");
        processData.add(process2);
        modelAndView.addObject("processData", processData);


        return modelAndView;
    }

    /**
     * 资料清单列表数据查找
     *
     * @param request
     * @param pageRequest
     * @param productCode
     * @param jsoncallback
     * @return
     */
    @RequestMapping("/materialListData")
    @UriKey(key = "com.zdsoft.finance.materialListData")
    @ResponseBody
    public String materialListData(HttpServletRequest request, PageRequest pageRequest, String productCode, String jsoncallback) {
        ResponseMsg msg = new ResponseMsg();
        // 获取页面封装的查询参数
        @SuppressWarnings("unchecked")
        List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
        Page<MaterialList> sourceData = this.materiaListService.findByHqlConditions(pageRequest, queryObjs);
        //声明返回页面的集合
        List<MaterialListVo> returnList = new ArrayList<MaterialListVo>();
        for (MaterialList temp : sourceData.getRecords()) {
            MaterialListVo materialListVo = new MaterialListVo(temp);
            returnList.add(materialListVo);
        }

        msg.setRows(returnList);
        msg.setTotal(sourceData.getTotalRows());


        return ObjectHelper.objectToJson(msg, jsoncallback);

    }

    /**
     * 资料清单保存或修改
     *
     * @param materialListVo
     * @return
     */
    @RequestMapping(value = "/materiaAdd"/*, produces = MediaType.APPLICATION_JSON_UTF8_VALUE*/)
    @UriKey(key = "com.zdsoft.finance.materiaAdd")
    @ResponseBody
    public ResponseMsg materiaAdd(MaterialListVo materialListVo,HttpServletRequest request) {
        ResponseMsg msg = new ResponseMsg();
        Enumeration<String> sss=request.getParameterNames();
        request.getParameter("materiaIdentifyName");
        Map<String, Object> returnData = new HashMap<String, Object>();
        try {
            MaterialList materialList = this.materiaListService.saveOrUpdateMateriaList(materialListVo.toPo());
            returnData.put("data", materialList);
            msg.setResultStatus(ResponseMsg.SUCCESS);
            msg.setOptional(returnData);
        } catch (BusinessException e) {
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            msg.setMsg(e.getMessage());
            logger.error("保存资料清单出错！", e);
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 逻辑删除资料清单
     *
     * @param id
     * @return
     */
    @RequestMapping("/materiaDeleteById")
    @UriKey(key = "com.zdsoft.finance.materiaDeleteById")
    @ResponseBody
    public ResponseMsg materiaDeleteById(String id) {
        ResponseMsg msg = new ResponseMsg();
        try {
            MaterialList materialList = this.materiaListService.logicDelete(id);
            msg.setResultStatus(ResponseMsg.SUCCESS);
        } catch (BusinessException e) {
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            msg.setMsg(e.getMessage());
            e.printStackTrace();
            logger.error("删除资料清单出错！", e);
        }
        return msg;
    }

    /**
     * 按照流程ID查找流程节点
     *
     * @return
     */
    @RequestMapping("/findProcessNode")
    @UriKey(key = "com.zdsoft.finance.findProcessNode")
    @ResponseBody
    public String findProcessNode(String jsoncallback) {
        List<Map<String, Object>> retrnList = new ArrayList<Map<String, Object>>();

        Map<String, Object> returnDada = new HashMap<String, Object>();
        Map<String, Object> process1 = new HashMap<String, Object>();
        process1.put("id", "1111");
        process1.put("name", "房贷流程1");
        List<Map<String,Object>> node1List=new ArrayList<>();
        Map<String, Object> node11 = new HashMap<String, Object>();
        node11.put("field", "node1");
        node11.put("name", "nodeName1");
        node11.put("processid", "1111");
        node11.put("processname", "房贷流程1");
        node1List.add(node11);
        Map<String, Object> node12 = new HashMap<String, Object>();
        node12.put("field", "node2");
        node12.put("name", "nodeName2");
        node12.put("processid", "1111");
        node12.put("processname", "房贷流程1");
        node1List.add(node12);
        Map<String, Object> node13 = new HashMap<String, Object>();
        node13.put("field", "node3");
        node13.put("name", "nodeName3");
        node13.put("processid", "1111");
        node13.put("processname", "房贷流程1");
        node1List.add(node13);
        Map<String, Object> node14 = new HashMap<String, Object>();
        node14.put("field", "node4");
        node14.put("name", "nodeName4");
        node14.put("processid", "1111");
        node14.put("processname", "房贷流程1");
        node1List.add(node14);
        returnDada.put("process", process1);
        returnDada.put("nodes", ObjectHelper.objectToJson(node1List));
        retrnList.add(returnDada);

        Map<String, Object> returnDada2 = new HashMap<String, Object>();
        Map<String, Object> process2 = new HashMap<String, Object>();
        process2.put("id", "2222");
        process2.put("name", "房贷流程2");
        Map<String, Object> node21 = new HashMap<String, Object>();
        List<Map<String,Object>> node2List=new ArrayList<>();
        node21.put("field", "node21");
        node21.put("name", "nodeName21");
        node21.put("processid", "2222");
        node21.put("processname", "房贷流程2");
        node2List.add(node21);
        Map<String, Object> node22 = new HashMap<String, Object>();
        node22.put("field", "node22");
        node22.put("name", "nodeName22");
        node22.put("processid", "2222");
        node22.put("processname", "房贷流程2");
        node2List.add(node22);
        Map<String, Object> node23 = new HashMap<String, Object>();
        node23.put("field", "node23");
        node23.put("name", "nodeName23");
        node23.put("processid", "2222");
        node23.put("processname", "房贷流程2");
        node2List.add(node23);
        Map<String, Object> node24 = new HashMap<String, Object>();
        node24.put("field", "node24");
        node24.put("name", "nodeName24");
        node24.put("processid", "2222");
        node24.put("processname", "房贷流程2");
        node2List.add(node24);
        returnDada2.put("process", process2);
        returnDada2.put("nodes", ObjectHelper.objectToJson(node2List));
        retrnList.add(returnDada2);
        return ObjectHelper.objectToJson(retrnList);
    }

    /**
     * 获得资料名称simpleCode数据
     *
     * @param codeCategoryId 识别ID
     * @return
     */
    @RequestMapping("/getMateriaSimpleCode")
    @UriKey(key = "com.zdsoft.finance.getMateriaSimpleCode")
    @ResponseBody
    public String getMateriaSimpleCode(String codeCategoryId,String jsoncallback) {
        List<Map<String,Object>> returnData =null;
        //如果缓存为空 则写数据进入缓存
        if (SimpleCodeCache.MATERIA_SIMPLES.size() == 0) {
            this.getAllCode();
        }
        if (ObjectHelper.isEmpty(codeCategoryId)) {
            returnData=new ArrayList<Map<String,Object>>();
            for(String key:SimpleCodeCache.MATERIA_SIMPLES.keySet()){
                returnData.addAll(SimpleCodeCache.MATERIA_SIMPLES.get(key));
            }
        } else {
            returnData = SimpleCodeCache.MATERIA_SIMPLES.get(codeCategoryId);
        }
        return ObjectHelper.objectToJson(returnData,jsoncallback);
    }

    @RequestMapping("/checkOnlyData")
    @UriKey(key = "com.zdsoft.finance.checkOnlyData")
    @ResponseBody
    public ResponseMsg checkOnlyData(String materiaTypeCode,String productCode,String materiaCode){
        ResponseMsg msg=new ResponseMsg();
        Map<String,Object> returnData=new HashMap<>();
        try{
//            MaterialList materialList=this.materiaListService.findByMateriaTypeCdAndProductCdAndMateriaCd(materiaTypeCode,productCode,materiaCode);
            MaterialList materialList=this.materiaListService.findByProductAndClass("4028a1195991ba27015991e632e70020","贷款用途");
            returnData.put("data",materialList);
            msg.setResultStatus(ResponseMsg.SUCCESS);
            msg.setOptional(returnData);
        }catch (BusinessException e){
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 二维码页面
     * @param modelAndView
     * @param productCode
     * @return
     */
    @RequestMapping("/twoCodePage")
    @UriKey(key = "com.zdsoft.finance.twoCodePage")
    public ModelAndView twoCodePage(ModelAndView modelAndView,String productCode,String materiaTypeCode,String materiaCode,HttpServletRequest request){
        modelAndView.setViewName("product/product_material_twocodes");
        try{
            List<MaterialList> sourceData=this.materiaListService.findByTypeCdAndProductIdAndMateriaCd(materiaTypeCode, productCode, materiaCode);
            String realPath = request.getSession().getServletContext().getRealPath("/");
            logger.info("》》》》》》》》》》》》》》》》》》》》获取的服务器绝对路径为:"+realPath);
            Map<String,List<List<Map<String,Object>>>> sourcePageData=this.materiaListService.getTwoCodeData(sourceData,realPath);
            modelAndView.addObject("data",sourcePageData);
        }catch (Exception e){
            logger.error("查询二维码数据出错",e);
            e.printStackTrace();
        }
        modelAndView.addObject("productCode",productCode);
        modelAndView.addObject("materiaTypeCode",materiaTypeCode);
        modelAndView.addObject("materiaCode",materiaCode);
        return modelAndView;
    }

    @RequestMapping("/printPage")
    @UriKey(key = "com.zdsoft.finance.printPage")
    public ModelAndView printPage (ModelAndView modelAndView,String htmlContent){
        modelAndView.setViewName("product/product_materia_print");
        modelAndView.addObject("content",htmlContent);
        return modelAndView;
    }

    /**
     * 根据条件查找资料清单
     * @param materiaTypeCode
     * @param productCode
     * @param materiaCode
     * @param jsoncallback
     * @return
     */
    @RequestMapping("/findByCondition")
    @UriKey(key = "com.zdsoft.finance.findByCondition")
    @ResponseBody
    public String findByCondition(String materiaTypeCode,String productCode,String materiaCode,HttpServletRequest request,String jsoncallback){
        Map<String,Object> returnData=new HashMap<String,Object>();
        try{
            List<MaterialList> sourceData=this.materiaListService.findByTypeCdAndProductIdAndMateriaCd(materiaTypeCode, productCode, materiaCode);
            String realPath = request.getSession().getServletContext().getRealPath("/");
        }catch (BusinessException e){
            logger.error("根据条件查找资料清单出错！",e);
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 资料清单权限节点保存或修改
     * @param dataJsonStr 节点json字符串
     * @param productId 产品ID
     * @param materiaListId 资料ID
     * @return
     */
    @RequestMapping(value = "/saveOrUpdateMateriaListAuth")
    @UriKey(key = "com.zdsoft.finance.saveOrUpdateMateriaListAuth")
    @ResponseBody
    public ResponseMsg saveOrUpdateMateriaListAuth(String dataJsonStr,String productId,String materiaListId){
        ResponseMsg msg=new ResponseMsg();
        try{
            List<MateriaListAuth> sourceData=this.materiaListAuthService.saveOrUpdateMateriaAuth(dataJsonStr, productId, materiaListId);
            msg.setResultStatus(ResponseMsg.SUCCESS);
        }catch (Exception e){
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            msg.setMsg(e.getMessage());
            logger.error("保存资料清单节点权限出错",e);
            e.printStackTrace();
        }
        return msg;
    }

    /************************************私有方法集********************************************/
    private Map<String, List<Map<String,Object>>> getAllCode() {
        try {
            //客户资料
            List<SimpleCodeDto> simpleCodeDtoClient = CED.querySimpleCodeByCategoryId("maclass1");
            List<Map<String, Object>> clientSim = new ArrayList<Map<String, Object>>();
            for (SimpleCodeDto temp : simpleCodeDtoClient) {
                Map<String, Object> clientSimMap = new HashMap<String, Object>();
                clientSimMap.put("id", temp.getId());
                clientSimMap.put("text", temp.getName());
                clientSimMap.put("rememberNo", temp.getCode());
                clientSimMap.put("rememberCode", temp.getFullCode());
                clientSim.add(clientSimMap);
            }
            SimpleCodeCache.MATERIA_SIMPLES.put("maclass1", clientSim);
            //合同
            List<SimpleCodeDto> simpleCodeDtoContract = CED.querySimpleCodeByCategoryId("maclass2");
            List<Map<String, Object>> contractSim = new ArrayList<Map<String, Object>>();
            for (SimpleCodeDto temp : simpleCodeDtoContract) {
                Map<String, Object> contractSimMap = new HashMap<String, Object>();
                contractSimMap.put("id", temp.getId());
                contractSimMap.put("text", temp.getName());
                contractSimMap.put("rememberNo", temp.getCode());
                contractSimMap.put("rememberCode", temp.getFullCode());
                contractSim.add(contractSimMap);
            }
            SimpleCodeCache.MATERIA_SIMPLES.put("maclass2", contractSim);
            //内审文件夹
            List<SimpleCodeDto> simpleCodeDtoFolder = CED.querySimpleCodeByCategoryId("maclass3");
            List<Map<String, Object>> folderSim = new ArrayList<Map<String, Object>>();
            for (SimpleCodeDto temp : simpleCodeDtoFolder) {
                Map<String, Object> folderSimMap = new HashMap<String, Object>();
                folderSimMap.put("id", temp.getId());
                folderSimMap.put("text", temp.getName());
                folderSimMap.put("rememberNo", temp.getCode());
                folderSimMap.put("rememberCode", temp.getFullCode());
                folderSim.add(folderSimMap);
            }
            SimpleCodeCache.MATERIA_SIMPLES.put("maclass3", folderSim);
            //权证类
            List<SimpleCodeDto> simpleCodeDtoWarrant = CED.querySimpleCodeByCategoryId("maclass4");
            List<Map<String, Object>> warrantSim = new ArrayList<Map<String, Object>>();
            for (SimpleCodeDto temp : simpleCodeDtoWarrant) {
                Map<String, Object> warrantSimMap = new HashMap<String, Object>();
                warrantSimMap.put("id", temp.getId());
                warrantSimMap.put("text", temp.getName());
                warrantSimMap.put("rememberNo", temp.getCode());
                warrantSimMap.put("rememberCode", temp.getFullCode());
                warrantSim.add(warrantSimMap);
            }
            SimpleCodeCache.MATERIA_SIMPLES.put("maclass4", warrantSim);
            //银行信息
            List<SimpleCodeDto> simpleCodeDtoBank = CED.querySimpleCodeByCategoryId("maclass5");
            List<Map<String, Object>> bankSim = new ArrayList<Map<String, Object>>();
            for (SimpleCodeDto temp : simpleCodeDtoBank) {
                Map<String, Object> bankSimMap = new HashMap<String, Object>();
                bankSimMap.put("id", temp.getId());
                bankSimMap.put("text", temp.getName());
                bankSimMap.put("rememberNo", temp.getCode());
                bankSimMap.put("rememberCode", temp.getFullCode());
                bankSim.add(bankSimMap);
            }
            SimpleCodeCache.MATERIA_SIMPLES.put("maclass5", bankSim);
            //其他
            List<SimpleCodeDto> simpleCodeDtoOther = CED.querySimpleCodeByCategoryId("maclass6");
            List<Map<String, Object>> otherSim = new ArrayList<Map<String, Object>>();
            for (SimpleCodeDto temp : simpleCodeDtoOther) {
                Map<String, Object> otherSimMap = new HashMap<String, Object>();
                otherSimMap.put("id", temp.getId());
                otherSimMap.put("text", temp.getName());
                otherSimMap.put("rememberNo", temp.getCode());
                otherSimMap.put("rememberCode", temp.getFullCode());
                otherSim.add(otherSimMap);
            }
            SimpleCodeCache.MATERIA_SIMPLES.put("maclass6", otherSim);
        } catch (Exception e) {
            logger.error("资料清单查询simpleCode出错", e);
            e.printStackTrace();
        }
        return SimpleCodeCache.MATERIA_SIMPLES;
    }
}
