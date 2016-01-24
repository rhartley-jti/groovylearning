@GrabConfig(systemClassLoader=true)
@Grapes([
        @Grab(group='mysql', module='mysql-connector-java', version='5.1.26')
])
import groovy.sql.Sql

def sql = Sql.newInstance("jdbc:mysql://192.168.15.10:3306/gps", "gps", "gps", "com.mysql.jdbc.Driver")

//sql.eachRow('select * from routepoints where temperature < 50') {
//    println "Latitude: ${it.latitude}, Longitude: ${it.longitude}, " +
//            "Timestep: ${it.timestep}, Temperature: ${it.temperature}"
//}

//def row = sql.firstRow('select latitude,longitude from routepoints')
//println "Latitude ${row.latitude}, Longitude: ${row.longitude}, Temperature: ${row.temperature}"

def newTemperature = 100
sql.executeUpdate("update routepoints set temperature = ${newTemperature}")

sql.close()