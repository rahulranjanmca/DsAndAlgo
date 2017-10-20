package zcom.rahul.ranjan.samsung.two;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ThreadLocalRandom;

public class DummyQuestionWriter {
	public static String FILENAME="input1.txt";
	public static ThreadLocalRandom tlr=	ThreadLocalRandom.current();
	public static void main(String[] args) {
		
		int N=1000;
		int M= 999;
		
		
			
		
				FileWriter fw = null;
			  PrintWriter out =null; 

				try {

					
					

					fw = new FileWriter(FILENAME);
					out =new PrintWriter(fw);
				    out.println(N+" "+M);			
				
					for(int i=0;i<N;i++)
					{
						for(int j=0;j<M;j++)
						{
							out.print(tlr.nextInt(2, 100000)+" ");
						}
						out.println();
						
						//out.print(" ");
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
