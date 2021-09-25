package graph.bfs_dfs;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3055 {

	static int R, C, DR, DC, SR, SC, time,size;
	static boolean flag = false;
	static char[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static Queue<int[]> q = new LinkedList<>();
	static Queue<int[]> sq = new LinkedList<>();
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		visited = new boolean[R][C];
		map = new char[R][];
		for (int i = 0; i < R; i++) {
			char[] a = br.readLine().toCharArray();
			map[i] = a;
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				// 동굴 위치 저장
				if (map[i][j] == 'D') {
					DR = i;
					DC = j;
				}
				// 고슴도치 위치
				else if (map[i][j] == 'S') {
					SR = i;
					SC = j;
					map[i][j] = '.';
					sq.offer(new int[] { i, j });
					visited[i][j] = true;
				}
				// 물 위치
				else if (map[i][j] == '*') {
					q.offer(new int[] { i, j });
				}
			}
		}

	
		
		while(!flag) {
		    size = q.size();
			water();
            
			size = sq.size();
			moveS();
			
			time++;
		}

		

	}

	private static void water() {
		// BFS
		while (size>0) {
			int[] p = q.poll();
			int r = p[0];
			int c = p[1];

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				if (!isIn(nr, nc) || map[nr][nc] == 'X' || map[nr][nc] == 'D'|| map[nr][nc] == '*')
					continue;

				map[nr][nc] = '*';
				q.offer(new int[] {nr, nc });

			}
			size--;
			
		}
	}

	private static void moveS() {
		// BFS
        // 탐색할 지점이 없으면 KAKTUS출력 후 종료
		if(sq.isEmpty()) {
			System.out.println("KAKTUS");
			flag = true;
			return;
		}
		
		while(size>0) {
			int[] p = sq.poll();
			int r = p[0];
			int c = p[1];

			// 종료조건
			if (map[r][c] == 'D') {
				System.out.println(time);
				flag = true;
				return;
			}

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				
				if (!isIn(nr, nc) || map[nr][nc] == 'X' || map[nr][nc] == '*' || visited[nr][nc])
					continue;

				// 표시는 하지 말고,큐에만 넣기
				visited[nr][nc] = true;
				sq.offer(new int[] { nr, nc });

			}
			size--;
		}

	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}

}
