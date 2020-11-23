package wgjd.sitecheck.concurrent;

import java.util.concurrent.CompletableFuture;
import java.net.http.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import wgjd.sitecheck.HTTPChecker;

public class ParallelHTTPChecker implements HTTPChecker {
    private final HttpClient client;

    public ParallelHTTPChecker(HttpClient cl) {
        client = cl;
    }

    @Override
    public void check(String location) throws IOException, URISyntaxException, InterruptedException {
        var uri = new URI(location);
        var req = HttpRequest.newBuilder(uri).build();

        var handler = HttpResponse.BodyHandlers.ofString();
        CompletableFuture.allOf(
            client.sendAsync(req, handler)
                .thenAccept((resp) -> System.out.println("First")),
            client.sendAsync(req, handler)
                .thenAccept((resp) -> System.out.println("Second")),
            client.sendAsync(req, handler)
                .thenAccept((resp) -> System.out.println("Third"))
        ).join();
    }
    


}
