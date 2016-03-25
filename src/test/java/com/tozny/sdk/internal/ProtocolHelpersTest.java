package com.tozny.sdk.internal;

import static org.junit.Assert.*;
import org.junit.Test;

import java.io.IOException;

public class ProtocolHelpersTest {
    @Test
    public void testCompareStrings() throws IOException {
        assertTrue(ProtocolHelpers.compareStrings("one", "one"));
        assertTrue(ProtocolHelpers.compareStrings("two", "two"));
        assertFalse(ProtocolHelpers.compareStrings("one", "two"));
        assertFalse(ProtocolHelpers.compareStrings("two", "one"));
        assertFalse(ProtocolHelpers.compareStrings("one", "onetwo"));
        assertFalse(ProtocolHelpers.compareStrings("two", "onetwo"));
        // TODO: Implement random comparisons
    }
}
