package com.lib4.ngo.appbase.fragments;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.lib4.ngo.R;

public class WhoWeAreFragment extends BaseFragment{

	
	
	
	
	private ScrollView linearLayout;
	
	public WhoWeAreFragment(){
		
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

		
		// Inflate the layout for this fragment
		linearLayout = (ScrollView) inflater.inflate(R.layout.who_we_are, container, false);
		return linearLayout;
		
	}
	
	

}
