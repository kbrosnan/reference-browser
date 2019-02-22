/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.reference.browser.ui.robots

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.web.assertion.WebViewAssertions.webMatches
import androidx.test.espresso.web.model.Atoms
import androidx.test.espresso.web.sugar.Web.onWebView
import androidx.test.espresso.web.webdriver.DriverAtoms.findElement
import androidx.test.espresso.web.webdriver.DriverAtoms.getText
import androidx.test.espresso.web.webdriver.Locator
import androidx.test.uiautomator.UiDevice
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.mozilla.reference.browser.R

class AwesomeBarRobot {

    /**
     */

    fun clearUrl() = urlBarSearch().perform(clearText())

    class Transition {

        fun openWebPage(interact: NavigationToolbarRobot.() -> Unit): NavigationToolbarRobot.Transition {

            NavigationToolbarRobot().interact()
            return NavigationToolbarRobot.Transition()
        }
    }
}

fun browser(interact: AwesomeBarRobot.() -> Unit): AwesomeBarRobot.Transition {
    AwesomeBarRobot().interact()
    return AwesomeBarRobot.Transition()
}
private fun webView() = onWebView()
private fun urlBarSearch() = onView(
        allOf(withText("https://www.mozilla.org/en-US/"),
                childAtPosition(
                        childAtPosition(
                                withId(R.id.toolbar),
                                0),
                        1),
                isDisplayed()))


private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int): Matcher<View> {

    return object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description) {
            description.appendText("Child at position $position in parent ")
            parentMatcher.describeTo(description)
        }

        public override fun matchesSafely(view: View): Boolean {
            val parent = view.parent
            return (parent is ViewGroup && parentMatcher.matches(parent)
                    && view == parent.getChildAt(position))
        }
    }
}


//inlineAutocompleteEditText.perform(pressImeActionButton())