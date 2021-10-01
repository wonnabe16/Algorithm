package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_magnet {

	static String[] q;
	static int N,score;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <=T; tc++) {
			

			q = new String[5]; 
			N =  Integer.parseInt(br.readLine());
			
			for(int i=1;i<=4;i++) {
				st = new StringTokenizer(br.readLine());
				String s = "";
				for(int j=0;j<8;j++) {
					s += Integer.parseInt(st.nextToken());
				}
				q[i] = s;
			}

			
			for(int i=0;i<N;i++) {
				
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				int[] turn = new int[5];
				
				
				// 회전 여부 체크
				int tempStart = start; // 회전 기준점
				int tempDir = dir; // 기준점의 회전 방향 
				turn[start] = dir; 

				//오른쪽
				while(tempStart<4) {
					int l = q[tempStart].charAt(2)-'0';
					int r = q[tempStart+1].charAt(6)-'0';
					
					// 다르면 반대방향
					if(r!=l) {
						tempDir = tempDir*-1;
						turn[tempStart+1] = tempDir;
					}else {
						turn[tempStart+1]=0;
						break;
					}
					tempStart++;
				}
				
				
				// 왼쪽 회전 여부 체크 시 재초기화
				tempStart = start;
				tempDir = dir;
				//왼쪽
				while(tempStart>1) {
					int r = q[tempStart].charAt(6)-'0';
					int l = q[tempStart-1].charAt(2)-'0';
					// 다르면 반대방향
					if(r!=l) {
						tempDir = tempDir*-1;
						turn[tempStart-1] = tempDir;
					}else {
						turn[tempStart-1]=0;
						break;
						
					}
					tempStart--;
				}
				
				
				
				// 회전
				for(int t=1;t<5;t++) {
					String s = q[t];
					String ns = "";
					// 시계방향
					if(turn[t]==1) {
						ns += s.charAt(7);						
						ns += s.substring(0,7);
						q[t]=ns;
					}
					// 반 시계
					else if(turn[t]==-1) {
						ns += s.substring(1,8);
						ns += s.charAt(0);
						q[t]=ns;
					}
				}

			}
			
			
			score = 0;
			int n=1;
			for(int t=1;t<5;t++) {
				if(q[t].charAt(0) == '1') {
					score += Math.pow(2, t-1); // 점수는 위치 순서대로 1,2,4,8 점 
				}
			}
			
			
			sb.append("#"+tc+" ").append(score).append("\n");
		}

		System.out.println(sb.toString());
	}

}
