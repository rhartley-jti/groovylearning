@GrabConfig(systemClassLoader=true)
@Grapes([
        @Grab(
                group = 'org.codehaus.groovy.modules.http-builder',
                module = 'http-builder',
                version = '0.7.1'),
        @Grab('mysql:mysql-connector-java:5.1.6'),
        @Grab(group='joda-time', module='joda-time', version='2.3')
])
import groovyx.net.http.RESTClient
import groovy.sql.Sql
import org.joda.time.DateTime

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

def apiUrl = 'https://api.forecast.io/'
def forcastApi = new RESTClient(apiUrl)
def credentialsFile = new File('credentials.groovy')
def configSlurper = new ConfigSlurper()
def credentials = configSlurper.parse(credentialsFile.toURI().toURL())
def sql = Sql.newInstance("jdbc:mysql://192.168.15.10:3306/gps", "gps", "gps", "com.mysql.jdbc.Driver");

gpx.rte.rtept.each {
    println it.@log
    println it.@lat

    def parser = new DateParser()
    println parser.parse(it.time.toString())

    def queryString = "forecast/$credentials.apiKey/${it.@lat},${it.@lon},${it.time}"
    def response = forcastApi.get(path: queryString)

    println "${response.data.currently.summary}"
    println "${response.data.currently.temperature} degrees"

    def routepoints = sql.dataSet("routepoints")
    routepoints.add(latitude: it.@lat.toDouble(), longitude: it.@lon.toDouble(),
        timestep: new DateTime(it.time.toString()).toDate(),
        temperature: response.data.currently.temperature)
}

sql.close()