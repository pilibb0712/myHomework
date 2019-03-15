package rmi.dataRemoteObject.financeRemoteObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import assistant.exception.AccountNameExistException;
import blImpl.financeBl.BankAccountBlController;
import blService.financeBlService.BankAccountBlService;
import vo.BankAccountVO;

public class BankAccountRemoteObject extends UnicastRemoteObject implements BankAccountBlService{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4120842507103279141L;

	public BankAccountRemoteObject() throws RemoteException{
		super();
	}

	BankAccountBlService service=new BankAccountBlController();
	
	@Override
	public boolean newBankAccount(BankAccountVO toSave) throws RemoteException, AccountNameExistException{
		return service.newBankAccount(toSave);
	}

	@Override
	public ArrayList<BankAccountVO> viewBankAccounts() throws RemoteException {
		return service.viewBankAccounts();
	}

	@Override
	public boolean updateAccount(String oldAccountName, String newAccountName) throws RemoteException, AccountNameExistException{
		return service.updateAccount(oldAccountName, newAccountName);
	}

	@Override
	public boolean deleteAccount(String toDelete) throws RemoteException {
		return service.deleteAccount(toDelete);
	}
	
}
