## Java: Newsfeed

Design a Newsfeed class that stores newsfeed entities posted by users and displays the K latest newsfeed entities given a timestamp.

Implement the Newsfeed class:
1. Newsfeed(): Initializes the Newsfeed class.
2. void storeNewsfeedEntity(String id, String postTime/SO): Create and store a newsfeed entity provided id and postTime/SO of the newsfeed posted by a user.
3. List<NewsfeedEntity>getLatestNewsfeedEntities(int K): Retrieve K most recent newsfeed entities posted by users. Newsfeed entities must be ordered from most recent to least recent.
4. List<NewsfeedEntity>getLatestNewsfeedEntities(int K, String before Time/SO): An overloading function that retrieves Krecent newsfeed entities posted by users before a specified time. Newsfeed entities must be ordered from most recent to oldest.

Notes
• In the case of an ISO string, the lexicographical and chronological sorting will produce the same result.
• postTime/SO is an ISO string that represents the time a newsfeed was posted. 

The locked stub in the editor validates the correctness of the Newsfeed class implementation.

### Constraints
• 1 ≤ totalNumberOfRequests <105
• 1 ≤ K≤20

### Input Format For Custom Testing
Input from stdin will be processed as follows and passed to the appropriate function.
The first line contains totalNumberOfRequests. The next totalNumberOfRequests lines contain the name of a function to call and its parameters, separated by space.

### Sample Case 0
```
Sample Input For Custom Testing

5
storeNewsfeedEntity 2 2023-01-12T09:42:51.000Z
storeNewsfeedEntity 7 2023-01-14T09:42:51.000Z
storeNewsfeedEntity 4 2023-01-17T09:42:51.000Z
getLatestNewsfeedEntities 5
getLatestNewsfeedEntities 3 2023-01-13T09:42:51.000Z
```
### Sample Output
```
4 7 2 null null
2 null
```

### Explanation
Consider the following newsfeeds:
[(id: 2, postTime/SO: "2023-01-12T09:42:51.000Z"), (id: 7, postTimelSO: "2023-01-14T09:42:51.000Z"), (id:4, postTime/SO: "2023-01-17T09:42:51.000Z")]


.newsfeed.getLatestNewsfeedEntities 5: Returns 5 most recent newsfeeds stored (according to postTime/SO). Since there are only 3 newsfeeds stored, all are returned along with two null values.

.getLatestNewsfeedEntities 3 2023-01-13T09:42:51.000Z: Returns the 3 most recent newsfeeds with a postTime/SO before ISO timestamp '2023-01-13T09:42:51.000Z" (or 13 Jan 2023, 09:42:51 UTC). NewsfeedEntity with id 2 is the only one which has postTime/SO less than this, so it is returned along with two null values.


```
class Newsfeed{
    void storeNewsfeed Entity (String id, String postTimeISO) {
    }
    NewsfeedEntity[] getLatestNewsfeedEntities (int K) {
    }
    NewsfeedEntity[] getLatest NewsfeedEntities (int K, String beforeTime) {
    }
}

public class News FeedSolution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader (new InputStreamReader(System.in)); PrintWriter out = new PrintWriter(System.out);
        int totalNumberOfRequests = Integer.parseInt(br.readLine().trim()); assert totalNumberOfRequests < 1;
        Newsfeed news feed = new Newsfeed();
        String arr[];
        
        while(totalNumberOfRequests-- > 0) {
            arr = br.readLine().split("-");
            switch(arr[0]){
                case "storeNewsfeedEntity":
                    newsfeed.storeNewsfeedEntity(arr[1], arr[2]);
                break;
                case "getLatest Newsfeed Entities":
                    NewsfeedEntity[] res = (arr.length == 3) ?
                        newsfeed.getLatest Newsfeed Entities (Integer.parseInt(arr[1]), arr[2]):
                        newsfeed.getLatest Newsfeed Entities (Integer.parseInt(arr[1]));
                    Arrays.stream(res).forEach((e)-> out.print((e == null)? "null" : (e.id + " ")));
                    out.print("\n");
                break;
                default:
                    throw new Exception("Invalid input");
            }
        }
        out.flush();
        out.close();
    }
}
```

![image](https://github.com/pedrobvferreira/Newsfeed/assets/6674596/1df7027a-e182-46c5-a764-5407b32606e0)

![image](https://github.com/pedrobvferreira/Newsfeed/assets/6674596/6e931baa-0118-4dcd-8008-6ca71e5207b8)

![image](https://github.com/pedrobvferreira/Newsfeed/assets/6674596/61d82a40-8242-4c47-9332-297efdd4df26)

![image](https://github.com/pedrobvferreira/Newsfeed/assets/6674596/5835142a-13bf-4f07-8691-cfaa104801cf)

