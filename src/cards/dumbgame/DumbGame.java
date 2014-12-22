package cards.dumbgame;

import cards.Logger;
import cards.common.*;
import java.util.ArrayList;
import java.util.List;

public class DumbGame implements Game {

    // TODO: is it too many responsibilities?

    private final List<DumbPlayer> players = new ArrayList();
    private final List<DumbPlayer> playersOut = new ArrayList();
    private final List<Card> cardsInAction = new ArrayList();
    private final DumbService service;
    private Card trumpCard;
    private Suit trumpSuit;
    protected DumbPlayer mover;
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

    @Override
    public void play() {
        init();
        while (!gameIsOver())
            playRound();
        handleGameOver();
    }

    private void init() {
        dealCardsStartingFrom(players.get(0));
        trumpCard = deck.deal();
        trumpSuit = trumpCard.getSuit();
        looser = null;
        mover = findFirstMover(trumpSuit);
        printStartGameInfo();
    }

    private boolean gameIsOver() {
        return (!deck.haveCardsToDeal() && countPlayersWithCards() < 2);
    }

    private void playRound() {
        Logger.log("Round begins!");
        boolean shakerDoesNotTake = true;
        cardsInAction.clear();
        shaker = findShakerNextTo(mover);
        Card moverCard;
        do {
            moverCard = mover.move(cardsInAction, trumpSuit);
            if (moverCard == null)
                break;
            cardsInAction.add(moverCard);
            Logger.log(mover + " move: " + moverCard.getSymbol() + "\tHand:" + mover.getHand());
        }
        while (shakerDoesNotTake = shakerBeat(moverCard) && !(shaker.isEmpty()));
        Logger.log("Round end! Cards left in deck: " + deck.getCardsRemaining() + " trump: " + trumpCard);
        dealCardsStartingFrom(mover);
        markWinners();
        mover = nextMover(shakerDoesNotTake);
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
        Logger.log("New Game Started!");
        Logger.log("Deck:" + deck);
        Logger.log("Cards left in deck: " + deck.getCardsRemaining());
        for (Player p: players)
            Logger.log(p + " hand:" + p.getHand());
        Logger.log("Trump: " + trumpCard);
        Logger.log(mover + " moves first");
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

    protected int countPlayersWithCards() {
        int count = 0;
        for (Player p: players)
            if (!(p.isEmpty()))
                count++;
        return count;
    }

    protected DumbPlayer findShakerNextTo(DumbPlayer mover) {
        List<DumbPlayer> playersSorted = getPlayersListStartingFrom(mover);
        playersSorted.remove(0);
        for (DumbPlayer p: playersSorted)
            if (!(playersOut.contains(p)))
                return p;
        return null;
    }

    private List getPlayersListStartingFrom(DumbPlayer first) {
        List<DumbPlayer> playersSorted = new ArrayList();
        int index = players.indexOf(first);
        playersSorted.add(first);
        playersSorted.addAll(players.subList(index+1, players.size()));
        playersSorted.addAll(players.subList(0, index));
        return playersSorted;
    }

    private boolean shakerBeat(Card moverCard) {
        Card shakerCard = shaker.beat(moverCard, trumpSuit);
        if (shakerCard == null) {
            Logger.log(shaker + " take: " + moverCard.getSymbol() + "\tHand:" + shaker.getHand());
            shaker.addCard(cardsInAction);
            return false;
        } else {
            Logger.log(shaker + " beat: " + shakerCard.getSymbol() + "\tHand:" + shaker.getHand());
            cardsInAction.add(shakerCard);
            return true;
        }
    }

    protected void dealCardsStartingFrom(DumbPlayer first) {
        List<DumbPlayer> playersSorted = getPlayersListStartingFrom(first);
        for (DumbPlayer player: playersSorted)
            dealCardTo(player);
        dealTrumpIfNeeded(playersSorted);
    }

    private void dealCardTo(DumbPlayer player) {
        while (deck.haveCardsToDeal() &&  player.numberOfCards() < 6)
            player.addCard(deck.deal());
    }

    private void dealTrumpIfNeeded(List playersSorted) {
        if (deck.haveCardsToDeal() || trumpCardIsDealt())
            return;
        dealTrump(playersSorted);
    }

    private void dealTrump(List<DumbPlayer> playersSorted) {
        for (DumbPlayer player : playersSorted) {
            if (player.numberOfCards() < 6) {
                player.addCard(trumpCard);
                trumpCard = null;
                break;
            }
        }
    }

    private boolean trumpCardIsDealt() {
        return null == trumpCard;
    }

    private void markWinners() {
        for (DumbPlayer p: players)
            if (p.isEmpty())
                playersOut.add(p);
    }

    protected DumbPlayer nextMover(boolean shakerDoesNotTake) {
        List<DumbPlayer> sortedPlayers = getPlayersListStartingFrom(mover);
        sortedPlayers.remove(mover);
        if (!shakerDoesNotTake)
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
        Logger.log("Game Over!");
        if (looser == null)
            Logger.log("Game Draw!");
        else
            Logger.log("Looser: " + looser + " " + looser.getHand());
    }

    public DumbPlayer getLastLooser() {
        return looser;
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
