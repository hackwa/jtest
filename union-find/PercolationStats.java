package algorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	
		private Percolation percInstance;
		private int gridWidth = 0;
		private int nTrials = 0;
		private double openSitesFraction[];
		
		private void runTrial(int n)
		{
			while (!percInstance.percolates()) {
				int row = StdRandom.uniform(1, gridWidth + 1);
				int col = StdRandom.uniform(1, gridWidth + 1);
				if(percInstance.isOpen(row, col)) continue;
				//StdOut.println("Coordinates : " + row + " " + col);
				percInstance.open(row, col);
			}
			openSitesFraction[n] = (double)percInstance.numberOfOpenSites()/(double)(gridWidth*gridWidth);
			//System.out.println("fraction : " + openSitesFraction[n] + " "  + percInstance.numberOfOpenSites() + " " + gridWidth*gridWidth);
		}
		
		public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
		{
			if(n<=0 || trials <= 0)
				throw new java.lang.IllegalArgumentException();
			gridWidth = n;
			nTrials = trials;
			openSitesFraction = new double[trials];
			for (int i=0; i < trials; i++) {
				percInstance = new Percolation(n);
				runTrial(i);
			}
		}
		
		public double mean()                          // sample mean of percolation threshold
		{
			return StdStats.mean(openSitesFraction);
		}
		public double stddev()                        // sample standard deviation of percolation threshold
		{
		   return StdStats.stddev(openSitesFraction);
		}
		public double confidenceLo()                  // low  endpoint of 95% confidence interval
		{
			double m = mean();
			double s = stddev();
			return m - (1.96*s/Math.sqrt(nTrials));
		}
		public double confidenceHi()                  // high endpoint of 95% confidence interval
		{
			double m = mean();
			double s = stddev();
			return m - (1.96*s/Math.sqrt(nTrials));
		}
		public static void main(String[] args)        // test client (described below)
		{
			PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
			StdOut.println("Mean                  = " + ps.mean());
			StdOut.println("Stddev                = " + ps.stddev());
			StdOut.println("95 % confidence level = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
		}
}