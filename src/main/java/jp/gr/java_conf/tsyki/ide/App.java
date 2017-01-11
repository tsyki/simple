package jp.gr.java_conf.tsyki.ide;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
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
        TextArea codeEditTextArea = new TextArea();
        TextArea stdoutTextArea = new TextArea();
        stdoutTextArea.setEditable( false);
        Button runButton = new Button( "Run");
        BorderPane pane = new BorderPane();
        pane.setTop( codeEditTextArea);
        pane.setCenter( stdoutTextArea);
        pane.setBottom( runButton);

        runButton.setOnAction( new EventHandler<ActionEvent>() {

            public void handle( ActionEvent event) {
                stdoutTextArea.setText( "start compile.");
                // TODO コンパイル
                // TODO コンパイル結果の表示
            }
        });

        Scene scene = new Scene( pane, 640, 480);
        primaryStage.setScene( scene);

        primaryStage.show();
    }

}
