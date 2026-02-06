package com.example.tictactowtask.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class TicTacToeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: TicTacToeViewModel

    @Before
    fun setup() {
        viewModel = TicTacToeViewModel()
    }

    @Test
    fun `initial board is empty`() {
        val board = viewModel.board.value
        assertNotNull(board)

        board?.forEach { row ->
            row.forEach { cell ->
                assertEquals("", cell)
            }
        }
    }

    @Test
    fun `initial game status is Player X's Turn`() {
        assertEquals("Player X's Turn", viewModel.gameStatus.value)
    }

    @Test
    fun `X goes first`() {
        viewModel.onSquareCellClicked(0, 0)
        val board = viewModel.board.value
        assertEquals("X", board?.get(0)?.get(0))
    }

    @Test
    fun `players alternate turns - X then O`() {
        viewModel.onSquareCellClicked(0, 0)
        viewModel.onSquareCellClicked(0, 1)

        val board = viewModel.board.value
        assertEquals("X", board?.get(0)?.get(0))
        assertEquals("O", board?.get(0)?.get(1))
    }

    @Test
    fun `detect X wins - top row`() {
        viewModel.onSquareCellClicked(0, 0)
        viewModel.onSquareCellClicked(1, 0)
        viewModel.onSquareCellClicked(0, 1)
        viewModel.onSquareCellClicked(1, 1)
        viewModel.onSquareCellClicked(0, 2)

        assertEquals("Player X Wins", viewModel.gameStatus.value)
        assertEquals(true, viewModel.isGameOver.value ?: false)
    }

    @Test
    fun `detect O wins - top row`() {
        viewModel.onSquareCellClicked(1, 0)
        viewModel.onSquareCellClicked(0, 0)
        viewModel.onSquareCellClicked(1, 1)
        viewModel.onSquareCellClicked(0, 1)
        viewModel.onSquareCellClicked(2, 2)
        viewModel.onSquareCellClicked(0, 2)

        assertEquals("Player O Wins", viewModel.gameStatus.value)
        assertEquals(true, viewModel.isGameOver.value ?: false)
    }

    @Test
    fun `detect draw when board is full with no winner`() {

        viewModel.onSquareCellClicked(0, 0)
        viewModel.onSquareCellClicked(0, 1)
        viewModel.onSquareCellClicked(0, 2)
        viewModel.onSquareCellClicked(1, 0)
        viewModel.onSquareCellClicked(1, 1)
        viewModel.onSquareCellClicked(2, 0)
        viewModel.onSquareCellClicked(1, 2)
        viewModel.onSquareCellClicked(2, 2)
        viewModel.onSquareCellClicked(2, 1)

        assertEquals("It's a Draw", viewModel.gameStatus.value)
        assertEquals(true, viewModel.isGameOver.value ?: false)
    }

    @Test
    fun `resetGame resets board and status`() {

        viewModel.onSquareCellClicked(0, 0)

        viewModel.resetGame()

        assertEquals("", viewModel.board.value?.get(0)?.get(0))
        assertEquals("Player X's Turn", viewModel.gameStatus.value)
        assertEquals(false, viewModel.isGameOver.value ?: true)
    }

}