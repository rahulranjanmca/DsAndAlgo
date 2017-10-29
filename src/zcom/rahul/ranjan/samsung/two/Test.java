package zcom.rahul.ranjan.samsung.two;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class Test {
	 static String[] reformatPatientAppointment(String[] patientName, String[] appointmentDate) {
         String[] resultantArray= new String[patientName.length];
        try{
        	
      Map<String,TreeSet<Date>> map= new HashMap<>();
      Map<String,TreeSet<Date>> map2= new HashMap<>();
      for(int i=0;i<patientName.length;i++)
      {
       SimpleDateFormat simpleDateFormat=   new SimpleDateFormat("ddMMyyyy h:mm");
       Date dateObject = simpleDateFormat.parse(appointmentDate[i]);
       String date=appointmentDate[i].split(" ")[0];
       String key=   patientName[i]+date;
          if(map2.get(date)==null)
          {
             // map.put(key, new TreeSet<Date>());
              map2.put(date, new TreeSet<Date>());
          }
         // map.get(key).add(dateObject);
         
          map2.get(date).add(dateObject);
       
         
      }
      for(int i=0;i<patientName.length;i++)
      {
           SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("ddMMyyyy h:mm");
          Date dateObject= simpleDateFormat.parse(appointmentDate[i]);
           String date=appointmentDate[i].split(" ")[0];
           String key=   patientName[i]+date;
          resultantArray[i]=patientName[i]+" - "+date+" - "+ (map2.get(date).headSet(dateObject).size()+1);
      }
        }   catch(Exception e)
        {
            e.printStackTrace();
        }
        return resultantArray;
    }

	public static void main(String[] args) {
		String [] patients = {"Jame","Joshula", "Jane","Emily","Jack"};
		String [] appointments= {"01012016 08:24", "01012016 9:44", "01032016 10:90" ,"01042016 11:10", "01082016 12:10"};
		String [] result=reformatPatientAppointment(patients, appointments);
		System.out.println(Arrays.toString(result));
	}
	

}
