#!/bin/bash

# Tehtävänanto
# Laske yhteen annetun luvun binääriesityksessä esiintyvät ykköset

# Aikavaativuus
# O(n), sillä annettu luku käydään läpi kahdesti peräkkäin (oletan, että luvun muuttaminen
# binääriksi on O(n)).

number=$1
# obase = outputbase, ulostulon kantaluku
number_binary=$(echo "obase=2;$number" | bc)

sum=0
for (( i=0; i<${#number_binary}; i++ )); do
    if [[ "${number_binary:$i:1}" == "1" ]]; then
        sum=$((sum+1))
    fi
done

echo "$sum"
exit 0
