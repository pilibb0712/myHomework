package ui.approveBillUi;

import java.util.Optional;

import javafx.scene.control.TextInputDialog;

public class CommentDialog {
	private TextInputDialog dialog;
	protected CommentDialog(String comment){
		dialog = new TextInputDialog();
		dialog.setContentText("µ±Ç°±¸×¢:"+comment);
	}
	
	protected String showAndWait(){
		Optional<String> result = dialog.showAndWait();
		if(result.isPresent()){
			return result.get();
		}
		return null;
	}
	
}
