/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */


package org.apache.tools.ant.taskdefs;

import org.apache.tools.ant.AntAssert;
import org.apache.tools.ant.BuildFileRule;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.rmic.RmicAdapterFactory;
import org.apache.tools.ant.taskdefs.rmic.DefaultRmicAdapter;
import org.apache.tools.ant.util.JavaEnvUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;

/**
 * Date: 04-Aug-2004
 * Time: 22:15:46
 */
public class RmicAdvancedTest {

    private static final String TASKDEFS_DIR = "src/etc/testcases/taskdefs/rmic/";

    @Rule
    public BuildFileRule buildRule = new BuildFileRule();

    @Rule
    public ExpectedException tried = ExpectedException.none();

    /**
     * The JUnit setup method
     */
    @Before
    public void setUp() throws Exception {
        buildRule.configureProject(TASKDEFS_DIR + "rmic.xml");
    }

    /**
     * verify that "default" binds us to the default compiler
     */
    @Test
    public void testDefault() throws Exception {
        buildRule.executeTarget("testDefault");
    }

    /**
     * verify that "default" binds us to the default compiler
     */
    @Test
    public void testDefaultDest() throws Exception {
        buildRule.executeTarget("testDefaultDest");
    }

    /**
     * verify that "" binds us to the default compiler
     */
    @Test
    public void testEmpty() throws Exception {
        buildRule.executeTarget("testEmpty");
    }

    /**
     * verify that "" binds us to the default compiler
     */
    @Test
    public void testEmptyDest() throws Exception {
        buildRule.executeTarget("testEmptyDest");
    }

    /**
     * test sun's rmic compiler
     */
    @Test
    public void testRmic() throws Exception {
        buildRule.executeTarget("testRmic");
    }

    /**
     * test sun's rmic compiler
     */
    @Test
    public void testRmicDest() throws Exception {
        buildRule.executeTarget("testRmicDest");
    }

    /**
     * test sun's rmic compiler strips
     * out -J arguments when not forking
     */
    @Test
    public void testRmicJArg() throws Exception {
        buildRule.executeTarget("testRmicJArg");
    }

    /**
     * test sun's rmic compiler strips
     * out -J arguments when not forking
     */
    @Test
    public void testRmicJArgDest() throws Exception {
        buildRule.executeTarget("testRmicJArgDest");
    }

    /**
     * A unit test for JUnit
     */
    @Test
    public void testKaffe() throws Exception {
        buildRule.executeTarget("testKaffe");
    }

    /**
     * A unit test for JUnit
     */
    @Test
    public void testKaffeDest() throws Exception {
        buildRule.executeTarget("testKaffeDest");
    }

    // WLrmic tests don't work
    /**
     * test weblogic
     */
    @Test
    @Ignore("WLRmic tests don't work")
    public void XtestWlrmic() throws Exception {
        buildRule.executeTarget("testWlrmic");
    }

    /**
     *  test weblogic's stripping of -J args
     */
    @Test
    @Ignore("WLRmic tests don't work")
    public void XtestWlrmicJArg() throws Exception {
        buildRule.executeTarget("testWlrmicJArg");
    }

    /**
     * test the forking compiler
     */
    @Test
    public void testForking() throws Exception {
        buildRule.executeTarget("testForking");
    }

    /**
     * test the forking compiler
     */
    @Test
    public void testForkingAntClasspath() throws Exception {
        buildRule.executeTarget("testForkingAntClasspath");
    }

    /**
     * test the forking compiler
     */
    @Test
    public void testForkingAntClasspathDest() throws Exception {
        buildRule.executeTarget("testForkingAntClasspathDest");
    }

    /**
     * test the forking compiler
     */
    @Test
    public void testAntClasspath() throws Exception {
        buildRule.executeTarget("testAntClasspath");
    }

    /**
     * test the forking compiler
     */
    @Test
    public void testAntClasspathDest() throws Exception {
        buildRule.executeTarget("testAntClasspathDest");
    }

    /**
     * A unit test for JUnit
     */
    @Test
    public void testBadName() throws Exception {
        tried.expect(BuildException.class);
        tried.expectMessage(RmicAdapterFactory.ERROR_UNKNOWN_COMPILER);
        buildRule.executeTarget("testBadName");
    }

    /**
     * load an adapter by name
     */
    @Test
    public void testExplicitClass() throws Exception {
        buildRule.executeTarget("testExplicitClass");
    }

    /**
     * A unit test for JUnit
     */
    @Test
    public void testWrongClass() throws Exception {
        tried.expect(BuildException.class);
        tried.expectMessage(RmicAdapterFactory.ERROR_NOT_RMIC_ADAPTER);
        buildRule.executeTarget("testWrongClass");
    }

