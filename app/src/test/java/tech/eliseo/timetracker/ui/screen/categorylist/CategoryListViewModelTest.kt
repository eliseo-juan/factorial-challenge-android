package tech.eliseo.timetracker.ui.screen.categorylist

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import tech.eliseo.timetracker.domain.model.Category
import tech.eliseo.timetracker.domain.model.CategoryIcon
import tech.eliseo.timetracker.domain.usecase.CreateCategoryUseCase
import tech.eliseo.timetracker.domain.usecase.GetCategoryListUseCase

class CategoryListViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when screen is shown first state is Loading`() = runTest {
        val getCategoryListUseCase = mock(GetCategoryListUseCase::class.java)
        val createCategoryUseCase = mock(CreateCategoryUseCase::class.java)
        val channel = Channel<List<Category>>()
        val flow = channel.consumeAsFlow()
        `when`(getCategoryListUseCase()).thenReturn(flow)
        val viewModel = CategoryListViewModel(
            getCategoryListUseCase,
            createCategoryUseCase
        )
        launch {
            channel.send(emptyList())
        }
        assertEquals(viewModel.uiState.first(), CategoryListUiState.Loading)
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
        assertEquals(
            (viewModel.uiState.drop(1).first() as CategoryListUiState.Success).list.first().id,
            10
        )
    }

    @Test
    fun `if OnAddCategoryClicked`() = runTest {

        val getCategoryListUseCase = mock(GetCategoryListUseCase::class.java)
        val createCategoryUseCase = mock(CreateCategoryUseCase::class.java)
        `when`(getCategoryListUseCase()).thenReturn(flow {
            emit(emptyList())
        })
        val viewModel = CategoryListViewModel(
            getCategoryListUseCase,
            createCategoryUseCase
        )
        viewModel.onUiEvent(CategoryListUiEvent.OnAddCategoryClicked)

        assertTrue((viewModel.uiState.value as CategoryListUiState.Success).addCategoryDialogOpen)
    }


}