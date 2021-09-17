package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2606 {

	static class node {
		int s, e;

		node(int s, int e) {
			super();
			this.s = s;
			this.e = e;
		}

		public int getS() {
			return s;
		}

		public int getE() {
			return e;
		}

	}

	static int V, E;
	static int[][] arr;
	// static List<node> list = new ArrayList<>();
	static Queue<Integer> q = new LinkedList<>();
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		V = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());
		arr = new int[V + 1][V + 1];
		visited = new boolean[V + 1];
		for (int i = 0; i < E; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			arr[e][s] = arr[s][e] = 1;
		}
		q.offer(1);

		bfs();

		int result = 0;
		for (int i = 2; i <= V; i++) {
			if (visited[i])
				result++;
		}
		System.out.println(result);

	}

	private static void bfs() {
		visited[1] = true;
		while (!q.isEmpty()) {
			int s = q.poll();

			for (int i = 1; i <= V; i++) {
				// 연결되어 있음
				if (arr[s][i] == 1 && visited[i] == false) {

					q.offer(i);
					visited[i] = true;

				}
			}
			
			

		}

	}

}
