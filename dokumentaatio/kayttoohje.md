#### Kayttoohje
Peli käynnistetään ajamalla jar-tiedosto. Lisää karttoja voi lisätä luomalla ```maps``` kansion samaan kansioon jar-tiedoston kanssa ja lisäämällä sinne tiedosto jonka sisältö kuvaillaan kohdassa kartat.

Pelissä pelaaja saa alkuun rahaa riippuen vaikeustasosta. Rahaa voi käyttää tornien rakentamiseen ja päivittämiseen. Tämä tapahtuu valitsemalla ruudun johon torni halutaan rakentaa tai jossa on päivitettävä torni. Tällöin oikean laidan valikkoon ilmestyy vaihtoehtoja rakentamisen tai päivittämisen tekemiseksi. Torneja voi myös myydä.

Pelaaja saa valita milloin seuraava aalto vihollisia hyökkää. Jokainen aalto on vaikeapi kuin edellinen, mutta tuhoituista vihollisista saa lisää rahaa.

Peli päättyy, kun pelaaja kuolee tai kaikki aallot on tuhottu.

#### Kartat
Kartan täytyy olla neliskanttinen niin, että joka rivillä on yhtä monta merkkiä.

* $-merkki on kohde jota hyökkääjät yrittävät tuhota.
* X-merkki on vihollisten aloituspaikka. Näitä voi olla useitakin.
* \#-merkki on seinää. Siihen ei voi rakentaa, eikä siitä voi liikkua läpi.
* @-merkki on rakennettava kohta. Eli pelaaja voi rakentaa siihen tornin.

##### Esimerkki:
```
X    ##
#@@@ @#
#@@@ @#
#@@@ @#
#@@@  $
#######
```