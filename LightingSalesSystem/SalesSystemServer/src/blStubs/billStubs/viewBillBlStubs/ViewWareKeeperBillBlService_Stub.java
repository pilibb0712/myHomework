package blStubs.billStubs.viewBillBlStubs;

<<<<<<< HEAD
import assistant.type.BillCategoryEnum;
import assistant.type.BillStateEnum;
import assistant.utility.Date;
=======
import java.rmi.RemoteException;

>>>>>>> gxy
import blService.billService.viewBillBlService.ViewWareKeeperBillBlService;
import vo.InventoryLossBillVO;
import vo.InventoryOverBillVO;
import vo.InventoryWarningBillVO;
import vo.UserInfoVO;

/**
<<<<<<< HEAD
 * 查看库存类单据的逻辑的桩
 * @author beibeiZhang
 * @version 2017.11.7;2017.12.13
 *
 */
public class ViewWareKeeperBillBlService_Stub implements ViewWareKeeperBillBlService{
	public InventoryWarningBillVO getInventoryWarningBill(String billID){
		InventoryWarningBillVO inventoryWarningBill=new InventoryWarningBillVO();
		UserInfoVO creator=new UserInfoVO();
		creator.setId("001");
		creator.setName("beibei");
		UserInfoVO approver=new UserInfoVO();
		approver.setId("002");
		approver.setName("zhangao");
		UserInfoVO finisher=new UserInfoVO();
		finisher.setId("001");
		finisher.setName("beibei");
		Date createDate=new Date();
		Date approveDate=new Date();
		Date finishDate=new Date();
		inventoryWarningBill.setApproveDate(approveDate);
		inventoryWarningBill.setApprover(approver);
		inventoryWarningBill.setApproverComment("xxxx");
		inventoryWarningBill.setBillStateEnum(BillStateEnum.DONE);
		inventoryWarningBill.setCategoryEnum(BillCategoryEnum.INVENTORY_WARNING_BILL);
		inventoryWarningBill.setCreateDate(createDate);
		inventoryWarningBill.setCreater(creator);
		inventoryWarningBill.setExecutor(finisher);
		inventoryWarningBill.setFinishDate(finishDate);
		inventoryWarningBill.setGoodsID("00001");
		inventoryWarningBill.setGoodsName("小灯A");
		inventoryWarningBill.setGoodsType("00001A");
		inventoryWarningBill.setId(billID);
		inventoryWarningBill.setLossAmount("10");
		inventoryWarningBill.setSystemAmount("40");
		inventoryWarningBill.setWarningAmount("50");
		return inventoryWarningBill;
	}
	public InventoryLossBillVO getInventoryLossBill(String billID){
		InventoryLossBillVO inventoryLossBill=new InventoryLossBillVO();
		UserInfoVO creator=new UserInfoVO();
		creator.setId("001");
		creator.setName("beibei");
		UserInfoVO approver=new UserInfoVO();
		approver.setId("002");
		approver.setName("zhangao");
		UserInfoVO finisher=new UserInfoVO();
		finisher.setId("001");
		finisher.setName("beibei");
		Date createDate=new Date();
		Date approveDate=new Date();
		Date finishDate=new Date();
		inventoryLossBill.setApproveDate(approveDate);
		inventoryLossBill.setApprover(approver);
		inventoryLossBill.setApproverComment("xxxx");
		inventoryLossBill.setBillStateEnum(BillStateEnum.DONE);
		inventoryLossBill.setCategoryEnum(BillCategoryEnum.INVENTORY_LOSS_BILL);
		inventoryLossBill.setCreateDate(createDate);
		inventoryLossBill.setCreater(creator);
		inventoryLossBill.setExecutor(finisher);
		inventoryLossBill.setFinishDate(finishDate);
		inventoryLossBill.setGoodsID(billID);
		inventoryLossBill.setGoodsName("小灯A");
		inventoryLossBill.setGoodsType("00001A");
		inventoryLossBill.setId("KCBSD20171213153300");
		inventoryLossBill.setLossAmount("10");
		inventoryLossBill.setSystemAmount("50");
		inventoryLossBill.setRealAmount("40");
		return inventoryLossBill;
	}
	public InventoryOverBillVO getInventoryOverBill(String billID){
		InventoryOverBillVO inventoryOverBill=new InventoryOverBillVO();
		UserInfoVO creator=new UserInfoVO();
		creator.setId("001");
		creator.setName("beibei");
		UserInfoVO approver=new UserInfoVO();
		approver.setId("002");
		approver.setName("zhangao");
		UserInfoVO finisher=new UserInfoVO();
		finisher.setId("001");
		finisher.setName("beibei");
		Date createDate=new Date();
		Date approveDate=new Date();
		Date finishDate=new Date();
		inventoryOverBill.setApproveDate(approveDate);
		inventoryOverBill.setApprover(approver);
		inventoryOverBill.setApproverComment("xxxx");
		inventoryOverBill.setBillStateEnum(BillStateEnum.DONE);
		inventoryOverBill.setCategoryEnum(BillCategoryEnum.INVENTORY_OVER_BILL);
		inventoryOverBill.setCreateDate(createDate);
		inventoryOverBill.setCreater(creator);
		inventoryOverBill.setExecutor(finisher);
		inventoryOverBill.setFinishDate(finishDate);
		inventoryOverBill.setGoodsID("00001");
		inventoryOverBill.setGoodsName("小灯A");
		inventoryOverBill.setGoodsType("00001A");
		inventoryOverBill.setId(billID);
		inventoryOverBill.setOverAmount("10");
		inventoryOverBill.setSystemAmount("40");
		inventoryOverBill.setRealAmount("50");
		return inventoryOverBill;
		}
=======
 * 查看库存类单据的逻辑接口
 * @author guxinyu
 * @version 2017.12.12
 *
 */
public class ViewWareKeeperBillBlService_Stub implements ViewWareKeeperBillBlService{

	@Override
	public InventoryWarningBillVO getInventoryWarningBill(String billID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InventoryLossBillVO getInventoryLossBill(String billID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InventoryOverBillVO getInventoryOverBill(String billID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
>>>>>>> gxy
	}

}


