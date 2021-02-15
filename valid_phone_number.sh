#!/bin/bash

# Tehtävänanto
#
# Kirjoita skripti, joka tarkastaa, onko sille parametrina annettu merkkijono puhelinnumero, joka
# on muotoa (123) 123-1234.

valid_phone_number () {
    phone_number=$1
    phone_number_regex='^\([0-9]{3}\) [0-9]{3}-[0-9]{4}$'
    if [[ "$phone_number" =~ $phone_number_regex ]]
    then
        echo "True"
    else
        echo "False"
    fi
}

valid_phone_number "$1"
