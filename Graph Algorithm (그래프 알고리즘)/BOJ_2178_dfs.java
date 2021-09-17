package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2178 {
	
	static int R, C, result,count;
	static int[][] arr;
	static boolean[][] visited;
	static int[][] dir = {{1,0},{0,1},{0,-1},{-1,0}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		arr = new int[R][C];
		visited = new boolean[R][C];
		result = Integer.MAX_VALUE;
		for(int i=0;i<R;i++) {
			 char[]  cArr=br.readLine().toCharArray();
			for(int j=0;j<C;j++) {
				arr[i][j] = cArr[j] -'0';
			}
		}	
		visited[0][0] = true;
		find(0,0,1);
		
		System.out.println(result);

	}

	private static void find(int r, int c, int count) {
		
		
		// 기저
		if(r == R-1 && c ==C-1) {
			
			result = Math.min(result,count);
			return;
		}
		
		for(int i=0;i<4;i++) {
			int nr  = r + dir[i][0];
			int nc = c + dir[i][1];
			
			if(!isIn(nr,nc) || visited[nr][nc]) continue;
			
			if(arr[nr][nc]==1) {
				visited[nr][nc] = true;
				
				
				find(nr,nc,count+1);
				
				
				visited[nr][nc] = false;
			}
			
		}
		return;
		
	}
	
	private static boolean isIn(int r, int c) {
		return r>=0 && r<R && c>=0 && c<C;
	}
	
	
	
}
