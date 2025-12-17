import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version (20251217)
 *  このテストコードは RailwayLineクラスに３つのメソッドが未定義でもエラーとならないように変更した
 * @version (20220623)
 *  注意）このテストコードは RailwayLineクラスに３つのメソッドが定義されるまでエラーとなる
 */
public class RailwayLineTest {
    private static final String TARGET_CLASS_NAME = "RailwayLine";
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

    /**
     * テスト対象クラスのインスタンスを取得し、同時に存在チェックを行う
     * @return RailwayLineクラスのインスタンス（Object型として返す）
     * @throws Exception メソッド内で処理できないリフレクション例外
     */
    private Object createTargetInstance(Class<?> targetClass) throws Exception {
        Constructor<?> constructor = null;
        try {
            // 1. デフォルトコンストラクタの取得
            constructor = targetClass.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            // コンストラクタが存在しない場合にテスト失敗
            Assertions.fail(TARGET_CLASS_NAME + " にデフォルトコンストラクタが定義されていません。");
        }

        // 2. インスタンスの生成
        try {
            return constructor.newInstance();
        } catch (Exception e) {
            // インスタンス生成中に例外が発生した場合（コンストラクタ内で例外発生など）
            Assertions.fail(TARGET_CLASS_NAME + " のインスタンス生成中に例外が発生しました。", e);
            return null; // unreachable
        }
    }

    private Method getMethod(Class<?> targetClass, String methodName, Class<?>[] parameterTypes) {
        return assertDoesNotThrow(() -> targetClass.getDeclaredMethod(methodName, parameterTypes), 
            () -> TARGET_CLASS_NAME + " にメソッド " + methodName + "(" + formatParams(parameterTypes) + ") が定義されていません。");
    }

    // ★【変更点：パラメータ整形ヘルパーメソッドを追加】
    private String formatParams(Class<?>[] parameterTypes) {
        if (parameterTypes.length == 0) return "";
        return Arrays.stream(parameterTypes).map(Class::getSimpleName).reduce((a, b) -> a + ", " + b).orElse("");
    }

    @Test
    public void testAdd()
    {
        try{
            // action
            // 【変更点：クラスの動的ロード】
            Class<?> targetClass = Class.forName(TARGET_CLASS_NAME);
            // 【変更点：インスタンス生成をヘルパーメソッドに置き換え】
            Object y = createTargetInstance(targetClass); // RailwayLine y = new RailwayLine(); に相当
            // 【変更点：リフレクションでフィールドを取得するため、targetClassを使用】
            Field stationsField = targetClass.getDeclaredField("stations");

            // assertion
            assertEquals(Modifier.PRIVATE, stationsField.getModifiers(),"stationsのprivate宣言がなくなっています!");

            // action
            stationsField.setAccessible(true);
            stationsField.set(y, this.stations);

            // 【変更点：メソッドの存在チェックとオブジェクト取得】
            final Method addMethod = getMethod(targetClass, "add", new Class<?>[]{int.class, String.class});

            // 【変更点：リフレクションによる動的呼び出し (あいまいさエラー回避のためブロックを使用)】
            assertDoesNotThrow(() -> { 
                        addMethod.invoke(y, 2, "札幌"); // 戻り値を利用せず、実行のみ
                }, () -> "addメソッドの呼び出し中に例外が発生しました。");
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

            @SuppressWarnings("unchecked")
            List<String> actual = (List<String>) stationsField.get(y);
            assertEquals(expected, actual, "addメソッドの処理が不正です!");
        } catch (ClassNotFoundException e) {
            fail("テスト対象クラス " + TARGET_CLASS_NAME + " が見つかりません。作成したクラス（ファイル）の名前を確認してください。");
        } catch (NoSuchFieldException e) {
            fail("フィールド stations が見つかりません。");
        } catch (Exception e) {
            e.printStackTrace();
            fail("テスト実行中に予期せぬ例外が発生しました: " + e.getMessage());
        }
    }

    @Test
    public void testSet()
    {
        try{
            // action
            // 【変更点：クラスの動的ロード】
            Class<?> targetClass = Class.forName(TARGET_CLASS_NAME);
            // 【変更点：インスタンス生成をヘルパーメソッドに置き換え】
            Object y = createTargetInstance(targetClass);
            // 【変更点：リフレクションでフィールドを取得するため、targetClassを使用】
            Field stationsField = targetClass.getDeclaredField("stations");

            // assertion
            assertEquals(Modifier.PRIVATE, stationsField.getModifiers(),"stationsのprivate宣言がなくなっています!");

            // action
            stationsField.setAccessible(true);
            stationsField.set(y, this.stations);

            // 【変更点：メソッドの存在チェックとオブジェクト取得】
            final Method setMethod = getMethod(targetClass, "set", new Class<?>[]{int.class, String.class});

            // 【変更点：リフレクションによる動的呼び出し (あいまいさエラー回避のためブロックを使用)】
            assertDoesNotThrow(() -> {
                        setMethod.invoke(y, 2, "札幌"); // 戻り値を利用せず、実行のみ
                }, () -> "setメソッドの呼び出し中に例外が発生しました。");

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

            @SuppressWarnings("unchecked")
            List<String> actual = (List<String>) stationsField.get(y);
            assertEquals(expected, actual, "setメソッドの処理が不正です!");
        } catch (ClassNotFoundException e) {
            fail("テスト対象クラス " + TARGET_CLASS_NAME + " が見つかりません。作成したクラス（ファイル）の名前を確認してください。");
        } catch (NoSuchFieldException e) {
            fail("フィールド stations が見つかりません。");
        } catch (Exception e) {
            e.printStackTrace();
            fail("テスト実行中に予期せぬ例外が発生しました: " + e.getMessage());
        }
    }

    @Test
    public void testRemove()
    {
        try{
            // action
            // 【変更点：クラスの動的ロード】
            Class<?> targetClass = Class.forName(TARGET_CLASS_NAME);
            // 【変更点：インスタンス生成をヘルパーメソッドに置き換え】
            Object y = createTargetInstance(targetClass);
            // 【変更点：リフレクションでフィールドを取得するため、targetClassを使用】
            Field stationsField = targetClass.getDeclaredField("stations");

            // assertion
            assertEquals(Modifier.PRIVATE, stationsField.getModifiers(),"stationsのprivate宣言がなくなっています!");

            // action
            stationsField.setAccessible(true);
            stationsField.set(y, this.stations);
            
            // 【変更点：メソッドの存在チェックとオブジェクト取得】
            final Method removeMethod = getMethod(targetClass, "remove", new Class<?>[]{int.class});
            
            // 【変更点：リフレクションによる動的呼び出し (あいまいさエラー回避のためブロックを使用)】
            assertDoesNotThrow(() -> {
                removeMethod.invoke(y, 2); // 戻り値を利用せず、実行のみ
            }, () -> "removeメソッドの呼び出し中に例外が発生しました。");

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

            @SuppressWarnings("unchecked")
            List<String> actual = (List<String>) stationsField.get(y);
            assertEquals(expected, actual, "removeメソッドの処理が不正です!");
        } catch (ClassNotFoundException e) {
            fail("テスト対象クラス " + TARGET_CLASS_NAME + " が見つかりません。作成したクラス（ファイル）の名前を確認してください。");
        } catch (NoSuchFieldException e) {
            fail("フィールド stations が見つかりません。");
        } catch (Exception e) {
            e.printStackTrace();
            fail("テスト実行中に予期せぬ例外が発生しました: " + e.getMessage());
        }
    }
}
