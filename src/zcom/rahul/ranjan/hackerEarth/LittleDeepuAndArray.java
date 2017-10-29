package zcom.rahul.ranjan.hackerEarth;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;

public class LittleDeepuAndArray {

	
	static int segmentTree[]; // To store segment tree
	static int lazy[]; // To store pending updates

	/*
	 * si -> index of current node in segment tree ss and se -> Starting and ending
	 * indexes of elements for which current nodes stores sum. us and eu -> starting
	 * and ending indexes of update query ue -> ending index of update query diff ->
	 * which we need to add in the range us to ue
	 */
	void updateRangeUtil(int si, int ss, int se, int us, int ue, int diff) {
		// If lazy value is non-zero for current node of segment
		// tree, then there are some pending updates. So we need
		// to make sure that the pending updates are done before
		// making new updates. Because this value may be used by
		// parent after recursive calls (See last line of this
		// function)
		if (lazy[si] != 0) {
			// Make pending updates using value stored in lazy
			// nodes
			segmentTree[si] += (se - ss + 1) * lazy[si];

			// checking if it is not leaf node because if
			// it is leaf node then we cannot go further
			if (ss != se) {
				// We can postpone updating children we don't
				// need their new values now.
				// Since we are not yet updating children of si,
				// we need to set lazy flags for the children
				lazy[si * 2 + 1] += lazy[si];
				lazy[si * 2 + 2] += lazy[si];
			}

			// Set the lazy value for current node as 0 as it
			// has been updated
			lazy[si] = 0;
		}

		// out of range
		if (ss > se || ss > ue || se < us)
			return;

		// Current segment is fully in range
		if (ss >= us && se <= ue) {
			// Add the difference to current node
			segmentTree[si] += (se - ss + 1) * diff;

			// same logic for checking leaf node or not
			if (ss != se) {
				// This is where we store values in lazy nodes,
				// rather than updating the segment tree itelf
				// Since we don't need these updated values now
				// we postpone updates by storing values in lazy[]
				lazy[si * 2 + 1] += diff;
				lazy[si * 2 + 2] += diff;
			}
			return;
		}

		// If not completely in rang, but overlaps, recur for
		// children,
		int mid = (ss + se) / 2;
		updateRangeUtil(si * 2 + 1, ss, mid, us, ue, diff);
		updateRangeUtil(si * 2 + 2, mid + 1, se, us, ue, diff);

		// And use the result of children calls to update this
		// node
		segmentTree[si] = segmentTree[si * 2 + 1] + segmentTree[si * 2 + 2];
	}

	// Function to update a range of values in segment
	// tree
	/*
	 * us and eu -> starting and ending indexes of update query ue -> ending index
	 * of update query diff -> which we need to add in the range us to ue
	 */
	void updateRange(int n, int us, int ue, int diff) {
		updateRangeUtil(0, 0, n - 1, us, ue, diff);
	}

	/*
	 * A recursive function to get the sum of values in given range of the array.
	 * The following are parameters for this function. si --> Index of current node
	 * in the segment tree. Initially 0 is passed as root is always at' index 0 ss &
	 * se --> Starting and ending indexes of the segment represented by current
	 * node, i.e., tree[si] qs & qe --> Starting and ending indexes of query range
	 */
	int getSumUtil(int ss, int se, int qs, int qe, int si) {
		// If lazy flag is set for current node of segment tree,
		// then there are some pending updates. So we need to
		// make sure that the pending updates are done before
		// processing the sub sum query
		if (lazy[si] != 0) {
			// Make pending updates to this node. Note that this
			// node represents sum of elements in arr[ss..se] and
			// all these elements must be increased by lazy[si]
			segmentTree[si] += (se - ss + 1) * lazy[si];

			// checking if it is not leaf node because if
			// it is leaf node then we cannot go further
			if (ss != se) {
				// Since we are not yet updating children os si,
				// we need to set lazy values for the children
				lazy[si * 2 + 1] += lazy[si];
				lazy[si * 2 + 2] += lazy[si];
			}

			// unset the lazy value for current node as it has
			// been updated
			lazy[si] = 0;
		}

		// Out of range
		if (ss > se || ss > qe || se < qs)
			return 0;

		// At this point sure, pending lazy updates are done
		// for current node. So we can return value (same as
		// was for query in our previous post)

		// If this segment lies in range
		if (ss >= qs && se <= qe)
			return segmentTree[si];

		// If a part of this segment overlaps with the given
		// range
		int mid = (ss + se) / 2;
		return getSumUtil(ss, mid, qs, qe, 2 * si + 1) + getSumUtil(mid + 1, se, qs, qe, 2 * si + 2);
	}

