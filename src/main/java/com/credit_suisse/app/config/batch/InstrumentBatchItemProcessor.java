package com.credit_suisse.app.config.batch;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;

import com.credit_suisse.app.core.CalculatorEngine;
import com.credit_suisse.app.model.Instrument;
import com.credit_suisse.app.model.InstrumentFactory;
import com.credit_suisse.app.util.InstrumentUtil;

public class InstrumentBatchItemProcessor implements ItemProcessor<InstrumentBatch, InstrumentBatch> {

	public InstrumentBatch process(final InstrumentBatch instrumentBatch) throws Exception {
		System.out.println(instrumentBatch.toString() + " in memory");

		Instrument instrument = InstrumentFactory.createInstrument(instrumentBatch.getName());
		instrument.setDate(InstrumentUtil.getDate(instrumentBatch.getIdate()));
		instrument.setPrice(instrumentBatch.getPrice());
		
		if (InstrumentUtil.isWorkDay(InstrumentUtil.getDate(instrumentBatch.getIdate()))) {
			CalculatorEngine.add(instrument);			
		}
        return instrumentBatch;
    }

}
