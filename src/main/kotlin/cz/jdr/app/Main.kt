package cz.jdr.app

import cz.jdr.app.service.JiraWorklogService
import io.ktor.client.call.*
import cz.jdr.app.logger.logger

object Main {
    val log by logger()
}
    
suspend fun main() {
    val jiraWorklogService = JiraWorklogService()

    val responseBody: String = jiraWorklogService.getWorklogs("ROSS-3583").body()

    Main.log.info(responseBody)
}
