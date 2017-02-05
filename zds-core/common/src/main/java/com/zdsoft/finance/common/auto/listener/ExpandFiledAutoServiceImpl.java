package com.zdsoft.finance.common.auto.listener;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zdsoft.finance.common.auto.entity.ExpandFiled;
import com.zdsoft.finance.common.auto.repository.ExpandFiledRepository;
import com.zdsoft.framework.core.common.util.ObjectHelper;

@Component("expandFiledAutoService")
public class ExpandFiledAutoServiceImpl implements ExpandFiledAutoService {

	@Autowired
	ExpandFiledRepository expandFiledRepository;

	ServletContext sc;

	@Override
	public void init(ServletContext sc) {
		try{
			this.sc = sc;
			List<ExpandFiled> expandFileds = (List<ExpandFiled>) expandFiledRepository.findAll();
			sc.setAttribute("expandFileds", expandFileds);
		}catch(Exception e){
			
		}
		

	}

	@Override
	public List<ExpandFiled> findByClassNm(String classNm) {
		@SuppressWarnings("unchecked")
		List<ExpandFiled> expandFileds = (List<ExpandFiled>) sc.getAttribute("expandFileds");
		List<ExpandFiled> newExpandFileds = new ArrayList<ExpandFiled>();

		if (ObjectHelper.isNotEmpty(expandFileds)) {
			for (ExpandFiled expandFiled : expandFileds) {
				if (expandFiled.getClassPath().equals(classNm)) {
					newExpandFileds.add(expandFiled);
				}
			}
		}
		return newExpandFileds;
	}

}
