package com.zdsoft.finance.product.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.product.entity.ProductArchivesBill;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ProductArchivesBillVo.java 
 * @ClassName: ProductArchivesBillVo 
 * @Description: 档案清单
 * @author gufeng 
 * @date 2017年3月13日 下午4:46:07 
 * @version V1.0
 */
public class ProductArchivesBillVo extends BaseVo<ProductArchivesBill> {

	private static final long serialVersionUID = -4236624007400366554L;

	/**
	 * 档案名称
	 */
	private String archivesName;
	
	/**
	 * 档案等级
	 */
	private String archivesLevel;
	/**
	 * 档案等级 名字
	 */
	private String archivesLevelName;
	
	/**
	 * 原件/复印件/照片件
	 */
	private String archivesType;
	/**
	 * 原件/复印件/照片件 名字
	 */
	private String archivesTypeName;
	
	/**
	 * 产品id
	 */
	private String productId;

	public String getArchivesName() {
		return archivesName;
	}

	public void setArchivesName(String archivesName) {
		this.archivesName = archivesName;
	}

	public String getArchivesLevel() {
		return archivesLevel;
	}

	public void setArchivesLevel(String archivesLevel) {
		this.archivesLevel = archivesLevel;
	}

	public String getArchivesLevelName() {
		return archivesLevelName;
	}

	public void setArchivesLevelName(String archivesLevelName) {
		this.archivesLevelName = archivesLevelName;
	}

	public String getArchivesType() {
		return archivesType;
	}

	public void setArchivesType(String archivesType) {
		this.archivesType = archivesType;
	}

	public String getArchivesTypeName() {
		return archivesTypeName;
	}

	public void setArchivesTypeName(String archivesTypeName) {
		this.archivesTypeName = archivesTypeName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public ProductArchivesBillVo(){}
	
	public ProductArchivesBillVo(ProductArchivesBill po){
		super(po,null,new String[]{"archivesLevel","archivesType"});
	}
	
	public ProductArchivesBill toPO(){
		ProductArchivesBill po = new ProductArchivesBill();
		return super.toPo(this, po);
	}
	
}