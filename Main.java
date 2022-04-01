package NeedlemanWunschAlgorithm;

import java.util.Scanner;

public class Main {
	public static void display(int[][] m,char[] s1,char[] s2,int r,int c) {
		
		// for displaying everything
		int k=0; 
		System.out.print("\t");
		for(int i=0;i<c-1;i++) {
			System.out.print("\t"+s2[i]);
		}
		System.out.print("\n\t");
		for(int d=0;d<s2.length*9;d++) {
			System.out.print("*");	
		}
		System.out.print("\t\n   #");
		
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {

				System.out.print("\t"+m[i][j]);

			}
			System.out.println("\n");
			for(;k<r-1;) {
				System.out.print(s1[k]+"  #");
				k++;
				break;
				
			}
			
	}
}
	
	public static int max(int d,int y,int x,int match,int mismatch,int gap,String choice) {
		int m = 0;
		int diag = 0;
		int above = 0;
		int left = 0;
		if(choice.equals("match")) {
			diag = d+match; 
			above = y+gap;
			left = x+gap;
			
			// finding the maximum from adding match to diagonal vs adding gap to the above or left entry
			
			if(diag>above) {
				if(diag>left) {
					m = diag;
				}
				else {
					m = left;
				}
			}
			else if(above>left) {
				m = above;
			}
			else {
				m = left;
			}
			return m;
		}
		else {
		 m = 0;
		 diag = d+mismatch;
		 above = y+gap;
		 left = x+gap;
		
			// finding the maximum from adding mismatch to diagonal vs adding gap to the above or left entry
		 
		if(diag>above) {
			if(diag>left) {
				m = diag;
			}
			else {
				m = left;
			}
		}
		else if(above>left) {
			m = above;
		}
		else {
			m = left;
		}
	
		//	(diag > above)?(diag>left? m= diag : m= left):(above>left?m= above: m = left);
		return m;
		}
		
	}
	public static void calculate(char[] seq1,char[] seq2,int match,int mismatch,int gap,int r,int c) {
		
		int[][] matrix = new int[r][c];
		
		for(int y=0;y<r;y++) {
			
			for(int x=0;x<c;x++) {
				
				if(y==0 || x==0) {
					if(y==0 && x!=0) {
						//setting the first letter of each column as the multiple of gap score
						matrix[0][x] = x*gap; 
					}
					else if(x==0 && y!=0){
						//setting the first letter of each column as the multiple of gap score
						matrix[y][0] = y*gap; 
					}
					else {
						matrix[0][0] =0; 
					}
				}
				// if two nucleotides match
				else if(seq1[y-1]==seq2[x-1]) { 
					matrix[y][x] = max(matrix[y-1][x-1],matrix[y-1][x],matrix[y][x-1],match,mismatch,gap,"match");
			    // if two nucleotides do not match 	
				}
				else if(seq1[y-1]!=seq2[x-1]) {
				//  result matrix = max(diagonal entry,above entry,left entry,match,mismatch,gap,choice
					matrix[y][x] = max(matrix[y-1][x-1],matrix[y-1][x],matrix[y][x-1],match,mismatch,gap,"mis");
				}
				
			}
		}
		
		display(matrix,seq1,seq2,r,c);
		
	}

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);

		int r=0;
		int c=0;
		System.out.println("Enter First Sequence: ");
		String x =scan.next();
		r= x.length();			// saving length of x in r (for rows)
		char[] seq1 = new char[r];
		
		seq1 = x.toCharArray(); // converting sequence x from string to character array
		
		System.out.println("Enter Second Sequence: ");
		String y =scan.next();
		c= y.length();			// saving length of y in c (for columns)
		char[] seq2 = new char[c];
		seq2 = y.toCharArray(); // converting sequence y from string to character array
		
		System.out.print("Enter Match Score : ");
		int match = scan.nextInt();
		
		System.out.print("Enter Mis-Match Score : ");
		int mismatch = scan.nextInt();
		
		System.out.print("Enter Gap Score : ");
		int gap = scan.nextInt();	
		
		calculate(seq1,seq2,match,mismatch,gap,r+1,c+1); // calling another method for calculation
		
	}

}
