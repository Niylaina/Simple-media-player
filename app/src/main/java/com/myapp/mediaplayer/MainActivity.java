package com.myapp.mediaplayer;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static Uri file;
    private TextView path;
    private VideoView videoView;
    public static final int PICKFILE_RESULT_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        path = findViewById(R.id.file);
        videoView = findViewById(R.id.videoView);

        ImageButton open_btn = findViewById(R.id.open_btn);
        open_btn.setOnClickListener(this);
        file = null;
    }

    @Override
    public void onClick(View v) {
        Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("video/*");
        chooseFile = Intent.createChooser(chooseFile, "Choisissez une vidéo: ");
        startActivityForResult(chooseFile, PICKFILE_RESULT_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PICKFILE_RESULT_CODE:
                if (resultCode == -1) {
                    try {
                        file = data.getData();
                        if(file != null){
                            path.setText(file.getPath());
                            videoView.setVideoURI(file);
                            MediaController mediaController = new MediaController(this);
                            mediaController.setAnchorView(videoView);
                            videoView.setMediaController(mediaController);
                            videoView.start();
                        }
                    }catch(Exception e){

                    }
                }
                break;
        }
    }
}
