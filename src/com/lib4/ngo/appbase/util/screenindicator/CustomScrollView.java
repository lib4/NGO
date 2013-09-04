package com.lib4.ngo.appbase.util.screenindicator;

import com.lib4.ngo.appbase.activities.LauncherActivity;
import com.lib4.ngo.appbase.adapters.Sidebar_Adapter;


import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

public class CustomScrollView extends ScrollView implements OnDoubleTapListener{

	
	private Context mContext;
	
	private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;
	
	public CustomScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	    gestureDetector = new GestureDetector(LauncherActivity.mLauncherActivity, new CustomGestureDetector());
			gestureListener = new View.OnTouchListener() {
			    public boolean onTouch(View v, MotionEvent event) {


				return gestureDetector.onTouchEvent(event);
			    }
			};

			this.setOnTouchListener(gestureListener);
	}

	@Override
	public boolean onDoubleTap(MotionEvent e) {
		// TODO Auto-generated method stub
		LauncherActivity.mLauncherActivity.sidebarToggle();
		return false;
	}

	@Override
	public boolean onDoubleTapEvent(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onSingleTapConfirmed(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
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
		LauncherActivity.mLauncherActivity.sidebarToggle();
		return true;
	}

    }
	
	
}
