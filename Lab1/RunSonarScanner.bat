
"C:\Program Files (x86)\Microsoft Visual Studio\2019\Enterprise\Common7\IDE\CommonExtensions\Microsoft\TestWindow\vstest.console.exe" /Settings:CodeCoverage.runsettings /EnableCodeCoverage "GreenTourismManagementTest\bin\Debug\GreenTourismManagement.Test.dll"
set /p coverage_path=Enter path to *.coverage file: 
@echo off
set CUR_YYYY=%date:~6,4%
set CUR_MM=%date:~3,2%
set CUR_DD=%date:~0,2%
set CUR_HH=%time:~0,2%
if %CUR_HH% lss 10 (set CUR_HH=0%time:~1,1%)

set CUR_NN=%time:~3,2%
set CUR_SS=%time:~6,2%

set SUBFILENAME=%CUR_YYYY%-%CUR_MM%-%CUR_DD%_%CUR_HH%-%CUR_NN%-%CUR_SS%

"C:\Program Files (x86)\Microsoft Visual Studio\2019\Enterprise\Team Tools\Dynamic Code Coverage Tools\CodeCoverage.exe" analyze /output:"C:\vscoveragexml\%SUBFILENAME%.xml" "%coverage_path%"
@echo on

SonarScanner.MSBuild.exe begin /k:"green-tourism-management" /v:1 /d:sonar.msbuild.testProjectPattern="[^\\]*Test[^\\]*$" /d:sonar.cs.vscoveragexml.reportsPaths="C:\vscoveragexml\%SUBFILENAME%.xml"
"C:\Program Files (x86)\Microsoft Visual Studio\2017\Community\MSBuild\15.0\Bin\MSBuild.exe" /t:Rebuild
SonarScanner.MSBuild.exe end