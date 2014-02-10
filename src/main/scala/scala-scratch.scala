package src.main.scala

object GameOfLife {
    def get_neighbors(x: Int, y: Int) = {
        for (dx <- List(-1,0,1); dy <- List(-1,0,1) if !(dx==0 && dy==0))
            yield (x+dx, y+dy)
    }

    def main(args: Array[String]) = {
        val glider          = ((2, 0), (2, 1), (2, 2), (1, 2), (0, 1))
        val light_spaceship = ((2, 0), (4, 0), (1, 1), (1, 2), (1, 3), (4, 3), (1, 4), (2, 4), (3, 4))
        val blinker         = ((1,0), (1,1), (1,2))

        val cells = glider
    }
}

