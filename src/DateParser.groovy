@Grapes(
        @Grab(group='joda-time', module='joda-time', version='2.9.1')
)

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

class DateParser {
    def String parse(time) {
        def printableTime = new DateTime(time.toString())
        def format = DateTimeFormat.forPattern('MM/dd/yyyy - hh:mm aa')
        return printableTime.toString(format)
    }
}
