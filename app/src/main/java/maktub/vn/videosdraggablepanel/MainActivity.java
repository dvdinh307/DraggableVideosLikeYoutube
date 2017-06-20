package maktub.vn.videosdraggablepanel;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.VideoView;

import com.github.pedrovgs.DraggableListener;
import com.github.pedrovgs.DraggableView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    VideoView videoView;
    DraggableView draggableView;
    private ListView mListSub, mListMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = (VideoView) findViewById(R.id.video_view);
        draggableView = (DraggableView) findViewById(R.id.draggable_view);
        mListSub = (ListView) findViewById(R.id.list_sub);
        mListMain = (ListView) findViewById(R.id.list_main);
        initializeVideoView();
        hookDraggableViewListener();
        initListSub();
        initListMain();
    }

    private void initializeVideoView() {
        Uri video = Uri.parse("android.resource://" + getPackageName() + "/"
                + R.raw.video);
        videoView.setVideoURI(video);
        videoView.start();
    }

    private void initListSub() {
        ArrayList<String> listItems = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            listItems.add("Demo :" + i);
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        mListSub.setAdapter(adapter);
    }

    private void initListMain() {
        ArrayList<String> listItems = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            listItems.add("Main :" + i);
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        mListMain.setAdapter(adapter);
    }

    private void hookDraggableViewListener() {
        draggableView.setDraggableListener(new DraggableListener() {
            @Override
            public void onMaximized() {
                startVideo();
                Log.e("STATUS", "onMaximized");
            }

            //Empty
            @Override
            public void onMinimized() {
                //Empty
                Log.e("STATUS", "onMinimized");
            }

            @Override
            public void onClosedToLeft() {
                pauseVideo();
                Log.e("STATUS", "onClosedToLeft");
            }

            @Override
            public void onClosedToRight() {
                Log.e("STATUS", "onClosedToLeft");
                pauseVideo();
            }
        });
    }


    /**
     * Pause the VideoView content.
     */
    private void pauseVideo() {
        if (videoView.isPlaying()) {
            videoView.pause();
        }
    }

    /**
     * Resume the VideoView content.
     */
    private void startVideo() {
        if (!videoView.isPlaying()) {
            videoView.start();
        }
    }

}
