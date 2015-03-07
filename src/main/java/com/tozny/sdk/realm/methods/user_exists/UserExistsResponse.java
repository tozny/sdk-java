package com.tozny.sdk.realm.methods.user_exists;

import com.tozny.sdk.ToznyApiException;
import com.tozny.sdk.ToznyApiResponse;

/**
 * A GenericJson instance intended for marshaling an boolean response.
 */
public class UserExistsResponse extends ToznyApiResponse {
    /**
     * @return The response from the Tozny API server.
     * @throws ToznyApiException if we culd not determine the boolean response from realm.user_get
     */
    public boolean getUserExists() throws ToznyApiException {
        Object response = this.get("return");
        if (response instanceof Boolean) return (Boolean) response;
        else if (response instanceof String) return Boolean.valueOf((String) response);
        throw new ToznyApiException("Unexpected result from server when attempting to determine if user exists.");
    }

}
