
# System

The system infrastructure is based on [Amazon AWS](https://aws.amazon.com/) and managed with [Terraform](https://developer.hashicorp.com/terraform) and our [main.tf](https://github.com/thb-cc/app/blob/main/main.tf) file.
The Terraform state is maintained by manually executing `terraform apply` locally, since our AWS access is based on [AWS Academy](https://aws.amazon.com/training/awsacademy/) and thus the credentials change every lab session.
State consistency is achieved by storing the [.terraform.lock.hcl](https://github.com/thb-cc/app/blob/main/.terraform.lock.hcl) file in our repository.

```mermaid
graph TD;
  repo["GitHub</br>thb-cc/app"]

  subgraph aws["AWS VPC"]
    s3["S3"]
    dynamo["DynamoDB"]
    subgraph public["Public Subnet"]
      subgraph ec2["EC2 Instance"]
        certbot
        Cosign
        subgraph docker["Docker (via Compose)"]
          app["Application"]
        end
      end
    end
    subgraph private["Private Subnet"]
      null["..."]
    end
    igw["Internet Gateway"]
    igw --Access--> app
  end

  dev["Developer"]
  user["User"]

  dev --> repo
  user --HTTPS--> igw

  repo --Push & Sign Image--> dhub["DockerHub</br>fdalitz/app"]
  repo --Terraform--> aws
  repo --Deploy--> app
  certbot --Certificates--> app
  Cosign --Verify--> app
  app --Object Storage--> s3
  app --Quote Storage--> dynamo
  app --Pull Image--> dhub
```

The private subnet is prepared for future use but is not actively utilized yet.
