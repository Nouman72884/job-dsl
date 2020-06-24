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
    withAWS(region:AWS_REGION) {
        def response = cfnValidate(file:'s3cft.yaml')
        echo "template description: ${response.description}"
        def outputs = cfnUpdate(stack:'cfn-stack', file:'s3cft.yaml', params:['VpcName=demovpc','ec2Name=noumanec2'], timeoutInMinutes:10)

        }

    }
}
