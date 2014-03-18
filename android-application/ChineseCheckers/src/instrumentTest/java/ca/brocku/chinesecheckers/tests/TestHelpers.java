package ca.brocku.chinesecheckers.tests;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import ca.brocku.chinesecheckers.R;

/**
 * Created by Main on 2/18/14.
 */
public class TestHelpers{

    public int timeoutForActivityTransition = 50000;

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

    public void ImageButtonTest(ActivityInstrumentationTestCase2 withThis, ImageButton toTest,Boolean shouldBeVisible){
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

    public void RadioButtonTest(ActivityInstrumentationTestCase2 withThis,RadioButton toTest,Boolean shouldBeVisible,Boolean shouldBeClicked){
        String radioButtonName = toTest.getResources().getResourceName(toTest.getId());

        if(shouldBeVisible){ //Test visibility
            withThis.assertTrue(radioButtonName + " Not Shown", toTest.isShown());
        }
        else{
            withThis.assertFalse(radioButtonName + " Shown When Shouldn't", toTest.isShown());
        }
        if(shouldBeClicked){ //Test visibility
            withThis.assertTrue(radioButtonName + " Not Checked", toTest.isChecked());
        }
        else{
            withThis.assertFalse(radioButtonName + " Checked When Shouldn't", toTest.isChecked());
        }

        withThis.assertTrue(radioButtonName +  " Not Clickable",toTest.isClickable()); //Test if enabled
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

    public void AIToggleButtonTest(ActivityInstrumentationTestCase2 withThis, ToggleButton easy,ToggleButton medium, ToggleButton hard,int shouldBeSelected){
        String easyName = easy.getResources().getResourceName(easy.getId());
        String mediumName = medium.getResources().getResourceName(medium.getId());
        String hardName = hard.getResources().getResourceName(hard.getId());

        withThis.assertNotNull(easyName + " Not Found",easy); //Test if existing
        withThis.assertNotNull(mediumName + " Not Found",medium); //Test if existing
        withThis.assertNotNull(hardName + " Not Found",hard); //Test if existing

        withThis.assertTrue(easyName + " Not Shown", easy.isShown());
        withThis.assertTrue(mediumName + " Not Shown", medium.isShown());
        withThis.assertTrue(hardName + " Not Shown", hard.isShown());

        switch(shouldBeSelected){
            case 1:
                withThis.assertTrue(easyName + " Not Selected By Default", easy.isChecked());
                withThis.assertFalse(mediumName + " Selected By Default", medium.isChecked());
                withThis.assertFalse(hardName + " Selected By Default", hard.isChecked());
                break;
            case 2:
                withThis.assertFalse(easyName + " Selected Improperly", easy.isChecked());
                withThis.assertTrue(mediumName + " Not Selected", medium.isChecked());
                withThis.assertFalse(hardName + " Selected Improperly", hard.isChecked());
                break;
            case 3:
                withThis.assertFalse(easyName + " Selected Improperly", easy.isChecked());
                withThis.assertFalse(mediumName + " Selected Improperly", medium.isChecked());
                withThis.assertTrue(hardName + " Not Selected", hard.isChecked());
                break;
        }

    }

}
