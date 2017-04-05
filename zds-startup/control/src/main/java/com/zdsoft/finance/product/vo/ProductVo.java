package com.zdsoft.finance.product.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.product.entity.Category;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ProductVo.java 
 * @ClassName: ProductVo 
 * @Description: 产品VO
 * @author gufeng 
 * @date 2017年2月16日 下午5:48:57 
 * @version V1.0
 */
public class ProductVo extends BaseVo<Product> {

	private static final long serialVersionUID = -2712433620147374931L;

	/**
	 * 产品名称
	 */
	private String productName;
	
	/**
	 * 是否有效
	 */
	private Boolean isValid;
	
	/**
	 * 开始日期
	 */
	private String startTime;
	
	/**
	 * 结束日期
	 */
	private String endTime;
	
	/**
	 * 产品编号
	 */
	private String productCode;
	
	/**
	 * 产品编号-自定义
	 */
	private String customCode;
	
	/**
	 * 面签资料
	 */
	private String faceData;
	
	/**
	 * 产品类别
	 */
	private CategoryVo categoryVo;
	
	/**
	 * 类别id
	 */
	private String categoryId;
	
	/**
	 * 资方
	 */
	private String capitalistId;
	/**
	 * 资方名字
	 */
	private String capitalistName;
	
	public String getCapitalistName() {
		return capitalistName;
	}

	public void setCapitalistName(String capitalistName) {
		this.capitalistName = capitalistName;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getCustomCode() {
		return customCode;
	}

	public void setCustomCode(String customCode) {
		this.customCode = customCode;
	}

	public String getFaceData() {
		return faceData;
	}

	public void setFaceData(String faceData) {
		this.faceData = faceData;
	}

	public CategoryVo getCategoryVo() {
		return categoryVo;
	}

	public void setCategoryVo(CategoryVo categoryVo) {
		this.categoryVo = categoryVo;
	}

	public String getCapitalistId() {
		return capitalistId;
	}

	public void setCapitalistId(String capitalistId) {
		this.capitalistId = capitalistId;
	}

	public ProductVo(){}
	
	public ProductVo(Product product){
		super(product,new String[]{"categoryVo","startTime","endTime"});
		if (ObjectHelper.isNotEmpty(product.getStartTime())) {
			SimpleDateFormat sdf = new SimpleDateFormat(DateHelper.DATE_SHORT_SIMPLE_FORMAT);
			try {
				Date date = sdf.parse(product.getStartTime().toString());
				this.setStartTime(DateHelper.dateToString(date, DateHelper.DATE_SHORT_FORMAT));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (ObjectHelper.isNotEmpty(product.getEndTime())) {
			SimpleDateFormat sdf = new SimpleDateFormat(DateHelper.DATE_SHORT_SIMPLE_FORMAT);
			try {
				Date date = sdf.parse(product.getEndTime().toString());
				this.setEndTime(DateHelper.dateToString(date, DateHelper.DATE_SHORT_FORMAT));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		CategoryVo categoryVo=new CategoryVo(product.getCategory());
		this.setCategoryVo(categoryVo);
	}
	
	public Product toPo(){
		Product product=new Product();
		product = super.toPo(this,product,new String[]{"categoryVo","startTime","endTime"});
		if(ObjectHelper.isNotEmpty(this.getCategoryVo())){
			Category Category=this.getCategoryVo().toPo();
			product.setCategory(Category);
		}
		if (ObjectHelper.isNotEmpty(this.getStartTime())) {
			product.setStartTime(DateHelper.stringDateToLong(this.getStartTime().toString(),
					DateHelper.DATE_SHORT_FORMAT, DateHelper.DATE_SHORT_SIMPLE_FORMAT));
		}
		if (ObjectHelper.isNotEmpty(this.getEndTime())) {
			product.setEndTime(DateHelper.stringDateToLong(this.getEndTime().toString(),
					DateHelper.DATE_SHORT_FORMAT, DateHelper.DATE_SHORT_SIMPLE_FORMAT));
		}

		return product;
	}
}
