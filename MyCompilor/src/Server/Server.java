//2017.06.22 BeibeiZhang
//2017.06.23 BeibeiZhang
//2017.06.24 BeibeiZhang
//2017.06.25 BeibeiZhang
//2017.06.26 BeibeiZhang Note
 //name file-password file(loginFile)-currentFile(contain a .bf or .ook file and its all versions)
 
package Server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
	  private File loginFile;
	  //loginFile:the user file
	  private File currentFile;
	  //the currrent new or open file
	  private String langarage;
	  //set langarage to know run code of which kind
	  //set langarage when new or open a file
	  private ServerSocket server;
	  private Socket client;
	  private BufferedReader reader;
	  private PrintWriter writer;
	  //set reader and writer as private variable to send and get message from client directly
	  private boolean needinput=false;
	  //set needinput bit to show if need to input
	  //if true, send "Please input" to client to get input and then run the code 
	  //if false, run the code directly
	  private String inputArea;
	  //inputArea: the input from client 
	  private String code;
	  //code: the code from client
	  //set code as private variable to be used in different functions
       public static void main(String[] args){
    	  new Server().go(); 
       } 
       
       /*Socket Note: open Server firstly to build a port
	   then client set the IP and port of it to get connection with it
	   once get connected, server copy a socket of the client in Server 
	   and write and read the message from the copy one
	   then the copy one synchronized with the client
	    * 
	    */
      
       public void go(){
    	   //open the ServerSocket and get the clientSocket
    	   try{
    		   server=new ServerSocket(2020);
    		   
    		   while(true){
    			   client=server.accept();
    			   Thread handleclient=new Thread(new handleClient());
    			   handleclient.start();
    			   
    		//	   System.out.println("Server get connection...");
    		   }
    	   }catch(SocketException ee){
    		
    		   System.out.println("SocketException: Server block because of accept();");
    	   }catch(Exception e){
    		   e.printStackTrace();
    	   }
       }
       public class handleClient implements Runnable{
    	   //open an independent thread to get message from client
    	   //so that if it block, it will make no effect
    	   public handleClient(){
    		   try{
    			   InputStreamReader inputstream=new InputStreamReader(client.getInputStream());
    			   reader=new BufferedReader(inputstream); 
    			   writer=new PrintWriter(client.getOutputStream());
    		   }catch(Exception e){
    			   e.printStackTrace();
    		   }
    	   }
    	   public void run(){
    		 String message;
    		 try{
    			 while((message=reader.readLine())!=null){
    		//		 System.out.println("server read: "+message);
    			     Thread handlecmd=new Thread(new handleCmd(message));
    				 handlecmd.start();
    			 }
    		 }catch(IOException e){
    			System.out.println("server getMessageThread blocks because readline()");
    		 }catch(Exception ee){
    			 ee.printStackTrace();
    		 }
    	   }
       }
       public class handleCmd implements Runnable{
    	   //open an independent thread to handle the message from client 
    	   //so that it will not be influenced by the block thread
    	   String cmd;
    	   String[] input;
    	   public handleCmd(String input){
    		   this.input=input.split(";");
    		   this.cmd=this.input[0];
    		   //the first word or message is command and the last is params
    		   //they are split by ";"
    	   }
    	   public void run(){
    		   switch(cmd){
    		   case "WindowClose":{//close the server and client
    			                   //but it seems no use...
    			   try{
    			   reader.close();
    			   writer.close();
    		       client.close();
    			   server.close();
    			     }catch(IOException ee){
    				   System.out.println("IOException...");
    			   }
    			   break;
    		   }
    		   case "BF":{
    			   BF(input);
    			   break;
    		   }
    		   case "OOK":{
    			   OOK(input);
    			   break;
    		   }
    		   case "Open":{
    			 
    			   Open();
    			   break;
    		   }
    		   case "OpenFile":{
    			   String targetFile=input[1];
    			   OpenFile(targetFile);
    			   break;
    			   
    		   }
    		   case "Save":{
    			   Save(input);
    			   break;
    		   }
    		   case "Version":{
    			   Version();
    			   break;
    		   }
    		   case "OpenVersion":{
    			   String targetVersion=input[1];
    			   OpenVersion(targetVersion);
    			   break;
    		   }
    		   case "Delete":{
    			   Delete();
    			   break;
    		   }
    		   case "Login":{
    			   login(input);
    			   break;
    		   }
    		   case "Signup":{
    			   signup(input);
    			   break;
    			 
    		   }
    		   case "Run":{
    			   runCodeStep1(input);
    			   break;
    		   }
    		   case "Input":{
    			   inputArea=input[1];
    			   runCodeStep2(inputArea);
    			   break;
    		   }	   
    		   }
    	   }
       }
     public void BF(String[] input){
         //set langarage as BF
    	 //and get the fileName, new the file
    	 //then send "build successfully" to Client
    	 langarage="BF";
    	 String currentfileName=input[1];
    	 currentFile=new File(loginFile.getAbsolutePath()+"/"+currentfileName);
    	 currentFile.mkdirs();
    	 writer.println("build successfully");
		 writer.flush();
    	 
     }
     public void OOK(String[] input){
    	//set langarage as OOK
    	//and get the fileName, new the file
    	//then send "build successfully" to Client
    	 langarage="OOK";
    	 String currentfileName=input[1];
    	 currentFile=new File(loginFile.getAbsolutePath()+"/"+currentfileName);
    	 currentFile.mkdirs();
    	 writer.println("build successfully");
		 writer.flush();
    	 
     }
     
     public void Open(){
    	 //(nameFile(contain passwordFile(contain currentFile)))
    	 //nameFile+passwordFile=loginFile
    	 //Open:get the filelist(.bf and .ook files) in loginFile and send it to client(use ";" to separate them)
    	  StringBuffer filename=new StringBuffer();
    	 File[] files=loginFile.listFiles();
 	     for(int i=0;i<=files.length-1;i++){
 	    	String[] filenames=files[i].list();
 	    	  for(int j=0;j<=filenames.length-1;j++){
 	  	    	if((filenames[j].endsWith(".bf"))||(filenames[j].endsWith(".ook"))){
 	  	    		filename.append(filenames[j]+";");
 	  	    	}
 	  	    }
 	    }
 	     writer.println(filename.toString());
 	     writer.flush();
     }
     
     public void OpenFile(String targetfile){
    	 //get targetFile name from client
    	 //set currentFile=targetFile
    	 //get the file content and send it to client
  //  	 System.out.println(targetfile);
    	 String[] targetFile=targetfile.split("\\.");
    	 String Filename=targetFile[0];
    	 currentFile=new File(loginFile.getAbsolutePath()+"/"+Filename);
    	 String langa=targetFile[1];
    	 if(langa.equals("bf")){
    		 langarage="BF";
    	 }else{
    		 langarage="OOK";
    	 }
 //   	 System.out.println(Filename);
    	 File target=new File(currentFile.getAbsolutePath()+"/"+targetfile);
    	 try{
    	 FileReader reader=new FileReader(target);
   	     BufferedReader buffreader=new BufferedReader(reader);
   	     String content=buffreader.readLine();
   	     buffreader.close();
   	     reader.close();
   	     //because when we write file, we write the whole file at once
   	     //there is a lineseparator at the end of the file
   	     //so we just use readline to read once and then get the whole file
   	     writer.println(content);
    	 writer.flush();
    	 }catch(IOException ee){
    		 ee.printStackTrace();
    	 }
     }
     
     public void Save(String[] input){
    	 //save currentFile as .bf or .ook which is decided by langarage
    	 String savecode=input[1];
    	 File saveFile;
    	 String savename;
    	 if(langarage.equals("BF")){
    	  savename=currentFile.getName()+".bf";
    	  saveFile=new File(currentFile.getAbsolutePath()+"/"+savename);
    	 }else{
    	  savename=currentFile.getName()+".ook";
    	  saveFile=new File(currentFile.getAbsolutePath()+"/"+savename); 
    	 }
    	 try{
    	 FileWriter filewriter=new FileWriter(saveFile);
    	 filewriter.write(savecode);
    	 filewriter.flush();
    	 filewriter.close();
    	 }catch(IOException e){
    		 e.printStackTrace();
    	 }
    
     }
     
     public void Version(){
    	 //(nameFile(contain passwordFile(contain currentFile)))
    	 //nameFile+passwordFile=loginFile
    	 //Version:get the versionfilelist(.txt) in currentFile and send it to client(use ";" to separate them)
    	    StringBuffer versionnames=new StringBuffer();
 	    	String[] versions=currentFile.list();
 	    	  for(int j=0;j<=versions.length-1;j++){
 	  	    	if((versions[j].endsWith(".txt"))){
 	  	    		versionnames.append(versions[j]+";");
 	  	    	}
 	  	    }
 	  
 	     writer.println(versionnames.toString());
 	     writer.flush();
     }
     
     public void OpenVersion(String targetVersion){
    	 //get targetVersionFile name from the client and then get its content in Server
    	 //finally send the content to client
    	 File target=new File(currentFile.getAbsolutePath()+"/"+targetVersion);
    	 try{
    	 FileReader reader=new FileReader(target);
   	     BufferedReader buffreader=new BufferedReader(reader);
   	     String content=buffreader.readLine();
   	     buffreader.close();
   	     reader.close();
   	     writer.println(content);
    	 writer.flush();
    	 }catch(IOException ee){
    		 ee.printStackTrace();
    	 }
     }
     
     public void Delete(){
    	 //delete the current file
    	 File[] subFiles=currentFile.listFiles();
    	 for(int i=0;i<=subFiles.length-1;i++){
    		 subFiles[i].delete();
    	 }
    	 currentFile.delete();
     }
     
     public void login(String[] input){
    	 //log in
    	 //if nameFile does not exist: "name wrong" to client
    	 //if passwordFile does not exist: "password wrong" to client
    	 //finally log in succesfully: "log in succesfully" to client
    	 //and set loginFile=nameFile+passwordFile
    	 String name=input[1];
		 String password=input[2];
		 File nameFile=new File(name);
		 if(nameFile.exists()){
			 loginFile=new File(nameFile.getAbsolutePath()+"/"+password);
			 if(loginFile.exists()){
				 writer.println("log in successfully");
				 writer.flush();
			 }
			 else{
				 writer.println("password wrong");
				 writer.flush();
			 }
		 }else{
			 writer.println("name wrong");
			 writer.flush();
		 }
     }
     public void signup(String[] input){
    	 //sign up
    	 //found the nameFile and passwordFile (loginFile)
    	 //and then send "sign up successfully, you can log in" to client
    	 //then the user log in again, the files will both exist
    	 String name=input[1];
		 String password=input[2];
		 File nameFile=new File(name);
		 nameFile.mkdirs();
		 File signFile=new File(nameFile.getAbsolutePath()+"/"+password);
		 signFile.mkdirs();
		 writer.println("sign up successfully, you can log in");
		 writer.flush();
		  /*File Note:
		    * new File("/tmp/one/two/three").mkdirs();
          * 执行后， 会建立tmp/one/two/three四级目录
          * new File("/tmp/one/two/three").mkdir();
          * 则不会建立任何目录， 因为找不到/tmp/one/two目录， 结果返回false
		    */
     }
     public void runCodeStep1(String[] input){
    //when get "Run" cmd
    //runCodeStep1: save the currentFile and save the version (use time as versions' names)
    //if langarage=BF: 
    	 //if there is ",": send "Please input." to client to get input 
    	 //if there is no ",": input="", runCodeStep2("") to run the code
    //the same with "OOK"
    	 code=input[1];
    	 Date now=new Date();
    	 DateFormat nowform=new SimpleDateFormat("yy-MM-dd HH-mm-ss");
         String time=nowform.format(now);
         String version=time+".txt";
    	 File versionFile=new File(currentFile.getAbsolutePath()+"/"+version);
    	 try{
    	 FileWriter verFilewriter=new FileWriter(versionFile);
    	 verFilewriter.write(code);
    	 verFilewriter.flush();
    	 verFilewriter.close();
    	 }catch(IOException e){
    		 e.printStackTrace();
    	 }
    	 if(langarage.equals("BF")){//BF file
    		 for(int i=0;i<=code.length()-1;i++){
    			 if(code.charAt(i)==','){
    				 
    				 needinput=true;
    				 break;
    			 }
    		 }
    		if(needinput){
    			writer.println("Please input.");
				writer.flush();
    		}else{
    			runCodeStep2("");
    		}
    		
    	 }else{//OOK file
    		 StringBuffer newcontent=new StringBuffer();
		    	for(int i=0;i<=code.length()-1;i++){
		    		if(code.charAt(i)==' '){
		    			
		    		}else{
		    			newcontent.append(code.charAt(i));
		    		}
		    	}
		    	 String[] contentString=new String[newcontent.length()/8]; 
		    	 for(int i=0;i<=contentString.length-1;i++){
		   	    	contentString[i]=newcontent.substring(i*8,i*8+8);
		   	    }
    		 
    		 for(int i=0;i<=contentString.length-1;i++){
    			 if(contentString[i].equals("Ook.Ook!")){
    				 needinput=true;
    				 break;
    			 }
    		 }
    		
    		if(needinput){
    			writer.println("Please input.");
				writer.flush();
    		}else{
    			runCodeStep2("");
    		}
    		
    		 
    	 }
    	 
    	
    	 
     }
     public void runCodeStep2(String param){
    	 //if need input, get input from client and runCodeStep2(input)
    	 //send input and code together to BF or OOK compiler  
    	 //then get the result from the compiler and send it to client 
    	if(langarage.equals("BF")){
    		BF BFrun=new BF();
    		String result=BFrun.execute(code, param);
    		writer.println(result);
    		writer.flush();
    	}
    	else{
    		OOK OOKrun=new OOK();
    		String result=OOKrun.execute(code, param);
    		writer.println(result);
    		writer.flush();
    	}
    	needinput=false;
     }
}
