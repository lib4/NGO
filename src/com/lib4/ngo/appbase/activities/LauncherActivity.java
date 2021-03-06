/**
 * 
 * 
 */

package com.lib4.ngo.appbase.activities;

import java.util.concurrent.ConcurrentHashMap;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.lib4.ngo.R;
import com.lib4.ngo.appbase.adapters.Sidebar_Adapter;
import com.lib4.ngo.appbase.datastorage.DBManager;
import com.lib4.ngo.appbase.fragments.ContactUsFragment;
import com.lib4.ngo.appbase.fragments.GetInvolvedFragment;
import com.lib4.ngo.appbase.fragments.HomeFragment;
import com.lib4.ngo.appbase.fragments.NewsFragment;
import com.lib4.ngo.appbase.fragments.WhatWeDoFragment;
import com.lib4.ngo.appbase.fragments.WhoWeAreFragment;

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
	public static LauncherActivity mLauncherActivity;
	
	public LinearLayout sidebar;
	ListView sidebarlistView;

	Sidebar_Adapter mSidebar_Adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mLauncherActivity	=	this;
		mFrameLayout = (FrameLayout) findViewById(R.id.parenview);

		sidebar = (LinearLayout) findViewById(R.id.sidebar);

		sidebarlistView = (ListView) sidebar.findViewById(R.id.sidelistview);
		mSidebar_Adapter = new Sidebar_Adapter(this);
		sidebarlistView.setAdapter(mSidebar_Adapter);
		sidebarlistView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Log.i("Item",""+ arg2);
				Fragment mFragment	=	new HomeFragment();
				switch(arg2){
				case 0:
					mFragment	=	new HomeFragment();
					setTitle("Home");
					break;
				case 1:
					mFragment	=	new WhoWeAreFragment();
					setTitle("Who We Are");
					break;
				case 2:
					mFragment	=	new WhatWeDoFragment();
					setTitle("What We Do");
					break;
				case 3:
					mFragment	=	new GetInvolvedFragment();
					setTitle("Get Involved");
					break;
				case 4:
					mFragment	=	new ContactUsFragment();
					setTitle("Contact Us");
					break;
				case 5:
					mFragment	=	new HomeFragment();
					setTitle("News");
					break;
				}
				setItemSelected(arg2);
				loadFragment(mFragment);
				
				hideSidebar();
			}
			
		});

			
	
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
		//animationSlideOutLeft.setFillAfter(true);

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
	
	public void setItemSelected(int position){
		mSidebar_Adapter.setitemPressed(position);
		mSidebar_Adapter.notifyDataSetChanged();
		
	}
	
	
	public void loadFragment(Fragment mFragment){
	
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		fragmentTransaction.setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit);
		// Replace whatever is in the fragment_container view with this
		// fragment,
		// and add the transaction to the back stack
		fragmentTransaction.replace(R.id.contentpane, mFragment);
		// Commit the transaction
		fragmentTransaction.commit();

	}

}
