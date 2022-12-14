package tech.eliseo.timetracker.domain.usecase

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.*
import tech.eliseo.timetracker.domain.model.CategoryIcon
import tech.eliseo.timetracker.domain.repository.CategoryRepository

@OptIn(ExperimentalCoroutinesApi::class)
class CreateCategoryUseCaseImplTest {

    @Test
    operator fun invoke() = runTest {
        val repository = mock(CategoryRepository::class.java)
        `when`(repository.createCategory("name", 0xFFfee440, CategoryIcon.DEFAULT)).thenReturn(Unit)
        val useCase: CreateCategoryUseCase = CreateCategoryUseCaseImpl(repository)
        useCase.invoke("name")
        verify(repository, times(1)).createCategory("name", 0xFFfee440, CategoryIcon.DEFAULT)
    }
}