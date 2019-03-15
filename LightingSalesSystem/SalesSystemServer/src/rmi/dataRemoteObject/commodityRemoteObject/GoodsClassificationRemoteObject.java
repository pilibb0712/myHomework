package rmi.dataRemoteObject.commodityRemoteObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import blImpl.commodityBl.GoodsClassificationController;
import blService.commodityBlService.GoodsClassificationBlService;
import vo.ClassificationTreeVO;
import vo.ClassificationVO;

public class GoodsClassificationRemoteObject extends UnicastRemoteObject implements GoodsClassificationBlService{


	/**
	 *
	 */
	private static final long serialVersionUID = 9187040855339678913L;

	public GoodsClassificationRemoteObject() throws RemoteException {
		super();
	}

	GoodsClassificationBlService service=new GoodsClassificationController();

	@Override
	public ClassificationTreeVO getClassificationTree() throws RemoteException {
		// TODO Auto-generated method stub
		return service.getClassificationTree();
	}

	@Override
	public boolean ifClassificationExist(String classificationTag) throws RemoteException {
		// TODO Auto-generated method stub
		return service.ifClassificationExist(classificationTag);
	}

	@Override
	public boolean addNewClassification(ClassificationVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return service.addNewClassification(vo);
	}

	@Override
	public ClassificationVO classificationInfor(String classificationNum) throws RemoteException {
		// TODO Auto-generated method stub
		return service.classificationInfor(classificationNum);
	}

	@Override
	public boolean addClassification(ClassificationVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return service.addClassification(vo);
	}

	@Override
	public boolean deleteClassification(ClassificationVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return service.deleteClassification(vo);
	}

	@Override
	public boolean modifyClassificationInfor(ClassificationVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return service.modifyClassificationInfor(vo);
	}


	}
