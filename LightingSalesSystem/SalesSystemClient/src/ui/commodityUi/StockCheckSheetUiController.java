package ui.commodityUi;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.utility.Date;
import blService.commodityBlService.StockCheckBlService;
import blStubs.commodityBlStubs.StockCheckService_Stub;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import rmi.remoteHelper.CommodityRemoteHelper;
import ui.uiAssistants.Fonts;
import vo.StockCheckSheetVO;

public class StockCheckSheetUiController {
	@FXML private VBox goodsName;
	@FXML private VBox goodsID;
	@FXML private VBox productionDate;
	@FXML private VBox stockTime;
	@FXML private VBox stockInAmount;
	@FXML private VBox costPrice;
	@FXML private VBox stockOutAmount;
	@FXML private VBox sellingPrice;

	@FXML private VBox inAllLabel;
	@FXML private VBox inAllOverAmount;
	@FXML private VBox inAllOverMoney;
	@FXML private VBox inAllLessAmount;
	@FXML private VBox inAllLessMoney;



	private Stage currentStage;
	public void setStage(Stage stage){
		currentStage=stage;
	}
	public void initStockCheckBill(Date startTime,Date endTime){
		//库存审查表的界面显示
			try{
				StockCheckBlService stockCheckSer=CommodityRemoteHelper.getInstance().getStockCheckBlService();
				StockCheckSheetVO stockCheckSheet=stockCheckSer.stockCheckSheet(startTime, endTime);
				ArrayList<String[]> stockSheetContents=stockCheckSheet.getStockCheckSheet();
				Label buzinessInAllLabel=new Label("进销货合计");
			    Label notBuzinessInAllLabel=new Label("报损（溢）合计");
			    Text stockInAmountAll=new Text();
				Text stockInMoneyAll=new Text();
				Text stockOutAmountAll=new Text();
				Text stockOutMoneyAll=new Text();
				Text addAmountAll=new Text();
				Text addMoneyAll=new Text();
				Text deleteAmountAll=new Text();
				Text deleteMoneyAll=new Text();
				for(int i=0;i<=stockSheetContents.size()-1;i++){
					if(i==stockSheetContents.size()-1){
						//倒数第一行是非交易向合计的元素

						notBuzinessInAllLabel.setFont(Fonts.COMMODITY_FONT);
						inAllLabel.getChildren().add(notBuzinessInAllLabel);


						String[] notBuzinessInAll=stockSheetContents.get(i);
						addAmountAll.setText("总报溢量："+notBuzinessInAll[0]);
						addAmountAll.setFont(Fonts.COMMODITY_FONT);
						inAllOverAmount.getChildren().add(addAmountAll);

						addMoneyAll.setText("总报溢金额："+notBuzinessInAll[1]);
						addMoneyAll.setFont(Fonts.COMMODITY_FONT);
						inAllOverMoney.getChildren().add(addMoneyAll);

						deleteAmountAll.setText("总报损量："+notBuzinessInAll[2]);
						deleteAmountAll.setFont(Fonts.COMMODITY_FONT);
						inAllLessAmount.getChildren().add(deleteAmountAll);

						deleteMoneyAll.setText("总报损金额："+notBuzinessInAll[3]);
						deleteMoneyAll.setFont(Fonts.COMMODITY_FONT);
						inAllLessMoney.getChildren().add(deleteMoneyAll);


					}else if(i==stockSheetContents.size()-2){
						//倒数第二行是交易向合计的元素
						buzinessInAllLabel.setFont(Fonts.COMMODITY_FONT);
						inAllLabel.getChildren().add(buzinessInAllLabel);


						String[] buzinessInAll=stockSheetContents.get(i);
						stockInAmountAll.setText("总入库量："+buzinessInAll[0]);
						stockInAmountAll.setFont(Fonts.COMMODITY_FONT);
						inAllOverAmount.getChildren().add(stockInAmountAll);

						stockInMoneyAll.setText("总入库金额："+buzinessInAll[1]);
						stockInMoneyAll.setFont(Fonts.COMMODITY_FONT);
						inAllOverMoney.getChildren().add(stockInMoneyAll);

						stockOutAmountAll.setText("总出库量："+buzinessInAll[2]);
						stockOutAmountAll.setFont(Fonts.COMMODITY_FONT);
						inAllLessAmount.getChildren().add(stockOutAmountAll);

						stockOutMoneyAll.setText("总出库金额："+buzinessInAll[3]);
						stockOutMoneyAll.setFont(Fonts.COMMODITY_FONT);
						inAllLessMoney.getChildren().add(stockOutMoneyAll);
					}else{
						String[] oneInfor=stockSheetContents.get(i);
						//contents[7]:name ID time stock-in-amount cost-money stock-out-amount selling-money
						Text name=new Text();
						name.setText(oneInfor[0]);
						name.setFont(Fonts.BILL_FONT);
						goodsName.getChildren().add(name);

						Text id=new Text();
						id.setText(oneInfor[1]);
						id.setFont(Fonts.BILL_FONT);
						goodsID.getChildren().add(id);

						Text productDate=new Text();
						//有的时候会是null，比如遇到库存报溢、报损而进行出入库的时候
						productDate.setText(oneInfor[2]);
						productDate.setFont(Fonts.BILL_FONT);
						productionDate.getChildren().add(productDate);

						Text time=new Text();
						time.setText(oneInfor[3]);
						time.setFont(Fonts.BILL_FONT);
						stockTime.getChildren().add(time);

						Text inAmount=new Text();
						//当是出库时，入库会是null
						if(oneInfor[4]==null){
							inAmount.setText("             ");
						}else{
						inAmount.setText(oneInfor[4]+"             ");
						inAmount.setFont(Fonts.BILL_FONT);
						}
						stockInAmount.getChildren().add(inAmount);

						Text cost=new Text();
						//当是出库时，cost会是null
						if(oneInfor[5]==null){
							cost.setText("              ");
						}else{
						cost.setText(oneInfor[5]+"           ");
						cost.setFont(Fonts.BILL_FONT);
						}
						costPrice.getChildren().add(cost);

						Text outAmount=new Text();
						//当是入库时，出库会是null

						if(oneInfor[6]==null){
							outAmount.setText("             ");
						}else{
							int amount=Integer.parseInt(oneInfor[6]);
							outAmount.setText(String.valueOf((-1)*amount)+"           ");
							//把负数改成整数

						}
						outAmount.setFont(Fonts.BILL_FONT);
						stockOutAmount.getChildren().add(outAmount);

						Text selling=new Text();
						//当是入库时，selling会是null
						if(oneInfor[7]==null){
							selling.setText("              ");
						}else{
						selling.setText(oneInfor[7]);
						selling.setFont(Fonts.BILL_FONT);
						}
						sellingPrice.getChildren().add(selling);
					}
				}
						}catch(RemoteException e){
				e.printStackTrace();
			}
		}
		@FXML protected void confirmStockCheck(){
			backToStockCheckUi();
		}
		private void backToStockCheckUi(){
			StockCheckUiStarter starter=new StockCheckUiStarter();
			starter.initStockCheckUi(currentStage);
		}
}
