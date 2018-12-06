import processing.core.PApplet
import processing.core.PApplet.*
import processing.core.PConstants
import processing.core.PVector

class CentralCircle(private val sketch: PApplet) {
    var vertices = mutableListOf<Vertex>()
    private val r: Float by lazy {
        sketch.width / 6f
    }

    lateinit var items: ArrayList<CircleWithPattern>

    var distanceFromItem = 0f
    var sizeOfItem = 10f

    init {
        distanceFromItem = r / 3f
        sizeOfItem = distanceFromItem * 0.5f
        val sides = 360
        val angle = 360 / sides
        for (i in 0 until sides) {
            val theta = radians(i * angle.toFloat())
            val x = r * cos(theta)
            val y = r * sin(theta)
            vertices.add(Vertex(PVector(x, y)))
        }
    }

    private fun updateVertices() {
        sketch.fill(219f, 80f,100f,100f)
        sketch.beginShape()
        for (vertex in vertices) {
            var force = r
            force = shapeDistanceChecker(vertex, force)
            vertex.currentLocation.normalize()
            vertex.currentLocation.mult(force)

            sketch.vertex(vertex.currentLocation.x, vertex.currentLocation.y)
        }

        respositionItem()
        sketch.endShape(PConstants.CLOSE)
    }

    private fun shapeDistanceChecker(vertex: Vertex, force: Float): Float {
        var force1 = force
        for (item in this.items) {
            //Mouse position does not get updated with the translate() method
    //                val currentObjectPosition = PVector(sketch.mouseX.toFloat() - sketch.width / 2, sketch.mouseY.toFloat() - sketch.height / 2)
            val currentObjectPosition = applyObjectForce(item.position.copy())

            val distanceBetweenObjectAndVertexCurrent = PVector.dist(currentObjectPosition, vertex.currentLocation)
            if (distanceBetweenObjectAndVertexCurrent < distanceFromItem) {
                val vertexLimiter = map(distanceBetweenObjectAndVertexCurrent, 0f, distanceFromItem, sizeOfItem, 0f)
                force1 -= vertexLimiter
            }
        }
        return force1
    }

    private fun respositionItem() {
        for (item in this.items) {
            val itemR = item.position.mag()
            var itemAngle = atan2(item.position.y, item.position.x)
            itemAngle += 0.0015f

            val itemX = itemR * cos(itemAngle)
            val itemY = itemR * sin(itemAngle)

            item.position.set(itemX, itemY)
        }
    }

    fun applyObjects(objects: ArrayList<CircleWithPattern>) {
        this.items = objects
        //1. Shapes on the outside
        items[0].size = r / 2
        items[0].position.set(-r * 1.2f, 0f)

        items[1].size = r / 2
        items[1].position.set(r * 1.2f, 0f)
        items[1].pattern = 1

        //2. 3 small shapes on the inside
        val polarR2 = r * .75f
        val angleGradations = 3*PI/2

        var theta = 3 * (PI/2)
        var x = polarR2 * cos(theta)
        var y = polarR2 * sin(theta)
        items[2].size = r / 2
        items[2].position.set(x, y)
        items[2].pattern = 3
        items[2].applyColour(39f, 61f, 100f)

        theta += (2*PI)/3
        x = polarR2 * cos(theta)
        y = polarR2 * sin(theta)
        items[3].size = r / 2
        items[3].position.set(x, y)
        items[3].pattern = 3
        items[3].applyColour(39f, 61f, 100f)

        theta += (2*PI)/3
        x = polarR2 * cos(theta)
        y = polarR2 * sin(theta)
        items[4].size = r / 2
        items[4].position.set(x, y)
        items[4].pattern = 3
        items[4].applyColour(39f, 61f, 100f)

        //3. Big shape in the centre
        items[5].size = r / 1.25f
        items[5].pattern = 3
        items[5].applyColour(39f, 61f, 100f)
        items[5].rotate = false

    }

    private fun applyObjectForce(objectPosition: PVector): PVector {
        val xForce = map(objectPosition.x, 0f, r, 0f, -distanceFromItem / 2f)
        val yForce = map(objectPosition.y, 0f, r, 0f, -distanceFromItem / 2f)
        objectPosition.x += xForce
        objectPosition.y += yForce

        return objectPosition
    }

    fun display() {
//        sketch.fill(0f, 0f)
        sketch.pushMatrix()
        sketch.translate(sketch.width / 2f, sketch.height / 2f)
        updateVertices()
        displayShapes()
        sketch.popMatrix()
    }

    private fun displayShapes() {
        for (shapes in this.items) {
            shapes.display()
        }
    }
}