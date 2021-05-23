# [Java-Maven](https://github.com/happyflyer/Java-Maven)

- Apache 下的一个纯 Java 开发的开源项目
- 基于项目对象模型（缩写：POM）概念
- Maven 是一个项目管理工具，可以对 Java 项目进行构建、依赖管理
- Maven 利用一个中央信息片断能管理一个项目的构建、报告和文档等步骤

## 1. 配置文件

位于 `${MAVEN_HOME}/conf/setting.xml`。

### 1.1. 本地仓库路径

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
          http://maven.apache.org/xsd/settings-1.0.0.xsd">
  <
   | The path to the local repository maven will use to store artifacts.
   |
   | Default: ${user.home}/.m2/repository
  -->
  <localRepository>/path/to/local/repo</localRepository>
</settings>
```

### 1.2. 国内镜像源

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
          http://maven.apache.org/xsd/settings-1.0.0.xsd">
  <mirrors>
    <mirror>
      <id>nexus-aliyun</id>
      <mirrorOf>central</mirrorOf>
      <name>Nexus aliyun</name>
      <url>http://maven.aliyun.com/nexus/content/groups/public</url>
    </mirror>
  </mirrors>
</settings>
```

### 1.3. Java 版本

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
          http://maven.apache.org/xsd/settings-1.0.0.xsd">
  <profiles>
    <profile>
      <id>jdk11</id>
      <activation>
        <activeByDefault>true</activeByDefault>
        <jdk>11</jdk>
      </activation>
      <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <maven.compiler.compilerVersion>11</maven.compiler.compilerVersion>
      </properties>
    </profile>
  </profiles>
</settings>
```

## 2. 坐标和仓库

### 2.1. 坐标

```xml
<groupId>org.example</groupId>
<artifactId>Java-Maven</artifactId>
<version>0.1.0</version>
```

```bash
mvn archetype:generate \
  -DgroupId=org.example \
  -DartifactId=Java-Maven \
  -Dversion=0.1.0 \
  -Dpackage=org.example.java_maven
```

### 2.2. 仓库

- 本地仓库
- [远程仓库](https://mvnrepository.com/)
- [镜像仓库](https://maven.aliyun.com/mvn/guide)

## 3. 项目目录结构

- src
  - main
    - java
      - packages
    - resources
  - test
    - java
      - packages
    - resources
- pom.xml

## 4. 三个标准的生命周期

- `clean`：项目清理的处理
- `default`(或 `build`)：项目部署的处理
- `site`：项目站点文档创建的处理

### 4.1. clean 生命周期

- `pre-clean`：执行一些需要在 `clean` 之前完成的工作
- `clean`：移除所有上一次构建生成的文件
- `post-clean`：执行一些需要在 `clean` 之后立刻完成的工作

### 4.2. build 生命周期

- `validate`：验证项目是否正确且所有必须信息是可用的
- `compile`：源代码编译在此阶段完成
- `test`：使用适当的单元测试框架（例如 JUnit）运行测试。
- `package`：创建 jar / war 包如在 `pom.xml` 中定义提及的包
- `verify`：对集成测试的结果进行检查，以保证质量达标
- `install`：安装打包的项目到本地仓库，以供其他项目使用
- `deploy`：拷贝最终的工程包到远程仓库中，以共享给其他开发人员和工程

### 4.3. site 生命周期

- `pre-site`：执行一些需要在生成站点文档之前完成的工作
- `site`：生成项目的站点文档
- `post-site`：执行一些需要在生成站点文档之后完成的工作，并且为部署做准备
- `site-deploy`：将生成的站点文档部署到特定的服务器上

## 5. POM 标签

### 5.1. 模型版本

- `project`
  - `modelVersion`

### 5.2. 项目坐标

- `project`
  - `groupId`
  - `artifactId`
  - `version`
  - `packaging`
  - `name`
  - `url`
  - `description`
  - `developers`
  - `licenses`
  - `organization`

`version` 可选值：

- `Snapshot`
- `Alpha`
- `Beta`
- `Release`

### 5.3. 模块管理

- `project`
- `parent`：用于子模块中对父模块的继承
- `modules`：用于聚合运行多个的 Maven 项目
  - `module`：指定多个模块，可以在一起编译

### 5.4. 项目依赖

- `project`
  - `dependencies`
    - `dependency`
      - `groupId`
      - `artifactId`
      - `version`
      - `type`
      - `scope`

`scope` 可选值：

- `compile`
- `provided`
- `runtime`
- `time`
- `system`
- `import`

### 5.5. 依赖管理

- `project`
  - `dependencyManagement`
    - `dependencies`
      - `dependency`

### 5.6. 构建插件

- `project`
  - `build`
    - `plugins`
      - `plugin`
        - `groupId`
        - `artifactId`
        - `version`

## 6. 依赖冲突

A 和 B 依赖不同版本的相同的构件，那么对于依赖 A 和 B 的 C 来说，究竟是依赖哪个版本的构件？

- 短路优先原则
- 先声明先优先原则
