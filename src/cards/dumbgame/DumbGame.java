package cards.dumbgame;

import cards.common.*;
import java.util.ArrayList;
import java.util.List;

public class DumbGame implements Game {

    // TODO: is it too many responsibilities?

    private final ArrayList<DumbPlayer> players = new ArrayList();
    private final ArrayList<DumbPlayer> playersOut = new ArrayList();
    private final List<Card> cardsInAction = new ArrayList();
    private final DumbService service;
    private Card trumpCard;
    private Suit trumpSuit;
    private DumbPlayer mover;
    private DumbPlayer shaker;
    private DumbPlayer looser; // TODO handle this later
    protected final Deck deck;

    public DumbGame() {
        service = new DumbService();
        deck = new DumbDeck();
        deck.shuffle();
    }

    @Override
    public void addPlayer(Player player) {
        this.players.add((DumbPlayer)player);
    }

    public DumbPlayer getLastLooser() {
        return looser;
    }

    private void init() {
        dealCardsStartingFrom(players.get(0));
        trumpCard = deck.deal();
        trumpSuit = trumpCard.getSuit();
        looser = null;
        mover = findFirstMover(trumpSuit);
        printStartGameInfo();
    }

    protected DumbPlayer findFirstMover(Suit trumpSuit) {
        if (players.isEmpty())
            return null;
        mover = players.get(0);
        for (DumbPlayer p: players)
            if (service.compareHands(p.getHand(), mover.getHand(), trumpSuit) > 0)
                mover = p;
        return mover;
    }

    private void printStartGameInfo() {
        System.out.println("New Game Started!");
        System.out.println("Deck:" + deck);
        System.out.println("Cards left in deck: " + deck.getCardsRemaining());
        for (Player p: players)
            System.out.println(p + " hand:" + p.getHand());
        System.out.println("Trump: " + trumpCard);
        System.out.println(mover + " moves first");
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
        while (!gameIsOver())
            playRound();
        handleGameOver();
    }

    private boolean gameIsOver() {
        return (!deck.haveCardsToDeal() && countPlayersWithCards() < 2);
    }

    private void playRound() {
        System.out.println("Round begins!");
        boolean cardsAreTaken = false; // shaker takes or not
        cardsInAction.clear();
        Card moverCard;
        shaker = findShaker(mover);
        while ((moverCard = mover.move(cardsInAction, trumpSuit)) != null) {
            cardsInAction.add(moverCard);
            System.out.println(mover + " move: " + moverCard.getSymbol() + "(" + moverCard + ") Hand:" + mover.getHand());
            if (cardsAreTaken = shakerBeat(moverCard))
                break;
        }
        System.out.println("Round end! Cards left in deck: " + deck.getCardsRemaining() + " trump: " + trumpCard);
        dealCardsStartingFrom(mover);
        markWinners();
        mover = nextMover(cardsAreTaken);
    }

    protected int countPlayersWithCards() {
        int count = 0;
        for (Player p: players)
            if (!(p.isEmpty()))
                count++;
        return count;
    }

    protected DumbPlayer findShaker(DumbPlayer mover) {
        ArrayList<DumbPlayer> playersSorted = getPlayersListStartingFrom(mover);
        playersSorted.remove(0);
        for (DumbPlayer p: playersSorted)
            if (!(playersOut.contains(p)))
                return p;
        return null;
    }

    private ArrayList getPlayersListStartingFrom(DumbPlayer first) {
        ArrayList<DumbPlayer> playersSorted = new ArrayList();
        int index = players.indexOf(first);
        playersSorted.add(first);
        playersSorted.addAll(players.subList(index+1, players.size()));
        playersSorted.addAll(players.subList(0, index));
        return playersSorted;
    }

    private boolean shakerBeat(Card moverCard) {
        // TODO: exchange true and false to reflect method name
        Card shakerCard = shaker.beat(moverCard, trumpSuit);
        if (shakerCard == null) { // если вариантов нет то
            System.out.println(shaker + " take: " + moverCard.getSymbol() + "(" + moverCard + ") Hand:" + shaker.getHand());
            shaker.addCard(cardsInAction); // берем все карты со стола и перемещаем в 2
            return true;
        } else { // иначе
            System.out.println(shaker + " beat: " + shakerCard.getSymbol() + "(" + shakerCard + ") Hand:" + shaker.getHand());
            cardsInAction.add(shakerCard); // и положить ее на стол (покрыть)
            return false;
        }

    }

    protected void dealCardsStartingFrom(DumbPlayer first) {
        List<DumbPlayer> playersSorted = getPlayersListStartingFrom(first);
        for (DumbPlayer player: playersSorted)
            while (deck.haveCardsToDeal() &&  player.numberOfCards() < 6)
                player.addCard(deck.deal());
        if (null != trumpCard) { // trump card is not yet dealt
            for (DumbPlayer player : playersSorted) {
                if (player.numberOfCards() < 6) {
                    player.addCard(trumpCard);
                    trumpCard = null;
                    break;
                }
            }
        }
    }

    private void markWinners() {
        for (DumbPlayer p: players)
            if (p.isEmpty())
                playersOut.add(p);
    }

    private DumbPlayer nextMover(boolean cardsTaken) {
        List<DumbPlayer> sortedPlayers = getPlayersListStartingFrom(mover);
        sortedPlayers.remove(mover);
        if (cardsTaken)
            sortedPlayers.remove(shaker);
        for (DumbPlayer p: sortedPlayers)
            if (!(playersOut.contains(p))) {
                mover = p;
                break;
            }
        return mover;
    }

    private void handleGameOver() {
        for (DumbPlayer p: players)
            if (!(playersOut.contains(p)))
                looser = p;
        printGameOverInfo();
    }

    private void printGameOverInfo() {
        System.out.println("Game Over!");
        if (looser == null)
            System.out.println("Game Draw!");
        else
            System.out.println("Looser: " + looser + " " + looser.getHand());
    }

    protected List getCardsInAction() {
        List cards = new ArrayList(cardsInAction);
        return cards;
    }

    protected Suit getTrumpSuit() {
        return trumpSuit;
    }

    protected int getPlayersCount() {
        return players.size();
    }
}
