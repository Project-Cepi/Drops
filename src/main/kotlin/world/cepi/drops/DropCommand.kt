package world.cepi.drops

import net.minestom.server.command.builder.arguments.ArgumentType
import world.cepi.itemextension.item.cepiItem
import world.cepi.kepi.item.AddCreationalItem
import world.cepi.kstom.command.arguments.literal
import world.cepi.kstom.command.kommand.Kommand

object DropCommand : Kommand({
    val create by literal

    syntax(create).onlyPlayers {
        AddCreationalItem.put(player, Drops().renderItem())
    }

    val add by literal
    val chance = ArgumentType.Double("chance")

    syntax(add, chance).onlyPlayers {
        val drop = player.itemInMainHand.dropItem ?: return@onlyPlayers

        val item = player.itemInOffHand.cepiItem ?: return@onlyPlayers

        player.itemInMainHand = drop.addItem(DropItem(item, player.itemInOffHand.amount, !chance)).renderItem()
    }

    val animate by literal

    syntax(animate).onlyPlayers {
        val drop = player.itemInMainHand.dropItem ?: return@onlyPlayers

        drop.dropItems(player.instance!!, player.position.add(0.0, 1.0, 0.0))
    }
}, "drop")