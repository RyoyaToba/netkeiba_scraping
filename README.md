# scraping

学習用のデータをネット上から取得するため、Javaのサードパーティである[JSOUP](https://jsoup.org/download)を活用したスクレイピングを行なっています。

## 使用しているライブラリ

* JSOUP-jar(1.14.3)
* Postgresql-jar(42.3.4)

## 用途

構築しているのは、競馬情報を取得するためのスクレイピングコードです。Netkeiba（株式会社ネットドリーマーズ様）のサイトより

* レース情報（レース名、日付、馬場状態など）

* レース結果（着順、競走馬名、騎手名など）

* 払い戻し額（馬番、払戻金、人気など）

* 競走馬情報（馬名、父馬、母馬など）

といった情報を取得することができます。

## 各パッケージ、クラスの役割

### Create

Createパッケージに属するクラス群は、実際にスクレイピングを行なっているクラスです。サイト上から必要なデータをスクレイピングし、Entity内の変数に格納しています。

### Entity

取得したいデータを詰めた入れ物のような役割です。このデータをもとにDBへ格納します。

### InsertCreater

Createパッケージ内のクラスで取得されたEntityのデータをDBに格納します。

### Parts

各クラスで繰り返し行われているような処理を切り出した部品のようなクラス群です。

### sample

実験用の作成途中のクラス群です。

## 使用例

NetKeibaサイトから、2021年のレース結果を取得したい時

**RaceResultEntity**

getter,setterは省略してあります。

```Java
package Entity;
public class RaceResultEntity {
	private String raceId;
	private String rank;
	private Integer waku;
	private Integer horseNumber;
	private String horseName;
	private String gender;
	private Integer age;
	private Double jockeyWeight;
	private String jockeyName;
	private String raceTime;
}
```

取得したいデータを格納するオブジェクトのクラスを作成します。この変数がそのままDBでのRaceResultテーブルのカラム名になっています。

**CreateRaceResultNewStyle**

このクラスは実際にWebページへアクセスし、スクレイピングによってデータを取得してきます。基本的なJSOUPの使い方に関しては公式リファレンス等を参照してください。クラス名ですがNewStyleがあるということはOldStyleもあります。これは、Netkeibaの構成が2007年以前からかなり変更されており、１つのスクレイピングコードでは取得ができなくなったためです。そのため、NewStyleクラスは2008年〜のデータの取得、OldStyleは〜2007年のデータの取得に使用しています。ポイントとなる部分のみ、コメントを記載しておきますので、是非ご覧ください。なお、きれいに描こうという意識はありますが、まだまだ修正すべきところはたくさんあると思いますので、精進いたします。

```Java
package Create;

public class CreateRaceResultNewStyle {

	public List<RaceResultEntity> createRaceResultNewStyle(String raceId) throws IOException {
    
    /* webサイトのUrlを設定します。ここでは、引数のraceIdによって接続先のサイトが変化します */
		String url = "https://race.netkeiba.com/race/result.html?race_id=" + raceId;
    
    /*　webページのdocumentの取得　*/
		Document document = Jsoup.connect(url).get();

		String rank = "";
		String gender = "";
		Integer age = 0;
		Double jockeyWeight = 0.1;
    
    /* 取得したいデータはtr属性のHorseListクラスによって囲まれています。
    接続先のページ（レース）によって出頭する馬数が異なるため、何個のEntityが取れるのかが変化します。
    そこでtr.HorseListのサイズを取得し。その数ループ処理を行うようにします */
		Integer horseSize = document.select("tr.HorseList").size();


		List<RaceResultEntity> raceResultList = new ArrayList<>();

		for (int i = 1; i <= horseSize; i++) {
  
			RaceResultEntity raceResult = new RaceResultEntity();
      
      
      /* rankingの取得。基本的にはJavaScriptのDOMの指定方法と同じだと思います。
      :nth-of-type("数値") とはその要素の"数値"番目を指定して取得してきてくれます*/
			Elements ranking = document.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(1)");
      
      /* 取得されたデータはElementsというListのような形で取得されます（１つでも）。そのため、for文によって要素を取り出します*/
			for (Element element : ranking) {
				rank = element.text();
			}
      
      /* 競馬特有の処理です。rankは数字だけでなく、除外、中止などの文字列も含まれてしまいます。
      DBのrankカラムをvarcharにする方法も考えましたが、Integerとしたため、文字列を特定の数字に変換させエラーを回避します。*/
			if (rank.equals("除外")) {
				rank = "-1";
			} else if (rank.equals("中止")) {
				rank = "99";
			} else if (rank.equals("取消")) {
				rank = "0";
			} else if (rank.equals("失格")) {
				rank = "100";
			}

			raceResult.setRaceId(raceId);
      
      /* 取得したrankをEntityに格納する。以下同様の処理*/
			raceResult.setRank(rank);
      
      /*　枠順の取得　*/
			Elements wakuing = document.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(2)");
			for (Element element : wakuing) {
				raceResult.setWaku(Integer.parseInt(element.text()));
			}
      
      /*　馬番の取得　*/
			Elements horseNumbering = document
					.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(3)");
			for (Element element : horseNumbering) {
				raceResult.setHorseNumber(Integer.parseInt(element.text()));
			}
      
      /*　馬名の取得　*/
			Elements horseNameing = document
					.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(4)");
			for (Element element : horseNameing) {
				raceResult.setHorseName(element.text());
			}
      
      /*　性別の取得　データは牝２のように取得されるため、”牝”　と　”２”　を分けています　*/
			Elements gendering = document
					.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(5)");
			for (Element element : gendering) {
				gender = (element.text()).substring(0, 1);
				raceResult.setGender(gender);
			}
      
      /*　年の取得　*/
			Elements ageing = document.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(5)");
			for (Element element : ageing) {
				String ageString = (element.text()).substring(1, 2);
				age = Integer.parseInt(ageString);
				raceResult.setAge(age);
			}
      
      /*　斤量の取得　レースによっては”未定”となる部分が存在しました。エラーの回避の処理を挟みます*/
			Elements jockWeighting = document
					.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(6)");
			for (Element element : jockWeighting) {
				if (element.text().equals("未定")) {
					jockeyWeight = 0.0;
				} else {
					jockeyWeight = Double.parseDouble(element.text());
				}
				raceResult.setJockeyWeight(jockeyWeight);
			}
      
      /*　騎手名の取得　*/
			Elements jockeyNameing = document
					.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(7)");
			for (Element element : jockeyNameing) {
				raceResult.setJockeyName(element.text());
			}
      
      /*　タイムの取得　*/
			Elements raceTimeing = document
					.select("tr.HorseList:nth-of-type" + "(" + i + ")" + " " + "td:nth-of-type(8)");
			for (Element element : raceTimeing) {
				raceResult.setRaceTime(element.text());
			}

			raceResultList.add(raceResult);
		}
		return raceResultList;
	}
}
```

