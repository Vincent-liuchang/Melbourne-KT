package sthforyourself;

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
		System.out.println(al.Ged("lended", "deaden"));
		//大概就是你在这里输入两个单词，就会给你打分这两个单词的相似程度 然后有两个打分方法 led 和ged
	}

}
