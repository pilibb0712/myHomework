package blMocks.commodityBlMocks;

import java.util.ArrayList;

import blImpl.commodityBl.ClassificationTree;
import vo.ClassificationTreeVO;

public class MockClassificationTree extends ClassificationTree {
	public ClassificationTreeVO setTreeLayerAndConnection(ClassificationTreeVO tree){
		ArrayList<String[]> treeLayers=new ArrayList<String[]>();
		String[] thefirstLayer={"A 1"};
		String[] thesecondLayer={"Aa 11","Ab 12"};
		treeLayers.add(thefirstLayer);
		treeLayers.add(thesecondLayer);
		ArrayList<String[]> layerConnection=new ArrayList<String[]>();
		String[] connection1={"0 1"};
		layerConnection.add(connection1);
		tree.setClassificationLayer(treeLayers);
		tree.setSonFatherConnection(layerConnection);
		return tree;
	}
	
	public ClassificationTreeVO getTree(){
		ClassificationTreeVO tree=new ClassificationTreeVO();
		tree=setTreeLayerAndConnection(tree);
		return tree;
	}
}
