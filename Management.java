package memoryUsage;
import java.util.Random;
import societyAperson.BirthCertificate;
import societyAperson.CitizenId;
import societyAperson.Elector;

import java.util.ArrayList;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

import java.time.Instant;
import java.time.Duration;

/**
* The Management is a class whose sake is measure the memory compsumption.
* this class leans on Elector class useful to construct an Arraylist with
* an object collection of such class. 
* The current class builds randomly the input data.
* @author  Hector Jose Vurchio Hurtado
* @version 1.0
* @since   2021-07-08 
*/

public class Management{
	/**
	* This method is used to generate an String with an array of byte.
	* min or max = [number of characters, Capital, Small]
	* n = number of characters.
	* @return a String name.
	*/	
	String nameGenerator(){
		Random random = new Random();
		int min[] = {4,65,97};
		int max[] = {10,90,122};
		int n = random.nextInt(max[0]-min[0])+min[0];
		byte b[] = new byte[n];
		for(int i = 0;i<n;i++){
			if(i == 0){
				b[i] = Integer.valueOf(random.nextInt(max[1]-min[1])+min[1]).byteValue();
			}else{
				b[i] = Integer.valueOf(random.nextInt(max[2]-min[2])+min[2]).byteValue();
			}
		}
		return new String(b);
	}
	/**
	* Method to generate a random birth date.
	* min or max = [year, month, day]
	* @return a String birth date.
	*/	
	String birthGenerator(){
		Random random = new Random();
		int min[] = {1920,1,1};
		int max[] = {2000,12,28};
		String year = Integer.valueOf(random.nextInt(max[0]-min[0])+min[0]).toString();
		int m = random.nextInt(max[1]-min[1])+min[1];
		String month = (m<10) ? "0"+Integer.valueOf(m).toString() : Integer.valueOf(m).toString();
		int d = random.nextInt(max[1]-min[1])+min[1];
		String day = (d<10) ? "0"+Integer.valueOf(d).toString() : Integer.valueOf(d).toString();
		return year+"-"+month+"-"+day;
	}
	/**
	* Method to generate a random initials gender.
	* @return a char initials gender.
	*/	
	char genderGenerator(){
		char gender[] = {'m','f'};
		Random random = new Random();
		int index = random.nextInt(2);
		return gender[index];
	}
	/**
	* Method to generate a random party.
	* @return a String party name.
	*/		
	String partyGenerator(){
		String parties[] = {"Comunist_Party","Right_Wing_party","Green_party"};
		Random random = new Random();
		int index = random.nextInt(3);
		return parties[index];
	}
	/**
	* Method to chose randomly if the elector voted or not.
	* @return a char initials yes or not.
	*/	
	char voteStatusGen(){
		char status[] = {'y','n'};
		Random random = new Random();
		int index = random.nextInt(2);
		return status[index];
	}
	/**
	* Method to generate a random time of vote.
	* min or max = [hour, minute, second]
	* @return a String vote time.
	*/		
	String timeGenerator(){
		Random random = new Random();
		int min[] = {0,0,0};
		int max[] = {24,60,60};
		int h = random.nextInt(max[0]-min[0])+min[0];
		String hour = (h<10) ? "0"+Integer.valueOf(h).toString() : Integer.valueOf(h).toString();
		int m = random.nextInt(max[1]-min[1])+min[1];
		String minute = (m<10) ? "0"+Integer.valueOf(m).toString() : Integer.valueOf(m).toString();
		int s = random.nextInt(max[2]-min[2])+min[2];
		String second = (s<10) ? "0"+Integer.valueOf(s).toString() : Integer.valueOf(s).toString();
		return hour+":"+minute+":"+second;
	}
	/**
	* Method to build an elector object
	* @return an elector object
	*/		
	Elector electorGenerator(){
		Random rand = new Random();
		BirthCertificate bicert = new BirthCertificate(nameGenerator(),
														birthGenerator(),
														genderGenerator());
		CitizenId citizenId = new CitizenId(bicert,
							Integer.valueOf(rand.nextInt(20000000)).toString());
		citizenId.setName(nameGenerator());
		citizenId.setLastName(nameGenerator());
		Elector elector = new Elector(citizenId);
		elector.setParty(partyGenerator());
		char status = voteStatusGen();
		elector.setVoteStatus(status);
		String time = (status == 'y') ? timeGenerator() : "";
		elector.setTime(time);
		return elector;
	}
	/**
	* Method to calculate % of heap memory used.
	* 1024*1024 transforms from bytes to mb
	* @return the percentage of memory used
	*/		
	static long percHeapMemoryUsed(){
		MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
		MemoryUsage heapUsage = memoryBean.getHeapMemoryUsage();
		int mbConvers = 1024 * 1024;
		long usedMemory = heapUsage.getUsed()/mbConvers;
		long maxMemory = heapUsage.getMax()/mbConvers;
		long percentageUsed = (long)(100.0*((1.0*usedMemory)/(1.0*maxMemory)));
		return percentageUsed;
	}
	
	
	/**
   * The main method that starts the program.
   * @param args, the command line arguments which not in use.
   */
	public static void main(String[] args){
		Management management = new Management();
		Elector elector;
		ArrayList<Elector> elecList = new ArrayList<Elector>();
		
		//get initial time
		Instant start = Instant.now();
		for(int i = 0; i< 1000000;i++){
		//while(true){
			elector = management.electorGenerator();
			elecList.add(elector);
			System.out.println("% of memory used: "+management.percHeapMemoryUsed()+
								"  List Size: "+elecList.size());
		}
		//get ending time
		Instant end = Instant.now();
		System.out.println("Initial Time "+start.toString());
		System.out.println("ending time "+end.toString());
		//stablish how long does the program take take
		Duration timeElapsed = Duration.between(start,end);
		long millis = timeElapsed.toMillis();
		System.out.println("time elapsed "+millis+" miliseconds");
	}
	
}

/*

javac -d . *.java

java memoryUsage.Management

git init 

git checkout -b main

touch README.md

touch .gitgnore

git remote add origin https://github.com/HectorVurchio/MemoryAndTimeConsumption.git

git push -u origin main

*/
