package wgjd.sitecheck.internal;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import wgjd.sitecheck.HTTPChecker;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class PushHTTPChecker implements HTTPChecker {
    private final HttpClient client;

    public PushHTTPChecker(HttpClient cl) {
        client = cl;
    }

    @Override
    public void check(String location) throws IOException, URISyntaxException, InterruptedException {
        var uri = new URI(location);
        var req = HttpRequest.newBuilder(uri).build();

        var futures = new ArrayList<CompletableFuture>();
        HttpResponse.PushPromiseHandler<String> pushPromiseHandler =
            (initiatingRequest, pushPromiseRequest, acceptor) -> {
                futures.add(
                    acceptor.apply(HttpResponse.BodyHandlers.ofString())
                        .thenAccept(response -> {
                            System.out.println(" Pushed response: " + response.uri());
                        }));
            };

        futures.add(client.sendAsync(req,
                HttpResponse.BodyHandlers.ofString(),
                pushPromiseHandler)
                .thenAccept((response) -> {
                    System.out.println(location + ": " + response.version());
                }));

        // Hang out to see whether the server sends us any pushes...
        Thread.sleep(1000);

        // Wait until we're done
        CompletableFuture.allOf(futures.stream()
                .toArray(CompletableFuture<?>[]::new))
                .join();

    }
    
}
