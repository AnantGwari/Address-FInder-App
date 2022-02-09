package com.example.addressfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends LifecycleLoggingActivity{

    private String Tag = getClass().getSimpleName();
    private EditText add;
    private boolean toggle;
    private Button action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = findViewById(R.id.editTextTextPersonName);
        action = findViewById(R.id.button);
        add.setVisibility(View.INVISIBLE);
        toggle = false;
    }
    public void MapAddress(View view)
    {
     if(toggle)
         {
             toggle = false;
             add.setVisibility(View.INVISIBLE);
             startMap();
         }
     else
         {
             toggle = true;
             add.setVisibility(View.VISIBLE);
         }
    }
    public void startMap()
        {

            try {
                String address = add.getText().toString();
                Log.d("sfsd",address);
                address.replace(' ','+');
                final Intent geo = makeMapIntent(address);
                if(geo.resolveActivity(getPackageManager()) != null)
                    {
                        startActivity(geo);
                    }
                else
                    {
                        startActivity(makeBrowserIntent(address));
                    }
            }
            catch (Exception e)
                {
                    e.printStackTrace();
                }
        }
        private Intent makeMapIntent(String address)
            {
                return new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="+address));
            }
            private Intent makeBrowserIntent(String address)
                {
                    return new Intent(Intent.ACTION_VIEW, Uri.parse("https://maps.google.com/?q="+address));
                }
}