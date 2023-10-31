# STAlign AAS directory Analyzer

This project uses [STAlign](https://pages.github.com/) application to calculate the distance between more than 2 proteins. STAlign provides a function that allow to calculate disrtance between only 2 files.
This application iterate that function to analyze a directory where there are more than 2 file AAS and compare them to each other. The result will be written in a csv file inside the aas directory file given in input.

# STAlignAasAnalyzer.jar Run Configuration Example

* java -jar STAlignAasAnalyzer.jar -a path/to/aas/files/directory -o path/to/Stalign.jar

