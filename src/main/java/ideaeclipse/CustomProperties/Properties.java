package ideaeclipse.CustomProperties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Properties {
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());
    private final String configName = "src/config.properties";
    private File file;
    private java.util.Properties prop;
    private String[] properties;

    public Properties(final String[] properties) {
        file = new File(configName);
        this.properties = properties;
    }

    public void start() throws IOException {
        prop = new java.util.Properties();
        if (!file.exists()) {
            LOGGER.info("Config file DOES NOT exist therefore creating file");
            if (!configure()) {
                for (String s : properties) {
                    prop.put(s, "");
                }
            }
            prop.store(new FileOutputStream(configName), null);
            if (file.createNewFile()) {
                LOGGER.warning("Configuration file could not be created. Try running program with administrator access");
            } else {
                LOGGER.info("Configuration Loaded");
            }
        } else {
            LOGGER.info("Config file exist");
            prop.load(new FileInputStream(configName));
            List<String> unsetConfig = new ArrayList<>();
            for (String s : properties) {
                if (prop.getProperty(s) == null) {
                    unsetConfig.add(s);
                }
            }
            if (unsetConfig.size() > 0)
                unset(unsetConfig);
            prop.store(new FileOutputStream(configName), null);
        }
    }

    private boolean configure() {
        Scanner sc = new Scanner(System.in);
        System.out.println("This is your first time running the program do you want to configure over command line or configure in the properties file? (Y/N)");
        if (answer(sc.nextLine())) {
            for (String s : properties) {
                System.out.println("Enter property for: " + s);
                prop.put(s, sc.nextLine());
            }
            sc.close();
            return true;
        }
        sc.close();
        return false;
    }

    private void unset(List<String> unset) {
        Scanner sc = new Scanner(System.in);
        System.out.println("You're config.properties file is out of date would you like to update it through the console? (Y/N)");
        if (answer(sc.nextLine())) {
            for (String s : unset) {
                System.out.println("Enter property for: " + s);
                prop.put(s, sc.nextLine());
            }
        } else {
            for (String s : unset) {
                prop.put(s, "");
            }
            System.out.println("Please set values manually in file or restart program to enter them");
            System.exit(1);
        }
    }

    private boolean answer(String s) {
        return s.toLowerCase().equals("y");
    }

    public String getProperty(String p) {
        return prop.getProperty(p);
    }
}
