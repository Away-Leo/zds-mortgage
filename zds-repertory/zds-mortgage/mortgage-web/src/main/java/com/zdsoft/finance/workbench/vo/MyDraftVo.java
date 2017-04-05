package com.zdsoft.finance.workbench.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.zdsoft.finance.busiform.entity.MyDraft;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MyDraftVo.java 
 * @ClassName: MyDraftVo 
 * @Description: 我的草稿vo
 * @author longwei 
 * @date 2017年2月6日 上午11:13:01 
 * @version V1.0
 */
public class MyDraftVo {

	// id
	private String id;
	
	// 流程名称
	private String processName;
	
	// 主借人
	private String borrowerPerson;
	
	// 申请单
	private String applayFormCode;
	
	// 申请单名
	private String applayFormName;
	
	// 申请时间
	private String startTime;
	
	// 结束时间
	private String endTime;
	
	// 业务编号
	private String businessCode;
	
	// 保存时间
	private String createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getBorrowerPerson() {
		return borrowerPerson;
	}

	public void setBorrowerPerson(String borrowerPerson) {
		this.borrowerPerson = borrowerPerson;
	}

	public String getApplayFormCode() {
		return applayFormCode;
	}

	public void setApplayFormCode(String applayFormCode) {
		this.applayFormCode = applayFormCode;
	}

	public String getApplayFormName() {
		return applayFormName;
	}

	public void setApplayFormName(String applayFormName) {
		this.applayFormName = applayFormName;
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

	public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public MyDraftVo(){}
	
	public MyDraftVo(MyDraft myDraft){
		BeanUtils.copyProperties(myDraft, this, new String[]{"startTime","endTime","createTime"});
		if(ObjectHelper.isNotEmpty(myDraft.getStartTime())){
			SimpleDateFormat sdf = new SimpleDateFormat(DateHelper.DATE_SHORT_SIMPLE_FORMAT);
			try {
				Date date = sdf.parse(myDraft.getStartTime().toString());
				this.setStartTime(DateHelper.dateToString(date, DateHelper.DATE_SHORT_FORMAT));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(ObjectHelper.isNotEmpty(myDraft.getEndTime())){
			SimpleDateFormat sdf = new SimpleDateFormat(DateHelper.DATE_SHORT_SIMPLE_FORMAT);
			try {
				Date date = sdf.parse(myDraft.getEndTime().toString());
				this.setEndTime(DateHelper.dateToString(date, DateHelper.DATE_SHORT_FORMAT));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		/*if(ObjectHelper.isNotEmpty(myDraft.getCreateTime())){
			SimpleDateFormat sdf = new SimpleDateFormat(DateHelper.DATE_SHORT_SIMPLE_FORMAT);
			try {
				Date date = sdf.parse(myDraft.getCreateTime().toString());
				this.setCreateTime(DateHelper.dateToString(date, DateHelper.DATE_SHORT_FORMAT));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}*/
	}
	
	public MyDraft toPo(){
		MyDraft myDraft=new MyDraft();
		BeanUtils.copyProperties(this, myDraft, new String[]{"startTime","endTime","createTime"});
		if (ObjectHelper.isNotEmpty(this.getStartTime())) {
			myDraft.setStartTime(DateHelper.stringDateToLong(this.getStartTime().toString(),
					DateHelper.DATE_SHORT_FORMAT, DateHelper.DATE_SHORT_SIMPLE_FORMAT));
		}
		if (ObjectHelper.isNotEmpty(this.getEndTime())) {
			myDraft.setEndTime(DateHelper.stringDateToLong(this.getEndTime().toString(),
					DateHelper.DATE_SHORT_FORMAT, DateHelper.DATE_SHORT_SIMPLE_FORMAT));
		}
		/*if (ObjectHelper.isNotEmpty(this.getCreateTime())) {
			myDraft.setCreateTime(DateHelper.stringDateToLong(this.getCreateTime().toString(),
					DateHelper.DATE_SHORT_FORMAT, DateHelper.DATE_SHORT_SIMPLE_FORMAT));
		}*/
		
		return myDraft;
	}
	
}
