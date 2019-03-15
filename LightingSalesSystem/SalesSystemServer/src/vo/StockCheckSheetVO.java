package vo;

import java.io.Serializable;
import java.util.ArrayList;

public class StockCheckSheetVO  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8541640464043374621L;
	private ArrayList<String[]> stockCheckSheet=new ArrayList<String[]>();

	//contents[8]:name ID productionDate time  stock-in-amount cost-money stock-out-amount selling-money

	   public void setStockCheckSheet(ArrayList<String[]> checkSheet){
	   stockCheckSheet=checkSheet;
	   }
	   public ArrayList<String[]> getStockCheckSheet(){
	   return stockCheckSheet;
	   }
}
