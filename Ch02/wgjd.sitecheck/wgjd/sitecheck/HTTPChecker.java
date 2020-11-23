package wgjd.sitecheck;

import java.io.IOException;
import java.net.URISyntaxException;

public interface HTTPChecker {
    public void check(String location) throws IOException, URISyntaxException, InterruptedException;
}
