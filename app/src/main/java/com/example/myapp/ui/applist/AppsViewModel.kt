package com.example.myapp.ui.applist

import androidx.lifecycle.ViewModel
import com.example.myapp.model.AppInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.myapp.data.apps as initialApps

class AppsViewModel : ViewModel() {
    private val _apps = MutableStateFlow<List<AppInfo>>(initialApps)

    val apps = _apps.asStateFlow()
}