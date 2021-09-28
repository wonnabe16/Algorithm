package CT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_6026 {

	static int M, N;
	static long[] fac;
	static long MOD = 1000000007;
	static private void factorial() {
		
		fac = new long[101];
		fac[0]=fac[1]=1;
		
		for(int i=2;i<101;i++) {
			fac[i] =  (fac[i-1]* i)%MOD;
		}
		
		return;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		factorial();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			long total = solve();			
			sb.append("#"+tc+" ").append(total).append("\n");
		}
		System.out.println(sb.toString());
	}
	//함수의 개수 : ∑(-1)^i * kCi * (k-i)^n;
	static long solve() {
		long total=0;
		for(int i=0;i<=M;i++) {
			long l1 = i%2==0 ? 1 : -1;
			long l2 = nCr(i);
			long l3 = pow((M-i),N);
			long result = ((l1*l2)%MOD*l3)%MOD;
			total = (total + result + MOD) % MOD;
		}
		
		return total;
	}
	// nCr = n!/((n-r)! * r!)%MOD  ->  (n!*((n-r)! * r!)^(MOD-2)) % MOD 
	static long nCr(int r) {
		if(r==0) {
			return 1;
		}
		
		long l1 = fac[M];
		long l2 = pow(fac[M-r], MOD-2);
		long l3 = pow(fac[r], MOD-2);
				
		return ((l1 * l2)%MOD*l3)%MOD;
		
	}
	
	
	static long pow(long a, long b) {
		
		if(b==1) {
			return a % MOD;
		}
		long half = pow(a, b/2);
		if(b%2==0) {
			return (half * half)%MOD;
		}else {
			return ((half * half)%MOD * a)%MOD;
		}
		
	}

}
