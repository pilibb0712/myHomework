import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//2018.11.03 Beibei Zhang
//a gammer analyzer of Tiny Language Granmmer based on DFA
//Tiny Language Grammer-----
//1 key word: if,then,else,end,repeat,until,read,write
//2 special symbol: :=,=,<,;
//3 comment:{xxx}
//4 calculation symbol:+,-,*,/
//5 number: all of num
//6 ID: all of letter or num, must begin with letter

public class GrammerAnalyzer {

    private static final int startState=1;
    private static final int numState=2;
    private static final int strState=3;
    //state_special for: =,<,;
    private static final int assignState=4;
    //state_assign only for: :=
    private static final int commentState=5;
    private static final int endState=6;

    private String[] keywords={"if","then","else","end","repeat","until","read","write"};
    private String[] specialSymbols={":=","=","<",";"};
    private String[] calculationSymbols={"+","-","*","/"};
    //use Table-Driven to indentify the type each token

    private BufferedReader source;
    private static final int maxBufsSize=256;
    private int currentBufSize=0;
    private String currentLine;
    private char[] currentLineBufs=new char[currentBufSize];
    private int currentLineIndex=0;
    private int currentBufPos=0;
    private boolean isEOF=false;

    public GrammerAnalyzer(String filePath){
        try {
            FileReader reader=new FileReader(filePath);
            this.source=new BufferedReader(reader);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    private void scanning(){
        while (!isEOF){
            getToken();
        }
        System.out.println("-------------------------------");
    }

    private char getNextChar(){
        char nextChar='\0';
        try {
            if (currentBufPos >= currentBufSize) {
                if ((currentLine = source.readLine()) != null) {
                    currentLineIndex++;
                    System.out.println();
                    System.out.println("line"+currentLineIndex + ": " + currentLine);
                    currentLineBufs = currentLine.toCharArray();
                    currentBufSize = currentLineBufs.length;
                    currentBufPos = 0;
                    nextChar = currentLineBufs[currentBufPos++];
                } else {
                    isEOF = true;
                }
            } else {
                nextChar = currentLineBufs[currentBufPos++];
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return nextChar;
    }

    private void readBack(int steps){
        if(!isEOF){
            currentBufPos=currentBufPos-steps;
        }
    }

    private boolean isDigit(char currentChar){
        if('0'<=currentChar && currentChar<='9'){
            return true;
        }else{
            return false;
        }
    }

    private boolean isLetter(char currentChar){
        if(('a'<=currentChar && currentChar<='z')||('A'<=currentChar && currentChar<='Z')){
            return true;
        }else{
            return false;
        }
    }

    private void getToken(){
        String token="";
        String currentToken="";
        int currentState=startState;
        boolean needSaveChar;
        while((currentState!=endState)&&(!isEOF)){
            char currentChar=getNextChar();
            needSaveChar=true;
            switch(currentState){
                case startState:
                    if(isDigit(currentChar)){
                        currentState=numState;
                    }else if(isLetter(currentChar)){
                        currentState=strState;
                    }else if(currentChar==':'){
                        currentState=assignState;
                    }else if(currentChar=='{'){
                        currentState=commentState;
                    }else if(currentChar==' '||currentChar=='\t'||currentChar=='\n'){
                        needSaveChar=false;
                    }else{
                        currentState=endState;
                    }
                    break;
                case numState:
                    if(!isDigit(currentChar)){
                        currentState=endState;
                        readBack(1);
                        needSaveChar=false;
                    }
                    break;
                case strState:
                    if(!isLetter(currentChar) && !isDigit(currentChar)){
                        currentState=endState;
                        readBack(1);
                        needSaveChar=false;
                    }
                    break;
                case assignState:
                    if(currentChar!='='){
                        currentState=endState;
                        readBack(1);
                        needSaveChar=false;
                    }else{
                        currentState=endState;
                    }
                    break;
                case commentState:
                    if(currentChar=='}'){
                        currentState=endState;
                    }
                    break;
                default:
                    System.out.println(currentLineIndex+" : Scanning Bug : state = "+currentState);
                    currentState=endState;
                    currentToken="error";
                    break;
            }
            if(needSaveChar){
                currentToken=currentToken+currentChar;
            }
            if(currentState==endState){
                token=currentToken;
                printToken(token);
            }
        }

    }

    private boolean isKeyWord(String token){
        for(int i=0;i<=keywords.length-1;i++){
            if(keywords[i].equals(token)){
                return true;
            }
        }
        return false;
    }

    private boolean isSpecialSymbol(String token){
        for(int i=0;i<=specialSymbols.length-1;i++){
            if(specialSymbols[i].equals(token)){
                return true;
            }
        }
        return false;
    }

    private boolean isCalculationSymbol(String token){
        for(int i=0;i<=calculationSymbols.length-1;i++){
            if(calculationSymbols[i].equals(token)){
                return true;
            }
        }
        return false;
    }

    private boolean isNumber(String token){
        if(token!=null){
            for(int i=0;i<=token.length()-1;i++){
                if(!isDigit(token.charAt(i))){
                    return false;
                }
            }
        }else{
            return false;
        }
        return true;
    }
    private boolean isID(String token){
        if(token!=null){
            if(isLetter(token.charAt(0))){
                for(int i=0;i<=token.length()-1;i++){
                    char c=token.charAt(i);
                    if(!(isLetter(c)) && !(isDigit(c))){
                           return false;
                    }
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
        return true;
    }

    private void printToken(String token){
        if(isKeyWord(token)){
            System.out.println("关键字："+token);
        }else if(isSpecialSymbol(token)){
            System.out.println("特殊符号："+token);
        }else if(isCalculationSymbol(token)){
            System.out.println("算数符号："+token);
        }else if(isNumber(token)){
            System.out.println("数字："+token);
        }else if(isID(token)){
            System.out.println("标识符："+token);
        }else if(token.startsWith("{")){
            System.out.println("注释："+token);
        }else if(token.equals("error")){
            System.out.println("错误："+token);
        }else{
            System.out.println("非正确字符："+token);
        }
    }

    public static void main(String[] args){
        GrammerAnalyzer test=new GrammerAnalyzer("src/test.txt");
        test.scanning();
    }

}
