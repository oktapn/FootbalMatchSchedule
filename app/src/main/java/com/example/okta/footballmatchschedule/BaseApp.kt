package com.example.okta.footballmatchschedule

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.okta.footballmatchschedule.deps.DaggerDeps
import com.example.okta.footballmatchschedule.deps.Deps
import com.example.okta.footballmatchschedule.networking.NetworkModule

import java.io.File

/**
 * Modified by okta on 9/03/18.
 */
open class BaseApp : AppCompatActivity() {
    lateinit var deps: Deps
        internal set

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cacheFile = File(cacheDir, "responses")
        deps = DaggerDeps.builder().networkModule(NetworkModule(cacheFile)).build()

    }
}
