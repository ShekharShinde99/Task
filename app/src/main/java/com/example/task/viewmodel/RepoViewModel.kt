package com.example.task.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task.model.Repo
import com.example.task.repository.ReposRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RepoViewModel(private val reposRepository: ReposRepository): ViewModel() {

    val repoList: LiveData<List<Repo>>
        get() = reposRepository.db.roomDao().getRepos()

    private val _isSuccessful = MutableLiveData<Boolean>()

    val isSuccessful: LiveData<Boolean> = _isSuccessful



    fun insertRepo(owner: String, repo: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isSuccessful.postValue(reposRepository.getRepo("https://api.github.com/repos/$owner/$repo"))
        }
    }

}