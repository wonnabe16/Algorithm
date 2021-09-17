package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Dot {
	int r;
	int c;

	Dot(int r, int c) {
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

public class BOJ_14502 {
	static int R, C, result;
	static int[][] arr, newArr;
	static Queue<Dot> q = new LinkedList<>();
	static int[][] dir = {{1,0},{0,1},{0,-1},{-1,0}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		arr = new int[R][C];
		newArr = new int[R][C];
		result = 0;
		for(int i=0;i<R;i++) {
			 st = new StringTokenizer(br.readLine());
			for(int j=0;j<C;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}	
		
		
		makeWall(0,0);
		
		System.out.println(result);
	}
	
	private static void spreadVirus() {
		
		while(!q.isEmpty()) {
			Dot d = q.poll();
			for(int i=0;i<4;i++) {
				int r = d.getR() + dir[i][0];
				int c = d.getC() + dir[i][1];
				
				if(isIn(r,c) && newArr[r][c] == 0) {
					newArr[r][c]  = 2;
					q.offer(new Dot(r,c));
				}
			}
		}
	}
	
	private static void copyArr() {
		
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				newArr[i][j] = arr[i][j];
				
				if(newArr[i][j] == 2) q.add(new Dot(i,j));
			}
		}
	}
	
	// DFS로  완전탐색
	private static void makeWall(int start, int count) {
		
		if(count == 3) {
			copyArr();
			spreadVirus();
			int sum = 0;
			for(int i=0;i<R;i++) {
				for(int j=0;j<C;j++) {
					if(newArr[i][j]==0) sum++;
				}
			}
			result = Math.max(result, sum);
			return;
		}
		
		for(int i=start;i<R*C;i++) {
			int r = i / C;
			int c= i % C;
			if(arr[r][c]!=0) continue;
			
			arr[r][c] = 1;
			makeWall(i+1, count+1);
			arr[r][c] = 0;
		}
	}
	private static boolean isIn(int r, int c) {
		return r>=0 && r<R && c>=0 && c < C;
	}

}
