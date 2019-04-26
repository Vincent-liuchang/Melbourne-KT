import java.util.ArrayList;

public class tools {

    public int GlobalEditDistance(String inputstring, String checkstring){

        int a[][] = new int[inputstring.length()+1][checkstring.length()+1];

        for(int i = 0; i <= inputstring.length(); i++){
            for(int j = 0; j <= checkstring.length(); j++){
                if(i*j == 0)
                    a[i][j] = -i-j;
                else{
                    a[i][j] = compare(a[i-1][j]-1, a[i][j-1]-1,a[i-1][j-1] + compare(inputstring.charAt(i-1), checkstring.charAt(j-1)));
                }
            }

        }
        return a[inputstring.length()][checkstring.length()];
    }

    public int LocalEditDistance(String inputstring, String checkstring){

        int a[][] = new int[inputstring.length()+1][checkstring.length()+1];
        int localdistance = 0;

        for(int i = 0; i <= inputstring.length(); i++){
            for(int j = 0; j <= checkstring.length(); j++){
                if(i*j == 0)
                    a[i][j] = 0;
                else{
                   a[i][j] = compare(a[i-1][j]-1, a[i][j-1]-1,a[i-1][j-1] + compare(inputstring.charAt(i-1), checkstring.charAt(j-1)));
                   a[i][j] = Math.max(a[i][j], 0);
                   if(a[i][j]>localdistance)
                       localdistance = a[i][j];
                }
            }
        }
        return localdistance;
    }

    public int twograms(String inputstring, String checkstring){
        String a[] = new String[inputstring.length()-1];
        String b[] = new String[checkstring.length()-1];
        int count = 0;

        for(int i = 0; i < inputstring.length()-1; i++){
            a[i] = String.valueOf(inputstring.charAt(i))+inputstring.charAt(i+1);
        }
        for(int j = 0; j < checkstring.length()-1; j++){
            b[j] = String.valueOf(checkstring.charAt(j))+checkstring.charAt(j+1);
        }

        for(String x:a){
            for(int i = 0; i < b.length; i++){
                if(x.equals(b[i])){
                    count++;
                    b[i] = null;
                    break;
                }
            }
        }
        return (a.length + b.length - 2 * count);
    }

    public String soundex(String inputstring){
        char[] input = inputstring.toCharArray();
        ArrayList<String> output = new ArrayList<String>();
        String previous  = null;

        for(int i = 0; i< inputstring.length(); i++){
            String s = charconvert(input[i]);
            if(s.equals("x"))
                continue;
            if(previous == null){
                output.add(String.valueOf(input[i]));
                previous = "one";
                continue;
            }
            else if(!s.equals("0") && !s.equals(previous))
                output.add(s);
            previous = s;
        }
        previous ="";
        for(String a: output){
            previous = previous + a;
        }
        return previous;

    }

    public String charconvert(char input){
        char[] zero = {'a','e','i','o','u','w','h','y'};
        char[] one = {'f','p','v','b',};
        char[] two = {'c','g','j','k','q','s','x','z'};
        char[] three = {'d','t'};
        char[] four = {'i'};
        char[] five = {'m','n'};
        char[] six = {'6'};

        if(contains(input,zero))
            return "0";
        else if(contains(input,one))
            return "1";
        else if(contains(input,two))
            return "2";
        else if(contains(input,three))
            return "3";
        else if(contains(input,four))
            return "4";
        else if(contains(input,five))
            return "5";
        else if(contains(input,six))
            return "6";
        else
            return "x";
    }

    public boolean contains(char x,char [] a){
        for(char y : a){
            if(y == x)
                return true;
        }
        return false;

    }

    public int compare(char a, char b){
        if(a == b)
            return 1;
        return -1;
    }

    public int compare(int a, int b, int c){
        return Math.max(Math.max(a,b),c);
    }
}

