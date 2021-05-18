package com.ashish.covitrack.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.ashish.covitrack.R

class AboutUs : AppCompatActivity() {
    lateinit var gitHub:ImageView
    lateinit var faceBook:ImageView
    lateinit var linkedIn:ImageView
    lateinit var instagram:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)
        gitHub=findViewById(R.id.github)
        faceBook=findViewById(R.id.facebook)
        linkedIn=findViewById(R.id.linkedIn)
        instagram=findViewById(R.id.insta)

        gitHub.setOnClickListener {
            openGit()
        }

        faceBook.setOnClickListener {
            openFacebook()
        }

        linkedIn.setOnClickListener {
            openLinkedIn()
        }

        instagram.setOnClickListener {
            openInstagram()
        }
    }
    private fun openGit(){
        val url="https://github.com/Ash-333"
        val intent=Intent(Intent.ACTION_VIEW)
        intent.data= Uri.parse(url)
        startActivity(intent)
    }

    private fun openInstagram(){
        val url="https://www.instagram.com/ashishrajkamat"
        val intent=Intent(Intent.ACTION_VIEW)
        intent.data= Uri.parse(url)
        startActivity(intent)
    }

    private fun openLinkedIn(){
        val url="https://www.linkedin.com/in/ashish-kamat-54a8121a0/"
        val intent=Intent(Intent.ACTION_VIEW)
        intent.data= Uri.parse(url)
        startActivity(intent)
    }

    private fun openFacebook(){
        val url="https://www.facebook.com/ashishraj.kumarkamat/"
        val intent=Intent(Intent.ACTION_VIEW)
        intent.data= Uri.parse(url)
        startActivity(intent)
    }
}