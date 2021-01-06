import kotlin.math.pow

/*
 Tehtävänanto

 Joillekin luvuille pätee seuraava: luvun numeroiden summa, korotettuina peräkkäisiin
 potensseihin, alkaen annetusta potenssista, on jaollinen luvulla itsellään. Esimerkkejä:
 89, potenssi 1 -> 8^1 + 8^2 = 89 * 1
 695, potenssi 2 -> 6^2 + 9^3 + 5^4 = 1390 = 695 * 2

 Tehtävänä on laskea ja palauttaa luku k (esimerkkien viimeiset luvut: 1 ja 2).

 Aikavaativuus

 Algoritmi muuttaa annetun luvun ensiksi numeroistaan koostuvaksi taulukoksi ja sen jälkeen
 laskee tässä taulukossa olevien numeroiden summan, jokainen numeroista korotettuna peräkkäisiin
 potensseihin. Jos tämä summa on jaollinen annetulla luvulla, palautetaan jaon tulos, muuten
 palautetaan -1.

 Luvun muuttaminen numerotaulukoksi on aikavaativuudeltaan O(n), sillä kyseessä on vain
 vakioaikaisen operaation suorittaminen taulukon jokaiselle alkiolle. Summan laskeminen on myös
 O(n) samasta syystä kuin luvun muuttaminen taulukoksikin. Kokonaisuudessaan aikavaativuus on
 siis O(n), missä n on annetun luvun pituus (numeroiden määrä).
 */

/**
 * Laskee lukujen n (luku, jota tarkastellaan) ja p (potenssi, josta luvun n potenssit
 * aloitetaan) perusteella, kuinka monta kertaa luvun n numeroiden summa, joista jokainen
 * korotettuna luvusta p alkaviin peräkkäisiin potensseihin, on jaollinen luvulla n.
 *
 * Jos saatu summa ei ole jaollinen luvulla n, palautetaan -1.
 */
fun digPow(n: Int, p: Int): Int {
    val digits = convertToDigitArray(n)
    val sum = calculateSumOfSuccessiveExponentsFromP(digits, p)
    return if (sum % n == 0) sum / n else -1
}

private fun convertToDigitArray(number: Int): List<Int> {
    return number.toString().map { digitChar -> Character.getNumericValue(digitChar) }
}

private fun calculateSumOfSuccessiveExponentsFromP(
    digits: List<Int>,
    startingExponent: Int
): Int {
    var sum = 0
    var exponent = startingExponent
    for (digit in digits) {
        val value = digit.toDouble().pow(exponent)
        sum += value.toInt()
        exponent++
    }
    return sum
}
