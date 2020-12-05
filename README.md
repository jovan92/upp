# Upravljanje Poslovnim Procesima (UPP)
## Specifikacija zadatka za projekat Literarno Udruženje
Literarno udruženje (LU) je sistem koji omogućava: 
- Registraciju novih korisnika na sistemu. Korisnici mogu biti čitaoci, beta-čitaoci, pisci, lektori, urednici i članovi odbora (od kojih je jedan glavni). Sistem treba da neregistrovanim korisnicima omogući registraciju u ulozi čitaoca ili betačitaoca, dok urednike i lektore mora potvrditi i administrator sistema.
- Vođenje procesa lektorisanja, revizije, uređivanja i izdavanja knjiga na srpskom jeziku.
- Vođenje procesa provere plagijarizma.
- Pretragu repozitorijuma knjiga, kao i napredne funkcionalnosti pretrage u sklopu procesa objavljivanja radova.
- Nekoliko vidova elektronskog plaćanja, putem kojih se uplaćuju članarine, plaća izdavanje knjiga ili njihova kupovina

## Model podataka
Za pisce knjiga evidentiraju se sledeći podaci: ime, prezime, grad i država i e-mail adresa. Pored podataka za pisce evidentiraju se i podaci potrebni za prijavu na sistem i žanrovi za koje je pisac zainteresovan. 

Za čitaoce se evidentiraju ime, prezime, grad, država, e-mail adresa, kao i da li želi da bude beta-čitalac. Beta-čitaoci su čitaoci kojima se šalju rukopisi pre izdavanja knjige, kako bi pisci dobili inicijalno mišljenje publike. Za beta-čitaoce se evidentiraju i žanrovi za koje su zainteresovani.

Za svaku knjigu treba da bude evidentiran njen naslov, pisci, žanr (osmisliti šifarnik), ISBN, ključni pojmovi, izdavač, godina kada je knjiga izdata, mesto gde je knjiga izdata, broj stranica, i sinopsis. Takođe, neophodni su i podaci o lektorisanju i urednicima.

## Tehnologije
- java 1.8 - virtualna mašina
- Eclipse IDE for Java developers - razvojno okruženje
- Spring Tools 3 (Standalone Edition) - backend framework
- Angular - frontend framework
- Camunda Modeler - alat za kreiranje diagrama procesa
- Azure DevOps - alat za praćenje razvoja aplikacije (lifecycle)

## Autori
**Novica Nikolić** - https://github.com/nole23 :grin: \
**Jovan Popović** - https://github.com/jovan92 :yum::wink: \
**Dejan Jovanović** - https://github.com/Blyatiful-git :upside_down_face:
