import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;

public class WireMockTest {

    @Rule
    public WireMockRule wiremock = new WireMockRule();

    @Before
    public void before(){
        stubFor(get(urlMatching("/blah"))
            .willReturn(aResponse()
            .withStatus(200)
            .withBody("body")));
    }

    @Test
    public void useWireMock() throws IOException {
        URL uri = new URL("http://localhost:8080/blah");
        InputStream content = uri.openConnection().getInputStream();

        //String retorno = new numa classe de negocio ();

        final String retrievedBody = IOUtils.toString(content);
        assertEquals("body", retrievedBody);
    }

}