package com.lib4.ngo.appbase.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;

import com.lib4.ngo.R;

/**
 * 
 * @author aabuback First Sample fragment. Class defines simple list view and
 *    loading of images using Aquery
 */
public class HomeFragment extends BaseFragment {

	
	private LinearLayout linearLayout;
	
	 /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 5;
    
    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;
    
    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null) {
			// We have different layouts, and in one of them this
			// fragment's containing frame doesn't exist. The fragment
			// may still be created from its saved state, but there is
			// no reason to try to create its view hierarchy because it
			// won't be displayed. Note this is not needed -- we could
			// just run the code below, where we would create and return
			// the view hierarchy; it would just never be used.
			return null;
		}

		// Inflate the layout for this fragment
		return (linearLayout = (LinearLayout) inflater.inflate(R.layout.home, container, false));
	}

	@Override
	public void onResume() {
		super.onResume();
		init();
	}

	/**
	 * Method to initialize all the UI element and start any network operation
	 * if required.
	 */
	private void init() {

		mPager = (ViewPager) linearLayout.findViewById(R.id.home_viewpager);
		 mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
	    mPager.setAdapter(mPagerAdapter);

	}

	/**
	 * Click listener for the dummyListview
	 */

	public OnItemClickListener mItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub

//			loadSecondFragment();
		}
	};

	/**
	 * Load second fragment
	 */

//	private void loadSecondFragment() {
//
//		SecondFragment mFirstFragment = new SecondFragment();
//		FragmentManager fragmentManager = getActivity()
//				.getSupportFragmentManager();
//		FragmentTransaction fragmentTransaction = fragmentManager
//				.beginTransaction();
//		fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit,
//				R.anim.pop_enter, R.anim.pop_exit);
//		// Replace whatever is in the fragment_container view with this
//		// fragment,
//		// and add the transaction to the back stack
//		fragmentTransaction.replace(R.id.contentpane, mFirstFragment);
//		fragmentTransaction.addToBackStack(null);
//		// Commit the transaction
//		fragmentTransaction.commit();
//
//	}
	
	
	  /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new GalleryFragment();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

}
