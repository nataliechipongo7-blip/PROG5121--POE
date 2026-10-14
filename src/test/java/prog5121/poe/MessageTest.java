
package prog5121.poe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    Message msg = new Message();

    //==================================================
    // MESSAGE LENGTH TESTS
    //==================================================

    @Test
    public void testMessageLengthSuccess() {
        // Test case for message not exceeding 250 characters
        String result = msg.checkMessageLength("Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", result);
    }

    @Test
    public void testMessageLengthFailure() {
        // Test case for message exceeding 250 characters limit
        String longText = "A".repeat(251);
        String result = msg.checkMessageLength(longText);
        assertEquals("Message exceeds 250 characters by 1; please reduce the size.", result);
    }

    //==================================================
    // RECIPIENT CELL PHONE TESTS
    //==================================================

    @Test
    public void testRecipientCellSuccess() {
        // Validating correct international format
        String result = msg.checkRecipientCell("+27718693002");
        assertEquals("Cell phone number successfully captured.", result);
    }

    @Test
    public void testRecipientCellFailure() {
        // Validating incorrect format without international code
        String result = msg.checkRecipientCell("08575975889");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", result);
    }

    //==================================================
    // MESSAGE HASH TESTS
    //==================================================

    @Test
    public void testMessageHashCreation() {
        // Test case 1 hash output matching rubric requirements: 00:0:HITONIGHT
        String hash = msg.createMessageHash("0000000000", 0, "Hi Mike, can you join us for dinner tonight?");
        assertEquals("00:0:HITONIGHT", hash);
    }

    //==================================================
    // MESSAGE ID VALIDATION TESTS
    //==================================================

    @Test
    public void testMessageIDValidationSuccess() {
        assertTrue(msg.checkMessageID("1234567890"));
    }

    @Test
    public void testMessageIDValidationFailure() {
        assertFalse(msg.checkMessageID("12345678901"));
    }

    //==================================================
    // SEND MESSAGE OPTIONS TESTS
    //==================================================

    @Test
    public void testSendMessageOptions() {
        // Option 1: Send Message
        assertEquals("Message successfully sent.", msg.SendMessage(1));
        
        // Option 2: Discard Message
        assertEquals("Press 0 to delete the message.", msg.SendMessage(2));
        
        // Option 3: Store Message
        assertEquals("Message successfully stored.", msg.SendMessage(3));
    }
}
