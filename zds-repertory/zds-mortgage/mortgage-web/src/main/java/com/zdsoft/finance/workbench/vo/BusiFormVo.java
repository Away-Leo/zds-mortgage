package com.zdsoft.finance.workbench.vo;

import javax.servlet.http.HttpServletRequest;

import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.busiform.entity.BusiFormStatus;
import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.common.enums.busiform.ApplyModelTypeEnum;
import com.zdsoft.finance.common.utils.VoUtil;
import com.zdsoft.finance.workbench.controller.BusiFormController;
import com.zdsoft.framework.core.common.domain.BaseEntity;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.tag.UriKeyContext;
import com.zdsoft.framework.core.commweb.util.UriConvertUtils;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BusiFormVo.java 
 * @ClassName: BusiFormVo 
 * @Description: 业务表单基类Vo
 * @author jingyh 
 * @date 2017年2月18日 上午10:48:37 
 * @version V1.0
 */
public class BusiFormVo extends BaseVo<BaseEntity> {

	private static final long serialVersionUID = -5036161252802320976L;

	/**
     * 业务品种编号
     */
    private String productCode;

    /**
     * 业务品种名称
     */
    private String productName;

    /**
     * 发起人编号
     */
    private String launchEmpCode;

    /**
     * 发起人姓名
     */
    private String launchEmpName;

    /**
     * 关联业务表单数据id
     */
    private String businessEntityId;

    /**
     * 关联表单域对象类名
     */
    private String businessEntityName;

    /**
     * 关联组件数据ID
     */
    private String componentsEntityId;

    /**
     * 关联组件域对象类名
     */
    private String componentsEntityName;

    /**
     * 流程实例key
     */
    private String processInstanceKey;

    /**
     * 流程开始时间
     */
    private Long processStartDate;

    /**
     * 流程结束时间
     */
    private Long processEndDate;

    /**
     * 申请时间
     */
    private Long applyDate;

    /**
     * 申请表状态
     */
    private Integer formStatus;
    
    /**
     * 申请表状态
     */
    private String formStatusName;

    /**
     * 标题(各申请表单自行生成)
     */
    private String applyTitle;

    /**
     * 模块类型(枚举类：ApplyModelTypeEnum中value)
     */
    private String modelType;
    
    /**
     * 模块名称
     */
    private String modelTypeName;
    
    /**
     * 编辑UriKey
     */
    private String editUriKey;
    
    /**
     * 编辑Url
     */
    private String editUrl;
    
    /**
     * 查看UriKey
     */
    private String viewUriKey;
    
    /**
     * 查看Url
     */
    private String viewUrl;
    
    /**
     * 废弃UriKey
     */
    private String scrappedUriKey;
    
    /**
     * 废弃Url
     */
    private String scrappedUrl;

    /**
     * 业务编号(各业务表单的编号)
     */
    private String businessCode;
    
    /**
     * 是否被规则拒绝过
     */
    private Boolean hadRulesRefuse;
    
    /**
     * 当前处理人Cd
     */
    private String currentDealEmpCd;
    
    /**
     * 当前处理人Nm
     */
    private String currentDealEmpNm;
   
    /**
     * 当前任务节点
     */
    private String currentTaskId;
    
    /**
     * 当前任务名称
     */
    private String currentTaskName;

    public String getLaunchEmpCode() {
        return launchEmpCode;
    }

    public void setLaunchEmpCode(String launchEmpCode) {
        this.launchEmpCode = launchEmpCode;
    }

    public String getLaunchEmpName() {
        return launchEmpName;
    }

    public void setLaunchEmpName(String launchEmpName) {
        this.launchEmpName = launchEmpName;
    }

    public String getBusinessEntityId() {
        return businessEntityId;
    }

    public void setBusinessEntityId(String businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    public String getBusinessEntityName() {
        return businessEntityName;
    }

    public void setBusinessEntityName(String businessEntityName) {
        this.businessEntityName = businessEntityName;
    }

    public String getComponentsEntityId() {
        return componentsEntityId;
    }

    public void setComponentsEntityId(String componentsEntityId) {
        this.componentsEntityId = componentsEntityId;
    }

    public String getComponentsEntityName() {
        return componentsEntityName;
    }

    public void setComponentsEntityName(String componentsEntityName) {
        this.componentsEntityName = componentsEntityName;
    }

    public String getProcessInstanceKey() {
        return processInstanceKey;
    }

    public void setProcessInstanceKey(String processInstanceKey) {
        this.processInstanceKey = processInstanceKey;
    }

    public Long getProcessStartDate() {
        return processStartDate;
    }

    public void setProcessStartDate(Long processStartDate) {
        this.processStartDate = processStartDate;
    }

    public Long getProcessEndDate() {
        return processEndDate;
    }

    public void setProcessEndDate(Long processEndDate) {
        this.processEndDate = processEndDate;
    }

    public Long getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Long applyDate) {
        this.applyDate = applyDate;
    }

