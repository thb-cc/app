
# Security

To improve the security of the application a [CodeQL workflow](https://github.com/thb-cc/app/blob/main/.github/workflows/codeql.yaml) is set up for automatic code analysis.
This workflow is scheduled to run automatically every saturday (but can be triggered manually too).
The workflow action used is [github/codeql-action/analyze@v4](https://github.com/github/codeql-action).
