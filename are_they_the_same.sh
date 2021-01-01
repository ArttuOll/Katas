#!/bin/bash

# Tehtävä
# Tarkasta, ovatko syötteenä annetut lukulistat samoja. Lukulistat ovat samoja, jos jälkimmäinen
# lukulista sisältää (vain) ensimmäisen arvot neliöön korotettuna, yhtä monena kappaleena.

# Aikavaativuus
# Kaksi sisäkkäistä for-silmukkaa, joista molemmissa käydään pahimmillaan molemmat listat läpi
# -> O(n²)

comp() {
    read -r -a values <<< "$1"
    read -r -a squares <<< "$2"

    for value in "${values[@]}"
    do
        square=$((value ** 2))
        # Poistetaan neliölistasta läpikäytävän arvon ensimmäinen esiintymä
        for i in "${!squares[@]}"
        do
            if [[ "${squares[i]}" == "$square" ]]; then
                unset "squares[i]"
                break
            fi
        done
    done

    # Neliölistassa ei pitäisi olla yhtään arvoa jäljellä
    if [[ "${#squares[@]}" -gt 0 ]]; then
        echo "false"
        return 1
    fi

    echo "true"
    return 0
}

comp "$1" "$2"
