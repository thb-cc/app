
# Security & Quality

To improve the security of the application a [CodeQL workflow](https://github.com/thb-cc/app/blob/main/.github/workflows/codeql.yaml) is set up for automatic code analysis.
This workflow is scheduled to run automatically every saturday (but can be triggered manually too).
The workflow action used is [github/codeql-action/analyze@v4](https://github.com/github/codeql-action).

To reduce the attack surface of our container it doesn't run as root but instead as user `spring` in the group `spring`.

[SonarQube](https://www.sonarsource.com/products/sonarqube/) Cloud is used for an additional layer of static code analysis.
Details about the current project status can be accessed through the [online dashboard](https://sonarcloud.io/project/overview?id=thb-cc_app).

For a simplified overview the [readme](https://github.com/thb-cc/app/blob/main/README.md) contains three badges: for the statuses of the last deployment and CodeQL workflows and for the last SonarCloud quality gate scan.

[certbot](https://certbot.eff.org/) is used to provide SSL certificates to the [EC2](https://aws.amazon.com/ec2/) instance via [EIP](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/elastic-ip-addresses-eip.html) in tandem with [sslip.io](https://sslip.io/).
The spring application terminates SSL itself by using the [Spring Boot SSL bundle mechanism](https://docs.spring.io/spring-boot/reference/features/ssl.html).

Connections are secured by utilizing the [IAM](https://aws.amazon.com/iam/) mechanism.
Since the project runs on [AWS Academy](https://aws.amazon.com/training/awsacademy/) the role "LabRole" authorizes everything.
