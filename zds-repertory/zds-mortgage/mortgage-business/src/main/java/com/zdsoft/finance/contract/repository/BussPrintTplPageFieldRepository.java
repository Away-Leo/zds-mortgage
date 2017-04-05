

package com.zdsoft.finance.contract.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.contract.entity.BussPrintTplPageField;


 
/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:BussPrintTplPageFieldRepository.java
 * @Package:com.zdsoft.finance.contract.repository
 * @Description:用一句话描述该文件做什么
 * @author: huangdongkui
 * @date:Mar 1, 2017 6:10:27 PM
 * @version:v1.0
 */
public interface BussPrintTplPageFieldRepository extends CustomRepository<BussPrintTplPageField,String> {
   int deleteBypageid(@Param("pageid") String pageid);

List<BussPrintTplPageField> findBypageid(@Param("pageid") String pageid); 
}
