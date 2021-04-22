package oop.focus.diary.view;

import javafx.collections.SetChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import oop.focus.diary.controller.CreatePageController;
import oop.focus.diary.controller.DiaryPagesController;
import oop.focus.diary.controller.FXMLPaths;
import oop.focus.diary.controller.RemovePageController;
import oop.focus.diary.model.DiaryImpl;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static oop.focus.diary.view.OpenWindow.openWindow;

/**
 * DiaryView represents the under-section of diary inside the section of diary. The representation of single pages
 * is managed by the appropriate class {@link SingleDiaryPage}.
 */
public class DiaryView implements UpdatableView<String>, Initializable {
    private static final Rectangle2D SCREEN_BOUNDS = Screen.getPrimary().getBounds();
    private static final double PAGES_HEIGHT = 0.2;
    private static final double DIARY_LABEL_HEIGHT = 0.1;
    private static final double H_BOX_SPACING = 0.04;
    private static final double BUTTON_WIDTH = 0.3;
    private static final double BUTTON_HEIGHT = 0.08;
    private static final double V_BOX_WIDTH = 0.5;
    private static final double CONTAINER_DIARY_HEIGHT = 0.7;
    @FXML
    private VBox vBox;

    @FXML
    private Label diaryLabel;

    @FXML
    private ScrollPane containerDiary;

    @FXML
    private Button addButton;

    @FXML
    private Button removeButton;
    @FXML
    private HBox hBox;

    private final DiaryPagesController controller;
    private Accordion pages;
    /**
     * Instantiates a new diary view and opens the relative FXML file.
     * @param controller    diary pages controller
     */
    public DiaryView(final DiaryPagesController controller) {
        this.controller = controller;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.DIARY_SCHEME.getPath()));
        loader.setController(this);
        try {
            loader.load();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.containerDiary.setFitToWidth(true);
        this.containerDiary.vbarPolicyProperty().set(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        this.pages = new Accordion();
        this.insertPages();
        this.controller.getObservableSet().addListener((SetChangeListener<DiaryImpl>) change -> {
            if (change.wasAdded()) {
                this.updateInput(change.getElementAdded().getName());
            } else if (change.wasRemoved()) {
                this.pages.getPanes().clear();
                this.insertPages();
            }
        });
        this.vBox = (VBox) new ContainerFactoryImpl().mergeVertically(List.of(this.diaryLabel, this.containerDiary,
                this.hBox)).getRoot();
        this.diaryLabel.setText("Diario");
        this.addButton.setText("Aggiungi");
        this.addButton.setOnMouseClicked(event -> openWindow((Parent) new CreatePageController(this.controller)
                .getView().getRoot()));
        this.removeButton.setText("Rimuovi");
        this.removeButton.setOnMouseClicked(event -> openWindow((Parent) new RemovePageController(this.controller).
                getView().getRoot()));
        this.hBox.spacingProperty().bind(this.vBox.widthProperty().multiply(H_BOX_SPACING));
        List.of(this.addButton, this.removeButton).forEach(s -> s.prefHeightProperty().bind(this.vBox.heightProperty().
                multiply(BUTTON_HEIGHT)));
        List.of(this.addButton, this.removeButton).forEach(s -> s.prefWidthProperty().bind(this.vBox.widthProperty().
                multiply(BUTTON_WIDTH)));
        this.containerDiary.prefHeightProperty().bind(this.vBox.heightProperty().multiply(CONTAINER_DIARY_HEIGHT));
        this.diaryLabel.prefHeightProperty().bind(this.containerDiary.heightProperty().multiply(DIARY_LABEL_HEIGHT));
        this.hBox.setAlignment(Pos.CENTER);
        this.vBox.prefWidthProperty().set(SCREEN_BOUNDS.getWidth() * V_BOX_WIDTH);
        this.diaryLabel.prefWidthProperty().bind(this.containerDiary.widthProperty());
        this.diaryLabel.setAlignment(Pos.CENTER);
        this.containerDiary.setContent(this.pages);
        this.pages.prefWidthProperty().bind(this.containerDiary.widthProperty());
        this.pages.prefHeightProperty().bind(this.containerDiary.heightProperty().multiply(PAGES_HEIGHT));
    }


    /**
     * The method can be used to add all pages' saved to the appropriate container.
     */
    private void insertPages() {
        this.controller.getFileName().forEach(this::updateInput);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Node getRoot() {
        return this.vBox;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateInput(final String input) {
        this.pages.getPanes().add(new SingleDiaryPageImpl(this.controller).createSinglePage(input));
    }
}
