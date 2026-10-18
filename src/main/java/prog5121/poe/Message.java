
package prog5121.poe;

import java.util.Random;

public class Message {

    // Ensures the message ID is not more than 10 characters
    public boolean checkMessageID(String messageID) {
        if (messageID == null) {
            return false;
        }
        return messageID.length() <= 10;
    }

    // Checks that message length is within 250 characters
    public String checkMessageLength(String messageText) {
        if (messageText == null) {
            return "Message exceeds 250 characters by 0; please reduce the size.";
        }
        if (messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = messageText.length() - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        }
    }

   // Validates the recipient cell number format and checks for the international code
    public String checkRecipientCell(String cellNumber) {
       if (cellNumber == null) {
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }
    
    // Checks that the number starts with '+' and meets the standard length requirements
       if (cellNumber.startsWith("+") && cellNumber.length() >= 10 && cellNumber.length() <= 13) {
        return "Cell phone number successfully captured.";
    }
    
    return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
}

    // Generates message hash using ID prefix, count, and text bounds
    public String createMessageHash(String messageID, int messageNumber, String messageText) {
        String prefix = "00";
        if (messageID != null && messageID.length() >= 2) {
            prefix = messageID.substring(0, 2);
        }
        String firstWord = "";
        String lastWord = "";

        if (messageText != null && !messageText.trim().isEmpty()) {
            String[] words = messageText.trim().split("\\s+");
            firstWord = words[0].replaceAll("[^a-zA-Z]", "");
            lastWord = words[words.length - 1].replaceAll("[^a-zA-Z]", "");
        }
        return (prefix + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }

    // Handles the send menu options (send, discard, store)
    public String SendMessage(int choice) {
        if (choice == 1) {
            return "Message successfully sent.";
        } else if (choice == 2) {
            return "Press 0 to delete the message.";
        } else if (choice == 3) {
            return "Message successfully stored.";
        }
        return "Invalid choice selected.";
    }

    // Returns sent messages output
    public String printMessages() {
        return "Coming Soon.";
    }

    // Returns total count of messages sent
    public int returnTotalMessagess() {
        return 0;
    }

    // Stores message output
    public void storeMessage(String messageID, String messageText) {
        // TODO: implement JSON storage logic
    }

    // Generates random 10 digit ID
    public String generatesMessageID() {
        Random rand = new Random();
        long id = 1000000000L + rand.nextLong(9000000000L);
        return String.valueOf(id);
    }
}