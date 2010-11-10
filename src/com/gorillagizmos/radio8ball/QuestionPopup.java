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
import android.widget.EditText;

import com.gorillagizmos.radio8ball.soapclient.*;

public class QuestionPopup extends Activity {
	private SensorManager mSensorManager;
	private float mAccel; // acceleration apart from gravity
	private float mAccelCurrent; // current acceleration including gravity
	private float mAccelLast; // last acceleration including gravity
	public static SoapClient soapClient;
	private EditText questionInput;
	
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
        
        soapClient = new SoapClient();
        
        questionInput = (EditText) findViewById(R.id.question_input);
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
	    	Boolean querySuccessful = soapClient.query(questionInput.getText().toString());
	    	if (querySuccessful) {
	    		finish();
	    		Intent songPlayerIntent = new Intent(QuestionPopup.this, SongPlayerPopup.class);
		    	QuestionPopup.this.startActivity(songPlayerIntent);
	    	} else {
	    		// TODO: show a message like "Sorry, the Oracle can't seem to be reached right now..."
	    	}
	    }
	};
}
