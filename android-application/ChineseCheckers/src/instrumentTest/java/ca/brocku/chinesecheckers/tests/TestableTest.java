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


    /*
     * this test should pass
     */
    public void testIsTrueShouldBeTrue() throws Exception {
        Testable sut = new Testable();
        assertTrue("testIsTrueShouldBeTrue failed: isTrue did not return true", sut.isTrue());
    }


    /*
     * this test should fail.
     * it was written to observe output created for a failing test
     */
    public void testIsTrueShouldBeFalse() throws Exception {
        Testable sut = new Testable();
        assertFalse("testIsTrueShouldBeFalse failed: isTrue did not return false", sut.isTrue());
    }
}
