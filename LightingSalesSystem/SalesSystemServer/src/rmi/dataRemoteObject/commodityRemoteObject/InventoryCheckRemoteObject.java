package rmi.dataRemoteObject.commodityRemoteObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import blImpl.commodityBl.InventoryCheckController;
import blService.commodityBlService.InventoryCheckBlService;
import vo.InventoryCheckSheetVO;

public class InventoryCheckRemoteObject extends UnicastRemoteObject implements InventoryCheckBlService{


	/**
	 *
	 */
	private static final long serialVersionUID = -1580000246783529709L;

	public InventoryCheckRemoteObject() throws RemoteException {
		super();
	}

	InventoryCheckBlService service=new InventoryCheckController();

	@Override
	public InventoryCheckSheetVO inventoryCheckSheet() throws RemoteException {
		// TODO Auto-generated method stub
		return service.inventoryCheckSheet();
	}


	}