package com.example.task

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task.adapters.RepoAdapter
import com.example.task.databinding.ActivityMainBinding
import com.example.task.model.Repo
import com.example.task.viewmodel.RepoViewModel
import com.example.task.viewmodel.RepoViewModelFactory

class MainActivity : AppCompatActivity(), RepoAdapter.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: RepoViewModel
    private lateinit var adapter: RepoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViews()
        setUpObservers()
    }

    override fun onClick(repo: Repo, position: Int) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,repo.html_url)
        startActivity(Intent.createChooser(intent, null))
    }

    private fun setUpViews(){
        viewModel = ViewModelProvider(
            this,
            RepoViewModelFactory((application as MyApp).repository)
        )[RepoViewModel::class.java]

        adapter = RepoAdapter(this,this)
        val verticalDivider =
            ContextCompat.getDrawable(this,R.drawable.divider)
        val verticalDecoration = DividerItemDecoration(
            this,
            DividerItemDecoration.VERTICAL
        )
        verticalDecoration.setDrawable(verticalDivider!!)
        binding.rvRepo.addItemDecoration(verticalDecoration)
        binding.rvRepo.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.rvRepo.adapter = adapter
        binding.toolbar.icAdd.setOnClickListener{
            startActivity(Intent(this@MainActivity,AddRepoActivity::class.java))
        }
        binding.btnAdd.setOnClickListener{
            startActivity(Intent(this@MainActivity,AddRepoActivity::class.java))
        }
    }

    private fun setUpObservers(){
        viewModel.repoList.observe(this) {
            if(it.isNotEmpty()) {
                binding.tvMsg.visibility = View.GONE
                binding.btnAdd.visibility = View.GONE
                adapter.submitList(it)
            }
            else {
                binding.tvMsg.visibility = View.VISIBLE
                binding.btnAdd.visibility = View.VISIBLE
            }
        }
    }
}