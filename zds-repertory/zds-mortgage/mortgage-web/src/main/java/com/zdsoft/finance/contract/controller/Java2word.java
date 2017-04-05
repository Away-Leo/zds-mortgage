package com.zdsoft.finance.contract.controller;

import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.Iterator;
import java.util.Map;

import com.jacob.activeX.ActiveXComponent;  
import com.jacob.com.Dispatch;  
import com.jacob.com.Variant;  
  
public class Java2word {  
      
     private boolean saveOnExit;  
 
     Dispatch doc = null;  
      
    
     private   ActiveXComponent word;  
    
     private   Dispatch documents;  
      
      
     
     public Java2word() {  
         if(word==null){  
         word = new ActiveXComponent("Word.Application");  
         word.setProperty("Visible",new Variant(false));  
         }  
         if(documents==null)  
         documents = word.getProperty("Documents").toDispatch();  
         saveOnExit = false;  
     }  
      
      
     public void setSaveOnExit(boolean saveOnExit) {  
         this.saveOnExit = saveOnExit;  
     }  
   
     public boolean getSaveOnExit() {  
         return saveOnExit;  
     }  
    
     public Dispatch open(String inputDoc) {  
         return Dispatch.call(documents,"Open",inputDoc).toDispatch();  
     }  
      
     /** 
      * ѡ������ 
      * @return Dispatch ѡ���ķ�Χ������ 
      */  
     public Dispatch select() {  
         return word.getProperty("Selection").toDispatch();  
     }  
      
     /** 
      * ��ѡ�����ݻ����������ƶ� 
      * @param selection Dispatch Ҫ�ƶ������� 
      * @param count int �ƶ��ľ��� 
      */  
     public void moveUp(Dispatch selection,int count) {  
         for(int i = 0;i < count;i ++)  
             Dispatch.call(selection,"MoveUp");  
     }  
      
     /** 
      * ��ѡ�����ݻ����������ƶ� 
      * @param selection Dispatch Ҫ�ƶ������� 
      * @param count int �ƶ��ľ��� 
      */  
     public void moveDown(Dispatch selection,int count) {  
         for(int i = 0;i < count;i ++)  
             Dispatch.call(selection,"MoveDown");  
     }  
      
     /** 
      * ��ѡ�����ݻ����������ƶ� 
      * @param selection Dispatch Ҫ�ƶ������� 
      * @param count int �ƶ��ľ��� 
      */  
     public void moveLeft(Dispatch selection,int count) {  
         for(int i = 0;i < count;i ++) {  
             Dispatch.call(selection,"MoveLeft");  
         }  
     }  
      
     /** 
      * ��ѡ�����ݻ����������ƶ� 
      * @param selection Dispatch Ҫ�ƶ������� 
      * @param count int �ƶ��ľ��� 
      */  
     public void moveRight(Dispatch selection,int count) {  
         for(int i = 0;i < count;i ++)  
             Dispatch.call(selection,"MoveRight");  
     }  
      
     /** 
      * �Ѳ�����ƶ����ļ���λ�� 
      * @param selection Dispatch ����� 
      */  
     public void moveStart(Dispatch selection) {  
         Dispatch.call(selection,"HomeKey",new Variant(6));  
     }  
      
     /** 
      * ��ѡ�����ݻ����㿪ʼ�����ı� 
      * @param selection Dispatch ѡ������ 
      * @param toFindText String Ҫ���ҵ��ı� 
      * @return boolean true-���ҵ���ѡ�и��ı���false-δ���ҵ��ı� 
      */  
     public boolean find(Dispatch selection,String toFindText) {  
         //��selection����λ�ÿ�ʼ��ѯ  
         Dispatch find = word.call(selection,"Find").toDispatch();  
         //����Ҫ���ҵ�����  
         Dispatch.put(find,"Text",toFindText);  
         //��ǰ����  
         Dispatch.put(find,"Forward","True");  
         //���ø�ʽ  
         Dispatch.put(find,"Format","True");  
         //��Сдƥ��  
         Dispatch.put(find,"MatchCase","True");  
         //ȫ��ƥ��  
         Dispatch.put(find,"MatchWholeWord","True");  
         //���Ҳ�ѡ��  
         return Dispatch.call(find,"Execute").getBoolean();  
     }  
      
