package task2;

import task1.*;

import java.io.FileWriter;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Task2 {
    Task1 task1 = new Task1();
    private final HttpClient client = HttpClient.newHttpClient();
    private static final String DEFAULT_URL =
            "https://jsonplaceholder.typicode.com/posts";

    public void allCommentsOnLastUserPost(int userId) {
        if (task1.maxId("users") < userId) {
            System.out.println("user with id: " + userId + " does not exist");
            return;
        }
        HttpRequest request = HttpRequest.newBuilder()
                .uri(java.net.URI.create(DEFAULT_URL + "/" + task1.maxId("users/" + userId + "/posts") + "/comments"))
                .header("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("HTTP Status Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());
            createNewJsonFile(response.body(), userId, task1.maxId("users/" + userId + "/posts"));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void createNewJsonFile(String data, int userId, int postId) {
        try (FileWriter writer = new FileWriter("user-"+ userId + "-post-" + postId + "-comments.json")) {
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
