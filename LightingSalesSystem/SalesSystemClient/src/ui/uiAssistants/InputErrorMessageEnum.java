package ui.uiAssistants;

public enum InputErrorMessageEnum {
	Time_Format_Error,//时间格式错误
	Time_End_Less_Than_Start,//结束时间和开始时间冲突
	
	Uncompleted_Error,//未完成
	
	Number_Format_Error,//数字格式错误
	Number_Out_Of_Bounds;//数字不在取值范围
	
	public String getErrorMessage(){
		switch (this) {
		case Time_Format_Error:
			return "请输入正确格式时间";
		case Time_End_Less_Than_Start:
			return "结束时间与开始时间冲突";
		case Uncompleted_Error:
			return "必填";
		case Number_Format_Error:
			return "输入格式错误";
		case Number_Out_Of_Bounds:
			return "输入不在正确范围内";
		default:
			return"哪里没填对你心里没有点b数吗";
		}
	}
}
