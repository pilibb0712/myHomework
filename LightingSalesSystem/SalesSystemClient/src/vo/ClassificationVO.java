package vo;

import java.io.Serializable;
import java.util.ArrayList;

public class ClassificationVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8027765569959877462L;
	//一旦新建了一个VO，请全部set，如果没有，某项的值请初始化为0系
	private String name;
	private String number;
	private String fatherClassification;
	//String: number+"-"+name
	private ArrayList<String> sonClassification;
	//String: number+"-"+name
	private ArrayList<String> goodsList;
	//String: number+"-"+name+"-"+type
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
