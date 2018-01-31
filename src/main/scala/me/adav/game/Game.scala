package me.adav.game

object Game {

  type Grid = Map[(Int, Int), Cell]

  def getAliveNeighboursCount(x: Int, y: Int, grid: Grid): Int = Seq(
      grid.getOrElse((x-1, y-1), Dead), grid.getOrElse((x, y-1), Dead), grid.getOrElse((x+1, y-1), Dead),
      grid.getOrElse((x-1, y),   Dead),                                 grid.getOrElse((x+1, y),   Dead),
      grid.getOrElse((x-1, y+1), Dead), grid.getOrElse((x, y+1), Dead), grid.getOrElse((x+1, y+1), Dead)
  ).count(Alive ==)

  def tick(grid: Grid, maxX: Int, maxY: Int): Grid = grid.map {
     case ((x, y), Alive) if getAliveNeighboursCount(x, y, grid) < 2 => (x, y) -> Dead
     case ((x, y), Alive) if getAliveNeighboursCount(x, y, grid) >= 2 => (x, y) -> Alive
     case ((x, y), Alive) if getAliveNeighboursCount(x, y, grid) > 3 => (x, y) -> Dead
     case ((x, y), Dead) if getAliveNeighboursCount(x, y, grid) == 3 => (x, y) -> Alive
     case cell => cell
  }
}
