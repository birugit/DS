package com.gap.fmr.purge;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Properties;

import oracle.sql.DATE;

public class FMRPurge {

	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";

	private static String DB_URL_FMR1 = "";
	private static String DB_URL_FMR2 = "";

	private static Connection fmrDBConnection = null;

	// Test
	private static final String INSERT_TPUR_STYLE_PURGE = "INSERT INTO TPUR_STYLE_PURGE(STY_CD) VALUES(?)";

	// Truncate Table
	private static final String TRUNCATE_TPUR_STYLE_PURGE = "TRUNCATE TABLE TPUR_STYLE_PURGE";

	// Total input styles query
	private static final String SELECT_INPUT_STYLE_COUNT_TPUR_STYLE_PURGE = "SELECT COUNT(*) FROM TPUR_STYLE_PURGE";

	// Total count to delete queries
	private static final String SELECT_COUNT_TO_DELETE_TSSP_STR_SPEC_PRC = "SELECT COUNT(DISTINCT STY_CD) FROM TSSP_STR_SPEC_PRC TSSP WHERE EXISTS (SELECT 1 FROM TPUR_STYLE_PURGE PUR WHERE TSSP.STY_CD = PUR.STY_CD)";
	private static final String SELECT_COUNT_TO_DELETE_TPLU_PLU_ITM_EXT = "SELECT COUNT(DISTINCT STY_CD) FROM TPLU_PLU_ITM_EXT TPLU WHERE EXISTS (SELECT 1 FROM FMR.TPUR_STYLE_PURGE PUR WHERE TPLU.STY_CD = PUR.STY_CD)";
	private static final String SELECT_COUNT_TO_DELETE_TCIE_CHINO_ITM_EXT = "SELECT COUNT(DISTINCT STY_CD) FROM TCIE_CHINO_ITM_EXT TCIE WHERE EXISTS (SELECT 1 FROM FMR.TPUR_STYLE_PURGE PUR WHERE TCIE.STY_CD = PUR.STY_CD)";
	private static final String SELECT_COUNT_TO_DELETE_TDME_DENIM_ITM_EXT = "SELECT COUNT(DISTINCT STY_CD) FROM TDME_DENIM_ITM_EXT TDME WHERE EXISTS (SELECT 1 FROM FMR.TPUR_STYLE_PURGE PUR WHERE TDME.STY_CD = PUR.STY_CD)";
	private static final String SELECT_COUNT_TO_DELETE_TKIE_KHAKI_ITM_EXT = "SELECT COUNT(DISTINCT STY_CD) FROM TKIE_KHAKI_ITM_EXT TKIE WHERE EXISTS (SELECT 1 FROM FMR.TPUR_STYLE_PURGE PUR WHERE TKIE.STY_CD = PUR.STY_CD)";

	// Total count to delete queries
	private static final String SELECT_DISTINCT_NOT_DELETED_TSSP_STR_SPEC_PRC = "SELECT DISTINCT STY_CD FROM TSSP_STR_SPEC_PRC TSSP WHERE EXISTS (SELECT 1 FROM TPUR_STYLE_PURGE PUR WHERE TSSP.STY_CD = PUR.STY_CD)";
	private static final String SELECT_DISTINCT_NOT_DELETED_TPLU_PLU_ITM_EXT = "SELECT DISTINCT STY_CD  FROM TPLU_PLU_ITM_EXT TPLU WHERE EXISTS (SELECT 1 FROM FMR.TPUR_STYLE_PURGE PUR WHERE TPLU.STY_CD = PUR.STY_CD)";
	private static final String SELECT_DISTINCT_NOT_DELETED_TCIE_CHINO_ITM_EXT = "SELECT DISTINCT STY_CD  FROM TCIE_CHINO_ITM_EXT TCIE WHERE EXISTS (SELECT 1 FROM FMR.TPUR_STYLE_PURGE PUR WHERE TCIE.STY_CD = PUR.STY_CD)";
	private static final String SELECT_DISTINCT_NOT_DELETED_TDME_DENIM_ITM_EXT = "SELECT DISTINCT STY_CD  FROM TDME_DENIM_ITM_EXT TDME WHERE EXISTS (SELECT 1 FROM FMR.TPUR_STYLE_PURGE PUR WHERE TDME.STY_CD = PUR.STY_CD)";
	private static final String SELECT_DISTINCT_NOT_DELETED_TKIE_KHAKI_ITM_EXT = "SELECT DISTINCT STY_CD  FROM TKIE_KHAKI_ITM_EXT TKIE WHERE EXISTS (SELECT 1 FROM FMR.TPUR_STYLE_PURGE PUR WHERE TKIE.STY_CD = PUR.STY_CD)";

