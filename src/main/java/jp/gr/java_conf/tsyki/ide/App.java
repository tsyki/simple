package jp.gr.java_conf.tsyki.ide;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

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
        codeEditTextArea.setText( getInitialCode());

        TextArea stdoutTextArea = new TextArea();
        stdoutTextArea.setEditable( false);

        Button runButton = new Button( "Run");

        BorderPane pane = new BorderPane();
        pane.setTop( codeEditTextArea);
        pane.setCenter( stdoutTextArea);
        pane.setBottom( runButton);

        runButton.setOnAction( new EventHandler<ActionEvent>() {

            public void handle( ActionEvent event) {
                runButton.setDisable( true);
                stdoutTextArea.setText( "start compile.\n");
                File sourceCodeFile;
                // 一旦ファイルに保存
                File tempDir = new File( "temp");
                if ( !tempDir.exists()) {
                    tempDir.mkdir();
                }
                try {
                    sourceCodeFile = saveSourceCode( codeEditTextArea);
                }
                catch ( IOException e) {
                    e.printStackTrace();
                    stdoutTextArea.appendText( "error occuered.\n" + e.getMessage() + "\n");
                    runButton.setDisable( false);
                    return;
                }

                File destDir = new File( "temp/classes");
                if ( !destDir.exists()) {
                    destDir.mkdir();
                }

                // コンパイル実行
                String[] args = {"-d", destDir.getAbsolutePath(), sourceCodeFile.getAbsolutePath()};

                JavaCompiler c = ToolProvider.getSystemJavaCompiler();
                int r = c.run( null, null, null, args);
                if ( r != 0) {
                    stdoutTextArea.appendText( "compile failed.\n");
                    // TODO コンパイル結果の表示
                    runButton.setDisable( false);
                    return;
                }
                stdoutTextArea.appendText( "compile success.\n");
                runButton.setDisable( false);
            }

        });

        Scene scene = new Scene( pane, 640, 480);
        primaryStage.setScene( scene);

        primaryStage.show();
    }

    private String getInitialCode() {
        StringBuilder sb = new StringBuilder();
        sb.append( "public class Main{\n");
        sb.append( "\tpublic static void main(String[] args){\n");
        sb.append( "\t\t\n");
        sb.append( "\t}\n");
        sb.append( "}\n");
        return sb.toString();
    }

    private File saveSourceCode( TextArea codeEditTextArea) throws FileNotFoundException, IOException {
        String sourceCode = codeEditTextArea.getText();
        File sourceCodeFile = new File( "temp/Main.java");
        try (BufferedWriter writer = new BufferedWriter( new OutputStreamWriter( new FileOutputStream( sourceCodeFile)))) {
            writer.write( sourceCode);
        }
        return sourceCodeFile;
    }

}
