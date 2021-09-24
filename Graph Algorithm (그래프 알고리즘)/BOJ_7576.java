package graph_basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	int r, c;

	public Point(int r, int c) {
		super();
		this.r = r;
		this.c = c;

	}

	public int getR() {
		return r;
	}

	public int getC() {
		return c;
	}

}

public class BOJ_7576 {

	static Queue<Point> q = new LinkedList<>();
	static boolean[][] visited;
	static int[][] arr;
	static int[][] time;
	static int r, c;
	static int result;
	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		c = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		arr = new int[r][c];
		time = new int[r][c];
		for (int[] a : time) {
			// 처음엔 다 -1인 상태
			Arrays.fill(a, -1);
		}
		visited = new boolean[r][c];
		// 초기화 및 익은 토마토 큐에 저장
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < c; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());

				// 토마토가 없다면
				if (arr[i][j] == -1) {
					visited[i][j] = true;
				}
				// 익은 토마토라면
				else if (arr[i][j] == 1) {
					visited[i][j] = true;
					q.offer(new Point(i, j));
					time[i][j] = 0;
				}

			}
		}

		result = bfs();

		// 다 익지 못하는 상황이라면
		out: for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (!visited[i][j]) {
					result = -1;
					break out;
				}

			}
		}

		System.out.println(result);

	}

	private static int bfs() {

		int resultTime = 0;

		while (!q.isEmpty()) {
			Point p = q.poll();
			int r = p.getR();
			int c = p.getC();

			for (int i = 0; i < 4; i++) {
				int nr = r + dir[i][0];
				int nc = c + dir[i][1];

				// 범위를 벗어났거나 이미 방문했던 곳이거나, 토마토가 없는 곳이라면
				if (!isIn(nr, nc) || visited[nr][nc] || arr[nr][nc] != 0)
					continue;

				visited[nr][nc] = true;
				time[nr][nc] = time[r][c] + 1;
				q.offer(new Point(nr, nc));

			}

		}
		
		for(int[] a : time) {
			for(int b : a) {
				if(b>resultTime) {
					resultTime = b;
				}
			}
		}
		return resultTime;
	}

	private static boolean isIn(int R, int C) {
		return R >= 0 && R < r && C >= 0 && C < c;
	}
}
