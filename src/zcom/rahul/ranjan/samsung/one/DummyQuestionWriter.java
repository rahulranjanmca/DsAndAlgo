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
					
					StringBuffer string= new StringBuffer();
					for(int i=0;i<100000;i++)
					{
						
					
			
						string.append(9999+" "+9999+" "+9999);
						string.append("\n");
						
						//out.print(" ");
					}
				//	System.out.println(string);
					
					out.println(string);
					

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
