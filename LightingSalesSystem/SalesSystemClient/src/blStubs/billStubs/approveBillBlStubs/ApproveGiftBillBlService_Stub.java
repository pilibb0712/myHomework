package blStubs.billStubs.approveBillBlStubs;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.utility.Date;
import blService.billService.approveBillBlService.ApproveGiftBillBlService;
import vo.GiftBillVO;
import vo.UserInfoVO;

public class ApproveGiftBillBlService_Stub implements ApproveGiftBillBlService {

	@Override
	public ArrayList<GiftBillVO> getBillsList() throws RemoteException {
		GiftBillVO vo1= new GiftBillVO();
		vo1.setCreateDate(new Date(2017,2,6,11,30));
		UserInfoVO creater1 = new UserInfoVO();
		creater1.setId("00001");
		creater1.setName("zhangao");
		vo1.setCreater(creater1);
		vo1.setId("KCZSD-20170206-00001");
		
//		GiftBillVO vo2 = new GiftBillVO();
//		GiftBillVO vo3 = new GiftBillVO();
		ArrayList<GiftBillVO> bills = new ArrayList<>();
		bills.add(vo1);
		return bills;
	}

	@Override
	public boolean passBill(GiftBillVO billVO) throws RemoteException {
		return true;
	}

	@Override
	public boolean denyBill(GiftBillVO billVO) throws RemoteException {
		return true;
	}

	

}
