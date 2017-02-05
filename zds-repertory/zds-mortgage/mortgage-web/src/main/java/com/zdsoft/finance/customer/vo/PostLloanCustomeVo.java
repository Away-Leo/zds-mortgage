package com.zdsoft.finance.customer.vo;

import com.zdsoft.finance.customer.entity.PostLloanCustome;

/**
 * 贷后客户信息
 * @author zhangchao
 * 2016/12/21
 */
public class PostLloanCustomeVo extends CustomerVo {

	public PostLloanCustomeVo(){}
	
	public PostLloanCustomeVo(PostLloanCustome po){
		super(po);
	}
	
	public PostLloanCustome toPO(){
		PostLloanCustome po = new PostLloanCustome();
		return (PostLloanCustome) super.toPo(this, po);
	}
}
