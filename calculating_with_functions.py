"""
Tehtävänanto

Luo funktioita, joilla voi suorittaa peruslaskentaoperaatioita (siis funktioilla itsellään!).
Esim. two(times(five())) => 10.

Aikavaativuus

O(1), koska ohjelma suorittaa pelkästään yksinkertaisia laskuoperaatioita, jotka ovat aina
vakioaikaisia.
"""

def generate_value_function(value):
    """
    Palauttaa "arvofunktion", eli funktion, jolle voi antaa argumenttina vapaaehtoisen
    operaattorifunktion (plus, minus, times, divided_by). Jos operaattori on annettu, niin
    arvofunktio antaa operaattorille argumenttina oman arvonsa (1-9) ja palauttaa tuloksen.
    Muussa tapauksessa arvofunktio palauttaa oman arvonsa.
    """

    return lambda operator=None : operator(value) if operator else value

def zero(operator=None):
    return generate_value_function(0)(operator)

def one(operator=None):
    return generate_value_function(1)(operator)

def two(operator=None):
    return generate_value_function(2)(operator)

def three(operator=None):
    return generate_value_function(3)(operator)

def four(operator=None):
    return generate_value_function(4)(operator)

def five(operator=None):
    return generate_value_function(5)(operator)

def six(operator=None):
    return generate_value_function(6)(operator)

def seven(operator=None):
    return generate_value_function(7)(operator)

def eight(operator=None):
    return generate_value_function(8)(operator)

def nine(operator=None):
    return generate_value_function(9)(operator)

def plus(value2):
    """
    Palauttaa funktion, joka ottaa argumenttina arvon ja palauttaa tämän arvon yhteen
    laskettuna tälle operaattorifunktiolle annetun arvon kanssa.
    Esim. plus(5) => fun(arvo): arvo + 5.
    """
    return lambda value1 : value1 + value2

def minus(value2):
    """
    Palauttaa funktion, joka ottaa argumenttina arvon ja palauttaa tämän arvon erotuksen tälle
    operaattorifunktiolle annetun arvon kanssa. Esim. minus(5) => fun(arvo): arvo - 5.
    """
    return lambda value1 : value1 - value2

def times(value2):
    """
    Palauttaa funktion, joka ottaa argumenttina arvon ja palauttaa tämän arvon tulon tälle
    operaattorifunktiolle annetun arvon kanssa. Esim. times(5) => fun(arvo): arvo * 5.
    """
    return lambda value1 : value1 * value2

def divided_by(value2):
    """
    Palauttaa funktion, joka ottaa argumenttina arvon ja palauttaa tämän osamäärän tälle
    operaattorifunktiolle annetun arvon kanssa. Esim. divided_by(5) => fun(arvo): arvo // 5.
    """
    return lambda value1 : value1 // value2
