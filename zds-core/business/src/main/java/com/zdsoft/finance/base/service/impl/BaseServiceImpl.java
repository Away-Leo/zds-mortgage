package com.zdsoft.finance.base.service.impl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.dto.permission.DataOperPermDto;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.domain.BaseEntity;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

public abstract class BaseServiceImpl<T extends BaseEntity, CT extends CustomRepository<T, String>>
		implements BaseService<T> {

	@Autowired
	public CT customReposity;

	@Log
	public Logger logger;
	
	@Override
	public Page<T> findByHqlConditions(Pageable pageable, List<QueryObj> li) {
		return customReposity.findByHqlConditions(pageable, li);
	}
	
	@Override
    public Page<T> findByHqlConditions(Pageable pageable, List<QueryObj> li,DataOperPermDto dataOperPermDto) {
        return customReposity.findByHqlConditions(pageable, li,dataOperPermDto);
    }

	@Override
	@Transactional
	public T saveEntity(T t) throws BusinessException {
		return customReposity.saveEntity(t);
	}

	@Override
	@Transactional
	public T updateEntity(T t) throws BusinessException {

		if (ObjectHelper.isEmpty(t) || ObjectHelper.isEmpty(t.getId())) {
			throw new BusinessException("10010004", "传入参数为空");
		}
		T oldT = findOne(t.getId());
		if (ObjectHelper.isEmpty(oldT)) {
			throw new BusinessException("10010002", "根据相应参数未找到相应的数据");
		}
		BeanUtils.copyProperties(t, oldT);

		return customReposity.updateEntity(oldT);
	}

	@Override
	@Transactional
	public T saveOrUpdateEntity(T t) throws BusinessException {
		if (ObjectHelper.isNotEmpty(t.getId())) {
			return this.updateEntity(t);
		} else {
			return this.saveEntity(t);
		}
	}

	@Override
	@Transactional
	public T logicDelete(T t) {
		return customReposity.logicDelete(t);
	}

	@Override
	@Transactional
	public T logicDelete(String id) throws BusinessException {
		if (ObjectHelper.isEmpty(id)) {
			throw new BusinessException("10010004", "传入参数为空");
		}
		T oldT = findOne(id);
		if (ObjectHelper.isEmpty(oldT)) {
			throw new BusinessException("10010002", "根据相应参数未找到相应的数据");
		}
		return customReposity.logicDelete(oldT);
	}

	@Override
	public T findOne(String id) throws BusinessException {
		if (ObjectHelper.isEmpty(id)) {
			throw new BusinessException("10010004", "传入参数为空");
		}
		return customReposity.findOne(id);
	}
	
	/**
	 * 
	 * @Title: createByDataAuth 
	 * @Description: 创建人的数据权限  
	 * @author dengyy 
	 * @param dataOperPermDto 权限
	 * @param tableName 表名 (sql 查询的表的别名)
	 * @return
	 * @throws BusinessException
	 */
    public StringBuffer createByDataAuth(DataOperPermDto dataOperPermDto,String tableName) throws BusinessException{
        StringBuffer sb=null;
        if(ObjectHelper.isNotEmpty(dataOperPermDto)){
            sb=new StringBuffer();
            tableName=tableName.trim();
            Set<String> whiteDataEmps=dataOperPermDto.getWhiteDataEmps();
            String whiteDataEmp_str=this.setConversionString(whiteDataEmps);
            
            Set<String> whiteDataOrgs=dataOperPermDto.getWhiteDataOrgs();
            String whiteDataOrg_str=this.setConversionString(whiteDataOrgs);
            
            Set<String> blackDataOrgs =dataOperPermDto.getBlackDataOrgs();
            String blackDataOrg_str=this.setConversionString(blackDataOrgs);
            
            Set<String> blackDataEmps = dataOperPermDto.getBlackDataEmps(); 
            String blackDataEmp_str=this.setConversionString(blackDataEmps);
            
            //白名单
            if(ObjectHelper.isNotEmpty(dataOperPermDto.getWhiteDataEmps() )  &&  ObjectHelper.isNotEmpty( dataOperPermDto.getWhiteDataOrgs()) ){
                sb.append(" AND  ( "+tableName+".createBy in ("+whiteDataEmp_str+") or  "+tableName+".createOrgCd in ( "+whiteDataOrg_str+" )  )");
            }else if ( ObjectHelper.isNotEmpty(dataOperPermDto.getWhiteDataEmps() )  &&  ObjectHelper.isEmpty( dataOperPermDto.getWhiteDataOrgs()) ){
                sb.append(" AND   "+tableName+".createBy in ("+whiteDataEmp_str+") ");
            }else if ( ObjectHelper.isEmpty(dataOperPermDto.getWhiteDataEmps() ) && ObjectHelper.isNotEmpty( dataOperPermDto.getWhiteDataOrgs()) ){
                sb.append(" AND   "+tableName+".createOrgCd in ("+whiteDataOrg_str+" )");
            }
            
            //黑名单
            if(ObjectHelper.isNotEmpty(dataOperPermDto.getBlackDataEmps() )  &&  ObjectHelper.isNotEmpty(dataOperPermDto.getBlackDataOrgs()) ){
                sb.append(" AND  ( "+tableName+".createBy  not in ("+blackDataEmp_str+") AND  "+tableName+".createOrgCd not in ( "+blackDataOrg_str+" )  )");
            }else if ( ObjectHelper.isNotEmpty(dataOperPermDto.getBlackDataEmps() )  &&  ObjectHelper.isEmpty(dataOperPermDto.getBlackDataOrgs()) ){
                sb.append(" AND   "+tableName+".createBy not in ("+blackDataEmp_str+")  ");
            }else if ( ObjectHelper.isEmpty(dataOperPermDto.getBlackDataEmps() ) && ObjectHelper.isNotEmpty( dataOperPermDto.getBlackDataOrgs()) ){
                sb.append(" AND   "+tableName+".createOrgCd not in ("+blackDataOrg_str+")  ");
            }
        }
            return sb;
        }
    
    /**
     * 
     * @Title: developmentManagerDataAuth 
     * @Description: 拓展经理 的数据权限  
     * @author dengyy 
     * @param dataOperPermDto 权限
     * @param tableName 表名(sql 查询的表的别名)
     * @return
     * @throws BusinessException
     */
    public StringBuffer developmentManagerDataAuth(DataOperPermDto dataOperPermDto,String tableName) throws BusinessException{
        StringBuffer sb=null;
        if(ObjectHelper.isNotEmpty(dataOperPermDto)){
            sb=new StringBuffer();
            tableName=tableName.trim();
            Set<String> whiteDataEmps=dataOperPermDto.getWhiteDataEmps();
            String whiteDataEmp_str=this.setConversionString(whiteDataEmps);
            
            Set<String> whiteDataOrgs=dataOperPermDto.getWhiteDataOrgs();
            
            String whiteDataOrg_str=this.setConversionString(whiteDataOrgs);
            
            Set<String> blackDataOrgs =dataOperPermDto.getBlackDataOrgs();
            String blackDataOrg_str=this.setConversionString(blackDataOrgs);
            
            Set<String> blackDataEmps = dataOperPermDto.getBlackDataEmps(); 
            String blackDataEmp_str=this.setConversionString(blackDataEmps);
            
           
            
            //白名单
            if(ObjectHelper.isNotEmpty(dataOperPermDto.getWhiteDataEmps() )  &&  ObjectHelper.isNotEmpty( dataOperPermDto.getWhiteDataOrgs()) ){
                sb.append(" AND  ( "+tableName+".developmentManagerCode in ("+whiteDataEmp_str+") or  "+tableName+".developmentDepartmentCode in ( "+whiteDataOrg_str+" )  )");
            }else if ( ObjectHelper.isNotEmpty(dataOperPermDto.getWhiteDataEmps() )  &&  ObjectHelper.isEmpty( dataOperPermDto.getWhiteDataOrgs()) ){
                sb.append(" AND   "+tableName+".developmentManagerCode in ("+whiteDataEmp_str+") ");
            }else if ( ObjectHelper.isEmpty(dataOperPermDto.getWhiteDataEmps() ) && ObjectHelper.isNotEmpty( dataOperPermDto.getWhiteDataOrgs()) ){
                sb.append(" AND   "+tableName+".createOrgCd in ("+whiteDataOrg_str+" )");
            }
            
            //黑名单
            if(ObjectHelper.isNotEmpty(dataOperPermDto.getBlackDataEmps() )  &&  ObjectHelper.isNotEmpty(dataOperPermDto.getBlackDataOrgs()) ){
                sb.append(" AND  ( "+tableName+".developmentManagerCode  not in ("+blackDataEmp_str+") AND  "+tableName+".developmentDepartmentCode not in ( "+blackDataOrg_str+" )  )");
            }else if ( ObjectHelper.isNotEmpty(dataOperPermDto.getBlackDataEmps() )  &&  ObjectHelper.isEmpty(dataOperPermDto.getBlackDataOrgs()) ){
                sb.append(" AND   "+tableName+".developmentManagerCode not in ("+blackDataEmp_str+")  ");
            }else if ( ObjectHelper.isEmpty(dataOperPermDto.getBlackDataEmps() ) && ObjectHelper.isNotEmpty( dataOperPermDto.getBlackDataOrgs()) ){
                sb.append(" AND   "+tableName+".developmentDepartmentCode not in ("+blackDataOrg_str+")  ");
            }
        }
            return sb;
        }
    
    /**
     * 
     * @Title: developmentManagerDataAuth 
     * @Description: 拓展经理 的数据权限  
     * @author dengyy 
     * @param dataOperPermDto 权限
     * @param tableName 表名(sql 查询的表的别名)
     * @return
     * @throws BusinessException
     */
    public StringBuffer developmentManagerDataAuth(DataOperPermDto dataOperPermDto,String tableName,String companyCd) throws BusinessException{
        StringBuffer sb=null;
        if(ObjectHelper.isNotEmpty(dataOperPermDto)){
            sb=new StringBuffer();
            tableName=tableName.trim();
            Set<String> whiteDataEmps=dataOperPermDto.getWhiteDataEmps();
            String whiteDataEmp_str=this.setConversionString(whiteDataEmps);
            
            Set<String> whiteDataOrgs=dataOperPermDto.getWhiteDataOrgs();
            String whiteDataOrg_str=this.setConversionString(whiteDataOrgs);
            
            Set<String> blackDataOrgs =dataOperPermDto.getBlackDataOrgs();
            String blackDataOrg_str=this.setConversionString(blackDataOrgs);
            
            Set<String> blackDataEmps = dataOperPermDto.getBlackDataEmps(); 
            String blackDataEmp_str=this.setConversionString(blackDataEmps);
            
            //白名单
            if(ObjectHelper.isNotEmpty(dataOperPermDto.getWhiteDataEmps() )  &&  ObjectHelper.isNotEmpty( dataOperPermDto.getWhiteDataOrgs()) ){
                sb.append(" AND  ( "+tableName+".developmentManagerCode in ("+whiteDataEmp_str+") or  "+tableName+".developmentDepartmentCode in ( "+whiteDataOrg_str+" )  )");
            }else if ( ObjectHelper.isNotEmpty(dataOperPermDto.getWhiteDataEmps() )  &&  ObjectHelper.isEmpty( dataOperPermDto.getWhiteDataOrgs()) ){
                sb.append(" AND   "+tableName+".developmentManagerCode in ("+whiteDataEmp_str+") ");
            }else if ( ObjectHelper.isEmpty(dataOperPermDto.getWhiteDataEmps() ) && ObjectHelper.isNotEmpty( dataOperPermDto.getWhiteDataOrgs()) ){
                sb.append(" AND   "+tableName+".createOrgCd in ("+whiteDataOrg_str+" )");
            }
            
            //黑名单
            if(ObjectHelper.isNotEmpty(dataOperPermDto.getBlackDataEmps() )  &&  ObjectHelper.isNotEmpty(dataOperPermDto.getBlackDataOrgs()) ){
                sb.append(" AND  ( "+tableName+".developmentManagerCode  not in ("+blackDataEmp_str+") AND  "+tableName+".developmentDepartmentCode not in ( "+blackDataOrg_str+" )  )");
            }else if ( ObjectHelper.isNotEmpty(dataOperPermDto.getBlackDataEmps() )  &&  ObjectHelper.isEmpty(dataOperPermDto.getBlackDataOrgs()) ){
                sb.append(" AND   "+tableName+".developmentManagerCode not in ("+blackDataEmp_str+")  ");
            }else if ( ObjectHelper.isEmpty(dataOperPermDto.getBlackDataEmps() ) && ObjectHelper.isNotEmpty( dataOperPermDto.getBlackDataOrgs()) ){
                sb.append(" AND   "+tableName+".developmentDepartmentCode not in ("+blackDataOrg_str+")  ");
            }
        }
            return sb;
        }
        
        /**
         * 
         * @Title: setConversionString 
         * @Description: 数据转换
         * @author dengyy 
         * @param setString 集合数据
         * @return
         */
        private String setConversionString(Set<String> setString) {
            String str="";
            boolean bool=false;
            for(String s:setString){
                if(bool){
                    str+=",";
                }
                str+="'"+s+"'";
                bool=true;
            }
            return str;
        }

}
