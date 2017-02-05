package com.zdsoft.finance.common.auto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 拓展字段
 * 两种设计思路
 * 一种为通过SQL alert表结构 通过AOP拦截查询语句修改查询SQL及重新封装返回值
 * 另外一种通过拓展表来存储 查询后封装
 * Created by Maple on 2016/5/7.
 */
@Entity
@Table(name="zf_expand_filed")
public class ExpandFiled  extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 字段名
     *
     */
    private String filed;
    /**
     * 字段描述 前台界面的展示
     */
    private String label;
    /**
     * 字段长度
     */
    @Column(nullable=false)
    private Integer length;
    /**
     * 小数位
     */
    private Integer digit=0;
    /**
     * 字段类型
     */
    @Enumerated(EnumType.STRING)
    private FiledType type;
    /**
     * 类完整路径
     */
    private String classPath;
    /**
     * vo类完整路径
     */
    private String voClassPath;
    /**
     * 全端展示类型
     */
    @Enumerated(EnumType.STRING)
    private ViewType viewType;
    /**
     * 显示序号 越小在前
     */
    private Integer orderNumber;

    public String getFiled() {
        return filed;
    }

    public void setFiled(String filed) {
        this.filed = filed;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    

	public FiledType getType() {
		return type;
	}

	public void setType(FiledType type) {
		this.type = type;
	}

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

	
	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getDigit() {
		return digit;
	}

	public void setDigit(Integer digit) {
		this.digit = digit;
	}

	public ViewType getViewType() {
		return viewType;
	}

	public void setViewType(ViewType viewType) {
		this.viewType = viewType;
	}

	public String getVoClassPath() {
		return voClassPath;
	}

	public void setVoClassPath(String voClassPath) {
		this.voClassPath = voClassPath;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	
	
   
}
