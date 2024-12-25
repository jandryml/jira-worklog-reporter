# Jira Worklog Reporter Documentation

## Introduction

This document provides detailed information about the Jira Worklog Reporter project, including setup instructions, usage, and examples.

## Setup Instructions

1. **Clone the repository:**
   ```sh
   git clone https://github.com/jandryml/jira-worklog-reporter.git
   cd jira-worklog-reporter
   ```

2. **Configure the project:**
   1. Copy `config-template.properties` to `config.properties`.
   2. Fill in the correct values in the `config.properties` file.

3. **Build the project:**
   ```sh
   ./gradlew build
   ```

## Usage

1. **Run the application:**
   ```sh
   ./gradlew run
   ```

2. **Provide the input CSV file:**
   - Ensure the input CSV file is named `input.csv` and placed in the root directory of the project.
   - The CSV file should have the following format:
     ```
     Date,Task,Project,Client,Description,Time (h),Time (decimal),Amount (USD)
     10/31/2024,ROSS-3345,ROSSMAN,(Without client),ROSS-3345 - cr,00:15:00,0.25,
     ```

3. **Follow the prompts:**
   - The application will prompt you to confirm if you want to continue after parsing the CSV file.
   - Type `y` to continue or `n` to exit.

## Examples

### Example CSV File

```
Date,Task,Project,Client,Description,Time (h),Time (decimal),Amount (USD)
10/31/2024,ROSS-3345,ROSSMAN,(Without client),ROSS-3345 - cr,00:15:00,0.25,
11/01/2024,BTIP-1285,BTIP,(Without client),BTIP-1285 - meeting,01:00:00,1.0,
```

### Example Output

```
Starting application
Wogklog: Worklog(issueKey=ROSS-3345, started=2024-10-31T12:00:00.000+0000, timeSpent=00:15:00h, comment=ROSS-3345 - cr)
Wogklog: Worklog(issueKey=BTIP-1285, started=2024-11-01T12:00:00.000+0000, timeSpent=01:00:00h, comment=BTIP-1285 - meeting)
Total time: 1.25
--- Want to continue? ---
y/n: y
Adding worklog: Worklog(issueKey=ROSS-3345, started=2024-10-31T12:00:00.000+0000, timeSpent=00:15:00h, comment=ROSS-3345 - cr)
200 OK
Adding worklog: Worklog(issueKey=BTIP-1285, started=2024-11-01T12:00:00.000+0000, timeSpent=01:00:00h, comment=BTIP-1285 - meeting)
200 OK
```

## Conclusion

This documentation provides a comprehensive guide to setting up and using the Jira Worklog Reporter project. If you have any questions or issues, please refer to the project's repository or contact the maintainer.
