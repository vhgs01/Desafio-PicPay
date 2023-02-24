package com.picpay.desafio.android.presentation.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.presentation.adapter.UserListAdapter
import com.picpay.desafio.android.presentation.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.recyclerView
import kotlinx.android.synthetic.main.list_item_user.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModel()

    private val usersAdapter by lazy { UserListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupView()
        setupObservers()

        viewModel.getUsers()
    }

    private fun setupView() {
        with(recyclerView) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = usersAdapter
        }

        progressBar?.visibility = View.VISIBLE
    }

    private fun setupObservers() {
        viewModel.successLiveData.observe(this, Observer { handleSuccess(it) })
        viewModel.failureLiveData.observe(this, Observer { handleFailure() })
    }

    private fun handleSuccess(response: List<User>) {
        progressBar?.visibility = View.GONE

        response.let {
            usersAdapter.users = it
        }
    }

    private fun handleFailure() {
        val message = getString(R.string.error)

        progressBar?.visibility = View.GONE
        recyclerView?.visibility = View.GONE

        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
            .show()
    }
}
