package task1;

import user.*;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {
    private final HttpClient client = HttpClient.newHttpClient();
    private static final String DEFAULT_URL =
            "https://jsonplaceholder.typicode.com";

    public void addNewUser(int id, String name, String username, String email,
                           String street, String suite, String city, String zipcode,
                           String lat, String lng, String phone, String website,
                           String companyName, String catchPhrase, String bs) {


        User newUser = new User(
                id,
                name,
                username,
                email,
                new Address(
                        street,
                        suite,
                        city,
                        zipcode,
                        new Geo(lat, lng)
                ),
                phone,
                website,
                new Company(
                        companyName,
                        catchPhrase,
                        bs
                )
        );
        HttpRequest request = HttpRequest.newBuilder()
                .uri(java.net.URI.create(DEFAULT_URL + "/users"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(newUser.toJsonString()))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("HTTP Status Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateElementOfUserData(int userId, String element, String dataOfNewElement) {
        if (maxId("users") < userId) {
            System.out.println("user with id: " + userId + " does not exist");
            return;
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(java.net.URI.create(DEFAULT_URL + "/users/" + userId))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString("{\"" + element + "\":\"" + dataOfNewElement + "\"}"))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("HTTP Status Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int userId) {
        String deleteUserUrl = DEFAULT_URL + "/users/" + userId;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(java.net.URI.create(deleteUserUrl))
                .DELETE()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                System.out.println("User with ID " + userId + " has been deleted successfully.");
            } else {
                System.out.println("Failed to delete user with ID " + userId);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void printAllUsers() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(java.net.URI.create(DEFAULT_URL + "/users"))
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
    public void printUserDataById(int id) {
        if (maxId("users") < id) {
            System.out.println("user with id: " + id + " does not exist");
            return;
        }
        HttpRequest request = HttpRequest.newBuilder()
                .uri(java.net.URI.create(DEFAULT_URL + "/users/" + id))
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
    public void printUserDataByUsername(String username) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(java.net.URI.create(DEFAULT_URL + "/users?username="+ username))
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
    public int maxId(String path) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(java.net.URI.create(DEFAULT_URL + "/" + path))
                .header("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String input = response.body();
            String regex = "\"id\":\\s*(\\d+)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);

            int maxId = Integer.MIN_VALUE;

            while (matcher.find()) {
                int currentId = Integer.parseInt(matcher.group(1));
                if (currentId > maxId) {
                    maxId = currentId;
                }
            }
            return maxId;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
