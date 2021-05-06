package com.gap.fmr.purge;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FMRSummaryReport {
	String application_Name = "FMR";


	public String getApplication_Name() {
		return application_Name;
	}

	public void setApplication_Name(String application_Name) {
		this.application_Name = application_Name;
	}

	void prepareSummaryReport(int stylesReceived, int stylesProcessed) {
		try {
			// create Bufferedwriter instance with a FileWriter
			// the flag set to 'true' tells it to append a file if file exists
			BufferedWriter out = new BufferedWriter(
					new FileWriter("/home/apps/fmr/bin/PURP.STYLE.PURGE.LOG.FMR", true));

			// write the text string to the file
			out.write(application_Name + "," + stylesReceived + "," + stylesProcessed);

			// write a `newline` to the file
			out.newLine();

			// close the file
			out.close();
		}

		// handle exceptions
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	
}
