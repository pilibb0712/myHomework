package ui.billUi.billStateUi;

import java.rmi.RemoteException;
import java.util.ArrayList;
import assistant.type.BillStateEnum;
import assistant.utility.Date;
import blService.billService.billStateBlService.WareKeeperBillStateBlService;
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
import javafx.stage.Stage;
import rmi.remoteHelper.BillStateRemoteHelper;
import rmi.remoteHelper.RemoteHelperFactory;
import ui.billUi.viewBillUi.ViewGiftBillUiStarter;
import ui.billUi.viewBillUi.ViewSalesmanBillsUiStarter;
import ui.billUi.viewBillUi.ViewWareKeeperBillUiStarter;
import ui.mainUi.wareKeeperMainUi.WareKeeperMainUiStarter;
import ui.uiAssistants.StageSize;
import vo.GiftBillVO;
import vo.InventoryLossBillVO;
import vo.InventoryOverBillVO;
import vo.InventoryWarningBillVO;
import vo.PurchaseBillVO;
import vo.PurchaseReturnBillVO;
import vo.SalesBillVO;
import vo.SalesReturnBillVO;
import vo.UserInfoVO;

/**
 * 仓库管理员单据状态界面controller
 * @author guxinyu
 * @version 2017.12.27
 *
 */
public class WareKeeperBillStateUiController {
	@FXML
	private HBox billHBox;

	private static UserInfoVO user;

	private static Stage currentStage;

	private BillStateRemoteHelper remoteHelper=RemoteHelperFactory.getBillStateRemoteHelper();

	private WareKeeperBillStateBlService billStateBl=remoteHelper.getWareKeeperBillStateBlService();

	public void setStage(Stage stage){
		if(stage==null){
			currentStage=new Stage();
		}else{
			currentStage=stage;
		}
	}
	public void init(UserInfoVO u){
		user=u;
		if(currentStage==null){
			System.out.print("stage3 is null");
		}
	}

