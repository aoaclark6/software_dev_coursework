class threadClass extends Thread {
    private player player;

    threadClass(player player) {
        this.player = player;
    }

    public void run() {
        System.out.println(player.playerCards[0].cardValue);


//                        if (player.playerCards[0].cardValue
//                                .equals(player.playerCards[1].cardValue
//                                        .equals(player.playerCards[2].cardValue
//                                                .equals(player.playerCards[3].cardValue)))){
//                            System.out.println("Player has won");
//                        }
            }

        }

