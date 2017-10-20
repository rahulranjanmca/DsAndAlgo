package zcom.rahul.ranjan.samsung.one;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ThreadLocalRandom;

public class DummyQuestionWriter {
	public static String FILENAME="input1.txt";
	public static ThreadLocalRandom tlr=	ThreadLocalRandom.current();
	public static void main(String[] args) {
		
		int N=100000;
		
		
		
			
		
				FileWriter fw = null;
			  PrintWriter out =null; 

				try {

					
					

					fw = new FileWriter(FILENAME);
					out =new PrintWriter(fw);
					int Q= tlr.nextInt(0, N-1);
					out.println(N+" "+Q);
					StringBuffer string= new StringBuffer();
					for(int i=0;i<N;i++)
					{
						
						long number=tlr.nextLong(1,1000000000l);
						String str=""+number;
						string.append(""+number);
						string.append(" ");
						
						//out.print(" ");
					}
					System.out.println(string);
					
					out.println(string);
					for(int i=0;i<Q;i++)
					{
						int firstBound=tlr.nextInt(1,100000);
						int secondBound=tlr.nextInt(firstBound, 100000);
						out.print(firstBound+" "+secondBound);
						out.print("\n");
					}

					System.out.println("Done");

				} catch (IOException e) {

					e.printStackTrace();

				} finally {

					try {

						if (out != null)
							out.close();

						if (fw != null)
							fw.close();

					} catch (IOException ex) {

						ex.printStackTrace();

					}

				}
		
	}

}
