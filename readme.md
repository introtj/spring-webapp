# Spring PetClinic Clone Coding

이 프로젝트는 petclinic 을 분석하기 위해서 spring-framework-petclinic 을
clone coding 한 프로젝트 입니다.

원본 : [spring-framework-petclinic](https://github.com/spring-petclinic/spring-framework-petclinic)

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
./mvnw jetty:run-war
```

### IntelliJ IDEA
먼저, css 파일을 생성한다.
```
./mvnw generate-resources
```

메뉴 `Run -> Edit Configuration` 에서 Tomcat 추가 후 war artifact 추가.
그 다음 `Run` 버튼 클릭해 실행.
