import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
public void displayTimetable() {
	System.out.print(bookings.size());
	
     LocalDate date1 = LocalDate.parse(startDate);
     LocalDate date2 = LocalDate.parse(endDate);
     
     // Define the date format to use when printing the dates
     DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
     
     // Print all dates between the first and second date
     LocalDate currentDate = date1;
     System.out.print("\n\nDate			first(shift)		Available	second(shift)		Available\n\n");		
     System.out.print("------------------------------------------------------------------------------------------------\n\n");		
    while (currentDate.isBefore(date2)) {
    	 DayOfWeek day=currentDate.getDayOfWeek();
    	 FitnessClass f1=getFitnessByShiftAndDay("first",day);
    	 FitnessClass f2=getFitnessByShiftAndDay("second",day);
    	 int c1=checkAvailableSeats(currentDate,"first") ;
    	 int c2=checkAvailableSeats(currentDate,"second") ;

    	 if(day==DayOfWeek.SUNDAY||day==DayOfWeek.SATURDAY ) {
         System.out.print(currentDate.format(dateFormatter)+"("+day+")"+"	"+f1.getLesson()+"("+f1.getFee()+")		"+c1+"		"+f2.getLesson()+"("+f2.getFee()+")		"+c2+"\n");
    	 }currentDate = currentDate.plusDays(1);
    	 
     }
     
 
 }
public String selectFeedback() {
    System.out.println("Select Feedback");
    for (int i = 0; i < feedbacks.length; i++) {
        System.out.println((i+1) + ". " + feedbacks[i]);
    }

    Scanner scanner = new Scanner(System.in);
    int choice = -1;
    while (choice < 1 || choice > feedbacks.length) {
        System.out.print("Enter the number of the feedback you want to select: ");
        choice = Integer.parseInt(scanner.nextLine());
        if (choice < 1 || choice > feedbacks.length) {
            System.out.println("Invalid choice. Please enter a number between 1 and " + feedbacks.length);
        }
    }
    String f = feedbacks[choice-1];
    System.out.print("You have selected the " + f + " as feedback.");
    return feedbacks[choice-1];
}


public FitnessClass selectLesson() {
    System.out.println("Available lesson types:");
    for (int i = 0; i < fitnessClasses.length; i++) {
        System.out.println((i+1) + ". " + fitnessClasses[i].getLesson());
    }

    Scanner scanner = new Scanner(System.in);
    int choice = -1;
    while (choice < 1 || choice > fitnessClasses.length) {
        System.out.print("Enter the number of the lesson type you want to select: ");
        choice = Integer.parseInt(scanner.nextLine());
        if (choice < 1 || choice > fitnessClasses.length) {
            System.out.println("Invalid choice. Please enter a number between 1 and " + fitnessClasses.length);
        }
    }

    String selectedLessonType = fitnessClasses[choice-1].getLesson();
    System.out.println("You have selected the " + selectedLessonType + " lesson type.");
    return fitnessClasses[choice-1];
}

public static String generateBookingId() {
    String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    StringBuilder bookingId = new StringBuilder();
    Random rnd = new Random();
    while (bookingId.length() < 10) { // Generate 10-character ID
        int index = (int) (rnd.nextFloat() * chars.length());
        bookingId.append(chars.charAt(index));
    }
    return bookingId.toString();
}
public static boolean checkCustomerBooking(String classId,Customer customer,LocalDate date)
{
	for (Booking b:bookings)
	{
	if(classId.equals(b.getClassId())&&b.getCustomer().getEmail().equals(customer.getEmail())&&date.equals(b.getDate()))
		return true;
	}
	return false;
}

public void checkReports()
{
	FitnessClass f=selectLesson();
	displayReports(f);
}


public void bookSeat() {
	FitnessClass f=selectLesson();
	LocalDate l=displayAvailableSeats(f);
	if(l==null)
	{
	System.out.print("No seates Available\n");
	f=selectLesson();
	}
	Customer c=new Customer();
	c.getInputFromUser();
	if(checkCustomerBooking(f.getClassId(), c, l))
	{
		System.out.print("User has Already booked in this Fitness Lesson! Plz change Email\n");
		c.getInputFromUser();
	}
	String id=this.generateBookingId();
	Booking b = new Booking(f.getClassId(),id,c,l, "booked","");
	
	bookings.add(b);
	

	System.out.print("\nSeat booked Successfully\n\nYour booking ID is + "+id+"\n\n");

}

