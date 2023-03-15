package com.example.studentdemo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class MainPage implements Initializable {

	@FXML
	private AnchorPane Course_Panel;

	@FXML
	private AnchorPane Home_Panel;

	@FXML
	private AnchorPane Scoreboard_Panel;

	@FXML
	private AnchorPane Student_Panel;

	@FXML
	private Button coursebtn;

	@FXML
	private Button crs_addBtn;

	@FXML
	private CheckBox crs_check;

	@FXML
	private Button crs_clearBtn;

	@FXML
	private Button crs_delBtn;

	@FXML
	private TextField crs_depart_box;

	@FXML
	private TextField crs_desc;

	@FXML
	private DatePicker crs_finish;

	@FXML
	private TextField crs_id_box;

	@FXML
	private TextField crs_lect;

	@FXML
	private TextField crs_name_box;

	@FXML
	private TextField crs_numStd_box;

	@FXML
	private TextField crs_search_box;

	@FXML
	private ImageView crs_search_icon;

	@FXML
	private DatePicker crs_start;

	@FXML
	private Button crs_updBtn;

	@FXML
	private Button homebtn;

	@FXML
	private Button scb_AddBtn;

	@FXML
	private Button scb_ClearBtn;

	@FXML
	private Button scb_DelBtn;

	@FXML
	private TextField scb_Score;

	@FXML
	private Button scb_UpDBtn;

	@FXML
	private TextField scb_crsId;

	@FXML
	private TextField scb_crsName;

	@FXML
	private TextField scb_stdID;

	@FXML
	private TextField scb_stdName;

	@FXML
	private Button scoreboardbtn;

	@FXML
	private Button scoreboardbtn1;

	@FXML
	private ImageView search_icon;

	@FXML
	private Button signout_btn;

	@FXML
	private Button std_AddBtn;

	@FXML
	private Button std_ClearBtn;

	@FXML
	private Button std_DelBtn;

	@FXML
	private Button std_UpdBtn;

	@FXML
	private CheckBox std_available_check;

	@FXML
	private DatePicker std_birth_box;


	@FXML
	private CheckBox std_gender_female;

	@FXML
	private CheckBox std_gender_male;

	@FXML
	private TextField std_id_box;

	@FXML
	private TextField std_name_box;

	@FXML
	private TextField std_search_box;

	@FXML
	private ChoiceBox<String> std_year;

	@FXML
	private Button studentbtn;

	@FXML
	private Label total_crs_count;

	@FXML
	private Label total_std_count;

	@FXML
	private Label total_usr_count;



	@FXML
	private TextField std_course_id_box;
	@FXML
	private ChoiceBox<String> std_Gender;

	@FXML
	private ChoiceBox<String> crs_Status;

	@FXML
	private TableView<StudentData> std_Table;
	@FXML
	private TableColumn<StudentData, Date> std_col_Birth;

	@FXML
	private TableColumn<StudentData, String> std_col_CrsID;

	@FXML
	private TableColumn<StudentData, String> std_col_Gender;

	@FXML
	private TableColumn<StudentData, Integer> std_col_ID;

	@FXML
	private TableColumn<StudentData, String> std_col_Name;

	@FXML
	private TableColumn<StudentData, String> std_col_Status;

	@FXML
	private TableColumn<StudentData, String> std_col_Year;

	private Connection connect;
	private PreparedStatement prepare;
	private Statement statement;
	private ResultSet result;

	public static void setupChoiceBox(ChoiceBox<String> choiceBox, String[] options, int defaultOptionIndex) {
		choiceBox.setItems(FXCollections.observableArrayList(options));
		choiceBox.getSelectionModel().select(defaultOptionIndex);
	}

	public void initChoiceBox() {
		setupChoiceBox(std_Gender, new String[]{"Male", "Female", "Others"}, 0);
		setupChoiceBox(std_year, new String[]{"I", "II", "III", "IV"}, 0);
		std_year.setValue(null);
		std_Gender.setValue(null);
	}

	public ObservableList<StudentData> getStudentsFromDatabase() throws SQLException {
		// Replace "jdbc:oracle:thin:@localhost:1521:XE" with your Oracle database connection URL
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "sys as SYSDBA", "12122003");

		ObservableList<StudentData> studentList = FXCollections.observableArrayList();

		// Replace "students" with the name of your table
		String sql = "SELECT * FROM student";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			int id = resultSet.getInt("ID");
			String name = resultSet.getString("NAME");
			Date birthDate = resultSet.getDate("BIRTH");
			String gender = resultSet.getString("GENDER");
			String Year = resultSet.getString("YEAR");
			String courseId = resultSet.getString("COURSE_ID");
			String status = resultSet.getString("STATUS");

			StudentData student = new StudentData(id, name, birthDate, gender, Year, courseId, status);
			studentList.add(student);

		}

		resultSet.close();
		statement.close();
		connection.close();

		return studentList;
	}


	public void displayStudentDataInTable() throws SQLException {
		ObservableList<StudentData> studentDataList = getStudentsFromDatabase();

		std_col_ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
		std_col_Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
		std_col_Birth.setCellValueFactory(new PropertyValueFactory<>("Birth"));
		std_col_Gender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
		std_col_Year.setCellValueFactory(new PropertyValueFactory<>("Year"));
		std_col_CrsID.setCellValueFactory(new PropertyValueFactory<>("Course_ID"));
		std_col_Status.setCellValueFactory(new PropertyValueFactory<>("Status"));

		std_Table.setItems(studentDataList);
	}
	@FXML
	private void clearBox(ActionEvent event) {
		std_id_box.clear();
		std_name_box.clear();
		std_birth_box.setValue(null);
		std_year.setValue(null);
		std_Gender.setValue(null);
		std_course_id_box.clear();
		std_available_check.setSelected(false);
	}
	@FXML
	private void handleUpdateButton(ActionEvent event) {
		if(!std_id_box.getText().isEmpty()){
			StudentData selectedStudent = std_Table.getSelectionModel().getSelectedItem();
			int selectedStudentId = selectedStudent.getID();

			try {
				Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "sys as SYSDBA", "12122003");
				String sql = "UPDATE student SET NAME = ?, BIRTH = ?, GENDER = ?, YEAR = ?, COURSE_ID = ?, STATUS = ? WHERE ID = ?";
				PreparedStatement statement = connection.prepareStatement(sql);

				statement.setString(1, std_name_box.getText());
				statement.setDate(2, Date.valueOf(std_birth_box.getValue()));
				statement.setString(3, std_Gender.getValue());
				statement.setString(4, std_year.getValue());
				statement.setString(5, std_course_id_box.getText());
				statement.setString(6, std_available_check.isSelected()? "A": "X");
				statement.setInt(7, selectedStudentId);

				statement.executeUpdate();

				selectedStudent.setName(std_name_box.getText());
				selectedStudent.setBirth(Date.valueOf(std_birth_box.getValue()));
				selectedStudent.setGender(std_Gender.getValue());
				selectedStudent.setYear(std_year.getValue());
				selectedStudent.setCourse_ID(std_course_id_box.getText());
				selectedStudent.setStatus(std_available_check.isSelected()? "A": "X");

				std_Table.refresh();

				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	@FXML
	void handleAddButtonAction(ActionEvent event) throws SQLException {
		if(!std_id_box.getText().isEmpty() && !std_name_box.getText().isEmpty() && std_birth_box.getValue() != null && std_Gender.getValue() != null && std_year.getValue() != null && !std_course_id_box.getText().isEmpty()) {
			// Get the values entered by the user in the text fields and choice boxes
			int Id = Integer.parseInt(std_id_box.getText());
			String name = std_name_box.getText();
			Date birthDate = Date.valueOf(std_birth_box.getValue());
			String gender = std_Gender.getValue();
			String year = std_year.getValue();
			String courseId = std_course_id_box.getText();
			String status = std_available_check.isSelected()?"A":"X";

			// Insert the new student data into the database
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "sys as SYSDBA", "12122003");
			String sql = "INSERT INTO student (ID, NAME, BIRTH, GENDER, YEAR, COURSE_ID, STATUS) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, Id);
			statement.setString(2, name);
			statement.setDate(3, birthDate);
			statement.setString(4, gender);
			statement.setString(5, year);
			statement.setString(6, courseId);
			statement.setString(7, status);
			statement.executeUpdate();
			statement.close();
			connection.close();

			// Clear the text fields and choice boxes
			std_id_box.clear();
			std_name_box.clear();
			std_birth_box.setValue(null);
			std_Gender.getSelectionModel().selectFirst();
			std_year.getSelectionModel().selectFirst();
			std_course_id_box.clear();
			std_available_check.setSelected(false);

			// Refresh the student table to show the new student data
			displayStudentDataInTable();
			System.out.println("Filled");
		}
		else{
			System.out.println("Fill all blank fields");
		}
	}

	@FXML
	void handleDeleteButtonAction(ActionEvent event) throws SQLException {
		if (!std_Table.getSelectionModel().getSelectedItems().isEmpty()) {
			int selectedStudentId = std_Table.getSelectionModel().getSelectedItem().getID();

			// Delete the student from the database
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "sys as SYSDBA", "12122003");
			String sql = "DELETE FROM student WHERE ID=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, selectedStudentId);
			statement.executeUpdate();
			statement.close();
			connection.close();

			displayStudentDataInTable();
		}
		else {
			System.out.println("Select item pls");
		}


	}

	@FXML
	void handleSearchButtonAction(ActionEvent event) throws SQLException {
		String searchQuery = std_search_box.getText();
		ObservableList<StudentData> studentList = FXCollections.observableArrayList();
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "sys as SYSDBA", "12122003");
		String sql = "SELECT * FROM student WHERE NAME LIKE '%" + searchQuery + "%' OR COURSE_ID LIKE '%" + searchQuery
				+ "%' OR ID LIKE '%" + searchQuery + "%' or gender LIKE '%" + searchQuery + "%' or YEAR LIKE '%" + searchQuery +
				"%' or Birth LIKE '%" + searchQuery + "%' or Status LIKE '%" + searchQuery + "%'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			StudentData student = new StudentData(resultSet.getInt("ID"),
					resultSet.getString("NAME"),
					resultSet.getDate("BIRTH"),
					resultSet.getString("GENDER"),
					resultSet.getString("YEAR"),
					resultSet.getString("COURSE_ID"),
					resultSet.getString("STATUS"));
			studentList.add(student);
		}

		resultSet.close();
		statement.close();
		connection.close();

		std_Table.setItems(studentList);

	}


	public void switchPanel(ActionEvent event) throws SQLException {

		if(event.getSource() == homebtn){
			Home_Panel.setVisible(true);
			Student_Panel.setVisible(false);
			Course_Panel.setVisible(false);
			Scoreboard_Panel.setVisible(false);

		}else if (event.getSource() == studentbtn){
			Home_Panel.setVisible(false);
			Student_Panel.setVisible(true);
			Course_Panel.setVisible(false);
			Scoreboard_Panel.setVisible(false);


			setupTableView();

		} else if (event.getSource() == coursebtn) {
			Home_Panel.setVisible(false);
			Student_Panel.setVisible(false);
			Course_Panel.setVisible(true);
			Scoreboard_Panel.setVisible(false);
		} else if (event.getSource() == scoreboardbtn) {
			Home_Panel.setVisible(false);
			Student_Panel.setVisible(false);
			Course_Panel.setVisible(false);
			Scoreboard_Panel.setVisible(true);
		}
	}

	public void setupTableView() throws SQLException {
		// assuming your TableView instance is named "tableView"
		displayStudentDataInTable();
		initChoiceBox();
		std_Table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				// assuming your TextField instance is named "textField"
				// update the TextField with the selected item
				int ID_field = newSelection.getID();
				String Name_field = newSelection.getName();
				Date Date_field = newSelection.getBirth();
				String Gender_field = newSelection.getGender();
				String Year_field = String.valueOf(newSelection.getYear());
				String CrsID_field = newSelection.getCourse_ID();
				String Status_field = newSelection.getStatus();

				std_id_box.setText(String.valueOf(ID_field));
				std_name_box.setText(Name_field);
				std_birth_box.setValue(Date_field.toLocalDate());
				std_year.setValue(Year_field);
				std_Gender.setValue(Gender_field);
				std_course_id_box.setText(String.valueOf(CrsID_field));
				std_available_check.setSelected(!Status_field.isEmpty());
			}else{
				std_id_box.clear();
				std_name_box.clear();
				std_birth_box.setValue(null);
				std_year.setValue(null);
				std_Gender.setValue(null);
				std_course_id_box.clear();
				std_available_check.setSelected(false);
			}
		});
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle){

		try {
			setupTableView();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
