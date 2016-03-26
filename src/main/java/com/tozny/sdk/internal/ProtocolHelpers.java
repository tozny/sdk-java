package com.tozny.sdk.internal;

import com.tozny.sdk.realm.config.ToznyRealmSecret;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * A Set of static helper functions that performs most of the performance or cryptographically sensitive functions needed
 * while interacting with the Tozny API.
 */
public class ProtocolHelpers {

    public static final String RANDOM_ALGORITHM = "SHA1PRNG";
    public static final Charset utf8 = Charset.forName("UTF-8");


    /**
     * Computes the signature of the given message, using the given secret, then compares to the given signature to
     * test if they match.
     *
     * @param secret The secret to sign the message with.
     * @param signature The signature to compare to after signing the message with the secret.
     * @param message The message to sign with the secret.
     * @return True if the given secret and message were digested and compared successfully to the given signature.
     * @throws NoSuchAlgorithmException if the "HmacSHA256" algorithm does not exist.
     * @throws InvalidKeyException If there was a problem initializing the HmacSHA256 mac instance.
     */
    public static boolean checkSignature (ToznyRealmSecret secret, String signature, String message)
    throws InvalidKeyException, NoSuchAlgorithmException {
        return compareStrings(sign(secret, message), signature);
    }


    /**
     * Encodes the given byte array into a URL safe Base64 encoding.
     *
     * @param bytes the bytes to encode.
     * @return URL safe Base64 encoding of the given bytes.
     */
    public static String base64UrlEncode (byte[] bytes) {
        return new Base64(9999, "".getBytes(), true).encodeToString(bytes);
    }

    public static byte[] base64UrlDecode (String encoded) {
        return Base64.decodeBase64(encoded);
    }

    /**
     * Creates a SHA256 HMAC digest function on the given message, using the given signature.
     *
     * @param secret The secret to create a digest with.
     * @param message The  message to create a digest for.
     * @return A Base64url encoded String of the digest.
     * @throws NoSuchAlgorithmException if the "HmacSHA256" algorithm does not exist.
     * @throws InvalidKeyException If there was a problem initializing the HmacSHA256 mac instance.
     */
    public static String sign (ToznyRealmSecret secret, String message)
    throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec secretKey = new SecretKeySpec(secret.value.getBytes(utf8), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKey);
        byte[] result = mac.doFinal(message.getBytes(utf8));
        return base64UrlEncode(result);
    }

    /**
     * Creates an expiry timestamp.
     *
     * @return an expiry timestamp 6 minutes in the future from now.
     */
    public static String getExpires () {
        return Double.toString(Math.floor((System.currentTimeMillis() + (5 * 60 * 1000)) / 1000));
    }

    /**
     * Creates a random sequence string.
     *
     * @return returns a random string, created from hexencoding 32 random bytes.
     * @throws NoSuchAlgorithmException if the "SHA1PRNG" random algorithm doesn't exit.
     */
    public static String getNonce () throws NoSuchAlgorithmException {
        byte[] bytes = getRandomBytes(32);
        return new String(Hex.encodeHex(bytes));
    }

    /**
     * Creates an array of random bytes.
     *
     * @param numberOfBytes the number of random bytes to return.
     * @return a byte array the size of numberOfBytes, containing random bytes.
     * @throws NoSuchAlgorithmException if the "SHA1PRNG" random algorithm doesn't exit.
     */
    public static byte[] getRandomBytes (int numberOfBytes) throws NoSuchAlgorithmException {
        byte[] bytes = new byte[numberOfBytes];
        SecureRandom random = SecureRandom.getInstance(RANDOM_ALGORITHM);
        random.nextBytes(bytes);
        return bytes;
    }

    /**
     * Compares two string, taking the same amount of time if the comparison fails, as it does if it succeeds.
     *
     * @param one the first input string to compare to the second.
     * @param two the second input string to compare to the first.
     * @return True if the two strings are identical.
     */
    public static boolean compareStrings (String one, String two) {
        byte[] b1 = one.getBytes();
        byte[] b2 = two.getBytes();

        // find the shortest length of the two inputs.
        int length = (b1.length >= b2.length) ? b2.length : b1.length;

        // We initialize the result accumulator with the difference of the lengths of the two inputs,
        // which, if they are the same length, will be zero, which is what we expect the final result to be.
        int result =  b1.length - b2.length;;

        // XOR the bytes at the same index in eachof the inputs, then OR them into our result accumulator.
        for (int i = 0; i < length; i++) { result  |= b1[i] ^ b2[i]; }

        // finally, if the inputs were the same length and the resulting aggregate is 0, then they are equal.
        return result == 0;
    }
}
