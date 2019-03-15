/**
 * 设置并获得check表，包括库存审查表与库存快照
 * @author BeibeiZhang
 * @version 2017.11.28
 *
 */
package blImpl.commodityBl;

import java.text.DecimalFormat;
import java.util.ArrayList;

import assistant.utility.Date;
import dataImpl.commodityData.GoodsData;
import dataImpl.commodityData.StockInOutRecord;
import po.GoodsPO;
import po.StockInOutPO;
import vo.InventoryCheckSheetVO;
import vo.StockCheckSheetVO;

public class CheckSheet {
	/**
	 * public StockCheckSheetVO getStockCheckSheet(Date beginTime,Date endTime)
	 * @param1 Date:beginTime
	 * @param2 Date:endTime
	 * @return StockCheckSheetVO which has been set
	 * @version 2017.11.28
	 * @comment String[]:name ID time stock-in-amount cost-money stock-out-amount selling-money
	 *
	 */
	public StockCheckSheetVO getStockCheckSheet(Date beginTime,Date endTime){
		StockInOutRecord stockData=new StockInOutRecord();
		ArrayList<StockInOutPO> stockRecords=stockData.finds(beginTime, endTime);
		ArrayList<String[]> stockCheckContents=new ArrayList<String[]>();
		int stockInAmount=0;
		int addAmount=0;
		int stockOutAmount=0;
		int deleteAmount=0;
		double stockInMoney=0.0;
		double addMoney=0.0;
		double stockOutMoney=0.0;
		double deleteMoney=0.0;
	    for(int i=0;i<=stockRecords.size()-1;i++){
        	String[] contents=new String[8];

        	//contents[8]:name ID productionDate time  stock-in-amount cost-money stock-out-amount selling-money

        	StockInOutPO oneRecord=stockRecords.get(i);
        	contents[0]=oneRecord.getGoodsName();
        	contents[1]=oneRecord.getGoodsID();
        	contents[2]=oneRecord.getProductionDate();
        	contents[3]=(oneRecord.getTime()).getDate();
        	int stockAmount=oneRecord.getAmount();
        	if(stockAmount<0){
        		contents[4]=null;
        		contents[5]=null;
        		contents[6]=String.valueOf(-stockAmount);
        		contents[7]=oneRecord.getCurrentSellingPrice();
                String price=oneRecord.getCurrentSellingPrice();
                 if(oneRecord.getProductionDate()==null){
                	//如果生产日期没填的话，意味着是库存报损或报溢，属于非交易性的减少或增加商品，另外算
                	deleteAmount=(-stockAmount)+deleteAmount;
                	deleteMoney=deleteMoney+(-stockAmount)*Double.parseDouble(price);
                }else{
        		stockOutAmount=(-stockAmount)+stockOutAmount;
        		stockOutMoney=stockOutMoney+(-stockAmount)*Double.parseDouble(price);
                }
        	}else{
        		contents[6]=null;
        		contents[7]=null;
        		contents[4]=String.valueOf(stockAmount);
        		contents[5]=oneRecord.getCurrentCostPrice();
        		String price=oneRecord.getCurrentCostPrice();
        		if(oneRecord.getProductionDate()==null){
        			//如果生产日期没填的话，意味着是库存报损或报溢，属于非交易性的减少或增加商品，另外算
                	addAmount=stockAmount+addAmount;
                	addMoney=addMoney+stockAmount*Double.parseDouble(price);
        		}else{
        		stockInAmount=stockAmount+stockOutAmount;
        		stockInMoney=stockInMoney+stockAmount*Double.parseDouble(price);
        		}
        }
        	stockCheckContents.add(contents);
	    }
	    DecimalFormat df= new DecimalFormat("#.00");
	    String[] buzinessInAll=new String[4];
	    buzinessInAll[0]=String.valueOf(stockInAmount);
	    buzinessInAll[1]=String.valueOf(df.format(stockInMoney));
	    buzinessInAll[2]=String.valueOf(stockOutAmount);
	    buzinessInAll[3]=String.valueOf(df.format(stockOutMoney));
	    stockCheckContents.add(buzinessInAll);
	    //stockCheckContents的倒数第二行内容为交易性出入库总值（即进售货性的出入库）
	    //stockInAmount、stockInMoney、stockOutAmount、stockOutMoney

	    String[] notBuzinessInAll=new String[4];
	    notBuzinessInAll[0]=String.valueOf(addAmount);
	    notBuzinessInAll[1]=String.valueOf(df.format(addMoney));
	    notBuzinessInAll[2]=String.valueOf(deleteAmount);
	    notBuzinessInAll[3]=String.valueOf(df.format(deleteMoney));
	    stockCheckContents.add(notBuzinessInAll);
	    //stockCheckContents的最后一行内容为非交易性出入库总值（即库存报溢、报损性的出入库）
	    //addAmount、addMoney、deleteAmount、deleteMoney
	    //stockCheckContents的size至少是2
	    StockCheckSheetVO stoCheckSh=new StockCheckSheetVO();
		stoCheckSh.setStockCheckSheet(stockCheckContents);
		return stoCheckSh;
	}

