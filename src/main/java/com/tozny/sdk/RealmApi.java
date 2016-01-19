package com.tozny.sdk;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;

import com.tozny.sdk.realm.RealmConfig;
import com.tozny.sdk.realm.Session;
import com.tozny.sdk.realm.User;
import com.tozny.sdk.realm.methods.check_valid_login.CheckValidLoginResponse;
import com.tozny.sdk.realm.methods.check_valid_login.CheckValidLoginUrl;
import com.tozny.sdk.realm.methods.question_challenge.QuestionChallengeResponse;
import com.tozny.sdk.realm.methods.question_challenge.QuestionChallengeUrl;
import com.tozny.sdk.realm.methods.user_exists.UserExistsResponse;
import com.tozny.sdk.realm.methods.user_exists.UserExistsUrl;
import com.tozny.sdk.realm.methods.user_get.UserGetResponse;
import com.tozny.sdk.realm.methods.user_get.UserGetUrl;

/**
 * Main entry point to the Tozny API's Realm calls.
 * Realm cals are typically used by Service Providers, web-site owners, or realm operators.
 * To make any Realm API calls, you will need a Tozny Realm Key ID and a Realm Key Secret,
 * which can be obtained from the Tozny Admin Console (admin.tozny.com).
 */
public class RealmApi {
    public final RealmConfig config;
    public final HttpRequestFactory requestFactory;
    public final JsonFactory jsonFactory;

    /**
     * Builds a RealmApi instance.
     *
     * @param config the Realm's KeyID and Secret to use on all realm calls
     */
    public RealmApi(RealmConfig config) {
        this(config, getDefaultRequestFactory(), getDefaultJsonFactory());
    }

    /**
     * Builds a RealmApi instance
     * @param config the Realm's KeyID and Secret to use on all realm calls
     * @param requestFactory the Google HTTP Client request factory instance to make calls out to Tozny's API servers with.
     * @param jsonFactory the Google HTTP Client JSON factory instance to use when constructing or maipulating JSON structures.
     */
    public RealmApi(RealmConfig config, HttpRequestFactory requestFactory, JsonFactory jsonFactory) {
        this.config = config;
        this.requestFactory = requestFactory;
        this.jsonFactory = jsonFactory;
    }

    /**
     * Verify that the signedData payload's signature matches the given signature.
     * @param signedData the payload to compute a signature with
     * @param signature the proposed signature of the signedData payload.
     * @return True if the signedData, along with the realm key's secret, produced a signature that matched the given signature.
     * @throws ToznyApiException If an error occurs while coputing the signature on the given signedData.
     */
    public boolean verifyLogin (String signedData, String signature) throws ToznyApiException {
        try {
            return Protocol.checkSignature(this.config.realmSecret, signature, signedData);
        } catch (InvalidKeyException e) {
            throw new ToznyApiException("While attempting to verify login", e);
        } catch (NoSuchAlgorithmException e) {
            throw new ToznyApiException("While attempting to verify login", e);
        }

    }

    /**
     * Calls 'realm.user_get' to retrieve the user identified by the given userId.
     * @param userId the id of the user to retrieve.
     * @return an instance of <code>User</code>
     * @throws ToznyApiException If a user does not exist, or if an error occurs either in communicating, or marshaling a <code>User</code> response from the Tozny API.
     */
    public User userGet (String userId) throws ToznyApiException {
        UserGetUrl url = new UserGetUrl(this.config, this.jsonFactory);
        url.user_id = userId;
        return rawCall(url, UserGetResponse.class).getUser();
    }


    /**
     * Calls 'realm.user_get' to retrieve the user identified by the given email address.
     * @param email the email address of the user to retrieve.
     * @return an instance of <code>User</code>
     * @throws ToznyApiException If a user does not exist, or if an error occurs either in communicating, or marshaling a <code>User</code> response from the Tozny API.
     */
    public User userGetByEmail (String email) throws ToznyApiException {
        UserGetUrl url = new UserGetUrl(this.config, this.jsonFactory);
        url.tozny_email = email;
        return rawCall(url, UserGetResponse.class).getUser();
    }

