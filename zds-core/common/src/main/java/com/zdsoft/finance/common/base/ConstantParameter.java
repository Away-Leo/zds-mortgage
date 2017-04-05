package com.zdsoft.finance.common.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zdsoft.framework.core.common.annotation.PropertySet;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:RuleEngineParameter.java
 * @Package:com.zdsoft.finance.common.base
 * @Description:公共常量配置文件 
 * @author: dengyy
 * @date:2017年1月18日 下午3:17:49
 * @version:v1.0
 */
public class ConstantParameter {
    
    static Logger logger = LoggerFactory.getLogger(ConstantParameter.class);

    /**
     * 规则引擎访问ip
     */
    private static String ruleEngineUrl ;
    
    /**
     * 规则引擎端口
     */
    private static String ruleEnginePort;
    
    /**
     * 规则引擎 GroupId
     */
    private static String ruleEngineGroupId;
    
    /**
     * 规则引擎 ArtifactId
     */
    private static String ruleEngineArtifactId;
    
    /**
     * 规则引擎 VersionCode
     */
    private static String ruleEngineVersionCode;
    /**
     * app文件查看地址
     */
    private static String appDownloadUrl;
    
    /**
     * 汇法网接口地址 huifaUrl
     */
    private static String huifaUrl;
    
    /**
     * #工商信息接口地址businessUrl
     */
    private static String businessUrl;
    
    /**
     * 资调摄像文件的服务器端存储位置定义
     */
    private static String shootPhotoServerFolderPath;

    /**
     * 资调摄像文件与服务器文件默认存储路径相对的路径地址
     */
    private static String shootPhotoRelativePath;
    
    /**
     * #工商信息接口地址houseAssessmentUrl
     */
    private static String houseAssessmentUrl;
    
    public static String getAppDownloadUrl() {
    	return appDownloadUrl;
    }
    
    @PropertySet
    public static void setAppDownloadUrl(String appDownloadUrl) {
    	ConstantParameter.appDownloadUrl = appDownloadUrl;
    }
    public static String getRuleEngineGroupId() {
        return ruleEngineGroupId;
    }

    @PropertySet
    public static void setRuleEngineGroupId(String ruleEngineGroupId) {
    	ConstantParameter.ruleEngineGroupId = ruleEngineGroupId;
    }

    public static String getRuleEngineArtifactId() {
        return ruleEngineArtifactId;
    }

    @PropertySet
    public static void setRuleEngineArtifactId(String ruleEngineArtifactId) {
    	ConstantParameter.ruleEngineArtifactId = ruleEngineArtifactId;
    }

    public static String getRuleEngineVersionCode() {
        return ruleEngineVersionCode;
    }
    
    @PropertySet
    public static void setRuleEngineVersionCode(String ruleEngineVersionCode) {
    	ConstantParameter.ruleEngineVersionCode = ruleEngineVersionCode;
    }

    public static String getRuleEngineUrl() {
        return ruleEngineUrl;
    }

    @PropertySet
    public static void setRuleEngineUrl(String ruleEngineUrl) {
    	ConstantParameter.ruleEngineUrl = ruleEngineUrl;
    }

    public static String getRuleEnginePort() {
        return ruleEnginePort;
    }

    @PropertySet
    public static void setRuleEnginePort(String ruleEnginePort) {
    	ConstantParameter.ruleEnginePort = ruleEnginePort;
    }
    
    public static String getHuifaUrl() {
    	return huifaUrl;
    }
    
    @PropertySet
    public static void setHuifaUrl(String huifaUrl) {
    	ConstantParameter.huifaUrl = huifaUrl;
    }
    
    public static String getBusinessUrl() {
    	return businessUrl;
    }
    
    @PropertySet
    public static void setBusinessUrl(String businessUrl) {
    	ConstantParameter.businessUrl = businessUrl;
    }

	public static String getShootPhotoServerFolderPath() {
		return shootPhotoServerFolderPath;
	}

	@PropertySet
	public static void setShootPhotoServerFolderPath(String shootPhotoServerFolderPath) {
		ConstantParameter.shootPhotoServerFolderPath = shootPhotoServerFolderPath;
	}

	public static String getShootPhotoRelativePath() {
		return shootPhotoRelativePath;
	}

	@PropertySet
	public static void setShootPhotoRelativePath(String shootPhotoRelativePath) {
		ConstantParameter.shootPhotoRelativePath = shootPhotoRelativePath;
	}

	public static String getHouseAssessmentUrl() {
		return houseAssessmentUrl;
	}
	
	@PropertySet
	public static void setHouseAssessmentUrl(String houseAssessmentUrl) {
		ConstantParameter.houseAssessmentUrl = houseAssessmentUrl;
	}
    
}


