W pliku Render znajduje się klasa(Render) rozszerzająca JPanel , metoda paintComponent pozwala nam wyświetlać wszystkie komponenty bazując na obiekcie klasy Graphics, (te które rysujemy odwołując się do metody repaint z klasy Pic2D)
W pliku Pic2D : Klasa Pic2D dziedziczy po ActionListener aby móc nadpisać jego metodę actionPerformed, dzięki czemu możliwe będzie wykonanie akcji jaką jest malowanie wszystkich elementów w oknie.  W konstruktorze klasy Pic2D ustalam odpowiednie wymiary i ustawienia okna/ramki (czy jest widoczna i czy da się zmieniać jej wymiary). Tworzę nowy obiekt klasy Render i dodaję go do ramki, dzięki temu nasz obraz będzie mógł być renderowany. W metodzie repaint implementuję wszystkie konkretne metody wyświetlające figury geometryczne, oczywiście bazuję w niej na obiekcie klasy Graphics.
