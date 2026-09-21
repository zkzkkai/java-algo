# java-algo

我的 Java 练习与算法刷题记录。从大二上开始，目标是大三下暑期实习投递。

这里放两类东西：

1. **`practice/`** —— Java 语言本身的练习。语法、面向对象、集合、文件 IO，按学期推进。
2. **`leetcode/`** —— 算法刷题。每道题一个目录，代码 + 思路笔记。

## 当前进度

| 项 | 状态 |
|---|---|
| 开始时间 | 2026 年 9 月（大二上） |
| Java 语言进度 | 语法入手，目标学期末能独立写控制台程序 |
| 算法进度 | 已刷 0 题，目标学期末累计 80–100 题 |
| 主力题型 | 数组、字符串、哈希表 |
| 实习投递窗口 | 2028 年 3 月（大三下） |

## 目标

- **大二上（2026.9–2027.1）**：Java 语法 → 面向对象 → 集合 → 文件 IO；SQL 补上多表 JOIN；算法累计 80–100 题
- **寒假**：集合源码、并发基础、JDBC；完成第一个控制台项目
- **大二下**：Spring Boot + MyBatis-Plus，项目部署到 Linux 服务器；算法累计 250 题
- **大二暑假**：Redis，把项目做完整，写简历初版；算法累计 300+
- **大三上**：面试知识补强 + 第二个项目（并发 / 缓存 / 消息队列）
- **大三下（2028.3）**：暑期实习投递

## 目录结构

```text
java-algo/
├── leetcode/                     算法刷题，每题一个目录
│   ├── _template/                新题模板，复制它开始新题
│   │   ├── Solution.java
│   │   └── notes.md
│   └── 001-two-sum/              题号-题名，三位数字补零
│       ├── Solution.java
│       └── notes.md
├── practice/                     Java 语言练习
│   └── basics/                   语法基础
├── tools/
│   └── template/                 可复用的代码模板
└── run.ps1                       一键编译运行脚本
```

每道题一个目录，命名规则：**三位题号 + 连字符 + 题名小写**，例如 `001-two-sum`、`020-valid-parentheses`。
补零的好处是文件列表按题号自然排序，一眼看出刷了多少、漏了多少。

### 单题目录里放什么

| 文件 | 作用 |
|---|---|
| `Solution.java` | 能独立跑通的解答，包含 `main` 方法方便本地测试 |
| `notes.md` | 思路、复杂度、关键点、易错点 |

`notes.md` 不是形式主义。三个月后你会忘记这道题考什么，面试前复习靠的就是它；而且写笔记会逼你把「为什么这么写」想清楚，比多刷十道题值钱。

## 怎么开始一道新题

1. 复制 `leetcode/_template/` 整个目录
2. 重命名为 `题号-题名`，例如 `001-two-sum`
3. 写 `Solution.java`
4. 在 `notes.md` 里补思路和复杂度
5. 把这道题登记到下面的进度表

## 进度表

### 数组

| # | 题目 | 难度 | 完成 |
|---|------|------|------|
| 001 | Two Sum | Easy | [ ] |

### 字符串

| # | 题目 | 难度 | 完成 |
|---|------|------|------|
| 020 | Valid Parentheses | Easy | [ ] |

### 哈希表

| # | 题目 | 难度 | 完成 |
|---|------|------|------|
| 049 | Group Anagrams | Medium | [ ] |

### 链表

| # | 题目 | 难度 | 完成 |
|---|------|------|------|

## 本地怎么跑

### 推荐：用脚本一键跑

```powershell
# 在仓库根目录执行
powershell -ExecutionPolicy Bypass -File .\run.ps1 leetcode\001-two-sum\Solution.java
powershell -ExecutionPolicy Bypass -File .\run.ps1 practice\basics\Basics.java
```

脚本会自动处理编码、编译、运行，并清理 `.class` 文件。

### 手动跑

```powershell
cd leetcode\001-two-sum
javac Solution.java
java Solution
```

需要 JDK 17 或更高。检查版本：

```powershell
java -version
```

## 两个必踩的坑（已经处理好了，但你得知道）

### 1. 中文输出乱码

Windows 控制台默认代码页是 GBK，Java 也就跟着输出 GBK，但很多终端按 UTF-8 解码，
于是 `System.out.println("最大值")` 打印出来是乱码。

**根因不在你的文件，也不在 javac** —— 所以别去改文件编码，改了也没用。

解决：先把控制台切成 UTF-8，再让 JVM 用 UTF-8 输出。

```powershell
chcp 65001
java -Dstdout.encoding=UTF-8 Solution
```

`-Dstdout.encoding=UTF-8` 这个参数**在 PowerShell 里必须带引号**，否则会被拆成两个参数，
Java 会报 `ClassNotFoundException: /encoding=UTF-8`。`run.ps1` 里已经处理了。

最省事的办法是直接用 IntelliJ IDEA 运行，它不存在这个问题。

### 2. 执行策略禁止运行脚本

如果你直接 `.\run.ps1` 报 `running scripts is disabled on this system`，
说明 PowerShell 执行策略是 `Restricted`。两个选择：

```powershell
# 临时绕过，只影响这一次
powershell -ExecutionPolicy Bypass -File .\run.ps1 leetcode\001-two-sum\Solution.java

# 或者永久放开（只影响当前用户，推荐）
Set-ExecutionPolicy -Scope CurrentUser RemoteSigned
```

## 说明

Java 是主力语言，因为它是国内技术岗岗位量最大的一门；后续会在这个库里补充 Go 的解法作为加分项。
早期用 C++ 写过少量练习，已归到另一个仓库。
