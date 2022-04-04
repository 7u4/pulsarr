package ai.platon.pulsar.examples

import ai.platon.pulsar.common.sql.ResultSetFormatter
import ai.platon.pulsar.ql.context.SQLContext
import ai.platon.pulsar.ql.context.SQLContexts
import java.util.*

class SqlManual(val context: SQLContext = SQLContexts.create()) {
    private val url = "https://list.jd.com/list.html?cat=652,12345,12349"

    /**
     * Load [url] if it's not in the database or it's expired, and then
     * scrape the fields in the page, all fields are restricted in a page section specified by restrictCss,
     * each field is specified by a css selector
     *
     * expire time: 1 day
     * restrict css selector: li[data-sku]
     * css selectors for fields: .p-name em, .p-price
     * */
    fun scrape() = execute("""
        select
            dom_first_text(dom, '.p-price') as price,
            dom_first_text(dom, '.p-name em') as name
        from
            load_and_select('$url -i 1d -ii 7d', 'li[data-sku]')"""
    )

    fun scrapeOutPages() = execute("""
        select
            dom_first_text(dom, '.p-price') as price,
            dom_first_text(dom, '.sku-name') as name
        from
            load_out_pages('$url -i 1d -ii 7d', 'a[href~=item]')"""
    )

    fun runAll() {
        scrape()
        scrapeOutPages()
    }

    private fun execute(sql: String) {
        val regex = "^(SELECT|CALL).+".toRegex()
        if (sql.uppercase(Locale.getDefault()).filter { it != '\n' }.trimIndent().matches(regex)) {
            val rs = context.executeQuery(sql)
            println(ResultSetFormatter(rs, withHeader = true))
        } else {
            val r = context.execute(sql)
            println(r)
        }
    }
}

fun main() {
    SqlManual().runAll()
}
