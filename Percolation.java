import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private static final int OPEN = 1;
	private int n;
	private int []id;
	private WeightedQuickUnionUF uf;
	private WeightedQuickUnionUF uf2;
	public Percolation(int N) {
		if (N < 1) throw new IllegalArgumentException("Argument is illegal");
		n = N;
		id = new int [2+n*n];
		uf = new WeightedQuickUnionUF(n*n+2);
		uf2 = new WeightedQuickUnionUF(n*n+1);
	}
	public void open(int i, int j) {
		if (i <= 0 || i > n) throw new IndexOutOfBoundsException("row index i out of bounds");
		if (j <= 0 || j > n) throw new IndexOutOfBoundsException("row index j out of bounds");
		id[i*n-n+j] = OPEN;
		if (i == 1) {
			uf.union(i*n-n+j, 0);
			uf2.union(i*n-n+j, 0);
		}
		if (i == n) {
			uf.union(i*n-n+j, 1+n*n);
		}
		if (i > 1 && isOpen(i-1, j)) {
			uf.union(i*n-n+j, i*n-2*n+j);
			uf2.union(i*n-n+j, i*n-2*n+j);
		}
		if (i < n && isOpen(i+1, j)) {
			uf.union(i*n-n+j, i*n+j);
			uf2.union(i*n-n+j, i*n+j);
		}
		if (j > 1 && isOpen(i, j-1)) {
			uf.union(i*n-n+j, i*n-n+j-1);
			uf2.union(i*n-n+j, i*n-n+j-1);
		}
		if (j < n && isOpen(i, j+1)) {
			uf.union(i*n-n+j, i*n-n+j+1);
			uf2.union(i*n-n+j, i*n-n+j+1);
		}
	}
	public boolean isOpen(int i, int j) {
		if (i <= 0 || i > n) throw new IndexOutOfBoundsException("row index i out of bounds");
		if (j <= 0 || j > n) throw new IndexOutOfBoundsException("row index j out of bounds");
		return id[i*n+j-n] == OPEN;
	}
	public boolean isFull(int i, int j) {
		if (i <= 0 || i > n) throw new IndexOutOfBoundsException("row index i out of bounds");
		if (j <= 0 || j > n) throw new IndexOutOfBoundsException("row index j out of bounds");
		return uf2.connected(0, i*n-n+j);
	}
	public boolean percolates() {
		return uf.connected(0, 1+n*n);
	}
	public static void main(String[] args) {
		// test client (optional)
	}
}