package ai.platon.pulsar.crawl.fetch.driver

import ai.platon.pulsar.browser.common.BrowserSettings
import ai.platon.pulsar.browser.common.ScriptConfuser
import ai.platon.pulsar.browser.common.ScriptLoader
import ai.platon.pulsar.common.event.AbstractEventEmitter
import ai.platon.pulsar.crawl.fetch.privacy.BrowserId
import java.time.Duration
import java.time.Instant
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicBoolean
import kotlinx.coroutines.runBlocking

abstract class AbstractBrowser(
    override val id: BrowserId,
    val browserSettings: BrowserSettings
): Browser, AbstractEventEmitter<BrowserEvents>() {
    
    protected val mutableDrivers = ConcurrentHashMap<String, WebDriver>()
    protected val mutableRecoveredDrivers = ConcurrentHashMap<String, WebDriver>()
    protected val mutableReusedDrivers = ConcurrentHashMap<String, WebDriver>()
    
    protected val initialized = AtomicBoolean()
    protected val closed = AtomicBoolean()
    protected var lastActiveTime = Instant.now()
    
    override val userAgent = getRandomUserAgentOrNull()
    
    override val navigateHistory = NavigateHistory()
    override val drivers: Map<String, WebDriver> get() = mutableDrivers
    /**
     * The associated data.
     * */
    override val data: MutableMap<String, Any?> = mutableMapOf()
    
    override val isIdle get() = Duration.between(lastActiveTime, Instant.now()) > idleTimeout
    
    val confuser = ScriptConfuser()
    val scriptLoader = ScriptLoader(confuser, conf = browserSettings.conf)
    
    val isGUI get() = browserSettings.isGUI
    val idleTimeout = Duration.ofMinutes(10)
    
    init {
        attach()
    }
    
    override fun destroyDriver(driver: WebDriver) {
        // Nothing to do
    }
    
    override fun destroyForcibly() {
    
    }
    
    override fun clearCookies() {
        runBlocking {
            val driver = drivers.values.firstOrNull() ?: newDriver()
            driver.clearBrowserCookies()
        }
    }
    
    override fun maintain() {
        // Nothing to do
    }
    
    override fun onInitialize() {
        initialized.set(true)
    }
    
    override fun onWillNavigate(entry: NavigateEntry) {
        navigateHistory.add(entry)
    }
    
    override fun close() {
        detach()
        mutableRecoveredDrivers.clear()
        mutableDrivers.clear()
    }
    
    private fun getRandomUserAgentOrNull() = if (browserSettings.isUserAgentOverridingEnabled) {
        browserSettings.userAgent.getRandomUserAgent()
    } else null
    
    /**
     * Attach default event handlers
     * */
    protected fun attach() {
        on(BrowserEvents.initialize) { onInitialize() }
        on(BrowserEvents.willNavigate) { entry: NavigateEntry -> onWillNavigate(entry) }
        on(BrowserEvents.maintain) { maintain() }
    }
    
    /**
     * Detach default event handlers
     * */
    protected fun detach() {
        off(BrowserEvents.initialize)
        off(BrowserEvents.willNavigate)
        off(BrowserEvents.maintain)
    }
}
