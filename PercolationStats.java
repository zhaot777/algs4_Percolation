import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private double [] x;
	private int T;
	public PercolationStats(int N, int T) {
		if (N < 1 || T < 1) throw new IllegalArgumentException("Argument is illegal");
		this.T = T;
		x = new double[T];
		for (int t = 0; t < T; t++) {
			Percolation percolation = new Percolation(N);
			while (!percolation.percolates()) {
				int i = StdRandom.uniform(1, N+1);
				int j = StdRandom.uniform(1, N+1);
				if (!percolation.isOpen(i, j)) {
					percolation.open(i, j);
					x[t] += 1;
				}
			}
			x[t] = x[t]/(N*N);
		}
	}
	public double mean() {
	    return StdStats.mean(x);
	}
	public double stddev() {
		return StdStats.stddev(x);
	}
	public double confidenceLo() {
		return mean()-1.96*stddev()/Math.sqrt(T);
	}
	public double confidenceHi() {
		return mean()+1.96*stddev()/Math.sqrt(T);
	}
	public static void main(String[] args) {
		PercolationStats test = new PercolationStats(StdIn.readInt(), StdIn.readInt());
		System.out.println("mean			= "+test.mean());
		System.out.println("stddev			= "+test.stddev());
		System.out.println("95% confidence interval = "+test.confidenceLo()+", "+test.confidenceHi());
		System.out.println();
	}
}