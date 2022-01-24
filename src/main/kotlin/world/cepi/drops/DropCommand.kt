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
    val chance = ArgumentType.Double("chance").setDefaultValue(100.0)

    syntax(add, chance).onlyPlayers {
        val drop = player.itemInMainHand.dropItem ?: return@onlyPlayers

        val item = player.itemInOffHand.cepiItem ?: return@onlyPlayers

        player.itemInMainHand = drop.addItem(DropItem(item, player.itemInOffHand.amount, !chance)).renderItem()
    }

    val animate by literal

    syntax(animate).onlyPlayers {
        val drop = player.itemInMainHand.dropItem ?: return@onlyPlayers

        drop.dropItems(player.instance!!, player.position.add(0.0, 1.0, 0.0)).map {
            it.isPickable = false
        }
    }

    val power by literal

    val xPower = ArgumentType.FloatRange("xPower")
    val yPower = ArgumentType.FloatRange("yPower")
    val zPower = ArgumentType.FloatRange("zPower")

    syntax(power, xPower, yPower, zPower).onlyPlayers {
        val drop = player.itemInMainHand.dropItem ?: return@onlyPlayers

        player.itemInMainHand = drop.copy(powerOptions = PowerOptions(
            (!xPower).minimum.toDouble(),
            (!xPower).maximum.toDouble(),
            (!yPower).minimum.toDouble(),
            (!yPower).maximum.toDouble(),
            (!zPower).minimum.toDouble(),
            (!zPower).maximum.toDouble()
        )).renderItem()
    }

    val powerAmount = ArgumentType.FloatRange("powerAmount")

    syntax(power, powerAmount).onlyPlayers {
        val drop = player.itemInMainHand.dropItem ?: return@onlyPlayers

        player.itemInMainHand = drop.copy(powerOptions = PowerOptions(
            (!powerAmount).minimum.toDouble(),
            (!powerAmount).maximum.toDouble(),
            (!powerAmount).minimum.toDouble(),
            (!powerAmount).maximum.toDouble(),
            (!powerAmount).minimum.toDouble(),
            (!powerAmount).maximum.toDouble()
        )).renderItem()
    }
}, "drop")