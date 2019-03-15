package blImpl.commodityBl;



import java.util.ArrayList;

import dataImpl.commodityData.GoodsClassificationData;
import po.ClassificationPO;
import vo.ClassificationTreeVO;

//2017.11.06  Beibei Zhang
public class ClassificationTree {

	private ArrayList<String[]> layers=new ArrayList<String[]>();
	//每层的类别编号+"-"+名称
	private ArrayList<String[]> connections=new ArrayList<String[]>();
	//相连两层类别的父子关系，上一层的父对应的下一层的n个子，中间用“-”连接
	//比如connection.get(0)表示layer.get(0)与layer.get(1)的对应关系
    //connection.get(0)的第0号元素如果是"0-1-2"代表layer.get(0)的第0号分类与第1层的0、1、2号分类为父子关系
	//connections的最后一层必然全是“”

	public void getLayersAndConnections(ArrayList<ClassificationPO> fathers){
		//先测，测完了之后将public换成private
		if(fathers.size()==0){

		}else{
		GoodsClassificationData classificationData=new GoodsClassificationData();
		//be used to find the sonPO

		String[] inforOfFathers=new String[fathers.size()];
		for(int i=0;i<=fathers.size()-1;i++){
			ClassificationPO father=fathers.get(i);
			String fatherName=father.getName();
			String fatherNumber=father.getNumber();
			inforOfFathers[i]=fatherNumber+"-"+fatherName;
		}
		layers.add(inforOfFathers);
		ArrayList<ClassificationPO> sons=new ArrayList<ClassificationPO>();
		ArrayList<String> connectionsLis=new ArrayList<String>();
		int sonIndex=0;
		for(int i=0;i<=fathers.size()-1;i++){
			ClassificationPO father=fathers.get(i);
			ArrayList<String> sonClassifications=father.getSonClassifacation();
			String connection="";
			//connection由这个父的所有子在该层的序号组成，用“-”隔开
			//后面显示的时候就可以得到第i层的所有元素的子在第i+1层的序号
			if(sonClassifications==null){

			}else{
			for(int j=0;j<=sonClassifications.size()-1;j++){
				String son=sonClassifications.get(j);
				//son:sonNumber+"-"+sonName
				String[] sonInfors=son.split("-");
				String sonNumber=sonInfors[0];
				ClassificationPO sonPO=classificationData.find(sonNumber);
				sons.add(sonPO);
				connection=connection+String.valueOf(sonIndex)+"-";
				//connection由这个父的所有子在该层的序号组成，用“-”隔开
				sonIndex++;
			}
			}
			//如果该类别下没有子类，则connection="",到时候在界面层遇到""便知该号元素下无子类
			connectionsLis.add(connection);
		}
		String[] connectionsArr=new String[connectionsLis.size()];
		for(int i=0;i<=connectionsLis.size()-1;i++){
			connectionsArr[i]=connectionsLis.get(i);
		}
		connections.add(connectionsArr);
		getLayersAndConnections(sons);
		//递归设置树结构
		}
	}

	public ClassificationTreeVO setTreeLayerAndConnection(ClassificationTreeVO tree){
		//先测，测完了之后将public换成private
		GoodsClassificationData classificationData=new GoodsClassificationData();
		ArrayList<ClassificationPO> allClassifications=classificationData.finds();
		ArrayList<ClassificationPO> roots=new ArrayList<ClassificationPO>();
		ArrayList<ClassificationPO> fathers=new ArrayList<ClassificationPO>();
		ArrayList<ClassificationPO> sons=new ArrayList<ClassificationPO>();
		for(int i=0;i<=allClassifications.size()-1;i++){
			ClassificationPO classification=allClassifications.get(i);
			String father=classification.getFatherClassification();
			if(father==null){
				roots.add(classification);
			}
			}
        layers.clear();
        connections.clear();
        getLayersAndConnections(roots);
        tree.setClassificationLayer(layers);
        tree.setSonFatherConnection(connections);
		return tree;
	}

	public ClassificationTreeVO getTree(){
        ClassificationTreeVO classificationTree=new ClassificationTreeVO();
        classificationTree=setTreeLayerAndConnection(classificationTree);
		return classificationTree;
	}

}
