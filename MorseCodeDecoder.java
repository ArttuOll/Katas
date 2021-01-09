public class MorseCodeDecoder {

    /*
    Tehtävänanto

    Muuta annettu morsekoodattu viesti selkokieliseksi. Morsekirjaimet ja niitä vastaavat aakkoset
    on annettu valmiiksi olion MorseCode kautta.

    Esimerkki: viestistä ".... . -.--   .--- ..- -.. ." kuuluisi tulla selkokielinen viesti
    "HEY JUDE".

    Aikavaativuus

    Morseviesti käydään useamman kerran kokonaan läpi, esimerkiksi korvattaessa välilyönnit
    v-kirjaimilla ja jaettaessa viestiä taulukkoon välilyöntien kohdalta. Kuitenkin, kaikki
    läpikäymiset ovat peräkkäisiä, joten aikavaativuus on yksinkertaisesti O(n), missä n viestissä
    olevien morsekirjainten määrä.
     */

    /**
     * Muuttaa annetun morsekoodin selkokieliseksi.
     * @param morseCode Muutettava koodi.
     * @return Selkokielinen viesti.
     */
    public static String decode(String morseCode) {
        // Korvataan viestissä olevat välilyönnit (eli kolme peräkkäistä välilyöntiä) kirjaimella v.
        morseCode = morseCode.strip().replaceAll(" {3}", " v ");
        String[] morseSymbols = morseCode.split(" ");
        return decodeSymbols(morseSymbols);
    }

    private static String decodeSymbols(String[] morseSymbols) {
        StringBuilder message = new StringBuilder();
        for (String morseSymbol : morseSymbols) {
            if (morseSymbol.equals("v")) {
                message.append(" ");
            } else {
                message.append(MorseCode.get(morseSymbol));
            }
        }
        return message.toString();
    }
}
