1. The framework is written upon Cucumber selenium-java with maven as an build management tool.
2. Both the API and UI are written in the same feature file "src\test\resources\Features\TrusTraceTestcases.feature"
3. Both Scenario Outline has the Step definition file "src\test\java\StepDefinition\TrusTraceTestcaseSteps.java"
4. For UI Testcases it configured to run in Three browsers chrome, Edge and Firefox and drivers are in "src\test\resources\drivers". Kindly check compatability of the versions if you are facing any issues in launching the browser.
5. The web objects are captured in "src\test\java\pages"
6. Reports could be found in:
   HTML - "target\HtmlReports\"
   JSON - "target\JSONReports\"
7. The Runner class is available in "src\test\java\testRunner\testRunner.java"
 