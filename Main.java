
package prog5121.poe;

import java.util.Scanner;

public class Main {
   
    public static void main(String[] args){
       Scanner scanner = new Scanner(System.in);
       Login user = new Login ();
       
       System.out.println("==================================================");
       System.out.println("            QUICKCHAT REGISTRATION               ");
       System.out.println("================================================");
               
    //Input the user names
    System.out.print("Enter your first name:");
    String firstName = scanner.nextLine();
    
    System.out.print("Enter your last name: ");
    String lastName = scanner.nextLine();
    
    //Input and validate username
    System.out.print("Enter your username:");
    String username = scanner.nextLine();
    
    if (!user.checkUserName (username)){
        System.out.println("\nUsername is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
        scanner.close();
        return;    
 }
    
  //Input and validate password
  System.out.print("Enter your password:");
  String password = scanner.nextLine();
  
if (!user.checkPasswordComplexity(password)){
    System.out.println("\nPassword is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
    scanner.close();
    return;
}
    //Input and validate cellphone number
    System.out.print("Enter your South African cellphone number:");
    String cellNumber = scanner.nextLine();
    
if (!user.checkCellPhoneNumber(cellNumber)){
    System.out.println("\nCellphone number is incorrectly formatted or does not contain an international code; please correct the number and try again.");
    scanner.close();
    return;
}

//Register user and output result
System.out.println("\n========================================================");
System.out.println("               REGISTRATION RESULTS                      ");
System.out.println("=========================================================");

String regResult = user.registerUser(username, password, cellNumber, firstName, lastName );
System.out.println(regResult);

//Login section
System.out.println("\n=======================================================");
System.out.println("                     LOGIN                              ");
System.out.println("========================================================");

System.out.print("Enter username:");
String loginUser = scanner.nextLine();

System.out.print("Enter password:");
String loginPass = scanner.nextLine();

//Process login and display response
boolean isSuccess = user.loginUser(loginUser, loginPass);
String status = user.returnLoginStatus(isSuccess);

System.out.println("\nWelcome " + firstName + " " + lastName + ", it is great to see you again.");
scanner.close();
    }
    
}



    