import processing.core.*
import kotlin.math.PI

class CircleWithPattern(private val sketch: PApplet) {
    var radius = sketch.width/2f
    var innerCircleRadius = radius/4f
    var angle = 2 * PI


    fun display(colourRange: Float = 0f, alphaRange: Float = 200f){
        sketch.pushMatrix()
        sketch.translate(sketch.width/2f, sketch.height/2f)
        sketch.stroke(0)
        sketch.ellipse(0f,0f, radius.toFloat(), radius.toFloat())
        innerCirclesOne()
//        innerCirclesTwo()
//        innerCirclesThree()
//        innerCirclesFour()
        angle+= 0.015
        sketch.popMatrix()
    }

    fun setSize(radius: Float) {
        this.radius = radius
        this.innerCircleRadius = this.radius/4

    }

    fun drawCircle(x: Float, y: Float, radiusFactor: Float = 1f){
        sketch.ellipse(x, y, innerCircleRadius*radiusFactor, innerCircleRadius*radiusFactor)
    }

    fun innerCirclesOne(){
        sketch.rotate(angle.toFloat())
        sketch.ellipse(0f, -innerCircleRadius, innerCircleRadius*2f, innerCircleRadius*2f)
        sketch.ellipse(0f, innerCircleRadius*1.5f, innerCircleRadius, innerCircleRadius)
    }

    fun innerCirclesTwo(){
        sketch.rotate(angle.toFloat())
        drawCircle(0f, innerCircleRadius)
        drawCircle(-innerCircleRadius, innerCircleRadius*0.5f)
        drawCircle(innerCircleRadius, innerCircleRadius*0.5f)

        drawCircle(0f, -innerCircleRadius)
        drawCircle(-innerCircleRadius, -innerCircleRadius*.5f)
        drawCircle(innerCircleRadius, -innerCircleRadius*.5f)
    }

    fun innerCirclesThree(){
        sketch.rotate(angle.toFloat())
        drawCircle(0f, -innerCircleRadius*.75f, 1.5f)
        drawCircle(0f, innerCircleRadius*.75f, 1.5f)
        drawCircle(-innerCircleRadius*.75f, 0f, 1.5f)
        drawCircle(innerCircleRadius*0.75f, 0f, 1.5f)
    }

    fun innerCirclesFour(){
        val radFactor = 1.5f
        sketch.rotate(angle.toFloat())
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
