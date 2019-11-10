@echo off
echo Start Tests
@rd /s /q "D:\Project_C#\PanCake\PanCake.UnitTests\TestResults\CoverageBinary\"
@md "D:\Project_C#\PanCake\PanCake.UnitTests\TestResults\CoverageBinary\"
@del /q /f "D:\Project_C#\PanCake\PanCake.UnitTests\TestResults"
cmd /c ""C:\Program Files (x86)\Microsoft Visual Studio\2019\Enterprise\Common7\IDE\CommonExtensions\Microsoft\TestWindow\vstest.console.exe" /EnableCodeCoverage /ResultsDirectory:"D:\Project_C#\PanCake\PanCake.UnitTests\TestResults\CoverageBinary" "D:\Project_C#\PanCake\PanCake.UnitTests\bin\Debug\netcoreapp2.1\PanCake.UnitTests.dll""
SetLocal EnableExtensions
for /F "delims=" %%a in ('where /R D:\Project_C#\PanCake\PanCake.UnitTests\TestResults\CoverageBinary\ "*.coverage"') do set file=%%a
echo %file%
cmd /c ""C:\Program Files (x86)\Microsoft Visual Studio\2019\Enterprise\Team Tools\Dynamic Code Coverage Tools\CodeCoverage.exe" analyze /output:"D:\Project_C#\PanCake\PanCake.UnitTests\TestResults\result.coveragexml" "%file%""
cmd /c "dotnet C:\Users\super\Desktop\sonarqube-7.9.1\sonar-scanner-msbuild-4.7.1.2311-netcoreapp2.0\SonarScanner.MSBuild.dll begin /k:"panCake" /v:3 /d:sonar.msbuild.testProjectPattern="[^\\]*Test[^\\]*$" /d:sonar.cs.vscoveragexml.reportsPaths="D:\Project_C#\PanCake\PanCake.UnitTests\TestResults\result.coveragexml""
cmd /c "dotnet build D:\Project_C#\PanCake\PanCake.sln"
dotnet C:\Users\super\Desktop\sonarqube-7.9.1\sonar-scanner-msbuild-4.7.1.2311-netcoreapp2.0\SonarScanner.MSBuild.dll end
@pause


