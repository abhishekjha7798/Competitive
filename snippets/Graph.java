// Comprehensive Graph Algorithms
import java.util.*;

public class Graph {
    // ========== BFS - Breadth First Search ==========
    static void bfs(List<List<Integer>> adj, int start) {
        boolean[] visited = new boolean[adj.size()];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");

            for (int neighbor : adj.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }

    // ========== DFS - Depth First Search ==========
    static void dfs(List<List<Integer>> adj, int node, boolean[] visited) {
        visited[node] = true;
        System.out.print(node + " ");

        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(adj, neighbor, visited);
            }
        }
    }

    // ========== Connected Components ==========
    static int countConnectedComponents(int n, List<List<Integer>> adj) {
        boolean[] visited = new boolean[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfsComponent(adj, i, visited);
                count++;
            }
        }
        return count;
    }

    static void dfsComponent(List<List<Integer>> adj, int node, boolean[] visited) {
        visited[node] = true;
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfsComponent(adj, neighbor, visited);
            }
        }
    }

    // ========== Dijkstra's Algorithm - Shortest Path (Non-negative weights) ==========
    static long[] dijkstra(List<List<int[]>> adj, int start) {
        int n = adj.size();
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        pq.offer(new long[]{0, start});

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long d = curr[0];
            int u = (int) curr[1];

            if (d > dist[u]) continue;

            for (int[] edge : adj.get(u)) {
                int v = edge[0];
                long w = edge[1];
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.offer(new long[]{dist[v], v});
                }
            }
        }
        return dist;
    }

    // ========== Bellman-Ford Algorithm - Shortest Path (Negative weights allowed) ==========
    static long[] bellmanFord(int n, List<int[]> edges, int start) {
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[start] = 0;

        // Relax edges n-1 times
        for (int i = 0; i < n - 1; i++) {
            for (int[] edge : edges) {
                int u = edge[0], v = edge[1];
                long w = edge[2];
                if (dist[u] != Long.MAX_VALUE && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        // Check for negative cycles
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            long w = edge[2];
            if (dist[u] != Long.MAX_VALUE && dist[u] + w < dist[v]) {
                return null; // Negative cycle detected
            }
        }
        return dist;
    }

    // ========== Floyd-Warshall - All Pairs Shortest Path ==========
    static long[][] floydWarshall(long[][] adj) {
        int n = adj.length;
        long[][] dist = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = adj[i][j];
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        return dist;
    }

    // ========== Prim's Algorithm - Minimum Spanning Tree ==========
    static long prims(List<List<int[]>> adj, int n) {
        boolean[] visited = new boolean[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[]{0, 0});
        long mstCost = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int cost = curr[0], u = curr[1];

            if (visited[u]) continue;
            visited[u] = true;
            mstCost += cost;

            for (int[] edge : adj.get(u)) {
                int v = edge[0], w = edge[1];
                if (!visited[v]) {
                    pq.offer(new int[]{w, v});
                }
            }
        }
        return mstCost;
    }

    // ========== Kruskal's Algorithm - Minimum Spanning Tree ==========
    static long kruskals(List<int[]> edges, int n) {
        Collections.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));
        UnionFind uf = new UnionFind(n);
        long mstCost = 0;

        for (int[] edge : edges) {
            if (uf.union(edge[0], edge[1])) {
                mstCost += edge[2];
            }
        }
        return mstCost;
    }

    // ========== Kosaraju's Algorithm - Strongly Connected Components ==========
    static int kosarajuSCC(List<List<Integer>> adj) {
        int n = adj.size();
        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();

        // First DFS to fill stack
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                kosarajuDFS1(adj, i, visited, stack);
            }
        }

        // Create transpose graph
        List<List<Integer>> transpose = new ArrayList<>();
        for (int i = 0; i < n; i++) transpose.add(new ArrayList<>());
        for (int i = 0; i < n; i++) {
            for (int j : adj.get(i)) {
                transpose.get(j).add(i);
            }
        }

        // Second DFS on transpose
        Arrays.fill(visited, false);
        int sccCount = 0;
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                kosarajuDFS2(transpose, node, visited);
                sccCount++;
            }
        }
        return sccCount;
    }

    static void kosarajuDFS1(List<List<Integer>> adj, int node, boolean[] visited, Stack<Integer> stack) {
        visited[node] = true;
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                kosarajuDFS1(adj, neighbor, visited, stack);
            }
        }
        stack.push(node);
    }

    static void kosarajuDFS2(List<List<Integer>> adj, int node, boolean[] visited) {
        visited[node] = true;
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                kosarajuDFS2(adj, neighbor, visited);
            }
        }
    }

    // ========== 2-SAT Solver ==========
    static boolean solveTwoSAT(int numVariables, List<int[]> clauses) {
        int n = 2 * numVariables;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        // Build implication graph
        for (int[] clause : clauses) {
            int a = clause[0], b = clause[1];
            // (a OR b) = (!a => b) AND (!b => a)
            graph.get(2 * Math.abs(a) + (a < 0 ? 1 : 0)).add(2 * Math.abs(b) + (b < 0 ? 1 : 0));
            graph.get(2 * Math.abs(b) + (b < 0 ? 1 : 0)).add(2 * Math.abs(a) + (a < 0 ? 1 : 0));
        }

        // Find SCCs and check if any variable and its negation are in same SCC
        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                kosarajuDFS1Graph(graph, i, visited, stack);
            }
        }

        List<List<Integer>> transpose = new ArrayList<>();
        for (int i = 0; i < n; i++) transpose.add(new ArrayList<>());
        for (int i = 0; i < n; i++) {
            for (int j : graph.get(i)) {
                transpose.get(j).add(i);
            }
        }

        int[] sccId = new int[n];
        Arrays.fill(visited, false);
        int sccCount = 0;
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                kosarajuDFS2Graph(transpose, node, visited, sccId, sccCount);
                sccCount++;
            }
        }

        // Check if x and !x are in same SCC
        for (int i = 0; i < numVariables; i++) {
            if (sccId[2 * i] == sccId[2 * i + 1]) return false;
        }
        return true;
    }

    static void kosarajuDFS1Graph(List<List<Integer>> g, int u, boolean[] vis, Stack<Integer> st) {
        vis[u] = true;
        for (int v : g.get(u)) {
            if (!vis[v]) kosarajuDFS1Graph(g, v, vis, st);
        }
        st.push(u);
    }

    static void kosarajuDFS2Graph(List<List<Integer>> g, int u, boolean[] vis, int[] id, int cnt) {
        vis[u] = true;
        id[u] = cnt;
        for (int v : g.get(u)) {
            if (!vis[v]) kosarajuDFS2Graph(g, v, vis, id, cnt);
        }
    }
}

// Union-Find helper class for Kruskal's
class UnionFind {
    int[] parent, rank;

    UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    boolean union(int x, int y) {
        int px = find(x), py = find(y);
        if (px == py) return false;

        if (rank[px] < rank[py]) {
            parent[px] = py;
        } else if (rank[px] > rank[py]) {
            parent[py] = px;
        } else {
            parent[py] = px;
            rank[px]++;
        }
        return true;
    }
}

