/**
 * 出入库情况数据包
 * @author BeibeiZhang
 * @version 2017.11.26
 *
 */
package dataImpl.commodityData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import assistant.utility.Date;
import dataService.commodityDataService.StockInOutRecordService;
import po.StockInOutPO;


public class StockInOutRecord implements StockInOutRecordService{
    private ArrayList<StockInOutPO> allStockRecords=new ArrayList<StockInOutPO>();
    private final String lastFileName="data/stocks";
 /*   public ArrayList<StockInOutPO> finds(){
    	ArrayList<StockInOutPO> stocks=new ArrayList<StockInOutPO>();
    	readStockInOutPOs();
    	stocks=allStockRecords;
    	return stocks;
    }//just for test
*/
    /**
     * public ArrayList<StockInOutPO> finds(String goodsID)
     * @param1 String:goodsID
     * @return ArrayList<StockInOutPO>:all the stockRecords of theGoods
     * @version 2017.11.28
     */
	@Override
	public ArrayList<StockInOutPO> finds(String goodsID) {
		//get all stockRecords of theGoods
		// TODO Auto-generated method stub
		readStockInOutPOs();
		ArrayList<StockInOutPO> stocksOfGood=new ArrayList<StockInOutPO>();
		for(int i=0;i<=allStockRecords.size()-1;i++){
			StockInOutPO stock=allStockRecords.get(i);
			String id=stock.getGoodsID();
			if(id.equals(goodsID)){
				stocksOfGood.add(stock);
			}
		}
		return stocksOfGood;
	}

	/**
     * public ArrayList<StockInOutPO> finds(Date beginTime, Date endTime)
     * @param1 Date:beginTime
     * @param2 Date:endTime
     * @return ArrayList<StockInOutPO>:all the stockRecords during the time
     * @version 2017.11.28
     */
	@Override
	public ArrayList<StockInOutPO> finds(Date beginTime, Date endTime) {
		// TODO Auto-generated method stub
		readStockInOutPOs();
		ArrayList<StockInOutPO> stocksDuringDate=new ArrayList<StockInOutPO>();
		for(int i=0;i<=allStockRecords.size()-1;i++){
			StockInOutPO stock=allStockRecords.get(i);
			Date time=stock.getTime();
			if(time.isInDateArea(beginTime, endTime)){
				stocksDuringDate.add(stock);
			}
		}
		return stocksDuringDate;
	}

	@Override
	public boolean insert(StockInOutPO po) {
		// TODO Auto-generated method stub
		readStockInOutPOs();
		allStockRecords.add(po);
		writeStockInOutPOs();
		return false;
	}
	private void readStockInOutPOs(){
		allStockRecords.clear();
		File lastFile=new File(lastFileName);
		lastFile.mkdirs();
		File[] stockFiles=lastFile.listFiles();
		if(stockFiles.length==0){

		}else{
			for(int i=0;i<=stockFiles.length-1;i++){
				try{
				File stockFile=stockFiles[i];
				FileInputStream fis=new FileInputStream(stockFile);
				ObjectInputStream ois=new ObjectInputStream(fis);
				StockInOutPO stock=(StockInOutPO)ois.readObject();
				allStockRecords.add(stock);
				ois.close();
				fis.close();
				}catch(ClassNotFoundException e1){
					e1.printStackTrace();
				}catch(FileNotFoundException e2){
					e2.printStackTrace();
				}catch(IOException e3){
					e3.printStackTrace();
				}

			}
		}
	}
	private void writeStockInOutPOs(){
		File lastFile=new File(lastFileName);
		lastFile.mkdirs();
		File[] stockFiles=lastFile.listFiles();
		if(stockFiles.length==0){

		}else{
			for(int i=0;i<=stockFiles.length-1;i++){
				stockFiles[i].delete();
			}
		}
		String lastFileAbsolutePath=lastFile.getAbsolutePath();
		for(int i=0;i<=allStockRecords.size()-1;i++){
			StockInOutPO stock=allStockRecords.get(i);
			String time=stock.getTime().getDate();
			String stockFileName=time.replace(':', '-');
			//以时刻作为stock文件的名字，不可能有同一秒同时出入库的情况，所以不会出现冲突
			//注：文件名中不能出现":"
			//注：文件名这样命名方便按出入库先后排序（序列化文件会自动按时间排序的）
			try{
			File stockFile=new File(lastFileAbsolutePath+"/"+stockFileName);
			stockFile.createNewFile();
			FileOutputStream fos=new FileOutputStream(stockFile);
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(stock);
			oos.close();
			fos.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}

}
