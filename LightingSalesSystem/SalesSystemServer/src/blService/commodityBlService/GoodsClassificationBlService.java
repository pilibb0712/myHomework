/**
 * 仓库管理员进行商品类别管理，包括商品分类的增删改
 * @author BeibeiZhang
 * @version 2017.11.19
 *
 */
package blService.commodityBlService;

import java.rmi.Remote;

import java.rmi.RemoteException;

import vo.*;

/////do some change 2017.10.30
public interface GoodsClassificationBlService extends Remote{
	public ClassificationTreeVO getClassificationTree() throws RemoteException;
	//获得类别树对象，以呈现在类别管理初始界面
	public boolean ifClassificationExist(String classificationTag) throws RemoteException;
	//检验某个类别是否已存在
	public boolean addNewClassification(ClassificationVO vo)throws RemoteException;
	//增加新的根类别
	public ClassificationVO classificationInfor(String classificationNum) throws RemoteException;
	//获取类别对象，以了解该类别的信息
	public boolean addClassification(ClassificationVO vo) throws RemoteException;
	//增加新的子类别
	public boolean deleteClassification(ClassificationVO vo) throws RemoteException;
	//删除某类别
	public boolean modifyClassificationInfor(ClassificationVO vo) throws RemoteException;
	//修改某类别信息，只能修改类别名称与其父类别
}
