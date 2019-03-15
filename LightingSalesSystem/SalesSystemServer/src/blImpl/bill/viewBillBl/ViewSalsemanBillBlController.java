package blImpl.bill.viewBillBl;

import java.rmi.RemoteException;
import assistant.convertors.SalesmanBillsPOVOConvertor;
import blImpl.bill.billBl.PurchaseBillBl;
import blImpl.bill.billBl.PurchaseReturnBillBl;
import blImpl.bill.billBl.SalesBillBl;
import blImpl.bill.billBl.SalesReturnBillBl;
import blInteract.billBlInteract.PurchaseBillBlService;
import blInteract.billBlInteract.PurchaseReturnBillBlService;
import blInteract.billBlInteract.SalesBillBlService;
import blInteract.billBlInteract.SalesReturnBillBlService;
import blService.billService.viewBillBlService.ViewSalsemanBillBlService;
import po.PurchaseBillPO;
import po.PurchaseReturnBillPO;
import po.SalesBillPO;
import po.SalesReturnBillPO;
import vo.PurchaseBillVO;
import vo.PurchaseReturnBillVO;
import vo.SalesBillVO;
import vo.SalesReturnBillVO;

public class ViewSalsemanBillBlController implements ViewSalsemanBillBlService{

	@Override
	public PurchaseBillVO getPurchaseBill(String billId) throws RemoteException {
		// TODO Auto-generated method stub
		PurchaseBillBlService service=new PurchaseBillBl();
		PurchaseBillPO po=service.getBillById(billId);
		PurchaseBillVO bill=SalesmanBillsPOVOConvertor.purchaseBillPOtoVO(po);
		return bill;
	}

	@Override
	public PurchaseReturnBillVO getPurchaseReturnBill(String billId) throws RemoteException {
		// TODO Auto-generated method stub
		PurchaseReturnBillBlService service=new PurchaseReturnBillBl();
		PurchaseReturnBillPO po=service.getBillById(billId);
		PurchaseReturnBillVO bill=SalesmanBillsPOVOConvertor.purchaseReturnBillPOtoVO(po);
		return bill;
	}

	@Override
	public SalesBillVO getSalesBill(String billId) throws RemoteException {
		// TODO Auto-generated method stub
		SalesBillBlService service=new SalesBillBl();
		SalesBillPO po=service.getBillById(billId);
		SalesBillVO bill=SalesmanBillsPOVOConvertor.salesBillPOtoVO(po);
		return bill;
	}

	@Override
	public SalesReturnBillVO getSalesReturnBill(String billId) throws RemoteException {
		// TODO Auto-generated method stub
		SalesReturnBillBlService service=new SalesReturnBillBl();
		SalesReturnBillPO po=service.getBillById(billId);
		SalesReturnBillVO bill=SalesmanBillsPOVOConvertor.salesReturnBillPOtoVO(po);
		return bill;
	}

}
