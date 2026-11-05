package com.example.myapp.ui.applist

import androidx.lifecycle.ViewModel
import com.example.myapp.domain.model.AppInfo
import com.example.myapp.domain.repository.AppsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AppsViewModel @Inject constructor(private val repository: AppsRepository) : ViewModel() {
    private val _apps = MutableStateFlow<List<AppInfo>>(repository.getApps())

    val apps = _apps.asStateFlow()
}