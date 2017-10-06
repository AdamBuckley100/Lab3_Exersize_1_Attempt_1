import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

//import MyClient.Listener;

//import Client.Listener;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MyClient extends JFrame {
	
	// Text field for receiving radius
	private JTextField jtf = new JTextField();

	// Text area to display contents
	private JTextArea jta = new JTextArea();

	// IO streams
	private DataOutputStream toServer;
	private DataInputStream fromServer;

//	Connection conn;
//	Statement theStatement;
//	ResultSet resultSet;
	
//	/** The name of the MySQL account to use (or empty for anonymous) */
//	private final String userName = "root";
//
//	/** The password for the MySQL account (or empty for anonymous) */
//	private final String password = "";
//
//	/** The name of the computer running MySQL */
//	private final String serverName = "localhost";
//
//	/** The port of the MySQL server (default is 3306) */
//	private final int portNumber = 3306;
//
//	/** The name of the database we are testing with (this default is installed with MySQL) */
//	private final String dbName = "data_structures_db";
//
//	/** The name of the table we are testing with */
//	private final String tableName = "employee";
	
	public static void main(String[] args) {
		new MyClient();
	}

	public MyClient() {
		
		// Panel p to hold the label and text field
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(new JLabel("Annual interest rate"), BorderLayout.WEST);
		p.add(jtf, BorderLayout.CENTER);
		jtf.setHorizontalAlignment(JTextField.RIGHT);

		setLayout(new BorderLayout());
		add(p, BorderLayout.NORTH);
		add(new JScrollPane(jta), BorderLayout.CENTER);

		JPanel p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		p1.add(new JLabel("number of years"), BorderLayout.WEST);
		p1.add(jtf, BorderLayout.CENTER);
		jtf.setHorizontalAlignment(JTextField.RIGHT);

		setLayout(new BorderLayout());
		add(p1, BorderLayout.NORTH);
		add(new JScrollPane(jta), BorderLayout.CENTER);

		jtf.addActionListener(new Listener()); // Register listener

		setTitle("Client");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true); // It is necessary to show the frame here!

		try {
			// Create a socket to connect to the server
			Socket socket = new Socket("localhost", 8000);
			// Socket socket = new Socket("130.254.204.36", 8000);
			// Socket socket = new Socket("drake.Armstrong.edu", 8000);

			// Create an input stream to receive data from the server
			fromServer = new DataInputStream(socket.getInputStream());

			// Create an output stream to send data to the server
			toServer = new DataOutputStream(socket.getOutputStream());
		}
		catch (IOException ex) {
			jta.append(ex.toString() + '\n');
		}
	}

	private class Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				// Get the radius from the text field
				double radius = Double.parseDouble(jtf.getText().trim());

				// Send the radius to the server
				toServer.writeDouble(radius);
				toServer.flush();

				// Get area from the server
				double area = fromServer.readDouble();

				// Display to the text area
				jta.append("Radius is " + radius + "\n");
				jta.append("Area received from the server is "
						+ area + '\n');
			}
			catch (IOException ex) {
				System.err.println(ex);
			}
		}
	}
	
//	/**
//	 * Get a new database connection
//	 * 
//	 * @return
//	 * @throws SQLException
//	 */
//	public Connection getConnection() throws SQLException { // for initial use only! establishes new connection.
//		Connection conn = null;
//		Properties connectionProps = new Properties();
//		connectionProps.put("user", this.userName);
//		connectionProps.put("password", this.password);
//
//		conn = DriverManager.getConnection("jdbc:mysql://"
//				+ this.serverName + ":" + this.portNumber + "/" + this.dbName,
//				connectionProps);
//
//		return conn;
//	}
	
//	public void reloadConnection() throws SQLException
//	{
//		Connection conn = null;
//		Properties connectionProps = new Properties();
//		connectionProps.put("user", this.userName);
//		connectionProps.put("password", this.password);
//
//		conn = DriverManager.getConnection("jdbc:mysql://"
//				+ this.serverName + ":" + this.portNumber + "/" + this.dbName,
//				connectionProps);
//	}

