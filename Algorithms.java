package sthforyourself;

import java.nio.charset.Charset;
import java.util.ArrayList;

public class Algorithms {
	
	
	public int Ged(String input, String check){
		int a[][] = new int[input.length()+1][check.length()+1];
		for(int i = 0; i <= input.length(); i++){
			for(int j = 0; j <= check.length(); j++){
				if(i==0||j==0){
					a[i][j] = -(i+j);
				}
				else{
					a[i][j] = Math.max(a[i-1][j]-1, a[i][j-1]-1);
					a[i][j] = Math.max(a[i][j], a[i-1][j-1] + this.compare(input.charAt(i-1), check.charAt(j-1)));
				}
				System.out.print(a[i][j]+"|");
			}
			System.out.println("\n");
		}
		return a[input.length()][check.length()];
	}
	
	public int Led(String input, String check){
		int a[][] = new int[input.length()+1][check.length()+1];
		for(int i = 0; i <= input.length(); i++){
			for(int j = 0; j <= check.length(); j++){
				if(i==0||j==0){
					a[i][j] = -(i+j);
				}
				else{
					a[i][j] = Math.max(a[i-1][j]-1, a[i][j-1]-1);
					a[i][j] = Math.max(a[i][j], a[i-1][j-1] + this.compare(input.charAt(i-1), check.charAt(j-1)));
					a[i][j] = Math.max(a[i][j], 0);
				}
				System.out.print(a[i][j]+"|");
			}
			System.out.println("\n");
		}
		return a[input.length()][check.length()];
	}
	
	public int compare(char a, char b){
		if(a==b)
			return 1;
		else
			return -1;
	}
	
	public static void main(String args[]){
		Algorithms al = new Algorithms();

		System.out.println(al.Ged("cratdsa","car"));
		System.out.println(al.soundex("xxx,,y,yx?."));
		//大概就是你在这里输入两个单词，就会给你打分这两个单词的相似程度 然后有两个打分方法 led 和ged
	}
	
	
	public int twogram(String inputstring, String checkstring){
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
	
}
