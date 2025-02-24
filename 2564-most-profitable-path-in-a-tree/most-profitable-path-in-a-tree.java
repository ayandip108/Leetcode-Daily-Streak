class Solution {
    private List<Integer>[] graph;
    private int[] profitAtNode;
    private int[] timeStamps;
    private int maximumProfit = Integer.MIN_VALUE;

    // Main method to find the most profitable path for Bob from a given start node
    public int mostProfitablePath(int[][] edges, int bobStartNode, int[] profit) {
        int n = edges.length + 1;
        graph = new List[n];
        timeStamps = new int[n];
        profitAtNode = profit;
        // Initialize lists for the adjacency representation of the graph and fill timestamps with an upper bound.
        Arrays.setAll(graph, k -> new ArrayList<>());
        Arrays.fill(timeStamps, n);
        for (var edge : edges) {
            int nodeA = edge[0], nodeB = edge[1];
            graph[nodeA].add(nodeB);
            graph[nodeB].add(nodeA);
        }
        // Run the first DFS for timestamp assignment
        dfsAssignTimestamps(bobStartNode, -1, 0);
        timeStamps[bobStartNode] = 0;  // Set the start node's timestamp to 0
        // Run the second DFS to calculate the maximum profit
        dfsCalculateProfit(0, -1, 0, 0);
        return maximumProfit;
    }

    // Helper method for the first DFS to assign timestamps
    private boolean dfsAssignTimestamps(int currentNode, int parent, int timestamp) {
        if (currentNode == 0) {
            timeStamps[currentNode] = Math.min(timeStamps[currentNode], timestamp);
            return true;
        }
        for (int nextNode : graph[currentNode]) {
            if (nextNode != parent && dfsAssignTimestamps(nextNode, currentNode, timestamp + 1)) {
                timeStamps[nextNode] = Math.min(timeStamps[nextNode], timestamp + 1);
                return true;
            }
        }
        return false;
    }

    // Helper method for the second DFS to calculate the maximum profit
    private void dfsCalculateProfit(int currentNode, int parent, int timestamp, int currentProfit) {
        // Partial profit is halved when on the most profitable path (determined by timestamp)
        if (timestamp == timeStamps[currentNode]) {
            currentProfit += profitAtNode[currentNode] >> 1;
        } else if (timestamp < timeStamps[currentNode]) {
            // If this node is reached earlier, add full profit
            currentProfit += profitAtNode[currentNode];
        }
        // If this node is a leaf node, update the maximum profit and return
        if (graph[currentNode].size() == 1 && graph[currentNode].get(0) == parent) {
            maximumProfit = Math.max(maximumProfit, currentProfit);
            return;
        }
        // Continue DFS for subsequent nodes
        for (int nextNode : graph[currentNode]) {
            if (nextNode != parent) {
                dfsCalculateProfit(nextNode, currentNode, timestamp + 1, currentProfit);
            }
        }
    }
}
