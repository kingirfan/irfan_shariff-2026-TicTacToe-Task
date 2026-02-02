package com.example.tictactowtask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TicTacToeViewModel : ViewModel() {

    private val _board = MutableLiveData(List(3) { List(3) { "" } })
    val board: LiveData<List<List<String>>> = _board

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
        xTurn = !xTurn
    }


}