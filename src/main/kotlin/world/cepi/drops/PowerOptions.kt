package world.cepi.drops

import kotlinx.serialization.Serializable
import net.minestom.server.coordinate.Vec
import net.minestom.server.utils.math.DoubleRange
import world.cepi.kstom.util.random

/**
 * Defines the power options for a drop. This can be used to change certain
 * parts of the velocity for a drop. For example,
 * Given the arguments:
 * 0, 0
 * 0, 10,
 * 0, 0
 *
 * The item can fly in the Y direction from 0 to 10.
 */
@Serializable
data class PowerOptions(
    val minX: Double = -1.5,
    val maxX: Double = 1.5,
    val minY: Double = -1.5,
    val maxY: Double = 1.5,
    val minZ: Double = -1.5,
    val maxZ: Double = 1.5
) {

    /**
     * Generates velocity from all properties in the constructor
     *
     * See [PowerOptions]
     */
    fun generateVelocity() = Vec(
        DoubleRange(minX, maxZ).random(),
        DoubleRange(minY, maxY).random(),
        DoubleRange(minZ, maxZ).random(),

    )
}