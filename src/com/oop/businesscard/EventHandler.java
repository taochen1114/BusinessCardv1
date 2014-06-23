package com.oop.businesscard;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class EventHandler
{
	public static void addUser(String email, String pwd, SignUpCallback callback)
	{
		ParseUser user = new ParseUser();
		user.setUsername(email);
		user.setPassword(pwd);
		user.setEmail(email);
		
		user.signUpInBackground(callback);
	}
	

	
	public static Bitmap scale(Bitmap bm, int width, int height)
	{
		int bmWidth = bm.getWidth();
		int bmHeight = bm.getHeight();
		
		float xScale = width / (float) bmWidth;
		float yScale = height / (float) bmHeight;
		
		float baseScale = Math.min(xScale, yScale);
		
		Matrix matrix = new Matrix();
		matrix.setScale(baseScale, baseScale);
	
		Bitmap result = Bitmap.createBitmap(bm, 0, 0, bmWidth, bmHeight, matrix, false);
		return result;
	}
	

}
