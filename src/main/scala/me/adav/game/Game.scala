package me.adav.game

object Game {

  type Grid = Map[(Int, Int), Cell]

  def getAliveNeighboursCount(x: Int, y: Int, grid: Grid): Int = Seq(
      grid.getOrElse((x-1, y-1), Dead), grid.getOrElse((x, y-1), Dead), grid.getOrElse((x+1, y-1), Dead),
      grid.getOrElse((x-1, y),   Dead),                                 grid.getOrElse((x+1, y),   Dead),
      grid.getOrElse((x-1, y+1), Dead), grid.getOrElse((x, y+1), Dead), grid.getOrElse((x+1, y+1), Dead)
  ).count(Alive ==)

  def tick(grid: Grid, maxX: Int, maxY: Int): Grid = grid.map { case cell @ ((x, y), status) =>
    val aliveNeighbours = getAliveNeighboursCount(x, y, grid)

    cell.copy(_2 = status match {
      case Alive if aliveNeighbours < 2 => Dead
      case Alive if aliveNeighbours >= 2 => Alive
      case Alive if aliveNeighbours > 3 => Dead
      case Dead if aliveNeighbours == 3 => Alive
      case s => s
    })
  }
}
