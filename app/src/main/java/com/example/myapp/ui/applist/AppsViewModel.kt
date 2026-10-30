package com.example.myapp.ui.applist

import androidx.lifecycle.ViewModel
import com.example.myapp.domain.model.AppInfo
import com.example.myapp.domain.repository.AppsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppsViewModel(private val repository: AppsRepository) : ViewModel() {
    private val _apps = MutableStateFlow<List<AppInfo>>(repository.getApps())

    val apps = _apps.asStateFlow()
}