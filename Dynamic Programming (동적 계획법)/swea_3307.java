package distance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea_3307 {

	
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
			
			// 자기 자신은 무조건 가질 수 있음
			Arrays.fill(dp, 1);
			
			for(int i=2;i<=N;i++) {
				for(int j=i-1;j>0; j--) {
					if(arr[i]> arr[j]) {
						dp[i] = Math.max(dp[j]+1, dp[i]);
					}
				}
				
			}
			
			int max = -1;
			for(int i=1;i<=N;i++) {
				max = Math.max(max, dp[i]);
			}
			
			
			System.out.println("#"+testcase+" "+max);
			
		}

	}

}