public int getIndex(String bookingId)
{
	int i=0;
	for(Booking b:bookings)
	{
		
		if(b.getBookingId().equals(bookingId))
		{
			return i;
		}
		i++;
	}
	return i;
}

public void changeBooking() {
	 Scanner scanner = new Scanner(System.in);

     System.out.print("Enter Booking ID: ");
     String bookingId = scanner.nextLine();
     int index=getIndex(bookingId);
     while(index==-1)
     {
         System.out.print("ID not found!\nEnter Booking ID : ");

    	  bookingId = scanner.nextLine();
          index=getIndex(bookingId);
     }
	
	FitnessClass f=selectLesson();
	LocalDate l=displayAvailableSeats(f);
	if(l==null)
	{
	System.out.print("No seates Available\n");
	f=selectLesson();
	}
	
	Booking updatebooking=bookings.get(index);
	updatebooking.setClassId(f.getClassId());
	updatebooking.setDate(l);
	bookings.set(index, updatebooking);
	System.out.print("Booking is Updated Successfully\n");
}

public void attendLesson() {
  	 Scanner scanner = new Scanner(System.in);

       System.out.print("Enter Booking ID: ");
       String bookingId = scanner.nextLine();
       int index=getIndex(bookingId);
       while(index==-1)
       {
           System.out.print("ID not found!\n\nEnter Booking ID: ");
      	  bookingId = scanner.nextLine();
            index=getIndex(bookingId);
       }
        
  	Booking updatebooking=bookings.get(index);
  	updatebooking.setFeedback(this.selectFeedback());
  	bookings.set(index, updatebooking);
  	System.out.print("Booking is Updated Successfully\n");
  }
   
   public void cancelBooking() {
  	 Scanner scanner = new Scanner(System.in);

       System.out.print("Enter Booking ID To Cancel : ");
       String bookingId = scanner.nextLine();
       int index=getIndex(bookingId);
       while(index==-1)
       {
       	System.out.print("Booking ID  not Found!\nEnter Booking ID To Cancel : ");
      	  bookingId = scanner.nextLine();
            index=getIndex(bookingId);
       }
 
    bookings.remove(index);
  	System.out.print("Booking is Updated Successfully\n");
  }

       
   


   public String getFitnessType() {
       return fitnessType;
   }
   public List<Booking> getBookings() {
       return bookings;
   }

   public void setBookings(List<Booking> bookings) {
       this.bookings = bookings;
   }

   public FitnessClass[] getFitnessClasses() {
       return fitnessClasses;
   }

   public void setFitnessClasses(FitnessClass[] fitnessClasses) {
       this.fitnessClasses = fitnessClasses;
   }
   
   public void showMenu()
   {
       int choice;
       
       do {

           System.out.println("\n\nFitness Club Management\n\n");
           System.out.println("1. Cancel Booking");
           System.out.println("2. Change Booking");
           System.out.println("3. Book a New Seat");
           System.out.println("4. Check Time Table");
           System.out.println("5. Check Lesson Reports");
           System.out.println("6. Attend Lesson");
           System.out.println("0. Exit\n\n\n\nPlease choose an option : ");
       	Scanner scanner = new Scanner(System.in);

           String s=scanner.next();
           choice = Integer.parseInt(s);
           
           switch (choice) {
               case 1:
                  this.cancelBooking();
                   break;
               case 2:
                   this.changeBooking();
                   break;
               case 3:
                  this.bookSeat();
                   break;
               case 4:
                   this.displayTimetable();
                   break;
               case 5:
                   this.checkReports();
                   break;
               case 6:
                   this.attendLesson();
                   break;
               case 0:
                   // exit the program
                   System.out.println("Exiting...");
                   break;
               default:
                   System.out.println("Invalid choice, please try again.\n\n");
           }
       } while (choice != 0);
   }
   


    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 String fitnessType="body composition";
 	    FitnessClass[] fitnessClasses = new FitnessClass[4];
 	    fitnessClasses[0] = new FitnessClass("F001", "Yoga", "first",DayOfWeek.SUNDAY, 20.0f);
 	    fitnessClasses[1] = new FitnessClass("F002", "Pilates", "first", DayOfWeek.SATURDAY, 25.0f);
 	    fitnessClasses[2] = new FitnessClass("F003", "Zumba", "second", DayOfWeek.SUNDAY, 15.0f);
 	    fitnessClasses[3] = new FitnessClass("F004", "Spinning", "second", DayOfWeek.SATURDAY, 30.0f);
 	    Fitness f=new Fitness(fitnessClasses,fitnessType);
 	    f.showMenu();
	}

}
