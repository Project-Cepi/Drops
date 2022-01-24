package world.cepi.drops

import kotlinx.serialization.Serializable
import net.minestom.server.coordinate.Point
import net.minestom.server.coordinate.Vec
import net.minestom.server.entity.ItemEntity
import net.minestom.server.instance.Instance
import world.cepi.itemextension.item.Item
import java.util.concurrent.ThreadLocalRandom

@Serializable
class DropItem(val item: Item, val amount: Int, val chance: Double) {

    fun drop(instance: Instance, position: Point): ItemEntity {
        val entity = ItemEntity(item.renderItem(1))

        entity.setInstance(instance, position)

        entity.velocity = Vec(
            ThreadLocalRandom.current().nextDouble(1.0),
            ThreadLocalRandom.current().nextDouble(1.0),
            ThreadLocalRandom.current().nextDouble(1.0)
        )

        return entity
    }

    fun chance() = ThreadLocalRandom.current().nextDouble(100.0) <= chance

}