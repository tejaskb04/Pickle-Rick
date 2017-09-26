package com.tejas_bharadwaj.picklerick;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout mainLayout;
    private Button pickleRickBtn;
    private MediaPlayer picklePlayer;
    private MediaPlayer rickPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLayout = (RelativeLayout) findViewById(R.id.main_layout);
        pickleRickBtn = (Button) findViewById(R.id.pickle_rick_button);
        picklePlayer = MediaPlayer.create(MainActivity.this, R.raw.pickle_rick_audio);
        rickPlayer = MediaPlayer.create(MainActivity.this, R.raw.rick_sound_effect);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int width = displayMetrics.widthPixels;
        final int height = displayMetrics.heightPixels;
        pickleRickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickleRickBtn.setVisibility(View.GONE);
                ImageView pickleViewOne = new ImageView(MainActivity.this);
                Picasso.with(MainActivity.this).load(R.drawable.pickle_rick_1)
                        .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                        .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                        .into(pickleViewOne);
                mainLayout.addView(pickleViewOne);
                // EDGY CODE BELOW
                /*ImageView pickleViewTwo = new ImageView(MainActivity.this);
                Picasso.with(MainActivity.this).load(R.drawable.pickle_rick_2)
                        .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                        .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                        .resize(width, 0)
                        .into(pickleViewTwo);
                mainLayout.addView(pickleViewTwo);*/
                ImageView pickleViewThree = new ImageView(MainActivity.this);
                Picasso.with(MainActivity.this).load(R.drawable.pickle_rick_3)
                        .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                        .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                        .resize(0, height)
                        .into(pickleViewThree);
                mainLayout.addView(pickleViewThree);
                // EDGY CODE BELOW
                /*ImageView pickleViewFour = new ImageView(MainActivity.this);
                Picasso.with(MainActivity.this).load(R.drawable.pickle_rick_4)
                        .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                        .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                        .resize(width, 0)
                        .centerCrop()
                        .into(pickleViewFour);
                mainLayout.addView(pickleViewFour);*/
                picklePlayer.start();
            }
        });
        picklePlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                rickPlayer.start();
            }
        });
        rickPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                picklePlayer.start();
            }
        });
    }

    @Override
    protected void onDestroy() {
        picklePlayer.stop();
        rickPlayer.stop();
        super.onDestroy();
    }
}
