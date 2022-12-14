package tech.eliseo.timetracker.ui.screen.categorylist

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import tech.eliseo.timetracker.domain.model.Category
import tech.eliseo.timetracker.domain.model.CategoryIcon

@RunWith(AndroidJUnit4::class)
class CategoryListScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setup() {
        composeTestRule.setContent {
            CategoryListScreen(
                list = listOf(
                    Category(
                        id = 1,
                        title = "Trabajo",
                        color = 0xFF00f5d4,
                        icon = CategoryIcon.WORK
                    ),
                    Category(
                        id = 2,
                        title = "Deporte",
                        color = 0xFF00bbf9,
                        icon = CategoryIcon.WORKOUT
                    ),
                    Category(
                        id = 3,
                        title = "Entretenimiento",
                        color = 0xFFf15bb5,
                        icon = CategoryIcon.ENTERTAINMENT
                    )
                ),
                addCategoryDialogOpen = false
            )
        }
    }

    @Test
    fun checkListIsShownWithThreeElements() {
        composeTestRule
            .onNodeWithText("Trabajo").assertExists()
        composeTestRule
            .onNodeWithText("Deporte").assertExists()
        composeTestRule
            .onNodeWithText("Entretenimiento").assertExists()
    }
}