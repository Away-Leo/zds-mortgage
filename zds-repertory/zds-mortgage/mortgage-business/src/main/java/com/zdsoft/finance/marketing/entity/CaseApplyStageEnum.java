package com.zdsoft.finance.marketing.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:CaseApplyStageEnum.java
 * @Package:com.zdsoft.finance.marketing.entity
 * @Description:案件阶段枚举类
 * @author: zhoushichao
 * @date:2017年1月10日 下午9:44:48
 * @version:v1.0
 */
public enum CaseApplyStageEnum {
	/**
	 * 申请
	 */
	ACCEPTANCE(0),
	/**
	 * 面签
	 */
	INVESTIGATION(1),
	/**
	 * 资调
	 */
	EXAMINE(2),	
	/**
	 * 办理抵制押
	 */
	REVIEW(3),
	/**
	 * 签署合同阶段
	 */
	SIGNCONT(4),
	/**
	 * 放款阶段
	 */
	LOAN(5),
	/**
	 * 在贷阶段（代偿、追偿、展期）
	 */
	INSURANCE(6),
	/**
	 * 结案阶段
	 */
	CASECLOSED(7),

	/**
	 * 授信申请
	 */
	CREDITAPPLY(8),
	/**
	 * 授信中
	 */
	CREDITING(9),
	/**
	 * 授信撤销
	 */
	CREDITCANCEL(10),
	/**
	 * 授信结束
	 */
	CREDITEND(11),
	/**
	 * 案件否决
	 */
	VETO(-1),
	/**
	 * 案件终止
	 */
	CANCEL(-2),
	/**
	 * 担保条件已落实
	 */
	GUARCONDITION(14),

	/**
	 * 案件签报
	 */
	SIGN(15),
	/**
	 * 复议
	 */
	RECONSIDER(16),
	/**
	 * 追偿阶段
	 */
	TRANCE(17),
	/**
	 * 受理阶段
	 */
	RECEPTION(18),
	//满标案件
	FULLSCALE(20),
	
	//案件废弃阶段
	ABANDONED(21);
	
	public final int value; 

    private CaseApplyStageEnum(int value) { 
        this.value = value; 
    } 
    
    /**
     * 
     * 获得对应的文字说明
     *
     * @author zhoushichao
     * @param value
     * @return
     */
    public static String getCaseApplyStageName(int value){
    	switch (value) {
		case -1: return "案件否决";
		case -2: return "案件终止";
		case 0: return "申请";
		case 1: return "面签";
		case 2: return "资调";
		case 3: return "办理抵制押";
		case 4: return "签署合同阶段";
		case 5: return "放款阶段";
		case 6: return "在贷阶段";
		case 7: return "结案阶段";
		case 8: return "授信申请";
		case 9: return "授信中";
		case 10: return "授信撤销";
		case 11: return "授信结束";
		case 14: return "担保条件已落实";
		case 15: return "案件签报";
		case 16: return "复议阶段";
		case 17: return "追偿阶段";
		case 18: return "受理阶段";
		case 20: return "满标";
		case 21: return "废弃阶段" ;
		default:
			return "";
		}
    }
    
    /**
     * 
     * 通过参数获得实体
     *
     * @author zhoushichao
     * @param value
     * @return
     */
    public static CaseApplyStageEnum getCaseApplyStageEnumByValue(int value){
    	for(CaseApplyStageEnum e:CaseApplyStageEnum.values()){
    		if(e.value==value){
    			return e;
    		}
    	}
    	return null;
    }
    
    /**
     * 
     * 获得枚举集合
     *
     * @author zhoushichao
     * @return
     */
    public static Map<Integer, String> getCaseApplyStageMap() {
        Map<Integer, String> result = new HashMap<Integer,String>();
        for(CaseApplyStageEnum e:CaseApplyStageEnum.values()){
            result.put(e.value, getCaseApplyStageName(e.value));
        }
        return result;
    }
    
	public static void main(String[] args) {
		System.out.println(getCaseApplyStageMap());		
	}

}
