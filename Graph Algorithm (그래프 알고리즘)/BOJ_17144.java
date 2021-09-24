package graph_basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17144 {

	static int R,C,T,size;
	static int[] up, down;
	static int[][] arr;
	static int[][] deltas = {{0,1},{1,0},{0,-1},{-1,0}};
	static Queue<int[]> q = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		arr = new int[R][C];
		up = new int[2];
		down = new int[2];
		for(int i=0;i<R;i++) {
			 st = new StringTokenizer(br.readLine());
			for(int j=0;j<C;j++) {
				arr[i][j] =  Integer.parseInt(st.nextToken());
				// 청소기 위치
				if(arr[i][j]==-1) {
					// 첫 번째 청소기 칸
					if(up[0]==0) {
						up[0] = i;
						up[1] = j;
					}
					// 두 번째 청소기 칸
					else {
						down[0] = i;
						down[1] = j;
					}					
				}
				
			}
		}
		

		for(int i=0;i<T;i++) {
			findDust();
			size = q.size();
			spread();
			clean();
		}
		
		/*for(int[] a : arr) {
			System.out.println(Arrays.toString(a));
		}*/
		
		int sum = 0;
		for(int[] ar : arr) {
			for(int a : ar) {				
				if(a>0) sum+=a;
			}
		}
		System.out.println(sum);
		
	}
	private static void spread() {
		
		
		for(int t=0;t<size;t++) {
			int[] p = q.poll();
			int r = p[0];
			int c = p[1];
			int dust = p[2];
			int div = dust / 5; 
			int n = 0;
			
			if(div>0) {
				for(int i=0;i<4;i++) {
					int nr = r + deltas[i][0];
					int nc = c + deltas[i][1];
					
					// 범위를 벗어나거나 청소기인 경우
					if(!isIn(nr,nc) || machine(nr, nc))
						continue;
					
					arr[nr][nc] += div; 
					n++;
					
				}
			}
			
			arr[r][c] -= div * n;

		}

	}
	private static void findDust() {
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(arr[i][j]>0) {
					q.offer(new int[] {i,j,arr[i][j]});
				}
			}
		}
	}
	private static void clean() {
		// 위
		int r = up[0]-1;
		int c = up[1];
		int[][] upClean =  {{-1,0},{0,1},{1,0},{0,-1}};
		int i=0;
		while(i<4){
			int nr = r + upClean[i][0];
			int nc = c + upClean[i][1];
			// 벽에 닿으면 방향 전환
			if(!(nr>=0 && nr<=up[0] && nc>= 0 && nc<C) || arr[nr][nc]==-1) {
				i++;
				continue;
			}
			arr[r][c] = arr[nr][nc];
			r = nr;
			c = nc;
		}
		arr[r][c] = 0;
		
		// 아래
		r = down[0]+1;
		c = down[1];
		int[][] downClean =  {{1,0},{0,1},{-1,0},{0,-1}};
		i=0;
		while(i<4){
			int nr = r + downClean[i][0];
			int nc = c + downClean[i][1];
			// 벽에 닿으면 방향 전환
			if(!(nr>=down[0] && nr<R && nc>= 0 && nc<C) || arr[nr][nc]==-1) {
				i++;
				continue;
			}
			arr[r][c] = arr[nr][nc];
			r = nr;
			c = nc;
		}
		arr[r][c] = 0;
		
	}
	private static boolean isIn(int r, int c) {
		return r>=0 && r<R && c>= 0 && c<C;
	}
	private static boolean machine(int r, int c) {
		if(up[0]==r && up[1]==c || down[0]==r && down[1]==c  ) {
			return true;
		}
		return false;
	}
	
	
}
