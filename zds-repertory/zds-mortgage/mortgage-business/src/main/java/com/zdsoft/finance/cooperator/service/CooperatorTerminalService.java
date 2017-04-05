package com.zdsoft.finance.cooperator.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.cooperator.entity.CooperatorTerminal;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CooperatorTerminalService.java
 * @ClassName: CooperatorTerminalService
 * @Description: 终端Service
 * @author liuwei
 * @date 2017年2月25日 下午4:31:35
 * @version V1.0
 */
public interface CooperatorTerminalService extends BaseService<CooperatorTerminal> {

	/**
	 * 
	 * @Title: getTerminalStatusAndOrgList
	 * @Description: 根据创建部门Cd以及状态查询终端
	 * @author liuwei
	 * @param createOrg
	 *            创建部门Cd
	 * @param status
	 *            终端状态
	 * @return 终端集合
	 */
	public List<CooperatorTerminal> getTerminalStatusAndOrgList(String createOrgCd, String status);

	/**
	 * 
	 * @Title: findByBelongRelevanceCodeAndTerminalStatus
	 * @Description: 根据终端归属Cd以及终端状态查看终端
	 * @author liuwei
	 * @param belongRelevanceCode
	 *            终端归属Cd
	 * @param status
	 *            终端状态
	 * @return 终端集合
	 */
	public List<CooperatorTerminal> findByBelongRelevanceCodeAndTerminalStatus(String belongRelevanceCode,
			String status);

	/**
	 * 
	 * @Title: findByShareOrgCdAndTerminalStatus
	 * @Description: 根据共用部门Cd以及终端状态查询终端
	 * @author liuwei
	 * @param shareOrgCd
	 *            公用部门cd
	 * @param status
	 *            终端状态
	 * @return 终端集合
	 */
	public List<CooperatorTerminal> findByShareOrgCdAndTerminalStatus(String shareOrgCd, String status);

	/**
	 * 
	 * @Title: saveOrUpdateTerminal
	 * @Description: 保存或修改终端
	 * @author liuwei
	 * @param cooperatorTerminal
	 *            终端信息
	 * @return 终端信息
	 * @throws Exception
	 */
	public CooperatorTerminal saveOrUpdateTerminal(CooperatorTerminal cooperatorTerminal) throws Exception;

	/**
	 * 
	 * @Title: saveTempTerminal
	 * @Description: 临时保存终端信息(直接置为已删除状态)
	 * @author liuwei
	 * @param cooperatorTerminal
	 *            终端信息
	 * @return 终端信息
	 * @throws Exception
	 */
	public CooperatorTerminal saveTempTerminal(CooperatorTerminal cooperatorTerminal) throws Exception;

	/**
	 * 
	 * @Title: updateTerminalStatusByIds
	 * @Description: 根据id批量修改终端状态
	 * @author liuwei
	 * @param ids
	 * @param status
	 */
	public void updateTerminalStatusByIds(String ids, String status);

}
