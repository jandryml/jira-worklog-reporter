package cz.jdr.app.domain

data class Worklog(
    val issueKey: String,
    val started: String,
    val timeSpent: String,
    val comment: String
)