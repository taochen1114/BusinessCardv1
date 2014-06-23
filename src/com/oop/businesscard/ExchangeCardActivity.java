package com.oop.businesscard;


import java.io.OutputStream;
import java.util.UUID;

import com.example.android.BluetoothChat.BluetoothChatService;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class ExchangeCardActivity extends ActionBarActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_exchange_card);
		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.exchange_card, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
//		
//		 // Debugging
//	    private static final String TAG = "BluetoothChat";
//	    private static final boolean D = true;
//
//	    // Message types sent from the BluetoothChatService Handler
//	    public static final int MESSAGE_STATE_CHANGE = 1;
//	    public static final int MESSAGE_READ = 2;
//	    public static final int MESSAGE_WRITE = 3;
//	    public static final int MESSAGE_DEVICE_NAME = 4;
//	    public static final int MESSAGE_TOAST = 5;
//
//	    // Key names received from the BluetoothChatService Handler
//	    public static final String DEVICE_NAME = "device_name";
//	    public static final String TOAST = "toast";
//
//	    // Intent request codes
//	    private static final int REQUEST_CONNECT_DEVICE = 1;
//	    private static final int REQUEST_ENABLE_BT = 2;
//
//	    // Layout Views
//	    private TextView mTitle;
//	    private ListView mConversationView;
//	    private EditText mOutEditText;
//	    private Button mSendButton;
//
//	    // Name of the connected device
//	    private String mConnectedDeviceName = null;
//	    // Array adapter for the conversation thread
//	    private ArrayAdapter<String> mConversationArrayAdapter;
//	    // String buffer for outgoing messages
//	    private StringBuffer mOutStringBuffer;
//	    // Local Bluetooth adapter
//	    private BluetoothAdapter mBluetoothAdapter = null;
//	    // Member object for the chat services
//	    private BluetoothChatService mChatService = null;
//

		private static BluetoothAdapter mBluetoothAdapter = null; // 用來搜尋、管理藍芽裝置
		
		private static BluetoothSocket mBluetoothSocket = null; // 用來連結藍芽裝置、以及傳送指令
		
		private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); // 一定要是這組
		
		private static  OutputStream mOutputStream = null;
		
		private final int REQUEST_ENABLE_BT=1;

	    private Activity activity;	
	    private ImageView photoBluetoothBtn;
	    private Button buttonForImageView;
	    
		public PlaceholderFragment() {
		}
	
		

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_exchange_card,container, false);
			
			activity = getActivity();
			photoBluetoothBtn = (ImageView) rootView.findViewById(R.id.bluetoothPhoto);
			buttonForImageView = (Button) rootView.findViewById(R.id.buttonForImageView);
			
			photoBluetoothBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
				}
			});
			
			buttonForImageView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(activity, BluetoothChat.class);
					startActivity(intent);
				}
			});
			
			return rootView;
		}
	}

}
