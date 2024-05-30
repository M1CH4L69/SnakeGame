[SnakeGame_Dokumentace_MichalNemec_C2B.pdf](https://github.com/M1CH4L69/SnakeGame/files/15503685/SnakeGame_Dokumentace_MichalNemec_C2B.pdf)

Střední průmyslová škola elektrotechnická Ječná 
Informační technologie 
Praha 2, Ječná 30, 120 00 

Ročníková práce 
Snake Game 

Michal Němec, C2B 
Informační technologie 
2024 



1 Cíl práce 
Cílem práce je naprogramovat hru had. Na hracím poli se v průběhu hry budou objevovat kameny, kterým se had musí vyhnout a sníst co nejvíce jablek, aby vyrostl do co největší délky. 

2 Software 
IntelliJ IDEA 
Java development kit: 16 java version 16.0.2 
 
3 Popis hry 
3.1 Obecně 
Hra spočívá v tom, že si hráč zvolí novou hru a zobrazí se mu hrací deska a na ní had, jablko a nějaké kameny. Cílem je, aby had snědl co nejvíce jablek, díky kterým roste. Nesmí narazit do žádného kamene, jiné části svého těla nebo na okraj hrací desky, jinak hra končí. 

3.2 Postavy a komponenty 
Jedinou postavou v této hře je had, který se pohybuje čtyřmi směry.  
Hlava hada je modrá, tělo růžové 
Kameny jsou černé 
Jablko červené 

3.3 Pravidla 
Hráč se pohybuje s hadem po desce tak, aby nasbíral co nejvíce jablek a měl tak co největšího hada. Na desce jsou ale překážky, kterým se musí vyhnout a to takovým způsobem, aby nenarazil do svého těla a ani do okraje hrací desky.  

4 Manuál 
Hra je graficky zpracovaná pomocí JFramu a JPanelu. 

Metody 
startGame() - Zobrazí uživateli okno s možností “začít hru” (použije metodu resetGame() pro nastavení všech proměnných na základní nastavení) nebo “ukončit program”.  

updateTime() - Další důležitou metodou je updateTime(), která zajišťuje nastavení časovače ve hře. 

grow() - Metoda pro růst hada, jakmile sní jablko, také se hra zrychlí a objeví se nové jablko. 

paintComponent() - Metoda pro vykreslování všech potřebných komponent na hrací desku. 

check() - Kontrola zda-li had do něčeho nenarazil. Pokud ano, hra se vypne a umožní uživateli zapnout novou. 

keyPressed() - Metoda, která bere vstup z klávesnice a nastavuje nový směr hada. 

moveSnake() - Metoda, díky které had mění směr. Hlídá, zdali je had na hrací desce. 

serialized() - metoda, která vše na konci serializuje. 

V první řadě se uživateli zobrazí okno, kde si zvolí zda-li zapne novou hru, nebo program vypne. 
Pokud zvolí “Start Game”, tak se zobrazí hrací deska a na ní had, jablko a kameny. Pokud zvolí “Exit”, program se vypne. 
Dále už jen pomocí kláves “W, A, S, D” uživatel mění směr hada, W nahoru, A doleva, S dolu, D doprava. Čím víc jablek had sní, tím větší a rychlejší je a obtížněji se hraje. 
Pod hrací deskou se nachází display, který ukazuje, jak dlouho už hra trvá a jakého jste dosáhli skóre. Po nárazu do některé z překážek/okraje hrací desky/části hadova těla, znovu vyskočí okno, na kterém jsou vypsány statistiky a možnost nové hry, či ukončení programu. 

5. Závěr 
Na projektu jsem pracoval průběžně od jeho zadání. Celkový čas se pohybuje okolo 65 hodin. S funkčností programu jsem spokojen a chtěl bych ho stále vylepšovat a pracovat na něm. Jsem rád, že díky možnosti programování této hry jsem se naučil pracovat s grafický rozhraním. 
Projekt beru jako další dobrou zkušenost pro další rozvoj v programování. 
