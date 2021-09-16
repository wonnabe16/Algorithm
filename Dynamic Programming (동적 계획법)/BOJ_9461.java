package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BOJ_9461 {

	static int T,N;
	static long[] dp = new long[101];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		dp[0] = 0;
		dp[1] = dp[2] = dp[3] = 1;
		dp[4] = dp[5] = 2;
		for(int i=6;i<101;i++) {
			dp[i] = dp[i-1] + dp[i-5];
		}
		
		for(int i=0;i<T;i++) {
			N = Integer.parseInt(br.readLine());
			
			System.out.println(dp[N]);
		}
		

	}

}
