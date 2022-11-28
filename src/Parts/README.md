## Parts

データの取得からDB格納までの流れの中の補助役を担うクラス

### CreateRaceId

レースIDの作成を行うクラス。中央競馬のレース全てにレースIDが振られており、 `年　+ 場所 + 回数 + レース`などの情報を組み合わせて作成されている

### CreateRoopCounter

開催の年によって、ループ処理を何回行うか判断するクラス。

### CreateRoopCounter2

前述したクラスでは、開催年数をコード上で修正する必要があるが、このクラスでは全ての年のループ数をあらかじめ定義しておき、引数で指定された年のループを戻すようにしようとしたクラス。

### DBManager

DBへの接続や切断を行うクラス。

### ReplacePlaceNumberPosition

数値で受け取った場所IDから、場所名へ変換するクラス。