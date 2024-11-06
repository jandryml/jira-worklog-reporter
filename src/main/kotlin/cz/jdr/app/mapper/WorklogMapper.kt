package cz.jdr.app.mapper

import cz.jdr.app.domain.Worklog

fun Worklog.toJson(): String = """
    {
        "started": "$started",
        "timeSpent": "$timeSpent",
        "comment": "$comment"
    }
""".trimIndent()