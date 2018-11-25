import processing.core.*
import kotlin.math.PI

class CircleWithPattern(private val sketch: PApplet) {
    var radius = sketch.width/2f
    var innerCircleRadius = radius/4f
    var angle = 2 * PI.toFloat()
    var position = PVector(0f, 0f)
    var pattern = 0
    var rotateAntiClockwise = false
    var rotationSpeed = 0.005f
    var size: Float
        get() = this.radius
        set(value) {
            this.radius = value
            this.innerCircleRadius = this.radius/4
        }

    fun display(colourRange: Float = 0f, alphaRange: Float = 200f){
        sketch.pushMatrix()
        sketch.translate(sketch.width/2f, sketch.height/2f)
//        sketch.stroke(0)
        sketch.fill(219f, 80f, 100f, 100f)
        sketch.ellipse(position.x,position.y, radius.toFloat(), radius.toFloat())
        sketch.translate(position.x, position.y)
        sketch.pushMatrix()
        displayPattern()
        sketch.popMatrix()
        if (rotateAntiClockwise) angle -= rotationSpeed else angle += rotationSpeed
        sketch.popMatrix()
    }

    fun drawCircle(x: Float, y: Float, radiusFactor: Float = 1f){
        sketch.ellipse(x, y, innerCircleRadius*radiusFactor, innerCircleRadius*radiusFactor)
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
