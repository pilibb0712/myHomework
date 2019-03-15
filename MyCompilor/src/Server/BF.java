package Server;

import java.rmi.RemoteException;

public class BF {
	String content="";
	String input="";
    char[] saveresult=new char[20];
    int pointer=0;
    int contentIndex=0;
    int inputIndex=0;
    char[] contentchars; 
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
   	 //remove ' ' in content
   	   contentchars=new char[newcontent.length()];
   	   for(int i=0;i<=newcontent.length()-1;i++){
  	    	contentchars[i]=newcontent.charAt(i);
  	    }
    	
        }
    public void run(){
    	 initial();
    	 while(contentIndex<contentchars.length){
    		 if(wrongchar){
    			 System.out.println("Wrong characters");
    			 break;
    		 }
    		 else{
    		 handle();
          	 contentIndex++;
    		 }
    	 }
     }
   
    public void handle(){
    	switch(contentchars[contentIndex]){
    	case '.':{
    		point();
    		break;
    	}
       case ',':{
    		comma();
    		break;
    	}
       case '<':{
	       leftarrow();
	       break;
        }
       case '>':{
	       rightarrow();
	       break;
        }
      case '+':{
	       plus();
	       break;
        }
      case '-':{
	        minus();
        	break;
        }
      case '[':{
	       leftbracket();
	       break;
       }
      case ']':{
    	   rightbracket();
	       break;
      }
    
      default:{
    	  wrongchar=true;
    	  
      }
    	
    	}
   
    }
    public void point(){
    	result.append(saveresult[pointer]);
    }
    public void comma(){
       
    	saveresult[pointer]=inputchars[inputIndex];
    	inputIndex++;
    }
    public void leftarrow(){
    	if(pointer==0){
    		System.out.println("Over~");
    		System.exit(1);
    		
    	}
    	else{
    	pointer--;
    	}
    }
    public void rightarrow(){
    	pointer++;
    }
    public void plus(){
         saveresult[pointer]++;
    }
    public void minus(){
    	saveresult[pointer]--;
    }
    public void leftbracket(){
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
    	for(int i=begin+1;i<=contentchars.length-1;i++){
    		if(contentchars[i]==']'){
    		end=i;
    		break;
    		}
    	}
    	for(int i=initial;i<=end;i++){
    		if(contentchars[i]=='['){
    			middlelefts++;
    		}
    		if(contentchars[i]==']'){
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
    public void rightbracket(){
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
    		if(contentchars[i]=='['){
    		end=i;
    		break;
    		}
    	}
    	for(int i=initial;i>=end;i--){
    		if(contentchars[i]=='['){
    			
    			middlelefts++;
    		}
    		if(contentchars[i]==']'){
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
