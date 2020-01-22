package com.example.lenovo.emptypro.Activities

import android.app.Activity
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.lenovo.emptypro.R
import kotlinx.android.synthetic.main.activity_web_base_content.*
import kotlinx.android.synthetic.main.header_layout.*
import java.lang.Exception

class WebBaseContentActivity : AppCompatActivity() , View.OnClickListener {
var actionFor=""
    override fun onClick(v: View?) {
        when(v!!.id)
        {
            R.id.iv_back ->
            {
                finish()
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_base_content)
try{
    actionFor=intent.getStringExtra("for")
    if(actionFor.equals("terms"))
    {
showWebView()
    }
    else if(actionFor.equals("privacy"))
    {
        showWebView()
    }
    else if(actionFor.equals("about"))
    {
      //  showWebView()
    }
}
catch (exp: Exception)
{
Log.e("","")
}


    }

    private fun showWebView() {
        gif_imgVw!!.visibility=VISIBLE

        Handler().postDelayed(Runnable {
            gif_imgVw!!.visibility= GONE
        },2500)
        iv_back.setOnClickListener(this)
        webVw_help.webViewClient = MyWebViewClient(this)
        webVw_help.loadUrl("https://dev6.webnode.com/c-storm-a")

    }

    class MyWebViewClient internal constructor(private val activity: Activity) : WebViewClient() {

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            val url: String = request?.url.toString();
            view?.loadUrl(url)
            return true
        }

        override fun shouldOverrideUrlLoading(webView: WebView, url: String): Boolean {
            webView.loadUrl(url)
            return true
        }

        override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
            Toast.makeText(activity, "Got Error! $error", Toast.LENGTH_SHORT).show()
        }
    }

}
