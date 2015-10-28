package com.melexis.rsa;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;


import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;


/**
 * Created by rsa on 30.09.15.
 */
public class Main implements BundleActivator {

    public void start (BundleContext context) throws Exception {
        System.out.println("Hello world");
    }

    public void stop(BundleContext context) throws Exception {
         System.out.println("Goodbye World");
    }


}

