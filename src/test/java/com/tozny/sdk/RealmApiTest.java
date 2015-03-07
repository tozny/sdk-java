package com.tozny.sdk;


import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.tozny.sdk.realm.RealmConfig;
import com.tozny.sdk.realm.Session;
import com.tozny.sdk.realm.User;
import com.tozny.sdk.realm.config.ToznyRealmKeyId;
import com.tozny.sdk.realm.config.ToznyRealmSecret;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class RealmApiTest extends TestCase
{

    static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    static final JsonFactory JSON_FACTORY = new JacksonFactory();

    private HttpRequestFactory requestFactory;
    private RealmConfig realmConfig;

    private  RealmApi realmApi;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public RealmApiTest(String testName)
    {
        super( testName );

        this.requestFactory =
                HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
                    @Override
                    public void initialize(HttpRequest request) {
                        request.setParser(new JsonObjectParser(JSON_FACTORY));
                    }
                });

        this.realmConfig = new RealmConfig(
                new ToznyRealmKeyId("sid_52fa6d0a3a290"),
                new ToznyRealmSecret("")

        );


        this.realmApi = new RealmApi(realmConfig, requestFactory, JSON_FACTORY);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( RealmApiTest.class );
    }


    /**
     *
     */
    public void testUserGet() throws IOException {

        User user = this.realmApi.userGet("sid_5302bde69d72f");

        assertEquals("kirk@tozny.com",user.meta.get("email"));

        user = this.realmApi.userGetByEmail("kirk@tozny.com");

        assertEquals("sid_5302bde69d72f",user.user_id);
    }

    public void testUserExists() throws IOException {

        boolean exists = this.realmApi.userExists("sid_5302bde69d72f");

        assertTrue( exists );

        exists = this.realmApi.userExistsByEmail("kirk@tozny.com");

        assertTrue( exists );
    }

    public void testQuestionChallenge() throws IOException {

        Session session = this.realmApi.questionChallenge("Do you like Java?");

        assertNotNull(session.challenge);
        assertNotNull(session.realm_key_id);
        assertNotNull(session.session_id);
        assertNotNull(session.qr_url);
        assertNotNull(session.mobile_url);
        assertNotNull(session.presence);
        assertNotNull(session.created_at);
    }

    public void testCheckValidLogin() throws IOException {

        try {
            boolean isValid = this.realmApi.checkValidLogin("bad", "worse");
            fail("bad session and user_id values should throw an exception");
        }
        catch (ToznyApiException e ) { assertTrue(true); }

    }
}

