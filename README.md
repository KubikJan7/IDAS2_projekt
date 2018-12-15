# IDAS2 Projekt
## Požadavky na databázi
- [x] min. 10 tabulek navrženého datového modelu i s číselníky
- [x] min. 2 číselníky
- [x] min. 3 sekvence - každý umělý primární klíč bude mít vytvořenou sekvenci
- [x] min. 3 pohledy – logicky využité, různého typu
- [x] min. 2 funkce různého typu
- [x] min. 3 procedury různého typu
- [x] min. 2 triggery různého typu
- [x] Aplikace bude umožňovat uložit vybraný binární obsah do databáze a následně jej i z databáze získat (a pokud se bude jednat o obrázek, tak i v rámci aplikace zobrazit). Pro tento úkol vytvořte ve svém schématu speciální tabulku. Tabulku navrhněte tak, aby kromě samotného binární obsahu umožnila uložit doplňkové informace, jako např.: typ souboru, přípona souboru, datum nahrání, datum modifikace
- [x] Aplikace bude využívat minimálně 2 plnohodnotné formuláře (např. ošetření vstupních polí, apod.) pro vytváření nebo modifikaci dat v tabulkách, ostatní potřebné formuláře jsou samozřejmostí.

## Funkční požadavky na aplikaci
1. Soukromá vysoká škola má zájem o vytvoření jednoduché databázové aplikace, která umožňuje přidávat, modifikovat a mazat záznamy v tabulkách.
2. Databázová aplikace bude sloužit k tvorbě úvazků a rozvrhů vyučujících, tedy eviduje vyučující a jejich předměty včetně jejich role v předmětu na základě rozsahu hodin daného způsobu výuky a dle předpokládaného počtu studentů na předmět.
3. Grafické rozhraní aplikace bude funkční a bude umožňovat editovat jakýkoliv záznam, který je načtený z databáze.
4. Aplikace bude mít menu nastaveno tak, že je možné z jednékarty přepnout na všechny ostatní, tak aby byla zaručena příjemná uživatelská správa.
5. Aplikace bude obsahovat následující záložky (karty):
   - [x] Na kartě evidence vyučujících evidujeme vyučujícího včetně jeho pracoviště.
   - [ ] Na kartě evidence pracovišť se spravují informace o pracovišti.
   - [ ] Na kartě předměty evidujeme informace o předmětech.
   - [ ] Na kartě studijních oborů evidujeme informace o studijních oborech.
   - [ ] Na kartě studijních plánů evidujeme informace o složení studijního plánu.
         - *v aplikaci studijní plány vůbec nejsou*
   - [ ] Na kartě předmětů evidujeme informace o předmětu. ???
         - *chápu tak, že po rozkliknutí předmětu se zobrazí vyučující kteří ho učí apod..*
   - [ ] Na kartě rozvrhy bude moci administrátor vytvořit rozvrh různým vyučujícím podle odhadovaného počtu skupin cvičení, přednášek či seminářů. Zároveň bude moci editovat tyto skupiny.
   - [ ] Vyučující si bude moci navrhnout svůj vlastní rozvrh, a to pouze tam, kde je volná učebna a zároveň nemá studijní skupina daného studijního plánu jinou aktivitu, administrátor ovšem může vkládat rozvrhové aktivity kamkoliv, hlídá se jen obsazenost učebny.
   
## Data
