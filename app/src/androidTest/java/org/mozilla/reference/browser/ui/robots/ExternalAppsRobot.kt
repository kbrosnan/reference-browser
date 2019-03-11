/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.reference.browser.ui.robots

import androidx.test.InstrumentationRegistry
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.uiautomator.UiDevice

class ExternalAppsRobot {
    fun verifyAndroidDefaultApps() = defaultAppsLayout()

    class Transition {
        private val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        fun exitToHomeScreen() {
            device.pressBack()
        }

    }
}

private fun defaultAppsLayout() = withText("Default apps")
