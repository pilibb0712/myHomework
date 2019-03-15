package blImpl.bill.billStateBl;


import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.convertors.CommodityPOVOConvertor;
import assistant.convertors.SalesmanBillsPOVOConvertor;
import assistant.convertors.UserInfoPOVOConvertor;
import assistant.type.BillStateEnum;
import blInteract.billBlInteract.BillBlInteractServiceFactory;
import blInteract.billBlInteract.GiftBillBlService;
import blInteract.billBlInteract.InventoryLossBillBlService;
import blInteract.billBlInteract.InventoryOverBillBlService;
import blInteract.billBlInteract.InventoryWarningBillBlService;
import blInteract.billBlInteract.PurchaseBillBlService;
import blInteract.billBlInteract.PurchaseReturnBillBlService;
import blInteract.billBlInteract.SalesBillBlService;
import blInteract.billBlInteract.SalesReturnBillBlService;
import blService.billService.billStateBlService.WareKeeperBillStateBlService;
import po.GiftBillPO;
import po.InventoryLossBillPO;
import po.InventoryOverBillPO;
import po.InventoryWarningBillPO;
import po.PurchaseBillPO;
import po.PurchaseReturnBillPO;
import po.SalesBillPO;
import po.SalesReturnBillPO;
import po.UserInfoPO;
import vo.GiftBillVO;
import vo.InventoryLossBillVO;
import vo.InventoryOverBillVO;
import vo.InventoryWarningBillVO;
import vo.PurchaseBillVO;
import vo.PurchaseReturnBillVO;
import vo.SalesBillVO;
import vo.SalesReturnBillVO;
import vo.UserInfoVO;

/**
 * 库存管理人员单据状态的相关操作
 * @author guxinyu
 * @version 2017.12.25
 *
 */
public class WareKeeperBillStateBlController implements WareKeeperBillStateBlService{
	BillBlInteractServiceFactory factory=new BillBlInteractServiceFactory();
	InventoryWarningBillBlService warningBillService=factory.getInventoryWarningBillBlService();
	InventoryOverBillBlService overBillService=factory.getInventoryOverBillBlService();
	InventoryLossBillBlService lossBillService=factory.getInventoryLossBillBlService();
	PurchaseBillBlService purchaseBillService=factory.getPurchaseBillBlService();
	PurchaseReturnBillBlService purchaseReturnBillService=factory.getPurchaseReturnBillBlService();
	SalesBillBlService salesBillService=factory.getSalesBillBlService();
	SalesReturnBillBlService salesReturnBillService=factory.getSalesReturnBillBlService();
	GiftBillBlService giftBillService=factory.getGiftBillBlService();
	
	@Override
	public ArrayList<InventoryWarningBillVO> getInventoryWarningList(BillStateEnum state, UserInfoVO user) throws RemoteException {
		ArrayList<InventoryWarningBillPO> po=null;
		UserInfoPO userPO=UserInfoPOVOConvertor.voToPO(user);
		if(state==BillStateEnum.DENIED){
			po=warningBillService.getDeniedBillsListByCreater(userPO);
		}else if(state==BillStateEnum.TODO){
			po=warningBillService.getToDoBillsListByExecutor(userPO);
		}else if(state==BillStateEnum.DONE){
			po=warningBillService.getDoneBillsListByExecutor(userPO);
		}
		
		ArrayList<InventoryWarningBillVO> vo=new ArrayList<InventoryWarningBillVO>();
		
		for(int i=0;i<po.size();i++){
			InventoryWarningBillVO eachVO=CommodityPOVOConvertor.inventoryWarningBillPOtoVO(po.get(i));
			vo.add(eachVO);
		}
		return vo;
	}

	@Override
	public boolean deleteUnpassInventoryWarningBill(InventoryWarningBillVO bill) throws RemoteException {
		InventoryWarningBillPO po=CommodityPOVOConvertor.inventoryWarningBillVOtoPO(bill);
		
		if(warningBillService.hideBill(po))
			return true;
		else
			return false;
	}

