package com.michaelcorral.newsviewer.screens.newslist

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.michaelcorral.newsviewer.R
import com.michaelcorral.newsviewer.api.models.Article
import com.michaelcorral.newsviewer.base.MvvmActivity
import com.michaelcorral.newsviewer.screens.newsdetails.NewsDetailsActivity
import com.michaelcorral.newsviewer.utils.LoadingState
import kotlinx.android.synthetic.main.activity_newslist.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.RuntimeException


class NewsListActivity : MvvmActivity() {

    private val newsListViewModel by viewModel<NewsListViewModel>()

    override fun getActivityLayout() = R.layout.activity_newslist
    override fun getActivityViewModel() = newsListViewModel

    override fun onActivityReady(savedInstanceState: Bundle?, intent: Intent) {
        super.onActivityReady(savedInstanceState, intent)

        displaySourceCountryDropdown()
        displayNewsList()
        setupSwipeRefreshLayout()
        displayState()

    }

    private fun displaySourceCountryDropdown() {
        ArrayAdapter.createFromResource(
            this,
            R.array.source_countries,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            newsListSpinner.adapter = adapter
        }

        setDropdown()
    }

    private fun setDropdown() {
        newsListViewModel.sourceCountrySpinnerPosition.observe(this, {
            newsListSpinner.setSelection(newsListViewModel.sourceCountrySpinnerPosition.value ?: 0,false)
            onSpinnerItemSelected()
        })
    }

    private fun onSpinnerItemSelected() {
        newsListSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                newsListViewModel.saveSourceCountry(parent?.getItemAtPosition(position).toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }

    private fun displayNewsList() {
        newsListViewModel.articles.observe(this, {
            newsListRecyclerview.layoutManager = LinearLayoutManager(this)

            newsListRecyclerview.adapter =
                NewsListAdapter(
                    articles = it,
                    onArticleClick = { item -> onArticleClick(item) })
        })
    }

    private fun setupSwipeRefreshLayout() {
        newsListSwipeRefreshLayout.setOnRefreshListener {
            newsListViewModel.refreshArticles()
        }

        newsListViewModel.refreshState.observe(this, {
            newsListSwipeRefreshLayout.isRefreshing = false
        })
    }

    private fun onArticleClick(article: Article) {
        redirectToNewsDetails(article)
    }

    private fun redirectToNewsDetails(article: Article) {
        NewsDetailsActivity.start(this, article)
    }

    private fun displayState() {
        newsListViewModel.loadingState.observe(this, {
            when (it.status) {
                LoadingState.Status.FAILED -> showSnackBar(newsListConstraintLayoutParent, it.msg ?: getString(
                    R.string.error_template), Snackbar.LENGTH_SHORT)
                LoadingState.Status.RUNNING -> showLoadingDialog()
                LoadingState.Status.SUCCESS -> hideLoadingDialog()
            }
        })
    }
}

