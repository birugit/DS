package com.gap.fmr.purge;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

public class FMRErrorReport {

	String application_Name = "FMR";

	void prepareErrorReport(HashSet setOfStylesNotDeleted) {
		try {
			Iterator errorStylesIterator;
			// create Bufferedwriter instance with a FileWriter
			// the flag set to 'true' tells it to append a file if file exists
			BufferedWriter out = new BufferedWriter(
					new FileWriter("/home/apps/fmr/bin/PURP.STYLE.PURGE.ERR.FMR", true));

			errorStylesIterator = setOfStylesNotDeleted.iterator();
			while (errorStylesIterator.hasNext()) {
				// write the text string to the file
				out.write(application_Name + "," + errorStylesIterator.next());

				// write a `newline` to the file
				out.newLine();
			}

			// close the file
			out.close();
		}

		// handle exceptions
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void prepareErrorReport() {
		try {
			// create Bufferedwriter instance with a FileWriter
			// the flag set to 'true' tells it to append a file if file exists
			BufferedWriter out = new BufferedWriter(
					new FileWriter("/home/apps/fmr/bin/PURP.STYLE.PURGE.ERR.FMR", true));

			out.close();
		}

		// handle exceptions
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}
