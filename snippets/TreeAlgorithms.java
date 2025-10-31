// Tree Algorithms - LCA, Tree DP, Diameter, Path queries
import java.util.*;

public class TreeAlgorithms {
    static class TreeNode {
        int val;
        List<TreeNode> children;

        TreeNode(int val) {
            this.val = val;
            this.children = new ArrayList<>();
        }
    }

    // ========== Tree Diameter ==========
    static int treeDiameter(List<List<Integer>> adj, int n) {
        int[] maxDist = {0};
        dfs(adj, 0, -1, 0, maxDist);

        int[] newMax = {0};
        dfs(adj, 0, -1, 0, newMax);

        return newMax[0];
    }

    static int dfs(List<List<Integer>> adj, int node, int parent, int dist, int[] maxDist) {
        maxDist[0] = Math.max(maxDist[0], dist);
        int maxChildDist = 0;

        for (int child : adj.get(node)) {
            if (child != parent) {
                maxChildDist = Math.max(maxChildDist, dfs(adj, child, node, dist + 1, maxDist));
            }
        }
        return maxChildDist + 1;
    }

    // ========== Lowest Common Ancestor (LCA) - Binary Lifting ==========
    static class LCA {
        int LOG = 20;
        int[][] up;
        int[] depth;

        LCA(List<List<Integer>> adj, int root, int n) {
            up = new int[n][LOG];
            depth = new int[n];
            dfsLCA(adj, root, -1, 0);

            for (int j = 1; j < LOG; j++) {
                for (int i = 0; i < n; i++) {
                    if (up[i][j - 1] != -1) {
                        up[i][j] = up[up[i][j - 1]][j - 1];
                    }
                }
            }
        }

        void dfsLCA(List<List<Integer>> adj, int node, int parent, int d) {
            up[node][0] = parent;
            depth[node] = d;
            for (int child : adj.get(node)) {
                if (child != parent) {
                    dfsLCA(adj, child, node, d + 1);
                }
            }
        }

        int getLCA(int u, int v) {
            if (depth[u] < depth[v]) {
                int temp = u;
                u = v;
                v = temp;
            }

            int diff = depth[u] - depth[v];
            for (int i = 0; i < LOG; i++) {
                if ((diff & (1 << i)) > 0) {
                    u = up[u][i];
                }
            }

            if (u == v) return u;

            for (int i = LOG - 1; i >= 0; i--) {
                if (up[u][i] != up[v][i]) {
                    u = up[u][i];
                    v = up[v][i];
                }
            }
            return up[u][0];
        }
    }

    // ========== Path Sum in Tree ==========
    static long pathSum(List<List<Integer>> adj, int start, int end, LCA lca) {
        int ancestor = lca.getLCA(start, end);
        long sum = 0;

        // Sum from start to ancestor
        int curr = start;
        while (curr != ancestor) {
            sum += curr;
            curr = lca.up[curr][0];
        }
        sum += ancestor;

        // Sum from end to ancestor (excluding ancestor)
        List<Integer> path = new ArrayList<>();
        curr = end;
        while (curr != ancestor) {
            path.add(curr);
            curr = lca.up[curr][0];
        }
        for (int node : path) {
            sum += node;
        }

        return sum;
    }

    // ========== Tree DP - Maximum Independent Set ==========
    static int maxIndependentSet(List<List<Integer>> adj, int node, int parent) {
        int include = node;
        int exclude = 0;

        for (int child : adj.get(node)) {
            if (child != parent) {
                include += maxIndependentSet(adj, child, node);
                exclude += Math.max(
                    maxIndependentSet(adj, child, node),
                    node // Can't use this value, so use child
                );
            }
        }

        return Math.max(include, exclude);
    }

    // ========== Tree DP - Maximum Path Sum ==========
    static long[] maxPathSum(List<List<Integer>> adj, int node, int parent, long[] maxSum) {
        long pathThroughNode = node;
        long maxChildPath = 0;

        for (int child : adj.get(node)) {
            if (child != parent) {
                long[] childResult = maxPathSum(adj, child, node, maxSum);
                maxSum[0] = Math.max(maxSum[0], pathThroughNode + childResult[0]);
                maxChildPath = Math.max(maxChildPath, childResult[0]);
            }
        }

        pathThroughNode += maxChildPath;
        maxSum[0] = Math.max(maxSum[0], pathThroughNode);

        return new long[]{pathThroughNode, node};
    }

    // ========== Centroid of Tree ==========
    static int treeCentroid(List<List<Integer>> adj, int node, int parent, int treeSize) {
        for (int child : adj.get(node)) {
            if (child != parent) {
                int childSize = getSubtreeSize(adj, child, node);
                if (childSize > treeSize / 2) {
                    return treeCentroid(adj, child, node, treeSize);
                }
            }
        }
        return node;
    }

    static int getSubtreeSize(List<List<Integer>> adj, int node, int parent) {
        int size = 1;
        for (int child : adj.get(node)) {
            if (child != parent) {
                size += getSubtreeSize(adj, child, node);
            }
        }
        return size;
    }

    // ========== Tree Distance - All Pairs ==========
    static int[][] treeDistances(List<List<Integer>> adj, int n) {
        int[][] dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            bfsDistance(adj, i, dist[i]);
        }

        return dist;
    }

    static void bfsDistance(List<List<Integer>> adj, int start, int[] dist) {
        Arrays.fill(dist, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        dist[start] = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : adj.get(node)) {
                if (dist[neighbor] == -1) {
                    dist[neighbor] = dist[node] + 1;
                    queue.add(neighbor);
                }
            }
        }
    }

    // ========== Subtree Sum ==========
    static long subtreeSum(List<List<Integer>> adj, int node, int parent, int[] values) {
        long sum = values[node];
        for (int child : adj.get(node)) {
            if (child != parent) {
                sum += subtreeSum(adj, child, node, values);
            }
        }
        return sum;
    }

    // ========== Count Nodes at Distance K ==========
    static int countNodesAtDistance(List<List<Integer>> adj, int node, int parent, int k) {
        if (k == 0) return 1;

        int count = 0;
        for (int child : adj.get(node)) {
            if (child != parent) {
                count += countNodesAtDistance(adj, child, node, k - 1);
            }
        }
        return count;
    }
}

