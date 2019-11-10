
"C:\Program Files (x86)\Microsoft Visual Studio\2019\Enterprise\Common7\IDE\CommonExtensions\Microsoft\TestWindow\vstest.console.exe" /Settings:CodeCoverage.runsettings /EnableCodeCoverage "GreenTourismManagementTest\bin\Debug\GreenTourismManagement.Test.dll"
set /p coverage_path=Enter path to *.coverage file: 
"C:\Program Files (x86)\Microsoft Visual Studio\2019\Enterprise\Team Tools\Dynamic Code Coverage Tools\CodeCoverage.exe" analyze /output:"C:\VSCoverageXML\coverage.xml" "%coverage_path%"

SonarScanner.MSBuild.exe begin /k:"GreenTourism" /v:1 /d:sonar.msbuild.testProjectPattern="[^\\]*Test[^\\]*$" /d:sonar.cs.vscoveragexml.reportsPaths="C:\VSCoverageXML\coverage.xml"
"C:\Program Files (x86)\Microsoft Visual Studio\2019\Community\MSBuild\Current\Bin\MSBuild.exe" /t:Rebuild
SonarScanner.MSBuild.exe end