package CT;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_8458 {

	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= tc; testcase++) {
			int N = Integer.parseInt(br.readLine());
			int[] len = new int[N];
			int sum=0, cnt=0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 비교를 위해 첫 번째 요소는 먼저 넣어놓기
			len[0]= Math.abs(Integer.parseInt(st.nextToken())) + Math.abs(Integer.parseInt(st.nextToken()));
			int max=len[0];
			
			// 두 번째 요소부터 맨하튼 거리 홀,짝 비교
			for (int i = 1; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				len[i]= Math.abs(Integer.parseInt(st.nextToken())) + Math.abs(Integer.parseInt(st.nextToken()));
				max=Math.max(max, len[i]);
				// 이전 요소와 홀,짝이 같지 않으면  cnt = -1
				if(len[i]%2 != len[i-1]%2) {
					cnt=-1;
				}
			}
			
			if(cnt==0) {
				while(true) {
					
					if(sum<max || (max-sum)%2!=0) {
						sum+=++cnt;
					}
					// sum(움직인 거리)이 max보다 크거나 같고, max-sum % 2의 나머지가 0의 의미는 모두 원점에 도착했다는 의미 
					else
						break;
				}
			}
			sb.append("#").append(testcase).append(" ").append(cnt).append("\n");
			
		}System.out.println(sb);
	}

}
