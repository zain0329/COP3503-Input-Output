/* 
*  Author: Zain Malik 
*  Course: COP3503 
*  Project #: 2 
*  Title  : Data Preprocessing 
*  Due Date:  10/26/2022
*  
*  Preprocesses data from a text File
*/ 
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.io.IOException;

/** 
 * Preprocesses data from a text File.
 */ 
public class Project2_N01486327 {
	
	//Declaration of public array list variables
	public static ArrayList<String> dates = new ArrayList<String>();
	public static ArrayList<String> times = new ArrayList<String>();
	public static ArrayList<Double> sensor2278 = new ArrayList<Double>();
	public static ArrayList<Double> sensor3276 = new ArrayList<Double>();
	public static ArrayList<Double> sensor4689 = new ArrayList<Double>();
	public static ArrayList<Double> sensor5032 = new ArrayList<Double>();
	public static ArrayList<Double> section1Diff = new ArrayList<Double>();
	public static ArrayList<Double> section2Diff = new ArrayList<Double>();
	public static ArrayList<Double> totalAvg = new ArrayList<Double>();
	
	/** 
	   * Reads in data from the text file.
	* @return A Buffered Reader that has read in data from the text file.
	*/ 
	public static BufferedReader readData(String input) throws FileNotFoundException {
	    BufferedReader br = new BufferedReader(new FileReader(input));
		return br;
	}
	
	/** 
	   * Calculates averages of sensor array lists
	* @return ArrayList totalAvg updated with the averages of the sensor array lists.
	*/ 
	public static void calcAverage() {
		//caclulate average of sensor2278
	    double sensor2278Sum = 0;
  	    for (double d : sensor2278) {
  		  sensor2278Sum +=d;
  	    }
  	    double sensor2278Average = sensor2278Sum / sensor2278.size();
  	    
  	    //caclulate average of sensor3276
	    double sensor3276Sum = 0;
  	    for (double d : sensor3276) {
  		  sensor3276Sum +=d;
  	    }
  	    double sensor3276Average = sensor3276Sum / sensor3276.size();  	    
  	    
  	    //caclulate average of sensor4689
	    double sensor4689Sum = 0;
  	    for (double d : sensor4689) {
  	    	sensor4689Sum +=d;
  	    }
  	    double sensor4689Average = sensor4689Sum / sensor4689.size();
  	    
  	    //caclulate average of sensor5032
	    double sensor5032Sum = 0;
  	    for (double d : sensor5032) {
  	    	sensor5032Sum +=d;
  	    }
  	    double sensor5032Average = sensor5032Sum / sensor5032.size();
  	    
  	    //update totalAvg array list
  	    totalAvg.add(sensor2278Average);
  	    totalAvg.add(sensor3276Average);
  	    totalAvg.add(sensor4689Average);
  	    totalAvg.add(sensor5032Average);
	}
	
	/** 
	   * Writes data to a text file.
	* @return Text file with written data.
	*/ 
	public static void writeData(String input) throws IOException {
		//create string variable for name of file
		String outputFileName = input.replace(".csv", "_Difference.csv");
		
		//create FileWriter
		FileWriter fw =new FileWriter(outputFileName);
		
		System.out.println("Writing data to file "+outputFileName);
		
		//write headers to file
		fw.write("Date,Time,Sensor_2278,Sensor_3276,Sensor_4689,Sensor_5032,Section1_Diff,Section2_Diff,Total_Avg \n");
		
		//write dates to file
		Object[] dateArray = dates.toArray();
		Object[] timeArray = times.toArray();
		Object[] sensor2278Array = sensor2278.toArray();
		Object[] sensor3276Array = sensor3276.toArray();
		Object[] sensor4689Array = sensor4689.toArray();
		Object[] sensor5032Array = sensor5032.toArray();
		Object[] section1diffArray = section1Diff.toArray();
		Object[] section2diffArray = section2Diff.toArray();
		Object[] totalAvgArray = totalAvg.toArray();
		Object[] totalAvgArrayCopy = Arrays.copyOf(totalAvgArray, dateArray.length);
		for (int i = 0; i < dateArray.length; i++) {
	         fw.write(dateArray[i] + ",");
	         fw.write(timeArray[i] + ",");
	         fw.write(sensor2278Array[i] + ",");
	         fw.write(sensor3276Array[i] + ",");
	         fw.write(sensor4689Array[i] + ",");
	         fw.write(sensor5032Array[i] + ",");
	         fw.write(section1diffArray[i] + ",");
	         fw.write(section2diffArray[i] +",");
	         fw.write(totalAvgArrayCopy[i] +",\n");
	      }
		
		//close FileWriter
		fw.close();
	}

