package mindbody;

import java.net.*;
import java.net.http.*;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class CountingMovies {
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

  public static void main(String[] args) {
    final var titles = new String[]{"star", "ab"};
    final var totals = new int[]{1, 15};

    final Collection<CompletableFuture<Void>> futures = new ArrayList<>();

    for (int i = 0; i < titles.length; i++) {
      final var title = titles[i];
      final var total = totals[i];

      final var future = countMovies(title).thenAccept(moviesCount -> {
        System.out.printf("%s %d\n", title, total);
        assert moviesCount == total;
      });

      futures.add(future);
    }

    futures.forEach(CompletableFuture::join);
  }
}
