#!/bin/bash

# Tehtävänanto
# Nimen arvo lasketaan seuraavallatavalla: lasketaan yhteen nimen jokaisen kirjaimen
# järjestysnumero aakkosissa ja lisätään siihen nimen pituus. Lisäksi jokaista annettua nimeä kohti
# on annettu satunnainen luku, jolla nimen arvo kerrotaan.

# Tehtävänä on laskea arvot annetuille nimille ja palauttaa nimistä n:nneksi suurin.
# Tehtävän toteuttaa funktio rank(), joka saa parametrinaan 1. nimet, pilkuilla eroteltuina, 2.
# Painot, pilkuilla eroteltuina, 3. järjestysnumero, jota vastava nimi halutaan.

# Esimerkkejä ja erilainen selitys: https://www.codewars.com/kata/5616868c81a0f281e500005c/shell

set -e

get_character_value() {
    local character=${1,,}
    local char_rank_in_alphabet=0
    for alphabet_character in {a..z}
    do
        char_rank_in_alphabet=$((char_rank_in_alphabet + 1))
        if [[ $alphabet_character == "$character" ]]
        then
            echo "${char_rank_in_alphabet}"
            return 0
        fi
    done
}

calculate_name_value() {
    local name="$1"
    local value=${#name}
    # Käydään läpi nimen kirjaimet
    for (( i=0;  i<${#name}; i++ ))
    do
        local character="${name:$i:1}"
        local character_value; character_value=$(get_character_value "$character")
        value=$((value + character_value))
    done

    printf "%s" "${value}"
}

rank() {
    IFS=','
    declare -a first_names; read -r -a first_names <<< "$1"

    # Jos yhtään nimeä ei annettu, lopetetaan suoritus
    if [[ ${#first_names[@]} -eq 0 ]]
    then
        printf "%s" "No participants"
        return 1
    fi

    declare -a weights; read -r -a weights <<< "$2"
    unset IFS

    # Luodaan taulukko, jossa on merkkijonoja, jotka ovat muotoa: ARVO NIMI
    declare -a value_name_array
    for (( i=0; i<${#first_names[@]}; i++ ))
    do
        local name="${first_names[$i]}"
        local weight="${weights[$i]}"
        local name_value; name_value=$(($(calculate_name_value "${name}") * weight))
        value_name_array+=("${name_value} ${name}")
    done

    # Järjestetään taulukon arvot 1. arvon mukaan 2. nimen mukaan
    IFS=$'\n'
    declare -a sorted_value_name_array
    sorted_value_name_array=($(sort -k 1rn -k 2d <<< "${value_name_array[*]}"))
    unset IFS

    # Jos pyydetään esim. 8. nimeä, mutta nimiä on vain 4, lopetetaan suoritus
    local rank=$3
    if [[ rank -gt ${#first_names[@]} ]]
    then
        printf "%s" "Not enough participants"
        return 1
    fi

    local rank_index=$(($3 - 1))
    printf "%s" "$(echo "${sorted_value_name_array[$rank_index]}" | awk '{printf $2}')"
}

rank "$1" "$2" "$3"
