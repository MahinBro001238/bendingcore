plugins {
    java
}
group = "com.mahin"
version = "1.0"
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}
repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}
dependencies {
    compileOnly("io.papermc.paper:paper-api:26.2.build.+")
}