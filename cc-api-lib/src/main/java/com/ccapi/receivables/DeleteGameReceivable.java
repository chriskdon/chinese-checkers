package com.ccapi.receivables;

/**
 * @author kubasub
 * @date April 02, 2014
 */
public class DeleteGameReceivable extends GameDetailsReceivable {
    public boolean isDeleted;


    public DeleteGameReceivable() {}

    public DeleteGameReceivable(long gameId, boolean isDeleted) {
        this.gameId = gameId;
        this.isDeleted = isDeleted;
    }
}
