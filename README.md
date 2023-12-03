# ***English Learning Application***

## I. Introduction

+ This is an application created for a major assignment in **Object-Oriented Programming Course**
+ The Dictionary section includes about 100,000 English words with Vietnamese meanings and explanations


## II. Developers
1. [Dương Anh Tú](https://github.com/tumo2208) **(Leader: 34%)**
   + Build the structure of this application
   + Backend
   + Game Catch The Words
   + Handle all base function (search word, add word, game selection, translation, flashcard)

2. [Nguyễn Nhật Phong](https://github.com/xcviixix-rei) **(Member: 33%)**
   + All interface of application 
   + Animation flip card of Flashcard

3. [Hoàng Hữu Phước](https://github.com/ghfu-thym) **(Member: 33%)**
   + Game Memories
   + Voice of word search
   + Animation change card of Flashcard
   + Fix API bug


## III. Features
My application have 5 main features:
### 1. Search Word:
   + A listView contains history (the word we searched) when searchField is empty and otherwise, this contains all word look up of searchField
   + wordTarget and wordExplain
   + 5 button: webview, save, listen, edit and delete

### 2. Add Word:
   + TextArea of English word needed to be added and this meaning

### 3. Translation:
   + To translate a paragraph
   + Use Google Transate API
   + Can translate from English to Vienamese and vice versa

### 4. Flashcard:
   + All save words are saved here
   + Front of card is English word and back of card is abbreviated meaning
   + Change card by click to left and right button

### 5. Game:
   + This app have 2 games: Memories and Catch The Words and we can choose game in menu
   + Memories: click 2 card that is word and meaning of this word
   + Catch the words: guess the picture, answer the question to open suggestion


## IV. Installation
   + Download [javafx-sdk](https://download2.gluonhq.com/openjfx/21.0.1/openjfx-21.0.1_windows-x64_bin-sdk.zip)
   + Contract this zip file
   + Download this project [here](https://github.com/tumo2208/ElearnApp_javafx_Group8/archive/refs/heads/master.zip)
   + Contract this zip file and open in IntelliJ
   + Open File -> Project Structure -> Libraries and add sdk to this project
