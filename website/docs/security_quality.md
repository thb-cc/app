
# Security & Quality

To improve the security of the application a [CodeQL workflow](https://github.com/thb-cc/app/blob/main/.github/workflows/codeql.yaml) is set up for automatic code analysis.
This workflow is scheduled to run automatically every saturday (but can be triggered manually too).
The workflow action used is [github/codeql-action/analyze@v4](https://github.com/github/codeql-action).

[SonarQube](https://www.sonarsource.com/products/sonarqube/) Cloud is used for an additional layer of static code analysis.
Details about the current project status can be accessed through the [online dashboard](https://sonarcloud.io/project/overview?id=thb-cc_app).

For a simplified overview the [readme](https://github.com/thb-cc/app/blob/main/README.md) contains three badges: for the statuses of the last deployment and CodeQL workflows and for the last SonarCloud quality gate scan.
