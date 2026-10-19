
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Message {

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String message;
    private String messageHash;

    // Tracks messages sent during the current program run.
    private static final List<Message> sentMessages =
            new ArrayList<>();

    // Constructor for Gson JSON loading.
    public Message() {
    }

    // Constructor for creating a new message.
    public Message(int messageNumber, String recipient, String message) {

        this.messageID = String.format(
                "%010d",
                ThreadLocalRandom.current().nextLong(
                        0, 10_000_000_000L
                )
        );

        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;
        this.messageHash = createMessageHash();
    }

    // 1. Check that the message ID is no longer than 10 characters.
    public boolean checkMessageID() {
        return messageID != null
                && !messageID.isEmpty()
                && messageID.length() <= 10;
    }

    // 2. Validate a South African recipient number.
    // Format: +27 followed by nine digits (12 characters total).
    public String checkRecipientCell() {

        if (recipient != null
                && recipient.matches("^\\+27[0-9]{9}$")) {
            return "Recipient cell number successfully captured.";
        }

        return "Recipient cell number is incorrectly formatted. "
                + "Use +27 followed by nine digits.";
    }

    // 3. Create and return the message hash.
    public String createMessageHash() {

        if (messageID == null || message == null
                || message.trim().isEmpty()) {
            return "";
        }

        String[] words = message.trim().split("\\s+");

        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        String firstTwoIDDigits = messageID.substring(0, 2);

        return (firstTwoIDDigits + ":"
                + messageNumber + ":"
                + firstWord + lastWord).toUpperCase();
    }

    // 4. Allow the user to send, disregard, or store a message.
    // 1 = send, 2 = disregard, 3 = store.
    public String sentMessage(String action) {

        switch (action) {

            case "1":
                sentMessages.add(this);
                storeMessage();
                return "Message successfully sent.";

            case "2":
                return "Message disregarded.";

            case "3":
                storeMessage();
                return "Message successfully stored.";

            default:
                return "Invalid option. The message was not processed.";
        }
    }

    // 5. Return all messages sent during this program run.
    public static String printMessages() {

        if (sentMessages.isEmpty()) {
            return "No messages have been sent during this session.";
        }

        StringBuilder output = new StringBuilder();

        for (Message sent : sentMessages) {
            output.append("Message ID: ")
                    .append(sent.getMessageID())
                    .append("\nMessage Hash: ")
                    .append(sent.getMessageHash())
                    .append("\nRecipient: ")
                    .append(sent.getRecipient())
                    .append("\nMessage: ")
                    .append(sent.getMessage())
                    .append("\n\n");
        }

        return output.toString();
    }

    // 6. Return the total number of messages sent this session.
    public static int returnTotalMessages() {
        return sentMessages.size();
    }

    // 7. Save the message using the existing JSON storage class.
    public String storeMessage() {

        List<Message> savedMessages = MessageStorage.loadMessages();

        // Avoid storing the same message ID twice.
        for (Message saved : savedMessages) {
            if (saved.getMessageID() != null
                    && saved.getMessageID().equals(messageID)) {
                return "Message is already stored.";
            }
        }

        savedMessages.add(this);
        MessageStorage.saveMessages(savedMessages);

        return "Message saved to JSON successfully.";
    }

    // Getters
    public String getMessageID() {
        return messageID;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageHash() {
        return messageHash;
    }

    // Check that the message does not exceed 250 characters.
    public String checkMessageLength() {

        if (message == null) {
            return "Please enter a message.";
        }

        if (message.length() <= 250) {
            return "Message ready to send.";
        }

        int excessCharacters = message.length() - 250;

        return "Message exceeds 250 characters by "
                + excessCharacters
                + "; please reduce the size.";
    }
}



