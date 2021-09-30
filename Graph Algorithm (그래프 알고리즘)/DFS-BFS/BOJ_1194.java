package graph.bfs_dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class node {
	int r, c;
	int count, key;

	public node(int r, int c, int count, int key) {
		super();
		this.r = r;
		this.c = c;
		this.count = count;
		this.key = key;
	}

}

public class BOJ_1194 {

	static int N, M, cnt;
	static char[][] map;
	static boolean[][][] visited;
	static boolean[] key;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static node start;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M][64];
		for (int i = 0; i < N; i++) {
			char[] c = br.readLine().toCharArray();
			map[i] = c;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == '0') {
					start = new node(i, j, 0, 0);
				}
			}
		}

		bfs(start.r,start.c);

	}

	/*
	 * 0. 출구에 바로 갈 수 있는지 확인 1. 열쇠를 찾는다 2. 열쇠에 해당하는 문을 찾는다.
	 */

	private static void bfs(int r, int c) {
		Queue<node> q = new LinkedList<>();
		q.offer(new node(r, c, 0, 0));

		while (!q.isEmpty()) {
			node n = q.poll();
			int count = n.count;
			int key = n.key;

			// 출구에 도착하면 끝
			if (map[n.r][n.c] == '1') {
				System.out.println(n.count);
				return;
			}

			for (int i = 0; i < 4; i++) {
				int nr = n.r + dx[i];
				int nc = n.c + dy[i];

				// 범위 벗어나거나, 벽을 만나거나, 방문한 적 있는 경우
				if (!isIn(nr, nc) || map[nr][nc] == '#' || visited[nr][nc][key])
					continue;

				// 열쇠를 만난 경우
				if (map[nr][nc] - 97 >= 0 && map[nr][nc] - 97 < 6) {
					int tempKey = (1 << (map[nr][nc] - 97)) | key;
					
					if (!visited[nr][nc][tempKey]) {
						visited[nr][nc][tempKey] = true;
						visited[nr][nc][key] = true;
						q.offer(new node(nr, nc, count + 1, tempKey));
					}

				}
				// 문인 경우
				else if (map[nr][nc] - 65 >= 0 && map[nr][nc] - 97 < 6) {
					int tempDoor = (1 << (map[nr][nc] - 65)) & key;

					// 열쇠 있는지 확인
					if (tempDoor > 0) {
						visited[nr][nc][key] = true;
						q.offer(new node(nr, nc, count + 1, key));
					}
				}
				// 둘 다 아닌 경우
				else {
					visited[nr][nc][key] = true;
					q.offer(new node(nr, nc, count + 1, key));
				}

			}
		}
		System.out.println(-1);
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

}


/*
	// 열쇠를 만나면
	if(map[nr][nc]=='a'||map[nr][nc]=='b'||map[nr][nc]=='c'||map[nr][nc]=='d'||map[nr][nc]=='e'||map[nr][nc]=='f') {
		int keys = map[nr][nc] - 97;
		// 열쇠가 있으면 길을 열어주기
		key[keys] = true;
		map[nr][nc]='.';
	}
				
	
	// 문에 닿으면 열쇠가 있는지 확인
	if(map[nr][nc]=='A'||map[nr][nc]=='B'||map[nr][nc]=='C'||map[nr][nc]=='D'||map[nr][nc]=='E'||map[nr][nc]=='F') {
		int door = map[nr][nc] - 65;
		// 열쇠가 있으면 길을 열어주기
		if(key[door]) {
			map[nr][nc] = '.';
		}
	}
*/
