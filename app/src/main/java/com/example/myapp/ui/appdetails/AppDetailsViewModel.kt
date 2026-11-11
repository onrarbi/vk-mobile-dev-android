package com.example.myapp.ui.appdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppDetailsViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow<AppDetailsState>(AppDetailsState.Content())

    val state = _state.asStateFlow()

    private var installJob: Job? = null

    fun onInstallClick() {
        val current = _state.value

        if (current !is AppDetailsState.Content) return

        if (current.installState is InstallState.Installing) {
            installJob?.cancel()
            installJob = null

            _state.value = current.copy(
                installState = InstallState.NotInstalled
            )
        } else if (current.installState is InstallState.NotInstalled) {
            _state.value = current.copy(
                installState = InstallState.Installing(0)
            )

            installJob = viewModelScope.launch {
                emulateInstall()
            }
        }
    }

    private suspend fun emulateInstall() {
        for (progress in 5..100 step 5) {
            delay(150)

            _state.update { current ->
                if (current is AppDetailsState.Content) {
                    current.copy(
                        installState = InstallState.Installing(progress)
                    )
                } else {
                    current
                }
            }
        }

        _state.update { current ->
            if (current is AppDetailsState.Content) {
                current.copy(
                    installState = InstallState.Installed
                )
            } else {
                current
            }
        }
    }
}