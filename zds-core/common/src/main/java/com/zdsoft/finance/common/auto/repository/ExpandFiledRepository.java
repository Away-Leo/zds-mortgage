package com.zdsoft.finance.common.auto.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.zdsoft.finance.common.auto.entity.ExpandFiled;


public interface ExpandFiledRepository extends PagingAndSortingRepository<ExpandFiled,String>, JpaSpecificationExecutor<ExpandFiled>{

}
