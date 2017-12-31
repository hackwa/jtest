package algorithms;

public class Percolation {
	
	private int id[];
	private int grid[][];
	private int nOpenSites = 0;
	private int size = 0;
	private int width = 0;
	
	private void union (int p, int q)
	{
		//System.out.println("Union " + p + " " +  q);
		int i = root(p);
		int j = root(q);
		id[i] = j;
	}
	private int root(int i)
	{
		while(i!=id[i]) i = id[i];
		return i;
	}
	private boolean connected (int p, int q)
	{
		return root(p) == root(q);
	}
	private int pos(int row, int col)
	{
		return width*row+col;
	}
	public Percolation(int n)
	{
		/* 
		 * create n-by-n grid, with all sites blocked.
		 * we need 2 more ids for top and bottom nodes
		 */
		size = n*n;
		width = n;
		id = new int[size + 2];
		grid = new int[width][width];
		for (int i=0; i < size+2; i++) {
			id[i] = i;
		}
	}
	public void printState()
	{
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
	public void open(int row, int col)
	{
		/* 
		 * open site (row, col) if it is not open already
		 */
		row = row -1;
		col = col -1;
		grid[row][col] = 1;
		nOpenSites++;
		//System.out.println("Open " + row + " " +  col);
		int pos = pos(row,col);
		if (testOpen(row-1,col)) union(pos,pos-width);
		if (testOpen(row+1,col)) union(pos,pos+width);
		if (testOpen(row,col-1)) union(pos,pos-1);
		if (testOpen(row,col+1)) union(pos,pos+1);
		if (row == 0) union(pos,size);
		if (row == width-1) union(pos,size+1);
	}
	private boolean testOpen(int row, int col)
	{
		if (row < 0 || col < 0) return false;
		if (row >= width || col >= width) return false;
		//System.out.println("Isopen " + row + " " +  col);
		return grid[row][col] == 1;
	}
	public boolean isOpen(int row, int col)
	{
		/*
		 *  is site (row, col) open?
		 */
		return grid[row-1][col-1] == 1;
	}
	public boolean isFull(int row, int col)
	{
		/*
		 *  is site (row, col) full?
		 *  full = connected to top
		 */
		return connected(pos(row-1,col-1),size);
	}
	public int numberOfOpenSites()
	{
		/*
		 *  number of open sites
		 */
		return nOpenSites;
	}
	public boolean percolates()
	{
		/*
		 *  does the system percolate?
		 *  top connected to bottom
		 */
		printState();
		return connected(size,size+1);
	}
	public static void main(String[] args)
	{
		System.out.println("hello nabwa");
		Percolation model = new Percolation(4);
		//model.printState();
		//model.open(0,0);
		//model.open(1,0);
		model.open(1,1);
		model.open(2,1);
		model.open(3,1);
		System.out.println(model.percolates());
		model.printState();
	}

}
