package tech.eliseo.timetracker.ui.screen.categorylist

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import tech.eliseo.timetracker.domain.model.Category
import tech.eliseo.timetracker.domain.model.CategoryIcon
import tech.eliseo.timetracker.domain.usecase.CreateCategoryUseCase
import tech.eliseo.timetracker.domain.usecase.GetCategoryListUseCase

@OptIn(ExperimentalCoroutinesApi::class)
class CategoryListViewModelTest {

    @Before
    fun setUp() {
        Dispatchers.setMain(TestCoroutineDispatcher())
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when screen is shown after loading state is Categories`() = runTest {
        val getCategoryListUseCase = mock(GetCategoryListUseCase::class.java)
        val createCategoryUseCase = mock(CreateCategoryUseCase::class.java)
        `when`(getCategoryListUseCase()).thenReturn(flow {
            emit(
                listOf(
                    Category(
                        id = 10,
                        title = "title",
                        color = 0xFFFFFF,
                        icon = CategoryIcon.DEFAULT
                    )
                )
            )
        })
        val viewModel = CategoryListViewModel(
            getCategoryListUseCase,
            createCategoryUseCase
        )
        assertEquals(1, viewModel.uiState.value.size)
        assertEquals(10, viewModel.uiState.value.first().id)
    }

    @Test
    fun `when click OnAddCategoryClicked it shows a dialog`() = runTest {

        val getCategoryListUseCase = mock(GetCategoryListUseCase::class.java)
        val createCategoryUseCase = mock(CreateCategoryUseCase::class.java)
        val viewModel = CategoryListViewModel(
            getCategoryListUseCase,
            createCategoryUseCase
        )
        viewModel.onUiEvent(CategoryListUiEvent.OnAddCategoryClicked)
        assertTrue(viewModel.categoryDialogOpenState.value)
    }

    @Test
    fun `when click OnRequestCloseDialog it hides a dialog`() = runTest {

        val getCategoryListUseCase = mock(GetCategoryListUseCase::class.java)
        val createCategoryUseCase = mock(CreateCategoryUseCase::class.java)
        val viewModel = CategoryListViewModel(
            getCategoryListUseCase,
            createCategoryUseCase
        )
        viewModel.categoryDialogOpenState.value = true

        viewModel.onUiEvent(CategoryListUiEvent.OnRequestCloseDialog)

        assertFalse(viewModel.categoryDialogOpenState.value)
    }

    @Test
    fun `when OnAddCategoryClicked it uses the usecase`() = runTest {

        val getCategoryListUseCase = mock(GetCategoryListUseCase::class.java)
        val createCategoryUseCase = mock(CreateCategoryUseCase::class.java)
        val viewModel = CategoryListViewModel(
            getCategoryListUseCase,
            createCategoryUseCase
        )
        viewModel.categoryDialogOpenState.value = true

        viewModel.onUiEvent(CategoryListUiEvent.OnConfirmNewCategoryClicked("name"))

        verify(createCategoryUseCase, times(1)).invoke("name")
        assertFalse(viewModel.categoryDialogOpenState.value)
    }
}