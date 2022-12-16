package se.miun.jasv2000.dt031g.dialer;



import android.content.Context;
import android.media.SoundPool;
import android.os.Environment;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class SoundPlayer extends AppCompatActivity {
    public static SoundPlayer soundPlayerInstance;
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
    private String voiceName;

    String default_voice = Environment.getDataDirectory() + "/data/se.miun.jasv2000.dt031g.dialer/files/voices/";


    private SoundPlayer(Context context) {
        voiceName = MainActivity.voiceName;
        default_voice = default_voice + voiceName + "/";


        soundPool = new SoundPool.Builder()
                .setMaxStreams(3)
                .build();
        soundZero = soundPool.load(default_voice + "zero.mp3", 1);
        soundOne = soundPool.load(default_voice + "one.mp3", 1);
        soundTwo = soundPool.load(default_voice + "two.mp3", 1);
        soundThree = soundPool.load(default_voice + "three.mp3", 1);
        soundFour = soundPool.load(default_voice + "four.mp3", 1);
        soundFive = soundPool.load(default_voice + "five.mp3", 1);
        soundSix = soundPool.load(default_voice + "six.mp3", 1);
        soundSeven = soundPool.load(default_voice + "seven.mp3", 1);
        soundEight = soundPool.load(default_voice + "eight.mp3", 1);
        soundNine = soundPool.load(default_voice + "nine.mp3", 1);
        soundPound = soundPool.load(default_voice + "pound.mp3", 1);
        soundStar = soundPool.load(default_voice + "star.mp3", 1);

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
