package graph.bfs_dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1953 {

	static int N, M, R, C, L, cnt;
	static int[] dr = { -1, 0, 1, 0 }; // 상, 우, 하, 좌
	static int[] dc = { 0, 1, 0, -1 };
	static int[][] arr;
	static boolean[][] visited;
	// 상, 우, 하, 좌
	static boolean[][] deltas = { { false, false, false, false }, { true, true, true, true },
			{ true, false, true, false }, { false, true, false, true }, { true, true, false, false },
			{ false, true, true, false }, { false, false, true, true }, { true, false, false, true } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			arr = new int[N][M];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());

				}

			}

			cnt = 1;
			bfs(R, C);
			


			// 출력
			sb.append("#" + tc + " ").append(cnt).append("\n");

		}
		System.out.println(sb.toString());

	}

	private static void bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		visited = new boolean[N][M];
		q.offer(new int[] { r, c, 1 });
		visited[r][c] = true;

		while (!q.isEmpty()) {
			int[] point = q.poll();
			int dir = arr[point[0]][point[1]];
			int time = point[2];

			if (time == L)
				continue;

			for (int i = 0; i < 4; i++) {

				// 터널이 갈 수 없는 길인 경우
				if (!deltas[dir][i])
					continue;

				int nr = point[0] + dr[i];
				int nc = point[1] + dc[i];

				// 범위 벗어나면 패스
				if (!isIn(nr, nc))
					continue;

				int newDir = arr[nr][nc];
				int newI = (i + 2) % 4;

				// 길이 막혔다면
				if (!deltas[newDir][newI])
					continue;

				// 방문 했었다면 패스
				if (visited[nr][nc])
					continue;

				visited[nr][nc] = true;
				q.offer(new int[] { nr, nc, time + 1 });
				cnt++;

			}

		}
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

}
