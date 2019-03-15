package vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 销售明细表
 * @author guxinyu
 * @version 2017.12.11
 *
 */
public class SalesDetailsListVO  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6780583279368261919L;
	private ArrayList<SalesDetailsListSalesBillVO> salesBills;//销售单
  	
	 public SalesDetailsListVO(ArrayList<SalesDetailsListSalesBillVO> sales){
		setSalesBills(sales);
	}

	public ArrayList<SalesDetailsListSalesBillVO> getSalesBills() {
		return salesBills;
	}

	public void setSalesBills(ArrayList<SalesDetailsListSalesBillVO> salesBills) {
		this.salesBills = salesBills;
	}
	
}
