package graph.bfs_dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Point implements Comparable<Point>{
	int r, c, weight;

	public Point(int r, int c, int weight) {
		super();
		this.r = r;
		this.c = c;
		this.weight = weight;
	}

	public int getR() {
		return r;
	}

	public int getC() {
		return c;
	}

	public int getWeight() {
		return weight;
	}

	@Override
	public int compareTo(Point o) {
		// 오름차순 정렬
		return this.weight - o.weight;
	}
	
}

public class BOJ_4485 {

	/**
	 * 녹색 옷 입은 애가 젤다지?
	 * 0929
	 * idea : bfs + 백트랙킹 + visited[]
	 * idea : 다익스트라 
	 */
	static int N, min;
	static int[][] map;
	//static boolean[][] visited;
	static int[][] dijk;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,1,-1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int testcase=1;
		while(true) {
			N = Integer.parseInt(br.readLine());
			min = Integer.MAX_VALUE;
			// N이 0이면 종료
			if(N==0)
				break;
			map = new int[N][N];
			//visited = new boolean[N][N];
			dijk= new int[N][N];
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());	
					dijk[i][j] = Integer.MAX_VALUE;
				}
			}

			//dfs(0,0,0);
						
			sb.append("Problem ").append(testcase+": ").append(dijkstra()).append("\n");
			testcase++;
		}
		System.out.println(sb.toString());
	}
	private static int dijkstra() {
		PriorityQueue<Point> q = new PriorityQueue<Point>();
		dijk[0][0] = map[0][0];
		q.offer(new Point(0,0,map[0][0]));
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			
			for(int i=0;i<4;i++) {
				int nr = p.getR() + dx[i];
				int nc = p.getC() + dy[i];
				
				if(!isIn(nr,nc))
					continue;
				//기존 가중치보다 작은 경우
				if(dijk[nr][nc] > dijk[p.getR()][p.getC()] + map[nr][nc]) {
					dijk[nr][nc] = dijk[p.getR()][p.getC()] + map[nr][nc];
					q.offer(new Point(nr,nc,dijk[nr][nc]));
				}
			}
		}
		return dijk[N - 1][N - 1];
	}
	
	
	/*private static void dfs(int r, int c, int total) {
		
		total += map[r][c];
		
		// 기저조건 오른쪽 아래에 도착한 경우
		if(r==N-1 && c==N-1) {
			min = Math.min(min, total);
			return;
		}
		// 백트랙킹(가지치기)
		if(total>min)
			return;

		visited[r][c] = true;
		for(int i=0;i<4;i++) {
			int nr = r + dx[i];
			int nc = c + dy[i];
			
			if(!isIn(nr,nc) || visited[nr][nc])
				continue;
			
			visited[nr][nc] = true;
			dfs(nr,nc,total);
			visited[nr][nc] = false;
			
		}
		
		// 더이상 갈 수 없는 경우
		return;
	}*/
	private static boolean isIn(int r, int c) {
		return r>=0&& r<N&& c>=0 && c<N;
	}
	
}
