package com.zdsoft.finance.common.utils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.zdsoft.finance.common.auto.entity.ExpandFiled;
import com.zdsoft.finance.common.auto.entity.FiledType;
import com.zdsoft.finance.common.auto.entity.ViewType;
import com.zdsoft.framework.core.common.util.ObjectHelper;

import javassist.CannotCompileException;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ConstPool;
import javassist.bytecode.FieldInfo;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.IntegerMemberValue;
/**
 * 自定义本地实体管理工厂
 * 创建时间 2016-11-22
 * @author Maple
 * @version 1.0
 *
 */
public class FinanceLocalContainerEntityManagerFactoryBean extends LocalContainerEntityManagerFactoryBean {
	private  Logger logger=LoggerFactory.getLogger(FinanceLocalContainerEntityManagerFactoryBean.class);
	/**
	 * 将数据源获取之后用于查询
	 *    	
	 */
	private DataSource tempDataSource;
	/**
	 * 复写父类的方法以拦截数据源
	 */
	@Override
    public void setDataSource(DataSource dataSource) {
        this.tempDataSource = dataSource;
        super.setDataSource(dataSource);
    }
	
	/**
	 * TODO 未完成异常处理
	 * 复写包扫描用于
	 */
	@Override
    public void setPackagesToScan(String... packagesToScan) {
        logger.info("开始读取配置修改现有方法....");
        
        try {
            Connection connection=tempDataSource.getConnection();
            Statement statement=connection.createStatement();
            //获取所有字段
            ResultSet resultSet= statement.executeQuery("select id,length,digit,type,label,filed,classpath,viewType,voClassPath from zf_expand_filed order by classpath desc");
            List<ExpandFiled> commonFileds=new ArrayList<ExpandFiled>();
            while (resultSet.next()){
                String id= resultSet.getString("id");
                String type= resultSet.getString("type");
                String label= resultSet.getString("label");
                String filed= resultSet.getString("filed");
                String classpath= resultSet.getString("classpath");
                Integer length= resultSet.getInt("length");
                Integer digit= resultSet.getInt("digit");
                String viewType= resultSet.getString("viewType");
                String voClassPath= resultSet.getString("voClassPath");
                ExpandFiled commonFiled=new ExpandFiled();
                commonFiled.setId(id);
                commonFiled.setType(FiledType.valueOf(type));
                commonFiled.setLabel(label);
                commonFiled.setFiled(filed);
                commonFiled.setClassPath(classpath);
                commonFiled.setLength(length);
                commonFiled.setDigit(digit);
                if(ObjectHelper.isNotEmpty(viewType)){
                	commonFiled.setViewType(ViewType.valueOf(viewType));
                }
                commonFiled.setVoClassPath(voClassPath);
                commonFileds.add(commonFiled);
            }
            logger.info("=======读取到数据量为:"+commonFileds.size());
            //分组后的
            Map<String, List<ExpandFiled>> fileds=new HashMap<String, List<ExpandFiled>>();
            
            List<ExpandFiled> group=new ArrayList<ExpandFiled>();
            String classPath=null;
            Map<String, String> poAndVoPath=new HashMap<String, String>();
            for (ExpandFiled commonFiled:commonFileds) {
            	//首次设置
            	if(classPath==null){
            		classPath=commonFiled.getClassPath();
            	 	poAndVoPath.put(classPath, commonFiled.getVoClassPath());
            	}
           
            	//如果相同则继续累加
            	if(classPath.equals(commonFiled.getClassPath())){
            		group.add(commonFiled);
        		}else{//如果不同则换新数组
        			//将之前的添加到MAP中
        			fileds.put(classPath, group);
        			group=new ArrayList<ExpandFiled>();
        			classPath=commonFiled.getClassPath();
        			poAndVoPath.put(classPath, commonFiled.getVoClassPath());
        			group.add(commonFiled);
        		}
			}
            if(classPath!=null){
            	 //最后一个添加到Map中
                fileds.put(classPath, group);
                modifyClass(fileds, poAndVoPath);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.setPackagesToScan(packagesToScan);
    }
	
	/**
	 * 修改类class 
	 * @param fileds 字段Maple
	 * @param poAndVoPath PO与VO的路径Map
	 */
	private void modifyClass(Map<String, List<ExpandFiled>> fileds,Map<String, String> poAndVoPath) {
		// TODO Auto-generated method stub
		// 实例化类型池对象
        ClassPool classPool = ClassPool.getDefault();
        //整理对象
        for (String key : fileds.keySet()) {
        	CtClass ctClass= null;
        	CtClass voCtClass= null;
            try {
            	ClassClassPath classPath = new ClassClassPath(this.getClass());
            	classPool.insertClassPath(classPath);
            	//获取拓展类
                ctClass = classPool.get(key);
                
                //获取Vo
                String voClassPath=poAndVoPath.get(key);
                if(ObjectHelper.isNotEmpty(voClassPath)){
                	voCtClass=classPool.get(voClassPath);
                }
                
                //获取拓展属性
                List<ExpandFiled> extpndFileds=fileds.get(key);
                
                for (ExpandFiled extpndFiled : extpndFileds) {
                	String fileName=extpndFiled.getFiled();
                	Integer length=extpndFiled.getLength();
                	Integer digit=extpndFiled.getDigit();
                	
                	CtField f=null;
                	CtField vof=null;
                	FieldInfo fieldInfo=null;
                	ConstPool cp=null;
                	AnnotationsAttribute attribute=null;
                	Annotation annotation=null;
                	CtMethod setMethod=null;
                	CtMethod getMethod=null;
                	CtMethod voSetMethod=null;
                	CtMethod voGetMethod=null;
                	
                	//根据类型进行动态添加
                	switch (extpndFiled.getType()) {
					case STRING:
						
						f= new CtField(classPool.get(String.class.getName()),fileName, ctClass);
						 
						//获取属性信息  
					    fieldInfo = f.getFieldInfo();
					    cp = fieldInfo.getConstPool();
					    attribute = new AnnotationsAttribute(cp, AnnotationsAttribute.visibleTag);
						annotation = new Annotation("javax.persistence.Column", cp);
						if(length!=null){
							annotation.addMemberValue("length", new IntegerMemberValue(cp,length));
						}
						attribute.setAnnotation(annotation);
						fieldInfo.addAttribute(attribute);
						ctClass.addField(f);
						
						//添加get set 方法
						setMethod=CtNewMethod.setter("set"+upStr(fileName), f);
						ctClass.addMethod(setMethod);
						getMethod=CtNewMethod.getter("get"+upStr(fileName), f);
						ctClass.addMethod(getMethod);
						//VO 部分
						if(voCtClass!=null){
							logger.info("======VO==========添加====字段名：="+fileName);
							vof= new CtField(classPool.get(String.class.getName()),fileName, voCtClass);
							voCtClass.addField(vof);
							//添加get set 方法
							voSetMethod=CtNewMethod.setter("set"+upStr(fileName), vof);
							voCtClass.addMethod(voSetMethod);
							voGetMethod=CtNewMethod.getter("get"+upStr(fileName), vof);
							voCtClass.addMethod(voGetMethod);
						}
						break;
					case INTGER:
						f = new CtField(classPool.get(Integer.class.getName()),fileName, ctClass);
						//获取属性信息  
						fieldInfo = f.getFieldInfo();
						cp= fieldInfo.getConstPool();  
					    attribute= new AnnotationsAttribute(cp, AnnotationsAttribute.visibleTag);
					    annotation= new Annotation("javax.persistence.Column", cp);
						attribute.setAnnotation(annotation);
						fieldInfo.addAttribute(attribute);
						ctClass.addField(f);
						
						//添加get set 方法
						setMethod=CtNewMethod.setter("set"+upStr(fileName), f);
						ctClass.addMethod(setMethod);
						getMethod=CtNewMethod.getter("get"+upStr(fileName), f);
						ctClass.addMethod(getMethod);
						
						//VO 部分
						if(voCtClass!=null){
							vof= new CtField(classPool.get(Integer.class.getName()),fileName, voCtClass);
							voCtClass.addField(vof);
							//添加get set 方法
							voSetMethod=CtNewMethod.setter("set"+upStr(fileName), vof);
							voCtClass.addMethod(voSetMethod);
							voGetMethod=CtNewMethod.getter("get"+upStr(fileName), vof);
							voCtClass.addMethod(voGetMethod);
						}
						break;
					case BOOLEAN:
						
						f= new CtField(classPool.get(Boolean.class.getName()),fileName, ctClass);
						
						//获取属性信息  
					    fieldInfo = f.getFieldInfo();
					    cp = fieldInfo.getConstPool();
					    attribute = new AnnotationsAttribute(cp, AnnotationsAttribute.visibleTag);
						annotation = new Annotation("javax.persistence.Column", cp);
						attribute.setAnnotation(annotation);
						fieldInfo.addAttribute(attribute);
						ctClass.addField(f);
						
						//添加get set 方法
						setMethod=CtNewMethod.setter("set"+upStr(fileName), f);
						ctClass.addMethod(setMethod);
						getMethod=CtNewMethod.getter("get"+upStr(fileName), f);
						ctClass.addMethod(getMethod);
						
						//VO 部分
						if(voCtClass!=null){
							vof= new CtField(classPool.get(Boolean.class.getName()),fileName, voCtClass);
							voCtClass.addField(vof);
							//添加get set 方法
							voSetMethod=CtNewMethod.setter("set"+upStr(fileName), vof);
							voCtClass.addMethod(voSetMethod);
							voGetMethod=CtNewMethod.getter("get"+upStr(fileName), vof);
							voCtClass.addMethod(voGetMethod);
						}
						break;
					case BIGDECIMAL:
						
						f= new CtField(classPool.get(BigDecimal.class.getName()),fileName, ctClass);
						
						//获取属性信息  
					    fieldInfo = f.getFieldInfo();
					    cp = fieldInfo.getConstPool();
					    attribute = new AnnotationsAttribute(cp, AnnotationsAttribute.visibleTag);
						annotation = new Annotation("javax.persistence.Column", cp);
						if(length!=null){
							annotation.addMemberValue("precision", new IntegerMemberValue(cp,length));
							annotation.addMemberValue("scale", new IntegerMemberValue(cp,digit));
						}
						attribute.setAnnotation(annotation);
						fieldInfo.addAttribute(attribute);
						ctClass.addField(f);
						
						//添加get set 方法
						setMethod=CtNewMethod.setter("set"+upStr(fileName), f);
						ctClass.addMethod(setMethod);
						getMethod=CtNewMethod.getter("get"+upStr(fileName), f);
						ctClass.addMethod(getMethod);
						
						//VO 部分
						if(voCtClass!=null){
							vof= new CtField(classPool.get(BigDecimal.class.getName()),fileName, voCtClass);
							voCtClass.addField(vof);
							//添加get set 方法
							voSetMethod=CtNewMethod.setter("set"+upStr(fileName), vof);
							voCtClass.addMethod(voSetMethod);
							voGetMethod=CtNewMethod.getter("get"+upStr(fileName), vof);
							voCtClass.addMethod(voGetMethod);
						}
						break;
					case DOUBLE:
						
						f= new CtField(classPool.get(Double.class.getName()),fileName, ctClass);
						
						//获取属性信息  
					    fieldInfo = f.getFieldInfo();
					    cp = fieldInfo.getConstPool();
					    attribute = new AnnotationsAttribute(cp, AnnotationsAttribute.visibleTag);
						annotation = new Annotation("javax.persistence.Column", cp);
						if(length!=null){
							annotation.addMemberValue("precision", new IntegerMemberValue(cp,length));
							annotation.addMemberValue("scale", new IntegerMemberValue(cp,digit));
						}
						attribute.setAnnotation(annotation);
						fieldInfo.addAttribute(attribute);
						ctClass.addField(f);

						//添加get set 方法
						setMethod=CtNewMethod.setter("set"+upStr(fileName), f);
						ctClass.addMethod(setMethod);
						getMethod=CtNewMethod.getter("get"+upStr(fileName), f);
						ctClass.addMethod(getMethod);
						
						//VO 部分
						if(voCtClass!=null){
							vof= new CtField(classPool.get(Double.class.getName()),fileName, voCtClass);
							voCtClass.addField(vof);
							//添加get set 方法
							voSetMethod=CtNewMethod.setter("set"+upStr(fileName), vof);
							voCtClass.addMethod(voSetMethod);
							voGetMethod=CtNewMethod.getter("get"+upStr(fileName), vof);
							voCtClass.addMethod(voGetMethod);
						}
						break;
					default:
						break;
					}
                     
                  
				}
                ctClass.toClass();
                if(voCtClass!=null){
                	logger.info("======VO======voCtClass=========");
                	 voCtClass.toClass();
                }
               
               
            } catch (NotFoundException e) {
                e.printStackTrace();
            }   catch (CannotCompileException e) {
                e.printStackTrace();
            }
		}
	}
    /**
     * 将字符转换为首字母大写
     * @param str
     * @return
     */
    private static String upStr(String str){
    	return str.replaceFirst(str.substring(0, 1),str.substring(0, 1).toUpperCase()) ;
    }
}
