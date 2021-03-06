package core.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import core.action.ResolveTime;
import core.battlefield.Battlefield;
import core.battlefield.BattlefieldCreature;
import core.battlefield.ObjectStatus;
import core.controllers.utils.MessageController;
import utils.Constants;

public class TurnController {
    private int turnCounter;
    private int turnOrderCounter;
    private List<BattlefieldCreature> turnOrder;

    public TurnController(int turnCounter, int turnOrderCounter, List<BattlefieldCreature> turnOrder) {
        this.turnCounter = turnCounter;
        this.turnOrderCounter = turnOrderCounter;
        this.turnOrder = turnOrder;
    }

    public static TurnController forBattlefield(Battlefield battlefield) {
        return new TurnController(0, 0, generateTurnOrder(battlefield.getAllCreatures()));
    }

    private static List<BattlefieldCreature> generateTurnOrder(List<BattlefieldCreature> creatures) {
        List<BattlefieldCreature> turnOrder = new ArrayList<>(creatures);
        Collections.shuffle(turnOrder);
        turnOrder.sort((o1, o2) -> (-1) * (o1.getCurrentSpeed() - o2.getCurrentSpeed()));
        return turnOrder;
    }

    public void regenerateTurnOrder(int from) {
        List<BattlefieldCreature> head = new ArrayList<>();
        List<BattlefieldCreature> tail = new ArrayList<>();
        for (int i = 0; i < this.turnOrder.size(); i++) {
            if (i < from) {
                head.add(this.turnOrder.get(i));
            } else {
                tail.add(this.turnOrder.get(i));
            }
        }
        tail = generateTurnOrder(tail);
        this.turnOrder = Stream.concat(head.stream(), tail.stream()).collect(Collectors.toList());
    }

    public void regenerateTurnOrder() {
        regenerateTurnOrder(turnOrderCounter);
    }

    public String nextTurn() {
        this.regenerateTurnOrder();
        if (this.turnOrderCounter >= turnOrder.size()) {
            MessageController.print(this.turnCounter + ": ");
            this.turnOrderCounter = 0;
            this.turnCounter += 1;
            this.regenerateTurnOrder();
        }
        String message = "";
        if (!this.turnOrder.isEmpty()) {
            BattlefieldCreature nextCreature = this.turnOrder.get(this.turnOrderCounter);
            if (nextCreature.hasStatus(ObjectStatus.ALIVE) && !nextCreature.hasStatus(ObjectStatus.DEAD)) {
                message = makeTurn(nextCreature);
                if (!nextCreature.hasStatus(ObjectStatus.ALIVE) || nextCreature.hasStatus(ObjectStatus.DEAD)) {
                    message += nextCreature.getCreature().getName() + " dies!\n";
                }
            }
        }

        this.turnOrderCounter += 1;
        return message;
    }

    private String makeTurn(BattlefieldCreature creature) {
        if (!creature.hasStatus(ObjectStatus.ALIVE) || creature.hasStatus(ObjectStatus.DEAD)) {
            return "";
        }
        String message = creature.getBattleName() + " starts turn\n";
        if (Constants.PRINT_CREATURE_STATS_IN_BATTLE.value > 0) {
            message += creature.getShopView() + "\n";
        }
        message += ActionController.resolve(
                creature,
                ResolveTime.ON_START_TURN,
                ResolveTime.BEFORE_MAIN_PHASE,
                ResolveTime.ON_MAIN_PHASE,
                ResolveTime.AFTER_MAIN_PHASE,
                ResolveTime.ON_END_TURN
        );
        if (Constants.PRINT_CREATURE_STATS_IN_BATTLE.value > 0) {
            message += creature.getShopView() + "\n";
        }
        message += creature.getBattleName() + " ends turn\n";
        return message;
    }

    public void addCreatureToTurnOrder(BattlefieldCreature creature) {
        turnOrder.add(creature);
        regenerateTurnOrder(this.turnOrderCounter);
    }

    public void removeCreatureFromTurnOrder(BattlefieldCreature creature) {
        turnOrder.remove(creature);
        regenerateTurnOrder(this.turnOrderCounter);
    }

    public int getTurnCounter() {
        return turnCounter;
    }

    public List<BattlefieldCreature> getTurnOrder() {
        return turnOrder;
    }
}
