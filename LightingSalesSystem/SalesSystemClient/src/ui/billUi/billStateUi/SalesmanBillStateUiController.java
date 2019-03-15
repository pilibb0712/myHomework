package ui.billUi.billStateUi;

import java.rmi.RemoteException;
import java.util.ArrayList;
import assistant.type.BillStateEnum;
import blService.billService.billStateBlService.SalesmanBillStateBlService;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import rmi.remoteHelper.BillStateRemoteHelper;
import rmi.remoteHelper.RemoteHelperFactory;
import ui.billUi.viewBillUi.ViewSalesmanBillsUiStarter;
import ui.mainUi.salesmanMainUi.SalesmanMainUiStarter;
import ui.salesUi.NewSalesmanBillsUiStarter;
import ui.uiAssistants.StageSize;
import vo.PurchaseBillVO;
import vo.PurchaseReturnBillVO;
import vo.SalesBillVO;
import vo.SalesReturnBillVO;
import vo.UserInfoVO;

/**
 * 销售进货员单据状态界面controller
 * @author guxinyu
 * @version 2017.12.19
 *
 */
public class SalesmanBillStateUiController {
	private static UserInfoVO user;
	
	@FXML
	private HBox billHBox;
	
	private BillStateRemoteHelper remoteHelper=RemoteHelperFactory.getBillStateRemoteHelper();

	private SalesmanBillStateBlService billStateBl=remoteHelper.getSalesmanBillStateBlService();
	
	public void init(UserInfoVO u){
		user=u;
	}
	
