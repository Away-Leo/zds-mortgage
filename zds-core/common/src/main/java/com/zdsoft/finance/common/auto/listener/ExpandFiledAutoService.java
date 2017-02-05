package com.zdsoft.finance.common.auto.listener;

import java.util.List;

import javax.servlet.ServletContext;

import com.zdsoft.finance.common.auto.entity.ExpandFiled;

public interface ExpandFiledAutoService {

	void init(ServletContext sc);

	List<ExpandFiled> findByClassNm(String classNm);
}
