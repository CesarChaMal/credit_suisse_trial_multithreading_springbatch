package com.credit_suisse.app;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

import org.junit.Test;

import com.credit_suisse.app.core.CalculatorEngine;
import com.credit_suisse.app.core.module.AverageModule;
import com.credit_suisse.app.model.Instrument;
import com.credit_suisse.app.util.InstrumentUtil;
import com.credit_suisse.app.util.PartitioningSpliterator;

public class ProcessingFileTest {

	private static final Map<String, List<Instrument>> INSTRUMENTS = new TreeMap<>();

	private static final String INSTRUMENT = "INSTRUMENT1";

	@Test
	public void averageProcessingTest() {

		INSTRUMENTS.put(INSTRUMENT, new ArrayList<Instrument>());

		// Load input file from classpath
		InputStream is = ProcessingFileTest.class.getClassLoader().getResourceAsStream("input.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line;
		AverageModule averageModule = new AverageModule();

		try {
			while ((line = br.readLine()) != null) {
				Instrument instrument = InstrumentUtil.defineOf(line);
				if (instrument != null) {
					if (INSTRUMENT.equals(instrument.getName())) {
						INSTRUMENTS.get(INSTRUMENT).add(instrument);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		averageModule.addInstruments(INSTRUMENTS.get(INSTRUMENT));
		assertEquals(4, INSTRUMENTS.get(INSTRUMENT).size());
	}
	
	@Test
	public void averageProcessingTesInChunks() {
		INSTRUMENTS.put(INSTRUMENT, new ArrayList<Instrument>());
		AverageModule averageModule = new AverageModule();

		// Load large_input.txt from classpath and process as a stream of lines
		InputStream is = ProcessingFileTest.class.getClassLoader().getResourceAsStream("large_input.txt");
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		     Stream<String> stream = reader.lines()) {

			Stream<List<String>> partitioned = PartitioningSpliterator.partition(stream, 200, 1);
			partitioned.forEach(chunk -> {
				chunk.stream()
					.filter(instrument -> InstrumentUtil.isWorkDay(InstrumentUtil.getDate(instrument.split(",")[1])))
					.forEach(instrument -> CalculatorEngine.add(InstrumentUtil.defineOf(instrument)));
				System.out.println(chunk.size());
				chunk.forEach(System.out::println);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
