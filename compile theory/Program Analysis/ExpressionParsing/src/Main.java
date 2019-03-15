import java.util.Stack;

public class Main {
    private static char[] vts={'(',')','i','+','*'};
    private static char[] vns={'E','A','T','B','F'};
    private static String[] proces={"E->TA","A->+TA","A->@","T->FB","B->*FB","B->@","F->(E)","F->i"};
    private static String[] firsts={"","","","","","","",""};
    private static String[] follows={"","","","","",",",""};
    private static String[][] table={{"","","","","",""},
                               {"","","","","",""},
                               {"","","","","",""},
                               {"","","","","",""},
                               {"","","","","",""}};
    private static String test="i*i+i";
    private static boolean getEmpty=false;
    //@ stands for empty symbol

    private static boolean isVT(char target){
        for(int i=0;i<=vts.length-1;i++){
            if(vts[i]==target){
                return true;
            }
        }
        return false;
    }

    private static boolean isVN(char target){
        for(int i=0;i<=vns.length-1;i++){
            if(vns[i]==target){
                return true;
            }
        }
        return false;
    }

    private static boolean canBeEmpty(char vn){
        int index=getIndexOfVn(vn);
        String first=firsts[index];
        if(first.contains("$")){
            return true;
        }
        return false;
    }

    private static int getIndexOfVn(char vn){
       for(int i=0;i<=vns.length;i++){
           if(vns[i]==vn){
               return i;
           }
       }
       return 0;
    }

    private static int getIndexOfVt(char vt){
        for(int i=0;i<=vts.length;i++){
            if(vts[i]==vt){
                return i;
            }
        }
        return 0;
    }

    private static String union(String str1,String str2){
        String result=str1;
        if(str2==null){
            return result;
        }
        if(result!=null) {
            for (int i = 0; i <= str2.length() - 1; i++) {
                char ch = str2.charAt(i);
                    if (!result.contains(String.valueOf(ch))) {
                        result = result + ch;
                    }
            }
        }else{
            result=result+str2;
        }
        return result;
    }

    private static String getFirstOfVn(char target){
        //获得该非终结符非空的first集合
        if(target=='@'){
            getEmpty=true;
        }
        if(isVT(target)){
            return String.valueOf(target);
        }
        String result="";
        for(int i=0;i<=proces.length-1;i++){
            String proce=proces[i];
            char start=proce.charAt(0);
            char first=proce.charAt(3);

            if(start==target){
                    result=result+getFirstOfVn(first);
                }
            }
        return result;
    }

    private static void setFirsts(){
        for(int i=0;i<=proces.length-1;i++){
            String proce=proces[i];
            char start=proce.charAt(0);
            int indexOfVn=getIndexOfVn(start);
            String firstOfTheProc="";
            int k=2;
            do{
                getEmpty=false;
                k++;
                if(k==proce.length()){
                   //这个产生式右部都是非终结符，而且都能推到空
                    firstOfTheProc=firstOfTheProc+"$";
                    break;
                }
                firstOfTheProc=union(firstOfTheProc,getFirstOfVn(proce.charAt(k)));
            } while(getEmpty);
            firsts[indexOfVn]=union(firsts[indexOfVn],firstOfTheProc);
        }
    }


    private static void setFollows(){
        follows[0]=union(follows[0],"$");
        for(int i=0;i<=proces.length-1;i++){
            String proce=proces[i];
            char start=proce.charAt(0);
            for(int j=3;j<=proce.length()-1;j++) {
                char target=proce.charAt(j);
                if (isVN(target)) {
                    int indexOfStartVn = getIndexOfVn(start);
                    int indexOfTargetVn = getIndexOfVn(target);
                    String followOfTheProc = "";
                    int k = j;
                    do {
                        k++;
                        getEmpty = false;
                        if (k == proce.length()) {
                            followOfTheProc = union(followOfTheProc, follows[indexOfStartVn]);
                            break;
                        }
                        char next = proce.charAt(k);

                        if (isVN(next)) {
                            int indexOfNext=getIndexOfVn(next);
                            followOfTheProc = union(followOfTheProc, firsts[indexOfNext]);
                            if(canBeEmpty(next)){
                                getEmpty=true;
                            }
                        } else {
                            followOfTheProc = union(followOfTheProc, String.valueOf(next));
                        }
                    } while (getEmpty);
                    follows[indexOfTargetVn] = union(follows[indexOfTargetVn], followOfTheProc);
                }
            }

        }
    }

