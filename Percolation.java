import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation{
	private WeightedQuickUnionUF quickUnionObj;
	private boolean openSites[]; //true if site is open
	private final int rowsInGrid;
	private final int columnsInGrid;
	private final int indexOfTopVirtualNode;
	private final int indexOfBottomVirtualNode;
	private int numberOfOpenSites;
	public Percolation(int n){
		if(n <= 0) {
			 throw new java.lang.IllegalArgumentException();
		}
		WeightedQuickUnionUF quickUnionObj = new WeightedQuickUnionUF((n*n)); // A 1D array with n*n elements is created
		this.quickUnionObj = quickUnionObj;
		rowsInGrid = n;
		columnsInGrid = n;
		openSites = new boolean[(n*n)];
		indexOfTopVirtualNode = get1DIndexOfRowCol(1, 1); // arbitrarily selected (1,1) as top virtual node	
		indexOfBottomVirtualNode = get1DIndexOfRowCol(n, n); // arbitrarily selected (n, n) as bottom virtual node
		numberOfOpenSites = 0;
	}
	private int get1DIndexOfRowCol(int row, int col){ // For a particular row and column in n*n grid, it returns the corresponding index in the 1D array created in WeightedQuickUnionUF
		if(!isValidIndex(row,col)) {
			throw new IllegalArgumentException();
		}
		return ((row-1)*(columnsInGrid) + col) - 1;
	}
	private boolean isValidIndex(int row, int col){ // returns true if (row, col) is within limits of n*n grid
		return((row >= 1) && (col >= 1) && (row <= rowsInGrid) && (col <= columnsInGrid));
	}
	public boolean isOpen(int row, int col){
		if(!isValidIndex(row,col)) {
			throw new IllegalArgumentException();
		}
		if(openSites[get1DIndexOfRowCol(row, col)]) { // If site in the 1D array corresponding to (row, col) is open, return true 
			return true;
		}
		return false;
	}
	public void open(int row, int col){
		if(!isValidIndex(row,col)) {
			throw new IllegalArgumentException();
		}
		int n = get1DIndexOfRowCol(row, col);
		if(openSites[n]) { // If the site is already open, do not execute further steps in this method
			return;
		}
		//System.out.println("n = " + n);
		openSites[n] = true; // Since we open site with index n, we make index n of openSites[] true
		numberOfOpenSites++;
		if(col != columnsInGrid) { // If site is in the rightmost column, there is no element to its right 
			int indexOfRightCell = get1DIndexOfRowCol(row, col+1);
			if(isOpen(row, col+1)) {
				quickUnionObj.union(n, indexOfRightCell);
			}
		}
		if(col != 1){ // If it's the leftmost column (col = 1), there is no element to its left	
			int indexOfLeftCell = get1DIndexOfRowCol(row, col-1);
			if(isOpen(row, col-1)) {
				quickUnionObj.union(n, indexOfLeftCell);
			}
		}
		if(row == 1) { // If a site in the first row is open, it has to be connected to the top virtual node
			quickUnionObj.union(n, indexOfTopVirtualNode);
		}
		else { // If a site is not in the first row, connect it and it's top neighbor
			int indexOfTopCell = get1DIndexOfRowCol(row-1, col);
			if(isOpen(row-1, col)) {
				quickUnionObj.union(n, indexOfTopCell);
			}
		}
		if(row == rowsInGrid) { // If a site is in the bottom-most row, it has to be connected to the bottom virtual node 
			quickUnionObj.union(n, indexOfBottomVirtualNode); // 
		}
		else { // If a site is not in the bottom-most row, connect it and it's bottom neighbor 
			int indexOfBottomCell = get1DIndexOfRowCol(row+1, col); 
			if(isOpen(row+1, col)) {
				quickUnionObj.union(n, indexOfBottomCell);
			}
		}	
	}
	public boolean isFull(int row, int col){
		int n = get1DIndexOfRowCol(row, col);
		if((isOpen(row, col)) && (quickUnionObj.connected(n, indexOfTopVirtualNode))){ // By definition, full sites are open sites which are connected to the top row
			return true;
		}
		return false;
	}
	public int numberOfOpenSites(){
		return numberOfOpenSites;
	}
	public boolean percolates(){
		if(rowsInGrid == 1 && columnsInGrid == 1) {
			return (isOpen(1,1)? true : false);
		}
		// If top virtual node and bottom virtual node are in the same component, then the system percolates
		if(quickUnionObj.connected(indexOfTopVirtualNode, indexOfBottomVirtualNode)) {
			return true;
		}
		return false;
	}
	
	
	/*private boolean isConnected(int p, int q) { // Method for testing
		return(quickUnionObj.connected(p, q));
	} 
	/*
	public static void main(String args[]) {
		Percolation testObj = new Percolation(Integer.parseInt(args[0]));
		/*testObj.open(1, 1);
		testObj.open(2, 1);
		testObj.open(2, 2);
		testObj.open(3,2);
		testObj.open(2, 3);
		System.out.println(testObj.isConnected(0, 7));
		System.out.println(testObj.numberOfOpenSites());
		System.out.println(testObj.percolates());
		System.out.println(testObj.isFull(3, 1)); */
		/*
		testObj.open(1,6);
		System.out.println(testObj.isFull(1, 6)); 
	}
	*/
}