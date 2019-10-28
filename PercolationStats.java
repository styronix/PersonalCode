import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private final double mean;
	private final double standardDeviation;
	private final double variance;
	private final double lowerConfidenceIntervalBound;
	private final double higherConfidenceIntervalBound;
	public PercolationStats(int n, int trials) {
		if(n <= 0 || trials <= 0) {
			throw new IllegalArgumentException();
		}
		double x [] = new double[trials]; // x[i] stores the fraction of number of minimum open sites to total sites for percolation to take place in the ith trial
		for(int i = 0; i < trials; i++) { // perform trials
			x[i] = performTrial(n); 
		}
		mean = StdStats.mean(x);
		standardDeviation = StdStats.stddev(x);
		variance = Math.sqrt(standardDeviation); // By definition
		lowerConfidenceIntervalBound = mean - ((1.96 * variance) / Math.sqrt(trials)); //By definition
		higherConfidenceIntervalBound = mean + ((1.96 * variance) / Math.sqrt(trials));
	}
	private double performTrial(int n) { // Returns the ratio of the number of minimum open sites to total sites for which percolation takes place. n is the size of row/column of the n*n grid
		int totalNodes = (int)Math.pow(n, 2);
		//System.out.println(totalNodes);
		Percolation percolationObj = new Percolation(n);
		int openNodes = 0;
		while(true){
			int randomRow = StdRandom.uniform(n) + 1; // Generate a random row number between 1 and n, both inclusive
			int randomCol = StdRandom.uniform(n) + 1; // Generate a random column number between 1 to n, both inclusive
			percolationObj.open(randomRow, randomCol);
			if(percolationObj.percolates()) {
				openNodes = percolationObj.numberOfOpenSites();		
				break; // Since the  objective is to find the minimum number of nodes for which system percolates, 						  
				       //we break the loop at this point and record the number of open nodes in this situation
			}
		}
		//System.out.println("Number of Open Sites" + openNodes);
		double ratio = (double)openNodes / (double)totalNodes;
		//System.out.println(ratio);
		return ratio;
	}
	public double mean() {
		return mean;
	}
	public double stddev() {
		return standardDeviation;
	}
	public double confidenceLo() {
		return lowerConfidenceIntervalBound;
	}
	public double confidenceHi() {
		return higherConfidenceIntervalBound;
	}
	public static void main(String args[]) {
		int n = Integer.parseInt(args[0]); // Dimension of n*n grid
		int t = Integer.parseInt(args[1]); // Number of Trials
		PercolationStats percolationStatsObj = new PercolationStats(n,t);
		System.out.println("Mean\t\t\t = " + percolationStatsObj.mean());
		System.out.println("Standard Deviation\t = " + percolationStatsObj.stddev());
		System.out.println("95% confidence interval\t = " + "[" + percolationStatsObj.confidenceLo() + "," + percolationStatsObj.confidenceHi() + "]");
	}

}
