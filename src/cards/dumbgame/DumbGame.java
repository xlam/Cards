package cards.dumbgame;

import cards.common.*;
import java.util.ArrayList;
import java.util.List;

public class DumbGame implements Game {

    private final Deck deck;
    private final List<Card> cardsInAction = new ArrayList<>();
    private DumbPlayer player1;
    private DumbPlayer player2;
    private Card trumpCard;
    private Suit trumpSuit;

    public static String lastWinner = "";

    public DumbGame() {
        deck = new DumbDeck();
        deck.shuffle();
    }

    @Override
    public void addPlayer(Player player) {
        if (null == player1)
            player1 = (DumbPlayer)player;
        else if (null == player2)
            player2 = (DumbPlayer)player;
    }

    private void init() {
        for (int i = 0; i < 6; i++) {
            player1.addCard(deck.deal());
            player2.addCard(deck.deal());
        }
        trumpCard = deck.deal();
        trumpSuit = trumpCard.getSuit();
    }

    @Override
    public void reset() {
        player1.clearHand();
        player2.clearHand();
        cardsInAction.clear();
        deck.restore();
        deck.shuffle();
    }

    @Override
    public void play() {
        init();
        System.out.println("New Game Started!");
        System.out.println("Deck:" + deck);
        System.out.println("Cards left in deck: " + deck.getCardsRemaining());
        System.out.println(player1 + " hand:" + player1.getHand());
        System.out.println(player2 + " hand:" + player2.getHand());
        System.out.println("Trump: " + trumpCard);

        DumbPlayer mover, shaker;
        if (player1.getHand().compareTo(player2.getHand(), trumpSuit) > 0) {
            mover = player1;
            shaker = player2;
        } else {
            mover = player2;
            shaker = player1;
        }

        System.out.println(mover + " moves first");

        while (true) {
        System.out.println("Round begins!");
            boolean swapPlayers = true;
            cardsInAction.clear();
            Card moverCard;
            Card shakerCard;

            while ((moverCard = mover.move()) != null) {
                // ходим пока у 1 есть карты для хода
                cardsInAction.add(moverCard);
                System.out.println(mover + " move: " + moverCard.getSymbol() + "(" + moverCard + ")");
                shakerCard = shaker.beat(moverCard);
                // найти карту для покрытия у 2
                if (shakerCard == null) { // если вариантов нет то
                    System.out.println(shaker + " take: " + moverCard.getSymbol() + "(" + moverCard + ")");
                    shaker.addCard(cardsInAction); // берем все карты со стола и перемещаем в 2
                    swapPlayers = false; // ходит снова 1
                    break;
                } else { // иначе
                    System.out.println(shaker + " beat: " + shakerCard.getSymbol() + "(" + shakerCard + ")");
                    cardsInAction.add(shakerCard); // и положить ее на стол (покрыть)
                    swapPlayers = true; // на случай если отбились ходит 2
                }
            }
            System.out.println("Round end! Cards left in deck: " + deck.getCardsRemaining());
            fillHands(mover, shaker); // раздать карты из колоды если кому нехватает

            if ((mover.isEmpty() || shaker.isEmpty()) && deck.getCardsRemaining() == 0) {
                System.out.println("Game Over!");
                if (mover.numberOfCards() == 0 && shaker.numberOfCards() == 0) {
                    System.out.println("Game Draw!");
                } else if (shaker.numberOfCards() == 0) {
                    lastWinner = shaker.toString();
                    System.out.println("Winner: " + shaker + " " + shaker.getHand());
                    System.out.println("Looser: " + mover + " " + mover.getHand());
                } else {
                    lastWinner = mover.toString();
                    System.out.println("Winner: " + mover + " " + mover.getHand());
                    System.out.println("Looser: " + shaker + " " + shaker.getHand());
                }
                return;
            }

            if (swapPlayers) { // меняем ходящего 2 отбился
                DumbPlayer temp = mover;
                mover = shaker;
                shaker = temp;
            }
        }
    }

    private void fillHands(DumbPlayer mover, DumbPlayer shaker) {
        if (mover.numberOfCards() < 6) {
            while (deck.getCardsRemaining() > 0 && mover.numberOfCards() < 6)
                mover.addCard(deck.deal());
        }
        if (shaker.numberOfCards() < 6) {
            while (deck.getCardsRemaining() > 0 && shaker.numberOfCards() < 6)
                shaker.addCard(deck.deal());
        }
        if (deck.getCardsRemaining() == 0 && trumpCard != null) {
            if (mover.numberOfCards() < 6) {
                mover.addCard(trumpCard);
                trumpCard = null;
            } else if (shaker.numberOfCards() < 6) {
                shaker.addCard(trumpCard);
                trumpCard = null;
            }
        }
    }

    public List<Card> getCardsInAction() {
        List<Card> cards = new ArrayList<>(cardsInAction);
        return cards;
    }

    public Suit getTrumpSuit() {
        return trumpSuit;
    }

    public int getPlayersCount() {
        // TODO Implement getPlayersCount()
        return 2;
    }
}
