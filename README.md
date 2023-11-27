# STAlign AAS directory Analyzer

This project uses [STAlign](https://pages.github.com/) application to calculate the distance between more than 2 proteins. STAlign provides a function that allow to calculate distance between only 2 files.
This application iterate that function to analyze a directory where there are more than 2 file AAS and compare them to each other. The result will be written in a csv file inside the directory containing aas files given in input.

# STAlignAasAnalyzer.jar Run Configuration Example

* java -jar STAlignAasAnalyzer.jar -a path/to/aas/files/directory/ -o path/to/StalignJarDirectory/

This command generate StalignDistance.csv , this file containes the calculated distances between all the aas files in the given directory with -a option, using STAlign.jar located in the given directory with -o option.