	@Override
	public ArrayList<InventoryLossBillVO> getInventoryLossList(BillStateEnum state, UserInfoVO user) throws RemoteException {
		System.out.println("fjiewijfiew");
		ArrayList<InventoryLossBillPO> po=null;
		UserInfoPO userPO=UserInfoPOVOConvertor.voToPO(user);
		if(state==BillStateEnum.DENIED){
			System.out.println(userPO.getId()+" "+userPO.getName());
			po=lossBillService.getDeniedBillsListByCreater(userPO);
		}else if(state==BillStateEnum.TODO){
			po=lossBillService.getToDoBillsListByExecutor(userPO);
		}else if(state==BillStateEnum.DONE){
			po=lossBillService.getDoneBillsListByExecutor(userPO);
		}
		
		ArrayList<InventoryLossBillVO> vo=new ArrayList<InventoryLossBillVO>();
		
		for(int i=0;i<po.size();i++){
			InventoryLossBillVO eachVO=CommodityPOVOConvertor.inventoryLossBillPOtoVO(po.get(i));
			vo.add(eachVO);
		}
		return vo;
	}

	@Override
	public boolean deleteUnpassInventoryLossBill(InventoryLossBillVO bill) throws RemoteException {
		InventoryLossBillPO po=CommodityPOVOConvertor.inventoryLossBillVOtoPO(bill);
		
		if(lossBillService.hideBill(po))
			return true;
		else
			return false;
	}

	@Override
	public boolean doneInventoryLossBill(InventoryLossBillVO bill) throws RemoteException {
		InventoryLossBillPO po=CommodityPOVOConvertor.inventoryLossBillVOtoPO(bill);
		
		if(lossBillService.doneBill(po))
			return true;
		else
			return false;
	}

	@Override
	public ArrayList<InventoryOverBillVO> getInventoryOverList(BillStateEnum state, UserInfoVO user) throws RemoteException {
		ArrayList<InventoryOverBillPO> po=null;
		UserInfoPO userPO=UserInfoPOVOConvertor.voToPO(user);
		if(state==BillStateEnum.DENIED){
			po=overBillService.getDeniedBillsListByCreater(userPO);
		}else if(state==BillStateEnum.TODO){
			po=overBillService.getToDoBillsListByExecutor(userPO);
		}else if(state==BillStateEnum.DONE){
			po=overBillService.getDoneBillsListByExecutor(userPO);
		}
		
		ArrayList<InventoryOverBillVO> vo=new ArrayList<InventoryOverBillVO>();
		
		for(int i=0;i<po.size();i++){
			InventoryOverBillVO eachVO=CommodityPOVOConvertor.inventoryOverBillPOtoVO(po.get(i));
			vo.add(eachVO);
		}
		return vo;
	}

	@Override
	public boolean deleteUnpassInventoryOverBill(InventoryOverBillVO bill) throws RemoteException {
		InventoryOverBillPO po=CommodityPOVOConvertor.inventoryOverBillVOtoPO(bill);
		
		if(overBillService.hideBill(po))
			return true;
		else
			return false;
	}

	@Override
	public boolean doneInventoryOverBill(InventoryOverBillVO bill) throws RemoteException {
		InventoryOverBillPO po=CommodityPOVOConvertor.inventoryOverBillVOtoPO(bill);
		
		if(overBillService.doneBill(po))
			return true;
		else
			return false;
	}

	@Override
	public ArrayList<PurchaseBillVO> getPurchaseBillList(BillStateEnum state, UserInfoVO user) throws RemoteException {
		ArrayList<PurchaseBillPO> po=null;
		UserInfoPO userPO=UserInfoPOVOConvertor.voToPO(user);
		if(state==BillStateEnum.DENIED){
			po=purchaseBillService.getDeniedBillsListByCreater(userPO);
		}else if(state==BillStateEnum.TODO){
			po=purchaseBillService.getToDoBillsListByExecutor(userPO);
		}else if(state==BillStateEnum.DONE){
			po=purchaseBillService.getDoneBillsListByExecutor(userPO);
		}
		
		ArrayList<PurchaseBillVO> vo=new ArrayList<PurchaseBillVO>();
		
		for(int i=0;i<po.size();i++){
			PurchaseBillVO eachVO=SalesmanBillsPOVOConvertor.purchaseBillPOtoVO(po.get(i));
			vo.add(eachVO);
		}
		return vo;
	}

