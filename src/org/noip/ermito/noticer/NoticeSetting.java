package org.noip.ermito.noticer;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ToggleButton;
import android.content.Intent;





public class NoticeSetting extends Activity {

	final String LOG_TAG = "myLogs";
	ToggleButton toogleButton;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notice_setting);	
		
		//проверим, запущен ли наш сервис
		Boolean already = false;
		ActivityManager am = (ActivityManager)this.getSystemService(ACTIVITY_SERVICE);
		List<ActivityManager.RunningServiceInfo> rs = am.getRunningServices(50);
		for (int i=0; i<rs.size() && !already; i++)
		{
			ActivityManager.RunningServiceInfo rsi = rs.get(i);
			already=rsi.service.getClassName().contentEquals("org.noip.ermito.noticer.NoticeService");			
		}
		View view = findViewById(R.id.toggleButton1);
		((ToggleButton) view).setChecked(already);				
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.notice_setting, menu);
		return true;
	}
	public void onClickStartStopService(View view) {
	    // Is the toggle on?
	    boolean on = ((ToggleButton) view).isChecked();
	    Intent NSI = new Intent(this, NoticeService.class);
	        
	    if (on) {	    	
	        // Enable	    	
	    	startService(NSI);	    		    	
	    } else {
	        // Disable
	    	stopService(NSI);
	    }
	}
	
	
	
	public void onClickStart(View v) {
	     startService(new Intent(this, TestService.class));
	}
	    
	public void onClickStop(View v) {
	     stopService(new Intent(this, TestService.class));	  
	}
	
	

}
