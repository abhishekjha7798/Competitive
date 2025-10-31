// Fenwick Tree (Binary Indexed Tree) - Efficient prefix sum and point updates
public class FenwickTree {
    int[] tree;
    int n;

    FenwickTree(int n) {
        this.n = n;
        this.tree = new int[n + 1];
    }

    // ========== Initialize from array ==========
    FenwickTree(int[] arr) {
        this.n = arr.length;
        this.tree = new int[n + 1];
        for (int i = 0; i < n; i++) {
            update(i, arr[i]);
        }
    }

    // ========== Update - Add value to index ==========
    void update(int idx, int delta) {
        idx++; // 1-indexed
        while (idx <= n) {
            tree[idx] += delta;
            idx += idx & (-idx);
        }
    }

    // ========== Query - Get prefix sum [0, idx] ==========
    int query(int idx) {
        idx++; // 1-indexed
        int sum = 0;
        while (idx > 0) {
            sum += tree[idx];
            idx -= idx & (-idx);
        }
        return sum;
    }

    // ========== Range Sum - Get sum [l, r] ==========
    int rangeSum(int l, int r) {
        if (l == 0) return query(r);
        return query(r) - query(l - 1);
    }

    // ========== Set value at index ==========
    void set(int idx, int value) {
        int current = query(idx) - (idx == 0 ? 0 : query(idx - 1));
        update(idx, value - current);
    }
}

// ========== Fenwick Tree - Long version for large numbers ==========
class FenwickTreeLong {
    long[] tree;
    int n;

    FenwickTreeLong(int n) {
        this.n = n;
        this.tree = new long[n + 1];
    }

    FenwickTreeLong(long[] arr) {
        this.n = arr.length;
        this.tree = new long[n + 1];
        for (int i = 0; i < n; i++) {
            update(i, arr[i]);
        }
    }

    void update(int idx, long delta) {
        idx++;
        while (idx <= n) {
            tree[idx] += delta;
            idx += idx & (-idx);
        }
    }

    long query(int idx) {
        idx++;
        long sum = 0;
        while (idx > 0) {
            sum += tree[idx];
            idx -= idx & (-idx);
        }
        return sum;
    }

    long rangeSum(int l, int r) {
        if (l == 0) return query(r);
        return query(r) - query(l - 1);
    }

    void set(int idx, long value) {
        long current = query(idx) - (idx == 0 ? 0 : query(idx - 1));
        update(idx, value - current);
    }
}

// ========== 2D Fenwick Tree ==========
class FenwickTree2D {
    int[][] tree;
    int rows, cols;

    FenwickTree2D(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.tree = new int[rows + 1][cols + 1];
    }

    void update(int r, int c, int delta) {
        r++; c++;
        while (r <= rows) {
            int col = c;
            while (col <= cols) {
                tree[r][col] += delta;
                col += col & (-col);
            }
            r += r & (-r);
        }
    }

    int query(int r, int c) {
        r++; c++;
        int sum = 0;
        while (r > 0) {
            int col = c;
            while (col > 0) {
                sum += tree[r][col];
                col -= col & (-col);
            }
            r -= r & (-r);
        }
        return sum;
    }

    int rangeSum(int r1, int c1, int r2, int c2) {
        if (r1 == 0 && c1 == 0) return query(r2, c2);
        if (r1 == 0) return query(r2, c2) - query(r2, c1 - 1);
        if (c1 == 0) return query(r2, c2) - query(r1 - 1, c2);
        return query(r2, c2) - query(r1 - 1, c2) - query(r2, c1 - 1) + query(r1 - 1, c1 - 1);
    }
}

// ========== Fenwick Tree with MOD ==========
class FenwickTreeMod {
    long[] tree;
    int n;
    long mod;

    FenwickTreeMod(int n, long mod) {
        this.n = n;
        this.mod = mod;
        this.tree = new long[n + 1];
    }

    void update(int idx, long delta) {
        idx++;
        while (idx <= n) {
            tree[idx] = (tree[idx] + delta) % mod;
            idx += idx & (-idx);
        }
    }

    long query(int idx) {
        idx++;
        long sum = 0;
        while (idx > 0) {
            sum = (sum + tree[idx]) % mod;
            idx -= idx & (-idx);
        }
        return sum;
    }

    long rangeSum(int l, int r) {
        if (l == 0) return query(r);
        long res = (query(r) - query(l - 1)) % mod;
        if (res < 0) res += mod;
        return res;
    }
}

