# Bangumix ( 番組X )

## Synopsis

2018 年数据库概论中期作业, 使用 Kotlin on Spring Boot 为整体框架, MySQL 为
数据库服务, 搭建的 B/S 数据库服务 —— 一个简单的番组评价网站

## Usage

1. 依赖环境: Maven, Kotlin, MySQL

2. 下载代码后解压, 修改 resources 目录下的 application.properties 文件
以匹配本地的数据库信息

3. 在同一目录下使用 schema.sql 文件导入数据库表模式, 使用 data.sql 导入样例
数据,保持数据库连接打开

4. 在工程根目录 bangumix/ 下运行 `mvn clean spring-boot:run` 运行程序

5. 在浏览器中跳转到 localhost:8080 查看效果

## License

See [LICENSE](LICENSE)