//	/**
//	 * Run a SQL command which does NOT return a recordset:
//	 * CREATE/INSERT/UPDATE/DELETE/DROP/etc.
//	 * 
//	 * @throws SQLException If something goes wrong
//	 */
//	public boolean executeUpdate(Connection conn, String command) throws SQLException {
//		Statement stmt = null;
//		try {
//			stmt = conn.createStatement();
//			stmt.executeUpdate(command); // This will throw a SQLException if it fails
//			return true;
//		} finally {
//
//			// This will run whether we throw an exception or not
//			if (stmt != null) { stmt.close(); }
//		}
//	}

//	/**
//	 * Connect to MySQL and do some stuff.
//	 */
//	public void runn() {
//
//		// Connect to MySQL for first and only time needed
//		//Connection conn = null;
//		try {
//			System.out.println("got here 1");
//			conn = this.getConnection();
//			System.out.println("Connected to database");
//
//			theStatement = conn.createStatement(); // let this happen ONE time.
//
//			resultSet = theStatement.executeQuery("select * from employee"); // ONE time.
//
//		} catch (SQLException e) {
//			System.out.println("ERROR: Could not connect to the database");
//			e.printStackTrace();
//			return;
//		}
//
//		try {
//			resultSet.absolute(1);
//		} catch (SQLException e) {
//			//Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

