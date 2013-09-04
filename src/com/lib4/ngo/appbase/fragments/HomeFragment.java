package com.lib4.ngo.appbase.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lib4.ngo.R;
import com.lib4.ngo.appbase.activities.LauncherActivity;
import com.lib4.ngo.appbase.util.screenindicator.IndicatorView;

/**
 * 
 * @author aabuback First Sample fragment. Class defines simple list view and
 *    loading of images using Aquery
 */
public class HomeFragment extends BaseFragment {

	
	private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;
	private RelativeLayout linearLayout;
	
	
	IndicatorView mIndicatorView;

	LinearLayout contentpane;

	private int TOTAL_NUM_SCREENS = 6;
	private int CURRENT_SCREEN = 1;
	
	 /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 6;
    
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
		return (linearLayout = (RelativeLayout) inflater.inflate(R.layout.home, container, false));
	}

	@Override
	public void onResume() {
		super.onResume();
		init();
		((LauncherActivity)getActivity()).setItemSelected(0);
	}

	/**
	 * Method to initialize all the UI element and start any network operation
	 * if required.
	 */
	private void init() {
		
		

		mPager = (ViewPager) linearLayout.findViewById(R.id.home_viewpager);
		mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
	    mPager.setAdapter(mPagerAdapter);
	    
	    
	    gestureDetector = new GestureDetector(getActivity(), new CustomGestureDetector());
		gestureListener = new View.OnTouchListener() {
		    public boolean onTouch(View v, MotionEvent event) {


			return gestureDetector.onTouchEvent(event);
		    }
		};

		

		
		mPager.setOnTouchListener(gestureListener);
		mIndicatorView	=	(IndicatorView) linearLayout.findViewById(R.id.indicator);
		mIndicatorView.setContext(getActivity());
		mIndicatorView.setDrawables(R.drawable.tutorial_number_active,
				R.drawable.tutorial_bullets_bg,
				R.drawable.tutorial_number_inactive);
		CURRENT_SCREEN = 1;
		mIndicatorView.setNumberofScreens(TOTAL_NUM_SCREENS);
		mIndicatorView.switchToScreen(CURRENT_SCREEN, CURRENT_SCREEN);
		
	    
		mPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				Log.i("Arg0 ",""+ arg0);
				mIndicatorView.switchToScreen(CURRENT_SCREEN, arg0+1);
				CURRENT_SCREEN	=	arg0+1;
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
				
			}
		});
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
        	Bundle mBundle	=	new Bundle();
        	mBundle.putInt("position", position);
        	ImageFragment mFragment	=	new ImageFragment();
        	mFragment.setArguments(mBundle);
            return  mFragment;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
    
    
	
	/**
     * 
     * 
     * @author Anas Abubacker
     *         Right Left Gesture Detection
     * 
     */
    class CustomGestureDetector extends SimpleOnGestureListener {


	@Override
	public boolean onDown(MotionEvent event) {


	    return true;
	}
	@Override
	public boolean onDoubleTap(MotionEvent e){
		((LauncherActivity)getActivity()).sidebarToggle();
		return true;
	}

    }

}
