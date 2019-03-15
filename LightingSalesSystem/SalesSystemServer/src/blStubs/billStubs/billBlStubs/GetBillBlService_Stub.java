package blStubs.billStubs.billBlStubs;

import java.util.ArrayList;
import assistant.type.UserPositionEnum;
import vo.BillVO;


public class GetBillBlService_Stub {

	public BillVO getBill(String billName, String billId) {
		BillVO billVo=new BillVO();
		return billVo;
	}

	public ArrayList<BillVO> getBillList(String billState) {
		ArrayList<BillVO> bills = null;
		bills.add(new BillVO());
		return bills;
	}

	public ArrayList<BillVO> getBillList(String billState, String billName) {
		ArrayList<BillVO> bills = null;
		bills.add(new BillVO());
		return bills;
	}

	public ArrayList<BillVO> getTBDBillsList(String userName, String userId, UserPositionEnum userRole) {
		ArrayList<BillVO> bills = null;
		bills.add(new BillVO());
		return bills;
	}

	public ArrayList<BillVO> getToDoBillsList(String userName, String userId, UserPositionEnum userRole) {
		ArrayList<BillVO> bills = null;
		bills.add(new BillVO());
		return bills;
	}

	public ArrayList<BillVO> getDeniedBillsList(String userName, String userId, UserPositionEnum userRole) {
		ArrayList<BillVO> bills = null;
		bills.add(new BillVO());
		return bills;
	}

	public ArrayList<BillVO> getDoneBillsList(String userName, String userId, UserPositionEnum userRole) {
		ArrayList<BillVO> bills = null;
		bills.add(new BillVO());
		return bills;
	}

}
