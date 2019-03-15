package vo;

import java.io.Serializable;

import assistant.utility.Date;

//do some changes 2017.11.06
public class StockInOutVO  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4698293516694035423L;
	//一旦新建了一个VO，请全部set，如果没有，某项的值请初始化为0系
	private String operatorName;
	private Date time;
	private String goodsID;
	private String goodsName;
	private String productionDate;
	//如果出库，则为当天日期，如果是入库，则是仓库管理员手动填写
	//当然也存在库存报溢、报损、红冲时的出入库，这时候没有出厂日期，为null
	private int amount;
	//can be negative
	//positive: stockIN
	//negative:stockOUT
	private String currentCostPrice;
    //如果入库，则为仓库管理员手动填写的最近进价，最近零售价则为0，设为null
	//当然也存在库存报溢或红冲时的入库，如果是库存报溢，将currentCostPrice设为单据上的goodsPrice
	private String currentSellingPrice;
    //如果出库，则为仓库管理员手动填写的最近零售价，最近进价则为0，设为null
	//当然也存在库存报损或红冲时的出库，如果是库存报损，将currentSellingPrice设为单据上的goodsPrice

    public void setOperatorName(String str){
       operatorName=str;
    }
    public void setTime(Date date){
       time=date;
    }
    public void setGoodsID(String str){
        goodsID=str;
     }
    public void setGoodsName(String str){
        goodsName=str;
     }
    public void setAmount(int amoun){
       amount=amoun;
    }
    public void setProductionDate(String productdate){
        productionDate=productdate;
     }
     public void setCurrentCostPrice(String str){
        currentCostPrice=str;
     }
     public void setCurrentSellingPrice(String str){
        currentSellingPrice=str;
     }
    public String getOperatorName(){
       return operatorName;
    }
    public Date getTime(){
       return time;
    }
    public String getGoodsID(){
        return goodsID;
     }
    public String getGoodsName(){
        return goodsName;
     }
    public int getAmount(){
       return amount;
    }
    public String getProductionDate(){
        return productionDate;
     }
     public String getCurrentCostPrice(){
        return currentCostPrice;
     }
     public String getCurrentSellingPrice(){
        return currentSellingPrice;
     }
}
