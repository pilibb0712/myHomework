 package rmi.register.approveBillRegister;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import rmi.config.RmiPort;
import rmi.dataRemoteObject.approveBillRemoteObject.ApproveInventoryLossBillRemoteObject;
/**
 * 网络通信的注册器
 * @author zhangao
 * @version 2017.12.28
 * */
public class ApproveInventoryLossBillRegister {
	public ApproveInventoryLossBillRegister(){
		
	}
	
	/**
	 * 该服务的注册方法
	 * */
	public void register(){
		ApproveInventoryLossBillRemoteObject remoteObject;
		try {
			remoteObject = new ApproveInventoryLossBillRemoteObject();
			LocateRegistry.createRegistry(7703);
			Naming.bind(RmiPort.PATH+"7703/ApproveInventoryLossBillRemoteObject",
					remoteObject);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
	}
}
