
object GameOfLife {
    type Point = Tuple2[Int, Int]
    type Board = Set[Point]

    def get_neighbors(p: Point) = {
        for (dx <- Set(-1,0,1); dy <- Set(-1,0,1) if !(dx==0 && dy==0))
            yield new Point(p._1+dx, p._2+dy)
    }

    def make_stepper(birth_num: List[Int], survive_num: List[Int])
        : Board => Board =
    {
        return (board) => {
            var all_neighbors = board.toList.flatMap(get_neighbors)
            var histogram = all_neighbors.groupBy(identity).mapValues(_.size)
            for ( (loc,n) <- histogram.toSet if (board.contains(loc) && survive_num.contains(n))
                                              || birth_num.contains(n) )
                yield loc
        }
    }

    def make_drawer(width: Int, height: Int)
        : Board => String =
    {
        return (board: Board) => {
            val s = new StringBuilder
            for (y <- 0 until height) {
                for (x <- 0 until width) {
                    if (board.contains((x,y)))
                        s ++= "x"
                    else
                        s ++= " "
                }
                s ++= "|\n"
            }
            s ++= "-"*width
            s.toString()
        }
    }

    val step = make_stepper(List(3), List(2,3))
    val draw = make_drawer(40, 10)

    def main(args: Array[String]) = {
        val glider          = Set((2, 0), (2, 1), (2, 2), (1, 2), (0, 1))
        val light_spaceship = Set((2, 0), (4, 0), (1, 1), (1, 2), (1, 3), (4, 3), (1, 4), (2, 4), (3, 4))
        val blinker         = Set((1,0), (1,1), (1,2))

        var board: Board = blinker

        def run_and_print_each(board: Board) {
            println(draw(board))
            Thread.sleep(200)
            run_and_print_each(step(board))
        }

        def run_until(board: Board, generations: Int) {
            if (generations == 0)
                println(draw(board))
            else
                run_until(step(board), generations-1)
        }

        // run_until(board, 10000)
        run_and_print_each(board)
    }
}

