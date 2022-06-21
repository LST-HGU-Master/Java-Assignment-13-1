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
