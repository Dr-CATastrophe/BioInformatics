package NeedlemanWunschAlgorithm;

public class Main {
	
	public static int max(int d,int y,int x,int mismatch,int gap) {
		int m = 0;
		int diag = d+mismatch;
		int above = y+gap;
		int left = x+gap;
		
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
	public static void calculate(char[] seq1,char[] seq2,int match,int mismatch,int gap,int r,int c) {
		
		int[][] matrix = new int[r][c];
		
		for(int y=0;y<r;y++) {
			
			for(int x=0;x<c;x++) {
				
				if(y==0 || x==0) {
					if(y==0 && x!=0) {
						matrix[0][x] = x*gap;
					}
					else if(x==0 && y!=0){
						matrix[y][0] = y*gap;
					}
					else {
						matrix[0][0] =0; 
					}
				}
				
				else if(seq1[y-1]==seq2[x-1]) {
					matrix[y][x] = matrix[y-1][x-1]+match;
					
				}
				else if(seq1[y-1]!=seq2[x-1]) {
					matrix[y][x] = max(matrix[y-1][x-1],matrix[y-1][x],matrix[y][x-1],mismatch,gap);
				}
				
			}
		}
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				System.out.print(matrix[i][j]+"\t");

			}
			System.out.println("\n");
		}
		
	}

	public static void main(String[] args) {
		

		int r=6;
		int c=7;
		char[] seq1 = new char[r];
		
		String x ="GATCT";
		
		seq1 = x.toCharArray();
		
		String y ="GCATTC";
		
		char[] seq2 = new char[c];
				
		seq2 = y.toCharArray();
		
		int gap = -3;
		int mismatch = 2;
		int match = 5;
		
		
		calculate(seq1,seq2,match,mismatch,gap,r,c);
		
	}

}