	// Delete queries
	private static final String DELETE_TSSP_STR_SPEC_PRC = "DELETE FROM TSSP_STR_SPEC_PRC TSSP WHERE EXISTS (SELECT 1 FROM FMR.TPUR_STYLE_PURGE PUR WHERE TSSP.STY_CD = PUR.STY_CD)";
	private static final String DELETE_TPLU_PLU_ITM_EXT = "DELETE FROM TPLU_PLU_ITM_EXT TPLU WHERE EXISTS (SELECT 1 FROM FMR.TPUR_STYLE_PURGE PUR WHERE TPLU.STY_CD = PUR.STY_CD)";
	private static final String DELETE_TCIE_CHINO_ITM_EXT = "DELETE FROM TCIE_CHINO_ITM_EXT TCIE WHERE EXISTS (SELECT 1 FROM FMR.TPUR_STYLE_PURGE PUR WHERE TCIE.STY_CD = PUR.STY_CD)";
	private static final String DELETE_TDME_DENIM_ITM_EXT = "DELETE FROM TDME_DENIM_ITM_EXT TDME WHERE EXISTS (SELECT 1 FROM FMR.TPUR_STYLE_PURGE PUR WHERE TDME.STY_CD = PUR.STY_CD)";
	private static final String DELETE_TKIE_KHAKI_ITM_EXT = "DELETE FROM TKIE_KHAKI_ITM_EXT TKIE WHERE EXISTS (SELECT 1 FROM FMR.TPUR_STYLE_PURGE PUR WHERE TKIE.STY_CD = PUR.STY_CD)";

	private static LinkedHashSet listOfItems = new LinkedHashSet();
	private static HashSet setOfStylesNotDeleted = new HashSet();
	private static int countOfTotalRecordsToDelete = 0;
	private static FMRErrorReport fmrErrorReport = new FMRErrorReport();
	private static FMRSummaryReport fmrSummaryReport = new FMRSummaryReport();
	private static int stylesProcessed = 0;

	private static String DB_URL_CLIENT_FMR1;
	private static String DB_INSTANCE_FMR1;
	private static String DB_USER_FMR1;
	private static String DB_PASSWORD_FMR1;
	private static String DB_URL_CLIENT_FMR2;
	private static String DB_INSTANCE_FMR2;
	private static String DB_USER_FMR2;
	private static String DB_PASSWORD_FMR2;
	private static String HOSTNAME;
	private static String ACTUAL_HOST;

