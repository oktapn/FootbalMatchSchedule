package com.example.okta.footballmatchschedule

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.okta.footballmatchschedule.deps.DaggerDeps
import com.example.okta.footballmatchschedule.deps.Deps
import com.example.okta.footballmatchschedule.networking.NetworkModule

import java.io.File

open class BaseAppFragment : Fragment() {
    lateinit var deps: Deps
        internal set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cacheFile = File(context?.cacheDir, "responses")
        deps = DaggerDeps.builder().networkModule(NetworkModule(cacheFile)).build()
    }
}
