package com.techjd.a450dsacracker.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.techjd.a450dsacracker.R


class WebViewActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var swipe: SwipeRefreshLayout
    private lateinit var toolbar: Toolbar
    private lateinit var toolbar_title: TextView
    private lateinit var fab_main: FloatingActionButton
    private lateinit var fab1_mail: FloatingActionButton
    private lateinit var fab2_share: FloatingActionButton
    private lateinit var fab_open: Animation
    private lateinit var fab_close: Animation
    private lateinit var fab_clock: Animation
    private lateinit var fab_anti_clock: Animation
    private lateinit var textview_mail: TextView
    private lateinit var textview_share: TextView
    var isOpen: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_web_view)


        val topicName = intent.extras!!.getString("QUESTION")
        val url = intent.extras!!.getString("URL")



        toolbar = findViewById(R.id.tl)
        toolbar_title = findViewById(R.id.ttl)
        toolbar_title.text = topicName

        fab_main = findViewById(R.id.fab);
        fab1_mail = findViewById(R.id.fab1);
        fab2_share = findViewById(R.id.fab2);
        fab_close = AnimationUtils.loadAnimation(applicationContext, R.anim.fab_close);
        fab_open = AnimationUtils.loadAnimation(applicationContext, R.anim.fab_open);
        fab_clock = AnimationUtils.loadAnimation(applicationContext, R.anim.fab_rotate_clock);
        fab_anti_clock =
            AnimationUtils.loadAnimation(applicationContext, R.anim.fab_rotate_anti);

        textview_mail = findViewById(R.id.textview_mail);
        textview_share = findViewById(R.id.textview_share);

        fab_main.setOnClickListener {
            if (isOpen) {
                textview_mail.visibility = View.INVISIBLE
                textview_share.visibility = View.INVISIBLE
                fab2_share.startAnimation(fab_close)
                fab1_mail.startAnimation(fab_close)
                fab_main.startAnimation(fab_anti_clock)
                fab2_share.isClickable = false
                fab1_mail.isClickable = false
                isOpen = false
            } else {
                textview_mail.visibility = View.VISIBLE
                textview_share.visibility = View.VISIBLE
                fab2_share.startAnimation(fab_open)
                fab1_mail.startAnimation(fab_open)
                fab_main.startAnimation(fab_clock)
                fab2_share.isClickable = true
                fab1_mail.isClickable = true
                isOpen = true
            }
        }


        fab2_share.setOnClickListener {

            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"


            intent.putExtra(
                Intent.EXTRA_TEXT,
                """Check out this amazing App 450 DSA Cracker by Jaydeepsinh Parmar which helps you in tracking your progress of DS Algo Skills

Download this app now: 

https://play.google.com/store/apps/details?id=com.techjd.a450dsacracker"""
            )
            startActivity(Intent.createChooser(intent, "Send To"))

        }

        fab1_mail.setOnClickListener {
            val defaultBrowser =
                Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER)
            defaultBrowser.data = Uri.parse(url)
            startActivity(defaultBrowser)
        }


        swipe = findViewById<View>(R.id.swipe) as SwipeRefreshLayout
        swipe.setOnRefreshListener {
            if (url != null) {
                LoadWeb(url)
            }
        }

        if (url != null) {
            LoadWeb(url)
        }
    }

    fun LoadWeb(url: String) {

        webView = findViewById<View>(R.id.webview1) as WebView
        webView.settings.javaScriptEnabled = true
        val webSettings = webView.settings
        webView.loadUrl(url)
        swipe.isRefreshing = true
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {

                //Hide the SwipeReefreshLayout
                swipe.isRefreshing = false
            }
        }
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            finish()
        }
    }
}