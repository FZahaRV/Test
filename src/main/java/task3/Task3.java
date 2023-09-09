package task3;


import task1.Task1;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Task3 {
    Task1 task1 = new Task1();
    private final HttpClient client = HttpClient.newHttpClient();
    private static final String DEFAULT_URL =
            "https://jsonplaceholder.typicode.com/users";

    public void allOpenTasksOfUserById(int userId) {
        if (task1.maxId("users") < userId) {
            System.out.println("user with id: " + userId + " does not exist");
            return;
        }
        HttpRequest request = HttpRequest.newBuilder()
                .uri(java.net.URI.create(DEFAULT_URL + "/" + userId + "/todos?completed=false"))
                .header("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("HTTP Status Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
