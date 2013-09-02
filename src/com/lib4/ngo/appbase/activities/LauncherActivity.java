/**
 * 
 * 
 */

package com.lib4.ngo.appbase.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lib4.ngo.R;
import com.lib4.ngo.appbase.datastorage.DBManager;
import com.lib4.ngo.appbase.fragments.HomeFragment;

/**
 * 
 * @author aabuback Launcher class to simulate the Server request and image
 *         downloading part. Activity making use of open source volley network
 *         framework http request and response and Aquery framework for image
 *         downloading.
 * 
 */

public class LauncherActivity extends BaseActivity {

	Animation animationSlideInLeft, animationSlideOutLeft;

	FrameLayout mFrameLayout;
	LinearLayout sidebar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mFrameLayout = (FrameLayout) findViewById(R.id.parenview);

		sidebar = (LinearLayout) findViewById(R.id.sidebar);

		InitializeAnimation(this);

		loadHomeFragment();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * Load the first fragment
	 * 
	 */

	private void loadHomeFragment() {

		HomeFragment mFirstFragment = new HomeFragment();
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		// Replace whatever is in the fragment_container view with this
		// fragment,
		// and add the transaction to the back stack
		fragmentTransaction.replace(R.id.contentpane, mFirstFragment);
		// Commit the transaction
		fragmentTransaction.commit();

	}

	/**
	 * Insert and fetch the Comments from local data storage
	 */

	private void dumpdataandget() {
		DBManager appDbManager = new DBManager(this);
		appDbManager.open();
		appDbManager.insertComments();
		appDbManager.fetchComments().close();
		appDbManager.close();
	}

	public void sidebarToggle() {
		if (sidebar.getVisibility() == View.VISIBLE) {
			hideSidebar();
		} else {
			showSidebar();
		}
	}

	/**
	 * 
	 * Animation Sidebar
	 */

	public void showSidebar() {

		sidebar.startAnimation(animationSlideInLeft);
	}

	public void hideSidebar() {

		sidebar.startAnimation(animationSlideOutLeft);
	}

	private void InitializeAnimation(Context context) {
		animationSlideInLeft = AnimationUtils.loadAnimation(context,
				R.anim.left_right);
		animationSlideOutLeft = AnimationUtils.loadAnimation(context,
				R.anim.right_left);
		animationSlideInLeft.setDuration(1000);
		animationSlideOutLeft.setDuration(1000);
		animationSlideOutLeft.setFillAfter(true);

		animationSlideInLeft.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				sidebar.setVisibility(View.VISIBLE);
			}
		});

		animationSlideOutLeft.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				sidebar.setVisibility(View.GONE);
			}
		});

	}

}