    /**
     * Calls 'realm.user_exists' to test if the user identified by the given userId exists in the realm.
     * @param userId the id of the user to test the existence of.
     * @return true if the user exists
     * @throws ToznyApiException If an error occurs either in communicating, or marshaling a response from the Tozny API.
     */
    public boolean userExists(String userId) throws ToznyApiException {
        UserExistsUrl url = new UserExistsUrl(this.config, this.jsonFactory);
        url.user_id = userId;
        return rawCall(url, UserExistsResponse.class).getUserExists();
    }

    /**
     * Calls 'realm.user_exists' to test if the user identified by the given email address exists in the realm.
     * @param email the email address of the user to retrieve.
     * @return true if the user exists
     * @throws ToznyApiException If an error occurs either in communicating, or marshaling a response from the Tozny API.
     */
    public boolean userExistsByEmail(String email) throws ToznyApiException {
        UserExistsUrl url = new UserExistsUrl(this.config, this.jsonFactory);
        url.tozny_email = email;
        return rawCall(url, UserExistsResponse.class).getUserExists();
    }

    /**
     * Calls 'realm.question_challenge' to create a new question challenge session.
     *
     * @param question the question to ask the user.
     * @return an instance of Session that represents a new challenge session.
     * @throws ToznyApiException If an error occurs either in communicating, or marshaling a response from the Tozny API.
     */
    public Session questionChallenge (String question) throws ToznyApiException {
        QuestionChallengeUrl url = new QuestionChallengeUrl(this.config, this.jsonFactory, question);
        return rawCall(url,  QuestionChallengeResponse.class).getChallengeSession();
    }

    /**
     * Calls 'realm.question_challenge' to create a new question challenge session and associates it with the given userId.
     *
     * @param question the question to ask the user.
     * @param userId the user to associate a response from.
     * @return an instance of Session that represents a new challenge session.
     * @throws ToznyApiException If an error occurs either in communicating, or marshaling a response from the Tozny API.
     */
    public Session questionChallenge (String question, String userId) throws ToznyApiException {
        QuestionChallengeUrl url = new QuestionChallengeUrl(this.config, this.jsonFactory, question);
        url.user_id = userId;
        return rawCall(url,  QuestionChallengeResponse.class).getChallengeSession();
    }

    /**
     * Calls 'realm.check_valid_login' to test if the given userId owns the given sessionId.
     *
     * @param userId The the proposed owner of the session
     * @param sessionId The session whos owner we are testing.
     * @return True if the given user owen the given session.
     * @throws ToznyApiException  If the session or user does not exist, or if an error occurs either in communicating, or marshaling a response from the Tozny API.
     */
    public boolean checkValidLogin (String userId, String sessionId) throws ToznyApiException {
        CheckValidLoginUrl url = new CheckValidLoginUrl(this.config, this.jsonFactory, userId, sessionId);
        return rawCall(url, CheckValidLoginResponse.class).isLoginValid();
    }

    /**
     * Performs a raw call to the the given URL, after setting the accept header to 'application/json'.
     * The Response is them passed to an instance of the given dataClass for marshaling into the result type.
     *
     * @param url The URL to call
     * @param dataClass The response marshalling class.
     * @param <T> A descendant of ToznyApiResponse, which will provide helpers for managing ToznyAPI errors.
     * @return an instance of the given dataClass.
     * @throws ToznyApiException
     */
    public <T extends ToznyApiResponse> T rawCall (GenericUrl url, Class<T> dataClass) throws ToznyApiException {
        T apiResponse;
        try {
            HttpRequest request = this.requestFactory.buildGetRequest(url);

            HttpHeaders headers = request.getHeaders();
            headers.setAccept("application/json");

            apiResponse = request.execute().parseAs(dataClass);
        } catch (IOException e) {
            String message =  "While calling "+url+".";
            throw new ToznyApiException(message,e);
        }

        if (apiResponse.isError()) throw apiResponse.getException();
        else return apiResponse;
    }

    // Default HTTP and JSON factories.
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    private static HttpRequestFactory getDefaultRequestFactory() {
        return HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
            @Override
            public void initialize(HttpRequest request) {
                request.setParser(new JsonObjectParser(JSON_FACTORY));
            }
        });
    }

    private static JsonFactory getDefaultJsonFactory() {
        return JSON_FACTORY;
    }
}
