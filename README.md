## プロジェクト概要

TODO LIST

タスクとそのリストを管理するアプリ

## 使用した技術要素

homebrew 

reference : https://qiita.com/_daisuke/items/d3b2477d15ed2611a058

java

version : 1.8

maven

version : 3.5.2

Spring boot 

version : 1.5.9

mysql 

version : 5.7.20

## 全体の設計・構成についての説明

### DB設計

https://github.com/hhhirokunnn/todolist/wiki/DB%E8%A8%AD%E8%A8%88

### API設計

https://github.com/hhhirokunnn/todolist/wiki/API%E8%A8%AD%E8%A8%88

### URL設計

https://github.com/hhhirokunnn/todolist/wiki/URL%E8%A8%AD%E8%A8%88

## 開発環境のセットアップ手順

### HOMEBREW

インストール

```
$ ruby -e "$(curl -fsSL https://raw.github.com/Homebrew/homebrew/go/install)"
```

バージョン確認

```
$ brew -v
Homebrew 1.4.1
```

### JAVA

インストール

```
$ brew cask install java
```

バージョン確認

```
$ java -version
java version "1.8.0_121"
```

### MAVEN

インストール

```
$ brew install maven
```

バージョン確認

```
$ mvn -v
Apache Maven 3.5.2 (138edd61fd100ec658bfa2d307c43b76940a5d7d; 2017-10-18T16:58:13+09:00)
```

### MYSQL

インストール

```
$ brew install mysql
```

バージョン確認

```
$ mysql -uroot -D mysql -e "select version();"
+-----------+
| version() |
+-----------+
| 5.7.20    |
+-----------+
```

### TOMCAT 


以下参照

http://koulog.hatenablog.com/entry/2017/02/27/220843


### ECLIPSE

以下URLよりインストール

https://www.eclipse.org/neon/


eclipseからspring bootを使用するのに必要なものをダウンロード

以下参照（STSダウンロードまで）

https://dev.classmethod.jp/server-side/java/using_spring_boot_1/

