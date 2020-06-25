def AWS_ROLE = "arn:aws:iam::020046395185:role/adminrole"
def AWS_REGION = "us-east-1"
job('jenkins-cfn') {
    scm {
        git('git://github.com/Nouman72884/cloudformation-demo.git') {  node -> // is hudson.plugins.git.GitSCM
        node / gitConfigName('Nouman72884')
        node / gitConfigEmail('noumansatti7@gmail.com')
        }
    triggers {
        scm('*/5 * * * *')
        }
    steps{
        shell("aws cloudformation create-stack --stack-name myteststack --template-body file:s3cft.yaml --parameters ParameterKey=VpcName,ParameterValue=vpcdemo ParameterKey=ec2Name,ParameterValue=test,region=us-east-1")
    }

    }
}
