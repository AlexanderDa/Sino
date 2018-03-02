/*
 * To change this license header, choose License headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.gui;

import ec.edu.sino.dao.metodos.MAdministrador;
import ec.edu.sino.dao.metodos.MDocente;
import ec.edu.sino.gui.componentes.Notification;
import ec.edu.sino.negocios.entidades.Administrador;
import ec.edu.sino.negocios.entidades.Docente;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author alexander
 */
public final class FPrincipal extends AnchorPane {

    private final ImageView teacherImage = new ImageView(new Image(getClass().getResourceAsStream("imagenes/teacher.png")));
    private final ImageView adminImage = new ImageView(new Image(getClass().getResourceAsStream("imagenes/admin.png")));
    private final Notification notification = new Notification();
    private HBox header;
    private Label lblUsuario;
    private Label lblTitle;
    private StackPane container;
    private VBox teacherSessionPanel;
    private VBox adminSessionPanel;
    private Docente docente;
    private Administrador admin;
    private TextField tfProfesorUser;
    private PasswordField pfProfesorPassword;
    private TextField tfAdminUser;
    private PasswordField pfAdminPassword;
    private HBox userType;

    public FPrincipal() {
        initContainer();
        initheader();
        notification.lowerRightCorner();
        getChildren().add(notification);
    }

    private void initheader() {
        header = new HBox();
        header.getStyleClass().add("header");
        header.setPrefSize(USE_PREF_SIZE, 45);

        AnchorPane.setTopAnchor(header, 0.0);
        AnchorPane.setLeftAnchor(header, 0.0);
        AnchorPane.setRightAnchor(header, 0.0);

        lblTitle = new Label("Sino");
        lblTitle.setPrefSize(200, 45);
        lblTitle.setAlignment(Pos.CENTER);
        lblTitle.getStyleClass().add("title");

        HBox rightContainer = new HBox(10);
        rightContainer.setPrefSize(USE_PREF_SIZE, 45);
        rightContainer.setAlignment(Pos.CENTER_RIGHT);
        rightContainer.setPadding(new Insets(0, 25, 0, 0));
        HBox.setHgrow(rightContainer, Priority.ALWAYS);

        lblUsuario = new Label();
        lblUsuario.getStyleClass().add("user");
        Button settings = new Button("Login");
        rightContainer.getChildren().addAll(lblUsuario, settings);

        header.getChildren().addAll(lblTitle, rightContainer);
        getChildren().add(header);

    }

    private void initContainer() {
        container = new StackPane();
        container.getStyleClass().add("login-container");
        AnchorPane.setLeftAnchor(container, 0.0);
        AnchorPane.setTopAnchor(container, 45.0);
        AnchorPane.setRightAnchor(container, 0.0);
        AnchorPane.setBottomAnchor(container, 0.0);
        initUserTipe();
        getChildren().add(container);
    }

    private void initDropDownMenu() {

    }

    private void initUserTipe() {
        userType = new HBox(50);
        userType.setPadding(new Insets(25));
        userType.setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
        userType.getStyleClass().add("user-type");
        
        Button btnAdminTipe = new Button("Administrador", adminImage);
        btnAdminTipe.setContentDisplay(ContentDisplay.TOP);
        btnAdminTipe.setOnAction(ShowLoginAdminActionEvent());
        
        Button btnProfesorType = new Button("Docente", teacherImage);
        btnProfesorType.setOnAction(ShowLoginTeacherActionEvent());
        btnProfesorType.setContentDisplay(ContentDisplay.TOP);
        
        userType.getChildren().addAll(btnAdminTipe, btnProfesorType);
        container.getChildren().add(userType);
    }

