package com.zdsoft.finance.contract.repository;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.zdsoft.finance.authgrade.entity.AuthGrade;
import com.zdsoft.finance.casemanage.material.entity.CaseMaterialListAttaLog;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.contract.entity.CoactCaseContract;
import com.zdsoft.framework.core.common.domain.BaseEntity;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

import antlr.collections.List;

public interface SealRepository extends CustomRepository<CoactCaseContract, String>  {

}
