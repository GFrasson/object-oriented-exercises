package br.ufjf.dcc.dcc025.bingo;

public class Player {
    private Card card;
    private final int PLAYER_ID;
    private boolean winner;

    public Player(int playerId) {
        this.PLAYER_ID = playerId;
        this.card = new Card(this.PLAYER_ID);
        this.winner = false;
    }

    // --------------- Getters ---------------

    public Card getCard() {
        return this.card;
    }

    public boolean isWinner() {
        return this.winner;
    }

    // --------------- Setters ---------------

    public void setWinner() {
        this.winner = true;
    }
}
