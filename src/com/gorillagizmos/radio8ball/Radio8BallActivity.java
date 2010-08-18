package com.gorillagizmos.radio8ball;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Radio8BallActivity extends Activity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.social:
            return true;
        case R.id.history:
            return true;
        case R.id.options:
            return true;
        case R.id.about:
        	Intent aboutIntent = new Intent(Radio8BallActivity.this, AboutActivity.class);
        	Radio8BallActivity.this.startActivity(aboutIntent);
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}
