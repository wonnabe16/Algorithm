package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Shark {
	int num, r, c, s, d, z;

	public Shark(int num, int r, int c, int s, int d, int z) {
		super();
		this.num = num;
		this.r = r;
		this.c = c;
		this.s = s;
		this.d = d;
		this.z = z;
		
	}

	public void setD(int d) {
		this.d = d;
	}

	public void setR(int r) {
		this.r = r;
	}

	public void setC(int c) {
		this.c = c;
	}

	public int getNum() {
		return num;
	}

}
/*
 * 
 * 시뮬레이션은 객체지향으로 접근하는 것이 빠를 수도 있음
 */
public class BOJ_17143 {

	static int R, C, M, sum, maxS;
	static int[][] arr;
	static List<Shark> list = new ArrayList<>();
	static int[] dr = { 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[R + 1][C + 1];

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());

			
			if(d==1 || d==2) {
				s = s%((R-1)*2);
			}else {
				s = s%((C-1)*2);
			}
			

			list.add(new Shark(i, r, c, s, d, z));

			maxS = Math.max(maxS, s);

			arr[r][c] = i;

		}

		// 상어가 없으면 끝
		if (M == 0) {
			System.out.println(0);
			return;
		}

		// 상어가 있는 경우
		// 1.낚시 후 2.물고기 움직임
		// i는 낚시꾼의 위치
		boolean[] delete = new boolean[M + 1];
		int size = list.size();
		total: for (int i = 1; i <= C; i++) {
			// 1. 낚시
			out: for (int j = 1; j <= R; j++) {
				
				
				
				// 상어를 만나면 잡기
				if (arr[j][i] != 0) {
					
					Shark s = list.get(arr[j][i] - 1);
					if(delete[s.num])
						continue;
					
					sum += s.z;
					delete[arr[j][i]] = true;
					arr[j][i] = 0;
					break;
				}
			}

			if (i == C) {
				break total;
			}
			// 테두리에 닿으면 *-1
			// 2. 상어 움직이기

			// 상어의 개수 만큼
			for (int n = 0; n < size; n++) {
				Shark s = list.get(n);

				// 지워진 상어라면
				if(delete[s.num])
					continue;

				// 속도가 0 이상이라면 이동
				if (s.s > 0) {

					
					int nr = s.r;
					int nc = s.c;
					arr[nr][nc] = 0;
					// 상어 속도만큼 이동
					for (int moveS = 0; moveS < s.s; moveS++) {

						int dir = s.d;
						nr += dr[dir];
						nc += dc[dir];

						// 범위 벗어나면
						if (!isIn(nr, nc)) {
							nr += dr[dir] * -2;
							nc += dc[dir] * -2;

							// 방향 바꿔서 저장
							s.setD(change(s.d));
						}
					}
					
					//arr[nr][nc] = s.num;
					s.setR(nr);
					s.setC(nc);
				}

			}

			// 같은 위치에 있는지 확인
			arr = new int[R + 1][C + 1];
			for (int n = 0; n < size; n++) {
				Shark s = list.get(n);
				if (delete[s.num])
					continue;

				int r = s.r;
				int c = s.c;
				// 다른 상어가 있는 경우 크기 비교
				if (arr[r][c] != 0) {
					Shark bs = list.get(arr[r][c] - 1);
					// 이미 저장된 상어가 더 큰 경우
					if (bs.z > s.z) {
						delete[s.num] = true;
					} else {
						arr[r][c] = s.num;
						delete[bs.num] = true;
					}

				} else {
					arr[r][c] = s.num;
				}

			}

		}

		System.out.println(sum);

	}

	
	
	
	
	// 범위 안에 있는지 체크
	private static boolean isIn(int r, int c) {
		return r > 0 && r <= R && c > 0 && c <= C;
	}

	// 방향 전환 
	private static int change(int d) {
		int ret = 0;
		switch (d) {
		case 1:
			ret = 2;
			break;
		case 2:
			ret = 1;
			break;
		case 3:
			ret = 4;
			break;
		case 4:
			ret = 3;
			break;
		}

		return ret;
	}

}
