package graph.bfs_dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11404 {

	static int N, bus;
	static int INF = 987654321;
	static int[][]arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		bus = Integer.parseInt(br.readLine());
		arr = new int[N+1][N+1];

		// 간선이 없는 경우는 INF, i==j인 경우는 0 
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				if(i==j)
					arr[i][j]=0;
				else {
					arr[i][j] = INF;
				}
			}
		}
		
		for(int i=0;i<bus;i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			// 이미 해당 간선에 비용이 저장되어있는 경우 
			if(arr[s][e]!=0) {
				// 최솟값을 선택해 저장
				arr[s][e] = Math.min(arr[s][e],cost);
			}else {				
				arr[s][e] = cost;
			}
		}
		
		// 플로이드-워셜 알고리즘 
		for(int k=1;k<=N;k++) {
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=N;j++) {
					if(arr[i][k] + arr[k][j] < arr[i][j]) {
						arr[i][j] = arr[i][k] + arr[k][j];
					}
				}
			}
		}
		
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				// 갈 수 없는 길인 경우는 0 출력
				if(arr[i][j] ==INF)
					sb.append(0).append(" ");
				else
					sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		
	}
	
}
