"""
Tehtävänanto

Muodosta funktio, joka palauttaa Xbonaccin lukujonon listana annetun alustuksen perusteella.
Xbonaccin lukujono on lukujono, jonka seuraava luku on aina X:n edellisen luvun summa (Fibonaccin
lukujonossa X on 2).

Aikavaativuus

Algoritmi laskee lukujonon rekursiolla. Rekursiopuu on muodoltaan suora, ja sen jokaisessa
solmukohdassa tehdään vakiotyö, joten aikavaativuus on O(n), missä n on laskettavien lukujen määrä.
"""

def xbonacci(signature, n):
    """Palauttaa xbonaccin lukujonon listana. Parametri signature on lukujonon alustus, jonka luvut
    ovat lukujonon ensimmäisiä, ja jonka koko kertoo X:n, eli sen, kuinka monen edellisen luvun
    summasta seuraava luku koostuu. Parametri n on palautettavan listan koko, eli kuinka monta
    lukujonon lukua halutaan."""

    sequence = signature[:n]
    last_elements = len(signature)
    _compute_sequence(sequence, n, last_elements)
    return sequence

def _compute_sequence(signature, number_of_elements, last_elements):
    if len(signature) == number_of_elements:
        return

    next_number = sum(signature[-last_elements:])
    signature.append(next_number)
    _compute_sequence(signature, number_of_elements, last_elements)
