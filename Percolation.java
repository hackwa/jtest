package algorithms;

private class Node {
	private int id[];
	
}

public class Percolation {
	
	private int id[];
	private int grid[][];
	private int nOpenSites = 0;
	private int size = 0;
	private int width = 0;
	
	private void union (int p, int q) {
		/*
		 * implements union
		 */
	}
	private boolean connected (int p, int q) {
		return true;
	}
	public Percolation(int n) {
		/* 
		 * create n-by-n grid, with all sites blocked.
		 * we need 2 more ids for top and bottom nodes
		 */
		size = n*n;
		width = n;
		id = new int[size + 2];
		grid = new int[width][width];
		for (int i=0; i < width; i++) {
			for (int j=0; j < width; j++) {
				grid[i][j] = 0;
				id[i*width +j] = i*width+j;
			}
		}
		for(int i=0; i < width ; i++) {
			// top
			id[i] = size;
			// bottom
			id[width*(width-1)+i] = size+1;
		}
		id[width] = width;
		id[width+1] = width+1;
	}
	public void printState() {
		System.out.println("Grid");
		for (int i=0; i < width; i++) {
			for (int j=0; j < width; j++) {
			System.out.print(grid[i][j] + "  ");
			}
			System.out.println();
		}
		System.out.println("IDs");
		System.out.println(id[size]);
		for (int i=0; i < width; i++) {
			for (int j=0; j < width; j++) {
			System.out.print(id[i*width + j] + "  ");
			}
			System.out.println();
		}
		System.out.println(id[size+1]);
	}
	public void open(int row, int col) {
		/* 
		 * open site (row, col) if it is not open already
		 */
		grid[row][col] = 1;
		nOpenSites++;
	}
	public boolean isOpen(int row, int col) {
		/*
		 *  is site (row, col) open?
		 */
		return grid[row][col] == 1;
	}
	public boolean isFull(int row, int col) {
		/*
		 *  is site (row, col) full?
		 *  full = connected to top
		 */
		return grid[row][col] == 2;
	}
	public int numberOfOpenSites() {
		/*
		 *  number of open sites
		 */
		return nOpenSites;
	}
	public boolean percolates() {
		/*
		 *  does the system percolate?
		 */
		return true;
	}
	public static void main(String[] args) {
		System.out.println("hello nabwa");
		Percolation model = new Percolation(4);
		model.printState();
	}

}
