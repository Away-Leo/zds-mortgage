package com.zdsoft.finance.common.auto.listener;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ServletContextAware;

/**
 * 初始化项目扩展化字段进入内存
 * 
 * @author liuwei
 *
 */
public class InitAutoListener implements InitializingBean, ServletContextAware {

	@Autowired
	ExpandFiledAutoService expandFiledAutoService;

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		expandFiledAutoService.init(servletContext);
	}

}
