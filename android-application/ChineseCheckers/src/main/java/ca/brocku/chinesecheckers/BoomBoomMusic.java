package ca.brocku.chinesecheckers;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

/**
 * Created by Main on 3/20/14.
 */
public class BoomBoomMusic {

    static MediaPlayer mp;
    private static Boolean started = false;

    public static void start(Context c) {
        if (started && !mp.isPlaying()) {
            mp.start();
        } else if (!started) {
            iniSound(c);
        }
    }

    private static void iniSound(Context c) {
        mp = MediaPlayer.create(c, R.raw.eis);
        mp.setVolume(100.0f, 100.0f);
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mp.setLooping(true);
        mp.start();
        started = true;
    }

    private static void setVolume(float setTo) {
        mp.setVolume(setTo, setTo);
    }

    public static void pause() {
        mp.pause();
    }

    public static void stop() {
        mp.stop();
        started = false;
    }
}
