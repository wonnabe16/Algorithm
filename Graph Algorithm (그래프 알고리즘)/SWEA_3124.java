package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_3124 {

	static class Edge implements Comparable<Edge> {
		int start, end, weight;

		public Edge(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		
		@Override
		public String toString() {
			return "Edge [start=" + start + ", end=" + end + ", weight=" + weight + "]";
		}

		@Override
		public int compareTo(Edge o) {
			// 간선의 부호가 모두 같을 때
			// return this.weight, o.weight;

			// this.weight 기준
			return Integer.compare(this.weight, o.weight);
		}

	}

	static int[] parents; // 부모원소를 관리(트리처럼 사용)

	private static void make() {
		parents = new int[V+1];
		// 모든 원소를 자신을 대표자로 만듦
		for (int i = 1; i <=V; i++) {
			parents[i] = i;
		}
	}

	// a가 속한 집합의 대표자 찾기
	private static int find(int a) {
		if (a == parents[a])
			return a; // 자신이 대표자.
		return parents[a] = find(parents[a]); // 자신이 속합 집합의 대표자를 자신의 부모로 : path compression
	}

	// 두 원소를 하나의 집합으로 합치기(대표자를 이용해서 합침)
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot)
			return false; // 이미 같은 집합으로 합치지 않음
		int Min_parent = Math.min(aRoot, bRoot);
		parents[aRoot] = Min_parent;
		parents[bRoot] = Min_parent;
		return true;
	}

	static int V, E;
	static Edge[] edgeList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int Tcase = 1; Tcase <= T; Tcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			// 간선리스트 작성
			edgeList = new Edge[E];

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				edgeList[i] = new Edge(s, e, w);
			}

			Arrays.sort(edgeList); // 오름차순 정렬

			// 모든 정점을 각각의 집합으로 만
			make();

			// 간선 하나씩 시도하면서 트리 만들기
			int cnt = 0;
			//  ***간선의 크기가 커서 long으로 선언해주어야 함
			long result = 0;
			for (Edge edge : edgeList) {
				if (union(edge.start, edge.end)) {
					result += edge.weight;
					// 신장트리 완성
					if (++cnt == V - 1)
						break;
				}
			}
			System.out.println("#"+Tcase+" "+result);
		}

	}

}
