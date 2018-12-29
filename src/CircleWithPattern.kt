import processing.core.*
import kotlin.math.PI

class CircleWithPattern(private val sketch: PApplet) {
    var radius = sketch.width/2f
    var innerCircleRadius = radius/4f
    var angle = 2 * PI.toFloat()
    var position = PVector(0f, 0f)
    var pattern = 0
    var rotate = true
    var rotateAntiClockwise = false
    var rotatePatternAntiClockwise = false
    var rotationSpeed = 0.005f
    var size: Float
        get() = this.radius
        set(value) {
            this.radius = value
            this.innerCircleRadius = this.radius/4
        }
    var orbitingPace = 0.0025f
    var shapeColor = this.sketch.color(219f, 61f, 100f, 100f)

    fun display(colourRange: Float = 0f, alphaRange: Float = 200f){
        sketch.pushMatrix()
        sketch.fill(this.shapeColor)
        sketch.ellipse(position.x,position.y, radius.toFloat(), radius.toFloat())
        sketch.translate(position.x, position.y)
        sketch.pushMatrix()
        displayPattern()
        sketch.popMatrix()
        if (rotate) {
            if (rotatePatternAntiClockwise) angle -= rotationSpeed else angle += rotationSpeed
        }
        sketch.popMatrix()
    }

    fun drawCircle(x: Float, y: Float, radiusFactor: Float = 1f){
        sketch.ellipse(x, y, innerCircleRadius*radiusFactor, innerCircleRadius*radiusFactor)
    }

    //named params 'value' as the colorMode could be either RGB or HSB
    fun applyColour(value1: Float, value2: Float, value3: Float, alpha: Float = 100f){
        this.shapeColor = sketch.color(value1, value2, value3, alpha)
    }

    private fun displayPattern() {
        when (pattern){
            0 -> patternOne()
            1 -> patternOneReflection()
            2 -> patternTwo()
            3 -> patternThree()
            4 -> patternFour()
        }
    }

    fun patternOne(){
        sketch.rotate(angle)
        sketch.ellipse(0f, -innerCircleRadius, innerCircleRadius*2f, innerCircleRadius*2f)
        sketch.ellipse(0f, innerCircleRadius*1.5f, innerCircleRadius, innerCircleRadius)
    }

    fun patternOneReflection() {
        sketch.rotate(angle)
        drawCircle(0f, innerCircleRadius, 2f)
        drawCircle(0f, -innerCircleRadius*1.5f)
    }

    fun patternTwo(){
        sketch.rotate(angle)
        drawCircle(0f, innerCircleRadius)
        drawCircle(-innerCircleRadius, innerCircleRadius*0.5f)
        drawCircle(innerCircleRadius, innerCircleRadius*0.5f)

        drawCircle(0f, -innerCircleRadius)
        drawCircle(-innerCircleRadius, -innerCircleRadius*.5f)
        drawCircle(innerCircleRadius, -innerCircleRadius*.5f)
    }

    fun patternThree(){
        sketch.rotate(angle)
        drawCircle(0f, -innerCircleRadius*.75f, 1.5f)
        drawCircle(0f, innerCircleRadius*.75f, 1.5f)
        drawCircle(-innerCircleRadius*.75f, 0f, 1.5f)
        drawCircle(innerCircleRadius*0.75f, 0f, 1.5f)
    }

    fun patternFour(){
        val radFactor = 1.5f
        sketch.rotate(angle)
        drawCircle(0f, -innerCircleRadius*.75f, radFactor)
        drawCircle(0f, innerCircleRadius*.75f, radFactor)
        drawCircle(-innerCircleRadius*.75f, 0f, radFactor)
        drawCircle(innerCircleRadius*0.75f, 0f, radFactor)

        drawCircle(innerCircleRadius*0.75f, -innerCircleRadius*.75f, radFactor)
        drawCircle(innerCircleRadius*0.75f, innerCircleRadius*.75f, radFactor)
        drawCircle(-innerCircleRadius*0.75f, -innerCircleRadius*.75f, radFactor)
        drawCircle(-innerCircleRadius*0.75f, innerCircleRadius*.75f, radFactor)
    }
}
