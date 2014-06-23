package com.oop.businesscard;

import android.app.Application;
import com.parse.*;

public class BusinessCardApp extends Application
{
	private static final String APP_ID = "xLsKZJzXFFyvvdkwt5vv5l2S8tH0oJ7V0SJrAnC5";
	private static final String CLIENT_KEY = "U6fG5VFBPu0z2juriiazOcU6UHldxX6r3Dyvr6XI";
	@Override
	public void onCreate() 
	{
		// TODO Auto-generated method stub
		super.onCreate();
		Parse.initialize(this, APP_ID, CLIENT_KEY);
	}
}
