/**
 * 商品分类数据包
 * @author BeibeiZhang
 * @version 2017.11.26
 *
 */
package dataImpl.commodityData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import dataService.commodityDataService.GoodsClassificationDataService;
import po.ClassificationPO;

public class GoodsClassificationData implements GoodsClassificationDataService{
	private ArrayList<ClassificationPO> allClassifications=new ArrayList<ClassificationPO>();
    private final String lastClassificationFilePath="data/classifications";
	@Override
	public ArrayList<ClassificationPO> finds() {
		// TODO Auto-generated method stub
		readClassificationPOs();
		return allClassifications;
		//allClassifications已初始化，就算没有类别，也不是null，而是size==0
	}

	@Override
	public ClassificationPO find(String classificationTag) {
		// TODO Auto-generated method stub
		readClassificationPOs();
		for(int i=0;i<=allClassifications.size()-1;i++){
			ClassificationPO classification=allClassifications.get(i);
			String name=classification.getName();
			String number=classification.getNumber();
			if(name.equals(classificationTag)||number.equals(classificationTag)){
				return classification;
			}
		}
		return null;
		//如果没找到对应的类别，就返回null
		//bl层/ui层对null进行判别，得知不存在相应的classification，显示相应错误消息
	}

	@Override
	public boolean insert(ClassificationPO po) {
		// TODO Auto-generated method stub
		readClassificationPOs();
		allClassifications.add(po);
		writeClassificationPOs();
		return true;
	}

	@Override
	public boolean delete(ClassificationPO po) {
		// TODO Auto-generated method stub
		readClassificationPOs();
		String theClassificationID=po.getNumber();
		for(int i=0;i<=allClassifications.size()-1;i++){
			ClassificationPO classification=allClassifications.get(i);
			String id=classification.getNumber();
			if(id.equals(theClassificationID)){
				//用ID判别目标类别，因为ID是唯一且不变的
				allClassifications.remove(i);
				writeClassificationPOs();
				return true;
			}
		}
		return false;
		//如果没找到对应的类别，就返回false
		//bl层/ui层对false进行判别，得知不存在相应的classification，显示相应错误消息

	}

	@Override
	public boolean update(ClassificationPO po) {
		// TODO Auto-generated method stub
		readClassificationPOs();
		String number=po.getNumber();
		for(int i=0;i<=allClassifications.size()-1;i++){
			ClassificationPO classification=allClassifications.get(i);
			if(classification.getNumber().equals(number)){
				allClassifications.set(i, po);
				writeClassificationPOs();
				return true;
			}
		}
		return false;
		//如果没找到对应的类别，就返回false
		//bl层/ui层对false进行判别，得知不存在相应的classification，显示相应错误消息

	}

	private void readClassificationPOs(){
		allClassifications.clear();
        File lastFile=new File(lastClassificationFilePath);
        lastFile.mkdirs();
        File[] classificationFiles=lastFile.listFiles();
        if(classificationFiles.length==0){

        }else{
        for(int i=0;i<=classificationFiles.length-1;i++){
        	try{
        	File classificationFile=classificationFiles[i];
        	FileInputStream fis=new FileInputStream(classificationFile);
        	ObjectInputStream ois=new ObjectInputStream(fis);
        	ClassificationPO calssificationObj=(ClassificationPO) ois.readObject();
        	allClassifications.add(calssificationObj);
        	ois.close();
        	fis.close();
        	}catch(ClassNotFoundException e1){
				e1.printStackTrace();
			}catch(FileNotFoundException e2){
				e2.printStackTrace();
			}catch(IOException e3){
				e3.printStackTrace();
			}
        }
        }
	}
    private void writeClassificationPOs(){
    	 File lastFile=new File(lastClassificationFilePath);
         lastFile.mkdirs();
         File[] classificationFiles=lastFile.listFiles();
         if(classificationFiles.length==0){

         }else{
        	 for(int i=0;i<=classificationFiles.length-1;i++){
        		 classificationFiles[i].delete();
        	 }
         }
         String lastFileAbsolutePath=lastFile.getAbsolutePath();

         for(int i=0;i<=allClassifications.size()-1;i++){
        	 try{
        	 ClassificationPO classificationObj=allClassifications.get(i);
        	 File classificationFile=new File(lastFileAbsolutePath+"/"+classificationObj.getNumber());
        	 classificationFile.createNewFile();
        	 FileOutputStream fos=new FileOutputStream(classificationFile);
           	 ObjectOutputStream oos=new ObjectOutputStream(fos);
        	 oos.writeObject(classificationObj);
        	 oos.close();
        	 fos.close();
        	 }catch(IOException e){
        		 e.printStackTrace();
        	 }
         }

    }

}
