class Solution {
    public int maxEvents(int[][] events) {
        // Create a map to associate start days with a list of their respective end days
        Map<Integer, List<Integer>> dayToEventsMap = new HashMap<>();
        int earliestStart = Integer.MAX_VALUE; // Initialize earliest event start day
        int latestEnd = 0; // Initialize latest event end day
      
        // Process the events to populate the map and find the range of event days
        for (int[] event : events) {
            int startDay = event[0];
            int endDay = event[1];
          
            // Map the start day to the end day of the event
            dayToEventsMap.computeIfAbsent(startDay, k -> new ArrayList<>()).add(endDay);
          
            // Update earliest start and latest end
            earliestStart = Math.min(earliestStart, startDay);
            latestEnd = Math.max(latestEnd, endDay);
        }
      
        // Create a min-heap to manage event end days
        PriorityQueue<Integer> eventsEndingQueue = new PriorityQueue<>();
      
        int attendedEventsCount = 0; // Initialize the count of events attended
      
        // Iterate over each day within the range of event days
        for (int currentDay = earliestStart; currentDay <= latestEnd; ++currentDay) {
            // Remove past events that have already ended
            while (!eventsEndingQueue.isEmpty() && eventsEndingQueue.peek() < currentDay) {
                eventsEndingQueue.poll();
            }
          
            // Add new events that start on the current day
            List<Integer> eventsStartingToday = dayToEventsMap.getOrDefault(currentDay, Collections.emptyList());
            for (int endDay : eventsStartingToday) {
                eventsEndingQueue.offer(endDay);
            }
          
            // Attend the event that ends the earliest, if any are available
            if (!eventsEndingQueue.isEmpty()) {
                eventsEndingQueue.poll();
                ++attendedEventsCount; // Increment the count of events attended
            }
        }
      
        return attendedEventsCount;
    }
}