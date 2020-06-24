job('jenkins-cfn') {
    scm {
        git('git://github.com/Nouman72884/cloudformation-demo.git') {  node -> // is hudson.plugins.git.GitSCM
        node / gitConfigName('Nouman72884')
        node / gitConfigEmail('noumansatti7@gmail.com')
        }
    triggers {
        scm('*/5 * * * *')
        }
        steps {
                shell("echo \"aws cloudformation --stack-name cfn --template-body file:s3cft.yaml --parameters s3://${parametersBucket}/${it['parametersKeyName']}\" > ${it['name']}-cmd.txt")
            }
    withAWS(region:'us-east-1') {
      def response = cfnValidate(file:'s3cft.yaml')
      echo "template description: ${response.description}"

      def outputs = cfnUpdate(stack:'cfn-stack', file:'s3cft.yaml', params:['VpcName=demovpc','ec2Name=noumanec2'], timeoutInMinutes:10,)

        }
    }
}
