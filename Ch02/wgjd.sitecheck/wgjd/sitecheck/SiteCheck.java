package wgjd.sitecheck;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.nio.charset.Charset;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.*;
import java.util.ArrayList;
import java.security.NoSuchAlgorithmException;
import java.security.KeyManagementException;

import wgjd.sitecheck.concurrent.ParallelHTTPChecker;
import wgjd.sitecheck.internal.TrustEveryone;
import wgjd.sitecheck.internal.PushHTTPChecker;

public final class SiteCheck implements HTTPChecker {
    private final HttpClient client;

    public SiteCheck(HttpClient cl) {
        client = cl;
    }

    private static void usage() {
        System.err.println("Usage: HTTPCheck <URL>");
        System.exit(1);
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            usage();
        }

        var location = args[0];

        var mode = "sync";
        if (args.length > 1) {
            mode = args[1];
        }

        try {
            var sslContext = TrustEveryone.context();

            var client = HttpClient.newBuilder()
                .sslContext(sslContext)
                .build();
            HTTPChecker checker = null;

            if (mode.equals("sync")) {
                checker = new SiteCheck(client);
            } else if (mode.equals("async")) {
                checker = new ParallelHTTPChecker(client);
            } else if (mode.equals("push")) {
                checker = new PushHTTPChecker(client);
            } else {
                usage(); // Doesn't return
            }
            checker.check(location);
        } catch (URISyntaxException urix) {
            System.err.println("Location: "+ location +" is not valid");
            usage();
        } catch (NoSuchAlgorithmException | KeyManagementException nsax) {
            System.err.println("Cryptographic error: ");
            nsax.printStackTrace();
        } catch (InterruptedException intx) {
//        } catch (ExecutionException | InterruptedException intx) {
            System.err.println("Contacting "+ location +" interrupted.");
        } catch (IOException iox) {
            System.err.println("Contacting "+ location +" failed:");
            iox.printStackTrace();
        }
    }

    @Override
    public void check(String location) throws IOException, URISyntaxException, InterruptedException {
        var uri = new URI(location);
        var req = HttpRequest.newBuilder(uri).build();

        var response = client.send(req,
        HttpResponse.BodyHandlers.ofString(Charset.defaultCharset()));

        System.out.println(location + ": " + response.version());
    }

}
