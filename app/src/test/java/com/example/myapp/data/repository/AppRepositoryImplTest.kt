package com.example.myapp.data.repository

import com.example.myapp.data.dto.CatalogAppDto
import com.example.myapp.data.remote.CatalogApi
import com.example.myapp.domain.model.AppInfo
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.IOException
import kotlin.test.assertEquals
import kotlin.test.assertSame

class AppRepositoryImplTest {
    @Test
    fun getApps_returnEmptyList_whenCatalogIsEmpty() {
        runBlocking {
            val fakeApi = object : CatalogApi {
                override suspend fun getCatalog(): List<CatalogAppDto> =
                    emptyList()

                override suspend fun getAppById(id: String): CatalogAppDto =
                    error("This method should not be called in this test")
            }

            val repository = AppRepositoryImpl(fakeApi)
            val result = repository.getApps()
            kotlin.test.assertTrue(result.isEmpty())
        }
    }

    @Test
    fun getApps_mapsCatalogAndPreservesOrder() {
        runBlocking {
            val firstDto = CatalogAppDto(
                id = "quicknote",
                name = "QuickNote",
                description = "Приложение для заметок",
                category = "Производительность",
                iconUrl = "https://example.com/quicknote.png"
            )
            val secondDto = firstDto.copy(id = "calmalist", name = "Calmalist")
            val repository = AppRepositoryImpl(
                fakeApi { listOf(firstDto, secondDto) }
            )

            val result = repository.getApps()

            val firstExpected = AppInfo(
                id = "quicknote",
                name = "QuickNote",
                description = "Приложение для заметок",
                appCategory = "Производительность",
                iconUrl = "https://example.com/quicknote.png"
            )
            val secondExpected = firstExpected.copy(id = "calmalist", name = "Calmalist")
            assertEquals(listOf(firstExpected, secondExpected), result)
        }
    }

    @Test
    fun getApps_propagatesApiException() {
        val expected = okio.IOException("Offline")
        val repository = AppRepositoryImpl(fakeApi { throw expected })

        val actual = assertThrows(IOException::class.java) {
            runBlocking { repository.getApps() }
        }

        assertSame(expected, actual)
    }

    @Test
    fun loadRatingsSequentially_loadsThreeRatingsIn4500Ms() {
        runTest {
            val repository = AppRepositoryImpl(fakeApi())
            val ids = listOf("quicknote", "calmalist", "fitriver")
            val start = testScheduler.currentTime

            val ratings = repository.loadRatingsSequentially(ids)

            assertEquals(ids.toSet(), ratings.keys)
            assertTrue(ratings.values.all { it in 3.0f..5.0f })
            assertEquals(4500L, testScheduler.currentTime - start)
        }
    }

    @Test
    fun loadRatingsInParallel_loadsThreeRatingsIn1500Ms() {
        runTest {
            val repository = AppRepositoryImpl(fakeApi())
            val ids = listOf("quicknote", "calmalist", "fitriver")
            val start = testScheduler.currentTime

            val ratings = repository.loadRatingsInParallel(ids)

            assertEquals(ids.toSet(), ratings.keys)
            assertTrue(ratings.values.all { it in 3.0f..5.0f })
            assertEquals(1500L, testScheduler.currentTime - start)
        }
    }

    private fun fakeApi(
        catalog: suspend () -> List<CatalogAppDto> = {
            error("getCatalog() should not be called in this test")
        }
    ): CatalogApi = object : CatalogApi {
        override suspend fun getCatalog(): List<CatalogAppDto> = catalog()

        override suspend fun getAppById(id: String): CatalogAppDto =
            error("getAppById() should not be called in this test")
    }
}