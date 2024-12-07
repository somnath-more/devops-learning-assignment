# Devops-Learning-Assignment

[![Java CI with Maven](https://github.com/somnath-more/devops-learning-assignment/actions/workflows/github-action-demo.yml/badge.svg)](https://github.com/somnath-more/devops-learning-assignment/actions/workflows/github-action-demo.yml)

## Using variables in your workflows
```yml
jobs:
  example-job:
    runs-on: ubuntu-latest
    steps:
      - name: Connect to PostgreSQL
        run: node client.js
        env:
          POSTGRES_HOST: postgres
          POSTGRES_PORT: 5432
```
## Adding scripts to your workflow
You can use a GitHub Actions workflow to run scripts and shell commands, which are then executed on the assigned runner.
```yml
jobs:
  example-job:
    runs-on: ubuntu-latest
    steps:
      - run: npm install -g bats
```

## Execute a Script with permission
```yml
jobs:
  example-job:
    runs-on: ubuntu-latest
    defaults:
      run:
        working-directory: ./scripts
    steps:
      - name: Check out the repository to the runner
        uses: actions/checkout@v4  
      - name: Make the script files executable
        run: chmod +x my-script.sh my-other-script.sh
      - name: Run the scripts
        run: |
          ./my-script.sh
          ./my-other-script.sh
 ```

 ## Sharing data between jobs
```yml
jobs:
  example-job:
    name: Save output
    runs-on: ubuntu-latest
    steps:
      - shell: bash
        run: |
          expr 1 + 1 > output.log
      - name: Upload output file
        uses: actions/upload-artifact@v4
        with:
          name: output-log-file
          path: output.log
```
 To download an artifact from a separate workflow run, you can use the actions/download-artifact action. For example, you can download the artifact named output-log-file.
 ```yml
 jobs:
  example-job:
    runs-on: ubuntu-latest
    steps:
      - name: Download a single artifact
        uses: actions/download-artifact@v4
        with:
          name: output-log-file
```

## Expressions

## Contexts
You can access context information in workflows and actions.

```yml
name: Context testing
on: push

jobs:
  dump_contexts_to_log:
    runs-on: ubuntu-latest
    steps:
      - name: Dump GitHub context
        env:
          GITHUB_CONTEXT: ${{ toJson(github) }}
        run: echo "$GITHUB_CONTEXT"
      - name: Dump job context
        env:
          JOB_CONTEXT: ${{ toJson(job) }}
        run: echo "$JOB_CONTEXT"
      - name: Dump steps context
        env:
          STEPS_CONTEXT: ${{ toJson(steps) }}
        run: echo "$STEPS_CONTEXT"
      - name: Dump runner context
        env:
          RUNNER_CONTEXT: ${{ toJson(runner) }}
        run: echo "$RUNNER_CONTEXT"
      - name: Dump strategy context
        env:
          STRATEGY_CONTEXT: ${{ toJson(strategy) }}
        run: echo "$STRATEGY_CONTEXT"
      - name: Dump matrix context
        env:
          MATRIX_CONTEXT: ${{ toJson(matrix) }}
        run: echo "$MATRIX_CONTEXT"

```

- The github context contains information about the workflow run and the event that triggered the run

## runner context
The runner context contains information about the runner that is executing the current job..
### secrets context
#### Example contents of the secrets context
The following example contents of the secrets context shows the automatic GITHUB_TOKEN, as well as two other secrets available to the workflow run.
## strategy context
Example usage of the strategy context
This example workflow uses the strategy.job-index property to set a unique name for a log file for each job in a matrix.
### Example usage of the matrix context
This example workflow creates a matrix with os and node keys. It uses the matrix.os property to set the runner type for each job, and uses the matrix.node property to set the Node.js version for each job.

```yml
name: Test matrix
on: push

jobs:
  build:
    runs-on: ${{ matrix.os }}
    strategy:
      matrix:
        os: [ubuntu-latest, windows-latest]
        node: [14, 16]
    steps:
      - uses: actions/setup-node@v4
        with:
          node-version: ${{ matrix.node }}
      - name: Output node version
        run: node --version
```
## Defining environment variables for a single workflow
```yml
name: Greeting on variable day

on:
  workflow_dispatch

env:
  DAY_OF_WEEK: Monday

jobs:
  greeting_job:
    runs-on: ubuntu-latest
    env:
      Greeting: Hello
    steps:
      - name: "Say Hello Mona it's Monday"
        run: echo "$Greeting $First_Name. Today is $DAY_OF_WEEK!"
        env:
          First_Name: Mona
```
## Usage limits, billing, and administration
There are usage limits for GitHub Actions workflows. Usage charges apply to repositories that go beyond the amount of free minutes and storage for a repository.

## Disabling and enabling workflows

## Events that trigger workflows
You can configure your workflows to run when specific activity on GitHub happens, at a scheduled time, or when an event outside of GitHub occurs.
```yml
on:
  pull_request:
    # Sequence of patterns matched against refs/heads
    branches:
      - main
      - 'mona/octocat'
      - 'releases/**'
```

Example: Excluding branches
When a pattern matches the branches-ignore pattern, the workflow will not run. 

```yml
on:
  pull_request:
    # Sequence of patterns matched against refs/heads
    branches-ignore:
      - 'mona/octocat'
      - 'releases/**'
```

The shortest interval you can run scheduled workflows is once every 5 minutes.

## Workflow Syntax 