	public static void main(String[] args) throws IOException {
		
		//Display project title, followed by a blank line
		System.out.println("Project 2 Data Preprocessing\n");
		
		//Do-while loop that loops program
	    String choice = " ";
			do {
				switch (choice){
				    	
				    case "?":
				            System.out.println("Enter file name & location");
				            break;
				        }  
				//Enters file name by user keyboard input.
				System.out.println("Enter file name & location."); 
				Scanner scnr = new Scanner(System.in);
			    String inputFileName = scnr.nextLine();
			    
			    //boolean variables declaration
			    boolean isProjectDone = true;
			    boolean isParseError = false;
			    
			    //try statement for file not found exception try-catch
				try { 
					
			      System.out.println("Reading in Data from the file " + inputFileName);
			    	  
			      BufferedReader br = readData(inputFileName);
			      String line = "";
			      String dl = ",";

				  line = br.readLine();
				  String[] reader;
				  
				  //try statement for number format exception try-catch
				  try {
					
			        while ((line = br.readLine()) != null) {

			    	  reader = line.split(dl);
			    	  
			    	  //try statement for illegal argument exception, parse exception try-catch
			    	  try {
 
			    	  //formatting and storing date data in date array list
			    	  @SuppressWarnings("deprecation")
					  Date date = new Date(reader[0]);
			    	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd"); 
			    	  
			    	  dates.add(formatter.format(date));
			    	  if(isParseError == true) {
			    	     throw new ParseException("Couldn't parse date-string: ", 0);
			    	  }
			    	  
			    	  //catch statement for illegal argument exception, parse exception try-catch
			    	  } catch(IllegalArgumentException | ParseException e) {
			    		  System.out.println("Converting Dates from MM/DD/YYYY to YYYY/MM/DD");
			    		  System.out.println("*Bad Date Data in CSV File.*");
			    		  System.out.println("Check CSV file data and try again.");
			    		  isProjectDone = false;
			    	  }
			    	  
			    	  //storing time data in times array list
			    	  times.add(reader[1]);
			    	  
			    	  //storing sensor 2278 data in sensor2278 array list
			    	  sensor2278.add(Double.parseDouble(reader[2]));
			    	 
			    	  //storing sensor 3276 data in sensor3276 array list
			    	  sensor3276.add(Double.parseDouble(reader[3]));

			    	  //storing sensor 4689 data in sensor4689 array list
			    	  sensor4689.add(Double.parseDouble(reader[4]));

			    	  //storing sensor 5032 data in sensor5032 array list
			    	  sensor5032.add(Double.parseDouble(reader[5]));
			    	  
			    	  //storing difference between sensor 2278 data  and sensor 3276 data in section1Diff array list
			    	  section1Diff.add(Double.parseDouble(reader[2]) - Double.parseDouble(reader[3]));
			    	  
			    	  //storing difference between sensor 4689 data  and sensor 5032 data in section1Diff array list
			    	  section2Diff.add(Double.parseDouble(reader[4]) - Double.parseDouble(reader[5]));
			    	  }
			        
			          //call calcAverage method
			          calcAverage();
			        
				  //catch statement for number format exception try-catch
				  } catch(NumberFormatException nfe) {
			    		  System.out.println("*Bad Number Data in CSV File.*");
			    		  System.out.println("Check CSV file data and try again.");
			    		  isProjectDone = false;
			    	  }
			      
				//catch statement for file not found exception try-catch  
				} catch (FileNotFoundException fnfe) {
					System.out.println("*File does not exist or path was entered incorrectly.*");
					System.out.println("Please try again.");
					isProjectDone = false;
				}
		       
				if(isProjectDone == true) {
					//print statements
					System.out.println("Converting Dates from MM/DD/YYYY to YYYY/MM/DD");
					System.out.println("Calculating Speed Difference");
					System.out.println("Calculating Speed Average");
					
					//call writeData method
					writeData(inputFileName);
					
					//end program
					System.out.println("Done! Exiting Program");
					System.exit(0);
					
					//close scanner
					scnr.close();
				}
		        
			}while (choice != "1");
		      
	}
}
