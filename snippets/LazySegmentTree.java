// Segment Tree with Lazy Propagation - For range updates and range queries
public class LazySegmentTree {
    int[] tree, lazy;
    int n;

    LazySegmentTree(int[] arr) {
        n = arr.length;
        tree = new int[4 * n];
        lazy = new int[4 * n];
        build(arr, 0, 0, n - 1);
    }

    void build(int[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            build(arr, 2 * node + 1, start, mid);
            build(arr, 2 * node + 2, mid + 1, end);
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    void updateRange(int l, int r, int val) {
        updateRange(0, 0, n - 1, l, r, val);
    }

    void updateRange(int node, int start, int end, int l, int r, int val) {
        if (lazy[node] != 0) {
            tree[node] += (end - start + 1) * lazy[node];
            if (start != end) {
                lazy[2 * node + 1] += lazy[node];
                lazy[2 * node + 2] += lazy[node];
            }
            lazy[node] = 0;
        }

        if (start > end || start > r || end < l) return;

        if (start >= l && end <= r) {
            tree[node] += (end - start + 1) * val;
            if (start != end) {
                lazy[2 * node + 1] += val;
                lazy[2 * node + 2] += val;
            }
            return;
        }

        int mid = (start + end) / 2;
        updateRange(node * 2 + 1, start, mid, l, r, val);
        updateRange(node * 2 + 2, mid + 1, end, l, r, val);
        tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
    }

    long queryRange(int l, int r) {
        return queryRange(0, 0, n - 1, l, r);
    }

    long queryRange(int node, int start, int end, int l, int r) {
        if (start > end || start > r || end < l) return 0;

        if (lazy[node] != 0) {
            tree[node] += (end - start + 1) * lazy[node];
            if (start != end) {
                lazy[2 * node + 1] += lazy[node];
                lazy[2 * node + 2] += lazy[node];
            }
            lazy[node] = 0;
        }

        if (start >= l && end <= r) return tree[node];

        int mid = (start + end) / 2;
        return queryRange(2 * node + 1, start, mid, l, r) +
               queryRange(2 * node + 2, mid + 1, end, l, r);
    }
}

