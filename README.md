# json_web_token_java_spring_boot
x=1001 
@Table(name="emd_employee_+x+")

@GeneratedValue(strategy=GenerationType.IDENTITY)

##spring.jpa.hibernate.ddl-auto=create-drop
#spring.jpa.generate-ddl=true
#
#spring.jpa.database=POSTGRESQL
#spring.datasource.platform=postgres
#spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
#
#spring.datasource.driverClassName=org.postgresql.Driver
#spring.datasource.url=jdbc:postgresql://localhost:5432/attendance
#spring.datasource.username=postgres
#spring.datasource.password=softlink
#server.port=8081
#
#spring.jpa.show-sql=true
#spring.session.store-type=none
#
#spring.jpa.hibernate.ddl-auto = update
#
#
##timestamp
#spring.jpa.properties.hibernate.jdbc.time_zone=UTC
#
##server multi
#spring.jmx.enabled = false




@TypeDefs({
    @TypeDef(name = "string-array", typeClass = StringArrayType.class),
    @TypeDef(name = "int-array", typeClass = IntArrayType.class),
    @TypeDef(name = "json", typeClass = JsonStringType.class),
    @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})

@Type(type = "jsonb")

		<!-- jackson -->
		<dependency>
		    <groupId>org.json</groupId>
		    <artifactId>json</artifactId>
		    <version>20180813</version>
		</dependency>
		
<!-- type jonb -->
<dependency>
    <groupId>com.vladmihalcea</groupId>
    <artifactId>hibernate-types-52</artifactId>
    <version>2.1.1</version>
</dependency> 
