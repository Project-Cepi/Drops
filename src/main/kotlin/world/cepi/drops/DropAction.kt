package world.cepi.drops

import net.minestom.server.entity.Entity
import world.cepi.actions.Action

@kotlinx.serialization.Serializable
class DropAction(val drops: Drops): Action() {

    override fun invoke(source: Entity, target: Entity?) {
        drops.dropItems(source.instance!!, source.position)
    }

}