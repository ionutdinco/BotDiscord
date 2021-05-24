# BotDiscordPA

DISCORD BOT->24 05 2021
-----------------------

Dinco Ionut-Andrei & Paladi Claudiu
-----------------------------------

Proiectul contine JavaDoc pentru informatii mai detaliate.

Folosim JDA (Java Discord API)(REST services) pentru a afisa in canalul "general" de pe serverul de Discord
feed-uri necitite, preluate prin RSS. Pentu asta, am creat un Thread care dupa un anumit timp 
afiseaza un feed necitit, iar daca nu se gaseste se afiseaza un mesaj sugestiv.

Bot-ul are comanda `~help` care afiseaza toate comenzile disponibile.

Bot-ul, la comanda `~ <intrebare>` cauta prima data intr-un fisier local ".json"  dupa cuvintele
cheie din intrebare, iar daca nu gaseste cauta folosind search engine-ul implementat. Dupa
cautare informatiile gasite de search engine pot fi adaugate local prin comanda `~add <key> "<value>"`.

Pentru suportul RSS avem comenzile :

`~news` => afiseaza in chat primul mesaj din feed necitit; 

`~readAllNews` => adauga la "citite" toate mesajele din toate feed-urile;

`~readAllNewsFrom <nr crt of feed>` => adauga la "citite" toate mesajele din feed-ul de pe pozitia
primita ca parametru;

`~addRss <link for GET request>` => adauga link-ul la lista de feed-uri urmarite;

`~removeRss <specific word>` => sterge din lista de feed-uri urmarite un anumit link;




