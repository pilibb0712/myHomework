package rmi.dataRemoteObject.commodityRemoteObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import blImpl.commodityBl.ActualInventoryCheckController;
import blService.commodityBlService.ActualInventoryCheckBlService;

public class ActualInventoryCheckRemoteObject extends UnicastRemoteObject implements ActualInventoryCheckBlService{


	/**
	 *
	 */
	private static final long serialVersionUID = -3601479521309448283L;

	public ActualInventoryCheckRemoteObject() throws RemoteException {
		super();
	}

	ActualInventoryCheckBlService service=new ActualInventoryCheckController();

	@Override
	public int actualInventoryCheck(String goodsTag, int actualAmount) throws RemoteException {
		// TODO Auto-generated method stub
		return service.actualInventoryCheck(goodsTag, actualAmount);
	}


}
