class threadClass extends Thread {
    private player player;

    threadClass(player player) {
        this.player = player;
    }

    public void run() {

            if (i==0) {
                if (player.playerCards[i].cardValue ==
                        player.playerCards[i + 1].cardValue
                        || player.playerCards[i + 2].cardValue
                        || player.playerCards[i + 3].cardValue) {

                            continue;
                            }
                else{
                    continue;
                }


                for (i = 0; i < 4; i++) {
                    if (player.playerCards[i].cardValue == player.playerIndex){
                        continue;
            }

        }
        System.out.println(task + " is being prepared by " +
                Thread.currentThread().getName());
    }
}