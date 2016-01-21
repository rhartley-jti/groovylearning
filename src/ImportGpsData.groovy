def file = new File('../Data/fells_loop.gpx')

def slurper = new XmlSlurper()
def gpx = slurper.parse(file)

/*
 The ability to reference the xml elements and attributes off the
 gpx object is referred to as gpath.  Groovy determines at runtime
 if that element or attribute that was parsed exists.  If it is not
 found an error is not thrown, just no data returned.
 */

gpx.with {
    println name
    println ''

    println desc
    println ''

    println attributes()['version']
    println attributes()['creator']
}

gpx.rte.rtept.each {
    println it.@log
    println it.@lat

    def parser = new DateParser()
    println parser.parse(it.time.toString())
}

