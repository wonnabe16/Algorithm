package graph.floyd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_9205_2 {

	static class Pos {
		int x;
		int y;
		
		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < T; i++) {
			
			int n = Integer.parseInt(br.readLine());
			Pos[] pos = new Pos[n+2];
			StringTokenizer st;
			for (int j = 0; j < n+2; j++) {
				st = new StringTokenizer(br.readLine()," ");
				pos[j] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			boolean[] v = new boolean[n+2];
			
			Pos start = pos[0];
			Pos end = pos[n+1];
			
			Queue<Pos> que = new LinkedList<>();
			que.add(start);
			boolean ans = false;
			while(!que.isEmpty()) {
				Pos now = que.poll();
				
				if(now.equals(end)) {
					ans = true;
					break;
				}
				for (int j = 1; j < n + 2; j++) {
	                   if (!v[j] && dist(now.x,  now.y , pos[j].x,pos[j].y) <= 1000) {
	                        que.add(pos[j]);
	                        v[j] = true;
	                    }
	                }
			}
			
			if(ans) System.out.println("happy");
			else System.out.println("sad");
			
		}
	}
	
	static int dist(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1-y2);
	}
	

}
