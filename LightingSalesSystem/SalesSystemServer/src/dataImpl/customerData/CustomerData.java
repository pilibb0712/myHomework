package dataImpl.customerData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import assistant.utility.ReadAndWriteTxtUtility;
import dataService.customerDataService.CustomerDataService;
import po.CustomerPO;
import vo.CustomerVO;

/**
 * 
 * @author 王宁一 161250140
 * @version 2017.11.21
 */

public class CustomerData implements CustomerDataService, Serializable {

	
	private static String ROOT_PATH="data"+File.separator+"customer"+File.separator;//根目录
	public static String RECORD_PATH="record.txt";//只存ID，方便查找
	
	/**
	 * 新增客户
	 * @see dataService.customerDataService.CustomerDataService#insert(po.CustomerPO)
	 * @param CustomerPO po需要新增的客户
	 * @return boolean true表示成功
	 */
	@Override
	public boolean insert(CustomerPO po){
		// TODO Auto-generated method stub		
		String Id=po.getId();
		if(findRecord(Id)) {//客户已存在，就不能插入
			return false;			
		}
		creatRecord(Id);//创建记录
		
		String path=ROOT_PATH+po.getId()+".ser";
		ObjectOutputStream oStream;
		
		try {
			oStream=new ObjectOutputStream(new FileOutputStream(path));
			oStream.writeObject(po);
			oStream.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 删除一个客户，要求姓名和ID完全匹配
	 * @see dataService.customerDataService.CustomerDataService#delete(java.lang.String, java.lang.String)
	 * @param Id
	 * @param name
	 * @return boolean true表示成功
	 */
	@Override
	public boolean delete(String Id, String name){
		// TODO Auto-generated method stub
		if(!findRecord(Id)) {//不存在相应客户
			return false;
		}
		CustomerPO po=find(Id).get(0);
		if(po.getName().equals(name)) {
			//先删除记录里的Id
			ArrayList<String> content=new ArrayList<String>();
			File recordFile=new File(ROOT_PATH+RECORD_PATH);
			try {
				FileInputStream inputStream=new FileInputStream(recordFile);
				BufferedReader bf=new BufferedReader(new InputStreamReader(inputStream));
				String str=null;
				while((str=bf.readLine())!=null) {
					if(!str.equals(Id)) {
						content.add(str);
					}
				}
				
				FileWriter fw=new FileWriter(recordFile);
				fw.write("");
				fw.flush();
				
				inputStream.close();
				bf.close();
				fw.close();
				for(int i=0;i<content.size();i++) {
					creatRecord(content.get(i));
				}
								
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			//删除对象
			Id+=".ser";
			File dir=new File(ROOT_PATH);
			String[] filesName=dir.list();
			for(int i=0;i<filesName.length;i++) {
				if(Id.equals(filesName[i])) {
					File delFile=new File(ROOT_PATH+Id);
					delFile.delete();
					return true;
				}
			}
		}
		
		return false;
	}

	/**
	 * 更新一个客户的信息
	 * @see dataService.customerDataService.CustomerDataService#update(po.CustomerPO)
	 * @param po
	 * @return boolean true表示成功
	 */
	@Override
	public boolean update(CustomerPO po) {
		// TODO Auto-generated method stub
		String Id=po.getId();
		if(!findRecord(Id)) {
			return false;
		}else {
			String path=ROOT_PATH+Id+".ser";			
			File delFile=new File(path);
			delFile.delete();
			
			ObjectOutputStream oStream;			
			try {
				oStream = new ObjectOutputStream(new FileOutputStream(path));
				oStream.writeObject(po);
				oStream.close();
				return true;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return false;
			}
		}
	}

	/**
	 * 查询客户信息，支持关键字查找
	 * @see dataService.customerDataService.CustomerDataService#find(java.lang.String)
	 * @param name
	 * @return ArrayList<CustomerPO>
	 */
	@Override
	public ArrayList<CustomerPO> find(String name){//关键字查找，含Id
		// TODO Auto-generated method stub
		//1. 判断是否是id，是的话调用下面的idFind
		//2. 关键字
		//3. 空, 全部
		ArrayList<CustomerPO> result=new ArrayList<CustomerPO>();
		boolean none=true;
		int idLength=6;//id 6 位
		if(name.length()==idLength) {
			if(idFind(name)!=null) {
				result.add(idFind(name));
				return result;
			}
			return null;
			
		}else {//关键字
			if(name.length()==0) {
				result=getAll();
				return result;
			}else {
				File folder=new File(ROOT_PATH);
				String[] filesName=folder.list();
				CustomerPO po=null;
				for(int i=0;i<filesName.length;i++) {
					if(filesName[i].contains(".ser")) {
						po=readInfo(ROOT_PATH+filesName[i]);
						if(po.getName()!=null&&po.getName().contains(name)) {//包含关键词
							result.add(po);
							none=false;
						}
					}
				}
				
				if(none) {
					return null;
				}else {
					return result;
				}
			}			
		}
	}

	/**
	 * 根据Id查找客户
	 * @param Id
	 * @return boolean true表示成功
	 */
	private CustomerPO idFind(String Id) {//通过Id查找
		File folder=new File(ROOT_PATH);
		String[] filesName=folder.list();
		String target=Id+".ser";
		for(int i=0;i<filesName.length;i++) {
			if(target.equals(filesName[i])) {
				return readInfo(ROOT_PATH+filesName[i]);
			}
		}
		return null;
	}
	
	/**
	 * 创建客户Id记录，方便查询
	 * @param Id
	 */
	private void creatRecord(String Id) {//record只存ID
		String path=ROOT_PATH+RECORD_PATH;
		File file=new File(path);
		try {
			if(!file.exists()) {
				file.createNewFile();
			}
			String record=Id+System.lineSeparator();
			ReadAndWriteTxtUtility.writeTxtAppend(path, record);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 根据Id查询客户是否存在
	 * @param Id
	 * @return boolean, true表示成功
	 */
	private boolean findRecord(String Id) {//判断客户是否已存在，true表示已存在
		String path=ROOT_PATH+RECORD_PATH;
		File file=new File(path);
		try {
			if(!file.exists()) {
				return false;
			}
			FileInputStream inputStream=new FileInputStream(file);
			BufferedReader bf=new BufferedReader(new InputStreamReader(inputStream));
			
			String str=null;
			while((str=bf.readLine())!=null) {
				if(str.equals(Id)) {//找到记录
					inputStream.close();
					bf.close();
					return true;
				}
			}
			inputStream.close();
			bf.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 读取（还原）序列化对象信息
	 * @param path
	 * @return CustomerPO对象
	 */
	private CustomerPO readInfo(String path) {
		CustomerPO po=null;
		ObjectInputStream iStream;
		try {
			iStream=new ObjectInputStream(new FileInputStream(path));
			po=(CustomerPO)iStream.readObject();
			iStream.close();
			return po;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 得到所有客户信息
	 * @see dataService.customerDataService.CustomerDataService#getAll()
	 * @return ArrayList<CustomerPO>
	 */
	@Override
	public ArrayList<CustomerPO> getAll() {
		// TODO Auto-generated method stub
		ArrayList<CustomerPO> result=new ArrayList<CustomerPO>();
		
		File folder=new File(ROOT_PATH);
		String[] filesName=folder.list();
		CustomerPO po=null;
		for(int i=0;i<filesName.length;i++) {
			if(filesName[i].contains(".ser")) {
				System.out.println(filesName[i]);
				po=readInfo(ROOT_PATH+filesName[i]);
				result.add(po);
			}				
		}
		return result;
	}
	
//	public static void main(String[] args) {
//		CustomerData data=new CustomerData();
//		CustomerPO t1=new CustomerPO();
//		t1.setId("i00001");
//		t1.setName("t1");
//		CustomerPO t2=new CustomerPO();
//		t2.setId("o00002");
//		t2.setName("t2");
//		data.insert(t1);
//		data.insert(t2);
//		ArrayList<CustomerPO> test=data.getAll();
//		for(int i=0;i<test.size();i++) {
//			System.out.println(test.get(i).getName());
//		}
//	}
}


