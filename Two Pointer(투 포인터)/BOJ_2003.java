package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2003 {

	static int N,count;
	static long div = 100_000_007;
	static long M;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] =  Integer.parseInt(st.nextToken());
		}
		
		// front 1번째 요소부터 마지막까지 검사 
		// end는 S를 넘는 그 요소 
		partSum();
				
		System.out.println(count);
	

	}
	private static void partSum() {

		
		/*
		 * start = 0, end = 1 
		 * 6 1 
		 * 1 1 1 1 1 1
		 * 에서 답이 5가 나옴(첫 번째 요소를 세지 않아서 에러 발생) 
		 * 
		 * 
		 */

		int start = 0; int end = 0;
		// front 1번째 요소부터 마지막까지 검사 
		while(end<N) {
			long sum=0;

			for(int i=start;i<=end;i++) {
				sum += arr[i];
			}
	
			if(sum>=M) {
				if(sum==M) 
					count++;
				start++;
				
			}else {
				end++;
			}
		}
		
	}

}