    public Integer getFormStatus() {
        return formStatus;
    }

    public void setFormStatus(Integer formStatus) {
        this.formStatus = formStatus;
    }

    public String getApplyTitle() {
        return applyTitle;
    }

    public void setApplyTitle(String applyTitle) {
        this.applyTitle = applyTitle;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getCurrentDealEmpCd() {
        return currentDealEmpCd;
    }

    public void setCurrentDealEmpCd(String currentDealEmpCd) {
        this.currentDealEmpCd = currentDealEmpCd;
    }

    public String getCurrentDealEmpNm() {
        return currentDealEmpNm;
    }

    public void setCurrentDealEmpNm(String currentDealEmpNm) {
        this.currentDealEmpNm = currentDealEmpNm;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

	public Boolean getHadRulesRefuse() {
		return hadRulesRefuse;
	}

	public void setHadRulesRefuse(Boolean hadRulesRefuse) {
		this.hadRulesRefuse = hadRulesRefuse;
	}

	public String getFormStatusName() {
		return formStatusName;
	}

	public void setFormStatusName(String formStatusName) {
		this.formStatusName = formStatusName;
	}

	public String getModelTypeName() {
		return modelTypeName;
	}

	public void setModelTypeName(String modelTypeName) {
		this.modelTypeName = modelTypeName;
	}

	public String getEditUriKey() {
		return editUriKey;
	}

	public void setEditUriKey(String editUriKey) {
		this.editUriKey = editUriKey;
	}

	public String getEditUrl() {
		return editUrl;
	}

	public void setEditUrl(String editUrl) {
		this.editUrl = editUrl;
	}

	public String getViewUriKey() {
		return viewUriKey;
	}

	public void setViewUriKey(String viewUriKey) {
		this.viewUriKey = viewUriKey;
	}

	public String getViewUrl() {
		return viewUrl;
	}

	public void setViewUrl(String viewUrl) {
		this.viewUrl = viewUrl;
	}

	public String getScrappedUriKey() {
		return scrappedUriKey;
	}

	public void setScrappedUriKey(String scrappedUriKey) {
		this.scrappedUriKey = scrappedUriKey;
	}

	public String getScrappedUrl() {
		return scrappedUrl;
	}

	public void setScrappedUrl(String scrappedUrl) {
		this.scrappedUrl = scrappedUrl;
	}

	public String getCurrentTaskId() {
		return currentTaskId;
	}

	public void setCurrentTaskId(String currentTaskId) {
		this.currentTaskId = currentTaskId;
	}

	public String getCurrentTaskName() {
		return currentTaskName;
	}

	public void setCurrentTaskName(String currentTaskName) {
		this.currentTaskName = currentTaskName;
	}

	public BusiFormVo() {
		super();
	}
	
	public BusiFormVo(BusiForm entity) {
		if (ObjectHelper.isNotEmpty(entity)) {
			VoUtil.copyPoperties(entity, this, false);
		}
		if (ObjectHelper.isNotEmpty(this.getFormStatus())) {
			this.setFormStatusName(BusiFormStatus.getName(this.getFormStatus()));
		}
		if (ObjectHelper.isNotEmpty(this.getModelType())) {
			this.setModelTypeName(ApplyModelTypeEnum.getName(this.getModelType()));
		}
	}
	
	/**
	 * 转换url
	 * @param req
	 * @param entity
	 */
	public BusiFormVo(HttpServletRequest req, BusiForm entity) {
		this(entity);
		if (ObjectHelper.isNotEmpty(this.getModelType())) {
			ApplyModelTypeEnum applyType = ApplyModelTypeEnum.getModelType(this.getModelType());
			if (applyType != null) {
				// 转换URL
				UriKeyContext uriKeyContext = UriConvertUtils.resolveUriKeyContext(req);
				this.setEditUriKey(applyType.editUriKey);
				this.setEditUrl( UriConvertUtils.getUrlByUriKey(applyType.editUriKey.trim(), "admin", uriKeyContext));
				this.setViewUriKey(applyType.viewUriKey);
				this.setViewUrl( UriConvertUtils.getUrlByUriKey(applyType.viewUriKey.trim(), "admin", uriKeyContext));
				if (ObjectHelper.isEmpty(applyType.scrappedUriKey)) {
					// 废弃urikey为空时，就使用默认值
					this.setScrappedUriKey(BusiFormController.DEFAULT_WASTE_URIKEY);
					this.setScrappedUrl(UriConvertUtils.getUrlByUriKey(BusiFormController.DEFAULT_WASTE_URIKEY.trim(), "admin", uriKeyContext));
				} else {
					this.setScrappedUriKey(applyType.scrappedUriKey);
					this.setScrappedUrl( UriConvertUtils.getUrlByUriKey(applyType.scrappedUriKey.trim(), "admin", uriKeyContext));
				}
			}
		}
	}
	
}
