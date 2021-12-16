package com.automation.develop.utilities;

import java.io.IOException;

public interface RulesForBaseClass {

    /*----------------------------------------------------------------------------------------------------
     * Define the standardize method in the interface which will be implemented in the BaseClass
     * This will reduce the redundancy and duplicate methods being created.
     * -----------------------------------------------------------------------------------------------------*/

    // Configuration methods
    public void initializePropertiesFile() throws IOException;
    public void initializeBrowserUsingDriverManager(String browser,boolean headless);


    // Featured based methods



}
