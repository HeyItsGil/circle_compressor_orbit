import javafx.scene.shape.Circle
import processing.core.PApplet
import processing.core.PConstants

class Main: PApplet(){
    private val numberOfInnerShapes = 5
    private var shapesArray: ArrayList<CircleWithPattern> = ArrayList<CircleWithPattern>()

    private val warpyCircle: CentralCircle by lazy {
        CentralCircle(this)
    }

    private val patternCircleOne: CircleWithPattern by lazy {
        CircleWithPattern(this)
    }

    init {
        runSketch()
    }

    override fun settings() {
        size(640, 480)
//        fullScreen()
//        pixelDensity(2)
//        noLoop()
    }

    override fun setup() {
        colorMode(PConstants.HSB, 360f, 100f, 100f)
        noStroke()
        for (i in 0..numberOfInnerShapes){
            shapesArray.add(CircleWithPattern(this))
        }
        warpyCircle.applyObjects(shapesArray)
    }

    override fun draw() {
        rect(mouseX.toFloat(), mouseY.toFloat(), 40f, 40f)
        background(39f, 60.75f, 100f)
        warpyCircle.display()
    }
}

fun main(args:Array<String>){
    Main()
}