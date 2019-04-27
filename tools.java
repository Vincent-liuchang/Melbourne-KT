
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
        char[] four = {'l'};
        char[] five = {'m','n'};
        char[] six = {'r'};

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
    
    public ArrayList<String> read(String filepath) throws IOException{
    	FileReader fr = new FileReader(filepath);
		BufferedReader bf = new BufferedReader(fr);
				
		ArrayList<String> misspell = new ArrayList<String>();
		
		String line = bf.readLine();
	
		while(line != null){
			misspell.add(line);
			line = bf.readLine();
		}
		bf.close();
		return misspell;
    }
    
    public void go1(){
    	try {
			ArrayList<String> misspell = this.read("C:\\Users\\cliu20\\Downloads\\misspell.txt");
			ArrayList<String> correct = this.read("C:\\Users\\cliu20\\Downloads\\correct.txt");
	    	ArrayList<String> dict = this.read("C:\\Users\\cliu20\\Downloads\\dict.txt");
	    	
	    	ArrayList<String> ged_result = this.geo(misspell, correct, dict);
	    	
	    	File result = new File("C:\\Users\\cliu20\\Downloads\\result1.txt");
	    	FileWriter fw = new FileWriter(result);
	    	BufferedWriter bfw = new BufferedWriter(fw);
	    	
	    	for(String s:ged_result){
	    		bfw.write(s);
	    		bfw.newLine();
	    		bfw.flush();	    		
	    	}
	    	bfw.close();
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    }
    
    public void go2(){
    	try {
			ArrayList<String> misspell = this.read("C:\\Users\\cliu20\\Downloads\\misspell.txt");
			ArrayList<String> correct = this.read("C:\\Users\\cliu20\\Downloads\\correct.txt");
	    	ArrayList<String> dict = this.read("C:\\Users\\cliu20\\Downloads\\dict.txt");

	    	ArrayList<String> ngram_result = this.ngram(misspell, correct, dict);
	    	
	    	File result = new File("C:\\Users\\cliu20\\Downloads\\result2.txt");
	    	FileWriter fw = new FileWriter(result);
	    	BufferedWriter bfw = new BufferedWriter(fw);
	    	
	    	for(String s:ngram_result){
	    		bfw.write(s);
	    		bfw.newLine();
	    		bfw.flush();
	    	}
	    	bfw.close();
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    }
    
    public void go3(){
    	try {
			ArrayList<String> misspell = this.read("C:\\Users\\cliu20\\Downloads\\misspell.txt");
			ArrayList<String> correct = this.read("C:\\Users\\cliu20\\Downloads\\correct.txt");
	    	ArrayList<String> dict = this.read("C:\\Users\\cliu20\\Downloads\\dict.txt");
	    	
	    	ArrayList<String> soundex_result = this.sdex(misspell, correct, dict);
	    	
	    	
	    	File result = new File("C:\\Users\\cliu20\\Downloads\\result3.txt");
	    	FileWriter fw = new FileWriter(result);
	    	BufferedWriter bfw = new BufferedWriter(fw);
	    	
	    	for(String s:soundex_result){
	    		bfw.write(s);
	    		bfw.newLine();
	    		bfw.flush();
	    	}
    		bfw.close();
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    }
    
  public ArrayList<String> geo(ArrayList<String> misspell,ArrayList<String> correct,ArrayList<String> dict){
		ArrayList<String> results = new ArrayList<String>();
	  	ArrayList<String> matched = new ArrayList<String>();
		int max;
		int count = 1;
		float precision;
		int allprenum = 0;
		float correctnum = 0;
		float precisionsum = 0;
		
		for(int i = 0; i< misspell.size(); i++){
			String mis = misspell.get(i);
			matched.clear();
			max = -10;
			precision = 0;

			for(String dic: dict){
				if(this.GlobalEditDistance(mis, dic) > max){
					max = this.GlobalEditDistance(mis, dic);
					matched.clear();
					matched.add(dic);
				}
				else if(this.GlobalEditDistance(mis, dic) == max)
					matched.add(dic);
				if(max == mis.length()){
					precision = 1;
					correctnum++;
					break;
				}
			}
			
			if(max != mis.length() && matched.contains(correct.get(i))){
				precision = (float)1/(float)matched.size();
				correctnum++;
			}
			System.out.println(count);
			count++;
			allprenum += matched.size();
			precisionsum += precision;
			results.add(mis+"\t"+matched.toString()+"\t"+max+"\t"+precision);
		}
		float accuracy =(float)correctnum/allprenum;
		results.add(String.valueOf(accuracy));
		results.add(String.valueOf(precisionsum/(count-1)));
		results.add(String.valueOf(correctnum/(count-1)));
		return results;
	}
  
  public ArrayList<String> ngram(ArrayList<String> misspell,ArrayList<String> correct,ArrayList<String> dict){
		ArrayList<String> results = new ArrayList<String>();
	  	ArrayList<String> matched = new ArrayList<String>();
		int minum;
		int count = 1;
		float precision;
		int allprenum = 0;
		float correctnum = 0;
		float precisionsum = 0;
		
		for(int i = 0; i< misspell.size(); i++){
			String mis = misspell.get(i);
			matched.clear();
			minum = 50;
			precision = 0;

			for(String dic: dict){
				if(this.twograms(mis, dic) < minum){
					minum = this.twograms(mis, dic);
					matched.clear();
					matched.add(dic);
				}
				else if(this.twograms(mis, dic) == minum)
					matched.add(dic);
				if(minum == 0){
					precision = 1;
					correctnum++;
					
					break;
				}
			}
			if(minum != 0 && matched.contains(correct.get(i))){
				precision = (float)1/(float)matched.size();
				correctnum++;
			}
			System.out.println(count);
			count++;
			allprenum += matched.size();
			precisionsum += precision;
			results.add(mis+"\t"+matched.toString()+"\t"+minum+"\t"+precision);
		}
		results.add(String.valueOf(correctnum/allprenum));
		results.add(String.valueOf(precisionsum/(count-1)));
		results.add(String.valueOf(correctnum/(count-1)));
		return results;
	}
  
  public ArrayList<String> sdex(ArrayList<String> misspell,ArrayList<String> correct,ArrayList<String> dict){
		ArrayList<String> results = new ArrayList<String>();
	  	ArrayList<String> matched = new ArrayList<String>();
	  	ArrayList<String> soundexls = new ArrayList<String>();
	  	
		int max;
		int count = 1;
		float precision;
		int allprenum = 0;
		float correctnum = 0;
		float precisionsum = 0;
		
		for(String dic: dict){
			soundexls.add(this.soundex(dic));
		}
		
		for(int i = 0; i<misspell.size(); i++){
			String mis = misspell.get(i);
			matched.clear();
			precision = 0;
			for(int j = 0; j< soundexls.size();j++){
				if(this.soundex(mis).equals(soundexls.get(j))){
					matched.add(dict.get(j));
				}
			}
			
			if(matched.contains(correct.get(i))){
				precision = (float)1/(float)matched.size();
				correctnum++;
			}
			System.out.println(count);
			count++;
			allprenum += matched.size();
			precisionsum += precision;
			results.add(mis+"\t"+this.soundex(mis)+"\t"+precision+"\t"+matched.toString());
		}
		results.add(String.valueOf(correctnum/allprenum));
		results.add(String.valueOf(precisionsum/(count-1)));
		results.add(String.valueOf(correctnum/(count-1)));
		return results;
	}
}    