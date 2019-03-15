package blStubs.billStubs.billStateBlStubs;

import blService.billService.billStateBlService.SalesmanBillStateBlService;

/**
 * 销售进货人员单据状态逻辑接口桩
 * @author guxinyu
 * @version 2017.11.7
 *
 */
public class SalesmanBillStateBlService_Stub implements SalesmanBillStateBlService{
	  
		@Override
		//获取未通过审核的单据的单据编号列表
		public String[] getUnpassedList(){
			String[] toReturn={"PurchaseBill/JHD-20160530-00001",
					"SalesBill/XSD-20160530-00002"};
			
			return toReturn;
		}
		
}
