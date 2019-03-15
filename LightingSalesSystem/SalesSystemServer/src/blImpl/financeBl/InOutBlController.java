package blImpl.financeBl;

import java.rmi.RemoteException;

import assistant.convertors.InOutPOVOConvertor;
import blInteract.financeInteract.AutoAddEntryService;
import blService.financeBlService.InOutBlService;
import po.InOutDetailsPO;
import po.InitialInfoPO;
import po.SingleEntryPO;
import vo.InOutDetailsVO;
import vo.InitialInfoVO;
import vo.SingleEntryVO;

/**
 * 账单管理的控制模块
 * @author guxinyu
 * @version 2017.12.4
 *
 */
public class InOutBlController implements InOutBlService, AutoAddEntryService{
	
	private InOutBl inOutBl=InOutBl.getInstance();

	@Override
	public boolean newInOut(InitialInfoVO initialInfo) throws RemoteException {
		InitialInfoPO po=InOutPOVOConvertor.initialInfoVOtoPO(initialInfo);
		if(inOutBl.saveInOut(po))
			return true;
		else
			return false;
	}

	@Override
	public InitialInfoVO viewInitialInformation(String inOutYear) throws RemoteException {
		InitialInfoPO po=inOutBl.getInitialInformation(inOutYear);
		InitialInfoVO vo=InOutPOVOConvertor.initialInfoPOtoVO(po);
		return vo;
	}

	@Override
	public InOutDetailsVO viewInOutDetails(String inOutYear) throws RemoteException {
		InOutDetailsPO po=inOutBl.getInOutDetails(inOutYear);
		InOutDetailsVO vo=InOutPOVOConvertor.inOutDetailsPOtoVO(po);
		return vo;
	}

	@Override
	public boolean autoAddEntry(SingleEntryVO toAdd) throws RemoteException {
		SingleEntryPO po=InOutPOVOConvertor.singleEntryVOtoPO(toAdd);
		if(inOutBl.addEntry(po))
			return true;
		else
			return false;
	}

	@Override
	public String[] viewInOutList() throws RemoteException {
		String[] list=inOutBl.getInOutList();
		return list;
	}

}
