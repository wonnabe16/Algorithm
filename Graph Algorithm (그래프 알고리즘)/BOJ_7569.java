package graph_basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7569 {

	static int M, N, H, result, max;
	static int[][][] arr, count;
	static boolean[][][] visited;
	static Queue<int[]> q = new LinkedList<>();
	// 6 방향
	static int[][] deltas = {{0,0,1},{0,1,0},{0,0,-1},{0,-1,0},{1,0,0},{-1,0,0}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 가로
		M = Integer.parseInt(st.nextToken());
		// 세로
		N = Integer.parseInt(st.nextToken());
		// 높이
		H = Integer.parseInt(st.nextToken());
		
		arr = new int[H][N][M];
		count = new int[H][N][M];
		visited = new boolean[H][N][M]; 
		for(int[][] rc : count) {
			for(int[] r : rc) {
				Arrays.fill(r, -1);
			}
		}
		
		for(int h=0;h<H;h++) {
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<M;j++) {
					arr[h][i][j] =  Integer.parseInt(st.nextToken());
					// 익은 토마토인 경우 1
					if(arr[h][i][j] == 1) {
						q.add(new int[] {h,i,j});
						count[h][i][j] = 0;
						//visited[h][i][j] = true;
					}else if(arr[h][i][j] == -1){
						// 토마토가 없는 경우 -1
						count[h][i][j] = 0;
						//visited[h][i][j] = true;
					}
				}
			}
		}
		max = -1;
		bfs();
		result = max;
		boolean flag = false;
		/*out : for(boolean[][] rc : visited) {
			for(boolean[] r : rc) {
				for(boolean c : r) {
					if(!c) {
						flag = true;
						break out;
					}
				}
			}
		}*/
		
		out : for(int[][] rc : count) {
			for(int[] r : rc) {
				for(int c : r) {
					if(c==-1) {
						flag = true;
						break out;
					}
				}
			}
		}
		
		if(flag) {
			result = -1;
		}else if(!flag && result ==-1) {
			result = 0;
		}
		System.out.println(result);
		
		
	}
	
	private static void bfs() {
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			int h = p[0];
			int r = p[1];
			int c = p[2];
			
			for(int i=0;i<6;i++) {
				int nh = h + deltas[i][0];
				int nr = r + deltas[i][1];
				int nc = c + deltas[i][2];
				
				// 범위안에 없거나 방문했던 경우
				if(!isIn(nr,nc,nh) || count[nh][nr][nc]!=-1)
					continue;
				
				count[nh][nr][nc] = count[h][r][c]+1;
				max = Math.max(max, count[nh][nr][nc]);
				//visited[nh][nr][nc] = true;
				q.add(new int[] {nh, nr, nc});
				
			}
			
		}
		
	}

	private static boolean isIn(int r, int c, int h) {
		return r>=0 && r<N && c >= 0 && c< M && h>=0 && h<H;
	}
}
