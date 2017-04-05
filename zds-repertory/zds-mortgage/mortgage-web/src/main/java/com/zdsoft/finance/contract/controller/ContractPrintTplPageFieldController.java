package com.zdsoft.finance.contract.controller;

import com.alibaba.fastjson.JSON;
import com.zdsoft.finance.businesssetting.entity.SysLabel;
import com.zdsoft.finance.businesssetting.service.SysLabelService;
import com.zdsoft.finance.contract.entity.BussPrintTplPageField;
import com.zdsoft.finance.contract.service.BussPrintTplPageFieldService;
import com.zdsoft.finance.contract.vo.ElmentVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/contract")
public class ContractPrintTplPageFieldController extends BaseController {
    @Autowired
    BussPrintTplPageFieldService bussPrintTplPageFieldService;
    @Autowired
    private SysLabelService sysLabelService;
    
    @RequestMapping("/printTplPageField_Save")
    @UriKey(key = "com.zdsoft.finance.contract.printTplPageField_Save")
    @ResponseBody
    public ResponseMsg printTplPageField_Save(String ElmentJsonString) {
        ResponseMsg msg = new ResponseMsg();
        try {

            @SuppressWarnings("unchecked")
            List<ElmentVo> m_ElmentVoList = JSON.parseArray(ElmentJsonString, ElmentVo.class);
            List<BussPrintTplPageField> listb = new ArrayList<BussPrintTplPageField>();
            for (ElmentVo m_ElmentVo : m_ElmentVoList) {
                BussPrintTplPageField m_BussPrintTplPageField = new BussPrintTplPageField();

                m_BussPrintTplPageField.setWidth(m_ElmentVo.getWidth());
                m_BussPrintTplPageField.setHeight(m_ElmentVo.getHeight());
                m_BussPrintTplPageField.setFontsize(Integer.parseInt(m_ElmentVo.getFontsize().replace("px", "")));
                m_BussPrintTplPageField.setLabelid(m_ElmentVo.getLabelid());
                m_BussPrintTplPageField.setLabelname(m_ElmentVo.getLabelname());
                m_BussPrintTplPageField.setPageid(m_ElmentVo.getPageID());
                m_BussPrintTplPageField.setXcoordinate(Long.parseLong(m_ElmentVo.getLeft().toString()));
                m_BussPrintTplPageField.setYcoordinate(Long.parseLong(m_ElmentVo.getTop().toString()));
                listb.add(m_BussPrintTplPageField);
            }

            // 每次保存先删除再添加

            bussPrintTplPageFieldService.saveListWithDelete(listb);
            msg.setResultStatus(ResponseMsg.SUCCESS);
           
            msg.setMsg("保存成功！");

        } catch (Exception e) {
            // TODO: handle exception
            msg.setMsg("保存失败！");
            e.printStackTrace();
        }

        return msg;
    }


    /**
     * 
     * 获取模版上标签的位置
     *
     * @author huangdongkui
     * @param PageID 页ID
     * @param actionname edit：解析标签,view:解析标签
     * @param TradeObjectID 暂时没有
     * @param flagName 暂时没有
     * @return
     */
    @RequestMapping("/printTplPageFieldList")
    @UriKey(key = "com.zdsoft.finance.contract.printTplPageFieldList")
    @ResponseBody
    public String printTplPageFieldList(String PageID, String actionname, String TradeObjectID, String flagName) {
        String resultStr = "";
        List<BussPrintTplPageField> tempList = bussPrintTplPageFieldService.findBypageid(PageID,actionname);

        List<ElmentVo> listElment = new ArrayList<ElmentVo>();

        for (BussPrintTplPageField item : tempList) {

            ElmentVo tempElmentVo = new ElmentVo();
            tempElmentVo.setFontsize(item.getFontsize().toString() + "px");
            tempElmentVo.setHeight(item.getHeight());
            tempElmentVo.setLabelid(item.getLabelid());
            tempElmentVo.setLabelname(item.getLabelname());
            tempElmentVo.setLeft(Integer.parseInt(item.getXcoordinate().toString()));
            tempElmentVo.setTop(Integer.parseInt(item.getYcoordinate().toString()));
            tempElmentVo.setPageID(item.getPageid());
            tempElmentVo.setWidth(item.getWidth());
            listElment.add(tempElmentVo);
        }

        resultStr = JSON.toJSONString(listElment);

        return resultStr;
    }

    
    @RequestMapping("/getSysLabelBylabelType")
    @UriKey(key = "com.zdsoft.finance.contract.getSysLabelBylabelType")
    @ResponseBody
    /**
     * 
     * 根据书签类型获取书签
     *
     * @author huangdongkui
     * @param LabelType
     * @return
     * @throws Exception
     */
    public String getSysLabelBylabelType(String LabelType) throws Exception {
        String resultStr = "";
        
        List<SysLabel> tempList = sysLabelService.findBylabelType(LabelType);

        resultStr = JSON.toJSONString(tempList);

        return resultStr;
    }
}
