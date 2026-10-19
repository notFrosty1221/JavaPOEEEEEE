

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    private Message message1;
    private Message message2;

    @BeforeEach
    public void setUp() {
        message1 = new Message(
                0,
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        message2 = new Message(
                1,
                "+27857597588",
                "Hi Keagan, did you receive the payment?"
        );
    }

    // Test 1: Message must not exceed 250 characters.
    @Test
    public void testMessageLengthSuccess() {
        assertEquals(
                "Message ready to send.",
                message1.checkMessageLength()
        );
    }

    @Test
    public void testMessageLengthFailure() {
        String longMessage = "A".repeat(260);
        Message longMessageObject =
                new Message(0, "+27718693002", longMessage);

        assertEquals(
                "Message exceeds 250 characters by 10; please reduce the size.",
                longMessageObject.checkMessageLength()
        );
    }

    // Test 2: Recipient number must be correctly formatted.
    @Test
    public void testRecipientNumberSuccess() {
        assertEquals(
                "Recipient cell number successfully captured.",
                message1.checkRecipientCell()
        );
    }

    @Test
    public void testRecipientNumberFailure() {
        Message invalidRecipient =
                new Message(0, "08966553", "Test message");

        String result = invalidRecipient.checkRecipientCell();

        assertEquals(
                "Recipient cell number is incorrectly formatted. "
                        + "Use +27 followed by nine digits.",
                result
        );
    }


    // Test : Message hash is generated.
    @Test
    public void testMessageHashGenerated() {
        String[] words = message1.getMessage().trim().split("\\s+");

        String expectedHash =
                (message1.getMessageID().substring(0, 2)
                        + ":"
                        + message1.getMessageNumber()
                        + ":"
                        + words[0]
                        + words[words.length - 1]).toUpperCase();

        assertEquals(expectedHash, message1.getMessageHash());
    }

    // Test : Message ID is generated.
    @Test
    public void testMessageIDGenerated() {
        String messageID = message1.getMessageID();

        assertEquals(10, messageID.length());
        assertTrue(messageID.matches("[0-9]{10}"));
    }

    // Test : Sending a message.
    @Test
    public void testSendMessage() {
        assertEquals(
                "Message successfully sent.",
                message1.sentMessage("1")
        );
    }



    @Test
    public void testStoreMessage() {
        assertEquals(
                "Message successfully stored.",
                message2.sentMessage("3")
        );
    }

    @Test
    public void testDisregardMessage() {
        String result = message2.sentMessage("2");

        assertEquals("Message disregarded.", result);
    }

    // Test : Only sent messages count as sent.
    @Test
    public void testTotalMessagesSent() {
        int initialTotal = Message.returnTotalMessages();

        Message messageToSend = new Message(
                0,
                "+27718693002",
                "Test message"
        );

        Message messageToDisregard = new Message(
                1,
                "+27857597588",
                "Another test message"
        );

        messageToSend.sentMessage("1");
        messageToDisregard.sentMessage("2");

        assertEquals(
                initialTotal + 1,
                Message.returnTotalMessages()
        );
    }
}

