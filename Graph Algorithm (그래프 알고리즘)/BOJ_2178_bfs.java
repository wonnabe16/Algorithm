package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2178_bfs {
	
	
	
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
		find(0,0);
		
		System.out.println(arr[R-1][C-1]);

	}

	private static void find(int r, int c) {
		
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {r,c});
		while(!q.isEmpty()) {
			int[] d = q.poll();
			int nr = d[0];
			int nc = d[1];
			for(int i=0;i<4;i++) {
				int nextX = nr + dir[i][0];
				int nextY = nc + dir[i][1];
				
				if(!isIn(nextX,nextY) || visited[nextX][nextY] || arr[nextX][nextY]==0) continue;
				
				q.add(new int[] {nextX,nextY});
				visited[nextX][nextY] = true;
				arr[nextX][nextY] = arr[nr][nc]+1;
			}
			
		}
		
	}
	
	private static boolean isIn(int r, int c) {
		return r>=0 && r<R && c>=0 && c<C;
	}
	
	
	
}
