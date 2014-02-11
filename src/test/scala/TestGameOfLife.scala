
import org.scalatest._
import GameOfLife._

abstract class UnitSpec extends FunSpec

class GameOfLifeSpec extends UnitSpec {

    describe("get_neighbors") {

        def compare(neighbors: Board, expected: Board) {
            assert(neighbors.size == 8)
            assert(neighbors == expected)
        }

        describe("for origin") {
            it("should return correct values") {
                val neighbors = get_neighbors(0,0)
                val expected_neighbors = Set((-1, -1), (-1, 0), (-1,1), (0,-1), (0,1), (1,-1), (1,0), (1,1))
                compare(neighbors, expected_neighbors)
            }
        }

        describe("for points with positive x,y coordinates") {
            it("should return correct values") {
                val neighbors = get_neighbors(1,1)
                val expected_neighbors = Set((0,0), (0,1), (0,2), (1,2), (1,0), (2,0), (2,1), (2,2))
                compare(neighbors, expected_neighbors)
            }
        }

        describe("for points with negative x,y coordinates") {
            it("should return correct values") {
                val neighbors = get_neighbors(-1,-1)
                val expected_neighbors = Set((0,0), (0,-1), (0,-2), (-1,-2), (-1,0), (-2,-0), (-2,-1), (-2,-2))
                compare(neighbors, expected_neighbors)
            }
        }

        describe("for points with big values") {
            it("should *still* return correct values") {
                val neighbors = get_neighbors(300,123)
                val expected_neighbors = Set((301, 122), (301, 123), (301, 124),
                                              (300, 122),             (300, 124),
                                              (299, 122), (299, 123), (299, 124))
                compare(neighbors, expected_neighbors)
            }
        }
    } // describe("get_neighbors")

    describe("step") {
        describe("for rule 1") {
            it("should kill cells with no neighbors") {
                val board = Set((0,0))
                assert(!step(board).contains((0,0)))
            }
            it("should kill cells with only one neighbor") {
                val board = Set((0,0), (0,1))
                assert(!step(board).contains((0,0)))
            }
        }
        describe("for rule 2") {
            it("should persist cells with two neighbors") {
                val board = Set((0,0), (0,1), (0,2))
                assert(step(board).contains((0,1)))
            }
        }
        describe("for rule 3") {
            it("should kill cells with 4 neighbors") {
                val board = Set((0,0), (0,1), (0,2), (1,1), (-1, 1))
                assert(!step(board).contains((0,1)))
            }
        }
        describe("for rule 4") {
            it("should birth dead cells with 3 neighbors") {
                val board = Set((0,0), (1,1), (0,2))
                assert(step(board).contains((0,1)))
            }
        }
    }

    describe("draw") {
        it("should draw an empty board") {
            val draw = make_drawer(3,4)
            assert( "   |\n   |\n   |\n   |\n---" == draw(Set()) )
        }
        it("should draw some cells") {
            val draw = make_drawer(3,4)
            assert( "  x|\n x |\n   |\n   |\n---" == draw(Set((1,1), (2,0))) )
        }
    }

    describe("blinker") {
        it("should alternate between states") {
            val blinker1 = Set((-1,0), (0,0), (1,0))
            val blinker2 = Set((0,-1), (0,0), (0,1))
            assert (step(blinker1) == blinker2)
            assert (step(blinker2) == blinker1)
        }
    }
}
