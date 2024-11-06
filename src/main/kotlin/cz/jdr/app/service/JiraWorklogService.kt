package cz.jdr.app.service

import cz.jdr.app.domain.Worklog
import cz.jdr.app.logger.logger
import cz.jdr.app.mapper.toJson
import cz.jdr.app.props.PropertiesReader
import cz.jdr.app.props.PropertyKeys
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class JiraWorklogService {
    private val logger by logger()
    private val client = HttpClient(CIO)

    private val userName = PropertiesReader.getProperty(PropertyKeys.JIRA_EMAIL)
    private val apiKey = PropertiesReader.getProperty(PropertyKeys.JIRA_API_KEY)
    private val baseUrl = PropertiesReader.getProperty(PropertyKeys.JIRA_URL)

    suspend fun getWorklogs(issueKey: String): HttpResponse {
        logger.info("Getting worklogs")

        return client.get {
            url("${baseUrl}/rest/api/2/issue/$issueKey/worklog")
            basicAuth(userName, apiKey)
            header("Accept", "application/json")
        }
    }

    suspend fun addWorklog(worklog: Worklog): HttpResponse {
        logger.info("Adding worklog")

        return client.post {
            url("${baseUrl}/rest/api/2/issue/${worklog.issueKey}/worklog")
            basicAuth(userName, apiKey)
            header("Accept", "application/json")
            header("Content-Type", "application/json")
            setBody(worklog.toJson())
        }
    }
}