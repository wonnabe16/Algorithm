package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1697 {

	static int N, K, result;
	static int[] point = { 1, -1, 2 };
	static boolean check = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 수빈이의 위치
		N = Integer.parseInt(st.nextToken());
		// 동생의 위치
		K = Integer.parseInt(st.nextToken());

		if (N == K)
			result = 0;
		else if (N > K)
			result = N - K;
		else
			bfs();

		System.out.println(result);

	}

	private static void bfs() {

		int[] visited = new int[100001];

		Queue<Integer> queue = new LinkedList<>();
		// 수빈이 위치에서 시작
		queue.add(N);
		visited[N] = 0;

		out: while (!queue.isEmpty()) {

			int index = queue.poll();

			for (int i = 0; i < 3; i++) {

				int newIndex;

				// i==2인 경우에만 X2
				if (i == 2)
					newIndex = index * point[i];
				// 1칸을 앞으로 가거나 뒤로 이동
				else
					newIndex = index + point[i];

				// 처음 K 가 발견되면 break;
				if (newIndex == K) {
					result = visited[index]+ 1;
					return;
				}

				// 범위를 벗어나거나 방문 했던 곳은 패스
				if (newIndex < 0 || newIndex >= visited.length || visited[newIndex] != 0)
					continue;

				visited[newIndex] = visited[index]+ 1;
				queue.add(newIndex);
			}
		}

	}

}