//	/**
//	 * Launch the application.
//	 * @throws SQLException 
//	 */
//	public static void main(String[] args) throws SQLException {
//
//		MyClient app = new MyClient();
//		app.runn(); // db set up (should only have to happen once).
//
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					//MyGui frame = new MyGui();
//					app.setVisible(true);
//
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	private String sendBackSsn() throws SQLException
//	{
//		Connection conn = getConnection();
//
//		System.out.println(conn); //you can take this out if you want
//
//		Statement theStatement = conn.createStatement();
//
//		ResultSet resultSet = theStatement.executeQuery("select Ssn from employee");
//		// Note: above replaced Data with web_members3. You can change "where id=11" to any number value.
//
//		String ssnInString = "";
//
//		int theSsn = 0;
//
//		if( resultSet.next() )
//		{
//			//id = resultSet.getString("id"); String.valueOf(i)
//			theSsn = resultSet.getInt("Ssn");
//		}
//
//		ssnInString = String.valueOf(theSsn);
//
//		System.out.println("Ssn is " + ssnInString);
//		return ssnInString;
//	}
//
//	private String sendBackBdate() throws SQLException
//	{
//		Connection conn = getConnection();
//
//		System.out.println(conn); //you can take this out if you want
//
//		Statement theStatement = conn.createStatement();
//
//		ResultSet resultSet = theStatement.executeQuery("select Bdate from employee");
//		// Note: above replaced Data with web_members3. You can change "where id=11" to any number value.
//
//		Date bDate = null;
//
//		if( resultSet.next() )
//		{
//			bDate = resultSet.getDate("BDate");
//		}
//
//		//convert the Date to String
//		String theDateInString = String.valueOf(bDate);
//
//		//int rowImCurrentlyAt = resultSet.getRow();
//
//		//Date bDate = resultSet.getDate(rowImCurrentlyAt);
//
//		//System.out.println("B Date is " + bDate);
//
//		return theDateInString;
//	}
//
//	private String sendBackName() throws SQLException
//	{
//		Connection conn = getConnection();
//
//		System.out.println(conn); //you can take this out if you want
//
//		Statement theStatement = conn.createStatement();
//
//		ResultSet resultSet = theStatement.executeQuery("select Name from employee");
//		// Note: above replaced Data with web_members3. You can change "where id=11" to any number value.
//
//		String name = "";
//
//		if( resultSet.next() )
//		{
//			name = resultSet.getString("name");
//		}
//
//		System.out.println("Name is " + name);
//		return name;
//	}
//
//	private String sendBackAddress() throws SQLException
//	{
//		Connection conn = getConnection();
//
//		System.out.println(conn); //you can take this out if you want
//
//		Statement theStatement = conn.createStatement();
//
//		ResultSet resultSet = theStatement.executeQuery("select Address from employee");
//		// Note: above replaced Data with web_members3. You can change "where id=11" to any number value.
//
//		String address = "";
//
//		if( resultSet.next() )
//		{
//			address = resultSet.getString("address");
//		}
//
//		System.out.println("Address is " + address);
//		return address;
//	}
//
//	private String sendBackSalary() throws SQLException
//	{
//		Connection conn = getConnection();
//
//		System.out.println(conn); //you can take this out if you want
//
//		Statement theStatement = conn.createStatement();
//
//		ResultSet resultSet = theStatement.executeQuery("select Salary from employee");
//		// Note: above replaced Data with web_members3. You can change "where id=11" to any number value.
//
//		String theSalaryInString = "";
//
//		if( resultSet.next() )
//		{
//			theSalaryInString = String.valueOf( resultSet.getBigDecimal("Salary") );
//		}
//
//		System.out.println("Salary is " + theSalaryInString);
//		return theSalaryInString;
//	}
//
//	private String sendBackGender() throws SQLException
//	{
//		Connection conn = getConnection();
//
//		System.out.println(conn); //you can take this out if you want
//
//		Statement theStatement = conn.createStatement();
//
//		ResultSet resultSet = theStatement.executeQuery("select Gender from employee");
//		// Note: above replaced Data with web_members3. You can change "where id=11" to any number value.
//
//		String genderInString = "";
//
//		if( resultSet.next() )
//		{
//			genderInString = resultSet.getString("Gender"); //Gender is char not varchar
//		}
//
//		System.out.println("Gender is " + genderInString);
//
//		return genderInString;
//	}
//
//	private String sendBackWorks_For() throws SQLException
//	{
//		Connection conn = getConnection();
//
//		System.out.println(conn); //you can take this out if you want
//
//		Statement theStatement = conn.createStatement();
//
//		ResultSet resultSet = theStatement.executeQuery("select Works_For from employee");
//		// Note: above replaced Data with web_members3. You can change "where id=11" to any number value.
//
//		int works_ForInInt = 0;
//		String works_ForInString = "";
//
//		if( resultSet.next() )
//		{
//			works_ForInInt = resultSet.getInt("Works_For");
//		}
//
//		works_ForInString = String.valueOf( works_ForInInt );
//
//		System.out.println("Works_For is " + works_ForInString);
//
//		return works_ForInString;
//	}
//
//	private String sendBackManages() throws SQLException
//	{
//		Connection conn = getConnection();
//
//		System.out.println(conn); //you can take this out if you want
//
//		Statement theStatement = conn.createStatement();
//
//		ResultSet resultSet = theStatement.executeQuery("select Manages from employee");
//		// Note: above replaced Data with web_members3. You can change "where id=11" to any number value.
//
//		int managesInInt = 0;
//		String managesInString = "";
//
//		if( resultSet.next() )
//		{
//			managesInInt = resultSet.getInt("Manages");
//		}
//
//		managesInString = String.valueOf( managesInInt );
//
//		System.out.println("Manages is " + managesInString);
//
//		return managesInString;
//	}
//
//	private String sendBackSupervises() throws SQLException
//	{
//		Connection conn = getConnection();
//
//		System.out.println(conn); //you can take this out if you want
//
//		Statement theStatement = conn.createStatement();
//
//		ResultSet resultSet = theStatement.executeQuery("select Supervises from employee");
//		// Note: above replaced Data with web_members3. You can change "where id=11" to any number value.
//
//		int supervisesInInt = 0;
//		String supervisesInString = "";
//
//		if( resultSet.next() )
//		{
//			supervisesInInt = resultSet.getInt("Supervises");
//		}
//
//		supervisesInString = String.valueOf( supervisesInInt );
//
//		System.out.println("Manages is " + supervisesInString);
//
//		System.out.println("Supervises is " + supervisesInString);
//
//		return supervisesInString;
//	}

//	/**
//	 * Create the frame.
//	 * @throws SQLException 
//	 */
//	public MyClient() throws SQLException {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 698, 444);
//		String theSsn = sendBackSsn();
//		String theBdate = sendBackBdate();
//		String theName = sendBackName();
//		String theAddress = sendBackAddress();
//		String theSalary = sendBackSalary();
//		String theGender = sendBackGender();
//		String theWorks_For = sendBackWorks_For();
//		String theManages = sendBackManages();
//		String theSupervises = sendBackSupervises();
//	}
}