package pl.paullettuce.dayscountdown.notfications.builder


sealed class NotificationContent(val contentText: String)

class OneLineContent(contentText: String) : NotificationContent(contentText)

class BigTextContent(
    contentText: String,
    val summaryText: String = ""
) : NotificationContent(contentText)
