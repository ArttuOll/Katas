#!/bin/bash

# Tehtävänanto
#
# Kirjoita skripti, joka hakee annetusta taulukosta (merkkijonoja eroteltuna välilyönneillä)
# pisimmän merkkijonon, joka on saatu yhdistämällä k peräkkäistä taulukon merkkijonoa. Esimerkiksi
# syötteellä "yksi kaksi kolme" "2" tulos olisi "kaksikolme", sillä merkkijonojen "kaksi" ja
# "kolme" yhdistelmä on pisin kahden peräkkäisen taulukossa esiintyvän merkkijonon yhdistelmä.

combine_number_of_strings_from_index() {
    local start_index=$1
    local number_of_strings_to_combine=$2
    local -n string_array=$3

    local combined_string=""
    local end_index=$((start_index + number_of_strings_to_combine - 1))
    for (( j=start_index; j <= end_index; j++ ))
    do
        combined_string="${combined_string}${string_array[$j]}"
    done

    echo "$combined_string"
}

longest_consecutive_string() {
    local strings
    read -r -a strings <<< "$1"
    local number_of_strings_to_combine=$2

    # Jos yritetään yhdistää enemmän merkkijonoja entä annetussa taulukossa on alkioita,
    # lopetetaan suoritus.
    if [ "${#strings[@]}" -lt "$number_of_strings_to_combine" ]
    then
        printf "Error: trying to combine more strings than there are strings in the array!"
        return 1
    fi

    local longest_consecutive_string=""
    for i in "${!strings[@]}"
    do
        # Yhdistetään k peräkkäistä merkkijonoa alkaen ulomman silmukan nykyisestä indeksistä
        combined_string=$(combine_number_of_strings_from_index "$i" "$number_of_strings_to_combine" strings)
        if [ "${#combined_string}" -gt "${#longest_consecutive_string}" ]
        then
            longest_consecutive_string="${combined_string}"
        fi
    done

    printf "%s" "$longest_consecutive_string"
    return 0
}

longest_consecutive_string "$1" "$2"