    /**
     * A unit test for JUnit
     */
    @Test
    public void testDefaultBadClass() throws Exception {
        tried.expect(BuildException.class);
        tried.expectMessage(Rmic.ERROR_RMIC_FAILED);
        try {
            buildRule.executeTarget("testDefaultBadClass");
        } finally {
            // don't look for much text here as it is vendor and version dependent
            AntAssert.assertContains("unimplemented.class", buildRule.getLog());
        }
    }

    /**
     * A unit test for JUnit
     */
    @Test
    public void testMagicProperty() throws Exception {
        tried.expect(BuildException.class);
        tried.expectMessage(RmicAdapterFactory.ERROR_UNKNOWN_COMPILER);
        buildRule.executeTarget("testMagicProperty");
    }

    /**
     * A unit test for JUnit
     */
    @Test
    public void testMagicPropertyOverridesEmptyString() throws Exception {
        tried.expect(BuildException.class);
        tried.expectMessage(RmicAdapterFactory.ERROR_UNKNOWN_COMPILER);
        buildRule.executeTarget("testMagicPropertyOverridesEmptyString");
    }

    @Test
    public void testMagicPropertyIsEmptyString() throws Exception {
        buildRule.executeTarget("testMagicPropertyIsEmptyString");
    }

    @Test
    @Ignore("Previously named to prevent execution")
    public void NotestFailingAdapter() throws Exception {
        tried.expect(BuildException.class);
        tried.expectMessage(Rmic.ERROR_RMIC_FAILED);
        buildRule.executeTarget("testFailingAdapter");
    }

    /**
     * test that version 1.1 stubs are good
     * @throws Exception if something goes wrong
     */
    @Test
    public void testVersion11() throws Exception {
        buildRule.executeTarget("testVersion11");
    }

    /**
     * test that version 1.1 stubs are good
     * @throws Exception if something goes wrong
     */
    @Test
    public void testVersion11Dest() throws Exception {
        buildRule.executeTarget("testVersion11Dest");
    }

    /**
     * test that version 1.2 stubs are good
     *
     * @throws Exception if something goes wrong
     */
    @Test
    public void testVersion12() throws Exception {
        buildRule.executeTarget("testVersion12");
    }

    /**
     * test that version 1.2 stubs are good
     *
     * @throws Exception if something goes wrong
     */
    @Test
    public void testVersion12Dest() throws Exception {
        buildRule.executeTarget("testVersion12Dest");
    }

    /**
     * test that version compat stubs are good
     *
     * @throws Exception if something goes wrong
     */
    @Test
    public void testVersionCompat() throws Exception {
        buildRule.executeTarget("testVersionCompat");
    }

    /**
     * test that version compat stubs are good
     *
     * @throws Exception if something goes wrong
     */
    @Test
    public void testVersionCompatDest() throws Exception {
        buildRule.executeTarget("testVersionCompatDest");
    }

    /**
     * test that passes -Xnew to sun's rmic running in a different VM.
     *
     * @throws Exception if something goes wrong
     */
    @Test
    public void testXnewForked() throws Exception {
        assumeFalse("Current system is Java 9 or newer", JavaEnvUtils.isAtLeastJavaVersion(JavaEnvUtils.JAVA_9));
        buildRule.executeTarget("testXnewForked");
    }

    @Test
    public void testXnewForkedJava9plus() throws Exception {
        assumeTrue("Current system is Java 8 or older", JavaEnvUtils.isAtLeastJavaVersion(JavaEnvUtils.JAVA_9));
        tried.expect(BuildException.class);
        tried.expectMessage("JDK9 has removed support for -Xnew");
        buildRule.executeTarget("testXnewForked");
    }

    /**
     * test that passes -Xnew to sun's rmic running in a different VM.
     *
     * @throws Exception if something goes wrong
     */
    @Test
    public void testXnewForkedDest() throws Exception {
        assumeFalse("Current system is Java 9 or newer", JavaEnvUtils.isAtLeastJavaVersion(JavaEnvUtils.JAVA_9));
        buildRule.executeTarget("testXnewForkedDest");
    }

    @Test
    public void testXnewForkedDestJava9plus() throws Exception {
        assumeTrue("Current system is Java 8 or older", JavaEnvUtils.isAtLeastJavaVersion(JavaEnvUtils.JAVA_9));
        tried.expect(BuildException.class);
        tried.expectMessage("JDK9 has removed support for -Xnew");
        buildRule.executeTarget("testXnewForkedDest");
    }

