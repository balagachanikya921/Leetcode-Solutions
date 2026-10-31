class Solution {
    static final int MOD = 1_000_000_007;
    int m, n;
    int[][] grid;
    int[][] memoR, memoD;

    public int uniquePaths(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

        memoR = new int[m][n];
        memoD = new int[m][n];

        for (int i = 0; i < m; i++) {
            java.util.Arrays.fill(memoR[i], -2);
            java.util.Arrays.fill(memoD[i], -2);
        }

        long[][] dp = new long[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 || dp[i][j] == 0) continue;

                if (j + 1 < n) {
                    int idx = resolveRight(i, j + 1);
                    if (idx != -1) {
                        dp[idx / n][idx % n] =
                                (dp[idx / n][idx % n] + dp[i][j]) % MOD;
                    }
                }

                if (i + 1 < m) {
                    int idx = resolveDown(i + 1, j);
                    if (idx != -1) {
                        dp[idx / n][idx % n] =
                                (dp[idx / n][idx % n] + dp[i][j]) % MOD;
                    }
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }

    private int resolveRight(int i, int j) {
        if (i >= m || j >= n) return -1;
        if (memoR[i][j] != -2) return memoR[i][j];

        if (grid[i][j] == 0)
            return memoR[i][j] = i * n + j;

        return memoR[i][j] = resolveDown(i + 1, j);
    }

    private int resolveDown(int i, int j) {
        if (i >= m || j >= n) return -1;
        if (memoD[i][j] != -2) return memoD[i][j];

        if (grid[i][j] == 0)
            return memoD[i][j] = i * n + j;

        return memoD[i][j] = resolveRight(i, j + 1);
    }
}