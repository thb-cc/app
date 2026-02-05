
# Development

To start local development you need an IDE preferably IntelliJ, VSC or Eclipse. Clone the GitHub repo into a project folder.
Change into the `/app` directory. Run `mvn clean verify` to install all dependecies.
You need to have an AWS Account with a S3 bucket and DynamoDB table. Copy both names into the `application-local.yaml` file.
For testing purposes upload images from your machine or the internet to S3. Do the same with quotes in the DynamoDB table. 
Set your AWS credentials in `~User/.aws/credentials`. 
Start the application by running AppApplication using the profile `local`.
A local Server starts and is available at http://localhost:8443/.
