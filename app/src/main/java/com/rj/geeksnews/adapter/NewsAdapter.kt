package com.rj.geeksnews.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.rj.geeksnews.databinding.NewsListBinding
import com.rj.geeksnews.model.NewsResponse

class NewsAdapter(context: Context):RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val list = ArrayList<NewsResponse>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.NewsViewHolder {
    return NewsViewHolder(NewsListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: NewsAdapter.NewsViewHolder, position: Int) {
        list.let {
            holder.setData(list[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
    inner class NewsViewHolder(var mBinding:NewsListBinding):RecyclerView.ViewHolder(mBinding.root){
        fun setData(item:NewsResponse){
            item.title.let{
                mBinding.tvNewsTitle.text = it
            }

            item.imgUrl.let {
                mBinding.pbImage.visibility = View.VISIBLE
                Glide.with(mBinding.ivNews.context)
                    .load(it)
                    .centerCrop()
                    .listener(object :RequestListener<Drawable>{
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                           mBinding.pbImage.visibility=View.GONE
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            mBinding.pbImage.visibility=View.GONE
                            return false
                        }

                    })
                    .into(mBinding.ivNews)
            }
        }
    }
}