package po;

import java.io.Serializable;
import java.util.ArrayList;

public class ClassificationPO implements Serializable{
	private String name;
	private String number;
	private String fatherClassification;
	//String: number+"-"+name
	//如果没有父类别，请设置为“”
	private ArrayList<String> sonClassification;
	//String: number+"-"+name
	private ArrayList<String> goodsList;
	//String: number+"-"+name+"-"+type
	//型号：T+四位十进制数
    public void setName(String str){
       name=str;
    }
    public void setNumber(String str){
       number=str;
    }
    public void setFatherClassification(String str){
       fatherClassification=str;
    }
    public void setSonClassification(ArrayList<String> arr){
       sonClassification=arr;
    }
    public void setGoodsList(ArrayList<String> arr){
       goodsList=arr;
    }
    public String getName(){
      return name;
    }
    public String getNumber(){
       return number;
    }
    public String getFatherClassification(){
       return fatherClassification;
    }
    public ArrayList<String> getSonClassifacation(){
       return sonClassification;
    }
    public ArrayList<String> getGoodsList(){
       return goodsList;
    }
}
