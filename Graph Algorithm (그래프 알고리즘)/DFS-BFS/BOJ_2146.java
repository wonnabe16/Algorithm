package graph.bfs_dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class NodeClass{
	int r,c;
	int cnt;
	public NodeClass(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}
	public NodeClass(int r, int c, int cnt) {
		super();
		this.r = r;
		this.c = c;
		this.cnt = cnt;
	}
	
	
}
public class BOJ_2146 {

	static int N, region, ans;
	static int[][] tempArr,arr; 
	static Queue<NodeClass>[] qlist;
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,1,-1};
	static boolean[][] isvisited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		tempArr = new int[N][N];
		arr = new int[N][N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				tempArr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 섬에 번호 매기기
		region = 1;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(tempArr[i][j]==1 && arr[i][j]==0) {
					bfs(i,j);
					region++;					
				}
			}
		}
		
		// 최단 거리 다리 설치하기
		isvisited = new boolean[N][N];
		ans = Integer.MAX_VALUE;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(arr[i][j]>0 && !isvisited[i][j]) {
					isvisited[i][j] = true;
					ans = Math.min(ans, bridge(i,j));			
				}
			}
		}
		System.out.println(ans);
	}
	
	// 섬 구별하기
	private static void bfs(int i, int j) {
		Queue<NodeClass> q = new LinkedList<>();
		q.offer(new NodeClass(i,j)); 
		arr[i][j] = region;
		while(!q.isEmpty()) {
			NodeClass n = q.poll();
			
			for(int d=0;d<4;d++) {
				int nr = n.r + dx[d];
				int nc = n.c + dy[d];
				
				if(!isIn(nr,nc) || arr[nr][nc]!=0 || tempArr[nr][nc]!=1)
					continue;
				
				q.offer(new NodeClass(nr,nc));
				arr[nr][nc] = region;
				
			}
			
		}
	}
	
	// 다리 놓기
	private static int bridge(int i, int j) {
		boolean[][] visited  = new boolean[N][N];
		Queue<NodeClass> q = new LinkedList<>();
		q.offer(new NodeClass(i,j,0)); 
		visited[i][j] = true;
		while(!q.isEmpty()) {
			NodeClass n = q.poll();
			
			for(int d=0;d<4;d++) {
				int nr = n.r + dx[d];
				int nc = n.c + dy[d];
				int count = n.cnt;
				
				// 백트랙킹 :다른 섬에 닿기 전데 다리 길이가 이전에 구한 다리 길이보다 길면 중단
				if(count>ans) {
					return ans;
				}
				
				if(!isIn(nr,nc) || visited[nr][nc])
					continue;
				
				// 다른 지역에 닿으면 다리 길이 반환
				if(arr[nr][nc]>0 && arr[nr][nc]!=arr[i][j]) {
					return count;
				}else {
					count++;
					 visited[nr][nc] = true;
					q.offer(new NodeClass(nr,nc,count));
				}
			}
		}
		return 0;
	}
	
	
	private static boolean isIn(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}
}
