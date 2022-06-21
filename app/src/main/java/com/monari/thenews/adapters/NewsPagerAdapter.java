package com.monari.thenews.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;

import com.monari.thenews.fragments.NewsDetailFragment;
import com.monari.thenews.models.Article;

import java.util.List;

public class NewsPagerAdapter extends FragmentPagerAdapter {
    private List<Article> mNews;

    public NewsPagerAdapter(@NonNull FragmentManager fm, int behavior, List<Article> articles) {
        super(fm, behavior);
        mNews = articles;
    }

    @Override
    public Fragment getItem(int position) {
        return NewsDetailFragment.newInstance(mNews.get(position));
    }

    @Override
    public int getCount() {
        return mNews.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mNews.get(position).getTitle();
    }
}
