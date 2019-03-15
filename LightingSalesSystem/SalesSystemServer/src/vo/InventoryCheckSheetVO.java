package vo;

import java.io.Serializable;
import java.util.ArrayList;

public class InventoryCheckSheetVO  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6759216738238031164L;
	private ArrayList<String[]> inventoryCheckSheet=new ArrayList<String[]>();
	//id name type amount averagePrice productionDate
	  public void setInventoryCheckSheet(ArrayList<String[]> checkSheet ){
	  inventoryCheckSheet=checkSheet;
	  }
	  public ArrayList<String[]> getInventoryCheckSheet(){
	  return inventoryCheckSheet;
	  }
}
