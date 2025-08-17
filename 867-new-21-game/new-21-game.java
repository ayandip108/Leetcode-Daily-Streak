class Solution {
    private double[] probabilityLookup; // Cached probabilities for intermediate results.
    private int maxFinalPoints, pointsToStop, maxPointsPerDraw;

    // The new21Game method where the game's calculation starts.
    public double new21Game(int maxFinalPoints, int pointsToStop, int maxPointsPerDraw) {
        this.maxFinalPoints = maxFinalPoints;
        this.pointsToStop = pointsToStop;
        this.maxPointsPerDraw = maxPointsPerDraw;
        this.probabilityLookup = new double[pointsToStop]; // Cache array initialized for stopping points.
        return calculateProbability(0); // Start calculating from point 0.
    }

    // Recursive method to calculate the probability of winning.
    private double calculateProbability(int currentPoints) {
        // If we've reached the stopping point, return 1 if it's a win, 0 if it's a loss.
        if (currentPoints >= pointsToStop) {
            return currentPoints <= maxFinalPoints ? 1 : 0;
        }
      
        // Base case for the stopping point.
        if (currentPoints == pointsToStop - 1) {
            return Math.min(maxFinalPoints - pointsToStop + 1, maxPointsPerDraw) / (double) maxPointsPerDraw;
        }
      
        // If we've already calculated the probability for these points, return it.
        if (probabilityLookup[currentPoints] != 0) {
            return probabilityLookup[currentPoints];
        }
      
        // Recursive call and formula to calculate the probability.
        // We advance one point and subtract the probability of going out of bounds, normalized by the max points per draw.
        probabilityLookup[currentPoints] = calculateProbability(currentPoints + 1) 
            + (calculateProbability(currentPoints + 1) - calculateProbability(currentPoints + maxPointsPerDraw + 1)) 
            / maxPointsPerDraw;

        return probabilityLookup[currentPoints];
    }
}