	private void sevenBox(BillStateEnum state){
		Separator sep1=new Separator();
		sep1.setOrientation(Orientation.VERTICAL);
		Separator sep2=new Separator();
		sep2.setOrientation(Orientation.VERTICAL);
		Separator sep3=new Separator();
		sep3.setOrientation(Orientation.VERTICAL);
		Separator sep4=new Separator();
		sep4.setOrientation(Orientation.VERTICAL);
		Separator sep5=new Separator();
		sep5.setOrientation(Orientation.VERTICAL);
		Separator sep6=new Separator();
		sep6.setOrientation(Orientation.VERTICAL);
		Separator sep7=new Separator();
		sep7.setOrientation(Orientation.VERTICAL);


		//外部VBox
		VBox overOuterBox=new VBox();
		VBox lossOuterBox=new VBox();
		VBox purchaseOuterBox=new VBox();
		VBox purchaseReOuterBox=new VBox();
		VBox salesOuterBox=new VBox();
		VBox salesReOuterBox=new VBox();
		VBox giftOuterBox=new VBox();

		//大小
		int boxWidth=145;
		overOuterBox.setPrefSize(boxWidth, StageSize.STAGE_HEIGHT);
		overOuterBox.setMaxSize(boxWidth, StageSize.STAGE_HEIGHT);
		overOuterBox.setMinSize(boxWidth, StageSize.STAGE_HEIGHT);
		lossOuterBox.setPrefSize(boxWidth, StageSize.STAGE_HEIGHT);
		lossOuterBox.setMaxSize(boxWidth, StageSize.STAGE_HEIGHT);
		lossOuterBox.setMinSize(boxWidth, StageSize.STAGE_HEIGHT);
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
		giftOuterBox.setPrefSize(boxWidth, StageSize.STAGE_HEIGHT);
		giftOuterBox.setMaxSize(boxWidth, StageSize.STAGE_HEIGHT);
		giftOuterBox.setMinSize(boxWidth, StageSize.STAGE_HEIGHT);

		//内边距
		overOuterBox.setPadding(new Insets(20,0,0,0));
		lossOuterBox.setPadding(new Insets(20,0,0,0));
		purchaseOuterBox.setPadding(new Insets(20,0,0,0));
		purchaseReOuterBox.setPadding(new Insets(20,0,0,0));
		salesOuterBox.setPadding(new Insets(20,0,0,0));
		salesReOuterBox.setPadding(new Insets(20,0,0,0));
		giftOuterBox.setPadding(new Insets(20,0,0,0));

		//子组件间距
		overOuterBox.setSpacing(20);
		lossOuterBox.setSpacing(20);
		purchaseOuterBox.setSpacing(20);
		purchaseReOuterBox.setSpacing(20);
		salesOuterBox.setSpacing(20);
		salesReOuterBox.setSpacing(20);
		giftOuterBox.setSpacing(20);

		//子组件对齐方式
		overOuterBox.setAlignment(Pos.TOP_CENTER);
		lossOuterBox.setAlignment(Pos.TOP_CENTER);
		purchaseOuterBox.setAlignment(Pos.TOP_CENTER);
		purchaseReOuterBox.setAlignment(Pos.TOP_CENTER);
		salesOuterBox.setAlignment(Pos.TOP_CENTER);
		salesReOuterBox.setAlignment(Pos.TOP_CENTER);
		giftOuterBox.setAlignment(Pos.TOP_CENTER);

		//提示Label
		int promptFontSize=25;
		Label overPrompt=new Label("库存报溢单");
		overPrompt.setFont(Font.font(promptFontSize));
		Label lossPrompt=new Label("库存报损单");
		lossPrompt.setFont(Font.font(promptFontSize));
		Label purchasePrompt=new Label("进货单");
		purchasePrompt.setFont(Font.font(promptFontSize));
		Label purchaseRePrompt=new Label("进货退货单");
		purchaseRePrompt.setFont(Font.font(promptFontSize));
		Label salesPrompt=new Label("销售单");
		salesPrompt.setFont(Font.font(promptFontSize));
		Label salesRePrompt=new Label("销售退货单");
		salesRePrompt.setFont(Font.font(promptFontSize));
		Label giftPrompt=new Label("库存赠送单");
		giftPrompt.setFont(Font.font(promptFontSize));

		//内部ScollPane
		ScrollPane overScroll=new ScrollPane();
	//	overScroll.setHbarPolicy(ScrollBarPolicy.NEVER);
		ScrollPane lossScroll=new ScrollPane();
	//	lossScroll.setHbarPolicy(ScrollBarPolicy.NEVER);
		ScrollPane purchaseScroll=new ScrollPane();
	//	purchaseScroll.setHbarPolicy(ScrollBarPolicy.NEVER);
		ScrollPane purchaseReScroll=new ScrollPane();
	//	purchaseReScroll.setHbarPolicy(ScrollBarPolicy.NEVER);
		ScrollPane salesScroll=new ScrollPane();
	//	salesScroll.setHbarPolicy(ScrollBarPolicy.NEVER);
		ScrollPane salesReScroll=new ScrollPane();
	//	salesReScroll.setHbarPolicy(ScrollBarPolicy.NEVER);
		ScrollPane giftScroll=new ScrollPane();
	//	giftScroll.setHbarPolicy(ScrollBarPolicy.NEVER);
		
		overScroll.setPannable(true);
		lossScroll.setPannable(true);
		purchaseScroll.setPannable(true);
		purchaseReScroll.setPannable(true);
		salesScroll.setPannable(true);
		salesReScroll.setPannable(true);
		giftScroll.setPannable(true);

		//ScrollPane大小固定
		overScroll.setPrefSize(boxWidth, StageSize.STAGE_HEIGHT-156);
		overScroll.setMaxSize(boxWidth, StageSize.STAGE_HEIGHT-156);
		overScroll.setMinSize(boxWidth, StageSize.STAGE_HEIGHT-156);
		lossScroll.setPrefSize(boxWidth, StageSize.STAGE_HEIGHT-156);
		lossScroll.setMaxSize(boxWidth, StageSize.STAGE_HEIGHT-156);
		lossScroll.setMinSize(boxWidth, StageSize.STAGE_HEIGHT-156);
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
		giftScroll.setPrefSize(boxWidth, StageSize.STAGE_HEIGHT-156);
		giftScroll.setMaxSize(boxWidth, StageSize.STAGE_HEIGHT-156);
		giftScroll.setMinSize(boxWidth, StageSize.STAGE_HEIGHT-156);

		//内部VBox
		VBox overInnerBox=new VBox();
		VBox lossInnerBox=new VBox();
		VBox purchaseInnerBox=new VBox();
		VBox purchaseReInnerBox=new VBox();
		VBox salesInnerBox=new VBox();
		VBox salesReInnerBox=new VBox();
		VBox giftInnerBox=new VBox();

		overScroll.setContent(overInnerBox);
		lossScroll.setContent(lossInnerBox);
		purchaseScroll.setContent(purchaseInnerBox);
		purchaseReScroll.setContent(purchaseReInnerBox);
		salesScroll.setContent(salesInnerBox);
		salesReScroll.setContent(salesReInnerBox);
		giftScroll.setContent(giftInnerBox);

		overInnerBox.setSpacing(10);
		lossInnerBox.setSpacing(10);
		purchaseInnerBox.setSpacing(10);
		purchaseReInnerBox.setSpacing(10);
		salesInnerBox.setSpacing(10);
		salesReInnerBox.setSpacing(10);
		giftInnerBox.setSpacing(10);

		overInnerBox.setAlignment(Pos.TOP_CENTER);
		lossInnerBox.setAlignment(Pos.TOP_CENTER);
		purchaseInnerBox.setAlignment(Pos.TOP_CENTER);
		purchaseReInnerBox.setAlignment(Pos.TOP_CENTER);
		salesInnerBox.setAlignment(Pos.TOP_CENTER);
		salesReInnerBox.setAlignment(Pos.TOP_CENTER);
		giftInnerBox.setAlignment(Pos.TOP_CENTER);

		overOuterBox.getChildren().addAll(overPrompt,overScroll);
		lossOuterBox.getChildren().addAll(lossPrompt,lossScroll);
		purchaseOuterBox.getChildren().addAll(purchasePrompt,purchaseScroll);
		purchaseReOuterBox.getChildren().addAll(purchaseRePrompt,purchaseReScroll);
		salesOuterBox.getChildren().addAll(salesPrompt,salesScroll);
		salesReOuterBox.getChildren().addAll(salesRePrompt,salesReScroll);
		giftOuterBox.getChildren().addAll(giftPrompt,giftScroll);

		billHBox.getChildren().addAll(sep1, overOuterBox, sep2, lossOuterBox, sep3,
				purchaseOuterBox, sep4, purchaseReOuterBox, sep5, salesOuterBox,
				sep6, salesReOuterBox,sep7, giftOuterBox);

		ArrayList<InventoryOverBillVO> inventoryOverBills=null;
		ArrayList<InventoryLossBillVO> inventoryLossBills=null;
		ArrayList<PurchaseBillVO> purchaseBills=null;
		ArrayList<PurchaseReturnBillVO> purchaseReturnBills=null;
		ArrayList<SalesBillVO> salesBills=null;
		ArrayList<SalesReturnBillVO> salesReturnBills=null;
		ArrayList<GiftBillVO> giftBills=null;
		try {
			inventoryOverBills=billStateBl.getInventoryOverList(state, user);
			inventoryLossBills=billStateBl.getInventoryLossList(state, user);
			purchaseBills=billStateBl.getPurchaseBillList(state, user);
			purchaseReturnBills=billStateBl.getPurchaseReturnBillList(state, user);
			salesBills=billStateBl.getSalesBillList(state, user);
			salesReturnBills=billStateBl.getSalesReturnBillList(state, user);
			giftBills=billStateBl.getGiftBillList(state, user);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		if(inventoryOverBills!=null){
			//显示库存报溢单
			for(int i=0;i<inventoryOverBills.size();i++){
				InventoryOverBillVO thisBill=inventoryOverBills.get(i);

				HBox each=new HBox();
				each.setAlignment(Pos.CENTER);
				each.setSpacing(20);

				Text billId=new Text(thisBill.getId());
				billId.setFont(Font.font(15));

				each.getChildren().add(billId);

				if(state==BillStateEnum.TODO){
					Button view=new Button("查看");
					view.setFont(Font.font(15));
					Button done=new Button("完成");
					done.setFont(Font.font(15));
					each.getChildren().addAll(view, done);

					view.setOnAction(oa ->{
						ViewWareKeeperBillUiStarter starter=new ViewWareKeeperBillUiStarter();
						starter.viewInventoryOverBill(thisBill.getId());
					});

					done.setOnAction(oa->{
						thisBill.setFinishDate(new Date());
						thisBill.setExecutor(user);
						try {
							if(billStateBl.doneInventoryOverBill(thisBill))
								overInnerBox.getChildren().remove(each);
							else{
								/**
								 * 错误提示
								 */
							}
						} catch (RemoteException e) {
							e.printStackTrace();
						}
					});
				}else if(state==BillStateEnum.DONE){
					Button view=new Button("查看");
					view.setFont(Font.font(15));
					each.getChildren().add(view);

					view.setOnAction(oa ->{
						ViewWareKeeperBillUiStarter starter=new ViewWareKeeperBillUiStarter();
						starter.viewInventoryOverBill(thisBill.getId());
					});
				}

				overInnerBox.getChildren().add(each);
			}
		}

		if(inventoryLossBills!=null){
		//显示库存报损单
		for(int i=0;i<inventoryLossBills.size();i++){
			InventoryLossBillVO thisBill=inventoryLossBills.get(i);

			HBox each=new HBox();
			each.setAlignment(Pos.CENTER);
			each.setSpacing(20);

			Text billId=new Text(thisBill.getId());
			billId.setFont(Font.font(15));

			each.getChildren().add(billId);

			if(state==BillStateEnum.TODO){
				Button view=new Button("查看");
				view.setFont(Font.font(15));
				Button done=new Button("完成");
				done.setFont(Font.font(15));
				each.getChildren().addAll(view, done);

				view.setOnAction(oa ->{
					ViewWareKeeperBillUiStarter starter=new ViewWareKeeperBillUiStarter();
					starter.viewInventoryLossBill(thisBill.getId());
				});

				done.setOnAction(oa->{
					try {
						if(billStateBl.doneInventoryLossBill(thisBill))
							lossInnerBox.getChildren().remove(each);
						else{
							/**
							 * 错误提示
							 */
						}
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				});
			}else if(state==BillStateEnum.DONE){
				Button view=new Button("查看");
				view.setFont(Font.font(15));
				each.getChildren().add(view);

				view.setOnAction(oa ->{
					ViewWareKeeperBillUiStarter starter=new ViewWareKeeperBillUiStarter();
					starter.viewInventoryLossBill(thisBill.getId());
				});
			}

			lossInnerBox.getChildren().add(each);
		}
		}

		if(purchaseBills!=null){
		//显示进货单
		for(int i=0;i<purchaseBills.size();i++){
			PurchaseBillVO thisBill=purchaseBills.get(i);

			HBox each=new HBox();
			each.setAlignment(Pos.CENTER);
			each.setSpacing(20);

			Text billId=new Text(thisBill.getId());
			billId.setFont(Font.font(15));

			each.getChildren().add(billId);

			if(state==BillStateEnum.TODO){
				Button view=new Button("查看");
				view.setFont(Font.font(15));
				Button done=new Button("完成");
				done.setFont(Font.font(15));
				each.getChildren().addAll(view, done);

				view.setOnAction(oa ->{
					ViewSalesmanBillsUiStarter starter=new ViewSalesmanBillsUiStarter();
					starter.viewPurchaseBill(thisBill.getId());
				});

				done.setOnAction(oa->{
					try {
						if(billStateBl.donePurchaseBill(thisBill))
							purchaseInnerBox.getChildren().remove(each);
						else{
							/**
							 * 错误提示
							 */
						}
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				});
			}else if(state==BillStateEnum.DONE){
				Button view=new Button("查看");
				view.setFont(Font.font(15));
				each.getChildren().addAll(view);

				view.setOnAction(oa ->{
					ViewSalesmanBillsUiStarter starter=new ViewSalesmanBillsUiStarter();
					starter.viewPurchaseBill(thisBill.getId());
				});
			}

			purchaseInnerBox.getChildren().add(each);
		}
		}

		if(purchaseReturnBills!=null){
		//显示进货退货单
		for(int i=0;i<purchaseReturnBills.size();i++){
			PurchaseReturnBillVO thisBill=purchaseReturnBills.get(i);

			HBox each=new HBox();
			each.setAlignment(Pos.CENTER);
			each.setSpacing(20);

			Text billId=new Text(thisBill.getId());
			billId.setFont(Font.font(15));

			each.getChildren().add(billId);

			if(state==BillStateEnum.TODO){
				Button view=new Button("查看");
				view.setFont(Font.font(15));
				Button done=new Button("完成");
				done.setFont(Font.font(15));
				each.getChildren().addAll(view, done);

				view.setOnAction(oa ->{
					ViewSalesmanBillsUiStarter starter=new ViewSalesmanBillsUiStarter();
					starter.viewPurchaseReturnBill(thisBill.getId());
				});

				done.setOnAction(oa->{
					try {
						if(billStateBl.donePurchaseReturnBill(thisBill))
							purchaseReInnerBox.getChildren().remove(each);
						else{
							/**
							 * 错误提示
							 */
						}
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				});
			}else if(state==BillStateEnum.DONE){
				Button view=new Button("查看");
				view.setFont(Font.font(15));
				each.getChildren().add(view);

				view.setOnAction(oa ->{
					ViewSalesmanBillsUiStarter starter=new ViewSalesmanBillsUiStarter();
					starter.viewPurchaseReturnBill(thisBill.getId());
				});
			}

			purchaseReInnerBox.getChildren().add(each);
		}
		}

		if(salesBills!=null){
		//显示销售单
		for(int i=0;i<salesBills.size();i++){
			SalesBillVO thisBill=salesBills.get(i);

			HBox each=new HBox();
			each.setAlignment(Pos.CENTER);
			each.setSpacing(20);

			Text billId=new Text(thisBill.getId());
			billId.setFont(Font.font(15));

			each.getChildren().add(billId);

			if(state==BillStateEnum.TODO){
				Button view=new Button("查看");
				view.setFont(Font.font(15));
				Button done=new Button("完成");
				done.setFont(Font.font(15));
				each.getChildren().addAll(view, done);

				view.setOnAction(oa ->{
					ViewSalesmanBillsUiStarter starter=new ViewSalesmanBillsUiStarter();
					starter.viewSalesBill(thisBill.getId());
				});

				done.setOnAction(oa->{
					try {
						if(billStateBl.doneSalesBill(thisBill))
							salesInnerBox.getChildren().remove(each);
						else{
							/**
							 * 错误提示
							 */
						}
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				});
			}else if(state==BillStateEnum.DONE){
				Button view=new Button("查看");
				view.setFont(Font.font(15));
				each.getChildren().addAll(view);

				view.setOnAction(oa ->{
					ViewSalesmanBillsUiStarter starter=new ViewSalesmanBillsUiStarter();
					starter.viewSalesBill(thisBill.getId());
				});
			}

			salesInnerBox.getChildren().add(each);
		}
		}

		if(salesReturnBills!=null){
		//显示销售退货单
		for(int i=0;i<salesReturnBills.size();i++){
			SalesReturnBillVO thisBill=salesReturnBills.get(i);

			HBox each=new HBox();
			each.setAlignment(Pos.CENTER);
			each.setSpacing(20);

			Text billId=new Text(thisBill.getId());
			billId.setFont(Font.font(15));

			each.getChildren().add(billId);

			if(state==BillStateEnum.TODO){
				Button view=new Button("查看");
				view.setFont(Font.font(15));
				Button done=new Button("完成");
				done.setFont(Font.font(15));
				each.getChildren().addAll(view, done);

				view.setOnAction(oa ->{
					ViewSalesmanBillsUiStarter starter=new ViewSalesmanBillsUiStarter();
					starter.viewSalesReturnBill(thisBill.getId());
				});

				done.setOnAction(oa->{
					try {
						if(billStateBl.doneSalesReturnBill(thisBill))
							salesReInnerBox.getChildren().remove(each);
						else{
							/**
							 * 错误提示
							 */
						}
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				});
			}else if(state==BillStateEnum.DONE){
				Button view=new Button("查看");
				view.setFont(Font.font(15));
				each.getChildren().addAll(view);

				view.setOnAction(oa ->{
					ViewSalesmanBillsUiStarter starter=new ViewSalesmanBillsUiStarter();
					starter.viewSalesReturnBill(thisBill.getId());
				});
			}

			salesReInnerBox.getChildren().add(each);
		}
		}

		if(giftBills!=null){
		//显示库存赠送单
		for(int i=0;i<giftBills.size();i++){
			GiftBillVO thisBill=giftBills.get(i);

			HBox each=new HBox();
			each.setAlignment(Pos.CENTER);
			each.setSpacing(20);

			Text billId=new Text(thisBill.getId());
			billId.setFont(Font.font(15));

			each.getChildren().add(billId);

			if(state==BillStateEnum.TODO){
				Button view=new Button("查看");
				view.setFont(Font.font(15));
				Button done=new Button("完成");
				done.setFont(Font.font(15));
				each.getChildren().addAll(view, done);

				view.setOnAction(oa ->{
					ViewGiftBillUiStarter starter=new ViewGiftBillUiStarter(thisBill.getId());
					starter.start();
				});

				done.setOnAction(oa->{
					try {
						if(billStateBl.doneGiftBill(thisBill))
							giftInnerBox.getChildren().remove(each);
						else{
							/**
							 * 错误提示
							 */
						}
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				});
			}else if(state==BillStateEnum.DONE){
				Button view=new Button("查看");
				view.setFont(Font.font(15));
				each.getChildren().add(view);

				view.setOnAction(oa ->{
					ViewGiftBillUiStarter starter=new ViewGiftBillUiStarter(thisBill.getId());
					starter.start();
				});
			}

			giftInnerBox.getChildren().add(each);
		}
		}

	}

	@FXML
	protected void unpassButtonListener() throws Exception{
		billHBox.getChildren().clear();

		Separator sep1=new Separator();
		sep1.setOrientation(Orientation.VERTICAL);
		Separator sep2=new Separator();
		sep2.setOrientation(Orientation.VERTICAL);
		Separator sep3=new Separator();
		sep3.setOrientation(Orientation.VERTICAL);

		//外部VBox
		VBox overOuterBox=new VBox();
		VBox lossOuterBox=new VBox();
		VBox warningOuterBox=new VBox();

		//大小
		int boxWidth=350;
		overOuterBox.setPrefSize(boxWidth, 786);
		overOuterBox.setMaxSize(boxWidth, 786);
		overOuterBox.setMinSize(boxWidth, 786);
		lossOuterBox.setPrefSize(boxWidth, 786);
		lossOuterBox.setMaxSize(boxWidth, 786);
		lossOuterBox.setMinSize(boxWidth, 786);
		warningOuterBox.setPrefSize(boxWidth, 786);
		warningOuterBox.setMaxSize(boxWidth, 786);
		warningOuterBox.setMinSize(boxWidth, 786);

		//内边距
		overOuterBox.setPadding(new Insets(20,0,0,0));
		lossOuterBox.setPadding(new Insets(20,0,0,0));
		warningOuterBox.setPadding(new Insets(20,0,0,0));

		//子组件间距
		overOuterBox.setSpacing(20);
		lossOuterBox.setSpacing(20);
		warningOuterBox.setSpacing(20);

		//子组件对齐方式
		overOuterBox.setAlignment(Pos.TOP_CENTER);
		lossOuterBox.setAlignment(Pos.TOP_CENTER);
		warningOuterBox.setAlignment(Pos.TOP_CENTER);

		//提示Label
		Label overPrompt=new Label("库存报溢单");
		overPrompt.setFont(Font.font(29));
		Label lossPrompt=new Label("库存报损单");
		lossPrompt.setFont(Font.font(29));
		Label warningPrompt=new Label("库存报警单");
		warningPrompt.setFont(Font.font(29));

		//内部ScollPane
		ScrollPane overScroll=new ScrollPane();
		overScroll.setHbarPolicy(ScrollBarPolicy.NEVER);
		ScrollPane lossScroll=new ScrollPane();
		lossScroll.setHbarPolicy(ScrollBarPolicy.NEVER);
		ScrollPane warningScroll=new ScrollPane();
		warningScroll.setHbarPolicy(ScrollBarPolicy.NEVER);

		//ScrollPane大小固定
		overScroll.setPrefSize(boxWidth, 650);
		overScroll.setMaxSize(boxWidth, 650);
		overScroll.setMinSize(boxWidth, 650);
		lossScroll.setPrefSize(boxWidth, 650);
		lossScroll.setMaxSize(boxWidth, 650);
		lossScroll.setMinSize(boxWidth, 650);
		warningScroll.setPrefSize(boxWidth, 650);
		warningScroll.setMaxSize(boxWidth, 650);
		warningScroll.setMinSize(boxWidth, 650);

		//内部VBox
		VBox overInnerBox=new VBox();
		VBox lossInnerBox=new VBox();
		VBox warningInnerBox=new VBox();

		overScroll.setContent(overInnerBox);
		lossScroll.setContent(lossInnerBox);
		warningScroll.setContent(warningInnerBox);

		overInnerBox.setPrefSize(boxWidth, 650);
		lossInnerBox.setPrefSize(boxWidth, 650);
		warningInnerBox.setPrefSize(boxWidth, 650);

		overInnerBox.setSpacing(10);
		lossInnerBox.setSpacing(10);
		warningInnerBox.setSpacing(10);

		overInnerBox.setAlignment(Pos.TOP_CENTER);
		lossInnerBox.setAlignment(Pos.TOP_CENTER);
		warningInnerBox.setAlignment(Pos.TOP_CENTER);

		overOuterBox.getChildren().addAll(overPrompt,overScroll);
		lossOuterBox.getChildren().addAll(lossPrompt,lossScroll);
		warningOuterBox.getChildren().addAll(warningPrompt,warningScroll);

		billHBox.getChildren().addAll(sep1, overOuterBox, sep2, lossOuterBox, sep3, warningOuterBox);
	//	System.out.println("uidebl");
		ArrayList<InventoryOverBillVO> inventoryOverBills=null;
		ArrayList<InventoryLossBillVO> inventoryLossBills=null;
		ArrayList<InventoryWarningBillVO> inventoryWarningBills=null;
		try {
			inventoryOverBills=billStateBl.getInventoryOverList(BillStateEnum.DENIED, user);
			inventoryLossBills=billStateBl.getInventoryLossList(BillStateEnum.DENIED, user);
			inventoryWarningBills=billStateBl.getInventoryWarningList(BillStateEnum.DENIED, user);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		System.out.println(inventoryLossBills.size());

		if(inventoryOverBills!=null){
		//显示库存报溢单
		for(int i=0;i<inventoryOverBills.size();i++){
			InventoryOverBillVO thisBill=inventoryOverBills.get(i);

			HBox each=new HBox();
			each.setAlignment(Pos.CENTER);
			each.setSpacing(20);

			Text billId=new Text(thisBill.getId());
			billId.setFont(Font.font(15));

			each.getChildren().add(billId);

			Button view=new Button("查看");
			view.setFont(Font.font(15));
			Button delete=new Button("删除");
			delete.setFont(Font.font(15));
			each.getChildren().addAll(view, delete);

			view.setOnAction(oa ->{
				ViewWareKeeperBillUiStarter starter=new ViewWareKeeperBillUiStarter();
				starter.viewInventoryOverBill(thisBill.getId());
			});

			delete.setOnAction(oa->{
				try {
					if(billStateBl.deleteUnpassInventoryOverBill(thisBill))
						overInnerBox.getChildren().remove(each);
					else{
						/**
						 * 错误提示
						 */
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			});

		overInnerBox.getChildren().add(each);
	}
	}

	if(inventoryLossBills!=null){
		//显示库存报损单
		for(int i=0;i<inventoryLossBills.size();i++){
			InventoryLossBillVO thisBill=inventoryLossBills.get(i);
			
			System.out.println(thisBill.getId());

			HBox each=new HBox();
			each.setAlignment(Pos.CENTER);
			each.setSpacing(20);

			Text billId=new Text(thisBill.getId());
			billId.setFont(Font.font(15));

			each.getChildren().add(billId);

			Button view=new Button("查看");
			view.setFont(Font.font(15));
			Button delete=new Button("删除");
			delete.setFont(Font.font(15));
			each.getChildren().addAll(view, delete);

			view.setOnAction(oa ->{
				ViewWareKeeperBillUiStarter starter=new ViewWareKeeperBillUiStarter();
				starter.viewInventoryLossBill(thisBill.getId());
			});

			delete.setOnAction(oa->{
				try {
					if(billStateBl.deleteUnpassInventoryLossBill(thisBill))
						lossInnerBox.getChildren().remove(each);
					else{
						/**
						 * 错误提示
						 */
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			});

			lossInnerBox.getChildren().add(each);
		}
	}

	if(inventoryWarningBills!=null){
		//显示库存报警单
		for(int i=0;i<inventoryWarningBills.size();i++){
			InventoryWarningBillVO thisBill=inventoryWarningBills.get(i);

			HBox each=new HBox();
			each.setAlignment(Pos.CENTER);
			each.setSpacing(20);

			Text billId=new Text(thisBill.getId());
			billId.setFont(Font.font(15));

			each.getChildren().add(billId);

			Button view=new Button("查看");
			view.setFont(Font.font(15));
			Button delete=new Button("删除");
			delete.setFont(Font.font(15));
			each.getChildren().addAll(view, delete);

			view.setOnAction(oa ->{
				ViewWareKeeperBillUiStarter starter=new ViewWareKeeperBillUiStarter();
				starter.viewInventoryWarningBill(thisBill.getId());
			});

			delete.setOnAction(oa->{
				try {
					if(billStateBl.deleteUnpassInventoryWarningBill(thisBill))
						warningInnerBox.getChildren().remove(each);
					else{
						/**
						 * 错误提示
						 */
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			});

			warningInnerBox.getChildren().add(each);
		}
		}
	}

	@FXML
	protected void toDoButtonListener() throws Exception{
		billHBox.getChildren().clear();
		sevenBox(BillStateEnum.TODO);
	}

	@FXML
	protected void doneButtonListener() throws Exception{
		billHBox.getChildren().clear();
		sevenBox(BillStateEnum.DONE);
	}

	@FXML
	protected void backButtonListener() throws Exception{
		WareKeeperMainUiStarter starter=new WareKeeperMainUiStarter();
		if(currentStage==null){
			System.out.print("stage2 is null");
		}
	    starter.wareKeeperMainUi(currentStage,null,null);
	}
}
