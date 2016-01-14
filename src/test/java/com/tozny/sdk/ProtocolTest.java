package com.tozny.sdk;

import static org.junit.Assert.*;
import org.junit.Test;

import java.io.IOException;

public class ProtocolTest {
    @Test
    public void testCompareStrings() throws IOException {
        assertTrue(Protocol.compareStrings("one", "one"));
        assertTrue(Protocol.compareStrings("two", "two"));
        assertFalse(Protocol.compareStrings("one", "two"));
        assertFalse(Protocol.compareStrings("two", "one"));
        assertFalse(Protocol.compareStrings("one", "onetwo"));
        assertFalse(Protocol.compareStrings("two", "onetwo"));
        // TODO: Implement random comparisons
    }
}
