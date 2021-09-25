package graph.bfs_dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
public class BOJ_1325 {
	static boolean[] visited;
	static int[] ans;
	static List<Integer>[] list;
	static int N,M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		ans = new int[N+1];
		for(int i=1;i<=M;i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			list[s].add(e);
		}
		
		for(int i=1;i<=N;i++) {
			visited = new boolean[N+1];
			bfs(i);
		}
		
		int index = -1;
		for(int k=0;k<2;k++) {
			int max = -1;
			for (int i = 1; i <= N; i++) {
				if(ans[i] > max) {
					// 한 번 출력했으면 넘어가기
					if(index==i)
						continue;
					max =ans[i];
					index = i;
				}				
			}
			sb.append(index + " ");
			
			
		}
		
		System.out.println(sb.toString());
		
	}
	private static void bfs(int s) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(s);
		visited[s] = true;
		while(!q.isEmpty()) {
			int start = q.poll();
			for(int end : list[start]) {
				if(!visited[end]) {
					ans[end]++;
					visited[end] = true;
					q.offer(end);
				}
			}
		}
		
	}
}




/*public class BOJ_1325 {
	static int N;
	static int M;
	static List<Integer>[] list;
	static int[] visited = new int[10001];
	static int[] ans = new int[10001];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input);
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new int[N + 1];
		ans = new int[N + 1];
		list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		String[] inputs = new String[2];
		for (int i = 1; i <= M; i++) {
			input = br.readLine();
			inputs = input.split(" ");
			list[Integer.parseInt(inputs[0])].add(Integer.parseInt(inputs[1]));
		}
		// DFS
		// for (int i = 1; i <= N; i++)
		// { // visited = new int[N+1];
		// visited[i] = 1; // dfs(i);
		// }
		// BFS
		for (int i = 1; i <= N; i++) {
			visited = new int[N + 1];
			bfs(i);
		}
		//System.out.println(Arrays.toString(ans));
		// answer 
		int max = 0;
		for (int i = 1; i <= N; i++) {
			max = Math.max(max, ans[i]);
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 1; i <= N; i++) {
			if (max == ans[i])
				sb.append(i + " ");
		}
		System.out.println(sb.toString().trim());
		// 컴퓨터당 해킹최대개수
		// System.out.println(Arrays.toString(ans));
	}

	private static void bfs(int node) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(node);
		visited[node] = 1;
		while (queue.isEmpty() == false) {
			node = queue.poll();
			for (int next : list[node]) {
				//System.out.println(node+"번째 :"+next);
				if (visited[next] == 0) {
					// System.out.println(node + " <- " + next);
					ans[next]++;
					visited[next] = 1;
					queue.add(next);
				}
			}
		}
	}

	private static void dfs(int node) {
		for (int next : list[node]) {
			if (visited[next] == 0) {
				// System.out.println(node + " -> " + next);
				ans[next]++;
				visited[next] = 1;
				dfs(next);
			}
		}
	}
}*/
