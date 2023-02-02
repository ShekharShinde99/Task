package com.example.task.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.task.repository.ReposRepository

class RepoViewModelFactory(private val reposRepository: ReposRepository):
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RepoViewModel(reposRepository) as T
    }
}