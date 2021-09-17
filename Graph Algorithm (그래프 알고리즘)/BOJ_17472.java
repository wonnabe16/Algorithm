package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17472 {

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
	static class Dot{
		int r, c;

		public int getR() {
			return r;
		}

		public int getC() {
			return c;
		}

		
		
		@Override
		public String toString() {
			return "Dot [r=" + r + ", c=" + c + "]";
		}

		Dot(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	

	static int[] parents; // 부모원소를 관리(트리처럼 사용)

	private static void make() {
		parents = new int[V + 1];
		// 모든 원소를 자신을 대표자로 만듦
		for (int i = 1; i <= V; i++) {
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

	static int V, E, size;
	static Edge[] edgeList;
	static int R, C;
	static int[][] arr;
	static Queue<Dot> q = new LinkedList<>();
	static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		
		arr = new int[R][C];
		
		for(int i=0;i<R;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<C;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j]==1) q.offer(new Dot(i,j));
			}
		}
		
		size = q.size();
		// 간선리스트 작성
		edgeList = new Edge[E];

		// 지역 정하기 
		makeRegion();
		
		
		/*for(int[] a : arr) {
			System.out.println(Arrays.toString(a));
		}*/
		
		// 간선 만들기
		bridgeCheck();
		
		
		
		
		// 정점의 개수
		V = q.size();
		// 간선 개수
		E = edgeList.length;
		
		Arrays.sort(edgeList); // 오름차순 정렬
		

		
		// 모든 정점을 각각의 집합으로 만
		make();

		// 간선 하나씩 시도하면서 트리 만들기
		int cnt = 0;
		long result = 0;
		for (Edge edge : edgeList) {
			if (union(edge.start, edge.end)) {
				result += edge.weight;
				// 신장트리 완성
				if (++cnt == V - 1)
					break;
			}
		}
		
		System.out.println(result);

	}
	
	// 지역 번호 매기기 
	private static void makeRegion() {
		int regionNum = 2;
		for(Dot d : q) {
			int r = d.getR();
			int c = d.getC();
			// 인접한 요소가 2이상의 수일때
			int num = regionNum;
			boolean flag = false;
			for(int i=0;i<4;i++) {
				int nr = r + dir[i][0];
				int nc = c + dir[i][1];
				
				if(isIn(nr,nc)) {
					if(arr[nr][nc]==0||arr[nr][nc]==1) continue;
					else if(arr[nr][nc]!=1) {
						arr[r][c] = arr[nr][nc];
						flag = true;
						break;
					}
				}
				
			}
			// 주변이 아직 탐색 전일때
			if(!flag) 
				arr[r][c] = regionNum++;
		
		}
	}
	
	
	// 연결할 수 있는지 검사
	private static void bridgeCheck() {
		
		while(!q.isEmpty()) {
			Dot d = q.poll();
			
			int r = d.getR();
			int c = d.getC();
			System.out.println(r+","+c);
			System.out.println();
			for(int count=0;count<4;count++) {
				int i=1;
				while(true) {
					int nr = r + dir[count][0]*i;
					int nc = c + dir[count][1]*i;
				
					
					if(isIn(nr,nc)) {
						if(arr[nr][nc]==0) {
							i++;
							continue;
						}else if(arr[nr][nc]==arr[r][c]){
							break;
						}else if(arr[nr][nc]!=arr[r][c] && i>=2) {
							// 다른 지역에 도착
							edgeList[i] = new Edge(arr[nr][nc],arr[r][c] , i);
							System.out.println("q input"+arr[nr][nc]+" "+arr[r][c] +" "+ i);
							break;
						}
					}else {
						break;
					}

				}
				
			}
	
			
		}
		
	}
	
	private static boolean isIn(int r, int c) {
		return r>=0 && r<R && c >= 0 && c < C;
	}

}
