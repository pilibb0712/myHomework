package blImpl.bill.billStateBl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.convertors.SalesmanBillsPOVOConvertor;
import assistant.convertors.UserInfoPOVOConvertor;
import assistant.type.BillStateEnum;
import blInteract.billBlInteract.BillBlInteractServiceFactory;
import blInteract.billBlInteract.PurchaseBillBlService;
import blInteract.billBlInteract.PurchaseReturnBillBlService;
import blInteract.billBlInteract.SalesBillBlService;
import blInteract.billBlInteract.SalesReturnBillBlService;
import blService.billService.billStateBlService.SalesmanBillStateBlService;
import po.PurchaseBillPO;
import po.PurchaseReturnBillPO;
import po.SalesBillPO;
import po.SalesReturnBillPO;
import po.UserInfoPO;
import vo.PurchaseBillVO;
import vo.PurchaseReturnBillVO;
import vo.SalesBillVO;
import vo.SalesReturnBillVO;
import vo.UserInfoVO;

/**
 * 销售进货人员单据状态的控制模块
 * @author guxinyu 
 * @version 2017.12.20
 *
 */
public class SalesmanBillStateBlController implements SalesmanBillStateBlService{
	BillBlInteractServiceFactory factory=new BillBlInteractServiceFactory();
	PurchaseBillBlService purchaseBillService=factory.getPurchaseBillBlService();
	PurchaseReturnBillBlService purchaseReturnBillService=factory.getPurchaseReturnBillBlService();
	SalesBillBlService salesBillService=factory.getSalesBillBlService();
	SalesReturnBillBlService salesReturnBillService=factory.getSalesReturnBillBlService();
	
	@Override
	public ArrayList<PurchaseBillVO> getPurchaseBillList(BillStateEnum state, UserInfoVO user) throws RemoteException {
		ArrayList<PurchaseBillPO> po=null;
		UserInfoPO userPO=UserInfoPOVOConvertor.voToPO(user);
		if(state==BillStateEnum.DENIED){
			po=purchaseBillService.getDeniedBillsListByCreater(userPO);
		}else if(state==BillStateEnum.DONE){
			po=purchaseBillService.getBillListByState(BillStateEnum.DONE);
		}
	
		ArrayList<PurchaseBillVO> vo=new ArrayList<PurchaseBillVO>();
		
		for(int i=0;i<po.size();i++){
			PurchaseBillVO eachVO=SalesmanBillsPOVOConvertor.purchaseBillPOtoVO(po.get(i));
			vo.add(eachVO);
		}
		return vo;
	}

	@Override
	public boolean deletePurchaseBill(PurchaseBillVO bill) throws RemoteException {
		PurchaseBillPO po=SalesmanBillsPOVOConvertor.purchaseBillVOtoPO(bill);
		
		if(purchaseBillService.hideBill(po))
			return true;
		else 
			return false;
	}

	@Override
	public ArrayList<PurchaseReturnBillVO> getPurchaseReturnBillList(BillStateEnum state, UserInfoVO user) throws RemoteException {
		ArrayList<PurchaseReturnBillPO> po=null;	
		UserInfoPO userPO=UserInfoPOVOConvertor.voToPO(user);
		if(state==BillStateEnum.DENIED){
			po=purchaseReturnBillService.getDeniedBillsListByCreater(userPO);
		}else if(state==BillStateEnum.DONE){
			po=purchaseReturnBillService.getBillListByState(BillStateEnum.DONE);
		}
		
		ArrayList<PurchaseReturnBillVO> vo=new ArrayList<PurchaseReturnBillVO>();
		
		for(int i=0;i<po.size();i++){
			PurchaseReturnBillVO eachVO=SalesmanBillsPOVOConvertor.purchaseReturnBillPOtoVO(po.get(i));
			vo.add(eachVO);
		}
		return vo;
	}

	@Override
	public boolean deletePurchaseReturnBill(PurchaseReturnBillVO bill) throws RemoteException {
		PurchaseReturnBillPO po=SalesmanBillsPOVOConvertor.purchaseReturnBillVOtoPO(bill);
		
		if(purchaseReturnBillService.hideBill(po))
			return true;
		else 
			return false;
	}

	@Override
	public ArrayList<SalesBillVO> getSalesBillList(BillStateEnum state, UserInfoVO user) throws RemoteException {
		ArrayList<SalesBillPO> po=null;
		UserInfoPO userPO=UserInfoPOVOConvertor.voToPO(user);
		if(state==BillStateEnum.DENIED){
			po=salesBillService.getDeniedBillsListByCreater(userPO);
		}else if(state==BillStateEnum.DONE){
			po=salesBillService.getBillListByState(BillStateEnum.DONE);
		}
		
		ArrayList<SalesBillVO> vo=new ArrayList<SalesBillVO>();
		
		for(int i=0;i<po.size();i++){
			SalesBillVO eachVO=SalesmanBillsPOVOConvertor.salesBillPOtoVO(po.get(i));
			vo.add(eachVO);
		}
		return vo;
	}

	@Override
	public boolean deleteSalesBill(SalesBillVO bill) throws RemoteException {
		SalesBillPO po=SalesmanBillsPOVOConvertor.salesBillVOtoPO(bill);
		
		if(salesBillService.hideBill(po))
			return true;
		else 
			return false;
	}

	@Override
	public ArrayList<SalesReturnBillVO> getSalesReturnBillList(BillStateEnum state, UserInfoVO user) throws RemoteException {
		ArrayList<SalesReturnBillPO> po=null;
		UserInfoPO userPO=UserInfoPOVOConvertor.voToPO(user);
		if(state==BillStateEnum.DENIED){
			po=salesReturnBillService.getDeniedBillsListByCreater(userPO);
		}else if(state==BillStateEnum.DONE){
			po=salesReturnBillService.getBillListByState(BillStateEnum.DONE);
		}
		
		ArrayList<SalesReturnBillVO> vo=new ArrayList<SalesReturnBillVO>();
		
		for(int i=0;i<po.size();i++){
			SalesReturnBillVO eachVO=SalesmanBillsPOVOConvertor.salesReturnBillPOtoVO(po.get(i));
			vo.add(eachVO);
		}
		return vo;
	}

	@Override
	public boolean deleteSalesReturnBill(SalesReturnBillVO bill) throws RemoteException {
		SalesReturnBillPO po=SalesmanBillsPOVOConvertor.salesReturnBillVOtoPO(bill);
		
		if(salesReturnBillService.hideBill(po))
			return true;
		else 
			return false;
	}
	
}
