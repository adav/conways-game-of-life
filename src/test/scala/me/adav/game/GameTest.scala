package me.adav.game

import me.adav.game.Game.Grid
import org.scalatest.{FlatSpec, Matchers}

class GameTest extends FlatSpec with Matchers {

  "A grid cell" should "return count of alive neighbours" in {
    val grid: Grid = Map(
      (0, 0) -> Dead, (1, 0) -> Alive,
      (0, 1) -> Dead, (1, 1) -> Dead
    )

    Game.getAliveNeighboursCount(1, 0, grid) should be (0)
    Game.getAliveNeighboursCount(0, 0, grid) should be (1)
  }

  "An alive cell with fewer than two alive neighbours" should "die" in {
    val grid: Grid = Map(
      (0, 0) -> Dead, (1, 0) -> Alive,
      (0, 1) -> Dead, (1, 1) -> Dead
    )
    val newGrid = Game.tick(grid, 1, 1)

    newGrid should be (Map(
      (0, 0) -> Dead, (1, 0) -> Dead,
      (0, 1) -> Dead, (1, 1) -> Dead
    ))
  }

  "An alive cell with two or three alive neighbours" should "live" in {
    val grid: Grid = Map(
      (0, 0) -> Alive, (1, 0) -> Alive, (2, 0) -> Alive,
      (0, 1) -> Dead, (1, 1) -> Dead, (2, 1) -> Alive
    )
    val newGrid = Game.tick(grid, 1, 1)

    newGrid should equal(Map(
      (0, 0) -> Dead, (1, 0) -> Alive, (2, 0) -> Alive,
      (0, 1) -> Dead, (1, 1) -> Dead, (2, 1) -> Alive
    ))
  }

  "An alive cell with more than three alive neighbours" should "die" in {
    val grid: Grid = Map(
      (0, 0) -> Dead, (1, 0) -> Alive, (2, 0) -> Dead,
      (0, 1) -> Alive, (1, 1) -> Alive, (2, 1) -> Alive,
      (0, 2) -> Dead, (1, 2) -> Alive, (2, 2) -> Dead
    )
    val newGrid = Game.tick(grid, 1, 1)

    newGrid should equal(Map(
      (0, 0) -> Alive, (1, 0) -> Alive, (2, 0) -> Alive,
      (0, 1) -> Alive, (1, 1) -> Alive, (2, 1) -> Alive,
      (0, 2) -> Alive, (1, 2) -> Alive, (2, 2) -> Alive
    ))
  }

  "A blinker" should "blink" in {
    val grid: Grid = Map(
      (0, 0) -> Dead, (1, 0) -> Alive, (2, 0) -> Dead,
      (0, 1) -> Dead, (1, 1) -> Alive, (2, 1) -> Dead,
      (0, 2) -> Dead, (1, 2) -> Alive, (2, 2) -> Dead
    )
    val newGrid = Game.tick(grid, 1, 1)

    newGrid should equal(Map(
      (0, 0) -> Dead, (1, 0) -> Dead, (2, 0) -> Dead,
      (0, 1) -> Alive, (1, 1) -> Alive, (2, 1) -> Alive,
      (0, 2) -> Dead, (1, 2) -> Dead, (2, 2) -> Dead
    ))
  }
}
