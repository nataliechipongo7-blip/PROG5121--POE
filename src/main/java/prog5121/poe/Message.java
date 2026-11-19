
package prog5121.poe;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Message {
private static int totalMessages = 0;
private static final List<String> sentMessages = new ArrayList<>();
private static final List<String> jsonEntries = new ArrayList<>();
private static final String JSON_FILE = "storedMessages.json";

    // Ensures the message ID is non-null and that it consists of strictly 10 numeric digits
    public boolean checkMessageID(String messageID) {
      if (messageID == null) {
        return false;
    }
      return messageID.matches("\\d{10}");
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

         // Validates that the recipient cell number uses the +27 South African prefix followed by 9 digits
    public String checkRecipientCell(String cellNumber) {
        if (cellNumber == null || cellNumber.isBlank()) {
        return "Cell phone number is incorrectly formatted or does not contain an international code.";
    }

        if (!cellNumber.matches("^\\+27\\d{9}$")) {
        return "Cell phone number is incorrectly formatted or does not contain an international code.";
    }

        return "Cell phone number successfully captured.";
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

  // Called when a message is sent: updates the counter and recent messages
    public void recordSentMessage(String id, String hash, String recipient, String text) {
        totalMessages++;
        sentMessages.add("Message ID: " + id
                + "\nMessage Hash: " + hash
                + "\nRecipient: " + recipient
                + "\nMessage: " + text);
    }

    // Returns all sent messages
    public String printMessages() {
        if (sentMessages.isEmpty()) {
            return "No messages have been sent yet.";
        }
        return String.join("\n\n", sentMessages);
    }

       // Tracks messages saved via "Store for later" separately from sent messages
        private static final List<String> storedMessages = new ArrayList<>();

// Records and displays a message saved for later
       public String recordStoredMessage(String id, String hash, String recipient, String text) {
        storeMessage(id, hash, recipient, text);
        String formatted = "Message ID: " + id
            + "\nMessage Hash: " + hash
            + "\nRecipient: " + recipient
            + "\nMessage: " + text;
        storedMessages.add(formatted);
       return formatted;
}

public String printStoredMessages() {
    if (storedMessages.isEmpty()) {
        return "No stored messages available.";
    }
    return String.join("\n\n", storedMessages);
}
    // Returns total count of messages sent
    public int returnTotalMessages() {
        return totalMessages;
    }

    public void storeMessage(String messageID, String messageText) {
        storeMessage(messageID, "", "", messageText);
    }

    // Stores a message in a JSON file
    public void storeMessage(String messageID, String messageHash,
                             String recipient, String messageText) {
        String entry = "  {\n"
                + "    \"messageID\": \"" + escapeJson(messageID) + "\",\n"
                + "    \"messageHash\": \"" + escapeJson(messageHash) + "\",\n"
                + "    \"recipient\": \"" + escapeJson(recipient) + "\",\n"
                + "    \"message\": \"" + escapeJson(messageText) + "\"\n"
                + "  }";
        jsonEntries.add(entry);

        try {
            String json = "[\n" + String.join(",\n", jsonEntries) + "\n]";
            Files.write(Paths.get(JSON_FILE), json.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            System.out.println("Could not save message: " + e.getMessage());
        }
    }

    private String escapeJson(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"")
                .replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t");
    }

   
    // Generates random 10 digit ID
    public String generatesMessageID() {
        Random rand = new Random();
        long id = 1000000000L + rand.nextLong(9000000000L);
        return String.valueOf(id);
    }

   public int returnTotalMessagess() {
    return totalMessages;
}
}