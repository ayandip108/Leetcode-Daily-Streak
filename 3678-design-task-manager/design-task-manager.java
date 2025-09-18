class TaskManager {
    // Map to store taskId -> [userId, priority]
    private final Map<Integer, int[]> taskMap = new HashMap<>();
  
    // TreeSet to maintain tasks sorted by priority (descending) and taskId (descending)
    // Format: [priority, taskId]
    private final TreeSet<int[]> priorityQueue = new TreeSet<>((a, b) -> {
        // If priorities are equal, sort by taskId in descending order
        if (a[0] == b[0]) {
            return b[1] - a[1];
        }
        // Sort by priority in descending order (higher priority first)
        return b[0] - a[0];
    });

    /**
     * Constructor to initialize the TaskManager with a list of tasks
     * @param tasks List of tasks where each task contains [userId, taskId, priority]
     */
    public TaskManager(List<List<Integer>> tasks) {
        for (List<Integer> task : tasks) {
            add(task.get(0), task.get(1), task.get(2));
        }
    }

    /**
     * Add a new task to the system
     * @param userId The user who owns the task
     * @param taskId The unique identifier for the task
     * @param priority The priority level of the task
     */
    public void add(int userId, int taskId, int priority) {
        // Store task information in the map
        taskMap.put(taskId, new int[] {userId, priority});
      
        // Add task to the priority queue
        priorityQueue.add(new int[] {priority, taskId});
    }

    /**
     * Edit the priority of an existing task
     * @param taskId The task to be edited
     * @param newPriority The new priority value for the task
     */
    public void edit(int taskId, int newPriority) {
        // Retrieve existing task information
        int[] taskInfo = taskMap.get(taskId);
        int userId = taskInfo[0];
        int oldPriority = taskInfo[1];
      
        // Remove old entry from priority queue
        priorityQueue.remove(new int[] {oldPriority, taskId});
      
        // Add updated entry to priority queue
        priorityQueue.add(new int[] {newPriority, taskId});
      
        // Update task information in the map
        taskMap.put(taskId, new int[] {userId, newPriority});
    }

    /**
     * Remove a task from the system
     * @param taskId The task to be removed
     */
    public void rmv(int taskId) {
        // Remove task from map and get its information
        int[] taskInfo = taskMap.remove(taskId);
        int priority = taskInfo[1];
      
        // Remove task from priority queue
        priorityQueue.remove(new int[] {priority, taskId});
    }

    /**
     * Execute the task with the highest priority and return the userId
     * @return The userId of the executed task, or -1 if no tasks exist
     */
    public int execTop() {
        // Check if there are any tasks to execute
        if (priorityQueue.isEmpty()) {
            return -1;
        }
      
        // Remove and get the highest priority task
        int[] topTask = priorityQueue.pollFirst();
        int taskId = topTask[1];
      
        // Remove task from map and get user information
        int[] taskInfo = taskMap.remove(taskId);
        int userId = taskInfo[0];
      
        return userId;
    }
}

/**
 * Your TaskManager object will be instantiated and called as such:
 * TaskManager obj = new TaskManager(tasks);
 * obj.add(userId,taskId,priority);
 * obj.edit(taskId,newPriority);
 * obj.rmv(taskId);
 * int param_4 = obj.execTop();
 */