package com.gorillagizmos.radio8ball;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class QuestionPopup extends Activity {
	private SensorManager mSensorManager;
	private float mAccel; // acceleration apart from gravity
	private float mAccelCurrent; // current acceleration including gravity
	private float mAccelLast; // last acceleration including gravity
	
	private final SensorEventListener mSensorListener = new SensorEventListener() {

		public void onSensorChanged(SensorEvent se) {
			float x = se.values[0];
			float y = se.values[1];
			float z = se.values[2];
			mAccelLast = mAccelCurrent;
			mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
			float delta = mAccelCurrent - mAccelLast;
			mAccel = mAccel * 0.9f + delta; // perform low-cut filter
			
			if (mAccel > 5) {
				Intent songPlayerIntent = new Intent(QuestionPopup.this, SongPlayerPopup.class);
		    	QuestionPopup.this.startActivity(songPlayerIntent);
			}
		}

		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}
	};
	  
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.question_popup);
        
        Button shakeButton = (Button) findViewById(R.id.shake_button);
        shakeButton.setOnClickListener(shakeButtonListener);
        
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;
	}
	
	protected void onPause() {
		super.onPause();
		
		this.setVisible(false);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
		this.setVisible(true);
	}
	
	@Override
	protected void onStop() {
		mSensorManager.unregisterListener(mSensorListener);
		super.onStop();
	}
	
	private OnClickListener shakeButtonListener = new OnClickListener() {
	    public void onClick(View v) {
	    	Intent songPlayerIntent = new Intent(QuestionPopup.this, SongPlayerPopup.class);
	    	QuestionPopup.this.startActivity(songPlayerIntent);
	    }
	};
}
