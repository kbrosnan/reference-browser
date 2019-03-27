/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.reference.browser.ui

import org.junit.Rule
import org.junit.Test
import org.mozilla.reference.browser.helpers.BrowserActivityTestRule
import org.mozilla.reference.browser.ui.robots.navigationToolbar

/**
 *  Tests for verifying the settings view options exist as expected:
 * - Appears when the settings submenu is tapped
 * - Expected options are displayed as listed below
 */

class SettingsViewTest {

    @get:Rule val browserActivityTestRule = BrowserActivityTestRule()

    /* ktlint-disable no-blank-line-before-rbrace */ // This imposes unrgeadable grouping.
    @Test
    // This test verifies settings view items are all in place
    fun settingsItemsTest() {
        navigationToolbar {
        }.openThreeDotMenu {
        }.openSettings {
            verifySettingsViewExists()
            verifyNavigateUp()
            verifySyncSigninButton()
            verifySyncHistorySummary()
            verifyPrivacyButton()
            verifyPrivacySummary()
            verifyMakeDefaultBrowserButton()
            verifyDeveloperToolsHeading()
            verifyRemoteDebuggingText()
            verifyRemoteDebuggingToggle()
            verifyMozillaHeading()
            verifyAboutReferenceBrowserButton()
        }
    }
    @Test
    fun openFXATest() {
        navigationToolbar {
        }.openThreeDotMenu {
        }.openSettings {
        }.openFXASignin {
            verifyFXAUrl()
        }
    }
    @Test
    fun privacySettingsItemsTest() {
        navigationToolbar {
        }.openThreeDotMenu {
        }.openSettings {
        }.openSettingsViewPrivacy {
            verifyPrivacyUpButton()
            verifyPrivacySettings()
            verifyTrackingProtectionHeading()
            verifyTPEnableInNormalBrowsing()
            verifyTPEnableinPrivateBrowsing()
            verifyDataChoicesHeading()
            verifyUseTelemetryToggle()
            verifyUseTelemetryToggle()
            verifyTelemetrySummary()

        }
    }
    @Test
    fun setDefaultBrowserTest() {
        navigationToolbar {
        }.openThreeDotMenu {
        }.openSettings {
        }.makeDefaultBrowser{
            verifyAndroidDefaultApps()
        }
    }
    @Test
    fun remoteDebuggingViaUSB() {
        navigationToolbar {
        }.openThreeDotMenu {
        }.openSettings {
            toggleRemoteDebuggingOn()
            toggleRemoteDebuggingOff()
            toggleRemoteDebuggingOn()
        }
    }
    @Test
    fun aboutReferenceBrowserTest() {
        navigationToolbar {
        }.openThreeDotMenu {
        }.openSettings {
        }.openAboutReferenceBrowser {
            verifyAboutBrowser()
        }
    }
}
