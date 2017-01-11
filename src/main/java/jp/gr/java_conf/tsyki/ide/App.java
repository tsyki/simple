package jp.gr.java_conf.tsyki.ide;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * IDEのエントリポイント
 * @author TOSHIYUKI.IMAIZUMI
 * @since 2017/01/11
 */
public class App extends Application {

    public static void main( String[] args) {
        launch( args);
    }

    @Override
    public void start( Stage primaryStage) throws Exception {
        primaryStage.show();
    }

}
