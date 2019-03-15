package blImpl.commodityBl;

import java.rmi.RemoteException;

import blService.commodityBlService.GoodsClassificationBlService;
import dataImpl.commodityData.GoodsClassificationData;
import po.ClassificationPO;
import po.GoodsPO;
import assistant.convertors.CommodityPOVOConvertor;
import vo.ClassificationTreeVO;
import vo.ClassificationVO;
import vo.GoodsVO;

public class GoodsClassificationController implements GoodsClassificationBlService{

	@Override
	public ClassificationTreeVO getClassificationTree() throws RemoteException {
		// TODO Auto-generated method stub
		ClassificationTree treeObj=new ClassificationTree();
		ClassificationTreeVO treeVO=treeObj.getTree();
		return treeVO;
	}

	@Override
	public boolean ifClassificationExist(String classificationTag) throws RemoteException {
		// TODO Auto-generated method stub
		GoodsClassificationData classificationData=new GoodsClassificationData();
		ClassificationPO theClassification=classificationData.find(classificationTag);
		if(theClassification==null){
			return false;
		}
		else{
			return true;
		}
	}

	@Override
	public boolean addNewClassification(ClassificationVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		//add new root classification
		ID setID=new ID();
		String rootClassificationID=setID.rootClassificationID();
		vo.setNumber(rootClassificationID);
		//set id of the new root classification

		GoodsClassificationData classificationData=new GoodsClassificationData();
		CommodityPOVOConvertor voTOpo=new CommodityPOVOConvertor();
		ClassificationPO po=voTOpo.classificationVOtoPO(vo);
		classificationData.insert(po);
		//update classificationData
		return true;
	}

	@Override
	public ClassificationVO classificationInfor(String classificationTag) throws RemoteException {
		// TODO Auto-generated method stub
		GoodsClassificationData classificationData=new GoodsClassificationData();
		ClassificationPO classPO=classificationData.find(classificationTag);
		CommodityPOVOConvertor poTOvo=new CommodityPOVOConvertor();
		ClassificationVO classVO=poTOvo.classificationPOtoVO(classPO);
		return classVO;
		//因为在此之前会做一次类别是否存在的检查，所以不用担心该类别不存在
	}

	@Override
	public boolean addClassification(ClassificationVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		String father=vo.getFatherClassification();
		ID setID=new ID();
		String sonClassifictaionID=setID.sonClassificationID(father);
		vo.setNumber(sonClassifictaionID);
		//set id of the new son classification


		GoodsClassificationData classificationData=new GoodsClassificationData();
		CommodityPOVOConvertor voTOpo=new CommodityPOVOConvertor();
		ClassificationPO classPO=voTOpo.classificationVOtoPO(vo);
	    classificationData.insert(classPO);
	    //update GoodsClassificationData

	    UpdateClassificationStructure updateStructure=new UpdateClassificationStructure();
	    updateStructure.addSon(vo);
	    //update the sonClassificationList of the father of the classification
		return true;
	}

	@Override
	public boolean deleteClassification(ClassificationVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		if(vo.getFatherClassification()==null){
			//如果要删除的类别是根节点，就不用更新类别structure了
	    }else{
	    	UpdateClassificationStructure updateStructure=new UpdateClassificationStructure();
		    updateStructure.deleteSon(vo);
	    }
	    //update the sonClassificationList of the father of the classification

	    GoodsClassificationData classificationData=new GoodsClassificationData();
		CommodityPOVOConvertor voTOpo=new CommodityPOVOConvertor();
		ClassificationPO classPO=voTOpo.classificationVOtoPO(vo);
	    classificationData.delete(classPO);
	    //update GoodsClassificationData
		return true;
	}
	@Override
	public boolean modifyClassificationInfor(ClassificationVO vo) throws RemoteException{
		CommodityPOVOConvertor voTOpo=new CommodityPOVOConvertor();
		GoodsClassificationData classificationData=new GoodsClassificationData();
		String classificationID=vo.getNumber();
		ClassificationPO preClassificationPO=classificationData.find(classificationID);
		String preFather=preClassificationPO.getFatherClassification();
		System.out.println(preFather);
		String newFather=vo.getFatherClassification();
		System.out.println(newFather);
		if((newFather==null)&&(preFather==null)){
			//如果父类别都是null，即没有修改，那么不用更新类别structure
		}else if((newFather==null)&&(preFather!=null)){
		   //原则上是不可能通过修改类别将一个类别的father设为null的
		}else if((newFather!=null)&&(preFather==null)){
			//如果父类别修改了，那么更新类别structure,原父类别中删掉该类别，新父类别中增加该类别
			UpdateClassificationStructure classStructure=new UpdateClassificationStructure();
			ClassificationVO preClassificationVO=voTOpo.classificationPOtoVO(preClassificationPO);
			classStructure.deleteSon(preClassificationVO);
			classStructure.addSon(vo);
		}else{//(newFather!=null)&&(preFather!=null)
		   if(newFather.equals(preFather)){
			//如果父类别没有修改，那么不用更新类别structure
		   }else{
			//如果父类别修改了，那么更新类别structure,原父类别中删掉该类别，新父类别中增加该类别
			UpdateClassificationStructure classStructure=new UpdateClassificationStructure();
			ClassificationVO preClassificationVO=voTOpo.classificationPOtoVO(preClassificationPO);
			classStructure.deleteSon(preClassificationVO);
			classStructure.addSon(vo);
			}
		}
		ClassificationPO classPO=voTOpo.classificationVOtoPO(vo);
		classificationData.update(classPO);
		return true;
	}

}
