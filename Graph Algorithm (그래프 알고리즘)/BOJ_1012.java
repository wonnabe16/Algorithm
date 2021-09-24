package graph_basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1012 {

	static int N, M, K;
	static int[][] arr;
	static boolean[][] visited;
	static Queue<int[]> q;
	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 상 우 하 좌

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			
			q = new LinkedList<>();
			arr = new int[N][M];
			visited = new boolean[N][M];
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());

				int c = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				// 배추 심기
				arr[r][c] = 1;
			}

			int result = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {

					// 배추를 만남
					// bfs로 배추가 모여있는 한 영역을 0으로 바꾸어줌
					if (arr[i][j] == 1 && !visited[i][j]) {
						bfs(i, j);
						result++;
					}

				}

			}

			System.out.println(result);

		}

	}

	private static void bfs(int r, int c) {

		q.offer(new int[] { r, c });
		visited[r][c] = true;
		while (!q.isEmpty()) {

			int[] p = q.poll();
			int pr = p[0];
			int pc = p[1];

			// 방문한 곳은 0으로
			
			for (int i = 0; i < 4; i++) {

				int nr = pr + dir[i][0];
				int nc = pc + dir[i][1];

				// 배추가 심어져 있는 영역이 아니라면
				if (!isIn(nr, nc) || arr[nr][nc] == 0 || visited[nr][nc])
					continue;

				visited[nr][nc] = true;
				q.offer(new int[] { nr, nc });

			}
		}

		return;
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}
