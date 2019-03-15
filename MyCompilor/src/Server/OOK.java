package Server;

import java.rmi.RemoteException;

public class OOK {
	 String content="";
	    String input="";
	    char[] saveresult=new char[20];
	    int pointer=0;
	    int inputIndex=0;
	    int contentIndex=0;
	    String[] contentString; 
	    char[] inputchars;
	    StringBuffer result=new StringBuffer();
	    Boolean wrongchar=false;
	    public void setContent(String content){
	    	 this.content=content;
	    	
	     }
	    public void setInput(String input){
	    	this.input=input;
	    }
	    public void initial(){
	    	for(int i=0;i<=saveresult.length-1;i++){
	    		saveresult[i]=0;
	    	}
	    	 inputchars=new char[input.length()];
	    	 for(int i=0;i<=input.length()-1;i++){
	    		 inputchars[i]=input.charAt(i);
	    	 }
	    	 
	    	 StringBuffer newcontent=new StringBuffer();
		    	for(int i=0;i<=content.length()-1;i++){
		    		if(content.charAt(i)==' '){
		    			
		    		}else{
		    			newcontent.append(content.charAt(i));
		    		}
		    	}
		    	if((newcontent.length()%8)!=0){
		    		wrongchar=true;
		    	}
		    	 contentString=new String[newcontent.length()/8]; 
		    	 for(int i=0;i<=contentString.length-1;i++){
		   	    	contentString[i]=newcontent.substring(i*8,i*8+8);
		   	    }
	    }
	    public void run(){
	    	 initial();
	    	 while(contentIndex<contentString.length){
	    		 if(wrongchar){
	    			break; 
	    		 }
	    		 else{
	    		 handle();
	    	     contentIndex++;
	    		 }
	    	 }
	     }
	   
	    public void handle(){
	    	switch(contentString[contentIndex]){
	    	case "Ook!Ook.":{
	    		output();
	    		break;
	    	}
	       case "Ook.Ook!":{
	    		input();
	    		break;
	    	}
	       case "Ook?Ook.":{
		       pointerminus();
		       break;
	        }
	       case "Ook.Ook?":{
		       pointerplus();
		       break;
	        }
	      case "Ook.Ook.":{
		       plus();
		       break;
	        }
	      case "Ook!Ook!":{
		        minus();
	        	break;
	        }
	      case "Ook!Ook?":{
		       whilebegin();
		       break;
	       }
	      case "Ook?Ook!":{
	    	   whileend();
		       break;
	      }
	      default:{
	    	  wrongchar=true;
	      }
	    	
	    	}
	   
	    }
	    public void output(){
	    	result.append(saveresult[pointer]);
	    }
	    public void input(){
	       
	    	saveresult[pointer]=inputchars[inputIndex];
	    	inputIndex++;
	    }
	    
	    public void pointerminus(){
	    	pointer--;
	    }
	    public void pointerplus(){
	    	pointer++;
	    }
	    public void plus(){
	    //	System.out.println("pointer:"+pointer);
	   // 	System.out.println("contentIndex:"+contentIndex);
	    	saveresult[pointer]++;
	    }
	    public void minus(){
	    	saveresult[pointer]--;
	    }
	    public void whilebegin(){
	    	if(saveresult[pointer]==0){
	    		contentIndex=findrightbracket(contentIndex,contentIndex);
	        }
	    	else{
	    		
	    	}
	    }
	    public int findrightbracket(int initial,int begin){
	    	int middlelefts=0;
	    	int middlerights=0;
	    	int end=0;
	    	for(int i=begin+1;i<=contentString.length-1;i++){
	    		if(contentString[i].equals("Ook?Ook!")){
	    		end=i;
	    		break;
	    		}
	    	}
	    	for(int i=initial;i<=end;i++){
	    		if(contentString[i]=="Ook!Ook?"){
	    			middlelefts++;
	    		}
	    		if(contentString[i].equals("Ook?Ook!")){
	    			middlerights++;
	    		}
	    	}
	    	if(middlerights==middlelefts){
	    		return end;
	    	}
	    	else{
	    		begin=end;
	    		return findrightbracket(initial,begin);
	    	}
	    	
	    }
	    
	    public void whileend(){
	    	if(saveresult[pointer]!=0){
	    		contentIndex=findleftbracket(contentIndex,contentIndex);
	    	}
	    	else{
	    		
	    	}
	    }
	    public int findleftbracket(int initial,int begin){
	    	int middlelefts=0;
	    	int middlerights=0;
	    	int end=0;
	    	for(int i=begin-1;i>=0;i--){
	    		if(contentString[i].equals("Ook!Ook?")){
	    		end=i;
	    		break;
	    		}
	    	}
	    	for(int i=initial;i>=end;i--){
	    		if(contentString[i].equals("Ook!Ook?")){
	    			
	    			middlelefts++;
	    		}
	    		if(contentString[i].equals("Ook?Ook!")){
	    			middlerights++;
	    		}
	    	}
	     	begin=end;
	     	if(middlerights==middlelefts){
	             return begin;
	    	}
	    	else{
	    		return findleftbracket(initial,begin);
	    	}
	    	
	    }
	 
	    public String execute(String code, String param){
			setContent(code);
			setInput(param);
			run();
			if(wrongchar){
				return "wrong characters!";
			}else{
			return result.toString();
			}
		}
}
