package cz.jdr.app

import cz.jdr.app.domain.Worklog
import cz.jdr.app.service.JiraWorklogService
import io.ktor.client.call.*
import cz.jdr.app.logger.logger

object Main {
    val log by logger()
}

    
suspend fun main() {
    val jiraWorklogService = JiraWorklogService()
    val log = Main.log

    val responseBody: String = jiraWorklogService.getWorklogs("ROSS-3583").body()

    log.info(responseBody)

    val worklog = Worklog(
        issueKey = "ROSS-287",
        started = "2024-10-03T12:00:00.000+0000",
        timeSpent = "0,25m",
        comment = "ROSS - Daily Scrum"
    )

    val response = jiraWorklogService.addWorklog(worklog)
    
    log.info(response.status.toString())
    log.info(response.body())
}
