package se.miun.jasv2000.dt031g.dialer;



import android.content.Context;
import android.media.SoundPool;

import androidx.appcompat.app.AppCompatActivity;

public class SoundPlayer extends AppCompatActivity {
    private static SoundPlayer soundPlayerInstance;
    private static SoundPool soundPool;
    private static int soundZero;
    private static int soundOne;
    private static int soundTwo;
    private static int soundThree;
    private static int soundFour;
    private static int soundFive;
    private static int soundSix;
    private static int soundSeven;
    private static int soundEight;
    private static int soundNine;
    private static int soundPound;
    private static int soundStar;

    private SoundPlayer(Context context) {

        soundPool = new SoundPool.Builder()
                .setMaxStreams(3)
                .build();
        soundZero = soundPool.load(context, R.raw.zero, 1);
        soundOne = soundPool.load(context, R.raw.one, 1);
        soundTwo = soundPool.load(context, R.raw.two, 1);
        soundThree = soundPool.load(context, R.raw.three, 1);
        soundFour = soundPool.load(context, R.raw.four, 1);
        soundFive = soundPool.load(context, R.raw.five, 1);
        soundSix = soundPool.load(context, R.raw.six, 1);
        soundSeven = soundPool.load(context, R.raw.seven, 1);
        soundEight = soundPool.load(context, R.raw.eight, 1);
        soundNine = soundPool.load(context, R.raw.nine, 1);
        soundPound = soundPool.load(context, R.raw.pound, 1);
        soundStar = soundPool.load(context, R.raw.star, 1);

    }

    public static SoundPlayer getInstance(Context context) {

        if (soundPlayerInstance == null) {
            soundPlayerInstance = new SoundPlayer(context);
        }
        return soundPlayerInstance;
    }


    public static void playSound(DialpadButton dialpadButton){

        getInstance(dialpadButton.getContext());
        String title = dialpadButton.getTitle();

        switch(title) {
            case "0":
                soundPool.play(soundZero, 1f, 1f, 1, 0, 1);
                break;
            case "1":
                soundPool.play(soundOne, 1f, 1f, 1, 0, 1);
                break;
            case "2":
                soundPool.play(soundTwo, 1f, 1f, 1, 0, 1);
                break;
            case "3":
                soundPool.play(soundThree, 1f, 1f, 1, 0, 1);
                break;
            case "4":
                soundPool.play(soundFour, 1f, 1f, 1, 0, 1);
                break;
            case "5":
                soundPool.play(soundFive, 1f, 1f, 1, 0, 1);
                break;
            case "6":
                soundPool.play(soundSix, 1f, 1f, 1, 0, 1);
                break;
            case "7":
                soundPool.play(soundSeven, 1f, 1f, 1, 0, 1);
                break;
            case "8":
                soundPool.play(soundEight, 1f, 1f, 1, 0, 1);
                break;
            case "9":
                soundPool.play(soundNine, 1f, 1f, 1, 0, 1);
                break;
            case "#":
                soundPool.play(soundPound, 1f, 1f, 1, 0, 1);
                break;
            case "✻":
                soundPool.play(soundStar, 1f, 1f, 1, 0, 1);
                break;
            default:
                break;
        }


    }
    public static void destroy() {

        soundPool.release();
        soundPool = null;
        soundPlayerInstance = null;
    }

}
