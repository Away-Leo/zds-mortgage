package com.zdsoft.finance.common.enums;

import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title ENUM_MEDIA_TYPE.java
 * @className ENUM_MEDIA_TYPE
 * @description 媒体文件类型枚举
 * @author LiaoGuoWei
 * @create 2017/2/21 20:45
 * @version V1.0
 **/
public enum ENUM_MEDIA_TYPE {

    /**
     * 图片类型
     */
    IMG(".bmp,.jpg,.tiff,.gif,.pcx,.tga,.exif,.fpx,.svg,.psd,.cdr,.pcd,.dxf,.ufo,.eps,.ai,.raw,.wmf,.png,","0"),
    /**
     * 视频类型
     */
    VIDEO(".mpeg,.mpg,.dat,.avi,.mov,.asf,.wmv,.navi,.3gp,.mkv,.flv,.f4v,.rmvb,.webm,.hddvd,.qsv,.mp4,","1"),

    /**
     * 办公文件格式
     */
    OFFICE(".xls,.xlsx,.ppt,.doc,.docx,.pptx,.pdf,","2"),

    /**
     * 音频类型
     */
    AUDIO(".cd,.ogg,.mp3,.asf,.wma,.wav,.mp3,.pro,.rm,.real,.ape,.module,.midi,.vqf,.au,.aiff,.amr,","3"),
    /**
     * 安装文件
     */
    SOFT(".exe,.apk,.msi,","4"),
    /**
     * 压缩文件
     */
    ZIP(".rar,.zip,.7z,.ace,.arj,.bz2,.bzip2,.cab,.gz,.gzip,.jar,.tar,.zip,","5"),
    /**
     * 安装文件
     */
    OTHER("","6");

    public final String types;
    public final String classType;

    private ENUM_MEDIA_TYPE(String types,String classType){
        this.types=types;
        this.classType=classType;
    }

    /**
     * 通过文件后缀得到大类
     * @param types
     * @return
     */
    public static String getClassType(String types) {
        String returnData="";
		for (ENUM_MEDIA_TYPE e : ENUM_MEDIA_TYPE.values()) {
			if (e.types.contains(types.toLowerCase())) {
				returnData=e.classType;
			}
		}
        if(ObjectHelper.isEmpty(returnData)){
            returnData="6";
        }
        return returnData;
	}

}
