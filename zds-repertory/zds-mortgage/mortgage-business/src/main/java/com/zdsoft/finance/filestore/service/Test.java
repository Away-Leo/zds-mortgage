package com.zdsoft.finance.filestore.service;

import cnfh.RiskSpecItem;
import cnfh.VerifyResult;
import cnfh.cnfh_rule.Calldrools;
import cnfh.cnfh_rule.InParam;
import cnfh.cnfh_rule.RuleInfo;
import cnfh.first_audit.YSHouseProperty;
import com.zdsoft.finance.filestore.entity.FileStore;

import java.util.ArrayList;
import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 *
 * @author LiaoGuoWei
 * @version V1.0
 * @title Test.java
 * @className Test
 * @description
 * @create 2017-03-02 17:06
 **/
public class Test {

    public static void main(String[] ar){
//        String fileName="YWDM11102";
//        System.out.println(fileName.substring(fileName.length()-2,fileName.length()));
        List<FileStore> sourceData=new ArrayList<>();

//        FileStore fileStore1=new FileStore();
//        fileStore1.setMateriaCode("m1");
//        FileStore fileStore2=new FileStore();
//        FileStore fileStore3=new FileStore();
//
//        fileStore1.setMateriaCode("mateia");

//        Map<String,Object> data=new TreeMap<>();
//        data.put("1",1);
//        data.put("2",2);
//        data.put("3",3);
//        data.put("4",4);
//        data.put("5",5);
//
//        System.out.println(data.toString());

//        Integer returnInt=100000;
//        String oriReturnData="TPDM000000";
//        String returnData=oriReturnData.substring(0,oriReturnData.length()-returnInt.toString().length())+returnInt.toString();
//        System.out.println(returnData);

//        Integer numInt=121;
//        Integer returnInt=numInt+1;
//        if(returnInt.toString().length()<7){
//            String oriReturnData="TPDM000000";
//            String returnData=oriReturnData.substring(0,oriReturnData.length()-returnInt.toString().length())+returnInt.toString();
//            System.out.println(returnData);
//        }else{
//            System.out.println("TPDM"+returnInt.toString());
//        }
//        int i=1;
//        int j=1;
//        i++;
//        ++j;
//        System.out.println(i);
//        System.out.println(j);

        /**
         * 一审调用示例，包含拒绝规则，特批规则
         *
         * @return
         */

            Calldrools drools = new Calldrools();
            List<Object> obj = new ArrayList<>();

//	//押品list
            YSHouseProperty gp1 = new YSHouseProperty();
            gp1.setEstateProperties("YWDM0051047");
            gp1.setEstateOwnership("YWDM0051054");
            gp1.setFloorAge("56");
            gp1.setProvince("620000");
            gp1.setCity("623000");
            gp1.setDistrict("623027");
            gp1.setMailingAddress("hjhjhjh");

        obj.add(gp1);
            VerifyResult svr = new VerifyResult();
            svr.setVrId(0);
            obj.add(svr);
            InParam inparam = new InParam();
            RuleInfo info = new RuleInfo();
            info.setGroupId("cnfh");
            info.setArtifactId("first_audit");
            info.setVersionCode("1.0");
            inparam.setRuleInfo(info);
        try{
            VerifyResult vr = drools.excuteCall(inparam, obj, svr);
            if("special".equals(vr.getVrResult()))
            {
                System.out.println("------" + vr.getVrDesc() + "------" + vr.getVrResult() + "------" + vr.getVrContent());
                for(RiskSpecItem riskspecitem : vr.getRiskSpecItems())
                {
                    System.out.println(riskspecitem.getRiskItemCode()+" "+riskspecitem.getRiskItemTips()+" "+riskspecitem.getRiskItemRes()+"\n");
                }
            }
            else
            {
                System.out.println("------" + vr.getVrDesc() + "------" + vr.getVrResult() + "------" + vr.getVrContent());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
