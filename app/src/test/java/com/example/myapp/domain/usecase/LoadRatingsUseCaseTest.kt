package com.example.myapp.domain.usecase

import com.example.myapp.domain.model.AppInfo
import com.example.myapp.domain.repository.AppsRepository
import java.io.IOException
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Assert.assertThrows
import org.junit.Test

class LoadRatingsUseCaseTest {

    private val repository = FakeAppsRepository()
    private val useCase = LoadRatingsUseCase(repository)

    @Test
    fun invoke_usesOnlySequentialMethod_whenParallelIsFalse() {
        runBlocking {
            useCase(listOf("quicknote"), parallel = false)

            assertEquals(1, repository.sequentialCalls)
            assertEquals(0, repository.parallelCalls)
        }
    }

    @Test
    fun invoke_usesOnlyParallelMethod_whenParallelIsTrue() {
        runBlocking {
            useCase(listOf("quicknote"), parallel = true)

            assertEquals(0, repository.sequentialCalls)
            assertEquals(1, repository.parallelCalls)
        }
    }

    @Test
    fun invoke_passesIdsInOriginalOrder() {
        runBlocking {
            val ids = listOf("fitriver", "quicknote", "calmalist")

            useCase(ids, parallel = true)

            assertEquals(ids, repository.lastIds)
        }
    }

    @Test
    fun invoke_returnsRatingsFromRepository() {
        runBlocking {
            val expected = mapOf("quicknote" to 4.6f, "fitriver" to 3.8f)
            repository.ratings = expected

            val result = useCase(listOf("quicknote", "fitriver"), parallel = false)

            assertEquals(expected, result)
        }
    }

    @Test
    fun invoke_propagatesRepositoryException() {
        val expected = IOException("Ratings unavailable")
        repository.failure = expected

        val actual = assertThrows(IOException::class.java) {
            runBlocking { useCase(listOf("quicknote"), parallel = true) }
        }

        assertSame(expected, actual)
    }

    private class FakeAppsRepository : AppsRepository {
        var sequentialCalls = 0
            private set
        var parallelCalls = 0
            private set
        var lastIds: List<String>? = null
            private set

        var ratings: Map<String, Float> = emptyMap()
        var failure: IOException? = null

        override suspend fun loadRatingsSequentially(ids: List<String>): Map<String, Float> {
            sequentialCalls++
            return respond(ids)
        }

        override suspend fun loadRatingsInParallel(ids: List<String>): Map<String, Float> {
            parallelCalls++
            return respond(ids)
        }

        private fun respond(ids: List<String>): Map<String, Float> {
            lastIds = ids
            failure?.let { throw it }
            return ratings
        }

        override suspend fun getApps(): List<AppInfo> =
            error("getApps() should not be called in these tests")

        override suspend fun getRating(id: String): Float =
            error("getRating() should not be called in these tests")
    }
}