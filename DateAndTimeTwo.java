import java.time.Instant;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.time.DayOfWeek;
import java.time.temporal.TemporalAdjusters;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.time.format.TextStyle;

public class DateAndTimeTwo{
	private void wastingTime(){
		for(int i = 0;i<10;i++){
			System.out.println("Number "+i);
		}
	}
	private void runningTime(){
		Instant start = Instant.now();
		System.out.println("start "+start.toString());
		wastingTime();
		Instant end = Instant.now();
		System.out.println("end "+end.toString());
		Duration timeElapsed = Duration.between(start,end);
		long millis = timeElapsed.toMillis();
		System.out.println("time elapsed "+millis);
	}
	
	private void getDates(){
		LocalDate today = LocalDate.now();
		System.out.println("today "+today);
		LocalDate alonzosBirthday = LocalDate.of(1903,06,14);
		alonzosBirthday = LocalDate.of(1903,Month.JUNE,14);
		System.out.println("alonzosBirthday  "+alonzosBirthday);
		
		LocalDate programmersDay = LocalDate.of(2014,1,1).plusDays(255);
		System.out.println("programmersDay "+programmersDay);
		
		LocalDate myBirthday = LocalDate.of(2021,8,29);
		long untMyBir = today.until(myBirthday,ChronoUnit.DAYS);
		System.out.println("From today on my birthday left "+untMyBir+" days");
		
		LocalDate febDat = LocalDate.of(2016,1,31).plusMonths(1);
		System.out.println("febDat "+febDat);
		febDat = LocalDate.of(2016,3,31).minusMonths(1);
		System.out.println("febDat "+febDat);
		System.out.println("DayOfWeek.MONDAY = "+DayOfWeek.MONDAY+", DayOfWeek.SATURDAY = "+DayOfWeek.SATURDAY);
		
		LocalDate RoccosBirth = LocalDate.of(2018,3,30);
		DayOfWeek dayOfBir = RoccosBirth.getDayOfWeek();
		System.out.println("Week day Rocco was born "+dayOfBir+", Value = "+dayOfBir.getValue());
		
		LocalDate firstTuesday = LocalDate.of(2021,5,1).with(TemporalAdjusters
														.nextOrSame(DayOfWeek.TUESDAY));
		System.out.println("firstTuesday of May 2021"+firstTuesday);
	}
	
	private void getTime(){
		LocalTime rightNow = LocalTime.now();
		System.out.println("RightNow = "+rightNow);
		LocalTime bedTime = LocalTime.of(22,30);
		System.out.println("bedtime = "+bedTime);
		LocalTime wakeup = bedTime.plusHours(8);
		System.out.println("wakeup = "+wakeup);
	}
	
	private void zoneIdentification(){
		ZoneId zi = ZoneId.of("America/New_York");
		ZonedDateTime apollo11Launch = ZonedDateTime.of(1969,7,16,9,32,0,0,zi);
		System.out.println("apollo11Launch "+apollo11Launch);
		Instant apolloInst = apollo11Launch.toInstant();
		System.out.println("apolloInst "+apolloInst);
		zi = ZoneId.of("UTC");
		ZonedDateTime inUtcInst = apolloInst.atZone(zi);
		System.out.println("inUtcInst = "+inUtcInst);
	}
	
	private void skipHour(){ //daylight saving time
		LocalDate berlinDate = LocalDate.of(2013,3,31);//Start data of daylight saving time
		LocalTime berlinTime = LocalTime.of(2,30);
		ZoneId berlinZI = ZoneId.of("Europe/Berlin");
		ZonedDateTime skipped = ZonedDateTime.of(berlinDate,berlinTime,berlinZI);
		System.out.println("Berlin Time 2:30 => "+skipped);
	}
	
