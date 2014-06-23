package com.oop.businesscard;

import com.parse.ParseUser;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends ActionBarActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		

		if (savedInstanceState == null)
		{
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment
	{
		
		private Activity activity;
		
		private ParseUser user;
		
		private ImageButton myCard;
		private ImageButton editCard;
		private ImageButton browseCard;
		private ImageButton exchangeCard;
		private TextView logout;
		
		

		public PlaceholderFragment()
		{
		}

		@Override
		public void onCreate(Bundle savedInstanceState)
		{
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setRetainInstance(true);
			activity = getActivity();
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			
			myCard = (ImageButton) rootView.findViewById(R.id.myCardBtn);
			editCard = (ImageButton) rootView.findViewById(R.id.editCardBtn);
			browseCard = (ImageButton) rootView.findViewById(R.id.browseCardsBtn);
			exchangeCard = (ImageButton) rootView.findViewById(R.id.exchangeCardBtn);
			logout = (TextView) rootView.findViewById(R.id.logout);
			
			user = ParseUser.getCurrentUser();
			
			myCard.setOnClickListener(new OnClickListener()
			{
				
				@Override
				public void onClick(View v)
				{
					// TODO Auto-generated method stub
					Intent intent = new Intent(activity, ViewCardActivity.class);
					intent.putExtra(Constants.UserKeys.OBJECT_ID, user.getObjectId());
					startActivity(intent);
				}
			});
			
			editCard.setOnClickListener(new OnClickListener()
			{
				
				@Override
				public void onClick(View v)
				{
					// TODO Auto-generated method stub
					Intent intent = new Intent(activity, EditCardActivity.class);
					startActivity(intent);
				}
			});
			
			browseCard.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(activity, BrowseCardActivity.class);
					startActivity(intent);
				}
			});
			
			exchangeCard.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(activity, ExchangeCardActivity.class);
					startActivity(intent);
					
				}
			});
			
//			exchangeCard.setOnClickListener(new OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					// TODO Auto-generated method stub
//					Intent intent = new Intent(activity, BluetoothChat.class);
//					startActivity(intent);
//					
//				}
//			});
			
			logout.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					user.logOut();
					Intent intent = new Intent(activity, LoginActivity.class);
					startActivity(intent);
				}
			});
			return rootView;
		
		} // end onCreateView
		
	} // end PlaceholderFragment class

} // end MainActivity class
