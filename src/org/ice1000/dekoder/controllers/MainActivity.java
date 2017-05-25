package org.ice1000.dekoder.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXProgressBar;
import org.ice1000.dekoder.decoders.DecoderInterface;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import org.jetbrains.annotations.NotNull;
import org.ice1000.dekoder.utils.Echoer;

import java.io.File;

public class MainActivity extends MainActivityFramework {
	@FXML
	private JFXListView<String> filesList;
	@FXML
	private JFXListView<String> propertiesList;
	@FXML
	private AnchorPane window;
	@FXML
	private JFXButton playButton;
	@FXML
	private JFXProgressBar progressBar;
	private DecoderInterface dekoder;

	@FXML
	protected void playMusic(ActionEvent event) {super.playMusic();}

	@FXML
	protected void openFile(ActionEvent event) {showOpenFileDialog();}

	@Override
	protected void showOpenFileDialog() {openFile(getChooser().showOpenDialog(window.getScene().getWindow()));}

	@Override
	protected void openFile(@NotNull File file) {super.openFile(file);}

	@FXML
	protected void openHelp(ActionEvent event) {openGitHub();}

	@FXML
	protected void refreshList(ActionEvent event) {showFilesInTheSamePath(dekoder.getPath());}

	@Override
	protected void clearFilesShown() {filesList.getItems().remove(0, filesList.getItems().size());}

	@Override
	public DecoderInterface getDekoder() {return dekoder;}

	@Override
	public void setDekoder(DecoderInterface decoderInterface) {dekoder = decoderInterface;}

	@NotNull
	@Override
	protected JFXButton getPlayButton() {return playButton;}

	@Override
	public void setProgress(long i) {progressBar.setProgress(i / 1000.0 / dekoder.getTotalTime());}

	@FXML
	protected void stopPlaying(ActionEvent event) {super.stopPlaying();}

	@FXML
	protected void prevSong(ActionEvent event) {super.changeSong(false);}

	@FXML
	protected void nextSong(ActionEvent event) {super.changeSong(true);}

	@FXML
	protected void onDestroyed(ActionEvent event) {super.onDestroyed();}

	@NotNull
	@Override
	protected Echoer propertiesPrinter() {return msg -> propertiesList.getItems().add(msg);}

	@NotNull
	@Override
	protected Echoer filesPrinter() {
		return msg -> {ObservableList list = filesList.getItems(); if (!list.contains(msg)) list.add(msg);};
	}

	@Override
	@FXML
	protected void initialize() {super.initialize();}

	@Override
	protected void clearPropertiesShown() {propertiesList.getItems().remove(0, propertiesList.getItems().size());}
}
