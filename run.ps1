# run.ps1 —— 编译并运行一个 Java 文件，自动处理中文编码
#
# 用法：
#   .\run.ps1 leetcode\001-two-sum\Solution.java
#   .\run.ps1 practice\basics\Basics.java
#
# 如果报「running scripts is disabled on this system」，用这个方式运行：
#   powershell -ExecutionPolicy Bypass -File .\run.ps1 leetcode\001-two-sum\Solution.java
#
# 它做的事情：
#   1. 把控制台代码页切成 UTF-8（chcp 65001）—— 这是中文不乱码的关键
#   2. 编译源文件
#   3. 运行主类，并强制 JVM 以 UTF-8 写标准输出
#   4. 清理生成的 .class 文件（仓库里不该提交编译产物）

param(
    [Parameter(Mandatory = $true)]
    [string]$Source
)

$ErrorActionPreference = "Stop"

# 控制台代码页切成 UTF-8。JS/Java 是否乱码主要取决于这个。
cmd /c "chcp 65001 >nul" 2>$null
[Console]::OutputEncoding = [System.Text.Encoding]::UTF8

if (-not (Test-Path $Source)) {
    Write-Host "File not found: $Source" -ForegroundColor Red
    exit 1
}

$file = Get-Item $Source

if ($file.Extension -ne ".java") {
    Write-Host "Only .java files are supported, got: $($file.Name)" -ForegroundColor Red
    exit 1
}

$dir = $file.DirectoryName
$className = [System.IO.Path]::GetFileNameWithoutExtension($file.Name)

Write-Host "Compiling $($file.Name) ..." -ForegroundColor Cyan
Push-Location $dir
try {
    javac $file.Name
    if ($LASTEXITCODE -ne 0) {
        Write-Host "Compile failed." -ForegroundColor Red
        exit 1
    }

    Write-Host "Running $className" -ForegroundColor Cyan
    Write-Host ("-" * 40)
    # 注意：-D 参数必须加引号，否则 PowerShell 会把它拆成两个参数传给 java
    java "-Dstdout.encoding=UTF-8" $className
    Write-Host ("-" * 40)
}
finally {
    Pop-Location
    # 清理编译产物，保持工作区干净
    Get-ChildItem -Path $dir -Filter "*.class" -ErrorAction SilentlyContinue |
        Remove-Item -Force -ErrorAction SilentlyContinue
}
