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
        distanceFromItem = r / 3
        sizeOfItem = distanceFromItem * 0.85f
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
        for (item in this.items){
            sketch.beginShape()
            for (vertex in vertices) {
                //Mouse position does not get updated with the translate() method
//                val currentObjectPosition = PVector(sketch.mouseX.toFloat() - sketch.width / 2, sketch.mouseY.toFloat() - sketch.height / 2)
                val currentObjectPosition = applyObjectForce(item.position.copy())
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
            var itemR = item.position.mag()
            var itemAngle = atan2(item.position.y, item.position.x)
            itemAngle += 0.01f

            var itemX = itemR * cos(itemAngle)
            var itemY = itemR * sin(itemAngle)

            item.position.set(itemX, itemY)
            sketch.endShape(PConstants.CLOSE)
        }
    }

    fun applyObjects(objects :ArrayList<CircleWithPattern>){
        this.items = objects
        for (items in this.items){
            items.size = r/2
            items.position.set(0f, -r*1.2f)
        }
    }

    private fun applyObjectForce(objectPosition: PVector): PVector{
        val xForce = map(objectPosition.x, 0f, r, 0f, -30f)
        val yForce = map(objectPosition.y, 0f, r, 0f, -30f)
        objectPosition.x += xForce
        objectPosition.y += yForce

        return objectPosition
    }

    fun display() {
        sketch.fill(0f, 0f)
        sketch.pushMatrix()
        items[0].display()
        sketch.translate(sketch.width/2f, sketch.height/2f)
        updateVertices()
        sketch.popMatrix()
    }
}