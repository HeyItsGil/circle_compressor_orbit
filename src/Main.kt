import processing.core.PApplet
import processing.core.PConstants

class Main: PApplet(){
    private val warpyCircle: CentralCircle by lazy {
        CentralCircle(this)
    }

    init {
        runSketch()
    }

    override fun settings() {
        size(640, 480)
    }

    override fun setup() {
        colorMode(PConstants.HSB, 360f, 100f, 100f)
    }

    override fun draw() {
//        rect(mouseX.toFloat(), mouseY.toFloat(), 40f, 40f)
        background(0f, 0f, 100f)
        warpyCircle.display()
    }
}

fun main(args:Array<String>){
    Main()
}