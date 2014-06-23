package com.oop.businesscard;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class LoginActivity extends ActionBarActivity 
{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		
		

		
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
		
		private EditText emailInput;
		private EditText pwdInput;
		private TextView register;
		private TextView login;
		
		private String email;
		private String pwd;
		
		private ParseUser user;

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
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) 
		{
			View rootView = inflater.inflate(R.layout.fragment_login, container,
					false);
			activity = getActivity();
			emailInput = (EditText) rootView.findViewById(R.id.emailInput);
			pwdInput = (EditText) rootView.findViewById(R.id.passwordInput);
			register = (TextView) rootView.findViewById(R.id.register);
			login = (TextView) rootView.findViewById(R.id.login);
			
			login.setOnClickListener(new OnClickListener()
			{
				
				@Override
				public void onClick(View v)
				{
					// TODO Auto-generated method stub
					email = emailInput.getText().toString();
					pwd = pwdInput.getText().toString();
					
					if(email.equals(""))
					{
						Toast.makeText(getActivity(), "請輸入電子郵件信箱", Toast.LENGTH_SHORT).show();
					}
					else if (pwd.equals(""))
					{
						Toast.makeText(getActivity(), "請輸入密碼", Toast.LENGTH_SHORT).show();
					}
					else {
						ParseUser.logInInBackground(email, pwd, new LogInCallback()
						{

							@Override
							public void done(ParseUser user, ParseException e)
							{
								// TODO Auto-generated method stub
								if(e == null)
								{
									Toast.makeText(activity, "成功登入", Toast.LENGTH_SHORT).show();
									Intent intent = new Intent(getActivity(), MainActivity.class);
									startActivity(intent);
									getActivity().finish();
								}
								/*else if(e.getCode() == ParseException.OBJECT_NOT_FOUND)
								{
									Toast.makeText(activity, "帳號或密碼錯誤", Toast.LENGTH_SHORT).show();
								}
								else if(e.getCode() == ParseException.EMAIL_NOT_FOUND)
								{
									Toast.makeText(activity, "���H�c�S����U�b���A�е�U�ΨϥΨ�L�H�c�n�J", Toast.LENGTH_LONG).show();
								}
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
					}
				}
			});
			
			register.setOnClickListener(new OnClickListener()
			{
				
				@Override
				public void onClick(View v)
				{
					// TODO Auto-generated method stub
					email = emailInput.getText().toString();
					pwd = pwdInput.getText().toString();
					
					email = emailInput.getText().toString();
					pwd = pwdInput.getText().toString();
					
					if(email.equals(""))
					{
						Toast.makeText(getActivity(), "請輸入電子郵件信箱", Toast.LENGTH_SHORT).show();
					}
					else if (pwd.equals(""))
					{
						Toast.makeText(getActivity(), "請輸入密碼", Toast.LENGTH_SHORT).show();
					}
					else {
						EventHandler.addUser(email, pwd, new SignUpCallback()
						{

							@Override
							public void done(ParseException e)
							{
								if(e == null)
								{
									Toast.makeText(getActivity(), "成功註冊新帳號", Toast.LENGTH_SHORT).show();
									Intent intent = new Intent(getActivity(), MainActivity.class);
									startActivity(intent);
									getActivity().finish();
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
					}
				}
			});
			
			user = ParseUser.getCurrentUser();
			if(user != null)
			{
				if(user.isAuthenticated())
				{
					Intent intent = new Intent(getActivity(), MainActivity.class);
					startActivity(intent);
					getActivity().finish();
				}
			}
			
			return rootView;
		}
	}

}
