package ui.listUi;

import java.io.File;
import java.io.FileOutputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import assistant.type.UserPositionEnum;
import assistant.utility.Date;
import blService.listBlService.BusinessConditionListBlService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import rmi.remoteHelper.ListRemoteHelper;
import rmi.remoteHelper.RemoteHelperFactory;
import ui.mainUi.financialStaffMainUi.FinancialStaffMainUiStarter;
import ui.mainUi.managerMainUi.ManagerMainUiStarter;
import ui.uiAssistants.StageFactory;
import vo.BusinessConditionListVO;
import vo.IncomeCostVO;

/**
 * 经营情况表界面Controller
 * @author guxinyu
 * @version 12.23
 *
 */
public class BusinessConditionListUiController {
	private Stage stage;
	
	@FXML
	private TableView<IncomeCostVO> conditionTable;
	
	@FXML
	private TableColumn<IncomeCostVO, String> salesEarningColumn;
	
	@FXML
	private TableColumn<IncomeCostVO, String> inventoryOverEarningColumn;
	
	@FXML
	private TableColumn<IncomeCostVO, String> costAdjustEarningColumn;
	
	@FXML
	private TableColumn<IncomeCostVO, String> purchaseReturnGapColumn;
	
	@FXML
	private TableColumn<IncomeCostVO, String> couponGapColumn;
	
	@FXML
	private TableColumn<IncomeCostVO, String> salesCostColumn;
	
	@FXML
	private TableColumn<IncomeCostVO, String> inventoryLossCostColumn;
	
	@FXML
	private TableColumn<IncomeCostVO, String> giftCostColumn;
	
	@FXML
	private Text afterAllowanceEarningText;
	
	@FXML
	private Text allowanceAmountText;
	
	@FXML
	private Text totalExpenseText;
	
	@FXML
	private Text profitText;
	
	private ObservableList<IncomeCostVO> items=FXCollections.observableArrayList();
	
	private ListRemoteHelper remoteHelper=RemoteHelperFactory.getListRemoteHelper();
	
	//private BusinessConditionListBlService listBlService=new BusinessConditionListBlService_Stub();
	
	private BusinessConditionListBlService listBlService=remoteHelper.getBusinessConditionListBlService();
	
	private UserPositionEnum pos;
	
	public void init(UserPositionEnum p){
		pos=p;
		
		if(p==UserPositionEnum.FINANCE)
			stage=StageFactory.getFinanceStage();
		else if(p==UserPositionEnum.MANAGER)
			stage=StageFactory.getManagerStage();
		
		BusinessConditionListVO listVO=null;
		try {
			listVO=listBlService.getBusinessConditionList();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		ArrayList<IncomeCostVO> statistics=listVO.getStatistics();
		
		items.addAll(statistics);
		
		conditionTable.setItems(items);
		
		salesEarningColumn.setCellValueFactory(
				new PropertyValueFactory<IncomeCostVO, String>("salesEarning"));
		inventoryOverEarningColumn.setCellValueFactory(
				new PropertyValueFactory<IncomeCostVO, String>("inventoryOverEarning"));
		costAdjustEarningColumn.setCellValueFactory(
				new PropertyValueFactory<IncomeCostVO, String>("costAjdustEarning"));
		purchaseReturnGapColumn.setCellValueFactory(
				new PropertyValueFactory<IncomeCostVO, String>("purchaseReturnGapEarning"));
		couponGapColumn.setCellValueFactory(
				new PropertyValueFactory<IncomeCostVO, String>("couponGapEarning"));
		salesCostColumn.setCellValueFactory(
				new PropertyValueFactory<IncomeCostVO, String>("salesCost"));
		inventoryLossCostColumn.setCellValueFactory(
				new PropertyValueFactory<IncomeCostVO, String>("inventoryLossCost"));
		giftCostColumn.setCellValueFactory(
				new PropertyValueFactory<IncomeCostVO, String>("giftCost"));
		
		double afterAllowanceEarning=listVO.getAfterAllowanceEarning();
		double allowance=listVO.getAllowance();
		double totalCost=listVO.getTotalCost();
		double profit=listVO.getProfit();
		
		afterAllowanceEarningText.setText(String.valueOf(afterAllowanceEarning));
		allowanceAmountText.setText(String.valueOf(allowance));
		totalExpenseText.setText(String.valueOf(totalCost));
		profitText.setText(String.valueOf(profit));
	}
	
	@FXML
	protected void exportButtonListener() throws Exception{
		HSSFWorkbook book=new HSSFWorkbook();
		HSSFSheet sheet=book.createSheet();
		HSSFRow title=sheet.createRow(0);
		
		HSSFCellStyle style=book.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		
		//标题
		HSSFCell titleCell=title.createCell(0);
		titleCell.setCellStyle(style);
		Date date=new Date();
		String time=date.getDate();
		titleCell.setCellValue("经营情况表    "+time);
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,7));
		
