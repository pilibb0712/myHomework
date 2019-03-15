package blDrivers.commodityBlDrivers;

import java.util.ArrayList;

import blImpl.commodityBl.ClassificationTree;
import vo.ClassificationTreeVO;

public class ClassificationTree_Driver {
public void drive(ClassificationTree tree){
	//test setTreeLayerAndConnection(ClassificationTreeVO tree);
	ClassificationTreeVO treeVO=new ClassificationTreeVO();
	treeVO=tree.setTreeLayerAndConnection(treeVO);
	ArrayList<String[]> treeLayers=treeVO.getClassificationLayer();
	ArrayList<String[]> layerConnections=treeVO.getSonFatherConnection();
	if(treeLayers.size()==0){
		System.out.println("There are no classifications.");
	}else{
		System.out.println("The classifications are:");
		for(int i=0;i<=treeLayers.size()-1;i++){
			String[] layer=treeLayers.get(i);
			for(int j=0;j<=layer.length-1;j++){
				System.out.print(layer[j]+"    ");
			}
			System.out.println();
		}
	}
	if(layerConnections.size()==0){
		System.out.println("There are no classifications or only one layer of classifications.");
	}else{
		System.out.println("The connection of layers are:");
		for(int i=0;i<=layerConnections.size()-1;i++){
			String[] connection=treeLayers.get(i);
			for(int j=0;j<=connection.length-1;j++){
				System.out.print(connection[j]+"    ");
			}
			System.out.println();
		}
	}
	
	//test getTree()
	treeVO=tree.getTree();
	treeLayers=treeVO.getClassificationLayer();
	layerConnections=treeVO.getSonFatherConnection();
	if(treeLayers.size()==0){
		System.out.println("There are no classifications.");
	}else{
		System.out.println("The classifications are:");
		for(int i=0;i<=treeLayers.size()-1;i++){
			String[] layer=treeLayers.get(i);
			for(int j=0;j<=layer.length-1;j++){
				System.out.print(layer[j]+"    ");
			}
			System.out.println();
		}
	}
	if(layerConnections.size()==0){
		System.out.println("There are no classifications or only one layer of classifications.");
	}else{
		System.out.println("The connection of layers are:");
		for(int i=0;i<=layerConnections.size()-1;i++){
			String[] connection=treeLayers.get(i);
			for(int j=0;j<=connection.length-1;j++){
				System.out.print(connection[j]+"    ");
			}
			System.out.println();
		}
	}
	
}
}
