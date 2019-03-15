package blMocks.listBlMocks;

import java.util.ArrayList;

import assistant.utility.Date;
import vo.PurchaseBillVO;
import vo.SalesBillVO;
import vo.SalesDetailsListVO;

public class MockSalesDetailsList {
	public SalesDetailsListVO getSalesDetailsList(){
		String[] a={" "," "};
		SalesBillVO sb=new SalesBillVO("i4541","张三","李四","王二",14,
				a,50.0,50.0,50.0,50.0," ",new Date());
		ArrayList<SalesBillVO> sbArray=new ArrayList<SalesBillVO>();
		sbArray.add(sb);
		
		String[] b={" "," "};
		PurchaseBillVO pb=new PurchaseBillVO("i4851","张三",14," ",b," ", 5000.0," ");
		ArrayList<PurchaseBillVO> pbArray=new ArrayList<PurchaseBillVO>();
		pbArray.add(pb);
		
		SalesDetailsListVO toReturn=new SalesDetailsListVO(sbArray,pbArray);
		
		return toReturn;
	}
}
