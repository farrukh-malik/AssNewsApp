package com.example.farrukhmalik.aasnewsapp.AdminArea;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Farrukh Malik on 31/05/2017.
 */


public class AdminFragmentAdapter extends FragmentPagerAdapter {
    public AdminFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {


        switch (position){

            case 0:
                return new PostVideoNews();
            case 1:
                return new PostPictureNews();

            default:
                return null;

        }

    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){

            case 0:
                return "Post Video News";
            case 1:
                return "Post Other News";

            default:
                return null;

        }

    }

    @Override
    public int getCount() {
        return 2;
    }
}
