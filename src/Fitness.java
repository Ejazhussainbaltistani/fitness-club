import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Fitness {
	
	private String fitnessType,startDate,endDate;
    private static List<Booking> bookings;
    private static FitnessClass[] fitnessClasses;
    private static String [] feedbacks;

    public Fitness(FitnessClass [] fitnessClasses,String fitnessType) {
    	startDate="2023-04-10";
    	endDate="2023-06-18";
    	this.fitnessType=fitnessType;
        bookings = new ArrayList<>();
        this.fitnessClasses = fitnessClasses;
        feedbacks=new String[]{"very bad","bad","average","good","very good"};
    }
    
    public static FitnessClass getFitnessByShiftAndDay( String shift, DayOfWeek day) {
					for (FitnessClass fitnessClass : fitnessClasses) {
					    if (fitnessClass.getShift().equals(shift) && fitnessClass.getDay().equals(day)) {
					        return fitnessClass;
					    }
					}
        
        return null;
    }
    public static void showBookings()
    {
    	for(Booking b:bookings)
    	{
    		b.display();
    	}
    }
    
    public FitnessClass getFitnessClassByLesson(String name) {
       FitnessClass fitnessClass=null;
        	for(FitnessClass f:fitnessClasses)
        	{
        		if(f.getLesson().equals(name)) {
        			fitnessClass=f;
        		}
        	}
       
        
        return fitnessClass; // indicate that the class is not available on the specified date and shift
    }

 
    

    public int checkAvailableSeats(LocalDate date, String shift) {
        int count = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (Booking booking : bookings) {
        	String id=booking.getClassId();
        	String shifts="";
        	for(FitnessClass f:fitnessClasses)
        	{
        		if(f.getClassId().equals(id)) {
        			shifts=f.getShift();
        			
        		}
        	}
            if (booking.getDate().equals(date) && shifts.equals(shift)) {
                count++;
            }
        }
        
        return 5-count; // indicate that the class is not available on the specified date and shift
    }
    
    public void checkFeedbacks(LocalDate date, String shift) {
        int count = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (Booking booking : bookings) {
        	String id=booking.getClassId();
        	String shifts="";
        	for(FitnessClass f:fitnessClasses)
        	{
        		if(f.getClassId().equals(id)) {
        			shifts=f.getShift();
        			
        		}
        	}
            if (booking.getDate().equals(date) && shifts.equals(shift)) {
              System.out.println(booking.getFeedback()+"\n");
            }
           
            
        }
        
        // indicate that the class is not available on the specified date and shift
    }
    
   public LocalDate displayAvailableSeats(FitnessClass f) {
    	
        LocalDate date1 = LocalDate.parse(startDate);
        LocalDate date2 = LocalDate.parse(endDate);
         List<LocalDate> dates=new ArrayList<>();
        
        // Define the date format to use when printing the dates
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // Print all dates between the first and second date
        LocalDate currentDate = date1;
        System.out.print("\n\n"+f.getLesson()+"("+f.getFee()+"$)		Feedbacks\n\n");		
        System.out.print("-------------------------------------\n\n");	
        int i=0;
       while (currentDate.isBefore(date2)) {
       	 DayOfWeek day=currentDate.getDayOfWeek();
       	 if(day==f.getDay()) {
       	 int c1=checkAvailableSeats(currentDate,f.getShift()) ;

       	 if(day==DayOfWeek.SUNDAY||day==DayOfWeek.SATURDAY ) {
            System.out.print(i+1+"   "+currentDate.format(dateFormatter)+"("+day+")"+"	"+c1+"\n\n");
            dates.add(currentDate);
            i++;
       	 }
       	 }
       	 
       	 currentDate = currentDate.plusDays(1);
       	 
        }
       if(i==0)
    	   return null;
       Scanner scanner = new Scanner(System.in);
       int choice = -1;
       while (choice < 1 || choice > dates.size()) {
           System.out.print("Enter the number of Date  you want to select: ");
           choice = scanner.nextInt();
           if (choice < 1 || choice > dates.size()) {
               System.out.println("Invalid choice. Please enter a number between 1 and " + dates.size());
           }
       }
       System.out.println("You have selected the " +dates.get(choice-1) + " date.\n\n");
       
     return dates.get(choice-1);
        
    
    }
    
    
public void displayReports(FitnessClass f) {
    	
        LocalDate date1 = LocalDate.parse(startDate);
        LocalDate date2 = LocalDate.parse(endDate);
         List<LocalDate> dates=new ArrayList<>();
        
        // Define the date format to use when printing the dates
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // Print all dates between the first and second date
        LocalDate currentDate = date1;
        System.out.print("\n\n"+f.getLesson()+"("+f.getFee()+"$)		FeedBacks\n\n");		
        System.out.print("-------------------------------------\n\n");	
        int i=0;
       while (currentDate.isBefore(date2)) {
       	 DayOfWeek day=currentDate.getDayOfWeek();
       	 if(day==f.getDay()) {
       	 

       	 if(day==DayOfWeek.SUNDAY||day==DayOfWeek.SATURDAY ) {
            System.out.print(i+1+"   "+currentDate.format(dateFormatter)+"("+day+")"+"\n");
            checkFeedbacks(currentDate,f.getShift()) ;
            dates.add(currentDate);
            i++;
       	 }
       	 }
       	 
       	 currentDate = currentDate.plusDays(1);
       	 
        }
  
        
    
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
