package wgjd.sitecheck.internal;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class TrustEveryone implements X509TrustManager {
    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }

    public static SSLContext context() throws NoSuchAlgorithmException, KeyManagementException {
        // I couldn't find any actual site that was doing H2 server push to test :/
        // There's a nice little local server for it at https://github.com/GoogleChromeLabs/simplehttp2server
        // BUT because it runs on localhost we have to convince HttpClient that no,
        // we really want to just trust it....
        var trustEveryone = new TrustManager[] { new TrustEveryone() };
        var sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustEveryone, new SecureRandom());

        return sslContext;
    }
}

