import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11909 {
    static int N;
    static int[][] array, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        array = new int[N+1][N+1];
        dp = new int[N+1][N+1];
        for(int i=1; i<N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<N+1; j++) {
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int minValue = escape();
        System.out.println(minValue);
    }

    static int escape() {
        for(int i=1; i<N+1; i++) {
            for(int j=1; j<N+1; j++) {
                if(i==1 && j==1) continue;

                int up, left;
                if(array[i][j] < array[i-1][j])
                    up = dp[i-1][j];
                else
                    up = dp[i-1][j] + array[i][j] - array[i-1][j] + 1;

                if(array[i][j] < array[i][j-1])
                    left = dp[i][j-1];
                else
                    left = dp[i][j-1] + array[i][j] - array[i][j-1] + 1;

                if(i==1) up = Integer.MAX_VALUE;
                if(j==1) left = Integer.MAX_VALUE;

                dp[i][j] = Integer.min(up, left);
            }
        }

        return dp[N][N];
    }
}
