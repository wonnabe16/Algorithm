package distance;

import java.util.Scanner;

public class BOJ_11727 {

	static int div = 10007;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		long[] dp = new long[n+1];
		dp[0]=1;
		dp[1]=1;
		for(int i=2;i<=n;i++) {
			// 짝수인 경우
			if(i%2==0)
				dp[i] = (dp[i-2]*4-1)%div;
			// 홀수인 경우
			else 
				dp[i] = (dp[i-1]*2-1)%div;
		}
		System.out.println(dp[n]);
	}

}
