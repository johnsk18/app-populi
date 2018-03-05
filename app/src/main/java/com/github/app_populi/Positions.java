package com.github.app_populi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class Positions extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_news_feed:
                    //mTextMessage.setText(R.string.title_news_feed);
                    startActivity(new Intent(Positions.this,NewsFeed.class));
                    return true;
                case R.id.navigation_events:
                    //mTextMessage.setText(R.string.title_events);
                    startActivity(new Intent(Positions.this,Events.class));
                    return true;
                case R.id.navigation_positions:
                    //mTextMessage.setText(R.string.title_positions);
                    startActivity(new Intent(Positions.this,Positions.class));
                    return true;
                case R.id.navigation_info:
                    //mTextMessage.setText(R.string.title_info);
                    startActivity(new Intent(Positions.this,Info.class));
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_positions);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
