LangRank is a Big Data Analytic project that ranks programming language based on type of user and his interest and help the user take a informed decision of what's the best language he should choose. 
Like a student is interested in a language which is more popular in the job market, 
a startup is interested in a salable technology, 
an instructor is interested in a language which lacks online learning resources.
To bring out aspects like relatively new, trending, stable, growing language from the huge amount of data LangRank uses Custom designed matrix and calculate the ranks from 
numbers like questions asked, questions answered, questions not yet answered, questions answered over longer period of time, number of projects.  
LangRank uses StackOverflow and Github as the primary data sets to make the rankings.

Use the slidedrop.html in presentations folder file for and broad overview and presentation of LangRank projects working.
Use LangRank Findings Paper.docx for a indepth view of Problem, Solution and Results behind the LangRank project.  
For Running the project from scratch and recalculating the data. 
> Download and extract GitHub data sourse and Stackoverflow data sourse.
> Use python files and use Hadoop Streaming in folder GHClean for initial cleaning of data.
> Use python files in PostFilter to further strip down data to required fields and formats.
> Use python files in TagCount for generating numeric figures from raw data. 
> Use java files in Visualization for applying LangRank Matrix and computing CSV files for charts.
>> tagmatch.java uses 3 input files – Languagelist.txt , stackinput.csv, git.txt
>> tagmatch.java gives 2 output files – Stackoutput.txt and finaloutput.csv
>> visualmatrix.java uses 1 input file – finaloutput.csv
>> visualmatrix.java gives 4 output files – visualstudent.csv, visualcompany.csv, visualtraining.csv and visualstartup.csv 
>> addmissinglang.java uses 1 input file – any visual file from the last pass.
>> addmissinglang.java gives 1 output file – <chartready>.csv
> Place calcutaled file in Charts/CSV and host the website to view results. 