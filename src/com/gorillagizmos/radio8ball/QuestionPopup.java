package com.gorillagizmos.radio8ball;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class QuestionPopup extends Activity implements OnClickListener {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.question_popup);
        
        Button shakeButton = (Button) findViewById(R.id.shake_button);
        shakeButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent songPlayerIntent = new Intent(QuestionPopup.this, SongPlayerPopup.class);
    	QuestionPopup.this.startActivity(songPlayerIntent);
	}
	
	protected void onPause() {
		super.onPause();
		
		this.setVisible(false);
	}
	
	protected void onResume() {
		super.onResume();
		
		this.setVisible(true);
	}
}
