import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Backjoon_2468 {

    static int[] R = {0, -1, 0 , +1}, C = {-1, 0, 1, 0};
    static int N, landNum, MAXHEIGHT = 0, MINHEIGHT = 100, MAX = 1;
    static int[][] land, floodLand;
    static boolean[][] visit;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        land = new int[N][N];

        for(int r=0;r<N;r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0;c<N;c++) {
                land[r][c] = Integer.parseInt(st.nextToken());
                if(land[r][c]>=MAXHEIGHT) {MAXHEIGHT = land[r][c];}
                if(land[r][c]<=MINHEIGHT) {MINHEIGHT = land[r][c];}
            }
        }

        measurePrecipitation(N);

        System.out.println(MAX);
    }

    private static void measurePrecipitation(Integer rowNum){
        for (int height=MINHEIGHT; height<MAXHEIGHT; height++) {
            floodLand = new int[N][N];
            visit = new boolean[N][N];

            markFloodLand(floodLand, height);

            landNum = 0;
            for(int r=0;r<N;r++) {
                for(int c=0;c<N;c++) {
                    if (floodLand[r][c] == 0 && !visit[r][c]) {
                        checkSafeLandByDFS(r, c, ++landNum);
                    }
                }
            }

            if(landNum >= MAX) { MAX = landNum; }
        }

    }
    private static void markFloodLand(int[][] floodLand, int height){
        for(int r=0;r<N;r++) {
            for(int c=0;c<N;c++) {
                if (land[r][c] <= height) {
                    floodLand[r][c] = -1;
                }
            }
        }
    }

    private static void checkSafeLandByDFS(int r, int c, int landNum){
        floodLand[r][c] = landNum;
        visit[r][c] = true;
        for (int i=0;i<4;i++) {
            int nextR = r + R[i];
            int nextC = c + C[i];
            if (isIn(nextR, nextC) && !visit[nextR][nextC] && floodLand[nextR][nextC] == 0) {
                checkSafeLandByDFS(nextR, nextC, landNum);
            }
        }
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
