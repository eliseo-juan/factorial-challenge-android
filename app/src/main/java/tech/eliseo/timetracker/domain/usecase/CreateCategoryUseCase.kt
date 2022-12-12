package tech.eliseo.timetracker.domain.usecase

interface CreateCategoryUseCase {
    suspend operator fun invoke(name: String)
}