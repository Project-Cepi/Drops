package world.cepi.drops

import kotlinx.serialization.Serializable
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.coordinate.Point
import net.minestom.server.entity.ItemEntity
import net.minestom.server.instance.Instance
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import net.minestom.server.utils.time.TimeUnit
import world.cepi.itemextension.item.itemSerializationModule
import world.cepi.kstom.item.get
import world.cepi.kstom.item.item
import world.cepi.kstom.item.set
import world.cepi.kstom.serializer.DurationSerializer
import java.time.Duration

@Serializable
data class Drops(
    val drops: List<DropItem> = listOf(),
    val powerOptions: PowerOptions = PowerOptions(),
    @Serializable(with = DurationSerializer::class)
    val timeout: Duration = Duration.of(3, TimeUnit.SECOND)
) {
    fun renderItem(): ItemStack = item(Material.LIME_DYE) {
        displayName(
            Component.text("Drop Set", NamedTextColor.GREEN)
                .decoration(TextDecoration.ITALIC, false)
                .append(Component.text(" (", NamedTextColor.DARK_GRAY))
                .append(Component.text(drops.size, NamedTextColor.GRAY))
                .append(Component.text(")", NamedTextColor.DARK_GRAY))
        )

        set("drop", item = this@Drops, module = itemSerializationModule)
    }

    fun calculateChance(): List<DropItem> = drops.filter { it.chance() }

    fun dropItems(
        instance: Instance,
        point: Point
    ): List<ItemEntity> {
        val items = calculateChance()

        return items.map {
            it.drop(instance, point, powerOptions)
        }
    }

    fun addItem(item: DropItem) = copy(drops = drops + item)
}

val ItemStack.dropItem: Drops? get() = get("drop", module = itemSerializationModule)