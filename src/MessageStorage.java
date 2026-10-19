import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MessageStorage {

    private static final String FILE_NAME = "messages.json";

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public static void saveMessages(List<Message> messages) {

        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(messages, writer);
            System.out.println("Messages saved to JSON successfully.");

        } catch (IOException e) {
            System.out.println("Error saving messages: " + e.getMessage());
        }
    }

    public static List<Message> loadMessages() {

        try (FileReader reader = new FileReader(FILE_NAME)) {

            Type messageListType =
                    new TypeToken<ArrayList<Message>>() {}.getType();

            List<Message> messages =
                    gson.fromJson(reader, messageListType);

            if (messages == null) {
                return new ArrayList<>();
            }

            return messages;

        } catch (IOException e) {
            // The file may not exist the first time QuickChat runs.
            return new ArrayList<>();
        }
    }
}

