package com.tozny.sdk;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.IOException;

public class ProtocolTest  extends TestCase {

    public ProtocolTest(String testName)
    {
        super( testName );

    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ProtocolTest.class );
    }

    public void testCompareStrings() throws IOException {
        assertTrue(Protocol.compareStrings("one", "one"));
        assertTrue(Protocol.compareStrings("two", "two"));
        assertFalse(Protocol.compareStrings("one", "two"));
        assertFalse(Protocol.compareStrings("two", "one"));
        assertFalse(Protocol.compareStrings("one", "onetwo"));
        assertFalse(Protocol.compareStrings("two", "onetwo"));
        //TODO: Implement random comparisons
    }
}
