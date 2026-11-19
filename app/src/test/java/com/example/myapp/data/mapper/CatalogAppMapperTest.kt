package com.example.myapp.data.mapper

import com.example.myapp.data.dto.CatalogAppDto
import com.example.myapp.domain.model.AppInfo
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Test

class CatalogAppMapperTest {

    private val dto = CatalogAppDto(
        id = "quicknote",
        name = "QuickNote",
        description = "Приложение для заметок",
        category = "Производительность",
        iconUrl = "https://example.com/quicknote.png"
    )

    private val app = AppInfo(
        id = "quicknote",
        name = "QuickNote",
        description = "Приложение для заметок",
        appCategory = "Производительность",
        iconUrl = "https://example.com/quicknote.png"
    )

    @Test
    fun toDomain_mapsCatalogFields() {
        val result = dto.toDomain()

        assertEquals(app.id, result.id)
        assertEquals(app.name, result.name)
        assertEquals(app.description, result.description)
        assertEquals(app.appCategory, result.appCategory)
        assertEquals(app.iconUrl, result.iconUrl)
    }

    @Test
    fun toDomain_leavesMissingDetailsUnset() {
        val result = dto.toDomain()

        assertNull(result.developer)
        assertNull(result.ageRating)
        assertNull(result.size)
        assertNull(result.icon)
        assertTrue(result.screenshotUrls.isEmpty())
    }

    @Test
    fun toCatalogDto_mapsCatalogFields() {
        val result = app.toCatalogDto()

        assertEquals(dto, result)
    }

    @Test
    fun toCatalogDto_ignoresDetailsOutsideCatalog() {
        val appWithDetails = app.copy(
            developer = "QuickNote Team",
            screenshotUrls = listOf("https://example.com/screenshot.png")
        )

        assertEquals(dto, appWithDetails.toCatalogDto())
    }

    @Test
    fun toCatalogDto_throwsWhenIconUrlIsMissing() {
        val appWithoutIconUrl = app.copy(iconUrl = null)

        assertThrows(IllegalArgumentException::class.java) {
            appWithoutIconUrl.toCatalogDto()
        }
    }
}