package ca.brocku.chinesecheckers.tests;

import junit.framework.TestCase;

import ca.brocku.chinesecheckers.Testable;

/**
 * Created by Curtis on 03/02/14.
 */
public class TestableTest extends TestCase {
    public void setUp() throws Exception {
        super.setUp();

    }

    public void tearDown() throws Exception {

    }


    public void testIsTrueShouldBeTrue() throws Exception {
        Testable sut = new Testable();
        assertTrue("testIsTrueShouldBeTrue failed: isTrue did not return true", sut.isTrue());
    }
}
