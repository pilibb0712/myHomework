package rmi.register.promotionRegister;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import rmi.config.RmiPort;
import rmi.dataRemoteObject.promotionRemoteObject.TotalStrategyRemoteObject;

public class TotalStrategyRegister {
	public void register(){
		TotalStrategyRemoteObject remoteObject;
		try {
			remoteObject = new TotalStrategyRemoteObject();
			LocateRegistry.createRegistry(7102);
			Naming.bind(RmiPort.PATH+"7102/TotalStrategyRemoteObject",
					remoteObject);
			System.out.println("总价促销策略服务注册成功");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
	}
}
