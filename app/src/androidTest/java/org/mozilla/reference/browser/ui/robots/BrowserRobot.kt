
/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.reference.browser.ui.robots

import androidx.preference.PreferenceManager
import androidx.test.espresso.Espresso.onView
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.web.assertion.WebViewAssertions.webMatches
import androidx.test.espresso.web.model.Atoms
import androidx.test.espresso.web.sugar.Web.onWebView
import androidx.test.espresso.web.webdriver.DriverAtoms.findElement
import androidx.test.espresso.web.webdriver.DriverAtoms.getText
import androidx.test.espresso.web.webdriver.Locator
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.mozilla.reference.browser.R
import org.mozilla.reference.browser.helpers.TestAssetHelper
import org.mozilla.reference.browser.helpers.assertEqualsWithDelta

class BrowserRobot {

    /**
     * Executes the given JS string.
     * @return if the final expression is a return statement, returns the provided String, else null.
     */
    fun executeJS(js: String): String? {
        return webView()
                .perform(Atoms.script(js))
                .get().value as? String
    }

    fun getPageTitle(): String {
        return webView()
                .perform(Atoms.getTitle())
                .get()
    }

    /**
     * Asserts that the text within DOM element with ID="testContent" has the given text, i.e.
     *   document.querySelector('#testContent').innerText == expectedText
     */

    // only works with WebView (not GeckoView)
//
//    fun assertDOMElementExists(locator: Locator, value: String) {
//        webView().withElement(findElement(locator, value))
//    }

    fun assertTestContent(expectedText: String) {
        val mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        mDevice.waitForIdle()
//        val testContent = mDevice.findObject((UiSelector().resourceId("testContent")))
        val testContent = mDevice.findObject((UiSelector().textContains(expectedText)))
        testContent.waitForExists(TestAssetHelper.waitingTime)
        assertTrue(testContent.exists())

    }
    fun enterUrl() {
        UrlBar().perform(clearText())
    }

    class Transition {
        private val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        fun openNavigationToolbar(interact: NavigationToolbarRobot.() -> Unit): NavigationToolbarRobot.Transition {
            device.pressMenu()

            NavigationToolbarRobot().interact()
            return NavigationToolbarRobot.Transition()
        }
    }
}

fun browser(interact: BrowserRobot.() -> Unit): BrowserRobot.Transition {
    BrowserRobot().interact()
    return BrowserRobot.Transition()
}

fun UrlBar() = onView(withId(R.id.mozac_browser_awesomebar_title))
private fun webView() = onWebView()
