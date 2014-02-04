package ca.brocku.chinesecheckers.tests;

import android.test.ActivityInstrumentationTestCase2;
import ca.brocku.chinesecheckers.MainActivity;

/**
* Created by Main on 2/3/14.
*/

public class HomeAndSeatTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity thisTest;

    public HomeAndSeatTest(){
        super(MainActivity.class);
    }
    protected void setUp() throws Exception{
        super.setUp();
        setActivityInitialTouchMode(false);
        thisTest = getActivity();
        assertEquals(1,2);
    }
}