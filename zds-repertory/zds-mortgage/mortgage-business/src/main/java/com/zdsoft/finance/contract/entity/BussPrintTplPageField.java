
package com.zdsoft.finance.contract.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:BussPrintTplPageField.java
 * @Package:com.zdsoft.finance.contract.entity
 * @Description:页书签定义实体类
 * @author: huangdongkui
 * @date:Mar 1, 2017 6:09:32 PM
 * @version:v1.0
 */
@Entity
@Table(name = "BUSS_PRINT_TPL_PAGE_FIELD")
public class BussPrintTplPageField extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = -3307895529384707655L;
	

    
	/** x */
    @Column
	private Long xcoordinate;
    
    
	/** y */
    @Column
	private Long ycoordinate;
    
    
	/** 书签ID */
    @Column(length=32)
	private String labelid;
    
    
	/** 所属页面ID */
    @Column(length=32)
	private String pageid;

    /**
     * 宽
     */
    @Column
    private Integer width;
    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getLabelname() {
        return labelname;
    }

    public void setLabelname(String labelname) {
        this.labelname = labelname;
    }

    public Integer getFontsize() {
        return fontsize;
    }

    public void setFontsize(Integer fontsize) {
        this.fontsize = fontsize;
    }

    /**
     * 高
     */
    @Column
    private Integer height;
    
    /**
     * 书签名
     */
    @Column(length=64)
    private String labelname;
    
    /**
     * 字体大小
     */
    @Column
    private Integer fontsize;
	/**
	 * @return 返回 x。
	 */

	public Long getXcoordinate() {
		return xcoordinate;
	}

	/**
	 * @param xcoordinate 要设置的 x。
	 */
	public void setXcoordinate(Long xcoordinate) {
		this.xcoordinate = xcoordinate;
	}
	/**
	 * @return 返回 y。
	 */

	public Long getYcoordinate() {
		return ycoordinate;
	}

	/**
	 * @param ycoordinate 要设置的 y。
	 */
	public void setYcoordinate(Long ycoordinate) {
		this.ycoordinate = ycoordinate;
	}
	/**
	 * @return 返回 书签ID。
	 */

	public String getLabelid() {
		return labelid;
	}

	/**
	 * @param labelid 要设置的 书签ID。
	 */
	public void setLabelid(String labelid) {
		this.labelid = labelid;
	}
	/**
	 * @return 返回 所属页面ID。
	 */

	public String getPageid() {
		return pageid;
	}

	/**
	 * @param pageid 要设置的 所属页面ID。
	 */
	public void setPageid(String pageid) {
		this.pageid = pageid;
	}
	
}
