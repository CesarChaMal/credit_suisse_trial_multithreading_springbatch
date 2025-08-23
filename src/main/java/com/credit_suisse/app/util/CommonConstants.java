package com.credit_suisse.app.util;

import org.springframework.stereotype.Service;

@Service("commonConstants")
public class CommonConstants {
    public static final String INSTRUMENT1 = "INSTRUMENT1";
    public static final String INSTRUMENT2 = "INSTRUMENT2";
    public static final String INSTRUMENT3 = "INSTRUMENT3";
    public static final String NEW_INSTRUMENT = "NEW_INSTRUMENT";
    public static Integer INSTRUMENTS_COUNT = 10;
    public static Integer NEWST = 10;
	public static boolean MANAGER_ON = false;
	public static boolean WORKER_ON = false;
	public static long REFRESH_MILLIS = 5000;
	public static long SLEEP_MILLIS = 1000;
	public static int THREAD_POOL_SIZE = 10;
	public static int MAX_THREADS = 2;
	// Use classpath-relative name for resources instead of file-system path
	public static String INPUT_FILE = "input.txt";
	public static String WORKER_PROFILE = "Instrument-1";
	public static String LOG = "logs/log";
	public static int MODIFIER_MIN = 1;
	public static int MODIFIER_MAX = 10;
	public static boolean MODIFIER_DOUBLE = true;
	public static boolean MODIFIERS = true;
}
