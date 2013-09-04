package com.lib4.ngo.appbase.adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lib4.ngo.R;
import android.R.color;

public class Sidebar_Adapter extends BaseAdapter{

	
	private String[] sidelists	=	{"Home","Who We Are","what We Do",
			"Get Involved","Contact Us","News"};
	
	
	private LayoutInflater mLayoutInflater;
	
	private int itemSelected=	0;
	
	public Sidebar_Adapter(Context mContext) {
		// TODO Auto-generated constructor stub
		
		mLayoutInflater	=	(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return sidelists.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressLint("ResourceAsColor")
	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		View mView	=	arg1;
		if(arg1==null){
			
			mView 	=	mLayoutInflater.inflate(R.layout.sidebar_listview_holder, null);
			ViewHolder viewHolder	=	new ViewHolder();
			viewHolder.mImageView	=	(ImageView) mView.findViewById(R.id.icon);
			viewHolder.mTextView	=	(TextView) mView.findViewById(R.id.text);
			mView.setTag(viewHolder);
		}
		
		ViewHolder holder	=	(ViewHolder) mView.getTag();
		holder.mTextView.setText(sidelists[arg0]);
		if(itemSelected==arg0){
			
			mView.setBackgroundColor(android.R.color.primary_text_dark);
		}else{
			mView.setBackgroundColor(Color.TRANSPARENT);
		}
		return mView;
	}
	
	static class ViewHolder{
		
		public ImageView mImageView;
		public TextView mTextView;
	}

	
	public void setitemPressed(int position){
		itemSelected	=	position;
	}
}
