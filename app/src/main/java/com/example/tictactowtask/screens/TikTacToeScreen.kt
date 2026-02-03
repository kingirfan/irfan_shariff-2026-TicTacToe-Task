package com.example.tictactowtask.screens

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tictactowtask.viewmodel.TicTacToeViewModel

@Composable
fun TicTacToeScreen(modifier: Modifier = Modifier, viewModel: TicTacToeViewModel = viewModel()) {

    val board by viewModel.board.observeAsState(
        initial = List(3) { List(3) { "" } })

    val context = LocalContext.current
    val gameStatus by viewModel.gameStatus.observeAsState(initial = "Player X's Turn")
    val isGameOver by viewModel.isGameOver.observeAsState(initial = false)

    LaunchedEffect(gameStatus) {
        if (
            gameStatus.contains("Wins") ||
            gameStatus.contains("Draw")
        ) {
            Toast.makeText(context, gameStatus, Toast.LENGTH_LONG).show()
        }
    }


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
            onCellClick = viewModel::onSquareCellClicked,
            isGameOver = isGameOver
        )
    }
}

@Composable
fun GameBoardUI(
    board: List<List<String>>,
    onCellClick: (Int, Int) -> Unit,
    isGameOver: Boolean,
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
                        isGameOver = isGameOver
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
    onClick: () -> Unit,
    isGameOver: Boolean = false
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .border(1.dp, MaterialTheme.colorScheme.primary)
            .background(color = Color.Transparent)
            .background(
                when (value) {
                    "X" -> Color(0xFFE3F2FD)
                    "O" -> Color(0xFFFFEBEE)
                    else -> Color.Transparent
                }
            )
            .clickable(enabled = !isGameOver && value.isEmpty()) {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = value, fontWeight = FontWeight.Bold, fontSize = 36.sp, color = when (value) {
                "X" -> Color(0xFF1976D2) // Blue
                "O" -> Color(0xFFD32F2F) // Red
                else -> Color.Black
            }
        )
    }
}