    private static void setAllTheFollows(){
        setFollows();
        setFollows();
        //运行两遍follows是要避免因为时序而导致follow求不全的问题（可能A的follow依赖于B，但是B的follow是在A的后面求）
    }
    private static void setTable(){
        //先根据是否为空产生式，决定是否需要follow
        //如果不是空产生式，就判断是否有直接产生式右部的first为vt，有就根据这个vt填入产生式
        //没有就根据右部的first vn的first集来填入产生式
        //如果发现first vn有空产生式，就用它的follow
        for(int i=0;i<=proces.length-1;i++){
            String proce=proces[i];
            char start=proce.charAt(0);
            int indexOfStart=getIndexOfVn(start);
            char firstOfProce=proce.charAt(3);
            if(firstOfProce=='@'){
                String followOfVn=follows[indexOfStart];
                for(int j=0;j<=followOfVn.length()-1;j++){
                    char follow=followOfVn.charAt(j);
                    if(follow=='$'){
                        table[indexOfStart][vts.length]=proce;
                    }else{
                        int indexOfVt=getIndexOfVt(follow);
                        table[indexOfStart][indexOfVt]=proce;
                    }

                }
            }else{
                if(isVT(firstOfProce)){
                    int index=getIndexOfVt(firstOfProce);
                    table[indexOfStart][index]=proce;
                }else {
                    int index = getIndexOfVn(firstOfProce);
                    String firstOfVn = firsts[index];
                    for (int j = 0; j <= firstOfVn.length() - 1; j++) {
                        char first = firstOfVn.charAt(j);
                        if (first == '$') {
                            String followOfVn=follows[index];
                            for(int k=0;k<=followOfVn.length()-1;k++){
                                char follow=followOfVn.charAt(k);
                                if(follow=='$'){
                                    table[indexOfStart][vts.length]=proce;
                                }else{
                                    int indexOfVt=getIndexOfVt(follow);
                                    table[indexOfStart][indexOfVt]=proce;
                                }
                            }
                            table[indexOfStart][vts.length] = proce;
                        } else {
                            int indexOfVt = getIndexOfVt(first);
                            table[indexOfStart][indexOfVt] = proce;
                        }
                    }
                }
            }
        }
    }

    private static void printProces(){
        System.out.println("proces:");
        for(int i=0;i<=proces.length-1;i++){
            String proce=proces[i];
            System.out.println(proce);
        }
        System.out.println();
    }

    private static void printFirst(){
        System.out.println("firsts:");
        for(int i=0;i<=vns.length-1;i++){
            char vn=vns[i];
            String first=firsts[i];
            System.out.println(vn+" : "+first);
        }
        System.out.println();
    }

    private static void printFollow(){
        System.out.println("follows:");
        for(int i=0;i<=vns.length-1;i++){
            char vn=vns[i];
            String follow=follows[i];
            System.out.println(vn+" : "+follow);
        }
        System.out.println();
    }

    private static void printTable(){
        System.out.println("parsing table:");
        System.out.print("      ");
        for(int i=0;i<=vts.length-1;i++){
            System.out.print(vts[i]+"           ");
        }
        System.out.print('$');
        System.out.println();
        for(int i=0;i<=vns.length-1;i++){
            System.out.print(vns[i]+"   ");
            for(int j=0;j<=vns.length;j++){
                if(table[i][j].equals("")){
                    System.out.print("             ");
                }else {
                    System.out.print(table[i][j] + "       ");
                }
            }
            System.out.println();
        }
    }
    private static String getStackContent(Stack stack){
        //stack居然不是值传递，而是指针传递！
        String result="";
        if(stack==null){
            return "null";
        }else{
            for(int i=0;i<=stack.size()-1;i++){
                result=result+(char)stack.elementAt(i);
            }
        }
        return result;
    }
    private static int analyze() {
        System.out.println("analyze process: ");
        test = test + "$";
        Stack stack = new Stack();
        stack.push('$');
        stack.push(vns[0]);
        int i = 0;
        while (!stack.isEmpty()) {
            System.out.print("stack: "+getStackContent(stack) + " ------- ");
            int indexOfVt = vts.length;
            char current = (char) stack.peek();
            stack.pop();
            char target = test.charAt(i);
            if (!(target == '$')) {
                indexOfVt = getIndexOfVt(target);
            }
            if (current == target) {
                i++;
                if (current == '$') {
                    System.out.println("$ ------ end");
                    return 1;//end
                }
            }else if (isVN(current)) {
                    int indexOfVn = getIndexOfVn(current);
                    if (!table[indexOfVn][indexOfVt].equals("")) {
                        String proce = table[indexOfVn][indexOfVt];
                        System.out.println("proce: "+proce + " ------- testStr: " + test.substring(i));
                        for (int j = proce.length() - 1; j >= 3; j--) {
                            char ch = proce.charAt(j);
                            if (ch != '@') {
                                stack.push(ch);
                            }
                        }
                    } else {
                        System.out.println("1:ERROR");
                        return 0;
                        //wrong expression
                    }
                } else {
                    System.out.println("2:ERROR");
                    return 0;
                    //wrong expression
                }

            }
            return 1;
        }
    public static void main(String[] args){
        printProces();
        setFirsts();
        printFirst();
        setAllTheFollows();
        printFollow();
        setTable();
        printTable();
        analyze();
    }

}
