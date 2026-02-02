package com.example.tictactowtask.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tictactowtask.viewmodel.TicTacToeViewModel

@Composable
fun TicTacToeScreen(modifier: Modifier = Modifier, viewModel: TicTacToeViewModel = viewModel()) {

    val board by viewModel.board.observeAsState(
        initial = List(3) { List(3) { "" } })

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Tic Tac Toe",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 20.dp),
        )

        Text(
            text = "Player X Start First",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.ExtraLight,
            modifier = Modifier.padding(bottom = 18.dp),
        )

        GameBoardUI(
            board = board,
            onCellClick = viewModel::onSquareCellClicked
        )
    }
}

@Composable
fun GameBoardUI(
    board: List<List<String>>,
    onCellClick: (Int, Int) -> Unit
) {
    Column(
        modifier = Modifier
            .size(300.dp)
            .border(2.dp, MaterialTheme.colorScheme.primary)
    ) {
        repeat(3) { row ->
            Row(modifier = Modifier.weight(1f)) {
                repeat(3) { column ->
                    SquareCellUI(
                        modifier = Modifier.weight(1f),
                        value = board[row][column],
                        onClick = { onCellClick(row, column) },
                    )
                }
            }
        }
    }
}

@Composable
fun SquareCellUI(
    modifier: Modifier = Modifier,
    value: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .border(1.dp, MaterialTheme.colorScheme.primary)
            .background(color = Color.Transparent)
            .clickable(enabled = value.isEmpty()) {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(text = value, fontWeight = FontWeight.Bold, fontSize = 36.sp, color = Color.Black)
    }
}
