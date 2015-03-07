package com.tozny.sdk.realm.methods.check_valid_login;


import com.tozny.sdk.ToznyApiException;
import com.tozny.sdk.ToznyApiResponse;

public class CheckValidLoginResponse extends ToznyApiResponse {
    /**
     * @return The response from the Tozny API server.
     * @throws com.tozny.sdk.ToznyApiException if we culd not determine the boolean response from realm.check_valid_login
     */
    public boolean isLoginValid() throws ToznyApiException {
        Object response = this.get("return");
        if (response instanceof Boolean) return (Boolean) response;
        else if (response instanceof String) return Boolean.valueOf((String) response);
        throw new ToznyApiException("Unexpected result from server when attempting to determine if the login was valid.");
    }
}
