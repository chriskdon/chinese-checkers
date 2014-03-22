package ca.brocku.chinesecheckers;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.preference.PreferenceManager;

/**
 * Created by Main on 3/20/14.
 */
public class BoomBoomMusic {

    static MediaPlayer mp;
    static SoundPool sp;
    private static Boolean started = false;
    private static Boolean loaded = false;
    private static int piecepopsound;
    final static String BACKSOUNDPREF = "BackgroundSoundPref";
    final static String EFFCTSOUNDPREF = "EffectsSoundPrefs";
    private static SharedPreferences sharedPrefs;

    public static void start(Context c) {
        if (started && !mp.isPlaying()) {
            mp.start();
        } else if (!started) {
            sharedPrefs = PreferenceManager.getDefaultSharedPreferences(c);
            setupMP(c);
            setupSP(c);
        }
    }

    private static void setupMP(Context c) {
        mp = MediaPlayer.create(c, R.raw.eis);
        mp.setVolume(sharedPrefs.getInt(BACKSOUNDPREF, 100) / 100.0f, sharedPrefs.getInt(BACKSOUNDPREF, 100) / 100.0f);
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mp.setLooping(true);
        mp.start();
        started = true;
    }

    private static void setupSP(Context c) {
        sp = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
            }
        });
        piecepopsound = sp.load(c, R.raw.piecepopsound, 1); //http://soundbible.com/2067-Blop.html
    }

    public static void setBackgroundVolume(int setTo) {
        if (mp != null) {
            mp.setVolume(setTo / 100.0f, setTo / 100.0f);
        }
    }

    public static void pause() {
        if (mp != null && mp.isPlaying()) {
            mp.pause();
        }
    }

    public static void onPieceTap() {
        sp.play(piecepopsound, sharedPrefs.getInt(EFFCTSOUNDPREF, 100) / 100.0f, sharedPrefs.getInt(EFFCTSOUNDPREF, 100) / 100.0f, 1, 0, 1f);
    }

    public static void stop() {
        mp.stop();
        started = false;
    }
}
