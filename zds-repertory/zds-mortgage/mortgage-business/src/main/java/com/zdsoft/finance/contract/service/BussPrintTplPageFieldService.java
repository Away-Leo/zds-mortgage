

package com.zdsoft.finance.contract.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.contract.entity.BussPrintTplPageField;



 
/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:BussPrintTplPageFieldService.java
 * @Package:com.zdsoft.finance.contract.service
 * @Description:用一句话描述该文件做什么
 * @author: huangdongkui
 * @date:Mar 1, 2017 6:11:01 PM
 * @version:v1.0
 */
public interface BussPrintTplPageFieldService extends BaseService<BussPrintTplPageField> {

   public void saveListWithDelete(List<BussPrintTplPageField> listb);

    public List<BussPrintTplPageField> findBypageid(String pageid,String actionname);
}
