package com.example.task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.task.databinding.ActivityAddRepoBinding
import com.example.task.viewmodel.RepoViewModel
import com.example.task.viewmodel.RepoViewModelFactory

class AddRepoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddRepoBinding
    private lateinit var viewModel: RepoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRepoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViews()
        setUpObservers()

    }
    private fun setUpViews(){
        viewModel = ViewModelProvider(
            this,
            RepoViewModelFactory((application as MyApp).repository)
        )[RepoViewModel::class.java]
        binding.toolbar.title.text = getString(R.string.add_repository)
        binding.toolbar.icAdd.visibility = View.GONE
        binding.buttonVerify.setOnClickListener {
            viewModel.insertRepo(
                binding.etOwnerName.text.toString(),
                binding.etRepoName.text.toString()
            )
        }
    }
    private fun setUpObservers(){
        viewModel.isSuccessful.observe(this) {
            if (it) {
                finish()
                Toast.makeText(this, "Repo Added Successfully !", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Repo already added or does not exist", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}