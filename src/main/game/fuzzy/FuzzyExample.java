package main.game.fuzzy;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.rule.FuzzyRuleSet;

public class FuzzyExample {

    public static void main(String[] args) throws Exception {
        try {
            String fileName = args[0];
            int odleglosc_prawo = Integer.parseInt(args[1]);
            int odleglosc_lewo = Integer.parseInt(args[2]);
            int odleglosc_przod = Integer.parseInt(args[3]);

            FIS fis = FIS.load(fileName,false);

//wyswietl wykresy funkcji fuzyfikacji i defuzyfikacji
            FuzzyRuleSet fuzzyRuleSet = fis.getFuzzyRuleSet();
            fuzzyRuleSet.chart();

//zadaj wartosci wejsciowe
            fuzzyRuleSet.setVariable("przeszkoda_prawo", odleglosc_prawo);
            fuzzyRuleSet.setVariable("przeszkoda_lewo", odleglosc_lewo);
            fuzzyRuleSet.setVariable("przeszkoda_przod", odleglosc_przod);
//logika sterownika
            fuzzyRuleSet.evaluate();

//graficzna prezentacja wyjscia
            fuzzyRuleSet.getVariable("zmiana_kierunku").chartDefuzzifier(true);
            System.out.println(fuzzyRuleSet.getVariable("zmiana_kierunku").getLatestDefuzzifiedValue());

//System.out.println(fuzzyRuleSet);

        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Niepoprawna liczba parametrow. Przyklad: java FuzzyExample string<plik_fcl> int<poziom natezenia> int<pora dnia>");
        } catch (NumberFormatException ex) {
            System.out.println("Niepoprawny parametr. Przyklad: java FuzzyExample string<plik_fcl> int<poziom natezenia> int<pora dnia>");
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

}
