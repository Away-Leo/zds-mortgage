package com.zdsoft.finance.businesssetting.repository;

import com.zdsoft.finance.businesssetting.entity.Question;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.framework.core.common.domain.BaseEntity;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: QuestionRepository.java 
 * @ClassName: QuestionRepository 
 * @Description: 问题定义操作仓库
 * @author longwei 
 * @date 2017年2月6日 上午11:09:01 
 * @version V1.0
 */
public interface QuestionRepository extends CustomRepository<Question, String>{

	/**
	 * @Title: findByPage
	 * @Description: 查询问题定义列表并分页
	 * @author liaoguowei
	 * @param question 查询条件
	 * @param pageable 分页参数
	 * @return com.zdsoft.framework.core.common.page.Page<com.zdsoft.finance.businesssetting.entity.Question>
	 * @throws
	 */
	public default Page<Question> findByPage(Question question, Pageable pageable){
		StringBuffer hql=new StringBuffer(" from Question q where q.isDeleted = :isDeleted ");
		Map<String,Object> qryCondition=new HashMap<String,Object>();
		qryCondition.put("isDeleted",!BaseEntity.DELETED);

		//问题
		if(ObjectHelper.isNotEmpty(question.getQuestionContent())){
			hql.append(" and q.questionContent like :questionContent ");
			qryCondition.put("questionContent","%"+question.getQuestionContent()+"%");
		}
		//类型
		if(ObjectHelper.isNotEmpty(question.getQuestionTypeCode())){
			hql.append(" and q.questionTypeCode = :questionTypeCode ");
			qryCondition.put("questionTypeCode",question.getQuestionTypeCode());
		}

		hql.append(" order by q.createTime desc,q.updateTime desc ");

		return this.findByHqlPage(pageable,hql.toString(),qryCondition);
	};

}
