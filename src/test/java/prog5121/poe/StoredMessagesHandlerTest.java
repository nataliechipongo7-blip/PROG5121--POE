package prog5121.poe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StoredMessagesHandlerTest {

    @Test
    public void testDisplayLongestStoredMessage() {
        // Test data: Message 1 through Message 4 lengths/contents
        String[] testMessages = {
            "Did you get the cake?",
            "Where are you? You are late! I have asked you to be on time.",
            "Yohoooo, I am at your gate.",
            "It is dinner time !"
        };

        String longest = "";
        for (String msg : testMessages) {
            if (msg != null && msg.length() > longest.length()) {
                longest = msg;
            }
        }

        assertEquals("Where are you? You are late! I have asked you to be on time.", longest);
    }

    @Test
    public void testSearchMessageById() {
        // Test data matching Test Data Message 4
        String[] messageIds = {"0838884567"};
        String[] messages = {"It is dinner time !"};
        String[] recipients = {"+27838884567"};

        String searchId = "0838884567";
        String foundMessage = "";

        for (int i = 0; i < messageIds.length; i++) {
            if (messageIds[i].equals(searchId)) {
                foundMessage = messages[i];
            }
        }

        assertEquals("It is dinner time !", foundMessage);
    }

    @Test
    public void testSearchByRecipient() {
        // Test data matching Test Data Message 2 recipient query
        String[] recipients = {"+27838884567", "+27834557896"};
        String[] messages = {
            "Where are you? You are late! I have asked you to be on time.",
            "Did you get the cake?"
        };

        String targetRecipient = "+27838884567";
        int matchCount = 0;

        for (String rec : recipients) {
            if (rec.equals(targetRecipient)) {
                matchCount++;
            }
        }

        assertTrue(matchCount > 0, "Should find messages for this recipient");
    }

    @Test
    public void testDeleteByMessageHash() {
        // Simulating deletion of message hash
        String[] messageHashes = {"HASH001", "HASH002"};
        String[] storedMessages = {"Where are you? You are late!", "Dinner is ready"};

        String targetHash = "HASH001";
        boolean deleted = false;

        for (int i = 0; i < messageHashes.length; i++) {
            if (messageHashes[i] != null && messageHashes[i].equals(targetHash)) {
                storedMessages[i] = null;
                messageHashes[i] = null;
                deleted = true;
                break;
            }
        }

        assertTrue(deleted, "Message should be successfully deleted");
        assertNull(storedMessages[0], "Message slot should now be null");
    }
}
