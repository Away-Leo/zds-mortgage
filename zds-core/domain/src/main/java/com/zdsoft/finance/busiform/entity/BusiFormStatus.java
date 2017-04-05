package com.zdsoft.finance.busiform.entity;

/** busiform状态
 * @author LiaoGuoWei
 * @create 2016-10-31 11:54
 **/
public enum BusiFormStatus {

    /**
     * 草稿
     */
    DRAFT(0),
    /**
     * 审批中
     */
    APPROVAL(1),
    /**
     * 暂停
     */
    PAUSE(2),
    /**
     * 已作废
     */
    SCRAPPED(3),
    /**
     * 审批通过
     */
    THROUAPPROVAL(4),
    /**
     * 审批不通过
     */
    NOTAPPROVALED(5),
    
    /**
     * 规则拒绝
     */
    RULESREFUSE(6);

    public final Integer value;
    
    private BusiFormStatus(Integer value){
        this.value=value;
    }
    
    public static String getName(Integer value){
    	String name = "";
    	switch (value) {
		case 0:
			name = "草稿";
			break;
		case 1:
			name = "审批中";
			break;
		case 2:
			name = "暂停";
			break;
		case 3:
			name = "已作废";
			break;
		case 4:
			name = "审批通过";
			break;
		case 5:
			name = "审批不通过";
			break;
		case 6:
			name = "规则拒绝";
			break;
		default:
			break;
		}
    	return name;
    }
}
