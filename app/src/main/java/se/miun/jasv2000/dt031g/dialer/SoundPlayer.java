package se.miun.jasv2000.dt031g.dialer;



import android.content.Context;
import android.media.SoundPool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class SoundPlayer extends AppCompatActivity {
    private static SoundPlayer soundPlayerInstance;
    private static SoundPool soundPool;
    private static int soundId;




    private SoundPlayer(Context context) {

        soundPool = new SoundPool.Builder()
                .setMaxStreams(3)
                .build();
        soundId = soundPool.load(getInstance(this), R.raw.eight, 1);
    }

    public static SoundPlayer getInstance(Context context) {
        if (soundPlayerInstance == null) {
            context = context.getApplicationContext();
            soundPlayerInstance = new SoundPlayer(context);
        }


        return soundPlayerInstance;

    }



    public static String playSound(DialpadButton dialpadButton){

        String title = dialpadButton.getTitle();

        return title;
    }
    public void destroy() {
        soundPool = null;
    }

}
