/**
 * 
 * 
 */

package com.lib4.ngo.appbase.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
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

	private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;
    
    
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;


    Animation animationSlideInLeft, animationSlideOutLeft;
    
    
    FrameLayout mFrameLayout;
    LinearLayout sidebar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		gestureDetector = new GestureDetector(this, new CustomGestureDetector());
		gestureListener = new View.OnTouchListener() {
		    public boolean onTouch(View v, MotionEvent event) {


			return gestureDetector.onTouchEvent(event);
		    }
		};

		
		mFrameLayout	=	(FrameLayout) findViewById(R.id.parenview);
		
		mFrameLayout.setOnTouchListener(gestureListener);
		 
		
		sidebar	=	(LinearLayout) findViewById(R.id.sidebar);
		
		InitializeAnimation(this);
		
		loadHomeFragment();
		//dumpdataandget();
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
	
	private void dumpdataandget(){
		DBManager appDbManager	=	new DBManager(this);
		appDbManager.open();
		appDbManager.insertComments();
		appDbManager.fetchComments().close();
		appDbManager.close();
	}
	
	
	/**
	 * 
	 * Animation Sidebar 
	 */
	
	 public void showSidebar(){
		 
		 sidebar.startAnimation(animationSlideInLeft);
	 }
	 
	 public void hideSidebar(){
		 
		 sidebar.startAnimation(animationSlideOutLeft);
	 }
	 


	    private void InitializeAnimation(Context context){
		animationSlideInLeft = AnimationUtils.loadAnimation(context,
			R.anim.left_right);
		animationSlideOutLeft = AnimationUtils.loadAnimation(context,
			R.anim.right_left);
		animationSlideInLeft.setDuration(1000);
		animationSlideOutLeft.setDuration(1000);
		animationSlideOutLeft.setFillAfter(true);


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
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
		float velocityY) {
	    try {


		if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
		    return false;
		// right to left swipe
		if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
			&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
		    Toast.makeText(LauncherActivity.this, "Left Swipe",
			    Toast.LENGTH_SHORT).show();
		    	hideSidebar();
		    
		} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
			&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
		    Toast.makeText(LauncherActivity.this, "Right Swipe",
			    Toast.LENGTH_SHORT).show();
		    sidebar.setVisibility(View.VISIBLE);
		    showSidebar();
		}
	    } catch (Exception e) {
		// nothing
	    }
	    return false;
	}


	@Override
	public boolean onDown(MotionEvent event) {


	    return true;
	}
	@Override
	public boolean onDoubleTap(MotionEvent e){
		Log.e("Double tap","Double tap");
		return true;
	}

    }

}
