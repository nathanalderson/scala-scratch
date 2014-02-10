package src.main.scala

import org.scalatest._

abstract class UnitSpec extends FunSpec

class GameOfLifeSpec extends UnitSpec {

    describe("get_neighbors") {

        def compare(neighbors: List[(Int, Int)], expected: List[(Int, Int)]) {
            assert(neighbors.length == 8)
            assert(neighbors.sorted == expected.sorted)
        }

        describe("for origin") {
            it("should return correct values") {
                val neighbors = GameOfLife.get_neighbors(0,0)
                val expected_neighbors = List((-1, -1), (-1, 0), (-1,1), (0,-1), (0,1), (1,-1), (1,0), (1,1))
                compare(neighbors, expected_neighbors)
            }
        }

        describe("for points with positive x,y coordinates") {
            it("should return correct values") {
                val neighbors = GameOfLife.get_neighbors(1,1)
                val expected_neighbors = List((0,0), (0,1), (0,2), (1,2), (1,0), (2,0), (2,1), (2,2))
                compare(neighbors, expected_neighbors)
            }
        }

        describe("for points with negative x,y coordinates") {
            it("should return correct values") {
                val neighbors = GameOfLife.get_neighbors(-1,-1)
                val expected_neighbors = List((0,0), (0,-1), (0,-2), (-1,-2), (-1,0), (-2,-0), (-2,-1), (-2,-2))
                compare(neighbors, expected_neighbors)
            }
        }

        describe("for points with big values") {
            it("should *still* return correct values") {
                val neighbors = GameOfLife.get_neighbors(300,123)
                val expected_neighbors = List((301, 122), (301, 123), (301, 124),
                                              (300, 122),             (300, 124),
                                              (299, 122), (299, 123), (299, 124))
                compare(neighbors, expected_neighbors)
            }
        }
    }
}
