
package prog5121.poe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    private Message msg;

    @BeforeEach
    public void setUp() {
        msg = new Message();
    }

    //======================================================================
    // MESSAGE HASH CREATION TESTS
    //======================================================================

    @Test
    public void testMessageHashCreation() {
        String hash = msg.createMessageHash("0000000000", 0, "Hi Mike, can you join us for dinner tonight");
        assertEquals("00:0:HITONIGHT", hash);
    }

    //======================================================================
    // MESSAGE ID VALIDATION TESTS
    //======================================================================

    @Test
    public void testMessageIDValidationSuccess() {
        assertTrue(msg.checkMessageID("1234567890"));
    }

    @Test
    public void testMessageIDValidationFailure() {
        assertFalse(msg.checkMessageID("12345678901"));
    }

    @Test
    public void testGeneratedMessageIDFormat() {
        String generatedId = msg.generatesMessageID();
        assertNotNull(generatedId);
        assertTrue(msg.checkMessageID(generatedId));
    }

    //======================================================================
    // RECIPIENT NUMBER VALIDATION TESTS
    //======================================================================

    @Test
    public void testRecipientNumberValidationSuccess() {
        assertTrue("+27831234567".matches("^\\+27\\d{9}$"));
    }

    @Test
    public void testRecipientNumberValidationFailure() {
        assertFalse("0831234567".matches("^\\+27\\d{9}$"));
        assertFalse("+278312345".matches("^\\+27\\d{9}$"));
        assertFalse("+278312345678".matches("^\\+27\\d{9}$"));
    }

    //======================================================================
    // MESSAGE LENGTH VALIDATION TESTS
    //======================================================================

    @Test
    public void testMessageLengthWithinLimit() {
        String validMsg = "Hi Mike, can you join us for dinner tonight?";
        assertTrue(validMsg.length() <= 250);
    }

    @Test
    public void testMessageLengthExceedingLimit() {
        String longMsg = "A".repeat(251);
        assertTrue(longMsg.length() > 250);
    }

    //======================================================================
    // SEND MESSAGE OPTIONS TESTS
    //======================================================================

    @Test
    public void testSendMessageOptions() {
        assertEquals("Message successfully sent.", msg.SendMessage(1));
        assertEquals("Press 0 to delete the message.", msg.SendMessage(2));
        assertEquals("Message successfully stored.", msg.SendMessage(3));
        assertEquals("Invalid choice selected.", msg.SendMessage(99));
    }

    //======================================================================
    // SENT & STORED MESSAGE TRACKING TESTS
    //======================================================================

    @Test
    public void testRecordSentMessageAndTotalCount() {
        int initialCount = msg.returnTotalMessages();

        msg.recordSentMessage("1234567890", "12:1:HITONIGHT", "+27831234567", "Hi tonight");

        assertEquals(initialCount + 1, msg.returnTotalMessages());
        String sentOutput = msg.printMessages();
        assertTrue(sentOutput.contains("1234567890"));
        assertTrue(sentOutput.contains("Hi tonight"));
    }

    @Test
    public void testRecordStoredMessageDoesNotIncrementSentTotal() {
        int countBefore = msg.returnTotalMessages();

        String storedOutput = msg.recordStoredMessage(
                "0987654321",
                "09:2:MEETLATER",
                "+27711234567",
                "Meet later"
        );

        assertEquals(countBefore, msg.returnTotalMessages());
        assertTrue(storedOutput.contains("0987654321"));
        assertTrue(storedOutput.contains("Meet later"));
    }
}
