package rmi.register.promotionRegister;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import rmi.config.RmiPort;
import rmi.dataRemoteObject.promotionRemoteObject.ComboStrategyRemoteObject;

public class ComboStrategyRegister {
	public void register(){
		ComboStrategyRemoteObject remoteObject;
		try {
			remoteObject = new ComboStrategyRemoteObject();
			LocateRegistry.createRegistry(7100);
			Naming.bind(RmiPort.PATH+""
					+ "7100/ComboStrategyRemoteObject",
					remoteObject);
			System.out.println("组合促销策略服务注册成功");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
	}
}