		//属性
		HSSFRow row1=sheet.createRow(1);
		
		HSSFCell income=row1.createCell(0);
		income.setCellStyle(style);
		income.setCellValue("收入");
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 4));
		
		HSSFCell cost=row1.createCell(1);
		cost.setCellStyle(style);
		cost.setCellValue("支出");
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 5, 7));
		
		HSSFRow row2=sheet.createRow(2);
		HSSFRow row3=sheet.createRow(3);
		
		HSSFCell salesEarning=row2.createCell(0);
		salesEarning.setCellStyle(style);
		salesEarning.setCellValue("销售收入");
		sheet.addMergedRegion(new CellRangeAddress(2, 3, 0, 0));
		
		HSSFCell comEarning=row2.createCell(1);
		comEarning.setCellStyle(style);
		comEarning.setCellValue("商品类收入");
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 4));
		
		HSSFCell salesCost=row2.createCell(5);
		salesCost.setCellStyle(style);
		salesCost.setCellValue("销售成本");
		sheet.addMergedRegion(new CellRangeAddress(2, 3, 5, 5));
		
		HSSFCell comCost=row2.createCell(6);
		comCost.setCellStyle(style);
		comCost.setCellValue("商品类支出");
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 6, 7));
		
		HSSFCell overEarning=row3.createCell(1);
		overEarning.setCellStyle(style);
		overEarning.setCellValue("商品报溢收入");
		
		HSSFCell costEarning=row3.createCell(2);
		costEarning.setCellStyle(style);
		costEarning.setCellValue("成本调价收入");
		
		HSSFCell returnEarning=row3.createCell(3);
		returnEarning.setCellStyle(style);
		returnEarning.setCellValue("退货进货差价");
		
		HSSFCell couponEarning=row3.createCell(4);
		couponEarning.setCellStyle(style);
		couponEarning.setCellValue("代金券差额");
		
		HSSFCell lossCost=row3.createCell(6);
		lossCost.setCellStyle(style);
		lossCost.setCellValue("商品报损");
		
		HSSFCell giftCost=row3.createCell(7);
		giftCost.setCellStyle(style);
		giftCost.setCellValue("商品溢出");
		
		//数据
		int rowIndex=4;
		for(int i=0;i<items.size();i++){
			IncomeCostVO vo=items.get(i);
			
			HSSFRow row=sheet.createRow(rowIndex);
			
			HSSFCell cell1=row.createCell(0);
			cell1.setCellValue(vo.getSalesEarning());
			cell1.setCellStyle(style);
			
			HSSFCell cell2=row.createCell(1);
			cell2.setCellValue(vo.getInventoryOverEarning());
			cell2.setCellStyle(style);
			
			HSSFCell cell3=row.createCell(2);
			cell3.setCellValue(vo.getCostAjdustEarning());
			cell3.setCellStyle(style);
			
			HSSFCell cell4=row.createCell(3);
			cell4.setCellValue(vo.getPurchaseReturnGapEarning());
			cell4.setCellStyle(style);
			
			HSSFCell cell5=row.createCell(4);
			cell5.setCellValue(vo.getCouponGapEarning());
			cell5.setCellStyle(style);
			
			HSSFCell cell6=row.createCell(5);
			cell6.setCellValue(vo.getSalesCost());
			cell6.setCellStyle(style);
			
			HSSFCell cell7=row.createCell(6);
			cell7.setCellValue(vo.getInventoryLossCost());
			cell7.setCellStyle(style);
			
			HSSFCell cell8=row.createCell(7);
			cell8.setCellValue(vo.getGiftCost());
			cell8.setCellStyle(style);
			
			rowIndex++;
		}
		
		FileChooser chooser=new FileChooser();
		chooser.setInitialFileName("经营情况表.xls");
		File file=chooser.showSaveDialog(stage);
		
		if(file!=null){
			FileOutputStream stream=new FileOutputStream(file);
			book.write(stream);
			stream.close();
		}
		
		book.close();
	}
	
	@FXML
	protected void backButtonListener(){
		if(pos==UserPositionEnum.FINANCE){
			FinancialStaffMainUiStarter starter=new FinancialStaffMainUiStarter();
			starter.mainUi();
		}else{
			ManagerMainUiStarter starter=new ManagerMainUiStarter();
			starter.start();	
		}
	}
}
