package blImpl.listBl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import assistant.type.BillStateEnum;
import blInteract.billBlInteract.BillBlInteractServiceFactory;
import blInteract.billBlInteract.CashExpenseBillBlService;
import blInteract.billBlInteract.GiftBillBlService;
import blInteract.billBlInteract.InventoryLossBillBlService;
import blInteract.billBlInteract.InventoryOverBillBlService;
import blInteract.billBlInteract.InventoryWarningBillBlService;
import blInteract.billBlInteract.PaymentBillBlService;
import blInteract.billBlInteract.PurchaseBillBlService;
import blInteract.billBlInteract.PurchaseReturnBillBlService;
import blInteract.billBlInteract.ReceiptBillBlService;
import blInteract.billBlInteract.SalesBillBlService;
import blInteract.billBlInteract.SalesReturnBillBlService;
import blInteract.customerInteract.CustomerServiceFactory;
import blInteract.customerInteract.GetCustomerService;
import blInteract.userInteract.GetUserService;
import blInteract.userInteract.GetUserServiceFactory;
import po.BusinessProcessListPO;
import po.CashExpenseBillPO;
import po.GiftBillPO;
import po.InventoryLossBillPO;
import po.InventoryOverBillPO;
import po.InventoryWarningBillPO;
import po.PaymentBillPO;
import po.PurchaseBillPO;
import po.PurchaseReturnBillPO;
import po.ReceiptBillPO;
import po.SalesBillPO;
import po.SalesReturnBillPO;
import vo.CustomerInfoVO;
import vo.UserVO;

/**
 * 经营历程表逻辑实现
 * @author guxinyu
 * @version 12.23
 *
 */
public class BusinessProcessListBl {
	
	private static BusinessProcessListBl bl;//单件对象
	
	private BillBlInteractServiceFactory billFactory=new BillBlInteractServiceFactory();
	private InventoryWarningBillBlService inventoryWarningBillService=billFactory.getInventoryWarningBillBlService();
	private InventoryLossBillBlService inventoryLossBillService=billFactory.getInventoryLossBillBlService();
	private InventoryOverBillBlService inventoryOverBillService=billFactory.getInventoryOverBillBlService();
	private GiftBillBlService giftBillService=billFactory.getGiftBillBlService();
	private PaymentBillBlService paymentBillService=billFactory.getPaymentBillBlService();
	private ReceiptBillBlService receiptBillService=billFactory.getReceiptBillBlService();
	private CashExpenseBillBlService cashExpenseBillService=billFactory.getCashExpenseBillBlService();
	private PurchaseBillBlService purchaseBillService=billFactory.getPurchaseBillBlService();
	private PurchaseReturnBillBlService purchaseReturnBillService=billFactory.getPurchaseReturnBillBlService();
	private SalesBillBlService salesBillService=billFactory.getSalesBillBlService();
	private SalesReturnBillBlService salesReturnBillService=billFactory.getSalesReturnBillBlService();
	
	private GetUserServiceFactory userFactory=new GetUserServiceFactory();
	private GetUserService userService=userFactory.getGetUserService();
	
	private CustomerServiceFactory customerFactory=new CustomerServiceFactory();
	private GetCustomerService customerService=customerFactory.getGetCustomerService();
	
	private BusinessProcessListBl(){
		
	}
	
	public static BusinessProcessListBl getInstance(){
		if(bl==null)
			bl=new BusinessProcessListBl();
		return bl;
	}
	
	public BusinessProcessListPO getBills() throws RemoteException {
		ArrayList<InventoryWarningBillPO> inventoryWarningBills=inventoryWarningBillService.getBillListByState(BillStateEnum.DONE);
		ArrayList<InventoryLossBillPO> inventoryLossBills=inventoryLossBillService.getBillListByState(BillStateEnum.DONE);
		ArrayList<InventoryOverBillPO> inventoryOverBills=inventoryOverBillService.getBillListByState(BillStateEnum.DONE);
		ArrayList<CashExpenseBillPO> cashExpenseBills=cashExpenseBillService.getBillListByState(BillStateEnum.DONE);
		ArrayList<PaymentBillPO> paymentBills=paymentBillService.getBillListByState(BillStateEnum.DONE);
		ArrayList<ReceiptBillPO> receiptBills=receiptBillService.getBillListByState(BillStateEnum.DONE);
		ArrayList<SalesBillPO> salesBills=salesBillService.getBillListByState(BillStateEnum.DONE);
		ArrayList<SalesReturnBillPO> salesReturnBills=salesReturnBillService.getBillListByState(BillStateEnum.DONE);
		ArrayList<PurchaseBillPO> purchaseBills=purchaseBillService.getBillListByState(BillStateEnum.DONE);
		ArrayList<PurchaseReturnBillPO> purchaseReturnBills=purchaseReturnBillService.getBillListByState(BillStateEnum.DONE);
		ArrayList<GiftBillPO> giftBills=giftBillService.getBillListByState(BillStateEnum.DONE);
		
		BusinessProcessListPO list=new BusinessProcessListPO(
				inventoryWarningBills,
				inventoryOverBills,
				inventoryLossBills,
				purchaseBills,
				purchaseReturnBills,
				salesBills,
				salesReturnBills,
				receiptBills,
				paymentBills,
				cashExpenseBills,
				giftBills
				);
		
		return list;
	}
	
	public ArrayList<UserVO> getWareKeeperList(){
		ArrayList<UserVO> wareKeeperPOs=userService.getWarekeeper();
		
		return wareKeeperPOs;
	}
	
	public ArrayList<UserVO> getSalesmanList(){
		ArrayList<UserVO> salesmanPOs=userService.getSalesman();
		
		return salesmanPOs;
	}
	
	public ArrayList<UserVO> getFinancialStaffList(){
		ArrayList<UserVO> financialStaffPOs=userService.getFianceStaff();
		
		return financialStaffPOs;
	}
	
	public ArrayList<UserVO> getManagerList(){
		ArrayList<UserVO> managerPOs=userService.getManager();
		
		return managerPOs;
	}
	
	public ArrayList<CustomerInfoVO> getCustomerList(){
		ArrayList<CustomerInfoVO> vo=customerService.GetCustomers();
		return vo;
	}
}
