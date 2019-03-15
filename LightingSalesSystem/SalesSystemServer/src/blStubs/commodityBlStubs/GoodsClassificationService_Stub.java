package blStubs.commodityBlStubs;

import java.rmi.RemoteException;
import java.util.ArrayList;

import blService.commodityBlService.GoodsClassificationBlService;
import vo.ClassificationTreeVO;
import vo.ClassificationVO;
import vo.GoodsVO;

public class GoodsClassificationService_Stub implements GoodsClassificationBlService{
//do some change 2017.10.30
	public ClassificationTreeVO getClassificationTree() throws RemoteException{
		ClassificationTreeVO classificationTree=new ClassificationTreeVO();
		ArrayList<String[]> classificationLayer=new ArrayList<String[]>();
		String[] theFirstLayer={"A 1"};
		String[] theSecondLayer={"AA 11"," AB 12","AC 13"};
		classificationLayer.add(theFirstLayer);
		classificationLayer.add(theSecondLayer);
		ArrayList<String[]> sonFatherConnection=new ArrayList<String[]>();
		String[] firstToSecond={"0 1 2"};
		sonFatherConnection.add(firstToSecond);
		classificationTree.setClassificationLayer(classificationLayer);
		classificationTree.setSonFatherConnection(sonFatherConnection);
		return classificationTree;
	}

	public boolean ifClassificationExist(String classificationTag) throws RemoteException{
		return true;
		//just return true is not suitable for the caller
		//because the caller need the boolen to do the judgement...???
	}
	public boolean addNewClassification(ClassificationVO vo)throws RemoteException{
		return true;
	}
	public ClassificationVO classificationInfor(String classificationNum) throws RemoteException{
		ClassificationVO classification=new ClassificationVO();
		classification.setName("A");
		classification.setNumber("1");
		classification.setFatherClassification("null");
		ArrayList<String> sonClassification=new ArrayList<String>();
		sonClassification.add("AA 11");
		sonClassification.add("AB 12");
		sonClassification.add("AC 13");
		return classification;
	}
	public boolean addClassification(ClassificationVO vo) throws RemoteException{
		return true;
	}
	public boolean deleteClassification(ClassificationVO vo) throws RemoteException{
		return true;
	}
	public boolean modifyClassificationInfor(ClassificationVO vo) throws RemoteException{
		return true;
	}


}
