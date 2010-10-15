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
import android.widget.TextView;

public class SongPlayerPopup extends Activity {
	private MediaPlayer mp;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.song_player_popup);
        
        TextView songTitleTextView = (TextView) findViewById(R.id.song_title);
        TextView songArtistTextView = (TextView) findViewById(R.id.song_artist);
        Button shareButton = (Button) findViewById(R.id.share_button);
        Button anotherQuestionButton = (Button) findViewById(R.id.ask_another_button);
        
        songTitleTextView.setText(QuestionPopup.soapClient.getSongTitle());
        songArtistTextView.setText(QuestionPopup.soapClient.getSongArtist());
        
        shareButton.setOnClickListener(shareButtonListener);
        anotherQuestionButton.setOnClickListener(anotherQuestionButtonListener);
        
        mp = new MediaPlayer();
        mp.setOnPreparedListener(onPreparedListener());
        
        try {
        	mp.setDataSource(QuestionPopup.soapClient.getSongFile());
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
