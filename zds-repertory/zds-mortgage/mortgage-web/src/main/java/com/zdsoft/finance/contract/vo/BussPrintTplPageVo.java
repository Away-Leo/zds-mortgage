package com.zdsoft.finance.contract.vo;

import com.zdsoft.finance.common.utils.VoUtil;
import com.zdsoft.finance.contract.entity.BussPrintTplPage;
import com.zdsoft.finance.contract.entity.BussPrintTplSet;

public class BussPrintTplPageVo extends BussPrintTplPage {
    /**
     * 用一句话描述这个变量表示什么
     */
    private static final long serialVersionUID = 9571702099835918L;

    private String creditAttachment;
    public String getCreditAttachment() {
        return creditAttachment;
    }

    public void setCreditAttachment(String creditAttachment) {
        this.creditAttachment = creditAttachment;
    }
    
    
    public BussPrintTplPage toPO() {
        BussPrintTplPage obj = new BussPrintTplPage();

        VoUtil.copyPoperties(this, obj, true);
        return obj;
    }
    
    public BussPrintTplPageVo() {
        super();

    }
    public BussPrintTplPageVo(BussPrintTplPage entity) throws Exception {
        VoUtil.copyPoperties(entity, this, false);
    }

 
 
}
