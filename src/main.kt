var x:Int = 0
var y:Int = 0

fun main(){

    var board = Array<CharArray>(3, { CharArray(3) })
    initBoard(board)

    val names = arrayOf("Player 1", "Player 2")
    val marks = arrayOf('O', 'X')

    play(board, names, marks)
   // 3 x 3의 보드 생성
   // 보드 초기화 (함수)
   // 플레이어 배열 (Player 1, Player2) 선언
   // 말 배열( 'O', 'X') 선언
   // 게임 진행

}

fun initBoard(board:Array<CharArray>):Unit{
    //보드 초기화 함수 - initBoard( )
    var len = board.size;
    for(i in 0..len-1){
        for(j in 0..len-1){
            board[i][j] = ' '
        }
    }
}

fun printBoard(board:Array<CharArray>):Unit{
    //보드 출력 함수 - printBoard( )
    print(" ")
    for(x in 0..2) print("$x ")
    println()

    for(y in 0..2){
        print("$y ")
        for(x in 0..2){
            print("${board[y][x]}")
            if(x !=2) print("|")
        }
        println()

        if(y!=2) {
            print(" ")
            for(x in 0..2){
                print("-")
                if(x!=2) print("+")
            }
            println()
        }
    }
}

var isInRange = {x:Int,y:Int -> x in 0..2 && y in 0..2}

fun isValid(board:Array<CharArray>,x:Int,y:Int):Boolean{
    //입력의 범위 검사 - isInRange( )
    return isInRange(x,y)&&board[y][x] == ' '
}

fun playerInput(name:String,board:Array<CharArray>):Boolean{
    //특정 격자위치에 말을 놓는 함수 - playerInput( )
    // Player 1 입력(줄,칸):
    print("$name 입력 (줄,칸) : ")
    val input:String? = readLine()
    var arr = (input?.split(","))?.apply {
        x = get(0).toInt()
        y = get(1).toInt()
    }
    //println("$x , $y")
    if(!isValid(board,x,y)) return false
    return true
}

fun isWin(board:Array<CharArray>,x:Int,y:Int):Boolean{
    // 승리 기준 검사 - isWin( )
    val dx = arrayOf(-1,1,0,0,-1,1,1,-1)
    val dy = arrayOf(0,0,-1,1,-1,1,-1,1)

    for(d in 0..3){
        var count = 1
        for(b in 0..1){
            val index = 2*d+b
            var cx = x+dx[index]
            var cy = y+dy[index]
            while(isInRange(cx,cy)){
                if(board[cy][cx]  == board[y][x]) count ++
                cx += dx[index]
                cy += dy[index]
            }
        }
        if(count == 3) return true
    }
    return false
}

fun play(board:Array<CharArray>,name:Array<String>,marks:Array<Char>):Unit{
    // 게임 진행 - play( )
    var round = 0
    var turn = 0

    while(true){
        println("\n ${turn + 1}번째 턴\n")
        printBoard(board)
        if (!playerInput(name[turn], board))
            continue
        board[y][x] = marks[turn]
        round++

        if(isWin(board,x,y)) {
            println("Tic Tac Toe 가 종료되었습니다 -- 결과확인")
            printBoard(board)
            break
        }

        when(turn){
            1->turn = 0
            0->turn = 1
            else->print("kk")
        }

    }

}