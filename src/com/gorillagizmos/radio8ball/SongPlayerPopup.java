package com.gorillagizmos.radio8ball;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SongPlayerPopup extends Activity {
	private MediaPlayer mp;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.song_player_popup);
        
        Button shareButton = (Button) findViewById(R.id.share_button);
        shareButton.setOnClickListener(shareButtonListener);
        
        Button anotherQuestionButton = (Button) findViewById(R.id.ask_another_button);
        anotherQuestionButton.setOnClickListener(anotherQuestionButtonListener);
        
        mp = new MediaPlayer();
        mp.setOnPreparedListener(onPreparedListener());
        
        try {
        	mp.setDataSource("http://radio8ball.com/sites/all/themes/box_grey/upload/song/1233085537Dan%20Bern%20-%20Tiger%20Woods%20%28Live%20in%20Oly%2098%29.mp3");
        } catch (IllegalArgumentException ex) {
        	ex.printStackTrace();
        } catch (IllegalStateException ex) {
        	ex.printStackTrace();
        } catch (IOException ex) {
        	ex.printStackTrace();
        }
        try {
        	mp.prepareAsync();
        } catch (IllegalStateException ex) {
        	System.out.println("prepareAsync() throws IllegalStateException");
        }
	}
	
	private OnPreparedListener onPreparedListener() {
		mp.start();
		return null;
	}
	
	private OnClickListener shareButtonListener = new OnClickListener() {
	    public void onClick(View v) {
	    	Intent shareIntent = new Intent(SongPlayerPopup.this, ShareActivity.class);
			SongPlayerPopup.this.startActivity(shareIntent);
	    }
	};
	
	private OnClickListener anotherQuestionButtonListener = new OnClickListener() {
	    public void onClick(View v) {
	    	finish();
	    	Intent anotherQuestionIntent = new Intent(SongPlayerPopup.this, QuestionPopup.class);
			SongPlayerPopup.this.startActivity(anotherQuestionIntent);
	    }
	};
}
