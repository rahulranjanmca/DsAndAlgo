package zcom.rahul.ranjan.tw.one;

import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class TestClass {
	public static void main(String[] args) throws Exception {

		MyInputStreamReader myInputStreamReader;
		InputStream is = TestClass.class.getResourceAsStream(TestClass.class.getSimpleName() + ".txt");
		if (is == null) {
			myInputStreamReader = new MyInputStreamReader(System.in);
		} else {
			myInputStreamReader = new MyInputStreamReader(is);
		}
		int noOfTestCase = myInputStreamReader.readInt();

		 for (int i = 0; i < noOfTestCase; i++) {
			int noOfTotalPerson = myInputStreamReader.readInt();
			boolean rightAnswer = true;
			int noOfDistinctGroup = 0;
			for (int j = 0; j < noOfTotalPerson;) {
				int noOfMyGroup = myInputStreamReader.readInt();
				
				if(j+noOfMyGroup>noOfTotalPerson)
				{
					rightAnswer = false;
				}
			
				for (int k = j+1; k < j + noOfMyGroup && k<noOfTotalPerson; k++) {
					int anwwer = myInputStreamReader.readInt();
					if (noOfMyGroup != anwwer) {
						rightAnswer = false;
					}

				}
				if (rightAnswer) {
					noOfDistinctGroup++;
				} 
				j = j + noOfMyGroup;
			}
			if(rightAnswer)
			{
				System.out.println(noOfDistinctGroup);
			}
			else {
				System.out.println("Invalid Data");
			}
			
		}

	}

}

class MyInputStreamReader {
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