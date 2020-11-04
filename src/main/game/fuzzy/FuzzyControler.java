package main.game.fuzzy;

import main.game.Board;
import main.game.Snake;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.rule.FuzzyRuleSet;
import net.sourceforge.jFuzzyLogic.rule.Variable;

import java.awt.event.KeyEvent;

public class FuzzyControler {

    private Board board;
    private FuzzyRuleSet fuzzyRuleSet;

    public FuzzyControler(Board b){
        this.board = b;

        FIS fis = FIS.load("fuzzy_snake.fcl", false);
        this.fuzzyRuleSet = fis.getFuzzyRuleSet();
    }

    public int getEvaluation(){
        fuzzyRuleSet.setVariable("przeszkoda_prawo", board.getSnakeDistanceFromObject(board.getSnake().getOrientation().getNext()));
        fuzzyRuleSet.setVariable("przeszkoda_lewo", board.getSnakeDistanceFromObject(board.getSnake().getOrientation().getPrevious()));
        fuzzyRuleSet.setVariable("przeszkoda_przod", board.getSnakeDistanceFromObject(board.getSnake().getOrientation()));

        fuzzyRuleSet.evaluate();

        double decision = fuzzyRuleSet.getVariable("zmiana_kierunku").getLatestDefuzzifiedValue();

        System.out.println(decision);

        if(decision >= 0 && decision <= 0.66){
            return KeyEvent.VK_LEFT;
        }
        if(decision <=2 && decision >= 1.34){
            return KeyEvent.VK_RIGHT;
        }

        return 0;
    }

}
