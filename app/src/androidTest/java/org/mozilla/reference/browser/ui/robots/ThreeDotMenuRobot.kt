/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.reference.browser.ui.robots

import android.provider.Browser
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.uiautomator.UiDevice
import org.mozilla.reference.browser.helpers.click
import org.mozilla.reference.browser.R

class ThreeDotMenuRobot {

    fun verifyThreeDotMenuExists() = threeDotMenuRecyclerViewExists()
    fun verifyThreeDotMenuDoesNotExist()= threeDotMenuRecyclerViewDoesNotExist()
    fun verifyForward() = forwardButton()
    fun verifyReload() = refreshButton()
    fun verifyStop() = stopButton()
    fun verifyToggleRequestDesktopSite() = requestDesktopSiteToggle()
    fun verifyReportIssue() = reportIssueButton()

    class Transition {

//        fun goForward(interact: NavigationToolbarRobot.() -> Unit): NavigationToolbarRobot.Transition {
//            forwardButton().click()
//            NavigationToolbarRobot().interact()
//            return NavigationToolbarRobot.Transition()
//        }

        fun goForward(interact: BrowserRobot.() -> Unit): BrowserRobot.Transition {
            forwardButton().click()
            BrowserRobot().interact()
            return BrowserRobot.Transition()
        }

        fun refreshPage(interact: NavigationToolbarRobot.() -> Unit): NavigationToolbarRobot.Transition {
            refreshButton().click()
            NavigationToolbarRobot().interact()
            return NavigationToolbarRobot.Transition()
        }

        fun doStop(interact: NavigationToolbarRobot.() -> Unit): NavigationToolbarRobot.Transition {
            stopButton().click()
            NavigationToolbarRobot().interact()
            return NavigationToolbarRobot.Transition()

        }

        fun openShare(interact: NavigationToolbarRobot.() -> Unit): NavigationToolbarRobot.Transition {
            val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

            shareButton().click()
            device.pressBack()

            NavigationToolbarRobot().interact()
            return NavigationToolbarRobot.Transition()
        }

        fun requestDesktopSite(interact: NavigationToolbarRobot.() -> Unit): NavigationToolbarRobot.Transition {
            requestDesktopSiteToggle().click()
            NavigationToolbarRobot().interact()
            return NavigationToolbarRobot.Transition()
        }

        fun findInPage(interact: NavigationToolbarRobot.() -> Unit): NavigationToolbarRobot.Transition {
            val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

            findInPageButton().click()
            device.pressBack()
            device.pressBack()
            NavigationToolbarRobot().interact()
            return NavigationToolbarRobot.Transition()
        }

        fun reportIssue(interact: NavigationToolbarRobot.() -> Unit): NavigationToolbarRobot.Transition {
            reportIssueButton().click()
            NavigationToolbarRobot().interact()
            return NavigationToolbarRobot.Transition()
        }

        fun openSettings(interact: SettingsViewRobot.() -> Unit): SettingsViewRobot.Transition {
            settingsButton().click()
            SettingsViewRobot().interact()            return SettingsViewRobot.Transition()
        }
    }
}

private fun threeDotMenuRecyclerViewExists() {
    onView(withId(R.id.mozac_browser_menu_recyclerView)).check(matches(isDisplayed()))
    reportIssueButton()
}

private fun threeDotMenuRecyclerViewDoesNotExist() = onView(withId(R.id.mozac_browser_menu_recyclerView)).check(doesNotExist())
private fun forwardButton() = onView(ViewMatchers.withContentDescription("Forward"))
private fun refreshButton() = onView(ViewMatchers.withContentDescription("Refresh"))
private fun stopButton() = onView(ViewMatchers.withContentDescription("Stop"))
//private fun verifyStopButton()  {
//    stopButton()
//    stopButton().click()
//    threeDotMenuRecyclerViewDoesNotExist()
//}
private fun shareButton() = onView(ViewMatchers.withText("Share"))
private fun requestDesktopSiteToggle() = onView(ViewMatchers.withText("Request desktop site"))
private fun findInPageButton() = onView(ViewMatchers.withText("Find in Page"))
private fun reportIssueButton() = onView(ViewMatchers.withText("Report issue"))
private fun settingsButton() = onView(ViewMatchers.withText("Settings"))