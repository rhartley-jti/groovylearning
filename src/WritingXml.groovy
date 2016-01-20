def inFile = new File('../Data/fells_loop.gpx')
def slurper = new XmlSlurper()
def gpx = slurper.parse(inFile);

def markupBuilder = new groovy.xml.StreamingMarkupBuilder()
def xml = markupBuilder.bind {
    route {
        mkp.comment(gpx.name)
        gpx.rte.rtept.each { point ->
            // TODO: write xml
            routepoint(timestamp: point.time.toString()) {
                latitue(point.@lat)
                longitude(point.@lon)
            }
        }
    }
}

def outFile = new File('../Data/fells_loop_truncated.xml')
outFile.write(xml.toString())