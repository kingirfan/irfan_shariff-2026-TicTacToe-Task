package com.example.tictactowtask.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TicTacToeScreen(modifier: Modifier = Modifier) {

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

        GameBoard()
    }
}

@Composable
fun GameBoard() {
    Column(
        modifier = Modifier
            .size(250.dp)
            .border(2.dp, MaterialTheme.colorScheme.primary)
    ) {
        repeat(3) {
            Row(modifier = Modifier.weight(1f)) {
                repeat(3) {
                    SquareCellUI(
                        modifier = Modifier
                            .weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun SquareCellUI(modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .border(1.dp, MaterialTheme.colorScheme.primary)
            .background(color = Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        Text(text = " X ", fontWeight = FontWeight.Bold, fontSize = 36.sp, color = Color.Black)
    }
}
