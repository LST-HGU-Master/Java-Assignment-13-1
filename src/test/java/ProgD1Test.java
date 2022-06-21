import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * @version (20220621)
 */
public class ProgD1Test {

    ArrayList<String> stations = new ArrayList<String>(Arrays.asList(
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
            "高輪ゲートウェイ",
            "田町",
            "浜松町",
            "新橋",
            "有楽町"
    ));

    @Test
    public void testMain()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        StandardInputStream in = new StandardInputStream();
        System.setIn(in);

        // action
        ProgD1.main(null);
        // undo the binding in System
        System.setOut(originalOut);
        
        // assertion
        String[] prints = bos.toString().split("\r\n|\n", -1);
        assertEquals("[東京, 神田, 秋葉原, 御徒町, 上野, 鶯谷, 日暮里, 西日暮里, 田端, 駒込, 巣鴨, 大塚, 池袋, 目白, 高田馬場, 新大久保, 新宿, 代々木, 原宿, 渋谷, 恵比寿, 目黒, 五反田, 大崎, 品川, 高輪ゲートウェイ, 田町, 浜松町, 新橋, 有楽町]", prints[0]);
    }
}
