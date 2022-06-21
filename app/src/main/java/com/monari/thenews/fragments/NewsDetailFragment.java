package com.monari.thenews.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.monari.thenews.R;
import com.monari.thenews.models.Article;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;



import butterknife.BindView;
import butterknife.ButterKnife;


public class NewsDetailFragment extends Fragment {
    @BindView(R.id.news_image)
    ImageView mNewsImage;
    @BindView(R.id.save)
    Button mSave;
    @BindView(R.id.publishDate)
    TextView mPublishDate;
    @BindView(R.id.news_source) TextView mNewsSource;
    @BindView(R.id.news_title) TextView mNewsTitle;
    @BindView(R.id.news_desc) TextView mNewsDesc;
    @BindView(R.id.news_content) TextView mNewsContent;



    private Article mNews;


    public NewsDetailFragment() {
        // Required empty public constructor
    }

    public static NewsDetailFragment newInstance(Article article) {
        NewsDetailFragment newsDetailFragment = new NewsDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("article", Parcels.wrap(article));
        newsDetailFragment.setArguments(args);
        return newsDetailFragment;
    }




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        mNews = Parcels.unwrap(getArguments().getParcelable("article"));

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_news_detail, container, false);
        ButterKnife.bind(this, view);
        Picasso.get().load(mNews.getUrlToImage()).into(mNewsImage);
        mPublishDate.setText(mNews.getPublishedAt());
        mNewsSource.setText(mNews.getSource().getName());
        mNewsTitle.setText(mNews.getTitle());
        mNewsDesc.setText(mNews.getDescription());
        mNewsContent.setText(mNews.getContent());

        return view;
    }
}