/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.towerdefencegamesinc.towerdefence.java.ui;

import fi.towerdefencegamesinc.towerdefence.java.Game;
import fi.towerdefencegamesinc.towerdefence.java.logic.Difficulty;
import fi.towerdefencegamesinc.towerdefence.java.logic.GameMap;
import fi.towerdefencegamesinc.towerdefence.java.logic.Tile;
import fi.towerdefencegamesinc.towerdefence.java.logic.attacker.Attacker;
import fi.towerdefencegamesinc.towerdefence.java.logic.tower.BasicTower;
import fi.towerdefencegamesinc.towerdefence.java.logic.tower.FreezeTower;
import fi.towerdefencegamesinc.towerdefence.java.logic.tower.Tower;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author vrsaari
 */
public class GraphicalUI extends Application {

    private Game game;
    private VBox sidepanel;
    private GridPane gameGrid;
    private Pane selectedGridTile;
    private StringProperty playerStatus;
    private StringProperty tileStatus;
    private Tile selectedTile;
    private VBox sidepanelContent;

    private static final Border SELECTED_BORDER = new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2)));
    private static final int SIDEBAR_WIDTH = 200;
    private static final double TICK_S = 0.5;
    private static final int TICK_NS = (int) (TICK_S * 1_000_000_000);

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.playerStatus = new SimpleStringProperty("");
        this.tileStatus = new SimpleStringProperty("");

        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        VBox root = new VBox();

        GridPane form = new GridPane();
        form.setAlignment(Pos.CENTER);
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(10, 10, 10, 10));

        root.getChildren().add(form);

        Label nameLabel = new Label("Name: ");
        form.add(nameLabel, 0, 1);
        TextField nameField = new TextField();
        form.add(nameField, 1, 1);

        Label mapSelectionLabel = new Label("Select map: ");
        form.add(mapSelectionLabel, 0, 3);
        ComboBox mapSelectionField = new ComboBox();
        form.add(mapSelectionField, 1, 3);
        mapSelectionField.getItems().addAll(GameMap.DEFAULT_MAPS);
        mapSelectionField.getItems().addAll(GameMap.externalMapFiles());
        mapSelectionField.getSelectionModel().selectFirst();

        Label difficultySelectionLabel = new Label("Select difficulty: ");
        form.add(difficultySelectionLabel, 0, 2);
        ComboBox<Difficulty> difficultySelectionField = new ComboBox();
        difficultySelectionField.getItems().addAll(Difficulty.values());
        difficultySelectionField.getSelectionModel().selectFirst();
        form.add(difficultySelectionField, 1, 2);

        Button startButton = new Button("Start game!");

        root.getChildren().add(startButton);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, Math.min(512, visualBounds.getWidth()),
                Math.min(256, visualBounds.getHeight()));

        primaryStage.setTitle("Towerdefence game!" + " "
                + visualBounds.getWidth() + " x " + visualBounds.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();

        startButton.setOnAction((ActionEvent t) -> {
            boolean external = false;
            String mapFileName = mapSelectionField.getValue().toString();
            if (mapFileName.startsWith(GameMap.EXTERNAL_MAP_PREFIX)) {
                external = true;
                mapFileName = mapFileName.substring(GameMap.EXTERNAL_MAP_PREFIX.length());
                System.out.println(mapFileName);

            }
            this.game = createGame(mapFileName, nameField.getText(), difficultySelectionField.getValue(), external);

            primaryStage.setScene(createGameScene(visualBounds));

            new AnimationTimer() {
                long previousTime = 0;

                @Override
                public void handle(long currentTime) {
                    if (currentTime - previousTime < 100_000_000) {
                        return;
                    }
                    playerStatus.set(game.getPlayer().toString());
                    if (selectedTile != null) {
                        tileStatus.set(selectedTile.toString());
                    }

                    if (game.gameOver()) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("TowerDefence");
                        alert.setHeaderText("Game Over - You Lost!");
                        alert.setContentText("Restart game to start a new game.");
                        alert.show();

                        this.stop();
                    }

                    if (game.gameWon()) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("TowerDefence");
                        alert.setHeaderText("Game Over - You Won!");
                        alert.setContentText("Restart game to start a new game.");
                        alert.show();

                        this.stop();
                    }

                }
            }.start();

            new AnimationTimer() {
                long previousTime = 0;
                Attacker[][] oldLocations = new Attacker[game.getMap().getHeight()][game.getMap().getWidth()];

                @Override
                public void handle(long currentTime) {
                    gameGrid.getChildren().stream().forEach((Node node) -> {
                        Integer row = GridPane.getRowIndex(node);
                        Integer col = GridPane.getColumnIndex(node);
                        Tile tile = game.getMap().getTile(GridPane.getRowIndex(node), GridPane.getColumnIndex(node));
                        Pane current = (Pane) node;
                        if (current.getBorder() != null && !current.equals(selectedGridTile)) {
                            current.setBorder(null);
                        }
                        current.getChildren().clear();
                        current.getChildren().add(createAttackerNode(tile.getAttackers()));
                        Tower t = tile.getTower();
                        if (t != null) {
                            current.getChildren().add(createTowerNode(t, current));
                        }
                    });

                }
            }.start();
        });

    }

    public Game createGame(String mapFileName, String playerName, Difficulty difficulty, boolean external) {
        Game newGame = new Game(mapFileName, playerName, difficulty, external);
        return newGame;
    }

    public Node createAttackerNode(List<Attacker> attackers) {

        FlowPane tile = new FlowPane(Orientation.VERTICAL);
        tile.setAlignment(Pos.CENTER);
        attackers.stream().forEach((Attacker a) -> {
            Label attackerLabel = new Label("A");
            Color color = Color.GREEN;
            System.out.println("HEALTHPCT: " + a.getHealthPct());
            if (a.getHealthPct() < 0.2) {
                color = Color.RED;
            } else if (a.getHealthPct() < 0.4) {
                color = Color.ORANGE;
            } else if (a.getHealthPct() < 0.6) {
                color = Color.YELLOW;
            } else if (a.getHealthPct() < 0.8) {
                color = Color.YELLOWGREEN;
            }
            attackerLabel.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
            tile.getChildren().add(attackerLabel);
        });
        return tile;
    }

    public Node createTowerNode(Tower t, Pane node) {
        StackPane towerImage = new StackPane();
        Circle towerBase = new Circle();
        towerBase.radiusProperty().bind(Bindings.min(towerImage.widthProperty().divide(2), towerImage.heightProperty().divide(2)));
        Color color;
        switch (t.getCharRepr()) {
            case 'B':
                color = Color.CORNSILK;
                break;
            case 'F':
                color = Color.AQUA;
                break;
            default:
                color = Color.BLACK;
        }
        towerBase.setFill(color);
        Text towerText = new Text(t.getCharRepr() + "");
        towerImage.getChildren().addAll(towerBase, towerText);
        return towerImage;
    }

    public void gameGridClick(int row, int col, Pane tile, MouseEvent e) {
        System.out.println("Clicked " + row + ", " + col);
        System.out.println("tile: " + tile);
        if (tile.equals(this.selectedGridTile)) {
            this.selectedGridTile = null;
            this.selectedTile = null;
            setSidepanelView(row, col, false);
            return;
        }
        this.selectedGridTile = tile;
        this.selectedTile = game.getMap().getTile(row, col);
        this.selectedGridTile.setBorder(SELECTED_BORDER);

        setSidepanelView(row, col, true);
    }

    public VBox initSidePanel() {
        VBox sidePanelDefaults = new VBox();
        sidePanelDefaults.setAlignment(Pos.TOP_LEFT);
        sidePanelDefaults.setPadding(new Insets(10, 0, 10, 0));
        sidePanelDefaults.setBackground(new Background(new BackgroundFill(Color.CORAL, CornerRadii.EMPTY, Insets.EMPTY)));
        sidePanelDefaults.setSpacing(10);
        Text first = new Text();
        Button startRoundButton = new Button("Start wave " + game.getNextWave());

        startRoundButton.setOnAction((ActionEvent e) -> {
            game.startNextWave();
            startRoundButton.setText("Start wave " + game.getNextWave());
            startRoundButton.setDisable(true);
            new AnimationTimer() {
                long previousTime = 0;

                @Override
                public void handle(long currentTime) {
                    if (currentTime - previousTime < TICK_NS) {
                        return;
                    }
                    previousTime = currentTime;
                    game.update();
                    if (game.currentWaveFinished()) {
                        this.stop();
                        startRoundButton.setDisable(false);
                    }
                }
            }.start();
        });

        first.textProperty().bind(this.playerStatus);
        this.sidepanelContent = new VBox();
        sidePanelDefaults.getChildren().addAll(first, sidepanelContent, startRoundButton);

        return sidePanelDefaults;
    }

    public void setSidepanelView(int row, int col, boolean selected) {
        this.sidepanelContent.getChildren().clear();
        if (selected) {
            this.sidepanelContent.getChildren().add(specialViewFor(row, col));
        }
    }

    public Node specialViewFor(int row, int col) {
        Tile mapTile = game.getMap().getTile(row, col);
        VBox specialView = new VBox();
        specialView.setAlignment(Pos.TOP_LEFT);
        Text tileText = new Text();
        tileText.textProperty().bind(tileStatus);
        tileText.wrappingWidthProperty().bind(this.sidepanel.widthProperty());
        if (mapTile.isBase()) {
            specialView.getChildren().addAll(new Label("Base\n"), tileText);
        } else if (mapTile.isBuildable()) {
            if (mapTile.getTower() == null) {
                Button buyBasicTowerButton = new Button("Basic tower");
                buyBasicTowerButton.setOnAction((ActionEvent e) -> {
                    Tower basicTower = new BasicTower(mapTile);
                    if (!game.getPlayer().buy(basicTower)) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("TowerDefence");
                        alert.setHeaderText("Not enough money.");
                        alert.setContentText("You need more money to buy a basic tower!\n"
                                + "Upgrade cost: " + basicTower.getBuildCost() + "\n"
                                + "Available funds: " + game.getPlayer().getCurrency());
                        alert.show();
                    }
                    setSidepanelView(row, col, true);
                });
                Button buyFreezeTowerButton = new Button("Freeze tower");
                buyFreezeTowerButton.setOnAction((ActionEvent e) -> {
                    Tower freezeTower = new FreezeTower(mapTile);
                    if (!game.getPlayer().buy(freezeTower)) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("TowerDefence");
                        alert.setHeaderText("Not enough money.");
                        alert.setContentText("You need more money to buy a freeze tower!\n"
                                + "Upgrade cost: " + freezeTower.getBuildCost() + "\n"
                                + "Available funds: " + game.getPlayer().getCurrency());
                        alert.show();
                    }
                    setSidepanelView(row, col, true);
                });
                specialView.getChildren().addAll(new Label("Add tower"), buyBasicTowerButton, buyFreezeTowerButton);

            } else {
                Tower tower = mapTile.getTower();
                Button upgradeButton = new Button("Upgrade tower (" + tower.getUpgradeCost() + ")");
                upgradeButton.setOnAction((ActionEvent e) -> {
                    if (!game.getPlayer().upgradeTower(tower)) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("TowerDefence");
                        alert.setHeaderText("Not enough money.");
                        alert.setContentText("You need more money to upgrade!\n"
                                + "Upgrade cost: " + tower.getUpgradeCost() + "\n"
                                + "Available funds: " + game.getPlayer().getCurrency());
                        alert.show();
                    }
                    setSidepanelView(row, col, true);
                });
                Button sellButton = new Button("Sell tower (" + tower.getWorth() + ")");
                sellButton.setOnAction((ActionEvent e) -> {
                    if (!game.getPlayer().sell(tower)) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("TowerDefence");
                        alert.setHeaderText("Unable to sell");
                        alert.setContentText("This shouldn't have happened...");
                        alert.show();
                    }
                    setSidepanelView(row, col, true);
                });
                specialView.getChildren().addAll(new Label("Tower\n"), tileText, upgradeButton, sellButton);
            }
        } else if (mapTile.isSpawn()) {
            specialView.getChildren().addAll(new Label("Spawn\n"), tileText);
        } else {
            specialView.getChildren().add(tileText);
        }
        return specialView;
    }

    public Scene createGameScene(Rectangle2D visualBounds) {
        int mapWidth = this.game.getMap().getWidth();
        int mapHeight = this.game.getMap().getHeight();

        int tileWidth = (int) ((visualBounds.getWidth() - SIDEBAR_WIDTH) / mapWidth);
        int tileHeight = (int) (visualBounds.getHeight() / mapHeight);

        int width = tileWidth * mapWidth + SIDEBAR_WIDTH;
        int height = tileHeight * mapHeight;

        BorderPane border = new BorderPane();

        this.gameGrid = new GridPane();
        border.setCenter(this.gameGrid);
        this.sidepanel = initSidePanel();
        border.setRight(this.sidepanel);
        this.sidepanel.setPrefWidth(SIDEBAR_WIDTH);
        RowConstraints fullHeight = new RowConstraints();
        fullHeight.setPercentHeight(100);

        for (int i = 0; i < mapHeight; i++) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100.0 / mapHeight);
            this.gameGrid.getRowConstraints().add(row);
            for (int j = 0; j < mapWidth; j++) {
                if (i == 0) {
                    ColumnConstraints col = new ColumnConstraints();
                    col.setPercentWidth(100.0 / (mapWidth));
                    this.gameGrid.getColumnConstraints().add(col);
                }
                StackPane tile = new StackPane();
                tile.setAlignment(Pos.CENTER);

                final int rowIndex = i;
                final int colIndex = j;
                tile.setOnMouseClicked((MouseEvent e) -> {
                    gameGridClick(rowIndex, colIndex, tile, e);
                });
                tile.setBackground(this.game.getMap().getTile(i, j).getType().getBackground());
                tile.getChildren().add(new Label("" + i + "," + j));
                this.gameGrid.add(tile, j, i);

            }
        }

        Scene gameScene = new Scene(border, width, height);

        return gameScene;
    }

    public static void run() {
        launch(GraphicalUI.class);
    }
}
