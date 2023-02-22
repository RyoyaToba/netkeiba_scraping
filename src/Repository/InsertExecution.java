package Repository;

import java.lang.reflect.Method;

public class InsertExecution {

    public static void main(String[] args) {
        //DBへのINSERTを共通処理にする
        String className = args[0];
        String methodName = args[1];

        try {
            // 実行するクラスの取得
            Class<?> classObject = Class.forName(className);
            // インスタンスの生成
            Object object = classObject.newInstance();
            // メソッドの取得
            Method method = classObject.getMethod(methodName);
            // メソッドの実行
            method.invoke(object);
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
