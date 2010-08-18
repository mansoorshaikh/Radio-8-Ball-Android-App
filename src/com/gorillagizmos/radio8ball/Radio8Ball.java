package com.gorillagizmos.radio8ball;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Radio8Ball extends Radio8BallActivity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button askButton = (Button) findViewById(R.id.ask_button);
        askButton.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		Intent questionIntent = new Intent(Radio8Ball.this, QuestionPopup.class);
    	Radio8Ball.this.startActivity(questionIntent);
	}
}