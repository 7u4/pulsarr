package ai.platon.pulsar.common.proxy

import ai.platon.pulsar.common.concurrent.ScheduledMonitor
import ai.platon.pulsar.common.config.ImmutableConfig

open class ProxyPoolMonitor(
        val proxyPool: ProxyPool,
        private val conf: ImmutableConfig
): ScheduledMonitor() {

    override fun watch() {}
}
