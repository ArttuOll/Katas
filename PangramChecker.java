package com.bsuuv;

import java.util.HashSet;
import java.util.Set;

/**
 * Tehtävänanto
 *
 * Kirjoita metodi, joka tarkistaa, onko annettu merkkijono pangrammi, eli merkkijono, joka sisältää
 * (perinteisten) aakkosten jokaisen kirjaimen.
 *
 * Aikavaativuus
 *
 * Algoritmi muuttaa ensin merkkijonon joukoksi, joka koostuu merkkijonon merkeistä. Sitten
 * tarkastetaan, sisältääkö tämä joukko jokaisen aakkosen.
 *
 * Merkkijonon muuttaminen joukoksi on vakioaikainen operaatio, kun käytetään HashSettiä (jokainen
 * add-operaatio vie vakioajan). Jokaisen aakkosen etsiminen tästä joukosta on myös vakioaikainen
 * operaatio (HashSetin contains-operaatio on vakioaikainen). Kokonaisuudessaan algoritmin
 * aikavaativuus on siis O(n), missä n on tarkastellun merkkijonon pituus.
 */
public class PangramChecker {

    private static final int ASCII_LOWERCASE_LOWER_BOUND = 97;
    private static final int ASCII_LOWERCASE_UPPER_BOUND = 122;

    /**
     * Tarkastaa, onko annettu merkkijono pangrammi.
     * @param sentence Merkkijono, jota tarkastellaan.
     * @return true, jos merkkijono on pangrammi, false, jos ei.
     */
    public boolean check(String sentence) {
        sentence = sentence.toLowerCase();
        HashSet<Character> individualCharacters = getCharactersAsSet(sentence);
        return setContainsWholeAlphabet(individualCharacters);
    }

    private HashSet<Character> getCharactersAsSet(String string) {
        var characters = new HashSet<Character>();
        for (int i = 0; i < string.length(); i++) {
            characters.add(string.charAt(i));
        }
        return characters;
    }

    private boolean setContainsWholeAlphabet(Set<Character> characters) {
        if (characters.isEmpty()) return false;
        for (int i = ASCII_LOWERCASE_LOWER_BOUND; i <= ASCII_LOWERCASE_UPPER_BOUND; i++) {
            // Muutetaan ASCII-taulukon indeksi sitä vastaavaksi kirjaimeksi
            char asciiLowercaseChar = (char) i;
            if (characterMissing(characters, asciiLowercaseChar)) {
                return false;
            }
        }
        return true;
    }

    private boolean characterMissing(Set<Character> characters, char character) {
        return !characters.contains(character);
    }
}
