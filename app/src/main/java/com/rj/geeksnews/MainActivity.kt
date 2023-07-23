package com.rj.geeksnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rj.geeksnews.adapter.CategoryAdapter
import com.rj.geeksnews.adapter.NewsAdapter
import com.rj.geeksnews.databinding.ActivityMainBinding
import com.rj.geeksnews.model.CategoryResponse

class MainActivity : AppCompatActivity(),CategoryAdapter.CategoryClickListener {
    private lateinit var mBinding:ActivityMainBinding
    private var mViewModel:NewsViewModel?=null
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var categoryList:ArrayList<CategoryResponse>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        categoryList = ArrayList()
        newsAdapter = NewsAdapter(this)
        categoryAdapter = CategoryAdapter(this,categoryList)

        mViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        mBinding.categoryList.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        mBinding.categoryList.adapter = categoryAdapter

        mBinding.rvNewList.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        mBinding.rvNewList.adapter = newsAdapter

        setData()
    }

    override fun onTabChange(tabId: Int?) {
        TODO("Not yet implemented")
    }

    private fun setData(){
        categoryList.add(CategoryResponse(1,"Top Heading",true))
        categoryList.add(CategoryResponse(2,"Top Business"))
        categoryList.add(CategoryResponse(3,"Bitcoin"))
        categoryList.add(CategoryResponse(4,"Android"))
        categoryList.add(CategoryResponse(5,"Technology"))


    }
}