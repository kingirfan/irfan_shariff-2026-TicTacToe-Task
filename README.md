**Tic Tac Toe App**
This is a simple Tic Tac Toe game built using Kotlin, Jetpack Compose, and MVVM architecture with LiveData + ViewModel.

**Features**
1. 3x3 Tic Tac Toe Board UI
2. Player X always starts first
3. Alternates turns between X and O
4. Prevents overwriting already selected cells
5. Automatically checks winner after each move
6. Detects draw when board is full
7. Disables board when game is over
8. Displays status using Toast

**Files Used**
TicTacToeViewModel.kt
1. Stores board values
2. Changes turns (X / O)
3. Checks winner or draw

TicTacToeScreen.kt
1. Displays the UI board
2. Handles click on boxes
3. Shows game result on screen

**Output**
1. If X wins → "Player X Wins"
2. If O wins → "Player O Wins"
3. If no winner → "It's a Draw"

**How to Run**
1. Open project in Android Studio
2. Sync Gradle
3. Click Run

**How to Run Test Cases**

1. Open the file:
TicTacToeViewModelTest.kt
2. Right click inside the file
3. Select Run 'TicTacToeViewModelTest'