package cz.jdr.app

import cz.jdr.app.props.PropertiesReader
import cz.jdr.app.props.PropertyKeys

fun main() {
    println("Hello person with email${PropertiesReader.getProperty(PropertyKeys.JIRA_EMAIL)}")
}