package cards.dumbgame;

import cards.common.*;
import java.util.ArrayList;
import java.util.List;

public class DumbGame implements Game {

    protected final Deck deck;
    private final List<Card> cardsInAction = new ArrayList<>();
    private Card trumpCard;
    private Suit trumpSuit;

    private final ArrayList<DumbPlayer> players = new ArrayList<>();
    private final ArrayList<DumbPlayer> playersOut = new ArrayList<>();

    public static DumbPlayer lastLooser;

    public DumbGame() {
        deck = new DumbDeck();
        deck.shuffle();
    }

    @Override
    public void addPlayer(Player player) {
        this.players.add((DumbPlayer)player);
    }

    public int countPlayersWithCards() {
        int count = 0;
        for (Player p: players)
            if (!(p.isEmpty()))
                count++;
        return count;
    }

    public DumbPlayer findFirstMover(Suit trump) {
        if (players.isEmpty())
            return null;
        DumbPlayer mover = players.get(0);
        for (DumbPlayer p: players)
            if (p.getHand().compareTo(mover.getHand(), trump) > 0)
                mover = p;
        return mover;
    }

    protected DumbPlayer findShaker(DumbPlayer mover) {
        ArrayList<DumbPlayer> playersSorted = getPlayersSortedByFirst(mover);
        playersSorted.remove(0);
        for (DumbPlayer p: playersSorted)
            if (!(playersOut.contains(p)))
                return p;
        return null;
    }

    private void init() {
        fillPlayersHands(players.get(0));
        trumpCard = deck.deal();
        trumpSuit = trumpCard.getSuit();
        lastLooser = null;
    }

    @Override
    public void reset() {
        for (Player p: players)
            p.clearHand();
        playersOut.clear();
        cardsInAction.clear();
        deck.restore();
        deck.shuffle();
    }

    @Override
    public void play() {
        init();

        // TODO extract method
        System.out.println("New Game Started!");
        System.out.println("Deck:" + deck);
        System.out.println("Cards left in deck: " + deck.getCardsRemaining());
        for (Player p: players)
            System.out.println(p + " hand:" + p.getHand());
        System.out.println("Trump: " + trumpCard);

        DumbPlayer mover = findFirstMover(trumpSuit);

        System.out.println(mover + " moves first");

        while (true) {
        System.out.println("Round begins!");
            boolean cardsTaken = false; // shaker takes or not
            cardsInAction.clear();
            Card moverCard, shakerCard;
            DumbPlayer shaker = findShaker(mover);

            while ((moverCard = mover.move()) != null) {
                // ходим пока у 1 есть карты для хода
                cardsInAction.add(moverCard);
                System.out.println(mover + " move: " + moverCard.getSymbol() + "(" + moverCard + ") Hand:" + mover.getHand());
                shakerCard = shaker.beat(moverCard);
                // найти карту для покрытия у 2
                if (shakerCard == null) { // если вариантов нет то
                    System.out.println(shaker + " take: " + moverCard.getSymbol() + "(" + moverCard + ") Hand:" + shaker.getHand());
                    shaker.addCard(cardsInAction); // берем все карты со стола и перемещаем в 2
                    cardsTaken = true;
                    break;
                } else { // иначе
                    System.out.println(shaker + " beat: " + shakerCard.getSymbol() + "(" + shakerCard + ") Hand:" + shaker.getHand());
                    cardsInAction.add(shakerCard); // и положить ее на стол (покрыть)
                }
            }
            System.out.println("Round end! Cards left in deck: " + deck.getCardsRemaining() + " trump: " + trumpCard);
            fillPlayersHands(mover); // раздать карты из колоды если кому нехватает

            // find out players if any
            for (DumbPlayer p: players)
                if (p.isEmpty())
                    playersOut.add(p);

            // if game over, find looser
            int playersWithCards = countPlayersWithCards();
            if (deck.getCardsRemaining() == 0 && playersWithCards < 2) {
                System.out.println("Game Over!");
                if (playersWithCards == 0) {
                    System.out.println("Game Draw!");
                } else {
                    for (DumbPlayer p: players)
                        if (!(playersOut.contains(p))) {
                            lastLooser = p;
                            System.out.println("Looser: " + p + " " + p.getHand());
                        }
                }
                return;
            }

            // change player
            ArrayList<DumbPlayer> sortedPlayers = getPlayersSortedByFirst(mover);
            sortedPlayers.remove(0);
            if (cardsTaken) {
                for (DumbPlayer p: sortedPlayers)
                    if (!(p.equals(shaker)) && !(playersOut.contains(p))) {
                        mover = p;
                        break;
                    }
            }
            else {
                for (DumbPlayer p: sortedPlayers)
                    if (!(playersOut.contains(p))) {
                        mover = p;
                        break;
                    }
            }
        }
    }

    protected void fillPlayersHands(DumbPlayer first) {
        ArrayList<DumbPlayer> playersSorted = getPlayersSortedByFirst(first);
        //System.out.println("Sorted players (first is " + first.toString() + "): " + playersSorted.toString());
        for (DumbPlayer player: playersSorted)
            // TODO implement something like deck.isEmpty()
            while (deck.getCardsRemaining() > 0 &&  player.numberOfCards() < 6)
                player.addCard(deck.deal());
        if (null != trumpCard) {
            for (DumbPlayer player : playersSorted) {
                if (player.numberOfCards() < 6) {
                    player.addCard(trumpCard);
                    trumpCard = null;
                    break;
                }
            }
        }
    }

    protected ArrayList<DumbPlayer> getPlayersSortedByFirst(DumbPlayer first) {
        // TODO needs refactoring
        ArrayList<DumbPlayer> playersSorted = new ArrayList<>();
        int index = players.indexOf(first);
        if (first.equals(players.get(0)))
            playersSorted.addAll(players);
        else if (first.equals(players.get(players.size()-1))) {
            playersSorted.add(first);
            playersSorted.addAll(players.subList(0, index));
        }
        else {
            playersSorted.add(first);
            playersSorted.addAll(players.subList(index+1, players.size()));
            playersSorted.addAll(players.subList(0, index));
        }
        return playersSorted;
    }

    public List<Card> getCardsInAction() {
        List<Card> cards = new ArrayList<>(cardsInAction);
        return cards;
    }

    public Suit getTrumpSuit() {
        return trumpSuit;
    }

    public int getPlayersCount() {
        return players.size();
    }
}