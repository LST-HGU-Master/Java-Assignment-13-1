# 課題 13-1: コレクション

### 課題の説明
次に示すRailwayLineクラスは初期値が山手線駅となっているArrayListをもつ。
ただし、駅名と駅順に誤りがあるため、これをmainメソッドから修正したい。
この修正をおこなうために以下のメソッドを追加しなさい。

| メソッド名  | 引数のリスト            | 戻り値の型 | 内容          |
|--------|-------------------|-------|-------------|
| add    | int n, String str | void  | n番目にstrを挿入  |
| set    | int n, String str | void  | n番目をstrに上書き |
| remove | int n | void  | n番目の要素を削除   |

(補足説明) RailwayLineクラスのstationsはprivate宣言されており、mainメソッドから直接アクセスすることができない。そこで、上記のメソッドに加えて、stationsのgetterメソッド `getStations()` を追加し、mainメソッドからは `yamanote.getStations().add(・・・)` のような形でアクセスするようにしよう。

また、以下の３点を修正するProgD1.mainメソッドを作成し、下に示す実行結果と同じ駅名・駅順になることを確認すること。

- 「品川」と「田町」の間に「高輪ゲートウェイ」駅を追加
- 「高田馬場」が2つあるので1つ削除する
- 「ゆうらくちょう」を「有楽町」に変更する

### RailwayLine.java
```java
import java.util.ArrayList;
import java.util.Arrays;

public class RailwayLine {
    private ArrayList<String> stations = new ArrayList<String>(Arrays.asList(
            "東京",
            "神田",
            "秋葉原",
            "御徒町",
            "上野",
            "鶯谷",
            "日暮里",
            "西日暮里",
            "田端",
            "駒込",
            "巣鴨",
            "大塚",
            "池袋",
            "目白",
            "高田馬場",
            "高田馬場",
            "新大久保",
            "新宿",
            "代々木",
            "原宿",
            "渋谷",
            "恵比寿",
            "目黒",
            "五反田",
            "大崎",
            "品川",
            "田町",
            "浜松町",
            "新橋",
            "ゆうらくちょう"
    ));
    void printStations () {
        int n = this.stations.size();
        int count=1;
        System.out.print("[");
        for( String stn: stations) {
            System.out.print(stn);
            if (count++ < n)  System.out.print(",");
        }
        System.out.print("]");
    }
}
```
### ProgD1.java
```java
public class ProgD1 {

    public static void main(String[] args) {
        // ここにプログラムを追加
        yamanote.printStations();
    }

}
```

### ProgD1.main()の実行結果
```
[東京, 神田, 秋葉原, 御徒町, 上野, 鶯谷, 日暮里, 西日暮里, 田端, 駒込, 巣鴨, 大塚, 池袋, 目白, 高田馬場, 新大久保, 新宿, 代々木, 原宿, 渋谷, 恵比寿, 目黒, 五反田, 大崎, 品川, 高輪ゲートウェイ, 田町, 浜松町, 新橋, 有楽町]
```