	// Return sum of elements in range from index qs (query
	// start) to qe (query end). It mainly uses getSumUtil()
	int getSum(int n, int qs, int qe) {
		// Check for erroneous input values
		if (qs < 0 || qe > n - 1 || qs > qe) {
			System.out.println("Invalid Input");
			return -1;
		}

		return getSumUtil(0, n - 1, qs, qe, 0);
	}

	/*
	 * A recursive function that constructs Segment Tree for array[ss..se]. si is
	 * index of current node in segment tree st.
	 */
	static void constructST(int arr[], int sequenceStart, int sequenceEnd, int sequenceIndex) {
		// out of range as ss can never be greater than se
		if (sequenceStart > sequenceEnd)
			return;

		/*
		 * If there is one element in array, store it in current node of segment tree
		 * and return
		 */
		if (sequenceStart == sequenceEnd) {
			segmentTree[sequenceIndex] = arr[sequenceStart];
			return;
		}

		/*
		 * If there are more than one elements, then recur for left and right subtrees
		 * and store the sum of values in this node
		 */
		int mid = (sequenceStart + sequenceEnd) / 2;
		constructST(arr, sequenceStart, mid, sequenceIndex * 2 + 1);
		constructST(arr, mid + 1, sequenceEnd, sequenceIndex * 2 + 2);

		segmentTree[sequenceIndex] = segmentTree[sequenceIndex * 2 + 1] + segmentTree[sequenceIndex * 2 + 2];
	}

	/*
	 * Function to construct segment tree from given array. This function allocates
	 * memory for segment tree and calls constructSTUtil() to fill the allocated
	 * memory
	 */
	public static int[] constructSegmentTree(int[] arr) {
		int height = (int) Math.ceil(Math.log(arr.length) / Math.log(2));
		int size = 2 * (int) Math.pow(2, height) - 1;
		segmentTree = new int[size];
		lazy=new int[size];
		constructST(arr, 0, arr.length - 1, 0);
		return segmentTree;
	}

	// Driver program to test above functions
	public static void main(String args[]) throws Exception {
		

		MyInputStreamReader myInputStreamReader;
		InputStream is = SmartTravelAgent.class.getResourceAsStream(LittleDeepuAndArray.class.getSimpleName() + ".txt");
		if (is == null) {
			myInputStreamReader = new MyInputStreamReader(System.in);
		} else {
			myInputStreamReader = new MyInputStreamReader(is);
		}
		int N = myInputStreamReader.readInt();
		int arr[]=new int[N];
		for(int i=0;i<N;i++)
		{
			arr[i]=myInputStreamReader.readInt();
		}
		
		int noOfOperationsM = myInputStreamReader.readInt();
		
		int X = myInputStreamReader.readInt();
		
		LittleDeepuAndArray tree = new LittleDeepuAndArray();

		// Build segment tree from given array
		constructSegmentTree(arr);
		System.out.println(Arrays.toString(segmentTree));
/*
		// Print sum of values in array from index 1 to 3
		System.out.println("Sum of values in given range = " + tree.getSum(N, 1, 3));

		// Add 10 to all nodes at indexes from 1 to 5.
		tree.updateRange(N, 1, 5, 10);

		// Find sum after the value is updated
		System.out.println("Updated sum of values in given range = " + tree.getSum(N, 1, 3));*/
	}
	
	public static class MyInputStreamReader {
		private byte[] buf = new byte[1 << 8];
		private int curChar;
		private int charsRead;
		private InputStream stream;

		public MyInputStreamReader(InputStream stream) {
			this.stream = stream;
		}

		public int readInt() throws IOException {
			int c = read();
			while (skip(c))
				c = read();
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			} while (!skip(c));
			return res * sgn;
		}

		public long readLong() throws IOException {
			int c = read();
			while (skip(c))
				c = read();
			int sign = 1;
			if (c == '-') {
				sign = -1;
				c = read();
			}
			long res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			} while (!skip(c));
			return res * sign;
		}

		private int read() throws IOException {
			if (charsRead == -1)
				throw new InputMismatchException();
			if (curChar >= charsRead) {
				curChar = 0;
				charsRead = stream.read(buf);
				if (charsRead <= 0)
					return -1;
			}
			return buf[curChar++];
		}

		private static boolean skip(int c) {
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

}
}
