package ca.brocku.chinesecheckers.computerplayer;

import android.os.Parcel;
import android.os.Parcelable;

import javajar.computerplayer.*;


/**
 * A potential move to be made by a computer player.
 *
 * Author: James Kostiuk
 * Student #: 4366340
 * Date: 2/24/2014
 */
public class AndroidAiPlayer extends javajar.computerplayer.AiPlayer implements Parcelable{

    public AndroidAiPlayer(String AILevel, PlayerColor playerColor){
        super(AILevel,playerColor);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Revive
     *
     * @param parcel
     */
    private AndroidAiPlayer(Parcel parcel) {
        this(parcel.readString(), PlayerColor.valueOf(parcel.readString()));
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(AILevel);
        dest.writeString(getPlayerColor().toString());
    }

    /**
     * Recreate this instance
     */
    public static final Parcelable.Creator<AndroidAiPlayer> CREATOR =
            new Parcelable.Creator<AndroidAiPlayer>() {

                public AndroidAiPlayer createFromParcel(Parcel in) { return new AndroidAiPlayer(in); }

                public AndroidAiPlayer[] newArray(int size) { return new AndroidAiPlayer[size]; }
            };
}
