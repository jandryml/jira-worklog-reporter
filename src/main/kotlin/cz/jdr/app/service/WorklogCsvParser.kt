package cz.jdr.app.service

import cz.jdr.app.domain.Worklog
import java.io.InputStream
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class WorklogCsvParser {
    //    "Date","Task","Project","Client","Description","Time (h)","Time (decimal)","Amount (USD)"
//    "10/31/2024","ROSS-3345","ROSSMAN","(Without client)","ROSS-3345 - cr","00:15:00","0.25",""
    var sourceFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
    var targetFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    private operator fun <T> List<T>.component6(): T = get(5)
    private operator fun <T> List<T>.component7(): T = get(6)

    fun parseCsv(inputStream: InputStream): List<Worklog> {
        val reader = inputStream.bufferedReader()
        reader.readLine() // skip 

        return reader.lineSequence()
            .filter { it.isNotBlank() }
            .map {
                val (date, issueKey, _, _, description, _, time) = it.split( "\",\"", ignoreCase = false, limit = 8)
               
                val parsedDate =
                    LocalDate.parse(date.trim('"'), sourceFormatter).format(targetFormatter)+"T12:00:00.000+0000"

                Worklog(
                    issueKey = issueKey,
                    started = parsedDate,
                    timeSpent = time + "h",
                    comment = description
                )
            }.toList()
    }
}