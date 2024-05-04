package org.example;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

class NewsFeedEntityAlternative {
    String id;
    LocalDateTime postTime;

    public NewsFeedEntityAlternative(String id, String postTimeISO) {
        this.id = id;
        this.postTime = LocalDateTime.parse(postTimeISO, DateTimeFormatter.ISO_DATE_TIME);
    }
}

class NewFeedAlternative {
    TreeMap<LocalDateTime, List<NewsFeedEntityAlternative>> newsfeedsByTime;

    public NewFeedAlternative() {
        newsfeedsByTime = new TreeMap<>(Collections.reverseOrder());
    }

    public void storeNewsfeedEntity(String id, String postTimeISO) {
        LocalDateTime postTime = LocalDateTime.parse(postTimeISO, DateTimeFormatter.ISO_DATE_TIME);
        newsfeedsByTime.computeIfAbsent(postTime, k ->
                new ArrayList<>()).add(new NewsFeedEntityAlternative(id, postTimeISO));
    }

    public List<NewsFeedEntityAlternative> getLatestNewsfeedEntities(int K) {
        return getLatestNewsfeedEntities(K, null);
    }

    public List<NewsFeedEntityAlternative> getLatestNewsfeedEntities(int K, String beforeTime) {
        LocalDateTime before = null;
        if (beforeTime != null)
            before = LocalDateTime.parse(beforeTime, DateTimeFormatter.ISO_DATE_TIME);

        List<NewsFeedEntityAlternative> result = new ArrayList<>();
        int count = 0;

        for (Map.Entry<LocalDateTime, List<NewsFeedEntityAlternative>> entry : newsfeedsByTime.entrySet()) {
            if (before != null && entry.getKey().isAfter(before))
                continue;

            for (NewsFeedEntityAlternative entity : entry.getValue()) {
                if (count >= K)
                    break;
                result.add(entity);
                count++;
            }

            if (count >= K)
                break;
        }

        return result;
    }
}

public class NewsFeedSolutionAlternative {
    public static void main(String[] args) throws Exception {
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
        assert totalNumberOfRequests >= 1;

        NewFeedAlternative newsfeed = new NewFeedAlternative();
        String[] arr;
        int count=0;

        while (totalNumberOfRequests-- > 0) {
            arr = scanner.nextLine().split(" ");
            switch (arr[0]) {
                case "storeNewsfeedEntity":
                    newsfeed.storeNewsfeedEntity(arr[1], arr[2]);
                    break;
                case "getLatestNewsfeedEntities":
                    List<NewsFeedEntityAlternative> res = (arr.length == 3) ?
                            newsfeed.getLatestNewsfeedEntities(Integer.parseInt(arr[1]), arr[2]) :
                            newsfeed.getLatestNewsfeedEntities(Integer.parseInt(arr[1]));

                    int k = Integer.parseInt(arr[1]);
                    for (int i = 0; i < k; i++) {
                        if (i < res.size()) {
                            out.print(res.get(i).id + " ");
                        } else {
                            out.print("null ");
                        }
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
