package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_0916 {
	
	static int T, N;
	static int[][] distance;
	static final int INF = 10000;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		
		for (int tcase = 1; tcase <= T; tcase++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			distance = new int[N + 1][N + 1];


			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					int a = Integer.parseInt(st.nextToken());
					if(i==j)
						distance[i][j]  = 0;
					else if(a != 1)
						distance[i][j] = INF;
					else
						distance[i][j] =  a;
				}
			}
			


			for (int k = 1; k <= N; k++) {
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						distance[i][j] = Math.min((distance[i][k] + distance[k][j]), distance[i][j]);
					}
				}
			}
			
	

			int min = INF;
			for (int i = 1; i <= N; i++) {
				int sum = 0;
				for (int j = 1; j <= N; j++) {
					sum += distance[i][j];
				}
				min = Math.min(min, sum);
			}
			
			sb.append("#").append(tcase);
			sb.append(" ").append(min).append("\n");

		}

		System.out.println(sb.toString());
		
	}

}
