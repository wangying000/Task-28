package com.example.dell.task_28;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "myTag";
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = new MediaPlayer();

        final TextView textView = (TextView) findViewById(R.id.textView);
        final Button button = (Button) findViewById(R.id.button);
        final Button button2 = (Button) findViewById(R.id.button2);
        final Button button3 = (Button) findViewById(R.id.button3);
        final Button button4 = (Button) findViewById(R.id.button4);

        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);


        //开始播放
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.v(TAG, "start");
                    mediaPlayer.reset();
                    AssetManager assetManager = getAssets();
                    AssetFileDescriptor assetFileDescriptor=null;
                    try{
                        assetFileDescriptor = assetManager.openFd("abc.mp3");
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(),
                            assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    button2.setEnabled(true);
                    button3.setEnabled(true);
                    button4.setEnabled(true);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //暂停播放
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    button2.setText("Play");
                    mediaPlayer.pause();
                } else {
                    button2.setText("Pause");
                    mediaPlayer.start();
                }
            }
        });

        //停止播放
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying())
                    mediaPlayer.stop();
            }
        });

        //循环播放
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "Looping");
                boolean loop = mediaPlayer.isLooping();
                mediaPlayer.setLooping(!loop);
                if (!loop)
                    textView.setText("循环播放");
                else
                    textView.setText("一次播放");
            }
        });
    }
}

