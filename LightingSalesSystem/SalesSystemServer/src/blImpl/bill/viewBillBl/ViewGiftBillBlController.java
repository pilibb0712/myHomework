package blImpl.bill.viewBillBl;

import java.rmi.RemoteException;

import assistant.convertors.GiftBillPOVOConvertor;
import assistant.exception.Myexception;
import blImpl.bill.billBl.GiftBillBl;
import blInteract.billBlInteract.GiftBillBlService;
import blService.billService.viewBillBlService.ViewGiftBillBlService;
import po.GiftBillPO;
import vo.GiftBillVO;

/**
 * @author zhangao 
 * @version 2017.12.28
 * */
public class ViewGiftBillBlController implements ViewGiftBillBlService {

	/**
	 * 根据单据Id得到一张giftBill
	 * @param billId单据的ID
	 * @return GiftVO 该编号的单据
	 * @throws RemoteException
	 * @throws Myexception 自定义异常
	 * */
	@Override
	public GiftBillVO getGiftBill(String billId) throws RemoteException, Myexception {
		GiftBillBlService service = new GiftBillBl();
		GiftBillPO po = service.getBillById(billId);
		GiftBillVO vo = GiftBillPOVOConvertor.giftBillPOtoVO(po);
		return vo;
	}
	
}
