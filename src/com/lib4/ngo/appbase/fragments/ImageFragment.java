package com.lib4.ngo.appbase.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lib4.ngo.R;

public class ImageFragment extends BaseFragment{

	
	
	private int imagePosition;
	private int[] resourceid	=	{R.drawable.second,R.drawable.first,R.drawable.third,
			R.drawable.fourth,R.drawable.fifth,R.drawable.sixth};
	
	private LinearLayout linearLayout;
	
	public ImageFragment(){
		
	}
	
	
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

		imagePosition	=	getArguments().getInt("position");
		// Inflate the layout for this fragment
		linearLayout = (LinearLayout) inflater.inflate(R.layout.imagefragment, container, false);
		ImageView mImageView	=	(ImageView) linearLayout.findViewById(R.id.image);
		
		mImageView.setImageDrawable(getResources().getDrawable(resourceid[imagePosition]));
		return linearLayout;
		
	}
	
	

}