	private void returnSkippedHour(){
		LocalDate berlinDate = LocalDate.of(2013,10,27); //End of daylight skip time
		LocalTime berlinTime = LocalTime.of(2,30);
		ZoneId berlinZI = ZoneId.of("Europe/Berlin");
		ZonedDateTime ambiguous = ZonedDateTime.of(berlinDate,berlinTime,berlinZI);
		System.out.println("Berlin Time 2:30 => "+ambiguous);
		ZonedDateTime anHourLater = ambiguous.plusHours(1);
		System.out.println("Berlin Time 2:30 an hour later => "+anHourLater);
		ZonedDateTime nextMeeting = anHourLater.plus(Duration.ofDays(7));
		System.out.println("next meeting 7 days later => "+nextMeeting);
	}
	
	private void formatForDateTime(){
		ZoneId zi = ZoneId.of("America/New_York");
		ZonedDateTime apollo11Launch = ZonedDateTime.of(1969,7,16,9,32,0,0,zi);
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
		String formatted = formatter.format(apollo11Launch);
		System.out.println("Apollo 11 Launch format LONG: "+formatted);
		formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);
		formatted = formatter.format(apollo11Launch);
		System.out.println("Apollo 11 Launch format FULL: "+formatted);
		formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
		formatted = formatter.format(apollo11Launch);
		System.out.println("Apollo 11 Launch format MEDIUM: "+formatted);
		formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
		formatted = formatter.format(apollo11Launch);
		System.out.println("Apollo 11 Launch format SHORT: "+formatted);
		//IN GERMAN
		formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);
		formatted = formatter.withLocale(Locale.GERMAN).format(apollo11Launch);
		System.out.println("Apollo 11 Launch format FULL: "+formatted);
		//IN Italian
		formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);
		formatted = formatter.withLocale(Locale.ITALIAN).format(apollo11Launch);
		System.out.println("Apollo 11 Launch format FULL: "+formatted);
	}
	
	private void dayOfWeek(){
		for(DayOfWeek w : DayOfWeek.values())
			System.out.print(w.getDisplayName(TextStyle.SHORT,Locale.GERMAN)+"  ");
		System.out.println(" ");
		for(DayOfWeek w : DayOfWeek.values())
			System.out.print(w.getDisplayName(TextStyle.FULL,Locale.GERMAN)+"  ");
		System.out.println(" ");
	}
	
	private void secondFormatDT(){
		ZoneId zi = ZoneId.of("America/New_York");
		ZonedDateTime apollo11Launch = ZonedDateTime.of(1969,7,16,9,32,0,0,zi);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E yyyy-MM-dd HH:mm");
		String formatted = formatter.format(apollo11Launch);
		System.out.println("Apollo 11 Launch format wit Pattern E yyyy-MM-dd HH:mm "+formatted);
		formatter = DateTimeFormatter.ofPattern("EEEE yyyy-MMMM-dd HH:mm");
		formatted = formatter.format(apollo11Launch);
		System.out.println("Apollo 11 Launch format wit Pattern EEEE yyyy-MMMM-dd HH:mm "+formatted);
		formatter = DateTimeFormatter.ofPattern("EEEE dd/MMMM/yyyy HH:mm");
		formatted = formatter.format(apollo11Launch);
		System.out.println("Apollo 11 Launch format wit Pattern EEEE dd/MMMM/yyyy HH:mm "+formatted);
		formatter = DateTimeFormatter.ofPattern("EEEE dd/MMMM/yyyy HH:mm a VV zzzz");
		formatted = formatter.format(apollo11Launch);
		System.out.println("Apollo 11 Launch format wit Pattern EEEE dd/MMMM/yyyy HH:mm a VV zzzz "+formatted);
	}
	
	public static void main(String[] args){
		DateAndTimeTwo dat = new DateAndTimeTwo();
		dat.runningTime();
		dat.getDates();
		dat.getTime();
		dat.zoneIdentification();
		dat.skipHour();
		dat.returnSkippedHour();
		dat.formatForDateTime();
		dat.dayOfWeek();
		dat.secondFormatDT();
	}
}