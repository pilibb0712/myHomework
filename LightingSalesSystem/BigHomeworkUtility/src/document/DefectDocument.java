package document;

import assistant.ReadWriteTxt;

/**
 * 在input文件中写入输入
 * 此处split用的Tab，因为我的excel分隔符为Tab 代码32行，你们可以自己再加一些功能
 * */

public class DefectDocument {
	
	
	public static void main(String [] args){
		write();
	}
	
	public static String read(){
		String path = "defectInput.txt";
		String input = ReadWriteTxt.readTxt(path);
		return input;
	}
	
	public static void write(){
		String path  = "defect.txt";
		String content = "|缺陷ID|发现日期|测试用例ID|期望结果|实际结果|优先级|备注|   ";
		content += System.lineSeparator()+"| :--- | :--- | :--- | :--- | :--- | :--- |  ";
		content += System.lineSeparator();
		
		String input = read();
		String[] lines=input.split(System.lineSeparator());
		for(String line:lines){
			String[] words = line.split("	");//在这用的Tab
			for(int i=0;i<words.length;i++){
				if(i!=words.length-1){
					content +=" | "+ words[i];
				}
				else{
					content += " | "+ words[i]+" |    "+System.lineSeparator();
				}
			}
		}
		ReadWriteTxt.writeTxtAppend(path, content);
	}
}
