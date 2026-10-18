
package prog5121.poe;

import java.util.Scanner;

public class Main {
   
    public static void main(String[] args){
       Scanner scanner = new Scanner(System.in);
       Login user = new Login ();
       Message msgHelper = new Message();
       
       System.out.println("==================================================");
       System.out.println("            QUICKCHAT REGISTRATION               ");
       System.out.println("=================================================");
               
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
    System.out.println(status);
 if (!isSuccess){
     scanner.close();
     return;
  }
 
 // Part 2: Welcome message and numeric menu loop 
        System.out.println("\nWelcome to QuickChat.");

        boolean keepRunning = true;
         
        while (keepRunning) {
            System.out.println("\n==========================================");
            System.out.println("                 MENU                     ");
            System.out.println("==========================================");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Choose an option (1-3): ");

            String choiceStr = scanner.nextLine();

            if (choiceStr.equalsIgnoreCase("quit") || choiceStr.equals("3")) {
                System.out.println("Exiting QuickChat. Goodbye!");
                keepRunning = false;
                continue;
            }

            int choice = 0;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number between 1 and 3.");
                continue;
            }

            if (choice == 1) {
                System.out.print("How many messages would you like to enter? ");
                int numMessages = Integer.parseInt(scanner.nextLine());
                
            // Initialize parallel arrays
              String[] recipientArray = new String[numMessages];
              String[] messageArray = new String[numMessages];
              String[] idArray = new String[numMessages];

                int totalProcessed = 0;

               

                for (int i = 0; i < numMessages; i++) {
                    System.out.println("\n--- Entering Message " + (i + 1) + " of " + numMessages + " ---");

                    System.out.print("Enter recipient cell number: ");
                    String cell = scanner.nextLine();
                    if (!user.checkCellPhoneNumber(cell)) {
                        System.out.println("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
                        i--; // Re-run iteration
                        continue;
                    }
                    
                    System.out.print("Enter message text (max 250 characters): ");
                    String text = scanner.nextLine();

                // Keeps asking until the user provides a non-empty text
               while (text.trim().isEmpty()) {
                     System.out.println("You cannot send a blank message. Please enter text.");
                     System.out.print("Enter message text (max 250 characters): ");
                            text = scanner.nextLine();
                    }          
                       
                   String lengthCheck = msgHelper.checkMessageLength(text);
if                   (!lengthCheck.equals("Message ready to send.")) {
                      System.out.println(lengthCheck);
                      i--; // Re-run iteration
                      continue;
                    }

                    String msgID = msgHelper.generatesMessageID();
                    String hash = msgHelper.createMessageHash(msgID, i, text);
                    
                    // Store current message into arrays
                     recipientArray[i] = cell;
                     messageArray[i] = text;
                     idArray[i] = msgID;
                    

                    System.out.println("\nMessage ID generated: " + msgID);
                    System.out.println("Message Hash: " + hash);

                    System.out.println("\nChoose action:");
                    System.out.println("1) Send Message");
                    System.out.println("2) Disregard Message");
                    System.out.println("3) Store Message to send later");
                    System.out.print("Enter choice (1-3): ");

                    int action = Integer.parseInt(scanner.nextLine());
                    String result = msgHelper.SendMessage(action);
                    System.out.println(result);

                    totalProcessed++;
                }

                System.out.println("\nTotal messages processed: " + totalProcessed);

            } else if (choice == 2) {
                System.out.println("Coming Soon.");
            } else {
                System.out.println("Please choose a valid option between 1 and 3.");
            }
        }
        
    }
}
        
        
    
        
         
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
    
    
 
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    




