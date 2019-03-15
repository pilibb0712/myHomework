package document;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class FindTestCase {
	static int doc =0;
	static int files =0;
	static String caseName;
	public static void main(String[] args){
		/**
		* 在这里输入文件的路径就可以统计代码行数之类的
		* */
	      //String path = "D:\\软工\\软工2\\大作业git项目\\LightingSalesSystem\\SalesSystemClient\\src";
	      String path =  "D:\\Homework\\软工二大作业\\git\\LightingSalesSystem\\SalesSystemServer\\src";
	     Scanner sc = new Scanner(System.in);
	     FindTestCase.caseName = sc.nextLine().trim();
	     codeLines(path);
	 }
	
	private static int codeLines(String rootPath){
        int lines = 0;
        File root = new File(rootPath);
        File[] dirsAndFiles = root.listFiles();
        for(File f: dirsAndFiles){
            if(f.isDirectory()){
                lines += codeLines(f.getAbsolutePath());
            } else {
                lines += codeLinesInFile(f);
            }
        }
        return lines;
    }
    
    private static int codeLinesInFile(File f){
        if(!f.getName().contains("java")){return 0;}
    	if(f.isDirectory()) return 0;
    	files++;
        int lines = 0;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line;
            while((line = reader.readLine()) != null){
                if(line.contains(caseName)){System.out.println(f.getAbsolutePath());}
            }
            reader.close();
            return lines;
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }
	    
	   
}
