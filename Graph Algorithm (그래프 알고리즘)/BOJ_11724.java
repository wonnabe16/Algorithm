package graph_basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_11724 {

	static int N,M,count;
	static int[][] map;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N+1][N+1];
		visited = new boolean[N+1];
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			map[start][end] = map[end][start] = 1; 
			
		}
		
		for(int i=1;i<N;i++) {
			if(!visited[i]) {
				dfs(i);
				count++;
			}
		}
		System.out.println(count);
		
	}
	
	private static void dfs(int idx) {

		visited[idx] = true;
		for(int i=1;i<N;i++) {
			// 길이 있고 방문한 적이 없는 노드라면
			if(map[idx][i]==1 && !visited[i]) {
				dfs(i);
			}
		}
	}

}