     /** 
      * ��ѡ�������滻Ϊ�趨�ı� 
      * @param selection Dispatch ѡ������ 
      * @param newText String �滻Ϊ�ı� 
      */  
     public void replace(Dispatch selection,String newText) {  
         //�����滻�ı�  
         Dispatch.put(selection,"Text",newText);  
     }  
      
     /** 
      * ȫ���滻 
      * @param selection Dispatch ѡ�����ݻ���ʼ����� 
      * @param oldText String Ҫ�滻���ı� 
      * @param newText String �滻Ϊ�ı� 
      */  
     public void replaceAll(Dispatch selection,String oldText,Object replaceObj) {  
         //�ƶ����ļ���ͷ  
         moveStart(selection);  
          
         if(oldText.startsWith("table") || replaceObj instanceof ArrayList)  
             replaceTable(selection,oldText,(ArrayList) replaceObj);  
         else {  
             String newText = (String) replaceObj;  
             if(newText==null)  
                 newText="";  
             if(oldText.indexOf("image") != -1&!newText.trim().equals("") || newText.lastIndexOf(".bmp") != -1 || newText.lastIndexOf(".jpg") != -1 || newText.lastIndexOf(".gif") != -1){  
                 while(find(selection,oldText)) {  
                     replaceImage(selection,newText);  
                     Dispatch.call(selection,"MoveRight");  
                 }  
             }else{  
                 while(find(selection,oldText)) {  
                     replace(selection,newText);  
                     Dispatch.call(selection,"MoveRight");  
                 }  
             }  
         }  
     }  
      
     /** 
      * �滻ͼƬ 
      * @param selection Dispatch ͼƬ�Ĳ���� 
      * @param imagePath String ͼƬ�ļ���ȫ·���� 
      */  
     public void replaceImage(Dispatch selection,String imagePath) {  
         Dispatch.call(Dispatch.get(selection,"InLineShapes").toDispatch(),"AddPicture",imagePath);  
     }  
      
     /** 
      * �滻��� 
      * @param selection Dispatch ����� 
      * @param tableName String ������ƣ� 
      * ����table$1@1��table$2@1...table$R@N��R����ӱ���еĵ�N�п�ʼ��䣬N����word�ļ��еĵ�N�ű� 
      * @param fields HashMap �����Ҫ�滻���ֶ������ݵĶ�Ӧ�� 
      */  
     public void replaceTable(Dispatch selection,String tableName,ArrayList dataList) {  
         if(dataList.size() <= 1) {  
             System.out.println("Empty table!");  
             return;  
         }  
          
         //Ҫ������  
         String[] cols = (String[]) dataList.get(0);  
          
         //������  
         String tbIndex = tableName.substring(tableName.lastIndexOf("@") + 1);  
         //�ӵڼ��п�ʼ���  
         int fromRow = Integer.parseInt(tableName.substring(tableName.lastIndexOf("$") + 1,tableName.lastIndexOf("@")));  
         //���б��  
         Dispatch tables = Dispatch.get(doc,"Tables").toDispatch();  
         //Ҫ���ı��  
         Dispatch table = Dispatch.call(tables,"Item",new Variant(tbIndex)).toDispatch();  
         //����������  
         Dispatch rows = Dispatch.get(table,"Rows").toDispatch();  
         //�����  
         for(int i = 1;i < dataList.size();i ++) {  
             //ĳһ������  
             String[] datas = (String[]) dataList.get(i);  
              
             //�ڱ�������һ��  
             if(Dispatch.get(rows,"Count").getInt() < fromRow + i - 1)  
                 Dispatch.call(rows,"Add");  
             //�����е������  
             for(int j = 0;j < datas.length;j ++) {  
                 //�õ���Ԫ��  
                 Dispatch cell = Dispatch.call(table,"Cell",Integer.toString(fromRow + i - 1),cols[j]).toDispatch();  
                 //ѡ�е�Ԫ��  
                 Dispatch.call(cell,"Select");  
                 //���ø�ʽ  
                 Dispatch font = Dispatch.get(selection,"Font").toDispatch();  
                 Dispatch.put(font,"Bold","0");  
                 Dispatch.put(font,"Italic","0");  
                 //��������  
                 Dispatch.put(selection,"Text",datas[j]);  
             }  
         }  
     }  
      
