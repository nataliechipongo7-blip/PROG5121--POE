
package prog5121.poe;
import java.util.Scanner;



public class StoredMessagesHandler {
  //public arrays for managing stored messages
    private String[] storedMessages;
    private String[] messageHash;
    private String[] messageId;
    private String[] recepientArray;
    private String[] senderArray;

    
   public StoredMessagesHandler(String[] messages,String[] hashes, String[] id, String[] recepients,String[] senders){
      this.storedMessages = (messages != null)? messages: new String [0];
      this.messageHash = (hashes != null)? hashes: new String [0];
      this.messageId = (id!= null)? id: new String [0];
      this.recepientArray = (recepients != null)? recepients: new String [0];
      this.senderArray = senders;
 
   }
   
   // sub-menu for option 4
    public void showStoredMessagesMenu(Scanner scanner){
        boolean exit = false;
        
        while(!exit){
            System.out.println("\n======== Stored Messages Menu ==========");
            System.out.println("1. Display sender and recepient of all stored messages");
            System.out.println("2. Display the longest stored message");
            System.out.println("3. Search for a message ID");
            System.out.println("4. Search messages for a particular recipient");
            System.out.println("5. Delete a message using message hash");
            System.out.println("6. Display a report of all stored messages");
            System.out.println("7. Return to main menu");
            System.out.println(" Choose an option:");
            
          int choice;
           try {
               choice = Integer.parseInt(scanner.nextLine());
             }catch (NumberFormatException e){
                 System.out.println("Please enetr a number.");
                 continue;
           }
           switch (choice){
               case 1:
                   displaySenderAndRecipient();
                   break;
               case 2:
                   displayLongestMessage();
                   break;
               case 3:
                   searchMessageById(scanner);
                   break;
               case 4:
                   searchByRecepient(scanner);
                  break;
               case 5:
                   deleteByHash(scanner);
                   break;
               case 6:
                   displayReport();
                   break;
               case 7: 
                  exit = true;
                  break;
                  default:
                    System.out.println("Invalid choice, please select between option 1 and 7.");
           }
            
        }
        
       
    }
   
    // a. display the sender and recipient
    private void displaySenderAndRecipient(){
        System.out.println("\n==== Stored Messages: Sender and Recepient ====");
        
        boolean any = false;
        for (int i =0; i < storedMessages.length; i++) {
            if ( storedMessages[i] != null){
                  System.out.println("Sender:" + senderArray[i]);
                System.out.println( "Recipient:" +recepientArray[i]);
                any = true;
              
            }
        }
        if (!any){
            System.out.println("No stored messages.");
    } 
 }
   
    //b. find and display the longest message
    private  void displayLongestMessage(){
        if (storedMessages == null || storedMessages.length == 0){
            System.out.println("No messages available.");
            return;
        }
        String longest = "";
         for (String msg : storedMessages){
             if (msg != null && msg.length() > longest.length()) {
             longest = msg;
            }
        }    System.out.println("\nLongest Stored Message:\n" + longest);

    }
    // c. search by message ID 
    private void searchMessageById (Scanner scanner) {
        System.out.print( "Enter message ID to search: ");
        String targetId = scanner.nextLine();
        boolean found = false;
        
        for (int i = 0; i < messageId. length; i++){
            if (messageId[i] != null && messageId[i].equalsIgnoreCase(targetId)) {
              System.out.println("Recepient:" + recepientArray[i]);
              System.out.println("Message:" +storedMessages[i]);
              found = true;
              break;
        }
      }
        if (!found){
            System.out.println("Messages ID not found.");
        }
    }
    
    //d. search by recipient recipient cellphone number
    private void searchByRecepient (Scanner scanner){
        System.out.print("Enter recepient cellphone number:");
        String targetRecepient = scanner.nextLine();
        boolean found = false;
        
        for (int i = 0; i < recepientArray.length; i++){
            if (recepientArray[i] != null && recepientArray[i].equals(targetRecepient)) {
            System.out.println( "Message:" + storedMessages[i]);
            found = true;
        }
      }
        if (!found){
            System.out.println("No messages found for this recipient.");
        }
    }
    
    // e. deletion of a message using its hash
    private void deleteByHash( Scanner scanner) {
        System.out.print("Enter message hash to delete:");
        String targetHash = scanner.nextLine();
        boolean deleted = false;
        
        for (int i= 0; i <  messageHash.length; i++){
            if (messageHash[i] != null && messageHash[i].equalsIgnoreCase(targetHash)){
                System.out.println("Message: \"" + storedMessages[i] + "\" successfully deleted.");
                
                //clear array slots
                storedMessages[i] = null;
                messageHash[i]= null;
                recepientArray[i] = null;
                
                deleted = true;
                break;
            }
        }
        if (!deleted){
            System.out.println( "Message hash has not been found");
        }
    }
     
    // full report of the stored message
    private void displayReport(){
        System.out.println("\n====== Full Stored Messages Report ========");
        for (int i = 0; i < storedMessages.length; i++){
            if (storedMessages[i] != null){
                System.out.println("Hash: " + messageHash[i]);
                System.out.println("ID:" + messageId[i]);
                System.out.println ( "Recipient: " + recepientArray[i]);
                System.out.println("Message:"  + storedMessages[i]);
                System.out.println("==================================");
            }
        }
    }
}   
    
    
    
         
       
   
   
   
   
   
   



