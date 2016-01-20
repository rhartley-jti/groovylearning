@Grapes(
        @Grab(group='joda-time', module='joda-time', version='2.9.1')
)

import org.joda.time.DateTime

class DateParserTests extends GroovyTestCase {
    def void testCanParseDateTime() {
        def parser = new DateParser()

        def result = parser.parse("2013-01-01T09:30:0")

        assert '01/01/2013 - 09:30 AM' == result
    }

    def void testWillThrowAnErrorWhenNullDateTimeIsProvided() {
        def parser = new DateParser()
        shouldFail(IllegalArgumentException) {
            parser.parse(null);
        }
    }
}
