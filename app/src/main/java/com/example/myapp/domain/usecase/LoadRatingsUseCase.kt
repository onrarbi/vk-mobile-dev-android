package com.example.myapp.domain.usecase

import com.example.myapp.domain.repository.AppsRepository
import javax.inject.Inject

class LoadRatingsUseCase @Inject constructor(
    private val repository: AppsRepository
) {
    suspend operator fun invoke(
        ids: List<String>,
        parallel: Boolean
    ): Map<String, Float> {
        return if (parallel) {
            repository.loadRatingsInParallel(ids)
        } else {
            repository.loadRatingsSequentially(ids)
        }
    }
}