package com.example.notes.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.databinding.ViewDataBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext


abstract class BaseFragment(private val isBindingRequired: Boolean = false) : Fragment(),CoroutineScope {

    private lateinit var job: Job
    override val coroutineContext :CoroutineContext
    get() = job + Dispatchers.Main
    abstract fun initComponents()
    abstract fun getLayoutId(): Int
    protected lateinit var binder: ViewDataBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (isBindingRequired) {
            binder = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
            binder.root
        } else
            inflater.inflate(
                getLayoutId(), container, false
            )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()

    }

}