package machine

enum class MachineState {
    STARTED, TAKE_ACTION, CLOSED
}

class CoffieMachine {
    private var water = 400
    private var milk = 540
    private var beans = 120
    private var d_cups = 9
    private var money = 550

    // water, milk,beans,cost
    private val espresso = listOf(250, 0, 16, 4)
    private val latte = listOf(350, 75, 20, 7)
    private val cappuccino = listOf(200, 100, 12, 6)

    private var state = MachineState.STARTED

    init {
        dispatch()
    }

    fun getInput(input: String): MachineState {
        return dispatch(input)
    }

    private fun dispatch(input: String = ""): MachineState {
        when (state) {
            MachineState.STARTED -> {
                displayCommandList()

            }
            MachineState.TAKE_ACTION -> {
                when (input) {
                    "buy" -> {
                        buy()
                        state = MachineState.STARTED
                        displayCommandList()
                    }
                    "fill" -> {
                        fill()
                        state = MachineState.STARTED
                        displayCommandList()
                    }
                    "take" -> {
                        take()
                        state = MachineState.STARTED
                        displayCommandList()
                    }
                    "remaining" -> {
                        remaining()
                        state = MachineState.STARTED
                        displayCommandList()
                    }
                    "exit" -> exit()
                }

            }
//            MachineState.REMAINING -> remaining()
            else -> {}
        }
        return state
    }

    private fun exit() {
        state = MachineState.CLOSED
    }

    private fun displayCommandList() {
        print("Write action (buy, fill, take, remaining, exit): >")
        state = MachineState.TAKE_ACTION
    }

    private fun remaining() {
        println(
            "\nThe coffee machine has:\n" +
                    "$water ml of water\n" +
                    "$milk ml of milk\n" +
                    "$beans g of coffee beans\n" +
                    "$d_cups disposable cups\n" +
                    "\$$money of money\n"
        )
    }

    private fun buy() {
        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: >")
        val buyWhat = readln()
        val what: List<Int>
        what = when (buyWhat) {
            "1" -> espresso
            "2" -> latte
            "3" -> cappuccino
            "back" -> return

            else -> return
        }
        if (what[0] > water) {
            println("Sorry, not enough water!")
            return
        }
        if (what[1] > milk) {
            println("Sorry, not enough milk!")
            return
        }

        if (what[2] > beans) {
            println("Sorry, not enough beans!")
            return
        }
        if (d_cups == 0) {
            println("Sorry, not enough cups!")
            return
        }
        water -= what[0]
        milk -= what[1]
        beans -= what[2]
        money += what[3]
        d_cups--
        println("I have enough resources, making you a coffee!")

    }

    private fun fill() {
        print("water ? ")
        val w = readln().toInt()
        print("milk ? ")
        val m = readln().toInt()
        print("beans ? ")
        val b = readln().toInt()
        print("cups ? ")
        val c = readln().toInt()

        water += w
        milk += m
        beans += b
//        money += what[3]
        d_cups += c


    }


    private fun take() {
        println("I gave you \$$money")
        money = 0
    }

    fun isWorking(): Boolean = state != MachineState.CLOSED


}

fun main() {

    val machine = CoffieMachine()

    var command: String
    while (machine.isWorking()) {

        command = readln()
        machine.getInput(command)
    }
}