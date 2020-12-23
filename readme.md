# Spring PetClinic Clone Coding

이 프로젝트는 petclinic 을 분석하기 위해서 spring-framework-petclinic 을
clone coding 한 프로젝트 입니다.

원본 : [spring-framework-petclinic](https://github.com/spring-petclinic/spring-framework-petclinic)

## Persistence layer 선택
spring.profiles 설정으로 persistence layer 를 선택 가능하도록 구성했다.

spring.profiles.default 설정 : web.xml
```
<context-param>
    <param-name>spring.profiles.default</param-name>
    <param-value>jpa</param-value>
</context-param>
```

VM 옵셔느로 아래 3 가지 옵션 중 하나를 사용하면 된다.

기본 값은 jpa 이다.
```
-Dspring.profiles.active=jpa
-Dspring.profiles.active=jdbc
-Dspring.profiles.active=spring-data-jpa
```

## Build Profiles
```
<profile>
    <id>H2</id>
    <activation>
        <activeByDefault>true</activeByDefault>
    </activation>
    <properties>
        <environment>h2</environment>
    </properties>
    <dependencies>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>${h2database.version}</version>
            <scope>runtime</scope>
        </dependency>
    </dependencies>
</profile>
<profile>
    <id>HSQLDB</id>
    <properties>
        <environment>hsqldb</environment>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.hsqldb</groupId>
            <artifactId>hsqldb</artifactId>
            <version>${hsqldb.version}</version>
            <scope>runtime</scope>
        </dependency>
    </dependencies>
</profile>
<profile>
    <id>MySQL</id>
    <properties>
        <environment>mysql</environment>
    </properties>
    <dependencies>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>${mysql-driver.version}</version>
            <scope>runtime</scope>
        </dependency>
    </dependencies>
</profile>
<profile>
    <id>PostgreSQL</id>
    <properties>
        <environment>postgresql</environment>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>${postgresql-driver.version}</version>
            <scope>runtime</scope>
        </dependency>
    </dependencies>
</profile>
```
## webjar 적용
pom.xml
```
<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>jquery</artifactId>
    <version>${webjars-jquery.version}</version>
</dependency>

<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>jquery-ui</artifactId>
    <version>${webjars-jquery-ui.version}</version>
</dependency>

<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>bootstrap</artifactId>
    <version>${webjars-bootstrap.version}</version>
</dependency>
```

servlet-context.xml
```
<mvc:resources mapping="/webjars/**" location="classpath:/META-INF/resources/webjars/" />
```

## webjar-locator 적용
pom.xml
```
<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>webjars-locator</artifactId>
    <version>${webjars-locator.version}</version>
</dependency>
```

servlet-context.xml
```
<mvc:resources mapping="/webjars/**" location="classpath:/META-INF/resources/webjars/">
    <mvc:resource-chain resource-cache="false" />
</mvc:resources>
```
## Running petclinic locally

### With Maven command line
```
./mvnw jetty:run-war -P H2 -Dspring.profiles.active=jdbc
```

### IntelliJ IDEA
먼저, css 파일을 생성한다.
```
./mvnw generate-resources
```

메뉴 `Run -> Edit Configuration` 에서 Tomcat 추가 후 war artifact 추가.
그 다음 `Run` 버튼 클릭해 실행.
