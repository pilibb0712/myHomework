package document;

import assistant.ReadWriteTxt;

/**
 * @see DefectDocument类
 * */

public class TestCaseDocument {

	public static void main(String [] args){
		write();
	}
	
	public static String read(){
		String path = "TestCaseInput.txt";
		String input = ReadWriteTxt.readTxt(path);
		return input;
	}
	
	public static void write(){
		String path  = "testCase.txt";
		String content = "|测试用例ID|条件|期望结果|测试结果|测试对象ID|   ";
		content += System.lineSeparator()+"| :--- | :--- | :--- | :--- | :--- |   ";
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