	/**
	 * public ArrayList<String[]> getInventoryCheckSheet()
	 * @param null
	 * @return InventoryCheckSheetVO which has been set
	 * @version 2017.11.28
	 * @comment String[]:id name type amount averagePrice productionDate
	 */

	public InventoryCheckSheetVO getInventoryCheckSheet(){
		GoodsData goodsData=new GoodsData();
		ArrayList<GoodsPO> allGoods=goodsData.finds();
		ArrayList<String[]> inventoryCheckContents=new ArrayList<String[]>();
		for(int i=0;i<=allGoods.size()-1;i++){
			GoodsPO good=allGoods.get(i);
			String[] contents=new String[6];
			//contents[6]:id name type amount averagePrice productionDate
			contents[0]=good.getNumber();
			contents[1]=good.getName();
			contents[2]=good.getType();
			contents[3]=String.valueOf(good.getAmount());
			contents[4]=String.valueOf(getAveragePrice(good.getNumber()));
			contents[5]=getProductionDate(good.getNumber());
			inventoryCheckContents.add(contents);
		}
		InventoryCheckSheetVO inCheckSh=new InventoryCheckSheetVO();
		inCheckSh.setInventoryCheckSheet(inventoryCheckContents);
		return inCheckSh;
	}

	/**
	 * private int getAveragePrice(String goodsID)
	 * @param String:goodsID
	 * @return the averagePrice of the goods
	 * @version 2017.11.28
	 * @comment via calculating the average of the prices of all stocks of the goods
	 */
	public int getAveragePrice(String goodsID){
		//测完了之后将public改为private
		//平均值向下取整，返回int，不返回double
		StockInOutRecord stockData=new StockInOutRecord();
		ArrayList<StockInOutPO> recordsOfTheGoods=stockData.finds(goodsID);
		int num=0;
		double sum=0.0;
		for(int i=0;i<=recordsOfTheGoods.size()-1;i++){
			StockInOutPO record=recordsOfTheGoods.get(i);
			String cuCostPriceStr=record.getCurrentCostPrice();
			String cuSellingPriceStr=record.getCurrentSellingPrice();
			if(cuCostPriceStr!=null){
			double cuCostPrice=Double.parseDouble(cuCostPriceStr);
			sum=sum+cuCostPrice;
			num++;
			}else{
				if(cuSellingPriceStr!=null){
                    double cuSellingPrice=Double.parseDouble(cuSellingPriceStr);
					sum=sum+cuSellingPrice;
					num++;
				}
			}
		}
		int average=0;
		if(num==0){
			//num==0意味着该商品没有出入库记录，那么就根据它初始的进价和零售价计算平均值吧
			GoodsData goodsData=new GoodsData();
			GoodsPO theGoods=goodsData.find(goodsID);
			String costPriceStr=theGoods.getCostPrice();
			String sellingPriceStr=theGoods.getSellingPrice();
			double sellingPrice=0.0;
			double costPrice=0.0;
			if(costPriceStr==null){
				if(sellingPriceStr==null){
					average=0;
				}else{
					sellingPrice=Double.parseDouble(sellingPriceStr);
					average=(int)sellingPrice;
				}
			}else{
				if(sellingPriceStr==null){
					costPrice=Double.parseDouble(costPriceStr);
					average=(int)costPrice;
				}else{
					sellingPrice=Double.parseDouble(sellingPriceStr);
					costPrice=Double.parseDouble(costPriceStr);
					average=(int)((sellingPrice+costPrice)/2);
				}
			}
		}else{
			average=(int)(sum/num);
		}
		return average;
	}

	/**
	 * private String getProductionDate(String goodsID)
	 * @param String:goodsID
	 * @return the productionDate of the goods
	 * @version 2017.11.28
	 * @comment actually productionDate is not the property of GoodsPO
	 * but it is the property of every stockRecord
	 * so i get the newest productionDate of the stocks of the goods
	 */
	public String getProductionDate(String goodsID){
		StockInOutRecord stockData=new StockInOutRecord();
		ArrayList<StockInOutPO> recordsOfTheGoods=stockData.finds(goodsID);
		String recentProductionDate="";
		for(int i=recordsOfTheGoods.size()-1;i>=0;i--){
			//倒序查该商品的出入库记录，很快就能选择最新的出厂日期返回
			//默认最新出入库的它的生产日期也是最新的
		    StockInOutPO record=recordsOfTheGoods.get(i);
		    String productionDate=record.getProductionDate();
		    if(productionDate!=null){
		    	recentProductionDate=productionDate;
		    	return recentProductionDate;
		    }
		}
		return null;
		//如果返回null意味着该商品的出入库记录中没有出厂日期，理论上是不可能的
		//或者该商品没有出入库记录，这理论上也是不可能的
		//但如果真的返回null，就作为null处理，ui上就显示空
	}
}
