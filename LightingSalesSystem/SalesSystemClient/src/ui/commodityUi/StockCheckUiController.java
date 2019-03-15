/**
 * 仓库管理员进行库存审查的界面
 * @author BeibeiZhang
 * @version 2017.11.19
 *
 */
package ui.commodityUi;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import assistant.utility.Date;
import blStubs.commodityBlStubs.StockCheckService_Stub;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ui.mainUi.wareKeeperMainUi.WareKeeperMainUiStarter;
import ui.uiAssistants.StageSize;
import vo.StockCheckSheetVO;

public class StockCheckUiController {
	@FXML private TextField startyear;
	@FXML private TextField startmonth;
	@FXML private TextField startday;
	@FXML private TextField starthour;
	@FXML private TextField startminute;
	@FXML private TextField endyear;
	@FXML private TextField endmonth;
	@FXML private TextField endday;
	@FXML private TextField endhour;
	@FXML private TextField endminute;



	private Stage currentStage;


	public void setStage(Stage stage){
		currentStage=stage;
	}
	@FXML protected void confirmTime(){
		boolean startTimeFor=true;
		boolean endTimeFor=true;
		boolean endOverStart=true;
		boolean nowOverEnd=true;
		Date start=null;
		Date end=null;
		String startTime=startyear.getText()+"-"+startmonth.getText()+"-"+startday.getText()+"_"+starthour.getText()+":"+startminute.getText()+":"+"00";
		//秒数默认为00
		 try{
	      		start=new Date(startTime);
	      		}catch(ParseException e){
	      			//startTime不是yyyy-MM-dd_HH:mm:ss格式
	      			startTimeFor=false;
	      			Alert startTimeErr=new Alert(Alert.AlertType.ERROR,"开始时间的输入格式错误（请参考默认值）！");
	          	    startTimeErr.showAndWait();
	      		}
		 String endTime=endyear.getText()+"-"+endmonth.getText()+"-"+endday.getText()+"_"+endhour.getText()+":"+endminute.getText()+":"+"00";
		 try{
	      		end=new Date(endTime);
	      		}catch(ParseException e){
	      			//endTime不是yyyy-MM-dd_HH:mm:ss格式
	      			endTimeFor=false;
	      			Alert endTimeErr=new Alert(Alert.AlertType.ERROR,"结束时间的输入格式错误（请参考默认值）！");
	          	    endTimeErr.showAndWait();
	      		}
		 if(startTimeFor&&endTimeFor){
			 int over=endTime.compareTo(startTime);
			 //0:相等  1:当前日期较晚  -1:当前日期较早
			 if(over>0){

			 }else{
				 endOverStart=false;
				 Alert startOverEndErr=new Alert(Alert.AlertType.ERROR,"开始时间晚于（等于）结束时间！");
	          	 startOverEndErr.showAndWait();
			 }
		 }
		 if(startTimeFor&&endTimeFor&&endOverStart){
			 Date current=new Date();
			 int over2=current.compareTo(end);
			//0:相等  1:当前日期较晚  -1:当前日期较早
			 if(over2>0){

			 }else{
				 nowOverEnd=false;
				 Alert startOverEndErr=new Alert(Alert.AlertType.ERROR,"结束时间晚于（等于）当前时间！");
	          	 startOverEndErr.showAndWait();
			 }
		 }
		 if(startTimeFor&&endTimeFor&&endOverStart&&nowOverEnd){
			 initStockCheckBillUi(start,end);
		 }

	}
	private void initStockCheckBillUi(Date start,Date end){
		StockCheckSheetUiStarter starter=new StockCheckSheetUiStarter();
		starter.initStockCheckSheetUi(currentStage, start, end);
	}
	@FXML protected void returnBack(){
		WareKeeperMainUiStarter starter=new WareKeeperMainUiStarter();
        starter.wareKeeperMainUi(currentStage, null, null);
	}
}