    private void initTeacherSessionPanel() {
        teacherSessionPanel = new VBox(15);
        teacherSessionPanel.setMaxSize(350, USE_PREF_SIZE);
        teacherSessionPanel.setPadding(new Insets(25, 25, 25, 25));
        teacherSessionPanel.getStyleClass().add("session-panel");
        teacherSessionPanel.setAlignment(Pos.CENTER);

        Label lblUser = new Label("Usuario");
        lblUser.setMinWidth(350);
        tfProfesorUser = new TextField("alexanderda");
        Label lblPass = new Label("Contraseña");
        lblPass.setMinWidth(350);
        pfProfesorPassword = new PasswordField();
        pfProfesorPassword.setText("alip4$");
        Button btnLogin = new Button("Entrar");
        btnLogin.setDefaultButton(true);
        btnLogin.setCursor(Cursor.HAND);
        btnLogin.setPrefWidth(350);
        btnLogin.setOnAction(loginTeacherActionEvente());

        teacherSessionPanel.getChildren().addAll(teacherImage, lblUser, tfProfesorUser, lblPass, pfProfesorPassword, btnLogin);

        container.getChildren().add(teacherSessionPanel);

    }

    private void initAdminSessionPanel() {
        adminSessionPanel = new VBox(15);
        adminSessionPanel.setMaxSize(350, USE_PREF_SIZE);
        adminSessionPanel.setPadding(new Insets(25, 25, 25, 25));
        adminSessionPanel.getStyleClass().add("session-panel");
        adminSessionPanel.setAlignment(Pos.CENTER);

        Label lblUser = new Label("Usuario");
        lblUser.setMinWidth(350);
        tfAdminUser = new TextField("admin");
        Label lblPass = new Label("Contraseña");
        lblPass.setMinWidth(350);
        pfAdminPassword = new PasswordField();
        pfAdminPassword.setText("admin");
        Button btnLogin = new Button("Entrar");
        btnLogin.setDefaultButton(true);
        btnLogin.setCursor(Cursor.HAND);
        btnLogin.setPrefWidth(350);
        btnLogin.setOnAction(loginAdminActionEvent());

        adminSessionPanel.getChildren().addAll(adminImage, lblUser, tfAdminUser, lblPass, pfAdminPassword, btnLogin);

        container.getChildren().add(adminSessionPanel);
    }

    private EventHandler ShowLoginTeacherActionEvent() {
        return (t) -> {
            getChildren().remove(userType);
            initTeacherSessionPanel();
        };
    }

    private EventHandler ShowLoginAdminActionEvent() {
        return (t) -> {
            getChildren().remove(userType);
            initAdminSessionPanel();
        };
    }

    private EventHandler loginTeacherActionEvente() {
        EventHandler handler = (t) -> {
            MDocente md = new MDocente();
            md.loginProfesor();
            try {
                docente = md.obtener(tfProfesorUser.getText(), pfProfesorPassword.getText());
                if (docente != null) {
                    lblUsuario.setText(docente.toString());
                    lblTitle.getStyleClass().remove(1);
                    lblTitle.getStyleClass().add("title-active");
                    notification.successful("Inicio de sesion exitoso.");
                    getChildren().remove(container);
                    getChildren().add(new FMainDocente(docente));

                } else {
                    notification.failed("Usuario o contraseña incorrectos.");
                }
            } catch (Exception e) {
                notification.failed("Se ha producido un error interno.");
            }
        };
        return handler;
    }

    private EventHandler loginAdminActionEvent() {
        EventHandler handler = (t) -> {
            MAdministrador ma = new MAdministrador();
            try {
                admin = ma.obtener(tfAdminUser.getText(), pfAdminPassword.getText());
                if (admin != null) {
                    notification.successful("Inicio de sesion exitoso.");
                    lblUsuario.setText(admin.toString());
                    lblTitle.getStyleClass().remove(1);
                    lblTitle.getStyleClass().add("title-active");
                    getChildren().remove(container);
                    getChildren().add(new FMainAdmin(admin));
                } else {
                    notification.failed("Usuario o contraseña incorrectos.");
                }
            } catch (Exception e) {
                notification.failed("Se ha producido un error interno." + e.getMessage());
            }
        };
        return handler;
    }
}
