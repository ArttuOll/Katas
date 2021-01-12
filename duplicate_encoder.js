/*
 * Tehtävänanto
 *
 * Muuta annettua merkkijonoa siten, että jos merkki esiintyy merkkijonossa kerran, sen paikalle
 * tulee "(" ja jos useammin kuin kerran ")".
 *
 * Aikavaativuus
 *
 * Ratkaisuni käy merkkijonon läpi kahdesti. Ensimmäisellä kerralla jokaisen merkin esiintymiset
 * lasketaan kuvaukseen ja toisella jokainen merkki muutetaan kuvauksesta löytyvien
 * esiintymiskertojen perusteella.
 *
 * Kunkin merkin esiintymiskertojen laskeminen kuvaukseen on aikavaativuudeltaan O(n). Merkkijonon
 * muuttaminen (merkkijonoja ei oikeasti tietenkään muuteta, sillä ne ovat muuttumattomia) on myös
 * O(n), sillä merkkien esiintymiskertojen tarkastaminen kuvauksesta on vakioaikaista.
 * (Javascriptin Mapin toteutus perustuu hajautukseen, mistä seuraa O(1) aikavaativuus
 * perusoperaatioille.)
*/

function augmentCharacterCount(character, charCounts) {
  if (charCounts.has(character)) {
    const currentCount = charCounts.get(character);
    charCounts.set(character, currentCount + 1);
  } else {
    charCounts.set(character, 1);
  }
}

function countChars(word) {
  const charCounts = new Map();
  [...word.toLowerCase()].forEach((character) => augmentCharacterCount(character, charCounts));
  return charCounts;
}

function buildDuplicateEncodedString(word, charCounts) {
  let encodedString = "";
  [...word.toLowerCase()].forEach((character) => {
    if (charCounts.get(character) === 1) {
      encodedString += "(";
    } else {
      encodedString += ")";
    }
  });
  return encodedString;
}

function duplicateEncode(word) {
  const charCounts = countChars(word);
  return buildDuplicateEncodedString(word, charCounts);
}
