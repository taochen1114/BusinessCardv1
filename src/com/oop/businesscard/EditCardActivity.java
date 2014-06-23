package com.oop.businesscard;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.*;
import android.R.integer;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.Media;

public class EditCardActivity extends ActionBarActivity
{
	public static final int GET_PHOTO_REQUEST_CODE = 1;
	public static final String FRAGMENT_TAG = "content";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_edit_card);

		if (savedInstanceState == null)
		{
			PlaceholderFragment content =  new PlaceholderFragment();
			android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, content, FRAGMENT_TAG).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_card, menu);
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
		
		private ImageView photoBtn;
		private EditText nameView;
		private EditText titleView;
		private EditText companyView;
		private EditText departmentView;
		private EditText addressView;
		private EditText telephoneView;
		private EditText faxView;
		private EditText cellphoneView;
		private EditText emailView;
		private TextView saveView;
		
		private ParseUser user;
		
		private Bitmap photoBm;
		private byte[] photoBytes;
		private ParseFile photoFile;
		private String suffix = "";
		private String name = "";
		private String title = "";
		private String company = "";
		private String department = "";
		private String address = "";
		private String telephone = "";
		private String fax = "";
		private String cellphone = "";
		private String email = "";
		
		private int photoSize = 100;
	

		public PlaceholderFragment()
		{
		}
		
		@Override
		public void onCreate(Bundle savedInstanceState)
		{
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setRetainInstance(true);
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{
			View rootView = inflater.inflate(R.layout.fragment_edit_card, container, false);
			
			photoBtn = (ImageView) rootView.findViewById(R.id.photo);
			nameView = (EditText) rootView.findViewById(R.id.nameInput);
			titleView = (EditText) rootView.findViewById(R.id.titleInput);
			companyView = (EditText) rootView.findViewById(R.id.companyInput);
			departmentView = (EditText) rootView.findViewById(R.id.departmentInput);
			addressView = (EditText) rootView.findViewById(R.id.addressInput);
			telephoneView = (EditText) rootView.findViewById(R.id.telephoneInput);
			faxView = (EditText) rootView.findViewById(R.id.faxInput);
			cellphoneView = (EditText) rootView.findViewById(R.id.cellphoneInput);
			emailView = (EditText) rootView.findViewById(R.id.emailInput);
			saveView = (TextView) rootView.findViewById(R.id.save);
			
			
			photoBtn.setOnClickListener(new OnClickListener()
			{
				
				@Override
				public void onClick(View v)
				{
					// TODO Auto-generated method stub
					Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
					startActivityForResult(intent, EditCardActivity.GET_PHOTO_REQUEST_CODE);
				}
			});
			
			saveView.setOnClickListener(new OnClickListener()
			{
				
				@Override
				public void onClick(View v)
				{
					// TODO Auto-generated method stub
					user = ParseUser.getCurrentUser();
					
					name = nameView.getText().toString();
					title = titleView.getText().toString();
					company = companyView.getText().toString();
					department = departmentView.getText().toString();
					address = addressView.getText().toString();
					telephone = telephoneView.getText().toString();
					fax = faxView.getText().toString();
					cellphone = cellphoneView.getText().toString();
					email = emailView.getText().toString();
					
					//Toast.makeText(getActivity(), String.valueOf(photoBytes.length), Toast.LENGTH_LONG).show();
					//Toast.makeText(getActivity(), "photoBytes: " + photoBytes.toString(), Toast.LENGTH_LONG).show();
					//Toast.makeText(getActivity(), "sufix: " + suffix, Toast.LENGTH_LONG).show();
					if(photoBytes != null)
					{
						String photoName = Constants.UserKeys.PHOTO + "." + suffix;
						//Toast.makeText(getActivity(), photoName, Toast.LENGTH_LONG).show();
						photoFile = new ParseFile(photoName, photoBytes);
						photoFile.saveInBackground(new SaveCallback()
						{
							
							@Override
							public void done(ParseException e)
							{
								// TODO Auto-generated method stub
								if(e == null)
								{
									user.put(Constants.UserKeys.PHOTO, photoFile);
									user.saveInBackground(new SaveCallback()
									{
										
										@Override
										public void done(ParseException e)
										{
											// TODO Auto-generated method stub
											if(e == null)
											{
												Toast.makeText(getActivity(), "使用者圖片已上傳", Toast.LENGTH_SHORT).show();
											}
											else 
											{
												Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
											}
										}
									});
								}else {
									Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
								}
							
							}
						});
						
					}
					if(!name.equals(""))
					{
						user.put(Constants.UserKeys.NAME, name);
					}
					if(!title.equals(""))
					{
						user.put(Constants.UserKeys.TITLE, title);
					}
					if(!company.equals(""))
					{
						user.put(Constants.UserKeys.COMPANY, company);
					}
					if(!department.equals(""))
					{
						user.put(Constants.UserKeys.DEPARTMENT, department);
					}
					if(!address.equals(""))
					{
						user.put(Constants.UserKeys.ADDRESS, address);
					}
					if(!telephone.equals(""))
					{
						user.put(Constants.UserKeys.TELEPHONE, telephone);
					}
					if(!fax.equals(""))
					{
						user.put(Constants.UserKeys.FAX, fax);
					}
					if(!cellphone.equals(""))
					{
						user.put(Constants.UserKeys.CELLPHONE, cellphone);
					}
					if(!email.equals(""))
					{
						user.setEmail(email);
						user.setUsername(email);
					}
					user.saveInBackground(new SaveCallback()
					{
						
						@Override
						public void done(ParseException e)
						{
							// TODO Auto-generated method stub
							if(e == null)
							{
								Toast.makeText(getActivity(), "已儲存變更", Toast.LENGTH_SHORT).show();
						
							}
							else 
							{
								Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
							}
						}
					});
				}
			});
			
			
			return rootView;
		}
		
		
		@Override
		public void onActivityResult(int requestCode, int resultCode, Intent data)
		{
			// TODO Auto-generated method stub
			super.onActivityResult(requestCode, resultCode, data);

			//Toast.makeText(getActivity(), "reauestCode: " + requestCode + ", RESULT_OK = " + (resultCode == RESULT_OK), Toast.LENGTH_LONG).show();
			
			if(requestCode == GET_PHOTO_REQUEST_CODE)
			{
				if(resultCode == RESULT_OK)
				{
					Uri image = data.getData();
					try
					{
						photoBytes = readFile(getActivity(), image);

					} catch (IOException e)
					{
						// TODO Auto-generated catch block
						Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
					}
					//Toast.makeText(getActivity(), image.toString(), Toast.LENGTH_LONG).show();
					String[] filePath = {MediaStore.Images.Media.DATA};
					
					
					Cursor cursor = getActivity().getContentResolver().query(image, filePath, null, null, null);
					cursor.moveToFirst();
					
					int index = cursor.getColumnIndex(filePath[0]);
					String picutrePath = cursor.getString(index);
					
					String[] splits = picutrePath.split("\\.");
					/*
					Toast.makeText(getActivity(), "picturePath: " + picutrePath, Toast.LENGTH_LONG).show();
				
					
					
					String result = "";
					for(int i = 0; i < splits.length; i++)
					{
						result = result + i + " : " + splits[i] + ", ";
					}
							
					Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
					*/
					suffix = splits[splits.length - 1];
					
					cursor.close();
					
				
					photoBm = BitmapFactory.decodeFile(picutrePath);
					BitmapDrawable photoBmd = new BitmapDrawable(getResources(), EventHandler.scale(photoBm, photoSize, photoSize));
					//Toast.makeText(getActivity(), photoBm.toString(), Toast.LENGTH_LONG).show();
					photoBtn.setImageDrawable(photoBmd);
					
				}
			}

			
		}
		
		public byte[] readFile(Activity activity, Uri uri) throws IOException
		{
			InputStream is = activity.getContentResolver().openInputStream(uri);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			
			int bufferSize = 1024;
			byte[] buffer = new byte[bufferSize];
			int len = 0;
			while((len = is.read(buffer)) != -1)
			{
				bos.write(buffer, 0, len);
			}
			
			return bos.toByteArray();
		}

		
	}

}