	private void fourBox(BillStateEnum state){
		billHBox.getChildren().clear();
		
		Separator sep1=new Separator();
		sep1.setOrientation(Orientation.VERTICAL);
		Separator sep2=new Separator();
		sep2.setOrientation(Orientation.VERTICAL);
		Separator sep3=new Separator();
		sep3.setOrientation(Orientation.VERTICAL);
		Separator sep4=new Separator();
		sep4.setOrientation(Orientation.VERTICAL);
		
		//外部VBox
		VBox purchaseOuterBox=new VBox();
		VBox purchaseReOuterBox=new VBox();
		VBox salesOuterBox=new VBox();
		VBox salesReOuterBox=new VBox();
		
		//大小
		int boxWidth=290;
		purchaseOuterBox.setPrefSize(boxWidth, StageSize.STAGE_HEIGHT);
		purchaseOuterBox.setMaxSize(boxWidth, StageSize.STAGE_HEIGHT);
		purchaseOuterBox.setMinSize(boxWidth, StageSize.STAGE_HEIGHT);
		purchaseReOuterBox.setPrefSize(boxWidth, StageSize.STAGE_HEIGHT);
		purchaseReOuterBox.setMaxSize(boxWidth, StageSize.STAGE_HEIGHT);
		purchaseReOuterBox.setMinSize(boxWidth, StageSize.STAGE_HEIGHT);
		salesOuterBox.setPrefSize(boxWidth, StageSize.STAGE_HEIGHT);
		salesOuterBox.setMaxSize(boxWidth, StageSize.STAGE_HEIGHT);
		salesOuterBox.setMinSize(boxWidth, StageSize.STAGE_HEIGHT);
		salesReOuterBox.setPrefSize(boxWidth, StageSize.STAGE_HEIGHT);
		salesReOuterBox.setMaxSize(boxWidth, StageSize.STAGE_HEIGHT);
		salesReOuterBox.setMinSize(boxWidth, StageSize.STAGE_HEIGHT);
		
		//内边距
		purchaseOuterBox.setPadding(new Insets(20,0,0,0));
		purchaseReOuterBox.setPadding(new Insets(20,0,0,0));
		salesOuterBox.setPadding(new Insets(20,0,0,0));
		salesReOuterBox.setPadding(new Insets(20,0,0,0));
		
		//子组件间距
		purchaseOuterBox.setSpacing(20);
		purchaseReOuterBox.setSpacing(20);
		salesOuterBox.setSpacing(20);
		salesReOuterBox.setSpacing(20);
		
		//子组件对齐方式
		purchaseOuterBox.setAlignment(Pos.TOP_CENTER);
		purchaseReOuterBox.setAlignment(Pos.TOP_CENTER);
		salesOuterBox.setAlignment(Pos.TOP_CENTER);
		salesReOuterBox.setAlignment(Pos.TOP_CENTER);
		
		//提示Label
		Label purchasePrompt=new Label("进货单");
		purchasePrompt.setFont(Font.font(29));
		Label purchaseRePrompt=new Label("进货退货单");
		purchaseRePrompt.setFont(Font.font(29));
		Label salesPrompt=new Label("销售单");
		salesPrompt.setFont(Font.font(29));
		Label salesRePrompt=new Label("销售退货单");
		salesRePrompt.setFont(Font.font(29));
		
		//内部ScollPane
		ScrollPane purchaseScroll=new ScrollPane();
	//	purchaseScroll.setHbarPolicy(ScrollBarPolicy.NEVER);
		ScrollPane purchaseReScroll=new ScrollPane();
	//	purchaseReScroll.setHbarPolicy(ScrollBarPolicy.NEVER);
		ScrollPane salesScroll=new ScrollPane();
	//	salesScroll.setHbarPolicy(ScrollBarPolicy.NEVER);
		ScrollPane salesReScroll=new ScrollPane();
	//	salesReScroll.setHbarPolicy(ScrollBarPolicy.NEVER);
		
		//ScrollPane大小固定
		purchaseScroll.setPrefSize(boxWidth, StageSize.STAGE_HEIGHT-156);
		purchaseScroll.setMaxSize(boxWidth, StageSize.STAGE_HEIGHT-156);
		purchaseScroll.setMinSize(boxWidth, StageSize.STAGE_HEIGHT-156);
		purchaseReScroll.setPrefSize(boxWidth, StageSize.STAGE_HEIGHT-156);
		purchaseReScroll.setMaxSize(boxWidth, StageSize.STAGE_HEIGHT-156);
		purchaseReScroll.setMinSize(boxWidth, StageSize.STAGE_HEIGHT-156);
		salesScroll.setPrefSize(boxWidth, StageSize.STAGE_HEIGHT-156);
		salesScroll.setMaxSize(boxWidth, StageSize.STAGE_HEIGHT-156);
		salesScroll.setMinSize(boxWidth, StageSize.STAGE_HEIGHT-156);
		salesReScroll.setPrefSize(boxWidth, StageSize.STAGE_HEIGHT-156);
		salesReScroll.setMaxSize(boxWidth, StageSize.STAGE_HEIGHT-156);
		salesReScroll.setMinSize(boxWidth, StageSize.STAGE_HEIGHT-156);
		
		//内部VBox
		VBox purchaseInnerBox=new VBox();
		VBox purchaseReInnerBox=new VBox();
		VBox salesInnerBox=new VBox();
		VBox salesReInnerBox=new VBox();
		
		purchaseScroll.setContent(purchaseInnerBox);
		purchaseReScroll.setContent(purchaseReInnerBox);
		salesScroll.setContent(salesInnerBox);
		salesReScroll.setContent(salesReInnerBox);
		
		purchaseInnerBox.setPrefSize(boxWidth, 650);
		purchaseReInnerBox.setPrefSize(boxWidth, 650);
		salesInnerBox.setPrefSize(boxWidth, 650);
		salesReInnerBox.setPrefSize(boxWidth, 650);
		
		purchaseInnerBox.setSpacing(10);
		purchaseReInnerBox.setSpacing(10);
		salesInnerBox.setSpacing(10);
		salesReInnerBox.setSpacing(10);
		
		purchaseInnerBox.setAlignment(Pos.TOP_CENTER);
		purchaseReInnerBox.setAlignment(Pos.TOP_CENTER);
		salesInnerBox.setAlignment(Pos.TOP_CENTER);
		salesReInnerBox.setAlignment(Pos.TOP_CENTER);
		
		purchaseOuterBox.getChildren().addAll(purchasePrompt,purchaseScroll);
		purchaseReOuterBox.getChildren().addAll(purchaseRePrompt,purchaseReScroll);
		salesOuterBox.getChildren().addAll(salesPrompt,salesScroll);
		salesReOuterBox.getChildren().addAll(salesRePrompt,salesReScroll);
		
		billHBox.getChildren().addAll(
				sep1, purchaseOuterBox, sep2, purchaseReOuterBox, sep3, salesOuterBox, sep4, salesReOuterBox);
		
		ArrayList<PurchaseBillVO> purchaseBills=null;
		ArrayList<PurchaseReturnBillVO> purchaseReturnBills=null;
		ArrayList<SalesBillVO> salesBills=null;
		ArrayList<SalesReturnBillVO> salesReturnBills=null;
		try {
			purchaseBills=billStateBl.getPurchaseBillList(state, user);
			purchaseReturnBills=billStateBl.getPurchaseReturnBillList(state, user);
			salesBills=billStateBl.getSalesBillList(state, user);
			salesReturnBills=billStateBl.getSalesReturnBillList(state, user);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		for(int i=0;i<salesBills.size();i++){
			System.out.println(salesBills.get(i).getId());
		}
		
		if(purchaseBills!=null){
			//显示进货单
			for(int i=0;i<purchaseBills.size();i++){
				PurchaseBillVO bill=purchaseBills.get(i);
			
				HBox each=new HBox();
				each.setAlignment(Pos.CENTER);
				each.setSpacing(10);
			
				Text billId=new Text(bill.getId());
				billId.setFont(Font.font(15));
			
				if(state==BillStateEnum.DENIED){
					Button edit=new Button("修改");
					edit.setFont(Font.font(15));
					Button delete=new Button("删除");
					delete.setFont(Font.font(15));
				
					each.getChildren().addAll(billId, edit, delete);
			
					purchaseInnerBox.getChildren().add(each);
			
					edit.setOnAction(oa ->{
						NewSalesmanBillsUiStarter starter=new NewSalesmanBillsUiStarter(user);
						starter.editPurchaseBill(bill);
					});
				
					delete.setOnAction(oa ->{
						try {
							if(billStateBl.deletePurchaseBill(bill)){
								purchaseInnerBox.getChildren().remove(each);
							}else{
								/**
								 * 报错
								 */
							}
						} catch (RemoteException e) {
							e.printStackTrace();
						}
					});	
				
				}else if(state==BillStateEnum.DONE){
					Button view=new Button("查看");
					view.setFont(Font.font(15));
			
					each.getChildren().addAll(billId, view);
			
					purchaseInnerBox.getChildren().add(each);
			
					view.setOnAction(oa ->{
						ViewSalesmanBillsUiStarter starter=new ViewSalesmanBillsUiStarter();
						starter.viewPurchaseBill(bill.getId());
					});	
				}
			}
		}
		
		if(purchaseReturnBills!=null){
			//显示进货退货单
			for(int i=0;i<purchaseReturnBills.size();i++){
				PurchaseReturnBillVO bill=purchaseReturnBills.get(i);
			
				HBox each=new HBox();
				each.setAlignment(Pos.CENTER);
				each.setSpacing(10);
			
				Text billId=new Text(bill.getId());
				billId.setFont(Font.font(15));
			
				if(state==BillStateEnum.DENIED){
					Button edit=new Button("修改");
					edit.setFont(Font.font(15));
					Button delete=new Button("删除");
					delete.setFont(Font.font(15));
				
					each.getChildren().addAll(billId, edit, delete);
			
					purchaseReInnerBox.getChildren().add(each);
			
					edit.setOnAction(oa ->{
						NewSalesmanBillsUiStarter starter=new NewSalesmanBillsUiStarter(user);
						starter.editPurchaseReturnBill(bill);
					});
				
					delete.setOnAction(oa ->{
						try {
							if(billStateBl.deletePurchaseReturnBill(bill)){
								purchaseReInnerBox.getChildren().remove(each);
							}else{
								/**
								 * 报错
								 */
							}
						} catch (RemoteException e) {
							e.printStackTrace();
						}
					});	
				}else if(state==BillStateEnum.DONE){
					Button view=new Button("查看");
					view.setFont(Font.font(15));
			
					each.getChildren().addAll(billId, view);
			
					purchaseReInnerBox.getChildren().add(each);
			
					view.setOnAction(oa ->{
						ViewSalesmanBillsUiStarter starter=new ViewSalesmanBillsUiStarter();
						starter.viewPurchaseReturnBill(bill.getId());
					});	
				}
			}
		}
		
		if(salesBills!=null){
			//显示销售单
			for(int i=0;i<salesBills.size();i++){
				SalesBillVO bill=salesBills.get(i);
			
				HBox each=new HBox();
				each.setAlignment(Pos.CENTER);
				each.setSpacing(10);
			
				Text billId=new Text(bill.getId());
				billId.setFont(Font.font(15));
			
				if(state==BillStateEnum.DENIED){
					Button edit=new Button("修改");
					edit.setFont(Font.font(15));
					Button delete=new Button("删除");
					delete.setFont(Font.font(15));
				
					each.getChildren().addAll(billId, edit, delete);
			
					salesInnerBox.getChildren().add(each);
			
					edit.setOnAction(oa ->{
						NewSalesmanBillsUiStarter starter=new NewSalesmanBillsUiStarter(user);
						starter.editSalesBill(bill);
					});
				
					delete.setOnAction(oa ->{
						try {
							if(billStateBl.deleteSalesBill(bill)){
								System.out.println("here");
								salesInnerBox.getChildren().remove(each);
							}else{
								/**
								 * 报错
								 */
							}
						} catch (RemoteException e) {
							e.printStackTrace();
						}
					});	
				}else if(state==BillStateEnum.DONE){
					Button view=new Button("查看");
					view.setFont(Font.font(15));
			
					each.getChildren().addAll(billId, view);
			
					salesInnerBox.getChildren().add(each);
			
					view.setOnAction(oa ->{
						ViewSalesmanBillsUiStarter starter=new ViewSalesmanBillsUiStarter();
						starter.viewSalesBill(bill.getId());
					});	
				}
			}
		}
		
		if(salesReturnBills!=null){
			//显示销售退货单
			for(int i=0;i<salesReturnBills.size();i++){
				SalesReturnBillVO bill=salesReturnBills.get(i);
			
				HBox each=new HBox();
				each.setAlignment(Pos.CENTER);
				each.setSpacing(10);
			
				Text billId=new Text(bill.getId());
				billId.setFont(Font.font(15));
			
				if(state==BillStateEnum.DENIED){
					Button edit=new Button("修改");
					edit.setFont(Font.font(15));
					Button delete=new Button("删除");
					delete.setFont(Font.font(15));
					
					each.getChildren().addAll(billId, edit, delete);
			
					salesReInnerBox.getChildren().add(each);
			
					edit.setOnAction(oa ->{
						NewSalesmanBillsUiStarter starter=new NewSalesmanBillsUiStarter(user);
						starter.editSalesReturnBill(bill);
					});
				
					delete.setOnAction(oa ->{
						try {
							if(billStateBl.deleteSalesReturnBill(bill)){
								salesReInnerBox.getChildren().remove(each);
							}else{
								/**
								 * 报错
								 */
							}
						} catch (RemoteException e) {
							e.printStackTrace();
						}
					});	
				}else if(state==BillStateEnum.DONE){
					Button view=new Button("查看");
					view.setFont(Font.font(15));
			
					each.getChildren().addAll(billId, view);
			
					salesReInnerBox.getChildren().add(each);
			
					view.setOnAction(oa ->{
						ViewSalesmanBillsUiStarter starter=new ViewSalesmanBillsUiStarter();
						starter.viewSalesReturnBill(bill.getId());
					});	
				}
			}
		}
		
	}
	
	@FXML
	protected void unpassButtonListener(){
		billHBox.getChildren().clear();
		fourBox(BillStateEnum.DENIED);
	}
	
	@FXML
	protected void passButtonListener(){
		billHBox.getChildren().clear();
		fourBox(BillStateEnum.DONE);
	}
	
	@FXML
	protected void backButtonListener(){
		SalesmanMainUiStarter starter=new SalesmanMainUiStarter();
		starter.startSalesman(user);
	}
}
