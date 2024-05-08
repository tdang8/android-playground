package com.example.compose.rally

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.hasAnyChild
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isSelectable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import com.example.compose.rally.ui.components.RallyTopAppBar
import com.example.compose.rally.ui.theme.RallyTheme
import org.junit.Rule
import org.junit.Test

class TopAppBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun rallyTopAppBarTest_currentTabSelected() {
        val allScreens = RallyScreen.entries
        composeTestRule.setContent {
            RallyTheme {
                RallyTopAppBar(
                    allScreens = allScreens,
                    onTabSelected = {},
                    currentScreen = RallyScreen.Accounts
                )
            }
        }
        composeTestRule.onNodeWithContentDescription(RallyScreen.Accounts.name).assertIsSelected()
    }

    @Test
    fun rallyTopAppBarTest_currentLabelExists() {
        val allScreens = RallyScreen.entries
        composeTestRule.setContent {
            RallyTheme {
                RallyTopAppBar(
                    allScreens = allScreens,
                    onTabSelected = {},
                    currentScreen = RallyScreen.Accounts
                )
            }
        }
//        composeTestRule.onRoot().printToLog("currentLabelExists")
//        composeTestRule.onNodeWithContentDescription(RallyScreen.Accounts.name)
//            .assertExists()

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("currentLabelExists")
        composeTestRule.onNode(
            useUnmergedTree = true,
            matcher = hasContentDescription(RallyScreen.Accounts.name) and hasAnyChild(
                hasText(RallyScreen.Accounts.name.uppercase())
            )
        ).assertExists()
    }

    val overviewTabNode = hasText(RallyScreen.Overview.name.uppercase()) and hasParent(hasContentDescription(RallyScreen.Overview.name))
    val accountsTabNode = hasText(RallyScreen.Accounts.name.uppercase()) and hasParent(hasContentDescription(RallyScreen.Accounts.name))
    val billTabNode = hasText(RallyScreen.Bills.name.uppercase()) and hasParent(hasContentDescription(RallyScreen.Bills.name))
    val accounts = hasContentDescription(RallyScreen.Accounts.name)

    @Test
    fun rallyTopAppBarTest_tabChangedOnClick() {

        //TODO: Finish this test

        composeTestRule.setContent {
            RallyTheme {
                RallyApp()
            }
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("tabChangedOnClick")
        composeTestRule.onNode(overviewTabNode, true).assertExists()
        composeTestRule.onNode(accounts).performClick()

    }

}


