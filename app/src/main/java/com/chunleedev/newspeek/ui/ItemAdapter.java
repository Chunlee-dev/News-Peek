package com.chunleedev.newspeek.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chunleedev.newspeek.R;
import com.chunleedev.newspeek.databinding.ItemAdapterViewBinding;
import com.chunleedev.newspeek.parceables.Article;
import com.chunleedev.newspeek.parceables.Source;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ArticleHolder> {

    private final Context mContext;
    private final LayoutInflater mInflater;
    private ArrayList<Article> articleList;
    //private ArrayList<Source> sourceList;

    public ItemAdapter(Context context, ArrayList<Article> articleList) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        this.articleList = articleList;
        //this.sourceList = sourceList;
    }

    @NonNull
    @Override
    public ArticleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemAdapterViewBinding itemAdapterViewBinding = ItemAdapterViewBinding.inflate(mInflater, parent, false);
        return new ArticleHolder(itemAdapterViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleHolder holder, int position) {
        Article model = articleList.get(position);
        if (model != null) {
            holder.bind(model);
        }

        //Source source = sourceList.get(position);
        //holder.name.setText(source.getName());

        holder.options.setOnClickListener(v -> {
            // createOptions();
        });
        holder.star.setOnClickListener(v -> {
            //  markFav();
        });
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    class ArticleHolder extends RecyclerView.ViewHolder {

        TextView name, author, date, title, contentPeek;
        ImageView image, star, options;

        public ArticleHolder(ItemAdapterViewBinding itemAdapterViewBinding) {
            super(itemAdapterViewBinding.getRoot());

            name = itemAdapterViewBinding.articleName;
            author = itemAdapterViewBinding.articleAuthor;
            date = itemAdapterViewBinding.articleDate;
            title = itemAdapterViewBinding.articleTitle;
            contentPeek = itemAdapterViewBinding.articlePeek;

            image = itemAdapterViewBinding.image;
            star = itemAdapterViewBinding.star;
            options = itemAdapterViewBinding.menuOptionIcon;

        }

        void bind(Article currentModel) {
            name.setText(currentModel.getAuthor());
            author.setText(currentModel.getAuthor());
            date.setText(currentModel.getPublishedAt());
            title.setText(currentModel.getTitle());
            contentPeek.setText(currentModel.getDescription());
            Glide
                    .with(mContext)
                    .load(currentModel.getUrlToImage())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .placeholder(R.color.toolbar_trans_color)
                    .into(image);
        }

    }
}
