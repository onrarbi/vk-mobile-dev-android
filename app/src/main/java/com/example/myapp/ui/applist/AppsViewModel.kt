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

@HiltViewModel
class AppsViewModel @Inject constructor(private val repository: AppsRepository) : ViewModel() {
    private val _apps = MutableStateFlow<List<AppInfo>>(emptyList())

    val apps = _apps.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                _apps.value = repository.getApps()
                _error.value = null
            } catch (e: Exception) {
                if (e is CancellationException) throw e

                Log.e("AppsViewModel", "Failed to load catalog", e)
                _error.value = "Unable to load apps"
            } finally {
                _isLoading.value = false
            }
        }
    }
}