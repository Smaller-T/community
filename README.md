## 麻酱凉皮

## 资料
[Spring文档](https://spring.io/guides)
[Spring Web](https://spring.io/guides/gs/serving-web-content/)
[es社区](https://elasticsearch.cn/explore)

[Bootstrap组件](https://v3.bootcss.com/components/)

[Create an Github OAuth App](https://docs.github.com/en/developers/apps/building-oauth-apps/creating-an-oauth-app)

[okHttp](https://square.github.io/okhttp/)

[Mybatis官网](https://mybatis.org/mybatis-3/zh/getting-started.html)

[Spring embedded-database](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/#boot-features-embedded-database-support)
## 工具
[Git](https://git-scm.com/download)

[Visual Paradigm](https://www.visual-paradigm.com)

[Flyway拿到maven依赖](https://flywaydb.org/documentation/getstarted/firststeps/maven)

[Lombok](https://projectlombok.org/) 点击install，选择maven，直接添加maven依赖

## 脚本
```sql 
create table USER
(
	ID INT auto_increment NOT NULL,
	ACCOUNT_ID VARCHAR(100),
	NAME VARCHAR(50),
	TOKEN CHAR(36),
	GMT_CREATE BIGINT,
	GMT_MODIFIED BIGINT,
	constraint USER_PK
		primary key (ID)
);
```

```bash
# 将V*__ADD_BIO_TO_USER_TABLE.sql中的修改同步到一个数据库中，多人合作比较方便
mvn flyway:migrate   
```

## 笔记
[bootstrap的全局样式](https://v3.bootcss.com/css/) :设置边距等等
