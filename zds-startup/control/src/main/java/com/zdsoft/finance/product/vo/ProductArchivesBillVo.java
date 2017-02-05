package com.zdsoft.finance.product.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.product.entity.ProductArchivesBill;

/**
 * 档案清单
 * @createTime 2017年1月10日 上午11:52:12
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @version 1.0
 */
public class ProductArchivesBillVo extends BaseVo<ProductArchivesBill> {

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