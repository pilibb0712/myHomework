package blMocks.commodityBlMocks;

import blImpl.commodityBl.UpdateClassificationStructure;
import vo.ClassificationVO;

public class MockUpdateClassificationStructure extends UpdateClassificationStructure{
	public boolean addSon(ClassificationVO sonClassification){
		return true;
	}
	public boolean deleteSon(ClassificationVO sonClassification){
		return true;
	}

}
