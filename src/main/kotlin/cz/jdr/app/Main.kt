package cz.jdr.app

import cz.jdr.app.logger.logger
import cz.jdr.app.props.PropertiesReader
import cz.jdr.app.props.PropertyKeys
import cz.jdr.app.service.JiraWorklogService
import cz.jdr.app.service.WorklogCsvParser
import io.ktor.client.call.*
import java.io.File

object Main {
    val log by logger()
}

val log = Main.log
    
suspend fun main() {
    log.info("Starting application")


    val parser = WorklogCsvParser()
    val jiraWorklogService = JiraWorklogService()

// format in clockify report - DATE, TASK ,DESC
    val inputFile = File(PropertiesReader.getProperty(PropertyKeys.WORKLOGS_INPUT_FILE))

    if (inputFile.exists()) {
        val parseResult = parser.parseCsv(inputFile.inputStream())

        parseResult.forEach {
            log.info("Wogklog: $it")
        }

        val sum = parseResult.sumOf { it.timeSpent.removeSuffix("h").toDouble() }
        log.info("Total time: $sum")

        log.info("--- Want to continue? ---")
        print("y/n: ")
        val readLIne = readlnOrNull()

        if (readLIne == "y") {
            
            log.info("Continuing")
            // TODO add parallel processing
            parseResult.forEach { worklog ->
                log.info("Adding worklog: $worklog")

                val response = jiraWorklogService.addWorklog(worklog)
                log.info(response.status.toString())
                log.info(response.body())
            }
        } else {
            log.info("Exiting")
            return
        }
    } else {
        log.error("Input file does not exist")
        return
    }
}