	@Override
	public boolean donePurchaseBill(PurchaseBillVO bill) throws RemoteException {
		PurchaseBillPO po=SalesmanBillsPOVOConvertor.purchaseBillVOtoPO(bill);
		
		if(purchaseBillService.doneBill(po))
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
		}else if(state==BillStateEnum.TODO){
			po=purchaseReturnBillService.getToDoBillsListByExecutor(userPO);
		}else if(state==BillStateEnum.DONE){
			po=purchaseReturnBillService.getDoneBillsListByExecutor(userPO);
		}
		
		ArrayList<PurchaseReturnBillVO> vo=new ArrayList<PurchaseReturnBillVO>();
		
		for(int i=0;i<po.size();i++){
			PurchaseReturnBillVO eachVO=SalesmanBillsPOVOConvertor.purchaseReturnBillPOtoVO(po.get(i));
			vo.add(eachVO);
		}
		return vo;
	}

	@Override
	public boolean donePurchaseReturnBill(PurchaseReturnBillVO bill) throws RemoteException {
		PurchaseReturnBillPO po=SalesmanBillsPOVOConvertor.purchaseReturnBillVOtoPO(bill);
		
		if(purchaseReturnBillService.doneBill(po))
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
		}else if(state==BillStateEnum.TODO){
			po=salesBillService.getToDoBillsListByExecutor(userPO);
		}else if(state==BillStateEnum.DONE){
			po=salesBillService.getDoneBillsListByExecutor(userPO);
		}
		
		ArrayList<SalesBillVO> vo=new ArrayList<SalesBillVO>();
		
		for(int i=0;i<po.size();i++){
			SalesBillVO eachVO=SalesmanBillsPOVOConvertor.salesBillPOtoVO(po.get(i));
			vo.add(eachVO);
		}
		return vo;
	}

	@Override
	public boolean doneSalesBill(SalesBillVO bill) throws RemoteException {
		SalesBillPO po=SalesmanBillsPOVOConvertor.salesBillVOtoPO(bill);
		
		if(salesBillService.doneBill(po))
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
		}else if(state==BillStateEnum.TODO){
			po=salesReturnBillService.getToDoBillsListByExecutor(userPO);
		}else if(state==BillStateEnum.DONE){
			po=salesReturnBillService.getDoneBillsListByExecutor(userPO);
		}
		
		ArrayList<SalesReturnBillVO> vo=new ArrayList<SalesReturnBillVO>();
		
		for(int i=0;i<po.size();i++){
			SalesReturnBillVO eachVO=SalesmanBillsPOVOConvertor.salesReturnBillPOtoVO(po.get(i));
			vo.add(eachVO);
		}
		return vo;
	}

	@Override
	public boolean doneSalesReturnBill(SalesReturnBillVO bill) throws RemoteException {
		SalesReturnBillPO po=SalesmanBillsPOVOConvertor.salesReturnBillVOtoPO(bill);
		
		if(salesReturnBillService.doneBill(po))
			return true;
		else
			return false;
	}

	@Override
	public ArrayList<GiftBillVO> getGiftBillList(BillStateEnum state, UserInfoVO user) throws RemoteException {
		ArrayList<GiftBillPO> po=giftBillService.getBillListByState(state);
		
		ArrayList<GiftBillVO> vo=new ArrayList<GiftBillVO>();
		for(int i=0;i<po.size();i++){
			GiftBillVO each=null;
			vo.add(each);
		}
		return vo;
	}

	@Override
	public boolean doneGiftBill(GiftBillVO bill) throws RemoteException {
		GiftBillPO po=null;
		
		if(giftBillService.doneBill(po))
			return true;
		else
			return false;
	}

}