    /**
     * test that runs the new xnew compiler adapter.
     *
     * @throws Exception if something goes wrong
     */
    @Test
    public void testXnewCompiler() throws Exception {
        assumeFalse("Current system is Java 9 or newer", JavaEnvUtils.isAtLeastJavaVersion(JavaEnvUtils.JAVA_9));
        buildRule.executeTarget("testXnewCompiler");
    }

    @Test
    public void testXnewCompilerJava9plus() throws Exception {
        assumeTrue("Current system is Java 8 or older", JavaEnvUtils.isAtLeastJavaVersion(JavaEnvUtils.JAVA_9));
        tried.expect(BuildException.class);
        tried.expectMessage("JDK9 has removed support for -Xnew");
        buildRule.executeTarget("testXnewCompiler");
    }

    /**
     * test that runs the new xnew compiler adapter.
     *
     * @throws Exception if something goes wrong
     */
    @Test
    public void testXnewCompilerDest() throws Exception {
        assumeFalse("Current system is Java 9 or newer", JavaEnvUtils.isAtLeastJavaVersion(JavaEnvUtils.JAVA_9));
        buildRule.executeTarget("testXnewCompilerDest");
    }

    @Test
    public void testXnewCompilerDestJava9plus() throws Exception {
        assumeTrue("Current system is Java 8 or older", JavaEnvUtils.isAtLeastJavaVersion(JavaEnvUtils.JAVA_9));
        tried.expect(BuildException.class);
        tried.expectMessage("JDK9 has removed support for -Xnew");
        buildRule.executeTarget("testXnewCompilerDest");
    }

    /**
     * test that verifies that IDL compiles.
     *
     * @throws Exception if something goes wrong
     */
    @Test
    public void testIDL() throws Exception {
        assumeFalse("Current system is Java 11 or newer", JavaEnvUtils.isAtLeastJavaVersion("11"));
        buildRule.executeTarget("testIDL");
    }

    @Test
    public void testIDLJava11plus() throws Exception {
        assumeTrue("Current system is Java 10 or older", JavaEnvUtils.isAtLeastJavaVersion("11"));
        tried.expect(BuildException.class);
        tried.expectMessage("this rmic implementation doesn't support the -idl switch");
        buildRule.executeTarget("testIDL");
    }

    /**
     * test that verifies that IDL compiles.
     *
     * @throws Exception if something goes wrong
     */
    @Test
    public void testIDLDest() throws Exception {
        assumeFalse("Current system is Java 11 or newer", JavaEnvUtils.isAtLeastJavaVersion("11"));
        buildRule.executeTarget("testIDLDest");
    }

    @Test
    public void testIDLDestJava11plus() throws Exception {
        assumeTrue("Current system is Java 10 or older", JavaEnvUtils.isAtLeastJavaVersion("11"));
        tried.expect(BuildException.class);
        tried.expectMessage("this rmic implementation doesn't support the -idl switch");
        buildRule.executeTarget("testIDL");
   }

    /**
     * test that verifies that IIOP compiles.
     *
     * @throws Exception if something goes wrong
     */
    @Test
    public void testIIOP() throws Exception {
        assumeFalse("Current system is Java 11 or newer", JavaEnvUtils.isAtLeastJavaVersion("11"));
        buildRule.executeTarget("testIIOP");
    }

    @Test
    public void testIIOPJava11plus() throws Exception {
        assumeTrue("Current system is Java 10 or older", JavaEnvUtils.isAtLeastJavaVersion("11"));
        tried.expect(BuildException.class);
        tried.expectMessage("this rmic implementation doesn't support the -iiop switch");
        buildRule.executeTarget("testIIOP");
    }

    /**
     * test that verifies that IIOP compiles.
     *
     * @throws Exception if something goes wrong
     */
    @Test
    public void testIIOPDest() throws Exception {
        assumeFalse("Current system is Java 11 or newer", JavaEnvUtils.isAtLeastJavaVersion("11"));
        buildRule.executeTarget("testIIOPDest");
    }

    @Test
    public void testIIOPDestJava11plus() throws Exception {
        assumeTrue("Current system is Java 10 or older", JavaEnvUtils.isAtLeastJavaVersion("11"));
        tried.expect(BuildException.class);
        tried.expectMessage("this rmic implementation doesn't support the -iiop switch");
        buildRule.executeTarget("testIIOP");
    }

    /**
     * this little bunny verifies that we can load stuff, and that
     * a failure to execute is turned into a fault
     */
    public static class FailingRmicAdapter extends DefaultRmicAdapter {
        public static final String LOG_MESSAGE = "hello from FailingRmicAdapter";

        /**
         * Executes the task.
         *
         * @return false -always
         */
        public boolean execute() throws BuildException {
            getRmic().log(LOG_MESSAGE);
            return false;
        }
    }
}

