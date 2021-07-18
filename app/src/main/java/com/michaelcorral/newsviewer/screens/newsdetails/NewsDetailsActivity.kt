package com.michaelcorral.newsviewer.screens.newsdetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.michaelcorral.newsviewer.R
import com.michaelcorral.newsviewer.api.models.Article
import com.michaelcorral.newsviewer.application.GlideApp
import com.michaelcorral.newsviewer.base.MvvmActivity
import com.michaelcorral.newsviewer.utils.extensions.dateInFormat
import kotlinx.android.synthetic.main.activity_news_details.*
import kotlinx.android.synthetic.main.article_item.view.*
import timber.log.Timber

private const val ARTICLE_ITEM_KEY = "articleItemKey"

class NewsDetailsActivity : MvvmActivity() {

    private var article: Article? = null

    companion object {
        fun start(context: Context, article: Article) {
            val intent = Intent(context, NewsDetailsActivity::class.java)
            intent.putExtra(ARTICLE_ITEM_KEY, article)
            context.startActivity(intent)
        }
    }

    override fun getActivityLayout() = R.layout.activity_news_details

    override fun onActivityReady(savedInstanceState: Bundle?, intent: Intent) {
        super.onActivityReady(savedInstanceState, intent)

        fetchArticleFromIntent()
        displayArticleDetails()
    }

    private fun fetchArticleFromIntent() {
        article = intent.getParcelableExtra(ARTICLE_ITEM_KEY)
    }

    private fun displayArticleDetails() {
        newsDetailsTextViewTitle.text = article?.title
        newsDetailsTextViewDescription.text = article?.description
        newsDetailsTextViewAuthor.text =
            resources.getString(R.string.news_details_author_template, article?.author ?: "")
        newsDetailsTextViewPublishDate.text =
            resources.getString(R.string.news_details_published_date_template, article?.publishedAt?.split("T")
                ?.get(0)
                ?: "")

        GlideApp.with(this)
            .load(article?.urlToImage)
            .transform(RoundedCorners(16))
            .into(newsDetailsImageView)

        newsDetailsTextViewContent.text = article?.content
    }
}