	public static void main(String[] args) {

		Date date = new Date();
		try {
			InetAddress ip;

			try {
				ip = InetAddress.getLocalHost();
				HOSTNAME = ip.getHostName();
				System.out.println("Log Startred at:" + date.toString());
				System.out.println("HOSTNAME:" + HOSTNAME);
			} catch (UnknownHostException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			// read the db configurations
			readDBConfigurations();

			if (DB_URL_FMR1 != null || !(DB_URL_FMR1.equalsIgnoreCase(null))) {
				fmrDBConnection = getFmrDBConnection(DB_URL_FMR1);
			}

			// read the style codes from PURP.STYLE.PURGE.READY.QUAL
			readDataFromFile();

			System.out.println("In instance1 db connection");
			// insert the records into TPUR_STYLE_PURGE of instance1
			if (DB_INSTANCE_FMR1 != null) {
				insertStyleCode(DB_INSTANCE_FMR1);

			}

			System.out.println("End instance1 db connection");

			if (DB_URL_FMR2 != null || !(DB_URL_FMR2.equalsIgnoreCase(null))) {
				fmrDBConnection = getFmrDBConnection(DB_URL_FMR2);

			}

			System.out.println("In instance2 db connection");

			// insert the records into TPUR_STYLE_PURGE of instance2
			if (DB_INSTANCE_FMR2 != null) {

				insertStyleCode(DB_INSTANCE_FMR2);
			}

			System.out.println("End instance2 db connection");

		} catch (SQLException exception) {
			System.out.println(exception.getMessage());
			exception.printStackTrace();
		} finally {
			stylesProcessed = countOfTotalRecordsToDelete - setOfStylesNotDeleted.size();
			fmrSummaryReport.prepareSummaryReport(countOfTotalRecordsToDelete, stylesProcessed);
			fmrErrorReport.prepareErrorReport(setOfStylesNotDeleted);

			if (fmrDBConnection != null) {
				try {
					fmrDBConnection.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}

	private static void readDBConfigurations() {

		BufferedReader br = null;
		InputStream configFile = null;
		Properties dbProperties = new Properties();
		try {

			configFile = new FileInputStream("/home/apps/fmr/bin/dbconfiguration.properties");
			///home/apps/fmr/bin/dbconfiguration.properties
			dbProperties.load(configFile);
			
			ACTUAL_HOST = dbProperties.getProperty("PROD_HOSTNAME");
			System.out.println("ACTUAL_HOST:"+ACTUAL_HOST);
			if(HOSTNAME != null || !("".equalsIgnoreCase(ACTUAL_HOST))){
			
				//commented dev block
		/*	if (HOSTNAME.equalsIgnoreCase(ACTUAL_HOST)) {
				// FMRT1 instance
				DB_URL_CLIENT_FMR1 = dbProperties.getProperty("FMRT1_URL");
				DB_INSTANCE_FMR1 = dbProperties.getProperty("FMRT1_Instance");
				DB_USER_FMR1 = dbProperties.getProperty("FMRT1_User");
				DB_PASSWORD_FMR1 = dbProperties.getProperty("FMRT1_Password");

				DB_URL_FMR1 = DB_URL_CLIENT_FMR1 + "/" + DB_INSTANCE_FMR1;

				// FMRT2 Instance
				DB_URL_CLIENT_FMR2 = dbProperties.getProperty("FMRT2_URL");
				DB_INSTANCE_FMR2 = dbProperties.getProperty("FMRT2_Instance");
				DB_USER_FMR2 = dbProperties.getProperty("FMRT2_User");
				DB_PASSWORD_FMR2 = dbProperties.getProperty("FMRT2_Password");

				DB_URL_FMR2 = DB_URL_CLIENT_FMR2 + "/" + DB_INSTANCE_FMR2;
			} else */if (HOSTNAME.equalsIgnoreCase(ACTUAL_HOST)) {
				// FMRP1 instance
				DB_URL_CLIENT_FMR1 = dbProperties.getProperty("FMRP1_URL");
				DB_INSTANCE_FMR1 = dbProperties.getProperty("FMRP1_Instance");
				DB_USER_FMR1 = dbProperties.getProperty("FMRP1_User");
				DB_PASSWORD_FMR1 = dbProperties.getProperty("FMRP1_Password");

				DB_URL_FMR1 = DB_URL_CLIENT_FMR1 + "/" + DB_INSTANCE_FMR1;

				// FMRP2 Instance
				DB_URL_CLIENT_FMR2 = dbProperties.getProperty("FMRP2_URL");
				DB_INSTANCE_FMR2 = dbProperties.getProperty("FMRP2_Instance");
				DB_USER_FMR2 = dbProperties.getProperty("FMRP2_User");
				DB_PASSWORD_FMR2 = dbProperties.getProperty("FMRP2_Password");

				DB_URL_FMR2 = DB_URL_CLIENT_FMR2 + "/" + DB_INSTANCE_FMR2;
			}
			}
			System.out.println("DB_URL_CLIENT_FMR1 = " + DB_URL_CLIENT_FMR1);
			System.out.println("DB_INSTANCE_FMR1 = " + DB_INSTANCE_FMR1);
			System.out.println("DB_USER_FMR1 = " + DB_USER_FMR1);
			System.out.println("DB_PASSWORD_FMR1 = " + DB_PASSWORD_FMR1);
			System.out.println("DB_URL_FMR1 = " + DB_URL_FMR1);

			System.out.println("DB_URL_CLIENT_FMR2 = " + DB_URL_CLIENT_FMR2);
			System.out.println("DB_INSTANCE_FMR2 = " + DB_INSTANCE_FMR2);
			System.out.println("DB_USER_FMR2 = " + DB_USER_FMR2);
			System.out.println("DB_PASSWORD_FMR2 = " + DB_PASSWORD_FMR2);
			System.out.println("DB_URL_FMR2 = " + DB_URL_FMR2);

		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println("DB URL issue, Please check the dbconfigurations.properties file");
			System.exit(1);
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				System.out.println(ex.getMessage());
				ex.printStackTrace();
				System.out.println("IO Connection close fail");
				System.exit(1);
			}
		}
	}

	private static void deleteRecordsFromInstanceFMR1() throws SQLException {
		try {
			processStyles(SELECT_COUNT_TO_DELETE_TSSP_STR_SPEC_PRC, DELETE_TSSP_STR_SPEC_PRC,
					SELECT_DISTINCT_NOT_DELETED_TSSP_STR_SPEC_PRC);
			processStyles(SELECT_COUNT_TO_DELETE_TPLU_PLU_ITM_EXT, DELETE_TPLU_PLU_ITM_EXT,
					SELECT_DISTINCT_NOT_DELETED_TPLU_PLU_ITM_EXT);
		} finally {
			if (fmrDBConnection != null) {
				fmrDBConnection.close();
			}
		}
	}

	private static void deleteRecordsFromInstanceFMR2() throws SQLException {
		try {
			processStyles(SELECT_COUNT_TO_DELETE_TSSP_STR_SPEC_PRC, DELETE_TSSP_STR_SPEC_PRC,
					SELECT_DISTINCT_NOT_DELETED_TSSP_STR_SPEC_PRC);
			processStyles(SELECT_COUNT_TO_DELETE_TPLU_PLU_ITM_EXT, DELETE_TPLU_PLU_ITM_EXT,
					SELECT_DISTINCT_NOT_DELETED_TPLU_PLU_ITM_EXT);
			processStyles(SELECT_COUNT_TO_DELETE_TCIE_CHINO_ITM_EXT, DELETE_TCIE_CHINO_ITM_EXT,
					SELECT_DISTINCT_NOT_DELETED_TCIE_CHINO_ITM_EXT);
			processStyles(SELECT_COUNT_TO_DELETE_TDME_DENIM_ITM_EXT, DELETE_TDME_DENIM_ITM_EXT,
					SELECT_DISTINCT_NOT_DELETED_TDME_DENIM_ITM_EXT);
			processStyles(SELECT_COUNT_TO_DELETE_TKIE_KHAKI_ITM_EXT, DELETE_TKIE_KHAKI_ITM_EXT,
					SELECT_DISTINCT_NOT_DELETED_TKIE_KHAKI_ITM_EXT);
		} finally {
			if (fmrDBConnection != null) {
				fmrDBConnection.close();
			}
		}
	}

	private static void processStyles(String countToDeleteRecordsQuery, String deleteQuery,
			String notDeletedRecordsQuery) {
		PreparedStatement preparedStatement = null;
		ResultSet rs;
		int countOfRecordsToDelete = 0;
		int numberOfStylesNotDeleted = 0;
		int styleNotDeleted = 0;
		try {

			// To know the number of unavailable styles
			preparedStatement = fmrDBConnection.prepareStatement(countToDeleteRecordsQuery);
			// int totalRecordsToDelete = preparedStatement.executeUpdate();
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				countOfRecordsToDelete = rs.getInt(1);
				System.out.println("Not Available:" + countOfRecordsToDelete);
			}
			preparedStatement.close();
			rs.close();

			// Delete the styles

			preparedStatement = fmrDBConnection.prepareStatement(deleteQuery);
			preparedStatement.executeUpdate();
			preparedStatement.close();

			System.out.println("Deleted");

			// To know any of not deleted styles count
			preparedStatement = fmrDBConnection.prepareStatement(countToDeleteRecordsQuery);

			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				numberOfStylesNotDeleted = rs.getInt(1);
				System.out.println("numberOfStylesNotDeleted:" + numberOfStylesNotDeleted);
			}
			rs.close();
			preparedStatement.close();
			

			// If number of styles not deleted more than 0 , update Error Report
			if (numberOfStylesNotDeleted > 0) {
				preparedStatement = fmrDBConnection.prepareStatement(notDeletedRecordsQuery);
				rs = preparedStatement.executeQuery();
				while (rs.next()) {
					styleNotDeleted = rs.getInt(1);
					System.out.println("styleNotDeleted:" + styleNotDeleted);
					setOfStylesNotDeleted.add(new Integer(styleNotDeleted));

				}
				rs.close();
				preparedStatement.close();
			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

	private static void insertStyleCode(String dbInstance) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet rs;
		int countOfTruncatedTable = 0;
		try {
			System.out.println("Truncate the Table started");
			preparedStatement = fmrDBConnection.prepareStatement(TRUNCATE_TPUR_STYLE_PURGE);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
			preparedStatement = fmrDBConnection.prepareStatement(SELECT_INPUT_STYLE_COUNT_TPUR_STYLE_PURGE);
			rs = preparedStatement.executeQuery();
			while(rs.next()){
				countOfTruncatedTable = rs.getInt(1);
			}
			if(countOfTruncatedTable>0){
				System.out.println("TPUR_STYLE_PURGE Table Not Truncated properly and count of remained records: "+countOfTruncatedTable);
			}else{
				System.out.println("TPUR_STYLE_PURGE Table Truncated properly and count of remained records: "+countOfTruncatedTable);
				
			}
			preparedStatement.close();
			rs.close();

			preparedStatement = fmrDBConnection.prepareStatement(INSERT_TPUR_STYLE_PURGE);
			Iterator it = listOfItems.iterator();

			System.out.println("List count:" + listOfItems.size());

			while (it.hasNext()) {
				preparedStatement.setInt(1, ((Integer) it.next()).intValue());
				preparedStatement.executeUpdate();
			}

			preparedStatement.close();
			
			// To know the total styles to be deleted and executing only one
			// time for two instances, as it will not going to change

			preparedStatement = fmrDBConnection.prepareStatement(SELECT_INPUT_STYLE_COUNT_TPUR_STYLE_PURGE);

			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				countOfTotalRecordsToDelete = rs.getInt(1);
				System.out.println("totalRecords:" + countOfTotalRecordsToDelete);
			}
			preparedStatement.close();
			rs.close();

			if (dbInstance.indexOf('1') > 0) {
				deleteRecordsFromInstanceFMR1();
			} else if (dbInstance.indexOf('2') > 0) {
				deleteRecordsFromInstanceFMR2();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			fmrDBConnection.rollback();
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (fmrDBConnection != null) {
				fmrDBConnection.close();
			}
		}
	}

	private static Connection getFmrDBConnection(String DB_URL_FMR) {
		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			System.out.println("Invalid Driver");
			System.exit(1);
		}

		System.out.println("DB_URL_FMR:" + DB_URL_FMR);

		try {
			if (DB_INSTANCE_FMR1.indexOf('1') > 0) {

				dbConnection = DriverManager.getConnection(DB_URL_FMR, DB_USER_FMR1, DB_PASSWORD_FMR1);
				return dbConnection;
			}
			if (DB_INSTANCE_FMR2.indexOf('2') > 0) {
				dbConnection = DriverManager.getConnection(DB_URL_FMR, DB_USER_FMR2, DB_PASSWORD_FMR2);
				return dbConnection;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Database connection issue");
			System.exit(1);
		}
		return dbConnection;
	}

	private static void readDataFromFile() {
		BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader("/home/apps/fmr/bin/PURP.STYLE.PURGE.READY.QUAL"));
				String textSplitBy = ",";
			
			while ((sCurrentLine = br.readLine()) != null) {
				String[] purge = sCurrentLine.split(textSplitBy);
				listOfItems.add(Integer.valueOf(purge[0]));
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println("No Input file, We are terminating the execution...");
			System.exit(1);
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				System.out.println(ex.getMessage());
				System.out.println("IO Connection close fail");
				System.exit(1);
			}
		}
	}
}
