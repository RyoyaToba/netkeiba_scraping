# scraping

学習用のデータをネット上から取得するため、Javaのサードパーティである[JSOUP](https://jsoup.org/download)を活用したスクレイピングを行なっています。

## 使用しているライブラリ

* JSOUP-jar(1.14.3)
* Postgresql-jar(42.3.4)

## 使用例

構築しているのは、競馬情報を取得するためのスクレイピングコードです。Netkeiba（株式会社ネットドリーマーズ様）のサイトより

* レース情報（レース名、日付、馬場状態など）

* レース結果（着順、競走馬名、騎手名など）

* 払い戻し額（馬番、払戻金、人気など）

* 競走馬情報（馬名、父馬、母馬など）

といった情報を取得することができます。

