package world.cepi.drops

import net.minestom.server.extensions.Extension;
import world.cepi.kstom.util.log

class DropsExtension : Extension() {

    override fun initialize(): LoadStatus {
        DropCommand.register()

        log.info("[Drops] has been enabled!")

        return LoadStatus.SUCCESS
    }

    override fun terminate() {
        DropCommand.unregister()

        log.info("[Drops] has been disabled!")
    }

}
