package po;

import java.util.ArrayList;

/**
 * 销售明细表
 * @author guxinyu
 * @version 2017.12.10
 *
 */
public class SalesDetailsListPO {
	private ArrayList<SalesBillPO> salesBills;//保存销售单
  	
	 public SalesDetailsListPO(ArrayList<SalesBillPO> sales){
		setSalesBills(sales);
	}

	public ArrayList<SalesBillPO> getSalesBills() {
		return salesBills;
	}

	public void setSalesBills(ArrayList<SalesBillPO> salesBills) {
		this.salesBills = salesBills;
	}

}
