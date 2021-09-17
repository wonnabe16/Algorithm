package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2667 {
	static class Dot{
		int r, c;

		public int getR() {
			return r;
		}

		public int getC() {
			return c;
		}

		Dot(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}
	
	static int N, num;
	static int[][] arr;
	static boolean[][] visited;
	static List<Integer> list = new ArrayList<>();
	static Queue<Dot> q = new LinkedList<>();
	
	static int[][] dir = {{1,0},{0,1},{0,-1},{-1,0}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
	
		arr = new int[N][N];
		visited = new boolean[N][N];
		for(int i=0;i<N;i++) {
			 char[]  cArr=br.readLine().toCharArray();
			for(int j=0;j<N;j++) {
				arr[i][j] = cArr[j] -'0';
				//if(arr[i][j]==1) q.offer(new Dot(i,j));
			}
		}	
		
		num = 2;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				
				// 1발견하면!
				if(arr[i][j]==1) {
					q.offer(new Dot(i,j));
					arr[i][j] = num++;
					list.add(apartComplex());
				}
			}
		}	
		
		int size = list.size();
		System.out.println(size);
		Collections.sort(list);
		for(int i=0;i<size;i++) {
			System.out.println(list.get(i));
		}
		
		
	}
	
	private static int apartComplex() {
		int count = 0;
		out : while(!q.isEmpty()) {
			Dot d = q.poll();
			int r = d.getR();
			int c = d.getC();
			
			count++;
			in : for(int i=0;i<4;i++) {
				int nr = r + dir[i][0];
				int nc = c + dir[i][1];
				
				if(!isIn(nr,nc)) continue in;
				
				if(arr[nr][nc] == 1) {
					 arr[nr][nc] = arr[r][c];
					 q.offer(new Dot(nr,nc));
				}

			}			
			
		}
		return count;
	}
	
	private static boolean isIn(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}

}
