package cards.dumbgame;

import cards.common.*;
import java.util.ArrayList;
import java.util.List;

public class DumbGame implements Game {

    private final ArrayList<AIDumbPlayer> players = new ArrayList<>();
    private final ArrayList<AIDumbPlayer> playersOut = new ArrayList<>();
    private final List<Card> cardsInAction = new ArrayList<>();
    private Card trumpCard;
    private Suit trumpSuit;
    private AIDumbPlayer lastLooser;
    protected final Deck deck;

    public DumbGame() {
        deck = new DumbDeck();
        deck.shuffle();
    }

    @Override
    public void addPlayer(Player player) {
        this.players.add((AIDumbPlayer)player);
    }

    public AIDumbPlayer getLastLooser() {
        return lastLooser;
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
        printStartGameInfo();
        AIDumbPlayer mover = findFirstMover(trumpSuit);
        System.out.println(mover + " moves first");
        while (!gameIsOver()) {
            System.out.println("Round begins!");
            boolean cardsAreTaken = false; // shaker takes or not
            cardsInAction.clear();
            Card moverCard, shakerCard;
            AIDumbPlayer shaker = findShaker(mover);
            while ((moverCard = mover.move()) != null) {
                cardsInAction.add(moverCard);
                System.out.println(mover + " move: " + moverCard.getSymbol() + "(" + moverCard + ") Hand:" + mover.getHand());
                shakerCard = shaker.beat(moverCard);
                if (shakerCard == null) { // если вариантов нет то
                    System.out.println(shaker + " take: " + moverCard.getSymbol() + "(" + moverCard + ") Hand:" + shaker.getHand());
                    shaker.addCard(cardsInAction); // берем все карты со стола и перемещаем в 2
                    cardsAreTaken = true;
                    break;
                } else { // иначе
                    System.out.println(shaker + " beat: " + shakerCard.getSymbol() + "(" + shakerCard + ") Hand:" + shaker.getHand());
                    cardsInAction.add(shakerCard); // и положить ее на стол (покрыть)
                }
            }
            System.out.println("Round end! Cards left in deck: " + deck.getCardsRemaining() + " trump: " + trumpCard);
            fillPlayersHands(mover); // раздать карты из колоды если кому нехватает
            markWinners();
            mover = nextMover(mover, shaker, cardsAreTaken);
        }
        handleGameOver();
    }

    private void printStartGameInfo() {
        System.out.println("New Game Started!");
        System.out.println("Deck:" + deck);
        System.out.println("Cards left in deck: " + deck.getCardsRemaining());
        for (Player p: players)
            System.out.println(p + " hand:" + p.getHand());
        System.out.println("Trump: " + trumpCard);
    }

    protected AIDumbPlayer findFirstMover(Suit trump) {
        if (players.isEmpty())
            return null;
        AIDumbPlayer mover = players.get(0);
        for (AIDumbPlayer p: players)
            if (p.getHand().compareTo(mover.getHand(), trump) > 0)
                mover = p;
        return mover;
    }

    private boolean gameIsOver() {
        return (deck.getCardsRemaining() == 0 && countPlayersWithCards() < 2);
    }

    protected int countPlayersWithCards() {
        int count = 0;
        for (Player p: players)
            if (!(p.isEmpty()))
                count++;
        return count;
    }

    protected AIDumbPlayer findShaker(AIDumbPlayer mover) {
        ArrayList<AIDumbPlayer> playersSorted = getPlayersListStartingFrom(mover);
        playersSorted.remove(0);
        for (AIDumbPlayer p: playersSorted)
            if (!(playersOut.contains(p)))
                return p;
        return null;
    }

    protected void fillPlayersHands(AIDumbPlayer first) {
        ArrayList<AIDumbPlayer> playersSorted = getPlayersListStartingFrom(first);
        for (AIDumbPlayer player: playersSorted)
            while (deck.haveCardsToDeal() &&  player.numberOfCards() < 6)
                player.addCard(deck.deal());
        if (null != trumpCard) { // trump card is not yet dealt
            for (AIDumbPlayer player : playersSorted) {
                if (player.numberOfCards() < 6) {
                    player.addCard(trumpCard);
                    trumpCard = null;
                    break;
                }
            }
        }
    }

    private ArrayList<AIDumbPlayer> getPlayersListStartingFrom(AIDumbPlayer first) {
        ArrayList<AIDumbPlayer> playersSorted = new ArrayList<>();
        int index = players.indexOf(first);
        playersSorted.add(first);
        playersSorted.addAll(players.subList(index+1, players.size()));
        playersSorted.addAll(players.subList(0, index));
        return playersSorted;
    }

    private void markWinners() {
        for (AIDumbPlayer p: players)
            if (p.isEmpty())
                playersOut.add(p);
    }

    private AIDumbPlayer nextMover(AIDumbPlayer mover, AIDumbPlayer shaker, boolean cardsTaken) {
        ArrayList<AIDumbPlayer> sortedPlayers = getPlayersListStartingFrom(mover);
        sortedPlayers.remove(mover);
        if (cardsTaken)
            sortedPlayers.remove(shaker);
        for (AIDumbPlayer p: sortedPlayers)
            if (!(playersOut.contains(p))) {
                mover = p;
                break;
            }
        return mover;
    }

    private void handleGameOver() {
            System.out.println("Game Over!");
            if (countPlayersWithCards() == 0) {
                System.out.println("Game Draw!");
            } else {
                for (AIDumbPlayer p: players)
                    if (!(playersOut.contains(p))) {
                        lastLooser = p;
                        System.out.println("Looser: " + p + " " + p.getHand());
                    }
            }
    }

    protected List<Card> getCardsInAction() {
        List<Card> cards = new ArrayList<>(cardsInAction);
        return cards;
    }

    protected Suit getTrumpSuit() {
        return trumpSuit;
    }

    protected int getPlayersCount() {
        return players.size();
    }
}
