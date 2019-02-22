/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.mozilla.reference.browser.ui

import androidx.test.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import okhttp3.mockwebserver.MockWebServer
import org.bouncycastle.crypto.tls.ConnectionEnd.server
import org.junit.*
import org.mozilla.reference.browser.ext.toUri
import org.mozilla.reference.browser.helpers.AndroidAssetDispatcher
import org.mozilla.reference.browser.helpers.BrowserActivityTestRule
import org.mozilla.reference.browser.helpers.MockWebServerHelper
import org.mozilla.reference.browser.helpers.TestAssetHelper
import org.mozilla.reference.browser.ui.robots.UrlBar
import org.mozilla.reference.browser.ui.robots.navigationToolbar

/** *  Tests for verifying three dot menu options:
 * - Appears when three dot icon is tapped
 * - Expected options are displayed as listed below
 */

class ThreeDotMenuTest {

    private val mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    @get:Rule
    val activityTestRule = BrowserActivityTestRule()

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer().apply {
            setDispatcher(AndroidAssetDispatcher())
            start()
        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun demoURL() {
        //val endpoint = TestAssetHelper.getRefreshAsset(mockWebServer)
        val endpoint = TestAssetHelper.getGenericAsset(mockWebServer, 1)

        navigationToolbar {
        }.enterUrlAndEnterToBrowser(endpoint.url) {
        }
    }
    /* ktlint-disable no-blank-line-before-rbrace */ // This imposes unreadable grouping.

    // This test verifies the three dot menu items are all in place
//    @Ignore

    @Test
    fun goForwardTest() {

        val defaultWebPage = TestAssetHelper.getGenericAsset(mockWebServer, 1)
        val nextWebPage = TestAssetHelper.getGenericAsset(mockWebServer, 2)

        // navigate to webpage and back to cache a browsing history
        // (for page forward test)

        navigationToolbar {
        }.enterUrlAndEnterToBrowser(defaultWebPage.url) {
            assertTestContent(defaultWebPage.content)
        }.openNavigationToolbar {
        }.enterUrlAndEnterToBrowser(nextWebPage.url) {
            assertTestContent(nextWebPage.content)
        }.openNavigationToolbar {
            mDevice.pressBack()
            mDevice.pressBack()
        }

        navigationToolbar {
        }.openThreeDotMenu {
            verifyThreeDotMenuExists()
        }.goForward {
            assertTestContent(nextWebPage.content)
        }
    }
    @Test
    fun threeDotMenuTest() {

        val defaultWebPage = TestAssetHelper.getGenericAsset(mockWebServer, 1)
        val nextWebPage = TestAssetHelper.getGenericAsset(mockWebServer, 2)
        val refreshWebPage = TestAssetHelper.getRefreshAsset(mockWebServer)

        navigationToolbar {
        }.enterUrlAndEnterToBrowser(defaultWebPage.url) {
            assertTestContent(defaultWebPage.content)
        }.openNavigationToolbar {
        }.enterUrlAndEnterToBrowser(nextWebPage.url) {
            assertTestContent(nextWebPage.content)
        }.openNavigationToolbar {
            mDevice.pressBack()
            mDevice.pressBack()
        }
        // BEGIN 3-DOT MENU TEST


        navigationToolbar {
        }.openThreeDotMenu {
            verifyThreeDotMenuExists()

        // page Forward test
        }.goForward {
            assertTestContent(nextWebPage.content)
        }.openNavigationToolbar {
        }.openThreeDotMenu {


        // page Refresh test
        }.refreshPage {
            //            assertPageRefresh()
        }.openThreeDotMenu {
        }.doStop {
        }.openThreeDotMenu {
        }.openShare {
            //            assertShareOverlay()
        }.openThreeDotMenu {
        }.requestDesktopSite {
        }.openThreeDotMenu {
        }.findInPage {
            //            assertFindInPage("Amazon")
//            assertNotFoundInPage("Google")
        }.openThreeDotMenu {
        }.reportIssue {
            //            assertReportIssuePage("https://github.com/mozilla-mobile/reference-browser/issues/new")
        }.openThreeDotMenu {
        }.openSettings {
            //            assertSettingsView()
//            exitSettings()
        }
    }

}