     /** 
      * �����ļ� 
      * @param outputPath String ����ļ�������·���� 
      */  
     public void save(String outputPath) {  
         Dispatch.call(Dispatch.call(word,"WordBasic").getDispatch(),"FileSaveAs",outputPath);  
     }  
      
     /** 
      * �ر��ļ� 
      * @param document Dispatch Ҫ�رյ��ļ� 
      */  
     public void close(Dispatch doc) {  
         Dispatch.call(doc,"Close",new Variant(saveOnExit));  
         word.invoke("Quit",new Variant[]{});  
         word = null;  
     }  
      
     
     public void toWord(String inputPath,String outPath,Map<String, Object> data) {  
         String oldText;  
         Object newValue;  
         try {  
             if(doc==null)  
             doc = open(inputPath);  
              
             Dispatch selection = select();  
              
             Iterator keys = data.keySet().iterator();  
             while(keys.hasNext()) {  
                 oldText = (String) keys.next();  
                 newValue = data.get(oldText);  
                 replaceAll(selection,oldText,newValue.toString());  
             } 
//             for (Map.Entry<String, Object> entry : data.entrySet()) {  
//                 oldText = entry.getKey().toString();  
//                 newValue = entry.getValue(); 
//                 replaceAll(selection,oldText,newValue);  
//                } 
              
             save(outPath);  
         } catch(Exception e) {  
             System.out.println("����word�ļ�ʧ�ܣ�");  
             e.printStackTrace();  
         } finally {  
             if(doc != null)  
                 close(doc);  
         }  
     }  
      
     public synchronized static void word(String inputPath,String outPath,HashMap data){  
         Java2word j2w = new Java2word();  
         j2w.toWord(inputPath,outPath,data);  
     }  
      
     @SuppressWarnings({ "rawtypes", "unchecked" })  
    public static void main(String[] args) {  
         //�滻word����ص��ֶ�  
         HashMap data = new HashMap();  
         data.put("{ǧ��}","1");  

         //�滻word�б�������  
         /** 
          * Ҫ�滻����е�����ʱ��HashMap�е�Key��ʽΪ��table$R@N�������У� 
          * R����ӱ��ĵ�R�п�ʼ�滻��N����wordģ���еĵ�N�ű�� 
          * ValueΪArrayList����ArrayList�а����Ķ���ͳһΪString[]�� 
          * һ��String[]����һ�����ݣ�ArrayList�е�һ����¼Ϊ�����¼����¼���Ǳ����Ҫ�滻���кţ� 
          * �磺Ҫ�滻��һ�С��ڶ��С������е����ݣ����һ����¼ΪString[3] {"1","2","3"} 
          */  
//         ArrayList table1 = new ArrayList(3);  
//         String[] fieldName1 = {"1","2","3"};  
//         table1.add(fieldName1);  
//         String[] field11 = {"1","751002","����֤ȯ"};  
//         table1.add(field11);  
//         String[] field21 = {"2","751004","��̩����"};  
//         table1.add(field21);  
//         String[] field31 = {"3","751005","��֤ͨȯ"};  
//         table1.add(field31);  
//         data.put("table$2@2",table1);  
           
         Java2word j2w = new Java2word();  
         long time1 = System.currentTimeMillis();  
         j2w.toWord("c:/123.doc","c:/321.doc",data);  
         System.out.println("time cost : " + (System.currentTimeMillis() - time1));  
     }  
}  
