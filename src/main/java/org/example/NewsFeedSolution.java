package org.example;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class NewsFeedEntity {
    String id;
    String postTimeISO;

    public NewsFeedEntity(String id, String postTimeISO) {
        this.id = id;
        this.postTimeISO = postTimeISO;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostTimeISO() {
        return postTimeISO;
    }

    public void setPostTimeISO(String postTimeISO) {
        this.postTimeISO = postTimeISO;
    }
}

class NewsFeed {
    List<NewsFeedEntity> newsfeedEntities;

    public NewsFeed() {
        this.newsfeedEntities = new ArrayList<>();
    }

    void storeNewsfeedEntity(String id, String postTimeISO) {
        if (postTimeISO != null) {
            newsfeedEntities.add(new NewsFeedEntity(id, postTimeISO));
        }
    }

    List<NewsFeedEntity> getLatestNewsfeedEntities(int K) {
        return getLatestNewsfeedEntities(K, null);
    }

    List<NewsFeedEntity> getLatestNewsfeedEntities(int K, String beforeTime) {
        List<NewsFeedEntity> filteredList = new ArrayList<>();
        List<NewsFeedEntity> sortedList = new ArrayList<>(newsfeedEntities);

        // Sort the list based on postTimeISO in descending order
        sortedList.sort((e1, e2) -> e2.postTimeISO.compareTo(e1.postTimeISO));

        // Filter the list based on beforeTime (if provided)
        if (beforeTime != null) {
            for (NewsFeedEntity entity : sortedList) {
                if (entity.postTimeISO.compareTo(beforeTime) < 0) {
                    filteredList.add(entity);
                }
                if (filteredList.size() == K) {
                    break;
                }
            }
        } else {
            filteredList = sortedList.subList(0, Math.min(K, sortedList.size()));
        }

        // Pad with nulls if necessary
        while (filteredList.size() < K) {
            filteredList.add(null);
        }

        return filteredList;
    }
}

public class NewsFeedSolution {
    public static void main(String[] args) throws Exception {
        // Define custom inputs
        String[] inputs = {
                "5", // Total number of requests
                "storeNewsfeedEntity 2 2023-01-12T09:42:51.000Z", // Example storeNewsfeedEntity request
                "storeNewsfeedEntity 7 2023-01-14T09:42:51.000Z", // Example storeNewsfeedEntity request
                "storeNewsfeedEntity 4 2023-01-17T09:42:51.000Z",
                "getLatestNewsfeedEntities 5", // Example getLatestNewsfeedEntities request
                "getLatestNewsfeedEntities 3 2023-01-13T09:42:51.000Z"
        };

        Scanner scanner = new Scanner(String.join(System.lineSeparator(), inputs));

        PrintWriter out = new PrintWriter(System.out);
        int totalNumberOfRequests = Integer.parseInt(scanner.nextLine().trim());
        assert totalNumberOfRequests > 0; // Ensure there's at least one request

        NewsFeed newsfeed = new NewsFeed();

        while (totalNumberOfRequests-- > 0) {
            String[] arr = scanner.nextLine().split(" ");
            switch (arr[0]) {
                case "storeNewsfeedEntity":
                    newsfeed.storeNewsfeedEntity(arr[1], arr[2]);
                    break;
                case "getLatestNewsfeedEntities":
                    List<NewsFeedEntity> res = (arr.length == 3) ?
                            newsfeed.getLatestNewsfeedEntities(Integer.parseInt(arr[1]), arr[2]) :
                            newsfeed.getLatestNewsfeedEntities(Integer.parseInt(arr[1]));
                    for (NewsFeedEntity entity : res) {
                        out.print((entity != null) ? entity.id + " " : "null ");
                    }
                    out.println();
                    break;
                default:
                    throw new Exception("Invalid input");
            }
        }
        out.flush();
        out.close();
    }
}