package me.adav.game

sealed trait Cell
case object Alive extends Cell
case object Dead extends Cell
