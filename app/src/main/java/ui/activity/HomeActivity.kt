package ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.upstocksapplication.R
import com.example.upstocksapplication.databinding.HomeActivityBinding
import ui.adapter.HomeAdapter
import viewmodels.HomeViewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var mViewModel: HomeViewModel
    private lateinit var mAdapter: HomeAdapter
    private var mBinding: HomeActivityBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.home_activity)
        mViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        initView()
        initAction()
        initObserver()
    }


    private fun showProgressBar(){
        mBinding?.rvContainer?.visibility = View.GONE
        mBinding?.bottomView?.visibility = View.GONE
        mBinding?.expandableView?.expandRoot?.visibility = View.GONE
        mBinding?.progressBar?.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        mBinding?.rvContainer?.visibility = View.VISIBLE
        mBinding?.bottomView?.visibility = View.VISIBLE
        mBinding?.expandableView?.expandRoot?.visibility = View.VISIBLE
        mBinding?.progressBar?.visibility = View.GONE
    }

    private fun initView() {
        showProgressBar()
        val recyclerView = mBinding?.rvContainer
        mAdapter = HomeAdapter()
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.adapter = mAdapter

    }

    private fun initAction(){
        mBinding?.expandableView?.expandButton?.setBackgroundResource(android.R.color.transparent)
        mBinding?.expandableView?.expandButton?.setOnClickListener {
            mBinding?.expandableView?.expandedView?.visibility = View.VISIBLE
            mBinding?.expandableView?.collapsedView?.visibility = View.GONE
            mBinding?.expandableView?.expandButton?.setBackgroundResource(android.R.color.transparent)

        }

        mBinding?.expandableView?.collapseButton?.setOnClickListener {
            mBinding?.expandableView?.collapsedView?.visibility = View.VISIBLE
            mBinding?.expandableView?.expandedView?.visibility = View.GONE
            mBinding?.expandableView?.collapseButton?.setBackgroundResource(android.R.color.transparent)
        }
    }

    private fun updateUI() {
        mBinding?.viewmodel = mViewModel
        mBinding?.expandableView?.viewmodel= mViewModel
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initObserver() {

            mViewModel.userHoldings.observe(this@HomeActivity) {
                if (!it.isNullOrEmpty()) {
                    hideProgressBar()
                    mViewModel.prepareUsrHolding()
                    mAdapter.setData(it)
                    updateUI()
                    mAdapter.notifyDataSetChanged()
                }
            }
    }
}