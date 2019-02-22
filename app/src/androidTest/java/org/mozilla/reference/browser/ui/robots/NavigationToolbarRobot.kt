/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.reference.browser.ui.robots

import android.net.Uri
import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.startsWith
import org.hamcrest.TypeSafeMatcher
import org.mozilla.reference.browser.R
import org.mozilla.reference.browser.helpers.click
/**
 * Implementation of Robot Pattern for the navigation toolbar  menu.
 */
class NavigationToolbarRobot {

//    fun navigateToPage()

    class Transition {

        fun enterUrlAndEnterToBrowser(url: Uri, interact: BrowserRobot.() -> Unit): BrowserRobot.Transition {
            urlBar().perform(click())
            urlBar2().perform(replaceText(url.toString()),
                    pressImeActionButton())

            BrowserRobot().interact()
            return BrowserRobot.Transition()
        }

        fun enterUrlAndOpenSearchView(url: Uri, interact: AwesomeBarRobot.() -> Unit): AwesomeBarRobot.Transition {
            urlBar().perform(click())
            urlBar2().perform(replaceText(url.toString()))
            urlBar3().perform(pressImeActionButton())
            AwesomeBarRobot().interact()
            return AwesomeBarRobot.Transition()
        }

        fun openThreeDotMenu(interact: ThreeDotMenuRobot.() -> Unit): ThreeDotMenuRobot.Transition {
            threeDotButton().click()
            ThreeDotMenuRobot().interact()            return ThreeDotMenuRobot.Transition()
        }

        fun openTabTrayMenu(interact: TabTrayMenuRobot.() -> Unit): TabTrayMenuRobot.Transition {
            openTabTray().click()
            TabTrayMenuRobot().interact()
            return TabTrayMenuRobot.Transition()
        }
    }
}

fun navigationToolbar(interact: NavigationToolbarRobot.() -> Unit): NavigationToolbarRobot.Transition {
    NavigationToolbarRobot().interact()
    return NavigationToolbarRobot.Transition()
}

private fun threeDotButton() = onView(ViewMatchers.withContentDescription("Menu"))
private fun openTabTray() = onView(ViewMatchers.withId(R.id.counter_box))
private fun urlBar() = onView(ViewMatchers.withId(R.id.mozac_browser_toolbar_url_view))
fun urlBar2() = onView(
        allOf(childAtPosition(
                childAtPosition(
                        withId(R.id.toolbar),
                        1),
                0),
                isDisplayed()))

fun urlBar3() = urlBar2()

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