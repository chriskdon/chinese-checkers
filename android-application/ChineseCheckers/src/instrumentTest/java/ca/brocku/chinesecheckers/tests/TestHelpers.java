package ca.brocku.chinesecheckers.tests;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ca.brocku.chinesecheckers.R;

/**
 * Created by Main on 2/18/14.
 */
public class TestHelpers{

    public TestHelpers(){}

    public void ButtonTest(ActivityInstrumentationTestCase2 withThis, Button toTest,Boolean shouldBeVisible){
        String buttonName = toTest.getResources().getResourceName(toTest.getId());

        withThis.assertNotNull(buttonName + " Not Found",toTest); //Test if existing
        if(shouldBeVisible){ //Test visibility
            withThis.assertTrue(buttonName + " Not Shown", toTest.isShown());
        }
        else{
            withThis.assertFalse(buttonName + " Shown When Shouldn't", toTest.isShown());
        }
        withThis.assertTrue(buttonName +  " Not Clickable",toTest.isClickable()); //Test if clickable
    }

    public void EditTextTest(ActivityInstrumentationTestCase2 withThis,EditText toTest,Boolean shouldBeVisible){
        String editTextName = toTest.getResources().getResourceName(toTest.getId());

        withThis.assertNotNull(editTextName + " Not Found",toTest); //Test if existing
        if(shouldBeVisible){ //Test visibility
            withThis.assertTrue(editTextName + " Not Shown", toTest.isShown());
        }
        else{
            withThis.assertFalse(editTextName + " Shown When Shouldn't", toTest.isShown());
        }
        withThis.assertTrue(editTextName +  " Not Enabled",toTest.isEnabled()); //Test if enabled
        withThis.assertEquals(editTextName + " Initalized With Improper Text", "", toTest.getText().toString()); //Checks proper text initialization
    }

    public void EditTextTest(ActivityInstrumentationTestCase2 withThis,EditText toTest,Boolean shouldBeVisible,String shouldRead){
        String editTextName = toTest.getResources().getResourceName(toTest.getId());

        withThis.assertNotNull(editTextName + " Not Found",toTest); //Test if existing
        if(shouldBeVisible){ //Test visibility
            withThis.assertTrue(editTextName + " Not Shown", toTest.isShown());
        }
        else{
            withThis.assertFalse(editTextName + " Shown When Shouldn't", toTest.isShown());
        }
        withThis.assertTrue(editTextName +  " Not Enabled",toTest.isEnabled()); //Test if enabled
        withThis.assertEquals(editTextName +  " Initalized With Improper Text",shouldRead,toTest.getText().toString()); //Checks proper text initialization
    } 
    public void TextViewTest(ActivityInstrumentationTestCase2 withThis,TextView toTest,Boolean shouldBeVisible,String shouldRead){
        String textViewName = toTest.getResources().getResourceName(toTest.getId());

        withThis.assertNotNull(textViewName + " Not Found",toTest); //Test if existing
        if(shouldBeVisible){ //Test visibility
            withThis.assertTrue(textViewName + " Not Shown", toTest.isShown());
        }
        else{
            withThis.assertFalse(textViewName + " Shown When Shouldn't", toTest.isShown());
        }
        withThis.assertEquals(textViewName +  " Initalized With Improper Text",shouldRead,toTest.getText().toString()); //Checks proper text initialization
    }

}
