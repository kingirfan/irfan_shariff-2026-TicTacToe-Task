package com.example.tictactowtask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TicTacToeViewModel : ViewModel() {

    private val _board = MutableLiveData(List(3) { List(3) { "" } })
    val board: LiveData<List<List<String>>> = _board

    private val _gameStatus = MutableLiveData("Player X's Turn")
    val gameStatus: LiveData<String> = _gameStatus

    private val _isGameOver = MutableLiveData(false)
    val isGameOver: LiveData<Boolean> = _isGameOver


    private var xTurn = true

    fun onSquareCellClicked(row: Int, column: Int) {
        val currentBoard = _board.value ?: return
        if (currentBoard[row][column].isNotEmpty()) return

        val newBoard = currentBoard.mapIndexed { r, rowList ->
            rowList.mapIndexed { c, cell ->
                if (r == row && c == column) {
                    if (xTurn) "X" else "O"
                } else cell
            }
        }

        _board.value = newBoard
        checkGameStatus(newBoard)
        xTurn = !xTurn
    }

    private fun checkGameStatus(board: List<List<String>>) {
        val winner = checkWinner(board)

        if (winner.isNotEmpty()) {
            _gameStatus.value = "Player $winner Wins"
            _isGameOver.value = true
        } else if (isBoardFull(board)) {
            _gameStatus.value = "It's a Draw"
            _isGameOver.value = true
        }
    }

    private fun checkWinner(board: List<List<String>>): String {
        // Check rows
        for (row in 0..2) {
            if (board[row][0].isNotEmpty() && board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                return board[row][0]
            }
        }

        // Check columns
        for (col in 0..2) {
            if (board[0][col].isNotEmpty() && board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                return board[0][col]
            }
        }

        // Check diagonal (top-left to bottom-right)
        if (board[0][0].isNotEmpty() && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return board[0][0]
        }

        // Check diagonal (top-right to bottom-left)
        if (board[0][2].isNotEmpty() && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return board[0][2]
        }

        return ""
    }

    private fun isBoardFull(board: List<List<String>>): Boolean {
        for (row in board) {
            for (cell in row) {
                if (cell.isEmpty()) return false
            }
        }
        return true
    }


}