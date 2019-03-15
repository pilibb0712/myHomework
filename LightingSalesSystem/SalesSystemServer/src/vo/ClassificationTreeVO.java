package vo;

import java.io.Serializable;
import java.util.ArrayList;

public class ClassificationTreeVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8259693120550886635L;
	private ArrayList<String[]> classificationLayer=new ArrayList<String[]>();
    //classificationLayer每一个元素为一个String数组
    //该数组存放的是该层的所有类别的编号加名称的String,以"-"隔开
	private ArrayList<String[]> sonFatherConnection=new ArrayList<String[]>();
    //sonFatherConnection每一个元素为一个String数组
    //该数组存放的是对应的层父类与下一层子类的对应关系
    //例：String[] sonToFather=sonFatherConnection.at(0);
    //该数组存放的是第一层元素与第二层元素的对应关系
    //而sonToFather[0]="012"表示
    //第一层中第一个父类对应第二层中位置为0、1、2的三个子类
    public void setClassificationLayer(ArrayList<String[]> classiLayer){
    classificationLayer=classiLayer;
    }
    public void setSonFatherConnection(ArrayList<String[]> connection){
    sonFatherConnection=connection;
    }
    public ArrayList<String[]> getClassificationLayer(){
    return classificationLayer;
    }
    public ArrayList<String[]> getSonFatherConnection(){
    return sonFatherConnection;
    }
}
