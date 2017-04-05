package com.zdsoft.finance.contract.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.drools.compiler.lang.dsl.DSLMapParser.mapping_file_return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.basedata.AttachmentDto;
import com.zdsoft.finance.businesssetting.service.SysLabelService;
import com.zdsoft.finance.contract.vo.CustomXWPFDocument;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

@Controller
@RequestMapping("/contract")
public class ContractPrintDocController extends BaseController {

    @Autowired
    private CED ced;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLabelService sysLabelService;

    @RequestMapping("/conDocGen")
    @UriKey(key = "com.zdsoft.finance.contract.conDocGen")
    @ResponseBody
    public ModelAndView genDocContract(String attachmentId, String zdsToken, String userToken) {

        try {
            String path = request.getSession().getServletContext().getRealPath("");
            // path += attachmentId + ".pdf";
            // 获取模版
            AttachmentDto findAttachment = ced.findAttachmentById(attachmentId);
            String filePath = "F:" + findAttachment.getFilePath();
            // 获取模版上的所有标签如{年}
            Map<String, Object> hsLabel = WordUtil.getKeyWord(filePath);
            // 根据标签去标签库获取脚本，解析成结果返回到showParam中
            Map<String, Object> showParam = sysLabelService.getLabelValueSqlByKey(hsLabel);

            Java2word j2w = new Java2word();
            j2w.toWord(filePath, path + "doctemplate.doc", showParam);

            // 生成PDF到path
            WordUtil.word2pdf(path + "doctemplate.doc", path + attachmentId + ".pdf");
        } catch (Exception e) {

            e.printStackTrace();
        }

        return new ModelAndView("/contract/conDocGen");
    }

}
