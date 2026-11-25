package com.example.myapp.ui.applist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.domain.model.AppInfo
import com.example.myapp.domain.repository.AppsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException
import kotlin.system.measureTimeMillis

@HiltViewModel
class AppsViewModel @Inject constructor(private val repository: AppsRepository) : ViewModel() {
    private val _apps = MutableStateFlow<List<AppInfo>>(emptyList())

    val apps = _apps.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    private val _ratings = MutableStateFlow<Map<String, Float>>(emptyMap())
    val ratings = _ratings.asStateFlow()

    private val _ratingsLoading = MutableStateFlow(false)
    val ratingsLoading = _ratingsLoading.asStateFlow()

    private val _ratingsResult = MutableStateFlow<String?>(null)
    val ratingsResult = _ratingsResult.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                _apps.value = repository.getApps()
                _error.value = null
                loadRatings(parallel = true)
            } catch (e: Exception) {
                if (e is CancellationException) throw e

                Log.e("AppsViewModel", "Failed to load catalog", e)
                _error.value = "Unable to load apps"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadRatings(parallel: Boolean) {
        if (_ratingsLoading.value || _apps.value.isEmpty()) return

        _ratingsLoading.value = true
        _ratingsResult.value = null

        viewModelScope.launch {
            try {
                val ids = _apps.value.map { it.id }
                var result: Map<String, Float> = emptyMap()

                val time = measureTimeMillis {
                    result = if (parallel) {
                        repository.loadRatingsInParallel(ids)
                    } else {
                        repository.loadRatingsSequentially(ids)
                    }
                }

                _ratings.value = result

                val method = if (parallel) "Parallel" else "Sequential"
                _ratingsResult.value = "$method: $time ms (${ids.size} apps)"

                Log.d("Ratings", "$method: $time ms")

            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e("Ratings", "Failed to load ratings", e)
                _ratingsResult.value = "Failed to load ratings"
            } finally {
                _ratingsLoading.value = false
            }
        }
    }
}