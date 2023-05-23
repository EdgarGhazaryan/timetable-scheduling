package timetable.scheduling.gui;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.skin.TableColumnHeader;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import timetable.scheduling.algorithm.Data;
import timetable.scheduling.algorithm.GeneticAlgorithm;
import timetable.scheduling.algorithm.Schedule;
import timetable.scheduling.model.*;
import timetable.scheduling.model.Class;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Output extends Application {

    private final TableView<Class> table = new TableView<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Timetable");

        List<Class> classes = getClasses();

        table.setItems(FXCollections.observableList(classes));

        final TableColumn<Class, String> groupCol = new TableColumn<>("Խումբ");
        groupCol.setCellValueFactory((TableColumn.CellDataFeatures<Class, String> p) -> new SimpleStringProperty(p.getValue().getGroup().getName()));
        groupCol.setCellFactory(col -> setColumnSize());

        final TableColumn<Class, String> meetingTimeCol = new TableColumn<>("Ժամ");
        meetingTimeCol.setCellValueFactory((TableColumn.CellDataFeatures<Class, String> p) -> new SimpleStringProperty(p.getValue().getMeetingTime().getTime()));
        meetingTimeCol.setCellFactory(col -> setColumnSize());

        final TableColumn<Class, String> courseCol = new TableColumn<>("Դաս");
        courseCol.setCellValueFactory((TableColumn.CellDataFeatures<Class, String> p) -> new SimpleStringProperty(p.getValue().getCourse().getName()));
        courseCol.setCellFactory(col -> setColumnSize());

        final TableColumn<Class, String> instructorCol = new TableColumn<>("Դասախոս");
        instructorCol.setCellValueFactory((TableColumn.CellDataFeatures<Class, String> p) -> new SimpleStringProperty(p.getValue().getInstructor().getName()));
        instructorCol.setCellFactory(col -> setColumnSize());

        final TableColumn<Class, String> roomCol = new TableColumn<>("Դասասենյակ");
        roomCol.setCellValueFactory((TableColumn.CellDataFeatures<Class, String> p) -> new SimpleStringProperty(p.getValue().getRoom().getNumber()));
        roomCol.setCellFactory(col -> setColumnSize());

        table.getColumns().addAll(groupCol, meetingTimeCol, courseCol, instructorCol, roomCol);

        VBox vbox = new VBox();
        VBox.setVgrow(table, Priority.ALWAYS);

        vbox.getChildren().addAll(table);
        Scene scene = new Scene(vbox);
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private <T> TableCell<T, String> setColumnSize() {
        return new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item);
                    setFont(Font.font(20));
                }
            }
        };
    }

    private List<Class> getClasses() {
        Schedule schedule = new GeneticAlgorithm(new Data()).run();
        List<Class> classes = new ArrayList<>();
        schedule.getClasses().forEach(c -> {
            if (c.getGroup() != null) {
                classes.add(c);
            } else {
                c.getGroupableWith().forEach(group -> {
                    Class clone = c.clone();
                    clone.setGroup(group);
                    clone.setGroupableWith(Collections.emptyList());
                    classes.add(clone);
                });
            }
        });

        classes.sort(Comparator.comparing((Class c) -> c.getGroup().getName()).thenComparing(Class::getMeetingTime));

        List<Integer> positions = new ArrayList<>();
        String lastGroupName = classes.get(0).getGroup().getName();

        for (int i = 1; i < classes.size(); i++) {
            String currentGroupName = classes.get(i).getGroup().getName();
            if (currentGroupName.isBlank()) {
                continue;
            }

            if (!currentGroupName.equals(lastGroupName)) {
                Group group = new Group("", 0, 0);
                classes.add(i, new Class(
                        0,
                        new Course("", "", Collections.emptyList(), group, 0),
                        new Instructor("", ""),
                        new MeetingTime("", ""),
                        new Room("", 0),
                        group,
                        Collections.emptyList()
                ));
            }
            lastGroupName = currentGroupName;
        }

        positions.forEach(p -> {
            Group group = new Group("", 0, 0);
            classes.add(p, new Class(
                    0,
                    new Course("", "", Collections.emptyList(), group, 0),
                    new Instructor("", ""),
                    new MeetingTime("", ""),
                    new Room("", 0),
                    group,
                    Collections.emptyList()
            ));
        });

        return classes;
    }
}

