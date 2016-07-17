package com.hansheng.studynote;

import android.support.test.rule.ActivityTestRule;

import com.hansheng.studynote.test.TestActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by hansheng on 2016/7/16.
 */
public class MainActivityInstrumentationTest {
    private static final String STRING_TO_BE_TYPED = "Peter";

    @Rule
    public ActivityTestRule<TestActivity> mActivityRule = new ActivityTestRule<>(
            TestActivity.class);

    @Test
    public void sayHello(){
        onView(withId(R.id.editText)).perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard()); //line 1

        onView(withText("Say hello!")).perform(click()); //line 2


    }


}
