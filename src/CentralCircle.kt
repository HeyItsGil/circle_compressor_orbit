import processing.core.PApplet
import processing.core.PApplet.*
import processing.core.PConstants
import processing.core.PVector

class CentralCircle(private val sketch: PApplet) {
    var vertices = mutableListOf<Vertex>()
    private val r: Float by lazy {
        sketch.width / 5f
    }

    lateinit var items: ArrayList<CircleWithPattern>

    var distanceFromItem = 0f
    var sizeOfItem = 10f

    init {
        distanceFromItem = r / 4
        val sides = 360
        val angle = 360 / sides
        for (i in 0 until sides) {
            val theta = radians(i * angle.toFloat())
            val x = r * cos(theta)
            val y = r * sin(theta)
            vertices.add(Vertex(PVector(x, y)))
        }
    }

    private fun updateVertices(){
        for (items in this.items){
            sketch.beginShape()
            for (vertex in vertices) {
                //Mouse position does not get updated with the translate() method
//                val currentMousePosition = PVector(sketch.mouseX.toFloat() - sketch.width / 2, sketch.mouseY.toFloat() - sketch.height / 2)
                val currentObjectPosition = PVector(items.xPos, items.yPos)
                sketch.ellipse(currentObjectPosition.x, currentObjectPosition.y, 50f, 50f)
                var force = r

                val distanceBetweenObjectAndVertexCurrent = PVector.dist(currentObjectPosition, vertex.currentLocation)

                vertex.currentLocation.normalize()
                if (distanceBetweenObjectAndVertexCurrent < distanceFromItem) {
                    val vertexLimiter = map(distanceBetweenObjectAndVertexCurrent, 0f, distanceFromItem, sizeOfItem, 0f)
                    force -= vertexLimiter
                }
                vertex.currentLocation.mult(force)

                sketch.vertex(vertex.currentLocation.x, vertex.currentLocation.y)
            }
            sketch.endShape(PConstants.CLOSE)
        }
    }

    fun applyObjects(objects :ArrayList<CircleWithPattern>){
        this.items = objects
        for (items in this.items){
            items.setSize(r/2)
        }

    }

    fun display() {
        sketch.fill(0f, 0f)
        sketch.pushMatrix()
        items[0].display(yPos = -r*1.2f)
        sketch.translate(sketch.width/2f, sketch.height/2f)
        updateVertices()
        sketch.popMatrix()
    }
}