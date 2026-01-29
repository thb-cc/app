
# CI / CD

The build and deployment of `thb-cc/app` is handled by the [deploy](https://github.com/thb-cc/app/blob/main/.github/workflows/deploy.yaml) workflow using GitHub actions.
The following major steps are performed by the workflow:
1. Test and package the application using the `Maven Temurin 21 JDK`
2. Build the container image using [Buildx](https://github.com/docker/buildx)
3. Perform a security scan on the image using [Trivy](https://github.com/aquasecurity/trivy)
4. Push the image to [DockerHub](https://hub.docker.com/)
5. Sign the image with the digest of the push task using [Cosign](https://github.com/sigstore/cosign)
6. Transfer the most recent [compose file](https://github.com/thb-cc/app/blob/main/docker-compose.yaml) to the EC2 instance using SCP
7. Deploy the new image to the EC2 instance via SSH by:
    1. Verifying the integrity of the image with Cosign
    2. Saving the full image name in a `.env` file on-server
    3. Performing a restart with `docker compose`
