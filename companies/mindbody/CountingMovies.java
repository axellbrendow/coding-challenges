package mindbody;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Pattern;

public class CountingMovies {
  private static CompletableFuture<Integer> countMovies(String title) {
    final var url = String.format("https://jsonmock.hackerrank.com/api/moviesdata/search/?Title=%s", title);

    final var client = HttpClient.newHttpClient();

    final var request = HttpRequest.newBuilder().uri(URI.create(url)).build();

    return client.sendAsync(request, BodyHandlers.ofString())
      .thenApply(res -> {
        final var json = res.body();
        System.out.println(json);
        final int total = Integer.parseInt(Pattern.compile("\"total\": (\\d+),").matcher(json).group(1));
        return total;
      });
  }

  public static void main(String[] args) {
    final var titles = new String[]{"star", "ab"};
    final var totals = new int[]{1, 15};

    final List<CompletableFuture<Void>> futures = new ArrayList<>();

    for (int i = 0; i < titles.length; i++) {
      final var title = titles[i];
      final var total = totals[i];

      final var future = countMovies(title).thenAccept(moviesCount -> {
        System.out.printf("%s %d\n", title, total);
        assert moviesCount == total;
      });

      futures.add(future);
    }

    for (CompletableFuture<Void> completableFuture : futures) completableFuture.join();
  }
}
