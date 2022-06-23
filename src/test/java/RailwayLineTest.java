import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * @version (20220623)
 *  注意）このテストコードは RailwayLineクラスに３つのメソッドが定義されるまでエラーとなる
 */
public class RailwayLineTest {

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
    public void testGetStations(){
        // action
        RailwayLine y = new RailwayLine();
        try {    
            Method getStn = y.getClass().getDeclaredMethod("getStations");
            Field field = y.getClass().getDeclaredField("stations");
            field.setAccessible(true);
            // assertion
            assertEquals(field.get(y), getStn.invoke(y),"getStations()の戻り値が不正です!");
        } catch  (NoSuchMethodException nsme) {
            fail("RailwayLineクラスにgetStations()が定義されていない、もしくはpublic宣言されていません! ");
        } catch (IllegalAccessException iae) {
            fail("RailwayLineクラスのgetStations()がpublic宣言されていません! ");
        } catch(NoSuchFieldException e) {
            fail("RailwayLineクラスにフィールドstationsが宣言されていません!");
        }catch (java.lang.reflect.InvocationTargetException ite) {
            fail("getStations()内で例外が発生しました"); // 教員対応
        }
    }
    
    @Test
    public void testAdd()
    {
        try{
            // action
            RailwayLine y = new RailwayLine();
            Field stations = y.getClass().getDeclaredField("stations");

            // assertion
            assertEquals(Modifier.PRIVATE, stations.getModifiers(),"stationsのprivate宣言がなくなっています!");

            // action
            stations.setAccessible(true);
            stations.set(y, this.stations);
            y.add(2, "札幌");

            // assertion
            ArrayList<String> expected = new ArrayList<String>(Arrays.asList(
                    "東京",
                    "神田",
                    "札幌",
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
            assertEquals(expected, stations.get(y),"addメソッドの処理が不正です!");

        } catch (Exception e) {
            // なにかしら例外がでたらfailにする
            fail("教員に相談してください!");
        }
    }

    @Test
    public void testSet()
    {
        try{
            // action
            RailwayLine y = new RailwayLine();
            Field stations = y.getClass().getDeclaredField("stations");

            // assertion
            assertEquals(Modifier.PRIVATE, stations.getModifiers(),"stationsのprivate宣言がなくなっています!");

            // action
            stations.setAccessible(true);
            stations.set(y, this.stations);
            y.set(2, "札幌");

            // assertion
            ArrayList<String> expected = new ArrayList<String>(Arrays.asList(
                    "東京",
                    "神田",
                    "札幌",
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
            assertEquals(expected, stations.get(y), "setメソッドの処理が不正です!");

        } catch (Exception e) {
            // なにかしら例外がでたらfailにする
            fail("教員に相談してください!");
        }
    }

    @Test
    public void testRemove()
    {
        try{
            // action
            RailwayLine y = new RailwayLine();
            Field stations = y.getClass().getDeclaredField("stations");

            // assertion
            assertEquals(Modifier.PRIVATE, stations.getModifiers(),"stationsのprivate宣言がなくなっています!");

            // action
            stations.setAccessible(true);
            stations.set(y, this.stations);
            y.remove(2);

            // assertion
            ArrayList<String> expected = new ArrayList<String>(Arrays.asList(
                    "東京",
                    "神田",
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
            assertEquals(expected, stations.get(y), "removeメソッドの処理が不正です!");

        } catch (Exception e) {
            // なにかしら例外がでたらfailにする
            fail("教員に相談してください!");
        }
    }
}
