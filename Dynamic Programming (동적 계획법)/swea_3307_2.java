package distance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_3307_2 {

	static int N; 
	static int[] arr, dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int testcase = 1; testcase<T+1 ; testcase++){
			N = Integer.parseInt(br.readLine());
			
			arr = new int[N+1];
			dp = new int[N+1];
			
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=1;i<N+1;i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			

			for(int i=1;i<=N;i++) {
				LIS(i);
			}
			
			int max = -1;
			for(int i=1;i<=N;i++) {
				max = Math.max(max, dp[i]);
			}
			
			System.out.println("#"+testcase+" "+max);
			
		}

	}
	
	private static int LIS(int N) {
		// 탐색하지 않았던 위치의 경우
		if(dp[N]==0) {
			dp[N]=1; // 1로 초기화
			
			for(int i=N-1;i>0;i--) {
				if(arr[N] > arr[i]) {
					dp[N] = Math.max(dp[N], LIS(i)+1);
				}
			}
		}
		// 탐색했던 위치의 경우
		return dp[N];
		
	}
}
