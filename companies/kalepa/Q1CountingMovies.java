package kalepa;

import java.net.*;
import java.net.http.*;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

/*-
Write a call to an HTTP GET method to retrieve information from a movie database concerning how many movies
have a particular string in their title. Given a search term, query
https://jsonmock.hackerrank.com/api/moviesdata/search/?Title=[substr]
The query response will be a JSON object with the following five fields:
- page: The current page.
- per_page: The maximum number of results per page.
- total: The total number of movies having the substring substr in their title.
- total_pages: The total number of pages that must be queried to get all the results.
- data: An array of JSON objects containing movie information where the Title field denotes the title of the movie.

The function must return the integer value found in the total field in the returned JSON object.

Function Description
Complete the function getNumberOfMovies in the editor below.

getNumberOfMovies has the following parameter(s):
str substr: the string to search for in the movie database

Returns
int: the value of the total field in the returned JSON object

Constraints
0 < |substr| < 20

Note: Please review the header in the code stub to see available libraries for API requests in the selected
language. Required libraries can be imported in order to solve the question. Check our full list of supported
libraries at https://www.hackerrank.com/environment.

Sample Case 0

Sample Input 0
getNumberOfMovies("maze")

Sample Output 0
37

Explanation 0
The value of substr is maze, so the query is https://jsonmock.hackerrank.com/api/moviesdata/search/?Title=maze,
and the response is:
{
  "page": 1,
  "per_page": 10,
  "total": 37,
  "total_pages": 4,
  "data": [
    {
      "Title": "The Maze Runner",
      "Year": 2014,
      "imdbID": "tt1790864"
    },
    ...
  ]
}
Return the value of the total field, 37, as the answer.
*/

public class Q1CountingMovies {
  private static CompletableFuture<Integer> countMovies(String title) {
    final var client = HttpClient.newHttpClient();

    final var url = "https://jsonmock.hackerrank.com/api/moviesdata/search/?Title=" + title;
    final var request = HttpRequest.newBuilder().uri(URI.create(url)).build();

    return client.sendAsync(request, BodyHandlers.ofString())
      .thenApply(res -> {
        String json = res.body();
        Matcher matcher = Pattern.compile(".*\"total\":(\\d+),.*").matcher(json);
        if (!matcher.matches()) throw new RuntimeException(
          String.format("total field not found, title = %s, response = %s", title, json)
        );
        return Integer.parseInt(matcher.group(1));
      });
  }

  public static void main(String[] args) throws Exception {
    final var titles = new String[]{"star", "ab"};
    final var totals = new int[]{1, 15};

    final Collection<CompletableFuture<Void>> futures = new ArrayList<>();

    for (int i = 0; i < titles.length; i++) {
      final var title = titles[i];
      final var total = totals[i];

      final var future = countMovies(title)
        .thenAccept(moviesCount -> {
          System.out.printf("%s %d\n", title, total);
          assert moviesCount == total;
        })
        .exceptionally(e -> {
          System.out.println(
            String.format("title = %s, expectedTotal = %d, error = %s", title, total, e.getMessage())
          );
          return null;
        });

      futures.add(future);
    }

    futures.forEach(CompletableFuture::join);
  }
}
