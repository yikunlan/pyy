package com.huang.pyy.main;

import android.annotation.SuppressLint;
import android.support.annotation.DrawableRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huang.pyy.R;
import com.huang.pyy.main.fragement.MainFragment;

public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private String[] TITLES;
    private int[] mNornalImgResouce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        TabLayout mTablayout = (TabLayout) findViewById(R.id.timeline_tablayout);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mTablayout.setupWithViewPager(mViewPager);

        //添加标题和图标
        for(int i = 0;i<mTablayout.getTabCount();i++){
            mTablayout.getTabAt(i).setCustomView(getTabView(i));
        }
        mTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView textView = tab.getCustomView().findViewById(R.id.tab_text);
                textView.setTextColor(getResources().getColor(R.color.content_color));
                ((ImageView)(tab.getCustomView().findViewById(R.id.tab_icon))).setImageResource(R.drawable.ic_launcher_background);
            }

            @SuppressLint("ResourceAsColor")
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
//                ((TextView)(tab.getCustomView().findViewById(R.id.tab_text))).setTextColor(R.color.colorAccent);
                ((ImageView)(tab.getCustomView().findViewById(R.id.tab_icon))).setImageResource(R.drawable.ic_launcher_background);
                TextView textView = tab.getCustomView().findViewById(R.id.tab_text);
                textView.setTextColor(getResources().getColor(R.color.colorAccent));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void init(){
        TITLES = new String[]{getString(R.string.apply_on_line), getString(R.string.repair_borrom), getString(R.string.search_credit), getString(R.string.main)};
        mNornalImgResouce = new int[]{R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tab, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position){
                case 0:
                    MainFragment mainFragment = new MainFragment();
                    return mainFragment;
//                    break;
                default:
                    break;
            }
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }
//        @Override
//        public CharSequence getPageTitle(int position) {
//            Drawable drawable;
//            String title;
//            switch (position) {
//                case 0:
//                    drawable = ContextCompat.getDrawable(TabActivity.this, R.drawable.a1);
//                    title = "广场";
//                    break;
//                case 1:
//                    drawable = ContextCompat.getDrawable(TabActivity.this, R.drawable.a2);
//                    title = "好友";
//                    break;
//                case 2:
//                    drawable = ContextCompat.getDrawable(TabActivity.this, R.drawable.a3);
//                    title = "我";
//                    break;
//                default:
//                    drawable = ContextCompat.getDrawable(TabActivity.this, R.drawable.a4);
//                    title = "微博";
//                    break;
//            }
//
////            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//            ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM);
//            SpannableString spannableString = new SpannableString(" " + title);
//            spannableString.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//            return spannableString;
//
//        }
    }


    private View getTabView(int position){
        View view = LayoutInflater.from(this).inflate(R.layout.tab_itme, null);
        TextView txt_title = (TextView) view.findViewById(R.id.tab_text);

        ImageView img_title = (ImageView) view.findViewById(R.id.tab_icon);

        txt_title.setText(TITLES[position]);
        img_title.setImageResource(mNornalImgResouce[position]);
        return view;

    }
}
