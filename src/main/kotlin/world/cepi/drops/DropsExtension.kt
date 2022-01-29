package world.cepi.drops

import net.minestom.server.extensions.Extension;
import net.minestom.server.utils.time.TimeUnit
import world.cepi.actions.list.ActionManager
import world.cepi.kstom.Manager
import world.cepi.kstom.util.log

class DropsExtension : Extension() {

    override fun initialize(): LoadStatus {
        DropCommand.register()

        log.info("[Drops] has been enabled!")

        Manager.scheduler.buildTask {
            ActionManager.add(DropAction::class)
        }.delay(1, TimeUnit.SERVER_TICK).schedule()

        return LoadStatus.SUCCESS
    }

    override fun terminate() {
        DropCommand.unregister()

        log.info("[Drops] has been disabled!")
    }

}
