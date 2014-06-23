package com.oop.businesscard;

import java.util.Date;
import java.util.List;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class ViewCardActivity extends ActionBarActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_view_card);
		
		String objectId = getIntent().getStringExtra(Constants.UserKeys.OBJECT_ID);
		//Toast.makeText(this, objectId, Toast.LENGTH_SHORT).show();
		
		if (savedInstanceState == null)
		{
			PlaceholderFragment content = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putString(Constants.UserKeys.OBJECT_ID, objectId);
			content.setArguments(args);
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, content).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_card, menu);
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
		
		private String objectId;
		private ParseUser user;
		
		private TextView nameView;
		private TextView titleView;
		private ImageView photoView;
		private TextView telephoneView;
		private TextView cellphoneView;
		private TextView emailView;
		private TextView addrView;
		private TextView companyView;
		private TextView departmentView;
		private TextView faxView;
		
		private Bitmap photo;
		private byte[] photoBytes;
		private String name;
		private String title;
		private String telephone;
		private String cellphone;
		private String email;
		private String address;
		private String company;
		private String department;
		private String fax;
		/*
		private String name;
		private String title;
		private Bitmap photo;
		private String telephone;
		private String cellphone;
		private String email;
		private String addr;
		private String company;
		private String department;
		private String fax;
		*/

		public PlaceholderFragment()
		{
		}
		
		@Override
		public void onCreate(Bundle savedInstanceState)
		{
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			activity = getActivity();
			setRetainInstance(true);

		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{
			View rootView = inflater.inflate(R.layout.fragment_view_card,
					container, false);
			
			nameView = (TextView) rootView.findViewById(R.id.name);
			titleView = (TextView) rootView.findViewById(R.id.title);
			photoView = (ImageView) rootView.findViewById(R.id.photo);
			telephoneView = (TextView) rootView.findViewById(R.id.telephone);
			cellphoneView = (TextView) rootView.findViewById(R.id.cellphone);
			emailView = (TextView) rootView.findViewById(R.id.email);
			addrView = (TextView) rootView.findViewById(R.id.address);
			companyView = (TextView) rootView.findViewById(R.id.company);
			departmentView = (TextView) rootView.findViewById(R.id.department);
			faxView = (TextView) rootView.findViewById(R.id.fax);
			
			String objectId = getArguments().getString(Constants.UserKeys.OBJECT_ID);
			
			ParseQuery<ParseUser> query = ParseUser.getQuery();
			//Toast.makeText(activity, objectId, Toast.LENGTH_SHORT).show();
			query.whereEqualTo(Constants.UserKeys.OBJECT_ID, objectId);
			query.findInBackground(new FindCallback<ParseUser>()
			{
				
				@Override
				public void done(List<ParseUser> objects, ParseException e)
				{
					// TODO Auto-generated method stub
					if(e == null)
					{
						if(objects.size() == 0)
						{
							Toast.makeText(activity, "找不到使用者", Toast.LENGTH_LONG).show();
						}
						else {
							user = objects.get(0);
							
							ParseFile photoFile = (ParseFile) user.get(Constants.UserKeys.PHOTO);
							if(photoFile != null)
							{
								photoFile.getDataInBackground(new GetDataCallback()
								{
									
									@Override
									public void done(byte[] data, ParseException e)
									{
										// TODO Auto-generated method stub
										if(e == null)
										{
											if(data != null)
											{
												photo = BitmapFactory.decodeByteArray(data, 0, data.length);
												photoView.setImageBitmap(photo);
											}
										}
										else {
											Toast.makeText(activity, e.getMessage(), Toast.LENGTH_LONG).show();
										}
									}
								});
								
							}
		
							name = user.getString(Constants.UserKeys.NAME);
							title = user.getString(Constants.UserKeys.TITLE);
							telephone = user.getString(Constants.UserKeys.TELEPHONE);
							cellphone = user.getString(Constants.UserKeys.CELLPHONE);
							email = user.getEmail();
							address = user.getString(Constants.UserKeys.ADDRESS);
							company = user.getString(Constants.UserKeys.COMPANY);
							department = user.getString(Constants.UserKeys.DEPARTMENT);
							fax = user.getString(Constants.UserKeys.FAX);
							
							nameView.setText(user.getString(Constants.UserKeys.NAME));
							titleView.setText(user.getString(Constants.UserKeys.TITLE));
							telephoneView.setText(user.getString(Constants.UserKeys.TELEPHONE));
							cellphoneView.setText(user.getString(Constants.UserKeys.CELLPHONE));
							emailView.setText(user.getEmail());
							addrView.setText(user.getString(Constants.UserKeys.ADDRESS));
							companyView.setText(user.getString(Constants.UserKeys.COMPANY));
							departmentView.setText(user.getString(Constants.UserKeys.DEPARTMENT));
							faxView.setText(user.getString(Constants.UserKeys.FAX));
						}
					
					}
					/*
					else if(e.getCode() == ParseException.TIMEOUT){
						Toast.makeText(activity, "�����s�u��í�A�нT�{�z���������A", Toast.LENGTH_SHORT).show();
					}
					else if(e.getCode() == ParseException.CONNECTION_FAILED)
					{
						Toast.makeText(activity, "�L�k�s�u���A��", Toast.LENGTH_SHORT).show();
					}
					else if(e.getCode() == ParseException.INTERNAL_SERVER_ERROR)
					{
						Toast.makeText(activity, "���@~ Parse Server�X�F��A���p���ڭ̡C", Toast.LENGTH_LONG).show();
					}
					else if (e.getCode() == ParseException.OTHER_CAUSE)
					{
						Toast.makeText(activity, "���@~ �����~�A���p���ڭ�", Toast.LENGTH_LONG).show();
					}
					*/
					else 
					{
						Toast.makeText(getActivity(), e.getCode() + ": " + e.getMessage(), Toast.LENGTH_LONG).show();
					}	
				}
			});
	
			return rootView;
		}
	}

}
