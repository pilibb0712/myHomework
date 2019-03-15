package vo;

import java.io.Serializable;

public class GoodsVO  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7321442645700285504L;
	//一旦新建了一个VO，请全部set，如果没有，某项的值请初始化为0系
	private String name;
	private String number;
	private String classification;
	//the name of classification
	private String type;
	private int amount;
	private String costPrice;
	private String sellingPrice;
	private String currentCostPrice;
	private String currentSellingPrice;
    public void setName(String str){
       name=str;
    }
    public void setNumber(String str){
       number=str;
    }
    public void setClassification(String str){
       classification=str;
    }
    public void setType(String str){
       type=str;
    }
    public void setAmount(int amoun){
       amount=amoun;
    }
    public void setCostPrice(String str){
       costPrice=str;
    }
    public void setSellingPrice(String str){
       sellingPrice=str;
    }
    public void setCurrentCostPrice(String str){
       currentCostPrice=str;
    }
    public void setCurrentSellingPrice(String str){
       currentSellingPrice=str;
    }
    public String getName(){
       return name;
    }
    public String getNumber(){
       return number;
    }
    public String getClassification(){
       return classification;
    }
    public String getType(){
       return type;
    }
    public int getAmount(){
      return amount;
    }
    public String getCostPrice(){
       return costPrice;
    }
    public String getSellingPrice(){
       return sellingPrice;
    }
    public String getCurrentCostPrice(){
       return currentCostPrice;
    }
    public String getCurrentSellingPrice(){
       return currentSellingPrice;
    }
}
