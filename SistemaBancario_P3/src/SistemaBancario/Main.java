package SistemaBancario;
//---------------------------------
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

//---------------------------------
public class Main extends Application {
    private static Stage stage;

    //cache de cada tela
    private static Scene sideBar_Scene;
    private static Scene login_Scene;
    private static Scene mensageBox_Scene;
    private static Scene cadastroFuncionarioForm_Scene;
    private static Scene alteraFuncionarioForm_Scene;
    private static Scene cadastroContaForm_Scene;
    private static Scene alteraContaForm_Scene;
    private static Scene extrato_Scene;
    private static Scene cadastroTipoContaForm_Scene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        //para cada janela vai ter um cache:

        //sideBar
        Parent fxmlSideBar = FXMLLoader.load(getClass().getResource("../View/SideBar.fxml"));
        sideBar_Scene = new Scene(fxmlSideBar,1200,800);
        //Login
        Parent fxmlLogin = FXMLLoader.load(getClass().getResource("../View/Login.fxml"));
        login_Scene = new Scene(fxmlLogin,700,400);
        //mensageBox
        Parent fxmlMensageBox = FXMLLoader.load(getClass().getResource("../View/mensageBox.fxml"));
        mensageBox_Scene = new Scene(fxmlMensageBox,600,220);
        //cadastroForm
        Parent fxmlcadastroFuncionarioForm = FXMLLoader.load(getClass().getResource("../View/CadastroFuncionario_Form_UI.fxml"));
        cadastroFuncionarioForm_Scene = new Scene(fxmlcadastroFuncionarioForm);
        //alteraFuncionarioForm
        Parent fxmlAlteraFuncionarioForm = FXMLLoader.load(getClass().getResource("../View/AlterarFuncionario_Form_UI.fxml"));
        alteraFuncionarioForm_Scene = new Scene(fxmlAlteraFuncionarioForm);
        //cadastroContaForm
        Parent fxmlCadastroContaForm = FXMLLoader.load(getClass().getResource("../View/CadastroConta_Form_UI.fxml"));
        cadastroContaForm_Scene = new Scene(fxmlCadastroContaForm);
        //alteraFuncionarioForm
        Parent fxmlAlteraContaForm = FXMLLoader.load(getClass().getResource("../View/AlterarConta_Form_UI.fxml"));
        alteraContaForm_Scene = new Scene(fxmlAlteraContaForm);
        //Extrato
        Parent fxmlExtrato =  FXMLLoader.load(getClass().getResource("../View/extrato.fxml"));
        extrato_Scene = new Scene(fxmlExtrato);
        //Cadastro tipo conta
        Parent fxmlcadastroTipoConta =  FXMLLoader.load(getClass().getResource("../View/CadastroContaTipo.fxml"));
        cadastroTipoContaForm_Scene = new Scene(fxmlcadastroTipoConta);

        //TIRANDO A BARRA SUPERIOR DO WINDOWS
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(login_Scene);
        primaryStage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);

    }

    public static void main(String[] args) {
        launch(args);
    }
    public static void changeScreen(String scr, Object userData){
        Stage auxStage;
        switch (scr){
            case "login":
                stage.setScene(login_Scene);
                centralizaScrean(stage);
                break;
            case "sidebar":
                stage.setScene(sideBar_Scene);
                //centralizaScrean(stage);
                stage.setFullScreen(true);
                notifyAllListeners("sidebar",userData); // aqui e disparado o evento para todas as telas dentro do listeners
                break;
            case "mensageBox":
                auxStage = new Stage();
                auxStage.initStyle(StageStyle.UNDECORATED);
                auxStage.setScene(mensageBox_Scene);
                auxStage.show();
                centralizaScrean(auxStage);
                notifyAllListeners("mensageBox",userData);
                break;
            case "cadastroFuncionarioForm":
                auxStage = new Stage();
                auxStage.initStyle(StageStyle.UNDECORATED);
                auxStage.setScene(cadastroFuncionarioForm_Scene);
                auxStage.show();
                centralizaScrean(auxStage);
                notifyAllListeners("cadastroFuncionarioForm",userData);
                break;
            case "alteraFuncionarioForm":
                auxStage = new Stage();
                auxStage.initStyle(StageStyle.UNDECORATED);
                auxStage.setScene(alteraFuncionarioForm_Scene);
                auxStage.show();
                centralizaScrean(auxStage);
                notifyAllListeners("alteraFuncionarioForm",userData);
                break;
            case "cadastroContaForm":
                auxStage = new Stage();
                auxStage.initStyle(StageStyle.UNDECORATED);
                auxStage.setScene(cadastroContaForm_Scene);
                auxStage.show();
                centralizaScrean(auxStage);
                notifyAllListeners("cadastroContaForm",userData);
                break;
            case "alteraContaForm":
                auxStage = new Stage();
                auxStage.initStyle(StageStyle.UNDECORATED);
                auxStage.setScene(alteraContaForm_Scene);
                auxStage.show();
                centralizaScrean(auxStage);
                notifyAllListeners("alteraContaForm",userData);
                break;
            case "extrato":
                auxStage = new Stage();
                auxStage.initStyle(StageStyle.UNDECORATED);
                auxStage.setScene(extrato_Scene);
                auxStage.show();
                centralizaScrean(auxStage);
                notifyAllListeners("extrato",userData);
                break;
            case "cadastroTipoConta":
                auxStage = new Stage();
                auxStage.initStyle(StageStyle.UNDECORATED);
                auxStage.setScene(cadastroTipoContaForm_Scene);
                auxStage.show();
                centralizaScrean(auxStage);
                notifyAllListeners("cadastroTipoConta",userData);
        }
    }
    //Sobrecarga de funçao, para eliminar o segundo parametro quando n for necessario
    public static void changeScreen(String scr){
        changeScreen(scr,null);
    }
    public static void centralizaScrean (Stage stage){
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }
    //-----------------
    private  static  ArrayList<OnChangeScreen> listeners = new ArrayList<>();

    //evento
    public interface OnChangeScreen {
        void onScreenChanged(String newScreen, Object userData);
    }

    public static void addOnChangeScreenListeners(OnChangeScreen newListener){
        listeners.add(newListener);
    }
    // manda pra todas as telas que tiver cadastrado no Listeners
    private static void notifyAllListeners(String newScreen, Object userData){
        for (OnChangeScreen l : listeners){
            l.onScreenChanged(newScreen,userData);
        }
    }
    /*
    * oq vai ter que fazer em todos os controllers:
    * Main.addOnChangeScreenListeners(new OnChangeScreen){
    *   @override
    *   public void onScreenChanged(String newScreen, Object... userData){
    *       //oq eu quiser pegar de informação ex: atributo X = userdata[0];
    *  }
    * }
    * */

    /*
    * a informação vai ser passada na função Main.changeScreen e tambem vai ocorrer a mudança de janela
    * */

}
